package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.Ops9_LocalFilePresenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops9_LocalFileFragment_MembersInjector implements MembersInjector<Ops9_LocalFileFragment> {
    private final Provider<Ops9_LocalFilePresenter> mPresenterProvider;
    private final Provider<AlertDialog> mSportDialogProvider;

    public Ops9_LocalFileFragment_MembersInjector(Provider<Ops9_LocalFilePresenter> provider, Provider<AlertDialog> provider2) {
        this.mPresenterProvider = provider;
        this.mSportDialogProvider = provider2;
    }

    public static MembersInjector<Ops9_LocalFileFragment> create(Provider<Ops9_LocalFilePresenter> provider, Provider<AlertDialog> provider2) {
        return new Ops9_LocalFileFragment_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.ui.fragment.Ops9_LocalFileFragment.mSportDialog")
    public static void injectMSportDialog(Ops9_LocalFileFragment ops9_LocalFileFragment, AlertDialog alertDialog) {
        ops9_LocalFileFragment.mSportDialog = alertDialog;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(Ops9_LocalFileFragment ops9_LocalFileFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops9_LocalFileFragment, this.mPresenterProvider.get());
        injectMSportDialog(ops9_LocalFileFragment, this.mSportDialogProvider.get());
    }
}
