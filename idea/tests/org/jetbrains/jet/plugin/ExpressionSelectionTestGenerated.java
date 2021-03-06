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

package org.jetbrains.jet.plugin;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.io.File;
import java.util.regex.Pattern;
import org.jetbrains.jet.JetTestUtils;
import org.jetbrains.jet.test.InnerTestClasses;
import org.jetbrains.jet.test.TestMetadata;

import org.jetbrains.jet.plugin.AbstractExpressionSelectionTest;

/** This class is generated by {@link org.jetbrains.jet.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("idea/testData/expressionSelection")
public class ExpressionSelectionTestGenerated extends AbstractExpressionSelectionTest {
    public void testAllFilesPresentInExpressionSelection() throws Exception {
        JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.TestsPackage", new File("idea/testData/expressionSelection"), Pattern.compile("^([^\\.]+)\\.kt$"), true);
    }
    
    @TestMetadata("binaryExpr.kt")
    public void testBinaryExpr() throws Exception {
        doTestExpressionSelection("idea/testData/expressionSelection/binaryExpr.kt");
    }
    
    @TestMetadata("labelledStatement.kt")
    public void testLabelledStatement() throws Exception {
        doTestExpressionSelection("idea/testData/expressionSelection/labelledStatement.kt");
    }
    
    @TestMetadata("labelledThis.kt")
    public void testLabelledThis() throws Exception {
        doTestExpressionSelection("idea/testData/expressionSelection/labelledThis.kt");
    }
    
    @TestMetadata("noExpression.kt")
    public void testNoExpression() throws Exception {
        doTestExpressionSelection("idea/testData/expressionSelection/noExpression.kt");
    }
    
}
