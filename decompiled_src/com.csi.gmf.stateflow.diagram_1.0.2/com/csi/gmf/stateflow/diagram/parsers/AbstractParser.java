/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.commands.operations.IUndoableOperation
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.ecore.EAttribute
 *  org.eclipse.emf.ecore.EClassifier
 *  org.eclipse.emf.ecore.EDataType
 *  org.eclipse.emf.ecore.EEnum
 *  org.eclipse.emf.ecore.EEnumLiteral
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.EStructuralFeature
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.emf.transaction.util.TransactionUtil
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParser
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus
 *  org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus
 *  org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand
 *  org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest
 *  org.eclipse.jface.text.contentassist.IContentAssistProcessor
 *  org.eclipse.osgi.util.NLS
 */
package com.csi.gmf.stateflow.diagram.parsers;

import com.csi.gmf.stateflow.diagram.part.Messages;
import java.util.Arrays;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.osgi.util.NLS;

public abstract class AbstractParser
implements IParser {
    protected final EAttribute[] features;
    private String viewPattern;
    private String editorPattern;
    private String editPattern;

    public AbstractParser(EAttribute[] features) {
        if (features == null || Arrays.asList(features).contains(null)) {
            throw new IllegalArgumentException();
        }
        this.features = features;
    }

    public String getViewPattern() {
        return this.viewPattern;
    }

    public void setViewPattern(String viewPattern) {
        this.viewPattern = viewPattern;
    }

    public String getEditorPattern() {
        return this.editorPattern;
    }

    public void setEditorPattern(String editorPattern) {
        this.editorPattern = editorPattern;
    }

    public String getEditPattern() {
        return this.editPattern;
    }

    public void setEditPattern(String editPattern) {
        this.editPattern = editPattern;
    }

    public boolean isAffectingEvent(Object event, int flags) {
        if (event instanceof Notification) {
            return this.isAffectingFeature(((Notification)event).getFeature());
        }
        return false;
    }

    protected boolean isAffectingFeature(Object feature) {
        int i = 0;
        while (i < this.features.length) {
            if (this.features[i] == feature) {
                return true;
            }
            ++i;
        }
        return false;
    }

    public IContentAssistProcessor getCompletionProcessor(IAdaptable element) {
        return null;
    }

    protected Object[] getValues(EObject element) {
        Object[] values = new Object[this.features.length];
        int i = 0;
        while (i < this.features.length) {
            values[i] = this.getValue(element, this.features[i]);
            ++i;
        }
        return values;
    }

    protected Object getValue(EObject element, EAttribute feature) {
        Object value = element.eGet((EStructuralFeature)feature);
        Class iClass = feature.getEAttributeType().getInstanceClass();
        if (String.class.equals((Object)iClass) && value == null) {
            value = "";
        }
        return value;
    }

    protected ICommand getParseCommand(IAdaptable adapter, Object[] values, int flags) {
        if (values == null || this.validateNewValues(values).getCode() != 0) {
            return UnexecutableCommand.INSTANCE;
        }
        EObject element = (EObject)adapter.getAdapter(EObject.class);
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain((EObject)element);
        if (editingDomain == null) {
            return UnexecutableCommand.INSTANCE;
        }
        CompositeTransactionalCommand command = new CompositeTransactionalCommand(editingDomain, "Set Values");
        int i = 0;
        while (i < values.length) {
            command.compose((IUndoableOperation)this.getModificationCommand(element, this.features[i], values[i]));
            ++i;
        }
        return command;
    }

    protected ICommand getModificationCommand(EObject element, EAttribute feature, Object value) {
        if ((value = this.getValidNewValue(feature, value)) instanceof InvalidValue) {
            return UnexecutableCommand.INSTANCE;
        }
        SetRequest request = new SetRequest(element, (EStructuralFeature)feature, value);
        return new SetValueCommand(request);
    }

    protected IParserEditStatus validateNewValues(Object[] values) {
        if (values.length != this.features.length) {
            return ParserEditStatus.UNEDITABLE_STATUS;
        }
        int i = 0;
        while (i < values.length) {
            Object value = this.getValidNewValue(this.features[i], values[i]);
            if (value instanceof InvalidValue) {
                return new ParserEditStatus("com.csi.gmf.stateflow.diagram", 1, value.toString());
            }
            ++i;
        }
        return ParserEditStatus.EDITABLE_STATUS;
    }

    protected Object getValidNewValue(EAttribute feature, Object value) {
        EClassifier type = feature.getEType();
        if (type instanceof EDataType) {
            Class iClass = type.getInstanceClass();
            if (Boolean.TYPE.equals(iClass)) {
                if (!(value instanceof Boolean)) {
                    value = value instanceof String ? Boolean.valueOf((String)value) : new InvalidValue(NLS.bind((String)Messages.AbstractParser_UnexpectedValueTypeMessage, (Object)iClass.getName()));
                }
            } else if (Character.TYPE.equals(iClass)) {
                if (!(value instanceof Character)) {
                    String s;
                    value = value instanceof String ? ((s = (String)value).length() == 0 ? null : new Character(s.charAt(0))) : new InvalidValue(NLS.bind((String)Messages.AbstractParser_UnexpectedValueTypeMessage, (Object)iClass.getName()));
                }
            } else if (Byte.TYPE.equals(iClass)) {
                if (!(value instanceof Byte)) {
                    if (value instanceof Number) {
                        value = new Byte(((Number)value).byteValue());
                    } else if (value instanceof String) {
                        String s = (String)value;
                        if (s.length() == 0) {
                            value = null;
                        } else {
                            try {
                                value = Byte.valueOf(s);
                            }
                            catch (NumberFormatException numberFormatException) {
                                value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_WrongStringConversionMessage, (Object)iClass.getName()));
                            }
                        }
                    } else {
                        value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_UnexpectedValueTypeMessage, (Object)iClass.getName()));
                    }
                }
            } else if (Short.TYPE.equals(iClass)) {
                if (!(value instanceof Short)) {
                    if (value instanceof Number) {
                        value = new Short(((Number)value).shortValue());
                    } else if (value instanceof String) {
                        String s = (String)value;
                        if (s.length() == 0) {
                            value = null;
                        } else {
                            try {
                                value = Short.valueOf(s);
                            }
                            catch (NumberFormatException numberFormatException) {
                                value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_WrongStringConversionMessage, (Object)iClass.getName()));
                            }
                        }
                    } else {
                        value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_UnexpectedValueTypeMessage, (Object)iClass.getName()));
                    }
                }
            } else if (Integer.TYPE.equals(iClass)) {
                if (!(value instanceof Integer)) {
                    if (value instanceof Number) {
                        value = new Integer(((Number)value).intValue());
                    } else if (value instanceof String) {
                        String s = (String)value;
                        if (s.length() == 0) {
                            value = null;
                        } else {
                            try {
                                value = Integer.valueOf(s);
                            }
                            catch (NumberFormatException numberFormatException) {
                                value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_WrongStringConversionMessage, (Object)iClass.getName()));
                            }
                        }
                    } else {
                        value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_UnexpectedValueTypeMessage, (Object)iClass.getName()));
                    }
                }
            } else if (Long.TYPE.equals(iClass)) {
                if (!(value instanceof Long)) {
                    if (value instanceof Number) {
                        value = new Long(((Number)value).longValue());
                    } else if (value instanceof String) {
                        String s = (String)value;
                        if (s.length() == 0) {
                            value = null;
                        } else {
                            try {
                                value = Long.valueOf(s);
                            }
                            catch (NumberFormatException numberFormatException) {
                                value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_WrongStringConversionMessage, (Object)iClass.getName()));
                            }
                        }
                    } else {
                        value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_UnexpectedValueTypeMessage, (Object)iClass.getName()));
                    }
                }
            } else if (Float.TYPE.equals(iClass)) {
                if (!(value instanceof Float)) {
                    if (value instanceof Number) {
                        value = new Float(((Number)value).floatValue());
                    } else if (value instanceof String) {
                        String s = (String)value;
                        if (s.length() == 0) {
                            value = null;
                        } else {
                            try {
                                value = Float.valueOf(s);
                            }
                            catch (NumberFormatException numberFormatException) {
                                value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_WrongStringConversionMessage, (Object)iClass.getName()));
                            }
                        }
                    } else {
                        value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_UnexpectedValueTypeMessage, (Object)iClass.getName()));
                    }
                }
            } else if (Double.TYPE.equals(iClass)) {
                if (!(value instanceof Double)) {
                    if (value instanceof Number) {
                        value = new Double(((Number)value).doubleValue());
                    } else if (value instanceof String) {
                        String s = (String)value;
                        if (s.length() == 0) {
                            value = null;
                        } else {
                            try {
                                value = Double.valueOf(s);
                            }
                            catch (NumberFormatException numberFormatException) {
                                value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_WrongStringConversionMessage, (Object)iClass.getName()));
                            }
                        }
                    } else {
                        value = new InvalidValue(NLS.bind((String)Messages.AbstractParser_UnexpectedValueTypeMessage, (Object)iClass.getName()));
                    }
                }
            } else if (type instanceof EEnum) {
                EEnumLiteral literal;
                value = value instanceof String ? ((literal = ((EEnum)type).getEEnumLiteralByLiteral((String)value)) == null ? new InvalidValue(NLS.bind((String)Messages.AbstractParser_UnknownLiteralMessage, (Object)value)) : literal.getInstance()) : new InvalidValue(NLS.bind((String)Messages.AbstractParser_UnexpectedValueTypeMessage, (Object)String.class.getName()));
            }
        }
        return value;
    }

    protected class InvalidValue {
        private String description;

        public InvalidValue(String description) {
            this.description = description;
        }

        public String toString() {
            return this.description;
        }
    }
}

