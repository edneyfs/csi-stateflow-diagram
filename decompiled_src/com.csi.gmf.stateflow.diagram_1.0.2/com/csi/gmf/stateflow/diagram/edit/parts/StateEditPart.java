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
package com.csi.gmf.stateflow.diagram.edit.parts;

import com.csi.gmf.stateflow.diagram.edit.parts.StateIdEditPart;
import com.csi.gmf.stateflow.diagram.edit.policies.StateCanonicalEditPolicy;
import com.csi.gmf.stateflow.diagram.edit.policies.StateGraphicalNodeEditPolicy;
import com.csi.gmf.stateflow.diagram.edit.policies.StateItemSemanticEditPolicy;
import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
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

public class StateEditPart
extends ShapeNodeEditPart {
    public static final int VISUAL_ID = 1002;
    protected IFigure contentPane;
    protected IFigure primaryShape;
    static final Color THIS_BACK = new Color(null, 207, 207, 207);

    public StateEditPart(View view) {
        super(view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("SemanticPolicy", (EditPolicy)new StateItemSemanticEditPolicy());
        this.installEditPolicy("GraphicalNodeEditPolicy", (EditPolicy)new StateGraphicalNodeEditPolicy());
        this.installEditPolicy("Canonical", (EditPolicy)new StateCanonicalEditPolicy());
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
        EllipticalStateFigure figure = new EllipticalStateFigure();
        this.primaryShape = figure;
        return this.primaryShape;
    }

    public EllipticalStateFigure getPrimaryShape() {
        return (EllipticalStateFigure)this.primaryShape;
    }

    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof StateIdEditPart) {
            ((StateIdEditPart)childEditPart).setLabel(this.getPrimaryShape().getFigureStateIdFigure());
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
        return this.getChildBySemanticHint(StateflowVisualIDRegistry.getType(4002));
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

    public class EllipticalStateFigure
    extends Ellipse {
        private WrappingLabel fFigureStateIdFigure;
        private boolean myUseLocalCoordinates = false;

        public EllipticalStateFigure() {
            this.setBackgroundColor(THIS_BACK);
            this.createContents();
        }

        private void createContents() {
            this.fFigureStateIdFigure = new WrappingLabel();
            this.fFigureStateIdFigure.setText("<...>");
            this.add((IFigure)this.fFigureStateIdFigure);
        }

        public WrappingLabel getFigureStateIdFigure() {
            return this.fFigureStateIdFigure;
        }

        protected boolean useLocalCoordinates() {
            return this.myUseLocalCoordinates;
        }

        protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
            this.myUseLocalCoordinates = useLocalCoordinates;
        }
    }
}

