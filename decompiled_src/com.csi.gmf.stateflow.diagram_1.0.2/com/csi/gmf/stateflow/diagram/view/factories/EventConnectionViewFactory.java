/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory
 *  org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter
 *  org.eclipse.gmf.runtime.notation.NotationFactory
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.view.factories;

import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

public class EventConnectionViewFactory
extends ConnectionViewFactory {
    protected List createStyles(View view) {
        ArrayList<Object> styles = new ArrayList<Object>();
        styles.add(NotationFactory.eINSTANCE.createRoutingStyle());
        styles.add(NotationFactory.eINSTANCE.createFontStyle());
        return styles;
    }

    protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
        if (semanticHint == null) {
            semanticHint = StateflowVisualIDRegistry.getType(3002);
            view.setType(semanticHint);
        }
        super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
        EObjectAdapter eObjectAdapter = null;
        EObject eObject = (EObject)semanticAdapter.getAdapter(EObject.class);
        if (eObject != null) {
            eObjectAdapter = new EObjectAdapter(eObject);
        }
        EventConnectionViewFactory.getViewService().createNode((IAdaptable)eObjectAdapter, view, StateflowVisualIDRegistry.getType(4004), -1, true, this.getPreferencesHint());
    }
}

