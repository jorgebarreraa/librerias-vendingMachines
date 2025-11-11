package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.jess.arms.di.scope.ActivityScope;
import com.yj.coffeemachines.mvp.contract.ADContract;
import com.yj.coffeemachines.mvp.model.ADModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dmax.dialog.SpotsDialog;

@Module
/* loaded from: classes.dex */
public abstract class ADModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ActivityScope
    public static AlertDialog getSportDialog(ADContract.View view) {
        return new SpotsDialog.Builder().setContext(view.getActivity()).setCancelable(true).build();
    }

    @Binds
    abstract ADContract.Model bindADModel(ADModel aDModel);
}
