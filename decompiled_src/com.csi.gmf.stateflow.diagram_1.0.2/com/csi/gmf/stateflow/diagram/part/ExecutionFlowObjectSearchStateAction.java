/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.action.IAction
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.part.ExecutionFlowObjectSearchAction;
import com.csi.gmf.stateflow.diagram.part.StateFlowDiagramSearchAssistant;
import org.eclipse.jface.action.IAction;

public class ExecutionFlowObjectSearchStateAction
extends ExecutionFlowObjectSearchAction {
    private final String STATE_EXTENSION = ".stateflow";
    private final String STATE_DIAGRAM_EXTENSION = ".stateflow_diagram";

    @Override
    public void run(IAction action) {
        StateFlowDiagramSearchAssistant.getInstance().search(this.myElement.eContainer().eResource().getURI().path(), this.myElement.getFilePath(), ".stateflow", ".stateflow_diagram");
    }
}

