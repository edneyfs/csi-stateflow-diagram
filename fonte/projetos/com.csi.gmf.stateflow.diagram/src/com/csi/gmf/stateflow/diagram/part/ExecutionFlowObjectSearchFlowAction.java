/**
 * 
 */
package com.csi.gmf.stateflow.diagram.part;

import org.eclipse.jface.action.IAction;

/**
 * @author gustav.monteiro
 * @generated NOT
 */
public class ExecutionFlowObjectSearchFlowAction extends ExecutionFlowObjectSearchAction {

	/**
	 * @generated NOT
	 */
	private final String FLOW_EXTENSION = ".executionflow";

	/**
	 * @generated NOT
	 */
	private final String FLOW_DIAGRAM_EXTENSION = ".executionflow_diagram";

	/** 
	 * @generated NOT
	 */
	public void run(IAction action) {
		StateFlowDiagramSearchAssistant.getInstance().search(myElement.eContainer().eResource().getURI().path(), 
				myElement.getFilePath(), 
				FLOW_EXTENSION, 
				FLOW_DIAGRAM_EXTENSION);
	}
}
