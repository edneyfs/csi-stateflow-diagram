/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.EventConnection
 *  com.csi.gmf.stateflow.ExecutionFlowObject
 *  com.csi.gmf.stateflow.State
 *  com.csi.gmf.stateflow.StateflowPackage
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.draw2d.IFigure
 *  org.eclipse.draw2d.Label
 *  org.eclipse.draw2d.geometry.Point
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.EStructuralFeature
 *  org.eclipse.emf.transaction.RunnableWithResult$Impl
 *  org.eclipse.gef.AccessibleEditPart
 *  org.eclipse.gef.EditPolicy
 *  org.eclipse.gef.GraphicalEditPart
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gef.editparts.AbstractGraphicalEditPart
 *  org.eclipse.gef.editparts.AbstractGraphicalEditPart$AccessibleGraphicalEditPart
 *  org.eclipse.gef.requests.DirectEditRequest
 *  org.eclipse.gef.tools.DirectEditManager
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParser
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus
 *  org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions
 *  org.eclipse.gmf.runtime.common.ui.services.parser.ParserService
 *  org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener
 *  org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart
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
 *  org.eclipse.jface.text.contentassist.IContentAssistProcessor
 *  org.eclipse.jface.viewers.ICellEditorValidator
 *  org.eclipse.swt.accessibility.AccessibleEvent
 *  org.eclipse.swt.graphics.Color
 *  org.eclipse.swt.graphics.FontData
 *  org.eclipse.swt.graphics.Image
 */
package com.csi.gmf.stateflow.diagram.edit.parts;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.diagram.edit.parts.StateflowEditPartFactory;
import com.csi.gmf.stateflow.diagram.edit.policies.StateflowTextSelectionEditPolicy;
import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;
import com.csi.gmf.stateflow.diagram.providers.StateflowParserProvider;
import java.util.Collections;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
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
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;

