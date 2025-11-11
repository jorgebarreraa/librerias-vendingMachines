package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.Ops2_RawMaterialSetingContract;
import com.yj.coffeemachines.mvp.model.Ops2_RawMaterialSetingModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class Ops2_RawMaterialSetingModule {
    @Binds
    abstract Ops2_RawMaterialSetingContract.Model bindOps2_RawMaterialSetingModel(Ops2_RawMaterialSetingModel ops2_RawMaterialSetingModel);
}
