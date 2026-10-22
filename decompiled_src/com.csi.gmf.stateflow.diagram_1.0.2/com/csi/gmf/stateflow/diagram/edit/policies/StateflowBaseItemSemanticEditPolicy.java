/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.ExecutionFlowObject
 *  com.csi.gmf.stateflow.StartPoint
 *  com.csi.gmf.stateflow.State
 *  com.csi.gmf.stateflow.StateFlow
 *  org.eclipse.core.commands.operations.IUndoableOperation
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.transaction.TransactionalEditingDomain
 *  org.eclipse.gef.EditPart
 *  org.eclipse.gef.Request
 *  org.eclipse.gef.commands.Command
 *  org.eclipse.gef.commands.CompoundCommand
 *  org.eclipse.gef.commands.UnexecutableCommand
 *  org.eclipse.gef.requests.ReconnectRequest
 *  org.eclipse.gmf.runtime.common.core.command.ICommand
 *  org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand
 *  org.eclipse.gmf.runtime.diagram.core.util.ViewUtil
 *  org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy
 *  org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy
 *  org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart
 *  org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy
 *  org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper
 *  org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand
 *  org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry
 *  org.eclipse.gmf.runtime.emf.type.core.IEditHelperContext
 *  org.eclipse.gmf.runtime.emf.type.core.IElementType
 *  org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest
 *  org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest
 *  org.eclipse.gmf.runtime.notation.Edge
 *  org.eclipse.gmf.runtime.notation.View
 */
package com.csi.gmf.stateflow.diagram.edit.policies;

import com.csi.gmf.stateflow.ExecutionFlowObject;
import com.csi.gmf.stateflow.StartPoint;
import com.csi.gmf.stateflow.State;
import com.csi.gmf.stateflow.StateFlow;
import com.csi.gmf.stateflow.diagram.part.StateflowVisualIDRegistry;
import java.util.Collections;
import java.util.Iterator;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IEditHelperContext;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

