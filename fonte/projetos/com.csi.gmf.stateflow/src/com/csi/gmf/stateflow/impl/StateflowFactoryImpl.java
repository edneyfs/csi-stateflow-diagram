/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.csi.gmf.stateflow.impl;

import com.csi.gmf.stateflow.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StateflowFactoryImpl extends EFactoryImpl implements StateflowFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StateflowFactory init() {
		try {
			StateflowFactory theStateflowFactory = (StateflowFactory)EPackage.Registry.INSTANCE.getEFactory("http:///com/csi/gmf/stateflow.ecore"); 
			if (theStateflowFactory != null) {
				return theStateflowFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StateflowFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateflowFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case StateflowPackage.EXECUTION_FLOW_OBJECT: return createExecutionFlowObject();
			case StateflowPackage.FLOW_OBJECT: return createFlowObject();
			case StateflowPackage.FLOW_OBJECT_CONNECTION: return createFlowObjectConnection();
			case StateflowPackage.STATE: return createState();
			case StateflowPackage.STATE_FLOW: return createStateFlow();
			case StateflowPackage.EVENT_CONNECTION: return createEventConnection();
			case StateflowPackage.START_POINT: return createStartPoint();
			case StateflowPackage.START_POINT_CONNECTION: return createStartPointConnection();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionFlowObject createExecutionFlowObject() {
		ExecutionFlowObjectImpl executionFlowObject = new ExecutionFlowObjectImpl();
		return executionFlowObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowObject createFlowObject() {
		FlowObjectImpl flowObject = new FlowObjectImpl();
		return flowObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowObjectConnection createFlowObjectConnection() {
		FlowObjectConnectionImpl flowObjectConnection = new FlowObjectConnectionImpl();
		return flowObjectConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateFlow createStateFlow() {
		StateFlowImpl stateFlow = new StateFlowImpl();
		return stateFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventConnection createEventConnection() {
		EventConnectionImpl eventConnection = new EventConnectionImpl();
		return eventConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartPoint createStartPoint() {
		StartPointImpl startPoint = new StartPointImpl();
		return startPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartPointConnection createStartPointConnection() {
		StartPointConnectionImpl startPointConnection = new StartPointConnectionImpl();
		return startPointConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateflowPackage getStateflowPackage() {
		return (StateflowPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static StateflowPackage getPackage() {
		return StateflowPackage.eINSTANCE;
	}

} //StateflowFactoryImpl
