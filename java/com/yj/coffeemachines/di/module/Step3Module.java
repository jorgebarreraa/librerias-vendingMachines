package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.mvp.contract.Step3Contract;
import com.yj.coffeemachines.mvp.model.Step3Model;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dmax.dialog.SpotsDialog;

@Module
/* loaded from: classes.dex */
public abstract class Step3Module {
    /* JADX INFO: Access modifiers changed from: package-private */
    @FragmentScope
    @Provides
    public static AlertDialog getSportDialog(Step3Contract.View view) {
        return new SpotsDialog.Builder().setContext(view.getActivity()).setCancelable(false).build();
    }

    @Binds
    abstract Step3Contract.Model bindStep3Model(Step3Model step3Model);
}
