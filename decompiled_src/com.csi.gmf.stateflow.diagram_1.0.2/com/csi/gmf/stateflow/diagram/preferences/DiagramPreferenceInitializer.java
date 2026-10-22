/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer
 *  org.eclipse.jface.preference.IPreferenceStore
 */
package com.csi.gmf.stateflow.diagram.preferences;

import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import com.csi.gmf.stateflow.diagram.preferences.DiagramAppearancePreferencePage;
import com.csi.gmf.stateflow.diagram.preferences.DiagramConnectionsPreferencePage;
import com.csi.gmf.stateflow.diagram.preferences.DiagramGeneralPreferencePage;
import com.csi.gmf.stateflow.diagram.preferences.DiagramPrintingPreferencePage;
import com.csi.gmf.stateflow.diagram.preferences.DiagramRulersAndGridPreferencePage;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class DiagramPreferenceInitializer
extends AbstractPreferenceInitializer {
    public void initializeDefaultPreferences() {
        IPreferenceStore store = this.getPreferenceStore();
        DiagramPrintingPreferencePage.initDefaults((IPreferenceStore)store);
        DiagramGeneralPreferencePage.initDefaults((IPreferenceStore)store);
        DiagramAppearancePreferencePage.initDefaults((IPreferenceStore)store);
        DiagramConnectionsPreferencePage.initDefaults((IPreferenceStore)store);
        DiagramRulersAndGridPreferencePage.initDefaults((IPreferenceStore)store);
    }

    protected IPreferenceStore getPreferenceStore() {
        return StateflowDiagramEditorPlugin.getInstance().getPreferenceStore();
    }
}

