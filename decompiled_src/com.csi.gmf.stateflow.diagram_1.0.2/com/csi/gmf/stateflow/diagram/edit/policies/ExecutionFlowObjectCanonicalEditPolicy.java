/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.edit.policies;

import java.util.LinkedList;
import java.util.List;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionFlowObjectCanonicalEditPolicy
extends CanonicalEditPolicy {
    protected List getSemanticChildrenList() {
        LinkedList result = new LinkedList();
        return result;
    }

    protected boolean shouldDeleteView(View view) {
        return view.isSetElement() && view.getElement() != null && view.getElement().eIsProxy();
    }

    protected String getDefaultFactoryHint() {
        return null;
    }
}

