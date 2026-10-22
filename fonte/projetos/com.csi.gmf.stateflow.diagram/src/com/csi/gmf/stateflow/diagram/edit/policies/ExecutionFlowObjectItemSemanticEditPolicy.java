package com.csi.gmf.stateflow.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gmf.runtime.notation.View;
import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.FlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;

import com.csi.gmf.stateflow.diagram.edit.commands.EventConnectionCreateCommand;
import com.csi.gmf.stateflow.diagram.edit.commands.EventConnectionReorientCommand;
import com.csi.gmf.stateflow.diagram.edit.commands.FlowObjectConnectionCreateCommand;
import com.csi.gmf.stateflow.diagram.edit.commands.FlowObjectConnectionReorientCommand;
import com.csi.gmf.stateflow.diagram.edit.parts.EventConnectionEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.FlowObjectConnectionEditPart;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gef.commands.UnexecutableCommand;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateRelationshipCommand;

/**
 * @generated
 */
public class ExecutionFlowObjectItemSemanticEditPolicy extends
		StateflowBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand();
		addDestroyShortcutsCommand(cc);
		View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			req.setElementToDestroy(view);
		}
		cc.add(getGEFWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (StateflowElementTypes.FlowObjectConnection_3001 == req
				.getElementType()) {
			return getGEFWrapper(new FlowObjectConnectionCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (StateflowElementTypes.EventConnection_3002 == req.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (StateflowElementTypes.FlowObjectConnection_3001 == req
				.getElementType()) {
			return null;
		}
		if (StateflowElementTypes.EventConnection_3002 == req.getElementType()) {
			return getGEFWrapper(new EventConnectionCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case FlowObjectConnectionEditPart.VISUAL_ID:
			return getGEFWrapper(new FlowObjectConnectionReorientCommand(req));
		case EventConnectionEditPart.VISUAL_ID:
			return getGEFWrapper(new EventConnectionReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}
}
