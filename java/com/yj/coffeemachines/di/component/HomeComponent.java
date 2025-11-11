package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.yj.coffeemachines.di.module.HomeModule;
import com.yj.coffeemachines.mvp.contract.HomeContract;
import com.yj.coffeemachines.mvp.ui.activity.HomeActivity;
import dagger.BindsInstance;
import dagger.Component;

@Component(dependencies = {AppComponent.class}, modules = {HomeModule.class})
@ActivityScope
/* loaded from: classes.dex */
public interface HomeComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        HomeComponent build();

        @BindsInstance
        Builder view(HomeContract.View view);
    }

    void inject(HomeActivity homeActivity);
}
