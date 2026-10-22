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

import com.csi.gmf.executionflow.diagram.edit.parts.ExecutableFlowObjectSimpleClassNameEditPart;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutableFlowObjectCanonicalEditPolicy;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutableFlowObjectGraphicalNodeEditPolicy;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutableFlowObjectItemSemanticEditPolicy;
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

public class ExecutableFlowObjectEditPart
extends ShapeNodeEditPart {
    public static final int VISUAL_ID = 1002;
    protected IFigure contentPane;
    protected IFigure primaryShape;
    static final Color THIS_BACK = new Color(null, 207, 220, 239);

    public ExecutableFlowObjectEditPart(View view) {
        super(view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("SemanticPolicy", (EditPolicy)new ExecutableFlowObjectItemSemanticEditPolicy());
        this.installEditPolicy("GraphicalNodeEditPolicy", (EditPolicy)new ExecutableFlowObjectGraphicalNodeEditPolicy());
        this.installEditPolicy("Canonical", (EditPolicy)new ExecutableFlowObjectCanonicalEditPolicy());
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
        RoundedExecutableFlowObjectFigure figure = new RoundedExecutableFlowObjectFigure();
        this.primaryShape = figure;
        return this.primaryShape;
    }

    public RoundedExecutableFlowObjectFigure getPrimaryShape() {
        return (RoundedExecutableFlowObjectFigure)this.primaryShape;
    }

    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof ExecutableFlowObjectSimpleClassNameEditPart) {
            ((ExecutableFlowObjectSimpleClassNameEditPart)childEditPart).setLabel(this.getPrimaryShape().getFigureExecutableFlowObjectClassNameFigure());
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
        return this.getChildBySemanticHint(ExecutionflowVisualIDRegistry.getType(4002));
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

    public void performRequest(Request request) {
        super.performRequest(request);
    }

    public class RoundedExecutableFlowObjectFigure
    extends RoundedRectangle {
        private WrappingLabel fFigureExecutableFlowObjectClassNameFigure;
        private boolean myUseLocalCoordinates = false;

        public RoundedExecutableFlowObjectFigure() {
            this.setCornerDimensions(new Dimension(ExecutableFlowObjectEditPart.this.getMapMode().DPtoLP(8), ExecutableFlowObjectEditPart.this.getMapMode().DPtoLP(8)));
            this.setBackgroundColor(THIS_BACK);
            this.createContents();
        }

        private void createContents() {
            this.fFigureExecutableFlowObjectClassNameFigure = new WrappingLabel();
            this.fFigureExecutableFlowObjectClassNameFigure.setText("");
            this.add((IFigure)this.fFigureExecutableFlowObjectClassNameFigure);
        }

        public WrappingLabel getFigureExecutableFlowObjectClassNameFigure() {
            return this.fFigureExecutableFlowObjectClassNameFigure;
        }

        protected boolean useLocalCoordinates() {
            return this.myUseLocalCoordinates;
        }

        protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
            this.myUseLocalCoordinates = useLocalCoordinates;
        }
    }
}

