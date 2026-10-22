/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.csi.gmf.stateflow.impl;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateflowPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.csi.gmf.stateflow.impl.EventConnectionImpl#getSourcePathKey <em>Source Path Key</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.EventConnectionImpl#getDestinationPathKey <em>Destination Path Key</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.EventConnectionImpl#getEventID <em>Event ID</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.EventConnectionImpl#getSourceState <em>Source State</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.EventConnectionImpl#getDestinationFlow <em>Destination Flow</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventConnectionImpl extends EObjectImpl implements EventConnection {
	/**
	 * The default value of the '{@link #getSourcePathKey() <em>Source Path Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourcePathKey()
	 * @generated
	 * @ordered
	 */
	protected static final Long SOURCE_PATH_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourcePathKey() <em>Source Path Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourcePathKey()
	 * @generated
	 * @ordered
	 */
	protected Long sourcePathKey = SOURCE_PATH_KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getDestinationPathKey() <em>Destination Path Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestinationPathKey()
	 * @generated
	 * @ordered
	 */
	protected static final Long DESTINATION_PATH_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDestinationPathKey() <em>Destination Path Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestinationPathKey()
	 * @generated
	 * @ordered
	 */
	protected Long destinationPathKey = DESTINATION_PATH_KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getEventID() <em>Event ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventID()
	 * @generated
	 * @ordered
	 */
	protected static final String EVENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEventID() <em>Event ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventID()
	 * @generated
	 * @ordered
	 */
	protected String eventID = EVENT_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSourceState() <em>Source State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceState()
	 * @generated
	 * @ordered
	 */
	protected State sourceState;

	/**
	 * The cached value of the '{@link #getDestinationFlow() <em>Destination Flow</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestinationFlow()
	 * @generated
	 * @ordered
	 */
	protected ExecutionFlowObject destinationFlow;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return StateflowPackage.Literals.EVENT_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getSourcePathKey() {
		return sourcePathKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourcePathKey(Long newSourcePathKey) {
		Long oldSourcePathKey = sourcePathKey;
		sourcePathKey = newSourcePathKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.EVENT_CONNECTION__SOURCE_PATH_KEY, oldSourcePathKey, sourcePathKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getDestinationPathKey() {
		return destinationPathKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestinationPathKey(Long newDestinationPathKey) {
		Long oldDestinationPathKey = destinationPathKey;
		destinationPathKey = newDestinationPathKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.EVENT_CONNECTION__DESTINATION_PATH_KEY, oldDestinationPathKey, destinationPathKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEventID() {
		return eventID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventID(String newEventID) {
		String oldEventID = eventID;
		eventID = newEventID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.EVENT_CONNECTION__EVENT_ID, oldEventID, eventID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State getSourceState() {
		if (sourceState != null && sourceState.eIsProxy()) {
			InternalEObject oldSourceState = (InternalEObject)sourceState;
			sourceState = (State)eResolveProxy(oldSourceState);
			if (sourceState != oldSourceState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StateflowPackage.EVENT_CONNECTION__SOURCE_STATE, oldSourceState, sourceState));
			}
		}
		return sourceState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State basicGetSourceState() {
		return sourceState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setSourceState(State newSourceState) {
		State oldSourceState = sourceState;
		sourceState = newSourceState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.EVENT_CONNECTION__SOURCE_STATE, oldSourceState, sourceState));

		if (newSourceState != null) {
			setSourcePathKey(newSourceState.getPathKey());
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionFlowObject getDestinationFlow() {
		if (destinationFlow != null && destinationFlow.eIsProxy()) {
			InternalEObject oldDestinationFlow = (InternalEObject)destinationFlow;
			destinationFlow = (ExecutionFlowObject)eResolveProxy(oldDestinationFlow);
			if (destinationFlow != oldDestinationFlow) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StateflowPackage.EVENT_CONNECTION__DESTINATION_FLOW, oldDestinationFlow, destinationFlow));
			}
		}
		return destinationFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionFlowObject basicGetDestinationFlow() {
		return destinationFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setDestinationFlow(ExecutionFlowObject newDestinationFlow) {
		ExecutionFlowObject oldDestinationFlow = destinationFlow;
		destinationFlow = newDestinationFlow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.EVENT_CONNECTION__DESTINATION_FLOW, oldDestinationFlow, destinationFlow));

		if (newDestinationFlow != null) {
			setDestinationPathKey(newDestinationFlow.getPathKey());
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StateflowPackage.EVENT_CONNECTION__SOURCE_PATH_KEY:
				return getSourcePathKey();
			case StateflowPackage.EVENT_CONNECTION__DESTINATION_PATH_KEY:
				return getDestinationPathKey();
			case StateflowPackage.EVENT_CONNECTION__EVENT_ID:
				return getEventID();
			case StateflowPackage.EVENT_CONNECTION__SOURCE_STATE:
				if (resolve) return getSourceState();
				return basicGetSourceState();
			case StateflowPackage.EVENT_CONNECTION__DESTINATION_FLOW:
				if (resolve) return getDestinationFlow();
				return basicGetDestinationFlow();
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
			case StateflowPackage.EVENT_CONNECTION__SOURCE_PATH_KEY:
				setSourcePathKey((Long)newValue);
				return;
			case StateflowPackage.EVENT_CONNECTION__DESTINATION_PATH_KEY:
				setDestinationPathKey((Long)newValue);
				return;
			case StateflowPackage.EVENT_CONNECTION__EVENT_ID:
				setEventID((String)newValue);
				return;
			case StateflowPackage.EVENT_CONNECTION__SOURCE_STATE:
				setSourceState((State)newValue);
				return;
			case StateflowPackage.EVENT_CONNECTION__DESTINATION_FLOW:
				setDestinationFlow((ExecutionFlowObject)newValue);
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
			case StateflowPackage.EVENT_CONNECTION__SOURCE_PATH_KEY:
				setSourcePathKey(SOURCE_PATH_KEY_EDEFAULT);
				return;
			case StateflowPackage.EVENT_CONNECTION__DESTINATION_PATH_KEY:
				setDestinationPathKey(DESTINATION_PATH_KEY_EDEFAULT);
				return;
			case StateflowPackage.EVENT_CONNECTION__EVENT_ID:
				setEventID(EVENT_ID_EDEFAULT);
				return;
			case StateflowPackage.EVENT_CONNECTION__SOURCE_STATE:
				setSourceState((State)null);
				return;
			case StateflowPackage.EVENT_CONNECTION__DESTINATION_FLOW:
				setDestinationFlow((ExecutionFlowObject)null);
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
			case StateflowPackage.EVENT_CONNECTION__SOURCE_PATH_KEY:
				return SOURCE_PATH_KEY_EDEFAULT == null ? sourcePathKey != null : !SOURCE_PATH_KEY_EDEFAULT.equals(sourcePathKey);
			case StateflowPackage.EVENT_CONNECTION__DESTINATION_PATH_KEY:
				return DESTINATION_PATH_KEY_EDEFAULT == null ? destinationPathKey != null : !DESTINATION_PATH_KEY_EDEFAULT.equals(destinationPathKey);
			case StateflowPackage.EVENT_CONNECTION__EVENT_ID:
				return EVENT_ID_EDEFAULT == null ? eventID != null : !EVENT_ID_EDEFAULT.equals(eventID);
			case StateflowPackage.EVENT_CONNECTION__SOURCE_STATE:
				return sourceState != null;
			case StateflowPackage.EVENT_CONNECTION__DESTINATION_FLOW:
				return destinationFlow != null;
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
		result.append(" (sourcePathKey: ");
		result.append(sourcePathKey);
		result.append(", destinationPathKey: ");
		result.append(destinationPathKey);
		result.append(", eventID: ");
		result.append(eventID);
		result.append(')');
		return result.toString();
	}

} //EventConnectionImpl