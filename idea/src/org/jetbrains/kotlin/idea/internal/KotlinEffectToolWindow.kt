/*
 * Copyright 2010-2017 JetBrains s.r.o.
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

package org.jetbrains.kotlin.idea.internal

import com.intellij.ide.highlighter.JavaFileType
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Computable
import com.intellij.openapi.util.text.StringUtil
import com.intellij.openapi.wm.ToolWindow
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.Alarm
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.effects.facade.EffectSystem
import org.jetbrains.kotlin.effects.facade.EsResolutionUtils
import org.jetbrains.kotlin.effects.facade.adapters.CallTreeBuilder
import org.jetbrains.kotlin.effects.structure.call.CtCall
import org.jetbrains.kotlin.effects.structure.call.CtNode
import org.jetbrains.kotlin.idea.caches.resolve.*
import org.jetbrains.kotlin.idea.util.InfinitePeriodicalTask
import org.jetbrains.kotlin.idea.util.LongRunningReadTask
import org.jetbrains.kotlin.idea.util.ProjectRootsUtil
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.callUtil.*
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall

import javax.swing.*
import java.awt.*

class KotlinEffectToolWindow(private val myProject: Project, private val toolWindow: ToolWindow) : JPanel(BorderLayout()), Disposable {
    private val LOG = Logger.getInstance(KotlinEffectToolWindow::class.java)

    private inner class UpdateEffectsToolWindowTask : LongRunningReadTask<ResolvedCall<out CallableDescriptor>, String>() {
        lateinit private var esResolutionUtils: EsResolutionUtils

        override fun prepareRequestInfo(): ResolvedCall<out CallableDescriptor>? {
            if (!toolWindow.isVisible) return null

            val location = Location.fromEditor(FileEditorManager.getInstance(myProject).selectedTextEditor, myProject) ?: return null

            val file = location.kFile ?: return null
            if (!ProjectRootsUtil.isInProjectSource(file)) return null

            val element = file.findElementAt(location.getStartOffset()) ?: return null
            val call = PsiTreeUtil.getParentOfType(element, KtCallExpression::class.java) ?: return null

            val context = call.analyze()
            val resolvedCall = call.getResolvedCall(context) ?: return null
            val moduleDescriptor = call.findModuleDescriptor()

            esResolutionUtils = EsResolutionUtils(context, KtPsiFactory(element.project), null, moduleDescriptor)

            return resolvedCall
        }

        override fun processRequest(call: ResolvedCall<out CallableDescriptor>): String? {
            val node = CallTreeBuilder(esResolutionUtils).buildCallTree(call) ?: return null

            return EffectSystem.printES(node, esResolutionUtils)
        }

        override fun onResultReady(call: ResolvedCall<out CallableDescriptor>, resultText: String?) {
            resultText ?: return

            ApplicationManager.getApplication().runWriteAction { myEditor.document.setText(StringUtil.convertLineSeparators(resultText)) }
        }
    }


    private val myEditor: Editor = EditorFactory.getInstance().createEditor(
            EditorFactory.getInstance().createDocument(""),
            myProject,
            JavaFileType.INSTANCE,
            true
    )

    init {
        add(myEditor.component)
        InfinitePeriodicalTask(UPDATE_DELAY.toLong(), Alarm.ThreadToUse.SWING_THREAD, this, Computable<LongRunningReadTask<*, *>> { UpdateEffectsToolWindowTask() }).start()
    }

    override fun dispose() {
        EditorFactory.getInstance().releaseEditor(myEditor)
    }

    companion object {
        val UPDATE_DELAY = 1000
    }
}