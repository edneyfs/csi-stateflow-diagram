/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.stateflow.StateflowPackage
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.ecore.EClass
 *  org.eclipse.emf.ecore.EClassifier
 *  org.eclipse.emf.ecore.ENamedElement
 *  org.eclipse.emf.ecore.EStructuralFeature
 *  org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry
 *  org.eclipse.gmf.runtime.emf.type.core.IElementType
 *  org.eclipse.jface.resource.ImageDescriptor
 *  org.eclipse.jface.resource.ImageRegistry
 *  org.eclipse.swt.graphics.Image
 */
package com.csi.gmf.stateflow.diagram.providers;

import com.csi.gmf.stateflow.StateflowPackage;
import com.csi.gmf.stateflow.diagram.part.StateflowDiagramEditorPlugin;
import com.csi.gmf.stateflow.diagram.providers.ElementInitializers;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

public class StateflowElementTypes
extends ElementInitializers {
    private static Map elements;
    private static ImageRegistry imageRegistry;
    public static final IElementType StateFlow_79;
    public static final IElementType ExecutionFlowObject_1001;
    public static final IElementType State_1002;
    public static final IElementType StartPoint_1003;
    public static final IElementType FlowObjectConnection_3001;
    public static final IElementType EventConnection_3002;
    public static final IElementType StartPointConnection_3003;
    private static Set KNOWN_ELEMENT_TYPES;

    static {
        StateFlow_79 = StateflowElementTypes.getElementType("com.csi.gmf.stateflow.diagram.StateFlow_79");
        ExecutionFlowObject_1001 = StateflowElementTypes.getElementType("com.csi.gmf.stateflow.diagram.ExecutionFlowObject_1001");
        State_1002 = StateflowElementTypes.getElementType("com.csi.gmf.stateflow.diagram.State_1002");
        StartPoint_1003 = StateflowElementTypes.getElementType("com.csi.gmf.stateflow.diagram.StartPoint_1003");
        FlowObjectConnection_3001 = StateflowElementTypes.getElementType("com.csi.gmf.stateflow.diagram.FlowObjectConnection_3001");
        EventConnection_3002 = StateflowElementTypes.getElementType("com.csi.gmf.stateflow.diagram.EventConnection_3002");
        StartPointConnection_3003 = StateflowElementTypes.getElementType("com.csi.gmf.stateflow.diagram.StartPointConnection_3003");
    }

    private StateflowElementTypes() {
    }

    private static ImageRegistry getImageRegistry() {
        if (imageRegistry == null) {
            imageRegistry = new ImageRegistry();
        }
        return imageRegistry;
    }

    private static String getImageRegistryKey(ENamedElement element) {
        return element.getName();
    }

    private static ImageDescriptor getProvidedImageDescriptor(ENamedElement element) {
        EClass eClass;
        if (element instanceof EStructuralFeature) {
            EStructuralFeature feature = (EStructuralFeature)element;
            EClass eContainingClass = feature.getEContainingClass();
            EClassifier eType = feature.getEType();
            if (eContainingClass != null && !eContainingClass.isAbstract()) {
                element = eContainingClass;
            } else if (eType instanceof EClass && !((EClass)eType).isAbstract()) {
                element = eType;
            }
        }
        if (element instanceof EClass && !(eClass = (EClass)element).isAbstract()) {
            return StateflowDiagramEditorPlugin.getInstance().getItemImageDescriptor(eClass.getEPackage().getEFactoryInstance().create(eClass));
        }
        return null;
    }

    public static ImageDescriptor getImageDescriptor(ENamedElement element) {
        String key = StateflowElementTypes.getImageRegistryKey(element);
        ImageDescriptor imageDescriptor = StateflowElementTypes.getImageRegistry().getDescriptor(key);
        if (imageDescriptor == null) {
            imageDescriptor = StateflowElementTypes.getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            StateflowElementTypes.getImageRegistry().put(key, imageDescriptor);
        }
        return imageDescriptor;
    }

    public static Image getImage(ENamedElement element) {
        String key = StateflowElementTypes.getImageRegistryKey(element);
        Image image = StateflowElementTypes.getImageRegistry().get(key);
        if (image == null) {
            ImageDescriptor imageDescriptor = StateflowElementTypes.getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            StateflowElementTypes.getImageRegistry().put(key, imageDescriptor);
            image = StateflowElementTypes.getImageRegistry().get(key);
        }
        return image;
    }

    public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
        ENamedElement element = StateflowElementTypes.getElement(hint);
        if (element == null) {
            return null;
        }
        return StateflowElementTypes.getImageDescriptor(element);
    }

    public static Image getImage(IAdaptable hint) {
        ENamedElement element = StateflowElementTypes.getElement(hint);
        if (element == null) {
            return null;
        }
        return StateflowElementTypes.getImage(element);
    }

    public static ENamedElement getElement(IAdaptable hint) {
        Object type = hint.getAdapter(IElementType.class);
        if (elements == null) {
            elements = new IdentityHashMap();
            elements.put(StateFlow_79, StateflowPackage.eINSTANCE.getStateFlow());
            elements.put(ExecutionFlowObject_1001, StateflowPackage.eINSTANCE.getExecutionFlowObject());
            elements.put(State_1002, StateflowPackage.eINSTANCE.getState());
            elements.put(StartPoint_1003, StateflowPackage.eINSTANCE.getStartPoint());
            elements.put(FlowObjectConnection_3001, StateflowPackage.eINSTANCE.getFlowObjectConnection());
            elements.put(EventConnection_3002, StateflowPackage.eINSTANCE.getEventConnection());
            elements.put(StartPointConnection_3003, StateflowPackage.eINSTANCE.getStartPointConnection());
        }
        return (ENamedElement)elements.get(type);
    }

    private static IElementType getElementType(String id) {
        return ElementTypeRegistry.getInstance().getType(id);
    }

    public static boolean isKnownElementType(IElementType elementType) {
        if (KNOWN_ELEMENT_TYPES == null) {
            KNOWN_ELEMENT_TYPES = new HashSet();
            KNOWN_ELEMENT_TYPES.add(StateFlow_79);
            KNOWN_ELEMENT_TYPES.add(ExecutionFlowObject_1001);
            KNOWN_ELEMENT_TYPES.add(State_1002);
            KNOWN_ELEMENT_TYPES.add(StartPoint_1003);
            KNOWN_ELEMENT_TYPES.add(FlowObjectConnection_3001);
            KNOWN_ELEMENT_TYPES.add(EventConnection_3002);
            KNOWN_ELEMENT_TYPES.add(StartPointConnection_3003);
        }
        return KNOWN_ELEMENT_TYPES.contains(elementType);
    }
}

