package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.Ops1_RawMaterialAddContract;
import com.yj.coffeemachines.mvp.model.Ops1_RawMaterialAddModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class Ops1_RawMaterialAddModule {
    @Binds
    abstract Ops1_RawMaterialAddContract.Model bindOps1_RawMaterialAddModel(Ops1_RawMaterialAddModel ops1_RawMaterialAddModel);
}
