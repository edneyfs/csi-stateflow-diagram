/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.InternalEObject
 *  org.eclipse.emf.ecore.impl.ENotificationImpl
 *  org.eclipse.emf.ecore.impl.EObjectImpl
 */
package com.csi.gmf.stateflow.impl;

import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateflowPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

public class FlowObjectConnectionImpl
extends EObjectImpl
implements FlowObjectConnection {
    protected static final Long SOURCE_PATH_KEY_EDEFAULT = null;
    protected Long sourcePathKey = SOURCE_PATH_KEY_EDEFAULT;
    protected static final Long DESTINATION_PATH_KEY_EDEFAULT = null;
    protected Long destinationPathKey = DESTINATION_PATH_KEY_EDEFAULT;
    protected static final String EXECUTION_FLOW_RESULT_VALUE_EDEFAULT = null;
    protected String executionFlowResultValue = EXECUTION_FLOW_RESULT_VALUE_EDEFAULT;
    protected ExecutionFlowObject sourceFlow;
    protected State destinationState;

    protected FlowObjectConnectionImpl() {
    }

    protected EClass eStaticClass() {
        return StateflowPackage.Literals.FLOW_OBJECT_CONNECTION;
    }

    @Override
    public Long getSourcePathKey() {
        return this.sourcePathKey;
    }

    @Override
    public void setSourcePathKey(Long newSourcePathKey) {
        Long oldSourcePathKey = this.sourcePathKey;
        this.sourcePathKey = newSourcePathKey;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, (Object)oldSourcePathKey, (Object)this.sourcePathKey));
        }
    }

    @Override
    public Long getDestinationPathKey() {
        return this.destinationPathKey;
    }

    @Override
    public void setDestinationPathKey(Long newDestinationPathKey) {
        Long oldDestinationPathKey = this.destinationPathKey;
        this.destinationPathKey = newDestinationPathKey;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, (Object)oldDestinationPathKey, (Object)this.destinationPathKey));
        }
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
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, (Object)oldExecutionFlowResultValue, (Object)this.executionFlowResultValue));
        }
    }

    @Override
    public ExecutionFlowObject getSourceFlow() {
        if (this.sourceFlow != null && this.sourceFlow.eIsProxy()) {
            InternalEObject oldSourceFlow = (InternalEObject)this.sourceFlow;
            this.sourceFlow = (ExecutionFlowObject)this.eResolveProxy(oldSourceFlow);
            if (this.sourceFlow != oldSourceFlow && this.eNotificationRequired()) {
                this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 3, (Object)oldSourceFlow, (Object)this.sourceFlow));
            }
        }
        return this.sourceFlow;
    }

    public ExecutionFlowObject basicGetSourceFlow() {
        return this.sourceFlow;
    }

    @Override
    public void setSourceFlow(ExecutionFlowObject newSourceFlow) {
        ExecutionFlowObject oldSourceFlow = this.sourceFlow;
        this.sourceFlow = newSourceFlow;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, (Object)oldSourceFlow, (Object)this.sourceFlow));
        }
        if (newSourceFlow != null) {
            this.setSourcePathKey(newSourceFlow.getPathKey());
        }
    }

    @Override
    public State getDestinationState() {
        if (this.destinationState != null && this.destinationState.eIsProxy()) {
            InternalEObject oldDestinationState = (InternalEObject)this.destinationState;
            this.destinationState = (State)this.eResolveProxy(oldDestinationState);
            if (this.destinationState != oldDestinationState && this.eNotificationRequired()) {
                this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 4, (Object)oldDestinationState, (Object)this.destinationState));
            }
        }
        return this.destinationState;
    }

    public State basicGetDestinationState() {
        return this.destinationState;
    }

    @Override
    public void setDestinationState(State newDestinationState) {
        State oldDestinationState = this.destinationState;
        this.destinationState = newDestinationState;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, (Object)oldDestinationState, (Object)this.destinationState));
        }
        if (newDestinationState != null) {
            this.setDestinationPathKey(newDestinationState.getPathKey());
        }
    }

    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case 0: {
                return this.getSourcePathKey();
            }
            case 1: {
                return this.getDestinationPathKey();
            }
            case 2: {
                return this.getExecutionFlowResultValue();
            }
            case 3: {
                if (resolve) {
                    return this.getSourceFlow();
                }
                return this.basicGetSourceFlow();
            }
            case 4: {
                if (resolve) {
                    return this.getDestinationState();
                }
                return this.basicGetDestinationState();
            }
        }
        return super.eGet(featureID, resolve, coreType);
    }

    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case 0: {
                this.setSourcePathKey((Long)newValue);
                return;
            }
            case 1: {
                this.setDestinationPathKey((Long)newValue);
                return;
            }
            case 2: {
                this.setExecutionFlowResultValue((String)newValue);
                return;
            }
            case 3: {
                this.setSourceFlow((ExecutionFlowObject)newValue);
                return;
            }
            case 4: {
                this.setDestinationState((State)newValue);
                return;
            }
        }
        super.eSet(featureID, newValue);
    }

    public void eUnset(int featureID) {
        switch (featureID) {
            case 0: {
                this.setSourcePathKey(SOURCE_PATH_KEY_EDEFAULT);
                return;
            }
            case 1: {
                this.setDestinationPathKey(DESTINATION_PATH_KEY_EDEFAULT);
                return;
            }
            case 2: {
                this.setExecutionFlowResultValue(EXECUTION_FLOW_RESULT_VALUE_EDEFAULT);
                return;
            }
            case 3: {
                this.setSourceFlow(null);
                return;
            }
            case 4: {
                this.setDestinationState(null);
                return;
            }
        }
        super.eUnset(featureID);
    }

    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case 0: {
                return SOURCE_PATH_KEY_EDEFAULT == null ? this.sourcePathKey != null : !SOURCE_PATH_KEY_EDEFAULT.equals(this.sourcePathKey);
            }
            case 1: {
                return DESTINATION_PATH_KEY_EDEFAULT == null ? this.destinationPathKey != null : !DESTINATION_PATH_KEY_EDEFAULT.equals(this.destinationPathKey);
            }
            case 2: {
                return EXECUTION_FLOW_RESULT_VALUE_EDEFAULT == null ? this.executionFlowResultValue != null : !EXECUTION_FLOW_RESULT_VALUE_EDEFAULT.equals(this.executionFlowResultValue);
            }
            case 3: {
                return this.sourceFlow != null;
            }
            case 4: {
                return this.destinationState != null;
            }
        }
        return super.eIsSet(featureID);
    }

    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }
        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (sourcePathKey: ");
        result.append(this.sourcePathKey);
        result.append(", destinationPathKey: ");
        result.append(this.destinationPathKey);
        result.append(", executionFlowResultValue: ");
        result.append(this.executionFlowResultValue);
        result.append(')');
        return result.toString();
    }
}

