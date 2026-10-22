/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.csi.gmf.stateflow;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.csi.gmf.stateflow.StateflowFactory
 * @model kind="package"
 * @generated
 */
public interface StateflowPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "stateflow";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///com/csi/gmf/stateflow.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "com.csi.gmf.stateflow";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StateflowPackage eINSTANCE = com.csi.gmf.stateflow.impl.StateflowPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.csi.gmf.stateflow.impl.FlowObjectImpl <em>Flow Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.csi.gmf.stateflow.impl.FlowObjectImpl
	 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getFlowObject()
	 * @generated
	 */
	int FLOW_OBJECT = 1;

	/**
	 * The feature id for the '<em><b>Path Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_OBJECT__PATH_KEY = 0;

	/**
	 * The number of structural features of the '<em>Flow Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_OBJECT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.csi.gmf.stateflow.impl.ExecutionFlowObjectImpl <em>Execution Flow Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.csi.gmf.stateflow.impl.ExecutionFlowObjectImpl
	 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getExecutionFlowObject()
	 * @generated
	 */
	int EXECUTION_FLOW_OBJECT = 0;

	/**
	 * The feature id for the '<em><b>Path Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_FLOW_OBJECT__PATH_KEY = FLOW_OBJECT__PATH_KEY;

	/**
	 * The feature id for the '<em><b>File Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_FLOW_OBJECT__FILE_PATH = FLOW_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_FLOW_OBJECT__TITLE = FLOW_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_FLOW_OBJECT__ID = FLOW_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_FLOW_OBJECT__DESCRIPTION = FLOW_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Execution Flow Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_FLOW_OBJECT_FEATURE_COUNT = FLOW_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.csi.gmf.stateflow.impl.FlowObjectConnectionImpl <em>Flow Object Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.csi.gmf.stateflow.impl.FlowObjectConnectionImpl
	 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getFlowObjectConnection()
	 * @generated
	 */
	int FLOW_OBJECT_CONNECTION = 2;

	/**
	 * The feature id for the '<em><b>Source Path Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY = 0;

	/**
	 * The feature id for the '<em><b>Destination Path Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY = 1;

	/**
	 * The feature id for the '<em><b>Execution Flow Result Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE = 2;

	/**
	 * The feature id for the '<em><b>Source Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_OBJECT_CONNECTION__SOURCE_FLOW = 3;

	/**
	 * The feature id for the '<em><b>Destination State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_OBJECT_CONNECTION__DESTINATION_STATE = 4;

	/**
	 * The number of structural features of the '<em>Flow Object Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_OBJECT_CONNECTION_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.csi.gmf.stateflow.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.csi.gmf.stateflow.impl.StateImpl
	 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getState()
	 * @generated
	 */
	int STATE = 3;

	/**
	 * The feature id for the '<em><b>Path Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__PATH_KEY = FLOW_OBJECT__PATH_KEY;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__ID = FLOW_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__DESCRIPTION = FLOW_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = FLOW_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.csi.gmf.stateflow.impl.StateFlowImpl <em>State Flow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.csi.gmf.stateflow.impl.StateFlowImpl
	 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getStateFlow()
	 * @generated
	 */
	int STATE_FLOW = 4;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FLOW__STATES = 0;

	/**
	 * The feature id for the '<em><b>Execution Flows</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FLOW__EXECUTION_FLOWS = 1;

	/**
	 * The feature id for the '<em><b>State Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FLOW__STATE_CONNECTIONS = 2;

	/**
	 * The feature id for the '<em><b>Flow Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FLOW__FLOW_CONNECTIONS = 3;

	/**
	 * The feature id for the '<em><b>Start Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FLOW__START_POINT = 4;

	/**
	 * The feature id for the '<em><b>Start Point Connection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FLOW__START_POINT_CONNECTION = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FLOW__ID = 6;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FLOW__DESCRIPTION = 7;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FLOW__VOLATILE = 8;

	/**
	 * The number of structural features of the '<em>State Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FLOW_FEATURE_COUNT = 9;


	/**
	 * The meta object id for the '{@link com.csi.gmf.stateflow.impl.EventConnectionImpl <em>Event Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.csi.gmf.stateflow.impl.EventConnectionImpl
	 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getEventConnection()
	 * @generated
	 */
	int EVENT_CONNECTION = 5;

	/**
	 * The feature id for the '<em><b>Source Path Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONNECTION__SOURCE_PATH_KEY = 0;

	/**
	 * The feature id for the '<em><b>Destination Path Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONNECTION__DESTINATION_PATH_KEY = 1;

	/**
	 * The feature id for the '<em><b>Event ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONNECTION__EVENT_ID = 2;

	/**
	 * The feature id for the '<em><b>Source State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONNECTION__SOURCE_STATE = 3;

	/**
	 * The feature id for the '<em><b>Destination Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONNECTION__DESTINATION_FLOW = 4;

	/**
	 * The number of structural features of the '<em>Event Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONNECTION_FEATURE_COUNT = 5;


	/**
	 * The meta object id for the '{@link com.csi.gmf.stateflow.impl.StartPointImpl <em>Start Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.csi.gmf.stateflow.impl.StartPointImpl
	 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getStartPoint()
	 * @generated
	 */
	int START_POINT = 6;

	/**
	 * The number of structural features of the '<em>Start Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.csi.gmf.stateflow.impl.StartPointConnectionImpl <em>Start Point Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.csi.gmf.stateflow.impl.StartPointConnectionImpl
	 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getStartPointConnection()
	 * @generated
	 */
	int START_POINT_CONNECTION = 7;

	/**
	 * The feature id for the '<em><b>Start Point</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT_CONNECTION__START_POINT = 0;

	/**
	 * The feature id for the '<em><b>Destination State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT_CONNECTION__DESTINATION_STATE = 1;

	/**
	 * The feature id for the '<em><b>Destination State Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT_CONNECTION__DESTINATION_STATE_ID = 2;

	/**
	 * The number of structural features of the '<em>Start Point Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT_CONNECTION_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link com.csi.gmf.stateflow.ExecutionFlowObject <em>Execution Flow Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Flow Object</em>'.
	 * @see com.csi.gmf.stateflow.ExecutionFlowObject
	 * @generated
	 */
	EClass getExecutionFlowObject();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.ExecutionFlowObject#getFilePath <em>File Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Path</em>'.
	 * @see com.csi.gmf.stateflow.ExecutionFlowObject#getFilePath()
	 * @see #getExecutionFlowObject()
	 * @generated
	 */
	EAttribute getExecutionFlowObject_FilePath();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.ExecutionFlowObject#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.csi.gmf.stateflow.ExecutionFlowObject#getTitle()
	 * @see #getExecutionFlowObject()
	 * @generated
	 */
	EAttribute getExecutionFlowObject_Title();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.ExecutionFlowObject#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.csi.gmf.stateflow.ExecutionFlowObject#getId()
	 * @see #getExecutionFlowObject()
	 * @generated
	 */
	EAttribute getExecutionFlowObject_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.ExecutionFlowObject#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.csi.gmf.stateflow.ExecutionFlowObject#getDescription()
	 * @see #getExecutionFlowObject()
	 * @generated
	 */
	EAttribute getExecutionFlowObject_Description();

	/**
	 * Returns the meta object for class '{@link com.csi.gmf.stateflow.FlowObject <em>Flow Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Flow Object</em>'.
	 * @see com.csi.gmf.stateflow.FlowObject
	 * @generated
	 */
	EClass getFlowObject();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.FlowObject#getPathKey <em>Path Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path Key</em>'.
	 * @see com.csi.gmf.stateflow.FlowObject#getPathKey()
	 * @see #getFlowObject()
	 * @generated
	 */
	EAttribute getFlowObject_PathKey();

	/**
	 * Returns the meta object for class '{@link com.csi.gmf.stateflow.FlowObjectConnection <em>Flow Object Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Flow Object Connection</em>'.
	 * @see com.csi.gmf.stateflow.FlowObjectConnection
	 * @generated
	 */
	EClass getFlowObjectConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.FlowObjectConnection#getSourcePathKey <em>Source Path Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Path Key</em>'.
	 * @see com.csi.gmf.stateflow.FlowObjectConnection#getSourcePathKey()
	 * @see #getFlowObjectConnection()
	 * @generated
	 */
	EAttribute getFlowObjectConnection_SourcePathKey();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.FlowObjectConnection#getDestinationPathKey <em>Destination Path Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Destination Path Key</em>'.
	 * @see com.csi.gmf.stateflow.FlowObjectConnection#getDestinationPathKey()
	 * @see #getFlowObjectConnection()
	 * @generated
	 */
	EAttribute getFlowObjectConnection_DestinationPathKey();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.FlowObjectConnection#getExecutionFlowResultValue <em>Execution Flow Result Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Execution Flow Result Value</em>'.
	 * @see com.csi.gmf.stateflow.FlowObjectConnection#getExecutionFlowResultValue()
	 * @see #getFlowObjectConnection()
	 * @generated
	 */
	EAttribute getFlowObjectConnection_ExecutionFlowResultValue();

	/**
	 * Returns the meta object for the reference '{@link com.csi.gmf.stateflow.FlowObjectConnection#getSourceFlow <em>Source Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Flow</em>'.
	 * @see com.csi.gmf.stateflow.FlowObjectConnection#getSourceFlow()
	 * @see #getFlowObjectConnection()
	 * @generated
	 */
	EReference getFlowObjectConnection_SourceFlow();

	/**
	 * Returns the meta object for the reference '{@link com.csi.gmf.stateflow.FlowObjectConnection#getDestinationState <em>Destination State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destination State</em>'.
	 * @see com.csi.gmf.stateflow.FlowObjectConnection#getDestinationState()
	 * @see #getFlowObjectConnection()
	 * @generated
	 */
	EReference getFlowObjectConnection_DestinationState();

	/**
	 * Returns the meta object for class '{@link com.csi.gmf.stateflow.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see com.csi.gmf.stateflow.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.State#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.csi.gmf.stateflow.State#getId()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.State#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.csi.gmf.stateflow.State#getDescription()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Description();

	/**
	 * Returns the meta object for class '{@link com.csi.gmf.stateflow.StateFlow <em>State Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Flow</em>'.
	 * @see com.csi.gmf.stateflow.StateFlow
	 * @generated
	 */
	EClass getStateFlow();

	/**
	 * Returns the meta object for the containment reference list '{@link com.csi.gmf.stateflow.StateFlow#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see com.csi.gmf.stateflow.StateFlow#getStates()
	 * @see #getStateFlow()
	 * @generated
	 */
	EReference getStateFlow_States();

	/**
	 * Returns the meta object for the containment reference list '{@link com.csi.gmf.stateflow.StateFlow#getExecutionFlows <em>Execution Flows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Execution Flows</em>'.
	 * @see com.csi.gmf.stateflow.StateFlow#getExecutionFlows()
	 * @see #getStateFlow()
	 * @generated
	 */
	EReference getStateFlow_ExecutionFlows();

	/**
	 * Returns the meta object for the containment reference list '{@link com.csi.gmf.stateflow.StateFlow#getStateConnections <em>State Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>State Connections</em>'.
	 * @see com.csi.gmf.stateflow.StateFlow#getStateConnections()
	 * @see #getStateFlow()
	 * @generated
	 */
	EReference getStateFlow_StateConnections();

	/**
	 * Returns the meta object for the containment reference list '{@link com.csi.gmf.stateflow.StateFlow#getFlowConnections <em>Flow Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Flow Connections</em>'.
	 * @see com.csi.gmf.stateflow.StateFlow#getFlowConnections()
	 * @see #getStateFlow()
	 * @generated
	 */
	EReference getStateFlow_FlowConnections();

	/**
	 * Returns the meta object for the containment reference '{@link com.csi.gmf.stateflow.StateFlow#getStartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Start Point</em>'.
	 * @see com.csi.gmf.stateflow.StateFlow#getStartPoint()
	 * @see #getStateFlow()
	 * @generated
	 */
	EReference getStateFlow_StartPoint();

	/**
	 * Returns the meta object for the containment reference '{@link com.csi.gmf.stateflow.StateFlow#getStartPointConnection <em>Start Point Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Start Point Connection</em>'.
	 * @see com.csi.gmf.stateflow.StateFlow#getStartPointConnection()
	 * @see #getStateFlow()
	 * @generated
	 */
	EReference getStateFlow_StartPointConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.StateFlow#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.csi.gmf.stateflow.StateFlow#getId()
	 * @see #getStateFlow()
	 * @generated
	 */
	EAttribute getStateFlow_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.StateFlow#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.csi.gmf.stateflow.StateFlow#getDescription()
	 * @see #getStateFlow()
	 * @generated
	 */
	EAttribute getStateFlow_Description();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.StateFlow#isVolatile <em>Volatile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Volatile</em>'.
	 * @see com.csi.gmf.stateflow.StateFlow#isVolatile()
	 * @see #getStateFlow()
	 * @generated
	 */
	EAttribute getStateFlow_Volatile();

	/**
	 * Returns the meta object for class '{@link com.csi.gmf.stateflow.EventConnection <em>Event Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Connection</em>'.
	 * @see com.csi.gmf.stateflow.EventConnection
	 * @generated
	 */
	EClass getEventConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.EventConnection#getSourcePathKey <em>Source Path Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Path Key</em>'.
	 * @see com.csi.gmf.stateflow.EventConnection#getSourcePathKey()
	 * @see #getEventConnection()
	 * @generated
	 */
	EAttribute getEventConnection_SourcePathKey();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.EventConnection#getDestinationPathKey <em>Destination Path Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Destination Path Key</em>'.
	 * @see com.csi.gmf.stateflow.EventConnection#getDestinationPathKey()
	 * @see #getEventConnection()
	 * @generated
	 */
	EAttribute getEventConnection_DestinationPathKey();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.EventConnection#getEventID <em>Event ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Event ID</em>'.
	 * @see com.csi.gmf.stateflow.EventConnection#getEventID()
	 * @see #getEventConnection()
	 * @generated
	 */
	EAttribute getEventConnection_EventID();

	/**
	 * Returns the meta object for the reference '{@link com.csi.gmf.stateflow.EventConnection#getSourceState <em>Source State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source State</em>'.
	 * @see com.csi.gmf.stateflow.EventConnection#getSourceState()
	 * @see #getEventConnection()
	 * @generated
	 */
	EReference getEventConnection_SourceState();

	/**
	 * Returns the meta object for the reference '{@link com.csi.gmf.stateflow.EventConnection#getDestinationFlow <em>Destination Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destination Flow</em>'.
	 * @see com.csi.gmf.stateflow.EventConnection#getDestinationFlow()
	 * @see #getEventConnection()
	 * @generated
	 */
	EReference getEventConnection_DestinationFlow();

	/**
	 * Returns the meta object for class '{@link com.csi.gmf.stateflow.StartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start Point</em>'.
	 * @see com.csi.gmf.stateflow.StartPoint
	 * @generated
	 */
	EClass getStartPoint();

	/**
	 * Returns the meta object for class '{@link com.csi.gmf.stateflow.StartPointConnection <em>Start Point Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start Point Connection</em>'.
	 * @see com.csi.gmf.stateflow.StartPointConnection
	 * @generated
	 */
	EClass getStartPointConnection();

	/**
	 * Returns the meta object for the reference '{@link com.csi.gmf.stateflow.StartPointConnection#getStartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Start Point</em>'.
	 * @see com.csi.gmf.stateflow.StartPointConnection#getStartPoint()
	 * @see #getStartPointConnection()
	 * @generated
	 */
	EReference getStartPointConnection_StartPoint();

	/**
	 * Returns the meta object for the reference '{@link com.csi.gmf.stateflow.StartPointConnection#getDestinationState <em>Destination State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destination State</em>'.
	 * @see com.csi.gmf.stateflow.StartPointConnection#getDestinationState()
	 * @see #getStartPointConnection()
	 * @generated
	 */
	EReference getStartPointConnection_DestinationState();

	/**
	 * Returns the meta object for the attribute '{@link com.csi.gmf.stateflow.StartPointConnection#getDestinationStateId <em>Destination State Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Destination State Id</em>'.
	 * @see com.csi.gmf.stateflow.StartPointConnection#getDestinationStateId()
	 * @see #getStartPointConnection()
	 * @generated
	 */
	EAttribute getStartPointConnection_DestinationStateId();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StateflowFactory getStateflowFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals  {
		/**
		 * The meta object literal for the '{@link com.csi.gmf.stateflow.impl.ExecutionFlowObjectImpl <em>Execution Flow Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.csi.gmf.stateflow.impl.ExecutionFlowObjectImpl
		 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getExecutionFlowObject()
		 * @generated
		 */
		EClass EXECUTION_FLOW_OBJECT = eINSTANCE.getExecutionFlowObject();

		/**
		 * The meta object literal for the '<em><b>File Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_FLOW_OBJECT__FILE_PATH = eINSTANCE.getExecutionFlowObject_FilePath();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_FLOW_OBJECT__TITLE = eINSTANCE.getExecutionFlowObject_Title();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_FLOW_OBJECT__ID = eINSTANCE.getExecutionFlowObject_Id();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_FLOW_OBJECT__DESCRIPTION = eINSTANCE.getExecutionFlowObject_Description();

		/**
		 * The meta object literal for the '{@link com.csi.gmf.stateflow.impl.FlowObjectImpl <em>Flow Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.csi.gmf.stateflow.impl.FlowObjectImpl
		 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getFlowObject()
		 * @generated
		 */
		EClass FLOW_OBJECT = eINSTANCE.getFlowObject();

		/**
		 * The meta object literal for the '<em><b>Path Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW_OBJECT__PATH_KEY = eINSTANCE.getFlowObject_PathKey();

		/**
		 * The meta object literal for the '{@link com.csi.gmf.stateflow.impl.FlowObjectConnectionImpl <em>Flow Object Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.csi.gmf.stateflow.impl.FlowObjectConnectionImpl
		 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getFlowObjectConnection()
		 * @generated
		 */
		EClass FLOW_OBJECT_CONNECTION = eINSTANCE.getFlowObjectConnection();

		/**
		 * The meta object literal for the '<em><b>Source Path Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY = eINSTANCE.getFlowObjectConnection_SourcePathKey();

		/**
		 * The meta object literal for the '<em><b>Destination Path Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY = eINSTANCE.getFlowObjectConnection_DestinationPathKey();

		/**
		 * The meta object literal for the '<em><b>Execution Flow Result Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE = eINSTANCE.getFlowObjectConnection_ExecutionFlowResultValue();

		/**
		 * The meta object literal for the '<em><b>Source Flow</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_OBJECT_CONNECTION__SOURCE_FLOW = eINSTANCE.getFlowObjectConnection_SourceFlow();

		/**
		 * The meta object literal for the '<em><b>Destination State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_OBJECT_CONNECTION__DESTINATION_STATE = eINSTANCE.getFlowObjectConnection_DestinationState();

		/**
		 * The meta object literal for the '{@link com.csi.gmf.stateflow.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.csi.gmf.stateflow.impl.StateImpl
		 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__ID = eINSTANCE.getState_Id();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__DESCRIPTION = eINSTANCE.getState_Description();

		/**
		 * The meta object literal for the '{@link com.csi.gmf.stateflow.impl.StateFlowImpl <em>State Flow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.csi.gmf.stateflow.impl.StateFlowImpl
		 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getStateFlow()
		 * @generated
		 */
		EClass STATE_FLOW = eINSTANCE.getStateFlow();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_FLOW__STATES = eINSTANCE.getStateFlow_States();

		/**
		 * The meta object literal for the '<em><b>Execution Flows</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_FLOW__EXECUTION_FLOWS = eINSTANCE.getStateFlow_ExecutionFlows();

		/**
		 * The meta object literal for the '<em><b>State Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_FLOW__STATE_CONNECTIONS = eINSTANCE.getStateFlow_StateConnections();

		/**
		 * The meta object literal for the '<em><b>Flow Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_FLOW__FLOW_CONNECTIONS = eINSTANCE.getStateFlow_FlowConnections();

		/**
		 * The meta object literal for the '<em><b>Start Point</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_FLOW__START_POINT = eINSTANCE.getStateFlow_StartPoint();

		/**
		 * The meta object literal for the '<em><b>Start Point Connection</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_FLOW__START_POINT_CONNECTION = eINSTANCE.getStateFlow_StartPointConnection();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_FLOW__ID = eINSTANCE.getStateFlow_Id();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_FLOW__DESCRIPTION = eINSTANCE.getStateFlow_Description();

		/**
		 * The meta object literal for the '<em><b>Volatile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_FLOW__VOLATILE = eINSTANCE.getStateFlow_Volatile();

		/**
		 * The meta object literal for the '{@link com.csi.gmf.stateflow.impl.EventConnectionImpl <em>Event Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.csi.gmf.stateflow.impl.EventConnectionImpl
		 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getEventConnection()
		 * @generated
		 */
		EClass EVENT_CONNECTION = eINSTANCE.getEventConnection();

		/**
		 * The meta object literal for the '<em><b>Source Path Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_CONNECTION__SOURCE_PATH_KEY = eINSTANCE.getEventConnection_SourcePathKey();

		/**
		 * The meta object literal for the '<em><b>Destination Path Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_CONNECTION__DESTINATION_PATH_KEY = eINSTANCE.getEventConnection_DestinationPathKey();

		/**
		 * The meta object literal for the '<em><b>Event ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_CONNECTION__EVENT_ID = eINSTANCE.getEventConnection_EventID();

		/**
		 * The meta object literal for the '<em><b>Source State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_CONNECTION__SOURCE_STATE = eINSTANCE.getEventConnection_SourceState();

		/**
		 * The meta object literal for the '<em><b>Destination Flow</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_CONNECTION__DESTINATION_FLOW = eINSTANCE.getEventConnection_DestinationFlow();

		/**
		 * The meta object literal for the '{@link com.csi.gmf.stateflow.impl.StartPointImpl <em>Start Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.csi.gmf.stateflow.impl.StartPointImpl
		 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getStartPoint()
		 * @generated
		 */
		EClass START_POINT = eINSTANCE.getStartPoint();

		/**
		 * The meta object literal for the '{@link com.csi.gmf.stateflow.impl.StartPointConnectionImpl <em>Start Point Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.csi.gmf.stateflow.impl.StartPointConnectionImpl
		 * @see com.csi.gmf.stateflow.impl.StateflowPackageImpl#getStartPointConnection()
		 * @generated
		 */
		EClass START_POINT_CONNECTION = eINSTANCE.getStartPointConnection();

		/**
		 * The meta object literal for the '<em><b>Start Point</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_POINT_CONNECTION__START_POINT = eINSTANCE.getStartPointConnection_StartPoint();

		/**
		 * The meta object literal for the '<em><b>Destination State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_POINT_CONNECTION__DESTINATION_STATE = eINSTANCE.getStartPointConnection_DestinationState();

		/**
		 * The meta object literal for the '<em><b>Destination State Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_POINT_CONNECTION__DESTINATION_STATE_ID = eINSTANCE.getStartPointConnection_DestinationStateId();

	}

} //StateflowPackage
