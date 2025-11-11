package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Factory2_SeralPortConfigPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Factory2_SeralPortConfigFragment_MembersInjector implements MembersInjector<Factory2_SeralPortConfigFragment> {
    private final Provider<Factory2_SeralPortConfigPresenter> mPresenterProvider;

    public Factory2_SeralPortConfigFragment_MembersInjector(Provider<Factory2_SeralPortConfigPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<Factory2_SeralPortConfigFragment> create(Provider<Factory2_SeralPortConfigPresenter> provider) {
        return new Factory2_SeralPortConfigFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Factory2_SeralPortConfigFragment factory2_SeralPortConfigFragment) {
        BaseFragment_MembersInjector.injectMPresenter(factory2_SeralPortConfigFragment, this.mPresenterProvider.get());
    }
}
