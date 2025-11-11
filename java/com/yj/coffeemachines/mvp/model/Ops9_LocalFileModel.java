package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yj.coffeemachines.Api;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract;
import com.yj.coffeemachines.mvp.model.beans.CheckFileIntactBack;
import io.reactivex.Observable;
import javax.inject.Inject;
import okhttp3.ResponseBody;

@FragmentScope
/* loaded from: classes.dex */
public class Ops9_LocalFileModel extends BaseModel implements Ops9_LocalFileContract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public Ops9_LocalFileModel(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract.Model
    public Observable<CheckFileIntactBack> checkFile(String str, String str2) {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).checkFileIntact(str, str2);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract.Model
    public Observable<ResponseBody> download(String str) {
        Tools.upLocalLog("本地文件：开始下载");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).download(str);
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}
