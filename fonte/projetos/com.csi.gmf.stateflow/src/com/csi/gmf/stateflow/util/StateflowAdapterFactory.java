/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.csi.gmf.stateflow.util;

import com.csi.gmf.stateflow.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.csi.gmf.stateflow.StateflowPackage
 * @generated
 */
public class StateflowAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static StateflowPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateflowAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = StateflowPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateflowSwitch modelSwitch =
		new StateflowSwitch() {
			public Object caseExecutionFlowObject(ExecutionFlowObject object) {
				return createExecutionFlowObjectAdapter();
			}
			public Object caseFlowObject(FlowObject object) {
				return createFlowObjectAdapter();
			}
			public Object caseFlowObjectConnection(FlowObjectConnection object) {
				return createFlowObjectConnectionAdapter();
			}
			public Object caseState(State object) {
				return createStateAdapter();
			}
			public Object caseStateFlow(StateFlow object) {
				return createStateFlowAdapter();
			}
			public Object caseEventConnection(EventConnection object) {
				return createEventConnectionAdapter();
			}
			public Object caseStartPoint(StartPoint object) {
				return createStartPointAdapter();
			}
			public Object caseStartPointConnection(StartPointConnection object) {
				return createStartPointConnectionAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.csi.gmf.stateflow.ExecutionFlowObject <em>Execution Flow Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.csi.gmf.stateflow.ExecutionFlowObject
	 * @generated
	 */
	public Adapter createExecutionFlowObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.csi.gmf.stateflow.FlowObject <em>Flow Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.csi.gmf.stateflow.FlowObject
	 * @generated
	 */
	public Adapter createFlowObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.csi.gmf.stateflow.FlowObjectConnection <em>Flow Object Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.csi.gmf.stateflow.FlowObjectConnection
	 * @generated
	 */
	public Adapter createFlowObjectConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.csi.gmf.stateflow.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.csi.gmf.stateflow.State
	 * @generated
	 */
	public Adapter createStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.csi.gmf.stateflow.StateFlow <em>State Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.csi.gmf.stateflow.StateFlow
	 * @generated
	 */
	public Adapter createStateFlowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.csi.gmf.stateflow.EventConnection <em>Event Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.csi.gmf.stateflow.EventConnection
	 * @generated
	 */
	public Adapter createEventConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.csi.gmf.stateflow.StartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.csi.gmf.stateflow.StartPoint
	 * @generated
	 */
	public Adapter createStartPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.csi.gmf.stateflow.StartPointConnection <em>Start Point Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.csi.gmf.stateflow.StartPointConnection
	 * @generated
	 */
	public Adapter createStartPointConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //StateflowAdapterFactory
