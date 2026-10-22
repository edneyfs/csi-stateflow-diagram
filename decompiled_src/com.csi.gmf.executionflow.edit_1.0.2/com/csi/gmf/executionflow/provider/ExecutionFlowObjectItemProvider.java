/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionFlowObject
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

import com.csi.gmf.executionflow.ExecutionFlowObject;
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

public class ExecutionFlowObjectItemProvider
extends SourceConnectableItemProvider
implements IEditingDomainItemProvider,
IStructuredItemContentProvider,
ITreeItemContentProvider,
IItemLabelProvider,
IItemPropertySource {
    public ExecutionFlowObjectItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public List getPropertyDescriptors(Object object) {
        if (this.itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);
            this.addFileNamePropertyDescriptor(object);
            this.addFilePathPropertyDescriptor(object);
            this.addIdPropertyDescriptor(object);
            this.addDescriptionPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    protected void addFileNamePropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_ExecutionFlowObject_fileName_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_ExecutionFlowObject_fileName_feature", "_UI_ExecutionFlowObject_type"), (EStructuralFeature)ExecutionflowPackage.Literals.EXECUTION_FLOW_OBJECT__FILE_NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addFilePathPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_ExecutionFlowObject_filePath_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_ExecutionFlowObject_filePath_feature", "_UI_ExecutionFlowObject_type"), (EStructuralFeature)ExecutionflowPackage.Literals.EXECUTION_FLOW_OBJECT__FILE_PATH, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addIdPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_ExecutionFlowObject_id_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_ExecutionFlowObject_id_feature", "_UI_ExecutionFlowObject_type"), (EStructuralFeature)ExecutionflowPackage.Literals.EXECUTION_FLOW_OBJECT__ID, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addDescriptionPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_ExecutionFlowObject_description_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_ExecutionFlowObject_description_feature", "_UI_ExecutionFlowObject_type"), (EStructuralFeature)ExecutionflowPackage.Literals.EXECUTION_FLOW_OBJECT__DESCRIPTION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    @Override
    public Object getImage(Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/ExecutionFlowObject"));
    }

    @Override
    public String getText(Object object) {
        String label = ((ExecutionFlowObject)object).getFileName();
        return label == null || label.length() == 0 ? this.getString("_UI_ExecutionFlowObject_type") : String.valueOf(this.getString("_UI_ExecutionFlowObject_type")) + " " + label;
    }

    @Override
    public void notifyChanged(Notification notification) {
        this.updateChildren(notification);
        switch (notification.getFeatureID(ExecutionFlowObject.class)) {
            case 1: 
            case 2: 
            case 3: 
            case 4: {
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

