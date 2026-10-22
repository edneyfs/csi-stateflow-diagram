/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.common.ui.URIEditorInput
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.ecore.util.EcoreUtil
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer
 *  org.eclipse.gmf.runtime.notation.Diagram
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.action.Action
 *  org.eclipse.jface.action.IAction
 *  org.eclipse.jface.action.IMenuManager
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.ui.IActionBars
 *  org.eclipse.ui.IEditorInput
 *  org.eclipse.ui.IWorkbenchPage
 *  org.eclipse.ui.PartInitException
 *  org.eclipse.ui.navigator.CommonActionProvider
 *  org.eclipse.ui.navigator.ICommonActionExtensionSite
 *  org.eclipse.ui.navigator.ICommonViewerWorkbenchSite
 *  org.eclipse.ui.part.FileEditorInput
 */
package com.csi.gmf.executionflow.diagram.navigator;

import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowNavigatorItem;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import com.csi.gmf.executionflow.diagram.part.Messages;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.part.FileEditorInput;

public class ExecutionflowNavigatorActionProvider
extends CommonActionProvider {
    private boolean myContribute;
    private OpenDiagramAction myOpenDiagramAction;

    public void init(ICommonActionExtensionSite aSite) {
        super.init(aSite);
        if (aSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
            this.myContribute = true;
            this.makeActions((ICommonViewerWorkbenchSite)aSite.getViewSite());
        } else {
            this.myContribute = false;
        }
    }

    private void makeActions(ICommonViewerWorkbenchSite viewerSite) {
        this.myOpenDiagramAction = new OpenDiagramAction(viewerSite);
    }

    public void fillActionBars(IActionBars actionBars) {
        if (!this.myContribute) {
            return;
        }
        IStructuredSelection selection = (IStructuredSelection)this.getContext().getSelection();
        this.myOpenDiagramAction.selectionChanged(selection);
        if (this.myOpenDiagramAction.isEnabled()) {
            actionBars.setGlobalActionHandler("org.eclipse.ui.navigator.Open", (IAction)this.myOpenDiagramAction);
        }
    }

    public void fillContextMenu(IMenuManager menu) {
    }

    private class OpenDiagramAction
    extends Action {
        private Diagram myDiagram;
        private ICommonViewerWorkbenchSite myViewerSite;

        public OpenDiagramAction(ICommonViewerWorkbenchSite viewerSite) {
            super(Messages.NavigatorActionProvider_OpenDiagramActionName);
            this.myViewerSite = viewerSite;
        }

        public final void selectionChanged(IStructuredSelection selection) {
            this.myDiagram = null;
            if (selection.size() == 1) {
                Diagram diagram;
                Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof ExecutionflowNavigatorItem) {
                    selectedElement = ((ExecutionflowNavigatorItem)((Object)selectedElement)).getView();
                } else if (selectedElement instanceof IAdaptable) {
                    selectedElement = ((IAdaptable)selectedElement).getAdapter(View.class);
                }
                if (selectedElement instanceof Diagram && "Executionflow".equals(ExecutionflowVisualIDRegistry.getModelID((View)(diagram = (Diagram)selectedElement)))) {
                    this.myDiagram = diagram;
                }
            }
            this.setEnabled(this.myDiagram != null);
        }

        public void run() {
            if (this.myDiagram == null || this.myDiagram.eResource() == null) {
                return;
            }
            IEditorInput editorInput = this.getEditorInput();
            IWorkbenchPage page = this.myViewerSite.getPage();
            try {
                page.openEditor(editorInput, "com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorID");
            }
            catch (PartInitException e) {
                ExecutionflowDiagramEditorPlugin.getInstance().logError("Exception while openning diagram", e);
            }
        }

        private IEditorInput getEditorInput() {
            for (EObject nextEObject : this.myDiagram.eResource().getContents()) {
                if (nextEObject == this.myDiagram) {
                    return new FileEditorInput(WorkspaceSynchronizer.getFile((Resource)this.myDiagram.eResource()));
                }
                if (nextEObject instanceof Diagram) break;
            }
            URI uri = EcoreUtil.getURI((EObject)this.myDiagram);
            String editorName = String.valueOf(uri.lastSegment()) + "#" + this.myDiagram.eResource().getContents().indexOf((Object)this.myDiagram);
            URIEditorInput editorInput = new URIEditorInput(uri, editorName);
            return editorInput;
        }
    }
}

