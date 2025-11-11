package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.Ops5_FeatureTestContract;
import com.yj.coffeemachines.mvp.model.Ops5_FeatureTestModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class Ops5_FeatureTestModule {
    @Binds
    abstract Ops5_FeatureTestContract.Model bindOps5_FeatureTestModel(Ops5_FeatureTestModel ops5_FeatureTestModel);
}
