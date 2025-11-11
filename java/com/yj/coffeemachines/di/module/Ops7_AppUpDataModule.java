package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.Ops7_AppUpDataContract;
import com.yj.coffeemachines.mvp.model.Ops7_AppUpDataModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class Ops7_AppUpDataModule {
    @Binds
    abstract Ops7_AppUpDataContract.Model bindOps7_AppUpDataModel(Ops7_AppUpDataModel ops7_AppUpDataModel);
}
