package com.yj.coffeemachines.di.component.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.di.component.mvp.presenter.Ops2_RawMaterialSetingPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops2_RawMaterialSetingFragment_MembersInjector implements MembersInjector<Ops2_RawMaterialSetingFragment> {
    private final Provider<Ops2_RawMaterialSetingPresenter> mPresenterProvider;

    public Ops2_RawMaterialSetingFragment_MembersInjector(Provider<Ops2_RawMaterialSetingPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<Ops2_RawMaterialSetingFragment> create(Provider<Ops2_RawMaterialSetingPresenter> provider) {
        return new Ops2_RawMaterialSetingFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops2_RawMaterialSetingFragment ops2_RawMaterialSetingFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops2_RawMaterialSetingFragment, this.mPresenterProvider.get());
    }
}
