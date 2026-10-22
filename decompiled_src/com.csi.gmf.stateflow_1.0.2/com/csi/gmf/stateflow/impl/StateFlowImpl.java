/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.common.notify.NotificationChain
 *  org.eclipse.emf.common.util.EList
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.InternalEObject
 *  org.eclipse.emf.ecore.impl.ENotificationImpl
 *  org.eclipse.emf.ecore.impl.EObjectImpl
 *  org.eclipse.emf.ecore.util.EObjectContainmentEList
 *  org.eclipse.emf.ecore.util.InternalEList
 */
package com.csi.gmf.stateflow.impl;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

public class StateFlowImpl
extends EObjectImpl
implements StateFlow {
    protected EList states;
    protected EList executionFlows;
    protected EList stateConnections;
    protected EList flowConnections;
    protected StartPoint startPoint;
    protected StartPointConnection startPointConnection;
    protected static final String ID_EDEFAULT = null;
    protected String id = ID_EDEFAULT;
    protected static final String DESCRIPTION_EDEFAULT = "";
    protected String description = "";
    protected static final boolean VOLATILE_EDEFAULT = false;
    protected boolean volatile_ = false;

    protected StateFlowImpl() {
    }

    protected EClass eStaticClass() {
        return StateflowPackage.Literals.STATE_FLOW;
    }

    @Override
    public EList getStates() {
        if (this.states == null) {
            this.states = new EObjectContainmentEList(State.class, (InternalEObject)this, 0);
        }
        return this.states;
    }

    @Override
    public EList getExecutionFlows() {
        if (this.executionFlows == null) {
            this.executionFlows = new EObjectContainmentEList(ExecutionFlowObject.class, (InternalEObject)this, 1);
        }
        return this.executionFlows;
    }

    @Override
    public EList getStateConnections() {
        if (this.stateConnections == null) {
            this.stateConnections = new EObjectContainmentEList(EventConnection.class, (InternalEObject)this, 2);
        }
        return this.stateConnections;
    }

    @Override
    public EList getFlowConnections() {
        if (this.flowConnections == null) {
            this.flowConnections = new EObjectContainmentEList(FlowObjectConnection.class, (InternalEObject)this, 3);
        }
        return this.flowConnections;
    }

    @Override
    public StartPoint getStartPoint() {
        return this.startPoint;
    }

    public NotificationChain basicSetStartPoint(StartPoint newStartPoint, NotificationChain msgs) {
        StartPoint oldStartPoint = this.startPoint;
        this.startPoint = newStartPoint;
        if (this.eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 4, (Object)oldStartPoint, (Object)newStartPoint);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add((Notification)notification);
            }
        }
        return msgs;
    }

    @Override
    public void setStartPoint(StartPoint newStartPoint) {
        if (newStartPoint != this.startPoint) {
            NotificationChain msgs = null;
            if (this.startPoint != null) {
                msgs = ((InternalEObject)this.startPoint).eInverseRemove((InternalEObject)this, -5, null, msgs);
            }
            if (newStartPoint != null) {
                msgs = ((InternalEObject)newStartPoint).eInverseAdd((InternalEObject)this, -5, null, msgs);
            }
            if ((msgs = this.basicSetStartPoint(newStartPoint, msgs)) != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, (Object)newStartPoint, (Object)newStartPoint));
        }
    }

    @Override
    public StartPointConnection getStartPointConnection() {
        return this.startPointConnection;
    }

    public NotificationChain basicSetStartPointConnection(StartPointConnection newStartPointConnection, NotificationChain msgs) {
        StartPointConnection oldStartPointConnection = this.startPointConnection;
        this.startPointConnection = newStartPointConnection;
        if (this.eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl((InternalEObject)this, 1, 5, (Object)oldStartPointConnection, (Object)newStartPointConnection);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add((Notification)notification);
            }
        }
        return msgs;
    }

    @Override
    public void setStartPointConnection(StartPointConnection newStartPointConnection) {
        if (newStartPointConnection != this.startPointConnection) {
            NotificationChain msgs = null;
            if (this.startPointConnection != null) {
                msgs = ((InternalEObject)this.startPointConnection).eInverseRemove((InternalEObject)this, -6, null, msgs);
            }
            if (newStartPointConnection != null) {
                msgs = ((InternalEObject)newStartPointConnection).eInverseAdd((InternalEObject)this, -6, null, msgs);
            }
            if ((msgs = this.basicSetStartPointConnection(newStartPointConnection, msgs)) != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 5, (Object)newStartPointConnection, (Object)newStartPointConnection));
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String newId) {
        String oldId = this.id;
        this.id = newId;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 6, (Object)oldId, (Object)this.id));
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String newDescription) {
        String oldDescription = this.description;
        this.description = newDescription;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 7, (Object)oldDescription, (Object)this.description));
        }
    }

    @Override
    public boolean isVolatile() {
        return this.volatile_;
    }

    @Override
    public void setVolatile(boolean newVolatile) {
        boolean oldVolatile = this.volatile_;
        this.volatile_ = newVolatile;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 8, oldVolatile, this.volatile_));
        }
    }

    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case 0: {
                return ((InternalEList)this.getStates()).basicRemove((Object)otherEnd, msgs);
            }
            case 1: {
                return ((InternalEList)this.getExecutionFlows()).basicRemove((Object)otherEnd, msgs);
            }
            case 2: {
                return ((InternalEList)this.getStateConnections()).basicRemove((Object)otherEnd, msgs);
            }
            case 3: {
                return ((InternalEList)this.getFlowConnections()).basicRemove((Object)otherEnd, msgs);
            }
            case 4: {
                return this.basicSetStartPoint(null, msgs);
            }
            case 5: {
                return this.basicSetStartPointConnection(null, msgs);
            }
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case 0: {
                return this.getStates();
            }
            case 1: {
                return this.getExecutionFlows();
            }
            case 2: {
                return this.getStateConnections();
            }
            case 3: {
                return this.getFlowConnections();
            }
            case 4: {
                return this.getStartPoint();
            }
            case 5: {
                return this.getStartPointConnection();
            }
            case 6: {
                return this.getId();
            }
            case 7: {
                return this.getDescription();
            }
            case 8: {
                return this.isVolatile() ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        return super.eGet(featureID, resolve, coreType);
    }

    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case 0: {
                this.getStates().clear();
                this.getStates().addAll((Collection)newValue);
                return;
            }
            case 1: {
                this.getExecutionFlows().clear();
                this.getExecutionFlows().addAll((Collection)newValue);
                return;
            }
            case 2: {
                this.getStateConnections().clear();
                this.getStateConnections().addAll((Collection)newValue);
                return;
            }
            case 3: {
                this.getFlowConnections().clear();
                this.getFlowConnections().addAll((Collection)newValue);
                return;
            }
            case 4: {
                this.setStartPoint((StartPoint)newValue);
                return;
            }
            case 5: {
                this.setStartPointConnection((StartPointConnection)newValue);
                return;
            }
            case 6: {
                this.setId((String)newValue);
                return;
            }
            case 7: {
                this.setDescription((String)newValue);
                return;
            }
            case 8: {
                this.setVolatile((Boolean)newValue);
                return;
            }
        }
        super.eSet(featureID, newValue);
    }

    public void eUnset(int featureID) {
        switch (featureID) {
            case 0: {
                this.getStates().clear();
                return;
            }
            case 1: {
                this.getExecutionFlows().clear();
                return;
            }
            case 2: {
                this.getStateConnections().clear();
                return;
            }
            case 3: {
                this.getFlowConnections().clear();
                return;
            }
            case 4: {
                this.setStartPoint(null);
                return;
            }
            case 5: {
                this.setStartPointConnection(null);
                return;
            }
            case 6: {
                this.setId(ID_EDEFAULT);
                return;
            }
            case 7: {
                this.setDescription(DESCRIPTION_EDEFAULT);
                return;
            }
            case 8: {
                this.setVolatile(false);
                return;
            }
        }
        super.eUnset(featureID);
    }

    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case 0: {
                return this.states != null && !this.states.isEmpty();
            }
            case 1: {
                return this.executionFlows != null && !this.executionFlows.isEmpty();
            }
            case 2: {
                return this.stateConnections != null && !this.stateConnections.isEmpty();
            }
            case 3: {
                return this.flowConnections != null && !this.flowConnections.isEmpty();
            }
            case 4: {
                return this.startPoint != null;
            }
            case 5: {
                return this.startPointConnection != null;
            }
            case 6: {
                return ID_EDEFAULT == null ? this.id != null : !ID_EDEFAULT.equals(this.id);
            }
            case 7: {
                return DESCRIPTION_EDEFAULT == null ? this.description != null : !DESCRIPTION_EDEFAULT.equals(this.description);
            }
            case 8: {
                return this.volatile_;
            }
        }
        return super.eIsSet(featureID);
    }

    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }
        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (id: ");
        result.append(this.id);
        result.append(", description: ");
        result.append(this.description);
        result.append(", volatile: ");
        result.append(this.volatile_);
        result.append(')');
        return result.toString();
    }
}

