package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.FactoryPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class FactoryFragment_MembersInjector implements MembersInjector<FactoryFragment> {
    private final Provider<FactoryPresenter> mPresenterProvider;

    public FactoryFragment_MembersInjector(Provider<FactoryPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<FactoryFragment> create(Provider<FactoryPresenter> provider) {
        return new FactoryFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FactoryFragment factoryFragment) {
        BaseFragment_MembersInjector.injectMPresenter(factoryFragment, this.mPresenterProvider.get());
    }
}
