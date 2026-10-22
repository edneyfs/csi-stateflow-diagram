/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.impl.ExecutableFlowObjectImpl
 *  org.eclipse.core.resources.IProject
 *  org.eclipse.core.resources.ResourcesPlugin
 *  org.eclipse.core.runtime.CoreException
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jdt.core.IJavaElement
 *  org.eclipse.jdt.core.IJavaProject
 *  org.eclipse.jdt.core.IType
 *  org.eclipse.jdt.internal.core.JavaModelManager
 *  org.eclipse.jdt.ui.JavaUI
 */
package com.csi.gmf.executionflow.diagram.edit.policies;

import com.csi.gmf.executionflow.impl.ExecutableFlowObjectImpl;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.internal.core.JavaModelManager;
import org.eclipse.jdt.ui.JavaUI;

public class ExecutableFlowObjectOpenEditPolicy
extends OpenEditPolicy {
    protected Command getOpenCommand(Request request) {
        ExecutableFlowObjectImpl executable = (ExecutableFlowObjectImpl)((View)this.getHost().getModel()).getElement();
        IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        int i = 0;
        while (i < projects.length) {
            IProject project = projects[i];
            if (projects[i].isOpen()) {
                try {
                    IJavaProject javaProject = JavaModelManager.getJavaModelManager().getJavaModel().getJavaProject(project.getName());
                    IType type = javaProject.findType(executable.getClassName());
                    JavaUI.openInEditor((IJavaElement)type);
                }
                catch (CoreException e) {
                    e.printStackTrace();
                }
            }
            ++i;
        }
        return null;
    }
}

