/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.draw2d.IFigure
 *  org.eclipse.draw2d.geometry.Dimension
 *  org.eclipse.draw2d.geometry.Point
 *  org.eclipse.emf.ecore.EStructuralFeature
 *  org.eclipse.gef.GraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.figures.LabelLocator
 *  org.eclipse.gmf.runtime.notation.NotationPackage
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.LabelLocator;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

public class StateflowExtNodeLabelEditPart
extends LabelEditPart {
    public StateflowExtNodeLabelEditPart(View view) {
        super(view);
    }

    public void refreshBounds() {
        IFigure refFigure = ((GraphicalEditPart)this.getParent()).getFigure();
        int dx = (Integer)this.getStructuralFeatureValue((EStructuralFeature)NotationPackage.eINSTANCE.getLocation_X());
        int dy = (Integer)this.getStructuralFeatureValue((EStructuralFeature)NotationPackage.eINSTANCE.getLocation_Y());
        Point offset = new Point(dx, dy);
        this.getFigure().getParent().setConstraint(this.getFigure(), (Object)new LabelLocator(refFigure, offset, this.getKeyPoint()){

            public void relocate(IFigure target) {
                Point location = this.getReferencePoint().getTranslated(this.getOffset());
                location.translate(-target.getBounds().width / 2, 0);
                target.setLocation(location);
                target.setSize(new Dimension(target.getPreferredSize().width, target.getPreferredSize().height));
            }

            protected Point getReferencePoint() {
                return StateflowExtNodeLabelEditPart.this.getLabelLocation(this.parent);
            }
        });
    }

    public Point getReferencePoint() {
        return this.getLabelLocation(((GraphicalEditPart)this.getParent()).getFigure());
    }

    protected Point getLabelLocation(IFigure parent) {
        return parent.getBounds().getBottom();
    }
}

