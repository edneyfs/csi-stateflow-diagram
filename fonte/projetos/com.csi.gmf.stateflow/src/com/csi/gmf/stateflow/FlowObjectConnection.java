/**
 * 
 */
package com.csi.gmf.stateflow;

import org.eclipse.emf.ecore.EObject;

/**
 * @author gustav.monteiro
 * @model
 */
public interface FlowObjectConnection extends EObject {
	
	/**
	 * @model
	 */
	Long getSourcePathKey();

	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.FlowObjectConnection#getSourcePathKey <em>Source Path Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Path Key</em>' attribute.
	 * @see #getSourcePathKey()
	 * @generated
	 */
	void setSourcePathKey(Long value);

	/**
	 * @model 
	 */
	Long getDestinationPathKey();
	
	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.FlowObjectConnection#getDestinationPathKey <em>Destination Path Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination Path Key</em>' attribute.
	 * @see #getDestinationPathKey()
	 * @generated
	 */
	void setDestinationPathKey(Long value);

	/**
	 * @model 
	 */
	String getExecutionFlowResultValue();

	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.FlowObjectConnection#getExecutionFlowResultValue <em>Execution Flow Result Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Flow Result Value</em>' attribute.
	 * @see #getExecutionFlowResultValue()
	 * @generated
	 */
	void setExecutionFlowResultValue(String value);

	/**
	 * @model 
	 */
	ExecutionFlowObject getSourceFlow();
	
	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.FlowObjectConnection#getSourceFlow <em>Source Flow</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Flow</em>' reference.
	 * @see #getSourceFlow()
	 * @generated
	 */
	void setSourceFlow(ExecutionFlowObject value);

	/**
	 * @model 
	 */
	State getDestinationState();
	
	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.FlowObjectConnection#getDestinationState <em>Destination State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination State</em>' reference.
	 * @see #getDestinationState()
	 * @generated
	 */
	void setDestinationState(State value);

}
