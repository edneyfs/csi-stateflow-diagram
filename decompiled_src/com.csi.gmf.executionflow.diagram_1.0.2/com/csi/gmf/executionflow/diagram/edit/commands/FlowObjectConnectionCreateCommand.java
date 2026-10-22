/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionFlow
 *  com.csi.gmf.executionflow.ExecutionflowFactory
 *  com.csi.gmf.executionflow.ExecutionflowPackage
 *  com.csi.gmf.executionflow.FlowObject
 *  com.csi.gmf.executionflow.FlowObjectConnection
 *  com.csi.gmf.executionflow.SourceConnectable
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
package com.csi.gmf.executionflow.diagram.edit.commands;

import com.csi.gmf.executionflow.ExecutionFlow;
import com.csi.gmf.executionflow.ExecutionflowFactory;
import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.FlowObject;
import com.csi.gmf.executionflow.FlowObjectConnection;
import com.csi.gmf.executionflow.SourceConnectable;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutionflowBaseItemSemanticEditPolicy;
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

public class FlowObjectConnectionCreateCommand
extends CreateElementCommand {
    private final EObject source;
    private final EObject target;
    private ExecutionFlow container;

    public FlowObjectConnectionCreateCommand(CreateRelationshipRequest request, EObject source, EObject target) {
        super((CreateElementRequest)request);
        this.source = source;
        this.target = target;
        if (request.getContainmentFeature() == null) {
            this.setContainmentFeature(ExecutionflowPackage.eINSTANCE.getExecutionFlow_FlowConnections());
        }
        EObject element = source;
        while (element != null) {
            if (element instanceof ExecutionFlow) {
                this.container = (ExecutionFlow)element;
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
        if (this.source != null && !(this.source instanceof SourceConnectable)) {
            return false;
        }
        if (this.target != null && !(this.target instanceof FlowObject)) {
            return false;
        }
        if (this.getSource() == null) {
            return true;
        }
        if (this.getContainer() == null) {
            return false;
        }
        return ExecutionflowBaseItemSemanticEditPolicy.LinkConstraints.canCreateFlowObjectConnection_3001(this.getContainer(), this.getSource(), this.getTarget());
    }

    protected EObject doDefaultElementCreation() {
        FlowObjectConnection newElement = ExecutionflowFactory.eINSTANCE.createFlowObjectConnection();
        this.getContainer().getFlowConnections().add((Object)newElement);
        newElement.setSourceObject(this.getSource());
        newElement.setDestinationObject(this.getTarget());
        return newElement;
    }

    protected EClass getEClassToEdit() {
        return ExecutionflowPackage.eINSTANCE.getExecutionFlow();
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

    protected SourceConnectable getSource() {
        return (SourceConnectable)this.source;
    }

    protected FlowObject getTarget() {
        return (FlowObject)this.target;
    }

    public ExecutionFlow getContainer() {
        return this.container;
    }
}

