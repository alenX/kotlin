/*
 * Copyright 2010-2013 JetBrains s.r.o.
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

package org.jetbrains.jet.jvm.compiler;

import org.jetbrains.jet.test.TestCaseWithTmpdir;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static org.jetbrains.jet.JetTestUtils.compileKotlinWithJava;

@SuppressWarnings({"JUnitTestCaseWithNonTrivialConstructors", "JUnitTestCaseWithNoTests"})
public abstract class AbstractCompileJavaAgainstKotlinTest extends TestCaseWithTmpdir {
    protected void doTest(String ktFilePath) throws IOException {
        Assert.assertTrue(ktFilePath.endsWith(".kt"));
        File ktFile = new File(ktFilePath);
        File javaFile = new File(ktFilePath.replaceFirst("\\.kt", ".java"));
        compileKotlinWithJava(Collections.singletonList(javaFile), Collections.singletonList(ktFile), tmpdir, getTestRootDisposable());
    }
}
