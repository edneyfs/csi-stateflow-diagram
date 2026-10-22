/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gmf.runtime.diagram.ui.view.factories.DiagramViewFactory
 *  org.eclipse.gmf.runtime.notation.DiagramStyle
 *  org.eclipse.gmf.runtime.notation.MeasurementUnit
 *  org.eclipse.gmf.runtime.notation.NotationFactory
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.DiagramViewFactory;
import org.eclipse.gmf.runtime.notation.DiagramStyle;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionFlowViewFactory
extends DiagramViewFactory {
    protected List createStyles(View view) {
        ArrayList<DiagramStyle> styles = new ArrayList<DiagramStyle>();
        styles.add(NotationFactory.eINSTANCE.createDiagramStyle());
        return styles;
    }

    protected MeasurementUnit getMeasurementUnit() {
        return MeasurementUnit.PIXEL_LITERAL;
    }
}

