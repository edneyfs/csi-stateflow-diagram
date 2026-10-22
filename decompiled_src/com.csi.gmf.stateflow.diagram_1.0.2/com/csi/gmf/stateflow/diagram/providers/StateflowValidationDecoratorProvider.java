/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IMarker
 *  org.eclipse.core.runtime.CoreException
 *  org.eclipse.draw2d.FlowLayout
 *  org.eclipse.draw2d.IFigure
 *  org.eclipse.draw2d.Label
 *  org.eclipse.draw2d.LayoutManager
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.transaction.util.TransactionUtil
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer
 *  org.eclipse.gef.EditDomain
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.GraphicalEditPart
 *  org.eclipse.gef.editparts.AbstractConnectionEditPart
 *  org.eclipse.gmf.runtime.common.core.service.AbstractProvider
 *  org.eclipse.gmf.runtime.common.core.service.IOperation
 *  org.eclipse.gmf.runtime.common.ui.resources.FileChangeManager
 *  org.eclipse.gmf.runtime.common.ui.resources.IFileObserver
 *  org.eclipse.gmf.runtime.diagram.core.util.ViewUtil
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain
 *  org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator
 *  org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation
 *  org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator
 *  org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider
 *  org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget
 *  org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget$Direction
 *  org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil
 *  org.eclipse.gmf.runtime.notation.Diagram
 *  org.eclipse.gmf.runtime.notation.Edge
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.ui.PlatformUI
 */
package com.csi.gmf.stateflow.diagram.providers;

import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditor;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.resources.FileChangeManager;
import org.eclipse.gmf.runtime.common.ui.resources.IFileObserver;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

