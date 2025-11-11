package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Ops7_AppUpDataPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops7_AppUpDataFragment_MembersInjector implements MembersInjector<Ops7_AppUpDataFragment> {
    private final Provider<Ops7_AppUpDataPresenter> mPresenterProvider;

    public Ops7_AppUpDataFragment_MembersInjector(Provider<Ops7_AppUpDataPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<Ops7_AppUpDataFragment> create(Provider<Ops7_AppUpDataPresenter> provider) {
        return new Ops7_AppUpDataFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops7_AppUpDataFragment ops7_AppUpDataFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops7_AppUpDataFragment, this.mPresenterProvider.get());
    }
}
