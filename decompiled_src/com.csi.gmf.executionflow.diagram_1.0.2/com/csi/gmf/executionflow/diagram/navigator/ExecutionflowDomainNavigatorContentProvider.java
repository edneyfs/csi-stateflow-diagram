/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
 *  org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer$Delegate
 *  org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory
 *  org.eclipse.jface.viewers.Viewer
 *  org.eclipse.ui.IMemento
 *  org.eclipse.ui.navigator.ICommonContentExtensionSite
 *  org.eclipse.ui.navigator.ICommonContentProvider
 *  org.eclipse.ui.views.properties.IPropertySourceProvider
 */
package com.csi.gmf.executionflow.diagram.navigator;

import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowAbstractNavigatorItem;
import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowDomainNavigatorItem;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

public class ExecutionflowDomainNavigatorContentProvider
implements ICommonContentProvider {
    private AdapterFactoryContentProvider myAdapterFctoryContentProvier = new AdapterFactoryContentProvider(ExecutionflowDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private Viewer myViewer;
    private AdapterFactoryEditingDomain myEditingDomain;
    private WorkspaceSynchronizer myWorkspaceSynchronizer;
    private Runnable myViewerRefreshRunnable;

    public ExecutionflowDomainNavigatorContentProvider() {
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
                if (ExecutionflowDomainNavigatorContentProvider.this.myViewer != null) {
                    ExecutionflowDomainNavigatorContentProvider.this.myViewer.refresh();
                }
            }
        };
        this.myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain, new WorkspaceSynchronizer.Delegate(){

            public void dispose() {
            }

            public boolean handleResourceChanged(Resource resource) {
                for (Resource nextResource : ExecutionflowDomainNavigatorContentProvider.this.myEditingDomain.getResourceSet().getResources()) {
                    nextResource.unload();
                }
                if (ExecutionflowDomainNavigatorContentProvider.this.myViewer != null) {
                    ExecutionflowDomainNavigatorContentProvider.this.myViewer.getControl().getDisplay().asyncExec(ExecutionflowDomainNavigatorContentProvider.this.myViewerRefreshRunnable);
                }
                return true;
            }

            public boolean handleResourceDeleted(Resource resource) {
                for (Resource nextResource : ExecutionflowDomainNavigatorContentProvider.this.myEditingDomain.getResourceSet().getResources()) {
                    nextResource.unload();
                }
                if (ExecutionflowDomainNavigatorContentProvider.this.myViewer != null) {
                    ExecutionflowDomainNavigatorContentProvider.this.myViewer.getControl().getDisplay().asyncExec(ExecutionflowDomainNavigatorContentProvider.this.myViewerRefreshRunnable);
                }
                return true;
            }

            public boolean handleResourceMoved(Resource resource, URI newURI) {
                for (Resource nextResource : ExecutionflowDomainNavigatorContentProvider.this.myEditingDomain.getResourceSet().getResources()) {
                    nextResource.unload();
                }
                if (ExecutionflowDomainNavigatorContentProvider.this.myViewer != null) {
                    ExecutionflowDomainNavigatorContentProvider.this.myViewer.getControl().getDisplay().asyncExec(ExecutionflowDomainNavigatorContentProvider.this.myViewerRefreshRunnable);
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
            return this.wrapEObjects(this.myAdapterFctoryContentProvier.getChildren((Object)resource), parentElement);
        }
        if (parentElement instanceof ExecutionflowDomainNavigatorItem) {
            return this.wrapEObjects(this.myAdapterFctoryContentProvier.getChildren((Object)((ExecutionflowDomainNavigatorItem)((Object)parentElement)).getEObject()), parentElement);
        }
        return EMPTY_ARRAY;
    }

    public Object[] wrapEObjects(Object[] objects, Object parentElement) {
        ArrayList<ExecutionflowDomainNavigatorItem> result = new ArrayList<ExecutionflowDomainNavigatorItem>();
        int i = 0;
        while (i < objects.length) {
            if (objects[i] instanceof EObject) {
                result.add(new ExecutionflowDomainNavigatorItem((EObject)objects[i], parentElement, (IPropertySourceProvider)this.myAdapterFctoryContentProvier));
            }
            ++i;
        }
        return result.toArray();
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

