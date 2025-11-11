package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.mvp.contract.EventContract;
import com.yj.coffeemachines.mvp.model.EventModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dmax.dialog.SpotsDialog;

@Module
/* loaded from: classes.dex */
public abstract class EventModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @FragmentScope
    @Provides
    public static AlertDialog getSportDialog(EventContract.View view) {
        return new SpotsDialog.Builder().setContext(view.getActivity()).setCancelable(false).build();
    }

    @Binds
    abstract EventContract.Model bindEventModel(EventModel eventModel);
}
