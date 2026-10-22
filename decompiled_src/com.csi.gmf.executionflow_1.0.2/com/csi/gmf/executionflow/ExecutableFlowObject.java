/*
 * Decompiled with CFR 0.152.
 */
package com.csi.gmf.executionflow;

import com.csi.gmf.executionflow.SourceConnectable;

public interface ExecutableFlowObject
extends SourceConnectable {
    public String getSimpleClassName();

    public void setSimpleClassName(String var1);

    public String getClassName();

    public void setClassName(String var1);
}

