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
package com.csi.gmf.stateflow.diagram.edit.policies;

import com.csi.gmf.stateflow.diagram.edit.commands.StartPointConnectionCreateCommand;
import com.csi.gmf.stateflow.diagram.edit.commands.StartPointConnectionReorientCommand;
import com.csi.gmf.stateflow.diagram.edit.policies.StateflowBaseItemSemanticEditPolicy;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;
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

public class StartPointItemSemanticEditPolicy
extends StateflowBaseItemSemanticEditPolicy {
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
        if (StateflowElementTypes.StartPointConnection_3003 == req.getElementType()) {
            return this.getGEFWrapper((ICommand)new StartPointConnectionCreateCommand(req, req.getSource(), req.getTarget()));
        }
        return null;
    }

    protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
        if (StateflowElementTypes.StartPointConnection_3003 == req.getElementType()) {
            return null;
        }
        return null;
    }

    @Override
    protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
        switch (this.getVisualID((IEditCommandRequest)req)) {
            case 3003: {
                return this.getGEFWrapper((ICommand)new StartPointConnectionReorientCommand(req));
            }
        }
        return super.getReorientRelationshipCommand(req);
    }
}

