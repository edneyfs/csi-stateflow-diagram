/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.draw2d.ColorConstants
 *  org.eclipse.draw2d.Figure
 *  org.eclipse.draw2d.Graphics
 *  org.eclipse.draw2d.IFigure
 *  org.eclipse.draw2d.Label
 *  org.eclipse.draw2d.RectangleFigure
 *  org.eclipse.draw2d.geometry.Rectangle
 *  org.eclipse.draw2d.geometry.Translatable
 *  org.eclipse.gef.editpolicies.SelectionEditPolicy
 *  org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel
 */
package com.csi.gmf.stateflow.diagram.edit.policies;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

public class StateflowTextSelectionEditPolicy
extends SelectionEditPolicy {
    private IFigure selectionFeedbackFigure;
    private IFigure focusFeedbackFigure;

    protected void showPrimarySelection() {
        if (this.getHostFigure() instanceof WrappingLabel) {
            ((WrappingLabel)this.getHostFigure()).setSelected(true);
            ((WrappingLabel)this.getHostFigure()).setFocus(true);
        } else {
            this.showSelection();
            this.showFocus();
        }
    }

    protected void showSelection() {
        if (this.getHostFigure() instanceof WrappingLabel) {
            ((WrappingLabel)this.getHostFigure()).setSelected(true);
            ((WrappingLabel)this.getHostFigure()).setFocus(false);
        } else {
            this.hideSelection();
            this.selectionFeedbackFigure = this.createSelectionFeedbackFigure();
            this.addFeedback(this.selectionFeedbackFigure);
            this.refreshSelectionFeedback();
            this.hideFocus();
        }
    }

    protected void hideSelection() {
        if (this.getHostFigure() instanceof WrappingLabel) {
            ((WrappingLabel)this.getHostFigure()).setSelected(false);
            ((WrappingLabel)this.getHostFigure()).setFocus(false);
        } else {
            if (this.selectionFeedbackFigure != null) {
                this.removeFeedback(this.selectionFeedbackFigure);
                this.selectionFeedbackFigure = null;
            }
            this.hideFocus();
        }
    }

    protected void showFocus() {
        if (this.getHostFigure() instanceof WrappingLabel) {
            ((WrappingLabel)this.getHostFigure()).setFocus(true);
        } else {
            this.hideFocus();
            this.focusFeedbackFigure = this.createFocusFeedbackFigure();
            this.addFeedback(this.focusFeedbackFigure);
            this.refreshFocusFeedback();
        }
    }

    protected void hideFocus() {
        if (this.getHostFigure() instanceof WrappingLabel) {
            ((WrappingLabel)this.getHostFigure()).setFocus(false);
        } else if (this.focusFeedbackFigure != null) {
            this.removeFeedback(this.focusFeedbackFigure);
            this.focusFeedbackFigure = null;
        }
    }

    protected Rectangle getFeedbackBounds() {
        Rectangle bounds;
        if (this.getHostFigure() instanceof Label) {
            bounds = ((Label)this.getHostFigure()).getTextBounds();
            bounds.intersect(this.getHostFigure().getBounds());
        } else {
            bounds = this.getHostFigure().getBounds().getCopy();
        }
        this.getHostFigure().getParent().translateToAbsolute((Translatable)bounds);
        this.getFeedbackLayer().translateToRelative((Translatable)bounds);
        return bounds;
    }

    protected IFigure createSelectionFeedbackFigure() {
        if (this.getHostFigure() instanceof Label) {
            Label feedbackFigure = new Label();
            feedbackFigure.setOpaque(true);
            feedbackFigure.setBackgroundColor(ColorConstants.menuBackgroundSelected);
            feedbackFigure.setForegroundColor(ColorConstants.menuForegroundSelected);
            return feedbackFigure;
        }
        RectangleFigure feedbackFigure = new RectangleFigure();
        feedbackFigure.setFill(false);
        return feedbackFigure;
    }

    protected IFigure createFocusFeedbackFigure() {
        return new Figure(){

            protected void paintFigure(Graphics graphics) {
                graphics.drawFocus(this.getBounds().getResized(-1, -1));
            }
        };
    }

    protected void updateLabel(Label target) {
        Label source = (Label)this.getHostFigure();
        target.setText(source.getText());
        target.setTextAlignment(source.getTextAlignment());
        target.setFont(source.getFont());
    }

    protected void refreshSelectionFeedback() {
        if (this.selectionFeedbackFigure != null) {
            if (this.selectionFeedbackFigure instanceof Label) {
                this.updateLabel((Label)this.selectionFeedbackFigure);
                this.selectionFeedbackFigure.setBounds(this.getFeedbackBounds());
            } else {
                this.selectionFeedbackFigure.setBounds(this.getFeedbackBounds().expand(5, 5));
            }
        }
    }

    protected void refreshFocusFeedback() {
        if (this.focusFeedbackFigure != null) {
            this.focusFeedbackFigure.setBounds(this.getFeedbackBounds());
        }
    }

    public void refreshFeedback() {
        this.refreshSelectionFeedback();
        this.refreshFocusFeedback();
    }
}

