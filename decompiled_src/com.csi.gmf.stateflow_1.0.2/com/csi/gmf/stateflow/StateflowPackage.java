/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EAttribute
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EPackage
 *  org.eclipse.emf.ecore.EReference
 */
package com.csi.gmf.stateflow;

import com.csi.gmf.stateflow.StateflowFactory;
import com.csi.gmf.stateflow.impl.StateflowPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

public interface StateflowPackage
extends EPackage {
    public static final String eNAME = "stateflow";
    public static final String eNS_URI = "http:///com/csi/gmf/stateflow.ecore";
    public static final String eNS_PREFIX = "com.csi.gmf.stateflow";
    public static final StateflowPackage eINSTANCE = StateflowPackageImpl.init();
    public static final int FLOW_OBJECT = 1;
    public static final int FLOW_OBJECT__PATH_KEY = 0;
    public static final int FLOW_OBJECT_FEATURE_COUNT = 1;
    public static final int EXECUTION_FLOW_OBJECT = 0;
    public static final int EXECUTION_FLOW_OBJECT__PATH_KEY = 0;
    public static final int EXECUTION_FLOW_OBJECT__FILE_PATH = 1;
    public static final int EXECUTION_FLOW_OBJECT__TITLE = 2;
    public static final int EXECUTION_FLOW_OBJECT__ID = 3;
    public static final int EXECUTION_FLOW_OBJECT__DESCRIPTION = 4;
    public static final int EXECUTION_FLOW_OBJECT_FEATURE_COUNT = 5;
    public static final int FLOW_OBJECT_CONNECTION = 2;
    public static final int FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY = 0;
    public static final int FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY = 1;
    public static final int FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE = 2;
    public static final int FLOW_OBJECT_CONNECTION__SOURCE_FLOW = 3;
    public static final int FLOW_OBJECT_CONNECTION__DESTINATION_STATE = 4;
    public static final int FLOW_OBJECT_CONNECTION_FEATURE_COUNT = 5;
    public static final int STATE = 3;
    public static final int STATE__PATH_KEY = 0;
    public static final int STATE__ID = 1;
    public static final int STATE__DESCRIPTION = 2;
    public static final int STATE_FEATURE_COUNT = 3;
    public static final int STATE_FLOW = 4;
    public static final int STATE_FLOW__STATES = 0;
    public static final int STATE_FLOW__EXECUTION_FLOWS = 1;
    public static final int STATE_FLOW__STATE_CONNECTIONS = 2;
    public static final int STATE_FLOW__FLOW_CONNECTIONS = 3;
    public static final int STATE_FLOW__START_POINT = 4;
    public static final int STATE_FLOW__START_POINT_CONNECTION = 5;
    public static final int STATE_FLOW__ID = 6;
    public static final int STATE_FLOW__DESCRIPTION = 7;
    public static final int STATE_FLOW__VOLATILE = 8;
    public static final int STATE_FLOW_FEATURE_COUNT = 9;
    public static final int EVENT_CONNECTION = 5;
    public static final int EVENT_CONNECTION__SOURCE_PATH_KEY = 0;
    public static final int EVENT_CONNECTION__DESTINATION_PATH_KEY = 1;
    public static final int EVENT_CONNECTION__EVENT_ID = 2;
    public static final int EVENT_CONNECTION__SOURCE_STATE = 3;
    public static final int EVENT_CONNECTION__DESTINATION_FLOW = 4;
    public static final int EVENT_CONNECTION_FEATURE_COUNT = 5;
    public static final int START_POINT = 6;
    public static final int START_POINT_FEATURE_COUNT = 0;
    public static final int START_POINT_CONNECTION = 7;
    public static final int START_POINT_CONNECTION__START_POINT = 0;
    public static final int START_POINT_CONNECTION__DESTINATION_STATE = 1;
    public static final int START_POINT_CONNECTION__DESTINATION_STATE_ID = 2;
    public static final int START_POINT_CONNECTION_FEATURE_COUNT = 3;

    public EClass getExecutionFlowObject();

    public EAttribute getExecutionFlowObject_FilePath();

    public EAttribute getExecutionFlowObject_Title();

    public EAttribute getExecutionFlowObject_Id();

    public EAttribute getExecutionFlowObject_Description();

    public EClass getFlowObject();

    public EAttribute getFlowObject_PathKey();

    public EClass getFlowObjectConnection();

    public EAttribute getFlowObjectConnection_SourcePathKey();

    public EAttribute getFlowObjectConnection_DestinationPathKey();

    public EAttribute getFlowObjectConnection_ExecutionFlowResultValue();

    public EReference getFlowObjectConnection_SourceFlow();

    public EReference getFlowObjectConnection_DestinationState();

    public EClass getState();

    public EAttribute getState_Id();

    public EAttribute getState_Description();

    public EClass getStateFlow();

    public EReference getStateFlow_States();

    public EReference getStateFlow_ExecutionFlows();

    public EReference getStateFlow_StateConnections();

    public EReference getStateFlow_FlowConnections();

    public EReference getStateFlow_StartPoint();

    public EReference getStateFlow_StartPointConnection();

    public EAttribute getStateFlow_Id();

    public EAttribute getStateFlow_Description();

    public EAttribute getStateFlow_Volatile();

    public EClass getEventConnection();

    public EAttribute getEventConnection_SourcePathKey();

    public EAttribute getEventConnection_DestinationPathKey();

    public EAttribute getEventConnection_EventID();

    public EReference getEventConnection_SourceState();

    public EReference getEventConnection_DestinationFlow();

    public EClass getStartPoint();

    public EClass getStartPointConnection();

    public EReference getStartPointConnection_StartPoint();

    public EReference getStartPointConnection_DestinationState();

    public EAttribute getStartPointConnection_DestinationStateId();

    public StateflowFactory getStateflowFactory();

    public static interface Literals {
        public static final EClass EXECUTION_FLOW_OBJECT = eINSTANCE.getExecutionFlowObject();
        public static final EAttribute EXECUTION_FLOW_OBJECT__FILE_PATH = eINSTANCE.getExecutionFlowObject_FilePath();
        public static final EAttribute EXECUTION_FLOW_OBJECT__TITLE = eINSTANCE.getExecutionFlowObject_Title();
        public static final EAttribute EXECUTION_FLOW_OBJECT__ID = eINSTANCE.getExecutionFlowObject_Id();
        public static final EAttribute EXECUTION_FLOW_OBJECT__DESCRIPTION = eINSTANCE.getExecutionFlowObject_Description();
        public static final EClass FLOW_OBJECT = eINSTANCE.getFlowObject();
        public static final EAttribute FLOW_OBJECT__PATH_KEY = eINSTANCE.getFlowObject_PathKey();
        public static final EClass FLOW_OBJECT_CONNECTION = eINSTANCE.getFlowObjectConnection();
        public static final EAttribute FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY = eINSTANCE.getFlowObjectConnection_SourcePathKey();
        public static final EAttribute FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY = eINSTANCE.getFlowObjectConnection_DestinationPathKey();
        public static final EAttribute FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE = eINSTANCE.getFlowObjectConnection_ExecutionFlowResultValue();
        public static final EReference FLOW_OBJECT_CONNECTION__SOURCE_FLOW = eINSTANCE.getFlowObjectConnection_SourceFlow();
        public static final EReference FLOW_OBJECT_CONNECTION__DESTINATION_STATE = eINSTANCE.getFlowObjectConnection_DestinationState();
        public static final EClass STATE = eINSTANCE.getState();
        public static final EAttribute STATE__ID = eINSTANCE.getState_Id();
        public static final EAttribute STATE__DESCRIPTION = eINSTANCE.getState_Description();
        public static final EClass STATE_FLOW = eINSTANCE.getStateFlow();
        public static final EReference STATE_FLOW__STATES = eINSTANCE.getStateFlow_States();
        public static final EReference STATE_FLOW__EXECUTION_FLOWS = eINSTANCE.getStateFlow_ExecutionFlows();
        public static final EReference STATE_FLOW__STATE_CONNECTIONS = eINSTANCE.getStateFlow_StateConnections();
        public static final EReference STATE_FLOW__FLOW_CONNECTIONS = eINSTANCE.getStateFlow_FlowConnections();
        public static final EReference STATE_FLOW__START_POINT = eINSTANCE.getStateFlow_StartPoint();
        public static final EReference STATE_FLOW__START_POINT_CONNECTION = eINSTANCE.getStateFlow_StartPointConnection();
        public static final EAttribute STATE_FLOW__ID = eINSTANCE.getStateFlow_Id();
        public static final EAttribute STATE_FLOW__DESCRIPTION = eINSTANCE.getStateFlow_Description();
        public static final EAttribute STATE_FLOW__VOLATILE = eINSTANCE.getStateFlow_Volatile();
        public static final EClass EVENT_CONNECTION = eINSTANCE.getEventConnection();
        public static final EAttribute EVENT_CONNECTION__SOURCE_PATH_KEY = eINSTANCE.getEventConnection_SourcePathKey();
        public static final EAttribute EVENT_CONNECTION__DESTINATION_PATH_KEY = eINSTANCE.getEventConnection_DestinationPathKey();
        public static final EAttribute EVENT_CONNECTION__EVENT_ID = eINSTANCE.getEventConnection_EventID();
        public static final EReference EVENT_CONNECTION__SOURCE_STATE = eINSTANCE.getEventConnection_SourceState();
        public static final EReference EVENT_CONNECTION__DESTINATION_FLOW = eINSTANCE.getEventConnection_DestinationFlow();
        public static final EClass START_POINT = eINSTANCE.getStartPoint();
        public static final EClass START_POINT_CONNECTION = eINSTANCE.getStartPointConnection();
        public static final EReference START_POINT_CONNECTION__START_POINT = eINSTANCE.getStartPointConnection_StartPoint();
        public static final EReference START_POINT_CONNECTION__DESTINATION_STATE = eINSTANCE.getStartPointConnection_DestinationState();
        public static final EAttribute START_POINT_CONNECTION__DESTINATION_STATE_ID = eINSTANCE.getStartPointConnection_DestinationStateId();
    }
}

