/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.gef.Tool
 *  org.eclipse.gef.palette.PaletteContainer
 *  org.eclipse.gef.palette.PaletteEntry
 *  org.eclipse.gef.palette.PaletteGroup
 *  org.eclipse.gef.palette.PaletteRoot
 *  org.eclipse.gef.palette.ToolEntry
 *  org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool
 *  org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool
 *  org.eclipse.gmf.runtime.emf.type.core.IElementType
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.Messages;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowElementTypes;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

public class ExecutionflowPaletteFactory {
    public void fillPalette(PaletteRoot paletteRoot) {
        paletteRoot.add((PaletteEntry)this.createFlowObjects1Group());
        paletteRoot.add((PaletteEntry)this.createConnections2Group());
    }

    private PaletteContainer createFlowObjects1Group() {
        PaletteGroup paletteContainer = new PaletteGroup(Messages.FlowObjects1Group_title);
        paletteContainer.setDescription(Messages.FlowObjects1Group_desc);
        paletteContainer.add((PaletteEntry)this.createStartPoint1CreationTool());
        paletteContainer.add((PaletteEntry)this.createExecutableObject2CreationTool());
        paletteContainer.add((PaletteEntry)this.createFlowObject3CreationTool());
        paletteContainer.add((PaletteEntry)this.createFlowResult4CreationTool());
        return paletteContainer;
    }

    private PaletteContainer createConnections2Group() {
        PaletteGroup paletteContainer = new PaletteGroup(Messages.Connections2Group_title);
        paletteContainer.add((PaletteEntry)this.createFlowLink1CreationTool());
        paletteContainer.add((PaletteEntry)this.createStartPointLink2CreationTool());
        return paletteContainer;
    }

    private ToolEntry createStartPoint1CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(ExecutionflowElementTypes.StartPoint_1004);
        NodeToolEntry entry = new NodeToolEntry(Messages.StartPoint1CreationTool_title, Messages.StartPoint1CreationTool_desc, types);
        entry.setSmallIcon(ExecutionflowElementTypes.getImageDescriptor((IAdaptable)ExecutionflowElementTypes.StartPoint_1004));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    private ToolEntry createExecutableObject2CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(ExecutionflowElementTypes.ExecutableFlowObject_1002);
        NodeToolEntry entry = new NodeToolEntry(Messages.ExecutableObject2CreationTool_title, Messages.ExecutableObject2CreationTool_desc, types);
        entry.setSmallIcon(ExecutionflowDiagramEditorPlugin.findImageDescriptor("/com.csi.gmf.executionflow.edit/icons/full/obj16/ExecutableFlowObject.gif"));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    private ToolEntry createFlowObject3CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(ExecutionflowElementTypes.ExecutionFlowObject_1001);
        NodeToolEntry entry = new NodeToolEntry(Messages.FlowObject3CreationTool_title, Messages.FlowObject3CreationTool_desc, types);
        entry.setSmallIcon(ExecutionflowDiagramEditorPlugin.findImageDescriptor("/com.csi.gmf.executionflow.edit/icons/full/obj16/ExecutionFlowObject.gif"));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    private ToolEntry createFlowResult4CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(ExecutionflowElementTypes.ExecutionFlowResult_1003);
        NodeToolEntry entry = new NodeToolEntry(Messages.FlowResult4CreationTool_title, Messages.FlowResult4CreationTool_desc, types);
        entry.setSmallIcon(ExecutionflowDiagramEditorPlugin.findImageDescriptor("/com.csi.gmf.executionflow.edit/icons/full/obj16/ExecutionFlowResult.gif"));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    private ToolEntry createFlowLink1CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(ExecutionflowElementTypes.FlowObjectConnection_3001);
        LinkToolEntry entry = new LinkToolEntry(Messages.FlowLink1CreationTool_title, Messages.FlowLink1CreationTool_desc, types);
        entry.setSmallIcon(ExecutionflowDiagramEditorPlugin.findImageDescriptor("/com.csi.gmf.executionflow.edit/icons/full/obj16/FlowObjectConnection.gif"));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    private ToolEntry createStartPointLink2CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(ExecutionflowElementTypes.StartPointConnection_3002);
        LinkToolEntry entry = new LinkToolEntry(Messages.StartPointLink2CreationTool_title, Messages.StartPointLink2CreationTool_desc, types);
        entry.setSmallIcon(ExecutionflowDiagramEditorPlugin.findImageDescriptor("/com.csi.gmf.executionflow.edit/icons/full/obj16/FlowObjectConnection.gif"));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    private static class LinkToolEntry
    extends ToolEntry {
        private final List relationshipTypes;

        private LinkToolEntry(String title, String description, List relationshipTypes) {
            super(title, description, null, null);
            this.relationshipTypes = relationshipTypes;
        }

        public Tool createTool() {
            UnspecifiedTypeConnectionTool tool = new UnspecifiedTypeConnectionTool(this.relationshipTypes);
            tool.setProperties(this.getToolProperties());
            return tool;
        }
    }

    private static class NodeToolEntry
    extends ToolEntry {
        private final List elementTypes;

        private NodeToolEntry(String title, String description, List elementTypes) {
            super(title, description, null, null);
            this.elementTypes = elementTypes;
        }

        public Tool createTool() {
            UnspecifiedTypeCreationTool tool = new UnspecifiedTypeCreationTool(this.elementTypes);
            tool.setProperties(this.getToolProperties());
            return tool;
        }
    }
}

