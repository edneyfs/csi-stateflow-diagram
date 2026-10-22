/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.csi.gmf.stateflow.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateflowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flow Object Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.csi.gmf.stateflow.impl.FlowObjectConnectionImpl#getSourcePathKey <em>Source Path Key</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.FlowObjectConnectionImpl#getDestinationPathKey <em>Destination Path Key</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.FlowObjectConnectionImpl#getExecutionFlowResultValue <em>Execution Flow Result Value</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.FlowObjectConnectionImpl#getSourceFlow <em>Source Flow</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.FlowObjectConnectionImpl#getDestinationState <em>Destination State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FlowObjectConnectionImpl extends EObjectImpl implements FlowObjectConnection {
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
	 * The default value of the '{@link #getExecutionFlowResultValue() <em>Execution Flow Result Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionFlowResultValue()
	 * @generated
	 * @ordered
	 */
	protected static final String EXECUTION_FLOW_RESULT_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExecutionFlowResultValue() <em>Execution Flow Result Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionFlowResultValue()
	 * @generated
	 * @ordered
	 */
	protected String executionFlowResultValue = EXECUTION_FLOW_RESULT_VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSourceFlow() <em>Source Flow</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceFlow()
	 * @generated
	 * @ordered
	 */
	protected ExecutionFlowObject sourceFlow;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlowObjectConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return StateflowPackage.Literals.FLOW_OBJECT_CONNECTION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY, oldSourcePathKey, sourcePathKey));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY, oldDestinationPathKey, destinationPathKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExecutionFlowResultValue() {
		return executionFlowResultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionFlowResultValue(String newExecutionFlowResultValue) {
		String oldExecutionFlowResultValue = executionFlowResultValue;
		executionFlowResultValue = newExecutionFlowResultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE, oldExecutionFlowResultValue, executionFlowResultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionFlowObject getSourceFlow() {
		if (sourceFlow != null && sourceFlow.eIsProxy()) {
			InternalEObject oldSourceFlow = (InternalEObject)sourceFlow;
			sourceFlow = (ExecutionFlowObject)eResolveProxy(oldSourceFlow);
			if (sourceFlow != oldSourceFlow) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StateflowPackage.FLOW_OBJECT_CONNECTION__SOURCE_FLOW, oldSourceFlow, sourceFlow));
			}
		}
		return sourceFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionFlowObject basicGetSourceFlow() {
		return sourceFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setSourceFlow(ExecutionFlowObject newSourceFlow) {
		ExecutionFlowObject oldSourceFlow = sourceFlow;
		sourceFlow = newSourceFlow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.FLOW_OBJECT_CONNECTION__SOURCE_FLOW, oldSourceFlow, sourceFlow));
		
		if (newSourceFlow != null) {
			setSourcePathKey(newSourceFlow.getPathKey());
		}
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StateflowPackage.FLOW_OBJECT_CONNECTION__DESTINATION_STATE, oldDestinationState, destinationState));
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
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.FLOW_OBJECT_CONNECTION__DESTINATION_STATE, oldDestinationState, destinationState));
		
		if (newDestinationState != null) {
			setDestinationPathKey(newDestinationState.getPathKey());
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StateflowPackage.FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY:
				return getSourcePathKey();
			case StateflowPackage.FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY:
				return getDestinationPathKey();
			case StateflowPackage.FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE:
				return getExecutionFlowResultValue();
			case StateflowPackage.FLOW_OBJECT_CONNECTION__SOURCE_FLOW:
				if (resolve) return getSourceFlow();
				return basicGetSourceFlow();
			case StateflowPackage.FLOW_OBJECT_CONNECTION__DESTINATION_STATE:
				if (resolve) return getDestinationState();
				return basicGetDestinationState();
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
			case StateflowPackage.FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY:
				setSourcePathKey((Long)newValue);
				return;
			case StateflowPackage.FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY:
				setDestinationPathKey((Long)newValue);
				return;
			case StateflowPackage.FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE:
				setExecutionFlowResultValue((String)newValue);
				return;
			case StateflowPackage.FLOW_OBJECT_CONNECTION__SOURCE_FLOW:
				setSourceFlow((ExecutionFlowObject)newValue);
				return;
			case StateflowPackage.FLOW_OBJECT_CONNECTION__DESTINATION_STATE:
				setDestinationState((State)newValue);
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
			case StateflowPackage.FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY:
				setSourcePathKey(SOURCE_PATH_KEY_EDEFAULT);
				return;
			case StateflowPackage.FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY:
				setDestinationPathKey(DESTINATION_PATH_KEY_EDEFAULT);
				return;
			case StateflowPackage.FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE:
				setExecutionFlowResultValue(EXECUTION_FLOW_RESULT_VALUE_EDEFAULT);
				return;
			case StateflowPackage.FLOW_OBJECT_CONNECTION__SOURCE_FLOW:
				setSourceFlow((ExecutionFlowObject)null);
				return;
			case StateflowPackage.FLOW_OBJECT_CONNECTION__DESTINATION_STATE:
				setDestinationState((State)null);
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
			case StateflowPackage.FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY:
				return SOURCE_PATH_KEY_EDEFAULT == null ? sourcePathKey != null : !SOURCE_PATH_KEY_EDEFAULT.equals(sourcePathKey);
			case StateflowPackage.FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY:
				return DESTINATION_PATH_KEY_EDEFAULT == null ? destinationPathKey != null : !DESTINATION_PATH_KEY_EDEFAULT.equals(destinationPathKey);
			case StateflowPackage.FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE:
				return EXECUTION_FLOW_RESULT_VALUE_EDEFAULT == null ? executionFlowResultValue != null : !EXECUTION_FLOW_RESULT_VALUE_EDEFAULT.equals(executionFlowResultValue);
			case StateflowPackage.FLOW_OBJECT_CONNECTION__SOURCE_FLOW:
				return sourceFlow != null;
			case StateflowPackage.FLOW_OBJECT_CONNECTION__DESTINATION_STATE:
				return destinationState != null;
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
		result.append(", executionFlowResultValue: ");
		result.append(executionFlowResultValue);
		result.append(')');
		return result.toString();
	}

} //FlowObjectConnectionImpl