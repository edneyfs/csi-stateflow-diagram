/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.EventConnection
 *  com.csi.gmf.stateflow.ExecutionFlowObject
 *  com.csi.gmf.stateflow.State
 *  com.csi.gmf.stateflow.StateFlow
 *  org.eclipse.core.commands.ExecutionException
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.common.core.command.CommandResult
 *  org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest
 */
package com.csi.gmf.stateflow.diagram.edit.commands;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.diagram.edit.policies.StateflowBaseItemSemanticEditPolicy;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

public class EventConnectionReorientCommand
extends EditElementCommand {
    private final int reorientDirection;
    private final EObject oldEnd;
    private final EObject newEnd;

    public EventConnectionReorientCommand(ReorientRelationshipRequest request) {
        super(request.getLabel(), request.getRelationship(), (IEditCommandRequest)request);
        this.reorientDirection = request.getDirection();
        this.oldEnd = request.getOldRelationshipEnd();
        this.newEnd = request.getNewRelationshipEnd();
    }

    public boolean canExecute() {
        if (!(this.getElementToEdit() instanceof EventConnection)) {
            return false;
        }
        if (this.reorientDirection == 1) {
            return this.canReorientSource();
        }
        if (this.reorientDirection == 2) {
            return this.canReorientTarget();
        }
        return false;
    }

    protected boolean canReorientSource() {
        if (!(this.oldEnd instanceof State) || !(this.newEnd instanceof State)) {
            return false;
        }
        ExecutionFlowObject target = this.getLink().getDestinationFlow();
        if (!(this.getLink().eContainer() instanceof StateFlow)) {
            return false;
        }
        StateFlow container = (StateFlow)this.getLink().eContainer();
        return StateflowBaseItemSemanticEditPolicy.LinkConstraints.canExistEventConnection_3002(container, this.getNewSource(), target);
    }

    protected boolean canReorientTarget() {
        if (!(this.oldEnd instanceof ExecutionFlowObject) || !(this.newEnd instanceof ExecutionFlowObject)) {
            return false;
        }
        State source = this.getLink().getSourceState();
        if (!(this.getLink().eContainer() instanceof StateFlow)) {
            return false;
        }
        StateFlow container = (StateFlow)this.getLink().eContainer();
        return StateflowBaseItemSemanticEditPolicy.LinkConstraints.canExistEventConnection_3002(container, source, this.getNewTarget());
    }

    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        if (!this.canExecute()) {
            throw new ExecutionException("Invalid arguments in reorient link command");
        }
        if (this.reorientDirection == 1) {
            return this.reorientSource();
        }
        if (this.reorientDirection == 2) {
            return this.reorientTarget();
        }
        throw new IllegalStateException();
    }

    protected CommandResult reorientSource() throws ExecutionException {
        this.getLink().setSourceState(this.getNewSource());
        return CommandResult.newOKCommandResult((Object)this.getLink());
    }

    protected CommandResult reorientTarget() throws ExecutionException {
        this.getLink().setDestinationFlow(this.getNewTarget());
        return CommandResult.newOKCommandResult((Object)this.getLink());
    }

    protected EventConnection getLink() {
        return (EventConnection)this.getElementToEdit();
    }

    protected State getOldSource() {
        return (State)this.oldEnd;
    }

    protected State getNewSource() {
        return (State)this.newEnd;
    }

    protected ExecutionFlowObject getOldTarget() {
        return (ExecutionFlowObject)this.oldEnd;
    }

    protected ExecutionFlowObject getNewTarget() {
        return (ExecutionFlowObject)this.newEnd;
    }
}

