package com.csi.gmf.stateflow.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;
import com.csi.gmf.stateflow.StateflowPackage;

import com.csi.gmf.stateflow.diagram.edit.parts.EventConnectionEventIDEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.ExecutionFlowObjectTitleEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.FlowObjectConnectionExecutionFlowResultValuEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StateIdEditPart;

import com.csi.gmf.stateflow.diagram.parsers.MessageFormatParser;
import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;

/**
 * @generated
 */
public class StateflowParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser executionFlowObjectTitle_4001Parser;

	/**
	 * @generated
	 */
	private IParser getExecutionFlowObjectTitle_4001Parser() {
		if (executionFlowObjectTitle_4001Parser == null) {
			executionFlowObjectTitle_4001Parser = createExecutionFlowObjectTitle_4001Parser();
		}
		return executionFlowObjectTitle_4001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createExecutionFlowObjectTitle_4001Parser() {
		EAttribute[] features = new EAttribute[] { StateflowPackage.eINSTANCE
				.getExecutionFlowObject_Title(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser stateId_4002Parser;

	/**
	 * @generated
	 */
	private IParser getStateId_4002Parser() {
		if (stateId_4002Parser == null) {
			stateId_4002Parser = createStateId_4002Parser();
		}
		return stateId_4002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createStateId_4002Parser() {
		EAttribute[] features = new EAttribute[] { StateflowPackage.eINSTANCE
				.getState_Id(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser flowObjectConnectionExecutionFlowResultValue_4003Parser;

	/**
	 * @generated
	 */
	private IParser getFlowObjectConnectionExecutionFlowResultValue_4003Parser() {
		if (flowObjectConnectionExecutionFlowResultValue_4003Parser == null) {
			flowObjectConnectionExecutionFlowResultValue_4003Parser = createFlowObjectConnectionExecutionFlowResultValue_4003Parser();
		}
		return flowObjectConnectionExecutionFlowResultValue_4003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createFlowObjectConnectionExecutionFlowResultValue_4003Parser() {
		EAttribute[] features = new EAttribute[] { StateflowPackage.eINSTANCE
				.getFlowObjectConnection_ExecutionFlowResultValue(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser eventConnectionEventID_4004Parser;

	/**
	 * @generated
	 */
	private IParser getEventConnectionEventID_4004Parser() {
		if (eventConnectionEventID_4004Parser == null) {
			eventConnectionEventID_4004Parser = createEventConnectionEventID_4004Parser();
		}
		return eventConnectionEventID_4004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createEventConnectionEventID_4004Parser() {
		EAttribute[] features = new EAttribute[] { StateflowPackage.eINSTANCE
				.getEventConnection_EventID(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ExecutionFlowObjectTitleEditPart.VISUAL_ID:
			return getExecutionFlowObjectTitle_4001Parser();
		case StateIdEditPart.VISUAL_ID:
			return getStateId_4002Parser();
		case FlowObjectConnectionExecutionFlowResultValuEditPart.VISUAL_ID:
			return getFlowObjectConnectionExecutionFlowResultValue_4003Parser();
		case EventConnectionEventIDEditPart.VISUAL_ID:
			return getEventConnectionEventID_4004Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(StateflowVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(StateflowVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (StateflowElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}
}
