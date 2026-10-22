/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.csi.gmf.stateflow.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.csi.gmf.stateflow.FlowObject;
import com.csi.gmf.stateflow.StateflowPackage;
import org.eclipse.emf.common.notify.Notification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flow Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.csi.gmf.stateflow.impl.FlowObjectImpl#getPathKey <em>Path Key</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FlowObjectImpl extends EObjectImpl implements FlowObject {
	/**
	 * The default value of the '{@link #getPathKey() <em>Path Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathKey()
	 * @generated
	 * @ordered
	 */
	protected static final Long PATH_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPathKey() <em>Path Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathKey()
	 * @generated
	 * @ordered
	 */
	protected Long pathKey = PATH_KEY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlowObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return StateflowPackage.Literals.FLOW_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getPathKey() {
		return pathKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPathKey(Long newPathKey) {
		Long oldPathKey = pathKey;
		pathKey = newPathKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.FLOW_OBJECT__PATH_KEY, oldPathKey, pathKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StateflowPackage.FLOW_OBJECT__PATH_KEY:
				return getPathKey();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StateflowPackage.FLOW_OBJECT__PATH_KEY:
				setPathKey((Long)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case StateflowPackage.FLOW_OBJECT__PATH_KEY:
				setPathKey(PATH_KEY_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case StateflowPackage.FLOW_OBJECT__PATH_KEY:
				return PATH_KEY_EDEFAULT == null ? pathKey != null : !PATH_KEY_EDEFAULT.equals(pathKey);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (pathKey: ");
		result.append(pathKey);
		result.append(')');
		return result.toString();
	}

} //FlowObjectImpl