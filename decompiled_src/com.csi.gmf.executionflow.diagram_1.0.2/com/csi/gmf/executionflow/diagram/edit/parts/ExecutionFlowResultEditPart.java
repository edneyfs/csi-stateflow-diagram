/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.draw2d.Ellipse
 *  org.eclipse.draw2d.IFigure
 *  org.eclipse.draw2d.LayoutManager
 *  org.eclipse.draw2d.StackLayout
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

import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowResultExecutionFlowResultValuEditPart;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutionFlowResultItemSemanticEditPolicy;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.StackLayout;
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

public class ExecutionFlowResultEditPart
extends ShapeNodeEditPart {
    public static final int VISUAL_ID = 1003;
    protected IFigure contentPane;
    protected IFigure primaryShape;
    static final Color THIS_BACK = new Color(null, 206, 255, 206);

    public ExecutionFlowResultEditPart(View view) {
        super(view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("SemanticPolicy", (EditPolicy)new ExecutionFlowResultItemSemanticEditPolicy());
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
        EllipticalExecutionFlowResultFigure figure = new EllipticalExecutionFlowResultFigure();
        this.primaryShape = figure;
        return this.primaryShape;
    }

    public EllipticalExecutionFlowResultFigure getPrimaryShape() {
        return (EllipticalExecutionFlowResultFigure)this.primaryShape;
    }

    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof ExecutionFlowResultExecutionFlowResultValuEditPart) {
            ((ExecutionFlowResultExecutionFlowResultValuEditPart)childEditPart).setLabel(this.getPrimaryShape().getFigureExecutionFlowResultExecutionFlowResultValueFigure());
            return true;
        }
        return false;
    }

    protected boolean removeFixedChild(EditPart childEditPart) {
        return false;
    }

    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(this.getMapMode().DPtoLP(30), this.getMapMode().DPtoLP(20));
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
        return this.getChildBySemanticHint(ExecutionflowVisualIDRegistry.getType(4003));
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

    public class EllipticalExecutionFlowResultFigure
    extends Ellipse {
        private WrappingLabel fFigureExecutionFlowResultExecutionFlowResultValueFigure;
        private boolean myUseLocalCoordinates = false;

        public EllipticalExecutionFlowResultFigure() {
            this.setBackgroundColor(THIS_BACK);
            this.createContents();
        }

        private void createContents() {
            this.fFigureExecutionFlowResultExecutionFlowResultValueFigure = new WrappingLabel();
            this.fFigureExecutionFlowResultExecutionFlowResultValueFigure.setText("<...>");
            this.add((IFigure)this.fFigureExecutionFlowResultExecutionFlowResultValueFigure);
        }

        public WrappingLabel getFigureExecutionFlowResultExecutionFlowResultValueFigure() {
            return this.fFigureExecutionFlowResultExecutionFlowResultValueFigure;
        }

        protected boolean useLocalCoordinates() {
            return this.myUseLocalCoordinates;
        }

        protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
            this.myUseLocalCoordinates = useLocalCoordinates;
        }
    }
}

