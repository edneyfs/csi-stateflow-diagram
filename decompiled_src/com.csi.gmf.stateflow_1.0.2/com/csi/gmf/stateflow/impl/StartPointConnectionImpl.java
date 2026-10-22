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

import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateflowPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

public class StartPointConnectionImpl
extends EObjectImpl
implements StartPointConnection {
    protected StartPoint startPoint;
    protected State destinationState;
    protected static final String DESTINATION_STATE_ID_EDEFAULT = null;
    protected String destinationStateId = DESTINATION_STATE_ID_EDEFAULT;

    protected StartPointConnectionImpl() {
    }

    protected EClass eStaticClass() {
        return StateflowPackage.Literals.START_POINT_CONNECTION;
    }

    @Override
    public StartPoint getStartPoint() {
        if (this.startPoint != null && this.startPoint.eIsProxy()) {
            InternalEObject oldStartPoint = (InternalEObject)this.startPoint;
            this.startPoint = (StartPoint)this.eResolveProxy(oldStartPoint);
            if (this.startPoint != oldStartPoint && this.eNotificationRequired()) {
                this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 0, (Object)oldStartPoint, (Object)this.startPoint));
            }
        }
        return this.startPoint;
    }

    public StartPoint basicGetStartPoint() {
        return this.startPoint;
    }

    @Override
    public void setStartPoint(StartPoint newStartPoint) {
        StartPoint oldStartPoint = this.startPoint;
        this.startPoint = newStartPoint;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, (Object)oldStartPoint, (Object)this.startPoint));
        }
    }

    @Override
    public State getDestinationState() {
        if (this.destinationState != null && this.destinationState.eIsProxy()) {
            InternalEObject oldDestinationState = (InternalEObject)this.destinationState;
            this.destinationState = (State)this.eResolveProxy(oldDestinationState);
            if (this.destinationState != oldDestinationState && this.eNotificationRequired()) {
                this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 1, (Object)oldDestinationState, (Object)this.destinationState));
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
        this.destinationStateId = newDestinationState.getId();
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, (Object)oldDestinationState, (Object)this.destinationState));
        }
    }

    @Override
    public String getDestinationStateId() {
        return this.destinationStateId;
    }

    @Override
    public void setDestinationStateId(String newDestinationStateId) {
        String oldDestinationStateId = this.destinationStateId;
        this.destinationStateId = newDestinationStateId;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, (Object)oldDestinationStateId, (Object)this.destinationStateId));
        }
    }

    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case 0: {
                if (resolve) {
                    return this.getStartPoint();
                }
                return this.basicGetStartPoint();
            }
            case 1: {
                if (resolve) {
                    return this.getDestinationState();
                }
                return this.basicGetDestinationState();
            }
            case 2: {
                return this.getDestinationStateId();
            }
        }
        return super.eGet(featureID, resolve, coreType);
    }

    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case 0: {
                this.setStartPoint((StartPoint)newValue);
                return;
            }
            case 1: {
                this.setDestinationState((State)newValue);
                return;
            }
            case 2: {
                this.setDestinationStateId((String)newValue);
                return;
            }
        }
        super.eSet(featureID, newValue);
    }

    public void eUnset(int featureID) {
        switch (featureID) {
            case 0: {
                this.setStartPoint(null);
                return;
            }
            case 1: {
                this.setDestinationState(null);
                return;
            }
            case 2: {
                this.setDestinationStateId(DESTINATION_STATE_ID_EDEFAULT);
                return;
            }
        }
        super.eUnset(featureID);
    }

    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case 0: {
                return this.startPoint != null;
            }
            case 1: {
                return this.destinationState != null;
            }
            case 2: {
                return DESTINATION_STATE_ID_EDEFAULT == null ? this.destinationStateId != null : !DESTINATION_STATE_ID_EDEFAULT.equals(this.destinationStateId);
            }
        }
        return super.eIsSet(featureID);
    }

    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }
        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (destinationStateId: ");
        result.append(this.destinationStateId);
        result.append(')');
        return result.toString();
    }
}

