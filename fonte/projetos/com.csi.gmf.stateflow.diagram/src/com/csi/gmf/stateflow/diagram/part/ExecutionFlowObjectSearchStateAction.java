/**
 * 
 */
package com.csi.gmf.stateflow.diagram.part;

import org.eclipse.jface.action.IAction;

/**
 * @author gustav.monteiro
 * @generated NOT
 */
public class ExecutionFlowObjectSearchStateAction extends ExecutionFlowObjectSearchAction {

	/**
	 * @generated NOT
	 */
	private final String STATE_EXTENSION = ".stateflow";

	/**
	 * @generated NOT
	 */
	private final String STATE_DIAGRAM_EXTENSION = ".stateflow_diagram";
	
	/** 
	 * @generated NOT
	 */
	public void run(IAction action) {
		StateFlowDiagramSearchAssistant.getInstance().search(myElement.eContainer().eResource().getURI().path(), 
				myElement.getFilePath(), 
				STATE_EXTENSION, 
				STATE_DIAGRAM_EXTENSION);
	}
}
