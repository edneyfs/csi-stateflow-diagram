/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.EMFPlugin
 *  org.eclipse.emf.common.ui.EclipseUIPlugin
 *  org.eclipse.emf.common.util.ResourceLocator
 */
package com.csi.gmf.stateflow.presentation;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

public final class StateflowEditorPlugin
extends EMFPlugin {
    public static final StateflowEditorPlugin INSTANCE = new StateflowEditorPlugin();
    private static Implementation plugin;

    public StateflowEditorPlugin() {
        super(new ResourceLocator[0]);
    }

    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    public static Implementation getPlugin() {
        return plugin;
    }

    public static class Implementation
    extends EclipseUIPlugin {
        public Implementation() {
            plugin = this;
        }
    }
}

