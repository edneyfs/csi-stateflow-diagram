/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.ui.URIEditorInput
 *  org.eclipse.ui.IEditorInput
 *  org.eclipse.ui.IEditorMatchingStrategy
 *  org.eclipse.ui.IEditorReference
 *  org.eclipse.ui.PartInitException
 */
package com.csi.gmf.executionflow.diagram.part;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

public class ExecutionflowMatchingStrategy
implements IEditorMatchingStrategy {
    public boolean matches(IEditorReference editorRef, IEditorInput input) {
        IEditorInput editorInput;
        try {
            editorInput = editorRef.getEditorInput();
        }
        catch (PartInitException partInitException) {
            return false;
        }
        if (editorInput.equals(input)) {
            return true;
        }
        if (editorInput instanceof URIEditorInput && input instanceof URIEditorInput) {
            return ((URIEditorInput)editorInput).getURI().equals((Object)((URIEditorInput)input).getURI());
        }
        return false;
    }
}

