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
import com.yj.coffeemachines.mvp.contract.Step1Contract;
import com.yj.coffeemachines.mvp.model.beans.AppUploadBean;
import com.yj.coffeemachines.mvp.model.beans.DeviceInfoBean;
import com.yj.coffeemachines.mvp.model.beans.DeviceQrBean;
import com.yj.coffeemachines.mvp.model.beans.ListTypeAllMaterialBack;
import com.yj.coffeemachines.mvp.model.beans.PositionVoiceListBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.model.beans.ProgramPlanListBack;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import okhttp3.ResponseBody;

@FragmentScope
/* loaded from: classes.dex */
public class Step1Model extends BaseModel implements Step1Contract.Model {

    @Inject
    Application mApplication;

    @Inject
    Gson mGson;

    @Inject
    public Step1Model(IRepositoryManager iRepositoryManager) {
        super(iRepositoryManager);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DeviceInfoBean lambda$getDeviceInfo$1(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (DeviceInfoBean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$getGoodsInfo$0(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (List) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AppUploadBean lambda$getMaxAppVersion$3(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (AppUploadBean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$getMaxAppVersion$5(Api api, AppUploadBean appUploadBean) throws Exception {
        Tools.upLocalLog("获取最新APP版本,getMaxAppVersion()");
        if (appUploadBean.getAppVersionNum() <= 34520) {
            return Observable.just(null);
        }
        Tools.upLocalLog("下载最新APP版本");
        return api.download(appUploadBean.getAppFullPath()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Step1Model$a7P5DTDm9NiDlXSDQa1VFO1z55E
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step1Model.lambda$null$4((ResponseBody) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DeviceQrBean lambda$getQrConfig$2(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (DeviceQrBean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ File lambda$null$4(ResponseBody responseBody) throws Exception {
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

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.Model
    public Observable<DeviceInfoBean> getDeviceInfo() {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).getDeviceInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Step1Model$6n29IGTe5xNAWTbNOtmcA_iwq2s
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step1Model.lambda$getDeviceInfo$1((HttpBean) obj);
            }
        }).retryWhen(new RetryWithDelay(2, 1));
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.Model
    public Observable<List<ProductBean>> getGoodsInfo() {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).getGoodInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Step1Model$gUF6vPdme-YyJZQZC_1QXlNzkBk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step1Model.lambda$getGoodsInfo$0((HttpBean) obj);
            }
        }).retryWhen(new RetryWithDelay(2, 1));
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.Model
    public Observable<File> getMaxAppVersion() {
        final Api api = (Api) this.mRepositoryManager.obtainRetrofitService(Api.class);
        return api.getMaxAppVersion().map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Step1Model$5RhZtgFcKAxoeTLDbfQc-PDDiVo
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step1Model.lambda$getMaxAppVersion$3((HttpBean) obj);
            }
        }).flatMap(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Step1Model$eYFoyFGF5QAsDxfvVXCYs-LhAOU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step1Model.lambda$getMaxAppVersion$5(Api.this, (AppUploadBean) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.Model
    public Observable<String> getPayWaySetting() {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).getPayWaySetting().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.Model
    public Observable<DeviceQrBean> getQrConfig() {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).getQrConfig().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).map(new Function() { // from class: com.yj.coffeemachines.mvp.model.-$$Lambda$Step1Model$n3cyRqJanMCPpAY4F1ip9Bc1LBI
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step1Model.lambda$getQrConfig$2((HttpBean) obj);
            }
        }).retryWhen(new RetryWithDelay(2, 1));
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.Model
    public Observable<ListTypeAllMaterialBack> listTypeAllMaterial() {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).listTypeAllMaterial().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.jess.arms.mvp.BaseModel, com.jess.arms.mvp.IModel
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.Model
    public Observable<PositionVoiceListBack> positionVoiceList() {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).positionVoiceList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.Model
    public Observable<ProgramPlanListBack> programPlanList() {
        return ((Api) this.mRepositoryManager.obtainRetrofitService(Api.class)).programPlanList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }
}
