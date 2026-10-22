/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IMarker
 *  org.eclipse.core.resources.IWorkspaceRoot
 *  org.eclipse.core.resources.ResourcesPlugin
 *  org.eclipse.core.runtime.CoreException
 *  org.eclipse.core.runtime.IPath
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.core.runtime.IStatus
 *  org.eclipse.core.runtime.NullProgressMonitor
 *  org.eclipse.emf.common.ui.URIEditorInput
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer
 *  org.eclipse.gef.EditPartViewer
 *  org.eclipse.gef.palette.PaletteRoot
 *  org.eclipse.gmf.runtime.common.ui.services.marker.MarkerNavigationService
 *  org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor
 *  org.eclipse.gmf.runtime.notation.Diagram
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.action.MenuManager
 *  org.eclipse.jface.dialogs.ErrorDialog
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.ISelectionProvider
 *  org.eclipse.jface.viewers.StructuredSelection
 *  org.eclipse.osgi.util.NLS
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.ui.IEditorInput
 *  org.eclipse.ui.IEditorMatchingStrategy
 *  org.eclipse.ui.IEditorPart
 *  org.eclipse.ui.IEditorReference
 *  org.eclipse.ui.IFileEditorInput
 *  org.eclipse.ui.IWorkbenchPart
 *  org.eclipse.ui.PlatformUI
 *  org.eclipse.ui.dialogs.SaveAsDialog
 *  org.eclipse.ui.ide.IGotoMarker
 *  org.eclipse.ui.part.FileEditorInput
 *  org.eclipse.ui.part.IShowInTargetList
 *  org.eclipse.ui.part.ShowInContext
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowNavigatorItem;
import com.csi.gmf.executionflow.diagram.part.DiagramEditorContextMenuProvider;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.common.ui.services.marker.MarkerNavigationService;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IShowInTargetList;
import org.eclipse.ui.part.ShowInContext;

