/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionflowPackage$Literals
 *  com.csi.gmf.executionflow.FlowObjectConnection
 *  org.eclipse.emf.common.notify.AdapterFactory
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.common.util.ResourceLocator
 *  org.eclipse.emf.ecore.EStructuralFeature
 *  org.eclipse.emf.edit.provider.ComposeableAdapterFactory
 *  org.eclipse.emf.edit.provider.IEditingDomainItemProvider
 *  org.eclipse.emf.edit.provider.IItemLabelProvider
 *  org.eclipse.emf.edit.provider.IItemPropertySource
 *  org.eclipse.emf.edit.provider.IStructuredItemContentProvider
 *  org.eclipse.emf.edit.provider.ITreeItemContentProvider
 *  org.eclipse.emf.edit.provider.ItemPropertyDescriptor
 *  org.eclipse.emf.edit.provider.ItemProviderAdapter
 *  org.eclipse.emf.edit.provider.ViewerNotification
 */
package com.csi.gmf.executionflow.provider;

import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.FlowObjectConnection;
import com.csi.gmf.executionflow.provider.ExecutionflowEditPlugin;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

public class FlowObjectConnectionItemProvider
extends ItemProviderAdapter
implements IEditingDomainItemProvider,
IStructuredItemContentProvider,
ITreeItemContentProvider,
IItemLabelProvider,
IItemPropertySource {
    public FlowObjectConnectionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    public List getPropertyDescriptors(Object object) {
        if (this.itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);
            this.addSourcePathKeyPropertyDescriptor(object);
            this.addDestinationPathKeyPropertyDescriptor(object);
            this.addExecutionFlowResultValuePropertyDescriptor(object);
            this.addSourceObjectPropertyDescriptor(object);
            this.addDestinationObjectPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    protected void addSourcePathKeyPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_FlowObjectConnection_sourcePathKey_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_FlowObjectConnection_sourcePathKey_feature", "_UI_FlowObjectConnection_type"), (EStructuralFeature)ExecutionflowPackage.Literals.FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addDestinationPathKeyPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_FlowObjectConnection_destinationPathKey_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_FlowObjectConnection_destinationPathKey_feature", "_UI_FlowObjectConnection_type"), (EStructuralFeature)ExecutionflowPackage.Literals.FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addExecutionFlowResultValuePropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_FlowObjectConnection_executionFlowResultValue_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_FlowObjectConnection_executionFlowResultValue_feature", "_UI_FlowObjectConnection_type"), (EStructuralFeature)ExecutionflowPackage.Literals.FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addSourceObjectPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_FlowObjectConnection_sourceObject_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_FlowObjectConnection_sourceObject_feature", "_UI_FlowObjectConnection_type"), (EStructuralFeature)ExecutionflowPackage.Literals.FLOW_OBJECT_CONNECTION__SOURCE_OBJECT, true, false, true, null, null, null));
    }

    protected void addDestinationObjectPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_FlowObjectConnection_destinationObject_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_FlowObjectConnection_destinationObject_feature", "_UI_FlowObjectConnection_type"), (EStructuralFeature)ExecutionflowPackage.Literals.FLOW_OBJECT_CONNECTION__DESTINATION_OBJECT, true, false, true, null, null, null));
    }

    public Object getImage(Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/FlowObjectConnection"));
    }

    public String getText(Object object) {
        Long labelValue = ((FlowObjectConnection)object).getSourcePathKey();
        String label = labelValue == null ? null : labelValue.toString();
        return label == null || label.length() == 0 ? this.getString("_UI_FlowObjectConnection_type") : String.valueOf(this.getString("_UI_FlowObjectConnection_type")) + " " + label;
    }

    public void notifyChanged(Notification notification) {
        this.updateChildren(notification);
        switch (notification.getFeatureID(FlowObjectConnection.class)) {
            case 0: 
            case 1: 
            case 2: {
                this.fireNotifyChanged((Notification)new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            }
        }
        super.notifyChanged(notification);
    }

    protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

    public ResourceLocator getResourceLocator() {
        return ExecutionflowEditPlugin.INSTANCE;
    }
}

