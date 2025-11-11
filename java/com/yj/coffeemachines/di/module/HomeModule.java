package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.jess.arms.di.scope.ActivityScope;
import com.yj.coffeemachines.mvp.contract.HomeContract;
import com.yj.coffeemachines.mvp.model.HomeModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dmax.dialog.SpotsDialog;

@Module
/* loaded from: classes.dex */
public abstract class HomeModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ActivityScope
    public static AlertDialog getSportDialog(HomeContract.View view) {
        return new SpotsDialog.Builder().setContext(view.getActivity()).setCancelable(true).build();
    }

    @Binds
    abstract HomeContract.Model bindHomeModel(HomeModel homeModel);
}
