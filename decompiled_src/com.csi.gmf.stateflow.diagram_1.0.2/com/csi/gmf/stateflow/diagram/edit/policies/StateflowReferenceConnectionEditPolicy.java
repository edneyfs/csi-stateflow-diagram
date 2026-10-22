/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gef.commands.CompoundCommand
 *  org.eclipse.gef.commands.UnexecutableCommand
 *  org.eclipse.gef.editparts.AbstractConnectionEditPart
 *  org.eclipse.gef.editpolicies.ConnectionEditPolicy
 *  org.eclipse.gef.requests.GroupRequest
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand
 *  org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages
 *  org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper
 *  org.eclipse.gmf.runtime.diagram.ui.requests.GroupRequestViaKeyboard
 *  org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.dialogs.MessageDialogWithToggle
 *  org.eclipse.jface.preference.IPreferenceStore
 *  org.eclipse.jface.util.Assert
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 */
package com.csi.gmf.stateflow.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.diagram.ui.requests.GroupRequestViaKeyboard;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.Assert;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class StateflowReferenceConnectionEditPolicy
extends ConnectionEditPolicy {
    private static final String DELETE_FROM_DIAGRAM_DLG_TITLE = DiagramUIMessages.PromptingDeleteAction_DeleteFromDiagramDialog_Title;
    private static final String DELETE_FROM_DIAGRAM_DLG_MESSAGE = DiagramUIMessages.PromptingDeleteAction_DeleteFromDiagramDialog_Message;
    private static final String DELETE_FROM_MODEL_DLG_TOGGLE_LABEL = DiagramUIMessages.MessageDialogWithToggle_DoNotPromptAgainToggle_label;

    protected final Command getDeleteCommand(GroupRequest deleteRequest) {
        GroupRequestViaKeyboard groupRequestViaKeyboard;
        boolean isDeleteFromKeyBoard = deleteRequest instanceof GroupRequestViaKeyboard;
        if (this.shouldDeleteSemantic()) {
            return this.createDeleteSemanticCommand(deleteRequest);
        }
        boolean proceedToDeleteView = true;
        if (isDeleteFromKeyBoard && (groupRequestViaKeyboard = (GroupRequestViaKeyboard)deleteRequest).isShowInformationDialog()) {
            proceedToDeleteView = this.showPrompt();
            groupRequestViaKeyboard.setShowInformationDialog(false);
            if (!proceedToDeleteView) {
                return UnexecutableCommand.INSTANCE;
            }
        }
        return this.createDeleteViewCommand(deleteRequest);
    }

    protected boolean shouldDeleteSemantic() {
        Assert.isTrue((boolean)(this.getHost() instanceof AbstractConnectionEditPart));
        AbstractConnectionEditPart cep = (AbstractConnectionEditPart)this.getHost();
        boolean isCanonical = false;
        if (cep.getSource() != null) {
            isCanonical = this.IsCanonical(cep.getSource());
        }
        if (cep.getTarget() != null) {
            return isCanonical ? isCanonical : this.IsCanonical(cep.getTarget());
        }
        return isCanonical;
    }

    private boolean IsCanonical(EditPart ep) {
        EditPart parent = ep.getParent();
        return parent instanceof GraphicalEditPart ? ((GraphicalEditPart)parent).isCanonical() : false;
    }

    protected Command createDeleteViewCommand(GroupRequest deleteRequest) {
        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart)this.getHost()).getEditingDomain();
        return new ICommandProxy((ICommand)new DeleteCommand(editingDomain, (View)this.getHost().getModel()));
    }

    protected Command createDeleteSemanticCommand(GroupRequest deleteRequest) {
        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart)this.getHost()).getEditingDomain();
        EditCommandRequestWrapper semReq = new EditCommandRequestWrapper((IEditCommandRequest)new DestroyElementRequest(editingDomain, false), deleteRequest.getExtendedData());
        Command semanticCmd = this.getHost().getCommand((Request)semReq);
        if (semanticCmd != null && semanticCmd.canExecute()) {
            CompoundCommand cc = new CompoundCommand();
            cc.add(semanticCmd);
            return cc;
        }
        return null;
    }

    private boolean showPrompt() {
        boolean prompt = ((IPreferenceStore)((IGraphicalEditPart)this.getHost()).getDiagramPreferencesHint().getPreferenceStore()).getBoolean("Global.promptOnDelFromDiagram");
        if (prompt) {
            return this.showMessageDialog();
        }
        return true;
    }

    private boolean showMessageDialog() {
        MessageDialogWithToggle dialog = MessageDialogWithToggle.openYesNoQuestion((Shell)Display.getCurrent().getActiveShell(), (String)DELETE_FROM_DIAGRAM_DLG_TITLE, (String)DELETE_FROM_DIAGRAM_DLG_MESSAGE, (String)DELETE_FROM_MODEL_DLG_TOGGLE_LABEL, (boolean)false, (IPreferenceStore)((IPreferenceStore)((IGraphicalEditPart)this.getHost()).getDiagramPreferencesHint().getPreferenceStore()), (String)"Global.promptOnDelFromDiagram");
        return dialog.getReturnCode() == 2;
    }

    public Command getCommand(Request request) {
        if (request instanceof GroupRequestViaKeyboard) {
            return this.getDeleteCommand((GroupRequest)request);
        }
        return super.getCommand(request);
    }
}

