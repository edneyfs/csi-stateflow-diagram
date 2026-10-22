/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.common.ui.viewer.IViewerProvider
 *  org.eclipse.emf.edit.domain.EditingDomain
 *  org.eclipse.emf.edit.domain.IEditingDomainProvider
 *  org.eclipse.emf.edit.ui.action.ControlAction
 *  org.eclipse.emf.edit.ui.action.CreateChildAction
 *  org.eclipse.emf.edit.ui.action.CreateSiblingAction
 *  org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor
 *  org.eclipse.emf.edit.ui.action.LoadResourceAction
 *  org.eclipse.emf.edit.ui.action.ValidateAction
 *  org.eclipse.jface.action.Action
 *  org.eclipse.jface.action.ActionContributionItem
 *  org.eclipse.jface.action.IAction
 *  org.eclipse.jface.action.IContributionItem
 *  org.eclipse.jface.action.IContributionManager
 *  org.eclipse.jface.action.IMenuListener
 *  org.eclipse.jface.action.IMenuManager
 *  org.eclipse.jface.action.IToolBarManager
 *  org.eclipse.jface.action.MenuManager
 *  org.eclipse.jface.action.Separator
 *  org.eclipse.jface.action.SubContributionItem
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.ISelectionChangedListener
 *  org.eclipse.jface.viewers.ISelectionProvider
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.jface.viewers.SelectionChangedEvent
 *  org.eclipse.jface.viewers.Viewer
 *  org.eclipse.ui.IEditorPart
 *  org.eclipse.ui.PartInitException
 */
package com.csi.gmf.stateflow.presentation;

import com.csi.gmf.stateflow.presentation.StateflowEditorPlugin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;

