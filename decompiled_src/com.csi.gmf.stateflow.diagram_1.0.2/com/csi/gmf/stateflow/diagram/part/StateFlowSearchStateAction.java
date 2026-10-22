/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.impl.StateFlowImpl
 *  org.eclipse.gmf.runtime.notation.impl.DiagramImpl
 *  org.eclipse.jface.action.IAction
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.ui.IObjectActionDelegate
 *  org.eclipse.ui.IWorkbenchPart
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.edit.parts.StateFlowEditPart;
import com.csi.gmf.stateflow.diagram.part.StateFlowDiagramSearchAssistant;
import com.csi.gmf.stateflow.impl.StateFlowImpl;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class StateFlowSearchStateAction
implements IObjectActionDelegate {
    private final String STATE_EXTENSION = ".stateflow";
    private final String STATE_DIAGRAM_EXTENSION = ".stateflow_diagram";
    private StateFlowImpl myElement;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    public void run(IAction action) {
        String fileName = this.myElement.eResource().getURI().segment(this.myElement.eResource().getURI().segments().length - 1);
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        StateFlowDiagramSearchAssistant.getInstance().search(this.myElement.eResource().getURI().path(), fileName, ".stateflow", ".stateflow_diagram");
    }

    public void selectionChanged(IAction action, ISelection selection) {
        IStructuredSelection structuredSelection;
        StateFlowEditPart mySelectedElement = null;
        if (selection instanceof IStructuredSelection && (structuredSelection = (IStructuredSelection)selection).size() == 1 && structuredSelection.getFirstElement() instanceof StateFlowEditPart) {
            mySelectedElement = (StateFlowEditPart)((Object)structuredSelection.getFirstElement());
            this.myElement = (StateFlowImpl)((DiagramImpl)mySelectedElement.getModel()).getElement();
        }
        action.setEnabled(this.isEnabled());
    }

    private boolean isEnabled() {
        return this.myElement != null;
    }
}

