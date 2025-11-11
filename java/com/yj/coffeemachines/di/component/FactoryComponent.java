package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.FactoryModule;
import com.yj.coffeemachines.mvp.contract.FactoryContract;
import com.yj.coffeemachines.mvp.ui.fragment.FactoryFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {FactoryModule.class})
/* loaded from: classes.dex */
public interface FactoryComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        FactoryComponent build();

        @BindsInstance
        Builder view(FactoryContract.View view);
    }

    void inject(FactoryFragment factoryFragment);
}
