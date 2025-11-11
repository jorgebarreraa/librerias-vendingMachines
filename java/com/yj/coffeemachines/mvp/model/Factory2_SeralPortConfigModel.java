package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yj.coffeemachines.mvp.contract.Factory2_SeralPortConfigContract;
import javax.inject.Inject;

@FragmentScope
/* loaded from: classes.dex */
public class Factory2_SeralPortConfigModel extends BaseModel implements Factory2_SeralPortConfigContract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public Factory2_SeralPortConfigModel(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}
