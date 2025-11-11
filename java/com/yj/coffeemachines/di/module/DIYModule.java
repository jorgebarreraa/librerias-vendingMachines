package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.mvp.contract.DIYContract;
import com.yj.coffeemachines.mvp.model.DIYModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dmax.dialog.SpotsDialog;

@Module
/* loaded from: classes.dex */
public abstract class DIYModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @FragmentScope
    @Provides
    public static AlertDialog getSportDialog(DIYContract.View view) {
        return new SpotsDialog.Builder().setContext(view.getActivity()).setCancelable(true).build();
    }

    @Binds
    abstract DIYContract.Model bindDIYModel(DIYModel dIYModel);
}
