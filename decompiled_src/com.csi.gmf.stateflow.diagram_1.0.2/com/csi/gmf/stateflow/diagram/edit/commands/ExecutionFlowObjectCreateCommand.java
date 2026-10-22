/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.StateflowPackage
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.edit.commands;

import com.csi.gmf.stateflow.StateflowPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionFlowObjectCreateCommand
extends CreateElementCommand {
    public ExecutionFlowObjectCreateCommand(CreateElementRequest req) {
        super(req);
    }

    protected EObject getElementToEdit() {
        EObject container = ((CreateElementRequest)this.getRequest()).getContainer();
        if (container instanceof View) {
            container = ((View)container).getElement();
        }
        return container;
    }

    protected EClass getEClassToEdit() {
        return StateflowPackage.eINSTANCE.getStateFlow();
    }
}

