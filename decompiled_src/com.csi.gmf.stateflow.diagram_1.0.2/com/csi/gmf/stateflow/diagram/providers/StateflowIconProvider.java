/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.gmf.runtime.common.core.service.AbstractProvider
 *  org.eclipse.gmf.runtime.common.core.service.IOperation
 *  org.eclipse.gmf.runtime.common.core.service.IProvider
 *  org.eclipse.gmf.runtime.common.ui.services.icon.GetIconOperation
 *  org.eclipse.gmf.runtime.common.ui.services.icon.IIconProvider
 *  org.eclipse.swt.graphics.Image
 */
package com.csi.gmf.stateflow.diagram.providers;

import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProvider;
import org.eclipse.gmf.runtime.common.ui.services.icon.GetIconOperation;
import org.eclipse.gmf.runtime.common.ui.services.icon.IIconProvider;
import org.eclipse.swt.graphics.Image;

public class StateflowIconProvider
extends AbstractProvider
implements IIconProvider {
    public Image getIcon(IAdaptable hint, int flags) {
        return StateflowElementTypes.getImage(hint);
    }

    public boolean provides(IOperation operation) {
        if (operation instanceof GetIconOperation) {
            return ((GetIconOperation)operation).execute((IProvider)this) != null;
        }
        return false;
    }
}

