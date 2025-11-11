package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.EventBusManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.DevConfig;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.OpsSeting;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.BeanUtils;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.app.utils.FileUtils;
import com.yj.coffeemachines.app.utils.KvUtil;
import com.yj.coffeemachines.bean.DefaultSetting;
import com.yj.coffeemachines.eventbusbean.AdMessage;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew_Msg;
import com.yj.coffeemachines.eventbusbean.LogoMessage;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.ADMessage;
import com.yj.coffeemachines.greendao.beans.FileMessage;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import com.yj.coffeemachines.greendao.beans.VoiceMessage;
import com.yj.coffeemachines.greendao.daos.ADMessageDao;
import com.yj.coffeemachines.greendao.daos.FileMessageDao;
import com.yj.coffeemachines.greendao.daos.MakeDrinkMessageDao;
import com.yj.coffeemachines.greendao.daos.MaterialMessageDao;
import com.yj.coffeemachines.greendao.daos.OpsActionMessageDao;
import com.yj.coffeemachines.greendao.daos.VoiceMessageDao;
import com.yj.coffeemachines.helper.DataLoadChecker;
import com.yj.coffeemachines.helper.DataProvider;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Step1Contract;
import com.yj.coffeemachines.mvp.model.beans.DeviceInfoBean;
import com.yj.coffeemachines.mvp.model.beans.DeviceQrBean;
import com.yj.coffeemachines.mvp.model.beans.ListTypeAllMaterialBack;
import com.yj.coffeemachines.mvp.model.beans.PayWaySettingBack;
import com.yj.coffeemachines.mvp.model.beans.PositionVoiceListBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.model.beans.ProgramPlanListBack;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.query.WhereCondition;

@FragmentScope
/* loaded from: classes.dex */
public class Step1Presenter extends BasePresenter<Step1Contract.Model, Step1Contract.View> {

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public Step1Presenter(Step1Contract.Model model, Step1Contract.View view) {
        super(model, view);
    }

