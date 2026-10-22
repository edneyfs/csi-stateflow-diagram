/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.commands.ExecutionException
 *  org.eclipse.core.commands.operations.IUndoableOperation
 *  org.eclipse.core.commands.operations.OperationHistoryFactory
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.core.runtime.IPath
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.core.runtime.NullProgressMonitor
 *  org.eclipse.core.runtime.Path
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.ecore.resource.ResourceSet
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.gmf.runtime.common.core.command.CommandResult
 *  org.eclipse.gmf.runtime.common.core.service.IOperation
 *  org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint
 *  org.eclipse.gmf.runtime.diagram.core.services.ViewService
 *  org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation
 *  org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand
 *  org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter
 *  org.eclipse.gmf.runtime.notation.Diagram
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.jface.viewers.StructuredSelection
 *  org.eclipse.jface.wizard.IWizardPage
 *  org.eclipse.jface.wizard.Wizard
 *  org.eclipse.osgi.util.NLS
 *  org.eclipse.ui.PartInitException
 *  org.eclipse.ui.dialogs.WizardNewFileCreationPage
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.part.Messages;
import com.csi.gmf.stateflow.diagram.part.ModelElementSelectionPage;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorUtil;
import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
import java.io.IOException;
import java.util.LinkedList;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class StateflowNewDiagramFileWizard
extends Wizard {
    private TransactionalEditingDomain myEditingDomain;
    private WizardNewFileCreationPage myFileCreationPage;
    private ModelElementSelectionPage diagramRootElementSelectionPage;

    public StateflowNewDiagramFileWizard(URI domainModelURI, EObject diagramRoot, TransactionalEditingDomain editingDomain) {
        Path filePath;
        assert (domainModelURI != null) : "Domain model uri must be specified";
        assert (diagramRoot != null) : "Doagram root element must be specified";
        assert (editingDomain != null) : "Editing domain must be specified";
        this.myFileCreationPage = new WizardNewFileCreationPage(Messages.StateflowNewDiagramFileWizard_CreationPageName, (IStructuredSelection)StructuredSelection.EMPTY);
        this.myFileCreationPage.setTitle(Messages.StateflowNewDiagramFileWizard_CreationPageTitle);
        this.myFileCreationPage.setDescription(NLS.bind((String)Messages.StateflowNewDiagramFileWizard_CreationPageDescription, (Object)"Stateflow"));
        String fileName = domainModelURI.trimFileExtension().lastSegment();
        if (domainModelURI.isPlatformResource()) {
            filePath = new Path(domainModelURI.trimSegments(1).toPlatformString(true));
        } else if (domainModelURI.isFile()) {
            filePath = new Path(domainModelURI.trimSegments(1).toFileString());
        } else {
            throw new IllegalArgumentException("Unsupported URI: " + domainModelURI);
        }
        this.myFileCreationPage.setContainerFullPath((IPath)filePath);
        this.myFileCreationPage.setFileName(StateflowDiagramEditorUtil.getUniqueFileName((IPath)filePath, fileName, "stateflow_diagram"));
        this.diagramRootElementSelectionPage = new DiagramRootElementSelectionPage(Messages.StateflowNewDiagramFileWizard_RootSelectionPageName);
        this.diagramRootElementSelectionPage.setTitle(Messages.StateflowNewDiagramFileWizard_RootSelectionPageTitle);
        this.diagramRootElementSelectionPage.setDescription(Messages.StateflowNewDiagramFileWizard_RootSelectionPageDescription);
        this.diagramRootElementSelectionPage.setModelElement(diagramRoot);
        this.myEditingDomain = editingDomain;
    }

    public void addPages() {
        this.addPage((IWizardPage)this.myFileCreationPage);
        this.addPage((IWizardPage)this.diagramRootElementSelectionPage);
    }

    public boolean performFinish() {
        LinkedList<IFile> affectedFiles = new LinkedList<IFile>();
        IFile diagramFile = this.myFileCreationPage.createNewFile();
        StateflowDiagramEditorUtil.setCharset(diagramFile);
        affectedFiles.add(diagramFile);
        URI diagramModelURI = URI.createPlatformResourceURI((String)diagramFile.getFullPath().toString(), (boolean)true);
        ResourceSet resourceSet = this.myEditingDomain.getResourceSet();
        final Resource diagramResource = resourceSet.createResource(diagramModelURI);
        AbstractTransactionalCommand command = new AbstractTransactionalCommand(this.myEditingDomain, Messages.StateflowNewDiagramFileWizard_InitDiagramCommand, affectedFiles){

            protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
                int diagramVID = StateflowVisualIDRegistry.getDiagramVisualID(StateflowNewDiagramFileWizard.this.diagramRootElementSelectionPage.getModelElement());
                if (diagramVID != 79) {
                    return CommandResult.newErrorCommandResult((String)Messages.StateflowNewDiagramFileWizard_IncorrectRootError);
                }
                Diagram diagram = ViewService.createDiagram((EObject)StateflowNewDiagramFileWizard.this.diagramRootElementSelectionPage.getModelElement(), (String)"Stateflow", (PreferencesHint)StateflowDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
                diagramResource.getContents().add((Object)diagram);
                return CommandResult.newOKCommandResult();
            }
        };
        try {
            OperationHistoryFactory.getOperationHistory().execute((IUndoableOperation)command, (IProgressMonitor)new NullProgressMonitor(), null);
            diagramResource.save(StateflowDiagramEditorUtil.getSaveOptions());
            StateflowDiagramEditorUtil.openDiagram(diagramResource);
        }
        catch (ExecutionException e) {
            StateflowDiagramEditorPlugin.getInstance().logError("Unable to create model and diagram", e);
        }
        catch (IOException ex) {
            StateflowDiagramEditorPlugin.getInstance().logError("Save operation failed for: " + diagramModelURI, ex);
        }
        catch (PartInitException ex) {
            StateflowDiagramEditorPlugin.getInstance().logError("Unable to open editor", ex);
        }
        return true;
    }

    private static class DiagramRootElementSelectionPage
    extends ModelElementSelectionPage {
        protected DiagramRootElementSelectionPage(String pageName) {
            super(pageName);
        }

        @Override
        protected String getSelectionTitle() {
            return Messages.StateflowNewDiagramFileWizard_RootSelectionPageSelectionTitle;
        }

        @Override
        protected boolean validatePage() {
            if (this.selectedModelElement == null) {
                this.setErrorMessage(Messages.StateflowNewDiagramFileWizard_RootSelectionPageNoSelectionMessage);
                return false;
            }
            boolean result = ViewService.getInstance().provides((IOperation)new CreateDiagramViewOperation((IAdaptable)new EObjectAdapter(this.selectedModelElement), "Stateflow", StateflowDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT));
            this.setErrorMessage(result ? null : Messages.StateflowNewDiagramFileWizard_RootSelectionPageInvalidSelectionMessage);
            return result;
        }
    }
}

