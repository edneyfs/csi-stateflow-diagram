/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutableFlowObject
 *  com.csi.gmf.executionflow.ExecutionflowPackage$Literals
 *  org.eclipse.emf.common.notify.AdapterFactory
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.ecore.EStructuralFeature
 *  org.eclipse.emf.edit.provider.ComposeableAdapterFactory
 *  org.eclipse.emf.edit.provider.IEditingDomainItemProvider
 *  org.eclipse.emf.edit.provider.IItemLabelProvider
 *  org.eclipse.emf.edit.provider.IItemPropertySource
 *  org.eclipse.emf.edit.provider.IStructuredItemContentProvider
 *  org.eclipse.emf.edit.provider.ITreeItemContentProvider
 *  org.eclipse.emf.edit.provider.ItemPropertyDescriptor
 *  org.eclipse.emf.edit.provider.ViewerNotification
 */
package com.csi.gmf.executionflow.provider;

import com.csi.gmf.executionflow.ExecutableFlowObject;
import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.provider.SourceConnectableItemProvider;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

public class ExecutableFlowObjectItemProvider
extends SourceConnectableItemProvider
implements IEditingDomainItemProvider,
IStructuredItemContentProvider,
ITreeItemContentProvider,
IItemLabelProvider,
IItemPropertySource {
    public ExecutableFlowObjectItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public List getPropertyDescriptors(Object object) {
        if (this.itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);
            this.addClassNamePropertyDescriptor(object);
            this.addSimpleClassNamePropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    protected void addClassNamePropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_ExecutableFlowObject_className_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_ExecutableFlowObject_className_feature", "_UI_ExecutableFlowObject_type"), (EStructuralFeature)ExecutionflowPackage.Literals.EXECUTABLE_FLOW_OBJECT__CLASS_NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addSimpleClassNamePropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_ExecutableFlowObject_simpleClassName_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_ExecutableFlowObject_simpleClassName_feature", "_UI_ExecutableFlowObject_type"), (EStructuralFeature)ExecutionflowPackage.Literals.EXECUTABLE_FLOW_OBJECT__SIMPLE_CLASS_NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    @Override
    public Object getImage(Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/ExecutableFlowObject"));
    }

    @Override
    public String getText(Object object) {
        String label = ((ExecutableFlowObject)object).getClassName();
        return label == null || label.length() == 0 ? this.getString("_UI_ExecutableFlowObject_type") : String.valueOf(this.getString("_UI_ExecutableFlowObject_type")) + " " + label;
    }

    @Override
    public void notifyChanged(Notification notification) {
        this.updateChildren(notification);
        switch (notification.getFeatureID(ExecutableFlowObject.class)) {
            case 1: 
            case 2: {
                this.fireNotifyChanged((Notification)new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            }
        }
        super.notifyChanged(notification);
    }

    @Override
    protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }
}

