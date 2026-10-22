/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IProject
 *  org.eclipse.core.resources.IResource
 *  org.eclipse.core.resources.ResourcesPlugin
 *  org.eclipse.core.runtime.CoreException
 *  org.eclipse.jface.operation.IRunnableContext
 *  org.eclipse.search.ui.ISearchQuery
 *  org.eclipse.search.ui.NewSearchUI
 *  org.eclipse.search.ui.text.AbstractTextSearchResult
 *  org.eclipse.search.ui.text.FileTextSearchScope
 *  org.eclipse.search.ui.text.TextSearchQueryProvider
 *  org.eclipse.search.ui.text.TextSearchQueryProvider$TextSearchInput
 *  org.eclipse.ui.PlatformUI
 */
package com.csi.gmf.stateflow.diagram.part;

import java.util.ArrayList;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.FileTextSearchScope;
import org.eclipse.search.ui.text.TextSearchQueryProvider;
import org.eclipse.ui.PlatformUI;

public class StateFlowDiagramSearchAssistant {
    private static StateFlowDiagramSearchAssistant instance;

    private StateFlowDiagramSearchAssistant() {
    }

    public static StateFlowDiagramSearchAssistant getInstance() {
        if (instance == null) {
            instance = new StateFlowDiagramSearchAssistant();
        }
        return instance;
    }

    public void search(String path, String searchText, String searchExtension, String listExtension) {
        ArrayList<IResource> resourceList = new ArrayList<IResource>();
        IResource resource = null;
        String diagramFile = path;
        String projectName = "";
        projectName = diagramFile.substring(10, diagramFile.length());
        projectName = projectName.substring(0, projectName.indexOf("/"));
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        FileTextSearchScope scope = FileTextSearchScope.newSearchScope((IResource[])new IResource[]{project}, (String[])new String[]{"*" + searchExtension}, (boolean)true);
        StateFlowDiagramSearchAssistant stateFlowDiagramSearchAssistant = this;
        stateFlowDiagramSearchAssistant.getClass();
        StateSearchInput searchInput = stateFlowDiagramSearchAssistant.new StateSearchInput("\"" + searchText + "\"", false, true, scope);
        try {
            ISearchQuery query = TextSearchQueryProvider.getPreferred().createQuery((TextSearchQueryProvider.TextSearchInput)searchInput);
            NewSearchUI.runQueryInForeground((IRunnableContext)PlatformUI.getWorkbench().getActiveWorkbenchWindow(), (ISearchQuery)query);
            AbstractTextSearchResult results = (AbstractTextSearchResult)NewSearchUI.getQueries()[0].getSearchResult();
            int i = 0;
            while (i < results.getElements().length) {
                IFile file = (IFile)results.getElements()[i];
                if (file.getName().endsWith(searchExtension)) {
                    String resourcePath = file.toString().replaceAll(searchExtension, listExtension);
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

    private class StateSearchInput
    extends TextSearchQueryProvider.TextSearchInput {
        private final String fSearchText;
        private final boolean fIsCaseSensitive;
        private final boolean fIsRegEx;
        private final FileTextSearchScope fScope;

        public StateSearchInput(String searchText, boolean isCaseSensitive, boolean isRegEx, FileTextSearchScope scope) {
            this.fSearchText = searchText;
            this.fIsCaseSensitive = isCaseSensitive;
            this.fIsRegEx = isRegEx;
            this.fScope = scope;
        }

        public String getSearchText() {
            return this.fSearchText;
        }

        public boolean isCaseSensitiveSearch() {
            return this.fIsCaseSensitive;
        }

        public boolean isRegExSearch() {
            return this.fIsRegEx;
        }

        public FileTextSearchScope getScope() {
            return this.fScope;
        }
    }
}

