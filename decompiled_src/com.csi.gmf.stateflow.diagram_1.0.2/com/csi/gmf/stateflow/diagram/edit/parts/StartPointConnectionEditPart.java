/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.draw2d.ColorConstants
 *  org.eclipse.draw2d.Connection
 *  org.eclipse.draw2d.PolylineDecoration
 *  org.eclipse.draw2d.RotatableDecoration
 *  org.eclipse.draw2d.geometry.PointList
 *  org.eclipse.gef.EditPolicy
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart
 *  org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.edit.parts;

import com.csi.gmf.stateflow.diagram.edit.policies.StartPointConnectionItemSemanticEditPolicy;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;

public class StartPointConnectionEditPart
extends ConnectionNodeEditPart
implements ITreeBranchEditPart {
    public static final int VISUAL_ID = 3003;

    public StartPointConnectionEditPart(View view) {
        super(view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("SemanticPolicy", (EditPolicy)new StartPointConnectionItemSemanticEditPolicy());
    }

    protected Connection createConnectionFigure() {
        return new StartPointConnectionFigure();
    }

    public StartPointConnectionFigure getPrimaryShape() {
        return (StartPointConnectionFigure)this.getFigure();
    }

    public class StartPointConnectionFigure
    extends PolylineConnectionEx {
        public StartPointConnectionFigure() {
            this.setLineStyle(3);
            this.setBackgroundColor(ColorConstants.black);
            this.setTargetDecoration(this.createTargetDecoration());
        }

        private RotatableDecoration createTargetDecoration() {
            PolylineDecoration df = new PolylineDecoration();
            PointList pl = new PointList();
            pl.addPoint(StartPointConnectionEditPart.this.getMapMode().DPtoLP(-1), StartPointConnectionEditPart.this.getMapMode().DPtoLP(1));
            pl.addPoint(StartPointConnectionEditPart.this.getMapMode().DPtoLP(0), StartPointConnectionEditPart.this.getMapMode().DPtoLP(0));
            pl.addPoint(StartPointConnectionEditPart.this.getMapMode().DPtoLP(-1), StartPointConnectionEditPart.this.getMapMode().DPtoLP(-1));
            df.setTemplate(pl);
            df.setScale((double)StartPointConnectionEditPart.this.getMapMode().DPtoLP(7), (double)StartPointConnectionEditPart.this.getMapMode().DPtoLP(3));
            return df;
        }
    }
}

