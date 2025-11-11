package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Ops6_LogcatPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops6_LogcatFragment_MembersInjector implements MembersInjector<Ops6_LogcatFragment> {
    private final Provider<Ops6_LogcatPresenter> mPresenterProvider;

    public Ops6_LogcatFragment_MembersInjector(Provider<Ops6_LogcatPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<Ops6_LogcatFragment> create(Provider<Ops6_LogcatPresenter> provider) {
        return new Ops6_LogcatFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops6_LogcatFragment ops6_LogcatFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops6_LogcatFragment, this.mPresenterProvider.get());
    }
}
