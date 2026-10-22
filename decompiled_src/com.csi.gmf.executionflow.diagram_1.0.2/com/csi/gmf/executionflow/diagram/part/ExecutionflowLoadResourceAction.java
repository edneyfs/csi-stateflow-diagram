/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionflowPackage
 *  com.csi.gmf.executionflow.FlowObject
 *  com.csi.gmf.executionflow.FlowObjectConnection
 *  com.csi.gmf.executionflow.SourceConnectable
 *  com.csi.gmf.executionflow.StartPoint
 *  com.csi.gmf.executionflow.StartPointConnection
 *  com.csi.gmf.executionflow.impl.ExecutableFlowObjectImpl
 *  com.csi.gmf.executionflow.impl.ExecutionFlowImpl
 *  com.csi.gmf.executionflow.impl.ExecutionFlowObjectImpl
 *  com.csi.gmf.executionflow.impl.ExecutionFlowResultImpl
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.EStructuralFeature
 *  org.eclipse.emf.edit.domain.EditingDomain
 *  org.eclipse.emf.edit.ui.action.LoadResourceAction$LoadResourceDialog
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy
 *  org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack
 *  org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest
 *  org.eclipse.gmf.runtime.emf.type.core.IElementType
 *  org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand
 *  org.eclipse.gmf.runtime.emf.type.core.commands.CreateRelationshipCommand
 *  org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest
 *  org.eclipse.gmf.runtime.notation.impl.DiagramImpl
 *  org.eclipse.jface.action.IAction
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.ui.IObjectActionDelegate
 *  org.eclipse.ui.IWorkbenchPart
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.FlowObject;
import com.csi.gmf.executionflow.FlowObjectConnection;
import com.csi.gmf.executionflow.SourceConnectable;
import com.csi.gmf.executionflow.StartPoint;
import com.csi.gmf.executionflow.StartPointConnection;
import com.csi.gmf.executionflow.diagram.edit.parts.ExecutionFlowEditPart;
import com.csi.gmf.executionflow.diagram.providers.ExecutionflowElementTypes;
import com.csi.gmf.executionflow.impl.ExecutableFlowObjectImpl;
import com.csi.gmf.executionflow.impl.ExecutionFlowImpl;
import com.csi.gmf.executionflow.impl.ExecutionFlowObjectImpl;
import com.csi.gmf.executionflow.impl.ExecutionFlowResultImpl;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateRelationshipCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ExecutionflowLoadResourceAction
implements IObjectActionDelegate {
    private ExecutionFlowEditPart mySelectedElement;
    private Shell myShell;
    private final String FLOW_FILE_EXTENSION = "executionflow";

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.myShell = targetPart.getSite().getShell();
    }

    private void processFile(URI fileToProcess) throws ParserConfigurationException, SAXException, IOException {
        String attValue = "";
        File file = new File(fileToProcess.toFileString());
        HashMap<String, Object> flowMap = new HashMap<String, Object>();
        if (file.getName().endsWith("executionflow")) {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = docBuilder.parse(file);
            NodeList list = document.getDocumentElement().getChildNodes();
            Node firstNode = list.item(0);
            String title = firstNode.getOwnerDocument().getDocumentElement().getAttribute("title");
            this.fillAttribute((EObject)((ExecutionFlowImpl)((DiagramImpl)this.mySelectedElement.getModel()).getElement()), (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getExecutionFlow_Title(), title);
            String id = firstNode.getOwnerDocument().getDocumentElement().getAttribute("id");
            this.fillAttribute((EObject)((ExecutionFlowImpl)((DiagramImpl)this.mySelectedElement.getModel()).getElement()), (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getExecutionFlow_Id(), id);
            String description = firstNode.getOwnerDocument().getDocumentElement().getAttribute("description");
            this.fillAttribute((EObject)((ExecutionFlowImpl)((DiagramImpl)this.mySelectedElement.getModel()).getElement()), (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getExecutionFlow_Description(), description);
            String requestAuthorization = firstNode.getOwnerDocument().getDocumentElement().getAttribute("requestAuthorization");
            this.fillAttribute((EObject)((ExecutionFlowImpl)((DiagramImpl)this.mySelectedElement.getModel()).getElement()), (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getExecutionFlow_RequestAuthorization(), new Boolean(requestAuthorization));
            DiagramCommandStack stack = this.mySelectedElement.getDiagramEditDomain().getDiagramCommandStack();
            CreateElementRequest stpRequest = new CreateElementRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), ExecutionflowElementTypes.StartPoint_1004);
            CreateElementCommand stpCommand = new CreateElementCommand(stpRequest);
            ICommandProxy stpCommandProxy = new ICommandProxy((ICommand)stpCommand);
            stack.execute((Command)stpCommandProxy);
            StartPoint startPoint = (StartPoint)stpCommand.getNewElement();
            int i = 0;
            while (i < list.getLength()) {
                Element element;
                NamedNodeMap map;
                Node node = list.item(i);
                if (node.getNodeType() == 1 && (map = (element = (Element)node).getAttributes()) != null) {
                    ExecutableFlowObjectImpl exec;
                    ICommandProxy commandProxy;
                    CreateElementCommand command;
                    CreateElementRequest createRequest;
                    if (node.getNodeName().equals("executableFlowObjects")) {
                        createRequest = new CreateElementRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), ExecutionflowElementTypes.ExecutableFlowObject_1002, ExecutionflowPackage.eINSTANCE.getExecutionFlow_ExecutableFlowObjects());
                        command = new CreateElementCommand(createRequest);
                        commandProxy = new ICommandProxy((ICommand)command);
                        stack.execute((Command)commandProxy);
                        exec = (ExecutableFlowObjectImpl)command.getNewElement();
                        flowMap.put(element.getAttribute("pathKey"), exec);
                        attValue = element.getAttribute("className");
                        this.fillAttribute((EObject)exec, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getFlowObject_PathKey(), element.getAttribute("pathKey"));
                        this.fillAttribute((EObject)exec, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getExecutableFlowObject_SimpleClassName(), attValue.substring(attValue.lastIndexOf(".") + 1, attValue.length()));
                        this.fillAttribute((EObject)exec, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getExecutableFlowObject_ClassName(), attValue);
                    } else if (node.getNodeName().equals("executionFlowObjects")) {
                        createRequest = new CreateElementRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), ExecutionflowElementTypes.ExecutionFlowObject_1001, ExecutionflowPackage.eINSTANCE.getExecutionFlow_ExecutionFlowObjects());
                        command = new CreateElementCommand(createRequest);
                        commandProxy = new ICommandProxy((ICommand)command);
                        stack.execute((Command)commandProxy);
                        exec = (ExecutionFlowObjectImpl)command.getNewElement();
                        flowMap.put(element.getAttribute("pathKey"), exec);
                        attValue = element.getAttribute("fileName");
                        this.fillAttribute((EObject)exec, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getExecutionFlowObject_FileName(), attValue);
                        attValue = element.getAttribute("filePath");
                        this.fillAttribute((EObject)exec, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getExecutionFlowObject_FilePath(), attValue);
                    } else if (node.getNodeName().equals("results")) {
                        createRequest = new CreateElementRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), ExecutionflowElementTypes.ExecutionFlowResult_1003, ExecutionflowPackage.eINSTANCE.getExecutionFlow_Results());
                        command = new CreateElementCommand(createRequest);
                        commandProxy = new ICommandProxy((ICommand)command);
                        stack.execute((Command)commandProxy);
                        ExecutionFlowResultImpl result = (ExecutionFlowResultImpl)command.getNewElement();
                        this.fillAttribute((EObject)result, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getFlowObject_PathKey(), new Long(element.getAttribute("pathKey")));
                        this.fillAttribute((EObject)result, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getExecutionFlowResult_ExecutionFlowResultValue(), element.getAttribute("executionFlowResultValue"));
                        flowMap.put(element.getAttribute("pathKey"), result);
                    } else if (node.getNodeName().equals("flowConnections")) {
                        SourceConnectable sourceObject = (SourceConnectable)flowMap.get(element.getAttribute("sourcePathKey"));
                        FlowObject targetObject = (FlowObject)flowMap.get(element.getAttribute("destinationPathKey"));
                        IElementType eType = ExecutionflowElementTypes.FlowObjectConnection_3001;
                        CreateRelationshipRequest req = new CreateRelationshipRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), (EObject)sourceObject, (EObject)targetObject, eType);
                        CreateRelationshipCommand command2 = new CreateRelationshipCommand(req);
                        ICommandProxy commandC = new ICommandProxy((ICommand)command2);
                        stack.execute((Command)commandC);
                        FlowObjectConnection connection = (FlowObjectConnection)command2.getNewElement();
                        this.fillAttribute((EObject)connection, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getFlowObjectConnection_SourceObject(), sourceObject);
                        this.fillAttribute((EObject)connection, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getFlowObjectConnection_SourcePathKey(), sourceObject.getPathKey());
                        this.fillAttribute((EObject)connection, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getFlowObjectConnection_DestinationObject(), targetObject);
                        this.fillAttribute((EObject)connection, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getFlowObjectConnection_DestinationPathKey(), targetObject.getPathKey());
                        this.fillAttribute((EObject)connection, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getFlowObjectConnection_ExecutionFlowResultValue(), element.getAttribute("executionFlowResultValue"));
                    } else if (node.getNodeName().equals("startPointConnection")) {
                        FlowObject targetObject = (FlowObject)flowMap.get(element.getAttribute("destinationObjectPathKey"));
                        IElementType eType = ExecutionflowElementTypes.StartPointConnection_3002;
                        CreateRelationshipRequest req = new CreateRelationshipRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), (EObject)startPoint, (EObject)targetObject, eType);
                        CreateElementCommand connectionCommand = new CreateElementCommand((CreateElementRequest)req);
                        ICommandProxy commandProxyConnection = new ICommandProxy((ICommand)connectionCommand);
                        stack.execute((Command)commandProxyConnection);
                        StartPointConnection connection = (StartPointConnection)connectionCommand.getNewElement();
                        this.fillAttribute((EObject)connection, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getStartPointConnection_StartPoint(), startPoint);
                        this.fillAttribute((EObject)connection, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getStartPointConnection_DestinationObject(), targetObject);
                        this.fillAttribute((EObject)connection, (EStructuralFeature)ExecutionflowPackage.eINSTANCE.getStartPointConnection_DestinationObjectPathKey(), targetObject.getPathKey());
                    }
                }
                ++i;
            }
            this.mySelectedElement.deactivate();
            this.mySelectedElement.activate();
            this.mySelectedElement.refresh();
            ArrangeRequest arrangeRequest = new ArrangeRequest("arrangeAllAction");
            List l = this.mySelectedElement.getChildren();
            arrangeRequest.setPartsToArrange(l);
            Command arrangeCommand = this.mySelectedElement.getCommand((Request)arrangeRequest);
            this.mySelectedElement.getDiagramEditDomain().getDiagramCommandStack().execute(arrangeCommand);
        } else {
            MessageDialog.openError((Shell)this.myShell, (String)"Erro", (String)"Arquivo para importa\u00e7\u00e3o inv\u00e1lido!");
        }
    }

    private void fillAttribute(EObject eObject, EStructuralFeature eFeature, Object value) {
        SetRequest setRequest = new SetRequest(this.mySelectedElement.getEditingDomain(), eObject, eFeature, value);
        SetValueCommand command = new SetValueCommand(setRequest);
        ICommandProxy commandValue = new ICommandProxy((ICommand)command);
        this.mySelectedElement.getDiagramEditDomain().getDiagramCommandStack().execute((Command)commandValue);
    }

    public void run(IAction action) {
        LoadResourceAction.LoadResourceDialog loadResourceDialog = new LoadResourceAction.LoadResourceDialog(this.myShell, (EditingDomain)this.mySelectedElement.getEditingDomain());
        if (loadResourceDialog.open() != 1) {
            List uris = loadResourceDialog.getURIs();
            for (URI element : uris) {
                try {
                    this.processFile(element);
                }
                catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                catch (SAXException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void selectionChanged(IAction action, ISelection selection) {
        IStructuredSelection structuredSelection;
        this.mySelectedElement = null;
        if (selection instanceof IStructuredSelection && (structuredSelection = (IStructuredSelection)selection).size() == 1 && structuredSelection.getFirstElement() instanceof ExecutionFlowEditPart) {
            this.mySelectedElement = (ExecutionFlowEditPart)((Object)structuredSelection.getFirstElement());
        }
        action.setEnabled(this.isEnabled());
    }

    private boolean isEnabled() {
        return this.mySelectedElement != null;
    }
}

