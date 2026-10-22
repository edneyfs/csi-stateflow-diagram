/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.stateflow;

import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.State;
import org.eclipse.emf.ecore.EObject;

public interface FlowObjectConnection
extends EObject {
    public Long getSourcePathKey();

    public void setSourcePathKey(Long var1);

    public Long getDestinationPathKey();

    public void setDestinationPathKey(Long var1);

    public String getExecutionFlowResultValue();

    public void setExecutionFlowResultValue(String var1);

    public ExecutionFlowObject getSourceFlow();

    public void setSourceFlow(ExecutionFlowObject var1);

    public State getDestinationState();

    public void setDestinationState(State var1);
}

