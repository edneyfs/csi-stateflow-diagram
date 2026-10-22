/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IMarker
 *  org.eclipse.core.resources.IResource
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
package com.csi.gmf.executionflow.diagram.providers;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditor;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
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

public class ExecutionflowValidationDecoratorProvider
extends AbstractProvider
implements IDecoratorProvider {
    private static final String KEY = "validationStatus";
    private static final String MARKER_TYPE = "com.csi.gmf.executionflow.diagram.diagnostic";
    private static MarkerObserver fileObserver = null;

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
            if (((DiagramEditDomain)ed).getEditorPart() instanceof ExecutionflowDiagramEditor) {
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
        return view != null && "Executionflow".equals(ExecutionflowVisualIDRegistry.getModelID(view));
    }

    static class MarkerObserver
    implements IFileObserver {
        private HashMap mapOfIdsToDecorators = null;
        private boolean isRegistered = false;
        private Diagram diagramView;

        private MarkerObserver(Diagram diagramView) {
            this.diagramView = diagramView;
        }

        private void registerDecorator(StatusDecorator decorator) {
            String decoratorViewId;
            if (decorator == null) {
                return;
            }
            if (this.mapOfIdsToDecorators == null) {
                this.mapOfIdsToDecorators = new HashMap();
            }
            if ((decoratorViewId = decorator.getViewId()) == null) {
                return;
            }
            ArrayList<StatusDecorator> list = (ArrayList<StatusDecorator>)this.mapOfIdsToDecorators.get(decoratorViewId);
            if (list == null) {
                list = new ArrayList<StatusDecorator>(2);
                list.add(decorator);
                this.mapOfIdsToDecorators.put(decoratorViewId, list);
            } else if (!list.contains((Object)decorator)) {
                list.add(decorator);
            }
            if (!this.isRegistered()) {
                FileChangeManager.getInstance().addFileObserver((IFileObserver)this);
                this.isRegistered = true;
            }
        }

        private void unregisterDecorator(StatusDecorator decorator) {
            if (decorator == null) {
                return;
            }
            String decoratorViewId = decorator.getViewId();
            if (decoratorViewId == null) {
                return;
            }
            if (this.mapOfIdsToDecorators != null) {
                List list = (List)this.mapOfIdsToDecorators.get(decoratorViewId);
                if (list != null) {
                    list.remove((Object)decorator);
                    if (list.isEmpty()) {
                        this.mapOfIdsToDecorators.remove(decoratorViewId);
                    }
                }
                if (this.mapOfIdsToDecorators.isEmpty()) {
                    this.mapOfIdsToDecorators = null;
                }
            }
            if (this.mapOfIdsToDecorators == null && this.isRegistered()) {
                FileChangeManager.getInstance().removeFileObserver((IFileObserver)this);
                this.isRegistered = false;
            }
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
            List list;
            if (this.mapOfIdsToDecorators == null) {
                return;
            }
            String elementId = (String)attributes.get("elementId");
            List list2 = list = elementId != null ? (List)this.mapOfIdsToDecorators.get(elementId) : null;
            if (list != null && !list.isEmpty()) {
                this.refreshDecorators(list);
            }
        }

        public void handleMarkerChanged(IMarker marker) {
            List list;
            if (this.mapOfIdsToDecorators == null || !ExecutionflowValidationDecoratorProvider.MARKER_TYPE.equals(this.getType(marker))) {
                return;
            }
            String elementId = marker.getAttribute("elementId", "");
            List list2 = list = elementId != null ? (List)this.mapOfIdsToDecorators.get(elementId) : null;
            if (list != null && !list.isEmpty()) {
                this.refreshDecorators(list);
            }
        }

        private void refreshDecorators(List decorators) {
            final List decoratorsToRefresh = decorators;
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable(){

                @Override
                public void run() {
                    try {
                        TransactionUtil.getEditingDomain((EObject)MarkerObserver.this.diagramView).runExclusive(new Runnable(){

                            @Override
                            public void run() {
                                for (IDecorator decorator : decoratorsToRefresh) {
                                    if (decorator == null) continue;
                                    decorator.refresh();
                                }
                            }
                        });
                    }
                    catch (Exception e) {
                        ExecutionflowDiagramEditorPlugin.getInstance().logError("Decorator refresh failure", e);
                    }
                }
            });
        }

        private boolean isRegistered() {
            return this.isRegistered;
        }

        private String getType(IMarker marker) {
            try {
                return marker.getType();
            }
            catch (CoreException e) {
                ExecutionflowDiagramEditorPlugin.getInstance().logError("Validation marker refresh failure", e);
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
                ExecutionflowDiagramEditorPlugin.getInstance().logError("ViewID access failure", e);
            }
        }

        public void refresh() {
            this.removeDecoration();
            View view = (View)this.getDecoratorTarget().getAdapter(View.class);
            EditPart editPart = (EditPart)this.getDecoratorTarget().getAdapter(EditPart.class);
            if (view == null || view.eResource() == null) {
                return;
            }
            IResource resource = StatusDecorator.getResource(view);
            if (resource == null || !resource.exists()) {
                return;
            }
            IMarker[] markers = null;
            try {
                markers = resource.findMarkers(ExecutionflowValidationDecoratorProvider.MARKER_TYPE, true, 2);
            }
            catch (CoreException e) {
                ExecutionflowDiagramEditorPlugin.getInstance().logError("Validation marker refresh failure", e);
            }
            if (markers == null || markers.length == 0) {
                return;
            }
            String elementId = ViewUtil.getIdStr((View)view);
            if (elementId == null) {
                return;
            }
            IMarker foundMarker = null;
            Label toolTip = null;
            int severity = 0;
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
                Image img = this.getImage(severity);
                if (view instanceof Edge) {
                    this.setDecoration(this.getDecoratorTarget().addConnectionDecoration(img, 50, true));
                } else {
                    int margin = -1;
                    if (editPart instanceof GraphicalEditPart) {
                        margin = MapModeUtil.getMapMode((IFigure)((GraphicalEditPart)editPart).getFigure()).DPtoLP(margin);
                    }
                    this.setDecoration(this.getDecoratorTarget().addShapeDecoration(img, IDecoratorTarget.Direction.NORTH_EAST, margin, true));
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

        private static IResource getResource(View view) {
            Resource model = view.eResource();
            if (model != null) {
                return WorkspaceSynchronizer.getFile((Resource)model);
            }
            return null;
        }

        public void activate() {
            View view = (View)this.getDecoratorTarget().getAdapter(View.class);
            if (view == null) {
                return;
            }
            Diagram diagramView = view.getDiagram();
            if (diagramView == null) {
                return;
            }
            IFile file = WorkspaceSynchronizer.getFile((Resource)diagramView.eResource());
            if (file != null) {
                if (fileObserver == null) {
                    fileObserver = new MarkerObserver(diagramView);
                }
                fileObserver.registerDecorator(this);
            }
        }

        public void deactivate() {
            if (fileObserver != null) {
                fileObserver.unregisterDecorator(this);
                if (!fileObserver.isRegistered()) {
                    fileObserver = null;
                }
            }
            super.deactivate();
        }

        String getViewId() {
            return this.viewId;
        }
    }
}

