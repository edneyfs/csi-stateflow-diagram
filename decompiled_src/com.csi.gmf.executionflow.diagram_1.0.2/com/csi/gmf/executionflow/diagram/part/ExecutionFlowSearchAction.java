/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.impl.ExecutionFlowImpl
 *  org.eclipse.gmf.runtime.notation.impl.DiagramImpl
 *  org.eclipse.jface.action.IAction
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.ui.IObjectActionDelegate
 *  org.eclipse.ui.IWorkbenchPart
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowEditPart;
import com.csi.gmf.executionflow.impl.ExecutionFlowImpl;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public abstract class ExecutionFlowSearchAction
implements IObjectActionDelegate {
    protected ExecutionFlowImpl myElement;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    public abstract void run(IAction var1);

    public void selectionChanged(IAction action, ISelection selection) {
        IStructuredSelection structuredSelection;
        ExecutionFlowEditPart mySelectedElement = null;
        if (selection instanceof IStructuredSelection && (structuredSelection = (IStructuredSelection)selection).size() == 1 && structuredSelection.getFirstElement() instanceof ExecutionFlowEditPart) {
            mySelectedElement = (ExecutionFlowEditPart)((Object)structuredSelection.getFirstElement());
            this.myElement = (ExecutionFlowImpl)((DiagramImpl)mySelectedElement.getModel()).getElement();
        }
        action.setEnabled(this.isEnabled());
    }

    private boolean isEnabled() {
        return this.myElement != null;
    }
}

