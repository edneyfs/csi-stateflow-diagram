/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.StateFlow
 *  com.csi.gmf.stateflow.StateflowPackage
 *  org.eclipse.core.runtime.Platform
 *  org.eclipse.emf.ecore.EAnnotation
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.notation.Diagram
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

public class StateflowVisualIDRegistry {
    private static final String DEBUG_KEY = "com.csi.gmf.stateflow.diagram/debug/visualID";

    public static int getVisualID(View view) {
        if (view instanceof Diagram) {
            if ("Stateflow".equals(view.getType())) {
                return 79;
            }
            return -1;
        }
        return StateflowVisualIDRegistry.getVisualID(view.getType());
    }

    public static String getModelID(View view) {
        Diagram diagram = view.getDiagram();
        while (view != diagram) {
            EAnnotation annotation = view.getEAnnotation("Shortcut");
            if (annotation != null) {
                return (String)annotation.getDetails().get((Object)"modelID");
            }
            view = (View)view.eContainer();
        }
        return diagram != null ? diagram.getType() : null;
    }

    public static int getVisualID(String type) {
        try {
            return Integer.parseInt(type);
        }
        catch (NumberFormatException numberFormatException) {
            if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption((String)DEBUG_KEY))) {
                StateflowDiagramEditorPlugin.getInstance().logError("Unable to parse view type as a visualID number: " + type);
            }
            return -1;
        }
    }

    public static String getType(int visualID) {
        return String.valueOf(visualID);
    }

    public static int getDiagramVisualID(EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        if (StateflowPackage.eINSTANCE.getStateFlow().isSuperTypeOf(domainElement.eClass()) && StateflowVisualIDRegistry.isDiagram((StateFlow)domainElement)) {
            return 79;
        }
        return -1;
    }

    public static int getNodeVisualID(View containerView, EObject domainElement) {
        int containerVisualID;
        if (domainElement == null) {
            return -1;
        }
        String containerModelID = StateflowVisualIDRegistry.getModelID(containerView);
        if (!"Stateflow".equals(containerModelID)) {
            return -1;
        }
        if ("Stateflow".equals(containerModelID)) {
            containerVisualID = StateflowVisualIDRegistry.getVisualID(containerView);
        } else if (containerView instanceof Diagram) {
            containerVisualID = 79;
        } else {
            return -1;
        }
        switch (containerVisualID) {
            case 79: {
                if (StateflowPackage.eINSTANCE.getExecutionFlowObject().isSuperTypeOf(domainElement.eClass())) {
                    return 1001;
                }
                if (StateflowPackage.eINSTANCE.getState().isSuperTypeOf(domainElement.eClass())) {
                    return 1002;
                }
                if (!StateflowPackage.eINSTANCE.getStartPoint().isSuperTypeOf(domainElement.eClass())) break;
                return 1003;
            }
        }
        return -1;
    }

    public static boolean canCreateNode(View containerView, int nodeVisualID) {
        int containerVisualID;
        String containerModelID = StateflowVisualIDRegistry.getModelID(containerView);
        if (!"Stateflow".equals(containerModelID)) {
            return false;
        }
        if ("Stateflow".equals(containerModelID)) {
            containerVisualID = StateflowVisualIDRegistry.getVisualID(containerView);
        } else if (containerView instanceof Diagram) {
            containerVisualID = 79;
        } else {
            return false;
        }
        switch (containerVisualID) {
            case 1001: {
                if (4001 != nodeVisualID) break;
                return true;
            }
            case 1002: {
                if (4002 != nodeVisualID) break;
                return true;
            }
            case 79: {
                if (1001 == nodeVisualID) {
                    return true;
                }
                if (1002 == nodeVisualID) {
                    return true;
                }
                if (1003 != nodeVisualID) break;
                return true;
            }
            case 3001: {
                if (4003 != nodeVisualID) break;
                return true;
            }
            case 3002: {
                if (4004 != nodeVisualID) break;
                return true;
            }
        }
        return false;
    }

    public static int getLinkWithClassVisualID(EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        if (StateflowPackage.eINSTANCE.getFlowObjectConnection().isSuperTypeOf(domainElement.eClass())) {
            return 3001;
        }
        if (StateflowPackage.eINSTANCE.getEventConnection().isSuperTypeOf(domainElement.eClass())) {
            return 3002;
        }
        if (StateflowPackage.eINSTANCE.getStartPointConnection().isSuperTypeOf(domainElement.eClass())) {
            return 3003;
        }
        return -1;
    }

    private static boolean isDiagram(StateFlow element) {
        return true;
    }
}

