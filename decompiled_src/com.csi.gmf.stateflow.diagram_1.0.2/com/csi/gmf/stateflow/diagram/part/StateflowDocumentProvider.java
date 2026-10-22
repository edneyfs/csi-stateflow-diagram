/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.commands.ExecutionException
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IResource
 *  org.eclipse.core.resources.IStorage
 *  org.eclipse.core.resources.ResourcesPlugin
 *  org.eclipse.core.runtime.CoreException
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.core.runtime.IStatus
 *  org.eclipse.core.runtime.Path
 *  org.eclipse.core.runtime.Status
 *  org.eclipse.core.runtime.jobs.ISchedulingRule
 *  org.eclipse.core.runtime.jobs.MultiRule
 *  org.eclipse.emf.common.notify.Adapter
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.common.notify.Notifier
 *  org.eclipse.emf.common.ui.URIEditorInput
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.ecore.resource.ResourceSet
 *  org.eclipse.emf.ecore.util.EContentAdapter
 *  org.eclipse.emf.ecore.util.EcoreUtil
 *  org.eclipse.emf.transaction.NotificationFilter
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer$Delegate
 *  org.eclipse.gmf.runtime.common.core.command.CommandResult
 *  org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider$ElementInfo
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramDocument
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocumentProvider
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.DiagramIOUtil
 *  org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand
 *  org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory
 *  org.eclipse.gmf.runtime.notation.Diagram
 *  org.eclipse.jface.operation.IRunnableContext
 *  org.eclipse.osgi.util.NLS
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.ui.IEditorInput
 *  org.eclipse.ui.part.FileEditorInput
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.part.Messages;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.DiagramIOUtil;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

