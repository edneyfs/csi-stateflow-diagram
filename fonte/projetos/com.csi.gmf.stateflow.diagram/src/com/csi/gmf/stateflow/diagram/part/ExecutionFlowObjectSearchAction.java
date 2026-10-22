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

import com.csi.gmf.stateflow.diagram.edit.parts.ExecutionFlowObjectEditPart;
import com.csi.gmf.stateflow.impl.ExecutionFlowObjectImpl;

/**
 * @author gustav.monteiro
 *
 */
public abstract class ExecutionFlowObjectSearchAction implements IObjectActionDelegate {

	/**
	 * @generated NOT
	 */
	protected ExecutionFlowObjectImpl myElement;

	/**
	 * @generated NOT
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/** 
	 * @generated NOT
	 */
	public abstract void run(IAction action);

	/** 
	 * @generated NOT 
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		ExecutionFlowObjectEditPart mySelectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof ExecutionFlowObjectEditPart) {
				mySelectedElement = (ExecutionFlowObjectEditPart) structuredSelection
						.getFirstElement();
				myElement = (ExecutionFlowObjectImpl)((NodeImpl)mySelectedElement.getModel()).getElement();
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
