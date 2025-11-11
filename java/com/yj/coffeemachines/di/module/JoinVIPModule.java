package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.jess.arms.di.scope.ActivityScope;
import com.yj.coffeemachines.mvp.contract.JoinVIPContract;
import com.yj.coffeemachines.mvp.model.JoinVIPModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dmax.dialog.SpotsDialog;

@Module
/* loaded from: classes.dex */
public abstract class JoinVIPModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ActivityScope
    public static AlertDialog getSportDialog(JoinVIPContract.View view) {
        return new SpotsDialog.Builder().setContext(view.getActivity()).setCancelable(true).build();
    }

    @Binds
    abstract JoinVIPContract.Model bindJoinVIPModel(JoinVIPModel joinVIPModel);
}
