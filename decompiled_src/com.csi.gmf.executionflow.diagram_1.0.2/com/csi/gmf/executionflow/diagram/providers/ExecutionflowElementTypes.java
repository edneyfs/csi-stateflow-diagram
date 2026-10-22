/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.ExecutionflowPackage
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
package com.csi.gmf.executionflow.diagram.providers;

import com.csi.gmf.executionflow.ExecutionflowPackage;
import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import com.csi.gmf.executionflow.diagram.providers.ElementInitializers;
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

public class ExecutionflowElementTypes
extends ElementInitializers {
    private static Map elements;
    private static ImageRegistry imageRegistry;
    public static final IElementType ExecutionFlow_79;
    public static final IElementType ExecutionFlowObject_1001;
    public static final IElementType ExecutableFlowObject_1002;
    public static final IElementType ExecutionFlowResult_1003;
    public static final IElementType StartPoint_1004;
    public static final IElementType FlowObjectConnection_3001;
    public static final IElementType StartPointConnection_3002;
    private static Set KNOWN_ELEMENT_TYPES;

    static {
        ExecutionFlow_79 = ExecutionflowElementTypes.getElementType("com.csi.gmf.executionflow.diagram.ExecutionFlow_79");
        ExecutionFlowObject_1001 = ExecutionflowElementTypes.getElementType("com.csi.gmf.executionflow.diagram.ExecutionFlowObject_1001");
        ExecutableFlowObject_1002 = ExecutionflowElementTypes.getElementType("com.csi.gmf.executionflow.diagram.ExecutableFlowObject_1002");
        ExecutionFlowResult_1003 = ExecutionflowElementTypes.getElementType("com.csi.gmf.executionflow.diagram.ExecutionFlowResult_1003");
        StartPoint_1004 = ExecutionflowElementTypes.getElementType("com.csi.gmf.executionflow.diagram.StartPoint_1004");
        FlowObjectConnection_3001 = ExecutionflowElementTypes.getElementType("com.csi.gmf.executionflow.diagram.FlowObjectConnection_3001");
        StartPointConnection_3002 = ExecutionflowElementTypes.getElementType("com.csi.gmf.executionflow.diagram.StartPointConnection_3002");
    }

    private ExecutionflowElementTypes() {
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
            return ExecutionflowDiagramEditorPlugin.getInstance().getItemImageDescriptor(eClass.getEPackage().getEFactoryInstance().create(eClass));
        }
        return null;
    }

    public static ImageDescriptor getImageDescriptor(ENamedElement element) {
        String key = ExecutionflowElementTypes.getImageRegistryKey(element);
        ImageDescriptor imageDescriptor = ExecutionflowElementTypes.getImageRegistry().getDescriptor(key);
        if (imageDescriptor == null) {
            imageDescriptor = ExecutionflowElementTypes.getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            ExecutionflowElementTypes.getImageRegistry().put(key, imageDescriptor);
        }
        return imageDescriptor;
    }

    public static Image getImage(ENamedElement element) {
        String key = ExecutionflowElementTypes.getImageRegistryKey(element);
        Image image = ExecutionflowElementTypes.getImageRegistry().get(key);
        if (image == null) {
            ImageDescriptor imageDescriptor = ExecutionflowElementTypes.getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            ExecutionflowElementTypes.getImageRegistry().put(key, imageDescriptor);
            image = ExecutionflowElementTypes.getImageRegistry().get(key);
        }
        return image;
    }

    public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
        ENamedElement element = ExecutionflowElementTypes.getElement(hint);
        if (element == null) {
            return null;
        }
        return ExecutionflowElementTypes.getImageDescriptor(element);
    }

    public static Image getImage(IAdaptable hint) {
        ENamedElement element = ExecutionflowElementTypes.getElement(hint);
        if (element == null) {
            return null;
        }
        return ExecutionflowElementTypes.getImage(element);
    }

    public static ENamedElement getElement(IAdaptable hint) {
        Object type = hint.getAdapter(IElementType.class);
        if (elements == null) {
            elements = new IdentityHashMap();
            elements.put(ExecutionFlow_79, ExecutionflowPackage.eINSTANCE.getExecutionFlow());
            elements.put(ExecutionFlowObject_1001, ExecutionflowPackage.eINSTANCE.getExecutionFlowObject());
            elements.put(ExecutableFlowObject_1002, ExecutionflowPackage.eINSTANCE.getExecutableFlowObject());
            elements.put(ExecutionFlowResult_1003, ExecutionflowPackage.eINSTANCE.getExecutionFlowResult());
            elements.put(StartPoint_1004, ExecutionflowPackage.eINSTANCE.getStartPoint());
            elements.put(FlowObjectConnection_3001, ExecutionflowPackage.eINSTANCE.getFlowObjectConnection());
            elements.put(StartPointConnection_3002, ExecutionflowPackage.eINSTANCE.getStartPointConnection());
        }
        return (ENamedElement)elements.get(type);
    }

    private static IElementType getElementType(String id) {
        return ElementTypeRegistry.getInstance().getType(id);
    }

    public static boolean isKnownElementType(IElementType elementType) {
        if (KNOWN_ELEMENT_TYPES == null) {
            KNOWN_ELEMENT_TYPES = new HashSet();
            KNOWN_ELEMENT_TYPES.add(ExecutionFlow_79);
            KNOWN_ELEMENT_TYPES.add(ExecutionFlowObject_1001);
            KNOWN_ELEMENT_TYPES.add(ExecutableFlowObject_1002);
            KNOWN_ELEMENT_TYPES.add(ExecutionFlowResult_1003);
            KNOWN_ELEMENT_TYPES.add(StartPoint_1004);
            KNOWN_ELEMENT_TYPES.add(FlowObjectConnection_3001);
            KNOWN_ELEMENT_TYPES.add(StartPointConnection_3002);
        }
        return KNOWN_ELEMENT_TYPES.contains(elementType);
    }
}

