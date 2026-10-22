/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;

public class ExecutionflowDiagramActionBarContributor
extends DiagramActionBarContributor {
    protected Class getEditorClass() {
        return ExecutionflowDiagramEditor.class;
    }

    protected String getEditorId() {
        return "com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorID";
    }
}

