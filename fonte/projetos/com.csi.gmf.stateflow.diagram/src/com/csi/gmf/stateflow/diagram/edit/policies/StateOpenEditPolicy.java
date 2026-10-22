/**
 * 
 */
package com.csi.gmf.stateflow.diagram.edit.policies;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.csi.gmf.stateflow.State;

/**
 * @author gustav.monteiro
 * @generated NOT
 */
public class StateOpenEditPolicy extends OpenEditPolicy {

	private final String DIAGRAM_EXTENSION = ".stateflow_diagram";
	
	/**
	 * @generated NOT 
	 */
	protected Command getOpenCommand(Request arg0) {
		
		State state = (State)((View)this.getHost().getModel()).getElement();

		// Recupera a uri do arquivo do diagrama atual
		String diagramFile = "";
		
		String projectName = "";
		
		diagramFile = state.eContainer().eResource().getURI().path();
		
		projectName = diagramFile.substring(10, diagramFile.length());
		projectName = projectName.substring(0, projectName.indexOf("/"));
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		
		diagramFile = diagramFile
		.substring(diagramFile.lastIndexOf(project.getName())+project.getName().length(), 
				diagramFile.lastIndexOf("/")+1) + state.getId() + DIAGRAM_EXTENSION;

		try {
			IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), 
					project.getFile(diagramFile));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
