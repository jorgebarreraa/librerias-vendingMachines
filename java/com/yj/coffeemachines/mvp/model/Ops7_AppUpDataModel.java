package com.yj.coffeemachines.mvp.model;

import android.app.Application;
import com.blankj.utilcode.util.FileIOUtils;
import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.yj.coffeemachines.Api;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.app.utils.FileUtils;
import com.yj.coffeemachines.bean.HttpBean;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Ops7_AppUpDataContract;
import com.yj.coffeemachines.mvp.model.beans.AppUploadBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import javax.inject.Inject;
import okhttp3.ResponseBody;

@FragmentScope
/* loaded from: classes.dex */
public class Ops7_AppUpDataModel extends BaseModel implements Ops7_AppUpDataContract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public Ops7_AppUpDataModel(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ File lambda$downLoadApp$1(ResponseBody responseBody) throws Exception {
        File file = new File(Constants.apkPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(Constants.apkPath + File.separator + "coffee.apk");
        if (FileUtils.isFileExists(file2)) {
            FileUtils.deleteFile(file2);
        }
        if (FileIOUtils.writeFileFromIS(file2, responseBody.byteStream(), true)) {
            return file2;
        }
        throw new Exception("apk io error");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AppUploadBean lambda$uploadApp$0(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (AppUploadBean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops7_AppUpDataContract.Model
    public Observable<File> downLoadApp(String str) {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).download(str).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Ops7_AppUpDataModel$BPBzgGwsTHKFGFcCg1ZFyrlRedg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Ops7_AppUpDataModel.lambda$downLoadApp$1((ResponseBody) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops7_AppUpDataContract.Model
    public Observable<AppUploadBean> uploadApp() {
        Tools.upLocalLog("网络接口查询订单支付状态.getMaxAppVersion()");
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).getMaxAppVersion().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Ops7_AppUpDataModel$VdhnfslTGrlySxgq2xpCMKtv6YQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Ops7_AppUpDataModel.lambda$uploadApp$0((HttpBean) obj);
            }
        });
    }
}
