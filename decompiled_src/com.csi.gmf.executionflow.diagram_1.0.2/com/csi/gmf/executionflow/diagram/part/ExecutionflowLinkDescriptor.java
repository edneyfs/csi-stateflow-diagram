/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IAdaptable
 *  org.eclipse.emf.ecore.EObject
 *  org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter
 *  org.eclipse.gmf.runtime.emf.type.core.IElementType
 */
package com.csi.gmf.executionflow.diagram.part;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowNodeDescriptor;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

public class ExecutionflowLinkDescriptor
extends ExecutionflowNodeDescriptor {
    private EObject mySource;
    private EObject myDestination;
    private IAdaptable mySemanticAdapter;

    private ExecutionflowLinkDescriptor(EObject source, EObject destination, EObject linkElement, int linkVID) {
        super(linkElement, linkVID);
        this.mySource = source;
        this.myDestination = destination;
    }

    public ExecutionflowLinkDescriptor(EObject source, EObject destination, IElementType elementType, int linkVID) {
        this(source, destination, (EObject)null, linkVID);
        final IElementType elementTypeCopy = elementType;
        this.mySemanticAdapter = new IAdaptable(){

            public Object getAdapter(Class adapter) {
                if (IElementType.class.equals((Object)adapter)) {
                    return elementTypeCopy;
                }
                return null;
            }
        };
    }

    public ExecutionflowLinkDescriptor(EObject source, EObject destination, EObject linkElement, IElementType elementType, int linkVID) {
        this(source, destination, linkElement, linkVID);
        final IElementType elementTypeCopy = elementType;
        this.mySemanticAdapter = new EObjectAdapter(linkElement){

            public Object getAdapter(Class adapter) {
                if (IElementType.class.equals((Object)adapter)) {
                    return elementTypeCopy;
                }
                return super.getAdapter(adapter);
            }
        };
    }

    public EObject getSource() {
        return this.mySource;
    }

    public EObject getDestination() {
        return this.myDestination;
    }

    public IAdaptable getSemanticAdapter() {
        return this.mySemanticAdapter;
    }
}

