package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.OpsPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class OpsFragment_MembersInjector implements MembersInjector<OpsFragment> {
    private final Provider<OpsPresenter> mPresenterProvider;

    public OpsFragment_MembersInjector(Provider<OpsPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<OpsFragment> create(Provider<OpsPresenter> provider) {
        return new OpsFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(OpsFragment opsFragment) {
        BaseFragment_MembersInjector.injectMPresenter(opsFragment, this.mPresenterProvider.get());
    }
}
