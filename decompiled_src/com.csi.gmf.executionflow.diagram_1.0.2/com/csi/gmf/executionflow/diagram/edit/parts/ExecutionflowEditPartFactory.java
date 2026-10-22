/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.draw2d.FigureUtilities
 *  org.eclipse.draw2d.Label
 *  org.eclipse.draw2d.geometry.Dimension
 *  org.eclipse.draw2d.geometry.Rectangle
 *  org.eclipse.draw2d.geometry.Translatable
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.EditPartFactory
 *  org.eclipse.gef.tools.CellEditorLocator
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart
 *  org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.viewers.CellEditor
 *  org.eclipse.swt.graphics.Font
 *  org.eclipse.swt.widgets.Text
 */
package com.csi.gmf.executionflow.diagram.edit.parts;

import com.csi.gmf.executionflow.diagram.edit.parts.ExecutableFlowObjectEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.ExecutableFlowObjectSimpleClassNameEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowObjectEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowObjectFileNameEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowResultEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowResultExecutionFlowResultValuEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.FlowObjectConnectionEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.FlowObjectConnectionExecutionFlowResultValuEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.StartPointConnectionEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.StartPointEditPart;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Text;

public class ExecutionflowEditPartFactory
implements EditPartFactory {
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof View) {
            View view = (View)model;
            switch (ExecutionflowVisualIDRegistry.getVisualID(view)) {
                case 79: {
                    return new ExecutionFlowEditPart(view);
                }
                case 1001: {
                    return new ExecutionFlowObjectEditPart(view);
                }
                case 4001: {
                    return new ExecutionFlowObjectFileNameEditPart(view);
                }
                case 1002: {
                    return new ExecutableFlowObjectEditPart(view);
                }
                case 4002: {
                    return new ExecutableFlowObjectSimpleClassNameEditPart(view);
                }
                case 1003: {
                    return new ExecutionFlowResultEditPart(view);
                }
                case 4003: {
                    return new ExecutionFlowResultExecutionFlowResultValuEditPart(view);
                }
                case 1004: {
                    return new StartPointEditPart(view);
                }
                case 3001: {
                    return new FlowObjectConnectionEditPart(view);
                }
                case 4004: {
                    return new FlowObjectConnectionExecutionFlowResultValuEditPart(view);
                }
                case 3002: {
                    return new StartPointConnectionEditPart(view);
                }
            }
        }
        return this.createUnrecognizedEditPart(context, model);
    }

    private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
        return null;
    }

    public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
        if (source.getFigure() instanceof WrappingLabel) {
            return new TextCellEditorLocator((WrappingLabel)source.getFigure());
        }
        return new LabelCellEditorLocator((Label)source.getFigure());
    }

    private static class LabelCellEditorLocator
    implements CellEditorLocator {
        private Label label;

        public LabelCellEditorLocator(Label label) {
            this.label = label;
        }

        public Label getLabel() {
            return this.label;
        }

        public void relocate(CellEditor celleditor) {
            Text text = (Text)celleditor.getControl();
            Rectangle rect = this.getLabel().getTextBounds().getCopy();
            this.getLabel().translateToAbsolute((Translatable)rect);
            int avr = FigureUtilities.getFontMetrics((Font)text.getFont()).getAverageCharWidth();
            rect.setSize(new Dimension(text.computeSize(-1, -1)).expand(avr * 2, 0));
            if (!rect.equals((Object)new Rectangle(text.getBounds()))) {
                text.setBounds(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }

    private static class TextCellEditorLocator
    implements CellEditorLocator {
        private WrappingLabel wrapLabel;

        public TextCellEditorLocator(WrappingLabel wrapLabel) {
            this.wrapLabel = wrapLabel;
        }

        public WrappingLabel getWrapLabel() {
            return this.wrapLabel;
        }

        public void relocate(CellEditor celleditor) {
            Text text = (Text)celleditor.getControl();
            Rectangle rect = this.getWrapLabel().getTextBounds().getCopy();
            this.getWrapLabel().translateToAbsolute((Translatable)rect);
            if (this.getWrapLabel().isTextWrapOn() && this.getWrapLabel().getText().length() > 0) {
                rect.setSize(new Dimension(text.computeSize(rect.width, -1)));
            } else {
                int avr = FigureUtilities.getFontMetrics((Font)text.getFont()).getAverageCharWidth();
                rect.setSize(new Dimension(text.computeSize(-1, -1)).expand(avr * 2, 0));
            }
            if (!rect.equals((Object)new Rectangle(text.getBounds()))) {
                text.setBounds(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }
}

