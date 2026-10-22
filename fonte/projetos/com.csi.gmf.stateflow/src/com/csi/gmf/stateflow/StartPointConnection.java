/**
 * 
 */
package com.csi.gmf.stateflow;

import org.eclipse.emf.ecore.EObject;

/**
 * @author gustav.monteiro
 * @model
 */
public interface StartPointConnection extends EObject {
	
	/**
	 * @model
	 */
	StartPoint getStartPoint();
	
	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.StartPointConnection#getStartPoint <em>Start Point</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Point</em>' reference.
	 * @see #getStartPoint()
	 * @generated
	 */
	void setStartPoint(StartPoint value);

	/**
	 * @model
	 */
	State getDestinationState();
	
	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.StartPointConnection#getDestinationState <em>Destination State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination State</em>' reference.
	 * @see #getDestinationState()
	 * @generated
	 */
	void setDestinationState(State value);

	/**
	 * @model
	 */
	String getDestinationStateId();
	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.StartPointConnection#getDestinationStateId <em>Destination State Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination State Id</em>' attribute.
	 * @see #getDestinationStateId()
	 * @generated
	 */
	void setDestinationStateId(String value);

}
