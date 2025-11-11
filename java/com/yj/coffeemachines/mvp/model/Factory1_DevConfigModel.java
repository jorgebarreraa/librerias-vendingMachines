package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yj.coffeemachines.mvp.contract.Factory1_DevConfigContract;
import javax.inject.Inject;

@FragmentScope
/* loaded from: classes.dex */
public class Factory1_DevConfigModel extends BaseModel implements Factory1_DevConfigContract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public Factory1_DevConfigModel(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}
