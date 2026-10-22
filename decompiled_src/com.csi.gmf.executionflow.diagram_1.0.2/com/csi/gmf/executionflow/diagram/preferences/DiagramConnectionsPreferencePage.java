/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.preferences.ConnectionsPreferencePage
 */
package com.csi.gmf.executionflow.diagram.preferences;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import org.eclipse.gmf.runtime.diagram.ui.preferences.ConnectionsPreferencePage;

public class DiagramConnectionsPreferencePage
extends ConnectionsPreferencePage {
    public DiagramConnectionsPreferencePage() {
        this.setPreferenceStore(ExecutionflowDiagramEditorPlugin.getInstance().getPreferenceStore());
    }
}

