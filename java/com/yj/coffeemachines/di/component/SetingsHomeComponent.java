package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.yj.coffeemachines.di.module.SetingsHomeModule;
import com.yj.coffeemachines.mvp.contract.SetingsHomeContract;
import com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity;
import dagger.BindsInstance;
import dagger.Component;

@Component(dependencies = {AppComponent.class}, modules = {SetingsHomeModule.class})
@ActivityScope
/* loaded from: classes.dex */
public interface SetingsHomeComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        SetingsHomeComponent build();

        @BindsInstance
        Builder view(SetingsHomeContract.View view);
    }

    void inject(SetingsHomeActivity setingsHomeActivity);
}
