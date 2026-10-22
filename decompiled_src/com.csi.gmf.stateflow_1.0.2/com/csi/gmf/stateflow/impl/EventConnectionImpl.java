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

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateflowPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

public class EventConnectionImpl
extends EObjectImpl
implements EventConnection {
    protected static final Long SOURCE_PATH_KEY_EDEFAULT = null;
    protected Long sourcePathKey = SOURCE_PATH_KEY_EDEFAULT;
    protected static final Long DESTINATION_PATH_KEY_EDEFAULT = null;
    protected Long destinationPathKey = DESTINATION_PATH_KEY_EDEFAULT;
    protected static final String EVENT_ID_EDEFAULT = null;
    protected String eventID = EVENT_ID_EDEFAULT;
    protected State sourceState;
    protected ExecutionFlowObject destinationFlow;

    protected EventConnectionImpl() {
    }

    protected EClass eStaticClass() {
        return StateflowPackage.Literals.EVENT_CONNECTION;
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
    public String getEventID() {
        return this.eventID;
    }

    @Override
    public void setEventID(String newEventID) {
        String oldEventID = this.eventID;
        this.eventID = newEventID;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, (Object)oldEventID, (Object)this.eventID));
        }
    }

    @Override
    public State getSourceState() {
        if (this.sourceState != null && this.sourceState.eIsProxy()) {
            InternalEObject oldSourceState = (InternalEObject)this.sourceState;
            this.sourceState = (State)this.eResolveProxy(oldSourceState);
            if (this.sourceState != oldSourceState && this.eNotificationRequired()) {
                this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 3, (Object)oldSourceState, (Object)this.sourceState));
            }
        }
        return this.sourceState;
    }

    public State basicGetSourceState() {
        return this.sourceState;
    }

    @Override
    public void setSourceState(State newSourceState) {
        State oldSourceState = this.sourceState;
        this.sourceState = newSourceState;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, (Object)oldSourceState, (Object)this.sourceState));
        }
        if (newSourceState != null) {
            this.setSourcePathKey(newSourceState.getPathKey());
        }
    }

    @Override
    public ExecutionFlowObject getDestinationFlow() {
        if (this.destinationFlow != null && this.destinationFlow.eIsProxy()) {
            InternalEObject oldDestinationFlow = (InternalEObject)this.destinationFlow;
            this.destinationFlow = (ExecutionFlowObject)this.eResolveProxy(oldDestinationFlow);
            if (this.destinationFlow != oldDestinationFlow && this.eNotificationRequired()) {
                this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 4, (Object)oldDestinationFlow, (Object)this.destinationFlow));
            }
        }
        return this.destinationFlow;
    }

    public ExecutionFlowObject basicGetDestinationFlow() {
        return this.destinationFlow;
    }

    @Override
    public void setDestinationFlow(ExecutionFlowObject newDestinationFlow) {
        ExecutionFlowObject oldDestinationFlow = this.destinationFlow;
        this.destinationFlow = newDestinationFlow;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, (Object)oldDestinationFlow, (Object)this.destinationFlow));
        }
        if (newDestinationFlow != null) {
            this.setDestinationPathKey(newDestinationFlow.getPathKey());
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
                return this.getEventID();
            }
            case 3: {
                if (resolve) {
                    return this.getSourceState();
                }
                return this.basicGetSourceState();
            }
            case 4: {
                if (resolve) {
                    return this.getDestinationFlow();
                }
                return this.basicGetDestinationFlow();
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
                this.setEventID((String)newValue);
                return;
            }
            case 3: {
                this.setSourceState((State)newValue);
                return;
            }
            case 4: {
                this.setDestinationFlow((ExecutionFlowObject)newValue);
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
                this.setEventID(EVENT_ID_EDEFAULT);
                return;
            }
            case 3: {
                this.setSourceState(null);
                return;
            }
            case 4: {
                this.setDestinationFlow(null);
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
                return EVENT_ID_EDEFAULT == null ? this.eventID != null : !EVENT_ID_EDEFAULT.equals(this.eventID);
            }
            case 3: {
                return this.sourceState != null;
            }
            case 4: {
                return this.destinationFlow != null;
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
        result.append(", eventID: ");
        result.append(this.eventID);
        result.append(')');
        return result.toString();
    }
}

