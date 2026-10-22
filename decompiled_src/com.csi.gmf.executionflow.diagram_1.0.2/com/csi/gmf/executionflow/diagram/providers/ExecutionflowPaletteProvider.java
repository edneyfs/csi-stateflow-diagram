/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IConfigurationElement
 *  org.eclipse.gef.palette.PaletteRoot
 *  org.eclipse.gmf.runtime.common.core.service.AbstractProvider
 *  org.eclipse.gmf.runtime.common.core.service.IOperation
 *  org.eclipse.gmf.runtime.diagram.ui.services.palette.IPaletteProvider
 *  org.eclipse.ui.IEditorPart
 */
package com.csi.gmf.executionflow.diagram.providers;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowPaletteFactory;
import java.util.Map;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.IPaletteProvider;
import org.eclipse.ui.IEditorPart;

public class ExecutionflowPaletteProvider
extends AbstractProvider
implements IPaletteProvider {
    public void contributeToPalette(IEditorPart editor, Object content, PaletteRoot root, Map predefinedEntries) {
        ExecutionflowPaletteFactory factory = new ExecutionflowPaletteFactory();
        factory.fillPalette(root);
    }

    public void setContributions(IConfigurationElement configElement) {
    }

    public boolean provides(IOperation operation) {
        return false;
    }
}

