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

public class ExecutionFlowObjectSearchFlowAction
extends ExecutionFlowObjectSearchAction {
    private final String FLOW_EXTENSION = ".executionflow";
    private final String FLOW_DIAGRAM_EXTENSION = ".executionflow_diagram";

    @Override
    public void run(IAction action) {
        StateFlowDiagramSearchAssistant.getInstance().search(this.myElement.eContainer().eResource().getURI().path(), this.myElement.getFilePath(), ".executionflow", ".executionflow_diagram");
    }
}

