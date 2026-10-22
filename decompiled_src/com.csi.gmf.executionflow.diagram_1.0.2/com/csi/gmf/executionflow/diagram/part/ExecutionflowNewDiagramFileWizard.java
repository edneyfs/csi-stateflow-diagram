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
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorUtil;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import com.csi.gmf.executionflow.diagram.part.Messages;
import com.csi.gmf.executionflow.diagram.part.ModelElementSelectionPage;
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

public class ExecutionflowNewDiagramFileWizard
extends Wizard {
    private TransactionalEditingDomain myEditingDomain;
    private WizardNewFileCreationPage myFileCreationPage;
    private ModelElementSelectionPage diagramRootElementSelectionPage;

    public ExecutionflowNewDiagramFileWizard(URI domainModelURI, EObject diagramRoot, TransactionalEditingDomain editingDomain) {
        Path filePath;
        assert (domainModelURI != null) : "Domain model uri must be specified";
        assert (diagramRoot != null) : "Doagram root element must be specified";
        assert (editingDomain != null) : "Editing domain must be specified";
        this.myFileCreationPage = new WizardNewFileCreationPage(Messages.ExecutionflowNewDiagramFileWizard_CreationPageName, (IStructuredSelection)StructuredSelection.EMPTY);
        this.myFileCreationPage.setTitle(Messages.ExecutionflowNewDiagramFileWizard_CreationPageTitle);
        this.myFileCreationPage.setDescription(NLS.bind((String)Messages.ExecutionflowNewDiagramFileWizard_CreationPageDescription, (Object)"Executionflow"));
        String fileName = domainModelURI.trimFileExtension().lastSegment();
        if (domainModelURI.isPlatformResource()) {
            filePath = new Path(domainModelURI.trimSegments(1).toPlatformString(true));
        } else if (domainModelURI.isFile()) {
            filePath = new Path(domainModelURI.trimSegments(1).toFileString());
        } else {
            throw new IllegalArgumentException("Unsupported URI: " + domainModelURI);
        }
        this.myFileCreationPage.setContainerFullPath((IPath)filePath);
        this.myFileCreationPage.setFileName(ExecutionflowDiagramEditorUtil.getUniqueFileName((IPath)filePath, fileName, "executionflow_diagram"));
        this.diagramRootElementSelectionPage = new DiagramRootElementSelectionPage(Messages.ExecutionflowNewDiagramFileWizard_RootSelectionPageName);
        this.diagramRootElementSelectionPage.setTitle(Messages.ExecutionflowNewDiagramFileWizard_RootSelectionPageTitle);
        this.diagramRootElementSelectionPage.setDescription(Messages.ExecutionflowNewDiagramFileWizard_RootSelectionPageDescription);
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
        ExecutionflowDiagramEditorUtil.setCharset(diagramFile);
        affectedFiles.add(diagramFile);
        URI diagramModelURI = URI.createPlatformResourceURI((String)diagramFile.getFullPath().toString(), (boolean)true);
        ResourceSet resourceSet = this.myEditingDomain.getResourceSet();
        final Resource diagramResource = resourceSet.createResource(diagramModelURI);
        AbstractTransactionalCommand command = new AbstractTransactionalCommand(this.myEditingDomain, Messages.ExecutionflowNewDiagramFileWizard_InitDiagramCommand, affectedFiles){

            protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
                int diagramVID = ExecutionflowVisualIDRegistry.getDiagramVisualID(ExecutionflowNewDiagramFileWizard.this.diagramRootElementSelectionPage.getModelElement());
                if (diagramVID != 79) {
                    return CommandResult.newErrorCommandResult((String)Messages.ExecutionflowNewDiagramFileWizard_IncorrectRootError);
                }
                Diagram diagram = ViewService.createDiagram((EObject)ExecutionflowNewDiagramFileWizard.this.diagramRootElementSelectionPage.getModelElement(), (String)"Executionflow", (PreferencesHint)ExecutionflowDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
                diagramResource.getContents().add((Object)diagram);
                return CommandResult.newOKCommandResult();
            }
        };
        try {
            OperationHistoryFactory.getOperationHistory().execute((IUndoableOperation)command, (IProgressMonitor)new NullProgressMonitor(), null);
            diagramResource.save(ExecutionflowDiagramEditorUtil.getSaveOptions());
            ExecutionflowDiagramEditorUtil.openDiagram(diagramResource);
        }
        catch (ExecutionException e) {
            ExecutionflowDiagramEditorPlugin.getInstance().logError("Unable to create model and diagram", e);
        }
        catch (IOException ex) {
            ExecutionflowDiagramEditorPlugin.getInstance().logError("Save operation failed for: " + diagramModelURI, ex);
        }
        catch (PartInitException ex) {
            ExecutionflowDiagramEditorPlugin.getInstance().logError("Unable to open editor", ex);
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
            return Messages.ExecutionflowNewDiagramFileWizard_RootSelectionPageSelectionTitle;
        }

        @Override
        protected boolean validatePage() {
            if (this.selectedModelElement == null) {
                this.setErrorMessage(Messages.ExecutionflowNewDiagramFileWizard_RootSelectionPageNoSelectionMessage);
                return false;
            }
            boolean result = ViewService.getInstance().provides((IOperation)new CreateDiagramViewOperation((IAdaptable)new EObjectAdapter(this.selectedModelElement), "Executionflow", ExecutionflowDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT));
            this.setErrorMessage(result ? null : Messages.ExecutionflowNewDiagramFileWizard_RootSelectionPageInvalidSelectionMessage);
            return result;
        }
    }
}

