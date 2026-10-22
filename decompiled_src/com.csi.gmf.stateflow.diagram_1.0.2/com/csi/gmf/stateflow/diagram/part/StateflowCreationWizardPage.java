/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IPath
 *  org.eclipse.core.runtime.Path
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.osgi.util.NLS
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.ui.dialogs.WizardNewFileCreationPage
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.part.Messages;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorUtil;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class StateflowCreationWizardPage
extends WizardNewFileCreationPage {
    private final String fileExtension;

    public StateflowCreationWizardPage(String pageName, IStructuredSelection selection, String fileExtension) {
        super(pageName, selection);
        this.fileExtension = fileExtension;
    }

    protected String getExtension() {
        return this.fileExtension;
    }

    public URI getURI() {
        return URI.createPlatformResourceURI((String)this.getFilePath().toString(), (boolean)false);
    }

    protected IPath getFilePath() {
        String fileName;
        IPath path = this.getContainerFullPath();
        if (path == null) {
            path = new Path("");
        }
        if ((fileName = this.getFileName()) != null) {
            path = path.append(fileName);
        }
        return path;
    }

    public void createControl(Composite parent) {
        super.createControl(parent);
        this.setFileName(StateflowDiagramEditorUtil.getUniqueFileName(this.getContainerFullPath(), this.getFileName(), this.getExtension()));
        this.setPageComplete(this.validatePage());
    }

    protected boolean validatePage() {
        if (!super.validatePage()) {
            return false;
        }
        String extension = this.getExtension();
        if (extension != null && !this.getFilePath().toString().endsWith("." + extension)) {
            this.setErrorMessage(NLS.bind((String)Messages.StateflowCreationWizardPageExtensionError, (Object)extension));
            return false;
        }
        return true;
    }
}

