package com.jess.arms.base;

import com.jess.arms.mvp.IPresenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class BaseActivity_MembersInjector<P extends IPresenter> implements MembersInjector<BaseActivity<P>> {
    private final Provider<P> mPresenterProvider;

    public BaseActivity_MembersInjector(Provider<P> provider) {
        this.mPresenterProvider = provider;
    }

    public static <P extends IPresenter> MembersInjector<BaseActivity<P>> create(Provider<P> provider) {
        return new BaseActivity_MembersInjector(provider);
    }

    @InjectedFieldSignature("com.jess.arms.base.BaseActivity.mPresenter")
    public static <P extends IPresenter> void injectMPresenter(BaseActivity<P> baseActivity, P p) {
        baseActivity.mPresenter = p;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BaseActivity<P> baseActivity) {
        injectMPresenter(baseActivity, this.mPresenterProvider.get());
    }
}
