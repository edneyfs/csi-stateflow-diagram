/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.preferences.RulerGridPreferencePage
 */
package com.csi.gmf.executionflow.diagram.preferences;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.RulerGridPreferencePage;

public class DiagramRulersAndGridPreferencePage
extends RulerGridPreferencePage {
    public DiagramRulersAndGridPreferencePage() {
        this.setPreferenceStore(ExecutionflowDiagramEditorPlugin.getInstance().getPreferenceStore());
    }
}

