package com.yj.coffeemachines.di.module;

import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.mvp.contract.Ops6_LogcatContract;
import com.yj.coffeemachines.mvp.model.Ops6_LogcatModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import java.util.List;

@Module
/* loaded from: classes.dex */
public abstract class Ops6_LogcatModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @FragmentScope
    @Provides
    public static List<Object> getdata() {
        return new ArrayList();
    }

    @Binds
    abstract Ops6_LogcatContract.Model bindOps6_LogcatModel(Ops6_LogcatModel ops6_LogcatModel);
}
