package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.yj.coffeemachines.di.module.ADModule;
import com.yj.coffeemachines.mvp.contract.ADContract;
import com.yj.coffeemachines.mvp.ui.activity.ADActivity;
import dagger.BindsInstance;
import dagger.Component;

@Component(dependencies = {AppComponent.class}, modules = {ADModule.class})
@ActivityScope
/* loaded from: classes.dex */
public interface ADComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        ADComponent build();

        @BindsInstance
        Builder view(ADContract.View view);
    }

    void inject(ADActivity aDActivity);
}
