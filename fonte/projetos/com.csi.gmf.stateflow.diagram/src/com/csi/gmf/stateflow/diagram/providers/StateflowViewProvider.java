package com.csi.gmf.stateflow.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
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

import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;

import com.csi.gmf.stateflow.diagram.view.factories.EventConnectionEventIDViewFactory;
import com.csi.gmf.stateflow.diagram.view.factories.EventConnectionViewFactory;
import com.csi.gmf.stateflow.diagram.view.factories.ExecutionFlowObjectTitleViewFactory;
import com.csi.gmf.stateflow.diagram.view.factories.ExecutionFlowObjectViewFactory;
import com.csi.gmf.stateflow.diagram.view.factories.FlowObjectConnectionExecutionFlowResultValuViewFactory;
import com.csi.gmf.stateflow.diagram.view.factories.FlowObjectConnectionViewFactory;
import com.csi.gmf.stateflow.diagram.view.factories.StartPointConnectionViewFactory;
import com.csi.gmf.stateflow.diagram.view.factories.StartPointViewFactory;
import com.csi.gmf.stateflow.diagram.view.factories.StateFlowViewFactory;
import com.csi.gmf.stateflow.diagram.view.factories.StateIdViewFactory;
import com.csi.gmf.stateflow.diagram.view.factories.StateViewFactory;

/**
 * @generated
 */
public class StateflowViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (StateFlowEditPart.MODEL_ID.equals(diagramKind)
				&& StateflowVisualIDRegistry
						.getDiagramVisualID(semanticElement) != -1) {
			return StateFlowViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		if (containerView == null) {
			return null;
		}
		IElementType elementType = getSemanticElementType(semanticAdapter);
		EObject domainElement = getSemanticElement(semanticAdapter);
		int visualID;
		if (semanticHint == null) {
			// Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
			// In this situation there should be NO elementType, visualID will be determined
			// by VisualIDRegistry.getNodeVisualID() for domainElement.
			if (elementType != null || domainElement == null) {
				return null;
			}
			visualID = StateflowVisualIDRegistry.getNodeVisualID(containerView,
					domainElement);
		} else {
			visualID = StateflowVisualIDRegistry.getVisualID(semanticHint);
			if (elementType != null) {
				// Semantic hint is specified together with element type.
				// Both parameters should describe exactly the same diagram element.
				// In addition we check that visualID returned by VisualIDRegistry.getNodeVisualID() for
				// domainElement (if specified) is the same as in element type.
				if (!StateflowElementTypes.isKnownElementType(elementType)
						|| (!(elementType instanceof IHintedType))) {
					return null; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType)
						.getSemanticHint();
				if (!semanticHint.equals(elementTypeHint)) {
					return null; // if semantic hint is specified it should be the same as in element type
				}
				if (domainElement != null
						&& visualID != StateflowVisualIDRegistry
								.getNodeVisualID(containerView, domainElement)) {
					return null; // visual id for node EClass should match visual id from element type
				}
			} else {
				// Element type is not specified. Domain element should be present (except pure design elements).
				// This method is called with EObjectAdapter as parameter from:
				//   - ViewService.createNode(View container, EObject eObject, String type, PreferencesHint preferencesHint) 
				//   - generated ViewFactory.decorateView() for parent element
				if (!StateFlowEditPart.MODEL_ID
						.equals(StateflowVisualIDRegistry
								.getModelID(containerView))) {
					return null; // foreign diagram
				}
				switch (visualID) {
				case ExecutionFlowObjectEditPart.VISUAL_ID:
				case StateEditPart.VISUAL_ID:
				case StartPointEditPart.VISUAL_ID:
					if (domainElement == null
							|| visualID != StateflowVisualIDRegistry
									.getNodeVisualID(containerView,
											domainElement)) {
						return null; // visual id in semantic hint should match visual id for domain element
					}
					break;
				case ExecutionFlowObjectTitleEditPart.VISUAL_ID:
					if (ExecutionFlowObjectEditPart.VISUAL_ID != StateflowVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case StateIdEditPart.VISUAL_ID:
					if (StateEditPart.VISUAL_ID != StateflowVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case FlowObjectConnectionExecutionFlowResultValuEditPart.VISUAL_ID:
					if (FlowObjectConnectionEditPart.VISUAL_ID != StateflowVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case EventConnectionEventIDEditPart.VISUAL_ID:
					if (EventConnectionEditPart.VISUAL_ID != StateflowVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				default:
					return null;
				}
			}
		}
		return getNodeViewClass(containerView, visualID);
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(View containerView, int visualID) {
		if (containerView == null
				|| !StateflowVisualIDRegistry.canCreateNode(containerView,
						visualID)) {
			return null;
		}
		switch (visualID) {
		case ExecutionFlowObjectEditPart.VISUAL_ID:
			return ExecutionFlowObjectViewFactory.class;
		case ExecutionFlowObjectTitleEditPart.VISUAL_ID:
			return ExecutionFlowObjectTitleViewFactory.class;
		case StateEditPart.VISUAL_ID:
			return StateViewFactory.class;
		case StateIdEditPart.VISUAL_ID:
			return StateIdViewFactory.class;
		case StartPointEditPart.VISUAL_ID:
			return StartPointViewFactory.class;
		case FlowObjectConnectionExecutionFlowResultValuEditPart.VISUAL_ID:
			return FlowObjectConnectionExecutionFlowResultValuViewFactory.class;
		case EventConnectionEventIDEditPart.VISUAL_ID:
			return EventConnectionEventIDViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (!StateflowElementTypes.isKnownElementType(elementType)
				|| (!(elementType instanceof IHintedType))) {
			return null; // foreign element type
		}
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		if (elementTypeHint == null) {
			return null; // our hint is visual id and must be specified
		}
		if (semanticHint != null && !semanticHint.equals(elementTypeHint)) {
			return null; // if semantic hint is specified it should be the same as in element type
		}
		int visualID = StateflowVisualIDRegistry.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(semanticAdapter);
		if (domainElement != null
				&& visualID != StateflowVisualIDRegistry
						.getLinkWithClassVisualID(domainElement)) {
			return null; // visual id for link EClass should match visual id from element type
		}
		return getEdgeViewClass(visualID);
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(int visualID) {
		switch (visualID) {
		case FlowObjectConnectionEditPart.VISUAL_ID:
			return FlowObjectConnectionViewFactory.class;
		case EventConnectionEditPart.VISUAL_ID:
			return EventConnectionViewFactory.class;
		case StartPointConnectionEditPart.VISUAL_ID:
			return StartPointConnectionViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		return (IElementType) semanticAdapter.getAdapter(IElementType.class);
	}

}
