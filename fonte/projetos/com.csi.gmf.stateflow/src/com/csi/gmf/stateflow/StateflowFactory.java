/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.csi.gmf.stateflow;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.csi.gmf.stateflow.StateflowPackage
 * @generated
 */
public interface StateflowFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StateflowFactory eINSTANCE = com.csi.gmf.stateflow.impl.StateflowFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Execution Flow Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Flow Object</em>'.
	 * @generated
	 */
	ExecutionFlowObject createExecutionFlowObject();

	/**
	 * Returns a new object of class '<em>Flow Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Flow Object</em>'.
	 * @generated
	 */
	FlowObject createFlowObject();

	/**
	 * Returns a new object of class '<em>Flow Object Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Flow Object Connection</em>'.
	 * @generated
	 */
	FlowObjectConnection createFlowObjectConnection();

	/**
	 * Returns a new object of class '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State</em>'.
	 * @generated
	 */
	State createState();

	/**
	 * Returns a new object of class '<em>State Flow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State Flow</em>'.
	 * @generated
	 */
	StateFlow createStateFlow();

	/**
	 * Returns a new object of class '<em>Event Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event Connection</em>'.
	 * @generated
	 */
	EventConnection createEventConnection();

	/**
	 * Returns a new object of class '<em>Start Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Start Point</em>'.
	 * @generated
	 */
	StartPoint createStartPoint();

	/**
	 * Returns a new object of class '<em>Start Point Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Start Point Connection</em>'.
	 * @generated
	 */
	StartPointConnection createStartPointConnection();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StateflowPackage getStateflowPackage();

} //StateflowFactory
