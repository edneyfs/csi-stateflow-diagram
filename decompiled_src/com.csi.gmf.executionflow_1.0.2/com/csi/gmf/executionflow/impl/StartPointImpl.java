/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.impl.EObjectImpl
 */
package com.csi.gmf.executionflow.impl;

import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.StartPoint;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;

public class StartPointImpl
extends EObjectImpl
implements StartPoint {
    protected StartPointImpl() {
    }

    protected EClass eStaticClass() {
        return ExecutionflowPackage.Literals.START_POINT;
    }
}

