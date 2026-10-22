/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.preferences.AppearancePreferencePage
 */
package com.csi.gmf.stateflow.diagram.preferences;

import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.AppearancePreferencePage;

public class DiagramAppearancePreferencePage
extends AppearancePreferencePage {
    public DiagramAppearancePreferencePage() {
        this.setPreferenceStore(StateflowDiagramEditorPlugin.getInstance().getPreferenceStore());
    }
}

