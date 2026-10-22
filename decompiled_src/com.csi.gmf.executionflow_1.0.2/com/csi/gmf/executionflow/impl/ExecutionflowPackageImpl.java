/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EAttribute
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EClassifier
 *  org.eclipse.emf.ecore.EDataType
 *  org.eclipse.emf.ecore.EFactory
 *  org.eclipse.emf.ecore.EPackage$Registry
 *  org.eclipse.emf.ecore.EReference
 *  org.eclipse.emf.ecore.impl.EPackageImpl
 */
package com.csi.gmf.executionflow.impl;

import com.csi.gmf.executionflow.ExecutableFlowObject;
import com.csi.gmf.executionflow.ExecutionFlow;
import com.csi.gmf.executionflow.ExecutionFlowObject;
import com.csi.gmf.executionflow.ExecutionFlowResult;
import com.csi.gmf.executionflow.ExecutionflowFactory;
import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.FlowObject;
import com.csi.gmf.executionflow.FlowObjectConnection;
import com.csi.gmf.executionflow.SourceConnectable;
import com.csi.gmf.executionflow.StartPoint;
import com.csi.gmf.executionflow.StartPointConnection;
import java.util.Enumeration;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

public class ExecutionflowPackageImpl
extends EPackageImpl
implements ExecutionflowPackage {
    private EClass executableFlowObjectEClass = null;
    private EClass executionFlowEClass = null;
    private EClass executionFlowObjectEClass = null;
    private EClass executionFlowResultEClass = null;
    private EClass flowObjectEClass = null;
    private EClass flowObjectConnectionEClass = null;
    private EClass sourceConnectableEClass = null;
    private EClass startPointEClass = null;
    private EClass startPointConnectionEClass = null;
    private EDataType enumerationEDataType = null;
    private static boolean isInited = false;
    private boolean isCreated = false;
    private boolean isInitialized = false;

    private ExecutionflowPackageImpl() {
        super("http:///com/csi/gmf/executionflow.ecore", (EFactory)ExecutionflowFactory.eINSTANCE);
    }

    public static ExecutionflowPackage init() {
        if (isInited) {
            return (ExecutionflowPackage)EPackage.Registry.INSTANCE.getEPackage("http:///com/csi/gmf/executionflow.ecore");
        }
        ExecutionflowPackageImpl theExecutionflowPackage = EPackage.Registry.INSTANCE.getEPackage("http:///com/csi/gmf/executionflow.ecore") instanceof ExecutionflowPackageImpl ? EPackage.Registry.INSTANCE.getEPackage("http:///com/csi/gmf/executionflow.ecore") : new ExecutionflowPackageImpl();
        isInited = true;
        theExecutionflowPackage.createPackageContents();
        theExecutionflowPackage.initializePackageContents();
        theExecutionflowPackage.freeze();
        return theExecutionflowPackage;
    }

    @Override
    public EClass getExecutableFlowObject() {
        return this.executableFlowObjectEClass;
    }

    @Override
    public EAttribute getExecutableFlowObject_ClassName() {
        return (EAttribute)this.executableFlowObjectEClass.getEStructuralFeatures().get(0);
    }

    @Override
    public EAttribute getExecutableFlowObject_SimpleClassName() {
        return (EAttribute)this.executableFlowObjectEClass.getEStructuralFeatures().get(1);
    }

    @Override
    public EClass getExecutionFlow() {
        return this.executionFlowEClass;
    }

    @Override
    public EAttribute getExecutionFlow_Title() {
        return (EAttribute)this.executionFlowEClass.getEStructuralFeatures().get(0);
    }

    @Override
    public EReference getExecutionFlow_ExecutableFlowObjects() {
        return (EReference)this.executionFlowEClass.getEStructuralFeatures().get(1);
    }

    @Override
    public EReference getExecutionFlow_Results() {
        return (EReference)this.executionFlowEClass.getEStructuralFeatures().get(2);
    }

    @Override
    public EReference getExecutionFlow_FlowConnections() {
        return (EReference)this.executionFlowEClass.getEStructuralFeatures().get(3);
    }

    @Override
    public EReference getExecutionFlow_ExecutionFlowObjects() {
        return (EReference)this.executionFlowEClass.getEStructuralFeatures().get(4);
    }

    @Override
    public EReference getExecutionFlow_StartPoint() {
        return (EReference)this.executionFlowEClass.getEStructuralFeatures().get(5);
    }

    @Override
    public EReference getExecutionFlow_StartPointConnection() {
        return (EReference)this.executionFlowEClass.getEStructuralFeatures().get(6);
    }

    @Override
    public EAttribute getExecutionFlow_Id() {
        return (EAttribute)this.executionFlowEClass.getEStructuralFeatures().get(7);
    }

    @Override
    public EAttribute getExecutionFlow_Description() {
        return (EAttribute)this.executionFlowEClass.getEStructuralFeatures().get(8);
    }

    @Override
    public EAttribute getExecutionFlow_RequestAuthorization() {
        return (EAttribute)this.executionFlowEClass.getEStructuralFeatures().get(9);
    }

    @Override
    public EClass getExecutionFlowObject() {
        return this.executionFlowObjectEClass;
    }

    @Override
    public EAttribute getExecutionFlowObject_FileName() {
        return (EAttribute)this.executionFlowObjectEClass.getEStructuralFeatures().get(0);
    }

    @Override
    public EAttribute getExecutionFlowObject_FilePath() {
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
    public EClass getExecutionFlowResult() {
        return this.executionFlowResultEClass;
    }

    @Override
    public EAttribute getExecutionFlowResult_ExecutionFlowResultValue() {
        return (EAttribute)this.executionFlowResultEClass.getEStructuralFeatures().get(0);
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
    public EReference getFlowObjectConnection_SourceObject() {
        return (EReference)this.flowObjectConnectionEClass.getEStructuralFeatures().get(3);
    }

    @Override
    public EReference getFlowObjectConnection_DestinationObject() {
        return (EReference)this.flowObjectConnectionEClass.getEStructuralFeatures().get(4);
    }

    @Override
    public EClass getSourceConnectable() {
        return this.sourceConnectableEClass;
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
    public EReference getStartPointConnection_DestinationObject() {
        return (EReference)this.startPointConnectionEClass.getEStructuralFeatures().get(1);
    }

    @Override
    public EAttribute getStartPointConnection_DestinationObjectPathKey() {
        return (EAttribute)this.startPointConnectionEClass.getEStructuralFeatures().get(2);
    }

    @Override
    public EDataType getEnumeration() {
        return this.enumerationEDataType;
    }

    @Override
    public ExecutionflowFactory getExecutionflowFactory() {
        return (ExecutionflowFactory)this.getEFactoryInstance();
    }

    public void createPackageContents() {
        if (this.isCreated) {
            return;
        }
        this.isCreated = true;
        this.executableFlowObjectEClass = this.createEClass(0);
        this.createEAttribute(this.executableFlowObjectEClass, 1);
        this.createEAttribute(this.executableFlowObjectEClass, 2);
        this.executionFlowEClass = this.createEClass(1);
        this.createEAttribute(this.executionFlowEClass, 0);
        this.createEReference(this.executionFlowEClass, 1);
        this.createEReference(this.executionFlowEClass, 2);
        this.createEReference(this.executionFlowEClass, 3);
        this.createEReference(this.executionFlowEClass, 4);
        this.createEReference(this.executionFlowEClass, 5);
        this.createEReference(this.executionFlowEClass, 6);
        this.createEAttribute(this.executionFlowEClass, 7);
        this.createEAttribute(this.executionFlowEClass, 8);
        this.createEAttribute(this.executionFlowEClass, 9);
        this.executionFlowObjectEClass = this.createEClass(2);
        this.createEAttribute(this.executionFlowObjectEClass, 1);
        this.createEAttribute(this.executionFlowObjectEClass, 2);
        this.createEAttribute(this.executionFlowObjectEClass, 3);
        this.createEAttribute(this.executionFlowObjectEClass, 4);
        this.executionFlowResultEClass = this.createEClass(3);
        this.createEAttribute(this.executionFlowResultEClass, 1);
        this.flowObjectEClass = this.createEClass(4);
        this.createEAttribute(this.flowObjectEClass, 0);
        this.flowObjectConnectionEClass = this.createEClass(5);
        this.createEAttribute(this.flowObjectConnectionEClass, 0);
        this.createEAttribute(this.flowObjectConnectionEClass, 1);
        this.createEAttribute(this.flowObjectConnectionEClass, 2);
        this.createEReference(this.flowObjectConnectionEClass, 3);
        this.createEReference(this.flowObjectConnectionEClass, 4);
        this.sourceConnectableEClass = this.createEClass(6);
        this.startPointEClass = this.createEClass(7);
        this.startPointConnectionEClass = this.createEClass(8);
        this.createEReference(this.startPointConnectionEClass, 0);
        this.createEReference(this.startPointConnectionEClass, 1);
        this.createEAttribute(this.startPointConnectionEClass, 2);
        this.enumerationEDataType = this.createEDataType(9);
    }

    public void initializePackageContents() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;
        this.setName("executionflow");
        this.setNsPrefix("com.csi.gmf.executionflow");
        this.setNsURI("http:///com/csi/gmf/executionflow.ecore");
        this.executableFlowObjectEClass.getESuperTypes().add((Object)this.getSourceConnectable());
        this.executionFlowObjectEClass.getESuperTypes().add((Object)this.getSourceConnectable());
        this.executionFlowResultEClass.getESuperTypes().add((Object)this.getFlowObject());
        this.sourceConnectableEClass.getESuperTypes().add((Object)this.getFlowObject());
        this.initEClass(this.executableFlowObjectEClass, ExecutableFlowObject.class, "ExecutableFlowObject", false, false, true);
        this.initEAttribute(this.getExecutableFlowObject_ClassName(), (EClassifier)this.ecorePackage.getEString(), "className", null, 0, 1, ExecutableFlowObject.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getExecutableFlowObject_SimpleClassName(), (EClassifier)this.ecorePackage.getEString(), "simpleClassName", null, 0, 1, ExecutableFlowObject.class, false, false, true, false, false, true, false, true);
        this.initEClass(this.executionFlowEClass, ExecutionFlow.class, "ExecutionFlow", false, false, true);
        this.initEAttribute(this.getExecutionFlow_Title(), (EClassifier)this.ecorePackage.getEString(), "title", null, 0, 1, ExecutionFlow.class, false, false, true, false, false, true, false, true);
        this.initEReference(this.getExecutionFlow_ExecutableFlowObjects(), (EClassifier)this.getExecutableFlowObject(), null, "executableFlowObjects", null, 0, -1, ExecutionFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEReference(this.getExecutionFlow_Results(), (EClassifier)this.getExecutionFlowResult(), null, "results", null, 0, -1, ExecutionFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEReference(this.getExecutionFlow_FlowConnections(), (EClassifier)this.getFlowObjectConnection(), null, "flowConnections", null, 0, -1, ExecutionFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEReference(this.getExecutionFlow_ExecutionFlowObjects(), (EClassifier)this.getExecutionFlowObject(), null, "executionFlowObjects", null, 0, -1, ExecutionFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEReference(this.getExecutionFlow_StartPoint(), (EClassifier)this.getStartPoint(), null, "startPoint", null, 0, 1, ExecutionFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEReference(this.getExecutionFlow_StartPointConnection(), (EClassifier)this.getStartPointConnection(), null, "startPointConnection", null, 0, 1, ExecutionFlow.class, false, false, true, true, false, false, true, false, true);
        this.initEAttribute(this.getExecutionFlow_Id(), (EClassifier)this.ecorePackage.getEString(), "id", null, 0, 1, ExecutionFlow.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getExecutionFlow_Description(), (EClassifier)this.ecorePackage.getEString(), "description", null, 0, 1, ExecutionFlow.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getExecutionFlow_RequestAuthorization(), (EClassifier)this.ecorePackage.getEBoolean(), "requestAuthorization", "false", 0, 1, ExecutionFlow.class, false, false, true, false, false, false, false, true);
        this.initEClass(this.executionFlowObjectEClass, ExecutionFlowObject.class, "ExecutionFlowObject", false, false, true);
        this.initEAttribute(this.getExecutionFlowObject_FileName(), (EClassifier)this.ecorePackage.getEString(), "fileName", null, 0, 1, ExecutionFlowObject.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getExecutionFlowObject_FilePath(), (EClassifier)this.ecorePackage.getEString(), "filePath", null, 0, 1, ExecutionFlowObject.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getExecutionFlowObject_Id(), (EClassifier)this.ecorePackage.getEString(), "id", null, 0, 1, ExecutionFlowObject.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getExecutionFlowObject_Description(), (EClassifier)this.ecorePackage.getEString(), "description", null, 0, 1, ExecutionFlowObject.class, false, false, true, false, false, true, false, true);
        this.initEClass(this.executionFlowResultEClass, ExecutionFlowResult.class, "ExecutionFlowResult", false, false, true);
        this.initEAttribute(this.getExecutionFlowResult_ExecutionFlowResultValue(), (EClassifier)this.ecorePackage.getEString(), "executionFlowResultValue", "", 0, 1, ExecutionFlowResult.class, false, false, true, false, false, false, false, true);
        this.initEClass(this.flowObjectEClass, FlowObject.class, "FlowObject", false, false, true);
        this.initEAttribute(this.getFlowObject_PathKey(), (EClassifier)this.ecorePackage.getELongObject(), "pathKey", null, 1, 1, FlowObject.class, false, false, true, false, false, true, false, true);
        this.initEClass(this.flowObjectConnectionEClass, FlowObjectConnection.class, "FlowObjectConnection", false, false, true);
        this.initEAttribute(this.getFlowObjectConnection_SourcePathKey(), (EClassifier)this.ecorePackage.getELongObject(), "sourcePathKey", null, 0, 1, FlowObjectConnection.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getFlowObjectConnection_DestinationPathKey(), (EClassifier)this.ecorePackage.getELongObject(), "destinationPathKey", null, 0, 1, FlowObjectConnection.class, false, false, true, false, false, true, false, true);
        this.initEAttribute(this.getFlowObjectConnection_ExecutionFlowResultValue(), (EClassifier)this.ecorePackage.getEString(), "executionFlowResultValue", null, 0, 1, FlowObjectConnection.class, false, false, true, false, false, true, false, true);
        this.initEReference(this.getFlowObjectConnection_SourceObject(), (EClassifier)this.getSourceConnectable(), null, "sourceObject", null, 0, 1, FlowObjectConnection.class, false, false, true, false, true, false, true, false, true);
        this.initEReference(this.getFlowObjectConnection_DestinationObject(), (EClassifier)this.getFlowObject(), null, "destinationObject", null, 0, 1, FlowObjectConnection.class, false, false, true, false, true, false, true, false, true);
        this.initEClass(this.sourceConnectableEClass, SourceConnectable.class, "SourceConnectable", false, false, true);
        this.initEClass(this.startPointEClass, StartPoint.class, "StartPoint", false, false, true);
        this.initEClass(this.startPointConnectionEClass, StartPointConnection.class, "StartPointConnection", false, false, true);
        this.initEReference(this.getStartPointConnection_StartPoint(), (EClassifier)this.getStartPoint(), null, "startPoint", null, 0, 1, StartPointConnection.class, false, false, true, false, true, false, true, false, true);
        this.initEReference(this.getStartPointConnection_DestinationObject(), (EClassifier)this.getSourceConnectable(), null, "destinationObject", null, 0, 1, StartPointConnection.class, false, false, true, false, true, false, true, false, true);
        this.initEAttribute(this.getStartPointConnection_DestinationObjectPathKey(), (EClassifier)this.ecorePackage.getELongObject(), "destinationObjectPathKey", null, 0, 1, StartPointConnection.class, false, false, true, false, false, true, false, true);
        this.initEDataType(this.enumerationEDataType, Enumeration.class, "Enumeration", true, false);
        this.createResource("http:///com/csi/gmf/executionflow.ecore");
    }
}

