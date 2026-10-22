/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.jface.viewers.ComboBoxCellEditor
 *  org.eclipse.swt.widgets.Composite
 */
package com.csi.gmf.executionflow.diagram.edit.parts;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.widgets.Composite;

public class PatchedComboBoxCellEditor
extends ComboBoxCellEditor {
    public PatchedComboBoxCellEditor(Composite parent, String[] editorValues) {
        super(parent, editorValues);
    }

    protected void doSetValue(Object value) {
        super.doSetValue(value);
        this.fireEditorValueChanged(true, true);
    }
}

