package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Step5Presenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step5Fragment_MembersInjector implements MembersInjector<Step5Fragment> {
    private final Provider<Step5Presenter> mPresenterProvider;
    private final Provider<AlertDialog> mSportDialogProvider;

    public Step5Fragment_MembersInjector(Provider<Step5Presenter> provider, Provider<AlertDialog> provider2) {
        this.mPresenterProvider = provider;
        this.mSportDialogProvider = provider2;
    }

    public static MembersInjector<Step5Fragment> create(Provider<Step5Presenter> provider, Provider<AlertDialog> provider2) {
        return new Step5Fragment_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.ui.fragment.Step5Fragment.mSportDialog")
    public static void injectMSportDialog(Step5Fragment step5Fragment, AlertDialog alertDialog) {
        step5Fragment.mSportDialog = alertDialog;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step5Fragment step5Fragment) {
        BaseFragment_MembersInjector.injectMPresenter(step5Fragment, this.mPresenterProvider.get());
        injectMSportDialog(step5Fragment, this.mSportDialogProvider.get());
    }
}
