/**
 * 
 */
package com.csi.gmf.stateflow.diagram.part;

import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.csi.gmf.stateflow.diagram.edit.parts.StateFlowEditPart;
import com.csi.gmf.stateflow.impl.StateFlowImpl;

/**
 * @author gustav.monteiro
 *
 */
public class StateFlowSearchStateAction implements IObjectActionDelegate {

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
	private StateFlowImpl myElement;

	/** 
	 * @generated NOT
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @generated NOT
	 */
	public void run(IAction action) {
		String fileName = myElement.eResource().getURI().segment(myElement.eResource().getURI().segments().length-1);
		
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		
		StateFlowDiagramSearchAssistant.getInstance().search(myElement.eResource().getURI().path(), 
				fileName, 
				STATE_EXTENSION, 
				STATE_DIAGRAM_EXTENSION);
	}

	/**
	 * @generated NOT
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		StateFlowEditPart mySelectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof StateFlowEditPart) {
				mySelectedElement = (StateFlowEditPart) structuredSelection
						.getFirstElement();
				myElement = (StateFlowImpl)((DiagramImpl)mySelectedElement.getModel()).getElement();
			}
		}
		action.setEnabled(isEnabled());
	}

	/**
	 * @generated NOT
	 */
	private boolean isEnabled() {
		return myElement != null;
	}

}
