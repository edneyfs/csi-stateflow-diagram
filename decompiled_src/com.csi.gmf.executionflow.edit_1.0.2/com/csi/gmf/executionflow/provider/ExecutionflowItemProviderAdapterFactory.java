/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.csi.gmf.executionflow.util.ExecutionflowAdapterFactory
 *  org.eclipse.emf.common.notify.Adapter
 *  org.eclipse.emf.common.notify.AdapterFactory
 *  org.eclipse.emf.common.notify.Notification
 *  org.eclipse.emf.common.notify.Notifier
 *  org.eclipse.emf.edit.provider.ChangeNotifier
 *  org.eclipse.emf.edit.provider.ComposeableAdapterFactory
 *  org.eclipse.emf.edit.provider.ComposedAdapterFactory
 *  org.eclipse.emf.edit.provider.IChangeNotifier
 *  org.eclipse.emf.edit.provider.IDisposable
 *  org.eclipse.emf.edit.provider.IEditingDomainItemProvider
 *  org.eclipse.emf.edit.provider.IItemLabelProvider
 *  org.eclipse.emf.edit.provider.IItemPropertySource
 *  org.eclipse.emf.edit.provider.INotifyChangedListener
 *  org.eclipse.emf.edit.provider.IStructuredItemContentProvider
 *  org.eclipse.emf.edit.provider.ITreeItemContentProvider
 */
package com.csi.gmf.executionflow.provider;

import com.csi.gmf.executionflow.provider.ExecutableFlowObjectItemProvider;
import com.csi.gmf.executionflow.provider.ExecutionFlowItemProvider;
import com.csi.gmf.executionflow.provider.ExecutionFlowObjectItemProvider;
import com.csi.gmf.executionflow.provider.ExecutionFlowResultItemProvider;
import com.csi.gmf.executionflow.provider.FlowObjectConnectionItemProvider;
import com.csi.gmf.executionflow.provider.FlowObjectItemProvider;
import com.csi.gmf.executionflow.provider.SourceConnectableItemProvider;
import com.csi.gmf.executionflow.provider.StartPointConnectionItemProvider;
import com.csi.gmf.executionflow.provider.StartPointItemProvider;
import com.csi.gmf.executionflow.util.ExecutionflowAdapterFactory;
import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

