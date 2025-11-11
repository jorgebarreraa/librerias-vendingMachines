package com.jess.arms.base;

import com.jess.arms.mvp.IPresenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class BaseFragment_MembersInjector<P extends IPresenter> implements MembersInjector<BaseFragment<P>> {
    private final Provider<P> mPresenterProvider;

    public BaseFragment_MembersInjector(Provider<P> provider) {
        this.mPresenterProvider = provider;
    }

    public static <P extends IPresenter> MembersInjector<BaseFragment<P>> create(Provider<P> provider) {
        return new BaseFragment_MembersInjector(provider);
    }

    @InjectedFieldSignature("com.jess.arms.base.BaseFragment.mPresenter")
    public static <P extends IPresenter> void injectMPresenter(BaseFragment<P> baseFragment, P p) {
        baseFragment.mPresenter = p;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BaseFragment<P> baseFragment) {
        injectMPresenter(baseFragment, this.mPresenterProvider.get());
    }
}
