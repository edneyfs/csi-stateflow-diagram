package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;

import com.csi.gmf.stateflow.diagram.edit.parts.EventConnectionEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.EventConnectionEventIDEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.ExecutionFlowObjectEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.ExecutionFlowObjectTitleEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.FlowObjectConnectionEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.FlowObjectConnectionExecutionFlowResultValuEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StartPointConnectionEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StartPointEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StateEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StateFlowEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StateIdEditPart;

import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented 
 * by a domain model object.
 *
 * @generated
 */
public class StateflowVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "com.csi.gmf.stateflow.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (StateFlowEditPart.MODEL_ID.equals(view.getType())) {
				return StateFlowEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				StateflowDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (StateflowPackage.eINSTANCE.getStateFlow().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((StateFlow) domainElement)) {
			return StateFlowEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry
				.getModelID(containerView);
		if (!StateFlowEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (StateFlowEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = StateFlowEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case StateFlowEditPart.VISUAL_ID:
			if (StateflowPackage.eINSTANCE.getExecutionFlowObject()
					.isSuperTypeOf(domainElement.eClass())) {
				return ExecutionFlowObjectEditPart.VISUAL_ID;
			}
			if (StateflowPackage.eINSTANCE.getState().isSuperTypeOf(
					domainElement.eClass())) {
				return StateEditPart.VISUAL_ID;
			}
			if (StateflowPackage.eINSTANCE.getStartPoint().isSuperTypeOf(
					domainElement.eClass())) {
				return StartPointEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry
				.getModelID(containerView);
		if (!StateFlowEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (StateFlowEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = StateFlowEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case ExecutionFlowObjectEditPart.VISUAL_ID:
			if (ExecutionFlowObjectTitleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StateEditPart.VISUAL_ID:
			if (StateIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StateFlowEditPart.VISUAL_ID:
			if (ExecutionFlowObjectEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartPointEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FlowObjectConnectionEditPart.VISUAL_ID:
			if (FlowObjectConnectionExecutionFlowResultValuEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EventConnectionEditPart.VISUAL_ID:
			if (EventConnectionEventIDEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (StateflowPackage.eINSTANCE.getFlowObjectConnection().isSuperTypeOf(
				domainElement.eClass())) {
			return FlowObjectConnectionEditPart.VISUAL_ID;
		}
		if (StateflowPackage.eINSTANCE.getEventConnection().isSuperTypeOf(
				domainElement.eClass())) {
			return EventConnectionEditPart.VISUAL_ID;
		}
		if (StateflowPackage.eINSTANCE.getStartPointConnection().isSuperTypeOf(
				domainElement.eClass())) {
			return StartPointConnectionEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(StateFlow element) {
		return true;
	}
}
