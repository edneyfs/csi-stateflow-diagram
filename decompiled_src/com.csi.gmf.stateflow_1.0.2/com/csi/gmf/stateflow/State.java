/*
 * Decompiled with CFR 0.152.
 */
package com.csi.gmf.stateflow;

import com.csi.gmf.stateflow.FlowObject;

public interface State
extends FlowObject {
    public String getId();

    public void setId(String var1);

    public String getDescription();

    public void setDescription(String var1);
}

