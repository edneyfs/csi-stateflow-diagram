/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionFlow
 *  com.csi.gmf.executionflow.ExecutionflowFactory
 *  com.csi.gmf.executionflow.ExecutionflowPackage$Literals
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

import com.csi.gmf.executionflow.ExecutionFlow;
import com.csi.gmf.executionflow.ExecutionflowFactory;
import com.csi.gmf.executionflow.ExecutionflowPackage;
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

public class ExecutionFlowItemProvider
extends ItemProviderAdapter
implements IEditingDomainItemProvider,
IStructuredItemContentProvider,
ITreeItemContentProvider,
IItemLabelProvider,
IItemPropertySource {
    public ExecutionFlowItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    public List getPropertyDescriptors(Object object) {
        if (this.itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);
            this.addTitlePropertyDescriptor(object);
            this.addIdPropertyDescriptor(object);
            this.addDescriptionPropertyDescriptor(object);
            this.addRequestAuthorizationPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    protected void addTitlePropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_ExecutionFlow_title_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_ExecutionFlow_title_feature", "_UI_ExecutionFlow_type"), (EStructuralFeature)ExecutionflowPackage.Literals.EXECUTION_FLOW__TITLE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addIdPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_ExecutionFlow_id_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_ExecutionFlow_id_feature", "_UI_ExecutionFlow_type"), (EStructuralFeature)ExecutionflowPackage.Literals.EXECUTION_FLOW__ID, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addDescriptionPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_ExecutionFlow_description_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_ExecutionFlow_description_feature", "_UI_ExecutionFlow_type"), (EStructuralFeature)ExecutionflowPackage.Literals.EXECUTION_FLOW__DESCRIPTION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    protected void addRequestAuthorizationPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor((AdapterFactory)((ComposeableAdapterFactory)this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_ExecutionFlow_requestAuthorization_feature"), this.getString("_UI_PropertyDescriptor_description", "_UI_ExecutionFlow_requestAuthorization_feature", "_UI_ExecutionFlow_type"), (EStructuralFeature)ExecutionflowPackage.Literals.EXECUTION_FLOW__REQUEST_AUTHORIZATION, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    public Collection getChildrenFeatures(Object object) {
        if (this.childrenFeatures == null) {
            super.getChildrenFeatures(object);
            this.childrenFeatures.add(ExecutionflowPackage.Literals.EXECUTION_FLOW__EXECUTABLE_FLOW_OBJECTS);
            this.childrenFeatures.add(ExecutionflowPackage.Literals.EXECUTION_FLOW__RESULTS);
            this.childrenFeatures.add(ExecutionflowPackage.Literals.EXECUTION_FLOW__FLOW_CONNECTIONS);
            this.childrenFeatures.add(ExecutionflowPackage.Literals.EXECUTION_FLOW__EXECUTION_FLOW_OBJECTS);
            this.childrenFeatures.add(ExecutionflowPackage.Literals.EXECUTION_FLOW__START_POINT);
            this.childrenFeatures.add(ExecutionflowPackage.Literals.EXECUTION_FLOW__START_POINT_CONNECTION);
        }
        return this.childrenFeatures;
    }

    protected EStructuralFeature getChildFeature(Object object, Object child) {
        return super.getChildFeature(object, child);
    }

    public Object getImage(Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/ExecutionFlow"));
    }

    public String getText(Object object) {
        String label = ((ExecutionFlow)object).getId();
        return label == null || label.length() == 0 ? this.getString("_UI_ExecutionFlow_type") : String.valueOf(this.getString("_UI_ExecutionFlow_type")) + " " + label;
    }

    public void notifyChanged(Notification notification) {
        this.updateChildren(notification);
        switch (notification.getFeatureID(ExecutionFlow.class)) {
            case 0: 
            case 7: 
            case 8: 
            case 9: {
                this.fireNotifyChanged((Notification)new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            }
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: {
                this.fireNotifyChanged((Notification)new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
            }
        }
        super.notifyChanged(notification);
    }

    protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
        newChildDescriptors.add(this.createChildParameter(ExecutionflowPackage.Literals.EXECUTION_FLOW__EXECUTABLE_FLOW_OBJECTS, ExecutionflowFactory.eINSTANCE.createExecutableFlowObject()));
        newChildDescriptors.add(this.createChildParameter(ExecutionflowPackage.Literals.EXECUTION_FLOW__RESULTS, ExecutionflowFactory.eINSTANCE.createExecutionFlowResult()));
        newChildDescriptors.add(this.createChildParameter(ExecutionflowPackage.Literals.EXECUTION_FLOW__FLOW_CONNECTIONS, ExecutionflowFactory.eINSTANCE.createFlowObjectConnection()));
        newChildDescriptors.add(this.createChildParameter(ExecutionflowPackage.Literals.EXECUTION_FLOW__EXECUTION_FLOW_OBJECTS, ExecutionflowFactory.eINSTANCE.createExecutionFlowObject()));
        newChildDescriptors.add(this.createChildParameter(ExecutionflowPackage.Literals.EXECUTION_FLOW__START_POINT, ExecutionflowFactory.eINSTANCE.createStartPoint()));
        newChildDescriptors.add(this.createChildParameter(ExecutionflowPackage.Literals.EXECUTION_FLOW__START_POINT_CONNECTION, ExecutionflowFactory.eINSTANCE.createStartPointConnection()));
    }

    public ResourceLocator getResourceLocator() {
        return ExecutionflowEditPlugin.INSTANCE;
    }
}

