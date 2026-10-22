/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.StateflowFactory
 *  com.csi.gmf.stateflow.StateflowPackage
 *  com.csi.gmf.stateflow.provider.StateflowEditPlugin
 *  org.eclipse.core.resources.IContainer
 *  org.eclipse.core.resources.IFile
 *  org.eclipse.core.resources.IFolder
 *  org.eclipse.core.resources.IProject
 *  org.eclipse.core.resources.IResource
 *  org.eclipse.core.resources.ResourcesPlugin
 *  org.eclipse.core.runtime.IProgressMonitor
 *  org.eclipse.core.runtime.Path
 *  org.eclipse.emf.common.util.URI
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EClassifier
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.emf.ecore.resource.Resource
 *  org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
 *  org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry
 *  org.eclipse.jface.dialogs.MessageDialog
 *  org.eclipse.jface.operation.IRunnableWithProgress
 *  org.eclipse.jface.viewers.ISelection
 *  org.eclipse.jface.viewers.IStructuredSelection
 *  org.eclipse.jface.viewers.StructuredSelection
 *  org.eclipse.jface.wizard.IWizardPage
 *  org.eclipse.jface.wizard.Wizard
 *  org.eclipse.jface.wizard.WizardPage
 *  org.eclipse.swt.events.ModifyEvent
 *  org.eclipse.swt.events.ModifyListener
 *  org.eclipse.swt.layout.GridData
 *  org.eclipse.swt.layout.GridLayout
 *  org.eclipse.swt.widgets.Combo
 *  org.eclipse.swt.widgets.Composite
 *  org.eclipse.swt.widgets.Control
 *  org.eclipse.swt.widgets.Label
 *  org.eclipse.swt.widgets.Layout
 *  org.eclipse.swt.widgets.Shell
 *  org.eclipse.ui.IEditorInput
 *  org.eclipse.ui.INewWizard
 *  org.eclipse.ui.IWorkbench
 *  org.eclipse.ui.IWorkbenchPage
 *  org.eclipse.ui.IWorkbenchPart
 *  org.eclipse.ui.IWorkbenchWindow
 *  org.eclipse.ui.PartInitException
 *  org.eclipse.ui.actions.WorkspaceModifyOperation
 *  org.eclipse.ui.dialogs.WizardNewFileCreationPage
 *  org.eclipse.ui.part.FileEditorInput
 *  org.eclipse.ui.part.ISetSelectionTarget
 */
package com.csi.gmf.stateflow.presentation;

import com.csi.gmf.stateflow.StateflowFactory;
import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.presentation.StateflowEditorPlugin;
import com.csi.gmf.stateflow.provider.StateflowEditPlugin;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.StringTokenizer;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

