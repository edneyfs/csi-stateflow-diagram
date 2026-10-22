package com.csi.gmf.stateflow.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowFactory;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.diagram.edit.policies.StateflowBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class EventConnectionCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	private final EObject source;

	/**
	 * @generated
	 */
	private final EObject target;

	/**
	 * @generated
	 */
	private StateFlow container;

	/**
	 * @generated
	 */
	public EventConnectionCreateCommand(CreateRelationshipRequest request,
			EObject source, EObject target) {
		super(request);
		this.source = source;
		this.target = target;
		if (request.getContainmentFeature() == null) {
			setContainmentFeature(StateflowPackage.eINSTANCE
					.getStateFlow_StateConnections());
		}

		// Find container element for the new link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null; element = element
				.eContainer()) {
			if (element instanceof StateFlow) {
				container = (StateFlow) element;
				super.setElementToEdit(container);
				break;
			}
		}
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (source == null && target == null) {
			return false;
		}
		if (source != null && false == source instanceof State) {
			return false;
		}
		if (target != null && false == target instanceof ExecutionFlowObject) {
			return false;
		}
		if (getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}
		// target may be null here but it's possible to check constraint
		if (getContainer() == null) {
			return false;
		}
		return StateflowBaseItemSemanticEditPolicy.LinkConstraints
				.canCreateEventConnection_3002(getContainer(), getSource(),
						getTarget());
	}

	/**
	 * @generated
	 */
	protected EObject doDefaultElementCreation() {
		EventConnection newElement = StateflowFactory.eINSTANCE
				.createEventConnection();
		getContainer().getStateConnections().add(newElement);
		newElement.setSourceState(getSource());
		newElement.setDestinationFlow(getTarget());
		return newElement;
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return StateflowPackage.eINSTANCE.getStateFlow();
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in create link command"); //$NON-NLS-1$
		}
		return super.doExecuteWithResult(monitor, info);
	}

	/**
	 * @generated
	 */
	protected ConfigureRequest createConfigureRequest() {
		ConfigureRequest request = super.createConfigureRequest();
		request.setParameter(CreateRelationshipRequest.SOURCE, getSource());
		request.setParameter(CreateRelationshipRequest.TARGET, getTarget());
		return request;
	}

	/**
	 * @generated
	 */
	protected void setElementToEdit(EObject element) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @generated
	 */
	protected State getSource() {
		return (State) source;
	}

	/**
	 * @generated
	 */
	protected ExecutionFlowObject getTarget() {
		return (ExecutionFlowObject) target;
	}

	/**
	 * @generated
	 */
	public StateFlow getContainer() {
		return container;
	}
}
