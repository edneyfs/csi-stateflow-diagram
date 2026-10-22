/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.util.EList
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EObject
 */
package com.csi.gmf.executionflow.util;

import com.csi.gmf.executionflow.ExecutableFlowObject;
import com.csi.gmf.executionflow.ExecutionFlow;
import com.csi.gmf.executionflow.ExecutionFlowObject;
import com.csi.gmf.executionflow.ExecutionFlowResult;
import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.FlowObject;
import com.csi.gmf.executionflow.FlowObjectConnection;
import com.csi.gmf.executionflow.SourceConnectable;
import com.csi.gmf.executionflow.StartPoint;
import com.csi.gmf.executionflow.StartPointConnection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class ExecutionflowSwitch {
    protected static ExecutionflowPackage modelPackage;

    public ExecutionflowSwitch() {
        if (modelPackage == null) {
            modelPackage = ExecutionflowPackage.eINSTANCE;
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
                ExecutableFlowObject executableFlowObject = (ExecutableFlowObject)theEObject;
                Object result = this.caseExecutableFlowObject(executableFlowObject);
                if (result == null) {
                    result = this.caseSourceConnectable(executableFlowObject);
                }
                if (result == null) {
                    result = this.caseFlowObject(executableFlowObject);
                }
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 1: {
                ExecutionFlow executionFlow = (ExecutionFlow)theEObject;
                Object result = this.caseExecutionFlow(executionFlow);
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 2: {
                ExecutionFlowObject executionFlowObject = (ExecutionFlowObject)theEObject;
                Object result = this.caseExecutionFlowObject(executionFlowObject);
                if (result == null) {
                    result = this.caseSourceConnectable(executionFlowObject);
                }
                if (result == null) {
                    result = this.caseFlowObject(executionFlowObject);
                }
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 3: {
                ExecutionFlowResult executionFlowResult = (ExecutionFlowResult)theEObject;
                Object result = this.caseExecutionFlowResult(executionFlowResult);
                if (result == null) {
                    result = this.caseFlowObject(executionFlowResult);
                }
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 4: {
                FlowObject flowObject = (FlowObject)theEObject;
                Object result = this.caseFlowObject(flowObject);
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 5: {
                FlowObjectConnection flowObjectConnection = (FlowObjectConnection)theEObject;
                Object result = this.caseFlowObjectConnection(flowObjectConnection);
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 6: {
                SourceConnectable sourceConnectable = (SourceConnectable)theEObject;
                Object result = this.caseSourceConnectable(sourceConnectable);
                if (result == null) {
                    result = this.caseFlowObject(sourceConnectable);
                }
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 7: {
                StartPoint startPoint = (StartPoint)theEObject;
                Object result = this.caseStartPoint(startPoint);
                if (result == null) {
                    result = this.defaultCase(theEObject);
                }
                return result;
            }
            case 8: {
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

    public Object caseExecutableFlowObject(ExecutableFlowObject object) {
        return null;
    }

    public Object caseExecutionFlow(ExecutionFlow object) {
        return null;
    }

    public Object caseExecutionFlowObject(ExecutionFlowObject object) {
        return null;
    }

    public Object caseExecutionFlowResult(ExecutionFlowResult object) {
        return null;
    }

    public Object caseFlowObject(FlowObject object) {
        return null;
    }

    public Object caseFlowObjectConnection(FlowObjectConnection object) {
        return null;
    }

    public Object caseSourceConnectable(SourceConnectable object) {
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

