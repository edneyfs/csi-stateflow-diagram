/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.common.util.TreeIterator
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry
 *  org.eclipse.gmf.runtime.emf.type.core.IElementType
 *  org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider
 *  org.eclipse.gmf.runtime.notation.Diagram
 *  org.eclipse.jface.viewers.ILabelProvider
 *  org.eclipse.swt.widgets.Display
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.ui.dialogs.ElementListSelectionDialog
 */
package com.csi.gmf.executionflow.diagram.providers;

import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowEditPart;
import com.csi.gmf.executionflow.diagram.edit.parts.StartPointEditPart;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.Messages;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowElementTypes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

public class ExecutionflowModelingAssistantProvider
extends ModelingAssistantProvider {
    public List getTypesForPopupBar(IAdaptable host) {
        IGraphicalEditPart editPart = (IGraphicalEditPart)host.getAdapter(IGraphicalEditPart.class);
        if (editPart instanceof ExecutionFlowEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            types.add(ExecutionflowElementTypes.ExecutionFlowObject_1001);
            types.add(ExecutionflowElementTypes.ExecutableFlowObject_1002);
            types.add(ExecutionflowElementTypes.ExecutionFlowResult_1003);
            types.add(ExecutionflowElementTypes.StartPoint_1004);
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    public List getRelTypesOnSource(IAdaptable source) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart)source.getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof StartPointEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            types.add(ExecutionflowElementTypes.StartPointConnection_3002);
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    public List getRelTypesOnTarget(IAdaptable target) {
        IGraphicalEditPart cfr_ignored_0 = (IGraphicalEditPart)target.getAdapter(IGraphicalEditPart.class);
        return Collections.EMPTY_LIST;
    }

    public List getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart)source.getAdapter(IGraphicalEditPart.class);
        IGraphicalEditPart cfr_ignored_0 = (IGraphicalEditPart)target.getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof StartPointEditPart) {
            ArrayList types = new ArrayList();
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    public List getTypesForSource(IAdaptable target, IElementType relationshipType) {
        IGraphicalEditPart cfr_ignored_0 = (IGraphicalEditPart)target.getAdapter(IGraphicalEditPart.class);
        return Collections.EMPTY_LIST;
    }

    public List getTypesForTarget(IAdaptable source, IElementType relationshipType) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart)source.getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof StartPointEditPart) {
            ArrayList types = new ArrayList();
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    public EObject selectExistingElementForSource(IAdaptable target, IElementType relationshipType) {
        return this.selectExistingElement(target, this.getTypesForSource(target, relationshipType));
    }

    public EObject selectExistingElementForTarget(IAdaptable source, IElementType relationshipType) {
        return this.selectExistingElement(source, this.getTypesForTarget(source, relationshipType));
    }

    protected EObject selectExistingElement(IAdaptable host, Collection types) {
        if (types.isEmpty()) {
            return null;
        }
        IGraphicalEditPart editPart = (IGraphicalEditPart)host.getAdapter(IGraphicalEditPart.class);
        if (editPart == null) {
            return null;
        }
        Diagram diagram = (Diagram)editPart.getRoot().getContents().getModel();
        HashSet<EObject> elements = new HashSet<EObject>();
        TreeIterator it = diagram.getElement().eAllContents();
        while (it.hasNext()) {
            EObject element = (EObject)it.next();
            if (!this.isApplicableElement(element, types)) continue;
            elements.add(element);
        }
        if (elements.isEmpty()) {
            return null;
        }
        return this.selectElement(elements.toArray(new EObject[elements.size()]));
    }

    protected boolean isApplicableElement(EObject element, Collection types) {
        IElementType type = ElementTypeRegistry.getInstance().getElementType(element);
        return types.contains(type);
    }

    protected EObject selectElement(EObject[] elements) {
        Shell shell = Display.getCurrent().getActiveShell();
        AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(ExecutionflowDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());
        ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, (ILabelProvider)labelProvider);
        dialog.setMessage(Messages.ExecutionflowModelingAssistantProviderMessage);
        dialog.setTitle(Messages.ExecutionflowModelingAssistantProviderTitle);
        dialog.setMultipleSelection(false);
        dialog.setElements((Object[])elements);
        EObject selected = null;
        if (dialog.open() == 0) {
            selected = (EObject)dialog.getFirstResult();
        }
        return selected;
    }
}

