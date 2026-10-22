/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.csi.gmf.stateflow.impl;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.csi.gmf.stateflow.impl.StateFlowImpl#getStates <em>States</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.StateFlowImpl#getExecutionFlows <em>Execution Flows</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.StateFlowImpl#getStateConnections <em>State Connections</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.StateFlowImpl#getFlowConnections <em>Flow Connections</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.StateFlowImpl#getStartPoint <em>Start Point</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.StateFlowImpl#getStartPointConnection <em>Start Point Connection</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.StateFlowImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.StateFlowImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.csi.gmf.stateflow.impl.StateFlowImpl#isVolatile <em>Volatile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StateFlowImpl extends EObjectImpl implements StateFlow {
	/**
	 * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStates()
	 * @generated
	 * @ordered
	 */
	protected EList states;

	/**
	 * The cached value of the '{@link #getExecutionFlows() <em>Execution Flows</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionFlows()
	 * @generated
	 * @ordered
	 */
	protected EList executionFlows;

	/**
	 * The cached value of the '{@link #getStateConnections() <em>State Connections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateConnections()
	 * @generated
	 * @ordered
	 */
	protected EList stateConnections;

	/**
	 * The cached value of the '{@link #getFlowConnections() <em>Flow Connections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlowConnections()
	 * @generated
	 * @ordered
	 */
	protected EList flowConnections;

	/**
	 * The cached value of the '{@link #getStartPoint() <em>Start Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartPoint()
	 * @generated
	 * @ordered
	 */
	protected StartPoint startPoint;

	/**
	 * The cached value of the '{@link #getStartPointConnection() <em>Start Point Connection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartPointConnection()
	 * @generated
	 * @ordered
	 */
	protected StartPointConnection startPointConnection;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #isVolatile() <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVolatile()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VOLATILE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isVolatile() <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVolatile()
	 * @generated
	 * @ordered
	 */
	protected boolean volatile_ = VOLATILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateFlowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return StateflowPackage.Literals.STATE_FLOW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getStates() {
		if (states == null) {
			states = new EObjectContainmentEList(State.class, this, StateflowPackage.STATE_FLOW__STATES);
		}
		return states;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExecutionFlows() {
		if (executionFlows == null) {
			executionFlows = new EObjectContainmentEList(ExecutionFlowObject.class, this, StateflowPackage.STATE_FLOW__EXECUTION_FLOWS);
		}
		return executionFlows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getStateConnections() {
		if (stateConnections == null) {
			stateConnections = new EObjectContainmentEList(EventConnection.class, this, StateflowPackage.STATE_FLOW__STATE_CONNECTIONS);
		}
		return stateConnections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFlowConnections() {
		if (flowConnections == null) {
			flowConnections = new EObjectContainmentEList(FlowObjectConnection.class, this, StateflowPackage.STATE_FLOW__FLOW_CONNECTIONS);
		}
		return flowConnections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartPoint getStartPoint() {
		return startPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartPoint(StartPoint newStartPoint, NotificationChain msgs) {
		StartPoint oldStartPoint = startPoint;
		startPoint = newStartPoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StateflowPackage.STATE_FLOW__START_POINT, oldStartPoint, newStartPoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartPoint(StartPoint newStartPoint) {
		if (newStartPoint != startPoint) {
			NotificationChain msgs = null;
			if (startPoint != null)
				msgs = ((InternalEObject)startPoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StateflowPackage.STATE_FLOW__START_POINT, null, msgs);
			if (newStartPoint != null)
				msgs = ((InternalEObject)newStartPoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StateflowPackage.STATE_FLOW__START_POINT, null, msgs);
			msgs = basicSetStartPoint(newStartPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.STATE_FLOW__START_POINT, newStartPoint, newStartPoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartPointConnection getStartPointConnection() {
		return startPointConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartPointConnection(StartPointConnection newStartPointConnection, NotificationChain msgs) {
		StartPointConnection oldStartPointConnection = startPointConnection;
		startPointConnection = newStartPointConnection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StateflowPackage.STATE_FLOW__START_POINT_CONNECTION, oldStartPointConnection, newStartPointConnection);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartPointConnection(StartPointConnection newStartPointConnection) {
		if (newStartPointConnection != startPointConnection) {
			NotificationChain msgs = null;
			if (startPointConnection != null)
				msgs = ((InternalEObject)startPointConnection).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StateflowPackage.STATE_FLOW__START_POINT_CONNECTION, null, msgs);
			if (newStartPointConnection != null)
				msgs = ((InternalEObject)newStartPointConnection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StateflowPackage.STATE_FLOW__START_POINT_CONNECTION, null, msgs);
			msgs = basicSetStartPointConnection(newStartPointConnection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.STATE_FLOW__START_POINT_CONNECTION, newStartPointConnection, newStartPointConnection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.STATE_FLOW__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.STATE_FLOW__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVolatile() {
		return volatile_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVolatile(boolean newVolatile) {
		boolean oldVolatile = volatile_;
		volatile_ = newVolatile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StateflowPackage.STATE_FLOW__VOLATILE, oldVolatile, volatile_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StateflowPackage.STATE_FLOW__STATES:
				return ((InternalEList)getStates()).basicRemove(otherEnd, msgs);
			case StateflowPackage.STATE_FLOW__EXECUTION_FLOWS:
				return ((InternalEList)getExecutionFlows()).basicRemove(otherEnd, msgs);
			case StateflowPackage.STATE_FLOW__STATE_CONNECTIONS:
				return ((InternalEList)getStateConnections()).basicRemove(otherEnd, msgs);
			case StateflowPackage.STATE_FLOW__FLOW_CONNECTIONS:
				return ((InternalEList)getFlowConnections()).basicRemove(otherEnd, msgs);
			case StateflowPackage.STATE_FLOW__START_POINT:
				return basicSetStartPoint(null, msgs);
			case StateflowPackage.STATE_FLOW__START_POINT_CONNECTION:
				return basicSetStartPointConnection(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StateflowPackage.STATE_FLOW__STATES:
				return getStates();
			case StateflowPackage.STATE_FLOW__EXECUTION_FLOWS:
				return getExecutionFlows();
			case StateflowPackage.STATE_FLOW__STATE_CONNECTIONS:
				return getStateConnections();
			case StateflowPackage.STATE_FLOW__FLOW_CONNECTIONS:
				return getFlowConnections();
			case StateflowPackage.STATE_FLOW__START_POINT:
				return getStartPoint();
			case StateflowPackage.STATE_FLOW__START_POINT_CONNECTION:
				return getStartPointConnection();
			case StateflowPackage.STATE_FLOW__ID:
				return getId();
			case StateflowPackage.STATE_FLOW__DESCRIPTION:
				return getDescription();
			case StateflowPackage.STATE_FLOW__VOLATILE:
				return isVolatile() ? Boolean.TRUE : Boolean.FALSE;
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
			case StateflowPackage.STATE_FLOW__STATES:
				getStates().clear();
				getStates().addAll((Collection)newValue);
				return;
			case StateflowPackage.STATE_FLOW__EXECUTION_FLOWS:
				getExecutionFlows().clear();
				getExecutionFlows().addAll((Collection)newValue);
				return;
			case StateflowPackage.STATE_FLOW__STATE_CONNECTIONS:
				getStateConnections().clear();
				getStateConnections().addAll((Collection)newValue);
				return;
			case StateflowPackage.STATE_FLOW__FLOW_CONNECTIONS:
				getFlowConnections().clear();
				getFlowConnections().addAll((Collection)newValue);
				return;
			case StateflowPackage.STATE_FLOW__START_POINT:
				setStartPoint((StartPoint)newValue);
				return;
			case StateflowPackage.STATE_FLOW__START_POINT_CONNECTION:
				setStartPointConnection((StartPointConnection)newValue);
				return;
			case StateflowPackage.STATE_FLOW__ID:
				setId((String)newValue);
				return;
			case StateflowPackage.STATE_FLOW__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case StateflowPackage.STATE_FLOW__VOLATILE:
				setVolatile(((Boolean)newValue).booleanValue());
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
			case StateflowPackage.STATE_FLOW__STATES:
				getStates().clear();
				return;
			case StateflowPackage.STATE_FLOW__EXECUTION_FLOWS:
				getExecutionFlows().clear();
				return;
			case StateflowPackage.STATE_FLOW__STATE_CONNECTIONS:
				getStateConnections().clear();
				return;
			case StateflowPackage.STATE_FLOW__FLOW_CONNECTIONS:
				getFlowConnections().clear();
				return;
			case StateflowPackage.STATE_FLOW__START_POINT:
				setStartPoint((StartPoint)null);
				return;
			case StateflowPackage.STATE_FLOW__START_POINT_CONNECTION:
				setStartPointConnection((StartPointConnection)null);
				return;
			case StateflowPackage.STATE_FLOW__ID:
				setId(ID_EDEFAULT);
				return;
			case StateflowPackage.STATE_FLOW__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case StateflowPackage.STATE_FLOW__VOLATILE:
				setVolatile(VOLATILE_EDEFAULT);
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
			case StateflowPackage.STATE_FLOW__STATES:
				return states != null && !states.isEmpty();
			case StateflowPackage.STATE_FLOW__EXECUTION_FLOWS:
				return executionFlows != null && !executionFlows.isEmpty();
			case StateflowPackage.STATE_FLOW__STATE_CONNECTIONS:
				return stateConnections != null && !stateConnections.isEmpty();
			case StateflowPackage.STATE_FLOW__FLOW_CONNECTIONS:
				return flowConnections != null && !flowConnections.isEmpty();
			case StateflowPackage.STATE_FLOW__START_POINT:
				return startPoint != null;
			case StateflowPackage.STATE_FLOW__START_POINT_CONNECTION:
				return startPointConnection != null;
			case StateflowPackage.STATE_FLOW__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case StateflowPackage.STATE_FLOW__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case StateflowPackage.STATE_FLOW__VOLATILE:
				return volatile_ != VOLATILE_EDEFAULT;
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
		result.append(" (id: ");
		result.append(id);
		result.append(", description: ");
		result.append(description);
		result.append(", volatile: ");
		result.append(volatile_);
		result.append(')');
		return result.toString();
	}

} //StateFlowImpl