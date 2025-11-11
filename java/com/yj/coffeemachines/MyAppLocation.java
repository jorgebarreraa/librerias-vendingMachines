package com.yj.coffeemachines;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import com.blankj.utilcode.util.NetworkUtils;
import com.hjq.language.MultiLanguages;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.integration.EventBusManager;
import com.yj.coffeemachines.app.mqtt.MqttManager;
import com.yj.coffeemachines.app.service.MultiScreenService;
import com.yj.coffeemachines.app.service.MyMqttService;
import com.yj.coffeemachines.app.service.SerialDataService;
import com.yj.coffeemachines.app.service.VoicePlayService;
import com.yj.coffeemachines.app.utils.KvUtil;
import com.yj.coffeemachines.eventbusbean.NetWorkState;
import com.yj.coffeemachines.helper.LanguageHelper;
import com.yj.coffeemachines.helper.Tools;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class MyAppLocation extends BaseApplication {
    public static MyAppLocation myAppLocation;
    private NetworkUtils.OnNetworkStatusChangedListener changedListener;
    private Intent mIntent;
    public Intent mIntent1;
    private Intent mIntent2;
    public SerialDataService mSerialDataService;
    private MultiScreenService multiScreenService;
    public MyMqttService myMqttService;
    public VoicePlayService voicePlayService;
    private ServiceConnection mConnection = new ServiceConnection() { // from class: com.yj.coffeemachines.MyAppLocation.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Tools.upLocalLog("SerialDataService启动成功");
            MyAppLocation.this.mSerialDataService = ((SerialDataService.MyBinder) iBinder).getService();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Tools.upLocalLog("SerialDataService启动失败");
        }
    };
    public ServiceConnection mConnection1 = new ServiceConnection() { // from class: com.yj.coffeemachines.MyAppLocation.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Tools.upLocalLog("AllServicesMyMqttService启动成功");
            MyAppLocation.this.myMqttService = ((MyMqttService.MyBinder) iBinder).getService();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Tools.upLocalLog("MyMqttService已关闭");
            Log.d("TAG", "AllServicesonServiceDisconnected: MyMqttService已关闭");
            MyAppLocation.this.myMqttService = null;
        }
    };
    private ServiceConnection mConnection2 = new ServiceConnection() { // from class: com.yj.coffeemachines.MyAppLocation.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Tools.upLocalLog("VoicePlayService启动成功");
            MyAppLocation.this.voicePlayService = ((VoicePlayService.VoicePlayBinder) iBinder).getService();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Tools.upLocalLog("VoicePlayService启动失败");
            Log.d("TAG", "onServiceDisconnected:VoicePlayService启动失败 ");
        }
    };
    private final ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.yj.coffeemachines.MyAppLocation.5
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyAppLocation.this.multiScreenService = ((MultiScreenService.MultiScreenBinder) iBinder).getService();
            if (Constants.getDualLCD() == 1) {
                MyAppLocation.this.showSecondaryDisplayIfNeeded();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MyAppLocation.this.multiScreenService = null;
        }
    };

    private boolean isDualScreenSupported() {
        return Constants.Model == 0;
    }

    public static void killAllServices(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (context.checkCallingOrSelfPermission("android.permission.KILL_BACKGROUND_PROCESSES") == 0) {
            Iterator<ActivityManager.RunningServiceInfo> it2 = runningServices.iterator();
            while (it2.hasNext()) {
                String className = it2.next().service.getClassName();
                Tools.upLocalLog("TAG killAllServices: " + className);
                activityManager.killBackgroundProcesses(className);
            }
        }
    }

    public void closeSearchPresentation() {
        MultiScreenService multiScreenService;
        if (isDualScreenSupported() && (multiScreenService = this.multiScreenService) != null) {
            multiScreenService.hindSearchPresentation();
        }
    }

    public String getThisProcessName() {
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) getApplicationContext().getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
            continue;
        }
        return null;
    }

    public boolean isAppProcess() {
        String thisProcessName = getThisProcessName();
        return thisProcessName != null && thisProcessName.equalsIgnoreCase(getPackageName());
    }

    @Override // com.jess.arms.base.BaseApplication, android.app.Application
    public void onCreate() {
        KvUtil.getInstance().init(this);
        LanguageHelper.initLanguage();
        super.onCreate();
        myAppLocation = this;
        this.mIntent = new Intent(this, (Class<?>) SerialDataService.class);
        this.mIntent1 = new Intent(this, (Class<?>) MyMqttService.class);
        this.mIntent2 = new Intent(this, (Class<?>) VoicePlayService.class);
        if (isAppProcess()) {
            bindService(this.mIntent, this.mConnection, 1);
            bindService(this.mIntent2, this.mConnection2, 1);
        }
        this.changedListener = new NetworkUtils.OnNetworkStatusChangedListener() { // from class: com.yj.coffeemachines.MyAppLocation.4
            @Override // com.blankj.utilcode.util.NetworkUtils.OnNetworkStatusChangedListener
            public void onConnected(NetworkUtils.NetworkType networkType) {
                EventBusManager.getInstance().post(new NetWorkState(true, networkType.name()));
            }

            @Override // com.blankj.utilcode.util.NetworkUtils.OnNetworkStatusChangedListener
            public void onDisconnected() {
                EventBusManager.getInstance().post(new NetWorkState(false, ""));
            }
        };
        NetworkUtils.registerNetworkStatusChangedListener(this.changedListener);
        MultiLanguages.setAppLanguage(this, new Locale(LanguageHelper.language().getLangCode()));
    }

    public void onDestroy() {
        Tools.upLocalLog("killAllServices:开始关闭服务");
        NetworkUtils.unregisterNetworkStatusChangedListener(this.changedListener);
        try {
            MqttManager.getInstance().disconnect();
        } catch (Exception unused) {
        }
        killAllServices(this);
    }

    @Override // com.jess.arms.base.BaseApplication, android.app.Application
    public void onTerminate() {
        super.onTerminate();
        getApplicationContext().unbindService(this.serviceConnection);
    }

    public void openSearchPresentation() {
        if (isDualScreenSupported()) {
            getApplicationContext().bindService(new Intent(this, (Class<?>) MultiScreenService.class), this.serviceConnection, 1);
        }
    }

    public void showSecondaryDisplayIfNeeded() {
        MultiScreenService multiScreenService = this.multiScreenService;
        if (multiScreenService != null) {
            multiScreenService.showSearchPresentation();
        }
    }
}
