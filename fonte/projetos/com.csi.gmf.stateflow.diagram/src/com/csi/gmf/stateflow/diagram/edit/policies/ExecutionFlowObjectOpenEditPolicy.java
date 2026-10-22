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

import com.csi.gmf.stateflow.ExecutionFlowObject;

/**
 * @author gustav.monteiro
 * @generated NOT
 */
public class ExecutionFlowObjectOpenEditPolicy extends OpenEditPolicy {
	
	private final String DIAGRAM_SUFIX = "_diagram";

	/** (non-Javadoc)
	 * @generated NOT
	 */
	protected Command getOpenCommand(Request arg0) {
		
		ExecutionFlowObject flow = (ExecutionFlowObject)((View)this.getHost().getModel()).getElement();

		
		// Recupera a uri do arquivo do diagrama atual
		String diagramFile = "";
		
		String projectName = "";
		
		diagramFile = flow.eContainer().eResource().getURI().path();
		
		projectName = diagramFile.substring(10, diagramFile.length());
		projectName = projectName.substring(0, projectName.indexOf("/"));
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		
		diagramFile = diagramFile
		.substring(diagramFile.lastIndexOf(project.getName())+project.getName().length(), 
				diagramFile.lastIndexOf("/")+1) + flow.getFilePath() + DIAGRAM_SUFIX;

		try {
			IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), 
					project.getFile(diagramFile));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
