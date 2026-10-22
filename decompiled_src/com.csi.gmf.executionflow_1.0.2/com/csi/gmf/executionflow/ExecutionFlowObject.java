/*
 * Decompiled with CFR 0.152.
 */
package com.csi.gmf.executionflow;

import com.csi.gmf.executionflow.SourceConnectable;

public interface ExecutionFlowObject
extends SourceConnectable {
    public String getFilePath();

    public void setFilePath(String var1);

    public String getId();

    public void setId(String var1);

    public String getDescription();

    public void setDescription(String var1);

    public String getFileName();

    public void setFileName(String var1);
}

