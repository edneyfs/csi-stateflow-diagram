/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.draw2d.IFigure
 *  org.eclipse.draw2d.LayoutManager
 *  org.eclipse.draw2d.RoundedRectangle
 *  org.eclipse.draw2d.StackLayout
 *  org.eclipse.draw2d.geometry.Dimension
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.EditPolicy
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gef.editpolicies.LayoutEditPolicy
 *  org.eclipse.gef.editpolicies.NonResizableEditPolicy
 *  org.eclipse.gef.requests.CreateRequest
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart
 *  org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout
 *  org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel
 *  org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure
 *  org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.swt.graphics.Color
 */
package com.csi.gmf.executionflow.diagram.edit.parts;

import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowObjectFileNameEditPart;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutionFlowObjectItemSemanticEditPolicy;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

public class ExecutionFlowObjectEditPart
extends ShapeNodeEditPart {
    public static final int VISUAL_ID = 1001;
    protected IFigure contentPane;
    protected IFigure primaryShape;
    static final Color THIS_BACK = new Color(null, 255, 255, 202);

    public ExecutionFlowObjectEditPart(View view) {
        super(view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("SemanticPolicy", (EditPolicy)new ExecutionFlowObjectItemSemanticEditPolicy());
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
        RoundedExecutionFlowObjectFigure figure = new RoundedExecutionFlowObjectFigure();
        this.primaryShape = figure;
        return this.primaryShape;
    }

    public RoundedExecutionFlowObjectFigure getPrimaryShape() {
        return (RoundedExecutionFlowObjectFigure)this.primaryShape;
    }

    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof ExecutionFlowObjectFileNameEditPart) {
            ((ExecutionFlowObjectFileNameEditPart)childEditPart).setLabel(this.getPrimaryShape().getFigureExecutionFlowObjectFileNameFigure());
            return true;
        }
        return false;
    }

    protected boolean removeFixedChild(EditPart childEditPart) {
        return false;
    }

    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(this.getMapMode().DPtoLP(40), this.getMapMode().DPtoLP(40));
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
        if (nodeShape.getLayoutManager() == null) {
            ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
            layout.setSpacing(this.getMapMode().DPtoLP(5));
            nodeShape.setLayoutManager((LayoutManager)layout);
        }
        return nodeShape;
    }

    public IFigure getContentPane() {
        if (this.contentPane != null) {
            return this.contentPane;
        }
        return super.getContentPane();
    }

    public EditPart getPrimaryChildEditPart() {
        return this.getChildBySemanticHint(ExecutionflowVisualIDRegistry.getType(4001));
    }

    protected void addChildVisual(EditPart childEditPart, int index) {
        if (this.addFixedChild(childEditPart)) {
            return;
        }
        super.addChildVisual(childEditPart, -1);
    }

    protected void removeChildVisual(EditPart childEditPart) {
        if (this.removeFixedChild(childEditPart)) {
            return;
        }
        super.removeChildVisual(childEditPart);
    }

    protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
        return super.getContentPaneFor(editPart);
    }

    public class RoundedExecutionFlowObjectFigure
    extends RoundedRectangle {
        private WrappingLabel fFigureExecutionFlowObjectFileNameFigure;
        private boolean myUseLocalCoordinates = false;

        public RoundedExecutionFlowObjectFigure() {
            this.setCornerDimensions(new Dimension(ExecutionFlowObjectEditPart.this.getMapMode().DPtoLP(8), ExecutionFlowObjectEditPart.this.getMapMode().DPtoLP(8)));
            this.setBackgroundColor(THIS_BACK);
            this.createContents();
        }

        private void createContents() {
            this.fFigureExecutionFlowObjectFileNameFigure = new WrappingLabel();
            this.fFigureExecutionFlowObjectFileNameFigure.setText("");
            this.add((IFigure)this.fFigureExecutionFlowObjectFileNameFigure);
        }

        public WrappingLabel getFigureExecutionFlowObjectFileNameFigure() {
            return this.fFigureExecutionFlowObjectFileNameFigure;
        }

        protected boolean useLocalCoordinates() {
            return this.myUseLocalCoordinates;
        }

        protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
            this.myUseLocalCoordinates = useLocalCoordinates;
        }
    }
}

