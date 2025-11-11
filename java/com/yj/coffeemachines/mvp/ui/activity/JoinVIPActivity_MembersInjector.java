package com.yj.coffeemachines.mvp.ui.activity;

import android.app.AlertDialog;
import com.jess.arms.base.BaseActivity_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.JoinVIPPresenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class JoinVIPActivity_MembersInjector implements MembersInjector<JoinVIPActivity> {
    private final Provider<AlertDialog> mAlertDialogProvider;
    private final Provider<JoinVIPPresenter> mPresenterProvider;

    public JoinVIPActivity_MembersInjector(Provider<JoinVIPPresenter> provider, Provider<AlertDialog> provider2) {
        this.mPresenterProvider = provider;
        this.mAlertDialogProvider = provider2;
    }

    public static MembersInjector<JoinVIPActivity> create(Provider<JoinVIPPresenter> provider, Provider<AlertDialog> provider2) {
        return new JoinVIPActivity_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.ui.activity.JoinVIPActivity.mAlertDialog")
    public static void injectMAlertDialog(JoinVIPActivity joinVIPActivity, AlertDialog alertDialog) {
        joinVIPActivity.mAlertDialog = alertDialog;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(JoinVIPActivity joinVIPActivity) {
        BaseActivity_MembersInjector.injectMPresenter(joinVIPActivity, this.mPresenterProvider.get());
        injectMAlertDialog(joinVIPActivity, this.mAlertDialogProvider.get());
    }
}
