/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.notify.Adapter
 *  org.eclipse.emf.common.notify.Notifier
 *  org.eclipse.emf.common.notify.impl.AdapterFactoryImpl
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.executionflow.util;

import com.csi.gmf.executionflow.ExecutableFlowObject;
import com.csi.gmf.executionflow.ExecutionFlow;
import com.csi.gmf.executionflow.ExecutionFlowObject;
import com.csi.gmf.executionflow.ExecutionFlowResult;
import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.FlowObject;
import com.csi.gmf.executionflow.FlowObjectConnection;
import com.csi.gmf.executionflow.SourceConnectable;
import com.csi.gmf.executionflow.StartPoint;
import com.csi.gmf.executionflow.StartPointConnection;
import com.csi.gmf.executionflow.util.ExecutionflowSwitch;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

public class ExecutionflowAdapterFactory
extends AdapterFactoryImpl {
    protected static ExecutionflowPackage modelPackage;
    protected ExecutionflowSwitch modelSwitch = new ExecutionflowSwitch(){

        @Override
        public Object caseExecutableFlowObject(ExecutableFlowObject object) {
            return ExecutionflowAdapterFactory.this.createExecutableFlowObjectAdapter();
        }

        @Override
        public Object caseExecutionFlow(ExecutionFlow object) {
            return ExecutionflowAdapterFactory.this.createExecutionFlowAdapter();
        }

        @Override
        public Object caseExecutionFlowObject(ExecutionFlowObject object) {
            return ExecutionflowAdapterFactory.this.createExecutionFlowObjectAdapter();
        }

        @Override
        public Object caseExecutionFlowResult(ExecutionFlowResult object) {
            return ExecutionflowAdapterFactory.this.createExecutionFlowResultAdapter();
        }

        @Override
        public Object caseFlowObject(FlowObject object) {
            return ExecutionflowAdapterFactory.this.createFlowObjectAdapter();
        }

        @Override
        public Object caseFlowObjectConnection(FlowObjectConnection object) {
            return ExecutionflowAdapterFactory.this.createFlowObjectConnectionAdapter();
        }

        @Override
        public Object caseSourceConnectable(SourceConnectable object) {
            return ExecutionflowAdapterFactory.this.createSourceConnectableAdapter();
        }

        @Override
        public Object caseStartPoint(StartPoint object) {
            return ExecutionflowAdapterFactory.this.createStartPointAdapter();
        }

        @Override
        public Object caseStartPointConnection(StartPointConnection object) {
            return ExecutionflowAdapterFactory.this.createStartPointConnectionAdapter();
        }

        @Override
        public Object defaultCase(EObject object) {
            return ExecutionflowAdapterFactory.this.createEObjectAdapter();
        }
    };

    public ExecutionflowAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ExecutionflowPackage.eINSTANCE;
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

    public Adapter createExecutableFlowObjectAdapter() {
        return null;
    }

    public Adapter createExecutionFlowAdapter() {
        return null;
    }

    public Adapter createExecutionFlowObjectAdapter() {
        return null;
    }

    public Adapter createExecutionFlowResultAdapter() {
        return null;
    }

    public Adapter createFlowObjectAdapter() {
        return null;
    }

    public Adapter createFlowObjectConnectionAdapter() {
        return null;
    }

    public Adapter createSourceConnectableAdapter() {
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

