/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.csi.gmf.stateflow.impl;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.FlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowFactory;
import com.csi.gmf.stateflow.StateflowPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StateflowPackageImpl extends EPackageImpl implements StateflowPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionFlowObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowObjectConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateFlowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startPointConnectionEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.csi.gmf.stateflow.StateflowPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StateflowPackageImpl() {
		super(eNS_URI, StateflowFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StateflowPackage init() {
		if (isInited) return (StateflowPackage)EPackage.Registry.INSTANCE.getEPackage(StateflowPackage.eNS_URI);

		// Obtain or create and register package
		StateflowPackageImpl theStateflowPackage = (StateflowPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof StateflowPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new StateflowPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theStateflowPackage.createPackageContents();

		// Initialize created meta-data
		theStateflowPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStateflowPackage.freeze();

		return theStateflowPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionFlowObject() {
		return executionFlowObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionFlowObject_FilePath() {
		return (EAttribute)executionFlowObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionFlowObject_Title() {
		return (EAttribute)executionFlowObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionFlowObject_Id() {
		return (EAttribute)executionFlowObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionFlowObject_Description() {
		return (EAttribute)executionFlowObjectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFlowObject() {
		return flowObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFlowObject_PathKey() {
		return (EAttribute)flowObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFlowObjectConnection() {
		return flowObjectConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFlowObjectConnection_SourcePathKey() {
		return (EAttribute)flowObjectConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFlowObjectConnection_DestinationPathKey() {
		return (EAttribute)flowObjectConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFlowObjectConnection_ExecutionFlowResultValue() {
		return (EAttribute)flowObjectConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFlowObjectConnection_SourceFlow() {
		return (EReference)flowObjectConnectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFlowObjectConnection_DestinationState() {
		return (EReference)flowObjectConnectionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_Id() {
		return (EAttribute)stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_Description() {
		return (EAttribute)stateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStateFlow() {
		return stateFlowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateFlow_States() {
		return (EReference)stateFlowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateFlow_ExecutionFlows() {
		return (EReference)stateFlowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateFlow_StateConnections() {
		return (EReference)stateFlowEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateFlow_FlowConnections() {
		return (EReference)stateFlowEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateFlow_StartPoint() {
		return (EReference)stateFlowEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateFlow_StartPointConnection() {
		return (EReference)stateFlowEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateFlow_Id() {
		return (EAttribute)stateFlowEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateFlow_Description() {
		return (EAttribute)stateFlowEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateFlow_Volatile() {
		return (EAttribute)stateFlowEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventConnection() {
		return eventConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventConnection_SourcePathKey() {
		return (EAttribute)eventConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventConnection_DestinationPathKey() {
		return (EAttribute)eventConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventConnection_EventID() {
		return (EAttribute)eventConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventConnection_SourceState() {
		return (EReference)eventConnectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventConnection_DestinationFlow() {
		return (EReference)eventConnectionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStartPoint() {
		return startPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStartPointConnection() {
		return startPointConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStartPointConnection_StartPoint() {
		return (EReference)startPointConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStartPointConnection_DestinationState() {
		return (EReference)startPointConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartPointConnection_DestinationStateId() {
		return (EAttribute)startPointConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateflowFactory getStateflowFactory() {
		return (StateflowFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		executionFlowObjectEClass = createEClass(EXECUTION_FLOW_OBJECT);
		createEAttribute(executionFlowObjectEClass, EXECUTION_FLOW_OBJECT__FILE_PATH);
		createEAttribute(executionFlowObjectEClass, EXECUTION_FLOW_OBJECT__TITLE);
		createEAttribute(executionFlowObjectEClass, EXECUTION_FLOW_OBJECT__ID);
		createEAttribute(executionFlowObjectEClass, EXECUTION_FLOW_OBJECT__DESCRIPTION);

		flowObjectEClass = createEClass(FLOW_OBJECT);
		createEAttribute(flowObjectEClass, FLOW_OBJECT__PATH_KEY);

		flowObjectConnectionEClass = createEClass(FLOW_OBJECT_CONNECTION);
		createEAttribute(flowObjectConnectionEClass, FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY);
		createEAttribute(flowObjectConnectionEClass, FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY);
		createEAttribute(flowObjectConnectionEClass, FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE);
		createEReference(flowObjectConnectionEClass, FLOW_OBJECT_CONNECTION__SOURCE_FLOW);
		createEReference(flowObjectConnectionEClass, FLOW_OBJECT_CONNECTION__DESTINATION_STATE);

		stateEClass = createEClass(STATE);
		createEAttribute(stateEClass, STATE__ID);
		createEAttribute(stateEClass, STATE__DESCRIPTION);

		stateFlowEClass = createEClass(STATE_FLOW);
		createEReference(stateFlowEClass, STATE_FLOW__STATES);
		createEReference(stateFlowEClass, STATE_FLOW__EXECUTION_FLOWS);
		createEReference(stateFlowEClass, STATE_FLOW__STATE_CONNECTIONS);
		createEReference(stateFlowEClass, STATE_FLOW__FLOW_CONNECTIONS);
		createEReference(stateFlowEClass, STATE_FLOW__START_POINT);
		createEReference(stateFlowEClass, STATE_FLOW__START_POINT_CONNECTION);
		createEAttribute(stateFlowEClass, STATE_FLOW__ID);
		createEAttribute(stateFlowEClass, STATE_FLOW__DESCRIPTION);
		createEAttribute(stateFlowEClass, STATE_FLOW__VOLATILE);

		eventConnectionEClass = createEClass(EVENT_CONNECTION);
		createEAttribute(eventConnectionEClass, EVENT_CONNECTION__SOURCE_PATH_KEY);
		createEAttribute(eventConnectionEClass, EVENT_CONNECTION__DESTINATION_PATH_KEY);
		createEAttribute(eventConnectionEClass, EVENT_CONNECTION__EVENT_ID);
		createEReference(eventConnectionEClass, EVENT_CONNECTION__SOURCE_STATE);
		createEReference(eventConnectionEClass, EVENT_CONNECTION__DESTINATION_FLOW);

		startPointEClass = createEClass(START_POINT);

		startPointConnectionEClass = createEClass(START_POINT_CONNECTION);
		createEReference(startPointConnectionEClass, START_POINT_CONNECTION__START_POINT);
		createEReference(startPointConnectionEClass, START_POINT_CONNECTION__DESTINATION_STATE);
		createEAttribute(startPointConnectionEClass, START_POINT_CONNECTION__DESTINATION_STATE_ID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Add supertypes to classes
		executionFlowObjectEClass.getESuperTypes().add(this.getFlowObject());
		stateEClass.getESuperTypes().add(this.getFlowObject());

		// Initialize classes and features; add operations and parameters
		initEClass(executionFlowObjectEClass, ExecutionFlowObject.class, "ExecutionFlowObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecutionFlowObject_FilePath(), ecorePackage.getEString(), "filePath", null, 0, 1, ExecutionFlowObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionFlowObject_Title(), ecorePackage.getEString(), "title", null, 0, 1, ExecutionFlowObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionFlowObject_Id(), ecorePackage.getEString(), "id", null, 0, 1, ExecutionFlowObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExecutionFlowObject_Description(), ecorePackage.getEString(), "description", null, 0, 1, ExecutionFlowObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(flowObjectEClass, FlowObject.class, "FlowObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFlowObject_PathKey(), ecorePackage.getELongObject(), "pathKey", null, 1, 1, FlowObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(flowObjectConnectionEClass, FlowObjectConnection.class, "FlowObjectConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFlowObjectConnection_SourcePathKey(), ecorePackage.getELongObject(), "sourcePathKey", null, 0, 1, FlowObjectConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlowObjectConnection_DestinationPathKey(), ecorePackage.getELongObject(), "destinationPathKey", null, 0, 1, FlowObjectConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlowObjectConnection_ExecutionFlowResultValue(), ecorePackage.getEString(), "executionFlowResultValue", null, 0, 1, FlowObjectConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowObjectConnection_SourceFlow(), this.getExecutionFlowObject(), null, "sourceFlow", null, 0, 1, FlowObjectConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlowObjectConnection_DestinationState(), this.getState(), null, "destinationState", null, 0, 1, FlowObjectConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateEClass, State.class, "State", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getState_Id(), ecorePackage.getEString(), "id", null, 1, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_Description(), ecorePackage.getEString(), "description", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateFlowEClass, StateFlow.class, "StateFlow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateFlow_States(), this.getState(), null, "states", null, 0, -1, StateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateFlow_ExecutionFlows(), this.getExecutionFlowObject(), null, "executionFlows", null, 0, -1, StateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateFlow_StateConnections(), this.getEventConnection(), null, "stateConnections", null, 0, -1, StateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateFlow_FlowConnections(), this.getFlowObjectConnection(), null, "flowConnections", null, 0, -1, StateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateFlow_StartPoint(), this.getStartPoint(), null, "startPoint", null, 0, 1, StateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateFlow_StartPointConnection(), this.getStartPointConnection(), null, "startPointConnection", null, 0, 1, StateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateFlow_Id(), ecorePackage.getEString(), "id", null, 0, 1, StateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateFlow_Description(), ecorePackage.getEString(), "description", "", 0, 1, StateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateFlow_Volatile(), ecorePackage.getEBoolean(), "volatile", "false", 1, 1, StateFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventConnectionEClass, EventConnection.class, "EventConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventConnection_SourcePathKey(), ecorePackage.getELongObject(), "sourcePathKey", null, 0, 1, EventConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventConnection_DestinationPathKey(), ecorePackage.getELongObject(), "destinationPathKey", null, 0, 1, EventConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventConnection_EventID(), ecorePackage.getEString(), "eventID", null, 0, 1, EventConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventConnection_SourceState(), this.getState(), null, "sourceState", null, 0, 1, EventConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventConnection_DestinationFlow(), this.getExecutionFlowObject(), null, "destinationFlow", null, 0, 1, EventConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(startPointEClass, StartPoint.class, "StartPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(startPointConnectionEClass, StartPointConnection.class, "StartPointConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStartPointConnection_StartPoint(), this.getStartPoint(), null, "startPoint", null, 0, 1, StartPointConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStartPointConnection_DestinationState(), this.getState(), null, "destinationState", null, 0, 1, StartPointConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartPointConnection_DestinationStateId(), ecorePackage.getEString(), "destinationStateId", null, 0, 1, StartPointConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //StateflowPackageImpl
