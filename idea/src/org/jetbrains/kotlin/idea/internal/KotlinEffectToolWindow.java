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

package org.jetbrains.kotlin.idea.internal;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Alarm;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.effects.facade.EffectSystem;
import org.jetbrains.kotlin.effects.facade.adapters.CallTreeBuilder;
import org.jetbrains.kotlin.effects.structure.call.CtCall;
import org.jetbrains.kotlin.effects.structure.call.CtNode;
import org.jetbrains.kotlin.idea.caches.resolve.ResolutionUtils;
import org.jetbrains.kotlin.idea.util.InfinitePeriodicalTask;
import org.jetbrains.kotlin.idea.util.LongRunningReadTask;
import org.jetbrains.kotlin.idea.util.ProjectRootsUtil;
import org.jetbrains.kotlin.psi.KtCallExpression;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.calls.callUtil.CallUtilKt;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;

import javax.swing.*;
import java.awt.*;

public class KotlinEffectToolWindow extends JPanel implements Disposable {
    private final Logger LOG = Logger.getInstance(KotlinEffectToolWindow.class);
    public static final int UPDATE_DELAY = 1000;

    private class UpdateEffectsToolWindowTask extends LongRunningReadTask< ResolvedCall<? extends CallableDescriptor>, String> {
        @Nullable
        private BindingContext context;

        @Nullable
        @Override
        protected ResolvedCall<? extends CallableDescriptor> prepareRequestInfo() {
            if (!toolWindow.isVisible()) {
                return null;
            }

            Location location = Location.fromEditor(FileEditorManager.getInstance(myProject).getSelectedTextEditor(), myProject);
            if (location.getEditor() == null) {
                return null;
            }

            KtFile file = location.getKFile();
            if (file == null || !ProjectRootsUtil.isInProjectSource(file)) {
                return null;
            }

            PsiElement element = file.findElementAt(location.getStartOffset());
            KtCallExpression call = PsiTreeUtil.getParentOfType(element, KtCallExpression.class);
            if (call == null) {
                return null;
            }

            context = ResolutionUtils.analyze(call);

            ResolvedCall<? extends CallableDescriptor> resolvedCall = CallUtilKt.getResolvedCall(call, context);
            return resolvedCall;
        }

        @Nullable
        @Override
        protected String processRequest(@NotNull ResolvedCall<? extends CallableDescriptor> call) {
            if (context == null) {
                return null;
            }

            CtNode node = (new CallTreeBuilder(context)).buildCallTree(call);
            if (node == null) return null;

            return EffectSystem.INSTANCE.printES((CtCall) node);
        }

        @Override
        protected void onResultReady(@NotNull ResolvedCall<? extends CallableDescriptor> call, @Nullable final String resultText) {
            if (resultText == null) {
                return;
            }

            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                @Override
                public void run() {
                    myEditor.getDocument().setText(StringUtil.convertLineSeparators(resultText));
                }
            });
        }
    }

    private final Editor myEditor;
    private final Project myProject;
    private final ToolWindow toolWindow;

    public KotlinEffectToolWindow(Project project, ToolWindow toolWindow) {
        super(new BorderLayout());
        myProject = project;
        this.toolWindow = toolWindow;

        myEditor = EditorFactory.getInstance().createEditor(
                EditorFactory.getInstance().createDocument(""),
                project,
                JavaFileType.INSTANCE,
                true
        );

        add(myEditor.getComponent());

        new InfinitePeriodicalTask(UPDATE_DELAY, Alarm.ThreadToUse.SWING_THREAD, this, new Computable<LongRunningReadTask>() {
            @Override
            public LongRunningReadTask compute() {
                return new UpdateEffectsToolWindowTask();
            }
        }).start();
    }
    @Override
    public void dispose() {
        EditorFactory.getInstance().releaseEditor(myEditor);
    }
}
