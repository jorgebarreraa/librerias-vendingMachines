package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Step5Module;
import com.yj.coffeemachines.mvp.contract.Step5Contract;
import com.yj.coffeemachines.mvp.ui.fragment.Step5Fragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Step5Module.class})
/* loaded from: classes.dex */
public interface Step5Component {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Step5Component build();

        @BindsInstance
        Builder view(Step5Contract.View view);
    }

    void inject(Step5Fragment step5Fragment);
}
