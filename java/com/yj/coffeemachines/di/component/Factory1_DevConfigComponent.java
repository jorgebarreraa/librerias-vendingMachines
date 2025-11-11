package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Factory1_DevConfigModule;
import com.yj.coffeemachines.mvp.contract.Factory1_DevConfigContract;
import com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Factory1_DevConfigModule.class})
/* loaded from: classes.dex */
public interface Factory1_DevConfigComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Factory1_DevConfigComponent build();

        @BindsInstance
        Builder view(Factory1_DevConfigContract.View view);
    }

    void inject(Factory1_DevConfigFragment factory1_DevConfigFragment);
}
