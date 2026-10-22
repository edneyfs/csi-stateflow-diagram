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
package com.csi.gmf.executionflow.impl;

import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.FlowObject;
import com.csi.gmf.executionflow.FlowObjectConnection;
import com.csi.gmf.executionflow.SourceConnectable;
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
    protected SourceConnectable sourceObject;
    protected FlowObject destinationObject;

    protected FlowObjectConnectionImpl() {
    }

    protected EClass eStaticClass() {
        return ExecutionflowPackage.Literals.FLOW_OBJECT_CONNECTION;
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
    public SourceConnectable getSourceObject() {
        if (this.sourceObject != null && this.sourceObject.eIsProxy()) {
            InternalEObject oldSourceObject = (InternalEObject)this.sourceObject;
            this.sourceObject = (SourceConnectable)this.eResolveProxy(oldSourceObject);
            if (this.sourceObject != oldSourceObject && this.eNotificationRequired()) {
                this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 3, (Object)oldSourceObject, (Object)this.sourceObject));
            }
        }
        return this.sourceObject;
    }

    public SourceConnectable basicGetSourceObject() {
        return this.sourceObject;
    }

    @Override
    public void setSourceObject(SourceConnectable newSourceObject) {
        SourceConnectable oldSourceObject = this.sourceObject;
        this.sourceObject = newSourceObject;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, (Object)oldSourceObject, (Object)this.sourceObject));
        }
        if (newSourceObject != null) {
            this.setSourcePathKey(newSourceObject.getPathKey());
        }
    }

    @Override
    public FlowObject getDestinationObject() {
        if (this.destinationObject != null && this.destinationObject.eIsProxy()) {
            InternalEObject oldDestinationObject = (InternalEObject)this.destinationObject;
            this.destinationObject = (FlowObject)this.eResolveProxy(oldDestinationObject);
            if (this.destinationObject != oldDestinationObject && this.eNotificationRequired()) {
                this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 4, (Object)oldDestinationObject, (Object)this.destinationObject));
            }
        }
        return this.destinationObject;
    }

    public FlowObject basicGetDestinationObject() {
        return this.destinationObject;
    }

    @Override
    public void setDestinationObject(FlowObject newDestinationObject) {
        FlowObject oldDestinationObject = this.destinationObject;
        this.destinationObject = newDestinationObject;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, (Object)oldDestinationObject, (Object)this.destinationObject));
        }
        if (newDestinationObject != null) {
            this.setDestinationPathKey(newDestinationObject.getPathKey());
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
                    return this.getSourceObject();
                }
                return this.basicGetSourceObject();
            }
            case 4: {
                if (resolve) {
                    return this.getDestinationObject();
                }
                return this.basicGetDestinationObject();
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
                this.setSourceObject((SourceConnectable)newValue);
                return;
            }
            case 4: {
                this.setDestinationObject((FlowObject)newValue);
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
                this.setSourceObject(null);
                return;
            }
            case 4: {
                this.setDestinationObject(null);
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
                return this.sourceObject != null;
            }
            case 4: {
                return this.destinationObject != null;
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

