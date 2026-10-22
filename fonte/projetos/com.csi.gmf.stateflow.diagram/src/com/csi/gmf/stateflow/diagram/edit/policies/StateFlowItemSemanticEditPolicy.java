package com.csi.gmf.stateflow.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;

import com.csi.gmf.stateflow.diagram.edit.commands.ExecutionFlowObjectCreateCommand;
import com.csi.gmf.stateflow.diagram.edit.commands.StartPointCreateCommand;
import com.csi.gmf.stateflow.diagram.edit.commands.StateCreateCommand;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class StateFlowItemSemanticEditPolicy extends
		StateflowBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (StateflowElementTypes.ExecutionFlowObject_1001 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(StateflowPackage.eINSTANCE
						.getStateFlow_ExecutionFlows());
			}
			return getGEFWrapper(new ExecutionFlowObjectCreateCommand(req));
		}
		if (StateflowElementTypes.State_1002 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(StateflowPackage.eINSTANCE
						.getStateFlow_States());
			}
			return getGEFWrapper(new StateCreateCommand(req));
		}
		if (StateflowElementTypes.StartPoint_1003 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(StateflowPackage.eINSTANCE
						.getStateFlow_StartPoint());
			}
			return getGEFWrapper(new StartPointCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}
	}
}
