package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.yj.coffeemachines.mvp.contract.Step4Contract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step4Module_GetSportDialogFactory implements Factory<AlertDialog> {
    private final Provider<Step4Contract.View> viewProvider;

    public Step4Module_GetSportDialogFactory(Provider<Step4Contract.View> provider) {
        this.viewProvider = provider;
    }

    public static Step4Module_GetSportDialogFactory create(Provider<Step4Contract.View> provider) {
        return new Step4Module_GetSportDialogFactory(provider);
    }

    public static AlertDialog getSportDialog(Step4Contract.View view) {
        return (AlertDialog) Preconditions.checkNotNull(Step4Module.getSportDialog(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AlertDialog get() {
        return getSportDialog(this.viewProvider.get());
    }
}
