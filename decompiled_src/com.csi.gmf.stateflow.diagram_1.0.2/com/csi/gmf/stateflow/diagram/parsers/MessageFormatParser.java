/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.ecore.EAttribute
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus
 *  org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus
 *  org.eclipse.osgi.util.NLS
 */
package com.csi.gmf.stateflow.diagram.parsers;

import com.csi.gmf.stateflow.diagram.parsers.AbstractParser;
import com.csi.gmf.stateflow.diagram.part.Messages;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.text.ParsePosition;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.osgi.util.NLS;

public class MessageFormatParser
extends AbstractParser {
    private String defaultPattern;
    private MessageFormat viewProcessor;
    private MessageFormat editorProcessor;
    private MessageFormat editProcessor;

    public MessageFormatParser(EAttribute[] features) {
        super(features);
    }

    protected String getDefaultPattern() {
        if (this.defaultPattern == null) {
            StringBuffer sb = new StringBuffer();
            int i = 0;
            while (i < this.features.length) {
                if (i > 0) {
                    sb.append(' ');
                }
                sb.append('{');
                sb.append(i);
                sb.append('}');
                ++i;
            }
            this.defaultPattern = sb.toString();
        }
        return this.defaultPattern;
    }

    @Override
    public String getViewPattern() {
        String pattern = super.getViewPattern();
        return pattern != null ? pattern : this.getDefaultPattern();
    }

    @Override
    public void setViewPattern(String viewPattern) {
        super.setViewPattern(viewPattern);
        this.viewProcessor = null;
    }

    protected MessageFormat createViewProcessor(String viewPattern) {
        return new MessageFormat(viewPattern);
    }

    protected MessageFormat getViewProcessor() {
        if (this.viewProcessor == null) {
            this.viewProcessor = this.createViewProcessor(this.getViewPattern());
        }
        return this.viewProcessor;
    }

    @Override
    public String getEditorPattern() {
        String pattern = super.getEditorPattern();
        return pattern != null ? pattern : this.getDefaultPattern();
    }

    @Override
    public void setEditorPattern(String editorPattern) {
        super.setEditorPattern(editorPattern);
        this.editorProcessor = null;
    }

    protected MessageFormat createEditorProcessor(String editorPattern) {
        return new MessageFormat(editorPattern);
    }

    protected MessageFormat getEditorProcessor() {
        if (this.editorProcessor == null) {
            this.editorProcessor = this.createEditorProcessor(this.getEditorPattern());
        }
        return this.editorProcessor;
    }

    @Override
    public String getEditPattern() {
        String pattern = super.getEditPattern();
        return pattern != null ? pattern : this.getDefaultPattern();
    }

    @Override
    public void setEditPattern(String editPattern) {
        super.setEditPattern(editPattern);
        this.editProcessor = null;
    }

    protected MessageFormat createEditProcessor(String editPattern) {
        return new MessageFormat(editPattern);
    }

    protected MessageFormat getEditProcessor() {
        if (this.editProcessor == null) {
            this.editProcessor = this.createEditProcessor(this.getEditPattern());
        }
        return this.editProcessor;
    }

    public String getPrintString(IAdaptable adapter, int flags) {
        EObject element = (EObject)adapter.getAdapter(EObject.class);
        return this.getViewProcessor().format(this.getValues(element), new StringBuffer(), new FieldPosition(0)).toString();
    }

    public String getEditString(IAdaptable adapter, int flags) {
        EObject element = (EObject)adapter.getAdapter(EObject.class);
        return this.getEditorProcessor().format(this.getValues(element), new StringBuffer(), new FieldPosition(0)).toString();
    }

    public IParserEditStatus isValidEditString(IAdaptable adapter, String editString) {
        ParsePosition pos = new ParsePosition(0);
        Object[] values = this.getEditProcessor().parse(editString, pos);
        if (values == null) {
            return new ParserEditStatus("com.csi.gmf.stateflow.diagram", 1, NLS.bind((String)Messages.MessageFormatParser_InvalidInputError, (Object)new Integer(pos.getErrorIndex())));
        }
        return this.validateNewValues(values);
    }

    public ICommand getParseCommand(IAdaptable adapter, String newString, int flags) {
        Object[] values = this.getEditProcessor().parse(newString, new ParsePosition(0));
        return this.getParseCommand(adapter, values, flags);
    }
}

