package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.LogPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class LogFragment_MembersInjector implements MembersInjector<LogFragment> {
    private final Provider<LogPresenter> mPresenterProvider;

    public LogFragment_MembersInjector(Provider<LogPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<LogFragment> create(Provider<LogPresenter> provider) {
        return new LogFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(LogFragment logFragment) {
        BaseFragment_MembersInjector.injectMPresenter(logFragment, this.mPresenterProvider.get());
    }
}
