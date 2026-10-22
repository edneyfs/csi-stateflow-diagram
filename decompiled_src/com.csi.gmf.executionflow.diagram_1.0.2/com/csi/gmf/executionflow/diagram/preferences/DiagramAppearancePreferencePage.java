/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.preferences.AppearancePreferencePage
 */
package com.csi.gmf.executionflow.diagram.preferences;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.AppearancePreferencePage;

public class DiagramAppearancePreferencePage
extends AppearancePreferencePage {
    public DiagramAppearancePreferencePage() {
        this.setPreferenceStore(ExecutionflowDiagramEditorPlugin.getInstance().getPreferenceStore());
    }
}

