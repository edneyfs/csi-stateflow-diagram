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
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;

import com.csi.gmf.stateflow.diagram.edit.commands.StartPointConnectionCreateCommand;
import com.csi.gmf.stateflow.diagram.edit.commands.StartPointConnectionReorientCommand;
import com.csi.gmf.stateflow.diagram.edit.parts.StartPointConnectionEditPart;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;

import org.eclipse.gef.commands.UnexecutableCommand;

/**
 * @generated
 */
public class StartPointItemSemanticEditPolicy extends
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
		if (StateflowElementTypes.StartPointConnection_3003 == req
				.getElementType()) {
			return getGEFWrapper(new StartPointConnectionCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (StateflowElementTypes.StartPointConnection_3003 == req
				.getElementType()) {
			return null;
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
		case StartPointConnectionEditPart.VISUAL_ID:
			return getGEFWrapper(new StartPointConnectionReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}
}
