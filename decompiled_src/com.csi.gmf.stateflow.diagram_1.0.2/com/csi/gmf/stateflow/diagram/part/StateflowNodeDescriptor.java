/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
import org.eclipse.emf.ecore.EObject;

public class StateflowNodeDescriptor {
    private EObject myModelElement;
    private int myVisualID;
    private String myType;

    public StateflowNodeDescriptor(EObject modelElement, int visualID) {
        this.myModelElement = modelElement;
        this.myVisualID = visualID;
    }

    public EObject getModelElement() {
        return this.myModelElement;
    }

    public int getVisualID() {
        return this.myVisualID;
    }

    public String getType() {
        if (this.myType == null) {
            this.myType = StateflowVisualIDRegistry.getType(this.getVisualID());
        }
        return this.myType;
    }
}

