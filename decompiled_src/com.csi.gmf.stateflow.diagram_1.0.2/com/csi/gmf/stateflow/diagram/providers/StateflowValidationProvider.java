/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider
 *  org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.action.IAction
 */
package com.csi.gmf.stateflow.diagram.providers;

import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
import com.csi.gmf.stateflow.diagram.part.ValidateAction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;

public class StateflowValidationProvider
extends AbstractContributionItemProvider {
    private static boolean constraintsActive = false;

    public static boolean shouldConstraintsBePrivate() {
        return false;
    }

    public static void runWithConstraints(TransactionalEditingDomain editingDomain, Runnable operation) {
        final Runnable op = operation;
        Runnable task = new Runnable(){

            @Override
            public void run() {
                try {
                    constraintsActive = true;
                    op.run();
                }
                finally {
                    constraintsActive = false;
                }
            }
        };
        if (editingDomain != null) {
            try {
                editingDomain.runExclusive(task);
            }
            catch (Exception e) {
                StateflowDiagramEditorPlugin.getInstance().logError("Validation failed", e);
            }
        } else {
            task.run();
        }
    }

    protected IAction createAction(String actionId, IWorkbenchPartDescriptor partDescriptor) {
        if ("validateAction".equals(actionId)) {
            return new ValidateAction(partDescriptor);
        }
        return super.createAction(actionId, partDescriptor);
    }

    static boolean isInDefaultEditorContext(Object object) {
        if (StateflowValidationProvider.shouldConstraintsBePrivate() && !constraintsActive) {
            return false;
        }
        if (object instanceof View) {
            return constraintsActive && "Stateflow".equals(StateflowVisualIDRegistry.getModelID((View)object));
        }
        return true;
    }
}

