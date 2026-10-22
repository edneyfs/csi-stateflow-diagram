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
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.part.Messages;
import com.csi.gmf.stateflow.diagram.part.StateflowCreationWizardPage;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorUtil;
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

public class StateflowCreationWizard
extends Wizard
implements INewWizard {
    private IWorkbench workbench;
    protected IStructuredSelection selection;
    protected StateflowCreationWizardPage diagramModelFilePage;
    protected StateflowCreationWizardPage domainModelFilePage;
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
        this.diagramModelFilePage = new StateflowCreationWizardPage("DiagramModelFile", this.getSelection(), "stateflow_diagram");
        this.diagramModelFilePage.setTitle(Messages.StateflowCreationWizard_DiagramModelFilePageTitle);
        this.diagramModelFilePage.setDescription(Messages.StateflowCreationWizard_DiagramModelFilePageDescription);
        this.addPage((IWizardPage)this.diagramModelFilePage);
        this.domainModelFilePage = new StateflowCreationWizardPage("DomainModelFile", this.getSelection(), "stateflow"){

            public void setVisible(boolean visible) {
                if (visible) {
                    String fileName = StateflowCreationWizard.this.diagramModelFilePage.getFileName();
                    fileName = fileName.substring(0, fileName.length() - ".stateflow_diagram".length());
                    this.setFileName(StateflowDiagramEditorUtil.getUniqueFileName(this.getContainerFullPath(), fileName, "stateflow"));
                }
                super.setVisible(visible);
            }
        };
        this.domainModelFilePage.setTitle(Messages.StateflowCreationWizard_DomainModelFilePageTitle);
        this.domainModelFilePage.setDescription(Messages.StateflowCreationWizard_DomainModelFilePageDescription);
        this.addPage((IWizardPage)this.domainModelFilePage);
    }

    public boolean performFinish() {
        WorkspaceModifyOperation op = new WorkspaceModifyOperation(null){

            protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {
                StateflowCreationWizard.this.diagram = StateflowDiagramEditorUtil.createDiagram(StateflowCreationWizard.this.diagramModelFilePage.getURI(), StateflowCreationWizard.this.domainModelFilePage.getURI(), monitor);
                if (StateflowCreationWizard.this.isOpenNewlyCreatedDiagramEditor() && StateflowCreationWizard.this.diagram != null) {
                    try {
                        StateflowDiagramEditorUtil.openDiagram(StateflowCreationWizard.this.diagram);
                    }
                    catch (PartInitException e) {
                        ErrorDialog.openError((Shell)StateflowCreationWizard.this.getContainer().getShell(), (String)Messages.StateflowCreationWizardOpenEditorError, null, (IStatus)e.getStatus());
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
                ErrorDialog.openError((Shell)this.getContainer().getShell(), (String)Messages.StateflowCreationWizardCreationError, null, (IStatus)((CoreException)e.getTargetException()).getStatus());
            } else {
                StateflowDiagramEditorPlugin.getInstance().logError("Error creating diagram", e.getTargetException());
            }
            return false;
        }
        return this.diagram != null;
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        this.setWindowTitle(Messages.StateflowCreationWizardTitle);
        this.setDefaultPageImageDescriptor(StateflowDiagramEditorPlugin.getBundledImageDescriptor("icons/wizban/NewStateflowWizard.gif"));
        this.setNeedsProgressMonitor(true);
    }
}

