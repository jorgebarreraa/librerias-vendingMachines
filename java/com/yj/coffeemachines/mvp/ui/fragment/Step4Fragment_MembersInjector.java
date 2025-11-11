package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Step4Presenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step4Fragment_MembersInjector implements MembersInjector<Step4Fragment> {
    private final Provider<Step4Presenter> mPresenterProvider;
    private final Provider<AlertDialog> mSportDialogProvider;

    public Step4Fragment_MembersInjector(Provider<Step4Presenter> provider, Provider<AlertDialog> provider2) {
        this.mPresenterProvider = provider;
        this.mSportDialogProvider = provider2;
    }

    public static MembersInjector<Step4Fragment> create(Provider<Step4Presenter> provider, Provider<AlertDialog> provider2) {
        return new Step4Fragment_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.ui.fragment.Step4Fragment.mSportDialog")
    public static void injectMSportDialog(Step4Fragment step4Fragment, AlertDialog alertDialog) {
        step4Fragment.mSportDialog = alertDialog;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Step4Fragment step4Fragment) {
        BaseFragment_MembersInjector.injectMPresenter(step4Fragment, this.mPresenterProvider.get());
        injectMSportDialog(step4Fragment, this.mSportDialogProvider.get());
    }
}
