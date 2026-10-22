/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.provider.ExecutionflowItemProviderAdapterFactory
 *  org.eclipse.core.resources.IFolder
 *  org.eclipse.core.resources.IProject
 *  org.eclipse.core.resources.ResourcesPlugin
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
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDocumentProvider;
import com.csi.gmf.executionflow.provider.ExecutionflowItemProviderAdapterFactory;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
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

public class ExecutionflowDiagramEditorPlugin
extends AbstractUIPlugin {
    public static final String ID = "com.csi.gmf.executionflow.diagram";
    public static final PreferencesHint DIAGRAM_PREFERENCES_HINT = new PreferencesHint("com.csi.gmf.executionflow.diagram");
    private static ExecutionflowDiagramEditorPlugin instance;
    private ComposedAdapterFactory adapterFactory;
    private ExecutionflowDocumentProvider documentProvider;
    private static URLClassLoader pluginClassLoader;
    private final String DEFAULT_OUTPUT_FOLDER = "bin";

    public void start(BundleContext context) throws Exception {
        super.start(context);
        instance = this;
        PreferencesHint.registerPreferenceStore((PreferencesHint)DIAGRAM_PREFERENCES_HINT, (Object)this.getPreferenceStore());
        this.adapterFactory = this.createAdapterFactory();
    }

    private void startClassLoader() {
        IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        ArrayList<URL> jarList = new ArrayList<URL>();
        File path = null;
        try {
            int i = 0;
            while (i < projects.length) {
                path = new File(projects[i].getLocation().toString(), File.separator);
                this.fillJarList(path, jarList);
                IFolder folder = projects[i].getFolder("bin");
                path = new File(folder.getLocation().toString(), "/");
                jarList.add(path.toURL());
                ++i;
            }
            URL[] urls = this.listToURLArray(jarList);
            pluginClassLoader = new URLClassLoader(urls);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void fillJarList(File rootFolder, List jarList) {
        File[] folders;
        File[] files = rootFolder.listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File theDir, String theFilename) {
                return theFilename.endsWith(".jar");
            }
        });
        if (files != null && files.length > 0) {
            int i = 0;
            while (i < files.length) {
                try {
                    jarList.add(files[i].toURL());
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                ++i;
            }
        }
        if ((folders = rootFolder.listFiles(new FileFilter(){

            @Override
            public boolean accept(File theDir) {
                return theDir.isDirectory();
            }
        })) != null && folders.length > 0) {
            int i = 0;
            while (i < folders.length) {
                this.fillJarList(folders[i], jarList);
                ++i;
            }
        }
    }

    private URL[] listToURLArray(List list) {
        URL[] urls = new URL[list.size()];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            urls[i++] = (URL)it.next();
        }
        return urls;
    }

    public void stop(BundleContext context) throws Exception {
        this.adapterFactory.dispose();
        this.adapterFactory = null;
        instance = null;
        super.stop(context);
    }

    public static ExecutionflowDiagramEditorPlugin getInstance() {
        return instance;
    }

    protected ComposedAdapterFactory createAdapterFactory() {
        ArrayList factories = new ArrayList();
        this.fillItemProviderFactories(factories);
        return new ComposedAdapterFactory(factories);
    }

    protected void fillItemProviderFactories(List factories) {
        factories.add(new ExecutionflowItemProviderAdapterFactory());
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
        return ExecutionflowDiagramEditorPlugin.getBundledImageDescriptor(p.makeAbsolute().toString());
    }

    public Image getBundledImage(String path) {
        Image image = this.getImageRegistry().get(path);
        if (image == null) {
            this.getImageRegistry().put(path, ExecutionflowDiagramEditorPlugin.getBundledImageDescriptor(path));
            image = this.getImageRegistry().get(path);
        }
        return image;
    }

    public static String getString(String key) {
        return Platform.getResourceString((Bundle)ExecutionflowDiagramEditorPlugin.getInstance().getBundle(), (String)("%" + key));
    }

    public ExecutionflowDocumentProvider getDocumentProvider() {
        if (this.documentProvider == null) {
            this.documentProvider = new ExecutionflowDocumentProvider();
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

    public static URLClassLoader getPluginClassLoader() {
        return pluginClassLoader;
    }
}

