/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.preferences.DiagramsPreferencePage
 */
package com.csi.gmf.executionflow.diagram.preferences;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.DiagramsPreferencePage;

public class DiagramGeneralPreferencePage
extends DiagramsPreferencePage {
    public DiagramGeneralPreferencePage() {
        this.setPreferenceStore(ExecutionflowDiagramEditorPlugin.getInstance().getPreferenceStore());
    }
}

