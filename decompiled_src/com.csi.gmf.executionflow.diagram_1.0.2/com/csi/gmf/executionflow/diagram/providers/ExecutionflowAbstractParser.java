/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.ecore.EClassifier
 *  org.eclipse.emf.ecore.EDataType
 *  org.eclipse.emf.ecore.EEnum
 *  org.eclipse.emf.ecore.EEnumLiteral
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.EStructuralFeature
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParser
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus
 *  org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus
 *  org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest
 *  org.eclipse.jface.text.contentassist.IContentAssistProcessor
 */
package com.csi.gmf.executionflow.diagram.providers;

import java.text.MessageFormat;
import java.text.ParsePosition;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;

public abstract class ExecutionflowAbstractParser
implements IParser {
    private String viewPattern;
    private MessageFormat viewProcessor;
    private String editPattern;
    private MessageFormat editProcessor;

    public String getViewPattern() {
        return this.viewPattern;
    }

    protected MessageFormat getViewProcessor() {
        return this.viewProcessor;
    }

    public void setViewPattern(String viewPattern) {
        this.viewPattern = viewPattern;
        this.viewProcessor = this.createViewProcessor(viewPattern);
    }

    protected MessageFormat createViewProcessor(String viewPattern) {
        return new MessageFormat(viewPattern);
    }

    public String getEditPattern() {
        return this.editPattern;
    }

    protected MessageFormat getEditProcessor() {
        return this.editProcessor;
    }

    public void setEditPattern(String editPattern) {
        this.editPattern = editPattern;
        this.editProcessor = this.createEditProcessor(editPattern);
    }

    protected MessageFormat createEditProcessor(String editPattern) {
        return new MessageFormat(editPattern);
    }

    public String getPrintString(IAdaptable adapter, int flags) {
        return this.getStringByPattern(adapter, flags, this.getViewPattern(), this.getViewProcessor());
    }

    public String getEditString(IAdaptable adapter, int flags) {
        return this.getStringByPattern(adapter, flags, this.getEditPattern(), this.getEditProcessor());
    }

    protected abstract String getStringByPattern(IAdaptable var1, int var2, String var3, MessageFormat var4);

    public IParserEditStatus isValidEditString(IAdaptable element, String editString) {
        ParsePosition pos = new ParsePosition(0);
        Object[] values = this.getEditProcessor().parse(editString, pos);
        if (values == null) {
            return new ParserEditStatus("com.csi.gmf.executionflow.diagram", 1, "Invalid input at " + pos.getErrorIndex());
        }
        return this.validateNewValues(values);
    }

    protected IParserEditStatus validateNewValues(Object[] values) {
        return ParserEditStatus.EDITABLE_STATUS;
    }

    public ICommand getParseCommand(IAdaptable adapter, String newString, int flags) {
        Object[] values = this.getEditProcessor().parse(newString, new ParsePosition(0));
        if (values == null || this.validateNewValues(values).getCode() != 0) {
            return UnexecutableCommand.INSTANCE;
        }
        return this.getParseCommand(adapter, values);
    }

    protected abstract ICommand getParseCommand(IAdaptable var1, Object[] var2);

    public IContentAssistProcessor getCompletionProcessor(IAdaptable element) {
        return null;
    }

    protected ICommand getModificationCommand(EObject element, EStructuralFeature feature, Object value) {
        if ((value = this.getValidNewValue(feature, value)) instanceof InvalidValue) {
            return UnexecutableCommand.INSTANCE;
        }
        SetRequest request = new SetRequest(element, feature, value);
        return new SetValueCommand(request);
    }

    protected Object getValidValue(EStructuralFeature feature, Object value) {
        Class iClass;
        EClassifier type = feature.getEType();
        if (type instanceof EDataType && String.class.equals((Object)(iClass = type.getInstanceClass())) && value == null) {
            value = "";
        }
        return value;
    }

    protected Object getValidNewValue(EStructuralFeature feature, Object value) {
        EClassifier type = feature.getEType();
        if (type instanceof EDataType) {
            Class iClass = type.getInstanceClass();
            if (Boolean.TYPE.equals(iClass)) {
                if (!(value instanceof Boolean)) {
                    value = value instanceof String ? Boolean.valueOf((String)value) : new InvalidValue("Value of type Boolean is expected");
                }
            } else if (Character.TYPE.equals(iClass)) {
                if (!(value instanceof Character)) {
                    String s;
                    value = value instanceof String ? ((s = (String)value).length() == 0 ? null : new Character(s.charAt(0))) : new InvalidValue("Value of type Character is expected");
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
                                value = new InvalidValue("String value does not convert to Byte value");
                            }
                        }
                    } else {
                        value = new InvalidValue("Value of type Byte is expected");
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
                                value = new InvalidValue("String value does not convert to Short value");
                            }
                        }
                    } else {
                        value = new InvalidValue("Value of type Short is expected");
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
                                value = new InvalidValue("String value does not convert to Integer value");
                            }
                        }
                    } else {
                        value = new InvalidValue("Value of type Integer is expected");
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
                                value = new InvalidValue("String value does not convert to Long value");
                            }
                        }
                    } else {
                        value = new InvalidValue("Value of type Long is expected");
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
                                value = new InvalidValue("String value does not convert to Float value");
                            }
                        }
                    } else {
                        value = new InvalidValue("Value of type Float is expected");
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
                                value = new InvalidValue("String value does not convert to Double value");
                            }
                        }
                    } else {
                        value = new InvalidValue("Value of type Double is expected");
                    }
                }
            } else if (type instanceof EEnum) {
                EEnumLiteral literal;
                value = value instanceof String ? ((literal = ((EEnum)type).getEEnumLiteralByLiteral((String)value)) == null ? new InvalidValue("Unknown literal: " + value) : literal.getInstance()) : new InvalidValue("Value of type String is expected");
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

