/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.gmf.runtime.common.core.command.CommandResult
 *  org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand
 *  org.eclipse.gmf.runtime.notation.Edge
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.edit.commands;

import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionflowReorientConnectionViewCommand
extends AbstractTransactionalCommand {
    private IAdaptable edgeAdaptor;

    public ExecutionflowReorientConnectionViewCommand(TransactionalEditingDomain editingDomain, String label) {
        super(editingDomain, label, null);
    }

    public List getAffectedFiles() {
        View view = (View)this.edgeAdaptor.getAdapter(View.class);
        if (view != null) {
            return ExecutionflowReorientConnectionViewCommand.getWorkspaceFiles((EObject)view);
        }
        return super.getAffectedFiles();
    }

    public IAdaptable getEdgeAdaptor() {
        return this.edgeAdaptor;
    }

    public void setEdgeAdaptor(IAdaptable edgeAdaptor) {
        this.edgeAdaptor = edgeAdaptor;
    }

    protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) {
        assert (this.edgeAdaptor != null) : "Null child in ExecutionflowReorientConnectionViewCommand";
        Edge edge = (Edge)this.getEdgeAdaptor().getAdapter(Edge.class);
        assert (edge != null) : "Null edge in ExecutionflowReorientConnectionViewCommand";
        View tempView = edge.getSource();
        edge.setSource(edge.getTarget());
        edge.setTarget(tempView);
        return CommandResult.newOKCommandResult();
    }
}

