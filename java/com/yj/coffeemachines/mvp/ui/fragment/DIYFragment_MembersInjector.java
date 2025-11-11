package com.yj.coffeemachines.mvp.ui.fragment;

import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.DIYPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DIYFragment_MembersInjector implements MembersInjector<DIYFragment> {
    private final Provider<DIYPresenter> mPresenterProvider;

    public DIYFragment_MembersInjector(Provider<DIYPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<DIYFragment> create(Provider<DIYPresenter> provider) {
        return new DIYFragment_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DIYFragment dIYFragment) {
        BaseFragment_MembersInjector.injectMPresenter(dIYFragment, this.mPresenterProvider.get());
    }
}
