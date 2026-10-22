/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.CoreException
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.core.runtime.IStatus
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.jface.dialogs.ErrorDialog
 *  org.eclipse.jface.operation.IRunnableWithProgress
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.jface.wizard.IWizardPage
 *  org.eclipse.jface.wizard.Wizard
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.ui.INewWizard
 *  org.eclipse.ui.IWorkbench
 *  org.eclipse.ui.PartInitException
 *  org.eclipse.ui.actions.WorkspaceModifyOperation
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowCreationWizardPage;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorUtil;
import com.csi.gmf.executionflow.diagram.part.Messages;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

public class ExecutionflowCreationWizard
extends Wizard
implements INewWizard {
    private IWorkbench workbench;
    protected IStructuredSelection selection;
    protected ExecutionflowCreationWizardPage diagramModelFilePage;
    protected ExecutionflowCreationWizardPage domainModelFilePage;
    protected Resource diagram;
    private boolean openNewlyCreatedDiagramEditor = true;

    public IWorkbench getWorkbench() {
        return this.workbench;
    }

    public IStructuredSelection getSelection() {
        return this.selection;
    }

    public final Resource getDiagram() {
        return this.diagram;
    }

    public final boolean isOpenNewlyCreatedDiagramEditor() {
        return this.openNewlyCreatedDiagramEditor;
    }

    public void setOpenNewlyCreatedDiagramEditor(boolean openNewlyCreatedDiagramEditor) {
        this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
    }

    public void addPages() {
        this.diagramModelFilePage = new ExecutionflowCreationWizardPage("DiagramModelFile", this.getSelection(), "executionflow_diagram");
        this.diagramModelFilePage.setTitle(Messages.ExecutionflowCreationWizard_DiagramModelFilePageTitle);
        this.diagramModelFilePage.setDescription(Messages.ExecutionflowCreationWizard_DiagramModelFilePageDescription);
        this.addPage((IWizardPage)this.diagramModelFilePage);
        this.domainModelFilePage = new ExecutionflowCreationWizardPage("DomainModelFile", this.getSelection(), "executionflow"){

            public void setVisible(boolean visible) {
                if (visible) {
                    String fileName = ExecutionflowCreationWizard.this.diagramModelFilePage.getFileName();
                    fileName = fileName.substring(0, fileName.length() - ".executionflow_diagram".length());
                    this.setFileName(ExecutionflowDiagramEditorUtil.getUniqueFileName(this.getContainerFullPath(), fileName, "executionflow"));
                }
                super.setVisible(visible);
            }
        };
        this.domainModelFilePage.setTitle(Messages.ExecutionflowCreationWizard_DomainModelFilePageTitle);
        this.domainModelFilePage.setDescription(Messages.ExecutionflowCreationWizard_DomainModelFilePageDescription);
        this.addPage((IWizardPage)this.domainModelFilePage);
    }

    public boolean performFinish() {
        WorkspaceModifyOperation op = new WorkspaceModifyOperation(null){

            protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {
                ExecutionflowCreationWizard.this.diagram = ExecutionflowDiagramEditorUtil.createDiagram(ExecutionflowCreationWizard.this.diagramModelFilePage.getURI(), ExecutionflowCreationWizard.this.domainModelFilePage.getURI(), monitor);
                if (ExecutionflowCreationWizard.this.isOpenNewlyCreatedDiagramEditor() && ExecutionflowCreationWizard.this.diagram != null) {
                    try {
                        ExecutionflowDiagramEditorUtil.openDiagram(ExecutionflowCreationWizard.this.diagram);
                    }
                    catch (PartInitException e) {
                        ErrorDialog.openError((Shell)ExecutionflowCreationWizard.this.getContainer().getShell(), (String)Messages.ExecutionflowCreationWizardOpenEditorError, null, (IStatus)e.getStatus());
                    }
                }
            }
        };
        try {
            this.getContainer().run(false, true, (IRunnableWithProgress)op);
        }
        catch (InterruptedException interruptedException) {
            return false;
        }
        catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog.openError((Shell)this.getContainer().getShell(), (String)Messages.ExecutionflowCreationWizardCreationError, null, (IStatus)((CoreException)e.getTargetException()).getStatus());
            } else {
                ExecutionflowDiagramEditorPlugin.getInstance().logError("Error creating diagram", e.getTargetException());
            }
            return false;
        }
        return this.diagram != null;
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        this.setWindowTitle(Messages.ExecutionflowCreationWizardTitle);
        this.setDefaultPageImageDescriptor(ExecutionflowDiagramEditorPlugin.getBundledImageDescriptor("icons/wizban/NewExecutionflowWizard.gif"));
        this.setNeedsProgressMonitor(true);
    }
}

