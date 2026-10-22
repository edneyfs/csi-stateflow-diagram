/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.stateflow;

import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.State;
import org.eclipse.emf.ecore.EObject;

public interface StartPointConnection
extends EObject {
    public StartPoint getStartPoint();

    public void setStartPoint(StartPoint var1);

    public State getDestinationState();

    public void setDestinationState(State var1);

    public String getDestinationStateId();

    public void setDestinationStateId(String var1);
}

