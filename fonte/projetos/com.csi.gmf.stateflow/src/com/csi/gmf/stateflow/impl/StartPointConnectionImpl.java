/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.csi.gmf.stateflow.impl;

import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateflowPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Start Point Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.csi.gmf.stateflow.impl.StartPointConnectionImpl#getStartPoint <em>Start Point</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.StartPointConnectionImpl#getDestinationState <em>Destination State</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.StartPointConnectionImpl#getDestinationStateId <em>Destination State Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StartPointConnectionImpl extends EObjectImpl implements StartPointConnection {
	/**
	 * The cached value of the '{@link #getStartPoint() <em>Start Point</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartPoint()
	 * @generated
	 * @ordered
	 */
	protected StartPoint startPoint;

	/**
	 * The cached value of the '{@link #getDestinationState() <em>Destination State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestinationState()
	 * @generated
	 * @ordered
	 */
	protected State destinationState;

	/**
	 * The default value of the '{@link #getDestinationStateId() <em>Destination State Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestinationStateId()
	 * @generated
	 * @ordered
	 */
	protected static final String DESTINATION_STATE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDestinationStateId() <em>Destination State Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestinationStateId()
	 * @generated
	 * @ordered
	 */
	protected String destinationStateId = DESTINATION_STATE_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StartPointConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return StateflowPackage.Literals.START_POINT_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartPoint getStartPoint() {
		if (startPoint != null && startPoint.eIsProxy()) {
			InternalEObject oldStartPoint = (InternalEObject)startPoint;
			startPoint = (StartPoint)eResolveProxy(oldStartPoint);
			if (startPoint != oldStartPoint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StateflowPackage.START_POINT_CONNECTION__START_POINT, oldStartPoint, startPoint));
			}
		}
		return startPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartPoint basicGetStartPoint() {
		return startPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartPoint(StartPoint newStartPoint) {
		StartPoint oldStartPoint = startPoint;
		startPoint = newStartPoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.START_POINT_CONNECTION__START_POINT, oldStartPoint, startPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State getDestinationState() {
		if (destinationState != null && destinationState.eIsProxy()) {
			InternalEObject oldDestinationState = (InternalEObject)destinationState;
			destinationState = (State)eResolveProxy(oldDestinationState);
			if (destinationState != oldDestinationState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StateflowPackage.START_POINT_CONNECTION__DESTINATION_STATE, oldDestinationState, destinationState));
			}
		}
		return destinationState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State basicGetDestinationState() {
		return destinationState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setDestinationState(State newDestinationState) {
		State oldDestinationState = destinationState;
		destinationState = newDestinationState;
		destinationStateId = newDestinationState.getId();
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.START_POINT_CONNECTION__DESTINATION_STATE, oldDestinationState, destinationState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDestinationStateId() {
		return destinationStateId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestinationStateId(String newDestinationStateId) {
		String oldDestinationStateId = destinationStateId;
		destinationStateId = newDestinationStateId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.START_POINT_CONNECTION__DESTINATION_STATE_ID, oldDestinationStateId, destinationStateId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StateflowPackage.START_POINT_CONNECTION__START_POINT:
				if (resolve) return getStartPoint();
				return basicGetStartPoint();
			case StateflowPackage.START_POINT_CONNECTION__DESTINATION_STATE:
				if (resolve) return getDestinationState();
				return basicGetDestinationState();
			case StateflowPackage.START_POINT_CONNECTION__DESTINATION_STATE_ID:
				return getDestinationStateId();
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
			case StateflowPackage.START_POINT_CONNECTION__START_POINT:
				setStartPoint((StartPoint)newValue);
				return;
			case StateflowPackage.START_POINT_CONNECTION__DESTINATION_STATE:
				setDestinationState((State)newValue);
				return;
			case StateflowPackage.START_POINT_CONNECTION__DESTINATION_STATE_ID:
				setDestinationStateId((String)newValue);
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
			case StateflowPackage.START_POINT_CONNECTION__START_POINT:
				setStartPoint((StartPoint)null);
				return;
			case StateflowPackage.START_POINT_CONNECTION__DESTINATION_STATE:
				setDestinationState((State)null);
				return;
			case StateflowPackage.START_POINT_CONNECTION__DESTINATION_STATE_ID:
				setDestinationStateId(DESTINATION_STATE_ID_EDEFAULT);
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
			case StateflowPackage.START_POINT_CONNECTION__START_POINT:
				return startPoint != null;
			case StateflowPackage.START_POINT_CONNECTION__DESTINATION_STATE:
				return destinationState != null;
			case StateflowPackage.START_POINT_CONNECTION__DESTINATION_STATE_ID:
				return DESTINATION_STATE_ID_EDEFAULT == null ? destinationStateId != null : !DESTINATION_STATE_ID_EDEFAULT.equals(destinationStateId);
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
		result.append(" (destinationStateId: ");
		result.append(destinationStateId);
		result.append(')');
		return result.toString();
	}

} //StartPointConnectionImpl