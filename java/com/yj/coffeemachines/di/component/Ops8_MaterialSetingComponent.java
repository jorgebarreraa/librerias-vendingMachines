package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Ops8_MaterialSetingModule;
import com.yj.coffeemachines.mvp.contract.Ops8_MaterialSetingContract;
import com.yj.coffeemachines.mvp.ui.fragment.Ops8_MaterialSetingFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Ops8_MaterialSetingModule.class})
/* loaded from: classes.dex */
public interface Ops8_MaterialSetingComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Ops8_MaterialSetingComponent build();

        @BindsInstance
        Builder view(Ops8_MaterialSetingContract.View view);
    }

    void inject(Ops8_MaterialSetingFragment ops8_MaterialSetingFragment);
}