public class ExecutionflowItemProviderAdapterFactory
extends ExecutionflowAdapterFactory
implements ComposeableAdapterFactory,
IChangeNotifier,
IDisposable {
    protected ComposedAdapterFactory parentAdapterFactory;
    protected IChangeNotifier changeNotifier = new ChangeNotifier();
    protected Collection supportedTypes = new ArrayList();
    protected ExecutableFlowObjectItemProvider executableFlowObjectItemProvider;
    protected ExecutionFlowItemProvider executionFlowItemProvider;
    protected ExecutionFlowObjectItemProvider executionFlowObjectItemProvider;
    protected ExecutionFlowResultItemProvider executionFlowResultItemProvider;
    protected FlowObjectItemProvider flowObjectItemProvider;
    protected FlowObjectConnectionItemProvider flowObjectConnectionItemProvider;
    protected SourceConnectableItemProvider sourceConnectableItemProvider;
    protected StartPointItemProvider startPointItemProvider;
    protected StartPointConnectionItemProvider startPointConnectionItemProvider;

    public ExecutionflowItemProviderAdapterFactory() {
        this.supportedTypes.add(IEditingDomainItemProvider.class);
        this.supportedTypes.add(IStructuredItemContentProvider.class);
        this.supportedTypes.add(ITreeItemContentProvider.class);
        this.supportedTypes.add(IItemLabelProvider.class);
        this.supportedTypes.add(IItemPropertySource.class);
    }

    public Adapter createExecutableFlowObjectAdapter() {
        if (this.executableFlowObjectItemProvider == null) {
            this.executableFlowObjectItemProvider = new ExecutableFlowObjectItemProvider((AdapterFactory)this);
        }
        return this.executableFlowObjectItemProvider;
    }

    public Adapter createExecutionFlowAdapter() {
        if (this.executionFlowItemProvider == null) {
            this.executionFlowItemProvider = new ExecutionFlowItemProvider((AdapterFactory)this);
        }
        return this.executionFlowItemProvider;
    }

    public Adapter createExecutionFlowObjectAdapter() {
        if (this.executionFlowObjectItemProvider == null) {
            this.executionFlowObjectItemProvider = new ExecutionFlowObjectItemProvider((AdapterFactory)this);
        }
        return this.executionFlowObjectItemProvider;
    }

    public Adapter createExecutionFlowResultAdapter() {
        if (this.executionFlowResultItemProvider == null) {
            this.executionFlowResultItemProvider = new ExecutionFlowResultItemProvider((AdapterFactory)this);
        }
        return this.executionFlowResultItemProvider;
    }

    public Adapter createFlowObjectAdapter() {
        if (this.flowObjectItemProvider == null) {
            this.flowObjectItemProvider = new FlowObjectItemProvider((AdapterFactory)this);
        }
        return this.flowObjectItemProvider;
    }

    public Adapter createFlowObjectConnectionAdapter() {
        if (this.flowObjectConnectionItemProvider == null) {
            this.flowObjectConnectionItemProvider = new FlowObjectConnectionItemProvider((AdapterFactory)this);
        }
        return this.flowObjectConnectionItemProvider;
    }

    public Adapter createSourceConnectableAdapter() {
        if (this.sourceConnectableItemProvider == null) {
            this.sourceConnectableItemProvider = new SourceConnectableItemProvider((AdapterFactory)this);
        }
        return this.sourceConnectableItemProvider;
    }

    public Adapter createStartPointAdapter() {
        if (this.startPointItemProvider == null) {
            this.startPointItemProvider = new StartPointItemProvider((AdapterFactory)this);
        }
        return this.startPointItemProvider;
    }

    public Adapter createStartPointConnectionAdapter() {
        if (this.startPointConnectionItemProvider == null) {
            this.startPointConnectionItemProvider = new StartPointConnectionItemProvider((AdapterFactory)this);
        }
        return this.startPointConnectionItemProvider;
    }

    public ComposeableAdapterFactory getRootAdapterFactory() {
        return this.parentAdapterFactory == null ? this : this.parentAdapterFactory.getRootAdapterFactory();
    }

    public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
        this.parentAdapterFactory = parentAdapterFactory;
    }

    public boolean isFactoryForType(Object type) {
        return this.supportedTypes.contains(type) || super.isFactoryForType(type);
    }

    public Adapter adapt(Notifier notifier, Object type) {
        return super.adapt(notifier, (Object)this);
    }

    public Object adapt(Object object, Object type) {
        if (this.isFactoryForType(type)) {
            Object adapter = super.adapt(object, type);
            if (!(type instanceof Class) || ((Class)type).isInstance(adapter)) {
                return adapter;
            }
        }
        return null;
    }

    public void addListener(INotifyChangedListener notifyChangedListener) {
        this.changeNotifier.addListener(notifyChangedListener);
    }

    public void removeListener(INotifyChangedListener notifyChangedListener) {
        this.changeNotifier.removeListener(notifyChangedListener);
    }

    public void fireNotifyChanged(Notification notification) {
        this.changeNotifier.fireNotifyChanged(notification);
        if (this.parentAdapterFactory != null) {
            this.parentAdapterFactory.fireNotifyChanged(notification);
        }
    }

    public void dispose() {
        if (this.executableFlowObjectItemProvider != null) {
            this.executableFlowObjectItemProvider.dispose();
        }
        if (this.executionFlowItemProvider != null) {
            this.executionFlowItemProvider.dispose();
        }
        if (this.executionFlowObjectItemProvider != null) {
            this.executionFlowObjectItemProvider.dispose();
        }
        if (this.executionFlowResultItemProvider != null) {
            this.executionFlowResultItemProvider.dispose();
        }
        if (this.flowObjectItemProvider != null) {
            this.flowObjectItemProvider.dispose();
        }
        if (this.flowObjectConnectionItemProvider != null) {
            this.flowObjectConnectionItemProvider.dispose();
        }
        if (this.sourceConnectableItemProvider != null) {
            this.sourceConnectableItemProvider.dispose();
        }
        if (this.startPointItemProvider != null) {
            this.startPointItemProvider.dispose();
        }
        if (this.startPointConnectionItemProvider != null) {
            this.startPointConnectionItemProvider.dispose();
        }
    }
}

