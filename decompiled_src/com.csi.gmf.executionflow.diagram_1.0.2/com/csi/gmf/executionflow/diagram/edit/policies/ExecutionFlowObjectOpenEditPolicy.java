/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionFlowObject
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IProject
 *  org.eclipse.core.resources.ResourcesPlugin
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.ui.IWorkbenchPage
 *  org.eclipse.ui.PartInitException
 *  org.eclipse.ui.PlatformUI
 *  org.eclipse.ui.ide.IDE
 */
package com.csi.gmf.executionflow.diagram.edit.policies;

import com.csi.gmf.executionflow.ExecutionFlowObject;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class ExecutionFlowObjectOpenEditPolicy
extends OpenEditPolicy {
    private final String DIAGRAM_SUFIX = "_diagram";

    protected Command getOpenCommand(Request arg0) {
        ExecutionFlowObject flow = (ExecutionFlowObject)((View)this.getHost().getModel()).getElement();
        String diagramFile = "";
        String projectName = "";
        diagramFile = flow.eContainer().eResource().getURI().path();
        projectName = diagramFile.substring(10, diagramFile.length());
        projectName = projectName.substring(0, projectName.indexOf("/"));
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        diagramFile = String.valueOf(diagramFile.substring(diagramFile.lastIndexOf(project.getName()) + project.getName().length(), diagramFile.lastIndexOf("/") + 1)) + flow.getFilePath() + "_diagram";
        try {
            IDE.openEditor((IWorkbenchPage)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile)project.getFile(diagramFile));
        }
        catch (PartInitException e) {
            e.printStackTrace();
        }
        return null;
    }
}

