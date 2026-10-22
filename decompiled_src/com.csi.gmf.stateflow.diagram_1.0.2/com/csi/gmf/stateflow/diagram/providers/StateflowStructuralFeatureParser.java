/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.EStructuralFeature
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.emf.transaction.util.TransactionUtil
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus
 *  org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus
 *  org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand
 */
package com.csi.gmf.stateflow.diagram.providers;

import com.csi.gmf.stateflow.diagram.providers.StateflowAbstractParser;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.Collections;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;

public class StateflowStructuralFeatureParser
extends StateflowAbstractParser {
    private static final MessageFormat DEFAULT_PROCESSOR = new MessageFormat("{0}");
    private EStructuralFeature feature;

    public StateflowStructuralFeatureParser(EStructuralFeature feature) {
        this.feature = feature;
    }

    @Override
    protected MessageFormat getViewProcessor() {
        MessageFormat processor = super.getViewProcessor();
        return processor == null ? DEFAULT_PROCESSOR : processor;
    }

    @Override
    protected MessageFormat getEditProcessor() {
        MessageFormat processor = super.getEditProcessor();
        return processor == null ? DEFAULT_PROCESSOR : processor;
    }

    @Override
    protected String getStringByPattern(IAdaptable adapter, int flags, String pattern, MessageFormat processor) {
        EObject element = (EObject)adapter.getAdapter(EObject.class);
        Object value = element.eGet(this.feature);
        value = this.getValidValue(this.feature, value);
        return processor.format(new Object[]{value}, new StringBuffer(), new FieldPosition(0)).toString();
    }

    protected IParserEditStatus validateValues(Object[] values) {
        if (values.length > 1) {
            return ParserEditStatus.UNEDITABLE_STATUS;
        }
        Object value = values.length == 1 ? values[0] : null;
        if ((value = this.getValidNewValue(this.feature, value)) instanceof StateflowAbstractParser.InvalidValue) {
            return new ParserEditStatus("com.csi.gmf.stateflow.diagram", 1, value.toString());
        }
        return ParserEditStatus.EDITABLE_STATUS;
    }

    @Override
    public ICommand getParseCommand(IAdaptable adapter, Object[] values) {
        EObject element = (EObject)adapter.getAdapter(EObject.class);
        if (element == null) {
            return UnexecutableCommand.INSTANCE;
        }
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain((EObject)element);
        if (editingDomain == null) {
            return UnexecutableCommand.INSTANCE;
        }
        Object value = values.length == 1 ? values[0] : null;
        ICommand command = this.getModificationCommand(element, this.feature, value);
        return new CompositeTransactionalCommand(editingDomain, command.getLabel(), Collections.singletonList(command));
    }

    public boolean isAffectingEvent(Object event, int flags) {
        return event instanceof Notification && this.feature == ((Notification)event).getFeature();
    }
}

