/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.commands.operations.IUndoableOperation
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
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.commands.operations.IUndoableOperation;
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

public class StateflowStructuralFeaturesParser
extends StateflowAbstractParser {
    private List features;

    public StateflowStructuralFeaturesParser(List features) {
        this.features = features;
    }

    @Override
    protected String getStringByPattern(IAdaptable adapter, int flags, String pattern, MessageFormat processor) {
        EObject element = (EObject)adapter.getAdapter(EObject.class);
        ArrayList<Object> values = new ArrayList<Object>(this.features.size());
        for (EStructuralFeature feature : this.features) {
            Object value = element.eGet(feature);
            value = this.getValidValue(feature, value);
            values.add(value);
        }
        return processor.format(values.toArray(new Object[values.size()]), new StringBuffer(), new FieldPosition(0)).toString();
    }

    protected IParserEditStatus validateValues(Object[] values) {
        if (values.length != this.features.size()) {
            return ParserEditStatus.UNEDITABLE_STATUS;
        }
        int i = 0;
        while (i < values.length) {
            Object value = this.getValidNewValue((EStructuralFeature)this.features.get(i), values[i]);
            if (value instanceof StateflowAbstractParser.InvalidValue) {
                return new ParserEditStatus("com.csi.gmf.stateflow.diagram", 1, value.toString());
            }
            ++i;
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
        CompositeTransactionalCommand command = new CompositeTransactionalCommand(editingDomain, "Set Values");
        int i = 0;
        while (i < values.length) {
            EStructuralFeature feature = (EStructuralFeature)this.features.get(i);
            command.compose((IUndoableOperation)this.getModificationCommand(element, feature, values[i]));
            ++i;
        }
        return command;
    }

    public boolean isAffectingEvent(Object event, int flags) {
        Object feature;
        return event instanceof Notification && this.features.contains(feature = ((Notification)event).getFeature());
    }
}

