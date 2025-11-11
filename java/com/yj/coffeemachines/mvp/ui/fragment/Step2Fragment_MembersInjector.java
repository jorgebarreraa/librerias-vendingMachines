package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Step2Presenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step2Fragment_MembersInjector implements MembersInjector<Step2Fragment> {
    private final Provider<Step2Presenter> mPresenterProvider;
    private final Provider<AlertDialog> mSportDialogProvider;

    public Step2Fragment_MembersInjector(Provider<Step2Presenter> provider, Provider<AlertDialog> provider2) {
        this.mPresenterProvider = provider;
        this.mSportDialogProvider = provider2;
    }

    public static MembersInjector<Step2Fragment> create(Provider<Step2Presenter> provider, Provider<AlertDialog> provider2) {
        return new Step2Fragment_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.ui.fragment.Step2Fragment.mSportDialog")
    public static void injectMSportDialog(Step2Fragment step2Fragment, AlertDialog alertDialog) {
        step2Fragment.mSportDialog = alertDialog;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step2Fragment step2Fragment) {
        BaseFragment_MembersInjector.injectMPresenter(step2Fragment, this.mPresenterProvider.get());
        injectMSportDialog(step2Fragment, this.mSportDialogProvider.get());
    }
}
