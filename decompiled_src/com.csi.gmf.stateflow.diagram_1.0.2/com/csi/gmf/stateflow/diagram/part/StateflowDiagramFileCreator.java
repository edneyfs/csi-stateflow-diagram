/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.resources.IWorkspaceRoot
 *  org.eclipse.core.resources.ResourcesPlugin
 *  org.eclipse.core.runtime.IPath
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.util.IDEEditorFileCreator
 *  org.eclipse.gmf.runtime.diagram.ui.resources.editor.util.DiagramFileCreator
 */
package com.csi.gmf.stateflow.diagram.part;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.util.IDEEditorFileCreator;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.util.DiagramFileCreator;

public class StateflowDiagramFileCreator
extends IDEEditorFileCreator {
    private static StateflowDiagramFileCreator INSTANCE = new StateflowDiagramFileCreator();

    public static DiagramFileCreator getInstance() {
        return INSTANCE;
    }

    public String getExtension() {
        return ".stateflow_diagram";
    }

    public String getUniqueFileName(IPath containerPath, String fileName) {
        int nFileNumber = 1;
        String newFileName = fileName = this.removeExtensionFromFileName(fileName);
        IPath diagramFilePath = containerPath.append(this.appendExtensionToFileName(newFileName));
        IPath modelFilePath = containerPath.append(this.appendExtensionToModelFileName(newFileName));
        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        while (workspaceRoot.exists(diagramFilePath) || workspaceRoot.exists(modelFilePath)) {
            newFileName = String.valueOf(fileName) + ++nFileNumber;
            diagramFilePath = containerPath.append(this.appendExtensionToFileName(newFileName));
            modelFilePath = containerPath.append(this.appendExtensionToModelFileName(newFileName));
        }
        return newFileName;
    }

    private String removeExtensionFromFileName(String fileName) {
        if (fileName.endsWith(this.getExtension())) {
            return fileName.substring(0, fileName.length() - this.getExtension().length());
        }
        return fileName;
    }

    private String appendExtensionToModelFileName(String fileName) {
        return String.valueOf(fileName) + ".stateflow";
    }
}

