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

public interface EventConnection
extends EObject {
    public Long getSourcePathKey();

    public void setSourcePathKey(Long var1);

    public Long getDestinationPathKey();

    public void setDestinationPathKey(Long var1);

    public String getEventID();

    public void setEventID(String var1);

    public State getSourceState();

    public void setSourceState(State var1);

    public ExecutionFlowObject getDestinationFlow();

    public void setDestinationFlow(ExecutionFlowObject var1);
}

