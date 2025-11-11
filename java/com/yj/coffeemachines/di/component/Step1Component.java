package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Step1Module;
import com.yj.coffeemachines.mvp.contract.Step1Contract;
import com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Step1Module.class})
/* loaded from: classes.dex */
public interface Step1Component {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Step1Component build();

        @BindsInstance
        Builder view(Step1Contract.View view);
    }

    void inject(Step1Fragment step1Fragment);
}
