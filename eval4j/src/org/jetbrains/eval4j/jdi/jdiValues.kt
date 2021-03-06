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

package org.jetbrains.eval4j.jdi

import org.jetbrains.eval4j.*
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame
import org.jetbrains.org.objectweb.asm.tree.MethodNode
import org.jetbrains.org.objectweb.asm.Type
import org.jetbrains.org.objectweb.asm.Opcodes.*
import com.sun.jdi

fun makeInitialFrame(methodNode: MethodNode, arguments: List<Value>): Frame<Value> {
    val isStatic = (methodNode.access and ACC_STATIC) != 0
    assert(isStatic, "Instance methods are not supported: $methodNode")

    val params = Type.getArgumentTypes(methodNode.desc)
    assert(params.size == arguments.size(), "Wrong number of arguments for $methodNode: $arguments")

    val frame = Frame<Value>(methodNode.maxLocals, methodNode.maxStack)
    frame.setReturn(makeNotInitializedValue(Type.getReturnType(methodNode.desc)))

    for ((i, arg) in arguments.withIndices()) {
        frame.setLocal(i, arg)
    }

    for (i in arguments.size..methodNode.maxLocals - 1) {
        frame.setLocal(i, NOT_A_VALUE)
    }
    
    return frame
}

class JDIFailureException(message: String?, cause: Throwable? = null): RuntimeException(message, cause)

fun <T: Any> T?.sure(message: String? = null): T = this ?: throw JDIFailureException(message)

fun jdi.Value?.asValue(): Value {
    return when (this) {
        null -> NULL_VALUE
        is jdi.VoidValue -> VOID_VALUE
        is jdi.BooleanValue -> IntValue(intValue(), Type.BOOLEAN_TYPE)
        is jdi.ByteValue -> IntValue(intValue(), Type.BYTE_TYPE)
        is jdi.ShortValue -> IntValue(intValue(), Type.SHORT_TYPE)
        is jdi.CharValue -> IntValue(intValue(), Type.CHAR_TYPE)
        is jdi.IntegerValue -> IntValue(intValue(), Type.INT_TYPE)
        is jdi.LongValue -> LongValue(longValue())
        is jdi.FloatValue -> FloatValue(floatValue())
        is jdi.DoubleValue -> DoubleValue(doubleValue())
        is jdi.ObjectReference -> ObjectValue(this, `type`().asType())
        else -> throw JDIFailureException("Unknown value: $this")
    }
}

fun jdi.Type.asType(): Type = Type.getType(this.signature())

val Value.jdiObj: jdi.ObjectReference?
    get() = this.obj() as jdi.ObjectReference?

val Value.jdiClass: jdi.ClassObjectReference?
    get() = this.jdiObj as jdi.ClassObjectReference?

fun Value.asJdiValue(vm: jdi.VirtualMachine, expectedType: Type): jdi.Value? {
    return when (this) {
        NULL_VALUE -> null
        VOID_VALUE -> vm.mirrorOfVoid()
        is IntValue -> when (expectedType) {
            Type.BOOLEAN_TYPE -> vm.mirrorOf(boolean)
            Type.BYTE_TYPE -> vm.mirrorOf(int.toByte())
            Type.SHORT_TYPE -> vm.mirrorOf(int.toShort())
            Type.CHAR_TYPE -> vm.mirrorOf(int.toChar())
            Type.INT_TYPE -> vm.mirrorOf(int)
            else -> throw JDIFailureException("Unknown value type: $this")
        }
        is LongValue -> vm.mirrorOf(value)
        is FloatValue -> vm.mirrorOf(value)
        is DoubleValue -> vm.mirrorOf(value)
        is ObjectValue -> value as jdi.ObjectReference
        is NewObjectValue -> this.obj() as jdi.ObjectReference
        else -> throw JDIFailureException("Unknown value: $this")
    }
}