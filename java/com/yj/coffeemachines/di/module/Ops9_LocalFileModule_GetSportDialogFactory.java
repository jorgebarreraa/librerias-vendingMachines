package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Ops9_LocalFileModule_GetSportDialogFactory implements Factory<AlertDialog> {
    private final Provider<Ops9_LocalFileContract.View> viewProvider;

    public Ops9_LocalFileModule_GetSportDialogFactory(Provider<Ops9_LocalFileContract.View> provider) {
        this.viewProvider = provider;
    }

    public static Ops9_LocalFileModule_GetSportDialogFactory create(Provider<Ops9_LocalFileContract.View> provider) {
        return new Ops9_LocalFileModule_GetSportDialogFactory(provider);
    }

    public static AlertDialog getSportDialog(Ops9_LocalFileContract.View view) {
        return (AlertDialog) Preconditions.checkNotNull(Ops9_LocalFileModule.getSportDialog(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AlertDialog get() {
        return getSportDialog(this.viewProvider.get());
    }
}
