package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.Factory2_SeralPortConfigContract;
import com.yj.coffeemachines.mvp.model.Factory2_SeralPortConfigModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class Factory2_SeralPortConfigModule {
    @Binds
    abstract Factory2_SeralPortConfigContract.Model bindFactory2_SeralPortConfigModel(Factory2_SeralPortConfigModel factory2_SeralPortConfigModel);
}
