/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
 *  org.eclipse.jface.viewers.ILabelProviderListener
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.ui.IMemento
 *  org.eclipse.ui.navigator.ICommonContentExtensionSite
 *  org.eclipse.ui.navigator.ICommonLabelProvider
 */
package com.csi.gmf.executionflow.diagram.navigator;

import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowDomainNavigatorItem;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

public class ExecutionflowDomainNavigatorLabelProvider
implements ICommonLabelProvider {
    private AdapterFactoryLabelProvider myAdapterFactoryLabelProvider = new AdapterFactoryLabelProvider(ExecutionflowDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

    public void init(ICommonContentExtensionSite aConfig) {
    }

    public Image getImage(Object element) {
        if (element instanceof ExecutionflowDomainNavigatorItem) {
            return this.myAdapterFactoryLabelProvider.getImage((Object)((ExecutionflowDomainNavigatorItem)((Object)element)).getEObject());
        }
        return null;
    }

    public String getText(Object element) {
        if (element instanceof ExecutionflowDomainNavigatorItem) {
            return this.myAdapterFactoryLabelProvider.getText((Object)((ExecutionflowDomainNavigatorItem)((Object)element)).getEObject());
        }
        return null;
    }

    public void addListener(ILabelProviderListener listener) {
        this.myAdapterFactoryLabelProvider.addListener(listener);
    }

    public void dispose() {
        this.myAdapterFactoryLabelProvider.dispose();
    }

    public boolean isLabelProperty(Object element, String property) {
        return this.myAdapterFactoryLabelProvider.isLabelProperty(element, property);
    }

    public void removeListener(ILabelProviderListener listener) {
        this.myAdapterFactoryLabelProvider.removeListener(listener);
    }

    public void restoreState(IMemento aMemento) {
    }

    public void saveState(IMemento aMemento) {
    }

    public String getDescription(Object anElement) {
        return null;
    }
}

