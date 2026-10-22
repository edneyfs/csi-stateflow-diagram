/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.impl.StateImpl
 *  org.eclipse.gmf.runtime.notation.impl.NodeImpl
 *  org.eclipse.jface.action.IAction
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.ui.IObjectActionDelegate
 *  org.eclipse.ui.IWorkbenchPart
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.edit.parts.StateEditPart;
import com.csi.gmf.stateflow.diagram.part.StateFlowDiagramSearchAssistant;
import com.csi.gmf.stateflow.impl.StateImpl;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class StateSearchStateAction
implements IObjectActionDelegate {
    private final String STATE_EXTENSION = ".stateflow";
    private final String STATE_DIAGRAM_EXTENSION = ".stateflow_diagram";
    private StateImpl myElement;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    public void run(IAction action) {
        StateFlowDiagramSearchAssistant.getInstance().search(this.myElement.eContainer().eResource().getURI().path(), this.myElement.getId(), ".stateflow", ".stateflow_diagram");
    }

    public void selectionChanged(IAction action, ISelection selection) {
        IStructuredSelection structuredSelection;
        StateEditPart mySelectedElement = null;
        if (selection instanceof IStructuredSelection && (structuredSelection = (IStructuredSelection)selection).size() == 1 && structuredSelection.getFirstElement() instanceof StateEditPart) {
            mySelectedElement = (StateEditPart)((Object)structuredSelection.getFirstElement());
            this.myElement = (StateImpl)((NodeImpl)mySelectedElement.getModel()).getElement();
        }
        action.setEnabled(this.isEnabled());
    }

    private boolean isEnabled() {
        return this.myElement != null;
    }
}

