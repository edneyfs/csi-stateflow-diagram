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

import com.csi.gmf.stateflow.FlowObject;
import com.csi.gmf.stateflow.StateflowPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

public class FlowObjectImpl
extends EObjectImpl
implements FlowObject {
    protected static final Long PATH_KEY_EDEFAULT = null;
    protected Long pathKey = PATH_KEY_EDEFAULT;

    protected FlowObjectImpl() {
    }

    protected EClass eStaticClass() {
        return StateflowPackage.Literals.FLOW_OBJECT;
    }

    @Override
    public Long getPathKey() {
        return this.pathKey;
    }

    @Override
    public void setPathKey(Long newPathKey) {
        Long oldPathKey = this.pathKey;
        this.pathKey = newPathKey;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 0, (Object)oldPathKey, (Object)this.pathKey));
        }
    }

    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case 0: {
                return this.getPathKey();
            }
        }
        return super.eGet(featureID, resolve, coreType);
    }

    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case 0: {
                this.setPathKey((Long)newValue);
                return;
            }
        }
        super.eSet(featureID, newValue);
    }

    public void eUnset(int featureID) {
        switch (featureID) {
            case 0: {
                this.setPathKey(PATH_KEY_EDEFAULT);
                return;
            }
        }
        super.eUnset(featureID);
    }

    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case 0: {
                return PATH_KEY_EDEFAULT == null ? this.pathKey != null : !PATH_KEY_EDEFAULT.equals(this.pathKey);
            }
        }
        return super.eIsSet(featureID);
    }

    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }
        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (pathKey: ");
        result.append(this.pathKey);
        result.append(')');
        return result.toString();
    }
}