public class ExecutionflowDiagramEditor
extends DiagramDocumentEditor
implements IGotoMarker {
    public static final String ID = "com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorID";
    public static final String CONTEXT_ID = "com.csi.gmf.executionflow.diagram.ui.diagramContext";

    public ExecutionflowDiagramEditor() {
        super(true);
    }

    protected String getContextID() {
        return CONTEXT_ID;
    }

    protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot) {
        PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
        return root;
    }

    protected PreferencesHint getPreferencesHint() {
        return ExecutionflowDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
    }

    public String getContributorId() {
        return "com.csi.gmf.executionflow.diagram";
    }

    public Object getAdapter(Class type) {
        if (type == IShowInTargetList.class) {
            return new IShowInTargetList(){

                public String[] getShowInTargetIds() {
                    return new String[]{"org.eclipse.ui.navigator.ProjectExplorer"};
                }
            };
        }
        return super.getAdapter(type);
    }

    protected IDocumentProvider getDocumentProvider(IEditorInput input) {
        if (input instanceof IFileEditorInput || input instanceof URIEditorInput) {
            return ExecutionflowDiagramEditorPlugin.getInstance().getDocumentProvider();
        }
        return super.getDocumentProvider(input);
    }

    public TransactionalEditingDomain getEditingDomain() {
        IDocument document;
        IDocument iDocument = document = this.getEditorInput() != null ? this.getDocumentProvider().getDocument((Object)this.getEditorInput()) : null;
        if (document instanceof IDiagramDocument) {
            return ((IDiagramDocument)document).getEditingDomain();
        }
        return super.getEditingDomain();
    }

    protected void setDocumentProvider(IEditorInput input) {
        if (input instanceof IFileEditorInput || input instanceof URIEditorInput) {
            this.setDocumentProvider((IDocumentProvider)ExecutionflowDiagramEditorPlugin.getInstance().getDocumentProvider());
        } else {
            super.setDocumentProvider(input);
        }
    }

    public void gotoMarker(IMarker marker) {
        MarkerNavigationService.getInstance().gotoMarker((IEditorPart)this, marker);
    }

    public boolean isSaveAsAllowed() {
        return true;
    }

    public void doSaveAs() {
        this.performSaveAs((IProgressMonitor)new NullProgressMonitor());
    }

    protected void performSaveAs(IProgressMonitor progressMonitor) {
        boolean success;
        block19: {
            IFile original;
            Shell shell = this.getSite().getShell();
            IEditorInput input = this.getEditorInput();
            SaveAsDialog dialog = new SaveAsDialog(shell);
            IFile iFile = original = input instanceof IFileEditorInput ? ((IFileEditorInput)input).getFile() : null;
            if (original != null) {
                dialog.setOriginalFile(original);
            }
            dialog.create();
            IDocumentProvider provider = this.getDocumentProvider();
            if (provider == null) {
                return;
            }
            if (provider.isDeleted((Object)input) && original != null) {
                String message = NLS.bind((String)Messages.ExecutionflowDiagramEditor_SavingDeletedFile, (Object)original.getName());
                dialog.setErrorMessage(null);
                dialog.setMessage(message, 2);
            }
            if (dialog.open() == 1) {
                if (progressMonitor != null) {
                    progressMonitor.setCanceled(true);
                }
                return;
            }
            IPath filePath = dialog.getResult();
            if (filePath == null) {
                if (progressMonitor != null) {
                    progressMonitor.setCanceled(true);
                }
                return;
            }
            IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
            IFile file = workspaceRoot.getFile(filePath);
            FileEditorInput newInput = new FileEditorInput(file);
            IEditorMatchingStrategy matchingStrategy = this.getEditorDescriptor().getEditorMatchingStrategy();
            IEditorReference[] editorRefs = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
            int i = 0;
            while (i < editorRefs.length) {
                if (matchingStrategy.matches(editorRefs[i], (IEditorInput)newInput)) {
                    MessageDialog.openWarning((Shell)shell, (String)Messages.ExecutionflowDiagramEditor_SaveAsErrorTitle, (String)Messages.ExecutionflowDiagramEditor_SaveAsErrorMessage);
                    return;
                }
                ++i;
            }
            success = false;
            try {
                try {
                    provider.aboutToChange((Object)newInput);
                    this.getDocumentProvider((IEditorInput)newInput).saveDocument(progressMonitor, (Object)newInput, this.getDocumentProvider().getDocument((Object)this.getEditorInput()), true);
                    success = true;
                }
                catch (CoreException x) {
                    IStatus status = x.getStatus();
                    if (status == null || status.getSeverity() != 8) {
                        ErrorDialog.openError((Shell)shell, (String)Messages.ExecutionflowDiagramEditor_SaveErrorTitle, (String)Messages.ExecutionflowDiagramEditor_SaveErrorMessage, (IStatus)x.getStatus());
                    }
                    provider.changed((Object)newInput);
                    if (success) {
                        this.setInput((IEditorInput)newInput);
                    }
                    break block19;
                }
            }
            catch (Throwable throwable) {
                provider.changed((Object)newInput);
                if (success) {
                    this.setInput((IEditorInput)newInput);
                }
                throw throwable;
            }
            provider.changed((Object)newInput);
            if (success) {
                this.setInput((IEditorInput)newInput);
            }
        }
        if (progressMonitor != null) {
            progressMonitor.setCanceled(!success);
        }
    }

    public ShowInContext getShowInContext() {
        return new ShowInContext((Object)this.getEditorInput(), this.getNavigatorSelection());
    }

    private ISelection getNavigatorSelection() {
        IDiagramDocument document = this.getDiagramDocument();
        if (document == null) {
            return StructuredSelection.EMPTY;
        }
        Diagram diagram = document.getDiagram();
        IFile file = WorkspaceSynchronizer.getFile((Resource)diagram.eResource());
        if (file != null) {
            ExecutionflowNavigatorItem item = new ExecutionflowNavigatorItem((View)diagram, file, false);
            return new StructuredSelection((Object)item);
        }
        return StructuredSelection.EMPTY;
    }

    protected void configureGraphicalViewer() {
        super.configureGraphicalViewer();
        DiagramEditorContextMenuProvider provider = new DiagramEditorContextMenuProvider((IWorkbenchPart)this, (EditPartViewer)this.getDiagramGraphicalViewer());
        this.getDiagramGraphicalViewer().setContextMenu((MenuManager)provider);
        this.getSite().registerContextMenu("org.eclipse.gmf.runtime.diagram.ui.DiagramEditorContextMenu", (MenuManager)provider, (ISelectionProvider)this.getDiagramGraphicalViewer());
    }
}

