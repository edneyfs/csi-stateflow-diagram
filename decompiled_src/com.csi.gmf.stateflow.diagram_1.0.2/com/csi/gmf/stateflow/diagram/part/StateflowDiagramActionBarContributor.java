/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;

public class StateflowDiagramActionBarContributor
extends DiagramActionBarContributor {
    protected Class getEditorClass() {
        return StateflowDiagramEditor.class;
    }

    protected String getEditorId() {
        return "com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorID";
    }
}

