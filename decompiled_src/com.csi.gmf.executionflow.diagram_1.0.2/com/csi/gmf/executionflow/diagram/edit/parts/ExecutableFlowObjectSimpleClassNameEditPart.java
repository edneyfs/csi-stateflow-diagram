/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionflowPackage
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.draw2d.IFigure
 *  org.eclipse.draw2d.Label
 *  org.eclipse.draw2d.geometry.Point
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.ecore.ENamedElement
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.EStructuralFeature
 *  org.eclipse.emf.transaction.RunnableWithResult$Impl
 *  org.eclipse.gef.AccessibleEditPart
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.EditPolicy
 *  org.eclipse.gef.GraphicalEditPart
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gef.editparts.AbstractGraphicalEditPart
 *  org.eclipse.gef.editparts.AbstractGraphicalEditPart$AccessibleGraphicalEditPart
 *  org.eclipse.gef.editpolicies.NonResizableEditPolicy
 *  org.eclipse.gef.handles.NonResizableHandleKit
 *  org.eclipse.gef.tools.DirectEditManager
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParser
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus
 *  org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions
 *  org.eclipse.gmf.runtime.common.ui.services.parser.ParserService
 *  org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener
 *  org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy
 *  org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry
 *  org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack
 *  org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager
 *  org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel
 *  org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter
 *  org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest
 *  org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser
 *  org.eclipse.gmf.runtime.notation.FontStyle
 *  org.eclipse.gmf.runtime.notation.NotationPackage
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jdt.core.IType
 *  org.eclipse.jdt.core.search.IJavaSearchScope
 *  org.eclipse.jdt.core.search.SearchEngine
 *  org.eclipse.jdt.ui.JavaUI
 *  org.eclipse.jface.dialogs.ProgressMonitorDialog
 *  org.eclipse.jface.operation.IRunnableContext
 *  org.eclipse.jface.text.contentassist.IContentAssistProcessor
 *  org.eclipse.jface.viewers.ICellEditorValidator
 *  org.eclipse.swt.accessibility.AccessibleEvent
 *  org.eclipse.swt.graphics.Color
 *  org.eclipse.swt.graphics.FontData
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.ui.IWorkbenchWindow
 *  org.eclipse.ui.PlatformUI
 *  org.eclipse.ui.dialogs.SelectionDialog
 */
package com.csi.gmf.executionflow.diagram.edit.parts;

import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionflowEditPartFactory;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutableFlowObjectOpenEditPolicy;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutionflowTextSelectionEditPolicy;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowElementTypes;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowParserProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.handles.NonResizableHandleKit;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SelectionDialog;

