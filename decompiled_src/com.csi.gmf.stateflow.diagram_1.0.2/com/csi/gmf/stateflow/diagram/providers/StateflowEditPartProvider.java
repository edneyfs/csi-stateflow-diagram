/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.EditPartFactory
 *  org.eclipse.gmf.runtime.common.core.service.IOperation
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider
 *  org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateGraphicEditPartOperation
 *  org.eclipse.gmf.runtime.diagram.ui.services.editpart.IEditPartOperation
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.providers;

import com.csi.gmf.stateflow.diagram.edit.parts.StateflowEditPartFactory;
import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
import java.lang.ref.WeakReference;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateGraphicEditPartOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.IEditPartOperation;
import org.eclipse.gmf.runtime.notation.View;

public class StateflowEditPartProvider
extends AbstractEditPartProvider {
    private EditPartFactory factory;
    private boolean allowCaching;
    private WeakReference cachedPart;
    private WeakReference cachedView;

    public StateflowEditPartProvider() {
        this.setFactory(new StateflowEditPartFactory());
        this.setAllowCaching(true);
    }

    public final EditPartFactory getFactory() {
        return this.factory;
    }

    protected void setFactory(EditPartFactory factory) {
        this.factory = factory;
    }

    public final boolean isAllowCaching() {
        return this.allowCaching;
    }

    protected synchronized void setAllowCaching(boolean allowCaching) {
        this.allowCaching = allowCaching;
        if (!allowCaching) {
            this.cachedPart = null;
            this.cachedView = null;
        }
    }

    protected IGraphicalEditPart createEditPart(View view) {
        EditPart part = this.factory.createEditPart(null, (Object)view);
        if (part instanceof IGraphicalEditPart) {
            return (IGraphicalEditPart)part;
        }
        return null;
    }

    protected IGraphicalEditPart getCachedPart(View view) {
        if (this.cachedView != null && this.cachedView.get() == view) {
            return (IGraphicalEditPart)this.cachedPart.get();
        }
        return null;
    }

    public synchronized IGraphicalEditPart createGraphicEditPart(View view) {
        if (this.isAllowCaching()) {
            IGraphicalEditPart part = this.getCachedPart(view);
            this.cachedPart = null;
            this.cachedView = null;
            if (part != null) {
                return part;
            }
        }
        return this.createEditPart(view);
    }

    public synchronized boolean provides(IOperation operation) {
        if (operation instanceof CreateGraphicEditPartOperation) {
            View view = ((IEditPartOperation)operation).getView();
            if (!"Stateflow".equals(StateflowVisualIDRegistry.getModelID(view))) {
                return false;
            }
            if (this.isAllowCaching() && this.getCachedPart(view) != null) {
                return true;
            }
            IGraphicalEditPart part = this.createEditPart(view);
            if (part != null) {
                if (this.isAllowCaching()) {
                    this.cachedPart = new WeakReference<IGraphicalEditPart>(part);
                    this.cachedView = new WeakReference<View>(view);
                }
                return true;
            }
        }
        return false;
    }
}

