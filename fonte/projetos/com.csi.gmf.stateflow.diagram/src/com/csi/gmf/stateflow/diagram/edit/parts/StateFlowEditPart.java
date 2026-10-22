package com.csi.gmf.stateflow.diagram.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

import com.csi.gmf.stateflow.diagram.edit.policies.ExecutionFlowObjectOpenEditPolicy;
import com.csi.gmf.stateflow.diagram.edit.policies.StateFlowCanonicalEditPolicy;
import com.csi.gmf.stateflow.diagram.edit.policies.StateFlowItemSemanticEditPolicy;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;

/**
 * @generated
 */
public class StateFlowEditPart extends DiagramEditPart {

	/**
	 * @generated
	 */
	public static final String MODEL_ID = "Stateflow"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 79;

	/**
	 * @generated
	 */
	public StateFlowEditPart(View view) {
		super(view);
	}

	/**
	 * @generated NOT
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new StateFlowItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new StateFlowCanonicalEditPolicy());
		installEditPolicy(EditPolicyRoles.OPEN_ROLE,
				new ExecutionFlowObjectOpenEditPolicy());
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
	}
}
