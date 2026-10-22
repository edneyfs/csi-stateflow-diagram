/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IResource
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.core.runtime.IStatus
 *  org.eclipse.core.runtime.NullProgressMonitor
 *  org.eclipse.emf.common.util.Diagnostic
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.ecore.util.Diagnostician
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.emf.transaction.util.TransactionUtil
 *  org.eclipse.emf.validation.model.EvaluationMode
 *  org.eclipse.emf.validation.model.IConstraintStatus
 *  org.eclipse.emf.validation.service.IBatchValidator
 *  org.eclipse.emf.validation.service.ModelValidationService
 *  org.eclipse.emf.workspace.util.WorkspaceSynchronizer
 *  org.eclipse.gef.EditPartViewer
 *  org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor
 *  org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart
 *  org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.action.Action
 *  org.eclipse.jface.operation.IRunnableWithProgress
 *  org.eclipse.ui.IEditorPart
 *  org.eclipse.ui.IWorkbenchPart
 *  org.eclipse.ui.PlatformUI
 *  org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorUtil;
import com.csi.gmf.executionflow.diagram.part.Messages;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowMarkerNavigationProvider;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowValidationProvider;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;

public class ValidateAction
extends Action {
    public static final String VALIDATE_ACTION_KEY = "validateAction";
    private IWorkbenchPartDescriptor workbenchPartDescriptor;

    public ValidateAction(IWorkbenchPartDescriptor workbenchPartDescriptor) {
        this.setId(VALIDATE_ACTION_KEY);
        this.setText(Messages.ValidateActionMessage);
        this.workbenchPartDescriptor = workbenchPartDescriptor;
    }

    public void run() {
        IWorkbenchPart workbenchPart = this.workbenchPartDescriptor.getPartPage().getActivePart();
        if (workbenchPart instanceof IDiagramWorkbenchPart) {
            final IDiagramWorkbenchPart part = (IDiagramWorkbenchPart)workbenchPart;
            try {
                new WorkspaceModifyDelegatingOperation(new IRunnableWithProgress(){

                    public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
                        ValidateAction.runValidation(part.getDiagramEditPart(), (View)part.getDiagram());
                    }
                }).run((IProgressMonitor)new NullProgressMonitor());
            }
            catch (Exception e) {
                ExecutionflowDiagramEditorPlugin.getInstance().logError("Validation action failed", e);
            }
        }
    }

    public static void runValidation(View view) {
        try {
            if (ExecutionflowDiagramEditorUtil.openDiagram(view.eResource())) {
                IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
                if (editorPart instanceof IDiagramWorkbenchPart) {
                    ValidateAction.runValidation(((IDiagramWorkbenchPart)editorPart).getDiagramEditPart(), view);
                } else {
                    ValidateAction.runNonUIValidation(view);
                }
            }
        }
        catch (Exception e) {
            ExecutionflowDiagramEditorPlugin.getInstance().logError("Validation action failed", e);
        }
    }

    public static void runNonUIValidation(View view) {
        DiagramEditPart diagramEditPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(view.getDiagram());
        ValidateAction.runValidation(diagramEditPart, view);
    }

    public static void runValidation(DiagramEditPart diagramEditPart, View view) {
        final DiagramEditPart fpart = diagramEditPart;
        final View fview = view;
        TransactionalEditingDomain txDomain = TransactionUtil.getEditingDomain((EObject)view);
        ExecutionflowValidationProvider.runWithConstraints(txDomain, new Runnable(){

            @Override
            public void run() {
                ValidateAction.validate(fpart, fview);
            }
        });
    }

    private static Diagnostic runEMFValidator(View target) {
        if (target.isSetElement() && target.getElement() != null) {
            return new Diagnostician(){

                public String getObjectLabel(EObject eObject) {
                    return EMFCoreUtil.getQualifiedName((EObject)eObject, (boolean)true);
                }
            }.validate(target.getElement());
        }
        return Diagnostic.OK_INSTANCE;
    }

    private static void validate(DiagramEditPart diagramEditPart, View view) {
        IFile target;
        IFile iFile = target = view.eResource() != null ? WorkspaceSynchronizer.getFile((Resource)view.eResource()) : null;
        if (target != null) {
            ExecutionflowMarkerNavigationProvider.deleteMarkers((IResource)target);
        }
        Diagnostic diagnostic = ValidateAction.runEMFValidator(view);
        ValidateAction.createMarkers(target, diagnostic, diagramEditPart);
        IBatchValidator validator = (IBatchValidator)ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);
        validator.setIncludeLiveConstraints(true);
        if (view.isSetElement() && view.getElement() != null) {
            IStatus status = validator.validate(view.getElement());
            ValidateAction.createMarkers(target, status, diagramEditPart);
        }
    }

    private static void createMarkers(IFile target, IStatus validationStatus, DiagramEditPart diagramEditPart) {
        if (validationStatus.isOK()) {
            return;
        }
        IStatus rootStatus = validationStatus;
        ArrayList allStatuses = new ArrayList();
        ExecutionflowDiagramEditorUtil.LazyElement2ViewMap element2ViewMap = new ExecutionflowDiagramEditorUtil.LazyElement2ViewMap((View)diagramEditPart.getDiagramView(), ValidateAction.collectTargetElements(rootStatus, new HashSet(), allStatuses));
        for (IConstraintStatus nextStatus : allStatuses) {
            View view = ExecutionflowDiagramEditorUtil.findView(diagramEditPart, nextStatus.getTarget(), element2ViewMap);
            ValidateAction.addMarker(diagramEditPart.getViewer(), target, view.eResource().getURIFragment((EObject)view), EMFCoreUtil.getQualifiedName((EObject)nextStatus.getTarget(), (boolean)true), nextStatus.getMessage(), nextStatus.getSeverity());
        }
    }

    private static void createMarkers(IFile target, Diagnostic emfValidationStatus, DiagramEditPart diagramEditPart) {
        if (emfValidationStatus.getSeverity() == 0) {
            return;
        }
        Diagnostic rootStatus = emfValidationStatus;
        ArrayList allDiagnostics = new ArrayList();
        ExecutionflowDiagramEditorUtil.LazyElement2ViewMap element2ViewMap = new ExecutionflowDiagramEditorUtil.LazyElement2ViewMap((View)diagramEditPart.getDiagramView(), ValidateAction.collectTargetElements(rootStatus, new HashSet(), allDiagnostics));
        for (Diagnostic nextDiagnostic : emfValidationStatus.getChildren()) {
            List data = nextDiagnostic.getData();
            if (data == null || data.isEmpty() || !(data.get(0) instanceof EObject)) continue;
            EObject element = (EObject)data.get(0);
            View view = ExecutionflowDiagramEditorUtil.findView(diagramEditPart, element, element2ViewMap);
            ValidateAction.addMarker(diagramEditPart.getViewer(), target, view.eResource().getURIFragment((EObject)view), EMFCoreUtil.getQualifiedName((EObject)element, (boolean)true), nextDiagnostic.getMessage(), ValidateAction.diagnosticToStatusSeverity(nextDiagnostic.getSeverity()));
        }
    }

    private static void addMarker(EditPartViewer viewer, IFile target, String elementId, String location, String message, int statusSeverity) {
        if (target == null) {
            return;
        }
        ExecutionflowMarkerNavigationProvider.addMarker(target, elementId, location, message, statusSeverity);
    }

    private static int diagnosticToStatusSeverity(int diagnosticSeverity) {
        if (diagnosticSeverity == 0) {
            return 0;
        }
        if (diagnosticSeverity == 1) {
            return 1;
        }
        if (diagnosticSeverity == 2) {
            return 2;
        }
        if (diagnosticSeverity == 4 || diagnosticSeverity == 8) {
            return 4;
        }
        return 1;
    }

    private static Set collectTargetElements(IStatus status, Set targetElementCollector, List allConstraintStatuses) {
        if (status instanceof IConstraintStatus) {
            targetElementCollector.add(((IConstraintStatus)status).getTarget());
            allConstraintStatuses.add(status);
        }
        if (status.isMultiStatus()) {
            IStatus[] children = status.getChildren();
            int i = 0;
            while (i < children.length) {
                ValidateAction.collectTargetElements(children[i], targetElementCollector, allConstraintStatuses);
                ++i;
            }
        }
        return targetElementCollector;
    }

    private static Set collectTargetElements(Diagnostic diagnostic, Set targetElementCollector, List allDiagnostics) {
        List data = diagnostic.getData();
        EObject target = null;
        if (data != null && !data.isEmpty() && data.get(0) instanceof EObject) {
            target = (EObject)data.get(0);
            targetElementCollector.add(target);
            allDiagnostics.add(diagnostic);
        }
        if (diagnostic.getChildren() != null && !diagnostic.getChildren().isEmpty()) {
            Iterator it = diagnostic.getChildren().iterator();
            while (it.hasNext()) {
                ValidateAction.collectTargetElements((Diagnostic)it.next(), targetElementCollector, allDiagnostics);
            }
        }
        return targetElementCollector;
    }
}

