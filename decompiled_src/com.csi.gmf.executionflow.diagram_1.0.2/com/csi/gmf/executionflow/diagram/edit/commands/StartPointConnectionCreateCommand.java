/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionFlow
 *  com.csi.gmf.executionflow.ExecutionflowFactory
 *  com.csi.gmf.executionflow.ExecutionflowPackage
 *  com.csi.gmf.executionflow.SourceConnectable
 *  com.csi.gmf.executionflow.StartPoint
 *  com.csi.gmf.executionflow.StartPointConnection
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
import com.csi.gmf.executionflow.SourceConnectable;
import com.csi.gmf.executionflow.StartPoint;
import com.csi.gmf.executionflow.StartPointConnection;
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

public class StartPointConnectionCreateCommand
extends CreateElementCommand {
    private final EObject source;
    private final EObject target;
    private ExecutionFlow container;

    public StartPointConnectionCreateCommand(CreateRelationshipRequest request, EObject source, EObject target) {
        super((CreateElementRequest)request);
        this.source = source;
        this.target = target;
        if (request.getContainmentFeature() == null) {
            this.setContainmentFeature(ExecutionflowPackage.eINSTANCE.getExecutionFlow_StartPointConnection());
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
        if (this.source != null && !(this.source instanceof StartPoint)) {
            return false;
        }
        if (this.target != null && !(this.target instanceof SourceConnectable)) {
            return false;
        }
        if (this.getSource() == null) {
            return true;
        }
        if (this.getContainer() == null) {
            return false;
        }
        return ExecutionflowBaseItemSemanticEditPolicy.LinkConstraints.canCreateStartPointConnection_3002(this.getContainer(), this.getSource(), this.getTarget());
    }

    protected EObject doDefaultElementCreation() {
        StartPointConnection newElement = ExecutionflowFactory.eINSTANCE.createStartPointConnection();
        this.getContainer().setStartPointConnection(newElement);
        newElement.setStartPoint(this.getSource());
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

    protected StartPoint getSource() {
        return (StartPoint)this.source;
    }

    protected SourceConnectable getTarget() {
        return (SourceConnectable)this.target;
    }

    public ExecutionFlow getContainer() {
        return this.container;
    }
}

