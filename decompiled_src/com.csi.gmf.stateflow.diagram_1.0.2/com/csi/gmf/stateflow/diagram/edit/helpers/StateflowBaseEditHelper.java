/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.commands.operations.IUndoableOperation
 *  org.eclipse.gmf.runtime.common.core.command.CompositeCommand
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelper
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest
 */
package com.csi.gmf.stateflow.diagram.edit.helpers;

import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelper;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;

public class StateflowBaseEditHelper
extends AbstractEditHelper {
    public static final String EDIT_POLICY_COMMAND = "edit policy command";

    protected ICommand getInsteadCommand(IEditCommandRequest req) {
        ICommand epCommand = (ICommand)req.getParameter(EDIT_POLICY_COMMAND);
        req.setParameter(EDIT_POLICY_COMMAND, null);
        ICommand ehCommand = super.getInsteadCommand(req);
        if (epCommand == null) {
            return ehCommand;
        }
        if (ehCommand == null) {
            return epCommand;
        }
        CompositeCommand command = new CompositeCommand(null);
        command.add((IUndoableOperation)epCommand);
        command.add((IUndoableOperation)ehCommand);
        return command;
    }

    protected ICommand getCreateCommand(CreateElementRequest req) {
        return null;
    }

    protected ICommand getCreateRelationshipCommand(CreateRelationshipRequest req) {
        return null;
    }

    protected ICommand getDestroyElementCommand(DestroyElementRequest req) {
        return null;
    }

    protected ICommand getDestroyReferenceCommand(DestroyReferenceRequest req) {
        return null;
    }
}

