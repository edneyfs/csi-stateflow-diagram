/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer
 *  org.eclipse.jface.preference.IPreferenceStore
 */
package com.csi.gmf.executionflow.diagram.preferences;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.preferences.DiagramAppearancePreferencePage;
import com.csi.gmf.executionflow.diagram.preferences.DiagramConnectionsPreferencePage;
import com.csi.gmf.executionflow.diagram.preferences.DiagramGeneralPreferencePage;
import com.csi.gmf.executionflow.diagram.preferences.DiagramPrintingPreferencePage;
import com.csi.gmf.executionflow.diagram.preferences.DiagramRulersAndGridPreferencePage;
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
        return ExecutionflowDiagramEditorPlugin.getInstance().getPreferenceStore();
    }
}

