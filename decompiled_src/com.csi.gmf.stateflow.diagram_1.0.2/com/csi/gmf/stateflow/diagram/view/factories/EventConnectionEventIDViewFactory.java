/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint
 *  org.eclipse.gmf.runtime.diagram.ui.util.MeasurementUnitHelper
 *  org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractLabelViewFactory
 *  org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode
 *  org.eclipse.gmf.runtime.notation.Location
 *  org.eclipse.gmf.runtime.notation.MeasurementUnit
 *  org.eclipse.gmf.runtime.notation.Node
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.util.MeasurementUnitHelper;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractLabelViewFactory;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

public class EventConnectionEventIDViewFactory
extends AbstractLabelViewFactory {
    public View createView(IAdaptable semanticAdapter, View containerView, String semanticHint, int index, boolean persisted, PreferencesHint preferencesHint) {
        Node view = (Node)super.createView(semanticAdapter, containerView, semanticHint, index, persisted, preferencesHint);
        Location location = (Location)view.getLayoutConstraint();
        IMapMode mapMode = MeasurementUnitHelper.getMapMode((MeasurementUnit)containerView.getDiagram().getMeasurementUnit());
        location.setX(mapMode.DPtoLP(0));
        location.setY(mapMode.DPtoLP(40));
        return view;
    }

    protected List createStyles(View view) {
        ArrayList styles = new ArrayList();
        return styles;
    }
}

