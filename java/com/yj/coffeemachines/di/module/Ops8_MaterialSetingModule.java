package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.Ops8_MaterialSetingContract;
import com.yj.coffeemachines.mvp.model.Ops8_MaterialSetingModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class Ops8_MaterialSetingModule {
    @Binds
    abstract Ops8_MaterialSetingContract.Model bindOps8_MaterialSetingModel(Ops8_MaterialSetingModel ops8_MaterialSetingModel);
}