public class StateflowDocumentProvider
extends AbstractDocumentProvider
implements IDiagramDocumentProvider {
    protected AbstractDocumentProvider.ElementInfo createElementInfo(Object element) throws CoreException {
        if (!(element instanceof FileEditorInput) && !(element instanceof URIEditorInput)) {
            throw new CoreException((IStatus)new Status(4, "com.csi.gmf.stateflow.diagram", 0, NLS.bind((String)Messages.StateflowDocumentProvider_IncorrectInputError, (Object[])new Object[]{element, "org.eclipse.ui.part.FileEditorInput", "org.eclipse.emf.common.ui.URIEditorInput"}), null));
        }
        IEditorInput editorInput = (IEditorInput)element;
        IDiagramDocument document = (IDiagramDocument)this.createDocument(editorInput);
        ResourceSetInfo info = new ResourceSetInfo(document, editorInput);
        info.setModificationStamp(this.computeModificationStamp(info));
        info.fStatus = null;
        return info;
    }

    protected IDocument createDocument(Object element) throws CoreException {
        if (!(element instanceof FileEditorInput) && !(element instanceof URIEditorInput)) {
            throw new CoreException((IStatus)new Status(4, "com.csi.gmf.stateflow.diagram", 0, NLS.bind((String)Messages.StateflowDocumentProvider_IncorrectInputError, (Object[])new Object[]{element, "org.eclipse.ui.part.FileEditorInput", "org.eclipse.emf.common.ui.URIEditorInput"}), null));
        }
        IDocument document = this.createEmptyDocument();
        this.setDocumentContent(document, (IEditorInput)element);
        this.setupDocument(element, document);
        return document;
    }

    protected void setupDocument(Object element, IDocument document) {
    }

    private long computeModificationStamp(ResourceSetInfo info) {
        int result = 0;
        Iterator it = info.getLoadedResourcesIterator();
        while (it.hasNext()) {
            Resource nextResource = (Resource)it.next();
            IFile file = WorkspaceSynchronizer.getFile((Resource)nextResource);
            if (file == null) continue;
            result = file.getLocation() != null ? (int)((long)result + file.getLocation().toFile().lastModified()) : (int)((long)result + file.getModificationStamp());
        }
        return result;
    }

    protected IDocument createEmptyDocument() {
        DiagramDocument document = new DiagramDocument();
        document.setEditingDomain(this.createEditingDomain());
        return document;
    }

    private TransactionalEditingDomain createEditingDomain() {
        TransactionalEditingDomain editingDomain = DiagramEditingDomainFactory.getInstance().createEditingDomain();
        editingDomain.setID("com.csi.gmf.stateflow.diagram.EditingDomain");
        final NotificationFilter diagramResourceModifiedFilter = NotificationFilter.createNotifierFilter((Object)editingDomain.getResourceSet()).and(NotificationFilter.createEventTypeFilter((int)3)).and(NotificationFilter.createFeatureFilter(ResourceSet.class, (int)0));
        editingDomain.getResourceSet().eAdapters().add((Object)new Adapter(){
            private Notifier myTarger;

            public Notifier getTarget() {
                return this.myTarger;
            }

            public boolean isAdapterForType(Object type) {
                return false;
            }

            public void notifyChanged(Notification notification) {
                Object value;
                if (diagramResourceModifiedFilter.matches(notification) && (value = notification.getNewValue()) instanceof Resource) {
                    ((Resource)value).setTrackingModification(true);
                }
            }

            public void setTarget(Notifier newTarget) {
                this.myTarger = newTarget;
            }
        });
        return editingDomain;
    }

    protected void setDocumentContent(IDocument document, IEditorInput element) throws CoreException {
        IDiagramDocument diagramDocument = (IDiagramDocument)document;
        TransactionalEditingDomain domain = diagramDocument.getEditingDomain();
        if (!(element instanceof FileEditorInput)) {
            if (element instanceof URIEditorInput) {
                URI uri = ((URIEditorInput)element).getURI();
                Resource resource = null;
                try {
                    resource = domain.getResourceSet().getResource(uri.trimFragment(), false);
                    if (resource == null) {
                        resource = domain.getResourceSet().createResource(uri.trimFragment());
                    }
                    if (!resource.isLoaded()) {
                        try {
                            HashMap options = new HashMap(GMFResourceFactory.getDefaultLoadOptions());
                            resource.load(options);
                        }
                        catch (IOException e) {
                            resource.unload();
                            throw e;
                        }
                    }
                    if (uri.fragment() != null) {
                        EObject rootElement = resource.getEObject(uri.fragment());
                        if (rootElement instanceof Diagram) {
                            document.setContent((Object)((Diagram)rootElement));
                            return;
                        }
                    } else {
                        for (Object rootElement : resource.getContents()) {
                            if (!(rootElement instanceof Diagram)) continue;
                            document.setContent((Object)((Diagram)rootElement));
                            return;
                        }
                    }
                    throw new RuntimeException(Messages.StateflowDocumentProvider_NoDiagramInResourceError);
                }
                catch (Exception e) {
                    String msg;
                    CoreException thrownExcp = null;
                    thrownExcp = e instanceof CoreException ? (CoreException)((Object)e) : new CoreException((IStatus)new Status(4, "com.csi.gmf.stateflow.diagram", 0, (msg = e.getLocalizedMessage()) != null ? msg : Messages.StateflowDocumentProvider_DiagramLoadingError, (Throwable)e));
                    throw thrownExcp;
                }
            }
            throw new CoreException((IStatus)new Status(4, "com.csi.gmf.stateflow.diagram", 0, NLS.bind((String)Messages.StateflowDocumentProvider_IncorrectInputError, (Object[])new Object[]{element, "org.eclipse.ui.part.FileEditorInput", "org.eclipse.emf.common.ui.URIEditorInput"}), null));
        }
        IStorage storage = ((FileEditorInput)element).getStorage();
        Diagram diagram = DiagramIOUtil.load((TransactionalEditingDomain)domain, (IStorage)storage, (boolean)true, (IProgressMonitor)this.getProgressMonitor());
        document.setContent((Object)diagram);
    }

    public long getModificationStamp(Object element) {
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            return this.computeModificationStamp(info);
        }
        return super.getModificationStamp(element);
    }

    public boolean isDeleted(Object element) {
        Resource diagramResource;
        IDiagramDocument document = this.getDiagramDocument(element);
        if (document != null && (diagramResource = document.getDiagram().eResource()) != null) {
            IFile file = WorkspaceSynchronizer.getFile((Resource)diagramResource);
            return file == null || file.getLocation() == null || !file.getLocation().toFile().exists();
        }
        return super.isDeleted(element);
    }

    public ResourceSetInfo getResourceSetInfo(Object editorInput) {
        return (ResourceSetInfo)super.getElementInfo(editorInput);
    }

    protected void disposeElementInfo(Object element, AbstractDocumentProvider.ElementInfo info) {
        if (info instanceof ResourceSetInfo) {
            ResourceSetInfo resourceSetInfo = (ResourceSetInfo)info;
            resourceSetInfo.dispose();
        }
        super.disposeElementInfo(element, info);
    }

    protected void doValidateState(Object element, Object computationContext) throws CoreException {
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            ArrayList<IFile> files2Validate = new ArrayList<IFile>();
            Iterator it = info.getLoadedResourcesIterator();
            while (it.hasNext()) {
                Resource nextResource = (Resource)it.next();
                IFile file = WorkspaceSynchronizer.getFile((Resource)nextResource);
                if (file == null || !file.isReadOnly()) continue;
                files2Validate.add(file);
            }
            ResourcesPlugin.getWorkspace().validateEdit(files2Validate.toArray(new IFile[files2Validate.size()]), computationContext);
        }
        super.doValidateState(element, computationContext);
    }

    public boolean isReadOnly(Object element) {
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            if (info.isUpdateCache()) {
                try {
                    this.updateCache(element);
                }
                catch (CoreException ex) {
                    StateflowDiagramEditorPlugin.getInstance().logError(Messages.StateflowDocumentProvider_isModifiable, ex);
                }
            }
            return info.isReadOnly();
        }
        return super.isReadOnly(element);
    }

    public boolean isModifiable(Object element) {
        if (!this.isStateValidated(element) && (element instanceof FileEditorInput || element instanceof URIEditorInput)) {
            return true;
        }
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            if (info.isUpdateCache()) {
                try {
                    this.updateCache(element);
                }
                catch (CoreException ex) {
                    StateflowDiagramEditorPlugin.getInstance().logError(Messages.StateflowDocumentProvider_isModifiable, ex);
                }
            }
            return info.isModifiable();
        }
        return super.isModifiable(element);
    }

    protected void updateCache(Object element) throws CoreException {
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            Iterator it = info.getLoadedResourcesIterator();
            while (it.hasNext()) {
                Resource nextResource = (Resource)it.next();
                IFile file = WorkspaceSynchronizer.getFile((Resource)nextResource);
                if (file == null || !file.isReadOnly()) continue;
                info.setReadOnly(true);
                info.setModifiable(false);
                return;
            }
            info.setReadOnly(false);
            info.setModifiable(true);
            return;
        }
    }

    protected void doUpdateStateCache(Object element) throws CoreException {
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            info.setUpdateCache(true);
        }
        super.doUpdateStateCache(element);
    }

    public boolean isSynchronized(Object element) {
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            return info.isSynchronized();
        }
        return super.isSynchronized(element);
    }

    protected ISchedulingRule getResetRule(Object element) {
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            ArrayList<ISchedulingRule> rules = new ArrayList<ISchedulingRule>();
            Iterator it = info.getLoadedResourcesIterator();
            while (it.hasNext()) {
                Resource nextResource = (Resource)it.next();
                IFile file = WorkspaceSynchronizer.getFile((Resource)nextResource);
                if (file == null) continue;
                rules.add(ResourcesPlugin.getWorkspace().getRuleFactory().modifyRule((IResource)file));
            }
            return new MultiRule(rules.toArray(new ISchedulingRule[rules.size()]));
        }
        return null;
    }

    protected ISchedulingRule getSaveRule(Object element) {
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            ArrayList<ISchedulingRule> rules = new ArrayList<ISchedulingRule>();
            Iterator it = info.getLoadedResourcesIterator();
            while (it.hasNext()) {
                Resource nextResource = (Resource)it.next();
                IFile file = WorkspaceSynchronizer.getFile((Resource)nextResource);
                if (file == null) continue;
                rules.add(this.computeSchedulingRule((IResource)file));
            }
            return new MultiRule(rules.toArray(new ISchedulingRule[rules.size()]));
        }
        return null;
    }

    protected ISchedulingRule getSynchronizeRule(Object element) {
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            ArrayList<ISchedulingRule> rules = new ArrayList<ISchedulingRule>();
            Iterator it = info.getLoadedResourcesIterator();
            while (it.hasNext()) {
                Resource nextResource = (Resource)it.next();
                IFile file = WorkspaceSynchronizer.getFile((Resource)nextResource);
                if (file == null) continue;
                rules.add(ResourcesPlugin.getWorkspace().getRuleFactory().refreshRule((IResource)file));
            }
            return new MultiRule(rules.toArray(new ISchedulingRule[rules.size()]));
        }
        return null;
    }

    protected ISchedulingRule getValidateStateRule(Object element) {
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            ArrayList<IFile> files = new ArrayList<IFile>();
            Iterator it = info.getLoadedResourcesIterator();
            while (it.hasNext()) {
                Resource nextResource = (Resource)it.next();
                IFile file = WorkspaceSynchronizer.getFile((Resource)nextResource);
                if (file == null) continue;
                files.add(file);
            }
            return ResourcesPlugin.getWorkspace().getRuleFactory().validateEditRule((IResource[])files.toArray(new IFile[files.size()]));
        }
        return null;
    }

    private ISchedulingRule computeSchedulingRule(IResource toCreateOrModify) {
        if (toCreateOrModify.exists()) {
            return ResourcesPlugin.getWorkspace().getRuleFactory().modifyRule(toCreateOrModify);
        }
        IResource parent = toCreateOrModify;
        while ((parent = (toCreateOrModify = parent).getParent()) != null && !parent.exists()) {
        }
        return ResourcesPlugin.getWorkspace().getRuleFactory().createRule(toCreateOrModify);
    }

    protected void doSynchronize(Object element, IProgressMonitor monitor) throws CoreException {
        ResourceSetInfo info = this.getResourceSetInfo(element);
        if (info != null) {
            Iterator it = info.getLoadedResourcesIterator();
            while (it.hasNext()) {
                Resource nextResource = (Resource)it.next();
                this.handleElementChanged(info, nextResource, monitor);
            }
            return;
        }
        super.doSynchronize(element, monitor);
    }

    protected void doSaveDocument(IProgressMonitor monitor, Object element, IDocument document, boolean overwrite) throws CoreException {
        block19: {
            URI newResoruceURI;
            ResourceSetInfo info = this.getResourceSetInfo(element);
            if (info != null) {
                if (!overwrite && !info.isSynchronized()) {
                    throw new CoreException((IStatus)new Status(4, "com.csi.gmf.stateflow.diagram", 274, Messages.StateflowDocumentProvider_UnsynchronizedFileSaveError, null));
                }
                info.stopResourceListening();
                this.fireElementStateChanging(element);
                try {
                    try {
                        monitor.beginTask(Messages.StateflowDocumentProvider_SaveDiagramTask, info.getResourceSet().getResources().size() + 1);
                        Iterator it = info.getLoadedResourcesIterator();
                        while (it.hasNext()) {
                            Resource nextResource = (Resource)it.next();
                            monitor.setTaskName(NLS.bind((String)Messages.StateflowDocumentProvider_SaveNextResourceTask, (Object)nextResource.getURI()));
                            if (nextResource.isLoaded() && !info.getEditingDomain().isReadOnly(nextResource)) {
                                try {
                                    nextResource.save(StateflowDiagramEditorUtil.getSaveOptions());
                                }
                                catch (IOException e) {
                                    this.fireElementStateChangeFailed(element);
                                    throw new CoreException((IStatus)new Status(4, "com.csi.gmf.stateflow.diagram", 5, e.getLocalizedMessage(), null));
                                }
                            }
                            monitor.worked(1);
                        }
                        monitor.done();
                        info.setModificationStamp(this.computeModificationStamp(info));
                        break block19;
                    }
                    catch (RuntimeException x) {
                        this.fireElementStateChangeFailed(element);
                        throw x;
                    }
                }
                finally {
                    info.startResourceListening();
                }
            }
            List<IFile> affectedFiles = null;
            if (element instanceof FileEditorInput) {
                IFile newFile = ((FileEditorInput)element).getFile();
                affectedFiles = Collections.singletonList(newFile);
                newResoruceURI = URI.createPlatformResourceURI((String)newFile.getFullPath().toString(), (boolean)true);
            } else if (element instanceof URIEditorInput) {
                newResoruceURI = ((URIEditorInput)element).getURI();
            } else {
                this.fireElementStateChangeFailed(element);
                throw new CoreException((IStatus)new Status(4, "com.csi.gmf.stateflow.diagram", 0, NLS.bind((String)Messages.StateflowDocumentProvider_IncorrectInputError, (Object[])new Object[]{element, "org.eclipse.ui.part.FileEditorInput", "org.eclipse.emf.common.ui.URIEditorInput"}), null));
            }
            if (!(document instanceof IDiagramDocument)) {
                this.fireElementStateChangeFailed(element);
                throw new CoreException((IStatus)new Status(4, "com.csi.gmf.stateflow.diagram", 0, "Incorrect document used: " + document + " instead of org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument", null));
            }
            IDiagramDocument diagramDocument = (IDiagramDocument)document;
            final Resource newResource = diagramDocument.getEditingDomain().getResourceSet().createResource(newResoruceURI);
            final Diagram diagramCopy = (Diagram)EcoreUtil.copy((EObject)diagramDocument.getDiagram());
            try {
                new AbstractTransactionalCommand(diagramDocument.getEditingDomain(), NLS.bind((String)Messages.StateflowDocumentProvider_SaveAsOperation, (Object)diagramCopy.getName()), affectedFiles){

                    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
                        newResource.getContents().add((Object)diagramCopy);
                        return CommandResult.newOKCommandResult();
                    }
                }.execute(monitor, null);
                newResource.save(StateflowDiagramEditorUtil.getSaveOptions());
            }
            catch (ExecutionException e) {
                this.fireElementStateChangeFailed(element);
                throw new CoreException((IStatus)new Status(4, "com.csi.gmf.stateflow.diagram", 0, e.getLocalizedMessage(), null));
            }
            catch (IOException e) {
                this.fireElementStateChangeFailed(element);
                throw new CoreException((IStatus)new Status(4, "com.csi.gmf.stateflow.diagram", 0, e.getLocalizedMessage(), null));
            }
            newResource.unload();
        }
    }

    protected void handleElementChanged(ResourceSetInfo info, Resource changedResource, IProgressMonitor monitor) {
        IFile file = WorkspaceSynchronizer.getFile((Resource)changedResource);
        if (file != null) {
            try {
                file.refreshLocal(2, monitor);
            }
            catch (CoreException ex) {
                StateflowDiagramEditorPlugin.getInstance().logError(Messages.StateflowDocumentProvider_handleElementContentChanged, ex);
            }
        }
        changedResource.unload();
        this.fireElementContentAboutToBeReplaced(info.getEditorInput());
        this.removeUnchangedElementListeners(info.getEditorInput(), info);
        info.fStatus = null;
        try {
            this.setDocumentContent(info.fDocument, info.getEditorInput());
        }
        catch (CoreException e) {
            info.fStatus = e.getStatus();
        }
        if (!info.fCanBeSaved) {
            info.setModificationStamp(this.computeModificationStamp(info));
        }
        this.addUnchangedElementListeners(info.getEditorInput(), info);
        this.fireElementContentReplaced(info.getEditorInput());
    }

    protected void handleElementMoved(IEditorInput input, URI uri) {
        if (input instanceof FileEditorInput) {
            IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(URI.decode((String)uri.path())).removeFirstSegments(1));
            this.fireElementMoved(input, newFile == null ? null : new FileEditorInput(newFile));
            return;
        }
        this.fireElementMoved(input, new URIEditorInput(uri));
    }

    public IEditorInput createInputWithEditingDomain(IEditorInput editorInput, TransactionalEditingDomain domain) {
        return editorInput;
    }

    public IDiagramDocument getDiagramDocument(Object element) {
        IDocument doc = this.getDocument(element);
        if (doc instanceof IDiagramDocument) {
            return (IDiagramDocument)doc;
        }
        return null;
    }

    protected IRunnableContext getOperationRunner(IProgressMonitor monitor) {
        return null;
    }

    protected class ResourceSetInfo
    extends AbstractDocumentProvider.ElementInfo {
        private long myModificationStamp;
        private WorkspaceSynchronizer mySynchronizer;
        private Collection myUnSynchronizedResources;
        private IDiagramDocument myDocument;
        private IEditorInput myEditorInput;
        private boolean myUpdateCache;
        private boolean myModifiable;
        private boolean myReadOnly;
        private ResourceSetModificationListener myResourceSetListener;

        public ResourceSetInfo(IDiagramDocument document, IEditorInput editorInput) {
            super((AbstractDocumentProvider)StateflowDocumentProvider.this, (IDocument)document);
            this.myModificationStamp = -1L;
            this.myUnSynchronizedResources = new ArrayList();
            this.myUpdateCache = true;
            this.myModifiable = false;
            this.myReadOnly = true;
            this.myDocument = document;
            this.myEditorInput = editorInput;
            this.startResourceListening();
            this.myResourceSetListener = new ResourceSetModificationListener(this);
            this.getResourceSet().eAdapters().add((Object)this.myResourceSetListener);
        }

        public long getModificationStamp() {
            return this.myModificationStamp;
        }

        public void setModificationStamp(long modificationStamp) {
            this.myModificationStamp = modificationStamp;
        }

        public TransactionalEditingDomain getEditingDomain() {
            return this.myDocument.getEditingDomain();
        }

        public ResourceSet getResourceSet() {
            return this.getEditingDomain().getResourceSet();
        }

        public Iterator getLoadedResourcesIterator() {
            return new ArrayList(this.getResourceSet().getResources()).iterator();
        }

        public IEditorInput getEditorInput() {
            return this.myEditorInput;
        }

        public void dispose() {
            this.stopResourceListening();
            this.getResourceSet().eAdapters().remove((Object)this.myResourceSetListener);
            Iterator it = this.getLoadedResourcesIterator();
            while (it.hasNext()) {
                Resource resource = (Resource)it.next();
                resource.unload();
            }
        }

        public boolean isSynchronized() {
            return this.myUnSynchronizedResources.size() == 0;
        }

        public void setUnSynchronized(Resource resource) {
            this.myUnSynchronizedResources.add(resource);
        }

        public void setSynchronized(Resource resource) {
            this.myUnSynchronizedResources.remove(resource);
        }

        public final void stopResourceListening() {
            this.mySynchronizer.dispose();
            this.mySynchronizer = null;
        }

        public final void startResourceListening() {
            this.mySynchronizer = new WorkspaceSynchronizer(this.getEditingDomain(), (WorkspaceSynchronizer.Delegate)new SynchronizerDelegate());
        }

        public boolean isUpdateCache() {
            return this.myUpdateCache;
        }

        public void setUpdateCache(boolean update) {
            this.myUpdateCache = update;
        }

        public boolean isModifiable() {
            return this.myModifiable;
        }

        public void setModifiable(boolean modifiable) {
            this.myModifiable = modifiable;
        }

        public boolean isReadOnly() {
            return this.myReadOnly;
        }

        public void setReadOnly(boolean readOnly) {
            this.myReadOnly = readOnly;
        }

        private class SynchronizerDelegate
        implements WorkspaceSynchronizer.Delegate {
            private SynchronizerDelegate() {
            }

            public void dispose() {
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public boolean handleResourceChanged(final Resource resource) {
                ResourceSetInfo resourceSetInfo = ResourceSetInfo.this;
                synchronized (resourceSetInfo) {
                    if (ResourceSetInfo.this.fCanBeSaved) {
                        ResourceSetInfo.this.setUnSynchronized(resource);
                        return true;
                    }
                }
                Display.getDefault().asyncExec(new Runnable(){

                    @Override
                    public void run() {
                        StateflowDocumentProvider.this.handleElementChanged(ResourceSetInfo.this, resource, null);
                    }
                });
                return true;
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public boolean handleResourceDeleted(Resource resource) {
                ResourceSetInfo resourceSetInfo = ResourceSetInfo.this;
                synchronized (resourceSetInfo) {
                    if (ResourceSetInfo.this.fCanBeSaved) {
                        ResourceSetInfo.this.setUnSynchronized(resource);
                        return true;
                    }
                }
                Display.getDefault().asyncExec(new Runnable(){

                    @Override
                    public void run() {
                        StateflowDocumentProvider.this.fireElementDeleted(ResourceSetInfo.this.getEditorInput());
                    }
                });
                return true;
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public boolean handleResourceMoved(Resource resource, final URI newURI) {
                ResourceSetInfo resourceSetInfo = ResourceSetInfo.this;
                synchronized (resourceSetInfo) {
                    if (ResourceSetInfo.this.fCanBeSaved) {
                        ResourceSetInfo.this.setUnSynchronized(resource);
                        return true;
                    }
                }
                if (ResourceSetInfo.this.myDocument.getDiagram().eResource() == resource) {
                    Display.getDefault().asyncExec(new Runnable(){

                        @Override
                        public void run() {
                            StateflowDocumentProvider.this.handleElementMoved(ResourceSetInfo.this.getEditorInput(), newURI);
                        }
                    });
                    return true;
                }
                this.handleResourceDeleted(resource);
                return true;
            }
        }
    }

    private class ResourceSetModificationListener
    extends EContentAdapter {
        private NotificationFilter myModifiedFilter;
        private ResourceSetInfo myInfo;

        public ResourceSetModificationListener(ResourceSetInfo info) {
            this.myInfo = info;
            this.myModifiedFilter = NotificationFilter.createEventTypeFilter((int)1).or(NotificationFilter.createEventTypeFilter((int)2)).and(NotificationFilter.createFeatureFilter(Resource.class, (int)3));
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void notifyChanged(Notification notification) {
            Resource resource;
            if (notification.getNotifier() instanceof ResourceSet) {
                super.notifyChanged(notification);
            }
            if (!notification.isTouch() && this.myModifiedFilter.matches(notification) && notification.getNotifier() instanceof Resource && (resource = (Resource)notification.getNotifier()).isLoaded()) {
                boolean modified = false;
                Iterator it = this.myInfo.getLoadedResourcesIterator();
                while (it.hasNext() && !modified) {
                    Resource nextResource = (Resource)it.next();
                    if (!nextResource.isLoaded()) continue;
                    modified = nextResource.isModified();
                }
                boolean dirtyStateChanged = false;
                ResourceSetInfo resourceSetInfo = this.myInfo;
                synchronized (resourceSetInfo) {
                    if (modified != this.myInfo.fCanBeSaved) {
                        this.myInfo.fCanBeSaved = modified;
                        dirtyStateChanged = true;
                    }
                    if (!resource.isModified()) {
                        this.myInfo.setSynchronized(resource);
                    }
                }
                if (dirtyStateChanged) {
                    StateflowDocumentProvider.this.fireElementDirtyStateChanged(this.myInfo.getEditorInput(), modified);
                    if (!modified) {
                        this.myInfo.setModificationStamp(StateflowDocumentProvider.this.computeModificationStamp(this.myInfo));
                    }
                }
            }
        }
    }
}

