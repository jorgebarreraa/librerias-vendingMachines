package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.yj.coffeemachines.mvp.contract.DIYContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DIYModule_GetSportDialogFactory implements Factory<AlertDialog> {
    private final Provider<DIYContract.View> viewProvider;

    public DIYModule_GetSportDialogFactory(Provider<DIYContract.View> provider) {
        this.viewProvider = provider;
    }

    public static DIYModule_GetSportDialogFactory create(Provider<DIYContract.View> provider) {
        return new DIYModule_GetSportDialogFactory(provider);
    }

    public static AlertDialog getSportDialog(DIYContract.View view) {
        return (AlertDialog) Preconditions.checkNotNull(DIYModule.getSportDialog(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AlertDialog get() {
        return getSportDialog(this.viewProvider.get());
    }
}
