package com.jess.arms.base;

import com.jess.arms.mvp.IPresenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class BaseLazyLoadFragment_MembersInjector<P extends IPresenter> implements MembersInjector<BaseLazyLoadFragment<P>> {
    private final Provider<P> mPresenterProvider;
    private final Provider<Unused> mUnusedProvider;

    public BaseLazyLoadFragment_MembersInjector(Provider<P> provider, Provider<Unused> provider2) {
        this.mPresenterProvider = provider;
        this.mUnusedProvider = provider2;
    }

    public static <P extends IPresenter> MembersInjector<BaseLazyLoadFragment<P>> create(Provider<P> provider, Provider<Unused> provider2) {
        return new BaseLazyLoadFragment_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.jess.arms.base.BaseLazyLoadFragment.mUnused")
    public static <P extends IPresenter> void injectMUnused(BaseLazyLoadFragment<P> baseLazyLoadFragment, Unused unused) {
        baseLazyLoadFragment.mUnused = unused;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BaseLazyLoadFragment<P> baseLazyLoadFragment) {
        BaseFragment_MembersInjector.injectMPresenter(baseLazyLoadFragment, this.mPresenterProvider.get());
        injectMUnused(baseLazyLoadFragment, this.mUnusedProvider.get());
    }
}
