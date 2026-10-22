/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionflowPackage
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand
 *  org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalConnectionEditPolicy
 *  org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest
 *  org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest$ConnectionViewDescriptor
 *  org.eclipse.gmf.runtime.notation.Diagram
 *  org.eclipse.gmf.runtime.notation.Edge
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.edit.policies;

import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramUpdater;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowLinkDescriptor;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowNodeDescriptor;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalConnectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionFlowCanonicalEditPolicy
extends CanonicalConnectionEditPolicy {
    Set myFeaturesToSynchronize;

    protected List getSemanticChildrenList() {
        View viewObject = (View)this.getHost().getModel();
        LinkedList<EObject> result = new LinkedList<EObject>();
        Iterator it = ExecutionflowDiagramUpdater.getExecutionFlow_79SemanticChildren(viewObject).iterator();
        while (it.hasNext()) {
            result.add(((ExecutionflowNodeDescriptor)it.next()).getModelElement());
        }
        return result;
    }

    protected boolean shouldDeleteView(View view) {
        return true;
    }

    protected boolean isOrphaned(Collection semanticChildren, View view) {
        int visualID = ExecutionflowVisualIDRegistry.getVisualID(view);
        switch (visualID) {
            case 1001: 
            case 1002: 
            case 1003: 
            case 1004: {
                if (semanticChildren.contains(view.getElement())) break;
                return true;
            }
        }
        return false;
    }

    protected String getDefaultFactoryHint() {
        return null;
    }

    protected Set getFeaturesToSynchronize() {
        if (this.myFeaturesToSynchronize == null) {
            this.myFeaturesToSynchronize = new HashSet();
            this.myFeaturesToSynchronize.add(ExecutionflowPackage.eINSTANCE.getExecutionFlow_ExecutionFlowObjects());
            this.myFeaturesToSynchronize.add(ExecutionflowPackage.eINSTANCE.getExecutionFlow_ExecutableFlowObjects());
            this.myFeaturesToSynchronize.add(ExecutionflowPackage.eINSTANCE.getExecutionFlow_Results());
            this.myFeaturesToSynchronize.add(ExecutionflowPackage.eINSTANCE.getExecutionFlow_StartPoint());
        }
        return this.myFeaturesToSynchronize;
    }

    protected List getSemanticConnectionsList() {
        return Collections.EMPTY_LIST;
    }

    protected EObject getSourceElement(EObject relationship) {
        return null;
    }

    protected EObject getTargetElement(EObject relationship) {
        return null;
    }

    protected boolean shouldIncludeConnection(Edge connector, Collection children) {
        return false;
    }

    protected void refreshSemantic() {
        LinkedList createdViews = new LinkedList();
        createdViews.addAll(this.refreshSemanticChildren());
        LinkedList createdConnectionViews = new LinkedList();
        createdConnectionViews.addAll(this.refreshSemanticConnections());
        createdConnectionViews.addAll(this.refreshConnections());
        if (createdViews.size() > 1) {
            DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(this.host().getEditingDomain(), createdViews, this.host());
            this.executeCommand((Command)new ICommandProxy((ICommand)layoutCmd));
        }
        createdViews.addAll(createdConnectionViews);
        this.makeViewsImmutable(createdViews);
    }

    private Collection refreshConnections() {
        HashMap domain2NotationMap = new HashMap();
        Collection linkDescriptors = this.collectAllLinks((View)this.getDiagram(), domain2NotationMap);
        LinkedList existingLinks = new LinkedList(this.getDiagram().getEdges());
        Iterator linksIterator = existingLinks.iterator();
        while (linksIterator.hasNext()) {
            Edge nextDiagramLink = (Edge)linksIterator.next();
            int diagramLinkVisualID = ExecutionflowVisualIDRegistry.getVisualID((View)nextDiagramLink);
            if (diagramLinkVisualID == -1) {
                if (nextDiagramLink.getSource() == null || nextDiagramLink.getTarget() == null) continue;
                linksIterator.remove();
                continue;
            }
            EObject diagramLinkObject = nextDiagramLink.getElement();
            EObject diagramLinkSrc = nextDiagramLink.getSource().getElement();
            EObject diagramLinkDst = nextDiagramLink.getTarget().getElement();
            Iterator LinkDescriptorsIterator = linkDescriptors.iterator();
            while (LinkDescriptorsIterator.hasNext()) {
                ExecutionflowLinkDescriptor nextLinkDescriptor = (ExecutionflowLinkDescriptor)LinkDescriptorsIterator.next();
                if (diagramLinkObject != nextLinkDescriptor.getModelElement() || diagramLinkSrc != nextLinkDescriptor.getSource() || diagramLinkDst != nextLinkDescriptor.getDestination() || diagramLinkVisualID != nextLinkDescriptor.getVisualID()) continue;
                linksIterator.remove();
                LinkDescriptorsIterator.remove();
            }
        }
        this.deleteViews(existingLinks.iterator());
        return this.createConnections(linkDescriptors, domain2NotationMap);
    }

    private Collection collectAllLinks(View view, Map domain2NotationMap) {
        if (!"Executionflow".equals(ExecutionflowVisualIDRegistry.getModelID(view))) {
            return Collections.EMPTY_LIST;
        }
        LinkedList result = new LinkedList();
        switch (ExecutionflowVisualIDRegistry.getVisualID(view)) {
            case 79: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(ExecutionflowDiagramUpdater.getExecutionFlow_79ContainedLinks(view));
                }
                if (domain2NotationMap.containsKey(view.getElement()) && view.getEAnnotation("Shortcut") != null) break;
                domain2NotationMap.put(view.getElement(), view);
                break;
            }
            case 1001: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(ExecutionflowDiagramUpdater.getExecutionFlowObject_1001ContainedLinks(view));
                }
                if (domain2NotationMap.containsKey(view.getElement()) && view.getEAnnotation("Shortcut") != null) break;
                domain2NotationMap.put(view.getElement(), view);
                break;
            }
            case 1002: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(ExecutionflowDiagramUpdater.getExecutableFlowObject_1002ContainedLinks(view));
                }
                if (domain2NotationMap.containsKey(view.getElement()) && view.getEAnnotation("Shortcut") != null) break;
                domain2NotationMap.put(view.getElement(), view);
                break;
            }
            case 1003: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(ExecutionflowDiagramUpdater.getExecutionFlowResult_1003ContainedLinks(view));
                }
                if (domain2NotationMap.containsKey(view.getElement()) && view.getEAnnotation("Shortcut") != null) break;
                domain2NotationMap.put(view.getElement(), view);
                break;
            }
            case 1004: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(ExecutionflowDiagramUpdater.getStartPoint_1004ContainedLinks(view));
                }
                if (domain2NotationMap.containsKey(view.getElement()) && view.getEAnnotation("Shortcut") != null) break;
                domain2NotationMap.put(view.getElement(), view);
                break;
            }
            case 3001: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(ExecutionflowDiagramUpdater.getFlowObjectConnection_3001ContainedLinks(view));
                }
                if (domain2NotationMap.containsKey(view.getElement()) && view.getEAnnotation("Shortcut") != null) break;
                domain2NotationMap.put(view.getElement(), view);
                break;
            }
            case 3002: {
                if (!domain2NotationMap.containsKey(view.getElement())) {
                    result.addAll(ExecutionflowDiagramUpdater.getStartPointConnection_3002ContainedLinks(view));
                }
                if (domain2NotationMap.containsKey(view.getElement()) && view.getEAnnotation("Shortcut") != null) break;
                domain2NotationMap.put(view.getElement(), view);
            }
        }
        Iterator children = view.getChildren().iterator();
        while (children.hasNext()) {
            result.addAll(this.collectAllLinks((View)children.next(), domain2NotationMap));
        }
        Iterator edges = view.getSourceEdges().iterator();
        while (edges.hasNext()) {
            result.addAll(this.collectAllLinks((View)edges.next(), domain2NotationMap));
        }
        return result;
    }

    private Collection createConnections(Collection linkDescriptors, Map domain2NotationMap) {
        LinkedList<IAdaptable> adapters = new LinkedList<IAdaptable>();
        for (ExecutionflowLinkDescriptor nextLinkDescriptor : linkDescriptors) {
            EditPart sourceEditPart = this.getEditPart(nextLinkDescriptor.getSource(), domain2NotationMap);
            EditPart targetEditPart = this.getEditPart(nextLinkDescriptor.getDestination(), domain2NotationMap);
            if (sourceEditPart == null || targetEditPart == null) continue;
            CreateConnectionViewRequest.ConnectionViewDescriptor descriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(nextLinkDescriptor.getSemanticAdapter(), null, -1, false, ((IGraphicalEditPart)this.getHost()).getDiagramPreferencesHint());
            CreateConnectionViewRequest ccr = new CreateConnectionViewRequest(descriptor);
            ccr.setType((Object)"connection start");
            ccr.setSourceEditPart(sourceEditPart);
            sourceEditPart.getCommand((Request)ccr);
            ccr.setTargetEditPart(targetEditPart);
            ccr.setType((Object)"connection end");
            Command cmd = targetEditPart.getCommand((Request)ccr);
            if (cmd == null || !cmd.canExecute()) continue;
            this.executeCommand(cmd);
            IAdaptable viewAdapter = (IAdaptable)ccr.getNewObject();
            if (viewAdapter == null) continue;
            adapters.add(viewAdapter);
        }
        return adapters;
    }

    private EditPart getEditPart(EObject domainModelElement, Map domain2NotationMap) {
        View view = (View)domain2NotationMap.get(domainModelElement);
        if (view != null) {
            return (EditPart)this.getHost().getViewer().getEditPartRegistry().get(view);
        }
        return null;
    }

    private Diagram getDiagram() {
        return ((View)this.getHost().getModel()).getDiagram();
    }
}

