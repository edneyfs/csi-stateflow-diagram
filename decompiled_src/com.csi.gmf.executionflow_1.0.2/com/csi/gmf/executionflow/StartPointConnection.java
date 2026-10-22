/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.executionflow;

import com.csi.gmf.executionflow.SourceConnectable;
import com.csi.gmf.executionflow.StartPoint;
import org.eclipse.emf.ecore.EObject;

public interface StartPointConnection
extends EObject {
    public Long getDestinationObjectPathKey();

    public void setDestinationObjectPathKey(Long var1);

    public StartPoint getStartPoint();

    public void setStartPoint(StartPoint var1);

    public SourceConnectable getDestinationObject();

    public void setDestinationObject(SourceConnectable var1);
}

