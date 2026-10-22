/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.emf.common.ui.URIEditorInput
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.ecore.resource.ResourceSet
 *  org.eclipse.emf.ecore.util.EcoreUtil
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.GraphicalViewer
 *  org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument
 *  org.eclipse.gmf.runtime.notation.Diagram
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.jface.viewers.StructuredSelection
 *  org.eclipse.ui.IEditorInput
 *  org.eclipse.ui.IEditorPart
 *  org.eclipse.ui.IWorkbenchPage
 *  org.eclipse.ui.IWorkbenchPart
 *  org.eclipse.ui.navigator.ILinkHelper
 *  org.eclipse.ui.part.FileEditorInput
 */
package com.csi.gmf.executionflow.diagram.navigator;

import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowAbstractNavigatorItem;
import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowNavigatorGroup;
import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowNavigatorItem;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.navigator.ILinkHelper;
import org.eclipse.ui.part.FileEditorInput;

public class ExecutionflowNavigatorLinkHelper
implements ILinkHelper {
    private static IEditorInput getEditorInput(Diagram diagram) {
        Resource diagramResource = diagram.eResource();
        for (EObject nextEObject : diagramResource.getContents()) {
            if (nextEObject == diagram) {
                return new FileEditorInput(WorkspaceSynchronizer.getFile((Resource)diagramResource));
            }
            if (nextEObject instanceof Diagram) break;
        }
        URI uri = EcoreUtil.getURI((EObject)diagram);
        String editorName = String.valueOf(uri.lastSegment()) + "#" + diagram.eResource().getContents().indexOf((Object)diagram);
        URIEditorInput editorInput = new URIEditorInput(uri, editorName);
        return editorInput;
    }

    public IStructuredSelection findSelection(IEditorInput anInput) {
        IDiagramDocument document = ExecutionflowDiagramEditorPlugin.getInstance().getDocumentProvider().getDiagramDocument(anInput);
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

    public void activateEditor(IWorkbenchPage aPage, IStructuredSelection aSelection) {
        ExecutionflowNavigatorGroup navigatorGroup;
        if (aSelection == null || aSelection.isEmpty()) {
            return;
        }
        if (!(aSelection.getFirstElement() instanceof ExecutionflowAbstractNavigatorItem)) {
            return;
        }
        ExecutionflowAbstractNavigatorItem abstractNavigatorItem = (ExecutionflowAbstractNavigatorItem)((Object)aSelection.getFirstElement());
        View navigatorView = null;
        if (abstractNavigatorItem instanceof ExecutionflowNavigatorItem) {
            navigatorView = ((ExecutionflowNavigatorItem)abstractNavigatorItem).getView();
        } else if (abstractNavigatorItem instanceof ExecutionflowNavigatorGroup && (navigatorGroup = (ExecutionflowNavigatorGroup)abstractNavigatorItem).getParent() instanceof ExecutionflowNavigatorItem) {
            navigatorView = ((ExecutionflowNavigatorItem)((Object)navigatorGroup.getParent())).getView();
        }
        if (navigatorView == null) {
            return;
        }
        IEditorInput editorInput = ExecutionflowNavigatorLinkHelper.getEditorInput(navigatorView.getDiagram());
        IEditorPart editor = aPage.findEditor(editorInput);
        if (editor == null) {
            return;
        }
        aPage.bringToTop((IWorkbenchPart)editor);
        if (editor instanceof DiagramEditor) {
            DiagramEditor diagramEditor = (DiagramEditor)editor;
            ResourceSet diagramEditorResourceSet = diagramEditor.getEditingDomain().getResourceSet();
            EObject selectedView = diagramEditorResourceSet.getEObject(EcoreUtil.getURI((EObject)navigatorView), true);
            if (selectedView == null) {
                return;
            }
            GraphicalViewer graphicalViewer = (GraphicalViewer)diagramEditor.getAdapter(GraphicalViewer.class);
            EditPart selectedEditPart = (EditPart)graphicalViewer.getEditPartRegistry().get(selectedView);
            if (selectedEditPart != null) {
                graphicalViewer.select(selectedEditPart);
            }
        }
    }
}

