/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.draw2d.ColorConstants
 *  org.eclipse.draw2d.Connection
 *  org.eclipse.draw2d.IFigure
 *  org.eclipse.draw2d.PolylineDecoration
 *  org.eclipse.draw2d.RotatableDecoration
 *  org.eclipse.draw2d.geometry.PointList
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.EditPolicy
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart
 *  org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx
 *  org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.edit.parts;

import com.csi.gmf.stateflow.diagram.edit.parts.FlowObjectConnectionExecutionFlowResultValuEditPart;
import com.csi.gmf.stateflow.diagram.edit.policies.FlowObjectConnectionItemSemanticEditPolicy;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;

public class FlowObjectConnectionEditPart
extends ConnectionNodeEditPart
implements ITreeBranchEditPart {
    public static final int VISUAL_ID = 3001;

    public FlowObjectConnectionEditPart(View view) {
        super(view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("SemanticPolicy", (EditPolicy)new FlowObjectConnectionItemSemanticEditPolicy());
    }

    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof FlowObjectConnectionExecutionFlowResultValuEditPart) {
            ((FlowObjectConnectionExecutionFlowResultValuEditPart)childEditPart).setLabel(this.getPrimaryShape().getFigureLabelExecutionFlowResultValueFigure());
            return true;
        }
        return false;
    }

    protected void addChildVisual(EditPart childEditPart, int index) {
        if (this.addFixedChild(childEditPart)) {
            return;
        }
        super.addChildVisual(childEditPart, -1);
    }

    protected Connection createConnectionFigure() {
        return new FlowObjectConnectionFigure();
    }

    public FlowObjectConnectionFigure getPrimaryShape() {
        return (FlowObjectConnectionFigure)this.getFigure();
    }

    public class FlowObjectConnectionFigure
    extends PolylineConnectionEx {
        private WrappingLabel fFigureLabelExecutionFlowResultValueFigure;

        public FlowObjectConnectionFigure() {
            this.setForegroundColor(ColorConstants.blue);
            this.createContents();
            this.setTargetDecoration(this.createTargetDecoration());
        }

        private void createContents() {
            this.fFigureLabelExecutionFlowResultValueFigure = new WrappingLabel();
            this.fFigureLabelExecutionFlowResultValueFigure.setText("<...>");
            this.add((IFigure)this.fFigureLabelExecutionFlowResultValueFigure);
        }

        private RotatableDecoration createTargetDecoration() {
            PolylineDecoration df = new PolylineDecoration();
            PointList pl = new PointList();
            pl.addPoint(FlowObjectConnectionEditPart.this.getMapMode().DPtoLP(-1), FlowObjectConnectionEditPart.this.getMapMode().DPtoLP(1));
            pl.addPoint(FlowObjectConnectionEditPart.this.getMapMode().DPtoLP(0), FlowObjectConnectionEditPart.this.getMapMode().DPtoLP(0));
            pl.addPoint(FlowObjectConnectionEditPart.this.getMapMode().DPtoLP(-1), FlowObjectConnectionEditPart.this.getMapMode().DPtoLP(-1));
            df.setTemplate(pl);
            df.setScale((double)FlowObjectConnectionEditPart.this.getMapMode().DPtoLP(7), (double)FlowObjectConnectionEditPart.this.getMapMode().DPtoLP(3));
            return df;
        }

        public WrappingLabel getFigureLabelExecutionFlowResultValueFigure() {
            return this.fFigureLabelExecutionFlowResultValueFigure;
        }
    }
}

