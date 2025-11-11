package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.OpsContract;
import com.yj.coffeemachines.mvp.model.OpsModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class OpsModule {
    @Binds
    abstract OpsContract.Model bindOpsModel(OpsModel opsModel);
}