    private void checkDefaultSetting() {
        DefaultSetting defaultSetting;
        if (!TextUtils.isEmpty(KvUtil.getInstance().getString("DevConfig", "")) || (defaultSetting = Constants.getDefaultSetting()) == null) {
            return;
        }
        Constants.setDevConfig(new DevConfig(defaultSetting.isInstrut_order1(), defaultSetting.isInstrut_order2(), defaultSetting.isInstrut_order3(), defaultSetting.isInstrut_order4(), defaultSetting.isInstrut_order5(), defaultSetting.isInstrut_order6(), defaultSetting.isInstrut_order7(), defaultSetting.isInstrut_order1_l(), defaultSetting.isInstrut_order2_l(), defaultSetting.isInstrut_order2_l_ice(), defaultSetting.isInstrut_order3_l(), false, defaultSetting.isInstrut_order5_l(), defaultSetting.isInstrut_order6_l(), defaultSetting.isinstrut_order7_l(), defaultSetting.isInstrut_order8_l(), "00:01", "23:59", defaultSetting.isInstrut_order27()));
        Constants.getStatePort1(defaultSetting.getSerialportInstent() == 0 ? 0 : 1);
        Constants.getStatePort2(defaultSetting.getSerialportCurrent() == 0 ? 0 : 1);
        Constants.getStatePort3(defaultSetting.getSerialportPayment() != 0 ? 1 : 0);
        Constants.setSERIAPort_instant("/dev/ttyS" + defaultSetting.getSerialportInstent());
        Constants.setSERIAPort_currentgrinding("/dev/ttyS" + defaultSetting.getSerialportCurrent());
        Constants.setSERIAPort_payment("/dev/ttyS" + defaultSetting.getSerialportPayment());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void convertAd(List<ProgramPlanListBack.DataBean> list) {
        ArrayList arrayList = new ArrayList();
        for (ProgramPlanListBack.DataBean dataBean : list) {
            FileMessage fileMessage = new FileMessage();
            fileMessage.setFileType(2);
            String[] split = dataBean.getFullPath().split(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            String str = Constants.adPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + split[split.length - 1];
            fileMessage.setFileName(split[split.length - 1]);
            fileMessage.setLocalPath(str);
            fileMessage.setUseObject("广告文件-" + dataBean.getNo());
            fileMessage.setDownloadUrl(dataBean.getFullPath());
            arrayList.add(fileMessage);
        }
        insertFile(2, arrayList);
    }

    private void convertProduct(List<ProductBean> list) {
        ArrayList arrayList = new ArrayList();
        for (ProductBean productBean : list) {
            List<ProductBean.ProductDetailBean.AttachBean> attachList = productBean.getGoodsInfoDetailApiVo().getAttachList();
            if (attachList != null && !attachList.isEmpty()) {
                for (int i = 0; i < attachList.size(); i++) {
                    ProductBean.ProductDetailBean.AttachBean attachBean = attachList.get(i);
                    FileMessage fileMessage = new FileMessage();
                    fileMessage.setFileType(1);
                    fileMessage.setFileName(attachBean.getFileName());
                    fileMessage.setLocalPath(Constants.adPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + attachBean.getFileName());
                    fileMessage.setUseObject(productBean.getName());
                    fileMessage.setDownloadUrl(attachBean.getFullPath());
                    arrayList.add(fileMessage);
                }
            }
        }
        insertFile(1, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void convertVoice(List<PositionVoiceListBack.DataBean> list) {
        ArrayList arrayList = new ArrayList();
        for (PositionVoiceListBack.DataBean dataBean : list) {
            FileMessage fileMessage = new FileMessage();
            fileMessage.setFileType(3);
            String[] split = dataBean.getFullPath().split(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            String str = Constants.voicePath + MqttTopic.TOPIC_LEVEL_SEPARATOR + split[split.length - 1];
            fileMessage.setFileName(split[split.length - 1]);
            fileMessage.setLocalPath(str);
            fileMessage.setUseObject("语音文件-" + dataBean.getPositionSort());
            fileMessage.setDownloadUrl(dataBean.getFullPath());
            arrayList.add(fileMessage);
        }
        insertFile(3, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delayDismiss() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step1Presenter$w2ZjIGSkqad-ateJnaC25RU_OPY
            @Override // java.lang.Runnable
            public final void run() {
                ErrorCodeNew_Msg.updateErrorCode(false, StringUtils.getString(R.string.Cloudservicedata));
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAppVersion(File file) {
        if (!file.exists()) {
            Tools.upLocalLog("系统启动：APP下载失败");
            return;
        }
        if (Constants.Model == 3) {
            FileUtils.broadcastUpdateApk(file);
        } else {
            AppUtils.installApp(file);
        }
        Tools.upLocalLog("APP成功");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeviceQr(DeviceQrBean deviceQrBean) {
        KvUtil.getInstance().putString("DeviceQrBean", GsonUtils.toJson(deviceQrBean));
        Constants.memberQr = deviceQrBean.getMemberQr();
        Constants.exchangeQr = deviceQrBean.getExchangeQr();
        Constants.exchangeTips = deviceQrBean.getExchangePromptContent();
        Constants.memberTips = deviceQrBean.getMemberPromptContent();
        Constants.merchantLogo = deviceQrBean.getMerchantLogo();
        EventBusManager.getInstance().post(new LogoMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleGoods(List<ProductBean> list) {
        if (list != null) {
            ((Step1Contract.View) this.mRootView).refreshData(list);
            Constants.step1GoodsBack = new ArrayList();
            Constants.step1GoodsBack.addAll(list);
            Iterator<ProductBean> it2 = list.iterator();
            while (it2.hasNext()) {
                List<ProductBean.ProductDetailBean.AttachBean> attachList = it2.next().getGoodsInfoDetailApiVo().getAttachList();
                if (attachList == null || attachList.size() <= 0) {
                    Tools.upLocalLog("获取到的数据为空：" + GsonUtils.toJson(list));
                    return;
                }
                for (int i = 0; i < attachList.size(); i++) {
                    ProductBean.ProductDetailBean.AttachBean attachBean = attachList.get(i);
                    attachBean.setFileName(attachBean.getId() + "_" + attachBean.getFileName());
                    loaddata(attachBean);
                }
            }
            convertProduct(list);
        }
    }

    private void handleMaterial(List<ListTypeAllMaterialBack.DataBean> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                MaterialMessage materialMessage = (MaterialMessage) BeanUtils.modelAconvertoB_Json(list.get(i), MaterialMessage.class);
                MaterialMessage unique = DBHelper.getMaterialMessageDao().queryBuilder().where(MaterialMessageDao.Properties.Id.eq(materialMessage.getId()), new WhereCondition[0]).unique();
                if (unique != null) {
                    materialMessage.setKey(unique.getKey());
                    materialMessage.setIschange(unique.ischange());
                    materialMessage.setLocaName(unique.getLocaName());
                    materialMessage.setLocalFineTuning_hot(unique.getLocalFineTuning_hot());
                    materialMessage.setLocalFineTuning_cold(unique.getLocalFineTuning_cold());
                    materialMessage.setLocalMaterialFineTuning(unique.getLocalMaterialFineTuning());
                    materialMessage.setLocalIsSuger(unique.getLocalIsSuger());
                }
                arrayList.add(materialMessage);
            }
            DBHelper.getMaterialMessageDao().deleteAll();
            DBHelper.getMaterialMessageDao().insertOrReplaceInTx(arrayList);
        }
    }

    private void handlerDeviceInfo(DeviceInfoBean deviceInfoBean) {
        DataLoadChecker.getInstance().notifyDataLoaded(true);
        DeviceInfoBean.TypeInfoBean deviceType = deviceInfoBean.getDeviceType();
        Constants.deviceTypeDetail = deviceType;
        EventBus.getDefault().post(deviceType);
        List<DeviceInfoBean.PowerPlanBean> devicePowerPlan = deviceInfoBean.getDevicePowerPlan();
        if (devicePowerPlan != null && !devicePowerPlan.isEmpty()) {
            Tools.upLocalLog(deviceInfoBean.toString());
            Constants.setOpenAndCloseDev(devicePowerPlan);
        }
        DeviceInfoBean.InfoBean deviceInfo = deviceInfoBean.getDeviceInfo();
        Constants.deviceDetail = deviceInfo;
        checkDefaultSetting();
        EventBus.getDefault().post(deviceInfo);
        if (deviceInfo.getHotHight() < 0.0d || deviceInfo.getHotLow() < 0.0d || deviceInfo.getColdHight() < 0.0d || deviceInfo.getColdLog() < 0.0d) {
            return;
        }
        int hotHight = (int) deviceInfo.getHotHight();
        int hotLow = (int) deviceInfo.getHotLow();
        int coldHight = (int) deviceInfo.getColdHight();
        int coldLog = (int) deviceInfo.getColdLog();
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_SetHotDrinkTempThreshold, hotHight, hotLow));
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_SetColdDrinkTempThreshold, coldHight, coldLog));
        DataProvider.providerDevice(deviceInfoBean);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$getDeviceAndGoodsInfo$0(ListTypeAllMaterialBack listTypeAllMaterialBack) throws Exception {
        if (listTypeAllMaterialBack.getCode() == 200) {
            return listTypeAllMaterialBack.getData();
        }
        throw new Exception(listTypeAllMaterialBack.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ListTypeAllMaterialBack lambda$getListTypeAllMaterial$4(ListTypeAllMaterialBack listTypeAllMaterialBack) throws Exception {
        List<ListTypeAllMaterialBack.DataBean> data;
        if (listTypeAllMaterialBack.getCode() == 200 && (data = listTypeAllMaterialBack.getData()) != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < data.size(); i++) {
                MaterialMessage materialMessage = (MaterialMessage) BeanUtils.modelAconvertoB_Json(data.get(i), MaterialMessage.class);
                MaterialMessage unique = DBHelper.getMaterialMessageDao().queryBuilder().where(MaterialMessageDao.Properties.Id.eq(materialMessage.getId()), new WhereCondition[0]).unique();
                if (unique != null) {
                    materialMessage.setKey(unique.getKey());
                    materialMessage.setIschange(unique.ischange());
                    materialMessage.setLocaName(unique.getLocaName());
                    materialMessage.setLocalFineTuning_hot(unique.getLocalFineTuning_hot());
                    materialMessage.setLocalFineTuning_cold(unique.getLocalFineTuning_cold());
                    materialMessage.setLocalMaterialFineTuning(unique.getLocalMaterialFineTuning());
                    materialMessage.setLocalIsSuger(unique.getLocalIsSuger());
                }
                arrayList.add(materialMessage);
            }
            DBHelper.getMaterialMessageDao().deleteAll();
            DBHelper.getMaterialMessageDao().insertOrReplaceInTx(arrayList);
        }
        return listTypeAllMaterialBack;
    }

    private void loaddata(ProductBean.ProductDetailBean.AttachBean attachBean) {
        Tools.loadFileByFullpath(attachBean);
    }

    public void closeNoPay() {
        Tools.upLocalLog("系统启动：关闭免支付");
        OpsSeting opsSeting = Constants.opsSeting();
        opsSeting.setNopay(false, -1L);
        Constants.setOpsSeting(opsSeting);
    }

    public void deleteMessage() {
        Tools.upLocalLog("系统启动：删除信息成功");
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -30);
        MakeDrinkMessageDao makeDrinkMessageDao = DBHelper.getMakeDrinkMessageDao();
        makeDrinkMessageDao.deleteInTx(makeDrinkMessageDao.queryBuilder().where(MakeDrinkMessageDao.Properties.Time.lt(Long.valueOf(calendar.getTime().getTime())), new WhereCondition[0]).list());
        OpsActionMessageDao opsActionMessageDao = DBHelper.getOpsActionMessageDao();
        opsActionMessageDao.deleteInTx(opsActionMessageDao.queryBuilder().where(OpsActionMessageDao.Properties.Time.lt(Long.valueOf(calendar.getTime().getTime())), new WhereCondition[0]).list());
    }

    public void getADs() {
        ((Step1Contract.Model) this.mModel).programPlanList().retryWhen(new RetryWithDelay(2, 1)).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new ErrorHandleSubscriber<ProgramPlanListBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step1Presenter.4
            @Override // io.reactivex.Observer
            public void onNext(@NonNull ProgramPlanListBack programPlanListBack) {
                Tools.upLocalLog("网络接口-查询设备下的所有节目配置(广告)-programPlanList");
                if (!programPlanListBack.getSuccess()) {
                    Tools.upLocalLog("获取广告失败：" + programPlanListBack.toString());
                    return;
                }
                List<ProgramPlanListBack.DataBean> data = programPlanListBack.getData();
                if (data != null) {
                    Step1Presenter.this.convertAd(data);
                    ADMessageDao aDMessageDao = DBHelper.getADMessageDao();
                    aDMessageDao.deleteAll();
                    for (int i = 0; i < data.size(); i++) {
                        ADMessage aDMessage = (ADMessage) BeanUtils.modelAconvertoB_Gson(data.get(i), ADMessage.class);
                        aDMessage.setStartTime_long(DataUtils.stringyyymmddhhmmssDataLong(aDMessage.getStartTime()));
                        Long stringyyymmddhhmmssDataLong = DataUtils.stringyyymmddhhmmssDataLong(aDMessage.getEndTime());
                        String fullPath = aDMessage.getFullPath();
                        String str = Constants.adPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + fullPath.split(MqttTopic.TOPIC_LEVEL_SEPARATOR)[r6.length - 1];
                        aDMessage.setLocalPath(str);
                        File file = new File(str);
                        boolean isFileExists = FileUtils.isFileExists(file);
                        aDMessage.setKey(null);
                        if (!isFileExists) {
                            FileUtils.downloadFile(fullPath, file);
                        }
                        aDMessage.setEndTime_long(stringyyymmddhhmmssDataLong);
                        aDMessage.setKey(null);
                        aDMessageDao.insert(aDMessage);
                    }
                }
                EventBus.getDefault().post(new AdMessage());
            }
        });
    }

    public void getDeviceAndGoodsInfo() {
        Observable.zip(((Step1Contract.Model) this.mModel).getDeviceInfo(), ((Step1Contract.Model) this.mModel).getGoodsInfo(), ((Step1Contract.Model) this.mModel).listTypeAllMaterial().map(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step1Presenter$ypFkvxqtaUrLHVQidVHO1jc2lKU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step1Presenter.lambda$getDeviceAndGoodsInfo$0((ListTypeAllMaterialBack) obj);
            }
        }).retryWhen(new RetryWithDelay(2, 1)), new Function3() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step1Presenter$1SJrCbLp3rfCo-5evPu1RC0MtOI
            @Override // io.reactivex.functions.Function3
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return Step1Presenter.this.lambda$getDeviceAndGoodsInfo$1$Step1Presenter((DeviceInfoBean) obj, (List) obj2, (List) obj3);
            }
        }).observeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<Object>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step1Presenter.1
            @Override // io.reactivex.Observer
            public void onNext(Object obj) {
                ((Step1Contract.View) Step1Presenter.this.mRootView).adCountdown(true);
                Step1Presenter.this.delayDismiss();
            }
        });
    }

    public void getGoodsInfo() {
        ((Step1Contract.Model) this.mModel).getGoodsInfo().subscribeOn(Schedulers.io()).retryWhen(new RetryWithDelay(2, 1)).subscribeOn(AndroidSchedulers.mainThread()).onErrorReturn(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step1Presenter$FoBuShpXcCv6eA1Q19QWyGo1YmM
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step1Presenter.this.lambda$getGoodsInfo$3$Step1Presenter((Throwable) obj);
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<List<ProductBean>>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step1Presenter.3
            @Override // io.reactivex.Observer
            public void onNext(List<ProductBean> list) {
                Step1Presenter.this.handleGoods(list);
            }
        });
    }

    public void getListTypeAllMaterial() {
        Tools.upLocalLog("网络接口-获取机型下所有原料.listTypeAllMaterial()");
        ((Step1Contract.Model) this.mModel).listTypeAllMaterial().retryWhen(new RetryWithDelay(2, 1)).map(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Step1Presenter$fLV6LCqqpUDr8fMsQUyHLalKXPA
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Step1Presenter.lambda$getListTypeAllMaterial$4((ListTypeAllMaterialBack) obj);
            }
        }).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new ErrorHandleSubscriber<ListTypeAllMaterialBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step1Presenter.6
            @Override // io.reactivex.Observer
            public void onNext(@NonNull ListTypeAllMaterialBack listTypeAllMaterialBack) {
                Tools.upLocalLog("系统启动：获取机型下的所有原料");
                if (listTypeAllMaterialBack.getCode() == 200) {
                    return;
                }
                Tools.upLocalLog("获取型下所有原料失败：" + listTypeAllMaterialBack.toString());
            }
        });
    }

    public void getPayWaySetting() {
        ((Step1Contract.Model) this.mModel).getPayWaySetting().subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<String>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step1Presenter.8
            @Override // io.reactivex.Observer
            public void onNext(String str) {
                Tools.upLocalLog("查询设备存在哪些类型的支付配置" + new Gson().toJson(str));
                PayWaySettingBack payWaySettingBack = (PayWaySettingBack) JSON.parseObject(str, PayWaySettingBack.class);
                Constants.isPayWaySettingBack = payWaySettingBack.getSuccess();
                if (payWaySettingBack.getSuccess()) {
                    Constants.payWaySettingBack = payWaySettingBack;
                    Constants.isPayWaySettingBack = true;
                } else {
                    Tools.upLocalLogTest("获取支付方式失败：" + str);
                }
                Tools.upLocalLog("系统启动：获取设备支付方式。");
            }
        });
    }

    public void getQrConfig() {
        String string = KvUtil.getInstance().getString("DeviceQrBean", null);
        if (string != null && !TextUtils.isEmpty(string)) {
            handleDeviceQr((DeviceQrBean) GsonUtils.fromJson(string, DeviceQrBean.class));
        }
        ((Step1Contract.Model) this.mModel).getQrConfig().observeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<DeviceQrBean>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step1Presenter.2
            @Override // io.reactivex.Observer
            public void onNext(DeviceQrBean deviceQrBean) {
                Step1Presenter.this.handleDeviceQr(deviceQrBean);
            }
        });
    }

    public void getVoices() {
        Tools.upLocalLog("网络接口-查询设备下的所有w位置语音配置(语音)");
        ((Step1Contract.Model) this.mModel).positionVoiceList().retryWhen(new RetryWithDelay(2, 1)).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new ErrorHandleSubscriber<PositionVoiceListBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step1Presenter.5
            @Override // io.reactivex.Observer
            public void onNext(@NonNull PositionVoiceListBack positionVoiceListBack) {
                Tools.upLocalLog("系统启动：获取语音包");
                if (!positionVoiceListBack.getSuccess()) {
                    Tools.upLocalLog("获取语音包失败：" + positionVoiceListBack.toString());
                    return;
                }
                List<PositionVoiceListBack.DataBean> data = positionVoiceListBack.getData();
                if (data != null) {
                    Step1Presenter.this.convertVoice(data);
                    VoiceMessageDao voiceMessageDao = DBHelper.getVoiceMessageDao();
                    voiceMessageDao.deleteAll();
                    for (int i = 0; i < data.size(); i++) {
                        VoiceMessage voiceMessage = (VoiceMessage) BeanUtils.modelAconvertoB_Gson(data.get(i), VoiceMessage.class);
                        String fullPath = voiceMessage.getFullPath();
                        String str = Constants.voicePath + MqttTopic.TOPIC_LEVEL_SEPARATOR + fullPath.split(MqttTopic.TOPIC_LEVEL_SEPARATOR)[r5.length - 1];
                        voiceMessage.setLocalPath(str);
                        File file = new File(str);
                        boolean isFileExists = FileUtils.isFileExists(file);
                        voiceMessage.setKey(null);
                        if (!isFileExists) {
                            FileUtils.downloadFile(fullPath, file);
                        }
                        voiceMessageDao.insert(voiceMessage);
                    }
                }
            }
        });
    }

    public void insertFile(int i, List<FileMessage> list) {
        FileMessageDao fileMessageDao = DBHelper.getFileMessageDao();
        fileMessageDao.deleteInTx(fileMessageDao.queryBuilder().where(FileMessageDao.Properties.FileType.eq(Integer.valueOf(i)), new WhereCondition[0]).list());
        fileMessageDao.insertInTx(list);
    }

    public /* synthetic */ Object lambda$getDeviceAndGoodsInfo$1$Step1Presenter(DeviceInfoBean deviceInfoBean, List list, List list2) throws Exception {
        handlerDeviceInfo(deviceInfoBean);
        handleGoods(list);
        handleMaterial(list2);
        return new Object();
    }

    public /* synthetic */ List lambda$getGoodsInfo$3$Step1Presenter(Throwable th) throws Exception {
        ArmsUtils.snackbarText(th.toString());
        ((Step1Contract.View) this.mRootView).refreshData(null);
        Tools.upLocalLog("商品为空——" + th.toString());
        return null;
    }

    @Override // com.jess.arms.mvp.BasePresenter, com.jess.arms.mvp.IPresenter
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void uploadApp() {
        ((Step1Contract.Model) this.mModel).getMaxAppVersion().subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new ErrorHandleSubscriber<File>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Step1Presenter.7
            @Override // io.reactivex.Observer
            public void onNext(@NonNull File file) {
                if (file != null) {
                    Step1Presenter.this.handleAppVersion(file);
                }
            }
        });
    }
}
