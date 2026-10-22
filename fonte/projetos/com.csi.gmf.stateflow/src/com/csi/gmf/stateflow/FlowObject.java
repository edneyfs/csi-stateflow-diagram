/**
 * 
 */
package com.csi.gmf.stateflow;

import org.eclipse.emf.ecore.EObject;

/**
 * @author gustav.monteiro
 * @model
 */
public interface FlowObject extends EObject {
	
	/**
	 * @model required="true"
	 */
	Long getPathKey();

	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.FlowObject#getPathKey <em>Path Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path Key</em>' attribute.
	 * @see #getPathKey()
	 * @generated
	 */
	void setPathKey(Long value);

}
