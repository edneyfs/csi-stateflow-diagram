/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.ecore.EAnnotation
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.EcoreFactory
 *  org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory
 *  org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter
 *  org.eclipse.gmf.runtime.notation.NotationFactory
 *  org.eclipse.gmf.runtime.notation.ShapeStyle
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.view.factories;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionFlowResultViewFactory
extends AbstractShapeViewFactory {
    protected List createStyles(View view) {
        ArrayList<ShapeStyle> styles = new ArrayList<ShapeStyle>();
        styles.add(NotationFactory.eINSTANCE.createShapeStyle());
        return styles;
    }

    protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
        if (semanticHint == null) {
            semanticHint = ExecutionflowVisualIDRegistry.getType(1003);
            view.setType(semanticHint);
        }
        super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
        if (!"Executionflow".equals(ExecutionflowVisualIDRegistry.getModelID(containerView))) {
            EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
            shortcutAnnotation.setSource("Shortcut");
            shortcutAnnotation.getDetails().put((Object)"modelID", (Object)"Executionflow");
            view.getEAnnotations().add((Object)shortcutAnnotation);
        }
        EObjectAdapter eObjectAdapter = null;
        EObject eObject = (EObject)semanticAdapter.getAdapter(EObject.class);
        if (eObject != null) {
            eObjectAdapter = new EObjectAdapter(eObject);
        }
        ExecutionFlowResultViewFactory.getViewService().createNode((IAdaptable)eObjectAdapter, view, ExecutionflowVisualIDRegistry.getType(4003), -1, true, this.getPreferencesHint());
    }
}

