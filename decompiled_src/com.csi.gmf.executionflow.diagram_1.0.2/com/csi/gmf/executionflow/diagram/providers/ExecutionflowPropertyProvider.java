/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gmf.runtime.common.core.service.IOperation
 *  org.eclipse.gmf.runtime.common.ui.services.properties.GetPropertySourceOperation
 *  org.eclipse.gmf.runtime.common.ui.services.properties.ICompositePropertySource
 *  org.eclipse.gmf.runtime.common.ui.services.properties.IPropertiesProvider
 *  org.eclipse.gmf.runtime.emf.ui.properties.providers.GenericEMFPropertiesProvider
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.executionflow.diagram.providers;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.properties.GetPropertySourceOperation;
import org.eclipse.gmf.runtime.common.ui.services.properties.ICompositePropertySource;
import org.eclipse.gmf.runtime.common.ui.services.properties.IPropertiesProvider;
import org.eclipse.gmf.runtime.emf.ui.properties.providers.GenericEMFPropertiesProvider;
import org.eclipse.gmf.runtime.notation.View;

public class ExecutionflowPropertyProvider
extends GenericEMFPropertiesProvider
implements IPropertiesProvider {
    public boolean provides(IOperation operation) {
        if (operation instanceof GetPropertySourceOperation) {
            Object object = ((GetPropertySourceOperation)operation).getObject();
            return this.getSemanticElement(object) != null;
        }
        return false;
    }

    public ICompositePropertySource getPropertySource(Object object) {
        EObject element = this.getSemanticElement(object);
        if (element != null) {
            return super.getPropertySource((Object)element);
        }
        return null;
    }

    protected EObject getSemanticElement(Object object) {
        EditPart editPart;
        View view = null;
        if (object instanceof View) {
            view = (View)object;
        } else if (object instanceof EditPart && (editPart = (EditPart)object).getModel() instanceof View) {
            view = (View)editPart.getModel();
        }
        if (view != null && "Executionflow".equals(ExecutionflowVisualIDRegistry.getModelID(view))) {
            return view.getElement();
        }
        return null;
    }
}

