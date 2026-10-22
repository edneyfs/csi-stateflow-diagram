/**
 * 
 */
package com.csi.gmf.stateflow.diagram.part;

import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.csi.gmf.stateflow.diagram.edit.parts.StateEditPart;
import com.csi.gmf.stateflow.impl.StateImpl;

/**
 * @author gustav.monteiro
 * @generated NOT
 */
public class StateSearchStateAction implements IObjectActionDelegate {

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
	private StateImpl myElement;

	/**
	 * @generated NOT
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/** 
	 * @generated NOT
	 */
	public void run(IAction action) {
		StateFlowDiagramSearchAssistant.getInstance().search(myElement.eContainer().eResource().getURI().path(), 
				myElement.getId(), 
				STATE_EXTENSION, 
				STATE_DIAGRAM_EXTENSION);
	}

	/** 
	 * @generated NOT 
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		StateEditPart mySelectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof StateEditPart) {
				mySelectedElement = (StateEditPart) structuredSelection
						.getFirstElement();
				myElement = (StateImpl)((NodeImpl)mySelectedElement.getModel()).getElement();
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
