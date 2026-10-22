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
package com.csi.gmf.stateflow.diagram.providers;

import com.csi.gmf.stateflow.diagram.edit.parts.ExecutionFlowObjectEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StartPointEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StateEditPart;
import com.csi.gmf.stateflow.diagram.edit.parts.StateFlowEditPart;
import com.csi.gmf.stateflow.diagram.part.Messages;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;
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

public class StateflowModelingAssistantProvider
extends ModelingAssistantProvider {
    public List getTypesForPopupBar(IAdaptable host) {
        IGraphicalEditPart editPart = (IGraphicalEditPart)host.getAdapter(IGraphicalEditPart.class);
        if (editPart instanceof StateFlowEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            types.add(StateflowElementTypes.ExecutionFlowObject_1001);
            types.add(StateflowElementTypes.State_1002);
            types.add(StateflowElementTypes.StartPoint_1003);
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    public List getRelTypesOnSource(IAdaptable source) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart)source.getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof ExecutionFlowObjectEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            types.add(StateflowElementTypes.FlowObjectConnection_3001);
            return types;
        }
        if (sourceEditPart instanceof StateEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            types.add(StateflowElementTypes.EventConnection_3002);
            return types;
        }
        if (sourceEditPart instanceof StartPointEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            types.add(StateflowElementTypes.StartPointConnection_3003);
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    public List getRelTypesOnTarget(IAdaptable target) {
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart)target.getAdapter(IGraphicalEditPart.class);
        if (targetEditPart instanceof ExecutionFlowObjectEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            types.add(StateflowElementTypes.EventConnection_3002);
            return types;
        }
        if (targetEditPart instanceof StateEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            types.add(StateflowElementTypes.FlowObjectConnection_3001);
            types.add(StateflowElementTypes.StartPointConnection_3003);
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    public List getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart)source.getAdapter(IGraphicalEditPart.class);
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart)target.getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof ExecutionFlowObjectEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            if (targetEditPart instanceof StateEditPart) {
                types.add(StateflowElementTypes.FlowObjectConnection_3001);
            }
            return types;
        }
        if (sourceEditPart instanceof StateEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            if (targetEditPart instanceof ExecutionFlowObjectEditPart) {
                types.add(StateflowElementTypes.EventConnection_3002);
            }
            return types;
        }
        if (sourceEditPart instanceof StartPointEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            if (targetEditPart instanceof StateEditPart) {
                types.add(StateflowElementTypes.StartPointConnection_3003);
            }
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    public List getTypesForSource(IAdaptable target, IElementType relationshipType) {
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart)target.getAdapter(IGraphicalEditPart.class);
        if (targetEditPart instanceof ExecutionFlowObjectEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            if (relationshipType == StateflowElementTypes.EventConnection_3002) {
                types.add(StateflowElementTypes.State_1002);
            }
            return types;
        }
        if (targetEditPart instanceof StateEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            if (relationshipType == StateflowElementTypes.FlowObjectConnection_3001) {
                types.add(StateflowElementTypes.ExecutionFlowObject_1001);
            }
            if (relationshipType == StateflowElementTypes.StartPointConnection_3003) {
                types.add(StateflowElementTypes.StartPoint_1003);
            }
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    public List getTypesForTarget(IAdaptable source, IElementType relationshipType) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart)source.getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof ExecutionFlowObjectEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            if (relationshipType == StateflowElementTypes.FlowObjectConnection_3001) {
                types.add(StateflowElementTypes.State_1002);
            }
            return types;
        }
        if (sourceEditPart instanceof StateEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            if (relationshipType == StateflowElementTypes.EventConnection_3002) {
                types.add(StateflowElementTypes.ExecutionFlowObject_1001);
            }
            return types;
        }
        if (sourceEditPart instanceof StartPointEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>();
            if (relationshipType == StateflowElementTypes.StartPointConnection_3003) {
                types.add(StateflowElementTypes.State_1002);
            }
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
        AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(StateflowDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());
        ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, (ILabelProvider)labelProvider);
        dialog.setMessage(Messages.StateflowModelingAssistantProviderMessage);
        dialog.setTitle(Messages.StateflowModelingAssistantProviderTitle);
        dialog.setMultipleSelection(false);
        dialog.setElements((Object[])elements);
        EObject selected = null;
        if (dialog.open() == 0) {
            selected = (EObject)dialog.getFirstResult();
        }
        return selected;
    }
}

