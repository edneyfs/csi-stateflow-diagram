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
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.part.Messages;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;
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

public class StateflowPaletteFactory {
    public void fillPalette(PaletteRoot paletteRoot) {
        paletteRoot.add((PaletteEntry)this.createStateFlow1Group());
        paletteRoot.add((PaletteEntry)this.create2Group());
    }

    private PaletteContainer createStateFlow1Group() {
        PaletteGroup paletteContainer = new PaletteGroup(Messages.StateFlow1Group_title);
        paletteContainer.add((PaletteEntry)this.createStartPoint1CreationTool());
        paletteContainer.add((PaletteEntry)this.createFlowObject2CreationTool());
        paletteContainer.add((PaletteEntry)this.createState3CreationTool());
        return paletteContainer;
    }

    private PaletteContainer create2Group() {
        PaletteGroup paletteContainer = new PaletteGroup(Messages.Group_title);
        paletteContainer.add((PaletteEntry)this.createResultLink1CreationTool());
        paletteContainer.add((PaletteEntry)this.createEventLink2CreationTool());
        paletteContainer.add((PaletteEntry)this.createStartPointLink3CreationTool());
        return paletteContainer;
    }

    private ToolEntry createStartPoint1CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(StateflowElementTypes.StartPoint_1003);
        NodeToolEntry entry = new NodeToolEntry(Messages.StartPoint1CreationTool_title, Messages.StartPoint1CreationTool_desc, types);
        entry.setSmallIcon(StateflowElementTypes.getImageDescriptor((IAdaptable)StateflowElementTypes.StartPoint_1003));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    private ToolEntry createFlowObject2CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(StateflowElementTypes.ExecutionFlowObject_1001);
        NodeToolEntry entry = new NodeToolEntry(Messages.FlowObject2CreationTool_title, Messages.FlowObject2CreationTool_desc, types);
        entry.setSmallIcon(StateflowElementTypes.getImageDescriptor((IAdaptable)StateflowElementTypes.ExecutionFlowObject_1001));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    private ToolEntry createState3CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(StateflowElementTypes.State_1002);
        NodeToolEntry entry = new NodeToolEntry(Messages.State3CreationTool_title, Messages.State3CreationTool_desc, types);
        entry.setSmallIcon(StateflowElementTypes.getImageDescriptor((IAdaptable)StateflowElementTypes.State_1002));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    private ToolEntry createResultLink1CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(StateflowElementTypes.FlowObjectConnection_3001);
        LinkToolEntry entry = new LinkToolEntry(Messages.ResultLink1CreationTool_title, Messages.ResultLink1CreationTool_desc, types);
        entry.setSmallIcon(StateflowElementTypes.getImageDescriptor((IAdaptable)StateflowElementTypes.FlowObjectConnection_3001));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    private ToolEntry createEventLink2CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(StateflowElementTypes.EventConnection_3002);
        LinkToolEntry entry = new LinkToolEntry(Messages.EventLink2CreationTool_title, Messages.EventLink2CreationTool_desc, types);
        entry.setSmallIcon(StateflowElementTypes.getImageDescriptor((IAdaptable)StateflowElementTypes.EventConnection_3002));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    private ToolEntry createStartPointLink3CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(StateflowElementTypes.StartPointConnection_3003);
        LinkToolEntry entry = new LinkToolEntry(Messages.StartPointLink3CreationTool_title, Messages.StartPointLink3CreationTool_desc, types);
        entry.setSmallIcon(StateflowElementTypes.getImageDescriptor((IAdaptable)StateflowElementTypes.StartPointConnection_3003));
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

