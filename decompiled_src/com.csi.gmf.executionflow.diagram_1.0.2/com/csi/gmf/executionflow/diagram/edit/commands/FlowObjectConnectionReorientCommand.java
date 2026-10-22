/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionFlow
 *  com.csi.gmf.executionflow.FlowObject
 *  com.csi.gmf.executionflow.FlowObjectConnection
 *  com.csi.gmf.executionflow.SourceConnectable
 *  org.eclipse.core.commands.ExecutionException
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.common.core.command.CommandResult
 *  org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest
 */
package com.csi.gmf.executionflow.diagram.edit.commands;

import com.csi.gmf.executionflow.ExecutionFlow;
import com.csi.gmf.executionflow.FlowObject;
import com.csi.gmf.executionflow.FlowObjectConnection;
import com.csi.gmf.executionflow.SourceConnectable;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutionflowBaseItemSemanticEditPolicy;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

public class FlowObjectConnectionReorientCommand
extends EditElementCommand {
    private final int reorientDirection;
    private final EObject oldEnd;
    private final EObject newEnd;

    public FlowObjectConnectionReorientCommand(ReorientRelationshipRequest request) {
        super(request.getLabel(), request.getRelationship(), (IEditCommandRequest)request);
        this.reorientDirection = request.getDirection();
        this.oldEnd = request.getOldRelationshipEnd();
        this.newEnd = request.getNewRelationshipEnd();
    }

    public boolean canExecute() {
        if (!(this.getElementToEdit() instanceof FlowObjectConnection)) {
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
        if (!(this.oldEnd instanceof SourceConnectable) || !(this.newEnd instanceof SourceConnectable)) {
            return false;
        }
        FlowObject target = this.getLink().getDestinationObject();
        if (!(this.getLink().eContainer() instanceof ExecutionFlow)) {
            return false;
        }
        ExecutionFlow container = (ExecutionFlow)this.getLink().eContainer();
        return ExecutionflowBaseItemSemanticEditPolicy.LinkConstraints.canExistFlowObjectConnection_3001(container, this.getNewSource(), target);
    }

    protected boolean canReorientTarget() {
        if (!(this.oldEnd instanceof FlowObject) || !(this.newEnd instanceof FlowObject)) {
            return false;
        }
        SourceConnectable source = this.getLink().getSourceObject();
        if (!(this.getLink().eContainer() instanceof ExecutionFlow)) {
            return false;
        }
        ExecutionFlow container = (ExecutionFlow)this.getLink().eContainer();
        return ExecutionflowBaseItemSemanticEditPolicy.LinkConstraints.canExistFlowObjectConnection_3001(container, source, this.getNewTarget());
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
        this.getLink().setSourceObject(this.getNewSource());
        return CommandResult.newOKCommandResult((Object)this.getLink());
    }

    protected CommandResult reorientTarget() throws ExecutionException {
        this.getLink().setDestinationObject(this.getNewTarget());
        return CommandResult.newOKCommandResult((Object)this.getLink());
    }

    protected FlowObjectConnection getLink() {
        return (FlowObjectConnection)this.getElementToEdit();
    }

    protected SourceConnectable getOldSource() {
        return (SourceConnectable)this.oldEnd;
    }

    protected SourceConnectable getNewSource() {
        return (SourceConnectable)this.newEnd;
    }

    protected FlowObject getOldTarget() {
        return (FlowObject)this.oldEnd;
    }

    protected FlowObject getNewTarget() {
        return (FlowObject)this.newEnd;
    }
}

