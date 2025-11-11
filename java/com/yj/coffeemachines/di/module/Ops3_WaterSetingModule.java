package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.Ops3_WaterSetingContract;
import com.yj.coffeemachines.mvp.model.Ops3_WaterSetingModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class Ops3_WaterSetingModule {
    @Binds
    abstract Ops3_WaterSetingContract.Model bindOps3_WaterSetingModel(Ops3_WaterSetingModel ops3_WaterSetingModel);
}
