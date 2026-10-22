/*
 * Decompiled with CFR 0.152.
 */
package com.csi.gmf.executionflow.diagram.navigator;

import com.csi.gmf.executionflow.diagram.navigator.ExecutionflowAbstractNavigatorItem;
import java.util.Collection;
import java.util.LinkedList;

public class ExecutionflowNavigatorGroup
extends ExecutionflowAbstractNavigatorItem {
    private String myGroupName;
    private String myIcon;
    private Collection myChildren = new LinkedList();

    ExecutionflowNavigatorGroup(String groupName, String icon, Object parent) {
        super(parent);
        this.myGroupName = groupName;
        this.myIcon = icon;
    }

    public String getGroupName() {
        return this.myGroupName;
    }

    public String getIcon() {
        return this.myIcon;
    }

    public Object[] getChildren() {
        return this.myChildren.toArray();
    }

    public void addChildren(Collection children) {
        this.myChildren.addAll(children);
    }

    public void addChild(Object child) {
        this.myChildren.add(child);
    }

    public boolean isEmpty() {
        return this.myChildren.size() == 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ExecutionflowNavigatorGroup) {
            ExecutionflowNavigatorGroup anotherGroup = (ExecutionflowNavigatorGroup)((Object)obj);
            if (this.getGroupName().equals(anotherGroup.getGroupName())) {
                return this.getParent().equals(anotherGroup.getParent());
            }
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return this.getGroupName().hashCode();
    }
}

