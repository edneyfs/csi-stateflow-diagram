/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.StateflowPackage
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.ecore.EAttribute
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.common.core.service.AbstractProvider
 *  org.eclipse.gmf.runtime.common.core.service.IOperation
 *  org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParser
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider
 *  org.eclipse.gmf.runtime.emf.type.core.IElementType
 *  org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.providers;

import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.diagram.parsers.MessageFormatParser;
import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;
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

public class StateflowParserProvider
extends AbstractProvider
implements IParserProvider {
    private IParser executionFlowObjectTitle_4001Parser;
    private IParser stateId_4002Parser;
    private IParser flowObjectConnectionExecutionFlowResultValue_4003Parser;
    private IParser eventConnectionEventID_4004Parser;

    private IParser getExecutionFlowObjectTitle_4001Parser() {
        if (this.executionFlowObjectTitle_4001Parser == null) {
            this.executionFlowObjectTitle_4001Parser = this.createExecutionFlowObjectTitle_4001Parser();
        }
        return this.executionFlowObjectTitle_4001Parser;
    }

    protected IParser createExecutionFlowObjectTitle_4001Parser() {
        EAttribute[] features = new EAttribute[]{StateflowPackage.eINSTANCE.getExecutionFlowObject_Title()};
        MessageFormatParser parser = new MessageFormatParser(features);
        return parser;
    }

    private IParser getStateId_4002Parser() {
        if (this.stateId_4002Parser == null) {
            this.stateId_4002Parser = this.createStateId_4002Parser();
        }
        return this.stateId_4002Parser;
    }

    protected IParser createStateId_4002Parser() {
        EAttribute[] features = new EAttribute[]{StateflowPackage.eINSTANCE.getState_Id()};
        MessageFormatParser parser = new MessageFormatParser(features);
        return parser;
    }

    private IParser getFlowObjectConnectionExecutionFlowResultValue_4003Parser() {
        if (this.flowObjectConnectionExecutionFlowResultValue_4003Parser == null) {
            this.flowObjectConnectionExecutionFlowResultValue_4003Parser = this.createFlowObjectConnectionExecutionFlowResultValue_4003Parser();
        }
        return this.flowObjectConnectionExecutionFlowResultValue_4003Parser;
    }

    protected IParser createFlowObjectConnectionExecutionFlowResultValue_4003Parser() {
        EAttribute[] features = new EAttribute[]{StateflowPackage.eINSTANCE.getFlowObjectConnection_ExecutionFlowResultValue()};
        MessageFormatParser parser = new MessageFormatParser(features);
        return parser;
    }

    private IParser getEventConnectionEventID_4004Parser() {
        if (this.eventConnectionEventID_4004Parser == null) {
            this.eventConnectionEventID_4004Parser = this.createEventConnectionEventID_4004Parser();
        }
        return this.eventConnectionEventID_4004Parser;
    }

    protected IParser createEventConnectionEventID_4004Parser() {
        EAttribute[] features = new EAttribute[]{StateflowPackage.eINSTANCE.getEventConnection_EventID()};
        MessageFormatParser parser = new MessageFormatParser(features);
        return parser;
    }

    protected IParser getParser(int visualID) {
        switch (visualID) {
            case 4001: {
                return this.getExecutionFlowObjectTitle_4001Parser();
            }
            case 4002: {
                return this.getStateId_4002Parser();
            }
            case 4003: {
                return this.getFlowObjectConnectionExecutionFlowResultValue_4003Parser();
            }
            case 4004: {
                return this.getEventConnectionEventID_4004Parser();
            }
        }
        return null;
    }

    public IParser getParser(IAdaptable hint) {
        String vid = (String)hint.getAdapter(String.class);
        if (vid != null) {
            return this.getParser(StateflowVisualIDRegistry.getVisualID(vid));
        }
        View view = (View)hint.getAdapter(View.class);
        if (view != null) {
            return this.getParser(StateflowVisualIDRegistry.getVisualID(view));
        }
        return null;
    }

    public boolean provides(IOperation operation) {
        if (operation instanceof GetParserOperation) {
            IAdaptable hint = ((GetParserOperation)operation).getHint();
            if (StateflowElementTypes.getElement(hint) == null) {
                return false;
            }
            return this.getParser(hint) != null;
        }
        return false;
    }

    public static class HintAdapter
    extends ParserHintAdapter {
        private final IElementType elementType;

        public HintAdapter(IElementType type, EObject object, String parserHint) {
            super(object, parserHint);
            assert (type != null);
            this.elementType = type;
        }

        public Object getAdapter(Class adapter) {
            if (IElementType.class.equals((Object)adapter)) {
                return this.elementType;
            }
            return super.getAdapter(adapter);
        }
    }
}

