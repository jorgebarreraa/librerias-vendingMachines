package com.yj.coffeemachines.di.module;

import com.yj.coffeemachines.mvp.contract.SetingsHomeContract;
import com.yj.coffeemachines.mvp.model.SetingsHomeModel;
import dagger.Binds;
import dagger.Module;

@Module
/* loaded from: classes.dex */
public abstract class SetingsHomeModule {
    @Binds
    abstract SetingsHomeContract.Model bindSetingsHomeModel(SetingsHomeModel setingsHomeModel);
}
