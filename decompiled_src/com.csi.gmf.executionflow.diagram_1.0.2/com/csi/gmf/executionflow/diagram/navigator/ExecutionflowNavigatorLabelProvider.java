/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionFlow
 *  com.csi.gmf.executionflow.StartPointConnection
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.common.ui.services.parser.IParser
 *  org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions
 *  org.eclipse.gmf.runtime.common.ui.services.parser.ParserService
 *  org.eclipse.gmf.runtime.emf.type.core.IElementType
 *  org.eclipse.gmf.runtime.notation.View
 *  org.eclipse.jface.resource.ImageDescriptor
 *  org.eclipse.jface.resource.ImageRegistry
 *  org.eclipse.jface.viewers.ITreePathLabelProvider
 *  org.eclipse.jface.viewers.LabelProvider
 *  org.eclipse.jface.viewers.TreePath
 *  org.eclipse.jface.viewers.ViewerLabel
 *  org.eclipse.swt.graphics.Image
 *  org.eclipse.ui.IMemento
 *  org.eclipse.ui.navigator.ICommonContentExtensionSite
 *  org.eclipse.ui.navigator.ICommonLabelProvider
 */
package com.csi.gmf.executionflow.diagram.navigator;

import com.csi.gmf.executionflow.ExecutionFlow;
import com.csi.gmf.executionflow.StartPointConnection;
import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowNavigatorGroup;
import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowNavigatorItem;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowVisualIDRegistry;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowElementTypes;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowParserProvider;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

