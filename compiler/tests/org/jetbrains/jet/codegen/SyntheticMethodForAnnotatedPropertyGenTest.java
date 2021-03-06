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

package org.jetbrains.jet.codegen;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jet.ConfigurationKind;
import org.jetbrains.jet.OutputFile;
import org.jetbrains.jet.lang.resolve.java.JvmAbi;
import org.jetbrains.jet.lang.resolve.java.PackageClassUtils;
import org.jetbrains.jet.lang.resolve.name.FqName;
import org.jetbrains.jet.lang.resolve.name.Name;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SyntheticMethodForAnnotatedPropertyGenTest extends CodegenTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createEnvironmentWithMockJdkAndIdeaAnnotations(ConfigurationKind.JDK_ONLY);
    }

    @NotNull
    @Override
    protected String getPrefix() {
        return "properties/syntheticMethod";
    }

    private static final String TEST_SYNTHETIC_METHOD_NAME = JvmAbi.getSyntheticMethodNameForAnnotatedProperty(Name.identifier("property"));

    public void testInClass() {
        loadFile();
        assertAnnotatedSyntheticMethodExistence(true, generateClass("A"));
    }

    public void testTopLevel() {
        loadFile();
        String packageClassName = PackageClassUtils.getPackageClassName(FqName.ROOT);
        for (OutputFile outputFile : generateClassesInFile().asList()) {
            String filPath = outputFile.getRelativePath();

            if (filPath.startsWith(packageClassName) && !filPath.equals(packageClassName + ".class")) {
                // This should be package$src class
                Class<?> a = generateClass(filPath.substring(0, filPath.length() - ".class".length()));
                assertAnnotatedSyntheticMethodExistence(true, a);
            }
        }
    }

    public void testInTrait() throws ClassNotFoundException {
        loadFile();
        GeneratedClassLoader loader = generateAndCreateClassLoader();
        assertAnnotatedSyntheticMethodExistence(false, loader.loadClass("T"));
        assertAnnotatedSyntheticMethodExistence(true, loader.loadClass("T" + JvmAbi.TRAIT_IMPL_SUFFIX));
    }

    private static void assertAnnotatedSyntheticMethodExistence(boolean expected, @NotNull Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (TEST_SYNTHETIC_METHOD_NAME.equals(method.getName())) {
                if (!expected) {
                    fail("Synthetic method for annotated property found, but not expected: " + method);
                }
                assertTrue(method.isSynthetic());
                int modifiers = method.getModifiers();
                assertTrue(Modifier.isFinal(modifiers));
                assertTrue(Modifier.isStatic(modifiers));
                assertTrue(Modifier.isPrivate(modifiers));

                Annotation[] annotations = method.getDeclaredAnnotations();
                assertSize(1, annotations);
                assertEquals("@SomeAnnotation(value=OK)", annotations[0].toString());
                return;
            }
        }
        if (expected) {
            fail("Synthetic method for annotated property expected, but not found: " + TEST_SYNTHETIC_METHOD_NAME);
        }
    }
}
