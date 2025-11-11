package com.yj.coffeemachines.app.service;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.WorkRequest;
import com.blankj.utilcode.util.ToastUtils;
import com.jess.arms.base.BaseService;
import com.jess.arms.utils.ArmsUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.DevConfig;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.OpsSeting;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.mqtt.MqttManager;
import com.yj.coffeemachines.app.serialport.ConfigurationSdk;
import com.yj.coffeemachines.app.serialport.SerialPortManager;
import com.yj.coffeemachines.app.serialport.listener.OnOpenSerialPortListener;
import com.yj.coffeemachines.app.serialport.listener.OnSerialPortDataListener;
import com.yj.coffeemachines.app.utils.ByteUtils;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.app.utils.ErrorUtils;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew_Msg;
import com.yj.coffeemachines.eventbusbean.ErrorCode_CurrentGrinding;
import com.yj.coffeemachines.eventbusbean.ErrorCode_IceDEV;
import com.yj.coffeemachines.eventbusbean.ErrorCode_Instant;
import com.yj.coffeemachines.eventbusbean.ErrorCode_Making;
import com.yj.coffeemachines.eventbusbean.ErrorCode_Paymenting;
import com.yj.coffeemachines.eventbusbean.MoneyReceiveMessage;
import com.yj.coffeemachines.eventbusbean.MyEventBusMessage;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import com.yj.coffeemachines.exception.SerialPortOpenFailureException;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.message.LogManager;
import com.yj.coffeemachines.mvp.message.RecvMessage;
import com.yj.coffeemachines.mvp.message.SendMessage;
import com.yj.coffeemachines.pay.mdb.MdbReceivePayment;
import com.yj.coffeemachines.pay.mdb.TransmissionUtils;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.LongCompanionObject;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class SerialDataService extends BaseService implements OnOpenSerialPortListener, OnSerialPortDataListener {
    static final int TIME = 30;
    private int currentsendtimes;
    long getCupTimedown_time;
    private Handler mHandler;
    private ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor;
    private SerialPortManager mSerialPortManager_Currentgrinding;
    private SerialPortManager mSerialPortManager_Instant;
    private SerialPortManager mSerialPortManager_Payment;
    boolean oldislink1;
    boolean oldislink2;
    boolean oldislink3;
    long time_current;
    long time_instant;
    long time_payment;
    private int times1;
    int times_countdown;
    private IBinder bind = new MyBinder();
    public boolean mConversionNotice = true;
    private boolean issendCurrentFinish = false;
    public byte[] standyOrder = Constants.getOrderBytes_Currentgrinding(MqttWireMessage.MESSAGE_TYPE_PINGREQ, (byte) 92, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) -86);
    public boolean communicationStatus_Currentgrinding = false;
    public long lastTime_Currentgrinding = 0;
    public boolean communicationStatus_Payment = false;
    public long lastTime_Payment = 0;
    public boolean communicationStatus_Instant = false;
    public long lastTime_Instant = 0;
    public String state_Currentgrinding = "";
    public String state_Payment_change = "";
    public String state_Payment_cash = "";
    public String state_Payment_card = "";
    public String state_Instant = "";
    public String state_Ice = "";
    private int time_amd = 5;
    private int uploadtime = 60;
    private int offline_time = 30;
    private int counter = 0;
    int time_interval = 3;
    boolean check_instant = false;
    boolean check_current = false;
    boolean check_payment = false;
    boolean state_instant = false;
    boolean state_current = false;
    boolean state_payment = false;
    int times = 0;
    boolean runflag_getCupTimedown = true;
    AlertDialog dialog_instan = null;
    AlertDialog dialog_current = null;
    int clearNum = 0;
    int oldClearNum = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.yj.coffeemachines.app.service.SerialDataService$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ ScheduledFuture val$future;

        AnonymousClass3(ScheduledFuture scheduledFuture) {
            this.val$future = scheduledFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertDialog.Builder builder = new AlertDialog.Builder(MyAppLocation.myAppLocation);
            builder.setTitle(R.string.paplecleaning);
            builder.setCancelable(false);
            builder.setMessage(R.string.wateing);
            SerialDataService.this.dialog_instan = builder.create();
            if (Build.VERSION.SDK_INT >= 25) {
                SerialDataService.this.dialog_instan.getWindow().setType(2038);
            } else {
                SerialDataService.this.dialog_instan.getWindow().setType(2003);
            }
            AlertDialog alertDialog = SerialDataService.this.dialog_instan;
            final ScheduledFuture scheduledFuture = this.val$future;
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$SerialDataService$3$TLPfa5YxEn1DyYm2cQbZ-A6bbkA
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    scheduledFuture.cancel(false);
                }
            });
            SerialDataService.this.dialog_instan.show();
        }
    }

    /* loaded from: classes.dex */
    public class MyBinder extends Binder {
        public MyBinder() {
        }

        public SerialDataService getService() {
            return SerialDataService.this;
        }
    }

    private void checkLink() {
        long currentTimeMillis = System.currentTimeMillis();
        if (Constants.ISOPEN_INSTANT) {
            long j = this.lastTime_Instant;
            if (j == 0) {
                this.lastTime_Instant = currentTimeMillis;
            } else if (currentTimeMillis - j >= this.offline_time * 1000) {
                this.communicationStatus_Instant = false;
                linkStateChange(1, false);
            }
        }
        if (Constants.ISOPEN_CURRENTGRINDING) {
            long j2 = this.lastTime_Currentgrinding;
            if (j2 == 0) {
                this.lastTime_Currentgrinding = currentTimeMillis;
            } else if (currentTimeMillis - j2 >= this.offline_time * 1000) {
                this.communicationStatus_Currentgrinding = false;
                this.lastTime_Currentgrinding = currentTimeMillis;
                linkStateChange(2, false);
            }
        }
        if (Constants.ISOPEN_PAYMENT) {
            long j3 = this.lastTime_Payment;
            if (j3 == 0) {
                this.lastTime_Payment = currentTimeMillis;
            } else if (currentTimeMillis - j3 >= this.offline_time * 1000) {
                this.communicationStatus_Payment = false;
                linkStateChange(3, false);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0093, code lost:
    
        if (r4.contains("00 06") != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0097, code lost:
    
        r0 = "设置7种热饮的高低温度门限值失败";
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a8, code lost:
    
        if (r4.contains("00 07") != false) goto L103;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void clearupData(byte[] r4, java.lang.String r5, int r6) {
        /*
            Method dump skipped, instructions count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yj.coffeemachines.app.service.SerialDataService.clearupData(byte[], java.lang.String, int):void");
    }

    private void detailCurrentgrinding(byte[] bArr) {
        String str;
        if (bArr[6] == Byte.MAX_VALUE) {
            if (Constants.ISCLEAN_CURRENT) {
                Tools.upLocalLogTest("现磨清洗完成");
                AlertDialog alertDialog = this.dialog_current;
                if (alertDialog != null && alertDialog.isShowing()) {
                    this.dialog_current.dismiss();
                }
                Constants.ISCLEAN_CURRENT = false;
                return;
            }
            if (Constants.ISMAKINGADRINK) {
                EventBus.getDefault().post(new ErrorCode_Making(true, ErrorCode_Making.ErrorCode.CURRENTFINISH, ErrorCode_Making.ErrorCode.CURRENTFINISH.name()));
                return;
            }
        } else if (Constants.ISCLEAN_CURRENT) {
            Tools.upLocalLog("现磨清洗保持");
            this.mSerialPortManager_Currentgrinding.addSendList(Constants.getOrderBytes_Currentgrinding(MqttWireMessage.MESSAGE_TYPE_UNSUBACK, (byte) 90, (byte) 5, (byte) 50, (byte) 8, (byte) 0, (byte) 0));
            return;
        } else if (Constants.ISMAKINGADRINK) {
            EventBus.getDefault().post(new ErrorCode_Making(true, ErrorCode_Making.ErrorCode.CURRENTFINISHING, ErrorCode_Making.ErrorCode.CURRENTFINISHING.name()));
            return;
        }
        byte b = bArr[4];
        byte b2 = bArr[5];
        byte b3 = ByteUtils.getBooleanArray(b)[2];
        ErrorCodeNew_Msg.updateErrorCode(b3 == 1, ErrorCodeNew.E05.name());
        if (b3 == 1) {
            str = " 现磨：酿造核心没有就位";
        } else {
            str = "";
        }
        byte[] booleanArray = ByteUtils.getBooleanArray(b2);
        if (booleanArray[0] == 1) {
            ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E09.name());
            str = str + " " + ErrorCode_CurrentGrinding.ErrorCode.E09.name();
        }
        if (booleanArray[1] == 1) {
            ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E08.name());
            str = str + " " + ErrorCode_CurrentGrinding.ErrorCode.E08.name();
        }
        if (booleanArray[2] == 1) {
            ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E07.name());
            str = str + " " + ErrorCode_CurrentGrinding.ErrorCode.E07.name();
        }
        if (booleanArray[5] == 1) {
            ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E06.name());
            str = str + " " + ErrorCode_CurrentGrinding.ErrorCode.E06.name();
        }
        this.state_Currentgrinding = str;
        if (str.isEmpty()) {
            EventBus.getDefault().post(new ErrorCode_CurrentGrinding(true, ErrorCode_CurrentGrinding.ErrorCode.E06, ""));
            return;
        }
        if (Constants.ISMAKINGADRINK) {
            Tools.upLocalLog("现磨出问题：" + str);
            EventBus.getDefault().post(new ErrorCode_Making(false, ErrorCode_Making.ErrorCode.CURRENTERROR, ErrorCode_Making.ErrorCode.CURRENTERROR.name() + str));
        }
    }

    private void detailInstant(byte[] bArr) {
        StringBuilder sb;
        String str;
        String str2;
        String str3;
        byte b = bArr[3];
        if (bArr[3] == 32 && bArr[4] == 4) {
            byte b2 = bArr[7];
            byte b3 = bArr[8];
            if (Constants.clearCurrentTime <= 5) {
                ErrorCodeNew_Msg.updateErrorCode(ByteUtils.getBooleanArray(bArr[10])[2] == 1, ErrorCodeNew.E05.name());
                byte[] booleanArray = ByteUtils.getBooleanArray(bArr[9]);
                ErrorCodeNew_Msg.updateErrorCode(booleanArray[0] == 1, ErrorCodeNew.E09.name());
                ErrorCodeNew_Msg.updateErrorCode(booleanArray[1] == 1, ErrorCodeNew.E08.name());
                ErrorCodeNew_Msg.updateErrorCode(booleanArray[2] == 1, ErrorCodeNew.E07.name());
                ErrorCodeNew_Msg.updateErrorCode(booleanArray[5] == 1, ErrorCodeNew.E06.name());
                return;
            }
            byte[] booleanArray2 = ByteUtils.getBooleanArray(b3);
            if (booleanArray2[2] == 0 && booleanArray2[3] == 0 && Constants.clearCurrentTime > 12) {
                Constants.clearCurrentTime = 5;
            }
            if (b2 != Byte.MAX_VALUE || Constants.clearCurrentTime <= 5 || booleanArray2[2] == 1 || booleanArray2[3] == 0) {
                return;
            }
            Constants.clearCurrentTime = 5;
            return;
        }
        if (bArr[3] == 32 && bArr[4] == 2) {
            if (Constants.opsSeting().isDoorFaultIgnore()) {
                return;
            }
            if (bArr.length < 14) {
                Tools.upLocalLog("前门故障信息命令异常：" + Tools.byteToStr(bArr));
                return;
            }
            String str4 = ByteUtils.to32BitBinaryString(ByteUtils.convertsByteToInt(new byte[]{bArr[9], bArr[10]}, new byte[]{bArr[7], bArr[8]}));
            ErrorCodeNew_Msg.updateErrorCode(Long.parseLong(str4.substring(29), 2) == 4, ErrorCodeNew.A161.name());
            ErrorCodeNew_Msg.updateErrorCode(Long.parseLong(str4.substring(26, 29), 2) == 4, ErrorCodeNew.A167.name());
            ErrorCodeNew_Msg.updateErrorCode(Long.parseLong(str4.substring(23, 26), 2) == 4, ErrorCodeNew.A02.name());
            if (!Constants.opsSeting().isV3Lbq()) {
                ErrorCodeNew_Msg.updateErrorCode(Long.parseLong(str4.substring(20, 23), 2) == 4, ErrorCodeNew.E22.name());
                ErrorCodeNew_Msg.updateErrorCode(Long.parseLong(str4.substring(17, 20), 2) == 4, ErrorCodeNew.E23.name());
            }
            ErrorCodeNew_Msg.updateErrorCode(Long.parseLong(str4.substring(14, 17), 2) == 4, ErrorCodeNew.A160.name());
            ErrorCodeNew_Msg.updateErrorCode(Long.parseLong(str4.substring(11, 14), 2) == 4, ErrorCodeNew.A04.name());
            ErrorCodeNew_Msg.updateErrorCode(Long.parseLong(str4.substring(8, 11), 2) == 4, ErrorCodeNew.A123.name());
            return;
        }
        if (bArr[3] == 32 && bArr[4] == 1) {
            byte b4 = bArr[9];
            byte b5 = bArr[10];
            byte b6 = bArr[7];
            byte[] booleanArray3 = ByteUtils.getBooleanArray(bArr[8]);
            ByteUtils.getBooleanArray(b6);
            ByteUtils.getBooleanArray(b5);
            ByteUtils.getBooleanArray(b4);
            ErrorCodeNew_Msg.updateErrorCode(booleanArray3[2] == 1, ErrorCodeNew.E27.name());
            return;
        }
        if (bArr[3] == 41) {
            Tools.upLocalLog("排空查询: " + ByteUtils.byte2HexStr2(bArr));
            if ((bArr[6] & 133) == 133) {
                Constants.ISCLEAN_WATER = 1;
                return;
            } else if ((bArr[5] & 193) == 193) {
                Constants.ISCLEAN_WATER = 2;
                return;
            } else {
                if ((bArr[5] & ByteCompanionObject.MIN_VALUE) == 128) {
                    Constants.ISCLEAN_WATER = 2;
                    return;
                }
                return;
            }
        }
        if (b == Constants.CMD_instant.CMD_RUN_DOFFING.getId()) {
            String byteToStr = Tools.byteToStr(bArr);
            if (byteToStr.toUpperCase().contains("210206002406010B")) {
                Tools.upLocalLog("检测落盖成功" + byteToStr);
                return;
            }
            Tools.upLocalLog("检测落盖失败" + byteToStr);
            return;
        }
        if (b == Constants.CMD_instant.CMD_SetTimeMaterialAndWater.getId()) {
            if (Constants.ISMAKINGADRINK) {
                if (bArr[4] == 0) {
                    EventBus.getDefault().post(new ErrorCode_Making(true, ErrorCode_Making.ErrorCode.SETWATERANDMATERA, ErrorCode_Making.ErrorCode.SETWATERANDMATERA.name()));
                    return;
                } else {
                    Tools.upLocalLog("设置某种饮品出水出料时间失败");
                    EventBus.getDefault().post(new ErrorCode_Making(false, ErrorCode_Making.ErrorCode.SETWATERANDMATERA, ErrorCode_Making.ErrorCode.SETWATERANDMATERA.name()));
                    return;
                }
            }
            return;
        }
        if (b == Constants.CMD_instant.CMD_SellOneCupDrink.getId()) {
            if (Constants.ISMAKINGADRINK) {
                if (bArr[4] != 0) {
                    Tools.upLocalLog("出某种饮料一杯失败");
                    EventBus.getDefault().post(new ErrorCode_Making(false, ErrorCode_Making.ErrorCode.GETDRINK, ErrorCode_Making.ErrorCode.GETDRINK.name()));
                    return;
                } else {
                    this.getCupTimedown_time = System.currentTimeMillis();
                    this.runflag_getCupTimedown = true;
                    EventBus.getDefault().post(new ErrorCode_Making(true, ErrorCode_Making.ErrorCode.GETDRINK, ErrorCode_Making.ErrorCode.GETDRINK.name()));
                    return;
                }
            }
            return;
        }
        if (b == Constants.CMD_instant.CMD_ReadMachineErrorCode.getId()) {
            ErrorCodeNew_Msg.MacState = ErrorCodeNew.Error;
            Constants.isGetMacState = true;
            if (Constants.ISMAKINGADRINK || Constants.NowStep == 1) {
                if (bArr[4] == 5) {
                    this.runflag_getCupTimedown = false;
                    this.getCupTimedown_time = LongCompanionObject.MAX_VALUE;
                    EventBus.getDefault().post(new ErrorCode_Making(true, ErrorCode_Making.ErrorCode.GETCUP, ErrorCode_Making.ErrorCode.GETCUP.name()));
                    this.times1 = 0;
                    this.issendCurrentFinish = false;
                    return;
                }
                if (bArr[4] == 6) {
                    Tools.upLocalLog("落冰完成");
                    EventBus.getDefault().post(new ErrorCode_Making(true, ErrorCode_Making.ErrorCode.GETICE, ErrorCode_Making.ErrorCode.GETICE.name()));
                    return;
                } else if (bArr[4] == 16) {
                    this.runflag_getCupTimedown = false;
                    EventBus.getDefault().post(new ErrorCode_Making(true, ErrorCode_Making.ErrorCode.FINISH, ErrorCode_Making.ErrorCode.FINISH.name()));
                    return;
                } else if (bArr[4] == 32) {
                    ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E12.name());
                    this.runflag_getCupTimedown = false;
                    Tools.upLocalLog("检测到轨道异物，制作失败退出。");
                    EventBus.getDefault().post(new ErrorCode_Making(false, ErrorCode_Making.ErrorCode.ORBIT, ErrorCode_Making.ErrorCode.ORBIT.name()));
                    return;
                }
            }
            byte[] booleanArray4 = ByteUtils.getBooleanArray(bArr[4]);
            byte b7 = booleanArray4[0];
            byte b8 = booleanArray4[1];
            byte b9 = booleanArray4[2];
            byte b10 = booleanArray4[3];
            byte b11 = booleanArray4[4];
            byte b12 = booleanArray4[5];
            byte b13 = booleanArray4[6];
            byte b14 = booleanArray4[7];
            ErrorCodeNew_Msg.updateErrorCode(b7 == 1, ErrorCodeNew.E01.name());
            ErrorCodeNew_Msg.updateErrorCode(b8 == 1, ErrorCodeNew.E02.name());
            ErrorCodeNew_Msg.updateErrorCode(b9 == 1, ErrorCodeNew.E03.name());
            ErrorCodeNew_Msg.updateErrorCode(b10 == 1, ErrorCodeNew.E04.name());
            ErrorCodeNew_Msg.updateErrorCode(b12 == 1, ErrorCodeNew.E12.name());
            ErrorCodeNew_Msg.updateErrorCode(b13 == 1, ErrorCodeNew.A01.name());
            ErrorCodeNew_Msg.updateErrorCode(b14 == 1, ErrorCodeNew.E25.name());
            if (b7 == 1) {
                str2 = " " + ErrorCode_Instant.ErrorCode.E01.name();
                str3 = "(无水)";
            } else {
                str2 = "";
                str3 = str2;
            }
            if (b8 == 1) {
                str2 = str2 + " " + ErrorCode_Instant.ErrorCode.E02.name();
                str3 = str3 + "(无杯)";
            }
            if (b9 == 1) {
                str2 = str2 + " " + ErrorCode_Instant.ErrorCode.E03.name();
                str3 = str3 + "(无水无杯)";
            }
            if (b10 == 1) {
                str2 = str2 + " " + ErrorCode_Instant.ErrorCode.E04.name();
                str3 = str3 + "(温度传感器异常)";
            }
            if (b13 == 1) {
                ErrorCodeNew_Msg.MacState = ErrorCodeNew.Normal;
            }
            if (b14 == 1) {
                str2 = str2 + " " + ErrorCode_Instant.ErrorCode.E25.name();
                str3 = str3 + "(机器正在加热中，请稍后购买)";
            }
            this.state_Instant = str2.trim();
            if (Constants.deviceTypeDetail != null && Constants.deviceTypeDetail.getName().contains("杯茶机")) {
                str3 = str3.replace("(无杯)", "");
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E02.name());
                addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_LOCK, new int[0]));
            }
            if (Constants.deviceTypeDetail == null || !Constants.deviceTypeDetail.getName().contains("杯茶机") || str3 == "") {
                ErrorCodeNew_Msg.MacState = ErrorCodeNew.Normal;
            } else {
                Tools.upLocalLog("杯茶机_检测速溶，机器出现故障，发送具体错误查询");
            }
            if (str3 != "") {
                EventBus.getDefault().post(new ErrorCode_Instant(false, ErrorCode_Instant.ErrorCode.UNKNOWN, ErrorCode_Instant.ErrorCode.UNKNOWN.name()));
                return;
            }
            return;
        }
        if (b == Constants.CMD_instant.CMD_LOCK.getId()) {
            if (Constants.deviceTypeDetail.getName().contains("杯茶") && bArr.length == 7) {
                byte[] booleanArray5 = ByteUtils.getBooleanArray(bArr[5]);
                ErrorCodeNew_Msg.updateErrorCode(booleanArray5[0] == 1, ErrorCodeNew.A156.name());
                ErrorCodeNew_Msg.updateErrorCode(booleanArray5[1] == 1, ErrorCodeNew.A157.name());
                ErrorCodeNew_Msg.updateErrorCode(booleanArray5[2] == 1, ErrorCodeNew.A158.name());
                byte b15 = booleanArray5[0];
                return;
            }
            return;
        }
        if (b == Constants.CMD_instant.CMD_ReadMachineStatus.getId()) {
            Constants.MACHINESTATE = bArr[4];
            if (bArr[4] == 0) {
                ErrorCodeNew_Msg.MacState = ErrorCodeNew.Normal;
            }
            if (bArr[4] == 1) {
                ErrorCodeNew_Msg.MacState = ErrorCodeNew.Busy;
            }
            if (bArr[4] == 2) {
                if (Constants.deviceTypeDetail == null || !Constants.deviceTypeDetail.getName().contains("杯茶机")) {
                    Tools.upLocalLog("检测速溶，机器出现故障，发送具体错误查询");
                }
                this.mSerialPortManager_Instant.addSendList(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_ReadMachineErrorCode, new int[0]));
                return;
            }
            return;
        }
        if (b == Constants.CMD_instant.CMD_Test.getId()) {
            if (!Constants.ISMAKINGADRINK || bArr[4] == 0) {
                return;
            }
            Tools.upLocalLog("出杯盖失败");
            EventBus.getDefault().post(new ErrorCode_Making(false, ErrorCode_Making.ErrorCode.TEST, ErrorCode_Making.ErrorCode.TEST.name()));
            return;
        }
        if (b == Constants.CMD_instant.CMD_SetCupFallingWay.getId()) {
            if (Constants.ISMAKINGADRINK) {
                EventBus.getDefault().post(new ErrorCode_Making(true, ErrorCode_Making.ErrorCode.CUPTYPE, ErrorCode_Making.ErrorCode.CUPTYPE.name()));
                return;
            }
            return;
        }
        if (b == Constants.CMD_instant.FreshGroundCoffeeFinished.getId()) {
            if (Constants.ISMAKINGADRINK) {
                if (bArr[4] == 0 && !this.issendCurrentFinish) {
                    EventBus.getDefault().post(new ErrorCode_Making(true, ErrorCode_Making.ErrorCode.FRESHGROUNDCOFFEEFINISHED, ErrorCode_Making.ErrorCode.FRESHGROUNDCOFFEEFINISHED.name()));
                }
                int i = this.times1;
                if (i != 4) {
                    this.times1 = i + 1;
                    this.mSerialPortManager_Instant.addSendList(Constants.getOrderBytes_instant(Constants.CMD_instant.FreshGroundCoffeeFinished, 1));
                    return;
                }
                return;
            }
            return;
        }
        if (b == Constants.CMD_instant.CMD_CheckIce.getId()) {
            if (bArr.length != 7) {
                return;
            }
            byte[] booleanArray6 = ByteUtils.getBooleanArray(bArr[5]);
            byte b16 = booleanArray6[0];
            byte b17 = booleanArray6[1];
            byte b18 = booleanArray6[2];
            byte b19 = booleanArray6[3];
            byte b20 = booleanArray6[4];
            byte b21 = booleanArray6[5];
            byte b22 = booleanArray6[6];
            byte b23 = booleanArray6[7];
            ErrorCodeNew_Msg.updateErrorCode(b16 == 1, ErrorCodeNew.E15.name());
            ErrorCodeNew_Msg.updateErrorCode(b18 == 1, ErrorCodeNew.E16.name());
            ErrorCodeNew_Msg.updateErrorCode(b19 == 1, ErrorCodeNew.A117.name());
            ErrorCodeNew_Msg.updateErrorCode(b21 == 1, ErrorCodeNew.A118.name());
            ErrorCodeNew_Msg.updateErrorCode(b22 == 0, ErrorCodeNew.E15.name());
            if (b16 == 1) {
                str = " " + ErrorCode_IceDEV.ErrorCode.E22.name();
            } else {
                str = "";
            }
            if (b18 == 1) {
                str = str + " 制冰机电机故障";
            }
            if (b19 == 1) {
                str = str + " " + ErrorCode_IceDEV.ErrorCode.A117.name();
            }
            if (b21 == 1) {
                str = str + " " + ErrorCode_IceDEV.ErrorCode.A118.name();
            }
            if (b22 == 0) {
                str = str + " " + ErrorCode_IceDEV.ErrorCode.E15.name();
            }
            this.state_Ice = str;
            if (str.isEmpty()) {
                EventBus.getDefault().post(new ErrorCode_IceDEV(true, ErrorCode_IceDEV.ErrorCode.E15, ""));
                return;
            }
            return;
        }
        if (b == Constants.CMD_instant.CMD_ReadRunErrorCode.getId()) {
            Constants.isGetMacState = true;
            if (bArr.length < 9) {
                Tools.upLocalLog("查询主控运行状态_收到错误消息：" + Tools.byteToStr(bArr));
                return;
            }
            byte[] booleanArray7 = ByteUtils.getBooleanArray(bArr[7]);
            if (booleanArray7[0] == 0 && booleanArray7[1] == 0) {
                ErrorCodeNew_Msg.MacState = ErrorCodeNew.Normal;
            } else if (booleanArray7[0] == 1 && booleanArray7[1] == 0) {
                ErrorCodeNew_Msg.MacState = ErrorCodeNew.Busy;
            } else if (booleanArray7[0] == 0 && booleanArray7[1] == 1) {
                ErrorCodeNew_Msg.MacState = ErrorCodeNew.Abnormal;
                this.mSerialPortManager_Instant.addSendList(SerialOrderBytes.MainControlPanel);
            } else if (booleanArray7[0] == 1 && booleanArray7[1] == 1) {
                ErrorCodeNew_Msg.MacState = ErrorCodeNew.Error;
                this.mSerialPortManager_Instant.addSendList(SerialOrderBytes.MainControlPanel);
            }
            if (ErrorCodeNew_Msg.MacState != ErrorCodeNew.Abnormal && ErrorCodeNew_Msg.MacState != ErrorCodeNew.Error) {
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E27.name());
            }
            ErrorCodeNew_Msg.updateErrorCode(booleanArray7[2] == 1, ErrorCodeNew.E14.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray7[3] == 1, ErrorCodeNew.E15.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray7[4] == 1, ErrorCodeNew.E10.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray7[6] == 1, ErrorCodeNew.E16.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray7[7] == 1, ErrorCodeNew.E17.name());
            if (booleanArray7[7] == 1) {
                this.mSerialPortManager_Instant.addSendList(SerialOrderBytes.CurErrorQuery);
            } else if (Constants.getStatePort2() != 1) {
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E05.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E06.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E07.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E08.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E09.name());
            }
            if (booleanArray7[5] == 1) {
                this.mSerialPortManager_Instant.addSendList(SerialOrderBytes.QianMenErrorQuery);
            } else {
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A161.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A167.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A02.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E22.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E23.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A160.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A04.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A123.name());
            }
            byte[] booleanArray8 = ByteUtils.getBooleanArray(bArr[6]);
            ErrorCodeNew_Msg.updateErrorCode(booleanArray8[0] == 1, ErrorCodeNew.E02.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray8[1] == 1, ErrorCodeNew.A01.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray8[2] == 1, ErrorCodeNew.E01.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray8[3] == 1, ErrorCodeNew.A100.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray8[4] == 1, ErrorCodeNew.E25.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray8[5] == 1, ErrorCodeNew.A09.name());
            byte[] booleanArray9 = ByteUtils.getBooleanArray(bArr[5]);
            ErrorCodeNew_Msg.updateErrorCode(booleanArray9[0] == 1, ErrorCodeNew.A10.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray9[1] == 1, ErrorCodeNew.A11.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray9[2] == 1, ErrorCodeNew.A12.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray9[3] == 1, ErrorCodeNew.A13.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray9[4] == 1, ErrorCodeNew.A14.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray9[5] == 1, ErrorCodeNew.A15.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray9[6] == 1, ErrorCodeNew.A16.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray9[7] == 1, ErrorCodeNew.A17.name());
            byte[] booleanArray10 = ByteUtils.getBooleanArray(bArr[4]);
            ErrorCodeNew_Msg.updateErrorCode(booleanArray10[0] == 1, ErrorCodeNew.E19.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray10[1] == 1, ErrorCodeNew.A05.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray10[2] == 1, ErrorCodeNew.A06.name());
            ErrorCodeNew_Msg.updateErrorCode(booleanArray10[3] == 1, ErrorCodeNew.E20.name());
            if (Constants.opsSeting().isV3Lbq()) {
                ErrorCodeNew_Msg.updateErrorCode(booleanArray10[4] == 1, ErrorCodeNew.E22.name());
                ErrorCodeNew_Msg.updateErrorCode(booleanArray10[5] == 1, ErrorCodeNew.E23.name());
            }
            ErrorCodeNew_Msg.updateErrorCode(booleanArray10[6] == 1, ErrorCodeNew.E12.name());
            return;
        }
        if (b != Constants.CMD_instant.CMD_ReadMaking.getId()) {
            if (b == Constants.CMD_instant.CMD_SendCup.getId()) {
                return;
            }
            if (b != Constants.CMD_instant.CMD_REPORTED_DATA.getId()) {
                Constants.CMD_instant.CMD_WATER.getId();
                return;
            }
            if (bArr.length < 22) {
                Tools.upLocalLog("主动上报数据解析_收到错误消息：" + Tools.byteToStr(bArr));
                return;
            }
            byte[] booleanArray11 = ByteUtils.getBooleanArray(bArr[19]);
            ErrorCodeNew_Msg.updateErrorCode(booleanArray11[1] == 1, ErrorCodeNew.E08.name());
            if (booleanArray11[1] == 1) {
                MyAppLocation.myAppLocation.myMqttService.updateStatus(ErrorUtils.codeDesc(ErrorCodeNew_Msg.ErrorNew));
                return;
            }
            return;
        }
        if (bArr.length < 9) {
            Tools.upLocalLog("制作中机器状态查询收到错误消息：" + Tools.byteToStr(bArr));
            return;
        }
        byte[] booleanArray12 = ByteUtils.getBooleanArray(bArr[4]);
        if (booleanArray12[6] == 1 && booleanArray12[7] == 1) {
            EventBus.getDefault().post(new ErrorCode_Making(false, ErrorCode_Making.ErrorCode.CHECKFALSE, ErrorCode_Making.ErrorCode.CHECKFALSE.name()));
            Tools.upLocalLog("检测到执行失败：" + Tools.byteToStr(bArr));
            byte[] booleanArray13 = ByteUtils.getBooleanArray(bArr[7]);
            int parseInt = Integer.parseInt("0000" + ((int) booleanArray13[3]) + ((int) booleanArray13[2]) + ((int) booleanArray13[1]) + ((int) booleanArray13[0]), 2);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            if (parseInt <= 10) {
                sb = new StringBuilder();
                sb.append("热饮：");
            } else {
                sb = new StringBuilder();
                sb.append("冷饮：");
                parseInt -= 10;
            }
            sb.append(parseInt);
            sb2.append(sb.toString());
            String str5 = sb2.toString() + ";";
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str5);
            sb3.append("制饮类型：");
            sb3.append(booleanArray13[5] == 1 ? "协议命令要求制饮;" : "本地金额消费制饮;");
            String sb4 = sb3.toString();
            StringBuilder sb5 = new StringBuilder();
            sb5.append(sb4);
            sb5.append("本次制饮出杯方式：");
            sb5.append(booleanArray13[6] == 1 ? "手动放杯;" : "机器自动落杯;");
            String sb6 = sb5.toString();
            StringBuilder sb7 = new StringBuilder();
            sb7.append(sb6);
            sb7.append("落杯：");
            sb7.append(booleanArray13[7] == 1 ? "成功;" : "失败;");
            String sb8 = sb7.toString();
            byte[] booleanArray14 = ByteUtils.getBooleanArray(bArr[6]);
            StringBuilder sb9 = new StringBuilder();
            sb9.append(sb8);
            sb9.append("同步协作状态：");
            sb9.append(booleanArray14[0] == 1 ? "可同步操作;" : "不可同步操作;");
            String sb10 = sb9.toString();
            StringBuilder sb11 = new StringBuilder();
            sb11.append(sb10);
            sb11.append("制饮过程类型：");
            sb11.append(booleanArray14[1] == 1 ? "制饮操作运行在异常流程;" : "制作操作运行在正常流程;");
            String str6 = (sb11.toString() + "当前正在执行的流程步骤数：" + Integer.parseInt("00" + ((int) booleanArray14[7]) + ((int) booleanArray14[6]) + ((int) booleanArray14[5]) + ((int) booleanArray14[4]) + ((int) booleanArray14[3]) + ((int) booleanArray14[2]), 2) + ";") + "当前制作总流程步骤数：" + Integer.parseInt(String.valueOf((int) bArr[5])) + ";";
            byte[] booleanArray15 = ByteUtils.getBooleanArray(bArr[4]);
            int parseInt2 = Integer.parseInt("00" + ((int) booleanArray15[5]) + ((int) booleanArray15[4]) + ((int) booleanArray15[3]) + ((int) booleanArray15[2]) + ((int) booleanArray15[1]) + ((int) booleanArray15[0]), 2);
            StringBuilder sb12 = new StringBuilder();
            sb12.append(str6);
            sb12.append("制作失败原因:");
            sb12.append(parseInt2 == 0 ? "未失败；" : parseInt2 == 1 ? "配方信息异常;" : parseInt2 == 2 ? "传杯到起始位失败;" : parseInt2 == 3 ? "传杯到指定工位失败;" : parseInt2 == 4 ? "传杯到终点位失败;" : parseInt2 == 5 ? "开门操作失败;" : parseInt2 == 6 ? "关门操作失败;" : parseInt2 == 7 ? "自动落杯失败;" : parseInt2 == 8 ? "手动放杯超时;" : parseInt2 == 9 ? "低水位导致失败;" : parseInt2 == 10 ? "并行现磨操作失败;" : parseInt2 == 11 ? "本次现磨操作失败;" : parseInt2 == 12 ? "等待取杯超时;" : parseInt2 == 13 ? "现磨异常失败;" : "制作失败原因——错误代码未找到相对应的错误；");
            String sb13 = sb12.toString();
            int parseInt3 = Integer.parseInt("000000" + ((int) booleanArray15[7]) + ((int) booleanArray15[6]), 2);
            StringBuilder sb14 = new StringBuilder();
            sb14.append(sb13);
            sb14.append("执行结果:");
            sb14.append(parseInt3 == 0 ? "未执行" : parseInt3 == 1 ? "正在执行" : parseInt3 == 2 ? "执行成功结束" : parseInt3 == 3 ? "执行失败结束" : "执行结果——错误代码未找到相对应的错误；");
            Tools.upLocalLog(sb14.toString());
        }
    }

    private void detailPayment(byte[] bArr) {
        char c;
        char c2;
        OpsSeting opsSeting = Constants.opsSeting();
        boolean isChange = opsSeting.isChange();
        boolean isCash = opsSeting.isCash();
        boolean isCardpay = opsSeting.isCardpay();
        byte b = bArr[3];
        if (b == Constants.CMD_pay.CMD_ReadPayDeviceStatus.getId()) {
            byte b2 = bArr[4];
            byte[] booleanArray = ByteUtils.getBooleanArray(b2);
            Constants.CHANGEDEVICESTATE = b2;
            byte b3 = booleanArray[0];
            byte b4 = booleanArray[1];
            byte b5 = booleanArray[2];
            byte b6 = booleanArray[3];
            byte b7 = booleanArray[4];
            byte b8 = booleanArray[5];
            byte b9 = booleanArray[6];
            byte b10 = booleanArray[7];
            if (isChange) {
                ErrorCodeNew_Msg.updateErrorCode(b3 == 1, ErrorCodeNew.A148.name());
                ErrorCodeNew_Msg.updateErrorCode(b4 == 1, ErrorCodeNew.A149.name());
                ErrorCodeNew_Msg.updateErrorCode(b5 == 1, ErrorCodeNew.A150.name());
                ErrorCodeNew_Msg.updateErrorCode(b7 == 1, ErrorCodeNew.A151.name());
                ErrorCodeNew_Msg.updateErrorCode(b8 == 1, ErrorCodeNew.A152.name());
                c = 0;
            } else {
                c = 0;
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A148.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A149.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A150.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A151.name());
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A152.name());
            }
            byte[] booleanArray2 = ByteUtils.getBooleanArray(bArr[5]);
            byte b11 = booleanArray2[c];
            byte b12 = booleanArray2[1];
            byte b13 = booleanArray2[2];
            byte b14 = booleanArray2[3];
            byte b15 = booleanArray2[4];
            byte b16 = booleanArray2[5];
            byte b17 = booleanArray2[6];
            byte b18 = booleanArray2[7];
            if (isCash) {
                ErrorCodeNew_Msg.updateErrorCode(b11 == 1, ErrorCodeNew.A153.name());
                c2 = 0;
            } else {
                c2 = 0;
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A153.name());
            }
            byte[] booleanArray3 = ByteUtils.getBooleanArray(bArr[6]);
            byte b19 = booleanArray3[c2];
            byte b20 = booleanArray3[1];
            byte b21 = booleanArray3[2];
            byte b22 = booleanArray3[3];
            byte b23 = booleanArray3[4];
            byte b24 = booleanArray3[5];
            byte b25 = booleanArray3[6];
            byte b26 = booleanArray3[7];
            if (isCardpay) {
                ErrorCodeNew_Msg.updateErrorCode(b19 == 1, ErrorCodeNew.A154.name());
                return;
            } else {
                ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.A154.name());
                return;
            }
        }
        if (b == Constants.CMD_pay.CMD_SetPaymentWay.getId()) {
            Tools.upLocalLog("Constants.ISMAKINGADRINK" + Constants.ISMAKINGADRINK + "Constants.PAYSTATE" + Constants.PAYSTATE);
            if (!Constants.ISMAKINGADRINK || Constants.PAYSTATE == 1) {
                return;
            }
            Tools.upLocalLog("配置命令：" + opsSeting.toString() + "bytes[4]" + ((int) bArr[4]));
            if (bArr[4] == 0) {
                Constants.PAYSTATE = 1;
                this.mSerialPortManager_Payment.addSendList(Constants.readAmount_payment());
                this.mSerialPortManager_Payment.addSendList(Constants.readAmount_payment());
            } else {
                Tools.sendErrorMsgShort("开启支付失败", 2, 1);
            }
            this.times = 0;
            return;
        }
        if (b == Constants.CMD_pay.CMD_SetPaymentWayNew.getId()) {
            if (!Constants.ISMAKINGADRINK || Constants.PAYSTATE == 1) {
                return;
            }
            Tools.upLocalLog("配置命令：" + opsSeting.toString());
            if (bArr[4] == 0) {
                Constants.PAYSTATE = 1;
                this.mSerialPortManager_Payment.addSendList(Constants.readAmount_paymentnew());
                this.mSerialPortManager_Payment.addSendList(Constants.readAmount_paymentnew());
            } else {
                Tools.sendErrorMsgShort("开启支付失败", 2, 1);
            }
            this.times = 0;
            return;
        }
        if (b == Constants.CMD_pay.CMD_ReadAmount.getId()) {
            double rate_y = opsSeting.getRate_y();
            double rate = opsSeting.getRate();
            double pos = opsSeting.getPos();
            double bytesToInt4 = ByteUtils.bytesToInt4(bArr, 4) * rate_y;
            double bytesToInt42 = ByteUtils.bytesToInt4(bArr, 6) * rate;
            double bytesToInt43 = ByteUtils.bytesToInt4(bArr, 8) * pos;
            Tools.upLocalLog("读取金额：纸币：" + bytesToInt42 + " 硬币：" + bytesToInt4 + " 刷卡：" + bytesToInt43);
            Constants.pay_num = bytesToInt4 + bytesToInt42 + bytesToInt43;
            MoneyReceiveMessage moneyReceiveMessage = new MoneyReceiveMessage(bytesToInt4, bytesToInt42, bytesToInt43, rate_y, rate, pos, Constants.pay_num);
            EventBus.getDefault().post(moneyReceiveMessage);
            Intent intent = new Intent("com.yj.coffeemachines.MONEY_RECEIVE");
            intent.putExtra("MoneyReceive", moneyReceiveMessage);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            if (Constants.ISMAKINGADRINK && Constants.PAYSTATE == 1) {
                Constants.isChange = false;
                Constants.pice_now = Constants.nowProduct_Detail.realPrice();
                Constants.change_num = ((Constants.pay_num * 10000.0d) - (Constants.pice_now * 10000.0d)) / 10000.0d;
                double change = opsSeting.getChange() * 10000.0d;
                Constants.change_num_amd_success = (int) ((Constants.change_num * 10000.0d) / change);
                Constants.change_num_amd_fail = (int) ((Constants.pay_num * 10000.0d) / change);
                Constants.pice_now_amd = (int) ((Constants.pice_now * 10000.0d) / change);
                Tools.upLocalLog("金额计算：应找零金额：" + Constants.change_num + " 制作成功应找零个数：" + Constants.change_num_amd_success + " 制作失败应找零个数：" + Constants.change_num_amd_fail);
                if (Constants.pay_num < Constants.pice_now) {
                    this.mSerialPortManager_Payment.addSendList(Constants.readAmount_payment());
                    return;
                } else {
                    if (Constants.NowStep == 3) {
                        Constants.PAYSTATE = 4;
                        EventBus.getDefault().post(new ErrorCode_Paymenting(true, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS.name()));
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (b == Constants.CMD_pay.CMD_ReadAmountNew.getId()) {
            double rate_y2 = opsSeting.getRate_y();
            double rate2 = opsSeting.getRate();
            double pos2 = opsSeting.getPos();
            byte[] bArr2 = {bArr[4], bArr[5], bArr[6], bArr[7]};
            double bytesToInt = Tools.bytesToInt(bArr2) * rate_y2;
            bArr2[0] = bArr[8];
            bArr2[1] = bArr[9];
            bArr2[2] = bArr[10];
            bArr2[3] = bArr[11];
            double bytesToInt2 = Tools.bytesToInt(bArr2) * rate2;
            bArr2[0] = bArr[12];
            bArr2[1] = bArr[13];
            bArr2[2] = bArr[14];
            bArr2[3] = bArr[15];
            double bytesToInt3 = Tools.bytesToInt(bArr2) * pos2;
            Tools.upLocalLog("读取金额新：纸币：" + bytesToInt2 + " 硬币：" + bytesToInt + " 刷卡：" + bytesToInt3);
            Constants.pay_num = bytesToInt + bytesToInt2 + bytesToInt3;
            MoneyReceiveMessage moneyReceiveMessage2 = new MoneyReceiveMessage(bytesToInt, bytesToInt2, bytesToInt3, rate_y2, rate2, pos2, Constants.pay_num);
            EventBus.getDefault().post(moneyReceiveMessage2);
            Intent intent2 = new Intent("com.yj.coffeemachines.MONEY_RECEIVE");
            intent2.putExtra("MoneyReceive", moneyReceiveMessage2);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
            Constants.pice_now = Constants.nowProduct_Detail.realPrice();
            Constants.change_num = Double.parseDouble(BigDecimal.valueOf(Constants.pay_num).subtract(BigDecimal.valueOf(Constants.pice_now)).toString());
            double change2 = opsSeting.getChange();
            Constants.change_num_amd_success = (int) Double.parseDouble(BigDecimal.valueOf(Constants.change_num).divide(BigDecimal.valueOf(change2), 2, RoundingMode.HALF_UP).toString());
            Constants.change_num_amd_fail = (int) Double.parseDouble(BigDecimal.valueOf(Constants.pay_num).divide(BigDecimal.valueOf(change2), 2, RoundingMode.HALF_UP).toString());
            Constants.pice_now_amd = (int) Double.parseDouble(BigDecimal.valueOf(Constants.pice_now).divide(BigDecimal.valueOf(change2), 2, RoundingMode.HALF_UP).toString());
            Tools.upLocalLog("金额计算：应找零金额：" + Constants.change_num + " 制作成功应找零个数：" + Constants.change_num_amd_success + " 制作失败应找零个数：" + Constants.change_num_amd_fail);
            if (Constants.ISMAKINGADRINK && Constants.PAYSTATE == 1) {
                Constants.isChange = false;
                if (Constants.pay_num < Constants.pice_now) {
                    this.mSerialPortManager_Payment.addSendList(Constants.readAmount_paymentnew());
                    return;
                }
                Constants.PAYSTATE = 3;
                Tools.upLocalLog("开始确认金额");
                this.mSerialPortManager_Payment.addSendList(Constants.Amount_payment_sure(bArr));
                return;
            }
            return;
        }
        if (b == Constants.CMD_pay.CMD_SmallChange.getId()) {
            if (Constants.ISMAKINGADRINK) {
                byte b27 = bArr[4];
                if (Constants.PAYSTATE == 2) {
                    return;
                }
                if (b27 == 0) {
                    Constants.pay_num = 0.0d;
                    Constants.PAYSTATE = 2;
                    Constants.isChange = true;
                    EventBus.getDefault().post(new ErrorCode_Paymenting(true, ErrorCode_Paymenting.ErrorCode.CHANGE_ERROR, ErrorCode_Paymenting.ErrorCode.CHANGE_ERROR.name()));
                    return;
                }
                int i = this.times;
                if (i == 3) {
                    Tools.sendErrorMsgShort("找零" + this.times + "次失败。", 2, 1);
                    return;
                }
                this.times = i + 1;
                this.mSerialPortManager_Payment.addSendList(Constants.smallChange_payment(Constants.mNumber));
                Tools.upLocalLog("2退款：" + ((int) Constants.mNumber));
                return;
            }
            return;
        }
        if (b == Constants.CMD_pay.CMD_AmountSure.getId()) {
            if (bArr[4] != 0) {
                Constants.PAYSTATE = 1;
                this.mSerialPortManager_Payment.addSendList(Constants.readAmount_paymentnew());
                Tools.upLocalLog("金额确认失败" + Tools.byteToStr(bArr));
                return;
            }
            Tools.upLocalLog("确认金额成功");
            if (Constants.payType == 1) {
                Tools.upLocalLog("开始暂停现金支付");
                this.mSerialPortManager_Payment.addSendList(Constants.stopAmount());
                return;
            } else {
                Constants.PAYSTATE = 4;
                EventBus.getDefault().post(new ErrorCode_Paymenting(true, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS.name()));
                return;
            }
        }
        if (b != Constants.CMD_pay.CMD_StopAmount.getId()) {
            if (b == Constants.CMD_pay.CMD_DrinkStatus.getId()) {
                Constants.pay_num = 0.0d;
                Tools.upLocalLog("饮料制作命令" + Tools.byteToStr(bArr));
                return;
            }
            return;
        }
        if (bArr[4] == 0) {
            Tools.upLocalLog("暂停现金支付成功");
            EventBus.getDefault().post(new ErrorCode_Paymenting(true, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS.name()));
        } else {
            Tools.upLocalLog("暂停现金支付失败" + Tools.byteToStr(bArr));
        }
    }

    private void detailPayment_v1(byte[] bArr) {
        byte b = bArr[3];
        if (b == Constants.CMD_pay.CMD_ReadPayDeviceStatus.getId()) {
            byte b2 = bArr[4];
            byte[] booleanArray = ByteUtils.getBooleanArray(b2);
            Constants.CHANGEDEVICESTATE = b2;
            byte b3 = booleanArray[0];
            byte b4 = booleanArray[1];
            byte b5 = booleanArray[2];
            return;
        }
        if (b != Constants.CMD_pay.CMD_AmountQ.getId()) {
            if (b == Constants.CMD_pay.CMD_DrinkStatus.getId()) {
                Constants.pay_num = 0.0d;
                Tools.upLocalLog("饮料制作命令" + Tools.byteToStr(bArr));
                return;
            }
            return;
        }
        if (bArr[4] == 0) {
            Tools.upLocalLog("确认金额成功");
            Constants.PAYSTATE = 4;
            EventBus.getDefault().post(new MyEventBusMessage("Step3Fragment", MyEventBusMessage.TYPE.CLICK, "", 2));
            return;
        }
        Constants.PAYSTATE = 1;
        this.mSerialPortManager_Payment.addSendList(Constants.readAmount_paymentnew());
        Tools.upLocalLog("金额确认失败" + Tools.byteToStr(bArr));
        ToastUtils.showLong(getString(R.string.Paymentfailed));
        EventBus.getDefault().post(new MyEventBusMessage("Step3Fragment", MyEventBusMessage.TYPE.CLICK, "", 1));
    }

    private String getSerialData(int i) {
        return i == 0 ? getString(R.string.send_instant) : i == 1 ? getString(R.string.send_pay) : i == 2 ? getString(R.string.send_ground) : "";
    }

    private String getSerialReceiveData(int i) {
        return i == 0 ? getString(R.string.receive_instant) : i == 1 ? getString(R.string.receive_pay) : i == 2 ? getString(R.string.receive_ground) : "";
    }

    private void linkStateChange(int i, boolean z) {
        if (MyAppLocation.myAppLocation == null || MyAppLocation.myAppLocation.myMqttService == null) {
            return;
        }
        ErrorCodeNew_Msg.updateErrorCode(!z, i == 1 ? ErrorCodeNew.E11.name() : i == 2 ? ErrorCodeNew.E10.name() : "");
        if (Constants.HomePay() != 2) {
            ErrorCodeNew_Msg.updateErrorCode(Constants.getStatePort3() != 0, ErrorCodeNew.E13.name());
        }
    }

    private void sendBytes(byte[]... bArr) {
        for (byte[] bArr2 : bArr) {
            this.mSerialPortManager_Payment.addSendList(bArr2);
        }
    }

    public void addSendByteToList_Currentgrinding(byte[] bArr) {
        SerialPortManager serialPortManager = this.mSerialPortManager_Currentgrinding;
        if (serialPortManager != null) {
            serialPortManager.addSendList(bArr);
        }
    }

    public void addSendByteToList_Currentgrinding_main(byte[] bArr) {
        SerialPortManager serialPortManager = this.mSerialPortManager_Currentgrinding;
        if (serialPortManager != null) {
            serialPortManager.addSendMainList(bArr);
        }
    }

    public void addSendByteToList_Instant(byte[] bArr) {
        SerialPortManager serialPortManager = this.mSerialPortManager_Instant;
        if (serialPortManager != null) {
            serialPortManager.addSendList(bArr);
        }
    }

    public void addSendByteToList_Instant_main(byte[] bArr) {
        SerialPortManager serialPortManager = this.mSerialPortManager_Instant;
        if (serialPortManager != null) {
            serialPortManager.addSendMainList(bArr);
        }
    }

    public void addSendByteToList_Payment(byte[] bArr) {
        SerialPortManager serialPortManager = this.mSerialPortManager_Payment;
        if (serialPortManager != null) {
            serialPortManager.addSendList(bArr);
        }
    }

    public void addSendByteToList_Payment_main(byte[] bArr) {
        SerialPortManager serialPortManager = this.mSerialPortManager_Payment;
        if (serialPortManager != null) {
            serialPortManager.addSendMainList(bArr);
        }
    }

    public void cleanGround() {
        if (this.mSerialPortManager_Currentgrinding == null) {
            Tools.upLocalLog("清洗现磨，检测现磨串口没有连接，清洗现磨退出");
            return;
        }
        if (Constants.ISCLEAN_CURRENT) {
            ArmsUtils.snackbarText("正在清洗中");
            return;
        }
        Constants.ISCLEAN_CURRENT = true;
        this.mSerialPortManager_Currentgrinding.addSendList(Constants.getOrderBytes_Currentgrinding(MqttWireMessage.MESSAGE_TYPE_UNSUBACK, (byte) 90, (byte) 5, (byte) 50, (byte) 8, (byte) 0, (byte) -86));
        this.mHandler.post(new Runnable() { // from class: com.yj.coffeemachines.app.service.SerialDataService.4
            @Override // java.lang.Runnable
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyAppLocation.myAppLocation);
                builder.setTitle(R.string.paplecleaning);
                builder.setCancelable(false);
                builder.setMessage(R.string.wateing);
                SerialDataService.this.dialog_current = builder.create();
                if (Build.VERSION.SDK_INT >= 25) {
                    SerialDataService.this.dialog_current.getWindow().setType(2038);
                } else {
                    SerialDataService.this.dialog_current.getWindow().setType(2003);
                }
                SerialDataService.this.dialog_current.show();
            }
        });
        this.mScheduledThreadPoolExecutor.schedule(new Runnable() { // from class: com.yj.coffeemachines.app.service.SerialDataService.5
            @Override // java.lang.Runnable
            public void run() {
                if (SerialDataService.this.dialog_current != null && SerialDataService.this.dialog_current.isShowing()) {
                    SerialDataService.this.dialog_current.dismiss();
                    Tools.upLocalLogTest("时间超过60秒，执行现磨关闭指令");
                }
            }
        }, 60L, TimeUnit.SECONDS);
    }

    public void cleanGround(int i) {
        if (this.mSerialPortManager_Currentgrinding == null) {
            Tools.upLocalLog("清洗现磨，检测现磨串口没有连接，清洗现磨退出");
        } else {
            if (Constants.ISCLEAN_CURRENT) {
                ArmsUtils.snackbarText(getString(R.string.paplecleaning));
                return;
            }
            Constants.ISCLEAN_CURRENT = true;
            this.mSerialPortManager_Currentgrinding.addSendList(Constants.getOrderBytes_Currentgrinding(MqttWireMessage.MESSAGE_TYPE_UNSUBACK, (byte) 90, (byte) 5, (byte) 50, (byte) 8, (byte) 0, (byte) -86));
            this.mHandler.post(new Runnable() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$SerialDataService$Sd4X_JOsfXq2Pq1X-z5S8O-HD_E
                @Override // java.lang.Runnable
                public final void run() {
                    SerialDataService.this.lambda$cleanGround$1$SerialDataService();
                }
            });
        }
    }

    public void cleanGround(String str) {
        if (this.mSerialPortManager_Currentgrinding == null) {
            Tools.upLocalLog("清洗现磨，检测现磨串口没有连接，清洗现磨退出");
        } else {
            if (Constants.ISCLEAN_CURRENT) {
                return;
            }
            Constants.ISCLEAN_CURRENT = true;
            this.mSerialPortManager_Currentgrinding.addSendList(Constants.getOrderBytes_Currentgrinding(MqttWireMessage.MESSAGE_TYPE_UNSUBACK, (byte) 90, (byte) 5, (byte) 50, (byte) 8, (byte) 0, (byte) -86));
        }
    }

    public void cleanInstantSendList() {
        SerialPortManager serialPortManager = this.mSerialPortManager_Instant;
        if (serialPortManager != null) {
            serialPortManager.cleanSendList();
        }
    }

    public void cleanPipe(final int i) {
        DevConfig devConfig;
        AlertDialog alertDialog = this.dialog_instan;
        if ((alertDialog == null || !alertDialog.isShowing()) && (devConfig = Constants.devConfig()) != null) {
            if (devConfig.isRight2()) {
                new Thread(new Runnable() { // from class: com.yj.coffeemachines.app.service.SerialDataService.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i2 = 0; i2 < i; i2++) {
                            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_cleanAll, 1));
                            try {
                                Thread.sleep(60000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                showCleandialog(i * 70);
            } else {
                new Thread(new Runnable() { // from class: com.yj.coffeemachines.app.service.SerialDataService.2
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i2 = 0; i2 < i; i2++) {
                            for (int i3 = 1; i3 < 7; i3++) {
                                MyAppLocation.myAppLocation.mSerialDataService.mSerialPortManager_Instant.addSendList(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_DrinkXPipeClean, i3));
                                try {
                                    Thread.sleep(12000L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
                showCleandialog(i * 90);
            }
        }
    }

    public void coinInit() {
        sendBytes(TransmissionUtils.coinInit, TransmissionUtils.coinReadStatus, TransmissionUtils.coinReadId, TransmissionUtils.coinEnable, TransmissionUtils.billInit, TransmissionUtils.billNO1, TransmissionUtils.billChange);
    }

    public void emptying() {
        SerialPortManager serialPortManager = this.mSerialPortManager_Currentgrinding;
        if (serialPortManager == null) {
            return;
        }
        serialPortManager.addSendList(Constants.getOrderBytes_Currentgrinding((byte) -95, (byte) 90, (byte) 5, (byte) 50, (byte) 8, (byte) 0, (byte) -86));
    }

    public SerialPortManager getmSerialPortManager_Currentgrinding() {
        return this.mSerialPortManager_Currentgrinding;
    }

    public SerialPortManager getmSerialPortManager_Instant() {
        return this.mSerialPortManager_Instant;
    }

    public SerialPortManager getmmSerialPortManager_Payment() {
        return this.mSerialPortManager_Payment;
    }

    @Override // com.jess.arms.base.BaseService
    public void init() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mScheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) ArmsUtils.obtainAppComponentFromContext(MyAppLocation.myAppLocation).executorService();
        if (Constants.getStatePort1() == 1) {
            initInstantSerialcontrol();
        }
        if (Constants.getStatePort2() == 1) {
            initCurrentgrindingSerialcontrol();
        }
        if (Constants.getStatePort3() == 1) {
            initPaymentSerialcontrol();
        }
    }

    public void initCurrentgrindingSerialcontrol() {
        SerialPortManager serialPortManager = this.mSerialPortManager_Currentgrinding;
        if (serialPortManager != null) {
            serialPortManager.closeSerialPort();
        } else {
            this.mSerialPortManager_Currentgrinding = new SerialPortManager().setOnOpenSerialPortListener(this);
            this.mSerialPortManager_Currentgrinding.setOnSerialPortDataListener(this);
        }
        try {
            this.mSerialPortManager_Currentgrinding.init(new ConfigurationSdk.ConfigurationBuilder(new File(Constants.SERIAPort_currentgrinding()), Constants.SERIALBaudRate_currentgrinding).log("TAG", false, false).build());
            ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E10.name());
        } catch (SerialPortOpenFailureException e) {
            Tools.upLocalLog(e.getMessage());
            ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E10.name());
        }
    }

    public void initInstantSerialcontrol() {
        SerialPortManager serialPortManager = this.mSerialPortManager_Instant;
        if (serialPortManager != null) {
            serialPortManager.closeSerialPort();
        } else {
            this.mSerialPortManager_Instant = new SerialPortManager().setOnOpenSerialPortListener(this);
            this.mSerialPortManager_Instant.setOnSerialPortDataListener(this);
        }
        try {
            this.mSerialPortManager_Instant.init(new ConfigurationSdk.ConfigurationBuilder(new File(Constants.SERIAPort_instant()), Constants.SERIALBaudRate_instant).log("TAG", false, false).msgHead(Constants.RESPONSEFLAG_instant).build());
        } catch (SerialPortOpenFailureException e) {
            Tools.upLocalLog(e.getMessage());
            ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E10.name());
        }
    }

    public void initPaymentSerialcontrol() {
        SerialPortManager serialPortManager = this.mSerialPortManager_Payment;
        if (serialPortManager != null) {
            serialPortManager.closeSerialPort();
        } else {
            if (Constants.HomePay() != 2) {
                this.mSerialPortManager_Payment = new SerialPortManager(1000L).setOnOpenSerialPortListener(this);
            } else {
                this.mSerialPortManager_Payment = new SerialPortManager().setOnOpenSerialPortListener(this);
            }
            this.mSerialPortManager_Payment.setOnSerialPortDataListener(this);
        }
        try {
            this.mSerialPortManager_Payment.init(new ConfigurationSdk.ConfigurationBuilder(new File(Constants.SERIAPort_payment()), Constants.SERIALBaudRate_payment).log("TAG", false, false).msgHead(Constants.RESPONSEFLAG_payment).build());
            Tools.upLocalLogTest("E13支付串口4");
            ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E13.name());
        } catch (SerialPortOpenFailureException e) {
            Tools.upLocalLog(e.getMessage());
            Tools.upLocalLogTest("E13支付串口5");
            ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E13.name());
        }
    }

    public /* synthetic */ void lambda$cleanGround$1$SerialDataService() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyAppLocation.myAppLocation);
        builder.setTitle(R.string.paplecleaning);
        builder.setCancelable(false);
        builder.setMessage(R.string.wateing);
        this.dialog_current = builder.create();
        if (Build.VERSION.SDK_INT >= 25) {
            this.dialog_current.getWindow().setType(2038);
        } else {
            this.dialog_current.getWindow().setType(2003);
        }
        this.dialog_current.show();
    }

    public /* synthetic */ void lambda$showCleandialog$0$SerialDataService() {
        AlertDialog alertDialog = this.dialog_instan;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.dialog_instan.dismiss();
        }
    }

    @Override // com.jess.arms.base.BaseService, android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.bind;
    }

    @Override // com.yj.coffeemachines.app.serialport.listener.OnSerialPortDataListener
    public void onDataReceived(File file, byte[] bArr, boolean z, boolean z2) {
        String absolutePath = file.getAbsolutePath();
        clearupData(bArr, file.getAbsolutePath(), 0);
        if (this.mConversionNotice) {
            LogManager.instance().post(new RecvMessage(file.getAbsolutePath(), ByteUtils.byte2HexStr2(bArr)));
        } else {
            LogManager.instance().post(new RecvMessage(file.getAbsolutePath(), Arrays.toString(bArr)));
        }
        if (Constants.MQTTPUBLISH) {
            if (absolutePath.equals(Constants.SERIAPort_currentgrinding())) {
                MqttManager.getInstance().publishMessage(Constants.deviceSN() + "serialCurrentSend", getSerialReceiveData(2) + absolutePath + " " + DataUtils.getNowtimeyyymmddhhmmss() + " " + ByteUtils.byte2HexStr2(bArr), false);
            } else if (absolutePath.equals(Constants.SERIAPort_instant())) {
                MqttManager.getInstance().publishMessage(Constants.deviceSN() + "serialInstantSend", getSerialReceiveData(0) + absolutePath + " " + DataUtils.getNowtimeyyymmddhhmmss() + " " + ByteUtils.byte2HexStr2(bArr), false);
            } else if (absolutePath.equals(Constants.SERIAPort_payment())) {
                MqttManager.getInstance().publishMessage(Constants.deviceSN() + "serialPaySend", getSerialReceiveData(1) + absolutePath + " " + DataUtils.getNowtimeyyymmddhhmmss() + " " + ByteUtils.byte2HexStr2(bArr), false);
            }
        }
        if (absolutePath.equals(Constants.SERIAPort_currentgrinding())) {
            if (!this.communicationStatus_Currentgrinding) {
                linkStateChange(2, true);
            }
            this.state_current = true;
            this.communicationStatus_Currentgrinding = true;
            this.lastTime_Currentgrinding = System.currentTimeMillis();
            if (z && z2) {
                detailCurrentgrinding(bArr);
                return;
            }
            return;
        }
        if (absolutePath.equals(Constants.SERIAPort_instant())) {
            if (!this.communicationStatus_Instant) {
                linkStateChange(1, true);
            }
            this.state_instant = true;
            this.communicationStatus_Instant = true;
            this.lastTime_Instant = System.currentTimeMillis();
            if (z) {
                detailInstant(bArr);
                return;
            }
            return;
        }
        if (absolutePath.equals(Constants.SERIAPort_payment())) {
            if (!this.communicationStatus_Payment) {
                linkStateChange(3, true);
            }
            this.state_payment = true;
            this.communicationStatus_Payment = true;
            this.lastTime_Payment = System.currentTimeMillis();
            if (z) {
                if (Constants.HomePay() == 0 || Constants.HomePay() == 3) {
                    detailPayment(bArr);
                } else if (Constants.HomePay() == 1) {
                    detailPayment_v1(bArr);
                } else {
                    MdbReceivePayment.instance().payMdb(bArr);
                }
            }
        }
    }

    @Override // com.yj.coffeemachines.app.serialport.listener.OnSerialPortDataListener
    public void onDataSent(File file, byte[] bArr) {
        String absolutePath = file.getAbsolutePath();
        clearupData(bArr, file.getAbsolutePath(), 1);
        if (this.mConversionNotice) {
            LogManager.instance().post(new SendMessage(absolutePath, ByteUtils.byte2HexStr2(bArr)));
        } else {
            LogManager.instance().post(new SendMessage(absolutePath, Arrays.toString(bArr)));
        }
        if (Constants.MQTTPUBLISH) {
            if (absolutePath.equals(Constants.SERIAPort_currentgrinding())) {
                MqttManager.getInstance().publishMessage(Constants.deviceSN() + "serialCurrentSend", getSerialData(2) + absolutePath + " " + DataUtils.getNowtimeyyymmddhhmmss() + " " + ByteUtils.byte2HexStr2(bArr), false);
                return;
            }
            if (absolutePath.equals(Constants.SERIAPort_instant())) {
                MqttManager.getInstance().publishMessage(Constants.deviceSN() + "serialInstantSend", getSerialData(0) + absolutePath + " " + DataUtils.getNowtimeyyymmddhhmmss() + " " + ByteUtils.byte2HexStr2(bArr), false);
                return;
            }
            if (absolutePath.equals(Constants.SERIAPort_payment())) {
                MqttManager.getInstance().publishMessage(Constants.deviceSN() + "serialPaySend", getSerialData(1) + absolutePath + " " + DataUtils.getNowtimeyyymmddhhmmss() + " " + ByteUtils.byte2HexStr2(bArr), false);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(TimeMessage timeMessage) {
        try {
            if (MyAppLocation.myAppLocation != null && MyAppLocation.myAppLocation.myMqttService != null) {
                if (this.dialog_current != null && this.dialog_current.isShowing() && !Constants.ISCLEAN_CURRENT) {
                    this.dialog_current.dismiss();
                }
                if (this.runflag_getCupTimedown && System.currentTimeMillis() - this.getCupTimedown_time > WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS) {
                    EventBus.getDefault().post(new ErrorCode_Making(false, ErrorCode_Making.ErrorCode.GETCUP, ErrorCode_Making.ErrorCode.GETCUP.name()));
                    this.getCupTimedown_time = LongCompanionObject.MAX_VALUE;
                    this.runflag_getCupTimedown = false;
                }
                this.times_countdown = this.times_countdown >= 3600 ? 0 : this.times_countdown;
                if (!Constants.ISMAKINGADRINK) {
                    if (Constants.ISOPEN_INSTANT && this.check_instant && System.currentTimeMillis() - this.time_instant > this.time_interval * 1000 && !this.state_instant) {
                        this.check_instant = false;
                    }
                    if (Constants.ISOPEN_CURRENTGRINDING && this.check_current && System.currentTimeMillis() - this.time_current > this.time_interval * 1000) {
                        if (!this.state_current) {
                            ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E10.name());
                        }
                        this.check_current = false;
                    }
                    if (Constants.ISOPEN_PAYMENT && this.check_payment && System.currentTimeMillis() - this.time_payment > this.time_interval * 1000) {
                        if (!this.state_payment) {
                            Tools.upLocalLogTest("E13支付串口2");
                            ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E13.name());
                        }
                        this.check_payment = false;
                    }
                }
                this.times_countdown++;
                if (this.times_countdown % this.time_amd != 0) {
                    return;
                }
                if (Constants.ISMAKINGADRINK) {
                    if (Constants.AMD_instant() == 2 && Constants.ISOPEN_INSTANT) {
                        this.mSerialPortManager_Instant.addSendList(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_ReadDrinkSoldCups, 6));
                    }
                    if (Constants.MacMakingState == 1 && Constants.ISOPEN_INSTANT) {
                        if (Constants.deviceTypeDetail.getName().contains("JK88") || Constants.deviceTypeDetail.getName().contains("801")) {
                            this.mSerialPortManager_Instant.addSendList(SerialOrderBytes.MakingMacStateQuery);
                            return;
                        }
                        return;
                    }
                    return;
                }
                checkLink();
                if (Constants.ISOPEN_INSTANT) {
                    if (Constants.AMD_instant() == 3 && Constants.devConfig().isRight2_ice()) {
                        this.mSerialPortManager_Instant.addSendList(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_CheckIce, new int[0]));
                    }
                    if (Constants.deviceTypeDetail != null) {
                        if (!Constants.deviceTypeDetail.getName().contains("JK88") && !Constants.deviceTypeDetail.getName().contains("801")) {
                            this.mSerialPortManager_Instant.addSendList(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_ReadRunErrorCode, new int[0]));
                            this.mSerialPortManager_Instant.addSendList(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_ReadMachineErrorCode, new int[0]));
                        }
                        this.mSerialPortManager_Instant.addSendList(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_ReadRunErrorCode, new int[0]));
                    }
                }
                boolean z = Constants.ISOPEN_CURRENTGRINDING;
                if (!Constants.ISOPEN_PAYMENT || Constants.HomePay() == 2) {
                    return;
                }
                this.mSerialPortManager_Payment.addSendList(Constants.readPayDeviceStatus_payment());
            }
        } catch (Exception e) {
            Tools.upLocalLog("TimeMessage美丽的线程女士出错：" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override // com.yj.coffeemachines.app.serialport.listener.OnOpenSerialPortListener
    public void onFail(File file, OnOpenSerialPortListener.Status status) {
        Tools.upLocalLog(file.getAbsolutePath() + "打开失败");
        if (file.getAbsolutePath().equals(Constants.SERIAPort_instant())) {
            Constants.ISOPEN_INSTANT = false;
        }
        if (file.getAbsolutePath().equals(Constants.SERIAPort_payment())) {
            Constants.ISOPEN_PAYMENT = false;
            Tools.upLocalLog("E13支付串口打开回调失败");
            ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E13.name());
        }
        if (file.getAbsolutePath().equals(Constants.SERIAPort_currentgrinding())) {
            Constants.ISOPEN_CURRENTGRINDING = false;
            Tools.upLocalLog("E10现磨串口打开回调失败");
            ErrorCodeNew_Msg.updateErrorCode(true, ErrorCodeNew.E10.name());
        }
    }

    @Override // com.yj.coffeemachines.app.serialport.listener.OnOpenSerialPortListener
    public void onSuccess(File file) {
        Tools.upLocalLog(file.getAbsolutePath() + "打开成功");
        if (file.getAbsolutePath().equals(Constants.SERIAPort_instant())) {
            Constants.ISOPEN_INSTANT = true;
            this.times_countdown = 0;
            if (Constants.deviceTypeDetail == null || !(Constants.deviceTypeDetail.getName().contains("JK88") || Constants.deviceTypeDetail.getName().contains("801"))) {
                this.mSerialPortManager_Instant.addSendList(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_ReadRunErrorCode, new int[0]));
                this.mSerialPortManager_Instant.addSendList(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_ReadMachineErrorCode, new int[0]));
            } else {
                this.mSerialPortManager_Instant.addSendList(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_ReadRunErrorCode, new int[0]));
            }
            this.time_instant = System.currentTimeMillis();
            this.check_instant = true;
            this.state_instant = false;
        }
        if (file.getAbsolutePath().equals(Constants.SERIAPort_payment())) {
            Tools.upLocalLogTest("E13支付串口6");
            ErrorCodeNew_Msg.updateErrorCode(false, ErrorCodeNew.E13.name());
            Constants.ISOPEN_PAYMENT = true;
            if (Constants.HomePay() != 2) {
                this.mSerialPortManager_Payment.addSendList(Constants.readPayDeviceStatus_payment());
            } else {
                if (Constants.LevelPay() == 2) {
                    pulseInit(2);
                } else {
                    pulseInit(3);
                }
                coinInit();
            }
            this.time_payment = System.currentTimeMillis();
            this.check_payment = true;
            this.state_payment = false;
        }
        if (file.getAbsolutePath().equals(Constants.SERIAPort_currentgrinding())) {
            Constants.ISOPEN_CURRENTGRINDING = true;
            this.time_current = System.currentTimeMillis();
            this.check_current = true;
            this.state_current = false;
        }
    }

    public void pulseInit(int i) {
        sendBytes(TransmissionUtils.Resat, i == 2 ? TransmissionUtils.pulseLevel2 : TransmissionUtils.pulseLevel3, TransmissionUtils.max, TransmissionUtils.readId, TransmissionUtils.enable, TransmissionUtils.pulseStart);
    }

    public void showCleandialog(int i) {
        this.mHandler.post(new AnonymousClass3(this.mScheduledThreadPoolExecutor.schedule(new Runnable() { // from class: com.yj.coffeemachines.app.service.-$$Lambda$SerialDataService$9MKx9-PLmHJqrzM1IilqmyhtD30
            @Override // java.lang.Runnable
            public final void run() {
                SerialDataService.this.lambda$showCleandialog$0$SerialDataService();
            }
        }, i, TimeUnit.SECONDS)));
    }

    public void standby() {
        SerialPortManager serialPortManager = this.mSerialPortManager_Currentgrinding;
        if (serialPortManager == null) {
            return;
        }
        serialPortManager.addSendList(this.standyOrder);
    }
}
