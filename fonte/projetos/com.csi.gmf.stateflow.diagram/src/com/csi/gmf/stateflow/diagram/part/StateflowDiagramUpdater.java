package com.csi.gmf.stateflow.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.diagram.edit.parts.EventConnectionEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.ExecutionFlowObjectEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.FlowObjectConnectionEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StartPointConnectionEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StartPointEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StateEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StateFlowEditPart;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;

/**
 * @generated
 */
public class StateflowDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (StateflowVisualIDRegistry.getVisualID(view)) {
		case StateFlowEditPart.VISUAL_ID:
			return getStateFlow_79SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getStateFlow_79SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		StateFlow modelElement = (StateFlow) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getExecutionFlows().iterator(); it
				.hasNext();) {
			ExecutionFlowObject childElement = (ExecutionFlowObject) it.next();
			int visualID = StateflowVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ExecutionFlowObjectEditPart.VISUAL_ID) {
				result.add(new StateflowNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getStates().iterator(); it.hasNext();) {
			State childElement = (State) it.next();
			int visualID = StateflowVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == StateEditPart.VISUAL_ID) {
				result.add(new StateflowNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		{
			StartPoint childElement = modelElement.getStartPoint();
			int visualID = StateflowVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == StartPointEditPart.VISUAL_ID) {
				result.add(new StateflowNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (StateflowVisualIDRegistry.getVisualID(view)) {
		case StateFlowEditPart.VISUAL_ID:
			return getStateFlow_79ContainedLinks(view);
		case ExecutionFlowObjectEditPart.VISUAL_ID:
			return getExecutionFlowObject_1001ContainedLinks(view);
		case StateEditPart.VISUAL_ID:
			return getState_1002ContainedLinks(view);
		case StartPointEditPart.VISUAL_ID:
			return getStartPoint_1003ContainedLinks(view);
		case FlowObjectConnectionEditPart.VISUAL_ID:
			return getFlowObjectConnection_3001ContainedLinks(view);
		case EventConnectionEditPart.VISUAL_ID:
			return getEventConnection_3002ContainedLinks(view);
		case StartPointConnectionEditPart.VISUAL_ID:
			return getStartPointConnection_3003ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (StateflowVisualIDRegistry.getVisualID(view)) {
		case ExecutionFlowObjectEditPart.VISUAL_ID:
			return getExecutionFlowObject_1001IncomingLinks(view);
		case StateEditPart.VISUAL_ID:
			return getState_1002IncomingLinks(view);
		case StartPointEditPart.VISUAL_ID:
			return getStartPoint_1003IncomingLinks(view);
		case FlowObjectConnectionEditPart.VISUAL_ID:
			return getFlowObjectConnection_3001IncomingLinks(view);
		case EventConnectionEditPart.VISUAL_ID:
			return getEventConnection_3002IncomingLinks(view);
		case StartPointConnectionEditPart.VISUAL_ID:
			return getStartPointConnection_3003IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (StateflowVisualIDRegistry.getVisualID(view)) {
		case ExecutionFlowObjectEditPart.VISUAL_ID:
			return getExecutionFlowObject_1001OutgoingLinks(view);
		case StateEditPart.VISUAL_ID:
			return getState_1002OutgoingLinks(view);
		case StartPointEditPart.VISUAL_ID:
			return getStartPoint_1003OutgoingLinks(view);
		case FlowObjectConnectionEditPart.VISUAL_ID:
			return getFlowObjectConnection_3001OutgoingLinks(view);
		case EventConnectionEditPart.VISUAL_ID:
			return getEventConnection_3002OutgoingLinks(view);
		case StartPointConnectionEditPart.VISUAL_ID:
			return getStartPointConnection_3003OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getStateFlow_79ContainedLinks(View view) {
		StateFlow modelElement = (StateFlow) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_FlowObjectConnection_3001(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_EventConnection_3002(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_StartPointConnection_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getExecutionFlowObject_1001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getState_1002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getStartPoint_1003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getFlowObjectConnection_3001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEventConnection_3002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getStartPointConnection_3003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getExecutionFlowObject_1001IncomingLinks(View view) {
		ExecutionFlowObject modelElement = (ExecutionFlowObject) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_EventConnection_3002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getState_1002IncomingLinks(View view) {
		State modelElement = (State) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_FlowObjectConnection_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_StartPointConnection_3003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getStartPoint_1003IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getFlowObjectConnection_3001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEventConnection_3002IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getStartPointConnection_3003IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getExecutionFlowObject_1001OutgoingLinks(View view) {
		ExecutionFlowObject modelElement = (ExecutionFlowObject) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_FlowObjectConnection_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getState_1002OutgoingLinks(View view) {
		State modelElement = (State) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_EventConnection_3002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getStartPoint_1003OutgoingLinks(View view) {
		StartPoint modelElement = (StartPoint) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_StartPointConnection_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getFlowObjectConnection_3001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEventConnection_3002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getStartPointConnection_3003OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_FlowObjectConnection_3001(
			StateFlow container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getFlowConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof FlowObjectConnection) {
				continue;
			}
			FlowObjectConnection link = (FlowObjectConnection) linkObject;
			if (FlowObjectConnectionEditPart.VISUAL_ID != StateflowVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			State dst = link.getDestinationState();
			ExecutionFlowObject src = link.getSourceFlow();
			result.add(new StateflowLinkDescriptor(src, dst, link,
					StateflowElementTypes.FlowObjectConnection_3001,
					FlowObjectConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_EventConnection_3002(
			StateFlow container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getStateConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof EventConnection) {
				continue;
			}
			EventConnection link = (EventConnection) linkObject;
			if (EventConnectionEditPart.VISUAL_ID != StateflowVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			ExecutionFlowObject dst = link.getDestinationFlow();
			State src = link.getSourceState();
			result.add(new StateflowLinkDescriptor(src, dst, link,
					StateflowElementTypes.EventConnection_3002,
					EventConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_StartPointConnection_3003(
			StateFlow container) {
		Collection result = new LinkedList();
		StartPointConnection link = container.getStartPointConnection();
		if (StartPointConnectionEditPart.VISUAL_ID != StateflowVisualIDRegistry
				.getLinkWithClassVisualID(link)) {
			return result;
		}
		State dst = link.getDestinationState();
		StartPoint src = link.getStartPoint();
		result.add(new StateflowLinkDescriptor(src, dst, link,
				StateflowElementTypes.StartPointConnection_3003,
				StartPointConnectionEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_FlowObjectConnection_3001(
			State target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != StateflowPackage.eINSTANCE
					.getFlowObjectConnection_DestinationState()
					|| false == setting.getEObject() instanceof FlowObjectConnection) {
				continue;
			}
			FlowObjectConnection link = (FlowObjectConnection) setting
					.getEObject();
			if (FlowObjectConnectionEditPart.VISUAL_ID != StateflowVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			ExecutionFlowObject src = link.getSourceFlow();
			result.add(new StateflowLinkDescriptor(src, target, link,
					StateflowElementTypes.FlowObjectConnection_3001,
					FlowObjectConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_EventConnection_3002(
			ExecutionFlowObject target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != StateflowPackage.eINSTANCE
					.getEventConnection_DestinationFlow()
					|| false == setting.getEObject() instanceof EventConnection) {
				continue;
			}
			EventConnection link = (EventConnection) setting.getEObject();
			if (EventConnectionEditPart.VISUAL_ID != StateflowVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			State src = link.getSourceState();
			result.add(new StateflowLinkDescriptor(src, target, link,
					StateflowElementTypes.EventConnection_3002,
					EventConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_StartPointConnection_3003(
			State target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != StateflowPackage.eINSTANCE
					.getStartPointConnection_DestinationState()
					|| false == setting.getEObject() instanceof StartPointConnection) {
				continue;
			}
			StartPointConnection link = (StartPointConnection) setting
					.getEObject();
			if (StartPointConnectionEditPart.VISUAL_ID != StateflowVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			StartPoint src = link.getStartPoint();
			result.add(new StateflowLinkDescriptor(src, target, link,
					StateflowElementTypes.StartPointConnection_3003,
					StartPointConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_FlowObjectConnection_3001(
			ExecutionFlowObject source) {
		StateFlow container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof StateFlow) {
				container = (StateFlow) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getFlowConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof FlowObjectConnection) {
				continue;
			}
			FlowObjectConnection link = (FlowObjectConnection) linkObject;
			if (FlowObjectConnectionEditPart.VISUAL_ID != StateflowVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			State dst = link.getDestinationState();
			ExecutionFlowObject src = link.getSourceFlow();
			if (src != source) {
				continue;
			}
			result.add(new StateflowLinkDescriptor(src, dst, link,
					StateflowElementTypes.FlowObjectConnection_3001,
					FlowObjectConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_EventConnection_3002(
			State source) {
		StateFlow container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof StateFlow) {
				container = (StateFlow) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getStateConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof EventConnection) {
				continue;
			}
			EventConnection link = (EventConnection) linkObject;
			if (EventConnectionEditPart.VISUAL_ID != StateflowVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			ExecutionFlowObject dst = link.getDestinationFlow();
			State src = link.getSourceState();
			if (src != source) {
				continue;
			}
			result.add(new StateflowLinkDescriptor(src, dst, link,
					StateflowElementTypes.EventConnection_3002,
					EventConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_StartPointConnection_3003(
			StartPoint source) {
		StateFlow container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof StateFlow) {
				container = (StateFlow) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		StartPointConnection link = container.getStartPointConnection();
		if (StartPointConnectionEditPart.VISUAL_ID != StateflowVisualIDRegistry
				.getLinkWithClassVisualID(link)) {
			return result;
		}
		State dst = link.getDestinationState();
		StartPoint src = link.getStartPoint();
		if (src != source) {
			return result;
		}
		result.add(new StateflowLinkDescriptor(src, dst, link,
				StateflowElementTypes.StartPointConnection_3003,
				StartPointConnectionEditPart.VISUAL_ID));
		return result;
	}

}
