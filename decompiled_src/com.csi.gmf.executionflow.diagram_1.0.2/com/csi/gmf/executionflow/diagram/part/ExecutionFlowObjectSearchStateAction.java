/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.action.IAction
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.ExecutionFlowDiagramSearchAssistant;
import com.csi.gmf.executionflow.diagram.part.ExecutionFlowObjectSearchAction;
import org.eclipse.jface.action.IAction;

public class ExecutionFlowObjectSearchStateAction
extends ExecutionFlowObjectSearchAction {
    private final String STATE_EXTENSION = ".stateflow";
    private final String STATE_DIAGRAM_EXTENSION = ".stateflow_diagram";

    @Override
    public void run(IAction action) {
        ExecutionFlowDiagramSearchAssistant.getInstance().search(this.myElement.eResource().getURI().path(), this.myElement.getFileName(), ".stateflow", ".stateflow_diagram");
    }
}

