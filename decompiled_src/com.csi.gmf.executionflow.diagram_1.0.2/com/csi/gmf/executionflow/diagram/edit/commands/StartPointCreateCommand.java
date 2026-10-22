/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionFlow
 *  com.csi.gmf.executionflow.ExecutionflowPackage
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.edit.commands;

import com.csi.gmf.executionflow.ExecutionFlow;
import com.csi.gmf.executionflow.ExecutionflowPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

public class StartPointCreateCommand
extends CreateElementCommand {
    public StartPointCreateCommand(CreateElementRequest req) {
        super(req);
    }

    protected EObject getElementToEdit() {
        EObject container = ((CreateElementRequest)this.getRequest()).getContainer();
        if (container instanceof View) {
            container = ((View)container).getElement();
        }
        return container;
    }

    public boolean canExecute() {
        ExecutionFlow container = (ExecutionFlow)this.getElementToEdit();
        return container.getStartPoint() == null;
    }

    protected EClass getEClassToEdit() {
        return ExecutionflowPackage.eINSTANCE.getExecutionFlow();
    }
}