public class StateflowActionBarContributor
extends EditingDomainActionBarContributor
implements ISelectionChangedListener {
    protected IEditorPart activeEditorPart;
    protected ISelectionProvider selectionProvider;
    protected IAction showPropertiesViewAction = new Action(StateflowEditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item")){

        public void run() {
            try {
                StateflowActionBarContributor.this.getPage().showView("org.eclipse.ui.views.PropertySheet");
            }
            catch (PartInitException exception) {
                StateflowEditorPlugin.INSTANCE.log((Object)exception);
            }
        }
    };
    protected IAction refreshViewerAction = new Action(StateflowEditorPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item")){

        public boolean isEnabled() {
            return StateflowActionBarContributor.this.activeEditorPart instanceof IViewerProvider;
        }

        public void run() {
            Viewer viewer;
            if (StateflowActionBarContributor.this.activeEditorPart instanceof IViewerProvider && (viewer = ((IViewerProvider)StateflowActionBarContributor.this.activeEditorPart).getViewer()) != null) {
                viewer.refresh();
            }
        }
    };
    protected Collection createChildActions;
    protected IMenuManager createChildMenuManager;
    protected Collection createSiblingActions;
    protected IMenuManager createSiblingMenuManager;

    public StateflowActionBarContributor() {
        super(1);
        this.loadResourceAction = new LoadResourceAction();
        this.validateAction = new ValidateAction();
        this.controlAction = new ControlAction();
    }

    public void contributeToToolBar(IToolBarManager toolBarManager) {
        toolBarManager.add((IContributionItem)new Separator("stateflow-settings"));
        toolBarManager.add((IContributionItem)new Separator("stateflow-additions"));
    }

    public void contributeToMenu(IMenuManager menuManager) {
        super.contributeToMenu(menuManager);
        MenuManager submenuManager = new MenuManager(StateflowEditorPlugin.INSTANCE.getString("_UI_StateflowEditor_menu"), "com.csi.gmf.stateflowMenuID");
        menuManager.insertAfter("additions", (IContributionItem)submenuManager);
        submenuManager.add((IContributionItem)new Separator("settings"));
        submenuManager.add((IContributionItem)new Separator("actions"));
        submenuManager.add((IContributionItem)new Separator("additions"));
        submenuManager.add((IContributionItem)new Separator("additions-end"));
        this.createChildMenuManager = new MenuManager(StateflowEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
        submenuManager.insertBefore("additions", (IContributionItem)this.createChildMenuManager);
        this.createSiblingMenuManager = new MenuManager(StateflowEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
        submenuManager.insertBefore("additions", (IContributionItem)this.createSiblingMenuManager);
        submenuManager.addMenuListener(new IMenuListener(){

            public void menuAboutToShow(IMenuManager menuManager) {
                menuManager.updateAll(true);
            }
        });
        this.addGlobalActions((IMenuManager)submenuManager);
    }

    public void setActiveEditor(IEditorPart part) {
        super.setActiveEditor(part);
        this.activeEditorPart = part;
        if (this.selectionProvider != null) {
            this.selectionProvider.removeSelectionChangedListener((ISelectionChangedListener)this);
        }
        if (part == null) {
            this.selectionProvider = null;
        } else {
            this.selectionProvider = part.getSite().getSelectionProvider();
            this.selectionProvider.addSelectionChangedListener((ISelectionChangedListener)this);
            if (this.selectionProvider.getSelection() != null) {
                this.selectionChanged(new SelectionChangedEvent(this.selectionProvider, this.selectionProvider.getSelection()));
            }
        }
    }

    public void selectionChanged(SelectionChangedEvent event) {
        if (this.createChildMenuManager != null) {
            this.depopulateManager((IContributionManager)this.createChildMenuManager, this.createChildActions);
        }
        if (this.createSiblingMenuManager != null) {
            this.depopulateManager((IContributionManager)this.createSiblingMenuManager, this.createSiblingActions);
        }
        Collection newChildDescriptors = null;
        Collection newSiblingDescriptors = null;
        ISelection selection = event.getSelection();
        if (selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1) {
            Object object = ((IStructuredSelection)selection).getFirstElement();
            EditingDomain domain = ((IEditingDomainProvider)this.activeEditorPart).getEditingDomain();
            newChildDescriptors = domain.getNewChildDescriptors(object, null);
            newSiblingDescriptors = domain.getNewChildDescriptors(null, object);
        }
        this.createChildActions = this.generateCreateChildActions(newChildDescriptors, selection);
        this.createSiblingActions = this.generateCreateSiblingActions(newSiblingDescriptors, selection);
        if (this.createChildMenuManager != null) {
            this.populateManager((IContributionManager)this.createChildMenuManager, this.createChildActions, null);
            this.createChildMenuManager.update(true);
        }
        if (this.createSiblingMenuManager != null) {
            this.populateManager((IContributionManager)this.createSiblingMenuManager, this.createSiblingActions, null);
            this.createSiblingMenuManager.update(true);
        }
    }

    protected Collection generateCreateChildActions(Collection descriptors, ISelection selection) {
        ArrayList<CreateChildAction> actions = new ArrayList<CreateChildAction>();
        if (descriptors != null) {
            Iterator i = descriptors.iterator();
            while (i.hasNext()) {
                actions.add(new CreateChildAction(this.activeEditorPart, selection, i.next()));
            }
        }
        return actions;
    }

    protected Collection generateCreateSiblingActions(Collection descriptors, ISelection selection) {
        ArrayList<CreateSiblingAction> actions = new ArrayList<CreateSiblingAction>();
        if (descriptors != null) {
            Iterator i = descriptors.iterator();
            while (i.hasNext()) {
                actions.add(new CreateSiblingAction(this.activeEditorPart, selection, i.next()));
            }
        }
        return actions;
    }

    protected void populateManager(IContributionManager manager, Collection actions, String contributionID) {
        if (actions != null) {
            for (IAction action : actions) {
                if (contributionID != null) {
                    manager.insertBefore(contributionID, action);
                    continue;
                }
                manager.add(action);
            }
        }
    }

    protected void depopulateManager(IContributionManager manager, Collection actions) {
        if (actions != null) {
            IContributionItem[] items = manager.getItems();
            int i = 0;
            while (i < items.length) {
                IAction action;
                IContributionItem contributionItem = items[i];
                while (contributionItem instanceof SubContributionItem) {
                    contributionItem = ((SubContributionItem)contributionItem).getInnerItem();
                }
                if (contributionItem instanceof ActionContributionItem && actions.contains(action = ((ActionContributionItem)contributionItem).getAction())) {
                    manager.remove(contributionItem);
                }
                ++i;
            }
        }
    }

    public void menuAboutToShow(IMenuManager menuManager) {
        super.menuAboutToShow(menuManager);
        MenuManager submenuManager = null;
        submenuManager = new MenuManager(StateflowEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
        this.populateManager((IContributionManager)submenuManager, this.createChildActions, null);
        menuManager.insertBefore("edit", (IContributionItem)submenuManager);
        submenuManager = new MenuManager(StateflowEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
        this.populateManager((IContributionManager)submenuManager, this.createSiblingActions, null);
        menuManager.insertBefore("edit", (IContributionItem)submenuManager);
    }

    protected void addGlobalActions(IMenuManager menuManager) {
        menuManager.insertAfter("additions-end", (IContributionItem)new Separator("ui-actions"));
        menuManager.insertAfter("ui-actions", this.showPropertiesViewAction);
        this.refreshViewerAction.setEnabled(this.refreshViewerAction.isEnabled());
        menuManager.insertAfter("ui-actions", this.refreshViewerAction);
        super.addGlobalActions(menuManager);
    }

    protected boolean removeAllReferencesOnDelete() {
        return true;
    }
}

