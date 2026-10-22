/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.EventConnection
 *  com.csi.gmf.stateflow.ExecutionFlowObject
 *  com.csi.gmf.stateflow.FlowObjectConnection
 *  com.csi.gmf.stateflow.StartPoint
 *  com.csi.gmf.stateflow.StartPointConnection
 *  com.csi.gmf.stateflow.State
 *  com.csi.gmf.stateflow.StateFlow
 *  com.csi.gmf.stateflow.StateflowPackage
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.EStructuralFeature$Setting
 *  org.eclipse.emf.ecore.util.EcoreUtil$CrossReferencer
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.diagram.part.StateflowLinkDescriptor;
import com.csi.gmf.stateflow.diagram.part.StateflowNodeDescriptor;
import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

public class StateflowDiagramUpdater {
    public static List getSemanticChildren(View view) {
        switch (StateflowVisualIDRegistry.getVisualID(view)) {
            case 79: {
                return StateflowDiagramUpdater.getStateFlow_79SemanticChildren(view);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static List getStateFlow_79SemanticChildren(View view) {
        int visualID;
        if (!view.isSetElement()) {
            return Collections.EMPTY_LIST;
        }
        StateFlow modelElement = (StateFlow)view.getElement();
        LinkedList<StateflowNodeDescriptor> result = new LinkedList<StateflowNodeDescriptor>();
        for (ExecutionFlowObject childElement : modelElement.getExecutionFlows()) {
            visualID = StateflowVisualIDRegistry.getNodeVisualID(view, (EObject)childElement);
            if (visualID != 1001) continue;
            result.add(new StateflowNodeDescriptor((EObject)childElement, visualID));
        }
        for (ExecutionFlowObject childElement : modelElement.getStates()) {
            visualID = StateflowVisualIDRegistry.getNodeVisualID(view, (EObject)childElement);
            if (visualID != 1002) continue;
            result.add(new StateflowNodeDescriptor((EObject)childElement, visualID));
        }
        StartPoint childElement = modelElement.getStartPoint();
        int visualID2 = StateflowVisualIDRegistry.getNodeVisualID(view, (EObject)childElement);
        if (visualID2 == 1003) {
            result.add(new StateflowNodeDescriptor((EObject)childElement, visualID2));
        }
        return result;
    }

    public static List getContainedLinks(View view) {
        switch (StateflowVisualIDRegistry.getVisualID(view)) {
            case 79: {
                return StateflowDiagramUpdater.getStateFlow_79ContainedLinks(view);
            }
            case 1001: {
                return StateflowDiagramUpdater.getExecutionFlowObject_1001ContainedLinks(view);
            }
            case 1002: {
                return StateflowDiagramUpdater.getState_1002ContainedLinks(view);
            }
            case 1003: {
                return StateflowDiagramUpdater.getStartPoint_1003ContainedLinks(view);
            }
            case 3001: {
                return StateflowDiagramUpdater.getFlowObjectConnection_3001ContainedLinks(view);
            }
            case 3002: {
                return StateflowDiagramUpdater.getEventConnection_3002ContainedLinks(view);
            }
            case 3003: {
                return StateflowDiagramUpdater.getStartPointConnection_3003ContainedLinks(view);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static List getIncomingLinks(View view) {
        switch (StateflowVisualIDRegistry.getVisualID(view)) {
            case 1001: {
                return StateflowDiagramUpdater.getExecutionFlowObject_1001IncomingLinks(view);
            }
            case 1002: {
                return StateflowDiagramUpdater.getState_1002IncomingLinks(view);
            }
            case 1003: {
                return StateflowDiagramUpdater.getStartPoint_1003IncomingLinks(view);
            }
            case 3001: {
                return StateflowDiagramUpdater.getFlowObjectConnection_3001IncomingLinks(view);
            }
            case 3002: {
                return StateflowDiagramUpdater.getEventConnection_3002IncomingLinks(view);
            }
            case 3003: {
                return StateflowDiagramUpdater.getStartPointConnection_3003IncomingLinks(view);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static List getOutgoingLinks(View view) {
        switch (StateflowVisualIDRegistry.getVisualID(view)) {
            case 1001: {
                return StateflowDiagramUpdater.getExecutionFlowObject_1001OutgoingLinks(view);
            }
            case 1002: {
                return StateflowDiagramUpdater.getState_1002OutgoingLinks(view);
            }
            case 1003: {
                return StateflowDiagramUpdater.getStartPoint_1003OutgoingLinks(view);
            }
            case 3001: {
                return StateflowDiagramUpdater.getFlowObjectConnection_3001OutgoingLinks(view);
            }
            case 3002: {
                return StateflowDiagramUpdater.getEventConnection_3002OutgoingLinks(view);
            }
            case 3003: {
                return StateflowDiagramUpdater.getStartPointConnection_3003OutgoingLinks(view);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static List getStateFlow_79ContainedLinks(View view) {
        StateFlow modelElement = (StateFlow)view.getElement();
        LinkedList result = new LinkedList();
        result.addAll(StateflowDiagramUpdater.getContainedTypeModelFacetLinks_FlowObjectConnection_3001(modelElement));
        result.addAll(StateflowDiagramUpdater.getContainedTypeModelFacetLinks_EventConnection_3002(modelElement));
        result.addAll(StateflowDiagramUpdater.getContainedTypeModelFacetLinks_StartPointConnection_3003(modelElement));
        return result;
    }

    public static List getExecutionFlowObject_1001ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getState_1002ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getStartPoint_1003ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getFlowObjectConnection_3001ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getEventConnection_3002ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getStartPointConnection_3003ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getExecutionFlowObject_1001IncomingLinks(View view) {
        ExecutionFlowObject modelElement = (ExecutionFlowObject)view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find((Collection)view.eResource().getResourceSet().getResources());
        LinkedList result = new LinkedList();
        result.addAll(StateflowDiagramUpdater.getIncomingTypeModelFacetLinks_EventConnection_3002(modelElement, crossReferences));
        return result;
    }

    public static List getState_1002IncomingLinks(View view) {
        State modelElement = (State)view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find((Collection)view.eResource().getResourceSet().getResources());
        LinkedList result = new LinkedList();
        result.addAll(StateflowDiagramUpdater.getIncomingTypeModelFacetLinks_FlowObjectConnection_3001(modelElement, crossReferences));
        result.addAll(StateflowDiagramUpdater.getIncomingTypeModelFacetLinks_StartPointConnection_3003(modelElement, crossReferences));
        return result;
    }

    public static List getStartPoint_1003IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getFlowObjectConnection_3001IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getEventConnection_3002IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getStartPointConnection_3003IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getExecutionFlowObject_1001OutgoingLinks(View view) {
        ExecutionFlowObject modelElement = (ExecutionFlowObject)view.getElement();
        LinkedList result = new LinkedList();
        result.addAll(StateflowDiagramUpdater.getOutgoingTypeModelFacetLinks_FlowObjectConnection_3001(modelElement));
        return result;
    }

    public static List getState_1002OutgoingLinks(View view) {
        State modelElement = (State)view.getElement();
        LinkedList result = new LinkedList();
        result.addAll(StateflowDiagramUpdater.getOutgoingTypeModelFacetLinks_EventConnection_3002(modelElement));
        return result;
    }

    public static List getStartPoint_1003OutgoingLinks(View view) {
        StartPoint modelElement = (StartPoint)view.getElement();
        LinkedList result = new LinkedList();
        result.addAll(StateflowDiagramUpdater.getOutgoingTypeModelFacetLinks_StartPointConnection_3003(modelElement));
        return result;
    }

    public static List getFlowObjectConnection_3001OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getEventConnection_3002OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getStartPointConnection_3003OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    private static Collection getContainedTypeModelFacetLinks_FlowObjectConnection_3001(StateFlow container) {
        LinkedList<StateflowLinkDescriptor> result = new LinkedList<StateflowLinkDescriptor>();
        for (EObject linkObject : container.getFlowConnections()) {
            FlowObjectConnection link;
            if (!(linkObject instanceof FlowObjectConnection) || 3001 != StateflowVisualIDRegistry.getLinkWithClassVisualID((EObject)(link = (FlowObjectConnection)linkObject))) continue;
            State dst = link.getDestinationState();
            ExecutionFlowObject src = link.getSourceFlow();
            result.add(new StateflowLinkDescriptor((EObject)src, (EObject)dst, (EObject)link, StateflowElementTypes.FlowObjectConnection_3001, 3001));
        }
        return result;
    }

    private static Collection getContainedTypeModelFacetLinks_EventConnection_3002(StateFlow container) {
        LinkedList<StateflowLinkDescriptor> result = new LinkedList<StateflowLinkDescriptor>();
        for (EObject linkObject : container.getStateConnections()) {
            EventConnection link;
            if (!(linkObject instanceof EventConnection) || 3002 != StateflowVisualIDRegistry.getLinkWithClassVisualID((EObject)(link = (EventConnection)linkObject))) continue;
            ExecutionFlowObject dst = link.getDestinationFlow();
            State src = link.getSourceState();
            result.add(new StateflowLinkDescriptor((EObject)src, (EObject)dst, (EObject)link, StateflowElementTypes.EventConnection_3002, 3002));
        }
        return result;
    }

    private static Collection getContainedTypeModelFacetLinks_StartPointConnection_3003(StateFlow container) {
        LinkedList<StateflowLinkDescriptor> result = new LinkedList<StateflowLinkDescriptor>();
        StartPointConnection link = container.getStartPointConnection();
        if (3003 != StateflowVisualIDRegistry.getLinkWithClassVisualID((EObject)link)) {
            return result;
        }
        State dst = link.getDestinationState();
        StartPoint src = link.getStartPoint();
        result.add(new StateflowLinkDescriptor((EObject)src, (EObject)dst, (EObject)link, StateflowElementTypes.StartPointConnection_3003, 3003));
        return result;
    }

    private static Collection getIncomingTypeModelFacetLinks_FlowObjectConnection_3001(State target, Map crossReferences) {
        LinkedList<StateflowLinkDescriptor> result = new LinkedList<StateflowLinkDescriptor>();
        Collection settings = (Collection)crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            FlowObjectConnection link;
            if (setting.getEStructuralFeature() != StateflowPackage.eINSTANCE.getFlowObjectConnection_DestinationState() || !(setting.getEObject() instanceof FlowObjectConnection) || 3001 != StateflowVisualIDRegistry.getLinkWithClassVisualID((EObject)(link = (FlowObjectConnection)setting.getEObject()))) continue;
            ExecutionFlowObject src = link.getSourceFlow();
            result.add(new StateflowLinkDescriptor((EObject)src, (EObject)target, (EObject)link, StateflowElementTypes.FlowObjectConnection_3001, 3001));
        }
        return result;
    }

    private static Collection getIncomingTypeModelFacetLinks_EventConnection_3002(ExecutionFlowObject target, Map crossReferences) {
        LinkedList<StateflowLinkDescriptor> result = new LinkedList<StateflowLinkDescriptor>();
        Collection settings = (Collection)crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            EventConnection link;
            if (setting.getEStructuralFeature() != StateflowPackage.eINSTANCE.getEventConnection_DestinationFlow() || !(setting.getEObject() instanceof EventConnection) || 3002 != StateflowVisualIDRegistry.getLinkWithClassVisualID((EObject)(link = (EventConnection)setting.getEObject()))) continue;
            State src = link.getSourceState();
            result.add(new StateflowLinkDescriptor((EObject)src, (EObject)target, (EObject)link, StateflowElementTypes.EventConnection_3002, 3002));
        }
        return result;
    }

    private static Collection getIncomingTypeModelFacetLinks_StartPointConnection_3003(State target, Map crossReferences) {
        LinkedList<StateflowLinkDescriptor> result = new LinkedList<StateflowLinkDescriptor>();
        Collection settings = (Collection)crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            StartPointConnection link;
            if (setting.getEStructuralFeature() != StateflowPackage.eINSTANCE.getStartPointConnection_DestinationState() || !(setting.getEObject() instanceof StartPointConnection) || 3003 != StateflowVisualIDRegistry.getLinkWithClassVisualID((EObject)(link = (StartPointConnection)setting.getEObject()))) continue;
            StartPoint src = link.getStartPoint();
            result.add(new StateflowLinkDescriptor((EObject)src, (EObject)target, (EObject)link, StateflowElementTypes.StartPointConnection_3003, 3003));
        }
        return result;
    }

    private static Collection getOutgoingTypeModelFacetLinks_FlowObjectConnection_3001(ExecutionFlowObject source) {
        StateFlow container = null;
        ExecutionFlowObject element = source;
        while (element != null && container == null) {
            if (element instanceof StateFlow) {
                container = (StateFlow)element;
            }
            element = element.eContainer();
        }
        if (container == null) {
            return Collections.EMPTY_LIST;
        }
        LinkedList<StateflowLinkDescriptor> result = new LinkedList<StateflowLinkDescriptor>();
        for (EObject linkObject : container.getFlowConnections()) {
            FlowObjectConnection link;
            if (!(linkObject instanceof FlowObjectConnection) || 3001 != StateflowVisualIDRegistry.getLinkWithClassVisualID((EObject)(link = (FlowObjectConnection)linkObject))) continue;
            State dst = link.getDestinationState();
            ExecutionFlowObject src = link.getSourceFlow();
            if (src != source) continue;
            result.add(new StateflowLinkDescriptor((EObject)src, (EObject)dst, (EObject)link, StateflowElementTypes.FlowObjectConnection_3001, 3001));
        }
        return result;
    }

    private static Collection getOutgoingTypeModelFacetLinks_EventConnection_3002(State source) {
        StateFlow container = null;
        State element = source;
        while (element != null && container == null) {
            if (element instanceof StateFlow) {
                container = (StateFlow)element;
            }
            element = element.eContainer();
        }
        if (container == null) {
            return Collections.EMPTY_LIST;
        }
        LinkedList<StateflowLinkDescriptor> result = new LinkedList<StateflowLinkDescriptor>();
        for (EObject linkObject : container.getStateConnections()) {
            EventConnection link;
            if (!(linkObject instanceof EventConnection) || 3002 != StateflowVisualIDRegistry.getLinkWithClassVisualID((EObject)(link = (EventConnection)linkObject))) continue;
            ExecutionFlowObject dst = link.getDestinationFlow();
            State src = link.getSourceState();
            if (src != source) continue;
            result.add(new StateflowLinkDescriptor((EObject)src, (EObject)dst, (EObject)link, StateflowElementTypes.EventConnection_3002, 3002));
        }
        return result;
    }

    private static Collection getOutgoingTypeModelFacetLinks_StartPointConnection_3003(StartPoint source) {
        StateFlow container = null;
        StartPoint element = source;
        while (element != null && container == null) {
            if (element instanceof StateFlow) {
                container = (StateFlow)element;
            }
            element = element.eContainer();
        }
        if (container == null) {
            return Collections.EMPTY_LIST;
        }
        LinkedList<StateflowLinkDescriptor> result = new LinkedList<StateflowLinkDescriptor>();
        StartPointConnection link = container.getStartPointConnection();
        if (3003 != StateflowVisualIDRegistry.getLinkWithClassVisualID((EObject)link)) {
            return result;
        }
        State dst = link.getDestinationState();
        StartPoint src = link.getStartPoint();
        if (src != source) {
            return result;
        }
        result.add(new StateflowLinkDescriptor((EObject)src, (EObject)dst, (EObject)link, StateflowElementTypes.StartPointConnection_3003, 3003));
        return result;
    }
}

