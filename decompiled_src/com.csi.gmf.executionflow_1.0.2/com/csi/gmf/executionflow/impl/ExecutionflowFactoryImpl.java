/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EDataType
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.EPackage$Registry
 *  org.eclipse.emf.ecore.impl.EFactoryImpl
 *  org.eclipse.emf.ecore.plugin.EcorePlugin
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
import com.csi.gmf.executionflow.impl.ExecutableFlowObjectImpl;
import com.csi.gmf.executionflow.impl.ExecutionFlowImpl;
import com.csi.gmf.executionflow.impl.ExecutionFlowObjectImpl;
import com.csi.gmf.executionflow.impl.ExecutionFlowResultImpl;
import com.csi.gmf.executionflow.impl.FlowObjectConnectionImpl;
import com.csi.gmf.executionflow.impl.FlowObjectImpl;
import com.csi.gmf.executionflow.impl.SourceConnectableImpl;
import com.csi.gmf.executionflow.impl.StartPointConnectionImpl;
import com.csi.gmf.executionflow.impl.StartPointImpl;
import java.util.Enumeration;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

public class ExecutionflowFactoryImpl
extends EFactoryImpl
implements ExecutionflowFactory {
    public static ExecutionflowFactory init() {
        try {
            ExecutionflowFactory theExecutionflowFactory = (ExecutionflowFactory)EPackage.Registry.INSTANCE.getEFactory("http:///com/csi/gmf/executionflow.ecore");
            if (theExecutionflowFactory != null) {
                return theExecutionflowFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log((Object)exception);
        }
        return new ExecutionflowFactoryImpl();
    }

    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case 0: {
                return this.createExecutableFlowObject();
            }
            case 1: {
                return this.createExecutionFlow();
            }
            case 2: {
                return this.createExecutionFlowObject();
            }
            case 3: {
                return this.createExecutionFlowResult();
            }
            case 4: {
                return this.createFlowObject();
            }
            case 5: {
                return this.createFlowObjectConnection();
            }
            case 6: {
                return this.createSourceConnectable();
            }
            case 7: {
                return this.createStartPoint();
            }
            case 8: {
                return this.createStartPointConnection();
            }
        }
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }

    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case 9: {
                return this.createEnumerationFromString(eDataType, initialValue);
            }
        }
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }

    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case 9: {
                return this.convertEnumerationToString(eDataType, instanceValue);
            }
        }
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }

    @Override
    public ExecutableFlowObject createExecutableFlowObject() {
        ExecutableFlowObjectImpl executableFlowObject = new ExecutableFlowObjectImpl();
        return executableFlowObject;
    }

    @Override
    public ExecutionFlow createExecutionFlow() {
        ExecutionFlowImpl executionFlow = new ExecutionFlowImpl();
        return executionFlow;
    }

    @Override
    public ExecutionFlowObject createExecutionFlowObject() {
        ExecutionFlowObjectImpl executionFlowObject = new ExecutionFlowObjectImpl();
        return executionFlowObject;
    }

    @Override
    public ExecutionFlowResult createExecutionFlowResult() {
        ExecutionFlowResultImpl executionFlowResult = new ExecutionFlowResultImpl();
        return executionFlowResult;
    }

    @Override
    public FlowObject createFlowObject() {
        FlowObjectImpl flowObject = new FlowObjectImpl();
        return flowObject;
    }

    @Override
    public FlowObjectConnection createFlowObjectConnection() {
        FlowObjectConnectionImpl flowObjectConnection = new FlowObjectConnectionImpl();
        return flowObjectConnection;
    }

    @Override
    public SourceConnectable createSourceConnectable() {
        SourceConnectableImpl sourceConnectable = new SourceConnectableImpl();
        return sourceConnectable;
    }

    @Override
    public StartPoint createStartPoint() {
        StartPointImpl startPoint = new StartPointImpl();
        return startPoint;
    }

    @Override
    public StartPointConnection createStartPointConnection() {
        StartPointConnectionImpl startPointConnection = new StartPointConnectionImpl();
        return startPointConnection;
    }

    public Enumeration createEnumerationFromString(EDataType eDataType, String initialValue) {
        return (Enumeration)super.createFromString(eDataType, initialValue);
    }

    public String convertEnumerationToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    @Override
    public ExecutionflowPackage getExecutionflowPackage() {
        return (ExecutionflowPackage)this.getEPackage();
    }

    public static ExecutionflowPackage getPackage() {
        return ExecutionflowPackage.eINSTANCE;
    }
}

