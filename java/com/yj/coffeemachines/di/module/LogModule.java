package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.LogContract;
import com.yj.coffeemachines.mvp.model.LogModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class LogModule {
    @Binds
    abstract LogContract.Model bindLogModel(LogModel logModel);
}
