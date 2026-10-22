/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.gmf.runtime.diagram.ui.view.factories.BasicNodeViewFactory
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.BasicNodeViewFactory;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutableFlowObjectExecutaleClassViewFactory
extends BasicNodeViewFactory {
    protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
        super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
    }

    protected List createStyles(View view) {
        ArrayList styles = new ArrayList();
        return styles;
    }
}

