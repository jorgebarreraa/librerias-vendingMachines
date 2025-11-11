package com.yj.coffeemachines.di.component.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yj.coffeemachines.di.component.mvp.contract.Ops2_RawMaterialSetingContract;
import javax.inject.Inject;

@FragmentScope
/* loaded from: classes.dex */
public class Ops2_RawMaterialSetingModel extends BaseModel implements Ops2_RawMaterialSetingContract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public Ops2_RawMaterialSetingModel(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}
