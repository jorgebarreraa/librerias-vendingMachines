package com.yj.coffeemachines.di.module;

import android.app.AlertDialog;
import com.jess.arms.di.scope.FragmentScope;
import com.yj.coffeemachines.mvp.contract.Step2Contract;
import com.yj.coffeemachines.mvp.model.Step2Model;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dmax.dialog.SpotsDialog;

@Module
/* loaded from: classes.dex */
public abstract class Step2Module {
    /* JADX INFO: Access modifiers changed from: package-private */
    @FragmentScope
    @Provides
    public static AlertDialog getSportDialog(Step2Contract.View view) {
        return new SpotsDialog.Builder().setContext(view.getActivity()).setCancelable(true).build();
    }

    @Binds
    abstract Step2Contract.Model bindStep2Model(Step2Model step2Model);
}
