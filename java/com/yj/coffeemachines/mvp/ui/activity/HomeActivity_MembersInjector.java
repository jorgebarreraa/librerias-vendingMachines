package com.yj.coffeemachines.mvp.ui.activity;

import android.app.AlertDialog;
import com.jess.arms.base.BaseActivity_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.HomePresenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class HomeActivity_MembersInjector implements MembersInjector<HomeActivity> {
    private final Provider<HomePresenter> mPresenterProvider;
    private final Provider<AlertDialog> mSportDialogProvider;

    public HomeActivity_MembersInjector(Provider<HomePresenter> provider, Provider<AlertDialog> provider2) {
        this.mPresenterProvider = provider;
        this.mSportDialogProvider = provider2;
    }

    public static MembersInjector<HomeActivity> create(Provider<HomePresenter> provider, Provider<AlertDialog> provider2) {
        return new HomeActivity_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.ui.activity.HomeActivity.mSportDialog")
    public static void injectMSportDialog(HomeActivity homeActivity, AlertDialog alertDialog) {
        homeActivity.mSportDialog = alertDialog;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(HomeActivity homeActivity) {
        BaseActivity_MembersInjector.injectMPresenter(homeActivity, this.mPresenterProvider.get());
        injectMSportDialog(homeActivity, this.mSportDialogProvider.get());
    }
}
