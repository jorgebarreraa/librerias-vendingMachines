package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Step3Presenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step3Fragment_MembersInjector implements MembersInjector<Step3Fragment> {
    private final Provider<Step3Presenter> mPresenterProvider;
    private final Provider<AlertDialog> mSportDialogProvider;

    public Step3Fragment_MembersInjector(Provider<Step3Presenter> provider, Provider<AlertDialog> provider2) {
        this.mPresenterProvider = provider;
        this.mSportDialogProvider = provider2;
    }

    public static MembersInjector<Step3Fragment> create(Provider<Step3Presenter> provider, Provider<AlertDialog> provider2) {
        return new Step3Fragment_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment.mSportDialog")
    public static void injectMSportDialog(Step3Fragment step3Fragment, AlertDialog alertDialog) {
        step3Fragment.mSportDialog = alertDialog;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step3Fragment step3Fragment) {
        BaseFragment_MembersInjector.injectMPresenter(step3Fragment, this.mPresenterProvider.get());
        injectMSportDialog(step3Fragment, this.mSportDialogProvider.get());
    }
}
