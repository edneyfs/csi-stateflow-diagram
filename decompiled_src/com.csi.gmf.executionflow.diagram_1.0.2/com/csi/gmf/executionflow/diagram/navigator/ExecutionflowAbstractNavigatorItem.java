/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdapterFactory
 *  org.eclipse.core.runtime.Platform
 *  org.eclipse.core.runtime.PlatformObject
 *  org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor
 */
package com.csi.gmf.executionflow.diagram.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

public abstract class ExecutionflowAbstractNavigatorItem
extends PlatformObject {
    private Object myParent;

    static {
        final Class[] supportedTypes = new Class[]{ITabbedPropertySheetPageContributor.class};
        final ITabbedPropertySheetPageContributor propertySheetPageContributor = new ITabbedPropertySheetPageContributor(){

            public String getContributorId() {
                return "com.csi.gmf.executionflow.diagram";
            }
        };
        Platform.getAdapterManager().registerAdapters(new IAdapterFactory(){

            public Object getAdapter(Object adaptableObject, Class adapterType) {
                if (adaptableObject instanceof ExecutionflowAbstractNavigatorItem && adapterType == ITabbedPropertySheetPageContributor.class) {
                    return propertySheetPageContributor;
                }
                return null;
            }

            public Class[] getAdapterList() {
                return supportedTypes;
            }
        }, ExecutionflowAbstractNavigatorItem.class);
    }

    protected ExecutionflowAbstractNavigatorItem(Object parent) {
        this.myParent = parent;
    }

    public Object getParent() {
        return this.myParent;
    }
}

