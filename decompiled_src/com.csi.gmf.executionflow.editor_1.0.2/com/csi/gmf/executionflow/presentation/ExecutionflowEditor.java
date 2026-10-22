/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.provider.ExecutionflowItemProviderAdapterFactory
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IMarker
 *  org.eclipse.core.resources.IResourceChangeEvent
 *  org.eclipse.core.resources.IResourceChangeListener
 *  org.eclipse.core.resources.IResourceDelta
 *  org.eclipse.core.resources.IResourceDeltaVisitor
 *  org.eclipse.core.resources.ResourcesPlugin
 *  org.eclipse.core.runtime.CoreException
 *  org.eclipse.core.runtime.IPath
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.core.runtime.NullProgressMonitor
 *  org.eclipse.emf.common.command.BasicCommandStack
 *  org.eclipse.emf.common.command.Command
 *  org.eclipse.emf.common.command.CommandStack
 *  org.eclipse.emf.common.command.CommandStackListener
 *  org.eclipse.emf.common.notify.AdapterFactory
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.common.notify.Notifier
 *  org.eclipse.emf.common.ui.MarkerHelper
 *  org.eclipse.emf.common.ui.ViewerPane
 *  org.eclipse.emf.common.ui.editor.ProblemEditorPart
 *  org.eclipse.emf.common.ui.viewer.IViewerProvider
 *  org.eclipse.emf.common.util.BasicDiagnostic
 *  org.eclipse.emf.common.util.Diagnostic
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.ecore.resource.ResourceSet
 *  org.eclipse.emf.ecore.util.EContentAdapter
 *  org.eclipse.emf.ecore.util.EcoreUtil
 *  org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
 *  org.eclipse.emf.edit.domain.EditingDomain
 *  org.eclipse.emf.edit.domain.IEditingDomainProvider
 *  org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator
 *  org.eclipse.emf.edit.provider.ComposedAdapterFactory
 *  org.eclipse.emf.edit.provider.ComposedAdapterFactory$Descriptor$Registry
 *  org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory
 *  org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory
 *  org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor
 *  org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor
 *  org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter
 *  org.eclipse.emf.edit.ui.dnd.LocalTransfer
 *  org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter
 *  org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider
 *  org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
 *  org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider
 *  org.eclipse.emf.edit.ui.util.EditUIMarkerHelper
 *  org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage
 *  org.eclipse.jface.action.IContributionItem
 *  org.eclipse.jface.action.IMenuListener
 *  org.eclipse.jface.action.IMenuManager
 *  org.eclipse.jface.action.IStatusLineManager
 *  org.eclipse.jface.action.IToolBarManager
 *  org.eclipse.jface.action.MenuManager
 *  org.eclipse.jface.action.Separator
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.jface.dialogs.ProgressMonitorDialog
 *  org.eclipse.jface.operation.IRunnableWithProgress
 *  org.eclipse.jface.viewers.ColumnLayoutData
 *  org.eclipse.jface.viewers.ColumnWeightData
 *  org.eclipse.jface.viewers.IBaseLabelProvider
 *  org.eclipse.jface.viewers.IContentProvider
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.ISelectionChangedListener
 *  org.eclipse.jface.viewers.ISelectionProvider
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.jface.viewers.ListViewer
 *  org.eclipse.jface.viewers.SelectionChangedEvent
 *  org.eclipse.jface.viewers.StructuredSelection
 *  org.eclipse.jface.viewers.StructuredViewer
 *  org.eclipse.jface.viewers.TableLayout
 *  org.eclipse.jface.viewers.TableViewer
 *  org.eclipse.jface.viewers.TreeViewer
 *  org.eclipse.jface.viewers.Viewer
 *  org.eclipse.swt.custom.CTabFolder
 *  org.eclipse.swt.dnd.DragSourceListener
 *  org.eclipse.swt.dnd.DropTargetListener
 *  org.eclipse.swt.dnd.Transfer
 *  org.eclipse.swt.events.ControlAdapter
 *  org.eclipse.swt.events.ControlEvent
 *  org.eclipse.swt.events.ControlListener
 *  org.eclipse.swt.graphics.Point
 *  org.eclipse.swt.layout.FillLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Menu
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Table
 *  org.eclipse.swt.widgets.TableColumn
 *  org.eclipse.swt.widgets.Tree
 *  org.eclipse.swt.widgets.TreeColumn
 *  org.eclipse.ui.IActionBars
 *  org.eclipse.ui.IEditorInput
 *  org.eclipse.ui.IEditorPart
 *  org.eclipse.ui.IEditorSite
 *  org.eclipse.ui.IFileEditorInput
 *  org.eclipse.ui.IPartListener
 *  org.eclipse.ui.IWorkbenchPart
 *  org.eclipse.ui.IWorkbenchPartSite
 *  org.eclipse.ui.PartInitException
 *  org.eclipse.ui.actions.WorkspaceModifyOperation
 *  org.eclipse.ui.dialogs.SaveAsDialog
 *  org.eclipse.ui.ide.IGotoMarker
 *  org.eclipse.ui.part.FileEditorInput
 *  org.eclipse.ui.part.IPage
 *  org.eclipse.ui.part.MultiPageEditorPart
 *  org.eclipse.ui.views.contentoutline.ContentOutline
 *  org.eclipse.ui.views.contentoutline.ContentOutlinePage
 *  org.eclipse.ui.views.contentoutline.IContentOutlinePage
 *  org.eclipse.ui.views.properties.IPropertySheetPage
 *  org.eclipse.ui.views.properties.IPropertySourceProvider
 *  org.eclipse.ui.views.properties.PropertySheet
 *  org.eclipse.ui.views.properties.PropertySheetPage
 */
package com.csi.gmf.executionflow.presentation;

