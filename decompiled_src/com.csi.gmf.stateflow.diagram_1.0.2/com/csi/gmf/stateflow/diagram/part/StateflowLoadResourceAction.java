/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.EventConnection
 *  com.csi.gmf.stateflow.FlowObject
 *  com.csi.gmf.stateflow.FlowObjectConnection
 *  com.csi.gmf.stateflow.StartPoint
 *  com.csi.gmf.stateflow.StartPointConnection
 *  com.csi.gmf.stateflow.State
 *  com.csi.gmf.stateflow.StateflowPackage
 *  com.csi.gmf.stateflow.impl.ExecutionFlowObjectImpl
 *  com.csi.gmf.stateflow.impl.StateFlowImpl
 *  com.csi.gmf.stateflow.impl.StateImpl
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
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.EventConnection;
import com.csi.gmf.stateflow.FlowObject;
import com.csi.gmf.stateflow.FlowObjectConnection;
import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.StartPointConnection;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.diagram.edit.parts.StateFlowEditPart;
import com.csi.gmf.stateflow.diagram.providers.StateflowElementTypes;
import com.csi.gmf.stateflow.impl.ExecutionFlowObjectImpl;
import com.csi.gmf.stateflow.impl.StateFlowImpl;
import com.csi.gmf.stateflow.impl.StateImpl;
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

