/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.impl.ExecutableFlowObjectImpl
 *  com.csi.gmf.executionflow.impl.ExecutionFlowObjectImpl
 *  com.csi.gmf.executionflow.impl.ExecutionFlowResultImpl
 *  com.csi.gmf.executionflow.impl.FlowObjectConnectionImpl
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart
 *  org.eclipse.gmf.runtime.notation.impl.NodeImpl
 *  org.eclipse.jface.dialogs.DialogPage
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.search.ui.ISearchPage
 *  org.eclipse.search.ui.ISearchPageContainer
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.swt.widgets.Text
 *  org.eclipse.ui.PlatformUI
 */
package com.csi.gmf.executionflow.diagram;

import com.csi.gmf.executionflow.diagram.edit.parts.ExecutableFlowObjectEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowObjectEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowResultEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.FlowObjectConnectionEditPart;
import com.csi.gmf.executionflow.impl.ExecutableFlowObjectImpl;
import com.csi.gmf.executionflow.impl.ExecutionFlowObjectImpl;
import com.csi.gmf.executionflow.impl.ExecutionFlowResultImpl;
import com.csi.gmf.executionflow.impl.FlowObjectConnectionImpl;
import java.util.List;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class FlowObjectSearchPage
extends DialogPage
implements ISearchPage {
    private Text fileText;
    private String lastSearch = "";

    public boolean performAction() {
        EditPart lastPart = null;
        EditPart part2 = null;
        boolean found = false;
        int ocorrences = 0;
        String objectDescription = "";
        IDiagramWorkbenchPart diagramPart = (IDiagramWorkbenchPart)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
        if (!this.lastSearch.equals(this.fileText.getText())) {
            diagramPart.getDiagramGraphicalViewer().deselectAll();
            this.lastSearch = this.fileText.getText();
        }
        List parts = diagramPart.getDiagramEditPart().getChildren();
        for (EditPart part2 : parts) {
            ExecutableFlowObjectImpl objectImpl;
            NodeImpl node = (NodeImpl)part2.getModel();
            if (part2 instanceof ExecutableFlowObjectEditPart) {
                objectImpl = (ExecutableFlowObjectImpl)node.getElement();
                objectDescription = objectImpl.getSimpleClassName();
            } else if (part2 instanceof ExecutionFlowObjectEditPart) {
                objectImpl = (ExecutionFlowObjectImpl)node.getElement();
                objectDescription = objectImpl.getFileName();
            } else if (part2 instanceof ExecutionFlowResultEditPart) {
                objectImpl = (ExecutionFlowResultImpl)node.getElement();
                objectDescription = objectImpl.getExecutionFlowResultValue();
            } else if (part2 instanceof FlowObjectConnectionEditPart) {
                objectImpl = (FlowObjectConnectionImpl)node.getElement();
                objectDescription = objectImpl.getExecutionFlowResultValue();
            }
            if (objectDescription.toUpperCase().indexOf(this.fileText.getText().toUpperCase()) > -1) {
                if (!(found || part2.getSelected() != 0 || lastPart != null && part2.equals(lastPart))) {
                    part2.setSelected(2);
                    if (lastPart != null) {
                        lastPart.setSelected(0);
                    }
                    lastPart = part2;
                    found = true;
                    diagramPart.getDiagramGraphicalViewer().reveal(part2);
                } else {
                    part2.setSelected(0);
                }
                ocorrences = (short)(ocorrences + 1);
                continue;
            }
            part2.setSelected(0);
        }
        if (ocorrences == 0) {
            MessageDialog.openError((Shell)this.getShell(), (String)"Error", (String)("No object found with the given description! (" + this.fileText.getText() + ")"));
            this.fileText.selectAll();
            this.fileText.setFocus();
        }
        return ocorrences == 1;
    }

    public void setContainer(ISearchPageContainer container) {
    }

    public void createControl(Composite parent) {
        IDiagramWorkbenchPart diagramPart = (IDiagramWorkbenchPart)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
        diagramPart.getDiagramGraphicalViewer().deselectAll();
        Composite result = new Composite(parent, 0);
        GridLayout layout = new GridLayout(1, false);
        layout.horizontalSpacing = 10;
        result.setLayout((Layout)layout);
        Label label = new Label(result, 0);
        label.setText("Flow Object Name:");
        GridData gd = new GridData(4, 4, true, false, 2, 1);
        this.fileText = new Text(result, 128);
        this.fileText.setLayoutData((Object)gd);
        this.setControl((Control)result);
        super.setVisible(true);
        this.fileText.setFocus();
    }
}

