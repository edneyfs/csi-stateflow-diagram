/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IMarker
 *  org.eclipse.core.resources.IResource
 *  org.eclipse.core.runtime.CoreException
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor
 *  org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart
 *  org.eclipse.gmf.runtime.emf.ui.providers.marker.AbstractModelMarkerNavigationProvider
 */
package com.csi.gmf.executionflow.diagram.providers;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorUtil;
import java.util.Arrays;
import java.util.Map;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.ui.providers.marker.AbstractModelMarkerNavigationProvider;

public class ExecutionflowMarkerNavigationProvider
extends AbstractModelMarkerNavigationProvider {
    public static final String MARKER_TYPE = "com.csi.gmf.executionflow.diagram.diagnostic";

    protected void doGotoMarker(IMarker marker) {
        String elementId = marker.getAttribute("elementId", null);
        if (elementId == null || !(this.getEditor() instanceof DiagramEditor)) {
            return;
        }
        DiagramEditor editor = (DiagramEditor)this.getEditor();
        Map editPartRegistry = editor.getDiagramGraphicalViewer().getEditPartRegistry();
        EObject targetView = editor.getDiagram().eResource().getEObject(elementId);
        if (targetView == null) {
            return;
        }
        EditPart targetEditPart = (EditPart)editPartRegistry.get(targetView);
        if (targetEditPart != null) {
            ExecutionflowDiagramEditorUtil.selectElementsInDiagram((IDiagramWorkbenchPart)editor, Arrays.asList(targetEditPart));
        }
    }

    public static void deleteMarkers(IResource resource) {
        try {
            resource.deleteMarkers(MARKER_TYPE, true, 0);
        }
        catch (CoreException e) {
            ExecutionflowDiagramEditorPlugin.getInstance().logError("Failed to delete validation markers", e);
        }
    }

    public static IMarker addMarker(IFile file, String elementId, String location, String message, int statusSeverity) {
        IMarker marker = null;
        try {
            marker = file.createMarker(MARKER_TYPE);
            marker.setAttribute("message", (Object)message);
            marker.setAttribute("location", (Object)location);
            marker.setAttribute("elementId", (Object)elementId);
            int markerSeverity = 0;
            if (statusSeverity == 2) {
                markerSeverity = 1;
            } else if (statusSeverity == 4 || statusSeverity == 8) {
                markerSeverity = 2;
            }
            marker.setAttribute("severity", markerSeverity);
        }
        catch (CoreException e) {
            ExecutionflowDiagramEditorPlugin.getInstance().logError("Failed to create validation marker", e);
        }
        return marker;
    }
}

