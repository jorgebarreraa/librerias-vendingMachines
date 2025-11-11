package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Ops5_FeatureTestModule;
import com.yj.coffeemachines.mvp.contract.Ops5_FeatureTestContract;
import com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Ops5_FeatureTestModule.class})
/* loaded from: classes.dex */
public interface Ops5_FeatureTestComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Ops5_FeatureTestComponent build();

        @BindsInstance
        Builder view(Ops5_FeatureTestContract.View view);
    }

    void inject(Ops5_FeatureTestFragment ops5_FeatureTestFragment);
}
