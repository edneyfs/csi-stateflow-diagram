/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.InternalEObject
 *  org.eclipse.emf.ecore.impl.ENotificationImpl
 */
package com.csi.gmf.stateflow.impl;

import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.impl.FlowObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

public class StateImpl
extends FlowObjectImpl
implements State {
    private static String STATE_TYPE = "1001";
    protected static final String ID_EDEFAULT = null;
    protected String id = ID_EDEFAULT;
    protected static final String DESCRIPTION_EDEFAULT = null;
    protected String description = DESCRIPTION_EDEFAULT;

    protected StateImpl() {
    }

    @Override
    protected EClass eStaticClass() {
        return StateflowPackage.Literals.STATE;
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
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, (Object)oldId, (Object)this.id));
        }
        if (newId != null) {
            this.setPathKey(StateImpl.generatePathKey(newId));
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
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, (Object)oldDescription, (Object)this.description));
        }
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case 1: {
                return this.getId();
            }
            case 2: {
                return this.getDescription();
            }
        }
        return super.eGet(featureID, resolve, coreType);
    }

    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case 1: {
                this.setId((String)newValue);
                return;
            }
            case 2: {
                this.setDescription((String)newValue);
                return;
            }
        }
        super.eSet(featureID, newValue);
    }

    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case 1: {
                this.setId(ID_EDEFAULT);
                return;
            }
            case 2: {
                this.setDescription(DESCRIPTION_EDEFAULT);
                return;
            }
        }
        super.eUnset(featureID);
    }

    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case 1: {
                return ID_EDEFAULT == null ? this.id != null : !ID_EDEFAULT.equals(this.id);
            }
            case 2: {
                return DESCRIPTION_EDEFAULT == null ? this.description != null : !DESCRIPTION_EDEFAULT.equals(this.description);
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
        result.append(" (id: ");
        result.append(this.id);
        result.append(", description: ");
        result.append(this.description);
        result.append(')');
        return result.toString();
    }

    private static long generatePathKey(String str) {
        long pathKey = (String.valueOf(str) + STATE_TYPE).hashCode();
        if (pathKey < 0L) {
            pathKey *= -1L;
        }
        return pathKey;
    }
}

