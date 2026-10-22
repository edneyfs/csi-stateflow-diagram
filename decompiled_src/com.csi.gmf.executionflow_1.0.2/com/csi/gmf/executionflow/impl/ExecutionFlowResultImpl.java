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

import com.csi.gmf.executionflow.ExecutionFlowResult;
import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.impl.FlowObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

public class ExecutionFlowResultImpl
extends FlowObjectImpl
implements ExecutionFlowResult {
    protected static final String EXECUTION_FLOW_RESULT_VALUE_EDEFAULT = "";
    protected String executionFlowResultValue = "";

    protected ExecutionFlowResultImpl() {
    }

    @Override
    protected EClass eStaticClass() {
        return ExecutionflowPackage.Literals.EXECUTION_FLOW_RESULT;
    }

    @Override
    public String getExecutionFlowResultValue() {
        return this.executionFlowResultValue;
    }

    @Override
    public void setExecutionFlowResultValue(String newExecutionFlowResultValue) {
        String oldExecutionFlowResultValue = this.executionFlowResultValue;
        this.executionFlowResultValue = newExecutionFlowResultValue;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, (Object)oldExecutionFlowResultValue, (Object)this.executionFlowResultValue));
        }
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case 1: {
                return this.getExecutionFlowResultValue();
            }
        }
        return super.eGet(featureID, resolve, coreType);
    }

    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case 1: {
                this.setExecutionFlowResultValue((String)newValue);
                return;
            }
        }
        super.eSet(featureID, newValue);
    }

    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case 1: {
                this.setExecutionFlowResultValue(EXECUTION_FLOW_RESULT_VALUE_EDEFAULT);
                return;
            }
        }
        super.eUnset(featureID);
    }

    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case 1: {
                return EXECUTION_FLOW_RESULT_VALUE_EDEFAULT == null ? this.executionFlowResultValue != null : !EXECUTION_FLOW_RESULT_VALUE_EDEFAULT.equals(this.executionFlowResultValue);
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
        result.append(" (executionFlowResultValue: ");
        result.append(this.executionFlowResultValue);
        result.append(')');
        return result.toString();
    }
}

