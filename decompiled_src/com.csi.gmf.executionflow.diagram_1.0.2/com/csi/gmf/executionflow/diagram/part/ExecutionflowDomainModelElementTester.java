/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionflowPackage
 *  org.eclipse.core.expressions.PropertyTester
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.ExecutionflowPackage;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class ExecutionflowDomainModelElementTester
extends PropertyTester {
    public boolean test(Object receiver, String method, Object[] args, Object expectedValue) {
        if (!(receiver instanceof EObject)) {
            return false;
        }
        EObject eObject = (EObject)receiver;
        EClass eClass = eObject.eClass();
        if (eClass == ExecutionflowPackage.eINSTANCE.getExecutableFlowObject()) {
            return true;
        }
        if (eClass == ExecutionflowPackage.eINSTANCE.getExecutionFlow()) {
            return true;
        }
        if (eClass == ExecutionflowPackage.eINSTANCE.getExecutionFlowObject()) {
            return true;
        }
        if (eClass == ExecutionflowPackage.eINSTANCE.getExecutionFlowResult()) {
            return true;
        }
        if (eClass == ExecutionflowPackage.eINSTANCE.getFlowObject()) {
            return true;
        }
        if (eClass == ExecutionflowPackage.eINSTANCE.getFlowObjectConnection()) {
            return true;
        }
        if (eClass == ExecutionflowPackage.eINSTANCE.getSourceConnectable()) {
            return true;
        }
        if (eClass == ExecutionflowPackage.eINSTANCE.getStartPoint()) {
            return true;
        }
        return eClass == ExecutionflowPackage.eINSTANCE.getStartPointConnection();
    }
}

