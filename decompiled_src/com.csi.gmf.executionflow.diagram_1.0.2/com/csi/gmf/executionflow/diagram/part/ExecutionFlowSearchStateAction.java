/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.action.IAction
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.ExecutionFlowDiagramSearchAssistant;
import com.csi.gmf.executionflow.diagram.part.ExecutionFlowSearchAction;
import org.eclipse.jface.action.IAction;

public class ExecutionFlowSearchStateAction
extends ExecutionFlowSearchAction {
    private final String STATE_EXTENSION = ".stateflow";
    private final String STATE_DIAGRAM_EXTENSION = ".stateflow_diagram";

    @Override
    public void run(IAction action) {
        String fileName = this.myElement.eResource().getURI().segment(this.myElement.eResource().getURI().segments().length - 1);
        ExecutionFlowDiagramSearchAssistant.getInstance().search(this.myElement.eResource().getURI().path(), fileName, ".stateflow", ".stateflow_diagram");
    }
}