public class ExecutionflowNavigatorLabelProvider
extends LabelProvider
implements ICommonLabelProvider,
ITreePathLabelProvider {
    static {
        ExecutionflowDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor());
        ExecutionflowDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor());
    }

    public void updateLabel(ViewerLabel label, TreePath elementPath) {
        Object element = elementPath.getLastSegment();
        if (element instanceof ExecutionflowNavigatorItem && !this.isOwnView(((ExecutionflowNavigatorItem)((Object)element)).getView())) {
            return;
        }
        label.setText(this.getText(element));
        label.setImage(this.getImage(element));
    }

    public Image getImage(Object element) {
        if (element instanceof ExecutionflowNavigatorGroup) {
            ExecutionflowNavigatorGroup group = (ExecutionflowNavigatorGroup)((Object)element);
            return ExecutionflowDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
        }
        if (element instanceof ExecutionflowNavigatorItem) {
            ExecutionflowNavigatorItem navigatorItem = (ExecutionflowNavigatorItem)((Object)element);
            if (!this.isOwnView(navigatorItem.getView())) {
                return super.getImage(element);
            }
            return this.getImage(navigatorItem.getView());
        }
        return super.getImage(element);
    }

    public Image getImage(View view) {
        switch (ExecutionflowVisualIDRegistry.getVisualID(view)) {
            case 79: {
                return this.getImage("Navigator?Diagram?http:///com/csi/gmf/executionflow.ecore?ExecutionFlow", ExecutionflowElementTypes.ExecutionFlow_79);
            }
            case 1001: {
                return this.getImage("Navigator?TopLevelNode?http:///com/csi/gmf/executionflow.ecore?ExecutionFlowObject", ExecutionflowElementTypes.ExecutionFlowObject_1001);
            }
            case 1002: {
                return this.getImage("Navigator?TopLevelNode?http:///com/csi/gmf/executionflow.ecore?ExecutableFlowObject", ExecutionflowElementTypes.ExecutableFlowObject_1002);
            }
            case 1003: {
                return this.getImage("Navigator?TopLevelNode?http:///com/csi/gmf/executionflow.ecore?ExecutionFlowResult", ExecutionflowElementTypes.ExecutionFlowResult_1003);
            }
            case 1004: {
                return this.getImage("Navigator?TopLevelNode?http:///com/csi/gmf/executionflow.ecore?StartPoint", ExecutionflowElementTypes.StartPoint_1004);
            }
            case 3001: {
                return this.getImage("Navigator?Link?http:///com/csi/gmf/executionflow.ecore?FlowObjectConnection", ExecutionflowElementTypes.FlowObjectConnection_3001);
            }
            case 3002: {
                return this.getImage("Navigator?Link?http:///com/csi/gmf/executionflow.ecore?StartPointConnection", ExecutionflowElementTypes.StartPointConnection_3002);
            }
        }
        return this.getImage("Navigator?UnknownElement", null);
    }

    private Image getImage(String key, IElementType elementType) {
        ImageRegistry imageRegistry = ExecutionflowDiagramEditorPlugin.getInstance().getImageRegistry();
        Image image = imageRegistry.get(key);
        if (image == null && elementType != null && ExecutionflowElementTypes.isKnownElementType(elementType)) {
            image = ExecutionflowElementTypes.getImage((IAdaptable)elementType);
            imageRegistry.put(key, image);
        }
        if (image == null) {
            image = imageRegistry.get("Navigator?ImageNotFound");
            imageRegistry.put(key, image);
        }
        return image;
    }

    public String getText(Object element) {
        if (element instanceof ExecutionflowNavigatorGroup) {
            ExecutionflowNavigatorGroup group = (ExecutionflowNavigatorGroup)((Object)element);
            return group.getGroupName();
        }
        if (element instanceof ExecutionflowNavigatorItem) {
            ExecutionflowNavigatorItem navigatorItem = (ExecutionflowNavigatorItem)((Object)element);
            if (!this.isOwnView(navigatorItem.getView())) {
                return null;
            }
            return this.getText(navigatorItem.getView());
        }
        return super.getText(element);
    }

    public String getText(View view) {
        if (view.getElement() != null && view.getElement().eIsProxy()) {
            return this.getUnresolvedDomainElementProxyText(view);
        }
        switch (ExecutionflowVisualIDRegistry.getVisualID(view)) {
            case 79: {
                return this.getExecutionFlow_79Text(view);
            }
            case 1001: {
                return this.getExecutionFlowObject_1001Text(view);
            }
            case 1002: {
                return this.getExecutableFlowObject_1002Text(view);
            }
            case 1003: {
                return this.getExecutionFlowResult_1003Text(view);
            }
            case 1004: {
                return this.getStartPoint_1004Text(view);
            }
            case 3001: {
                return this.getFlowObjectConnection_3001Text(view);
            }
            case 3002: {
                return this.getStartPointConnection_3002Text(view);
            }
        }
        return this.getUnknownElementText(view);
    }

    private String getExecutionFlow_79Text(View view) {
        ExecutionFlow domainModelElement = (ExecutionFlow)view.getElement();
        if (domainModelElement != null) {
            return domainModelElement.getId();
        }
        ExecutionflowDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = 79");
        return "";
    }

    private String getExecutionFlowObject_1001Text(View view) {
        ExecutionflowParserProvider.HintAdapter hintAdapter = new ExecutionflowParserProvider.HintAdapter(ExecutionflowElementTypes.ExecutionFlowObject_1001, (EObject)(view.getElement() != null ? view.getElement() : view), ExecutionflowVisualIDRegistry.getType(4001));
        IParser parser = ParserService.getInstance().getParser((IAdaptable)hintAdapter);
        if (parser != null) {
            return parser.getPrintString((IAdaptable)hintAdapter, ParserOptions.NONE.intValue());
        }
        ExecutionflowDiagramEditorPlugin.getInstance().logError("Parser was not found for label 4001");
        return "";
    }

    private String getExecutableFlowObject_1002Text(View view) {
        ExecutionflowParserProvider.HintAdapter hintAdapter = new ExecutionflowParserProvider.HintAdapter(ExecutionflowElementTypes.ExecutableFlowObject_1002, (EObject)(view.getElement() != null ? view.getElement() : view), ExecutionflowVisualIDRegistry.getType(4002));
        IParser parser = ParserService.getInstance().getParser((IAdaptable)hintAdapter);
        if (parser != null) {
            return parser.getPrintString((IAdaptable)hintAdapter, ParserOptions.NONE.intValue());
        }
        ExecutionflowDiagramEditorPlugin.getInstance().logError("Parser was not found for label 4002");
        return "";
    }

    private String getExecutionFlowResult_1003Text(View view) {
        ExecutionflowParserProvider.HintAdapter hintAdapter = new ExecutionflowParserProvider.HintAdapter(ExecutionflowElementTypes.ExecutionFlowResult_1003, (EObject)(view.getElement() != null ? view.getElement() : view), ExecutionflowVisualIDRegistry.getType(4003));
        IParser parser = ParserService.getInstance().getParser((IAdaptable)hintAdapter);
        if (parser != null) {
            return parser.getPrintString((IAdaptable)hintAdapter, ParserOptions.NONE.intValue());
        }
        ExecutionflowDiagramEditorPlugin.getInstance().logError("Parser was not found for label 4003");
        return "";
    }

    private String getStartPoint_1004Text(View view) {
        return "";
    }

    private String getFlowObjectConnection_3001Text(View view) {
        ExecutionflowParserProvider.HintAdapter hintAdapter = new ExecutionflowParserProvider.HintAdapter(ExecutionflowElementTypes.FlowObjectConnection_3001, (EObject)(view.getElement() != null ? view.getElement() : view), ExecutionflowVisualIDRegistry.getType(4004));
        IParser parser = ParserService.getInstance().getParser((IAdaptable)hintAdapter);
        if (parser != null) {
            return parser.getPrintString((IAdaptable)hintAdapter, ParserOptions.NONE.intValue());
        }
        ExecutionflowDiagramEditorPlugin.getInstance().logError("Parser was not found for label 4004");
        return "";
    }

    private String getStartPointConnection_3002Text(View view) {
        StartPointConnection domainModelElement = (StartPointConnection)view.getElement();
        if (domainModelElement != null) {
            return String.valueOf(domainModelElement.getDestinationObjectPathKey());
        }
        ExecutionflowDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = 3002");
        return "";
    }

    private String getUnknownElementText(View view) {
        return "<UnknownElement Visual_ID = " + view.getType() + ">";
    }

    private String getUnresolvedDomainElementProxyText(View view) {
        return "<Unresolved domain element Visual_ID = " + view.getType() + ">";
    }

    public void init(ICommonContentExtensionSite aConfig) {
    }

    public void restoreState(IMemento aMemento) {
    }

    public void saveState(IMemento aMemento) {
    }

    public String getDescription(Object anElement) {
        return null;
    }

    private boolean isOwnView(View view) {
        return "Executionflow".equals(ExecutionflowVisualIDRegistry.getModelID(view));
    }
}

