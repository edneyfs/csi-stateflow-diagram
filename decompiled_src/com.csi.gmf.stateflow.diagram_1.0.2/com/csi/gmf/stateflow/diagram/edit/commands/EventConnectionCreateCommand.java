/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.EventConnection
 *  com.csi.gmf.stateflow.ExecutionFlowObject
 *  com.csi.gmf.stateflow.State
 *  com.csi.gmf.stateflow.StateFlow
 *  com.csi.gmf.stateflow.StateflowFactory
 *  com.csi.gmf.stateflow.StateflowPackage
 *  org.eclipse.core.commands.ExecutionException
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.common.core.command.CommandResult
 *  org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest
 */
package com.csi.gmf.stateflow.diagram.edit.commands;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowFactory;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.diagram.edit.policies.StateflowBaseItemSemanticEditPolicy;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

public class EventConnectionCreateCommand
extends CreateElementCommand {
    private final EObject source;
    private final EObject target;
    private StateFlow container;

    public EventConnectionCreateCommand(CreateRelationshipRequest request, EObject source, EObject target) {
        super((CreateElementRequest)request);
        this.source = source;
        this.target = target;
        if (request.getContainmentFeature() == null) {
            this.setContainmentFeature(StateflowPackage.eINSTANCE.getStateFlow_StateConnections());
        }
        EObject element = source;
        while (element != null) {
            if (element instanceof StateFlow) {
                this.container = (StateFlow)element;
                super.setElementToEdit((EObject)this.container);
                break;
            }
            element = element.eContainer();
        }
    }

    public boolean canExecute() {
        if (this.source == null && this.target == null) {
            return false;
        }
        if (this.source != null && !(this.source instanceof State)) {
            return false;
        }
        if (this.target != null && !(this.target instanceof ExecutionFlowObject)) {
            return false;
        }
        if (this.getSource() == null) {
            return true;
        }
        if (this.getContainer() == null) {
            return false;
        }
        return StateflowBaseItemSemanticEditPolicy.LinkConstraints.canCreateEventConnection_3002(this.getContainer(), this.getSource(), this.getTarget());
    }

    protected EObject doDefaultElementCreation() {
        EventConnection newElement = StateflowFactory.eINSTANCE.createEventConnection();
        this.getContainer().getStateConnections().add((Object)newElement);
        newElement.setSourceState(this.getSource());
        newElement.setDestinationFlow(this.getTarget());
        return newElement;
    }

    protected EClass getEClassToEdit() {
        return StateflowPackage.eINSTANCE.getStateFlow();
    }

    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        if (!this.canExecute()) {
            throw new ExecutionException("Invalid arguments in create link command");
        }
        return super.doExecuteWithResult(monitor, info);
    }

    protected ConfigureRequest createConfigureRequest() {
        ConfigureRequest request = super.createConfigureRequest();
        request.setParameter("CreateRelationshipRequest.source", (Object)this.getSource());
        request.setParameter("CreateRelationshipRequest.target", (Object)this.getTarget());
        return request;
    }

    protected void setElementToEdit(EObject element) {
        throw new UnsupportedOperationException();
    }

    protected State getSource() {
        return (State)this.source;
    }

    protected ExecutionFlowObject getTarget() {
        return (ExecutionFlowObject)this.target;
    }

    public StateFlow getContainer() {
        return this.container;
    }
}

