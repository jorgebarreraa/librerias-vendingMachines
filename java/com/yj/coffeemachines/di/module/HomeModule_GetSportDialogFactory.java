package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.yj.coffeemachines.mvp.contract.HomeContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class HomeModule_GetSportDialogFactory implements Factory<AlertDialog> {
    private final Provider<HomeContract.View> viewProvider;

    public HomeModule_GetSportDialogFactory(Provider<HomeContract.View> provider) {
        this.viewProvider = provider;
    }

    public static HomeModule_GetSportDialogFactory create(Provider<HomeContract.View> provider) {
        return new HomeModule_GetSportDialogFactory(provider);
    }

    public static AlertDialog getSportDialog(HomeContract.View view) {
        return (AlertDialog) Preconditions.checkNotNull(HomeModule.getSportDialog(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AlertDialog get() {
        return getSportDialog(this.viewProvider.get());
    }
}