public class ExecutableFlowObjectSimpleClassNameEditPart
extends CompartmentEditPart
implements ITextAwareEditPart {
    public static final int VISUAL_ID = 4002;
    private DirectEditManager manager;
    private IParser parser;
    private List parserElements;
    private String defaultText;

    public ExecutableFlowObjectSimpleClassNameEditPart(View view) {
        super((EObject)view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("DirectEditPolicy", (EditPolicy)new LabelDirectEditPolicy());
        this.installEditPolicy("PrimaryDrag Policy", (EditPolicy)new NonResizableEditPolicy(){

            protected List createSelectionHandles() {
                ArrayList handles = new ArrayList();
                NonResizableHandleKit.addMoveHandle((GraphicalEditPart)((GraphicalEditPart)this.getHost()), handles);
                return handles;
            }

            public Command getCommand(Request request) {
                return null;
            }

            public boolean understandsRequest(Request request) {
                return false;
            }
        });
        ExecutableFlowObjectOpenEditPolicy policy = new ExecutableFlowObjectOpenEditPolicy();
        policy.setHost((EditPart)this);
        this.installEditPolicy("OpenPolicy", (EditPolicy)policy);
    }

    protected String getLabelTextHelper(IFigure figure) {
        if (figure instanceof WrappingLabel) {
            return ((WrappingLabel)figure).getText();
        }
        return ((Label)figure).getText();
    }

    protected void setLabelTextHelper(IFigure figure, String text) {
        if (figure instanceof WrappingLabel) {
            ((WrappingLabel)figure).setText(text);
        } else {
            ((Label)figure).setText(text);
        }
    }

    protected Image getLabelIconHelper(IFigure figure) {
        if (figure instanceof WrappingLabel) {
            return ((WrappingLabel)figure).getIcon();
        }
        return ((Label)figure).getIcon();
    }

    protected void setLabelIconHelper(IFigure figure, Image icon) {
        if (figure instanceof WrappingLabel) {
            ((WrappingLabel)figure).setIcon(icon);
        } else {
            ((Label)figure).setIcon(icon);
        }
    }

    public void setLabel(WrappingLabel figure) {
        this.unregisterVisuals();
        this.setFigure((IFigure)figure);
        this.defaultText = this.getLabelTextHelper((IFigure)figure);
        this.registerVisuals();
        this.refreshVisuals();
    }

    protected List getModelChildren() {
        return Collections.EMPTY_LIST;
    }

    public IGraphicalEditPart getChildBySemanticHint(String semanticHint) {
        return null;
    }

    protected EObject getParserElement() {
        return this.resolveSemanticElement();
    }

    protected Image getLabelIcon() {
        EObject parserElement = this.getParserElement();
        if (parserElement == null) {
            return null;
        }
        return ExecutionflowElementTypes.getImage((ENamedElement)parserElement.eClass());
    }

    protected String getLabelText() {
        String text = null;
        EObject parserElement = this.getParserElement();
        if (parserElement != null && this.getParser() != null) {
            text = this.getParser().getPrintString((IAdaptable)new EObjectAdapter(parserElement), this.getParserOptions().intValue());
        }
        if (text == null || text.length() == 0) {
            text = this.defaultText;
        }
        return text;
    }

    public void setLabelText(String text) {
        this.setLabelTextHelper(this.getFigure(), text);
        EditPolicy pdEditPolicy = this.getEditPolicy("PrimaryDrag Policy");
        if (pdEditPolicy instanceof ExecutionflowTextSelectionEditPolicy) {
            ((ExecutionflowTextSelectionEditPolicy)pdEditPolicy).refreshFeedback();
        }
    }

    public String getEditText() {
        if (this.getParserElement() == null || this.getParser() == null) {
            return "";
        }
        return this.getParser().getEditString((IAdaptable)new EObjectAdapter(this.getParserElement()), this.getParserOptions().intValue());
    }

    protected boolean isEditable() {
        return false;
    }

    public ICellEditorValidator getEditTextValidator() {
        return new ICellEditorValidator(){

            public String isValid(final Object value) {
                if (value instanceof String) {
                    final EObject element = ExecutableFlowObjectSimpleClassNameEditPart.this.getParserElement();
                    final IParser parser = ExecutableFlowObjectSimpleClassNameEditPart.this.getParser();
                    try {
                        IParserEditStatus valid = (IParserEditStatus)ExecutableFlowObjectSimpleClassNameEditPart.this.getEditingDomain().runExclusive((Runnable)new RunnableWithResult.Impl(){

                            public void run() {
                                this.setResult(parser.isValidEditString((IAdaptable)new EObjectAdapter(element), (String)value));
                            }
                        });
                        return valid.getCode() == 0 ? null : valid.getMessage();
                    }
                    catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
                return null;
            }
        };
    }

    public IContentAssistProcessor getCompletionProcessor() {
        if (this.getParserElement() == null || this.getParser() == null) {
            return null;
        }
        return this.getParser().getCompletionProcessor((IAdaptable)new EObjectAdapter(this.getParserElement()));
    }

    public ParserOptions getParserOptions() {
        return ParserOptions.NONE;
    }

    public IParser getParser() {
        if (this.parser == null) {
            String parserHint = ((View)this.getModel()).getType();
            ExecutionflowParserProvider.HintAdapter hintAdapter = new ExecutionflowParserProvider.HintAdapter(ExecutionflowElementTypes.ExecutableFlowObject_1002, this.getParserElement(), parserHint);
            this.parser = ParserService.getInstance().getParser((IAdaptable)hintAdapter);
        }
        return this.parser;
    }

    protected DirectEditManager getManager() {
        if (this.manager == null) {
            this.setManager((DirectEditManager)new TextDirectEditManager((GraphicalEditPart)this, TextDirectEditManager.getTextCellEditorClass((GraphicalEditPart)this), ExecutionflowEditPartFactory.getTextCellEditorLocator(this)));
        }
        return this.manager;
    }

    protected void setManager(DirectEditManager manager) {
        this.manager = manager;
    }

    protected void performDirectEdit() {
        try {
            IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            Shell parent = window.getShell();
            SelectionDialog dialog = JavaUI.createTypeDialog((Shell)parent, (IRunnableContext)new ProgressMonitorDialog(parent), (IJavaSearchScope)SearchEngine.createWorkspaceScope(), (int)2, (boolean)false);
            dialog.setTitle("Search for ExecutableFlowObjects");
            if (dialog.open() != 1) {
                Object[] types = dialog.getResult();
                IType type = (IType)types[0];
                String className = type.getFullyQualifiedName();
                DiagramCommandStack stack = this.getDiagramEditDomain().getDiagramCommandStack();
                SetRequest setRequestSimpleClassName = new SetRequest(this.getEditingDomain(), this.getPrimaryView().getElement(), (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getExecutableFlowObject_SimpleClassName(), (Object)className.substring(className.lastIndexOf(".") + 1, className.length()));
                SetValueCommand operationSimpleClassName = new SetValueCommand(setRequestSimpleClassName);
                ICommandProxy commandClassName = new ICommandProxy((ICommand)operationSimpleClassName);
                stack.execute((Command)commandClassName);
                SetRequest setRequestClassName = new SetRequest(this.getEditingDomain(), this.getPrimaryView().getElement(), (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getExecutableFlowObject_ClassName(), (Object)className);
                SetValueCommand operationClassName = new SetValueCommand(setRequestClassName);
                ICommandProxy commandSimpleClassName = new ICommandProxy((ICommand)operationClassName);
                stack.execute((Command)commandSimpleClassName);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void performDirectEdit(Point eventLocation) {
    }

    private void performDirectEdit(char initialCharacter) {
    }

    protected void performDirectEditRequest(Request request) {
        this.performDirectEdit();
    }

    protected void refreshVisuals() {
        super.refreshVisuals();
        this.refreshLabel();
        this.refreshFont();
        this.refreshFontColor();
        this.refreshUnderline();
        this.refreshStrikeThrough();
    }

    protected void refreshLabel() {
        this.setLabelTextHelper(this.getFigure(), this.getLabelText());
        this.setLabelIconHelper(this.getFigure(), this.getLabelIcon());
        EditPolicy pdEditPolicy = this.getEditPolicy("PrimaryDrag Policy");
        if (pdEditPolicy instanceof ExecutionflowTextSelectionEditPolicy) {
            ((ExecutionflowTextSelectionEditPolicy)pdEditPolicy).refreshFeedback();
        }
    }

    protected void refreshUnderline() {
        FontStyle style = (FontStyle)this.getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
        if (style != null && this.getFigure() instanceof WrappingLabel) {
            ((WrappingLabel)this.getFigure()).setTextUnderline(style.isUnderline());
        }
    }

    protected void refreshStrikeThrough() {
        FontStyle style = (FontStyle)this.getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
        if (style != null && this.getFigure() instanceof WrappingLabel) {
            ((WrappingLabel)this.getFigure()).setTextStrikeThrough(style.isStrikeThrough());
        }
    }

    protected void refreshFont() {
        FontStyle style = (FontStyle)this.getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
        if (style != null) {
            FontData fontData = new FontData(style.getFontName(), style.getFontHeight(), (style.isBold() ? 1 : 0) | (style.isItalic() ? 2 : 0));
            this.setFont(fontData);
        }
    }

    protected void setFontColor(Color color) {
        this.getFigure().setForegroundColor(color);
    }

    protected void addSemanticListeners() {
        if (this.getParser() instanceof ISemanticParser) {
            EObject element = this.resolveSemanticElement();
            this.parserElements = ((ISemanticParser)this.getParser()).getSemanticElementsBeingParsed(element);
            int i = 0;
            while (i < this.parserElements.size()) {
                this.addListenerFilter("SemanticModel" + i, (NotificationListener)this, (EObject)this.parserElements.get(i));
                ++i;
            }
        } else {
            super.addSemanticListeners();
        }
    }

    protected void removeSemanticListeners() {
        if (this.parserElements != null) {
            int i = 0;
            while (i < this.parserElements.size()) {
                this.removeListenerFilter("SemanticModel" + i);
                ++i;
            }
        } else {
            super.removeSemanticListeners();
        }
    }

    protected AccessibleEditPart getAccessibleEditPart() {
        if (this.accessibleEP == null) {
            this.accessibleEP = new AbstractGraphicalEditPart.AccessibleGraphicalEditPart((AbstractGraphicalEditPart)this){

                public void getName(AccessibleEvent e) {
                    e.result = ExecutableFlowObjectSimpleClassNameEditPart.this.getLabelTextHelper(ExecutableFlowObjectSimpleClassNameEditPart.this.getFigure());
                }
            };
        }
        return this.accessibleEP;
    }

    private View getFontStyleOwnerView() {
        return this.getPrimaryView();
    }

    protected void addNotationalListeners() {
        super.addNotationalListeners();
        this.addListenerFilter("PrimaryView", (NotificationListener)this, (EObject)this.getPrimaryView());
    }

    protected void removeNotationalListeners() {
        super.removeNotationalListeners();
        this.removeListenerFilter("PrimaryView");
    }

    protected void handleNotificationEvent(Notification event) {
        Object feature = event.getFeature();
        if (NotationPackage.eINSTANCE.getFontStyle_FontColor().equals(feature)) {
            Integer c = (Integer)event.getNewValue();
            this.setFontColor(DiagramColorRegistry.getInstance().getColor(c));
        } else if (NotationPackage.eINSTANCE.getFontStyle_Underline().equals(feature)) {
            this.refreshUnderline();
        } else if (NotationPackage.eINSTANCE.getFontStyle_StrikeThrough().equals(feature)) {
            this.refreshStrikeThrough();
        } else if (NotationPackage.eINSTANCE.getFontStyle_FontHeight().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_FontName().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_Bold().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_Italic().equals(feature)) {
            this.refreshFont();
        } else {
            ISemanticParser modelParser;
            if (this.getParser() != null && this.getParser().isAffectingEvent((Object)event, this.getParserOptions().intValue())) {
                this.refreshLabel();
            }
            if (this.getParser() instanceof ISemanticParser && (modelParser = (ISemanticParser)this.getParser()).areSemanticElementsAffected(null, (Object)event)) {
                this.removeSemanticListeners();
                if (this.resolveSemanticElement() != null) {
                    this.addSemanticListeners();
                }
                this.refreshLabel();
            }
        }
        super.handleNotificationEvent(event);
    }

    protected IFigure createFigure() {
        return null;
    }
}

