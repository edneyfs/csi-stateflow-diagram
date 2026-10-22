/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.search.ui.text.FileTextSearchScope
 *  org.eclipse.search.ui.text.TextSearchQueryProvider$TextSearchInput
 */
package com.csi.gmf.executionflow.diagram.part;

import org.eclipse.search.ui.text.FileTextSearchScope;
import org.eclipse.search.ui.text.TextSearchQueryProvider;

public class ExecutionFlowSearchInput
extends TextSearchQueryProvider.TextSearchInput {
    private final String fSearchText;
    private final boolean fIsCaseSensitive;
    private final boolean fIsRegEx;
    private final FileTextSearchScope fScope;

    public ExecutionFlowSearchInput(String searchText, boolean isCaseSensitive, boolean isRegEx, FileTextSearchScope scope) {
        this.fSearchText = searchText;
        this.fIsCaseSensitive = isCaseSensitive;
        this.fIsRegEx = isRegEx;
        this.fScope = scope;
    }

    public String getSearchText() {
        return this.fSearchText;
    }

    public boolean isCaseSensitiveSearch() {
        return this.fIsCaseSensitive;
    }

    public boolean isRegExSearch() {
        return this.fIsRegEx;
    }

    public FileTextSearchScope getScope() {
        return this.fScope;
    }
}

