package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Step2Module;
import com.yj.coffeemachines.mvp.contract.Step2Contract;
import com.yj.coffeemachines.mvp.ui.fragment.Step2Fragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Step2Module.class})
/* loaded from: classes.dex */
public interface Step2Component {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Step2Component build();

        @BindsInstance
        Builder view(Step2Contract.View view);
    }

    void inject(Step2Fragment step2Fragment);
}
