/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.StateFlow
 *  com.csi.gmf.stateflow.StateflowFactory
 *  org.eclipse.core.commands.ExecutionException
 *  org.eclipse.core.commands.operations.IUndoableOperation
 *  org.eclipse.core.commands.operations.OperationHistoryFactory
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IResource
 *  org.eclipse.core.resources.ResourcesPlugin
 *  org.eclipse.core.runtime.CoreException
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.core.runtime.IPath
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.core.runtime.NullProgressMonitor
 *  org.eclipse.core.runtime.Path
 *  org.eclipse.core.runtime.SubProgressMonitor
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.ecore.xmi.XMLResource
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gmf.runtime.common.core.command.CommandResult
 *  org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint
 *  org.eclipse.gmf.runtime.diagram.core.services.ViewService
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer
 *  org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart
 *  org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand
 *  org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory
 *  org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil
 *  org.eclipse.gmf.runtime.notation.Diagram
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.dialogs.IDialogSettings
 *  org.eclipse.jface.wizard.IWizard
 *  org.eclipse.jface.wizard.Wizard
 *  org.eclipse.jface.wizard.WizardDialog
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.ui.IEditorInput
 *  org.eclipse.ui.IWorkbenchPage
 *  org.eclipse.ui.PartInitException
 *  org.eclipse.ui.PlatformUI
 *  org.eclipse.ui.part.FileEditorInput
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowFactory;
import com.csi.gmf.stateflow.diagram.part.Messages;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class StateflowDiagramEditorUtil {
    public static Map getSaveOptions() {
        HashMap<String, String> saveOptions = new HashMap<String, String>();
        saveOptions.put("ENCODING", "UTF-8");
        saveOptions.put("SAVE_ONLY_IF_CHANGED", "MEMORY_BUFFER");
        return saveOptions;
    }

    public static boolean openDiagram(Resource diagram) throws PartInitException {
        String path = diagram.getURI().toPlatformString(true);
        IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot().findMember((IPath)new Path(path));
        if (workspaceResource instanceof IFile) {
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            return page.openEditor((IEditorInput)new FileEditorInput((IFile)workspaceResource), "com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorID") != null;
        }
        return false;
    }

    public static void setCharset(IFile file) {
        if (file == null) {
            return;
        }
        try {
            file.setCharset("UTF-8", (IProgressMonitor)new NullProgressMonitor());
        }
        catch (CoreException e) {
            StateflowDiagramEditorPlugin.getInstance().logError("Unable to set charset for file " + file.getFullPath(), e);
        }
    }

    public static String getUniqueFileName(IPath containerFullPath, String fileName, String extension) {
        if (containerFullPath == null) {
            containerFullPath = new Path("");
        }
        if (fileName == null || fileName.trim().length() == 0) {
            fileName = "default";
        }
        IPath filePath = containerFullPath.append(fileName);
        if (extension != null && !extension.equals(filePath.getFileExtension())) {
            filePath = filePath.addFileExtension(extension);
        }
        extension = filePath.getFileExtension();
        fileName = filePath.removeFileExtension().lastSegment();
        int i = 1;
        while (ResourcesPlugin.getWorkspace().getRoot().exists(filePath)) {
            filePath = containerFullPath.append(String.valueOf(fileName) + ++i);
            if (extension == null) continue;
            filePath = filePath.addFileExtension(extension);
        }
        return filePath.lastSegment();
    }

    public static void runWizard(Shell shell, Wizard wizard, String settingsKey) {
        IDialogSettings pluginDialogSettings = StateflowDiagramEditorPlugin.getInstance().getDialogSettings();
        IDialogSettings wizardDialogSettings = pluginDialogSettings.getSection(settingsKey);
        if (wizardDialogSettings == null) {
            wizardDialogSettings = pluginDialogSettings.addNewSection(settingsKey);
        }
        wizard.setDialogSettings(wizardDialogSettings);
        WizardDialog dialog = new WizardDialog(shell, (IWizard)wizard);
        dialog.create();
        dialog.getShell().setSize(Math.max(500, dialog.getShell().getSize().x), 500);
        dialog.open();
    }

    public static Resource createDiagram(URI diagramURI, URI modelURI, IProgressMonitor progressMonitor) {
        TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
        progressMonitor.beginTask(Messages.StateflowDiagramEditorUtil_CreateDiagramProgressTask, 3);
        final Resource diagramResource = editingDomain.getResourceSet().createResource(diagramURI);
        final Resource modelResource = editingDomain.getResourceSet().createResource(modelURI);
        final String diagramName = diagramURI.lastSegment();
        AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain, Messages.StateflowDiagramEditorUtil_CreateDiagramCommandLabel, Collections.EMPTY_LIST){

            protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
                StateFlow model = StateflowDiagramEditorUtil.createInitialModel();
                StateflowDiagramEditorUtil.attachModelToResource(model, modelResource);
                Diagram diagram = ViewService.createDiagram((EObject)model, (String)"Stateflow", (PreferencesHint)StateflowDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
                if (diagram != null) {
                    diagramResource.getContents().add((Object)diagram);
                    diagram.setName(diagramName);
                    diagram.setElement((EObject)model);
                }
                try {
                    modelResource.save(StateflowDiagramEditorUtil.getSaveOptions());
                    diagramResource.save(StateflowDiagramEditorUtil.getSaveOptions());
                }
                catch (IOException e) {
                    StateflowDiagramEditorPlugin.getInstance().logError("Unable to store model and diagram resources", e);
                }
                return CommandResult.newOKCommandResult();
            }
        };
        try {
            OperationHistoryFactory.getOperationHistory().execute((IUndoableOperation)command, (IProgressMonitor)new SubProgressMonitor(progressMonitor, 1), null);
        }
        catch (ExecutionException e) {
            StateflowDiagramEditorPlugin.getInstance().logError("Unable to create model and diagram", e);
        }
        StateflowDiagramEditorUtil.setCharset(WorkspaceSynchronizer.getFile((Resource)modelResource));
        StateflowDiagramEditorUtil.setCharset(WorkspaceSynchronizer.getFile((Resource)diagramResource));
        return diagramResource;
    }

    private static StateFlow createInitialModel() {
        return StateflowFactory.eINSTANCE.createStateFlow();
    }

    private static void attachModelToResource(StateFlow model, Resource resource) {
        resource.getContents().add((Object)model);
    }

    public static void selectElementsInDiagram(IDiagramWorkbenchPart diagramPart, List editParts) {
        diagramPart.getDiagramGraphicalViewer().deselectAll();
        EditPart firstPrimary = null;
        for (EditPart nextPart : editParts) {
            diagramPart.getDiagramGraphicalViewer().appendSelection(nextPart);
            if (firstPrimary != null || !(nextPart instanceof IPrimaryEditPart)) continue;
            firstPrimary = nextPart;
        }
        if (!editParts.isEmpty()) {
            diagramPart.getDiagramGraphicalViewer().reveal(firstPrimary != null ? firstPrimary : (EditPart)editParts.get(0));
        }
    }

    private static int findElementsInDiagramByID(DiagramEditPart diagramPart, EObject element, List editPartCollector) {
        EditPart editPart;
        IDiagramGraphicalViewer viewer = (IDiagramGraphicalViewer)diagramPart.getViewer();
        int intialNumOfEditParts = editPartCollector.size();
        if (element instanceof View && (editPart = (EditPart)viewer.getEditPartRegistry().get(element)) != null) {
            editPartCollector.add(editPart);
            return 1;
        }
        String elementID = EMFCoreUtil.getProxyID((EObject)element);
        List associatedParts = viewer.findEditPartsForElement(elementID, IGraphicalEditPart.class);
        for (EditPart nextPart : associatedParts) {
            EditPart parentPart = nextPart.getParent();
            while (parentPart != null && !associatedParts.contains(parentPart)) {
                parentPart = parentPart.getParent();
            }
            if (parentPart != null) continue;
            editPartCollector.add(nextPart);
        }
        if (intialNumOfEditParts == editPartCollector.size()) {
            if (!associatedParts.isEmpty()) {
                editPartCollector.add(associatedParts.iterator().next());
            } else if (element.eContainer() != null) {
                return StateflowDiagramEditorUtil.findElementsInDiagramByID(diagramPart, element.eContainer(), editPartCollector);
            }
        }
        return editPartCollector.size() - intialNumOfEditParts;
    }

    public static View findView(DiagramEditPart diagramEditPart, EObject targetElement, LazyElement2ViewMap lazyElement2ViewMap) {
        boolean hasStructuralURI = false;
        if (targetElement.eResource() instanceof XMLResource) {
            hasStructuralURI = ((XMLResource)targetElement.eResource()).getID(targetElement) == null;
        }
        View view = null;
        if (hasStructuralURI && !lazyElement2ViewMap.getElement2ViewMap().isEmpty()) {
            view = (View)lazyElement2ViewMap.getElement2ViewMap().get(targetElement);
        } else if (StateflowDiagramEditorUtil.findElementsInDiagramByID(diagramEditPart, targetElement, lazyElement2ViewMap.editPartTmpHolder) > 0) {
            EditPart editPart = (EditPart)lazyElement2ViewMap.editPartTmpHolder.get(0);
            lazyElement2ViewMap.editPartTmpHolder.clear();
            view = editPart.getModel() instanceof View ? (View)editPart.getModel() : null;
        }
        return view == null ? diagramEditPart.getDiagramView() : view;
    }

    public static class LazyElement2ViewMap {
        private Map element2ViewMap;
        private View scope;
        private Set elementSet;
        public final List editPartTmpHolder = new ArrayList();

        public LazyElement2ViewMap(View scope, Set elements) {
            this.scope = scope;
            this.elementSet = elements;
        }

        public final Map getElement2ViewMap() {
            if (this.element2ViewMap == null) {
                this.element2ViewMap = new HashMap();
                for (EObject element : this.elementSet) {
                    View view;
                    if (!(element instanceof View) || (view = (View)element).getDiagram() != this.scope.getDiagram()) continue;
                    this.element2ViewMap.put(element, element);
                }
                LazyElement2ViewMap.buildElement2ViewMap(this.scope, this.element2ViewMap, this.elementSet);
            }
            return this.element2ViewMap;
        }

        static Map buildElement2ViewMap(View parentView, Map element2ViewMap, Set elements) {
            if (elements.size() == element2ViewMap.size()) {
                return element2ViewMap;
            }
            if (parentView.isSetElement() && !element2ViewMap.containsKey(parentView.getElement()) && elements.contains(parentView.getElement())) {
                element2ViewMap.put(parentView.getElement(), parentView);
                if (elements.size() == element2ViewMap.size()) {
                    return element2ViewMap;
                }
            }
            Iterator it = parentView.getChildren().iterator();
            while (it.hasNext()) {
                LazyElement2ViewMap.buildElement2ViewMap((View)it.next(), element2ViewMap, elements);
                if (elements.size() != element2ViewMap.size()) continue;
                return element2ViewMap;
            }
            it = parentView.getSourceEdges().iterator();
            while (it.hasNext()) {
                LazyElement2ViewMap.buildElement2ViewMap((View)it.next(), element2ViewMap, elements);
                if (elements.size() != element2ViewMap.size()) continue;
                return element2ViewMap;
            }
            it = parentView.getSourceEdges().iterator();
            while (it.hasNext()) {
                LazyElement2ViewMap.buildElement2ViewMap((View)it.next(), element2ViewMap, elements);
                if (elements.size() != element2ViewMap.size()) continue;
                return element2ViewMap;
            }
            return element2ViewMap;
        }
    }
}

