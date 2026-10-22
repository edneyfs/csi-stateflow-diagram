/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gef.EditPolicy
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.edit.parts;

import com.csi.gmf.stateflow.diagram.edit.policies.ExecutionFlowObjectOpenEditPolicy;
import com.csi.gmf.stateflow.diagram.edit.policies.StateFlowCanonicalEditPolicy;
import com.csi.gmf.stateflow.diagram.edit.policies.StateFlowItemSemanticEditPolicy;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.View;

public class StateFlowEditPart
extends DiagramEditPart {
    public static final String MODEL_ID = "Stateflow";
    public static final int VISUAL_ID = 79;

    public StateFlowEditPart(View view) {
        super(view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("SemanticPolicy", (EditPolicy)new StateFlowItemSemanticEditPolicy());
        this.installEditPolicy("Canonical", (EditPolicy)new StateFlowCanonicalEditPolicy());
        this.installEditPolicy("OpenPolicy", (EditPolicy)new ExecutionFlowObjectOpenEditPolicy());
    }
}

