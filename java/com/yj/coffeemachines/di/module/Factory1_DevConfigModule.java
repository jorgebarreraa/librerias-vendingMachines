package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.Factory1_DevConfigContract;
import com.yj.coffeemachines.mvp.model.Factory1_DevConfigModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class Factory1_DevConfigModule {
    @Binds
    abstract Factory1_DevConfigContract.Model bindFactory1_DevConfigModel(Factory1_DevConfigModel factory1_DevConfigModel);
}
