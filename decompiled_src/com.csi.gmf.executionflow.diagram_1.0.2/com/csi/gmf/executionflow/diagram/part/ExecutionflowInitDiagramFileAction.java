/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.emf.common.util.WrappedException
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.ecore.resource.ResourceSet
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory
 *  org.eclipse.jface.action.IAction
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.osgi.util.NLS
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.ui.IObjectActionDelegate
 *  org.eclipse.ui.IWorkbenchPart
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorUtil;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowNewDiagramFileWizard;
import com.csi.gmf.executionflow.diagram.part.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ExecutionflowInitDiagramFileAction
implements IObjectActionDelegate {
    private IWorkbenchPart targetPart;
    private URI domainModelURI;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.targetPart = targetPart;
    }

    public void selectionChanged(IAction action, ISelection selection) {
        this.domainModelURI = null;
        action.setEnabled(false);
        if (!(selection instanceof IStructuredSelection) || selection.isEmpty()) {
            return;
        }
        IFile file = (IFile)((IStructuredSelection)selection).getFirstElement();
        this.domainModelURI = URI.createPlatformResourceURI((String)file.getFullPath().toString(), (boolean)true);
        action.setEnabled(true);
    }

    private Shell getShell() {
        return this.targetPart.getSite().getShell();
    }

    public void run(IAction action) {
        TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
        ResourceSet resourceSet = editingDomain.getResourceSet();
        EObject diagramRoot = null;
        try {
            Resource resource = resourceSet.getResource(this.domainModelURI, true);
            diagramRoot = (EObject)resource.getContents().get(0);
        }
        catch (WrappedException ex) {
            ExecutionflowDiagramEditorPlugin.getInstance().logError("Unable to load resource: " + this.domainModelURI, ex);
        }
        if (diagramRoot == null) {
            MessageDialog.openError((Shell)this.getShell(), (String)Messages.ExecutionflowInitDiagramFileAction_InitDiagramFileResourceErrorDialogTitle, (String)Messages.ExecutionflowInitDiagramFileAction_InitDiagramFileResourceErrorDialogMessage);
            return;
        }
        ExecutionflowNewDiagramFileWizard wizard = new ExecutionflowNewDiagramFileWizard(this.domainModelURI, diagramRoot, editingDomain);
        wizard.setWindowTitle(NLS.bind((String)Messages.ExecutionflowInitDiagramFileAction_InitDiagramFileWizardTitle, (Object)"Executionflow"));
        ExecutionflowDiagramEditorUtil.runWizard(this.getShell(), wizard, "InitDiagramFile");
    }
}

