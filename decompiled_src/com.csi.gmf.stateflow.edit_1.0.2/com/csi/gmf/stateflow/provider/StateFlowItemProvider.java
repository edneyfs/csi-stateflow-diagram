/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.StateFlow
 *  com.csi.gmf.stateflow.StateflowFactory
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

import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowFactory;
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

public class StateFlowItemProvider
extends ItemProviderAdapter
implements IEditingDomainItemProvider,
IStructuredItemContentProvider,
ITreeItemContentProvider,
IItemLabelProvider,
IItemPropertySource {
    public StateFlowItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    public List getPropertyDescriptors(Object object) {
        if (this.itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);
            this.addIdPropertyDescriptor(object);
            this.addDescriptionPropertyDescriptor(object);
            this.addVolatilePropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    protected void addIdPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_StateFlow_id_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_StateFlow_id_feature", "_UI_StateFlow_type"), (EStructuralFeature)StateflowPackage.Literals.STATE_FLOW__ID, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addDescriptionPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_StateFlow_description_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_StateFlow_description_feature", "_UI_StateFlow_type"), (EStructuralFeature)StateflowPackage.Literals.STATE_FLOW__DESCRIPTION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addVolatilePropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_StateFlow_volatile_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_StateFlow_volatile_feature", "_UI_StateFlow_type"), (EStructuralFeature)StateflowPackage.Literals.STATE_FLOW__VOLATILE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    public Collection getChildrenFeatures(Object object) {
        if (this.childrenFeatures == null) {
            super.getChildrenFeatures(object);
            this.childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__STATES);
            this.childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__EXECUTION_FLOWS);
            this.childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__STATE_CONNECTIONS);
            this.childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__FLOW_CONNECTIONS);
            this.childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__START_POINT);
            this.childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__START_POINT_CONNECTION);
        }
        return this.childrenFeatures;
    }

    protected EStructuralFeature getChildFeature(Object object, Object child) {
        return super.getChildFeature(object, child);
    }

    public Object getImage(Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/StateFlow"));
    }

    public String getText(Object object) {
        String label = ((StateFlow)object).getId();
        return label == null || label.length() == 0 ? this.getString("_UI_StateFlow_type") : String.valueOf(this.getString("_UI_StateFlow_type")) + " " + label;
    }

    public void notifyChanged(Notification notification) {
        this.updateChildren(notification);
        switch (notification.getFeatureID(StateFlow.class)) {
            case 6: 
            case 7: 
            case 8: {
                this.fireNotifyChanged((Notification)new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            }
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                this.fireNotifyChanged((Notification)new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
            }
        }
        super.notifyChanged(notification);
    }

    protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
        newChildDescriptors.add(this.createChildParameter(StateflowPackage.Literals.STATE_FLOW__STATES, StateflowFactory.eINSTANCE.createState()));
        newChildDescriptors.add(this.createChildParameter(StateflowPackage.Literals.STATE_FLOW__EXECUTION_FLOWS, StateflowFactory.eINSTANCE.createExecutionFlowObject()));
        newChildDescriptors.add(this.createChildParameter(StateflowPackage.Literals.STATE_FLOW__STATE_CONNECTIONS, StateflowFactory.eINSTANCE.createEventConnection()));
        newChildDescriptors.add(this.createChildParameter(StateflowPackage.Literals.STATE_FLOW__FLOW_CONNECTIONS, StateflowFactory.eINSTANCE.createFlowObjectConnection()));
        newChildDescriptors.add(this.createChildParameter(StateflowPackage.Literals.STATE_FLOW__START_POINT, StateflowFactory.eINSTANCE.createStartPoint()));
        newChildDescriptors.add(this.createChildParameter(StateflowPackage.Literals.STATE_FLOW__START_POINT_CONNECTION, StateflowFactory.eINSTANCE.createStartPointConnection()));
    }

    public ResourceLocator getResourceLocator() {
        return StateflowEditPlugin.INSTANCE;
    }
}

