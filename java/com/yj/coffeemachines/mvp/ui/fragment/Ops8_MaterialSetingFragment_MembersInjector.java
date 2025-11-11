package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Ops8_MaterialSetingPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops8_MaterialSetingFragment_MembersInjector implements MembersInjector<Ops8_MaterialSetingFragment> {
    private final Provider<Ops8_MaterialSetingPresenter> mPresenterProvider;

    public Ops8_MaterialSetingFragment_MembersInjector(Provider<Ops8_MaterialSetingPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<Ops8_MaterialSetingFragment> create(Provider<Ops8_MaterialSetingPresenter> provider) {
        return new Ops8_MaterialSetingFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops8_MaterialSetingFragment ops8_MaterialSetingFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops8_MaterialSetingFragment, this.mPresenterProvider.get());
    }
}
