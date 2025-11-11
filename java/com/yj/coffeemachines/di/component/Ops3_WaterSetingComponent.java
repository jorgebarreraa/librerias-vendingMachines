package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Ops3_WaterSetingModule;
import com.yj.coffeemachines.mvp.contract.Ops3_WaterSetingContract;
import com.yj.coffeemachines.mvp.ui.fragment.Ops3_WaterSetingFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Ops3_WaterSetingModule.class})
/* loaded from: classes.dex */
public interface Ops3_WaterSetingComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Ops3_WaterSetingComponent build();

        @BindsInstance
        Builder view(Ops3_WaterSetingContract.View view);
    }

    void inject(Ops3_WaterSetingFragment ops3_WaterSetingFragment);
}
