package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.yj.coffeemachines.mvp.contract.ADContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ADModule_GetSportDialogFactory implements Factory<AlertDialog> {
    private final Provider<ADContract.View> viewProvider;

    public ADModule_GetSportDialogFactory(Provider<ADContract.View> provider) {
        this.viewProvider = provider;
    }

    public static ADModule_GetSportDialogFactory create(Provider<ADContract.View> provider) {
        return new ADModule_GetSportDialogFactory(provider);
    }

    public static AlertDialog getSportDialog(ADContract.View view) {
        return (AlertDialog) Preconditions.checkNotNull(ADModule.getSportDialog(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AlertDialog get() {
        return getSportDialog(this.viewProvider.get());
    }
}