public class StateflowValidationDecoratorProvider
extends AbstractProvider
implements IDecoratorProvider {
    private static final String KEY = "validationStatus";
    private static final String MARKER_TYPE = "com.csi.gmf.stateflow.diagram.diagnostic";
    private static MarkerObserver fileObserver;
    private static Map allDecorators;

    static {
        allDecorators = new HashMap();
    }

    public void createDecorators(IDecoratorTarget decoratorTarget) {
        EditPart editPart = (EditPart)decoratorTarget.getAdapter(EditPart.class);
        if (editPart instanceof org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart || editPart instanceof AbstractConnectionEditPart) {
            View view;
            Object model = editPart.getModel();
            if (model instanceof View && !((view = (View)model) instanceof Edge) && !view.isSetElement()) {
                return;
            }
            EditDomain ed = editPart.getViewer().getEditDomain();
            if (!(ed instanceof DiagramEditDomain)) {
                return;
            }
            if (((DiagramEditDomain)ed).getEditorPart() instanceof StateflowDiagramEditor) {
                decoratorTarget.installDecorator((Object)KEY, (IDecorator)new StatusDecorator(decoratorTarget));
            }
        }
    }

    public boolean provides(IOperation operation) {
        if (!(operation instanceof CreateDecoratorsOperation)) {
            return false;
        }
        IDecoratorTarget decoratorTarget = ((CreateDecoratorsOperation)operation).getDecoratorTarget();
        View view = (View)decoratorTarget.getAdapter(View.class);
        return view != null && "Stateflow".equals(StateflowVisualIDRegistry.getModelID(view));
    }

    public static void refreshDecorators(View view) {
        StateflowValidationDecoratorProvider.refreshDecorators(ViewUtil.getIdStr((View)view), view.getDiagram());
    }

    private static void refreshDecorators(String viewId, Diagram diagram) {
        List decorators;
        List list = decorators = viewId != null ? (List)allDecorators.get(viewId) : null;
        if (decorators == null || decorators.isEmpty() || diagram == null) {
            return;
        }
        final Diagram fdiagram = diagram;
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable(){

            @Override
            public void run() {
                try {
                    TransactionUtil.getEditingDomain((EObject)fdiagram).runExclusive(new Runnable(){

                        @Override
                        public void run() {
                            for (IDecorator decorator : decorators) {
                                decorator.refresh();
                            }
                        }
                    });
                }
                catch (Exception e) {
                    StateflowDiagramEditorPlugin.getInstance().logError("Decorator refresh failure", e);
                }
            }
        });
    }

    static class MarkerObserver
    implements IFileObserver {
        private Diagram diagram;

        private MarkerObserver(Diagram diagram) {
            this.diagram = diagram;
        }

        public void handleFileRenamed(IFile oldFile, IFile file) {
        }

        public void handleFileMoved(IFile oldFile, IFile file) {
        }

        public void handleFileDeleted(IFile file) {
        }

        public void handleFileChanged(IFile file) {
        }

        public void handleMarkerAdded(IMarker marker) {
            if (marker.getAttribute("elementId", null) != null) {
                this.handleMarkerChanged(marker);
            }
        }

        public void handleMarkerDeleted(IMarker marker, Map attributes) {
            String viewId = (String)attributes.get("elementId");
            StateflowValidationDecoratorProvider.refreshDecorators(viewId, this.diagram);
        }

        public void handleMarkerChanged(IMarker marker) {
            if (!StateflowValidationDecoratorProvider.MARKER_TYPE.equals(this.getType(marker))) {
                return;
            }
            String viewId = marker.getAttribute("elementId", "");
            StateflowValidationDecoratorProvider.refreshDecorators(viewId, this.diagram);
        }

        private String getType(IMarker marker) {
            try {
                return marker.getType();
            }
            catch (CoreException e) {
                StateflowDiagramEditorPlugin.getInstance().logError("Validation marker refresh failure", e);
                return "";
            }
        }
    }

    public static class StatusDecorator
    extends AbstractDecorator {
        private String viewId;

        public StatusDecorator(IDecoratorTarget decoratorTarget) {
            super(decoratorTarget);
            try {
                final View view = (View)this.getDecoratorTarget().getAdapter(View.class);
                TransactionUtil.getEditingDomain((EObject)view).runExclusive(new Runnable(){

                    @Override
                    public void run() {
                        StatusDecorator.this.viewId = view != null ? ViewUtil.getIdStr((View)view) : null;
                    }
                });
            }
            catch (Exception e) {
                StateflowDiagramEditorPlugin.getInstance().logError("ViewID access failure", e);
            }
        }

        public void refresh() {
            this.removeDecoration();
            View view = (View)this.getDecoratorTarget().getAdapter(View.class);
            if (view == null || view.eResource() == null) {
                return;
            }
            EditPart editPart = (EditPart)this.getDecoratorTarget().getAdapter(EditPart.class);
            if (editPart == null || editPart.getViewer() == null) {
                return;
            }
            String elementId = ViewUtil.getIdStr((View)view);
            if (elementId == null) {
                return;
            }
            int severity = 0;
            IMarker foundMarker = null;
            IFile resource = WorkspaceSynchronizer.getFile((Resource)view.eResource());
            if (resource == null || !resource.exists()) {
                return;
            }
            IMarker[] markers = null;
            try {
                markers = resource.findMarkers(StateflowValidationDecoratorProvider.MARKER_TYPE, true, 2);
            }
            catch (CoreException e) {
                StateflowDiagramEditorPlugin.getInstance().logError("Validation markers refresh failure", e);
            }
            if (markers == null || markers.length == 0) {
                return;
            }
            Label toolTip = null;
            int i = 0;
            while (i < markers.length) {
                IMarker marker = markers[i];
                String attribute = marker.getAttribute("elementId", "");
                if (attribute.equals(elementId)) {
                    int nextSeverity = marker.getAttribute("severity", 0);
                    Image nextImage = this.getImage(nextSeverity);
                    if (foundMarker == null) {
                        foundMarker = marker;
                        toolTip = new Label(marker.getAttribute("message", ""), nextImage);
                    } else {
                        if (toolTip.getChildren().isEmpty()) {
                            Label comositeLabel = new Label();
                            FlowLayout fl = new FlowLayout(false);
                            fl.setMinorSpacing(0);
                            comositeLabel.setLayoutManager((LayoutManager)fl);
                            comositeLabel.add((IFigure)toolTip);
                            toolTip = comositeLabel;
                        }
                        toolTip.add((IFigure)new Label(marker.getAttribute("message", ""), nextImage));
                    }
                    severity = nextSeverity > severity ? nextSeverity : severity;
                }
                ++i;
            }
            if (foundMarker == null) {
                return;
            }
            if (editPart instanceof GraphicalEditPart) {
                if (view instanceof Edge) {
                    this.setDecoration(this.getDecoratorTarget().addConnectionDecoration(this.getImage(severity), 50, true));
                } else {
                    int margin = -1;
                    if (editPart instanceof GraphicalEditPart) {
                        margin = MapModeUtil.getMapMode((IFigure)((GraphicalEditPart)editPart).getFigure()).DPtoLP(margin);
                    }
                    this.setDecoration(this.getDecoratorTarget().addShapeDecoration(this.getImage(severity), IDecoratorTarget.Direction.NORTH_EAST, margin, true));
                }
                this.getDecoration().setToolTip(toolTip);
            }
        }

        private Image getImage(int severity) {
            String imageName = "IMG_OBJS_ERROR_TSK";
            switch (severity) {
                case 2: {
                    imageName = "IMG_OBJS_ERROR_TSK";
                    break;
                }
                case 1: {
                    imageName = "IMG_OBJS_WARN_TSK";
                    break;
                }
                default: {
                    imageName = "IMG_OBJS_INFO_TSK";
                }
            }
            return PlatformUI.getWorkbench().getSharedImages().getImage(imageName);
        }

        public void activate() {
            if (this.viewId == null) {
                return;
            }
            ArrayList<StatusDecorator> list = (ArrayList<StatusDecorator>)allDecorators.get(this.viewId);
            if (list == null) {
                list = new ArrayList<StatusDecorator>(2);
                list.add(this);
                allDecorators.put(this.viewId, list);
            } else if (!list.contains((Object)this)) {
                list.add(this);
            }
            View view = (View)this.getDecoratorTarget().getAdapter(View.class);
            if (view == null) {
                return;
            }
            Diagram diagramView = view.getDiagram();
            if (diagramView == null) {
                return;
            }
            if (fileObserver == null) {
                FileChangeManager fileChangeManager = FileChangeManager.getInstance();
                MarkerObserver markerObserver = new MarkerObserver(diagramView);
                fileObserver = markerObserver;
                fileChangeManager.addFileObserver((IFileObserver)markerObserver);
            }
        }

        public void deactivate() {
            if (this.viewId == null) {
                return;
            }
            List list = (List)allDecorators.get(this.viewId);
            if (list != null) {
                list.remove((Object)this);
                if (list.isEmpty()) {
                    allDecorators.remove(this.viewId);
                }
            }
            if (fileObserver != null && allDecorators.isEmpty()) {
                FileChangeManager.getInstance().removeFileObserver((IFileObserver)fileObserver);
                fileObserver = null;
            }
            super.deactivate();
        }
    }
}

