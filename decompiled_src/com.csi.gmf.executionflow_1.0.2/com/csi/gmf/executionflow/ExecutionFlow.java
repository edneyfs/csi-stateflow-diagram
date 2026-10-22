/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.util.EList
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.executionflow;

import com.csi.gmf.executionflow.StartPoint;
import com.csi.gmf.executionflow.StartPointConnection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public interface ExecutionFlow
extends EObject {
    public StartPoint getStartPoint();

    public void setStartPoint(StartPoint var1);

    public StartPointConnection getStartPointConnection();

    public void setStartPointConnection(StartPointConnection var1);

    public String getId();

    public void setId(String var1);

    public String getDescription();

    public void setDescription(String var1);

    public boolean isRequestAuthorization();

    public void setRequestAuthorization(boolean var1);

    public String getTitle();

    public void setTitle(String var1);

    public EList getExecutableFlowObjects();

    public EList getResults();

    public EList getFlowConnections();

    public EList getExecutionFlowObjects();
}

