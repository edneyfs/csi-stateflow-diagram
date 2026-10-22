/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.executionflow;

import com.csi.gmf.executionflow.FlowObject;
import com.csi.gmf.executionflow.SourceConnectable;
import org.eclipse.emf.ecore.EObject;

public interface FlowObjectConnection
extends EObject {
    public Long getSourcePathKey();

    public void setSourcePathKey(Long var1);

    public Long getDestinationPathKey();

    public void setDestinationPathKey(Long var1);

    public String getExecutionFlowResultValue();

    public void setExecutionFlowResultValue(String var1);

    public SourceConnectable getSourceObject();

    public void setSourceObject(SourceConnectable var1);

    public FlowObject getDestinationObject();

    public void setDestinationObject(FlowObject var1);
}

