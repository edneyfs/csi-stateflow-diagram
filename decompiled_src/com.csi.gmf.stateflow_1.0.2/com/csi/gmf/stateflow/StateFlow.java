/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.util.EList
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.stateflow;

import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public interface StateFlow
extends EObject {
    public StartPoint getStartPoint();

    public void setStartPoint(StartPoint var1);

    public StartPointConnection getStartPointConnection();

    public void setStartPointConnection(StartPointConnection var1);

    public String getId();

    public void setId(String var1);

    public String getDescription();

    public void setDescription(String var1);

    public boolean isVolatile();

    public void setVolatile(boolean var1);

    public EList getStates();

    public EList getExecutionFlows();

    public EList getFlowConnections();

    public EList getStateConnections();
}

