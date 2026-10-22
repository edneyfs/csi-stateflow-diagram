/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EFactory
 */
package com.csi.gmf.stateflow;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.FlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.impl.StateflowFactoryImpl;
import org.eclipse.emf.ecore.EFactory;

public interface StateflowFactory
extends EFactory {
    public static final StateflowFactory eINSTANCE = StateflowFactoryImpl.init();

    public ExecutionFlowObject createExecutionFlowObject();

    public FlowObject createFlowObject();

    public FlowObjectConnection createFlowObjectConnection();

    public State createState();

    public StateFlow createStateFlow();

    public EventConnection createEventConnection();

    public StartPoint createStartPoint();

    public StartPointConnection createStartPointConnection();

    public StateflowPackage getStateflowPackage();
}