public class StateflowBaseItemSemanticEditPolicy
extends SemanticEditPolicy {
    public static final String VISUAL_ID_KEY = "visual_id";

    public Command getCommand(Request request) {
        Object view;
        if (request instanceof ReconnectRequest && (view = ((ReconnectRequest)request).getConnectionEditPart().getModel()) instanceof View) {
            Integer id = new Integer(StateflowVisualIDRegistry.getVisualID((View)view));
            request.getExtendedData().put(VISUAL_ID_KEY, id);
        }
        return super.getCommand(request);
    }

    protected int getVisualID(IEditCommandRequest request) {
        Object id = request.getParameter(VISUAL_ID_KEY);
        return id instanceof Integer ? (Integer)id : -1;
    }

    protected Command getSemanticCommand(IEditCommandRequest request) {
        TransactionalEditingDomain editingDomain;
        IElementType elementType;
        IEditCommandRequest completedRequest = this.completeRequest(request);
        Object editHelperContext = completedRequest.getEditHelperContext();
        if (editHelperContext instanceof View || editHelperContext instanceof IEditHelperContext && ((IEditHelperContext)editHelperContext).getEObject() instanceof View) {
            return null;
        }
        if (editHelperContext == null) {
            editHelperContext = ViewUtil.resolveSemanticElement((View)((View)this.getHost().getModel()));
        }
        if ((elementType = ElementTypeRegistry.getInstance().getElementType(editHelperContext)) == ElementTypeRegistry.getInstance().getType("org.eclipse.gmf.runtime.emf.type.core.default")) {
            elementType = null;
        }
        Command semanticCommand = this.getSemanticCommandSwitch(completedRequest);
        if (elementType != null) {
            ICommand command;
            if (semanticCommand != null) {
                command = semanticCommand instanceof ICommandProxy ? ((ICommandProxy)semanticCommand).getICommand() : new CommandProxy(semanticCommand);
                completedRequest.setParameter("edit policy command", (Object)command);
            }
            if ((command = elementType.getEditCommand(completedRequest)) != null) {
                if (!(command instanceof CompositeTransactionalCommand)) {
                    editingDomain = ((IGraphicalEditPart)this.getHost()).getEditingDomain();
                    command = new CompositeTransactionalCommand(editingDomain, command.getLabel()).compose((IUndoableOperation)command);
                }
                semanticCommand = new ICommandProxy(command);
            }
        }
        boolean shouldProceed = true;
        if (completedRequest instanceof DestroyRequest) {
            shouldProceed = this.shouldProceed((DestroyRequest)completedRequest);
        }
        if (shouldProceed) {
            if (completedRequest instanceof DestroyRequest) {
                editingDomain = ((IGraphicalEditPart)this.getHost()).getEditingDomain();
                ICommandProxy deleteViewCommand = new ICommandProxy((ICommand)new DeleteCommand(editingDomain, (View)this.getHost().getModel()));
                semanticCommand = semanticCommand == null ? deleteViewCommand : semanticCommand.chain((Command)deleteViewCommand);
            }
            return semanticCommand;
        }
        return null;
    }

    protected Command getSemanticCommandSwitch(IEditCommandRequest req) {
        if (req instanceof CreateRelationshipRequest) {
            return this.getCreateRelationshipCommand((CreateRelationshipRequest)req);
        }
        if (req instanceof CreateElementRequest) {
            return this.getCreateCommand((CreateElementRequest)req);
        }
        if (req instanceof ConfigureRequest) {
            return this.getConfigureCommand((ConfigureRequest)req);
        }
        if (req instanceof DestroyElementRequest) {
            return this.getDestroyElementCommand((DestroyElementRequest)req);
        }
        if (req instanceof DestroyReferenceRequest) {
            return this.getDestroyReferenceCommand((DestroyReferenceRequest)req);
        }
        if (req instanceof DuplicateElementsRequest) {
            return this.getDuplicateCommand((DuplicateElementsRequest)req);
        }
        if (req instanceof GetEditContextRequest) {
            return this.getEditContextCommand((GetEditContextRequest)req);
        }
        if (req instanceof MoveRequest) {
            return this.getMoveCommand((MoveRequest)req);
        }
        if (req instanceof ReorientReferenceRelationshipRequest) {
            return this.getReorientReferenceRelationshipCommand((ReorientReferenceRelationshipRequest)req);
        }
        if (req instanceof ReorientRelationshipRequest) {
            return this.getReorientRelationshipCommand((ReorientRelationshipRequest)req);
        }
        if (req instanceof SetRequest) {
            return this.getSetCommand((SetRequest)req);
        }
        return null;
    }

    protected Command getConfigureCommand(ConfigureRequest req) {
        return null;
    }

    protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
        return null;
    }

    protected Command getCreateCommand(CreateElementRequest req) {
        return null;
    }

    protected Command getSetCommand(SetRequest req) {
        return null;
    }

    protected Command getEditContextCommand(GetEditContextRequest req) {
        return null;
    }

    protected Command getDestroyElementCommand(DestroyElementRequest req) {
        return null;
    }

    protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
        return null;
    }

    protected Command getDuplicateCommand(DuplicateElementsRequest req) {
        return null;
    }

    protected Command getMoveCommand(MoveRequest req) {
        return null;
    }

    protected Command getReorientReferenceRelationshipCommand(ReorientReferenceRelationshipRequest req) {
        return UnexecutableCommand.INSTANCE;
    }

    protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
        return UnexecutableCommand.INSTANCE;
    }

    protected final Command getGEFWrapper(ICommand cmd) {
        return new ICommandProxy(cmd);
    }

    protected final Command getMSLWrapper(ICommand cmd) {
        return this.getGEFWrapper(cmd);
    }

    protected EObject getSemanticElement() {
        return ViewUtil.resolveSemanticElement((View)((View)this.getHost().getModel()));
    }

    protected TransactionalEditingDomain getEditingDomain() {
        return ((IGraphicalEditPart)this.getHost()).getEditingDomain();
    }

    protected Command getDestroyElementCommand(View view) {
        EditPart editPart = (EditPart)this.getHost().getViewer().getEditPartRegistry().get(view);
        DestroyElementRequest request = new DestroyElementRequest(this.getEditingDomain(), false);
        return editPart.getCommand((Request)new EditCommandRequestWrapper((IEditCommandRequest)request, Collections.EMPTY_MAP));
    }

    protected CompoundCommand getDestroyEdgesCommand() {
        CompoundCommand cmd = new CompoundCommand();
        View view = (View)this.getHost().getModel();
        Iterator it = view.getSourceEdges().iterator();
        while (it.hasNext()) {
            cmd.add(this.getDestroyElementCommand((View)((Edge)it.next())));
        }
        it = view.getTargetEdges().iterator();
        while (it.hasNext()) {
            cmd.add(this.getDestroyElementCommand((View)((Edge)it.next())));
        }
        return cmd;
    }

    protected void addDestroyShortcutsCommand(CompoundCommand command) {
        View view = (View)this.getHost().getModel();
        if (view.getEAnnotation("Shortcut") != null) {
            return;
        }
        for (View nextView : view.getDiagram().getChildren()) {
            if (nextView.getEAnnotation("Shortcut") == null || !nextView.isSetElement() || nextView.getElement() != view.getElement()) continue;
            command.add(this.getDestroyElementCommand(nextView));
        }
    }

    public static class LinkConstraints {
        public static boolean canCreateFlowObjectConnection_3001(StateFlow container, ExecutionFlowObject source, State target) {
            return LinkConstraints.canExistFlowObjectConnection_3001(container, source, target);
        }

        public static boolean canCreateEventConnection_3002(StateFlow container, State source, ExecutionFlowObject target) {
            return LinkConstraints.canExistEventConnection_3002(container, source, target);
        }

        public static boolean canCreateStartPointConnection_3003(StateFlow container, StartPoint source, State target) {
            if (container != null && container.getStartPointConnection() != null) {
                return false;
            }
            return LinkConstraints.canExistStartPointConnection_3003(container, source, target);
        }

        public static boolean canExistFlowObjectConnection_3001(StateFlow container, ExecutionFlowObject source, State target) {
            return true;
        }

        public static boolean canExistEventConnection_3002(StateFlow container, State source, ExecutionFlowObject target) {
            return true;
        }

        public static boolean canExistStartPointConnection_3003(StateFlow container, StartPoint source, State target) {
            return true;
        }
    }
}

