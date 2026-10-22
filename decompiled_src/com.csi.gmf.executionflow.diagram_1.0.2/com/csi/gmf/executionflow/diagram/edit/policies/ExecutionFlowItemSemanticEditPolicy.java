/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionflowPackage
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest
 */
package com.csi.gmf.executionflow.diagram.edit.policies;

import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.diagram.edit.commands.ExecutableFlowObjectCreateCommand;
import com.csi.gmf.executionflow.diagram.edit.commands.ExecutionFlowObjectCreateCommand;
import com.csi.gmf.executionflow.diagram.edit.commands.ExecutionFlowResultCreateCommand;
import com.csi.gmf.executionflow.diagram.edit.commands.StartPointCreateCommand;
import com.csi.gmf.executionflow.diagram.edit.policies.ExecutionflowBaseItemSemanticEditPolicy;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowElementTypes;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

public class ExecutionFlowItemSemanticEditPolicy
extends ExecutionflowBaseItemSemanticEditPolicy {
    @Override
    protected Command getCreateCommand(CreateElementRequest req) {
        if (ExecutionflowElementTypes.ExecutionFlowObject_1001 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ExecutionflowPackage.eINSTANCE.getExecutionFlow_ExecutionFlowObjects());
            }
            return this.getGEFWrapper((ICommand)new ExecutionFlowObjectCreateCommand(req));
        }
        if (ExecutionflowElementTypes.ExecutableFlowObject_1002 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ExecutionflowPackage.eINSTANCE.getExecutionFlow_ExecutableFlowObjects());
            }
            return this.getGEFWrapper((ICommand)new ExecutableFlowObjectCreateCommand(req));
        }
        if (ExecutionflowElementTypes.ExecutionFlowResult_1003 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ExecutionflowPackage.eINSTANCE.getExecutionFlow_Results());
            }
            return this.getGEFWrapper((ICommand)new ExecutionFlowResultCreateCommand(req));
        }
        if (ExecutionflowElementTypes.StartPoint_1004 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ExecutionflowPackage.eINSTANCE.getExecutionFlow_StartPoint());
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

