/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EFactory
 */
package com.csi.gmf.executionflow;

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
import com.csi.gmf.executionflow.impl.ExecutionflowFactoryImpl;
import org.eclipse.emf.ecore.EFactory;

public interface ExecutionflowFactory
extends EFactory {
    public static final ExecutionflowFactory eINSTANCE = ExecutionflowFactoryImpl.init();

    public ExecutableFlowObject createExecutableFlowObject();

    public ExecutionFlow createExecutionFlow();

    public ExecutionFlowObject createExecutionFlowObject();

    public ExecutionFlowResult createExecutionFlowResult();

    public FlowObject createFlowObject();

    public FlowObjectConnection createFlowObjectConnection();

    public SourceConnectable createSourceConnectable();

    public StartPoint createStartPoint();

    public StartPointConnection createStartPointConnection();

    public ExecutionflowPackage getExecutionflowPackage();
}

