/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParser
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus
 *  org.eclipse.jface.text.contentassist.IContentAssistProcessor
 */
package com.csi.gmf.executionflow.diagram.parsers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;

public class CompositeParser
implements IParser {
    private final IParser reader;
    private final IParser writer;

    public CompositeParser(IParser reader, IParser writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public boolean isAffectingEvent(Object event, int flags) {
        return this.reader.isAffectingEvent(event, flags);
    }

    public String getPrintString(IAdaptable adapter, int flags) {
        return this.reader.getPrintString(adapter, flags);
    }

    public String getEditString(IAdaptable adapter, int flags) {
        return this.reader.getEditString(adapter, flags);
    }

    public IParserEditStatus isValidEditString(IAdaptable adapter, String editString) {
        return this.writer.isValidEditString(adapter, editString);
    }

    public ICommand getParseCommand(IAdaptable adapter, String newString, int flags) {
        return this.writer.getParseCommand(adapter, newString, flags);
    }

    public IContentAssistProcessor getCompletionProcessor(IAdaptable adapter) {
        return this.writer.getCompletionProcessor(adapter);
    }
}

