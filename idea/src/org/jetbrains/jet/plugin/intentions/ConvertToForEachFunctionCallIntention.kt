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

package org.jetbrains.jet.plugin.intentions

import org.jetbrains.jet.lang.psi.JetForExpression
import com.intellij.openapi.editor.Editor
import org.jetbrains.jet.lang.psi.JetPsiFactory
import org.jetbrains.jet.lang.psi.JetBlockExpression

public class ConvertToForEachFunctionCallIntention : JetSelfTargetingIntention<JetForExpression>("convert.to.for.each.function.call.intention", javaClass()) {
    override fun isApplicableTo(element: JetForExpression): Boolean {
        return element.getLoopRange() != null && element.getLoopParameter() != null && element.getBody() != null
    }

    override fun applyTo(element: JetForExpression, editor: Editor) {
        val body = element.getBody()!!
        val bodyText = when (body) {
            is JetBlockExpression ->
                if (body.getStatements().size() > 0)
                    "${element.getLoopParameter()!!.getText()} -> ${body.getStatements().fold(StringBuilder(), { acc, h -> acc.append("${h.getText()}\n") }).toString()}"
                else
                    ""
            else -> "${element.getLoopParameter()!!.getText()} -> ${body.getText()}"
        }

        element.replace(JetPsiFactory.createExpression(element.getProject(), "${element.getLoopRange()!!.getText()}.forEach { $bodyText }"))
    }
}