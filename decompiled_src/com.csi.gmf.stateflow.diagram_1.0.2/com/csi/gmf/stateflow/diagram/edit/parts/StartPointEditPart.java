/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.draw2d.ColorConstants
 *  org.eclipse.draw2d.Ellipse
 *  org.eclipse.draw2d.IFigure
 *  org.eclipse.draw2d.LayoutManager
 *  org.eclipse.draw2d.StackLayout
 *  org.eclipse.draw2d.geometry.Dimension
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.EditPolicy
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gef.editpolicies.LayoutEditPolicy
 *  org.eclipse.gef.editpolicies.NonResizableEditPolicy
 *  org.eclipse.gef.requests.CreateRequest
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart
 *  org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure
 *  org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.edit.parts;

import com.csi.gmf.stateflow.diagram.edit.policies.StartPointItemSemanticEditPolicy;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;

public class StartPointEditPart
extends ShapeNodeEditPart {
    public static final int VISUAL_ID = 1003;
    protected IFigure contentPane;
    protected IFigure primaryShape;

    public StartPointEditPart(View view) {
        super(view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("SemanticPolicy", (EditPolicy)new StartPointItemSemanticEditPolicy());
        this.installEditPolicy("LayoutEditPolicy", (EditPolicy)this.createLayoutEditPolicy());
    }

    protected LayoutEditPolicy createLayoutEditPolicy() {
        LayoutEditPolicy lep = new LayoutEditPolicy(){

            protected EditPolicy createChildEditPolicy(EditPart child) {
                EditPolicy result = child.getEditPolicy((Object)"PrimaryDrag Policy");
                if (result == null) {
                    result = new NonResizableEditPolicy();
                }
                return result;
            }

            protected Command getMoveChildrenCommand(Request request) {
                return null;
            }

            protected Command getCreateCommand(CreateRequest request) {
                return null;
            }
        };
        return lep;
    }

    protected IFigure createNodeShape() {
        StartPointFigure figure = new StartPointFigure();
        this.primaryShape = figure;
        return this.primaryShape;
    }

    public StartPointFigure getPrimaryShape() {
        return (StartPointFigure)this.primaryShape;
    }

    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(this.getMapMode().DPtoLP(20), this.getMapMode().DPtoLP(15));
        return result;
    }

    protected NodeFigure createNodeFigure() {
        NodeFigure figure = this.createNodePlate();
        figure.setLayoutManager((LayoutManager)new StackLayout());
        IFigure shape = this.createNodeShape();
        figure.add(shape);
        this.contentPane = this.setupContentPane(shape);
        return figure;
    }

    protected IFigure setupContentPane(IFigure nodeShape) {
        return nodeShape;
    }

    public IFigure getContentPane() {
        if (this.contentPane != null) {
            return this.contentPane;
        }
        return super.getContentPane();
    }

    public class StartPointFigure
    extends Ellipse {
        private boolean myUseLocalCoordinates = false;

        public StartPointFigure() {
            this.setBackgroundColor(ColorConstants.black);
            this.setPreferredSize(new Dimension(StartPointEditPart.this.getMapMode().DPtoLP(20), StartPointEditPart.this.getMapMode().DPtoLP(15)));
        }

        protected boolean useLocalCoordinates() {
            return this.myUseLocalCoordinates;
        }

        protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
            this.myUseLocalCoordinates = useLocalCoordinates;
        }
    }
}

