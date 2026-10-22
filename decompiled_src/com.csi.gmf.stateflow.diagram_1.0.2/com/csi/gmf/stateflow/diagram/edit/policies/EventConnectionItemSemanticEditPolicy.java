/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest
 */
package com.csi.gmf.stateflow.diagram.edit.policies;

import com.csi.gmf.stateflow.diagram.edit.policies.StateflowBaseItemSemanticEditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

public class EventConnectionItemSemanticEditPolicy
extends StateflowBaseItemSemanticEditPolicy {
    @Override
    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        return this.getGEFWrapper((ICommand)new DestroyElementCommand(req));
    }
}

