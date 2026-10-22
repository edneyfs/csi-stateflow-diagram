/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdapterFactory
 *  org.eclipse.core.runtime.Platform
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.util.EcoreUtil
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.navigator;

import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowAbstractNavigatorItem;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionflowNavigatorItem
extends ExecutionflowAbstractNavigatorItem {
    private View myView;
    private boolean myLeaf = false;

    static {
        final Class[] supportedTypes = new Class[]{View.class, EObject.class};
        Platform.getAdapterManager().registerAdapters(new IAdapterFactory(){

            public Object getAdapter(Object adaptableObject, Class adapterType) {
                if (adaptableObject instanceof ExecutionflowNavigatorItem && (adapterType == View.class || adapterType == EObject.class)) {
                    return ((ExecutionflowNavigatorItem)((Object)adaptableObject)).getView();
                }
                return null;
            }

            public Class[] getAdapterList() {
                return supportedTypes;
            }
        }, ExecutionflowNavigatorItem.class);
    }

    public ExecutionflowNavigatorItem(View view, Object parent, boolean isLeaf) {
        super(parent);
        this.myView = view;
        this.myLeaf = isLeaf;
    }

    public View getView() {
        return this.myView;
    }

    public boolean isLeaf() {
        return this.myLeaf;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ExecutionflowNavigatorItem) {
            return EcoreUtil.getURI((EObject)this.getView()).equals((Object)EcoreUtil.getURI((EObject)((ExecutionflowNavigatorItem)((Object)obj)).getView()));
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return EcoreUtil.getURI((EObject)this.getView()).hashCode();
    }
}

