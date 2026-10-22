package com.csi.gmf.stateflow.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.diagram.edit.policies.StateflowBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class EventConnectionReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public EventConnectionReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof EventConnection) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof State && newEnd instanceof State)) {
			return false;
		}
		ExecutionFlowObject target = getLink().getDestinationFlow();
		if (!(getLink().eContainer() instanceof StateFlow)) {
			return false;
		}
		StateFlow container = (StateFlow) getLink().eContainer();
		return StateflowBaseItemSemanticEditPolicy.LinkConstraints
				.canExistEventConnection_3002(container, getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof ExecutionFlowObject && newEnd instanceof ExecutionFlowObject)) {
			return false;
		}
		State source = getLink().getSourceState();
		if (!(getLink().eContainer() instanceof StateFlow)) {
			return false;
		}
		StateFlow container = (StateFlow) getLink().eContainer();
		return StateflowBaseItemSemanticEditPolicy.LinkConstraints
				.canExistEventConnection_3002(container, source, getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setSourceState(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setDestinationFlow(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected EventConnection getLink() {
		return (EventConnection) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected State getOldSource() {
		return (State) oldEnd;
	}

	/**
	 * @generated
	 */
	protected State getNewSource() {
		return (State) newEnd;
	}

	/**
	 * @generated
	 */
	protected ExecutionFlowObject getOldTarget() {
		return (ExecutionFlowObject) oldEnd;
	}

	/**
	 * @generated
	 */
	protected ExecutionFlowObject getNewTarget() {
		return (ExecutionFlowObject) newEnd;
	}
}
