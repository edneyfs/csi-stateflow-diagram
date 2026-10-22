/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gef.commands.CompoundCommand
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.edit.policies;

import com.csi.gmf.executionflow.diagram.edit.commands.FlowObjectConnectionCreateCommand;
import com.csi.gmf.executionflow.diagram.edit.commands.FlowObjectConnectionReorientCommand;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutionflowBaseItemSemanticEditPolicy;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowElementTypes;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionFlowResultItemSemanticEditPolicy
extends ExecutionflowBaseItemSemanticEditPolicy {
    @Override
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        CompoundCommand cc = this.getDestroyEdgesCommand();
        this.addDestroyShortcutsCommand(cc);
        View view = (View)this.getHost().getModel();
        if (view.getEAnnotation("Shortcut") != null) {
            req.setElementToDestroy((EObject)view);
        }
        cc.add(this.getGEFWrapper((ICommand)new DestroyElementCommand(req)));
        return cc.unwrap();
    }

    @Override
    protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
        Command command = req.getTarget() == null ? this.getStartCreateRelationshipCommand(req) : this.getCompleteCreateRelationshipCommand(req);
        return command != null ? command : super.getCreateRelationshipCommand(req);
    }

    protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
        if (ExecutionflowElementTypes.FlowObjectConnection_3001 == req.getElementType()) {
            return null;
        }
        return null;
    }

    protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
        if (ExecutionflowElementTypes.FlowObjectConnection_3001 == req.getElementType()) {
            return this.getGEFWrapper((ICommand)new FlowObjectConnectionCreateCommand(req, req.getSource(), req.getTarget()));
        }
        return null;
    }

    @Override
    protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
        switch (this.getVisualID((IEditCommandRequest)req)) {
            case 3001: {
                return this.getGEFWrapper((ICommand)new FlowObjectConnectionReorientCommand(req));
            }
        }
        return super.getReorientRelationshipCommand(req);
    }
}

