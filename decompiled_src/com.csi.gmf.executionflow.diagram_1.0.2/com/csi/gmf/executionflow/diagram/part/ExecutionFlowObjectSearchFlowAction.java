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

public class ExecutionFlowObjectSearchFlowAction
extends ExecutionFlowObjectSearchAction {
    protected final String FLOW_EXTENSION = ".executionflow";
    protected final String FLOW_DIAGRAM_EXTENSION = ".executionflow_diagram";

    @Override
    public void run(IAction action) {
        ExecutionFlowDiagramSearchAssistant.getInstance().search(this.myElement.eContainer().eResource().getURI().path(), this.myElement.getFileName(), ".executionflow", ".executionflow_diagram");
    }
}

