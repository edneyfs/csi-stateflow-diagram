/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.preferences.RulerGridPreferencePage
 */
package com.csi.gmf.stateflow.diagram.preferences;

import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.RulerGridPreferencePage;

public class DiagramRulersAndGridPreferencePage
extends RulerGridPreferencePage {
    public DiagramRulersAndGridPreferencePage() {
        this.setPreferenceStore(StateflowDiagramEditorPlugin.getInstance().getPreferenceStore());
    }
}

