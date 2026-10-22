/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.expressions.PropertyTester
 *  org.eclipse.emf.common.ui.URIEditorInput
 */
package com.csi.gmf.executionflow.diagram.part;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.common.ui.URIEditorInput;

public class ExecutionflowUriEditorInputTester
extends PropertyTester {
    public boolean test(Object receiver, String method, Object[] args, Object expectedValue) {
        if (!(receiver instanceof URIEditorInput)) {
            return false;
        }
        URIEditorInput editorInput = (URIEditorInput)receiver;
        return "executionflow_diagram".equals(editorInput.getURI().fileExtension());
    }
}

