/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.viewers.ViewerSorter
 */
package com.csi.gmf.executionflow.diagram.navigator;

import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowNavigatorItem;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import org.eclipse.jface.viewers.ViewerSorter;

public class ExecutionflowNavigatorSorter
extends ViewerSorter {
    private static final int GROUP_CATEGORY = 3004;

    public int category(Object element) {
        if (element instanceof ExecutionflowNavigatorItem) {
            ExecutionflowNavigatorItem item = (ExecutionflowNavigatorItem)((Object)element);
            return ExecutionflowVisualIDRegistry.getVisualID(item.getView());
        }
        return 3004;
    }
}

