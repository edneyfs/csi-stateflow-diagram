/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.csi.gmf.stateflow.provider;


import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowFactory;
import com.csi.gmf.stateflow.StateflowPackage;

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

/**
 * This is the item provider adapter for a {@link com.csi.gmf.stateflow.StateFlow} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StateFlowItemProvider
	extends ItemProviderAdapter
	implements	
		IEditingDomainItemProvider,	
		IStructuredItemContentProvider,	
		ITreeItemContentProvider,	
		IItemLabelProvider,	
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateFlowItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addIdPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addVolatilePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_StateFlow_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_StateFlow_id_feature", "_UI_StateFlow_type"),
				 StateflowPackage.Literals.STATE_FLOW__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_StateFlow_description_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_StateFlow_description_feature", "_UI_StateFlow_type"),
				 StateflowPackage.Literals.STATE_FLOW__DESCRIPTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Volatile feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVolatilePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_StateFlow_volatile_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_StateFlow_volatile_feature", "_UI_StateFlow_type"),
				 StateflowPackage.Literals.STATE_FLOW__VOLATILE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__STATES);
			childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__EXECUTION_FLOWS);
			childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__STATE_CONNECTIONS);
			childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__FLOW_CONNECTIONS);
			childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__START_POINT);
			childrenFeatures.add(StateflowPackage.Literals.STATE_FLOW__START_POINT_CONNECTION);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns StateFlow.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/StateFlow"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((StateFlow)object).getId();
		return label == null || label.length() == 0 ?
			getString("_UI_StateFlow_type") :
			getString("_UI_StateFlow_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(StateFlow.class)) {
			case StateflowPackage.STATE_FLOW__ID:
			case StateflowPackage.STATE_FLOW__DESCRIPTION:
			case StateflowPackage.STATE_FLOW__VOLATILE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case StateflowPackage.STATE_FLOW__STATES:
			case StateflowPackage.STATE_FLOW__EXECUTION_FLOWS:
			case StateflowPackage.STATE_FLOW__STATE_CONNECTIONS:
			case StateflowPackage.STATE_FLOW__FLOW_CONNECTIONS:
			case StateflowPackage.STATE_FLOW__START_POINT:
			case StateflowPackage.STATE_FLOW__START_POINT_CONNECTION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(StateflowPackage.Literals.STATE_FLOW__STATES,
				 StateflowFactory.eINSTANCE.createState()));

		newChildDescriptors.add
			(createChildParameter
				(StateflowPackage.Literals.STATE_FLOW__EXECUTION_FLOWS,
				 StateflowFactory.eINSTANCE.createExecutionFlowObject()));

		newChildDescriptors.add
			(createChildParameter
				(StateflowPackage.Literals.STATE_FLOW__STATE_CONNECTIONS,
				 StateflowFactory.eINSTANCE.createEventConnection()));

		newChildDescriptors.add
			(createChildParameter
				(StateflowPackage.Literals.STATE_FLOW__FLOW_CONNECTIONS,
				 StateflowFactory.eINSTANCE.createFlowObjectConnection()));

		newChildDescriptors.add
			(createChildParameter
				(StateflowPackage.Literals.STATE_FLOW__START_POINT,
				 StateflowFactory.eINSTANCE.createStartPoint()));

		newChildDescriptors.add
			(createChildParameter
				(StateflowPackage.Literals.STATE_FLOW__START_POINT_CONNECTION,
				 StateflowFactory.eINSTANCE.createStartPointConnection()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return StateflowEditPlugin.INSTANCE;
	}

}
