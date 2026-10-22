/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider
 *  org.eclipse.gmf.runtime.emf.type.core.IElementType
 *  org.eclipse.gmf.runtime.emf.type.core.IHintedType
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.providers;

import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;
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
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;

public class StateflowViewProvider
extends AbstractViewProvider {
    protected Class getDiagramViewClass(IAdaptable semanticAdapter, String diagramKind) {
        EObject semanticElement = this.getSemanticElement(semanticAdapter);
        if ("Stateflow".equals(diagramKind) && StateflowVisualIDRegistry.getDiagramVisualID(semanticElement) != -1) {
            return StateFlowViewFactory.class;
        }
        return null;
    }

    protected Class getNodeViewClass(IAdaptable semanticAdapter, View containerView, String semanticHint) {
        int visualID;
        if (containerView == null) {
            return null;
        }
        IElementType elementType = this.getSemanticElementType(semanticAdapter);
        EObject domainElement = this.getSemanticElement(semanticAdapter);
        if (semanticHint == null) {
            if (elementType != null || domainElement == null) {
                return null;
            }
            visualID = StateflowVisualIDRegistry.getNodeVisualID(containerView, domainElement);
        } else {
            visualID = StateflowVisualIDRegistry.getVisualID(semanticHint);
            if (elementType != null) {
                if (!StateflowElementTypes.isKnownElementType(elementType) || !(elementType instanceof IHintedType)) {
                    return null;
                }
                String elementTypeHint = ((IHintedType)elementType).getSemanticHint();
                if (!semanticHint.equals(elementTypeHint)) {
                    return null;
                }
                if (domainElement != null && visualID != StateflowVisualIDRegistry.getNodeVisualID(containerView, domainElement)) {
                    return null;
                }
            } else {
                if (!"Stateflow".equals(StateflowVisualIDRegistry.getModelID(containerView))) {
                    return null;
                }
                switch (visualID) {
                    case 1001: 
                    case 1002: 
                    case 1003: {
                        if (domainElement != null && visualID == StateflowVisualIDRegistry.getNodeVisualID(containerView, domainElement)) break;
                        return null;
                    }
                    case 4001: {
                        if (1001 == StateflowVisualIDRegistry.getVisualID(containerView) && containerView.getElement() == domainElement) break;
                        return null;
                    }
                    case 4002: {
                        if (1002 == StateflowVisualIDRegistry.getVisualID(containerView) && containerView.getElement() == domainElement) break;
                        return null;
                    }
                    case 4003: {
                        if (3001 == StateflowVisualIDRegistry.getVisualID(containerView) && containerView.getElement() == domainElement) break;
                        return null;
                    }
                    case 4004: {
                        if (3002 == StateflowVisualIDRegistry.getVisualID(containerView) && containerView.getElement() == domainElement) break;
                        return null;
                    }
                    default: {
                        return null;
                    }
                }
            }
        }
        return this.getNodeViewClass(containerView, visualID);
    }

    protected Class getNodeViewClass(View containerView, int visualID) {
        if (containerView == null || !StateflowVisualIDRegistry.canCreateNode(containerView, visualID)) {
            return null;
        }
        switch (visualID) {
            case 1001: {
                return ExecutionFlowObjectViewFactory.class;
            }
            case 4001: {
                return ExecutionFlowObjectTitleViewFactory.class;
            }
            case 1002: {
                return StateViewFactory.class;
            }
            case 4002: {
                return StateIdViewFactory.class;
            }
            case 1003: {
                return StartPointViewFactory.class;
            }
            case 4003: {
                return FlowObjectConnectionExecutionFlowResultValuViewFactory.class;
            }
            case 4004: {
                return EventConnectionEventIDViewFactory.class;
            }
        }
        return null;
    }

    protected Class getEdgeViewClass(IAdaptable semanticAdapter, View containerView, String semanticHint) {
        IElementType elementType = this.getSemanticElementType(semanticAdapter);
        if (!StateflowElementTypes.isKnownElementType(elementType) || !(elementType instanceof IHintedType)) {
            return null;
        }
        String elementTypeHint = ((IHintedType)elementType).getSemanticHint();
        if (elementTypeHint == null) {
            return null;
        }
        if (semanticHint != null && !semanticHint.equals(elementTypeHint)) {
            return null;
        }
        int visualID = StateflowVisualIDRegistry.getVisualID(elementTypeHint);
        EObject domainElement = this.getSemanticElement(semanticAdapter);
        if (domainElement != null && visualID != StateflowVisualIDRegistry.getLinkWithClassVisualID(domainElement)) {
            return null;
        }
        return this.getEdgeViewClass(visualID);
    }

    protected Class getEdgeViewClass(int visualID) {
        switch (visualID) {
            case 3001: {
                return FlowObjectConnectionViewFactory.class;
            }
            case 3002: {
                return EventConnectionViewFactory.class;
            }
            case 3003: {
                return StartPointConnectionViewFactory.class;
            }
        }
        return null;
    }

    private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
        if (semanticAdapter == null) {
            return null;
        }
        return (IElementType)semanticAdapter.getAdapter(IElementType.class);
    }
}

