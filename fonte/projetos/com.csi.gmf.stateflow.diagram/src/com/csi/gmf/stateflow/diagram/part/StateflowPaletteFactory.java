package com.csi.gmf.stateflow.diagram.part;

/**
 * @generated
 */
public class StateflowPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(org.eclipse.gef.palette.PaletteRoot paletteRoot) {
		paletteRoot.add(createStateFlow1Group());
		paletteRoot.add(create2Group());
	}

	/**
	 * Creates "State Flow" palette tool group
	 * 
	 * @generated
	 */
	private org.eclipse.gef.palette.PaletteContainer createStateFlow1Group() {
		org.eclipse.gef.palette.PaletteGroup paletteContainer = new org.eclipse.gef.palette.PaletteGroup(
				com.csi.gmf.stateflow.diagram.part.Messages.StateFlow1Group_title);
		paletteContainer.add(createStartPoint1CreationTool());
		paletteContainer.add(createFlowObject2CreationTool());
		paletteContainer.add(createState3CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "" palette tool group
	 * 
	 * @generated
	 */
	private org.eclipse.gef.palette.PaletteContainer create2Group() {
		org.eclipse.gef.palette.PaletteGroup paletteContainer = new org.eclipse.gef.palette.PaletteGroup(
				com.csi.gmf.stateflow.diagram.part.Messages.Group_title);
		paletteContainer.add(createResultLink1CreationTool());
		paletteContainer.add(createEventLink2CreationTool());
		paletteContainer.add(createStartPointLink3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private org.eclipse.gef.palette.ToolEntry createStartPoint1CreationTool() {
		java.util.List/* <IElementType> */types = new java.util.ArrayList/*
																		 * <IElementType
																		 * >
																		 */(1);
		types
				.add(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.StartPoint_1003);
		NodeToolEntry entry = new NodeToolEntry(
				com.csi.gmf.stateflow.diagram.part.Messages.StartPoint1CreationTool_title,
				com.csi.gmf.stateflow.diagram.part.Messages.StartPoint1CreationTool_desc,
				types);
		entry
				.setSmallIcon(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes
						.getImageDescriptor(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.StartPoint_1003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private org.eclipse.gef.palette.ToolEntry createFlowObject2CreationTool() {
		java.util.List/* <IElementType> */types = new java.util.ArrayList/*
																		 * <IElementType
																		 * >
																		 */(1);
		types
				.add(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.ExecutionFlowObject_1001);
		NodeToolEntry entry = new NodeToolEntry(
				com.csi.gmf.stateflow.diagram.part.Messages.FlowObject2CreationTool_title,
				com.csi.gmf.stateflow.diagram.part.Messages.FlowObject2CreationTool_desc,
				types);
		entry
				.setSmallIcon(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes
						.getImageDescriptor(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.ExecutionFlowObject_1001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private org.eclipse.gef.palette.ToolEntry createState3CreationTool() {
		java.util.List/* <IElementType> */types = new java.util.ArrayList/*
																		 * <IElementType
																		 * >
																		 */(1);
		types
				.add(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.State_1002);
		NodeToolEntry entry = new NodeToolEntry(
				com.csi.gmf.stateflow.diagram.part.Messages.State3CreationTool_title,
				com.csi.gmf.stateflow.diagram.part.Messages.State3CreationTool_desc,
				types);
		entry
				.setSmallIcon(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes
						.getImageDescriptor(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.State_1002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private org.eclipse.gef.palette.ToolEntry createResultLink1CreationTool() {
		java.util.List/* <IElementType> */types = new java.util.ArrayList/*
																		 * <IElementType
																		 * >
																		 */(1);
		types
				.add(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.FlowObjectConnection_3001);
		LinkToolEntry entry = new LinkToolEntry(
				com.csi.gmf.stateflow.diagram.part.Messages.ResultLink1CreationTool_title,
				com.csi.gmf.stateflow.diagram.part.Messages.ResultLink1CreationTool_desc,
				types);
		entry
				.setSmallIcon(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes
						.getImageDescriptor(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.FlowObjectConnection_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private org.eclipse.gef.palette.ToolEntry createEventLink2CreationTool() {
		java.util.List/* <IElementType> */types = new java.util.ArrayList/*
																		 * <IElementType
																		 * >
																		 */(1);
		types
				.add(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.EventConnection_3002);
		LinkToolEntry entry = new LinkToolEntry(
				com.csi.gmf.stateflow.diagram.part.Messages.EventLink2CreationTool_title,
				com.csi.gmf.stateflow.diagram.part.Messages.EventLink2CreationTool_desc,
				types);
		entry
				.setSmallIcon(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes
						.getImageDescriptor(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.EventConnection_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private org.eclipse.gef.palette.ToolEntry createStartPointLink3CreationTool() {
		java.util.List/* <IElementType> */types = new java.util.ArrayList/*
																		 * <IElementType
																		 * >
																		 */(1);
		types
				.add(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.StartPointConnection_3003);
		LinkToolEntry entry = new LinkToolEntry(
				com.csi.gmf.stateflow.diagram.part.Messages.StartPointLink3CreationTool_title,
				com.csi.gmf.stateflow.diagram.part.Messages.StartPointLink3CreationTool_desc,
				types);
		entry
				.setSmallIcon(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes
						.getImageDescriptor(com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes.StartPointConnection_3003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends
			org.eclipse.gef.palette.ToolEntry {

		/**
		 * @generated
		 */
		private final java.util.List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				java.util.List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public org.eclipse.gef.Tool createTool() {
			org.eclipse.gef.Tool tool = new org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool(
					elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends
			org.eclipse.gef.palette.ToolEntry {

		/**
		 * @generated
		 */
		private final java.util.List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				java.util.List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public org.eclipse.gef.Tool createTool() {
			org.eclipse.gef.Tool tool = new org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool(
					relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
