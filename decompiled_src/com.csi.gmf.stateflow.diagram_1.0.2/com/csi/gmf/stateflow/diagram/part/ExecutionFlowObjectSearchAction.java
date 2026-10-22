/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.impl.ExecutionFlowObjectImpl
 *  org.eclipse.gmf.runtime.notation.impl.NodeImpl
 *  org.eclipse.jface.action.IAction
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.ui.IObjectActionDelegate
 *  org.eclipse.ui.IWorkbenchPart
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.edit.parts.ExecutionFlowObjectEditPart;
import com.csi.gmf.stateflow.impl.ExecutionFlowObjectImpl;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public abstract class ExecutionFlowObjectSearchAction
implements IObjectActionDelegate {
    protected ExecutionFlowObjectImpl myElement;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    public abstract void run(IAction var1);

    public void selectionChanged(IAction action, ISelection selection) {
        IStructuredSelection structuredSelection;
        ExecutionFlowObjectEditPart mySelectedElement = null;
        if (selection instanceof IStructuredSelection && (structuredSelection = (IStructuredSelection)selection).size() == 1 && structuredSelection.getFirstElement() instanceof ExecutionFlowObjectEditPart) {
            mySelectedElement = (ExecutionFlowObjectEditPart)((Object)structuredSelection.getFirstElement());
            this.myElement = (ExecutionFlowObjectImpl)((NodeImpl)mySelectedElement.getModel()).getElement();
        }
        action.setEnabled(this.isEnabled());
    }

    private boolean isEnabled() {
        return this.myElement != null;
    }
}

