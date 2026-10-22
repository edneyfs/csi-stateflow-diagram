/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.EMFPlugin
 *  org.eclipse.emf.common.EMFPlugin$EclipsePlugin
 *  org.eclipse.emf.common.util.ResourceLocator
 */
package com.csi.gmf.stateflow.provider;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

public final class StateflowEditPlugin
extends EMFPlugin {
    public static final StateflowEditPlugin INSTANCE = new StateflowEditPlugin();
    private static Implementation plugin;

    public StateflowEditPlugin() {
        super(new ResourceLocator[0]);
    }

    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    public static Implementation getPlugin() {
        return plugin;
    }

    public static class Implementation
    extends EMFPlugin.EclipsePlugin {
        public Implementation() {
            plugin = this;
        }
    }
}

