/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.impl.ExecutableFlowObjectImpl
 *  org.eclipse.core.internal.resources.File
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IProject
 *  org.eclipse.core.resources.IResource
 *  org.eclipse.core.resources.ResourcesPlugin
 *  org.eclipse.core.runtime.CoreException
 *  org.eclipse.gmf.runtime.notation.impl.NodeImpl
 *  org.eclipse.jface.action.IAction
 *  org.eclipse.jface.operation.IRunnableContext
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.search.ui.ISearchQuery
 *  org.eclipse.search.ui.NewSearchUI
 *  org.eclipse.search.ui.text.AbstractTextSearchResult
 *  org.eclipse.search.ui.text.FileTextSearchScope
 *  org.eclipse.search.ui.text.TextSearchQueryProvider
 *  org.eclipse.search.ui.text.TextSearchQueryProvider$TextSearchInput
 *  org.eclipse.ui.IObjectActionDelegate
 *  org.eclipse.ui.IWorkbenchPart
 *  org.eclipse.ui.PlatformUI
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.edit.parts.ExecutableFlowObjectEditPart;
import com.csi.gmf.executionflow.diagram.part.ExecutionFlowSearchInput;
import com.csi.gmf.executionflow.impl.ExecutableFlowObjectImpl;
import java.util.ArrayList;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.FileTextSearchScope;
import org.eclipse.search.ui.text.TextSearchQueryProvider;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class ExecutableFlowObjectSearchAction
implements IObjectActionDelegate {
    private final String FLOW_EXTENSION = ".executionflow";
    private final String FLOW_DIAGRAM_EXTENSION = ".executionflow_diagram";
    private IStructuredSelection mySelectedElement;

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    public void run(IAction action) {
        ArrayList<IResource> resourceList = new ArrayList<IResource>();
        IProject project = null;
        IResource resource = null;
        String searchClassName = "";
        String diagramFile = "";
        String projectName = "";
        try {
            if (this.mySelectedElement.getFirstElement() instanceof ExecutableFlowObjectEditPart) {
                ExecutableFlowObjectEditPart editPart = (ExecutableFlowObjectEditPart)((Object)this.mySelectedElement.getFirstElement());
                ExecutableFlowObjectImpl executable = (ExecutableFlowObjectImpl)((NodeImpl)editPart.getModel()).getElement();
                diagramFile = executable.eContainer().eResource().getURI().path();
                searchClassName = executable.getSimpleClassName();
                projectName = diagramFile.substring(10, diagramFile.length());
                projectName = projectName.substring(0, projectName.indexOf("/"));
                project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
            } else if (this.mySelectedElement.getFirstElement() instanceof File) {
                File fileToSearch = (File)this.mySelectedElement.getFirstElement();
                searchClassName = fileToSearch.getName().substring(0, fileToSearch.getName().lastIndexOf("."));
                project = fileToSearch.getProject();
            }
            FileTextSearchScope scope = FileTextSearchScope.newSearchScope((IResource[])new IResource[]{project}, (String[])new String[]{"*.executionflow"}, (boolean)true);
            ExecutionFlowSearchInput searchInput = new ExecutionFlowSearchInput("\"" + searchClassName + "\"", false, true, scope);
            ISearchQuery query = TextSearchQueryProvider.getPreferred().createQuery((TextSearchQueryProvider.TextSearchInput)searchInput);
            NewSearchUI.runQueryInForeground((IRunnableContext)PlatformUI.getWorkbench().getActiveWorkbenchWindow(), (ISearchQuery)query);
            AbstractTextSearchResult results = (AbstractTextSearchResult)NewSearchUI.getQueries()[0].getSearchResult();
            int i = 0;
            while (i < results.getElements().length) {
                IFile file = (IFile)results.getElements()[i];
                if (file.getName().endsWith(".executionflow")) {
                    String resourcePath = file.toString().replaceAll(".executionflow", ".executionflow_diagram");
                    resource = project.findMember(resourcePath = resourcePath.substring(resourcePath.lastIndexOf(project.getFullPath().toString()) + project.getFullPath().toString().length(), resourcePath.length()));
                    if (resource != null) {
                        resourceList.add(resource);
                    }
                }
                ++i;
            }
            IResource[] resourcesSelected = new IResource[resourceList.size()];
            Object[] resourcesTmp = resourceList.toArray();
            int i2 = 0;
            while (i2 < resourcesTmp.length) {
                resourcesSelected[i2] = (IResource)resourcesTmp[i2];
                ++i2;
            }
            query = TextSearchQueryProvider.getPreferred().createQuery("", resourcesSelected);
            NewSearchUI.runQueryInForeground((IRunnableContext)PlatformUI.getWorkbench().getActiveWorkbenchWindow(), (ISearchQuery)query);
        }
        catch (CoreException e) {
            e.printStackTrace();
        }
    }

    public void selectionChanged(IAction action, ISelection selection) {
        this.mySelectedElement = null;
        if (selection instanceof IStructuredSelection) {
            this.mySelectedElement = (IStructuredSelection)selection;
        }
        action.setEnabled(this.isEnabled());
    }

    private boolean isEnabled() {
        return this.mySelectedElement != null;
    }
}

