package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Step1Presenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step1Fragment_MembersInjector implements MembersInjector<Step1Fragment> {
    private final Provider<Step1Presenter> mPresenterProvider;
    private final Provider<AlertDialog> mSportDialogProvider;

    public Step1Fragment_MembersInjector(Provider<Step1Presenter> provider, Provider<AlertDialog> provider2) {
        this.mPresenterProvider = provider;
        this.mSportDialogProvider = provider2;
    }

    public static MembersInjector<Step1Fragment> create(Provider<Step1Presenter> provider, Provider<AlertDialog> provider2) {
        return new Step1Fragment_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment.mSportDialog")
    public static void injectMSportDialog(Step1Fragment step1Fragment, AlertDialog alertDialog) {
        step1Fragment.mSportDialog = alertDialog;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step1Fragment step1Fragment) {
        BaseFragment_MembersInjector.injectMPresenter(step1Fragment, this.mPresenterProvider.get());
        injectMSportDialog(step1Fragment, this.mSportDialogProvider.get());
    }
}
