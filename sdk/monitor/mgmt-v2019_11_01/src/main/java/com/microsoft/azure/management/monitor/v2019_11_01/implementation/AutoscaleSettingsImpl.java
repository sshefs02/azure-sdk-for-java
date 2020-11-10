/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * def
 */

package com.microsoft.azure.management.monitor.v2019_11_01.implementation;

import com.microsoft.azure.arm.resources.collection.implementation.GroupableResourcesCoreImpl;
import com.microsoft.azure.management.monitor.v2019_11_01.AutoscaleSettings;
import com.microsoft.azure.management.monitor.v2019_11_01.AutoscaleSettingResource;
import rx.Observable;
import rx.Completable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import com.microsoft.azure.arm.resources.ResourceUtilsCore;
import com.microsoft.azure.arm.utils.RXMapper;
import rx.functions.Func1;
import com.microsoft.azure.PagedList;
import com.microsoft.azure.Page;

class AutoscaleSettingsImpl extends GroupableResourcesCoreImpl<AutoscaleSettingResource, AutoscaleSettingResourceImpl, AutoscaleSettingResourceInner, AutoscaleSettingsInner, MonitorManager>  implements AutoscaleSettings {
    protected AutoscaleSettingsImpl(MonitorManager manager) {
        super(manager.inner().autoscaleSettings(), manager);
    }

    @Override
    protected Observable<AutoscaleSettingResourceInner> getInnerAsync(String resourceGroupName, String name) {
        AutoscaleSettingsInner client = this.inner();
        return client.getByResourceGroupAsync(resourceGroupName, name);
    }

    @Override
    protected Completable deleteInnerAsync(String resourceGroupName, String name) {
        AutoscaleSettingsInner client = this.inner();
        return client.deleteAsync(resourceGroupName, name).toCompletable();
    }

    @Override
    public Observable<String> deleteByIdsAsync(Collection<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return Observable.empty();
        }
        Collection<Observable<String>> observables = new ArrayList<>();
        for (String id : ids) {
            final String resourceGroupName = ResourceUtilsCore.groupFromResourceId(id);
            final String name = ResourceUtilsCore.nameFromResourceId(id);
            Observable<String> o = RXMapper.map(this.inner().deleteAsync(resourceGroupName, name), id);
            observables.add(o);
        }
        return Observable.mergeDelayError(observables);
    }

    @Override
    public Observable<String> deleteByIdsAsync(String...ids) {
        return this.deleteByIdsAsync(new ArrayList<String>(Arrays.asList(ids)));
    }

    @Override
    public void deleteByIds(Collection<String> ids) {
        if (ids != null && !ids.isEmpty()) {
            this.deleteByIdsAsync(ids).toBlocking().last();
        }
    }

    @Override
    public void deleteByIds(String...ids) {
        this.deleteByIds(new ArrayList<String>(Arrays.asList(ids)));
    }

    @Override
    public PagedList<AutoscaleSettingResource> listByResourceGroup(String resourceGroupName) {
        AutoscaleSettingsInner client = this.inner();
        return this.wrapList(client.listByResourceGroup(resourceGroupName));
    }

    @Override
    public Observable<AutoscaleSettingResource> listByResourceGroupAsync(String resourceGroupName) {
        AutoscaleSettingsInner client = this.inner();
        return client.listByResourceGroupAsync(resourceGroupName)
        .flatMapIterable(new Func1<Page<AutoscaleSettingResourceInner>, Iterable<AutoscaleSettingResourceInner>>() {
            @Override
            public Iterable<AutoscaleSettingResourceInner> call(Page<AutoscaleSettingResourceInner> page) {
                return page.items();
            }
        })
        .map(new Func1<AutoscaleSettingResourceInner, AutoscaleSettingResource>() {
            @Override
            public AutoscaleSettingResource call(AutoscaleSettingResourceInner inner) {
                return wrapModel(inner);
            }
        });
    }

    @Override
    public PagedList<AutoscaleSettingResource> list() {
        AutoscaleSettingsInner client = this.inner();
        return this.wrapList(client.list());
    }

    @Override
    public Observable<AutoscaleSettingResource> listAsync() {
        AutoscaleSettingsInner client = this.inner();
        return client.listAsync()
        .flatMapIterable(new Func1<Page<AutoscaleSettingResourceInner>, Iterable<AutoscaleSettingResourceInner>>() {
            @Override
            public Iterable<AutoscaleSettingResourceInner> call(Page<AutoscaleSettingResourceInner> page) {
                return page.items();
            }
        })
        .map(new Func1<AutoscaleSettingResourceInner, AutoscaleSettingResource>() {
            @Override
            public AutoscaleSettingResource call(AutoscaleSettingResourceInner inner) {
                return wrapModel(inner);
            }
        });
    }

    @Override
    public AutoscaleSettingResourceImpl define(String name) {
        return wrapModel(name);
    }

    @Override
    protected AutoscaleSettingResourceImpl wrapModel(AutoscaleSettingResourceInner inner) {
        return  new AutoscaleSettingResourceImpl(inner.name(), inner, manager());
    }

    @Override
    protected AutoscaleSettingResourceImpl wrapModel(String name) {
        return new AutoscaleSettingResourceImpl(name, new AutoscaleSettingResourceInner(), this.manager());
    }

}
