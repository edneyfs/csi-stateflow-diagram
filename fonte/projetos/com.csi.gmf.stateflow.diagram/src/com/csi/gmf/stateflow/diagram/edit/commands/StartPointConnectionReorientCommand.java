package com.csi.gmf.stateflow.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.diagram.edit.policies.StateflowBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class StartPointConnectionReorientCommand extends EditElementCommand {

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
	public StartPointConnectionReorientCommand(
			ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof StartPointConnection) {
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
		if (!(oldEnd instanceof StartPoint && newEnd instanceof StartPoint)) {
			return false;
		}
		State target = getLink().getDestinationState();
		if (!(getLink().eContainer() instanceof StateFlow)) {
			return false;
		}
		StateFlow container = (StateFlow) getLink().eContainer();
		return StateflowBaseItemSemanticEditPolicy.LinkConstraints
				.canExistStartPointConnection_3003(container, getNewSource(),
						target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof State && newEnd instanceof State)) {
			return false;
		}
		StartPoint source = getLink().getStartPoint();
		if (!(getLink().eContainer() instanceof StateFlow)) {
			return false;
		}
		StateFlow container = (StateFlow) getLink().eContainer();
		return StateflowBaseItemSemanticEditPolicy.LinkConstraints
				.canExistStartPointConnection_3003(container, source,
						getNewTarget());
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
		getLink().setStartPoint(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setDestinationState(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected StartPointConnection getLink() {
		return (StartPointConnection) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected StartPoint getOldSource() {
		return (StartPoint) oldEnd;
	}

	/**
	 * @generated
	 */
	protected StartPoint getNewSource() {
		return (StartPoint) newEnd;
	}

	/**
	 * @generated
	 */
	protected State getOldTarget() {
		return (State) oldEnd;
	}

	/**
	 * @generated
	 */
	protected State getNewTarget() {
		return (State) newEnd;
	}
}
