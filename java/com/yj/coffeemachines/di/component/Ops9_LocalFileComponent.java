package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Ops9_LocalFileModule;
import com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract;
import com.yj.coffeemachines.mvp.ui.fragment.Ops9_LocalFileFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Ops9_LocalFileModule.class})
/* loaded from: classes.dex */
public interface Ops9_LocalFileComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Ops9_LocalFileComponent build();

        @BindsInstance
        Builder view(Ops9_LocalFileContract.View view);
    }

    void inject(Ops9_LocalFileFragment ops9_LocalFileFragment);
}
