package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Ops7_AppUpDataModule;
import com.yj.coffeemachines.mvp.contract.Ops7_AppUpDataContract;
import com.yj.coffeemachines.mvp.ui.fragment.Ops7_AppUpDataFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Ops7_AppUpDataModule.class})
/* loaded from: classes.dex */
public interface Ops7_AppUpDataComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Ops7_AppUpDataComponent build();

        @BindsInstance
        Builder view(Ops7_AppUpDataContract.View view);
    }

    void inject(Ops7_AppUpDataFragment ops7_AppUpDataFragment);
}
