/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.transaction.util.TransactionUtil
 *  org.eclipse.gef.EditPartViewer
 *  org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService
 *  org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider
 *  org.eclipse.jface.action.IAction
 *  org.eclipse.jface.action.IMenuManager
 *  org.eclipse.ui.IWorkbenchPart
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.DeleteElementAction;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchPart;

public class DiagramEditorContextMenuProvider
extends DiagramContextMenuProvider {
    private IWorkbenchPart part;
    private DeleteElementAction deleteAction;

    public DiagramEditorContextMenuProvider(IWorkbenchPart part, EditPartViewer viewer) {
        super(part, viewer);
        this.part = part;
        this.deleteAction = new DeleteElementAction(part);
        this.deleteAction.init();
    }

    public void dispose() {
        if (this.deleteAction != null) {
            this.deleteAction.dispose();
            this.deleteAction = null;
        }
        super.dispose();
    }

    public void buildContextMenu(final IMenuManager menu) {
        this.getViewer().flush();
        try {
            TransactionUtil.getEditingDomain((EObject)((EObject)this.getViewer().getContents().getModel())).runExclusive(new Runnable(){

                @Override
                public void run() {
                    ContributionItemService.getInstance().contributeToPopupMenu((IMenuManager)DiagramEditorContextMenuProvider.this, DiagramEditorContextMenuProvider.this.part);
                    menu.remove("deleteFromModelAction");
                    menu.appendToGroup("editGroup", (IAction)DiagramEditorContextMenuProvider.this.deleteAction);
                }
            });
        }
        catch (Exception e) {
            ExecutionflowDiagramEditorPlugin.getInstance().logError("Error building context menu", e);
        }
    }
}

