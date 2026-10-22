/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.StartPointConnection
 *  com.csi.gmf.stateflow.StateflowPackage$Literals
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
package com.csi.gmf.stateflow.provider;

import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.provider.StateflowEditPlugin;
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

public class StartPointConnectionItemProvider
extends ItemProviderAdapter
implements IEditingDomainItemProvider,
IStructuredItemContentProvider,
ITreeItemContentProvider,
IItemLabelProvider,
IItemPropertySource {
    public StartPointConnectionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    public List getPropertyDescriptors(Object object) {
        if (this.itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);
            this.addStartPointPropertyDescriptor(object);
            this.addDestinationStatePropertyDescriptor(object);
            this.addDestinationStateIdPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    protected void addStartPointPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_StartPointConnection_startPoint_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_StartPointConnection_startPoint_feature", "_UI_StartPointConnection_type"), (EStructuralFeature)StateflowPackage.Literals.START_POINT_CONNECTION__START_POINT, true, false, true, null, null, null));
    }

    protected void addDestinationStatePropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_StartPointConnection_destinationState_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_StartPointConnection_destinationState_feature", "_UI_StartPointConnection_type"), (EStructuralFeature)StateflowPackage.Literals.START_POINT_CONNECTION__DESTINATION_STATE, true, false, true, null, null, null));
    }

    protected void addDestinationStateIdPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_StartPointConnection_destinationStateId_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_StartPointConnection_destinationStateId_feature", "_UI_StartPointConnection_type"), (EStructuralFeature)StateflowPackage.Literals.START_POINT_CONNECTION__DESTINATION_STATE_ID, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    public Object getImage(Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/StartPointConnection"));
    }

    public String getText(Object object) {
        String label = ((StartPointConnection)object).getDestinationStateId();
        return label == null || label.length() == 0 ? this.getString("_UI_StartPointConnection_type") : String.valueOf(this.getString("_UI_StartPointConnection_type")) + " " + label;
    }

    public void notifyChanged(Notification notification) {
        this.updateChildren(notification);
        switch (notification.getFeatureID(StartPointConnection.class)) {
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
        return StateflowEditPlugin.INSTANCE;
    }
}

