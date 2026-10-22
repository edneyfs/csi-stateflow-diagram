/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.InternalEObject
 *  org.eclipse.emf.ecore.impl.ENotificationImpl
 */
package com.csi.gmf.executionflow.impl;

import com.csi.gmf.executionflow.ExecutableFlowObject;
import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.impl.SourceConnectableImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

public class ExecutableFlowObjectImpl
extends SourceConnectableImpl
implements ExecutableFlowObject {
    protected static final String CLASS_NAME_EDEFAULT = null;
    protected String className = CLASS_NAME_EDEFAULT;
    protected static final String SIMPLE_CLASS_NAME_EDEFAULT = null;
    protected String simpleClassName = SIMPLE_CLASS_NAME_EDEFAULT;

    protected ExecutableFlowObjectImpl() {
    }

    @Override
    protected EClass eStaticClass() {
        return ExecutionflowPackage.Literals.EXECUTABLE_FLOW_OBJECT;
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public void setClassName(String newClassName) {
        String oldClassName = this.className;
        this.className = newClassName;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, (Object)oldClassName, (Object)this.className));
        }
    }

    @Override
    public String getSimpleClassName() {
        return this.simpleClassName;
    }

    @Override
    public void setSimpleClassName(String newSimpleClassName) {
        String oldSimpleClassName = this.simpleClassName;
        this.simpleClassName = newSimpleClassName;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, (Object)oldSimpleClassName, (Object)this.simpleClassName));
        }
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case 1: {
                return this.getClassName();
            }
            case 2: {
                return this.getSimpleClassName();
            }
        }
        return super.eGet(featureID, resolve, coreType);
    }

    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case 1: {
                this.setClassName((String)newValue);
                return;
            }
            case 2: {
                this.setSimpleClassName((String)newValue);
                return;
            }
        }
        super.eSet(featureID, newValue);
    }

    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case 1: {
                this.setClassName(CLASS_NAME_EDEFAULT);
                return;
            }
            case 2: {
                this.setSimpleClassName(SIMPLE_CLASS_NAME_EDEFAULT);
                return;
            }
        }
        super.eUnset(featureID);
    }

    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case 1: {
                return CLASS_NAME_EDEFAULT == null ? this.className != null : !CLASS_NAME_EDEFAULT.equals(this.className);
            }
            case 2: {
                return SIMPLE_CLASS_NAME_EDEFAULT == null ? this.simpleClassName != null : !SIMPLE_CLASS_NAME_EDEFAULT.equals(this.simpleClassName);
            }
        }
        return super.eIsSet(featureID);
    }

    @Override
    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }
        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (className: ");
        result.append(this.className);
        result.append(", simpleClassName: ");
        result.append(this.simpleClassName);
        result.append(')');
        return result.toString();
    }
}

