/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.common.notify.AdapterFactory
 *  org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain
 *  org.eclipse.emf.edit.provider.IItemPropertySource
 *  org.eclipse.emf.edit.ui.provider.PropertySource
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.emf.transaction.util.TransactionUtil
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.StructuredSelection
 *  org.eclipse.ui.IWorkbenchPart
 *  org.eclipse.ui.views.properties.IPropertySource
 *  org.eclipse.ui.views.properties.IPropertySourceProvider
 */
package com.csi.gmf.executionflow.diagram.sheet;

import java.util.ArrayList;
import java.util.Iterator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

public class ExecutionflowPropertySection
extends AdvancedPropertySection
implements IPropertySourceProvider {
    public IPropertySource getPropertySource(Object object) {
        IItemPropertySource ips;
        if (object instanceof IPropertySource) {
            return (IPropertySource)object;
        }
        AdapterFactory af = this.getAdapterFactory(object);
        if (af != null && (ips = (IItemPropertySource)af.adapt(object, IItemPropertySource.class)) != null) {
            return new PropertySource(object, ips);
        }
        if (object instanceof IAdaptable) {
            return (IPropertySource)((IAdaptable)object).getAdapter(IPropertySource.class);
        }
        return null;
    }

    protected IPropertySourceProvider getPropertySourceProvider() {
        return this;
    }

    protected Object transformSelection(Object selected) {
        View view;
        if (selected instanceof EditPart) {
            Object model = ((EditPart)selected).getModel();
            return model instanceof View ? ((View)model).getElement() : null;
        }
        if (selected instanceof View) {
            return ((View)selected).getElement();
        }
        if (selected instanceof IAdaptable && (view = (View)((IAdaptable)selected).getAdapter(View.class)) != null) {
            return view.getElement();
        }
        return selected;
    }

    public void setInput(IWorkbenchPart part, ISelection selection) {
        if (selection.isEmpty() || !(selection instanceof StructuredSelection)) {
            super.setInput(part, selection);
            return;
        }
        StructuredSelection structuredSelection = (StructuredSelection)selection;
        ArrayList<Object> transformedSelection = new ArrayList<Object>(structuredSelection.size());
        Iterator it = structuredSelection.iterator();
        while (it.hasNext()) {
            Object r = this.transformSelection(it.next());
            if (r == null) continue;
            transformedSelection.add(r);
        }
        super.setInput(part, (ISelection)new StructuredSelection(transformedSelection));
    }

    protected AdapterFactory getAdapterFactory(Object object) {
        if (this.getEditingDomain() instanceof AdapterFactoryEditingDomain) {
            return ((AdapterFactoryEditingDomain)this.getEditingDomain()).getAdapterFactory();
        }
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain((Object)object);
        if (editingDomain != null) {
            return ((AdapterFactoryEditingDomain)editingDomain).getAdapterFactory();
        }
        return null;
    }
}

