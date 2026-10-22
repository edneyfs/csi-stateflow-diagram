/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdapterFactory
 *  org.eclipse.core.runtime.Platform
 *  org.eclipse.core.runtime.PlatformObject
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.util.EcoreUtil
 *  org.eclipse.ui.views.properties.IPropertySource
 *  org.eclipse.ui.views.properties.IPropertySourceProvider
 */
package com.csi.gmf.executionflow.diagram.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

public class ExecutionflowDomainNavigatorItem
extends PlatformObject {
    private Object myParent;
    private EObject myEObject;
    private IPropertySourceProvider myPropertySourceProvider;

    static {
        final Class[] supportedTypes = new Class[]{EObject.class, IPropertySource.class};
        Platform.getAdapterManager().registerAdapters(new IAdapterFactory(){

            public Object getAdapter(Object adaptableObject, Class adapterType) {
                if (adaptableObject instanceof ExecutionflowDomainNavigatorItem) {
                    ExecutionflowDomainNavigatorItem domainNavigatorItem = (ExecutionflowDomainNavigatorItem)((Object)adaptableObject);
                    EObject eObject = domainNavigatorItem.getEObject();
                    if (adapterType == EObject.class) {
                        return eObject;
                    }
                    if (adapterType == IPropertySource.class) {
                        return domainNavigatorItem.getPropertySourceProvider().getPropertySource((Object)eObject);
                    }
                }
                return null;
            }

            public Class[] getAdapterList() {
                return supportedTypes;
            }
        }, ExecutionflowDomainNavigatorItem.class);
    }

    public ExecutionflowDomainNavigatorItem(EObject eObject, Object parent, IPropertySourceProvider propertySourceProvider) {
        this.myParent = parent;
        this.myEObject = eObject;
        this.myPropertySourceProvider = propertySourceProvider;
    }

    public Object getParent() {
        return this.myParent;
    }

    public EObject getEObject() {
        return this.myEObject;
    }

    public IPropertySourceProvider getPropertySourceProvider() {
        return this.myPropertySourceProvider;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ExecutionflowDomainNavigatorItem) {
            return EcoreUtil.getURI((EObject)this.getEObject()).equals((Object)EcoreUtil.getURI((EObject)((ExecutionflowDomainNavigatorItem)((Object)obj)).getEObject()));
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return EcoreUtil.getURI((EObject)this.getEObject()).hashCode();
    }
}

