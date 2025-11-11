package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.FactoryContract;
import com.yj.coffeemachines.mvp.model.FactoryModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class FactoryModule {
    @Binds
    abstract FactoryContract.Model bindFactoryModel(FactoryModel factoryModel);
}