public class EventConnectionEventIDEditPart
extends LabelEditPart
implements ITextAwareEditPart {
    public static final int VISUAL_ID = 4004;
    private DirectEditManager manager;
    private IParser parser;
    private List parserElements;
    private String defaultText;

    static {
        EventConnectionEventIDEditPart.registerSnapBackPosition((String)StateflowVisualIDRegistry.getType(4004), (Point)new Point(0, 40));
    }

    public EventConnectionEventIDEditPart(View view) {
        super(view);
    }

    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        this.installEditPolicy("DirectEditPolicy", (EditPolicy)new LabelDirectEditPolicy());
    }

    public int getKeyPoint() {
        return 4;
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
        return null;
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
        if (pdEditPolicy instanceof StateflowTextSelectionEditPolicy) {
            ((StateflowTextSelectionEditPolicy)pdEditPolicy).refreshFeedback();
        }
    }

    public String getEditText() {
        if (this.getParserElement() == null || this.getParser() == null) {
            return "";
        }
        return this.getParser().getEditString((IAdaptable)new EObjectAdapter(this.getParserElement()), this.getParserOptions().intValue());
    }

    protected boolean isEditable() {
        return this.getParser() != null;
    }

    public ICellEditorValidator getEditTextValidator() {
        return new ICellEditorValidator(){

            public String isValid(final Object value) {
                if (value instanceof String) {
                    final EObject element = EventConnectionEventIDEditPart.this.getParserElement();
                    final IParser parser = EventConnectionEventIDEditPart.this.getParser();
                    try {
                        IParserEditStatus valid = (IParserEditStatus)EventConnectionEventIDEditPart.this.getEditingDomain().runExclusive((Runnable)new RunnableWithResult.Impl(){

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
            StateflowParserProvider.HintAdapter hintAdapter = new StateflowParserProvider.HintAdapter(StateflowElementTypes.EventConnection_3002, this.getParserElement(), parserHint);
            this.parser = ParserService.getInstance().getParser((IAdaptable)hintAdapter);
        }
        return this.parser;
    }

    protected DirectEditManager getManager() {
        if (this.manager == null) {
            this.setManager((DirectEditManager)new TextDirectEditManager((GraphicalEditPart)this, TextDirectEditManager.getTextCellEditorClass((GraphicalEditPart)this), StateflowEditPartFactory.getTextCellEditorLocator(this)));
        }
        return this.manager;
    }

    protected void setManager(DirectEditManager manager) {
        this.manager = manager;
    }

    protected void performDirectEdit() {
        this.getManager().show();
    }

    protected void performDirectEdit(Point eventLocation) {
        if (this.getManager().getClass() == TextDirectEditManager.class) {
            ((TextDirectEditManager)this.getManager()).show(eventLocation.getSWTPoint());
        }
    }

    private void performDirectEdit(char initialCharacter) {
        if (this.getManager() instanceof TextDirectEditManager) {
            ((TextDirectEditManager)this.getManager()).show(initialCharacter);
        } else {
            this.performDirectEdit();
        }
    }

    protected void performDirectEditRequest(Request request) {
        EventConnection evtConnection = (EventConnection)this.getPrimaryView().getElement();
        if (evtConnection.getSourcePathKey() == null && evtConnection.getDestinationPathKey() == null) {
            this.updatePathKeys();
        }
        final Request theRequest = request;
        try {
            this.getEditingDomain().runExclusive(new Runnable(){

                @Override
                public void run() {
                    if (EventConnectionEventIDEditPart.this.isActive() && EventConnectionEventIDEditPart.this.isEditable()) {
                        if (theRequest.getExtendedData().get("directedit_extendeddata_initial_char") instanceof Character) {
                            Character initialChar = (Character)theRequest.getExtendedData().get("directedit_extendeddata_initial_char");
                            EventConnectionEventIDEditPart.this.performDirectEdit(initialChar.charValue());
                        } else if (theRequest instanceof DirectEditRequest && EventConnectionEventIDEditPart.this.getEditText().equals(EventConnectionEventIDEditPart.this.getLabelText())) {
                            DirectEditRequest editRequest = (DirectEditRequest)theRequest;
                            EventConnectionEventIDEditPart.this.performDirectEdit(editRequest.getLocation());
                        } else {
                            EventConnectionEventIDEditPart.this.performDirectEdit();
                        }
                    }
                }
            });
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updatePathKeys() {
        State state = ((EventConnection)this.getPrimaryView().getElement()).getSourceState();
        SetRequest setRequest = new SetRequest(this.getEditingDomain(), this.getPrimaryView().getElement(), (EStructuralFeature)StateflowPackage.eINSTANCE.getEventConnection_SourcePathKey(), (Object)state.getPathKey());
        SetValueCommand operation = new SetValueCommand(setRequest);
        ICommandProxy command = new ICommandProxy((ICommand)operation);
        DiagramCommandStack stack = this.getDiagramEditDomain().getDiagramCommandStack();
        stack.execute((Command)command);
        ExecutionFlowObject flowObject = ((EventConnection)this.getPrimaryView().getElement()).getDestinationFlow();
        setRequest = new SetRequest(this.getEditingDomain(), this.getPrimaryView().getElement(), (EStructuralFeature)StateflowPackage.eINSTANCE.getEventConnection_DestinationPathKey(), (Object)flowObject.getPathKey());
        operation = new SetValueCommand(setRequest);
        command = new ICommandProxy((ICommand)operation);
        stack.execute((Command)command);
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
        if (pdEditPolicy instanceof StateflowTextSelectionEditPolicy) {
            ((StateflowTextSelectionEditPolicy)pdEditPolicy).refreshFeedback();
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
                    e.result = EventConnectionEventIDEditPart.this.getLabelTextHelper(EventConnectionEventIDEditPart.this.getFigure());
                }
            };
        }
        return this.accessibleEP;
    }

    private View getFontStyleOwnerView() {
        return this.getPrimaryView();
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

