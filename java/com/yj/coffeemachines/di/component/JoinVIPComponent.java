package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.yj.coffeemachines.di.module.JoinVIPModule;
import com.yj.coffeemachines.mvp.contract.JoinVIPContract;
import com.yj.coffeemachines.mvp.ui.activity.JoinVIPActivity;
import dagger.BindsInstance;
import dagger.Component;

@Component(dependencies = {AppComponent.class}, modules = {JoinVIPModule.class})
@ActivityScope
/* loaded from: classes.dex */
public interface JoinVIPComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        JoinVIPComponent build();

        @BindsInstance
        Builder view(JoinVIPContract.View view);
    }

    void inject(JoinVIPActivity joinVIPActivity);
}
