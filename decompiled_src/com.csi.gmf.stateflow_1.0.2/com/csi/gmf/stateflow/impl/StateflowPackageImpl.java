/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EAttribute
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EClassifier
 *  org.eclipse.emf.ecore.EFactory
 *  org.eclipse.emf.ecore.EPackage$Registry
 *  org.eclipse.emf.ecore.EReference
 *  org.eclipse.emf.ecore.impl.EPackageImpl
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
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

public class StateflowPackageImpl
extends EPackageImpl
implements StateflowPackage {
    private EClass executionFlowObjectEClass = null;
    private EClass flowObjectEClass = null;
    private EClass flowObjectConnectionEClass = null;
    private EClass stateEClass = null;
    private EClass stateFlowEClass = null;
    private EClass eventConnectionEClass = null;
    private EClass startPointEClass = null;
    private EClass startPointConnectionEClass = null;
    private static boolean isInited = false;
    private boolean isCreated = false;
    private boolean isInitialized = false;

    private StateflowPackageImpl() {
        super("http:///com/csi/gmf/stateflow.ecore", (EFactory)StateflowFactory.eINSTANCE);
    }

    public static StateflowPackage init() {
        if (isInited) {
            return (StateflowPackage)EPackage.Registry.INSTANCE.getEPackage("http:///com/csi/gmf/stateflow.ecore");
        }
        StateflowPackageImpl theStateflowPackage = EPackage.Registry.INSTANCE.getEPackage("http:///com/csi/gmf/stateflow.ecore") instanceof StateflowPackageImpl ? EPackage.Registry.INSTANCE.getEPackage("http:///com/csi/gmf/stateflow.ecore") : new StateflowPackageImpl();
        isInited = true;
        theStateflowPackage.createPackageContents();
        theStateflowPackage.initializePackageContents();
        theStateflowPackage.freeze();
        return theStateflowPackage;
    }

    @Override
    public EClass getExecutionFlowObject() {
        return this.executionFlowObjectEClass;
    }

    @Override
    public EAttribute getExecutionFlowObject_FilePath() {
        return (EAttribute)this.executionFlowObjectEClass.getEStructuralFeatures().get(0);
    }

    @Override
    public EAttribute getExecutionFlowObject_Title() {
        return (EAttribute)this.executionFlowObjectEClass.getEStructuralFeatures().get(1);
    }

    @Override
    public EAttribute getExecutionFlowObject_Id() {
        return (EAttribute)this.executionFlowObjectEClass.getEStructuralFeatures().get(2);
    }

    @Override
    public EAttribute getExecutionFlowObject_Description() {
        return (EAttribute)this.executionFlowObjectEClass.getEStructuralFeatures().get(3);
    }

    @Override
    public EClass getFlowObject() {
        return this.flowObjectEClass;
    }

    @Override
    public EAttribute getFlowObject_PathKey() {
        return (EAttribute)this.flowObjectEClass.getEStructuralFeatures().get(0);
    }

    @Override
    public EClass getFlowObjectConnection() {
        return this.flowObjectConnectionEClass;
    }

    @Override
    public EAttribute getFlowObjectConnection_SourcePathKey() {
        return (EAttribute)this.flowObjectConnectionEClass.getEStructuralFeatures().get(0);
    }

    @Override
    public EAttribute getFlowObjectConnection_DestinationPathKey() {
        return (EAttribute)this.flowObjectConnectionEClass.getEStructuralFeatures().get(1);
    }

    @Override
    public EAttribute getFlowObjectConnection_ExecutionFlowResultValue() {
        return (EAttribute)this.flowObjectConnectionEClass.getEStructuralFeatures().get(2);
    }

    @Override
    public EReference getFlowObjectConnection_SourceFlow() {
        return (EReference)this.flowObjectConnectionEClass.getEStructuralFeatures().get(3);
    }

    @Override
    public EReference getFlowObjectConnection_DestinationState() {
        return (EReference)this.flowObjectConnectionEClass.getEStructuralFeatures().get(4);
    }

    @Override
    public EClass getState() {
        return this.stateEClass;
    }

    @Override
    public EAttribute getState_Id() {
        return (EAttribute)this.stateEClass.getEStructuralFeatures().get(0);
    }

    @Override
    public EAttribute getState_Description() {
        return (EAttribute)this.stateEClass.getEStructuralFeatures().get(1);
    }

    @Override
    public EClass getStateFlow() {
        return this.stateFlowEClass;
    }

    @Override
    public EReference getStateFlow_States() {
        return (EReference)this.stateFlowEClass.getEStructuralFeatures().get(0);
    }

    @Override
    public EReference getStateFlow_ExecutionFlows() {
        return (EReference)this.stateFlowEClass.getEStructuralFeatures().get(1);
    }

    @Override
    public EReference getStateFlow_StateConnections() {
        return (EReference)this.stateFlowEClass.getEStructuralFeatures().get(2);
    }

    @Override
    public EReference getStateFlow_FlowConnections() {
        return (EReference)this.stateFlowEClass.getEStructuralFeatures().get(3);
    }

    @Override
    public EReference getStateFlow_StartPoint() {
        return (EReference)this.stateFlowEClass.getEStructuralFeatures().get(4);
    }

    @Override
    public EReference getStateFlow_StartPointConnection() {
        return (EReference)this.stateFlowEClass.getEStructuralFeatures().get(5);
    }

    @Override
    public EAttribute getStateFlow_Id() {
        return (EAttribute)this.stateFlowEClass.getEStructuralFeatures().get(6);
    }

    @Override
    public EAttribute getStateFlow_Description() {
        return (EAttribute)this.stateFlowEClass.getEStructuralFeatures().get(7);
    }

    @Override
    public EAttribute getStateFlow_Volatile() {
        return (EAttribute)this.stateFlowEClass.getEStructuralFeatures().get(8);
    }

    @Override
    public EClass getEventConnection() {
        return this.eventConnectionEClass;
    }

    @Override
    public EAttribute getEventConnection_SourcePathKey() {
        return (EAttribute)this.eventConnectionEClass.getEStructuralFeatures().get(0);
    }

    @Override
    public EAttribute getEventConnection_DestinationPathKey() {
        return (EAttribute)this.eventConnectionEClass.getEStructuralFeatures().get(1);
    }

    @Override
    public EAttribute getEventConnection_EventID() {
        return (EAttribute)this.eventConnectionEClass.getEStructuralFeatures().get(2);
    }

    @Override
    public EReference getEventConnection_SourceState() {
        return (EReference)this.eventConnectionEClass.getEStructuralFeatures().get(3);
    }

    @Override
    public EReference getEventConnection_DestinationFlow() {
        return (EReference)this.eventConnectionEClass.getEStructuralFeatures().get(4);
    }

    @Override
    public EClass getStartPoint() {
        return this.startPointEClass;
    }

    @Override
    public EClass getStartPointConnection() {
        return this.startPointConnectionEClass;
    }

    @Override
    public EReference getStartPointConnection_StartPoint() {
        return (EReference)this.startPointConnectionEClass.getEStructuralFeatures().get(0);
    }

    @Override
    public EReference getStartPointConnection_DestinationState() {
        return (EReference)this.startPointConnectionEClass.getEStructuralFeatures().get(1);
    }

    @Override
    public EAttribute getStartPointConnection_DestinationStateId() {
        return (EAttribute)this.startPointConnectionEClass.getEStructuralFeatures().get(2);
    }

    @Override
    public StateflowFactory getStateflowFactory() {
        return (StateflowFactory)this.getEFactoryInstance();
    }

    public void createPackageContents() {
        if (this.isCreated) {
            return;
        }
        this.isCreated = true;
        this.executionFlowObjectEClass = this.createEClass(0);
        this.createEAttribute(this.executionFlowObjectEClass, 1);
        this.createEAttribute(this.executionFlowObjectEClass, 2);
        this.createEAttribute(this.executionFlowObjectEClass, 3);
        this.createEAttribute(this.executionFlowObjectEClass, 4);
        this.flowObjectEClass = this.createEClass(1);
        this.createEAttribute(this.flowObjectEClass, 0);
        this.flowObjectConnectionEClass = this.createEClass(2);
        this.createEAttribute(this.flowObjectConnectionEClass, 0);
        this.createEAttribute(this.flowObjectConnectionEClass, 1);
        this.createEAttribute(this.flowObjectConnectionEClass, 2);
        this.createEReference(this.flowObjectConnectionEClass, 3);
        this.createEReference(this.flowObjectConnectionEClass, 4);
        this.stateEClass = this.createEClass(3);
        this.createEAttribute(this.stateEClass, 1);
        this.createEAttribute(this.stateEClass, 2);
        this.stateFlowEClass = this.createEClass(4);
        this.createEReference(this.stateFlowEClass, 0);
        this.createEReference(this.stateFlowEClass, 1);
        this.createEReference(this.stateFlowEClass, 2);
        this.createEReference(this.stateFlowEClass, 3);
        this.createEReference(this.stateFlowEClass, 4);
        this.createEReference(this.stateFlowEClass, 5);
        this.createEAttribute(this.stateFlowEClass, 6);
        this.createEAttribute(this.stateFlowEClass, 7);
        this.createEAttribute(this.stateFlowEClass, 8);
        this.eventConnectionEClass = this.createEClass(5);
        this.createEAttribute(this.eventConnectionEClass, 0);
        this.createEAttribute(this.eventConnectionEClass, 1);
        this.createEAttribute(this.eventConnectionEClass, 2);
        this.createEReference(this.eventConnectionEClass, 3);
        this.createEReference(this.eventConnectionEClass, 4);
        this.startPointEClass = this.createEClass(6);
        this.startPointConnectionEClass = this.createEClass(7);
        this.createEReference(this.startPointConnectionEClass, 0);
        this.createEReference(this.startPointConnectionEClass, 1);
        this.createEAttribute(this.startPointConnectionEClass, 2);
    }

    public void initializePackageContents() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;
        this.setName("stateflow");
        this.setNsPrefix("com.csi.gmf.stateflow");
        this.setNsURI("http:///com/csi/gmf/stateflow.ecore");
        this.executionFlowObjectEClass.getESuperTypes().add((Object)this.getFlowObject());
        this.stateEClass.getESuperTypes().add((Object)this.getFlowObject());
        this.initEClass(this.executionFlowObjectEClass, ExecutionFlowObject.class, "ExecutionFlowObject", false, false, true);
        this.initEAttribute(this.getExecutionFlowObject_FilePath(), (EClassifier)this.ecorePackage.getEString(), "filePath", null, 0, 1, ExecutionFlowObject.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getExecutionFlowObject_Title(), (EClassifier)this.ecorePackage.getEString(), "title", null, 0, 1, ExecutionFlowObject.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getExecutionFlowObject_Id(), (EClassifier)this.ecorePackage.getEString(), "id", null, 0, 1, ExecutionFlowObject.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getExecutionFlowObject_Description(), (EClassifier)this.ecorePackage.getEString(), "description", null, 0, 1, ExecutionFlowObject.class, false, false, true, false, false, true, false, true);
        this.initEClass(this.flowObjectEClass, FlowObject.class, "FlowObject", false, false, true);
        this.initEAttribute(this.getFlowObject_PathKey(), (EClassifier)this.ecorePackage.getELongObject(), "pathKey", null, 1, 1, FlowObject.class, false, false, true, false, false, true, false, true);
        this.initEClass(this.flowObjectConnectionEClass, FlowObjectConnection.class, "FlowObjectConnection", false, false, true);
        this.initEAttribute(this.getFlowObjectConnection_SourcePathKey(), (EClassifier)this.ecorePackage.getELongObject(), "sourcePathKey", null, 0, 1, FlowObjectConnection.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getFlowObjectConnection_DestinationPathKey(), (EClassifier)this.ecorePackage.getELongObject(), "destinationPathKey", null, 0, 1, FlowObjectConnection.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getFlowObjectConnection_ExecutionFlowResultValue(), (EClassifier)this.ecorePackage.getEString(), "executionFlowResultValue", null, 0, 1, FlowObjectConnection.class, false, false, true, false, false, true, false, true);
        this.initEReference(this.getFlowObjectConnection_SourceFlow(), (EClassifier)this.getExecutionFlowObject(), null, "sourceFlow", null, 0, 1, FlowObjectConnection.class, false, false, true, false, true, false, true, false, true);
        this.initEReference(this.getFlowObjectConnection_DestinationState(), (EClassifier)this.getState(), null, "destinationState", null, 0, 1, FlowObjectConnection.class, false, false, true, false, true, false, true, false, true);
        this.initEClass(this.stateEClass, State.class, "State", false, false, true);
        this.initEAttribute(this.getState_Id(), (EClassifier)this.ecorePackage.getEString(), "id", null, 1, 1, State.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getState_Description(), (EClassifier)this.ecorePackage.getEString(), "description", null, 0, 1, State.class, false, false, true, false, false, true, false, true);
        this.initEClass(this.stateFlowEClass, StateFlow.class, "StateFlow", false, false, true);
        this.initEReference(this.getStateFlow_States(), (EClassifier)this.getState(), null, "states", null, 0, -1, StateFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEReference(this.getStateFlow_ExecutionFlows(), (EClassifier)this.getExecutionFlowObject(), null, "executionFlows", null, 0, -1, StateFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEReference(this.getStateFlow_StateConnections(), (EClassifier)this.getEventConnection(), null, "stateConnections", null, 0, -1, StateFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEReference(this.getStateFlow_FlowConnections(), (EClassifier)this.getFlowObjectConnection(), null, "flowConnections", null, 0, -1, StateFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEReference(this.getStateFlow_StartPoint(), (EClassifier)this.getStartPoint(), null, "startPoint", null, 0, 1, StateFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEReference(this.getStateFlow_StartPointConnection(), (EClassifier)this.getStartPointConnection(), null, "startPointConnection", null, 0, 1, StateFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEAttribute(this.getStateFlow_Id(), (EClassifier)this.ecorePackage.getEString(), "id", null, 0, 1, StateFlow.class, false, false, true, false, true, true, false, true);
        this.initEAttribute(this.getStateFlow_Description(), (EClassifier)this.ecorePackage.getEString(), "description", "", 0, 1, StateFlow.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getStateFlow_Volatile(), (EClassifier)this.ecorePackage.getEBoolean(), "volatile", "false", 1, 1, StateFlow.class, false, false, true, false, false, true, false, true);
        this.initEClass(this.eventConnectionEClass, EventConnection.class, "EventConnection", false, false, true);
        this.initEAttribute(this.getEventConnection_SourcePathKey(), (EClassifier)this.ecorePackage.getELongObject(), "sourcePathKey", null, 0, 1, EventConnection.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getEventConnection_DestinationPathKey(), (EClassifier)this.ecorePackage.getELongObject(), "destinationPathKey", null, 0, 1, EventConnection.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getEventConnection_EventID(), (EClassifier)this.ecorePackage.getEString(), "eventID", null, 0, 1, EventConnection.class, false, false, true, false, false, true, false, true);
        this.initEReference(this.getEventConnection_SourceState(), (EClassifier)this.getState(), null, "sourceState", null, 0, 1, EventConnection.class, false, false, true, false, true, false, true, false, true);
        this.initEReference(this.getEventConnection_DestinationFlow(), (EClassifier)this.getExecutionFlowObject(), null, "destinationFlow", null, 0, 1, EventConnection.class, false, false, true, false, true, false, true, false, true);
        this.initEClass(this.startPointEClass, StartPoint.class, "StartPoint", false, false, true);
        this.initEClass(this.startPointConnectionEClass, StartPointConnection.class, "StartPointConnection", false, false, true);
        this.initEReference(this.getStartPointConnection_StartPoint(), (EClassifier)this.getStartPoint(), null, "startPoint", null, 0, 1, StartPointConnection.class, false, false, true, false, true, false, true, false, true);
        this.initEReference(this.getStartPointConnection_DestinationState(), (EClassifier)this.getState(), null, "destinationState", null, 0, 1, StartPointConnection.class, false, false, true, false, true, false, true, false, true);
        this.initEAttribute(this.getStartPointConnection_DestinationStateId(), (EClassifier)this.ecorePackage.getEString(), "destinationStateId", null, 0, 1, StartPointConnection.class, false, false, true, false, false, true, false, true);
        this.createResource("http:///com/csi/gmf/stateflow.ecore");
    }
}

