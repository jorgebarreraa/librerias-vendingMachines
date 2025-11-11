package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract;
import com.yj.coffeemachines.mvp.model.Ops9_LocalFileModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dmax.dialog.SpotsDialog;

@Module
/* loaded from: classes.dex */
public abstract class Ops9_LocalFileModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @FragmentScope
    @Provides
    public static AlertDialog getSportDialog(Ops9_LocalFileContract.View view) {
        return new SpotsDialog.Builder().setContext(view.getActivity()).setCancelable(false).build();
    }

    @Binds
    abstract Ops9_LocalFileContract.Model bindOps9_LocalFileModel(Ops9_LocalFileModel ops9_LocalFileModel);
}