public class StateflowLoadResourceAction
implements IObjectActionDelegate {
    private StateFlowEditPart mySelectedElement;
    private Shell myShell;
    private final String STATE_FILE_EXTENSION = "stateflow";

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.myShell = targetPart.getSite().getShell();
    }

    private void processFile(URI fileToProcess) throws ParserConfigurationException, SAXException, IOException {
        try {
            File file = new File(fileToProcess.toFileString());
            HashMap<String, Object> objectMap = new HashMap<String, Object>();
            HashMap<String, StateImpl> stateMap = new HashMap<String, StateImpl>();
            if (file.getName().endsWith("stateflow")) {
                DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = docBuilder.parse(file);
                NodeList list = document.getDocumentElement().getChildNodes();
                Node firstNode = list.item(0);
                String id = firstNode.getOwnerDocument().getDocumentElement().getAttribute("id");
                this.fillAttribute((EObject)((StateFlowImpl)((DiagramImpl)this.mySelectedElement.getModel()).getElement()), (EStructuralFeature)StateflowPackage.eINSTANCE.getStateFlow_Id(), id);
                String description = firstNode.getOwnerDocument().getDocumentElement().getAttribute("description");
                this.fillAttribute((EObject)((StateFlowImpl)((DiagramImpl)this.mySelectedElement.getModel()).getElement()), (EStructuralFeature)StateflowPackage.eINSTANCE.getStateFlow_Description(), description);
                Boolean isVolatile = Boolean.valueOf(firstNode.getOwnerDocument().getDocumentElement().getAttribute("volatile"));
                this.fillAttribute((EObject)((StateFlowImpl)((DiagramImpl)this.mySelectedElement.getModel()).getElement()), (EStructuralFeature)StateflowPackage.eINSTANCE.getStateFlow_Volatile(), isVolatile);
                DiagramCommandStack stack = this.mySelectedElement.getDiagramEditDomain().getDiagramCommandStack();
                int i = 0;
                while (i < list.getLength()) {
                    Element element;
                    NamedNodeMap map;
                    Node node = list.item(i);
                    if (node.getNodeType() == 1 && (map = (element = (Element)node).getAttributes()) != null) {
                        EventConnection connection;
                        ICommandProxy commandProxyConnection;
                        CreateElementCommand connectionCommand;
                        CreateRelationshipRequest req;
                        IElementType eType;
                        FlowObject targetObject;
                        State sourceObject;
                        ICommandProxy commandProxy;
                        CreateElementCommand command;
                        CreateElementRequest createRequest;
                        if (node.getNodeName().equals("states")) {
                            createRequest = new CreateElementRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), StateflowElementTypes.State_1002, StateflowPackage.eINSTANCE.getStateFlow_States());
                            command = new CreateElementCommand(createRequest);
                            commandProxy = new ICommandProxy((ICommand)command);
                            stack.execute((Command)commandProxy);
                            StateImpl state = (StateImpl)command.getNewElement();
                            objectMap.put(element.getAttribute("pathKey"), state);
                            stateMap.put(element.getAttribute("id"), state);
                            this.fillAttribute((EObject)state, (EStructuralFeature)StateflowPackage.eINSTANCE.getFlowObject_PathKey(), new Long(element.getAttribute("pathKey")));
                            this.fillAttribute((EObject)state, (EStructuralFeature)StateflowPackage.eINSTANCE.getState_Id(), element.getAttribute("id"));
                            this.fillAttribute((EObject)state, (EStructuralFeature)StateflowPackage.eINSTANCE.getState_Description(), element.getAttribute("description"));
                        } else if (node.getNodeName().equals("executionFlows")) {
                            createRequest = new CreateElementRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), StateflowElementTypes.ExecutionFlowObject_1001, StateflowPackage.eINSTANCE.getStateFlow_ExecutionFlows());
                            command = new CreateElementCommand(createRequest);
                            commandProxy = new ICommandProxy((ICommand)command);
                            stack.execute((Command)commandProxy);
                            ExecutionFlowObjectImpl flow = (ExecutionFlowObjectImpl)command.getNewElement();
                            objectMap.put(element.getAttribute("pathKey"), flow);
                            this.fillAttribute((EObject)flow, (EStructuralFeature)StateflowPackage.eINSTANCE.getFlowObject_PathKey(), new Long(element.getAttribute("pathKey")));
                            this.fillAttribute((EObject)flow, (EStructuralFeature)StateflowPackage.eINSTANCE.getExecutionFlowObject_FilePath(), element.getAttribute("filePath"));
                            this.fillAttribute((EObject)flow, (EStructuralFeature)StateflowPackage.eINSTANCE.getExecutionFlowObject_Title(), element.getAttribute("title"));
                            this.fillAttribute((EObject)flow, (EStructuralFeature)StateflowPackage.eINSTANCE.getExecutionFlowObject_Id(), element.getAttribute("id"));
                            this.fillAttribute((EObject)flow, (EStructuralFeature)StateflowPackage.eINSTANCE.getExecutionFlowObject_Description(), element.getAttribute("description"));
                        } else if (node.getNodeName().equals("startPointConnection")) {
                            createRequest = new CreateElementRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), StateflowElementTypes.StartPoint_1003);
                            CreateElementCommand startCommand = new CreateElementCommand(createRequest);
                            ICommandProxy commandProxyStart = new ICommandProxy((ICommand)startCommand);
                            stack.execute((Command)commandProxyStart);
                            StartPoint sourceObject2 = (StartPoint)startCommand.getNewElement();
                            FlowObject targetObject2 = (FlowObject)stateMap.get(element.getAttribute("destinationStateId"));
                            IElementType eType2 = StateflowElementTypes.StartPointConnection_3003;
                            CreateRelationshipRequest req2 = new CreateRelationshipRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), (EObject)sourceObject2, (EObject)targetObject2, eType2);
                            CreateElementCommand connectionCommand2 = new CreateElementCommand((CreateElementRequest)req2);
                            ICommandProxy commandProxyConnection2 = new ICommandProxy((ICommand)connectionCommand2);
                            stack.execute((Command)commandProxyConnection2);
                            StartPointConnection connection2 = (StartPointConnection)connectionCommand2.getNewElement();
                            this.fillAttribute((EObject)connection2, (EStructuralFeature)StateflowPackage.eINSTANCE.getStartPointConnection_StartPoint(), sourceObject2);
                            this.fillAttribute((EObject)connection2, (EStructuralFeature)StateflowPackage.eINSTANCE.getStartPointConnection_DestinationState(), targetObject2);
                            this.fillAttribute((EObject)connection2, (EStructuralFeature)StateflowPackage.eINSTANCE.getStartPointConnection_DestinationStateId(), ((State)targetObject2).getId());
                        } else if (node.getNodeName().equals("stateConnections")) {
                            sourceObject = (State)objectMap.get(element.getAttribute("sourcePathKey"));
                            targetObject = (FlowObject)objectMap.get(element.getAttribute("destinationPathKey"));
                            eType = StateflowElementTypes.EventConnection_3002;
                            req = new CreateRelationshipRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), (EObject)sourceObject, (EObject)targetObject, eType);
                            connectionCommand = new CreateElementCommand((CreateElementRequest)req);
                            commandProxyConnection = new ICommandProxy((ICommand)connectionCommand);
                            stack.execute((Command)commandProxyConnection);
                            connection = (EventConnection)connectionCommand.getNewElement();
                            this.fillAttribute((EObject)connection, (EStructuralFeature)StateflowPackage.eINSTANCE.getEventConnection_SourceState(), sourceObject);
                            this.fillAttribute((EObject)connection, (EStructuralFeature)StateflowPackage.eINSTANCE.getEventConnection_DestinationFlow(), targetObject);
                            this.fillAttribute((EObject)connection, (EStructuralFeature)StateflowPackage.eINSTANCE.getEventConnection_EventID(), element.getAttribute("eventID"));
                            this.fillAttribute((EObject)connection, (EStructuralFeature)StateflowPackage.eINSTANCE.getEventConnection_SourcePathKey(), sourceObject.getPathKey());
                            this.fillAttribute((EObject)connection, (EStructuralFeature)StateflowPackage.eINSTANCE.getEventConnection_DestinationPathKey(), targetObject.getPathKey());
                        } else if (node.getNodeName().equals("flowConnections")) {
                            sourceObject = (FlowObject)objectMap.get(element.getAttribute("sourcePathKey"));
                            targetObject = (State)objectMap.get(element.getAttribute("destinationPathKey"));
                            eType = StateflowElementTypes.FlowObjectConnection_3001;
                            req = new CreateRelationshipRequest(this.mySelectedElement.getEditingDomain(), ((DiagramImpl)this.mySelectedElement.getModel()).getElement(), (EObject)sourceObject, (EObject)targetObject, eType);
                            connectionCommand = new CreateElementCommand((CreateElementRequest)req);
                            commandProxyConnection = new ICommandProxy((ICommand)connectionCommand);
                            stack.execute((Command)commandProxyConnection);
                            connection = (FlowObjectConnection)connectionCommand.getNewElement();
                            this.fillAttribute((EObject)connection, (EStructuralFeature)StateflowPackage.eINSTANCE.getFlowObjectConnection_SourceFlow(), sourceObject);
                            this.fillAttribute((EObject)connection, (EStructuralFeature)StateflowPackage.eINSTANCE.getFlowObjectConnection_DestinationState(), targetObject);
                            this.fillAttribute((EObject)connection, (EStructuralFeature)StateflowPackage.eINSTANCE.getFlowObjectConnection_ExecutionFlowResultValue(), element.getAttribute("executionFlowResultValue"));
                            this.fillAttribute((EObject)connection, (EStructuralFeature)StateflowPackage.eINSTANCE.getFlowObjectConnection_SourcePathKey(), sourceObject.getPathKey());
                            this.fillAttribute((EObject)connection, (EStructuralFeature)StateflowPackage.eINSTANCE.getFlowObjectConnection_DestinationPathKey(), targetObject.getPathKey());
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
        catch (Throwable e) {
            e.printStackTrace();
            MessageDialog.openError((Shell)this.myShell, (String)"Erro", (String)e.getMessage());
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
        if (selection instanceof IStructuredSelection && (structuredSelection = (IStructuredSelection)selection).size() == 1 && structuredSelection.getFirstElement() instanceof StateFlowEditPart) {
            this.mySelectedElement = (StateFlowEditPart)((Object)structuredSelection.getFirstElement());
        }
        action.setEnabled(this.isEnabled());
    }

    private boolean isEnabled() {
        return this.mySelectedElement != null;
    }
}

