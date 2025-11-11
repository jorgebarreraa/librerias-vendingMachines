package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.yj.coffeemachines.mvp.contract.Step1Contract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step1Module_GetSportDialogFactory implements Factory<AlertDialog> {
    private final Provider<Step1Contract.View> viewProvider;

    public Step1Module_GetSportDialogFactory(Provider<Step1Contract.View> provider) {
        this.viewProvider = provider;
    }

    public static Step1Module_GetSportDialogFactory create(Provider<Step1Contract.View> provider) {
        return new Step1Module_GetSportDialogFactory(provider);
    }

    public static AlertDialog getSportDialog(Step1Contract.View view) {
        return (AlertDialog) Preconditions.checkNotNull(Step1Module.getSportDialog(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AlertDialog get() {
        return getSportDialog(this.viewProvider.get());
    }
}
