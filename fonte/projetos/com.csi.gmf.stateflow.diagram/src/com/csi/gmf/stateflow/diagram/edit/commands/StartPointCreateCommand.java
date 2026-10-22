package com.csi.gmf.stateflow.diagram.edit.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;

/**
 * @generated
 */
public class StartPointCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	public StartPointCreateCommand(CreateElementRequest req) {
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
	public boolean canExecute() {
		StateFlow container = (StateFlow) getElementToEdit();
		if (container.getStartPoint() != null) {
			return false;
		}
		return true;
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return StateflowPackage.eINSTANCE.getStateFlow();
	}

}
