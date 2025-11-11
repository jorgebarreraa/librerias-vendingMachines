package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Factory1_DevConfigPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Factory1_DevConfigFragment_MembersInjector implements MembersInjector<Factory1_DevConfigFragment> {
    private final Provider<Factory1_DevConfigPresenter> mPresenterProvider;

    public Factory1_DevConfigFragment_MembersInjector(Provider<Factory1_DevConfigPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<Factory1_DevConfigFragment> create(Provider<Factory1_DevConfigPresenter> provider) {
        return new Factory1_DevConfigFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Factory1_DevConfigFragment factory1_DevConfigFragment) {
        BaseFragment_MembersInjector.injectMPresenter(factory1_DevConfigFragment, this.mPresenterProvider.get());
    }
}
