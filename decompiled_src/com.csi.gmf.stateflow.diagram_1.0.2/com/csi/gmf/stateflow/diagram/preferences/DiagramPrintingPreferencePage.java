/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.preferences.PrintingPreferencePage
 */
package com.csi.gmf.stateflow.diagram.preferences;

import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.PrintingPreferencePage;

public class DiagramPrintingPreferencePage
extends PrintingPreferencePage {
    public DiagramPrintingPreferencePage() {
        this.setPreferenceStore(StateflowDiagramEditorPlugin.getInstance().getPreferenceStore());
    }
}

