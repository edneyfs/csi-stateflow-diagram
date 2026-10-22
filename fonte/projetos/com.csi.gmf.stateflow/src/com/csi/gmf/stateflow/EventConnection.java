/**
 * 
 */
package com.csi.gmf.stateflow;

import org.eclipse.emf.ecore.EObject;

/**
 * @author gustav.monteiro
 * @model
 */
public interface EventConnection extends EObject {

	/**
	 * @model
	 */
	Long getSourcePathKey();

	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.EventConnection#getSourcePathKey <em>Source Path Key</em>}' attribute.
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
	 * Sets the value of the '{@link com.csi.gmf.stateflow.EventConnection#getDestinationPathKey <em>Destination Path Key</em>}' attribute.
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
	String getEventID();

	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.EventConnection#getEventID <em>Event ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event ID</em>' attribute.
	 * @see #getEventID()
	 * @generated
	 */
	void setEventID(String value);

	/**
	 * @model 
	 */
	State getSourceState();
	
	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.EventConnection#getSourceState <em>Source State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source State</em>' reference.
	 * @see #getSourceState()
	 * @generated
	 */
	void setSourceState(State value);

	/**
	 * @model 
	 */
	ExecutionFlowObject getDestinationFlow();
	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.EventConnection#getDestinationFlow <em>Destination Flow</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination Flow</em>' reference.
	 * @see #getDestinationFlow()
	 * @generated
	 */
	void setDestinationFlow(ExecutionFlowObject value);

}
