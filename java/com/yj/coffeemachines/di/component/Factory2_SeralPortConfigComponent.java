package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Factory2_SeralPortConfigModule;
import com.yj.coffeemachines.mvp.contract.Factory2_SeralPortConfigContract;
import com.yj.coffeemachines.mvp.ui.fragment.Factory2_SeralPortConfigFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Factory2_SeralPortConfigModule.class})
/* loaded from: classes.dex */
public interface Factory2_SeralPortConfigComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Factory2_SeralPortConfigComponent build();

        @BindsInstance
        Builder view(Factory2_SeralPortConfigContract.View view);
    }

    void inject(Factory2_SeralPortConfigFragment factory2_SeralPortConfigFragment);
}
