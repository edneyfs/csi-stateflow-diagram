/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EAttribute
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EDataType
 *  org.eclipse.emf.ecore.EPackage
 *  org.eclipse.emf.ecore.EReference
 */
package com.csi.gmf.executionflow;

import com.csi.gmf.executionflow.ExecutionflowFactory;
import com.csi.gmf.executionflow.impl.ExecutionflowPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

public interface ExecutionflowPackage
extends EPackage {
    public static final String eNAME = "executionflow";
    public static final String eNS_URI = "http:///com/csi/gmf/executionflow.ecore";
    public static final String eNS_PREFIX = "com.csi.gmf.executionflow";
    public static final ExecutionflowPackage eINSTANCE = ExecutionflowPackageImpl.init();
    public static final int FLOW_OBJECT = 4;
    public static final int FLOW_OBJECT__PATH_KEY = 0;
    public static final int FLOW_OBJECT_FEATURE_COUNT = 1;
    public static final int SOURCE_CONNECTABLE = 6;
    public static final int SOURCE_CONNECTABLE__PATH_KEY = 0;
    public static final int SOURCE_CONNECTABLE_FEATURE_COUNT = 1;
    public static final int EXECUTABLE_FLOW_OBJECT = 0;
    public static final int EXECUTABLE_FLOW_OBJECT__PATH_KEY = 0;
    public static final int EXECUTABLE_FLOW_OBJECT__CLASS_NAME = 1;
    public static final int EXECUTABLE_FLOW_OBJECT__SIMPLE_CLASS_NAME = 2;
    public static final int EXECUTABLE_FLOW_OBJECT_FEATURE_COUNT = 3;
    public static final int EXECUTION_FLOW = 1;
    public static final int EXECUTION_FLOW__TITLE = 0;
    public static final int EXECUTION_FLOW__EXECUTABLE_FLOW_OBJECTS = 1;
    public static final int EXECUTION_FLOW__RESULTS = 2;
    public static final int EXECUTION_FLOW__FLOW_CONNECTIONS = 3;
    public static final int EXECUTION_FLOW__EXECUTION_FLOW_OBJECTS = 4;
    public static final int EXECUTION_FLOW__START_POINT = 5;
    public static final int EXECUTION_FLOW__START_POINT_CONNECTION = 6;
    public static final int EXECUTION_FLOW__ID = 7;
    public static final int EXECUTION_FLOW__DESCRIPTION = 8;
    public static final int EXECUTION_FLOW__REQUEST_AUTHORIZATION = 9;
    public static final int EXECUTION_FLOW_FEATURE_COUNT = 10;
    public static final int EXECUTION_FLOW_OBJECT = 2;
    public static final int EXECUTION_FLOW_OBJECT__PATH_KEY = 0;
    public static final int EXECUTION_FLOW_OBJECT__FILE_NAME = 1;
    public static final int EXECUTION_FLOW_OBJECT__FILE_PATH = 2;
    public static final int EXECUTION_FLOW_OBJECT__ID = 3;
    public static final int EXECUTION_FLOW_OBJECT__DESCRIPTION = 4;
    public static final int EXECUTION_FLOW_OBJECT_FEATURE_COUNT = 5;
    public static final int EXECUTION_FLOW_RESULT = 3;
    public static final int EXECUTION_FLOW_RESULT__PATH_KEY = 0;
    public static final int EXECUTION_FLOW_RESULT__EXECUTION_FLOW_RESULT_VALUE = 1;
    public static final int EXECUTION_FLOW_RESULT_FEATURE_COUNT = 2;
    public static final int FLOW_OBJECT_CONNECTION = 5;
    public static final int FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY = 0;
    public static final int FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY = 1;
    public static final int FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE = 2;
    public static final int FLOW_OBJECT_CONNECTION__SOURCE_OBJECT = 3;
    public static final int FLOW_OBJECT_CONNECTION__DESTINATION_OBJECT = 4;
    public static final int FLOW_OBJECT_CONNECTION_FEATURE_COUNT = 5;
    public static final int START_POINT = 7;
    public static final int START_POINT_FEATURE_COUNT = 0;
    public static final int START_POINT_CONNECTION = 8;
    public static final int START_POINT_CONNECTION__START_POINT = 0;
    public static final int START_POINT_CONNECTION__DESTINATION_OBJECT = 1;
    public static final int START_POINT_CONNECTION__DESTINATION_OBJECT_PATH_KEY = 2;
    public static final int START_POINT_CONNECTION_FEATURE_COUNT = 3;
    public static final int ENUMERATION = 9;

    public EClass getExecutableFlowObject();

    public EAttribute getExecutableFlowObject_ClassName();

    public EAttribute getExecutableFlowObject_SimpleClassName();

    public EClass getExecutionFlow();

    public EAttribute getExecutionFlow_Title();

    public EReference getExecutionFlow_ExecutableFlowObjects();

    public EReference getExecutionFlow_Results();

    public EReference getExecutionFlow_FlowConnections();

    public EReference getExecutionFlow_ExecutionFlowObjects();

    public EReference getExecutionFlow_StartPoint();

    public EReference getExecutionFlow_StartPointConnection();

    public EAttribute getExecutionFlow_Id();

    public EAttribute getExecutionFlow_Description();

    public EAttribute getExecutionFlow_RequestAuthorization();

    public EClass getExecutionFlowObject();

    public EAttribute getExecutionFlowObject_FileName();

    public EAttribute getExecutionFlowObject_FilePath();

    public EAttribute getExecutionFlowObject_Id();

    public EAttribute getExecutionFlowObject_Description();

    public EClass getExecutionFlowResult();

    public EAttribute getExecutionFlowResult_ExecutionFlowResultValue();

    public EClass getFlowObject();

    public EAttribute getFlowObject_PathKey();

    public EClass getFlowObjectConnection();

    public EAttribute getFlowObjectConnection_SourcePathKey();

    public EAttribute getFlowObjectConnection_DestinationPathKey();

    public EAttribute getFlowObjectConnection_ExecutionFlowResultValue();

    public EReference getFlowObjectConnection_SourceObject();

    public EReference getFlowObjectConnection_DestinationObject();

    public EClass getSourceConnectable();

    public EClass getStartPoint();

    public EClass getStartPointConnection();

    public EReference getStartPointConnection_StartPoint();

    public EReference getStartPointConnection_DestinationObject();

    public EAttribute getStartPointConnection_DestinationObjectPathKey();

    public EDataType getEnumeration();

    public ExecutionflowFactory getExecutionflowFactory();

    public static interface Literals {
        public static final EClass EXECUTABLE_FLOW_OBJECT = eINSTANCE.getExecutableFlowObject();
        public static final EAttribute EXECUTABLE_FLOW_OBJECT__CLASS_NAME = eINSTANCE.getExecutableFlowObject_ClassName();
        public static final EAttribute EXECUTABLE_FLOW_OBJECT__SIMPLE_CLASS_NAME = eINSTANCE.getExecutableFlowObject_SimpleClassName();
        public static final EClass EXECUTION_FLOW = eINSTANCE.getExecutionFlow();
        public static final EAttribute EXECUTION_FLOW__TITLE = eINSTANCE.getExecutionFlow_Title();
        public static final EReference EXECUTION_FLOW__EXECUTABLE_FLOW_OBJECTS = eINSTANCE.getExecutionFlow_ExecutableFlowObjects();
        public static final EReference EXECUTION_FLOW__RESULTS = eINSTANCE.getExecutionFlow_Results();
        public static final EReference EXECUTION_FLOW__FLOW_CONNECTIONS = eINSTANCE.getExecutionFlow_FlowConnections();
        public static final EReference EXECUTION_FLOW__EXECUTION_FLOW_OBJECTS = eINSTANCE.getExecutionFlow_ExecutionFlowObjects();
        public static final EReference EXECUTION_FLOW__START_POINT = eINSTANCE.getExecutionFlow_StartPoint();
        public static final EReference EXECUTION_FLOW__START_POINT_CONNECTION = eINSTANCE.getExecutionFlow_StartPointConnection();
        public static final EAttribute EXECUTION_FLOW__ID = eINSTANCE.getExecutionFlow_Id();
        public static final EAttribute EXECUTION_FLOW__DESCRIPTION = eINSTANCE.getExecutionFlow_Description();
        public static final EAttribute EXECUTION_FLOW__REQUEST_AUTHORIZATION = eINSTANCE.getExecutionFlow_RequestAuthorization();
        public static final EClass EXECUTION_FLOW_OBJECT = eINSTANCE.getExecutionFlowObject();
        public static final EAttribute EXECUTION_FLOW_OBJECT__FILE_NAME = eINSTANCE.getExecutionFlowObject_FileName();
        public static final EAttribute EXECUTION_FLOW_OBJECT__FILE_PATH = eINSTANCE.getExecutionFlowObject_FilePath();
        public static final EAttribute EXECUTION_FLOW_OBJECT__ID = eINSTANCE.getExecutionFlowObject_Id();
        public static final EAttribute EXECUTION_FLOW_OBJECT__DESCRIPTION = eINSTANCE.getExecutionFlowObject_Description();
        public static final EClass EXECUTION_FLOW_RESULT = eINSTANCE.getExecutionFlowResult();
        public static final EAttribute EXECUTION_FLOW_RESULT__EXECUTION_FLOW_RESULT_VALUE = eINSTANCE.getExecutionFlowResult_ExecutionFlowResultValue();
        public static final EClass FLOW_OBJECT = eINSTANCE.getFlowObject();
        public static final EAttribute FLOW_OBJECT__PATH_KEY = eINSTANCE.getFlowObject_PathKey();
        public static final EClass FLOW_OBJECT_CONNECTION = eINSTANCE.getFlowObjectConnection();
        public static final EAttribute FLOW_OBJECT_CONNECTION__SOURCE_PATH_KEY = eINSTANCE.getFlowObjectConnection_SourcePathKey();
        public static final EAttribute FLOW_OBJECT_CONNECTION__DESTINATION_PATH_KEY = eINSTANCE.getFlowObjectConnection_DestinationPathKey();
        public static final EAttribute FLOW_OBJECT_CONNECTION__EXECUTION_FLOW_RESULT_VALUE = eINSTANCE.getFlowObjectConnection_ExecutionFlowResultValue();
        public static final EReference FLOW_OBJECT_CONNECTION__SOURCE_OBJECT = eINSTANCE.getFlowObjectConnection_SourceObject();
        public static final EReference FLOW_OBJECT_CONNECTION__DESTINATION_OBJECT = eINSTANCE.getFlowObjectConnection_DestinationObject();
        public static final EClass SOURCE_CONNECTABLE = eINSTANCE.getSourceConnectable();
        public static final EClass START_POINT = eINSTANCE.getStartPoint();
        public static final EClass START_POINT_CONNECTION = eINSTANCE.getStartPointConnection();
        public static final EReference START_POINT_CONNECTION__START_POINT = eINSTANCE.getStartPointConnection_StartPoint();
        public static final EReference START_POINT_CONNECTION__DESTINATION_OBJECT = eINSTANCE.getStartPointConnection_DestinationObject();
        public static final EAttribute START_POINT_CONNECTION__DESTINATION_OBJECT_PATH_KEY = eINSTANCE.getStartPointConnection_DestinationObjectPathKey();
        public static final EDataType ENUMERATION = eINSTANCE.getEnumeration();
    }
}

