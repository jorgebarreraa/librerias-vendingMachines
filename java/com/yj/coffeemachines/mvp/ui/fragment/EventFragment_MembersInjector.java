package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.yj.coffeemachines.mvp.presenter.EventPresenter;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class EventFragment_MembersInjector implements MembersInjector<EventFragment> {
    private final Provider<EventPresenter> mPresenterProvider;
    private final Provider<AlertDialog> mSportDialogProvider;

    public EventFragment_MembersInjector(Provider<EventPresenter> provider, Provider<AlertDialog> provider2) {
        this.mPresenterProvider = provider;
        this.mSportDialogProvider = provider2;
    }

    public static MembersInjector<EventFragment> create(Provider<EventPresenter> provider, Provider<AlertDialog> provider2) {
        return new EventFragment_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.yj.coffeemachines.mvp.ui.fragment.EventFragment.mSportDialog")
    public static void injectMSportDialog(EventFragment eventFragment, AlertDialog alertDialog) {
        eventFragment.mSportDialog = alertDialog;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EventFragment eventFragment) {
        BaseFragment_MembersInjector.injectMPresenter(eventFragment, this.mPresenterProvider.get());
        injectMSportDialog(eventFragment, this.mSportDialogProvider.get());
    }
}
