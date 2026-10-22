/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.commands.ExecutionEvent
 *  org.eclipse.core.commands.ExecutionException
 *  org.eclipse.core.commands.IHandler
 *  org.eclipse.core.commands.IHandlerListener
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.ui.PlatformUI
 */
package com.csi.gmf.executionflow.diagram.part;

import java.util.List;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

public class ExecutionflowDiagramUpdateCommand
implements IHandler {
    public void addHandlerListener(IHandlerListener handlerListener) {
    }

    public void dispose() {
    }

    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection)selection;
            if (structuredSelection.size() != 1) {
                return null;
            }
            if (structuredSelection.getFirstElement() instanceof EditPart && ((EditPart)structuredSelection.getFirstElement()).getModel() instanceof View) {
                EObject modelElement = ((View)((EditPart)structuredSelection.getFirstElement()).getModel()).getElement();
                List editPolicies = CanonicalEditPolicy.getRegisteredEditPolicies((EObject)modelElement);
                for (CanonicalEditPolicy nextEditPolicy : editPolicies) {
                    nextEditPolicy.refresh();
                }
            }
        }
        return null;
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean isHandled() {
        return true;
    }

    public void removeHandlerListener(IHandlerListener handlerListener) {
    }
}

