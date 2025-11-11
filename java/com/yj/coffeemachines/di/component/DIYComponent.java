package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.DIYModule;
import com.yj.coffeemachines.mvp.contract.DIYContract;
import com.yj.coffeemachines.mvp.ui.fragment.DIYFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {DIYModule.class})
/* loaded from: classes.dex */
public interface DIYComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        DIYComponent build();

        @BindsInstance
        Builder view(DIYContract.View view);
    }

    void inject(DIYFragment dIYFragment);
}
