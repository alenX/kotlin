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

package org.jetbrains.jet.completion.handlers;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.io.File;
import java.util.regex.Pattern;
import org.jetbrains.jet.JetTestUtils;
import org.jetbrains.jet.test.InnerTestClasses;
import org.jetbrains.jet.test.TestMetadata;

import org.jetbrains.jet.completion.handlers.AbstractSmartCompletionHandlerTest;

/** This class is generated by {@link org.jetbrains.jet.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("idea/testData/completion/handlers/smart")
public class SmartCompletionHandlerTestGenerated extends AbstractSmartCompletionHandlerTest {
    public void testAllFilesPresentInSmart() throws Exception {
        JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.TestsPackage", new File("idea/testData/completion/handlers/smart"), Pattern.compile("^(.+)\\.kt$"), true);
    }
    
    @TestMetadata("AnonymousObject1.kt")
    public void testAnonymousObject1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/AnonymousObject1.kt");
    }
    
    @TestMetadata("AnonymousObject2.kt")
    public void testAnonymousObject2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/AnonymousObject2.kt");
    }
    
    @TestMetadata("AnonymousObject3.kt")
    public void testAnonymousObject3() throws Exception {
        doTest("idea/testData/completion/handlers/smart/AnonymousObject3.kt");
    }
    
    @TestMetadata("AnonymousObjectInsertsImport.kt")
    public void testAnonymousObjectInsertsImport() throws Exception {
        doTest("idea/testData/completion/handlers/smart/AnonymousObjectInsertsImport.kt");
    }
    
    @TestMetadata("ClassObjectMethod1.kt")
    public void testClassObjectMethod1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ClassObjectMethod1.kt");
    }
    
    @TestMetadata("ClassObjectMethod2.kt")
    public void testClassObjectMethod2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ClassObjectMethod2.kt");
    }
    
    @TestMetadata("ClassObjectMethod3.kt")
    public void testClassObjectMethod3() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ClassObjectMethod3.kt");
    }
    
    @TestMetadata("ClassObjectMethod4.kt")
    public void testClassObjectMethod4() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ClassObjectMethod4.kt");
    }
    
    @TestMetadata("ClosingParenthesis1.kt")
    public void testClosingParenthesis1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ClosingParenthesis1.kt");
    }
    
    @TestMetadata("ClosingParenthesis2.kt")
    public void testClosingParenthesis2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ClosingParenthesis2.kt");
    }
    
    @TestMetadata("Comma1.kt")
    public void testComma1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Comma1.kt");
    }
    
    @TestMetadata("Comma2.kt")
    public void testComma2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Comma2.kt");
    }
    
    @TestMetadata("Comma3.kt")
    public void testComma3() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Comma3.kt");
    }
    
    @TestMetadata("Comma4.kt")
    public void testComma4() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Comma4.kt");
    }
    
    @TestMetadata("Comma5.kt")
    public void testComma5() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Comma5.kt");
    }
    
    @TestMetadata("Comma6.kt")
    public void testComma6() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Comma6.kt");
    }
    
    @TestMetadata("Comma7.kt")
    public void testComma7() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Comma7.kt");
    }
    
    @TestMetadata("Comma8.kt")
    public void testComma8() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Comma8.kt");
    }
    
    @TestMetadata("Comma9.kt")
    public void testComma9() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Comma9.kt");
    }
    
    @TestMetadata("Constructor.kt")
    public void testConstructor() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Constructor.kt");
    }
    
    @TestMetadata("ConstructorForGenericType.kt")
    public void testConstructorForGenericType() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ConstructorForGenericType.kt");
    }
    
    @TestMetadata("ConstructorForJavaClass.kt")
    public void testConstructorForJavaClass() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ConstructorForJavaClass.kt");
    }
    
    @TestMetadata("ConstructorForNullable.kt")
    public void testConstructorForNullable() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ConstructorForNullable.kt");
    }
    
    @TestMetadata("ConstructorInsertsImport.kt")
    public void testConstructorInsertsImport() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ConstructorInsertsImport.kt");
    }
    
    @TestMetadata("ConstructorInsertsImport2.kt")
    public void testConstructorInsertsImport2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ConstructorInsertsImport2.kt");
    }
    
    @TestMetadata("ConstructorWithLambdaParameter1.kt")
    public void testConstructorWithLambdaParameter1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ConstructorWithLambdaParameter1.kt");
    }
    
    @TestMetadata("ConstructorWithLambdaParameter2.kt")
    public void testConstructorWithLambdaParameter2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ConstructorWithLambdaParameter2.kt");
    }
    
    @TestMetadata("ConstructorWithParameters.kt")
    public void testConstructorWithParameters() throws Exception {
        doTest("idea/testData/completion/handlers/smart/ConstructorWithParameters.kt");
    }
    
    @TestMetadata("DoNotEraseBraceOnTab.kt")
    public void testDoNotEraseBraceOnTab() throws Exception {
        doTest("idea/testData/completion/handlers/smart/DoNotEraseBraceOnTab.kt");
    }
    
    @TestMetadata("DoNotReplaceOnEnter.kt")
    public void testDoNotReplaceOnEnter() throws Exception {
        doTest("idea/testData/completion/handlers/smart/DoNotReplaceOnEnter.kt");
    }
    
    @TestMetadata("EnumMember.kt")
    public void testEnumMember() throws Exception {
        doTest("idea/testData/completion/handlers/smart/EnumMember.kt");
    }
    
    @TestMetadata("FunctionReference1.kt")
    public void testFunctionReference1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/FunctionReference1.kt");
    }
    
    @TestMetadata("FunctionReference2.kt")
    public void testFunctionReference2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/FunctionReference2.kt");
    }
    
    @TestMetadata("GenericFunction.kt")
    public void testGenericFunction() throws Exception {
        doTest("idea/testData/completion/handlers/smart/GenericFunction.kt");
    }
    
    @TestMetadata("IfCondition.kt")
    public void testIfCondition() throws Exception {
        doTest("idea/testData/completion/handlers/smart/IfCondition.kt");
    }
    
    @TestMetadata("IfValue1.kt")
    public void testIfValue1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/IfValue1.kt");
    }
    
    @TestMetadata("IfValue2.kt")
    public void testIfValue2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/IfValue2.kt");
    }
    
    @TestMetadata("IfValue3.kt")
    public void testIfValue3() throws Exception {
        doTest("idea/testData/completion/handlers/smart/IfValue3.kt");
    }
    
    @TestMetadata("IfValueInBlock.kt")
    public void testIfValueInBlock() throws Exception {
        doTest("idea/testData/completion/handlers/smart/IfValueInBlock.kt");
    }
    
    @TestMetadata("InElvisOperator.kt")
    public void testInElvisOperator() throws Exception {
        doTest("idea/testData/completion/handlers/smart/InElvisOperator.kt");
    }
    
    @TestMetadata("JavaEnumMemberInsertsImport.kt")
    public void testJavaEnumMemberInsertsImport() throws Exception {
        doTest("idea/testData/completion/handlers/smart/JavaEnumMemberInsertsImport.kt");
    }
    
    @TestMetadata("JavaStaticField.kt")
    public void testJavaStaticField() throws Exception {
        doTest("idea/testData/completion/handlers/smart/JavaStaticField.kt");
    }
    
    @TestMetadata("JavaStaticFieldInsertImport.kt")
    public void testJavaStaticFieldInsertImport() throws Exception {
        doTest("idea/testData/completion/handlers/smart/JavaStaticFieldInsertImport.kt");
    }
    
    @TestMetadata("JavaStaticMethod.kt")
    public void testJavaStaticMethod() throws Exception {
        doTest("idea/testData/completion/handlers/smart/JavaStaticMethod.kt");
    }
    
    @TestMetadata("JavaStaticMethodInsertsImport.kt")
    public void testJavaStaticMethodInsertsImport() throws Exception {
        doTest("idea/testData/completion/handlers/smart/JavaStaticMethodInsertsImport.kt");
    }
    
    @TestMetadata("Lambda1.kt")
    public void testLambda1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Lambda1.kt");
    }
    
    @TestMetadata("Lambda2.kt")
    public void testLambda2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Lambda2.kt");
    }
    
    @TestMetadata("Lambda3.kt")
    public void testLambda3() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Lambda3.kt");
    }
    
    @TestMetadata("Lambda4.kt")
    public void testLambda4() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Lambda4.kt");
    }
    
    @TestMetadata("Lambda5.kt")
    public void testLambda5() throws Exception {
        doTest("idea/testData/completion/handlers/smart/Lambda5.kt");
    }
    
    @TestMetadata("LambdaInsertImport.kt")
    public void testLambdaInsertImport() throws Exception {
        doTest("idea/testData/completion/handlers/smart/LambdaInsertImport.kt");
    }
    
    @TestMetadata("MergeTail1.kt")
    public void testMergeTail1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/MergeTail1.kt");
    }
    
    @TestMetadata("MergeTail2.kt")
    public void testMergeTail2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/MergeTail2.kt");
    }
    
    @TestMetadata("MergeTail3.kt")
    public void testMergeTail3() throws Exception {
        doTest("idea/testData/completion/handlers/smart/MergeTail3.kt");
    }
    
    @TestMetadata("MergeTail4.kt")
    public void testMergeTail4() throws Exception {
        doTest("idea/testData/completion/handlers/smart/MergeTail4.kt");
    }
    
    @TestMetadata("NullableValue1.kt")
    public void testNullableValue1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/NullableValue1.kt");
    }
    
    @TestMetadata("NullableValue2.kt")
    public void testNullableValue2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/NullableValue2.kt");
    }
    
    @TestMetadata("NullableValue3.kt")
    public void testNullableValue3() throws Exception {
        doTest("idea/testData/completion/handlers/smart/NullableValue3.kt");
    }
    
    @TestMetadata("NullableValueKeepOldArguments.kt")
    public void testNullableValueKeepOldArguments() throws Exception {
        doTest("idea/testData/completion/handlers/smart/NullableValueKeepOldArguments.kt");
    }
    
    @TestMetadata("SAMExpected1.kt")
    public void testSAMExpected1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/SAMExpected1.kt");
    }
    
    @TestMetadata("TabReplaceComma1.kt")
    public void testTabReplaceComma1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/TabReplaceComma1.kt");
    }
    
    @TestMetadata("TabReplaceComma2.kt")
    public void testTabReplaceComma2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/TabReplaceComma2.kt");
    }
    
    @TestMetadata("TabReplaceExpression.kt")
    public void testTabReplaceExpression() throws Exception {
        doTest("idea/testData/completion/handlers/smart/TabReplaceExpression.kt");
    }
    
    @TestMetadata("TabReplaceExpression2.kt")
    public void testTabReplaceExpression2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/TabReplaceExpression2.kt");
    }
    
    @TestMetadata("TabReplaceExpression3.kt")
    public void testTabReplaceExpression3() throws Exception {
        doTest("idea/testData/completion/handlers/smart/TabReplaceExpression3.kt");
    }
    
    @TestMetadata("TabReplaceExpression4.kt")
    public void testTabReplaceExpression4() throws Exception {
        doTest("idea/testData/completion/handlers/smart/TabReplaceExpression4.kt");
    }
    
    @TestMetadata("TabReplaceFunctionName1.kt")
    public void testTabReplaceFunctionName1() throws Exception {
        doTest("idea/testData/completion/handlers/smart/TabReplaceFunctionName1.kt");
    }
    
    @TestMetadata("TabReplaceFunctionName2.kt")
    public void testTabReplaceFunctionName2() throws Exception {
        doTest("idea/testData/completion/handlers/smart/TabReplaceFunctionName2.kt");
    }
    
    @TestMetadata("TabReplaceFunctionName3.kt")
    public void testTabReplaceFunctionName3() throws Exception {
        doTest("idea/testData/completion/handlers/smart/TabReplaceFunctionName3.kt");
    }
    
    @TestMetadata("TabReplaceIdentifier.kt")
    public void testTabReplaceIdentifier() throws Exception {
        doTest("idea/testData/completion/handlers/smart/TabReplaceIdentifier.kt");
    }
    
    @TestMetadata("TabReplaceOperand.kt")
    public void testTabReplaceOperand() throws Exception {
        doTest("idea/testData/completion/handlers/smart/TabReplaceOperand.kt");
    }
    
    @TestMetadata("True.kt")
    public void testTrue() throws Exception {
        doTest("idea/testData/completion/handlers/smart/True.kt");
    }
    
}
