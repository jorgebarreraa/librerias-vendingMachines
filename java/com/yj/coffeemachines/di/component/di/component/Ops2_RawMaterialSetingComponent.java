package com.yj.coffeemachines.di.component.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.component.di.module.Ops2_RawMaterialSetingModule;
import com.yj.coffeemachines.di.component.mvp.contract.Ops2_RawMaterialSetingContract;
import com.yj.coffeemachines.di.component.mvp.ui.fragment.Ops2_RawMaterialSetingFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Ops2_RawMaterialSetingModule.class})
/* loaded from: classes.dex */
public interface Ops2_RawMaterialSetingComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Ops2_RawMaterialSetingComponent build();

        @BindsInstance
        Builder view(Ops2_RawMaterialSetingContract.View view);
    }

    void inject(Ops2_RawMaterialSetingFragment ops2_RawMaterialSetingFragment);
}
