package com.csi.gmf.stateflow.diagram.edit.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

import com.csi.gmf.stateflow.StateflowPackage;

/**
 * @generated
 */
public class ExecutionFlowObjectCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	public ExecutionFlowObjectCreateCommand(CreateElementRequest req) {
		super(req);
	}

	/**
	 * @generated
	 */
	protected EObject getElementToEdit() {
		EObject container = ((CreateElementRequest) getRequest())
				.getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		return container;
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return StateflowPackage.eINSTANCE.getStateFlow();
	}

}
