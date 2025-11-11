package com.yj.coffeemachines.mvp.ui.activity;

import com.jess.arms.base.BaseActivity_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.ADPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ADActivity_MembersInjector implements MembersInjector<ADActivity> {
    private final Provider<ADPresenter> mPresenterProvider;

    public ADActivity_MembersInjector(Provider<ADPresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<ADActivity> create(Provider<ADPresenter> provider) {
        return new ADActivity_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ADActivity aDActivity) {
        BaseActivity_MembersInjector.injectMPresenter(aDActivity, this.mPresenterProvider.get());
    }
}
