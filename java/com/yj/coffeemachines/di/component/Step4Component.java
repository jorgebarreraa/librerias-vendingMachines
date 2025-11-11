package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Step4Module;
import com.yj.coffeemachines.mvp.contract.Step4Contract;
import com.yj.coffeemachines.mvp.ui.fragment.Step4Fragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Step4Module.class})
/* loaded from: classes.dex */
public interface Step4Component {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Step4Component build();

        @BindsInstance
        Builder view(Step4Contract.View view);
    }

    void inject(Step4Fragment step4Fragment);
}
