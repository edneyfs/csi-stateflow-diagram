/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.commands.operations.IUndoableOperation
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gef.requests.CreateConnectionRequest
 *  org.eclipse.gmf.runtime.common.core.command.CompositeCommand
 *  org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter
 *  org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editpolicies.GraphicalNodeEditPolicy
 *  org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest
 *  org.eclipse.gmf.runtime.emf.type.core.IElementType
 */
package com.csi.gmf.stateflow.diagram.edit.policies;

import com.csi.gmf.stateflow.diagram.edit.commands.StateflowReorientConnectionViewCommand;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

public class StateflowGraphicalNodeEditPolicy
extends GraphicalNodeEditPolicy {
    protected IElementType getElementType(CreateConnectionRequest request) {
        if (request instanceof CreateConnectionViewAndElementRequest) {
            CreateElementRequestAdapter requestAdapter = ((CreateConnectionViewAndElementRequest)request).getConnectionViewAndElementDescriptor().getCreateElementRequestAdapter();
            return (IElementType)requestAdapter.getAdapter(IElementType.class);
        }
        return null;
    }

    protected Command getConnectionWithReorientedViewCompleteCommand(CreateConnectionRequest request) {
        ICommandProxy c = (ICommandProxy)super.getConnectionCompleteCommand(request);
        CompositeCommand cc = (CompositeCommand)c.getICommand();
        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart)this.getHost()).getEditingDomain();
        StateflowReorientConnectionViewCommand rcvCommand = new StateflowReorientConnectionViewCommand(editingDomain, null);
        rcvCommand.setEdgeAdaptor(this.getViewAdapter());
        cc.compose((IUndoableOperation)rcvCommand);
        return c;
    }
}

