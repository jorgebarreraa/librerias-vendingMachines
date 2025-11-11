package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.yj.coffeemachines.mvp.contract.Step2Contract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step2Module_GetSportDialogFactory implements Factory<AlertDialog> {
    private final Provider<Step2Contract.View> viewProvider;

    public Step2Module_GetSportDialogFactory(Provider<Step2Contract.View> provider) {
        this.viewProvider = provider;
    }

    public static Step2Module_GetSportDialogFactory create(Provider<Step2Contract.View> provider) {
        return new Step2Module_GetSportDialogFactory(provider);
    }

    public static AlertDialog getSportDialog(Step2Contract.View view) {
        return (AlertDialog) Preconditions.checkNotNull(Step2Module.getSportDialog(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AlertDialog get() {
        return getSportDialog(this.viewProvider.get());
    }
}
