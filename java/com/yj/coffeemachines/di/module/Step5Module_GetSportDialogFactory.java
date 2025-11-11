package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.yj.coffeemachines.mvp.contract.Step5Contract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step5Module_GetSportDialogFactory implements Factory<AlertDialog> {
    private final Provider<Step5Contract.View> viewProvider;

    public Step5Module_GetSportDialogFactory(Provider<Step5Contract.View> provider) {
        this.viewProvider = provider;
    }

    public static Step5Module_GetSportDialogFactory create(Provider<Step5Contract.View> provider) {
        return new Step5Module_GetSportDialogFactory(provider);
    }

    public static AlertDialog getSportDialog(Step5Contract.View view) {
        return (AlertDialog) Preconditions.checkNotNull(Step5Module.getSportDialog(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AlertDialog get() {
        return getSportDialog(this.viewProvider.get());
    }
}
