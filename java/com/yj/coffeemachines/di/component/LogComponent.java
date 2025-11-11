package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.LogModule;
import com.yj.coffeemachines.mvp.contract.LogContract;
import com.yj.coffeemachines.mvp.ui.fragment.LogFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {LogModule.class})
/* loaded from: classes.dex */
public interface LogComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        LogComponent build();

        @BindsInstance
        Builder view(LogContract.View view);
    }

    void inject(LogFragment logFragment);
}
