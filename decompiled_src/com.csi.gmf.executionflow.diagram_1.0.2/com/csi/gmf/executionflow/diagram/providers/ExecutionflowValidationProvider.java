/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutableFlowObject
 *  com.csi.gmf.executionflow.ExecutionFlowObject
 *  com.csi.gmf.executionflow.ExecutionFlowResult
 *  com.csi.gmf.executionflow.FlowObjectConnection
 *  org.eclipse.core.runtime.IStatus
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.emf.validation.AbstractModelConstraint
 *  org.eclipse.emf.validation.IValidationContext
 *  org.eclipse.emf.validation.model.IClientSelector
 *  org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider
 *  org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor
 *  org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.action.IAction
 */
package com.csi.gmf.executionflow.diagram.providers;

import com.csi.gmf.executionflow.ExecutableFlowObject;
import com.csi.gmf.executionflow.ExecutionFlowObject;
import com.csi.gmf.executionflow.ExecutionFlowResult;
import com.csi.gmf.executionflow.FlowObjectConnection;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import com.csi.gmf.executionflow.diagram.part.ValidateAction;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;

public class ExecutionflowValidationProvider
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
                ExecutionflowDiagramEditorPlugin.getInstance().logError("Validation failed", e);
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
        if (ExecutionflowValidationProvider.shouldConstraintsBePrivate() && !constraintsActive) {
            return false;
        }
        if (object instanceof View) {
            return constraintsActive && "Executionflow".equals(ExecutionflowVisualIDRegistry.getModelID((View)object));
        }
        return true;
    }

    static String formatElement(EObject object) {
        return EMFCoreUtil.getQualifiedName((EObject)object, (boolean)true);
    }

    public static class Adapter1
    extends AbstractModelConstraint {
        public IStatus validate(IValidationContext ctx) {
            ExecutableFlowObject cfr_ignored_0 = (ExecutableFlowObject)ctx.getTarget();
            throw new UnsupportedOperationException("No user java implementation provided for #validate(IValidationContext) operation");
        }
    }

    public static class Adapter2
    extends AbstractModelConstraint {
        public IStatus validate(IValidationContext ctx) {
            FlowObjectConnection cfr_ignored_0 = (FlowObjectConnection)ctx.getTarget();
            throw new UnsupportedOperationException("No user java implementation provided for #validate(IValidationContext) operation");
        }
    }

    public static class Adapter3
    extends AbstractModelConstraint {
        public IStatus validate(IValidationContext ctx) {
            ExecutionFlowResult cfr_ignored_0 = (ExecutionFlowResult)ctx.getTarget();
            throw new UnsupportedOperationException("No user java implementation provided for #validate(IValidationContext) operation");
        }
    }

    public static class Adapter4
    extends AbstractModelConstraint {
        public IStatus validate(IValidationContext ctx) {
            ExecutionFlowObject cfr_ignored_0 = (ExecutionFlowObject)ctx.getTarget();
            throw new UnsupportedOperationException("No user java implementation provided for #validate(IValidationContext) operation");
        }
    }

    public static class Adapter5
    extends AbstractModelConstraint {
        public IStatus validate(IValidationContext ctx) {
            ExecutableFlowObject cfr_ignored_0 = (ExecutableFlowObject)ctx.getTarget();
            throw new UnsupportedOperationException("No user java implementation provided for #validate(IValidationContext) operation");
        }
    }

    public static class DefaultCtx
    implements IClientSelector {
        public boolean selects(Object object) {
            return ExecutionflowValidationProvider.isInDefaultEditorContext(object);
        }
    }
}

