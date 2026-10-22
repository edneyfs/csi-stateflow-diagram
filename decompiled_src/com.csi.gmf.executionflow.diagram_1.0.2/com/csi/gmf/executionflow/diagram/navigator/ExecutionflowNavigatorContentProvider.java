/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer$Delegate
 *  org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory
 *  org.eclipse.gmf.runtime.notation.Diagram
 *  org.eclipse.gmf.runtime.notation.Edge
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.viewers.Viewer
 *  org.eclipse.ui.IMemento
 *  org.eclipse.ui.navigator.ICommonContentExtensionSite
 *  org.eclipse.ui.navigator.ICommonContentProvider
 */
package com.csi.gmf.executionflow.diagram.navigator;

import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowAbstractNavigatorItem;
import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowNavigatorGroup;
import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowNavigatorItem;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import com.csi.gmf.executionflow.diagram.part.Messages;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

public class ExecutionflowNavigatorContentProvider
implements ICommonContentProvider {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private Viewer myViewer;
    private AdapterFactoryEditingDomain myEditingDomain;
    private WorkspaceSynchronizer myWorkspaceSynchronizer;
    private Runnable myViewerRefreshRunnable;

    public ExecutionflowNavigatorContentProvider() {
        TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
        this.myEditingDomain = (AdapterFactoryEditingDomain)editingDomain;
        this.myEditingDomain.setResourceToReadOnlyMap((Map)new HashMap(){

            @Override
            public Object get(Object key) {
                if (!this.containsKey(key)) {
                    this.put(key, Boolean.TRUE);
                }
                return super.get(key);
            }
        });
        this.myViewerRefreshRunnable = new Runnable(){

            @Override
            public void run() {
                if (ExecutionflowNavigatorContentProvider.this.myViewer != null) {
                    ExecutionflowNavigatorContentProvider.this.myViewer.refresh();
                }
            }
        };
        this.myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain, new WorkspaceSynchronizer.Delegate(){

            public void dispose() {
            }

            public boolean handleResourceChanged(Resource resource) {
                for (Resource nextResource : ExecutionflowNavigatorContentProvider.this.myEditingDomain.getResourceSet().getResources()) {
                    nextResource.unload();
                }
                if (ExecutionflowNavigatorContentProvider.this.myViewer != null) {
                    ExecutionflowNavigatorContentProvider.this.myViewer.getControl().getDisplay().asyncExec(ExecutionflowNavigatorContentProvider.this.myViewerRefreshRunnable);
                }
                return true;
            }

            public boolean handleResourceDeleted(Resource resource) {
                for (Resource nextResource : ExecutionflowNavigatorContentProvider.this.myEditingDomain.getResourceSet().getResources()) {
                    nextResource.unload();
                }
                if (ExecutionflowNavigatorContentProvider.this.myViewer != null) {
                    ExecutionflowNavigatorContentProvider.this.myViewer.getControl().getDisplay().asyncExec(ExecutionflowNavigatorContentProvider.this.myViewerRefreshRunnable);
                }
                return true;
            }

            public boolean handleResourceMoved(Resource resource, URI newURI) {
                for (Resource nextResource : ExecutionflowNavigatorContentProvider.this.myEditingDomain.getResourceSet().getResources()) {
                    nextResource.unload();
                }
                if (ExecutionflowNavigatorContentProvider.this.myViewer != null) {
                    ExecutionflowNavigatorContentProvider.this.myViewer.getControl().getDisplay().asyncExec(ExecutionflowNavigatorContentProvider.this.myViewerRefreshRunnable);
                }
                return true;
            }
        });
    }

    public void dispose() {
        this.myWorkspaceSynchronizer.dispose();
        this.myWorkspaceSynchronizer = null;
        this.myViewerRefreshRunnable = null;
        for (Resource resource : this.myEditingDomain.getResourceSet().getResources()) {
            resource.unload();
        }
        ((TransactionalEditingDomain)this.myEditingDomain).dispose();
        this.myEditingDomain = null;
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        this.myViewer = viewer;
    }

    public Object[] getElements(Object inputElement) {
        return this.getChildren(inputElement);
    }

    public void restoreState(IMemento aMemento) {
    }

    public void saveState(IMemento aMemento) {
    }

    public void init(ICommonContentExtensionSite aConfig) {
    }

    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof IFile) {
            IFile file = (IFile)parentElement;
            URI fileURI = URI.createPlatformResourceURI((String)file.getFullPath().toString(), (boolean)true);
            Resource resource = this.myEditingDomain.getResourceSet().getResource(fileURI, true);
            ArrayList result = new ArrayList();
            result.addAll(this.createNavigatorItems(this.selectViewsByType((Collection)resource.getContents(), "Executionflow"), file, false));
            return result.toArray();
        }
        if (parentElement instanceof ExecutionflowNavigatorGroup) {
            ExecutionflowNavigatorGroup group = (ExecutionflowNavigatorGroup)((Object)parentElement);
            return group.getChildren();
        }
        if (parentElement instanceof ExecutionflowNavigatorItem) {
            ExecutionflowNavigatorItem navigatorItem = (ExecutionflowNavigatorItem)((Object)parentElement);
            if (navigatorItem.isLeaf() || !this.isOwnView(navigatorItem.getView())) {
                return EMPTY_ARRAY;
            }
            return this.getViewChildren(navigatorItem.getView(), parentElement);
        }
        return EMPTY_ARRAY;
    }

    private Object[] getViewChildren(View view, Object parentElement) {
        switch (ExecutionflowVisualIDRegistry.getVisualID(view)) {
            case 79: {
                ArrayList<ExecutionflowNavigatorGroup> result = new ArrayList<ExecutionflowNavigatorGroup>();
                ExecutionflowNavigatorGroup links = new ExecutionflowNavigatorGroup(Messages.NavigatorGroupName_ExecutionFlow_79_links, "icons/linksNavigatorGroup.gif", parentElement);
                Collection connectedViews = this.getChildrenByType(Collections.singleton(view), 1001);
                result.addAll(this.createNavigatorItems(connectedViews, parentElement, false));
                connectedViews = this.getChildrenByType(Collections.singleton(view), 1002);
                result.addAll(this.createNavigatorItems(connectedViews, parentElement, false));
                connectedViews = this.getChildrenByType(Collections.singleton(view), 1003);
                result.addAll(this.createNavigatorItems(connectedViews, parentElement, false));
                connectedViews = this.getChildrenByType(Collections.singleton(view), 1004);
                result.addAll(this.createNavigatorItems(connectedViews, parentElement, false));
                connectedViews = this.getDiagramLinksByType(Collections.singleton(view), 3001);
                links.addChildren(this.createNavigatorItems(connectedViews, (Object)links, false));
                connectedViews = this.getDiagramLinksByType(Collections.singleton(view), 3002);
                links.addChildren(this.createNavigatorItems(connectedViews, (Object)links, false));
                if (!links.isEmpty()) {
                    result.add(links);
                }
                return result.toArray();
            }
            case 1001: {
                ArrayList<ExecutionflowNavigatorGroup> result = new ArrayList<ExecutionflowNavigatorGroup>();
                ExecutionflowNavigatorGroup incominglinks = new ExecutionflowNavigatorGroup(Messages.NavigatorGroupName_ExecutionFlowObject_1001_incominglinks, "icons/incomingLinksNavigatorGroup.gif", parentElement);
                ExecutionflowNavigatorGroup outgoinglinks = new ExecutionflowNavigatorGroup(Messages.NavigatorGroupName_ExecutionFlowObject_1001_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", parentElement);
                Collection connectedViews = this.getIncomingLinksByType(Collections.singleton(view), 3001);
                incominglinks.addChildren(this.createNavigatorItems(connectedViews, (Object)incominglinks, true));
                connectedViews = this.getOutgoingLinksByType(Collections.singleton(view), 3001);
                outgoinglinks.addChildren(this.createNavigatorItems(connectedViews, (Object)outgoinglinks, true));
                connectedViews = this.getIncomingLinksByType(Collections.singleton(view), 3002);
                incominglinks.addChildren(this.createNavigatorItems(connectedViews, (Object)incominglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }
            case 1002: {
                ArrayList<ExecutionflowNavigatorGroup> result = new ArrayList<ExecutionflowNavigatorGroup>();
                ExecutionflowNavigatorGroup incominglinks = new ExecutionflowNavigatorGroup(Messages.NavigatorGroupName_ExecutableFlowObject_1002_incominglinks, "icons/incomingLinksNavigatorGroup.gif", parentElement);
                ExecutionflowNavigatorGroup outgoinglinks = new ExecutionflowNavigatorGroup(Messages.NavigatorGroupName_ExecutableFlowObject_1002_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", parentElement);
                Collection connectedViews = this.getIncomingLinksByType(Collections.singleton(view), 3001);
                incominglinks.addChildren(this.createNavigatorItems(connectedViews, (Object)incominglinks, true));
                connectedViews = this.getOutgoingLinksByType(Collections.singleton(view), 3001);
                outgoinglinks.addChildren(this.createNavigatorItems(connectedViews, (Object)outgoinglinks, true));
                connectedViews = this.getIncomingLinksByType(Collections.singleton(view), 3002);
                incominglinks.addChildren(this.createNavigatorItems(connectedViews, (Object)incominglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }
            case 1003: {
                ArrayList<ExecutionflowNavigatorGroup> result = new ArrayList<ExecutionflowNavigatorGroup>();
                ExecutionflowNavigatorGroup incominglinks = new ExecutionflowNavigatorGroup(Messages.NavigatorGroupName_ExecutionFlowResult_1003_incominglinks, "icons/incomingLinksNavigatorGroup.gif", parentElement);
                Collection connectedViews = this.getIncomingLinksByType(Collections.singleton(view), 3001);
                incominglinks.addChildren(this.createNavigatorItems(connectedViews, (Object)incominglinks, true));
                if (!incominglinks.isEmpty()) {
                    result.add(incominglinks);
                }
                return result.toArray();
            }
            case 1004: {
                ArrayList<ExecutionflowNavigatorGroup> result = new ArrayList<ExecutionflowNavigatorGroup>();
                ExecutionflowNavigatorGroup outgoinglinks = new ExecutionflowNavigatorGroup(Messages.NavigatorGroupName_StartPoint_1004_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", parentElement);
                Collection connectedViews = this.getOutgoingLinksByType(Collections.singleton(view), 3002);
                outgoinglinks.addChildren(this.createNavigatorItems(connectedViews, (Object)outgoinglinks, true));
                if (!outgoinglinks.isEmpty()) {
                    result.add(outgoinglinks);
                }
                return result.toArray();
            }
            case 3001: {
                ArrayList<ExecutionflowNavigatorGroup> result = new ArrayList<ExecutionflowNavigatorGroup>();
                ExecutionflowNavigatorGroup target = new ExecutionflowNavigatorGroup(Messages.NavigatorGroupName_FlowObjectConnection_3001_target, "icons/linkTargetNavigatorGroup.gif", parentElement);
                ExecutionflowNavigatorGroup source = new ExecutionflowNavigatorGroup(Messages.NavigatorGroupName_FlowObjectConnection_3001_source, "icons/linkSourceNavigatorGroup.gif", parentElement);
                Collection connectedViews = this.getLinksTargetByType(Collections.singleton(view), 1001);
                target.addChildren(this.createNavigatorItems(connectedViews, (Object)target, true));
                connectedViews = this.getLinksTargetByType(Collections.singleton(view), 1002);
                target.addChildren(this.createNavigatorItems(connectedViews, (Object)target, true));
                connectedViews = this.getLinksTargetByType(Collections.singleton(view), 1003);
                target.addChildren(this.createNavigatorItems(connectedViews, (Object)target, true));
                connectedViews = this.getLinksSourceByType(Collections.singleton(view), 1001);
                source.addChildren(this.createNavigatorItems(connectedViews, (Object)source, true));
                connectedViews = this.getLinksSourceByType(Collections.singleton(view), 1002);
                source.addChildren(this.createNavigatorItems(connectedViews, (Object)source, true));
                if (!target.isEmpty()) {
                    result.add(target);
                }
                if (!source.isEmpty()) {
                    result.add(source);
                }
                return result.toArray();
            }
            case 3002: {
                ArrayList<ExecutionflowNavigatorGroup> result = new ArrayList<ExecutionflowNavigatorGroup>();
                ExecutionflowNavigatorGroup target = new ExecutionflowNavigatorGroup(Messages.NavigatorGroupName_StartPointConnection_3002_target, "icons/linkTargetNavigatorGroup.gif", parentElement);
                ExecutionflowNavigatorGroup source = new ExecutionflowNavigatorGroup(Messages.NavigatorGroupName_StartPointConnection_3002_source, "icons/linkSourceNavigatorGroup.gif", parentElement);
                Collection connectedViews = this.getLinksTargetByType(Collections.singleton(view), 1001);
                target.addChildren(this.createNavigatorItems(connectedViews, (Object)target, true));
                connectedViews = this.getLinksTargetByType(Collections.singleton(view), 1002);
                target.addChildren(this.createNavigatorItems(connectedViews, (Object)target, true));
                connectedViews = this.getLinksSourceByType(Collections.singleton(view), 1004);
                source.addChildren(this.createNavigatorItems(connectedViews, (Object)source, true));
                if (!target.isEmpty()) {
                    result.add(target);
                }
                if (!source.isEmpty()) {
                    result.add(source);
                }
                return result.toArray();
            }
        }
        return EMPTY_ARRAY;
    }

    private Collection getLinksSourceByType(Collection edges, int visualID) {
        ArrayList<View> result = new ArrayList<View>();
        String type = ExecutionflowVisualIDRegistry.getType(visualID);
        for (Edge nextEdge : edges) {
            View nextEdgeSource = nextEdge.getSource();
            if (!type.equals(nextEdgeSource.getType()) || !this.isOwnView(nextEdgeSource)) continue;
            result.add(nextEdgeSource);
        }
        return result;
    }

    private Collection getLinksTargetByType(Collection edges, int visualID) {
        ArrayList<View> result = new ArrayList<View>();
        String type = ExecutionflowVisualIDRegistry.getType(visualID);
        for (Edge nextEdge : edges) {
            View nextEdgeTarget = nextEdge.getTarget();
            if (!type.equals(nextEdgeTarget.getType()) || !this.isOwnView(nextEdgeTarget)) continue;
            result.add(nextEdgeTarget);
        }
        return result;
    }

    private Collection getOutgoingLinksByType(Collection nodes, int visualID) {
        ArrayList result = new ArrayList();
        String type = ExecutionflowVisualIDRegistry.getType(visualID);
        for (View nextNode : nodes) {
            result.addAll(this.selectViewsByType((Collection)nextNode.getSourceEdges(), type));
        }
        return result;
    }

    private Collection getIncomingLinksByType(Collection nodes, int visualID) {
        ArrayList result = new ArrayList();
        String type = ExecutionflowVisualIDRegistry.getType(visualID);
        for (View nextNode : nodes) {
            result.addAll(this.selectViewsByType((Collection)nextNode.getTargetEdges(), type));
        }
        return result;
    }

    private Collection getChildrenByType(Collection nodes, int visualID) {
        ArrayList result = new ArrayList();
        String type = ExecutionflowVisualIDRegistry.getType(visualID);
        for (View nextNode : nodes) {
            result.addAll(this.selectViewsByType((Collection)nextNode.getChildren(), type));
        }
        return result;
    }

    private Collection getDiagramLinksByType(Collection diagrams, int visualID) {
        ArrayList result = new ArrayList();
        String type = ExecutionflowVisualIDRegistry.getType(visualID);
        for (Diagram nextDiagram : diagrams) {
            result.addAll(this.selectViewsByType((Collection)nextDiagram.getEdges(), type));
        }
        return result;
    }

    private Collection selectViewsByType(Collection views, String type) {
        ArrayList<View> result = new ArrayList<View>();
        for (View nextView : views) {
            if (!type.equals(nextView.getType()) || !this.isOwnView(nextView)) continue;
            result.add(nextView);
        }
        return result;
    }

    private boolean isOwnView(View view) {
        return "Executionflow".equals(ExecutionflowVisualIDRegistry.getModelID(view));
    }

    private Collection createNavigatorItems(Collection views, Object parent, boolean isLeafs) {
        ArrayList<ExecutionflowNavigatorItem> result = new ArrayList<ExecutionflowNavigatorItem>();
        Iterator it = views.iterator();
        while (it.hasNext()) {
            result.add(new ExecutionflowNavigatorItem((View)it.next(), parent, isLeafs));
        }
        return result;
    }

    public Object getParent(Object element) {
        if (element instanceof ExecutionflowAbstractNavigatorItem) {
            ExecutionflowAbstractNavigatorItem abstractNavigatorItem = (ExecutionflowAbstractNavigatorItem)((Object)element);
            return abstractNavigatorItem.getParent();
        }
        return null;
    }

    public boolean hasChildren(Object element) {
        return element instanceof IFile || this.getChildren(element).length > 0;
    }
}

