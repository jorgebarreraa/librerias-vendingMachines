package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Ops6_LogcatModule;
import com.yj.coffeemachines.mvp.contract.Ops6_LogcatContract;
import com.yj.coffeemachines.mvp.ui.fragment.Ops6_LogcatFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Ops6_LogcatModule.class})
/* loaded from: classes.dex */
public interface Ops6_LogcatComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Ops6_LogcatComponent build();

        @BindsInstance
        Builder view(Ops6_LogcatContract.View view);
    }

    void inject(Ops6_LogcatFragment ops6_LogcatFragment);
}
