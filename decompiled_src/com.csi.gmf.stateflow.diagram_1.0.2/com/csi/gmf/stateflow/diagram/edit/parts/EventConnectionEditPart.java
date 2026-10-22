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

import com.csi.gmf.stateflow.diagram.edit.parts.EventConnectionEventIDEditPart;
import com.csi.gmf.stateflow.diagram.edit.policies.EventConnectionItemSemanticEditPolicy;
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

public class EventConnectionEditPart
extends ConnectionNodeEditPart
implements ITreeBranchEditPart {
    public static final int VISUAL_ID = 3002;

    public EventConnectionEditPart(View view) {
        super(view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("SemanticPolicy", (EditPolicy)new EventConnectionItemSemanticEditPolicy());
    }

    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof EventConnectionEventIDEditPart) {
            ((EventConnectionEventIDEditPart)childEditPart).setLabel(this.getPrimaryShape().getFigureLabelEventIDFigure());
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
        return new StateConnectionFigure();
    }

    public StateConnectionFigure getPrimaryShape() {
        return (StateConnectionFigure)this.getFigure();
    }

    public class StateConnectionFigure
    extends PolylineConnectionEx {
        private WrappingLabel fFigureLabelEventIDFigure;

        public StateConnectionFigure() {
            this.setForegroundColor(ColorConstants.darkGreen);
            this.createContents();
            this.setTargetDecoration(this.createTargetDecoration());
        }

        private void createContents() {
            this.fFigureLabelEventIDFigure = new WrappingLabel();
            this.fFigureLabelEventIDFigure.setText("<...>");
            this.add((IFigure)this.fFigureLabelEventIDFigure);
        }

        private RotatableDecoration createTargetDecoration() {
            PolylineDecoration df = new PolylineDecoration();
            PointList pl = new PointList();
            pl.addPoint(EventConnectionEditPart.this.getMapMode().DPtoLP(-1), EventConnectionEditPart.this.getMapMode().DPtoLP(1));
            pl.addPoint(EventConnectionEditPart.this.getMapMode().DPtoLP(0), EventConnectionEditPart.this.getMapMode().DPtoLP(0));
            pl.addPoint(EventConnectionEditPart.this.getMapMode().DPtoLP(-1), EventConnectionEditPart.this.getMapMode().DPtoLP(-1));
            df.setTemplate(pl);
            df.setScale((double)EventConnectionEditPart.this.getMapMode().DPtoLP(7), (double)EventConnectionEditPart.this.getMapMode().DPtoLP(3));
            return df;
        }

        public WrappingLabel getFigureLabelEventIDFigure() {
            return this.fFigureLabelEventIDFigure;
        }
    }
}

