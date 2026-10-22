/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.preferences.DiagramPreferenceInitializer
 *  org.eclipse.jface.preference.IPreferenceStore
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.DiagramPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class StateflowDiagramPreferenceInitializer
extends DiagramPreferenceInitializer {
    protected IPreferenceStore getPreferenceStore() {
        return StateflowDiagramEditorPlugin.getInstance().getPreferenceStore();
    }
}

