/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.EMFPlugin
 *  org.eclipse.emf.common.EMFPlugin$EclipsePlugin
 *  org.eclipse.emf.common.util.ResourceLocator
 */
package com.csi.gmf.executionflow.provider;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

public final class ExecutionflowEditPlugin
extends EMFPlugin {
    public static final ExecutionflowEditPlugin INSTANCE = new ExecutionflowEditPlugin();
    private static Implementation plugin;

    public ExecutionflowEditPlugin() {
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

