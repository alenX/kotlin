/*
 * Copyright 2010-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.eval4j

import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame
import org.jetbrains.org.objectweb.asm.tree.MethodNode
import org.jetbrains.org.objectweb.asm.Type
import org.jetbrains.org.objectweb.asm.Opcodes.*
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode
import org.jetbrains.org.objectweb.asm.util.Printer
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode
import java.util.ArrayList

trait InterpreterResult {
    override fun toString(): String
}

class ExceptionThrown(val exception: Value): InterpreterResult {
    override fun toString(): String = "Thrown $exception"
}

data class ValueReturned(val result: Value): InterpreterResult {
    override fun toString(): String = "Returned $result"
}

class AbnormalTermination(val message: String): InterpreterResult {
    override fun toString(): String = "Terminated abnormally: $message"
}

trait InterpretationEventHandler {

    class object {
        object NONE : InterpretationEventHandler {
            override fun instructionProcessed(insn: AbstractInsnNode): InterpreterResult? = null
            override fun exceptionThrown(currentState: Frame<Value>, currentInsn: AbstractInsnNode, exception: Value): InterpreterResult? = null
            override fun exceptionCaught(currentState: Frame<Value>, currentInsn: AbstractInsnNode, exception: Value): InterpreterResult? = null
        }
    }

    // If a non-null value is returned, interpreter loop is terminated and that value is used as a result
    fun instructionProcessed(insn: AbstractInsnNode): InterpreterResult?

    fun exceptionThrown(currentState: Frame<Value>, currentInsn: AbstractInsnNode, exception: Value): InterpreterResult?
    fun exceptionCaught(currentState: Frame<Value>, currentInsn: AbstractInsnNode, exception: Value): InterpreterResult?
}

class ThrownFromEvalException(cause: Throwable): RuntimeException(cause) {
    override fun toString(): String = "Thrown by evaluator: ${getCause()}"
}

class ThrownFromEvaluatedCodeException(val exception: Value): RuntimeException() {
    override fun toString(): String = "Thrown from evaluated code: $exception"
}

fun interpreterLoop(
        m: MethodNode,
        initialState: Frame<Value>,
        eval: Eval,
        handler: InterpretationEventHandler = InterpretationEventHandler.NONE
): InterpreterResult {
    val firstInsn = m.instructions.getFirst()
    if (firstInsn == null) throw IllegalArgumentException("Empty method")

    var currentInsn = firstInsn!!

    fun goto(nextInsn: AbstractInsnNode?) {
        if (nextInsn == null) throw IllegalArgumentException("Instruction flow ended with no RETURN")
        currentInsn = nextInsn
    }

    val interpreter = SingleInstructionInterpreter(eval)
    val frame = Frame(initialState)
    val handlers = computeHandlers(m)

    class ResultException(val result: InterpreterResult): RuntimeException()

    fun exceptionCaught(exceptionValue: Value, instanceOf: (Type) -> Boolean): Boolean {
        val catchBlocks = handlers[m.instructions.indexOf(currentInsn)] ?: listOf()
        for (catch in catchBlocks) {
            val exceptionTypeInternalName = catch.`type`
            if (exceptionTypeInternalName != null) {
                val exceptionType = Type.getObjectType(exceptionTypeInternalName)
                if (instanceOf(exceptionType)) {
                    val handled = handler.exceptionCaught(frame, currentInsn, exceptionValue)
                    if (handled != null) throw ResultException(handled)
                    frame.clearStack()
                    frame.push(exceptionValue)
                    goto(catch.handler)
                    return true
                }
            }
        }
        return false
    }

    fun exceptionCaught(exceptionValue: Value): Boolean = exceptionCaught(exceptionValue) {
        exceptionType -> eval.isInstanceOf(exceptionValue, exceptionType)
    }

    fun exceptionFromEvalCaught(exception: Throwable, exceptionValue: Value): Boolean {
        return exceptionCaught(exceptionValue) {
            exceptionType ->
            try {
                val exceptionClass = exception.javaClass
                val _class = Class.forName(
                        exceptionType.getInternalName().replace('/', '.'),
                        true,
                        exceptionClass.getClassLoader()
                )
                _class.isAssignableFrom(exceptionClass)
            }
            catch (e: ClassNotFoundException) {
                // If the class is not available in this VM, it can not be a superclass of an exception trown in it
                false
            }
        }
    }

    try {
        while (true) {
            val insnOpcode = currentInsn.getOpcode()
            val insnType = currentInsn.getType()

            when (insnType) {
                AbstractInsnNode.LABEL,
                AbstractInsnNode.FRAME,
                AbstractInsnNode.LINE -> {
                    // skip to the next instruction
                }

                else -> {
                    when (insnOpcode) {
                        GOTO -> {
                            goto((currentInsn as JumpInsnNode).label)
                            continue
                        }

                        RET -> {
                            val varNode = currentInsn as VarInsnNode
                            val address = frame.getLocal(varNode.`var`)
                            goto((address as LabelValue).value)
                            continue
                        }

                        // TODO: switch
                        LOOKUPSWITCH -> UnsupportedByteCodeException("LOOKUPSWITCH is not supported yet")
                        TABLESWITCH -> UnsupportedByteCodeException("TABLESWITCH is not supported yet")

                        IRETURN, LRETURN, FRETURN, DRETURN, ARETURN -> {
                            val value = frame.getStack(0)!!
                            val expectedType = Type.getReturnType(m.desc)
                            if (expectedType.getSort() == Type.OBJECT) {
                                val coerced = if (value != NULL_VALUE && value.asmType != expectedType)
                                                    ObjectValue(value.obj(), expectedType)
                                              else value
                                return ValueReturned(coerced)
                            }
                            if (value.asmType != expectedType) {
                                assert(insnOpcode == IRETURN, "Only ints should be coerced: " + Printer.OPCODES[insnOpcode])

                                val coerced = when (expectedType.getSort()) {
                                    Type.BOOLEAN -> boolean(value.boolean)
                                    Type.BYTE -> byte(value.int.toByte())
                                    Type.SHORT -> short(value.int.toShort())
                                    Type.CHAR -> char(value.int.toChar())
                                    else -> throw UnsupportedByteCodeException("Should not be coerced: $expectedType")
                                }
                                return ValueReturned(coerced)
                            }
                            return ValueReturned(value)
                        }
                        RETURN -> return ValueReturned(VOID_VALUE)
                        IFEQ, IFNE, IFLT, IFGE, IFGT, IFLE, IFNULL, IFNONNULL -> {
                            if (interpreter.checkUnaryCondition(frame.getStack(0)!!, insnOpcode)) {
                                frame.execute(currentInsn, interpreter)
                                goto((currentInsn as JumpInsnNode).label)
                                continue
                            }
                        }
                        IF_ICMPEQ, IF_ICMPNE, IF_ICMPLT, IF_ICMPGE, IF_ICMPGT, IF_ICMPLE, IF_ACMPEQ, IF_ACMPNE -> {
                            if (interpreter.checkBinaryCondition(frame.getStack(0)!!, frame.getStack(1)!!, insnOpcode)) {
                                frame.execute(currentInsn, interpreter)
                                goto((currentInsn as JumpInsnNode).label)
                                continue
                            }
                        }

                        ATHROW -> {
                            val exceptionValue = frame.getStack(0)!!
                            val handled = handler.exceptionThrown(frame, currentInsn, exceptionValue)
                            if (handled != null) return handled
                            if (exceptionCaught(exceptionValue)) continue
                            return ExceptionThrown(exceptionValue)
                        }

                        // Workaround for a bug in Kotlin: NoPatterMatched exception is thrown otherwise!
                        else -> {}
                    }

                    try {
                        frame.execute(currentInsn, interpreter)
                    }
                    catch (e: ThrownFromEvalException) {
                        val exception = e.getCause()!!
                        val exceptionValue = ObjectValue(exception, Type.getType(exception.javaClass))
                        val handled = handler.exceptionThrown(frame, currentInsn,
                                exceptionValue)
                        if (handled != null) return handled
                        if (exceptionFromEvalCaught(exception, exceptionValue)) continue
                        return ExceptionThrown(exceptionValue)
                    }
                    catch (e: ThrownFromEvaluatedCodeException) {
                        val handled = handler.exceptionThrown(frame, currentInsn, e.exception)
                        if (handled != null) return handled
                        if (exceptionCaught(e.exception)) continue
                        return ExceptionThrown(e.exception)
                    }
                }
            }

            val handled = handler.instructionProcessed(currentInsn)
            if (handled != null) return handled

            goto(currentInsn.getNext())
        }
    }
    catch(e: ResultException) {
        return e.result
    }
}

// Copied from org.jetbrains.org.objectweb.asm.tree.analysis.Analyzer.analyze()
fun computeHandlers(m: MethodNode): Array<out List<TryCatchBlockNode>?> {
    val insns = m.instructions
    val handlers = Array<MutableList<TryCatchBlockNode>?>(insns.size()) {null}
    for (tcb in m.tryCatchBlocks) {
        val begin = insns.indexOf(tcb.start)
        val end = insns.indexOf(tcb.end)
        for (i in begin..end - 1) {
            val insnHandlers = handlers[i] ?: ArrayList<TryCatchBlockNode>()
            handlers[i] = insnHandlers

            insnHandlers.add(tcb)
        }
    }

    return handlers
}