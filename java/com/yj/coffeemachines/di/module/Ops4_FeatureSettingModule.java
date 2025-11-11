package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.Ops4_FeatureSettingContract;
import com.yj.coffeemachines.mvp.model.Ops4_FeatureSettingModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class Ops4_FeatureSettingModule {
    @Binds
    abstract Ops4_FeatureSettingContract.Model bindOps4_FeatureSettingModel(Ops4_FeatureSettingModel ops4_FeatureSettingModel);
}
