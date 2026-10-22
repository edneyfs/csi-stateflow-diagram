/**
 * 
 */
package com.csi.gmf.stateflow;


/**
 * @author gustav.monteiro
 * @model
 */
public interface State extends FlowObject {
	
	
	/**
	 * @model required="true"
	 */
	String getId();
	
	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.State#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * @model
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.State#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

}
