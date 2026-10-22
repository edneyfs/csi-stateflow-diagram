/**
 * 
 */
package com.csi.gmf.stateflow.diagram;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.csi.gmf.stateflow.diagram.edit.parts.EventConnectionEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.ExecutionFlowObjectEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.FlowObjectConnectionEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StateEditPart;
import com.csi.gmf.stateflow.impl.EventConnectionImpl;
import com.csi.gmf.stateflow.impl.ExecutionFlowObjectImpl;
import com.csi.gmf.stateflow.impl.FlowObjectConnectionImpl;
import com.csi.gmf.stateflow.impl.StateImpl;

/**
 * @author gustav.monteiro
 *
 */
public class StateFlowObjectsSearchPage extends DialogPage implements ISearchPage {
 
	private Text fileText;
	private String lastSearch = "";
	
	/* (non-Javadoc)
	 * @see org.eclipse.search.ui.ISearchPage#performAction()
	 */
	public boolean performAction() {
		
		EditPart lastPart = null;
		EditPart part = null;
		boolean found = false;
		short ocorrences = 0;
		String objectDescription = "";
		
		IDiagramWorkbenchPart diagramPart = ((IDiagramWorkbenchPart) PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActivePart());
		
		if (!lastSearch.equals(fileText.getText())) {
			diagramPart.getDiagramGraphicalViewer().deselectAll();
			lastSearch = fileText.getText();
		}
		
		List parts = diagramPart.getDiagramEditPart().getChildren();
		
		Iterator it = parts.iterator();
		
		while (it.hasNext()) {

			part = (EditPart) it.next();
			NodeImpl node = (NodeImpl)part.getModel();
			
			if (part instanceof EventConnectionEditPart) {
				EventConnectionImpl objectImpl = (EventConnectionImpl)node.getElement();
				objectDescription = objectImpl.getEventID();
			} else if (part instanceof FlowObjectConnectionEditPart) {
				FlowObjectConnectionImpl objectImpl = (FlowObjectConnectionImpl)node.getElement();
				objectDescription = objectImpl.getExecutionFlowResultValue();
			} else if (part instanceof ExecutionFlowObjectEditPart) {
				ExecutionFlowObjectImpl objectImpl = (ExecutionFlowObjectImpl)node.getElement();
				objectDescription = objectImpl.getTitle();
			} else if (part instanceof StateEditPart) {
				StateImpl objectImpl = (StateImpl)node.getElement();
				objectDescription = objectImpl.getId();
			}

			if (objectDescription != null && objectDescription.toUpperCase().indexOf(
					fileText.getText().toUpperCase()) > -1) {
				
				if (!found && part.getSelected() == EditPart.SELECTED_NONE
						&& (lastPart == null || !part.equals(lastPart))) {
					
					part.setSelected(EditPart.SELECTED_PRIMARY);
					
					if (lastPart != null) {
						lastPart.setSelected(EditPart.SELECTED_NONE);
					}
					
					lastPart = part;
					found = true;
					diagramPart.getDiagramGraphicalViewer().reveal(part);
				} else {
					part.setSelected(EditPart.SELECTED_NONE);
				}

				ocorrences += 1;
			} else {
				part.setSelected(EditPart.SELECTED_NONE);
			}
		}
		
		if (ocorrences == 0) {
			MessageDialog.openError(this.getShell(), "Error",
					"No object found with the given description! ("
							+ fileText.getText() + ")");
			fileText.selectAll();
			fileText.setFocus();
		}

		if (ocorrences == 1) {
			return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.search.ui.ISearchPage#setContainer(org.eclipse.search.ui.ISearchPageContainer)
	 */
	public void setContainer(ISearchPageContainer container) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createControl(Composite parent) {
		
		IDiagramWorkbenchPart diagramPart = ((IDiagramWorkbenchPart) PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActivePart());
		
		diagramPart.getDiagramGraphicalViewer().deselectAll();
		
		Composite result = new Composite(parent, SWT.NONE);
		
		GridLayout layout = new GridLayout(1, false);
		layout.horizontalSpacing = 10;
		
		result.setLayout(layout);

		Label label = new Label(result, SWT.NONE);
		label.setText("Flow Object Name:");

		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, false,
				2, 1);
		fileText = new Text(result, SWT.SEARCH);
		fileText.setLayoutData(gd);
		
		setControl(result);
		super.setVisible(true);

		fileText.setFocus();
	}
}
