/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.commands.operations.IUndoableOperation
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gef.commands.UnexecutableCommand
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.diagram.ui.actions.AbstractDeleteFromAction
 *  org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy
 *  org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy
 *  org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages
 *  org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand
 *  org.eclipse.ui.ISharedImages
 *  org.eclipse.ui.IWorkbenchPage
 *  org.eclipse.ui.IWorkbenchPart
 *  org.eclipse.ui.PlatformUI
 */
package com.csi.gmf.stateflow.diagram.part;

import java.util.Iterator;
import java.util.List;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.actions.AbstractDeleteFromAction;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class DeleteElementAction
extends AbstractDeleteFromAction {
    public DeleteElementAction(IWorkbenchPart part) {
        super(part);
    }

    public DeleteElementAction(IWorkbenchPage workbenchPage) {
        super(workbenchPage);
    }

    public void init() {
        super.init();
        this.setId("deleteFromModelAction");
        this.setText(DiagramUIMessages.DiagramEditor_Delete_from_Model);
        this.setToolTipText(DiagramUIMessages.DiagramEditor_Delete_from_ModelToolTip);
        ISharedImages workbenchImages = PlatformUI.getWorkbench().getSharedImages();
        this.setHoverImageDescriptor(workbenchImages.getImageDescriptor("IMG_TOOL_DELETE"));
        this.setImageDescriptor(workbenchImages.getImageDescriptor("IMG_TOOL_DELETE"));
        this.setDisabledImageDescriptor(workbenchImages.getImageDescriptor("IMG_TOOL_DELETE_DISABLED"));
    }

    protected String getCommandLabel() {
        return DiagramUIMessages.DiagramEditor_Delete_from_Model;
    }

    protected Command getCommand(Request request) {
        List operationSet = this.getOperationSet();
        if (operationSet.isEmpty()) {
            return UnexecutableCommand.INSTANCE;
        }
        Iterator editParts = operationSet.iterator();
        CompositeTransactionalCommand command = new CompositeTransactionalCommand(this.getEditingDomain(), this.getCommandLabel());
        while (editParts.hasNext()) {
            EditPart editPart = (EditPart)editParts.next();
            Command curCommand = editPart.getCommand(request);
            if (curCommand == null) continue;
            command.compose((IUndoableOperation)new CommandProxy(curCommand));
        }
        if (command.isEmpty() || command.size() != operationSet.size()) {
            return UnexecutableCommand.INSTANCE;
        }
        return new ICommandProxy((ICommand)command);
    }
}

