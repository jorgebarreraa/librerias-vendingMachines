package com.yj.coffeemachines.mvp.ui.activity;

import com.jess.arms.base.BaseActivity_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.SetingsHomePresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SetingsHomeActivity_MembersInjector implements MembersInjector<SetingsHomeActivity> {
    private final Provider<SetingsHomePresenter> mPresenterProvider;

    public SetingsHomeActivity_MembersInjector(Provider<SetingsHomePresenter> provider) {
        this.mPresenterProvider = provider;
    }

    public static MembersInjector<SetingsHomeActivity> create(Provider<SetingsHomePresenter> provider) {
        return new SetingsHomeActivity_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SetingsHomeActivity setingsHomeActivity) {
        BaseActivity_MembersInjector.injectMPresenter(setingsHomeActivity, this.mPresenterProvider.get());
    }
}
