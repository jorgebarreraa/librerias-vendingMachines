package com.yj.coffeemachines.app.service;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.Utils;
import com.google.gson.JsonObject;
import com.jess.arms.base.BaseService;
import com.jess.arms.utils.ArmsUtils;
import com.yj.coffeemachines.Api;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.DevConfig;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.OpsSeting;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.mqtt.MqttCallback;
import com.yj.coffeemachines.app.mqtt.MqttManager;
import com.yj.coffeemachines.app.utils.AppUtils;
import com.yj.coffeemachines.app.utils.BeanUtils;
import com.yj.coffeemachines.app.utils.ByteUtils;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.app.utils.TrafficUtils;
import com.yj.coffeemachines.app.utils.ZIPUtils;
import com.yj.coffeemachines.bean.HttpBean;
import com.yj.coffeemachines.eventbusbean.AdMessage;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew_Msg;
import com.yj.coffeemachines.eventbusbean.MqttStateMessage;
import com.yj.coffeemachines.eventbusbean.ProductRefish;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.ADMessage;
import com.yj.coffeemachines.greendao.beans.FileMessage;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import com.yj.coffeemachines.greendao.beans.OpsActionMessage;
import com.yj.coffeemachines.greendao.beans.VoiceMessage;
import com.yj.coffeemachines.greendao.daos.ADMessageDao;
import com.yj.coffeemachines.greendao.daos.FileMessageDao;
import com.yj.coffeemachines.greendao.daos.MaterialMessageDao;
import com.yj.coffeemachines.greendao.daos.OrderMessageBeanDao;
import com.yj.coffeemachines.greendao.daos.VoiceMessageDao;
import com.yj.coffeemachines.helper.LanguageHelper;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.model.beans.AddOpsLogBack;
import com.yj.coffeemachines.mvp.model.beans.AppUploadBean;
import com.yj.coffeemachines.mvp.model.beans.DeviceInfoBean;
import com.yj.coffeemachines.mvp.model.beans.GetUiInfoBack;
import com.yj.coffeemachines.mvp.model.beans.ListTypeAllMaterialBack;
import com.yj.coffeemachines.mvp.model.beans.PositionVoiceListBack;
import com.yj.coffeemachines.mvp.model.beans.ProgramPlanListBack;
import com.yj.coffeemachines.mvp.model.beans.UploadFileBack;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import it.sauronsoftware.cron4j.Scheduler;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.query.WhereCondition;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MyMqttService extends BaseService {
    private Api api;
    private RxErrorHandler mErrorHandler;
    private ScheduledThreadPoolExecutor mExecutor;
    private ScheduledFuture<?> mScheduledFuture;
    private ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor;
    private MqttManager mqttManager;
    private Scheduler scheduler_heat;
    private IBinder bind = new MyBinder();
    private boolean mqttIsConnect = false;

    /* loaded from: classes.dex */
    public class MyBinder extends Binder {
        public MyBinder() {
        }

        public MyMqttService getService() {
            return MyMqttService.this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWater(String str) {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(SerialOrderBytes.DrawWater);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeTheDoor(String str) {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 3, 4, 1, 0));
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
    public void deleteUI() {
        String absolutePath = MyAppLocation.myAppLocation.getFilesDir().getAbsolutePath();
        List<File> listFilesInDir = FileUtils.listFilesInDir(absolutePath);
        for (int i = 0; i < listFilesInDir.size(); i++) {
            FileUtils.delete(listFilesInDir.get(i));
        }
        FileUtils.listFilesInDir(absolutePath);
    }

    private RequestBody getaddOpsLogBody(String str, String str2, int i) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", str);
        jsonObject.addProperty("ip", NetworkUtils.getBroadcastIpAddress());
        jsonObject.addProperty("operateTimes", str2);
        jsonObject.addProperty("operateType", Integer.valueOf(i));
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    private RequestBody getupdateStatusBody(String str) {
        int i = (str.isEmpty() || Tools.toString(str).equals("")) ? 1 : 2;
        if (str.contains("已连接")) {
            i = 1;
        }
        if (i == 1) {
            str = "";
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceRunStatus", Integer.valueOf(i));
        jsonObject.addProperty("remark", str);
        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    private RequestBody getuploadFileBody(String str) {
        File file = new File(str);
        return new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("text/plain"), file)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initStep() {
        getDevDetail();
        initMqtt();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$addOpsLog$11(Disposable disposable) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$addOpsLog$12() throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ File lambda$appointOutPutOrders$6(String str, String str2, String str3) throws Exception {
        File file = new File(str);
        File file2 = new File(str2);
        FileUtils.copy(file, file2);
        return file2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getADs$17(Disposable disposable) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getADs$18() throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DeviceInfoBean.InfoBean lambda$getDevDetail$8(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (DeviceInfoBean.InfoBean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$getDevOpnenCloase$10(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (List) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DeviceInfoBean.TypeInfoBean lambda$getDevTypeDetail$7(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (DeviceInfoBean.TypeInfoBean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ListTypeAllMaterialBack lambda$getListTypeAllMaterial$9(ListTypeAllMaterialBack listTypeAllMaterialBack) throws Exception {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getUI$13(Disposable disposable) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getUI$14() throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getVoices$15(Disposable disposable) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getVoices$16() throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ File lambda$null$3(ResponseBody responseBody) throws Exception {
        File file = new File(Constants.apkPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(Constants.apkPath + File.separator + "coffee.apk");
        if (com.yj.coffeemachines.app.utils.FileUtils.isFileExists(file2)) {
            com.yj.coffeemachines.app.utils.FileUtils.deleteFile(file2);
        }
        if (FileIOUtils.writeFileFromIS(file2, responseBody.byteStream(), true)) {
            return file2;
        }
        throw new Exception("apk io error");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Thread lambda$onCreate$0(Runnable runnable) {
        Thread thread = new Thread(runnable, "MyService-Scheduler");
        thread.setDaemon(true);
        return thread;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onCreate$1() {
        try {
            EventBus.getDefault().post(new TimeMessage(1));
        } catch (Exception e) {
            Tools.upLocalLog("定时任务异常: " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ File lambda$outPutOrders$5(String str, String str2, String str3) throws Exception {
        File file = new File(str);
        File file2 = new File(str2);
        FileUtils.copy(file, file2);
        return file2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$updateStatus$21(Disposable disposable) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$updateStatus$22() throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AppUploadBean lambda$uploadApp$2(HttpBean httpBean) throws Exception {
        if (httpBean.isSuccess()) {
            return (AppUploadBean) httpBean.getData();
        }
        throw new Exception(httpBean.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lock(String str) {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_LOCK, 1, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openTheDoor(String str) {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 3, 4, 0, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void orderInstant(String str) {
        if (MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Instant() == null || str.isEmpty()) {
            return;
        }
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(ByteUtils.hexTobytes(str.replaceAll("\"", "")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replyDevState() {
        this.mqttManager.publishMessage(Constants.deviceSN() + "devStateBack", Constants.ISMAKINGADRINK ? "2" : "1", false);
        if (Constants.ISMAKINGADRINK) {
            Constants.MQTTPUBLISH = false;
        } else {
            Constants.MQTTPUBLISH = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetting(String str) {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 2, 1, 0, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void state1(String str) {
        if (MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Instant() == null) {
            return;
        }
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_ReadMachineStatus, new int[0]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void state2(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void state3(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unlock(String str) {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_LOCK, 1, 1));
    }

    public void addOpsLog(String str, String str2, int i) {
        OpsActionMessage opsActionMessage = new OpsActionMessage();
        opsActionMessage.setKey(null);
        opsActionMessage.setAction(str);
        opsActionMessage.setTime(Long.valueOf(System.currentTimeMillis()));
        opsActionMessage.setPar0(DataUtils.getNowtimeYY_MM_DD());
        DBHelper.getOpsActionMessageDao().insert(opsActionMessage);
        Tools.upLocalLog("运维日志addOpsLog（logs：" + str + "（日志内容）time" + str2 + "(操作时长)type" + i + "(1-补货 2-清洗 3-检修)");
        this.api.addOpsLog(getaddOpsLogBody(str, str2, i)).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$iFs9eNPsOfI_5AKJYeTztsK-Hg0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MyMqttService.lambda$addOpsLog$11((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$CrOiJFZ037zTTCnf1mQbf66h3Y4
            @Override // io.reactivex.functions.Action
            public final void run() {
                MyMqttService.lambda$addOpsLog$12();
            }
        }).subscribe(new ErrorHandleSubscriber<AddOpsLogBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.app.service.MyMqttService.9
            @Override // io.reactivex.Observer
            public void onNext(@NonNull AddOpsLogBack addOpsLogBack) {
            }
        });
    }

    public void appointOutPutOrders(String str, boolean z, boolean z2) {
        final String str2 = Constants.logPath + File.separator + str;
        final String str3 = str2 + "_" + System.currentTimeMillis() + ".txt";
        getUploadFile(Observable.just(str2).map(new Function() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$kRDhuET9YhTrfSrv5aUf-nFm788
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MyMqttService.lambda$appointOutPutOrders$6(str2, str3, (String) obj);
            }
        }), str3, z, z2);
    }

    public void claenInstant(String str) {
        if (MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Instant() == null) {
            return;
        }
        MyAppLocation.myAppLocation.mSerialDataService.cleanPipe(1);
    }

    public void cleanGround() {
        if (Constants.deviceTypeDetail == null || !Constants.deviceTypeDetail.getName().contains("JK88")) {
            MyAppLocation.myAppLocation.mSerialDataService.cleanGround();
        } else {
            if (MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Instant() == null) {
                return;
            }
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(SerialOrderBytes.FreshlyGroundClear);
        }
    }

    public void cleanGround(int i) {
        if (Constants.deviceTypeDetail == null || !Constants.deviceTypeDetail.getName().contains("JK88")) {
            MyAppLocation.myAppLocation.mSerialDataService.cleanGround(0);
        } else {
            if (MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Instant() == null) {
                return;
            }
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(SerialOrderBytes.FreshlyGroundClear);
        }
    }

    public void closeNoPay() {
        OpsSeting opsSeting = Constants.opsSeting();
        opsSeting.setNopay(false, -1L);
        Constants.setOpsSeting(opsSeting);
    }

    public void deleteMessage(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(12, -5);
        OrderMessageBeanDao orderMessageBeanDao = DBHelper.getOrderMessageBeanDao();
        orderMessageBeanDao.deleteInTx(orderMessageBeanDao.queryBuilder().where(OrderMessageBeanDao.Properties.Timestamp.lt(Long.valueOf(calendar.getTime().getTime())), new WhereCondition[0]).list());
    }

    public void emptying() {
        if (Constants.deviceTypeDetail == null || !Constants.deviceTypeDetail.getName().contains("JK88")) {
            MyAppLocation.myAppLocation.mSerialDataService.emptying();
        } else {
            if (MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Instant() == null) {
                return;
            }
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(SerialOrderBytes.FreshlyGroundPaiKong);
        }
    }

    public void getADs() {
        Tools.upLocalLog("网络接口-查询设备下的所有节目配置(广告)-programPlanList");
        this.api.programPlanList().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$hUbbbSx8QYB03S-C6WLzdzNt6us
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MyMqttService.lambda$getADs$17((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$huG_iLO-Qk-tqLC18ibciM3Kb8g
            @Override // io.reactivex.functions.Action
            public final void run() {
                MyMqttService.lambda$getADs$18();
            }
        }).subscribe(new ErrorHandleSubscriber<ProgramPlanListBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.app.service.MyMqttService.12
            @Override // io.reactivex.Observer
            public void onNext(@NonNull ProgramPlanListBack programPlanListBack) {
                if (programPlanListBack.getSuccess()) {
                    List<ProgramPlanListBack.DataBean> data = programPlanListBack.getData();
                    if (data != null) {
                        MyMqttService.this.convertAd(data);
                        ADMessageDao aDMessageDao = DBHelper.getADMessageDao();
                        aDMessageDao.deleteAll();
                        for (int i = 0; i < data.size(); i++) {
                            ProgramPlanListBack.DataBean dataBean = data.get(i);
                            Tools.upLocalLog("获取广告资源：" + dataBean.toString());
                            ADMessage aDMessage = (ADMessage) BeanUtils.modelAconvertoB_Gson(dataBean, ADMessage.class);
                            aDMessage.setStartTime_long(DataUtils.stringyyymmddhhmmssDataLong(aDMessage.getStartTime()));
                            Long stringyyymmddhhmmssDataLong = DataUtils.stringyyymmddhhmmssDataLong(aDMessage.getEndTime());
                            String fullPath = aDMessage.getFullPath();
                            String str = Constants.adPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + fullPath.split(MqttTopic.TOPIC_LEVEL_SEPARATOR)[r6.length - 1];
                            aDMessage.setLocalPath(str);
                            File file = new File(str);
                            boolean isFileExists = com.yj.coffeemachines.app.utils.FileUtils.isFileExists(file);
                            aDMessage.setKey(null);
                            if (!isFileExists) {
                                com.yj.coffeemachines.app.utils.FileUtils.downloadFile(fullPath, file);
                            }
                            aDMessage.setEndTime_long(stringyyymmddhhmmssDataLong);
                            aDMessage.setKey(null);
                            aDMessageDao.insert(aDMessage);
                        }
                    }
                    EventBus.getDefault().post(new AdMessage());
                }
            }
        });
    }

    public void getDevDetail() {
        Tools.upLocalLog("网络接口-获取设备详情deviceInfo()");
        this.api.deviceInfo().subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).map(new Function() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$9yond3nA_0D63UMbyYB59QRXd1I
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MyMqttService.lambda$getDevDetail$8((HttpBean) obj);
            }
        }).subscribe(new ErrorHandleSubscriber<DeviceInfoBean.InfoBean>(this.mErrorHandler) { // from class: com.yj.coffeemachines.app.service.MyMqttService.6
            @Override // io.reactivex.Observer
            public void onNext(@NonNull DeviceInfoBean.InfoBean infoBean) {
                ErrorCodeNew_Msg.updateErrorCode(false, MyMqttService.this.getString(R.string.Cloudservicedata));
                Tools.upLocalLog("设备详情" + infoBean.toString());
                Constants.deviceDetail = infoBean;
                EventBus.getDefault().post(infoBean);
                if (infoBean.getHotHight() < 0.0d || infoBean.getHotLow() < 0.0d || infoBean.getColdHight() < 0.0d || infoBean.getColdLog() < 0.0d) {
                    return;
                }
                int hotHight = (int) infoBean.getHotHight();
                int hotLow = (int) infoBean.getHotLow();
                int coldHight = (int) infoBean.getColdHight();
                int coldLog = (int) infoBean.getColdLog();
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_SetHotDrinkTempThreshold, hotHight, hotLow));
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_SetColdDrinkTempThreshold, coldHight, coldLog));
            }
        });
    }

    public void getDevOpnenCloase() {
        Tools.upLocalLog("网络接口-设备-定时开关机配置");
        this.api.powerPlanList().subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).map(new Function() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$tHAfH39nixiB4qKxEPZs6x5Kxa4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MyMqttService.lambda$getDevOpnenCloase$10((HttpBean) obj);
            }
        }).subscribe(new ErrorHandleSubscriber<List<DeviceInfoBean.PowerPlanBean>>(this.mErrorHandler) { // from class: com.yj.coffeemachines.app.service.MyMqttService.8
            @Override // io.reactivex.Observer
            public void onNext(@NonNull List<DeviceInfoBean.PowerPlanBean> list) {
                if (list == null || list.isEmpty()) {
                    return;
                }
                Tools.upLocalLog(list.toString());
                Constants.setOpenAndCloseDev(list);
            }
        });
    }

    public void getDevTypeDetail() {
        Tools.upLocalLog("网络接口-获取机型详情.deviceTypeDetail()");
        this.api.deviceTypeDetail().subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).map(new Function() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$D3eFxEjr6eJgFDQ7FZDxg2AsdRM
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MyMqttService.lambda$getDevTypeDetail$7((HttpBean) obj);
            }
        }).subscribe(new ErrorHandleSubscriber<DeviceInfoBean.TypeInfoBean>(this.mErrorHandler) { // from class: com.yj.coffeemachines.app.service.MyMqttService.5
            @Override // io.reactivex.Observer
            public void onNext(@NonNull DeviceInfoBean.TypeInfoBean typeInfoBean) {
                Tools.upLocalLog("机型详情" + typeInfoBean.toMyString());
                Constants.deviceTypeDetail = typeInfoBean;
                EventBus.getDefault().post(typeInfoBean);
            }
        });
    }

    public void getListTypeAllMaterial() {
        Tools.upLocalLog("网络接口-获取机型下所有原料.listTypeAllMaterial()");
        this.api.listTypeAllMaterial().map(new Function() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$bmFqyBMdda7vdoC4iyt9qr1gg24
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MyMqttService.lambda$getListTypeAllMaterial$9((ListTypeAllMaterialBack) obj);
            }
        }).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new ErrorHandleSubscriber<ListTypeAllMaterialBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.app.service.MyMqttService.7
            @Override // io.reactivex.Observer
            public void onNext(@NonNull ListTypeAllMaterialBack listTypeAllMaterialBack) {
            }
        });
    }

    public void getUI() {
        Tools.upLocalLog("网络接口-获取设备资源包信息");
        this.api.getUiInfo().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$UhzhkFHlhcq3T-DY5P41UN9PWFY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MyMqttService.lambda$getUI$13((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$7PRUgSHPL5Ek7qbzfuUfx3apHHo
            @Override // io.reactivex.functions.Action
            public final void run() {
                MyMqttService.lambda$getUI$14();
            }
        }).subscribe(new ErrorHandleSubscriber<GetUiInfoBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.app.service.MyMqttService.10
            @Override // io.reactivex.Observer
            public void onNext(@NonNull GetUiInfoBack getUiInfoBack) {
                if (getUiInfoBack.getSuccess()) {
                    GetUiInfoBack.DataBean data = getUiInfoBack.getData();
                    if (data == null) {
                        MyMqttService.this.deleteUI();
                        return;
                    }
                    String fullPath = data.getFullPath();
                    if (fullPath == null || fullPath.isEmpty()) {
                        MyMqttService.this.deleteUI();
                        return;
                    }
                    String str = Constants.uiPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + data.getPath().split(MqttTopic.TOPIC_LEVEL_SEPARATOR)[r1.length - 1];
                    File file = new File(str);
                    if (!com.yj.coffeemachines.app.utils.FileUtils.isFileExists(str)) {
                        com.yj.coffeemachines.app.utils.FileUtils.downloadFile(fullPath, file);
                        EventBus.getDefault().post(data);
                        return;
                    }
                    try {
                        ZIPUtils.UnZipFolder(file.getAbsolutePath(), MyAppLocation.myAppLocation.getFilesDir().getAbsolutePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void getUploadFile(Observable<File> observable, final String str, final boolean z, final boolean z2) {
        if (Constants.isUpNetworkLog) {
            observable.map(new Function() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$6jx8Tv3hFHcoiSAAAKGmzxHOSMo
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MyMqttService.this.lambda$getUploadFile$19$MyMqttService((File) obj);
                }
            }).flatMap(new Function() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$lGnK8xrwK-2Hq1x1FztJ4j4EWM8
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MyMqttService.this.lambda$getUploadFile$20$MyMqttService((RequestBody) obj);
                }
            }).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new ErrorHandleSubscriber<UploadFileBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.app.service.MyMqttService.13
                @Override // io.reactivex.Observer
                public void onNext(@NonNull UploadFileBack uploadFileBack) {
                    Tools.upLocalLog("网络接口-上传文件" + uploadFileBack.getSuccess());
                    if (uploadFileBack.getSuccess()) {
                        if (z) {
                            MqttManager.getInstance().publishMessage(Constants.deviceSN() + "logUpload", System.currentTimeMillis() + "", false);
                        }
                        if (z2) {
                            ArmsUtils.snackbarText(MyMqttService.this.getString(R.string.loguploadsuccess));
                        }
                        uploadFileBack.getData();
                        FileUtils.delete(str);
                    }
                }
            });
        }
    }

    public void getVoices() {
        Tools.upLocalLog("网络接口-查询设备下的所有w位置语音配置(语音)");
        this.api.positionVoiceList().subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$W3Eg0CbjPvIvU4F8zzcxZS0Xa9k
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MyMqttService.lambda$getVoices$15((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$1fQIUzAupqFZ93yO2Otp3uHmLsA
            @Override // io.reactivex.functions.Action
            public final void run() {
                MyMqttService.lambda$getVoices$16();
            }
        }).subscribe(new ErrorHandleSubscriber<PositionVoiceListBack>(this.mErrorHandler) { // from class: com.yj.coffeemachines.app.service.MyMqttService.11
            @Override // io.reactivex.Observer
            public void onNext(@NonNull PositionVoiceListBack positionVoiceListBack) {
                List<PositionVoiceListBack.DataBean> data;
                Tools.upLocalLogTest("获取语音包内容:" + positionVoiceListBack.toString());
                if (!positionVoiceListBack.getSuccess() || (data = positionVoiceListBack.getData()) == null) {
                    return;
                }
                MyMqttService.this.convertVoice(data);
                VoiceMessageDao voiceMessageDao = DBHelper.getVoiceMessageDao();
                voiceMessageDao.deleteAll();
                for (int i = 0; i < data.size(); i++) {
                    VoiceMessage voiceMessage = (VoiceMessage) BeanUtils.modelAconvertoB_Gson(data.get(i), VoiceMessage.class);
                    String fullPath = voiceMessage.getFullPath();
                    String str = Constants.voicePath + MqttTopic.TOPIC_LEVEL_SEPARATOR + fullPath.split(MqttTopic.TOPIC_LEVEL_SEPARATOR)[r5.length - 1];
                    voiceMessage.setLocalPath(str);
                    File file = new File(str);
                    boolean isFileExists = com.yj.coffeemachines.app.utils.FileUtils.isFileExists(file);
                    voiceMessage.setKey(null);
                    if (!isFileExists) {
                        com.yj.coffeemachines.app.utils.FileUtils.downloadFile(fullPath, file);
                    }
                    voiceMessageDao.insert(voiceMessage);
                }
            }
        });
    }

    @Override // com.jess.arms.base.BaseService
    @SuppressLint({"MissingPermission"})
    public void init() {
        NetworkUtils.registerNetworkStatusChangedListener(new NetworkUtils.OnNetworkStatusChangedListener() { // from class: com.yj.coffeemachines.app.service.MyMqttService.1
            @Override // com.blankj.utilcode.util.NetworkUtils.OnNetworkStatusChangedListener
            public void onConnected(NetworkUtils.NetworkType networkType) {
                EventBus.getDefault().post(new ProductRefish());
            }

            @Override // com.blankj.utilcode.util.NetworkUtils.OnNetworkStatusChangedListener
            public void onDisconnected() {
                ArmsUtils.snackbarText(MyMqttService.this.getString(R.string.netdisconnect));
            }
        });
        NetworkUtils.isAvailableAsync(new Utils.Consumer<Boolean>() { // from class: com.yj.coffeemachines.app.service.MyMqttService.2
            @Override // com.blankj.utilcode.util.Utils.Consumer
            public void accept(Boolean bool) {
                if (bool.booleanValue()) {
                    Tools.upLocalLog("initStep(),开启订阅消息");
                    MyMqttService.this.initStep();
                }
            }
        });
        this.mErrorHandler = ArmsUtils.obtainAppComponentFromContext(this).rxErrorHandler();
        this.api = (Api) ArmsUtils.obtainAppComponentFromContext(this).repositoryManager().obtainRetrofitService(Api.class);
        this.scheduler_heat = new Scheduler();
        this.scheduler_heat.schedule("* * * * *", new Runnable() { // from class: com.yj.coffeemachines.app.service.MyMqttService.3
            @Override // java.lang.Runnable
            public void run() {
                String trafficData = TrafficUtils.getTrafficData();
                MyMqttService.this.mqttManager.publishMessage("coffee/client/" + Constants.deviceSN() + "/heart", trafficData, false);
            }
        });
        this.scheduler_heat.start();
    }

    public void initMqtt() {
        Constants.isConnecting_mqtt = true;
        Tools.upLocalLog("initMqtt订阅消息");
        String deviceSN = Constants.deviceSN();
        final String[] strArr = {deviceSN + "devState", deviceSN + "claenInstant", deviceSN + "cleanGround", deviceSN + "emptying", deviceSN + "resetting", deviceSN + "openTheDoor", deviceSN + "closeTheDoor", deviceSN + "lock", deviceSN + "unlock", deviceSN + "addWater", deviceSN + "orderInstant", deviceSN + "state1", deviceSN + "state2", deviceSN + "state3", deviceSN + "finish", "coffee/server/" + deviceSN + "/clean", "coffee/server/" + deviceSN + "/power", "coffee/server/" + deviceSN + "/programPlan", "coffee/server/" + deviceSN + "/issueUi", "coffee/server/" + deviceSN + "/deviceInfo", "coffee/server/" + deviceSN + "/addMaterial", "coffee/server/" + deviceSN + "/delMaterial", "coffee/server/" + deviceSN + "/onShift", "coffee/server/" + deviceSN + "/offShift", "coffee/server/" + deviceSN + "/activeStart", "coffee/server/" + deviceSN + "/activeStop", "coffee/server/" + deviceSN + "/activeEnd", "coffee/server/" + deviceSN + "/delUI", deviceSN + "reboot", deviceSN + "getLog", "coffee/server/" + deviceSN + "/deviceAppVersionChange", "coffee/server/" + deviceSN + "/issueVoice", "coffee/server/" + deviceSN + "/sendDevicePassword"};
        this.mqttManager = MqttManager.getInstance().init(LanguageHelper.language().getMqttUrl(), Api.MQTT_USERNAME, Api.MQTT_PASSWORD, this, strArr, new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}).setCallback(new MqttCallback() { // from class: com.yj.coffeemachines.app.service.MyMqttService.15
            private String lastTopic = "";
            private long lastMessage = 0;

            @Override // com.yj.coffeemachines.app.mqtt.MqttCallback
            public void connectFail(String str) {
                Tools.upLocalLog("MQTT connectFail " + str);
                Constants.isConnecting_mqtt = false;
                Constants.isConnected_mqtt = false;
                Constants.iStartMQTT_mqtt = 10;
                MyMqttService.this.mqttIsConnect = false;
                EventBus.getDefault().post(new MqttStateMessage(MyMqttService.this.mqttIsConnect));
            }

            @Override // com.yj.coffeemachines.app.mqtt.MqttCallback
            public void connectLost(String str) {
                Tools.upLocalLog("MQTT connectLost " + str);
                Constants.isConnecting_mqtt = false;
                Constants.isConnected_mqtt = false;
                Constants.iStartMQTT_mqtt = 10;
                MyMqttService.this.mqttIsConnect = false;
                EventBus.getDefault().post(new MqttStateMessage(MyMqttService.this.mqttIsConnect));
            }

            @Override // com.yj.coffeemachines.app.mqtt.MqttCallback
            public void connectSuccess(boolean z) {
                Tools.upLocalLog("MQTT connectSuccess " + z);
                Constants.isConnecting_mqtt = false;
                Constants.isConnected_mqtt = true;
                MyMqttService.this.mqttIsConnect = true;
                EventBus.getDefault().post(new MqttStateMessage(MyMqttService.this.mqttIsConnect));
            }

            @Override // com.yj.coffeemachines.app.mqtt.MqttCallback
            public void receiveMessage(String str, String str2) {
                DevConfig devConfig;
                long parseLong;
                Tools.upLocalLog("网上消息接收MQTT receiveMessage：topic:" + str + "------receiveMessage:" + str2);
                if (Constants.ISMAKINGADRINK) {
                    Tools.upLocalLog("检测到正在制作饮料：忽略下发指令。");
                    return;
                }
                try {
                    parseLong = Long.parseLong(str2);
                } catch (Exception unused) {
                }
                if (TextUtils.isEmpty(this.lastTopic) || !this.lastTopic.equals(str) || this.lastMessage <= 0 || parseLong - this.lastMessage >= 2000) {
                    this.lastTopic = str;
                    this.lastMessage = parseLong;
                    if (str.equals(strArr[15])) {
                        if (str2.isEmpty()) {
                            return;
                        }
                        String[] split = str2.split(" ");
                        if (split.length != 3) {
                            return;
                        }
                        int parseInt = Integer.parseInt(split[0]);
                        String str3 = split[1];
                        String str4 = split[2];
                        for (int i = 0; i < parseInt; i++) {
                            if (str3.equals("1") && (devConfig = Constants.devConfig()) != null) {
                                if (devConfig.isRight2()) {
                                    MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_cleanAll, 1));
                                } else {
                                    MyAppLocation.myAppLocation.mSerialDataService.cleanPipe(1);
                                }
                            }
                            if (str4.equals("1")) {
                                MyMqttService.this.cleanGround();
                            }
                        }
                    } else if (str.equals(strArr[16])) {
                        MyMqttService.this.getDevOpnenCloase();
                    } else if (str.equals(strArr[17]) || str.equals(strArr[18])) {
                        MyMqttService.this.getADs();
                    } else if (str.equals(strArr[19])) {
                        MyMqttService.this.getDevDetail();
                    } else if (str.equals(strArr[20])) {
                        MyMqttService.this.getListTypeAllMaterial();
                    } else if (str.equals(strArr[21])) {
                        MyMqttService.this.getListTypeAllMaterial();
                    } else if (str.equals(strArr[22])) {
                        Constants.ProductRefish++;
                        EventBus.getDefault().post(new ProductRefish());
                        MyMqttService.this.getListTypeAllMaterial();
                    } else if (str.equals(strArr[23])) {
                        Constants.ProductRefish++;
                        EventBus.getDefault().post(new ProductRefish());
                        MyMqttService.this.getListTypeAllMaterial();
                    } else if (str.equals(strArr[24])) {
                        Constants.ProductRefish++;
                        EventBus.getDefault().post(new ProductRefish());
                    } else if (str.equals(strArr[25])) {
                        Constants.ProductRefish++;
                        EventBus.getDefault().post(new ProductRefish());
                    } else if (str.equals(strArr[26])) {
                        Constants.ProductRefish++;
                        EventBus.getDefault().post(new ProductRefish());
                    } else if (str.equals(strArr[27])) {
                        MyMqttService.this.getUI();
                    } else if (str.equals(strArr[28])) {
                        Constants.restart();
                    } else if (str.equals(strArr[29])) {
                        try {
                            if (TextUtils.isEmpty(str2)) {
                                MyMqttService.this.outPutOrders(false, true, true);
                            } else {
                                MyMqttService.this.appointOutPutOrders(new JSONObject(str2).getString("date"), true, true);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (str.equals(strArr[30])) {
                        MyMqttService.this.uploadApp();
                    } else if (str.equals(strArr[31])) {
                        MyMqttService.this.getVoices();
                    } else if (str.equals(strArr[32])) {
                        MyMqttService.this.getDevDetail();
                    }
                    if (str.equals(strArr[0])) {
                        MyMqttService.this.replyDevState();
                    }
                    if (Constants.MQTTPUBLISH) {
                        if (str.equals(strArr[1])) {
                            MyMqttService.this.claenInstant(str2);
                            return;
                        }
                        if (str.equals(strArr[2])) {
                            MyMqttService.this.cleanGround();
                            return;
                        }
                        if (str.equals(strArr[3])) {
                            MyMqttService.this.emptying();
                            return;
                        }
                        if (str.equals(strArr[4])) {
                            MyMqttService.this.resetting(str2);
                            return;
                        }
                        if (str.equals(strArr[5])) {
                            MyMqttService.this.openTheDoor(str2);
                            return;
                        }
                        if (str.equals(strArr[6])) {
                            MyMqttService.this.closeTheDoor(str2);
                            return;
                        }
                        if (str.equals(strArr[7])) {
                            MyMqttService.this.lock(str2);
                            return;
                        }
                        if (str.equals(strArr[8])) {
                            MyMqttService.this.unlock(str2);
                            return;
                        }
                        if (str.equals(strArr[9])) {
                            MyMqttService.this.addWater(str2);
                            return;
                        }
                        if (str.equals(strArr[10])) {
                            MyMqttService.this.orderInstant(str2);
                            return;
                        }
                        if (str.equals(strArr[11])) {
                            MyMqttService.this.state1(str2);
                            return;
                        }
                        if (str.equals(strArr[12])) {
                            MyMqttService.this.state2(str2);
                        } else if (str.equals(strArr[13])) {
                            MyMqttService.this.state3(str2);
                        } else if (str.equals(strArr[14])) {
                            Constants.MQTTPUBLISH = false;
                        }
                    }
                }
            }

            @Override // com.yj.coffeemachines.app.mqtt.MqttCallback
            public void subscribedFail(String str) {
                Tools.upLocalLog("MQTT subscribedFail " + str);
            }

            @Override // com.yj.coffeemachines.app.mqtt.MqttCallback
            public void subscribedSuccess(String[] strArr2) {
                Tools.upLocalLog("MQTT subscribedSuccess " + GsonUtils.toJson(strArr2));
                if (MyMqttService.this.scheduler_heat == null || MyMqttService.this.scheduler_heat.isStarted()) {
                    return;
                }
                MyMqttService.this.scheduler_heat.start();
            }
        });
        this.mqttManager.connect();
    }

    public void insertFile(int i, List<FileMessage> list) {
        FileMessageDao fileMessageDao = DBHelper.getFileMessageDao();
        fileMessageDao.deleteInTx(fileMessageDao.queryBuilder().where(FileMessageDao.Properties.FileType.eq(Integer.valueOf(i)), new WhereCondition[0]).list());
        fileMessageDao.insertInTx(list);
    }

    public /* synthetic */ RequestBody lambda$getUploadFile$19$MyMqttService(File file) throws Exception {
        return getuploadFileBody(file.getAbsolutePath());
    }

    public /* synthetic */ ObservableSource lambda$getUploadFile$20$MyMqttService(RequestBody requestBody) throws Exception {
        return this.api.uploadFile("99", requestBody);
    }

    public /* synthetic */ ObservableSource lambda$uploadApp$4$MyMqttService(AppUploadBean appUploadBean) throws Exception {
        Tools.upLocalLog("获取最新APP版本getMaxAppVersion()");
        if (appUploadBean.getAppVersionNum() <= 34520) {
            return Observable.just(null);
        }
        Tools.upLocalLog("下载最新APP版本");
        return this.api.download(appUploadBean.getAppFullPath()).map(new Function() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$Rfe10JjYCQ_tJfQxWwnr1z5aXI8
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MyMqttService.lambda$null$3((ResponseBody) obj);
            }
        });
    }

    @Override // com.jess.arms.base.BaseService, android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.bind;
    }

    @Override // com.jess.arms.base.BaseService, android.app.Service
    public void onCreate() {
        Tools.upLocalLog("AllServices: 创建成功");
        super.onCreate();
        this.mExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$bP5E-4uWDMFPw6ykXGek3Jhii4o
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return MyMqttService.lambda$onCreate$0(runnable);
            }
        });
        this.mScheduledFuture = this.mExecutor.scheduleAtFixedRate(new Runnable() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$D_e1tYH8kYtw3jTp8m7QSjBhLPw
            @Override // java.lang.Runnable
            public final void run() {
                MyMqttService.lambda$onCreate$1();
            }
        }, 0L, 1L, TimeUnit.SECONDS);
    }

    @Override // com.jess.arms.base.BaseService, android.app.Service
    public void onDestroy() {
        Tools.upLocalLog("killAllServices: 取消成功");
        ScheduledFuture<?> scheduledFuture = this.mScheduledFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        this.mExecutor.shutdownNow();
        Scheduler scheduler = this.scheduler_heat;
        if (scheduler != null) {
            scheduler.stop();
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        try {
            MqttManager.getInstance().disconnect();
        } catch (NullPointerException unused) {
        }
        return super.onUnbind(intent);
    }

    public void outPutOrders(boolean z, boolean z2, boolean z3) {
        final String str;
        if (z) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(5, -1);
            str = Constants.logPath + File.separator + TimeUtils.date2String(calendar.getTime(), "yyyy-MM-dd");
        } else {
            str = Constants.logPath + File.separator + TimeUtils.date2String(TimeUtils.getNowDate(), "yyyy-MM-dd");
        }
        final String str2 = str + "_" + System.currentTimeMillis() + ".txt";
        getUploadFile(Observable.just(str).map(new Function() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$rWYnbS4OPuRBXXh7nBRhL_xbE6Y
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MyMqttService.lambda$outPutOrders$5(str, str2, (String) obj);
            }
        }), str2, z2, z3);
    }

    public void updateStatus(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("设备状态——deviceRunStatus:");
        sb.append(str.isEmpty() ? 1 : 2);
        sb.append("remark:");
        sb.append(str);
        Tools.upLocalLog(sb.toString());
        this.api.updateStatus(getupdateStatusBody(str)).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$jvBdzQSZPB8QmsWHq-RqWhE6was
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MyMqttService.lambda$updateStatus$21((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$dkuxAvwLoX77PubNPQuTSD91O-A
            @Override // io.reactivex.functions.Action
            public final void run() {
                MyMqttService.lambda$updateStatus$22();
            }
        }).subscribe(new ErrorHandleSubscriber<String>(this.mErrorHandler) { // from class: com.yj.coffeemachines.app.service.MyMqttService.14
            @Override // io.reactivex.Observer
            public void onNext(@NonNull String str2) {
            }
        });
    }

    public void uploadApp() {
        this.api.getMaxAppVersion().map(new Function() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$JBcICyOZMbU7p2y2VdKAbXv0kbo
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MyMqttService.lambda$uploadApp$2((HttpBean) obj);
            }
        }).flatMap(new Function() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$MyMqttService$3FDJE32jEjjLDdjOp1DFZNPeuIQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MyMqttService.this.lambda$uploadApp$4$MyMqttService((AppUploadBean) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new ErrorHandleSubscriber<File>(this.mErrorHandler) { // from class: com.yj.coffeemachines.app.service.MyMqttService.4
            @Override // io.reactivex.Observer
            public void onNext(@NonNull File file) {
                if (file != null) {
                    if (file.exists()) {
                        AppUtils.install(file);
                    } else {
                        Tools.upLocalLog("系统启动：APP下载失败");
                    }
                }
            }
        });
    }
}
