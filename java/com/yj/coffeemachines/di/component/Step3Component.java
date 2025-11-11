package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Step3Module;
import com.yj.coffeemachines.mvp.contract.Step3Contract;
import com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Step3Module.class})
/* loaded from: classes.dex */
public interface Step3Component {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Step3Component build();

        @BindsInstance
        Builder view(Step3Contract.View view);
    }

    void inject(Step3Fragment step3Fragment);
}
