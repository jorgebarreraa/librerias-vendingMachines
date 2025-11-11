package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.yj.coffeemachines.mvp.contract.JoinVIPContract;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class JoinVIPModule_GetSportDialogFactory implements Factory<AlertDialog> {
    private final Provider<JoinVIPContract.View> viewProvider;

    public JoinVIPModule_GetSportDialogFactory(Provider<JoinVIPContract.View> provider) {
        this.viewProvider = provider;
    }

    public static JoinVIPModule_GetSportDialogFactory create(Provider<JoinVIPContract.View> provider) {
        return new JoinVIPModule_GetSportDialogFactory(provider);
    }

    public static AlertDialog getSportDialog(JoinVIPContract.View view) {
        return (AlertDialog) Preconditions.checkNotNull(JoinVIPModule.getSportDialog(view), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public AlertDialog get() {
        return getSportDialog(this.viewProvider.get());
    }
}
