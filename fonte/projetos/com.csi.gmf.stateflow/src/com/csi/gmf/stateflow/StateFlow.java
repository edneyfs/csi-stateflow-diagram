/**
 * 
 */
package com.csi.gmf.stateflow;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @author gustav.monteiro
 * @model
 */
public interface StateFlow extends EObject {

	/**
	 * @model type="com.csi.gmf.stateflow.StartPoint" containment="true"
	 */
	StartPoint getStartPoint();
	
	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.StateFlow#getStartPoint <em>Start Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Point</em>' containment reference.
	 * @see #getStartPoint()
	 * @generated
	 */
	void setStartPoint(StartPoint value);

	/**
	 * @model type="com.csi.gmf.stateflow.StartPointConnection" containment="true"
	 */
	StartPointConnection getStartPointConnection();
	
	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.StateFlow#getStartPointConnection <em>Start Point Connection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Point Connection</em>' containment reference.
	 * @see #getStartPointConnection()
	 * @generated
	 */
	void setStartPointConnection(StartPointConnection value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.csi.gmf.stateflow.StateflowPackage#getStateFlow_Id()
	 * @model id="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.StateFlow#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.csi.gmf.stateflow.StateflowPackage#getStateFlow_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.StateFlow#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Volatile</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Volatile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Volatile</em>' attribute.
	 * @see #setVolatile(boolean)
	 * @see com.csi.gmf.stateflow.StateflowPackage#getStateFlow_Volatile()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isVolatile();

	/**
	 * Sets the value of the '{@link com.csi.gmf.stateflow.StateFlow#isVolatile <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Volatile</em>' attribute.
	 * @see #isVolatile()
	 * @generated
	 */
	void setVolatile(boolean value);

	/**
	 * @model type="com.csi.gmf.stateflow.State" containment="true"
	 */
	EList getStates();
	
	/**
	 * @model type="com.csi.gmf.stateflow.ExecutionFlowObject" containment="true"
	 */
	EList getExecutionFlows();
	
	/**
	 * @model type="com.csi.gmf.stateflow.FlowObjectConnection" containment="true"
	 */
	EList getFlowConnections();

	/**
	 * @model type="com.csi.gmf.stateflow.EventConnection" containment="true"
	 */
	EList getStateConnections();
	
}
