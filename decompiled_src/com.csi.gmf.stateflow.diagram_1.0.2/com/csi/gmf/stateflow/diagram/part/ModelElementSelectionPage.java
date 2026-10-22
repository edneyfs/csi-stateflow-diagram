/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.util.FeatureMap$Entry
 *  org.eclipse.emf.edit.provider.IWrapperItemProvider
 *  org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider
 *  org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
 *  org.eclipse.jface.viewers.IBaseLabelProvider
 *  org.eclipse.jface.viewers.IContentProvider
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.ISelectionChangedListener
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.jface.viewers.SelectionChangedEvent
 *  org.eclipse.jface.viewers.StructuredSelection
 *  org.eclipse.jface.viewers.TreeViewer
 *  org.eclipse.jface.wizard.WizardPage
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 */
package com.csi.gmf.stateflow.diagram.part;

import com.csi.gmf.stateflow.diagram.part.Messages;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

public class ModelElementSelectionPage
extends WizardPage {
    protected EObject selectedModelElement;
    private TreeViewer modelViewer;

    public ModelElementSelectionPage(String pageName) {
        super(pageName);
    }

    public EObject getModelElement() {
        return this.selectedModelElement;
    }

    public void setModelElement(EObject modelElement) {
        this.selectedModelElement = modelElement;
        if (this.modelViewer != null) {
            if (this.selectedModelElement != null) {
                this.modelViewer.setInput((Object)this.selectedModelElement.eResource());
                this.modelViewer.setSelection((ISelection)new StructuredSelection((Object)this.selectedModelElement));
            } else {
                this.modelViewer.setInput(null);
            }
            this.setPageComplete(this.validatePage());
        }
    }

    public void createControl(Composite parent) {
        this.initializeDialogUnits((Control)parent);
        Composite plate = new Composite(parent, 0);
        plate.setLayoutData((Object)new GridData(1808));
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        plate.setLayout((Layout)layout);
        this.setControl((Control)plate);
        Label label = new Label(plate, 0);
        label.setText(this.getSelectionTitle());
        label.setLayoutData((Object)new GridData(32));
        this.modelViewer = new TreeViewer(plate, 2820);
        GridData layoutData = new GridData(1808);
        layoutData.heightHint = 300;
        layoutData.widthHint = 300;
        this.modelViewer.getTree().setLayoutData((Object)layoutData);
        this.modelViewer.setContentProvider((IContentProvider)new AdapterFactoryContentProvider(StateflowDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory()));
        this.modelViewer.setLabelProvider((IBaseLabelProvider)new AdapterFactoryLabelProvider(StateflowDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory()));
        if (this.selectedModelElement != null) {
            this.modelViewer.setInput((Object)this.selectedModelElement.eResource());
            this.modelViewer.setSelection((ISelection)new StructuredSelection((Object)this.selectedModelElement));
        }
        this.modelViewer.addSelectionChangedListener(new ISelectionChangedListener(){

            public void selectionChanged(SelectionChangedEvent event) {
                ModelElementSelectionPage.this.updateSelection((IStructuredSelection)event.getSelection());
            }
        });
        this.setPageComplete(this.validatePage());
    }

    protected String getSelectionTitle() {
        return Messages.ModelElementSelectionPageMessage;
    }

    protected void updateSelection(IStructuredSelection selection) {
        this.selectedModelElement = null;
        if (selection.size() == 1) {
            Object selectedElement = selection.getFirstElement();
            if (selectedElement instanceof IWrapperItemProvider) {
                selectedElement = ((IWrapperItemProvider)selectedElement).getValue();
            }
            if (selectedElement instanceof FeatureMap.Entry) {
                selectedElement = ((FeatureMap.Entry)selectedElement).getValue();
            }
            if (selectedElement instanceof EObject) {
                this.selectedModelElement = (EObject)selectedElement;
            }
        }
        this.setPageComplete(this.validatePage());
    }

    protected boolean validatePage() {
        return true;
    }
}