import com.csi.gmf.executionflow.presentation.ExecutionflowEditorPlugin;
import com.csi.gmf.executionflow.provider.ExecutionflowItemProviderAdapterFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.ui.ViewerPane;
import org.eclipse.emf.common.ui.editor.ProblemEditorPart;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.PropertySheetPage;

public class ExecutionflowEditor
extends MultiPageEditorPart
implements IEditingDomainProvider,
ISelectionProvider,
IMenuListener,
IViewerProvider,
IGotoMarker {
    protected AdapterFactoryEditingDomain editingDomain;
    protected ComposedAdapterFactory adapterFactory;
    protected IContentOutlinePage contentOutlinePage;
    protected IStatusLineManager contentOutlineStatusLineManager;
    protected TreeViewer contentOutlineViewer;
    protected PropertySheetPage propertySheetPage;
    protected TreeViewer selectionViewer;
    protected TreeViewer parentViewer;
    protected TreeViewer treeViewer;
    protected ListViewer listViewer;
    protected TableViewer tableViewer;
    protected TreeViewer treeViewerWithColumns;
    protected ViewerPane currentViewerPane;
    protected Viewer currentViewer;
    protected ISelectionChangedListener selectionChangedListener;
    protected Collection selectionChangedListeners = new ArrayList();
    protected ISelection editorSelection = StructuredSelection.EMPTY;
    protected MarkerHelper markerHelper = new EditUIMarkerHelper();
    protected IPartListener partListener = new IPartListener(){

        public void partActivated(IWorkbenchPart p) {
            if (p instanceof ContentOutline) {
                if (((ContentOutline)p).getCurrentPage() == ExecutionflowEditor.this.contentOutlinePage) {
                    ExecutionflowEditor.this.getActionBarContributor().setActiveEditor((IEditorPart)ExecutionflowEditor.this);
                    ExecutionflowEditor.this.setCurrentViewer((Viewer)ExecutionflowEditor.this.contentOutlineViewer);
                }
            } else if (p instanceof PropertySheet) {
                if (((PropertySheet)p).getCurrentPage() == ExecutionflowEditor.this.propertySheetPage) {
                    ExecutionflowEditor.this.getActionBarContributor().setActiveEditor((IEditorPart)ExecutionflowEditor.this);
                    ExecutionflowEditor.this.handleActivate();
                }
            } else if (p == ExecutionflowEditor.this) {
                ExecutionflowEditor.this.handleActivate();
            }
        }

        public void partBroughtToTop(IWorkbenchPart p) {
        }

        public void partClosed(IWorkbenchPart p) {
        }

        public void partDeactivated(IWorkbenchPart p) {
        }

        public void partOpened(IWorkbenchPart p) {
        }
    };
    protected Collection removedResources = new ArrayList();
    protected Collection changedResources = new ArrayList();
    protected Collection savedResources = new ArrayList();
    protected Map resourceToDiagnosticMap = new LinkedHashMap();
    protected boolean updateProblemIndication = true;
    protected EContentAdapter problemIndicationAdapter = new EContentAdapter(){

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public void notifyChanged(Notification notification) {
            if (notification.getNotifier() instanceof Resource) {
                switch (notification.getFeatureID(Resource.class)) {
                    case 4: 
                    case 6: 
                    case 7: {
                        Resource resource = (Resource)notification.getNotifier();
                        Diagnostic diagnostic = ExecutionflowEditor.this.analyzeResourceProblems(resource, null);
                        if (diagnostic.getSeverity() != 0) {
                            ExecutionflowEditor.this.resourceToDiagnosticMap.put(resource, diagnostic);
                        } else {
                            ExecutionflowEditor.this.resourceToDiagnosticMap.remove(resource);
                        }
                        if (!ExecutionflowEditor.this.updateProblemIndication) return;
                        ExecutionflowEditor.this.getSite().getShell().getDisplay().asyncExec(new Runnable(){

                            @Override
                            public void run() {
                                ExecutionflowEditor.this.updateProblemIndication();
                            }
                        });
                    }
                    default: {
                        return;
                    }
                }
            } else {
                super.notifyChanged(notification);
            }
        }

        protected void setTarget(Resource target) {
            this.basicSetTarget((Notifier)target);
        }

        protected void unsetTarget(Resource target) {
            this.basicUnsetTarget((Notifier)target);
        }
    };
    protected IResourceChangeListener resourceChangeListener = new IResourceChangeListener(){

        public void resourceChanged(IResourceChangeEvent event) {
            IResourceDelta delta = event.getDelta();
            try {
                class ResourceDeltaVisitor
                implements IResourceDeltaVisitor {
                    protected ResourceSet resourceSet;
                    protected Collection changedResources;
                    protected Collection removedResources;

                    ResourceDeltaVisitor() {
                        this.resourceSet = (this).ExecutionflowEditor.this.editingDomain.getResourceSet();
                        this.changedResources = new ArrayList();
                        this.removedResources = new ArrayList();
                    }

                    public boolean visit(IResourceDelta delta) {
                        Resource resource;
                        if (delta.getResource().getType() == 1 && (delta.getKind() == 2 || delta.getKind() == 4 && delta.getFlags() != 131072) && (resource = this.resourceSet.getResource(URI.createURI((String)delta.getFullPath().toString()), false)) != null) {
                            if (delta.getKind() == 2) {
                                this.removedResources.add(resource);
                            } else if (!(this).ExecutionflowEditor.this.savedResources.remove(resource)) {
                                this.changedResources.add(resource);
                            }
                        }
                        return true;
                    }

                    public Collection getChangedResources() {
                        return this.changedResources;
                    }

                    public Collection getRemovedResources() {
                        return this.removedResources;
                    }
                }
                ResourceDeltaVisitor visitor = new ResourceDeltaVisitor();
                delta.accept((IResourceDeltaVisitor)visitor);
                if (!visitor.getRemovedResources().isEmpty()) {
                    ExecutionflowEditor.this.removedResources.addAll(visitor.getRemovedResources());
                    if (!ExecutionflowEditor.this.isDirty()) {
                        ExecutionflowEditor.this.getSite().getShell().getDisplay().asyncExec(new Runnable(){

                            @Override
                            public void run() {
                                ExecutionflowEditor.this.getSite().getPage().closeEditor((IEditorPart)ExecutionflowEditor.this, false);
                            }
                        });
                    }
                }
                if (!visitor.getChangedResources().isEmpty()) {
                    ExecutionflowEditor.this.changedResources.addAll(visitor.getChangedResources());
                    if (ExecutionflowEditor.this.getSite().getPage().getActiveEditor() == ExecutionflowEditor.this) {
                        ExecutionflowEditor.this.getSite().getShell().getDisplay().asyncExec(new Runnable(){

                            @Override
                            public void run() {
                                ExecutionflowEditor.this.handleActivate();
                            }
                        });
                    }
                }
            }
            catch (CoreException exception) {
                ExecutionflowEditorPlugin.INSTANCE.log((Object)exception);
            }
        }
    };

    protected void handleActivate() {
        if (this.editingDomain.getResourceToReadOnlyMap() != null) {
            this.editingDomain.getResourceToReadOnlyMap().clear();
            this.setSelection(this.getSelection());
        }
        if (!this.removedResources.isEmpty()) {
            if (this.handleDirtyConflict()) {
                this.getSite().getPage().closeEditor((IEditorPart)this, false);
            } else {
                this.removedResources.clear();
                this.changedResources.clear();
                this.savedResources.clear();
            }
        } else if (!this.changedResources.isEmpty()) {
            this.changedResources.removeAll(this.savedResources);
            this.handleChangedResources();
            this.changedResources.clear();
            this.savedResources.clear();
        }
    }

    protected void handleChangedResources() {
        if (!(this.changedResources.isEmpty() || this.isDirty() && !this.handleDirtyConflict())) {
            if (this.isDirty()) {
                this.changedResources.addAll(this.editingDomain.getResourceSet().getResources());
            }
            this.editingDomain.getCommandStack().flush();
            this.updateProblemIndication = false;
            for (Resource resource : this.changedResources) {
                if (!resource.isLoaded()) continue;
                resource.unload();
                try {
                    resource.load(Collections.EMPTY_MAP);
                }
                catch (IOException exception) {
                    if (this.resourceToDiagnosticMap.containsKey(resource)) continue;
                    this.resourceToDiagnosticMap.put(resource, this.analyzeResourceProblems(resource, exception));
                }
            }
            if (AdapterFactoryEditingDomain.isStale((Object)this.editorSelection)) {
                this.setSelection((ISelection)StructuredSelection.EMPTY);
            }
            this.updateProblemIndication = true;
            this.updateProblemIndication();
        }
    }

    protected void updateProblemIndication() {
        if (this.updateProblemIndication) {
            BasicDiagnostic diagnostic = new BasicDiagnostic(0, "com.csi.gmf.executionflow.editor", 0, null, new Object[]{this.editingDomain.getResourceSet()});
            for (Diagnostic childDiagnostic : this.resourceToDiagnosticMap.values()) {
                if (childDiagnostic.getSeverity() == 0) continue;
                diagnostic.add(childDiagnostic);
            }
            int lastEditorPage = this.getPageCount() - 1;
            if (lastEditorPage >= 0 && this.getEditor(lastEditorPage) instanceof ProblemEditorPart) {
                ((ProblemEditorPart)this.getEditor(lastEditorPage)).setDiagnostic((Diagnostic)diagnostic);
                if (diagnostic.getSeverity() != 0) {
                    this.setActivePage(lastEditorPage);
                }
            } else if (diagnostic.getSeverity() != 0) {
                ProblemEditorPart problemEditorPart = new ProblemEditorPart();
                problemEditorPart.setDiagnostic((Diagnostic)diagnostic);
                problemEditorPart.setMarkerHelper(this.markerHelper);
                try {
                    this.addPage(++lastEditorPage, (IEditorPart)problemEditorPart, this.getEditorInput());
                    this.setPageText(lastEditorPage, problemEditorPart.getPartName());
                    this.setActivePage(lastEditorPage);
                    this.showTabs();
                }
                catch (PartInitException exception) {
                    ExecutionflowEditorPlugin.INSTANCE.log((Object)exception);
                }
            }
            if (this.markerHelper.hasMarkers((Object)this.editingDomain.getResourceSet())) {
                this.markerHelper.deleteMarkers((Object)this.editingDomain.getResourceSet());
                if (diagnostic.getSeverity() != 0) {
                    try {
                        this.markerHelper.createMarkers((Diagnostic)diagnostic);
                    }
                    catch (CoreException exception) {
                        ExecutionflowEditorPlugin.INSTANCE.log((Object)exception);
                    }
                }
            }
        }
    }

    protected boolean handleDirtyConflict() {
        return MessageDialog.openQuestion((Shell)this.getSite().getShell(), (String)ExecutionflowEditor.getString("_UI_FileConflict_label"), (String)ExecutionflowEditor.getString("_WARN_FileConflict"));
    }

    public ExecutionflowEditor() {
        this.initializeEditingDomain();
    }

    protected void initializeEditingDomain() {
        this.adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        this.adapterFactory.addAdapterFactory((AdapterFactory)new ResourceItemProviderAdapterFactory());
        this.adapterFactory.addAdapterFactory((AdapterFactory)new ExecutionflowItemProviderAdapterFactory());
        this.adapterFactory.addAdapterFactory((AdapterFactory)new ReflectiveItemProviderAdapterFactory());
        BasicCommandStack commandStack = new BasicCommandStack();
        commandStack.addCommandStackListener(new CommandStackListener(){

            public void commandStackChanged(final EventObject event) {
                ExecutionflowEditor.this.getContainer().getDisplay().asyncExec(new Runnable(){

                    @Override
                    public void run() {
                        ExecutionflowEditor.this.firePropertyChange(257);
                        Command mostRecentCommand = ((CommandStack)event.getSource()).getMostRecentCommand();
                        if (mostRecentCommand != null) {
                            ExecutionflowEditor.this.setSelectionToViewer(mostRecentCommand.getAffectedObjects());
                        }
                        if ((this).ExecutionflowEditor.this.propertySheetPage != null && !(this).ExecutionflowEditor.this.propertySheetPage.getControl().isDisposed()) {
                            (this).ExecutionflowEditor.this.propertySheetPage.refresh();
                        }
                    }
                });
            }
        });
        this.editingDomain = new AdapterFactoryEditingDomain((AdapterFactory)this.adapterFactory, (CommandStack)commandStack, new HashMap());
    }

    protected void firePropertyChange(int action) {
        super.firePropertyChange(action);
    }

    public void setSelectionToViewer(Collection collection) {
        final Collection theSelection = collection;
        if (theSelection != null && !theSelection.isEmpty()) {
            Runnable runnable = new Runnable(){

                @Override
                public void run() {
                    if (ExecutionflowEditor.this.currentViewer != null) {
                        ExecutionflowEditor.this.currentViewer.setSelection((ISelection)new StructuredSelection(theSelection.toArray()), true);
                    }
                }
            };
            runnable.run();
        }
    }

    public EditingDomain getEditingDomain() {
        return this.editingDomain;
    }

    public void setCurrentViewerPane(ViewerPane viewerPane) {
        if (this.currentViewerPane != viewerPane) {
            if (this.currentViewerPane != null) {
                this.currentViewerPane.showFocus(false);
            }
            this.currentViewerPane = viewerPane;
        }
        this.setCurrentViewer(this.currentViewerPane.getViewer());
    }

    public void setCurrentViewer(Viewer viewer) {
        if (this.currentViewer != viewer) {
            if (this.selectionChangedListener == null) {
                this.selectionChangedListener = new ISelectionChangedListener(){

                    public void selectionChanged(SelectionChangedEvent selectionChangedEvent) {
                        ExecutionflowEditor.this.setSelection(selectionChangedEvent.getSelection());
                    }
                };
            }
            if (this.currentViewer != null) {
                this.currentViewer.removeSelectionChangedListener(this.selectionChangedListener);
            }
            if (viewer != null) {
                viewer.addSelectionChangedListener(this.selectionChangedListener);
            }
            this.currentViewer = viewer;
            this.setSelection((ISelection)(this.currentViewer == null ? StructuredSelection.EMPTY : this.currentViewer.getSelection()));
        }
    }

    public Viewer getViewer() {
        return this.currentViewer;
    }

    protected void createContextMenuFor(StructuredViewer viewer) {
        MenuManager contextMenu = new MenuManager("#PopUp");
        contextMenu.add((IContributionItem)new Separator("additions"));
        contextMenu.setRemoveAllWhenShown(true);
        contextMenu.addMenuListener((IMenuListener)this);
        Menu menu = contextMenu.createContextMenu(viewer.getControl());
        viewer.getControl().setMenu(menu);
        this.getSite().registerContextMenu(contextMenu, (ISelectionProvider)new UnwrappingSelectionProvider((ISelectionProvider)viewer));
        int dndOperations = 7;
        Transfer[] transfers = new Transfer[]{LocalTransfer.getInstance()};
        viewer.addDragSupport(dndOperations, transfers, (DragSourceListener)new ViewerDragAdapter((Viewer)viewer));
        viewer.addDropSupport(dndOperations, transfers, (DropTargetListener)new EditingDomainViewerDropAdapter((EditingDomain)this.editingDomain, (Viewer)viewer));
    }

    public void createModel() {
        IFileEditorInput modelFile = (IFileEditorInput)this.getEditorInput();
        URI resourceURI = URI.createPlatformResourceURI((String)modelFile.getFile().getFullPath().toString(), (boolean)true);
        Exception exception = null;
        Resource resource = null;
        try {
            resource = this.editingDomain.getResourceSet().getResource(resourceURI, true);
        }
        catch (Exception e) {
            exception = e;
            resource = this.editingDomain.getResourceSet().getResource(resourceURI, false);
        }
        Diagnostic diagnostic = this.analyzeResourceProblems(resource, exception);
        if (diagnostic.getSeverity() != 0) {
            this.resourceToDiagnosticMap.put(resource, this.analyzeResourceProblems(resource, exception));
        }
        this.editingDomain.getResourceSet().eAdapters().add((Object)this.problemIndicationAdapter);
    }

    public Diagnostic analyzeResourceProblems(Resource resource, Exception exception) {
        if (!resource.getErrors().isEmpty() || !resource.getWarnings().isEmpty()) {
            BasicDiagnostic basicDiagnostic = new BasicDiagnostic(4, "com.csi.gmf.executionflow.editor", 0, ExecutionflowEditor.getString("_UI_CreateModelError_message", resource.getURI()), new Object[]{exception == null ? resource : exception});
            basicDiagnostic.merge(EcoreUtil.computeDiagnostic((Resource)resource, (boolean)true));
            return basicDiagnostic;
        }
        if (exception != null) {
            return new BasicDiagnostic(4, "com.csi.gmf.executionflow.editor", 0, ExecutionflowEditor.getString("_UI_CreateModelError_message", resource.getURI()), new Object[]{exception});
        }
        return Diagnostic.OK_INSTANCE;
    }

    public void createPages() {
        this.createModel();
        if (!this.getEditingDomain().getResourceSet().getResources().isEmpty()) {
            ViewerPane viewerPane = new ViewerPane(this.getSite().getPage(), (IWorkbenchPart)this){

                public Viewer createViewer(Composite composite) {
                    Tree tree = new Tree(composite, 2);
                    TreeViewer newTreeViewer = new TreeViewer(tree);
                    return newTreeViewer;
                }

                public void requestActivation() {
                    super.requestActivation();
                    ExecutionflowEditor.this.setCurrentViewerPane(this);
                }
            };
            viewerPane.createControl(this.getContainer());
            this.selectionViewer = (TreeViewer)viewerPane.getViewer();
            this.selectionViewer.setContentProvider((IContentProvider)new AdapterFactoryContentProvider((AdapterFactory)this.adapterFactory));
            this.selectionViewer.setLabelProvider((IBaseLabelProvider)new AdapterFactoryLabelProvider((AdapterFactory)this.adapterFactory));
            this.selectionViewer.setInput((Object)this.editingDomain.getResourceSet());
            this.selectionViewer.setSelection((ISelection)new StructuredSelection(this.editingDomain.getResourceSet().getResources().get(0)), true);
            viewerPane.setTitle((Object)this.editingDomain.getResourceSet());
            new AdapterFactoryTreeEditor(this.selectionViewer.getTree(), (AdapterFactory)this.adapterFactory);
            this.createContextMenuFor((StructuredViewer)this.selectionViewer);
            int pageIndex = this.addPage(viewerPane.getControl());
            this.setPageText(pageIndex, ExecutionflowEditor.getString("_UI_SelectionPage_label"));
            viewerPane = new ViewerPane(this.getSite().getPage(), (IWorkbenchPart)this){

                public Viewer createViewer(Composite composite) {
                    Tree tree = new Tree(composite, 2);
                    TreeViewer newTreeViewer = new TreeViewer(tree);
                    return newTreeViewer;
                }

                public void requestActivation() {
                    super.requestActivation();
                    ExecutionflowEditor.this.setCurrentViewerPane(this);
                }
            };
            viewerPane.createControl(this.getContainer());
            this.parentViewer = (TreeViewer)viewerPane.getViewer();
            this.parentViewer.setAutoExpandLevel(30);
            this.parentViewer.setContentProvider((IContentProvider)new ReverseAdapterFactoryContentProvider((AdapterFactory)this.adapterFactory));
            this.parentViewer.setLabelProvider((IBaseLabelProvider)new AdapterFactoryLabelProvider((AdapterFactory)this.adapterFactory));
            this.createContextMenuFor((StructuredViewer)this.parentViewer);
            pageIndex = this.addPage(viewerPane.getControl());
            this.setPageText(pageIndex, ExecutionflowEditor.getString("_UI_ParentPage_label"));
            viewerPane = new ViewerPane(this.getSite().getPage(), (IWorkbenchPart)this){

                public Viewer createViewer(Composite composite) {
                    return new ListViewer(composite);
                }

                public void requestActivation() {
                    super.requestActivation();
                    ExecutionflowEditor.this.setCurrentViewerPane(this);
                }
            };
            viewerPane.createControl(this.getContainer());
            this.listViewer = (ListViewer)viewerPane.getViewer();
            this.listViewer.setContentProvider((IContentProvider)new AdapterFactoryContentProvider((AdapterFactory)this.adapterFactory));
            this.listViewer.setLabelProvider((IBaseLabelProvider)new AdapterFactoryLabelProvider((AdapterFactory)this.adapterFactory));
            this.createContextMenuFor((StructuredViewer)this.listViewer);
            pageIndex = this.addPage(viewerPane.getControl());
            this.setPageText(pageIndex, ExecutionflowEditor.getString("_UI_ListPage_label"));
            viewerPane = new ViewerPane(this.getSite().getPage(), (IWorkbenchPart)this){

                public Viewer createViewer(Composite composite) {
                    return new TreeViewer(composite);
                }

                public void requestActivation() {
                    super.requestActivation();
                    ExecutionflowEditor.this.setCurrentViewerPane(this);
                }
            };
            viewerPane.createControl(this.getContainer());
            this.treeViewer = (TreeViewer)viewerPane.getViewer();
            this.treeViewer.setContentProvider((IContentProvider)new AdapterFactoryContentProvider((AdapterFactory)this.adapterFactory));
            this.treeViewer.setLabelProvider((IBaseLabelProvider)new AdapterFactoryLabelProvider((AdapterFactory)this.adapterFactory));
            new AdapterFactoryTreeEditor(this.treeViewer.getTree(), (AdapterFactory)this.adapterFactory);
            this.createContextMenuFor((StructuredViewer)this.treeViewer);
            pageIndex = this.addPage(viewerPane.getControl());
            this.setPageText(pageIndex, ExecutionflowEditor.getString("_UI_TreePage_label"));
            viewerPane = new ViewerPane(this.getSite().getPage(), (IWorkbenchPart)this){

                public Viewer createViewer(Composite composite) {
                    return new TableViewer(composite);
                }

                public void requestActivation() {
                    super.requestActivation();
                    ExecutionflowEditor.this.setCurrentViewerPane(this);
                }
            };
            viewerPane.createControl(this.getContainer());
            this.tableViewer = (TableViewer)viewerPane.getViewer();
            Table table = this.tableViewer.getTable();
            TableLayout layout = new TableLayout();
            table.setLayout((Layout)layout);
            table.setHeaderVisible(true);
            table.setLinesVisible(true);
            TableColumn objectColumn = new TableColumn(table, 0);
            layout.addColumnData((ColumnLayoutData)new ColumnWeightData(3, 100, true));
            objectColumn.setText(ExecutionflowEditor.getString("_UI_ObjectColumn_label"));
            objectColumn.setResizable(true);
            TableColumn selfColumn = new TableColumn(table, 0);
            layout.addColumnData((ColumnLayoutData)new ColumnWeightData(2, 100, true));
            selfColumn.setText(ExecutionflowEditor.getString("_UI_SelfColumn_label"));
            selfColumn.setResizable(true);
            this.tableViewer.setColumnProperties(new String[]{"a", "b"});
            this.tableViewer.setContentProvider((IContentProvider)new AdapterFactoryContentProvider((AdapterFactory)this.adapterFactory));
            this.tableViewer.setLabelProvider((IBaseLabelProvider)new AdapterFactoryLabelProvider((AdapterFactory)this.adapterFactory));
            this.createContextMenuFor((StructuredViewer)this.tableViewer);
            int pageIndex2 = this.addPage(viewerPane.getControl());
            this.setPageText(pageIndex2, ExecutionflowEditor.getString("_UI_TablePage_label"));
            viewerPane = new ViewerPane(this.getSite().getPage(), (IWorkbenchPart)this){

                public Viewer createViewer(Composite composite) {
                    return new TreeViewer(composite);
                }

                public void requestActivation() {
                    super.requestActivation();
                    ExecutionflowEditor.this.setCurrentViewerPane(this);
                }
            };
            viewerPane.createControl(this.getContainer());
            this.treeViewerWithColumns = (TreeViewer)viewerPane.getViewer();
            Tree tree = this.treeViewerWithColumns.getTree();
            tree.setLayoutData((Object)new FillLayout());
            tree.setHeaderVisible(true);
            tree.setLinesVisible(true);
            TreeColumn objectColumn2 = new TreeColumn(tree, 0);
            objectColumn2.setText(ExecutionflowEditor.getString("_UI_ObjectColumn_label"));
            objectColumn2.setResizable(true);
            objectColumn2.setWidth(250);
            TreeColumn selfColumn2 = new TreeColumn(tree, 0);
            selfColumn2.setText(ExecutionflowEditor.getString("_UI_SelfColumn_label"));
            selfColumn2.setResizable(true);
            selfColumn2.setWidth(200);
            this.treeViewerWithColumns.setColumnProperties(new String[]{"a", "b"});
            this.treeViewerWithColumns.setContentProvider((IContentProvider)new AdapterFactoryContentProvider((AdapterFactory)this.adapterFactory));
            this.treeViewerWithColumns.setLabelProvider((IBaseLabelProvider)new AdapterFactoryLabelProvider((AdapterFactory)this.adapterFactory));
            this.createContextMenuFor((StructuredViewer)this.treeViewerWithColumns);
            int pageIndex3 = this.addPage(viewerPane.getControl());
            this.setPageText(pageIndex3, ExecutionflowEditor.getString("_UI_TreeWithColumnsPage_label"));
            this.getSite().getShell().getDisplay().asyncExec(new Runnable(){

                @Override
                public void run() {
                    ExecutionflowEditor.this.setActivePage(0);
                }
            });
        }
        this.getContainer().addControlListener((ControlListener)new ControlAdapter(){
            boolean guard = false;

            public void controlResized(ControlEvent event) {
                if (!this.guard) {
                    this.guard = true;
                    ExecutionflowEditor.this.hideTabs();
                    this.guard = false;
                }
            }
        });
        this.getSite().getShell().getDisplay().asyncExec(new Runnable(){

            @Override
            public void run() {
                ExecutionflowEditor.this.updateProblemIndication();
            }
        });
    }

    protected void hideTabs() {
        if (this.getPageCount() <= 1) {
            this.setPageText(0, "");
            if (this.getContainer() instanceof CTabFolder) {
                ((CTabFolder)this.getContainer()).setTabHeight(1);
                Point point = this.getContainer().getSize();
                this.getContainer().setSize(point.x, point.y + 6);
            }
        }
    }

    protected void showTabs() {
        if (this.getPageCount() > 1) {
            this.setPageText(0, ExecutionflowEditor.getString("_UI_SelectionPage_label"));
            if (this.getContainer() instanceof CTabFolder) {
                ((CTabFolder)this.getContainer()).setTabHeight(-1);
                Point point = this.getContainer().getSize();
                this.getContainer().setSize(point.x, point.y - 6);
            }
        }
    }

    protected void pageChange(int pageIndex) {
        super.pageChange(pageIndex);
        if (this.contentOutlinePage != null) {
            this.handleContentOutlineSelection(this.contentOutlinePage.getSelection());
        }
    }

    public Object getAdapter(Class key) {
        if (key.equals(IContentOutlinePage.class)) {
            return this.showOutlineView() ? this.getContentOutlinePage() : null;
        }
        if (key.equals(IPropertySheetPage.class)) {
            return this.getPropertySheetPage();
        }
        if (key.equals(IGotoMarker.class)) {
            return this;
        }
        return super.getAdapter(key);
    }

    public IContentOutlinePage getContentOutlinePage() {
        if (this.contentOutlinePage == null) {
            class MyContentOutlinePage
            extends ContentOutlinePage {
                MyContentOutlinePage() {
                }

                public void createControl(Composite parent) {
                    super.createControl(parent);
                    ExecutionflowEditor.this.contentOutlineViewer = this.getTreeViewer();
                    ExecutionflowEditor.this.contentOutlineViewer.addSelectionChangedListener((ISelectionChangedListener)this);
                    ExecutionflowEditor.this.contentOutlineViewer.setContentProvider((IContentProvider)new AdapterFactoryContentProvider((AdapterFactory)ExecutionflowEditor.this.adapterFactory));
                    ExecutionflowEditor.this.contentOutlineViewer.setLabelProvider((IBaseLabelProvider)new AdapterFactoryLabelProvider((AdapterFactory)ExecutionflowEditor.this.adapterFactory));
                    ExecutionflowEditor.this.contentOutlineViewer.setInput((Object)ExecutionflowEditor.this.editingDomain.getResourceSet());
                    ExecutionflowEditor.this.createContextMenuFor((StructuredViewer)ExecutionflowEditor.this.contentOutlineViewer);
                    if (!ExecutionflowEditor.this.editingDomain.getResourceSet().getResources().isEmpty()) {
                        ExecutionflowEditor.this.contentOutlineViewer.setSelection((ISelection)new StructuredSelection(ExecutionflowEditor.this.editingDomain.getResourceSet().getResources().get(0)), true);
                    }
                }

                public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager) {
                    super.makeContributions(menuManager, toolBarManager, statusLineManager);
                    ExecutionflowEditor.this.contentOutlineStatusLineManager = statusLineManager;
                }

                public void setActionBars(IActionBars actionBars) {
                    super.setActionBars(actionBars);
                    ExecutionflowEditor.this.getActionBarContributor().shareGlobalActions((IPage)this, actionBars);
                }
            }
            this.contentOutlinePage = new MyContentOutlinePage();
            this.contentOutlinePage.addSelectionChangedListener(new ISelectionChangedListener(){

                public void selectionChanged(SelectionChangedEvent event) {
                    ExecutionflowEditor.this.handleContentOutlineSelection(event.getSelection());
                }
            });
        }
        return this.contentOutlinePage;
    }

    public IPropertySheetPage getPropertySheetPage() {
        if (this.propertySheetPage == null) {
            this.propertySheetPage = new ExtendedPropertySheetPage(this.editingDomain){

                public void setSelectionToViewer(List selection) {
                    ExecutionflowEditor.this.setSelectionToViewer(selection);
                    ExecutionflowEditor.this.setFocus();
                }

                public void setActionBars(IActionBars actionBars) {
                    super.setActionBars(actionBars);
                    ExecutionflowEditor.this.getActionBarContributor().shareGlobalActions((IPage)this, actionBars);
                }
            };
            this.propertySheetPage.setPropertySourceProvider((IPropertySourceProvider)new AdapterFactoryContentProvider((AdapterFactory)this.adapterFactory));
        }
        return this.propertySheetPage;
    }

    public void handleContentOutlineSelection(ISelection selection) {
        Iterator selectedElements;
        if (this.currentViewerPane != null && !selection.isEmpty() && selection instanceof IStructuredSelection && (selectedElements = ((IStructuredSelection)selection).iterator()).hasNext()) {
            Object selectedElement = selectedElements.next();
            if (this.currentViewerPane.getViewer() == this.selectionViewer) {
                ArrayList selectionList = new ArrayList();
                selectionList.add(selectedElement);
                while (selectedElements.hasNext()) {
                    selectionList.add(selectedElements.next());
                }
                this.selectionViewer.setSelection((ISelection)new StructuredSelection(selectionList));
            } else if (this.currentViewerPane.getViewer().getInput() != selectedElement) {
                this.currentViewerPane.getViewer().setInput(selectedElement);
                this.currentViewerPane.setTitle(selectedElement);
            }
        }
    }

    public boolean isDirty() {
        return ((BasicCommandStack)this.editingDomain.getCommandStack()).isSaveNeeded();
    }

    public void doSave(IProgressMonitor progressMonitor) {
        final HashMap<String, String> saveOptions = new HashMap<String, String>();
        saveOptions.put("SAVE_ONLY_IF_CHANGED", "MEMORY_BUFFER");
        WorkspaceModifyOperation operation = new WorkspaceModifyOperation(){

            public void execute(IProgressMonitor monitor) {
                boolean first = true;
                for (Resource resource : ExecutionflowEditor.this.editingDomain.getResourceSet().getResources()) {
                    if (!first && resource.getContents().isEmpty() && !ExecutionflowEditor.this.isPersisted(resource) || ExecutionflowEditor.this.editingDomain.isReadOnly(resource)) continue;
                    try {
                        long timeStamp = resource.getTimeStamp();
                        resource.save(saveOptions);
                        if (resource.getTimeStamp() != timeStamp) {
                            ExecutionflowEditor.this.savedResources.add(resource);
                        }
                    }
                    catch (Exception exception) {
                        ExecutionflowEditor.this.resourceToDiagnosticMap.put(resource, ExecutionflowEditor.this.analyzeResourceProblems(resource, exception));
                    }
                    first = false;
                }
            }
        };
        this.updateProblemIndication = false;
        try {
            new ProgressMonitorDialog(this.getSite().getShell()).run(true, false, (IRunnableWithProgress)operation);
            ((BasicCommandStack)this.editingDomain.getCommandStack()).saveIsDone();
            this.firePropertyChange(257);
        }
        catch (Exception exception) {
            ExecutionflowEditorPlugin.INSTANCE.log(exception);
        }
        this.updateProblemIndication = true;
        this.updateProblemIndication();
    }

    protected boolean isPersisted(Resource resource) {
        boolean result = false;
        try {
            InputStream stream = this.editingDomain.getResourceSet().getURIConverter().createInputStream(resource.getURI());
            if (stream != null) {
                result = true;
                stream.close();
            }
        }
        catch (IOException iOException) {}
        return result;
    }

    public boolean isSaveAsAllowed() {
        return true;
    }

    public void doSaveAs() {
        IFile file;
        SaveAsDialog saveAsDialog = new SaveAsDialog(this.getSite().getShell());
        saveAsDialog.open();
        IPath path = saveAsDialog.getResult();
        if (path != null && (file = ResourcesPlugin.getWorkspace().getRoot().getFile(path)) != null) {
            this.doSaveAs(URI.createPlatformResourceURI((String)file.getFullPath().toString(), (boolean)true), (IEditorInput)new FileEditorInput(file));
        }
    }

    protected void doSaveAs(URI uri, IEditorInput editorInput) {
        ((Resource)this.editingDomain.getResourceSet().getResources().get(0)).setURI(uri);
        this.setInputWithNotify(editorInput);
        this.setPartName(editorInput.getName());
        NullProgressMonitor progressMonitor = this.getActionBars().getStatusLineManager() != null ? this.getActionBars().getStatusLineManager().getProgressMonitor() : new NullProgressMonitor();
        this.doSave((IProgressMonitor)progressMonitor);
    }

    public void gotoMarker(IMarker marker) {
        try {
            String uriAttribute;
            if (marker.getType().equals("org.eclipse.emf.ecore.diagnostic") && (uriAttribute = marker.getAttribute("uri", null)) != null) {
                URI uri = URI.createURI((String)uriAttribute);
                EObject eObject = this.editingDomain.getResourceSet().getEObject(uri, true);
                if (eObject != null) {
                    this.setSelectionToViewer(Collections.singleton(this.editingDomain.getWrapper((Object)eObject)));
                }
            }
        }
        catch (CoreException exception) {
            ExecutionflowEditorPlugin.INSTANCE.log((Object)exception);
        }
    }

    public void init(IEditorSite site, IEditorInput editorInput) {
        this.setSite((IWorkbenchPartSite)site);
        this.setInputWithNotify(editorInput);
        this.setPartName(editorInput.getName());
        site.setSelectionProvider((ISelectionProvider)this);
        site.getPage().addPartListener(this.partListener);
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this.resourceChangeListener, 1);
    }

    public void setFocus() {
        if (this.currentViewerPane != null) {
            this.currentViewerPane.setFocus();
        } else {
            this.getControl(this.getActivePage()).setFocus();
        }
    }

    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        this.selectionChangedListeners.add(listener);
    }

    public void removeSelectionChangedListener(ISelectionChangedListener listener) {
        this.selectionChangedListeners.remove(listener);
    }

    public ISelection getSelection() {
        return this.editorSelection;
    }

    public void setSelection(ISelection selection) {
        this.editorSelection = selection;
        for (ISelectionChangedListener listener : this.selectionChangedListeners) {
            listener.selectionChanged(new SelectionChangedEvent((ISelectionProvider)this, selection));
        }
        this.setStatusLineManager(selection);
    }

    public void setStatusLineManager(ISelection selection) {
        IStatusLineManager statusLineManager;
        IStatusLineManager iStatusLineManager = statusLineManager = this.currentViewer != null && this.currentViewer == this.contentOutlineViewer ? this.contentOutlineStatusLineManager : this.getActionBars().getStatusLineManager();
        if (statusLineManager != null) {
            if (selection instanceof IStructuredSelection) {
                List collection = ((IStructuredSelection)selection).toList();
                switch (collection.size()) {
                    case 0: {
                        statusLineManager.setMessage(ExecutionflowEditor.getString("_UI_NoObjectSelected"));
                        break;
                    }
                    case 1: {
                        String text = new AdapterFactoryItemDelegator((AdapterFactory)this.adapterFactory).getText(collection.iterator().next());
                        statusLineManager.setMessage(ExecutionflowEditor.getString("_UI_SingleObjectSelected", text));
                        break;
                    }
                    default: {
                        statusLineManager.setMessage(ExecutionflowEditor.getString("_UI_MultiObjectSelected", Integer.toString(collection.size())));
                        break;
                    }
                }
            } else {
                statusLineManager.setMessage("");
            }
        }
    }

    private static String getString(String key) {
        return ExecutionflowEditorPlugin.INSTANCE.getString(key);
    }

    private static String getString(String key, Object s1) {
        return ExecutionflowEditorPlugin.INSTANCE.getString(key, new Object[]{s1});
    }

    public void menuAboutToShow(IMenuManager menuManager) {
        ((IMenuListener)this.getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
    }

    public EditingDomainActionBarContributor getActionBarContributor() {
        return (EditingDomainActionBarContributor)this.getEditorSite().getActionBarContributor();
    }

    public IActionBars getActionBars() {
        return this.getActionBarContributor().getActionBars();
    }

    public AdapterFactory getAdapterFactory() {
        return this.adapterFactory;
    }

    public void dispose() {
        this.updateProblemIndication = false;
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this.resourceChangeListener);
        this.getSite().getPage().removePartListener(this.partListener);
        this.adapterFactory.dispose();
        if (this.getActionBarContributor().getActiveEditor() == this) {
            this.getActionBarContributor().setActiveEditor(null);
        }
        if (this.propertySheetPage != null) {
            this.propertySheetPage.dispose();
        }
        if (this.contentOutlinePage != null) {
            this.contentOutlinePage.dispose();
        }
        super.dispose();
    }

    protected boolean showOutlineView() {
        return true;
    }

    public class ReverseAdapterFactoryContentProvider
    extends AdapterFactoryContentProvider {
        public ReverseAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        public Object[] getElements(Object object) {
            Object parent = super.getParent(object);
            return (parent == null ? Collections.EMPTY_SET : Collections.singleton(parent)).toArray();
        }

        public Object[] getChildren(Object object) {
            Object parent = super.getParent(object);
            return (parent == null ? Collections.EMPTY_SET : Collections.singleton(parent)).toArray();
        }

        public boolean hasChildren(Object object) {
            Object parent = super.getParent(object);
            return parent != null;
        }

        public Object getParent(Object object) {
            return null;
        }
    }
}

