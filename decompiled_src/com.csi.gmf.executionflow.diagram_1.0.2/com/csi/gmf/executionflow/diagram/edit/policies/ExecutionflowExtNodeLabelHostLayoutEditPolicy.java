/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.draw2d.geometry.Rectangle
 *  org.eclipse.draw2d.geometry.Translatable
 *  org.eclipse.emf.ecore.EStructuralFeature
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.GraphicalEditPart
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gef.editpolicies.LayoutEditPolicy
 *  org.eclipse.gef.requests.ChangeBoundsRequest
 *  org.eclipse.gef.requests.GroupRequest
 *  org.eclipse.gmf.runtime.diagram.core.util.ViewUtil
 *  org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy
 *  org.eclipse.gmf.runtime.notation.NotationPackage
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.edit.policies;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionflowExtNodeLabelHostLayoutEditPolicy
extends XYLayoutEditPolicy {
    private LayoutEditPolicy realLayoutEditPolicy;

    public LayoutEditPolicy getRealLayoutEditPolicy() {
        return this.realLayoutEditPolicy;
    }

    public void setRealLayoutEditPolicy(LayoutEditPolicy realLayoutEditPolicy) {
        this.realLayoutEditPolicy = realLayoutEditPolicy;
    }

    protected boolean isExternalLabel(EditPart editPart) {
        return false;
    }

    protected final List getExternalLabels(GroupRequest request) {
        ArrayList<EditPart> editParts = new ArrayList<EditPart>();
        if (request.getEditParts() != null) {
            for (EditPart editPart : request.getEditParts()) {
                if (!this.isExternalLabel(editPart)) continue;
                editParts.add(editPart);
            }
        }
        return editParts;
    }

    public Command getCommand(Request request) {
        List extLabels;
        ChangeBoundsRequest cbRequest;
        List extLabels2;
        if ("move children".equals(request.getType()) && !(extLabels2 = this.getExternalLabels((GroupRequest)(cbRequest = (ChangeBoundsRequest)request))).isEmpty()) {
            List editParts = cbRequest.getEditParts();
            Command cmd = null;
            if (this.realLayoutEditPolicy != null && editParts.size() > extLabels2.size()) {
                ArrayList other = new ArrayList(editParts);
                other.removeAll(extLabels2);
                cbRequest.setEditParts(other);
                cmd = this.realLayoutEditPolicy.getCommand(request);
            }
            cbRequest.setEditParts(extLabels2);
            Command extLabelsCmd = this.getMoveChildrenCommand(request);
            cbRequest.setEditParts(editParts);
            return cmd == null ? extLabelsCmd : cmd.chain(extLabelsCmd);
        }
        if (request instanceof GroupRequest && !(extLabels = this.getExternalLabels((GroupRequest)request)).isEmpty()) {
            return null;
        }
        return this.realLayoutEditPolicy == null ? null : this.realLayoutEditPolicy.getCommand(request);
    }

    protected Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
        int dx = (Integer)ViewUtil.getStructuralFeatureValue((View)((View)child.getModel()), (EStructuralFeature)NotationPackage.eINSTANCE.getLocation_X());
        int dy = (Integer)ViewUtil.getStructuralFeatureValue((View)((View)child.getModel()), (EStructuralFeature)NotationPackage.eINSTANCE.getLocation_Y());
        Rectangle r = new Rectangle(dx, dy, 0, 0);
        child.getFigure().translateToAbsolute((Translatable)r);
        r.translate(request.getMoveDelta());
        child.getFigure().translateToRelative((Translatable)r);
        return r;
    }

    public boolean understandsRequest(Request req) {
        if (this.realLayoutEditPolicy != null && this.realLayoutEditPolicy.understandsRequest(req)) {
            return true;
        }
        return super.understandsRequest(req);
    }

    protected void decorateChild(EditPart child) {
    }

    public void setHost(EditPart host) {
        super.setHost(host);
        if (this.realLayoutEditPolicy != null) {
            this.realLayoutEditPolicy.setHost(host);
        }
    }

    public void activate() {
        super.activate();
        if (this.realLayoutEditPolicy != null) {
            this.realLayoutEditPolicy.activate();
        }
    }

    public void deactivate() {
        super.deactivate();
        if (this.realLayoutEditPolicy != null) {
            this.realLayoutEditPolicy.deactivate();
        }
    }

    public EditPart getTargetEditPart(Request request) {
        if (this.realLayoutEditPolicy != null) {
            return this.realLayoutEditPolicy.getTargetEditPart(request);
        }
        return super.getTargetEditPart(request);
    }

    public void showSourceFeedback(Request request) {
        if (this.realLayoutEditPolicy != null) {
            this.realLayoutEditPolicy.showSourceFeedback(request);
        } else {
            super.showSourceFeedback(request);
        }
    }

    public void eraseSourceFeedback(Request request) {
        if (this.realLayoutEditPolicy != null) {
            this.realLayoutEditPolicy.eraseSourceFeedback(request);
        } else {
            super.eraseSourceFeedback(request);
        }
    }

    public void showTargetFeedback(Request request) {
        if (this.realLayoutEditPolicy != null) {
            this.realLayoutEditPolicy.showTargetFeedback(request);
        } else {
            super.showTargetFeedback(request);
        }
    }

    public void eraseTargetFeedback(Request request) {
        if (this.realLayoutEditPolicy != null) {
            this.realLayoutEditPolicy.eraseTargetFeedback(request);
        } else {
            super.eraseTargetFeedback(request);
        }
    }
}

