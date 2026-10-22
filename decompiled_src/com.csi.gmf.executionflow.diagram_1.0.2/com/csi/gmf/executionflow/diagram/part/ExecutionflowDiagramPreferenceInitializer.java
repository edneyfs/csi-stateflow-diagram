/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.preferences.DiagramPreferenceInitializer
 *  org.eclipse.jface.preference.IPreferenceStore
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.DiagramPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class ExecutionflowDiagramPreferenceInitializer
extends DiagramPreferenceInitializer {
    protected IPreferenceStore getPreferenceStore() {
        return ExecutionflowDiagramEditorPlugin.getInstance().getPreferenceStore();
    }
}

