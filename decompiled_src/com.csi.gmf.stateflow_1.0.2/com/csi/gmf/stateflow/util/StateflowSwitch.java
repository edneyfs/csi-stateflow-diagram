/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.util.EList
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.stateflow.util;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.FlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class StateflowSwitch {
    protected static StateflowPackage modelPackage;

    public StateflowSwitch() {
        if (modelPackage == null) {
            modelPackage = StateflowPackage.eINSTANCE;
        }
    }

    public Object doSwitch(EObject theEObject) {
        return this.doSwitch(theEObject.eClass(), theEObject);
    }

    protected Object doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return this.doSwitch(theEClass.getClassifierID(), theEObject);
        }
        EList eSuperTypes = theEClass.getESuperTypes();
        return eSuperTypes.isEmpty() ? this.defaultCase(theEObject) : this.doSwitch((EClass)eSuperTypes.get(0), theEObject);
    }

    protected Object doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case 0: {
                ExecutionFlowObject executionFlowObject = (ExecutionFlowObject)theEObject;
                Object result = this.caseExecutionFlowObject(executionFlowObject);
                if (result == null) {
                    result = this.caseFlowObject(executionFlowObject);
                }
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 1: {
                FlowObject flowObject = (FlowObject)theEObject;
                Object result = this.caseFlowObject(flowObject);
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 2: {
                FlowObjectConnection flowObjectConnection = (FlowObjectConnection)theEObject;
                Object result = this.caseFlowObjectConnection(flowObjectConnection);
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 3: {
                State state = (State)theEObject;
                Object result = this.caseState(state);
                if (result == null) {
                    result = this.caseFlowObject(state);
                }
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 4: {
                StateFlow stateFlow = (StateFlow)theEObject;
                Object result = this.caseStateFlow(stateFlow);
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 5: {
                EventConnection eventConnection = (EventConnection)theEObject;
                Object result = this.caseEventConnection(eventConnection);
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 6: {
                StartPoint startPoint = (StartPoint)theEObject;
                Object result = this.caseStartPoint(startPoint);
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 7: {
                StartPointConnection startPointConnection = (StartPointConnection)theEObject;
                Object result = this.caseStartPointConnection(startPointConnection);
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
        }
        return this.defaultCase(theEObject);
    }

    public Object caseExecutionFlowObject(ExecutionFlowObject object) {
        return null;
    }

    public Object caseFlowObject(FlowObject object) {
        return null;
    }

    public Object caseFlowObjectConnection(FlowObjectConnection object) {
        return null;
    }

    public Object caseState(State object) {
        return null;
    }

    public Object caseStateFlow(StateFlow object) {
        return null;
    }

    public Object caseEventConnection(EventConnection object) {
        return null;
    }

    public Object caseStartPoint(StartPoint object) {
        return null;
    }

    public Object caseStartPointConnection(StartPointConnection object) {
        return null;
    }

    public Object defaultCase(EObject object) {
        return null;
    }
}

