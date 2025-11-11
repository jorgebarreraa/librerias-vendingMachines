package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yj.coffeemachines.mvp.contract.JoinVIPContract;
import javax.inject.Inject;

@ActivityScope
/* loaded from: classes.dex */
public class JoinVIPModel extends BaseModel implements JoinVIPContract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public JoinVIPModel(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}
