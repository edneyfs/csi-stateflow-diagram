/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gef.EditPolicy
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.edit.parts;

import com.csi.gmf.executionflow.diagram.edit.policies.ExecutionFlowCanonicalEditPolicy;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutionFlowItemSemanticEditPolicy;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionFlowEditPart
extends DiagramEditPart {
    public static final String MODEL_ID = "Executionflow";
    public static final int VISUAL_ID = 79;

    public ExecutionFlowEditPart(View view) {
        super(view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("SemanticPolicy", (EditPolicy)new ExecutionFlowItemSemanticEditPolicy());
        this.installEditPolicy("Canonical", (EditPolicy)new ExecutionFlowCanonicalEditPolicy());
    }
}

