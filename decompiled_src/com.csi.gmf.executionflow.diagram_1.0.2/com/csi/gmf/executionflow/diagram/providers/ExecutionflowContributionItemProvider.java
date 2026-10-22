/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.common.ui.action.actions.IPrintActionHelper
 *  org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider
 *  org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor
 *  org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.EnhancedPrintActionHelper
 *  org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.RenderedPrintPreviewAction
 *  org.eclipse.jface.action.IAction
 */
package com.csi.gmf.executionflow.diagram.providers;

import org.eclipse.gmf.runtime.common.ui.action.actions.IPrintActionHelper;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.EnhancedPrintActionHelper;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.RenderedPrintPreviewAction;
import org.eclipse.jface.action.IAction;

public class ExecutionflowContributionItemProvider
extends AbstractContributionItemProvider {
    protected IAction createAction(String actionId, IWorkbenchPartDescriptor partDescriptor) {
        if (actionId.equals("printPreviewAction")) {
            return new RenderedPrintPreviewAction((IPrintActionHelper)new EnhancedPrintActionHelper());
        }
        return super.createAction(actionId, partDescriptor);
    }
}

