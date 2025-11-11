package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.yj.coffeemachines.mvp.contract.EventContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class EventModule_GetSportDialogFactory implements Factory<AlertDialog> {
    private final Provider<EventContract.View> viewProvider;

    public EventModule_GetSportDialogFactory(Provider<EventContract.View> provider) {
        this.viewProvider = provider;
    }

    public static EventModule_GetSportDialogFactory create(Provider<EventContract.View> provider) {
        return new EventModule_GetSportDialogFactory(provider);
    }

    public static AlertDialog getSportDialog(EventContract.View view) {
        return (AlertDialog) Preconditions.checkNotNull(EventModule.getSportDialog(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AlertDialog get() {
        return getSportDialog(this.viewProvider.get());
    }
}
