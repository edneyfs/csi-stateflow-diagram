/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.provider.StateflowItemProviderAdapterFactory
 *  org.eclipse.core.runtime.IStatus
 *  org.eclipse.core.runtime.Path
 *  org.eclipse.core.runtime.Platform
 *  org.eclipse.core.runtime.Status
 *  org.eclipse.emf.common.notify.AdapterFactory
 *  org.eclipse.emf.edit.provider.ComposedAdapterFactory
 *  org.eclipse.emf.edit.provider.IItemLabelProvider
 *  org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory
 *  org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory
 *  org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry
 *  org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint
 *  org.eclipse.jface.resource.ImageDescriptor
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.ui.plugin.AbstractUIPlugin
 *  org.osgi.framework.Bundle
 *  org.osgi.framework.BundleContext
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.part.StateflowDocumentProvider;
import com.csi.gmf.stateflow.provider.StateflowItemProviderAdapterFactory;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class StateflowDiagramEditorPlugin
extends AbstractUIPlugin {
    public static final String ID = "com.csi.gmf.stateflow.diagram";
    public static final PreferencesHint DIAGRAM_PREFERENCES_HINT = new PreferencesHint("com.csi.gmf.stateflow.diagram");
    private static StateflowDiagramEditorPlugin instance;
    private ComposedAdapterFactory adapterFactory;
    private StateflowDocumentProvider documentProvider;

    public void start(BundleContext context) throws Exception {
        super.start(context);
        instance = this;
        PreferencesHint.registerPreferenceStore((PreferencesHint)DIAGRAM_PREFERENCES_HINT, (Object)this.getPreferenceStore());
        this.adapterFactory = this.createAdapterFactory();
    }

    public void stop(BundleContext context) throws Exception {
        this.adapterFactory.dispose();
        this.adapterFactory = null;
        instance = null;
        super.stop(context);
    }

    public static StateflowDiagramEditorPlugin getInstance() {
        return instance;
    }

    protected ComposedAdapterFactory createAdapterFactory() {
        ArrayList factories = new ArrayList();
        this.fillItemProviderFactories(factories);
        return new ComposedAdapterFactory(factories);
    }

    protected void fillItemProviderFactories(List factories) {
        factories.add(new StateflowItemProviderAdapterFactory());
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());
    }

    public AdapterFactory getItemProvidersAdapterFactory() {
        return this.adapterFactory;
    }

    public ImageDescriptor getItemImageDescriptor(Object item) {
        IItemLabelProvider labelProvider = (IItemLabelProvider)this.adapterFactory.adapt(item, IItemLabelProvider.class);
        if (labelProvider != null) {
            return ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(item));
        }
        return null;
    }

    public static ImageDescriptor getBundledImageDescriptor(String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin((String)ID, (String)path);
    }

    public static ImageDescriptor findImageDescriptor(String path) {
        Path p = new Path(path);
        if (p.isAbsolute() && p.segmentCount() > 1) {
            return AbstractUIPlugin.imageDescriptorFromPlugin((String)p.segment(0), (String)p.removeFirstSegments(1).makeAbsolute().toString());
        }
        return StateflowDiagramEditorPlugin.getBundledImageDescriptor(p.makeAbsolute().toString());
    }

    public Image getBundledImage(String path) {
        Image image = this.getImageRegistry().get(path);
        if (image == null) {
            this.getImageRegistry().put(path, StateflowDiagramEditorPlugin.getBundledImageDescriptor(path));
            image = this.getImageRegistry().get(path);
        }
        return image;
    }

    public static String getString(String key) {
        return Platform.getResourceString((Bundle)StateflowDiagramEditorPlugin.getInstance().getBundle(), (String)("%" + key));
    }

    public StateflowDocumentProvider getDocumentProvider() {
        if (this.documentProvider == null) {
            this.documentProvider = new StateflowDocumentProvider();
        }
        return this.documentProvider;
    }

    public void logError(String error) {
        this.logError(error, null);
    }

    public void logError(String error, Throwable throwable) {
        if (error == null && throwable != null) {
            error = throwable.getMessage();
        }
        this.getLog().log((IStatus)new Status(4, ID, 0, error, throwable));
        this.debug(error, throwable);
    }

    public void logInfo(String message) {
        this.logInfo(message, null);
    }

    public void logInfo(String message, Throwable throwable) {
        if (message == null && throwable != null) {
            message = throwable.getMessage();
        }
        this.getLog().log((IStatus)new Status(1, ID, 0, message, throwable));
        this.debug(message, throwable);
    }

    private void debug(String message, Throwable throwable) {
        if (!this.isDebugging()) {
            return;
        }
        if (message != null) {
            System.err.println(message);
        }
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }
}