public class StateflowModelWizard
extends Wizard
implements INewWizard {
    public static final List FILE_EXTENSIONS = Collections.unmodifiableList(Arrays.asList(StateflowEditorPlugin.INSTANCE.getString("_UI_StateflowEditorFilenameExtensions").split("\\s*,\\s*")));
    public static final String FORMATTED_FILE_EXTENSIONS = StateflowEditorPlugin.INSTANCE.getString("_UI_StateflowEditorFilenameExtensions").replaceAll("\\s*,\\s*", ", ");
    protected StateflowPackage stateflowPackage = StateflowPackage.eINSTANCE;
    protected StateflowFactory stateflowFactory = this.stateflowPackage.getStateflowFactory();
    protected StateflowModelWizardNewFileCreationPage newFileCreationPage;
    protected StateflowModelWizardInitialObjectCreationPage initialObjectCreationPage;
    protected IStructuredSelection selection;
    protected IWorkbench workbench;
    protected List initialObjectNames;

    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        this.setWindowTitle(StateflowEditorPlugin.INSTANCE.getString("_UI_Wizard_label"));
        this.setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(StateflowEditorPlugin.INSTANCE.getImage("full/wizban/NewStateflow")));
    }

    protected Collection getInitialObjectNames() {
        if (this.initialObjectNames == null) {
            this.initialObjectNames = new ArrayList();
            for (EClassifier eClassifier : this.stateflowPackage.getEClassifiers()) {
                EClass eClass;
                if (!(eClassifier instanceof EClass) || (eClass = (EClass)eClassifier).isAbstract()) continue;
                this.initialObjectNames.add(eClass.getName());
            }
            Collections.sort(this.initialObjectNames, Collator.getInstance());
        }
        return this.initialObjectNames;
    }

    protected EObject createInitialModel() {
        EClass eClass = (EClass)this.stateflowPackage.getEClassifier(this.initialObjectCreationPage.getInitialObjectName());
        EObject rootObject = this.stateflowFactory.create(eClass);
        return rootObject;
    }

    public boolean performFinish() {
        try {
            final IFile modelFile = this.getModelFile();
            WorkspaceModifyOperation operation = new WorkspaceModifyOperation(){

                protected void execute(IProgressMonitor progressMonitor) {
                    try {
                        try {
                            ResourceSetImpl resourceSet = new ResourceSetImpl();
                            URI fileURI = URI.createPlatformResourceURI((String)modelFile.getFullPath().toString(), (boolean)true);
                            Resource resource = resourceSet.createResource(fileURI);
                            EObject rootObject = StateflowModelWizard.this.createInitialModel();
                            if (rootObject != null) {
                                resource.getContents().add((Object)rootObject);
                            }
                            HashMap<String, String> options = new HashMap<String, String>();
                            options.put("ENCODING", StateflowModelWizard.this.initialObjectCreationPage.getEncoding());
                            resource.save(options);
                        }
                        catch (Exception exception) {
                            StateflowEditorPlugin.INSTANCE.log(exception);
                            progressMonitor.done();
                        }
                    }
                    finally {
                        progressMonitor.done();
                    }
                }
            };
            this.getContainer().run(false, false, (IRunnableWithProgress)operation);
            IWorkbenchWindow workbenchWindow = this.workbench.getActiveWorkbenchWindow();
            IWorkbenchPage page = workbenchWindow.getActivePage();
            final IWorkbenchPart activePart = page.getActivePart();
            if (activePart instanceof ISetSelectionTarget) {
                StructuredSelection targetSelection = new StructuredSelection((Object)modelFile);
                this.getShell().getDisplay().asyncExec(new Runnable((ISelection)targetSelection){
                    private final /* synthetic */ ISelection val$targetSelection;
                    {
                        this.val$targetSelection = iSelection;
                    }

                    @Override
                    public void run() {
                        ((ISetSelectionTarget)activePart).selectReveal(this.val$targetSelection);
                    }
                });
            }
            try {
                page.openEditor((IEditorInput)new FileEditorInput(modelFile), this.workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString()).getId());
            }
            catch (PartInitException exception) {
                MessageDialog.openError((Shell)workbenchWindow.getShell(), (String)StateflowEditorPlugin.INSTANCE.getString("_UI_OpenEditorError_label"), (String)exception.getMessage());
                return false;
            }
            return true;
        }
        catch (Exception exception) {
            StateflowEditorPlugin.INSTANCE.log(exception);
            return false;
        }
    }

    public void addPages() {
        Object selectedElement;
        this.newFileCreationPage = new StateflowModelWizardNewFileCreationPage("Whatever", this.selection);
        this.newFileCreationPage.setTitle(StateflowEditorPlugin.INSTANCE.getString("_UI_StateflowModelWizard_label"));
        this.newFileCreationPage.setDescription(StateflowEditorPlugin.INSTANCE.getString("_UI_StateflowModelWizard_description"));
        this.newFileCreationPage.setFileName(String.valueOf(StateflowEditorPlugin.INSTANCE.getString("_UI_StateflowEditorFilenameDefaultBase")) + "." + (String)FILE_EXTENSIONS.get(0));
        this.addPage((IWizardPage)this.newFileCreationPage);
        if (this.selection != null && !this.selection.isEmpty() && (selectedElement = this.selection.iterator().next()) instanceof IResource) {
            IResource selectedResource = (IResource)selectedElement;
            if (selectedResource.getType() == 1) {
                selectedResource = selectedResource.getParent();
            }
            if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
                this.newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());
                String defaultModelBaseFilename = StateflowEditorPlugin.INSTANCE.getString("_UI_StateflowEditorFilenameDefaultBase");
                String defaultModelFilenameExtension = (String)FILE_EXTENSIONS.get(0);
                String modelFilename = String.valueOf(defaultModelBaseFilename) + "." + defaultModelFilenameExtension;
                int i = 1;
                while (((IContainer)selectedResource).findMember(modelFilename) != null) {
                    modelFilename = String.valueOf(defaultModelBaseFilename) + i + "." + defaultModelFilenameExtension;
                    ++i;
                }
                this.newFileCreationPage.setFileName(modelFilename);
            }
        }
        this.initialObjectCreationPage = new StateflowModelWizardInitialObjectCreationPage("Whatever2");
        this.initialObjectCreationPage.setTitle(StateflowEditorPlugin.INSTANCE.getString("_UI_StateflowModelWizard_label"));
        this.initialObjectCreationPage.setDescription(StateflowEditorPlugin.INSTANCE.getString("_UI_Wizard_initial_object_description"));
        this.addPage((IWizardPage)this.initialObjectCreationPage);
    }

    public IFile getModelFile() {
        return this.newFileCreationPage.getModelFile();
    }

    public class StateflowModelWizardInitialObjectCreationPage
    extends WizardPage {
        protected Combo initialObjectField;
        protected List encodings;
        protected Combo encodingField;
        protected ModifyListener validator;

        public StateflowModelWizardInitialObjectCreationPage(String pageId) {
            super(pageId);
            this.validator = new ModifyListener(){

                public void modifyText(ModifyEvent e) {
                    StateflowModelWizardInitialObjectCreationPage.this.setPageComplete(StateflowModelWizardInitialObjectCreationPage.this.validatePage());
                }
            };
        }

        public void createControl(Composite parent) {
            Composite composite = new Composite(parent, 0);
            GridLayout layout = new GridLayout();
            layout.numColumns = 1;
            layout.verticalSpacing = 12;
            composite.setLayout((Layout)layout);
            GridData data = new GridData();
            data.verticalAlignment = 4;
            data.grabExcessVerticalSpace = true;
            data.horizontalAlignment = 4;
            composite.setLayoutData((Object)data);
            Label containerLabel = new Label(composite, 16384);
            containerLabel.setText(StateflowEditorPlugin.INSTANCE.getString("_UI_ModelObject"));
            data = new GridData();
            data.horizontalAlignment = 4;
            containerLabel.setLayoutData((Object)data);
            this.initialObjectField = new Combo(composite, 2048);
            data = new GridData();
            data.horizontalAlignment = 4;
            data.grabExcessHorizontalSpace = true;
            this.initialObjectField.setLayoutData((Object)data);
            Iterator i = StateflowModelWizard.this.getInitialObjectNames().iterator();
            while (i.hasNext()) {
                this.initialObjectField.add(this.getLabel((String)i.next()));
            }
            if (this.initialObjectField.getItemCount() == 1) {
                this.initialObjectField.select(0);
            }
            this.initialObjectField.addModifyListener(this.validator);
            Label encodingLabel = new Label(composite, 16384);
            encodingLabel.setText(StateflowEditorPlugin.INSTANCE.getString("_UI_XMLEncoding"));
            GridData data2 = new GridData();
            data2.horizontalAlignment = 4;
            encodingLabel.setLayoutData((Object)data2);
            this.encodingField = new Combo(composite, 2048);
            data2 = new GridData();
            data2.horizontalAlignment = 4;
            data2.grabExcessHorizontalSpace = true;
            this.encodingField.setLayoutData((Object)data2);
            Iterator i2 = this.getEncodings().iterator();
            while (i2.hasNext()) {
                this.encodingField.add((String)i2.next());
            }
            this.encodingField.select(0);
            this.encodingField.addModifyListener(this.validator);
            this.setPageComplete(this.validatePage());
            this.setControl((Control)composite);
        }

        protected boolean validatePage() {
            return this.getInitialObjectName() != null && this.getEncodings().contains(this.encodingField.getText());
        }

        public void setVisible(boolean visible) {
            super.setVisible(visible);
            if (visible) {
                if (this.initialObjectField.getItemCount() == 1) {
                    this.initialObjectField.clearSelection();
                    this.encodingField.setFocus();
                } else {
                    this.encodingField.clearSelection();
                    this.initialObjectField.setFocus();
                }
            }
        }

        public String getInitialObjectName() {
            String label = this.initialObjectField.getText();
            for (String name : StateflowModelWizard.this.getInitialObjectNames()) {
                if (!this.getLabel(name).equals(label)) continue;
                return name;
            }
            return null;
        }

        public String getEncoding() {
            return this.encodingField.getText();
        }

        protected String getLabel(String typeName) {
            try {
                return StateflowEditPlugin.INSTANCE.getString("_UI_" + typeName + "_type");
            }
            catch (MissingResourceException mre) {
                StateflowEditorPlugin.INSTANCE.log(mre);
                return typeName;
            }
        }

        protected Collection getEncodings() {
            if (this.encodings == null) {
                this.encodings = new ArrayList();
                StringTokenizer stringTokenizer = new StringTokenizer(StateflowEditorPlugin.INSTANCE.getString("_UI_XMLEncodingChoices"));
                while (stringTokenizer.hasMoreTokens()) {
                    this.encodings.add(stringTokenizer.nextToken());
                }
            }
            return this.encodings;
        }
    }

    public class StateflowModelWizardNewFileCreationPage
    extends WizardNewFileCreationPage {
        public StateflowModelWizardNewFileCreationPage(String pageId, IStructuredSelection selection) {
            super(pageId, selection);
        }

        protected boolean validatePage() {
            if (super.validatePage()) {
                String extension = new Path(this.getFileName()).getFileExtension();
                if (extension == null || !FILE_EXTENSIONS.contains(extension)) {
                    String key = FILE_EXTENSIONS.size() > 1 ? "_WARN_FilenameExtensions" : "_WARN_FilenameExtension";
                    this.setErrorMessage(StateflowEditorPlugin.INSTANCE.getString(key, new Object[]{FORMATTED_FILE_EXTENSIONS}));
                    return false;
                }
                return true;
            }
            return false;
        }

        public IFile getModelFile() {
            return ResourcesPlugin.getWorkspace().getRoot().getFile(this.getContainerFullPath().append(this.getFileName()));
        }
    }
}

