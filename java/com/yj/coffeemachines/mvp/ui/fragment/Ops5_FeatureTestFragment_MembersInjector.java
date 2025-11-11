package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Ops5_FeatureTestPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops5_FeatureTestFragment_MembersInjector implements MembersInjector<Ops5_FeatureTestFragment> {
    private final Provider<Ops5_FeatureTestPresenter> mPresenterProvider;

    public Ops5_FeatureTestFragment_MembersInjector(Provider<Ops5_FeatureTestPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<Ops5_FeatureTestFragment> create(Provider<Ops5_FeatureTestPresenter> provider) {
        return new Ops5_FeatureTestFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops5_FeatureTestFragment ops5_FeatureTestFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops5_FeatureTestFragment, this.mPresenterProvider.get());
    }
}
