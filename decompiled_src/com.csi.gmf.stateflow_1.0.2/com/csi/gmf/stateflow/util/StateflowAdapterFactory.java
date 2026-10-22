/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.notify.Adapter
 *  org.eclipse.emf.common.notify.Notifier
 *  org.eclipse.emf.common.notify.impl.AdapterFactoryImpl
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.stateflow.util;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.FlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.util.StateflowSwitch;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

public class StateflowAdapterFactory
extends AdapterFactoryImpl {
    protected static StateflowPackage modelPackage;
    protected StateflowSwitch modelSwitch = new StateflowSwitch(){

        @Override
        public Object caseExecutionFlowObject(ExecutionFlowObject object) {
            return StateflowAdapterFactory.this.createExecutionFlowObjectAdapter();
        }

        @Override
        public Object caseFlowObject(FlowObject object) {
            return StateflowAdapterFactory.this.createFlowObjectAdapter();
        }

        @Override
        public Object caseFlowObjectConnection(FlowObjectConnection object) {
            return StateflowAdapterFactory.this.createFlowObjectConnectionAdapter();
        }

        @Override
        public Object caseState(State object) {
            return StateflowAdapterFactory.this.createStateAdapter();
        }

        @Override
        public Object caseStateFlow(StateFlow object) {
            return StateflowAdapterFactory.this.createStateFlowAdapter();
        }

        @Override
        public Object caseEventConnection(EventConnection object) {
            return StateflowAdapterFactory.this.createEventConnectionAdapter();
        }

        @Override
        public Object caseStartPoint(StartPoint object) {
            return StateflowAdapterFactory.this.createStartPointAdapter();
        }

        @Override
        public Object caseStartPointConnection(StartPointConnection object) {
            return StateflowAdapterFactory.this.createStartPointConnectionAdapter();
        }

        @Override
        public Object defaultCase(EObject object) {
            return StateflowAdapterFactory.this.createEObjectAdapter();
        }
    };

    public StateflowAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = StateflowPackage.eINSTANCE;
        }
    }

    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    public Adapter createAdapter(Notifier target) {
        return (Adapter)this.modelSwitch.doSwitch((EObject)target);
    }

    public Adapter createExecutionFlowObjectAdapter() {
        return null;
    }

    public Adapter createFlowObjectAdapter() {
        return null;
    }

    public Adapter createFlowObjectConnectionAdapter() {
        return null;
    }

    public Adapter createStateAdapter() {
        return null;
    }

    public Adapter createStateFlowAdapter() {
        return null;
    }

    public Adapter createEventConnectionAdapter() {
        return null;
    }

    public Adapter createStartPointAdapter() {
        return null;
    }

    public Adapter createStartPointConnectionAdapter() {
        return null;
    }

    public Adapter createEObjectAdapter() {
        return null;
    }
}

