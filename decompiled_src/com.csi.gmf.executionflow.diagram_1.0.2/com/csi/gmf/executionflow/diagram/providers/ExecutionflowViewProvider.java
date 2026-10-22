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
package com.csi.gmf.executionflow.diagram.providers;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowElementTypes;
import com.csi.gmf.executionflow.diagram.view.factories.ExecutableFlowObjectSimpleClassNameViewFactory;
import com.csi.gmf.executionflow.diagram.view.factories.ExecutableFlowObjectViewFactory;
import com.csi.gmf.executionflow.diagram.view.factories.ExecutionFlowObjectFileNameViewFactory;
import com.csi.gmf.executionflow.diagram.view.factories.ExecutionFlowObjectViewFactory;
import com.csi.gmf.executionflow.diagram.view.factories.ExecutionFlowResultExecutionFlowResultValuViewFactory;
import com.csi.gmf.executionflow.diagram.view.factories.ExecutionFlowResultViewFactory;
import com.csi.gmf.executionflow.diagram.view.factories.ExecutionFlowViewFactory;
import com.csi.gmf.executionflow.diagram.view.factories.FlowObjectConnectionExecutionFlowResultValuViewFactory;
import com.csi.gmf.executionflow.diagram.view.factories.FlowObjectConnectionViewFactory;
import com.csi.gmf.executionflow.diagram.view.factories.StartPointConnectionViewFactory;
import com.csi.gmf.executionflow.diagram.view.factories.StartPointViewFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionflowViewProvider
extends AbstractViewProvider {
    protected Class getDiagramViewClass(IAdaptable semanticAdapter, String diagramKind) {
        EObject semanticElement = this.getSemanticElement(semanticAdapter);
        if ("Executionflow".equals(diagramKind) && ExecutionflowVisualIDRegistry.getDiagramVisualID(semanticElement) != -1) {
            return ExecutionFlowViewFactory.class;
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
            visualID = ExecutionflowVisualIDRegistry.getNodeVisualID(containerView, domainElement);
        } else {
            visualID = ExecutionflowVisualIDRegistry.getVisualID(semanticHint);
            if (elementType != null) {
                if (!ExecutionflowElementTypes.isKnownElementType(elementType) || !(elementType instanceof IHintedType)) {
                    return null;
                }
                String elementTypeHint = ((IHintedType)elementType).getSemanticHint();
                if (!semanticHint.equals(elementTypeHint)) {
                    return null;
                }
                if (domainElement != null && visualID != ExecutionflowVisualIDRegistry.getNodeVisualID(containerView, domainElement)) {
                    return null;
                }
            } else {
                if (!"Executionflow".equals(ExecutionflowVisualIDRegistry.getModelID(containerView))) {
                    return null;
                }
                switch (visualID) {
                    case 1001: 
                    case 1002: 
                    case 1003: 
                    case 1004: {
                        if (domainElement != null && visualID == ExecutionflowVisualIDRegistry.getNodeVisualID(containerView, domainElement)) break;
                        return null;
                    }
                    case 4001: {
                        if (1001 == ExecutionflowVisualIDRegistry.getVisualID(containerView) && containerView.getElement() == domainElement) break;
                        return null;
                    }
                    case 4002: {
                        if (1002 == ExecutionflowVisualIDRegistry.getVisualID(containerView) && containerView.getElement() == domainElement) break;
                        return null;
                    }
                    case 4003: {
                        if (1003 == ExecutionflowVisualIDRegistry.getVisualID(containerView) && containerView.getElement() == domainElement) break;
                        return null;
                    }
                    case 4004: {
                        if (3001 == ExecutionflowVisualIDRegistry.getVisualID(containerView) && containerView.getElement() == domainElement) break;
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
        if (containerView == null || !ExecutionflowVisualIDRegistry.canCreateNode(containerView, visualID)) {
            return null;
        }
        switch (visualID) {
            case 1001: {
                return ExecutionFlowObjectViewFactory.class;
            }
            case 4001: {
                return ExecutionFlowObjectFileNameViewFactory.class;
            }
            case 1002: {
                return ExecutableFlowObjectViewFactory.class;
            }
            case 4002: {
                return ExecutableFlowObjectSimpleClassNameViewFactory.class;
            }
            case 1003: {
                return ExecutionFlowResultViewFactory.class;
            }
            case 4003: {
                return ExecutionFlowResultExecutionFlowResultValuViewFactory.class;
            }
            case 1004: {
                return StartPointViewFactory.class;
            }
            case 4004: {
                return FlowObjectConnectionExecutionFlowResultValuViewFactory.class;
            }
        }
        return null;
    }

    protected Class getEdgeViewClass(IAdaptable semanticAdapter, View containerView, String semanticHint) {
        IElementType elementType = this.getSemanticElementType(semanticAdapter);
        if (!ExecutionflowElementTypes.isKnownElementType(elementType) || !(elementType instanceof IHintedType)) {
            return null;
        }
        String elementTypeHint = ((IHintedType)elementType).getSemanticHint();
        if (elementTypeHint == null) {
            return null;
        }
        if (semanticHint != null && !semanticHint.equals(elementTypeHint)) {
            return null;
        }
        int visualID = ExecutionflowVisualIDRegistry.getVisualID(elementTypeHint);
        EObject domainElement = this.getSemanticElement(semanticAdapter);
        if (domainElement != null && visualID != ExecutionflowVisualIDRegistry.getLinkWithClassVisualID(domainElement)) {
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

