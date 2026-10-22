/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.StateflowPackage
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest
 */
package com.csi.gmf.stateflow.diagram.edit.policies;

import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.diagram.edit.commands.ExecutionFlowObjectCreateCommand;
import com.csi.gmf.stateflow.diagram.edit.commands.StartPointCreateCommand;
import com.csi.gmf.stateflow.diagram.edit.commands.StateCreateCommand;
import com.csi.gmf.stateflow.diagram.edit.policies.StateflowBaseItemSemanticEditPolicy;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

public class StateFlowItemSemanticEditPolicy
extends StateflowBaseItemSemanticEditPolicy {
    @Override
    protected Command getCreateCommand(CreateElementRequest req) {
        if (StateflowElementTypes.ExecutionFlowObject_1001 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(StateflowPackage.eINSTANCE.getStateFlow_ExecutionFlows());
            }
            return this.getGEFWrapper((ICommand)new ExecutionFlowObjectCreateCommand(req));
        }
        if (StateflowElementTypes.State_1002 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(StateflowPackage.eINSTANCE.getStateFlow_States());
            }
            return this.getGEFWrapper((ICommand)new StateCreateCommand(req));
        }
        if (StateflowElementTypes.StartPoint_1003 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(StateflowPackage.eINSTANCE.getStateFlow_StartPoint());
            }
            return this.getGEFWrapper((ICommand)new StartPointCreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

    @Override
    protected Command getDuplicateCommand(DuplicateElementsRequest req) {
        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart)this.getHost()).getEditingDomain();
        return this.getGEFWrapper((ICommand)new DuplicateAnythingCommand(editingDomain, req));
    }

    private static class DuplicateAnythingCommand
    extends DuplicateEObjectsCommand {
        public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
            super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
        }
    }
}

