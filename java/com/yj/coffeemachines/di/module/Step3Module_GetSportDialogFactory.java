package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.yj.coffeemachines.mvp.contract.Step3Contract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Step3Module_GetSportDialogFactory implements Factory<AlertDialog> {
    private final Provider<Step3Contract.View> viewProvider;

    public Step3Module_GetSportDialogFactory(Provider<Step3Contract.View> provider) {
        this.viewProvider = provider;
    }

    public static Step3Module_GetSportDialogFactory create(Provider<Step3Contract.View> provider) {
        return new Step3Module_GetSportDialogFactory(provider);
    }

    public static AlertDialog getSportDialog(Step3Contract.View view) {
        return (AlertDialog) Preconditions.checkNotNull(Step3Module.getSportDialog(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AlertDialog get() {
        return getSportDialog(this.viewProvider.get());
    }
}
