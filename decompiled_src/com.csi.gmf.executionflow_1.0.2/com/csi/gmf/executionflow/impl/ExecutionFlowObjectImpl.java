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

import com.csi.gmf.executionflow.ExecutionFlowObject;
import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.impl.SourceConnectableImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

public class ExecutionFlowObjectImpl
extends SourceConnectableImpl
implements ExecutionFlowObject {
    protected static final String FILE_NAME_EDEFAULT = null;
    protected String fileName = FILE_NAME_EDEFAULT;
    protected static final String FILE_PATH_EDEFAULT = null;
    protected String filePath = FILE_PATH_EDEFAULT;
    protected static final String ID_EDEFAULT = null;
    protected String id = ID_EDEFAULT;
    protected static final String DESCRIPTION_EDEFAULT = null;
    protected String description = DESCRIPTION_EDEFAULT;

    protected ExecutionFlowObjectImpl() {
    }

    @Override
    protected EClass eStaticClass() {
        return ExecutionflowPackage.Literals.EXECUTION_FLOW_OBJECT;
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public void setFileName(String newFileName) {
        String oldFileName = this.fileName;
        this.fileName = newFileName;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 1, (Object)oldFileName, (Object)this.fileName));
        }
    }

    @Override
    public String getFilePath() {
        return this.filePath;
    }

    @Override
    public void setFilePath(String newFilePath) {
        String oldFilePath = this.filePath;
        this.filePath = newFilePath;
        if (this.eNotificationRequired()) {
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 2, (Object)oldFilePath, (Object)this.filePath));
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
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 3, (Object)oldId, (Object)this.id));
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
            this.eNotify((Notification)new ENotificationImpl((InternalEObject)this, 1, 4, (Object)oldDescription, (Object)this.description));
        }
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case 1: {
                return this.getFileName();
            }
            case 2: {
                return this.getFilePath();
            }
            case 3: {
                return this.getId();
            }
            case 4: {
                return this.getDescription();
            }
        }
        return super.eGet(featureID, resolve, coreType);
    }

    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case 1: {
                this.setFileName((String)newValue);
                return;
            }
            case 2: {
                this.setFilePath((String)newValue);
                return;
            }
            case 3: {
                this.setId((String)newValue);
                return;
            }
            case 4: {
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
                this.setFileName(FILE_NAME_EDEFAULT);
                return;
            }
            case 2: {
                this.setFilePath(FILE_PATH_EDEFAULT);
                return;
            }
            case 3: {
                this.setId(ID_EDEFAULT);
                return;
            }
            case 4: {
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
                return FILE_NAME_EDEFAULT == null ? this.fileName != null : !FILE_NAME_EDEFAULT.equals(this.fileName);
            }
            case 2: {
                return FILE_PATH_EDEFAULT == null ? this.filePath != null : !FILE_PATH_EDEFAULT.equals(this.filePath);
            }
            case 3: {
                return ID_EDEFAULT == null ? this.id != null : !ID_EDEFAULT.equals(this.id);
            }
            case 4: {
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
        result.append(" (fileName: ");
        result.append(this.fileName);
        result.append(", filePath: ");
        result.append(this.filePath);
        result.append(", id: ");
        result.append(this.id);
        result.append(", description: ");
        result.append(this.description);
        result.append(')');
        return result.toString();
    }
}

