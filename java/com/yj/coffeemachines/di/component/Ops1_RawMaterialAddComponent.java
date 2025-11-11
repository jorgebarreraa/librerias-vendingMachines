package com.yj.coffeemachines.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.di.module.Ops1_RawMaterialAddModule;
import com.yj.coffeemachines.mvp.contract.Ops1_RawMaterialAddContract;
import com.yj.coffeemachines.mvp.ui.fragment.Ops1_RawMaterialAddFragment;
import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {Ops1_RawMaterialAddModule.class})
/* loaded from: classes.dex */
public interface Ops1_RawMaterialAddComponent {

    @Component.Builder
    /* loaded from: classes.dex */
    public interface Builder {
        Builder appComponent(AppComponent appComponent);

        Ops1_RawMaterialAddComponent build();

        @BindsInstance
        Builder view(Ops1_RawMaterialAddContract.View view);
    }

    void inject(Ops1_RawMaterialAddFragment ops1_RawMaterialAddFragment);
}
