/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EClass
 */
package com.csi.gmf.executionflow.impl;

import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.SourceConnectable;
import com.csi.gmf.executionflow.impl.FlowObjectImpl;
import org.eclipse.emf.ecore.EClass;

public class SourceConnectableImpl
extends FlowObjectImpl
implements SourceConnectable {
    protected SourceConnectableImpl() {
    }

    @Override
    protected EClass eStaticClass() {
        return ExecutionflowPackage.Literals.SOURCE_CONNECTABLE;
    }
}

