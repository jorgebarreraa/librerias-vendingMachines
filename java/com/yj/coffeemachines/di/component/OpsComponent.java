package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.OpsModule;
import com.yj.coffeemachines.mvp.contract.OpsContract;
import com.yj.coffeemachines.mvp.ui.fragment.OpsFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {OpsModule.class})
/* loaded from: classes.dex */
public interface OpsComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        OpsComponent build();

        @BindsInstance
        Builder view(OpsContract.View view);
    }

    void inject(OpsFragment opsFragment);
}
