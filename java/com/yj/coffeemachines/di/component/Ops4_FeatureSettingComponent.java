package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Ops4_FeatureSettingModule;
import com.yj.coffeemachines.mvp.contract.Ops4_FeatureSettingContract;
import com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Ops4_FeatureSettingModule.class})
/* loaded from: classes.dex */
public interface Ops4_FeatureSettingComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Ops4_FeatureSettingComponent build();

        @BindsInstance
        Builder view(Ops4_FeatureSettingContract.View view);
    }

    void inject(Ops4_FeatureSettingFragment ops4_FeatureSettingFragment);
}
