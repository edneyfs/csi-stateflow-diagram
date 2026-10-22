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
import com.csi.gmf.executionflow.SourceConnectable;
import com.csi.gmf.executionflow.StartPoint;
import com.csi.gmf.executionflow.StartPointConnection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

public class StartPointConnectionImpl
extends EObjectImpl
implements StartPointConnection {
    protected StartPoint startPoint;
    protected SourceConnectable destinationObject;
    protected static final Long DESTINATION_OBJECT_PATH_KEY_EDEFAULT = null;
    protected Long destinationObjectPathKey = DESTINATION_OBJECT_PATH_KEY_EDEFAULT;

    protected StartPointConnectionImpl() {
    }

    protected EClass eStaticClass() {
        return ExecutionflowPackage.Literals.START_POINT_CONNECTION;
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
    public SourceConnectable getDestinationObject() {
        if (this.destinationObject != null && this.destinationObject.eIsProxy()) {
            InternalEObject oldDestinationObject = (InternalEObject)this.destinationObject;
            this.destinationObject = (SourceConnectable)this.eResolveProxy(oldDestinationObject);
            if (this.destinationObject != oldDestinationObject && this.eNotificationRequired()) {
                this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 9, 1, (Object)oldDestinationObject, (Object)this.destinationObject));
            }
        }
        return this.destinationObject;
    }

    public SourceConnectable basicGetDestinationObject() {
        return this.destinationObject;
    }

    @Override
    public void setDestinationObject(SourceConnectable newDestinationObject) {
        SourceConnectable oldDestinationObject = this.destinationObject;
        this.destinationObject = newDestinationObject;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, (Object)oldDestinationObject, (Object)this.destinationObject));
        }
        if (newDestinationObject != null) {
            this.setDestinationObjectPathKey(newDestinationObject.getPathKey());
        }
    }

    @Override
    public Long getDestinationObjectPathKey() {
        return this.destinationObjectPathKey;
    }

    @Override
    public void setDestinationObjectPathKey(Long newDestinationObjectPathKey) {
        Long oldDestinationObjectPathKey = this.destinationObjectPathKey;
        this.destinationObjectPathKey = newDestinationObjectPathKey;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, (Object)oldDestinationObjectPathKey, (Object)this.destinationObjectPathKey));
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
                    return this.getDestinationObject();
                }
                return this.basicGetDestinationObject();
            }
            case 2: {
                return this.getDestinationObjectPathKey();
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
                this.setDestinationObject((SourceConnectable)newValue);
                return;
            }
            case 2: {
                this.setDestinationObjectPathKey((Long)newValue);
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
                this.setDestinationObject(null);
                return;
            }
            case 2: {
                this.setDestinationObjectPathKey(DESTINATION_OBJECT_PATH_KEY_EDEFAULT);
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
                return this.destinationObject != null;
            }
            case 2: {
                return DESTINATION_OBJECT_PATH_KEY_EDEFAULT == null ? this.destinationObjectPathKey != null : !DESTINATION_OBJECT_PATH_KEY_EDEFAULT.equals(this.destinationObjectPathKey);
            }
        }
        return super.eIsSet(featureID);
    }

    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }
        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (destinationObjectPathKey: ");
        result.append(this.destinationObjectPathKey);
        result.append(')');
        return result.toString();
    }
}

