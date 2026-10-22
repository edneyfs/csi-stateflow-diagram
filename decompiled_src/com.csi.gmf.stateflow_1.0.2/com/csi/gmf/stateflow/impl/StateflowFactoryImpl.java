/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.EPackage$Registry
 *  org.eclipse.emf.ecore.impl.EFactoryImpl
 *  org.eclipse.emf.ecore.plugin.EcorePlugin
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
import com.csi.gmf.stateflow.impl.EventConnectionImpl;
import com.csi.gmf.stateflow.impl.ExecutionFlowObjectImpl;
import com.csi.gmf.stateflow.impl.FlowObjectConnectionImpl;
import com.csi.gmf.stateflow.impl.FlowObjectImpl;
import com.csi.gmf.stateflow.impl.StartPointConnectionImpl;
import com.csi.gmf.stateflow.impl.StartPointImpl;
import com.csi.gmf.stateflow.impl.StateFlowImpl;
import com.csi.gmf.stateflow.impl.StateImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

public class StateflowFactoryImpl
extends EFactoryImpl
implements StateflowFactory {
    public static StateflowFactory init() {
        try {
            StateflowFactory theStateflowFactory = (StateflowFactory)EPackage.Registry.INSTANCE.getEFactory("http:///com/csi/gmf/stateflow.ecore");
            if (theStateflowFactory != null) {
                return theStateflowFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log((Object)exception);
        }
        return new StateflowFactoryImpl();
    }

    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case 0: {
                return this.createExecutionFlowObject();
            }
            case 1: {
                return this.createFlowObject();
            }
            case 2: {
                return this.createFlowObjectConnection();
            }
            case 3: {
                return this.createState();
            }
            case 4: {
                return this.createStateFlow();
            }
            case 5: {
                return this.createEventConnection();
            }
            case 6: {
                return this.createStartPoint();
            }
            case 7: {
                return this.createStartPointConnection();
            }
        }
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }

    @Override
    public ExecutionFlowObject createExecutionFlowObject() {
        ExecutionFlowObjectImpl executionFlowObject = new ExecutionFlowObjectImpl();
        return executionFlowObject;
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
    public State createState() {
        StateImpl state = new StateImpl();
        return state;
    }

    @Override
    public StateFlow createStateFlow() {
        StateFlowImpl stateFlow = new StateFlowImpl();
        return stateFlow;
    }

    @Override
    public EventConnection createEventConnection() {
        EventConnectionImpl eventConnection = new EventConnectionImpl();
        return eventConnection;
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

    @Override
    public StateflowPackage getStateflowPackage() {
        return (StateflowPackage)this.getEPackage();
    }

    public static StateflowPackage getPackage() {
        return StateflowPackage.eINSTANCE;
    }
}

