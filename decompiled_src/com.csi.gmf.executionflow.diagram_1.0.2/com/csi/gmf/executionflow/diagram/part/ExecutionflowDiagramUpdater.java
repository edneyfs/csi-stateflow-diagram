/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutableFlowObject
 *  com.csi.gmf.executionflow.ExecutionFlow
 *  com.csi.gmf.executionflow.ExecutionFlowObject
 *  com.csi.gmf.executionflow.ExecutionFlowResult
 *  com.csi.gmf.executionflow.ExecutionflowPackage
 *  com.csi.gmf.executionflow.FlowObject
 *  com.csi.gmf.executionflow.FlowObjectConnection
 *  com.csi.gmf.executionflow.SourceConnectable
 *  com.csi.gmf.executionflow.StartPoint
 *  com.csi.gmf.executionflow.StartPointConnection
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.EStructuralFeature$Setting
 *  org.eclipse.emf.ecore.util.EcoreUtil$CrossReferencer
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.ExecutableFlowObject;
import com.csi.gmf.executionflow.ExecutionFlow;
import com.csi.gmf.executionflow.ExecutionFlowObject;
import com.csi.gmf.executionflow.ExecutionFlowResult;
import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.FlowObject;
import com.csi.gmf.executionflow.FlowObjectConnection;
import com.csi.gmf.executionflow.SourceConnectable;
import com.csi.gmf.executionflow.StartPoint;
import com.csi.gmf.executionflow.StartPointConnection;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowLinkDescriptor;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowNodeDescriptor;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowElementTypes;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionflowDiagramUpdater {
    public static List getSemanticChildren(View view) {
        switch (ExecutionflowVisualIDRegistry.getVisualID(view)) {
            case 79: {
                return ExecutionflowDiagramUpdater.getExecutionFlow_79SemanticChildren(view);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static List getExecutionFlow_79SemanticChildren(View view) {
        int visualID;
        if (!view.isSetElement()) {
            return Collections.EMPTY_LIST;
        }
        ExecutionFlow modelElement = (ExecutionFlow)view.getElement();
        LinkedList<ExecutionflowNodeDescriptor> result = new LinkedList<ExecutionflowNodeDescriptor>();
        for (ExecutionFlowObject childElement : modelElement.getExecutionFlowObjects()) {
            visualID = ExecutionflowVisualIDRegistry.getNodeVisualID(view, (EObject)childElement);
            if (visualID != 1001) continue;
            result.add(new ExecutionflowNodeDescriptor((EObject)childElement, visualID));
        }
        for (ExecutionFlowObject childElement : modelElement.getExecutableFlowObjects()) {
            visualID = ExecutionflowVisualIDRegistry.getNodeVisualID(view, (EObject)childElement);
            if (visualID != 1002) continue;
            result.add(new ExecutionflowNodeDescriptor((EObject)childElement, visualID));
        }
        for (ExecutionFlowObject childElement : modelElement.getResults()) {
            visualID = ExecutionflowVisualIDRegistry.getNodeVisualID(view, (EObject)childElement);
            if (visualID != 1003) continue;
            result.add(new ExecutionflowNodeDescriptor((EObject)childElement, visualID));
        }
        StartPoint childElement = modelElement.getStartPoint();
        int visualID2 = ExecutionflowVisualIDRegistry.getNodeVisualID(view, (EObject)childElement);
        if (visualID2 == 1004) {
            result.add(new ExecutionflowNodeDescriptor((EObject)childElement, visualID2));
        }
        return result;
    }

    public static List getContainedLinks(View view) {
        switch (ExecutionflowVisualIDRegistry.getVisualID(view)) {
            case 79: {
                return ExecutionflowDiagramUpdater.getExecutionFlow_79ContainedLinks(view);
            }
            case 1001: {
                return ExecutionflowDiagramUpdater.getExecutionFlowObject_1001ContainedLinks(view);
            }
            case 1002: {
                return ExecutionflowDiagramUpdater.getExecutableFlowObject_1002ContainedLinks(view);
            }
            case 1003: {
                return ExecutionflowDiagramUpdater.getExecutionFlowResult_1003ContainedLinks(view);
            }
            case 1004: {
                return ExecutionflowDiagramUpdater.getStartPoint_1004ContainedLinks(view);
            }
            case 3001: {
                return ExecutionflowDiagramUpdater.getFlowObjectConnection_3001ContainedLinks(view);
            }
            case 3002: {
                return ExecutionflowDiagramUpdater.getStartPointConnection_3002ContainedLinks(view);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static List getIncomingLinks(View view) {
        switch (ExecutionflowVisualIDRegistry.getVisualID(view)) {
            case 1001: {
                return ExecutionflowDiagramUpdater.getExecutionFlowObject_1001IncomingLinks(view);
            }
            case 1002: {
                return ExecutionflowDiagramUpdater.getExecutableFlowObject_1002IncomingLinks(view);
            }
            case 1003: {
                return ExecutionflowDiagramUpdater.getExecutionFlowResult_1003IncomingLinks(view);
            }
            case 1004: {
                return ExecutionflowDiagramUpdater.getStartPoint_1004IncomingLinks(view);
            }
            case 3001: {
                return ExecutionflowDiagramUpdater.getFlowObjectConnection_3001IncomingLinks(view);
            }
            case 3002: {
                return ExecutionflowDiagramUpdater.getStartPointConnection_3002IncomingLinks(view);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static List getOutgoingLinks(View view) {
        switch (ExecutionflowVisualIDRegistry.getVisualID(view)) {
            case 1001: {
                return ExecutionflowDiagramUpdater.getExecutionFlowObject_1001OutgoingLinks(view);
            }
            case 1002: {
                return ExecutionflowDiagramUpdater.getExecutableFlowObject_1002OutgoingLinks(view);
            }
            case 1003: {
                return ExecutionflowDiagramUpdater.getExecutionFlowResult_1003OutgoingLinks(view);
            }
            case 1004: {
                return ExecutionflowDiagramUpdater.getStartPoint_1004OutgoingLinks(view);
            }
            case 3001: {
                return ExecutionflowDiagramUpdater.getFlowObjectConnection_3001OutgoingLinks(view);
            }
            case 3002: {
                return ExecutionflowDiagramUpdater.getStartPointConnection_3002OutgoingLinks(view);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static List getExecutionFlow_79ContainedLinks(View view) {
        ExecutionFlow modelElement = (ExecutionFlow)view.getElement();
        LinkedList result = new LinkedList();
        result.addAll(ExecutionflowDiagramUpdater.getContainedTypeModelFacetLinks_FlowObjectConnection_3001(modelElement));
        result.addAll(ExecutionflowDiagramUpdater.getContainedTypeModelFacetLinks_StartPointConnection_3002(modelElement));
        return result;
    }

    public static List getExecutionFlowObject_1001ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getExecutableFlowObject_1002ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getExecutionFlowResult_1003ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getStartPoint_1004ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getFlowObjectConnection_3001ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getStartPointConnection_3002ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getExecutionFlowObject_1001IncomingLinks(View view) {
        ExecutionFlowObject modelElement = (ExecutionFlowObject)view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find((Collection)view.eResource().getResourceSet().getResources());
        LinkedList result = new LinkedList();
        result.addAll(ExecutionflowDiagramUpdater.getIncomingTypeModelFacetLinks_FlowObjectConnection_3001((FlowObject)modelElement, crossReferences));
        result.addAll(ExecutionflowDiagramUpdater.getIncomingTypeModelFacetLinks_StartPointConnection_3002((SourceConnectable)modelElement, crossReferences));
        return result;
    }

    public static List getExecutableFlowObject_1002IncomingLinks(View view) {
        ExecutableFlowObject modelElement = (ExecutableFlowObject)view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find((Collection)view.eResource().getResourceSet().getResources());
        LinkedList result = new LinkedList();
        result.addAll(ExecutionflowDiagramUpdater.getIncomingTypeModelFacetLinks_FlowObjectConnection_3001((FlowObject)modelElement, crossReferences));
        result.addAll(ExecutionflowDiagramUpdater.getIncomingTypeModelFacetLinks_StartPointConnection_3002((SourceConnectable)modelElement, crossReferences));
        return result;
    }

    public static List getExecutionFlowResult_1003IncomingLinks(View view) {
        ExecutionFlowResult modelElement = (ExecutionFlowResult)view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find((Collection)view.eResource().getResourceSet().getResources());
        LinkedList result = new LinkedList();
        result.addAll(ExecutionflowDiagramUpdater.getIncomingTypeModelFacetLinks_FlowObjectConnection_3001((FlowObject)modelElement, crossReferences));
        return result;
    }

    public static List getStartPoint_1004IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getFlowObjectConnection_3001IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getStartPointConnection_3002IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getExecutionFlowObject_1001OutgoingLinks(View view) {
        ExecutionFlowObject modelElement = (ExecutionFlowObject)view.getElement();
        LinkedList result = new LinkedList();
        result.addAll(ExecutionflowDiagramUpdater.getOutgoingTypeModelFacetLinks_FlowObjectConnection_3001((SourceConnectable)modelElement));
        return result;
    }

    public static List getExecutableFlowObject_1002OutgoingLinks(View view) {
        ExecutableFlowObject modelElement = (ExecutableFlowObject)view.getElement();
        LinkedList result = new LinkedList();
        result.addAll(ExecutionflowDiagramUpdater.getOutgoingTypeModelFacetLinks_FlowObjectConnection_3001((SourceConnectable)modelElement));
        return result;
    }

    public static List getExecutionFlowResult_1003OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getStartPoint_1004OutgoingLinks(View view) {
        StartPoint modelElement = (StartPoint)view.getElement();
        LinkedList result = new LinkedList();
        result.addAll(ExecutionflowDiagramUpdater.getOutgoingTypeModelFacetLinks_StartPointConnection_3002(modelElement));
        return result;
    }

    public static List getFlowObjectConnection_3001OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    public static List getStartPointConnection_3002OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    private static Collection getContainedTypeModelFacetLinks_FlowObjectConnection_3001(ExecutionFlow container) {
        LinkedList<ExecutionflowLinkDescriptor> result = new LinkedList<ExecutionflowLinkDescriptor>();
        for (EObject linkObject : container.getFlowConnections()) {
            FlowObjectConnection link;
            if (!(linkObject instanceof FlowObjectConnection) || 3001 != ExecutionflowVisualIDRegistry.getLinkWithClassVisualID((EObject)(link = (FlowObjectConnection)linkObject))) continue;
            FlowObject dst = link.getDestinationObject();
            SourceConnectable src = link.getSourceObject();
            result.add(new ExecutionflowLinkDescriptor((EObject)src, (EObject)dst, (EObject)link, ExecutionflowElementTypes.FlowObjectConnection_3001, 3001));
        }
        return result;
    }

    private static Collection getContainedTypeModelFacetLinks_StartPointConnection_3002(ExecutionFlow container) {
        LinkedList<ExecutionflowLinkDescriptor> result = new LinkedList<ExecutionflowLinkDescriptor>();
        StartPointConnection link = container.getStartPointConnection();
        if (3002 != ExecutionflowVisualIDRegistry.getLinkWithClassVisualID((EObject)link)) {
            return result;
        }
        SourceConnectable dst = link.getDestinationObject();
        StartPoint src = link.getStartPoint();
        result.add(new ExecutionflowLinkDescriptor((EObject)src, (EObject)dst, (EObject)link, ExecutionflowElementTypes.StartPointConnection_3002, 3002));
        return result;
    }

    private static Collection getIncomingTypeModelFacetLinks_FlowObjectConnection_3001(FlowObject target, Map crossReferences) {
        LinkedList<ExecutionflowLinkDescriptor> result = new LinkedList<ExecutionflowLinkDescriptor>();
        Collection settings = (Collection)crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            FlowObjectConnection link;
            if (setting.getEStructuralFeature() != ExecutionflowPackage.eINSTANCE.getFlowObjectConnection_DestinationObject() || !(setting.getEObject() instanceof FlowObjectConnection) || 3001 != ExecutionflowVisualIDRegistry.getLinkWithClassVisualID((EObject)(link = (FlowObjectConnection)setting.getEObject()))) continue;
            SourceConnectable src = link.getSourceObject();
            result.add(new ExecutionflowLinkDescriptor((EObject)src, (EObject)target, (EObject)link, ExecutionflowElementTypes.FlowObjectConnection_3001, 3001));
        }
        return result;
    }

    private static Collection getIncomingTypeModelFacetLinks_StartPointConnection_3002(SourceConnectable target, Map crossReferences) {
        LinkedList<ExecutionflowLinkDescriptor> result = new LinkedList<ExecutionflowLinkDescriptor>();
        Collection settings = (Collection)crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            StartPointConnection link;
            if (setting.getEStructuralFeature() != ExecutionflowPackage.eINSTANCE.getStartPointConnection_DestinationObject() || !(setting.getEObject() instanceof StartPointConnection) || 3002 != ExecutionflowVisualIDRegistry.getLinkWithClassVisualID((EObject)(link = (StartPointConnection)setting.getEObject()))) continue;
            StartPoint src = link.getStartPoint();
            result.add(new ExecutionflowLinkDescriptor((EObject)src, (EObject)target, (EObject)link, ExecutionflowElementTypes.StartPointConnection_3002, 3002));
        }
        return result;
    }

    private static Collection getOutgoingTypeModelFacetLinks_FlowObjectConnection_3001(SourceConnectable source) {
        ExecutionFlow container = null;
        SourceConnectable element = source;
        while (element != null && container == null) {
            if (element instanceof ExecutionFlow) {
                container = (ExecutionFlow)element;
            }
            element = element.eContainer();
        }
        if (container == null) {
            return Collections.EMPTY_LIST;
        }
        LinkedList<ExecutionflowLinkDescriptor> result = new LinkedList<ExecutionflowLinkDescriptor>();
        for (EObject linkObject : container.getFlowConnections()) {
            FlowObjectConnection link;
            if (!(linkObject instanceof FlowObjectConnection) || 3001 != ExecutionflowVisualIDRegistry.getLinkWithClassVisualID((EObject)(link = (FlowObjectConnection)linkObject))) continue;
            FlowObject dst = link.getDestinationObject();
            SourceConnectable src = link.getSourceObject();
            if (src != source) continue;
            result.add(new ExecutionflowLinkDescriptor((EObject)src, (EObject)dst, (EObject)link, ExecutionflowElementTypes.FlowObjectConnection_3001, 3001));
        }
        return result;
    }

    private static Collection getOutgoingTypeModelFacetLinks_StartPointConnection_3002(StartPoint source) {
        ExecutionFlow container = null;
        StartPoint element = source;
        while (element != null && container == null) {
            if (element instanceof ExecutionFlow) {
                container = (ExecutionFlow)element;
            }
            element = element.eContainer();
        }
        if (container == null) {
            return Collections.EMPTY_LIST;
        }
        LinkedList<ExecutionflowLinkDescriptor> result = new LinkedList<ExecutionflowLinkDescriptor>();
        StartPointConnection link = container.getStartPointConnection();
        if (3002 != ExecutionflowVisualIDRegistry.getLinkWithClassVisualID((EObject)link)) {
            return result;
        }
        SourceConnectable dst = link.getDestinationObject();
        StartPoint src = link.getStartPoint();
        if (src != source) {
            return result;
        }
        result.add(new ExecutionflowLinkDescriptor((EObject)src, (EObject)dst, (EObject)link, ExecutionflowElementTypes.StartPointConnection_3002, 3002));
        return result;
    }
}

