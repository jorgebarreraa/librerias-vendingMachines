package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.EventModule;
import com.yj.coffeemachines.mvp.contract.EventContract;
import com.yj.coffeemachines.mvp.ui.fragment.EventFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {EventModule.class})
/* loaded from: classes.dex */
public interface EventComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        EventComponent build();

        @BindsInstance
        Builder view(EventContract.View view);
    }

    void inject(EventFragment eventFragment);
}
