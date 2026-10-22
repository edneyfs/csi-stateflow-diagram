/**
 * 
 */
package com.csi.gmf.stateflow.diagram.part;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.FileTextSearchScope;
import org.eclipse.search.ui.text.TextSearchQueryProvider;
import org.eclipse.ui.PlatformUI;

/**
 * @author gustav.monteiro
 * @generated NOT
 */
public class StateFlowDiagramSearchAssistant {
	
	/**
	 * @generated NOT
	 */
	private static StateFlowDiagramSearchAssistant instance;
	
	/**
	 * @generated NOT
	 */
	private StateFlowDiagramSearchAssistant() {
	}
	
	/**
	 * @generated NOT
	 */
	public static StateFlowDiagramSearchAssistant getInstance(){
		if (instance==null) {
			instance = new StateFlowDiagramSearchAssistant();
		}
		return instance;
	}
	
	/**
	 * @generated NOT
	 */
	private class StateSearchInput extends TextSearchQueryProvider.TextSearchInput {

		/**
		 * @generated NOT
		 */
		private final String fSearchText;
		/**
		 * @generated NOT
		 */
		private final boolean fIsCaseSensitive;
		/**
		 * @generated NOT
		 */
		private final boolean fIsRegEx;
		/**
		 * @generated NOT
		 */
		private final FileTextSearchScope fScope;

		/**
		 * @generated NOT
		 */
		public StateSearchInput(String searchText, boolean isCaseSensitive, boolean isRegEx, FileTextSearchScope scope) {
			fSearchText = searchText;
			fIsCaseSensitive = isCaseSensitive;
			fIsRegEx = isRegEx;
			fScope = scope;
		}
		
		/**
		 * @generated NOT
		 */
		public String getSearchText() {
			return fSearchText;
		}

		/**
		 * @generated NOT
		 */
		public boolean isCaseSensitiveSearch() {
			return fIsCaseSensitive;
		}

		/**
		 * @generated NOT
		 */
		public boolean isRegExSearch() {
			return fIsRegEx;
		}

		/**
		 * @generated NOT
		 */
		public FileTextSearchScope getScope() {
			return fScope;
		}
	}

	/**
	 * @generated NOT
	 * @param element
	 * @param searchText
	 * @param searchExtension
	 * @param listExtension
	 */
	public void search(String path, String searchText, String searchExtension, String listExtension) {
		ArrayList resourceList = new ArrayList();
		
		IResource resource     = null;
		
		String diagramFile     = path;
		
		String projectName     = "";
		
		projectName = diagramFile.substring(10, diagramFile.length());
		projectName = projectName.substring(0, projectName.indexOf("/"));
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		
		FileTextSearchScope scope = FileTextSearchScope.newSearchScope(new IResource[] {project}, new String[] {"*"+searchExtension}, true);

		StateSearchInput searchInput = this.new StateSearchInput("\""+searchText+"\"", false, true, scope);

		try {
			ISearchQuery query = TextSearchQueryProvider.getPreferred().createQuery(searchInput);
			
			NewSearchUI.runQueryInForeground(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), query);

			AbstractTextSearchResult results = (AbstractTextSearchResult)NewSearchUI.getQueries()[0].getSearchResult();
			
			for (int i = 0; i < results.getElements().length; i++) {
				
				IFile file = (IFile)results.getElements()[i];
				 
				if (file.getName().endsWith(searchExtension)) { 

					String resourcePath = file.toString().replaceAll(searchExtension, listExtension);
						
					resourcePath = resourcePath.substring(resourcePath.lastIndexOf(project.getFullPath().toString())+project.getFullPath().toString().length(), 
								resourcePath.length());

					resource = project.findMember(resourcePath);
					
						if (resource !=null)
							resourceList.add(resource);
				}
			}

			IResource[] resourcesSelected = new IResource[resourceList.size()];
			
			Object[] resourcesTmp = resourceList.toArray();
			
			for (int i = 0; i < resourcesTmp.length; i++) {
				resourcesSelected[i] = (IResource)resourcesTmp[i];
			}
			
			query = TextSearchQueryProvider.getPreferred().createQuery("",	resourcesSelected);
			
			NewSearchUI.runQueryInForeground(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), query);

		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
}
