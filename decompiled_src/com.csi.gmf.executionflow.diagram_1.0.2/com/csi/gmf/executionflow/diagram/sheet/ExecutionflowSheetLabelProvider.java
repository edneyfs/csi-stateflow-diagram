/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.viewers.DecoratingLabelProvider
 *  org.eclipse.jface.viewers.ILabelProvider
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.swt.graphics.Image
 */
package com.csi.gmf.executionflow.diagram.sheet;

import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowNavigatorGroup;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;

public class ExecutionflowSheetLabelProvider
extends DecoratingLabelProvider {
    public ExecutionflowSheetLabelProvider() {
        super((ILabelProvider)new AdapterFactoryLabelProvider(ExecutionflowDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory()), null);
    }

    public String getText(Object element) {
        Object selected = this.unwrap(element);
        if (selected instanceof ExecutionflowNavigatorGroup) {
            return ((ExecutionflowNavigatorGroup)((Object)selected)).getGroupName();
        }
        return super.getText(selected);
    }

    public Image getImage(Object element) {
        return super.getImage(this.unwrap(element));
    }

    private Object unwrap(Object element) {
        View view;
        if (element instanceof IStructuredSelection) {
            return this.unwrap(((IStructuredSelection)element).getFirstElement());
        }
        if (element instanceof EditPart) {
            return this.unwrapEditPart((EditPart)element);
        }
        if (element instanceof IAdaptable && (view = (View)((IAdaptable)element).getAdapter(View.class)) != null) {
            return this.unwrapView(view);
        }
        return element;
    }

    private Object unwrapEditPart(EditPart p) {
        if (p.getModel() instanceof View) {
            return this.unwrapView((View)p.getModel());
        }
        return p.getModel();
    }

    private Object unwrapView(View view) {
        return view.getElement() == null ? view : view.getElement();
    }
}

