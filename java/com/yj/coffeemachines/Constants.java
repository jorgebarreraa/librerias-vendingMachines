package com.yj.coffeemachines;

import android.app.Activity;
import android.app.Application;
import android.app.kingsun.KingsunSmartAPI;
import android.app.reecam.ReecamManager;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
// // // // import android.os.tniuds.TniudsUtils; // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.Gson;
// // // // import com.innohi.YNHAPI; // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible
import com.yj.coffeemachines.app.utils.AppUtils;
import com.yj.coffeemachines.app.utils.ByteUtils;
import com.yj.coffeemachines.app.utils.CRC8Util;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.app.utils.KvUtil;
import com.yj.coffeemachines.app.utils.SPUtils;
import com.yj.coffeemachines.bean.DefaultSetting;
import com.yj.coffeemachines.bean.ReturnAmt;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.model.beans.BaseMaterial;
import com.yj.coffeemachines.mvp.model.beans.DeviceInfoBean;
import com.yj.coffeemachines.mvp.model.beans.PayWaySettingBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes.dex */
public class Constants {
    public static int NowStep;
    public static double change_num;
    public static int change_num_amd_fail;
    public static int change_num_amd_success;
    public static double coin_Num;
    public static double countNum;
    public static DeviceInfoBean.InfoBean deviceDetail;
    public static DeviceInfoBean.TypeInfoBean deviceTypeDetail;
    public static String exchangeCode;
    public static String goods_no;
    public static boolean isChange;
    public static byte mNumber;
    public static MaterialMessage needCurrent_MaterialMessage;
    public static ProductBean.ProductDetailBean.FormulaListBean needCurrent_data;
    public static boolean needIce;
    public static Boolean netWorkState;
    public static double noteChange;
    public static double note_Num;
    public static ProductBean nowProduct;
    public static ProductBean.ProductDetailBean nowProduct_Detail;
    public static String pay_no;
    public static double pay_num;
    public static double pice_now;
    public static double pice_now_amd;
    public static int testnum1;
    public static String trade_no;
    public static final int Model = determineModel();
    public static boolean isConnected_mqtt = false;
    public static boolean isConnecting_mqtt = false;
    public static int iStartMQTT_mqtt = 10;
    public static String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String GSWYPath = absolutePath + "/A_GSWYData/";
    public static String voicePath = GSWYPath + "Voice";
    public static String videoPath = GSWYPath + "Video";
    public static String logPath = GSWYPath + "Log";
    public static String adPath = GSWYPath + "Ad";
    public static String tempPath = GSWYPath + "Temp";
    public static String apkPath = GSWYPath + "Apk";
    public static String uiPath = GSWYPath + "UI";
    public static String errorLogPath = GSWYPath + "ErrorLog";
    public static String defaultSettingPath = GSWYPath + "DefaultSetting";
    public static String resPath = GSWYPath + "Res";
    public static String tools = absolutePath + "/operation-tools/Vending";
    public static String tools_device = absolutePath + "/operation-tools/Vending/device.json";
    public static boolean e00001 = false;
    public static boolean e00002 = false;
    public static int goodsNull = 2;
    public static int dataLoadTimeOne = 180;
    public static PayWaySettingBack payWaySettingBack = null;
    public static boolean isPayWaySettingBack = false;
    public static String memberQr = null;
    public static String exchangeQr = null;
    public static String memberTips = null;
    public static String exchangeTips = null;
    public static String merchantLogo = null;
    public static boolean isVoice = true;
    public static int ProductRefish = 0;
    public static int netErrorNum = 20;
    public static int netError = 0;
    public static long coffeeTime = System.currentTimeMillis();
    public static int coffeeInterval = 180000;
    public static int JK88ClearCurrent = 0;
    public static int clearCurrentTime = 0;
    public static String ReturnAmtRemove = "";
    public static String ReturnAmtAdd = "";
    public static List<ReturnAmt> returnAmtList = new ArrayList();
    public static boolean isRestart = false;
    public static int payType = 0;
    public static boolean isGetMacState = false;
    public static int MacMakingState = 9;
    public static int loadCount = 3;
    public static long noPayTime = System.currentTimeMillis();
    public static int npPayInterval = 1200000;
    public static long Sendtime_Instant = System.currentTimeMillis();
    public static long Sendtime_Current = System.currentTimeMillis();
    public static long Sendtime_Payment = System.currentTimeMillis();
    public static long Receivertime_Instant = System.currentTimeMillis();
    public static long Receivertime_Current = System.currentTimeMillis();
    public static long Receivertime_Payment = System.currentTimeMillis();
    public static int testNum = 1;
    public static long time = System.currentTimeMillis();
    public static boolean isUpNetworkLog = true;
    public static boolean isLocalFactoryInterface = false;
    public static int logSignificance = 1;
    public static int loadGood = 9;
    public static String InstantErrorMsg = "";
    public static String PaymentErrorMsg = "";
    public static String NetworkErrorMsg = "";
    public static String IceErrorMsg = "";
    public static String CurrentErrorMsg = "";
    public static String OldErrorMsg = "";
    public static String ADActivityString = "";
    public static boolean isSettingInterface = false;
    public static boolean isRefreshStep1 = false;
    public static boolean isAgainDialog = false;
    public static boolean isunlock = true;
    public static String networkErrorPassword = "gaosheng@123";
    public static String factroyPsw = "861752";
    public static boolean MQTTPUBLISH = false;
    public static boolean ISOPEN_INSTANT = false;
    public static boolean ISOPEN_PAYMENT = false;
    public static boolean ISOPEN_CURRENTGRINDING = false;
    public static boolean ISHAVECUPLID = false;
    public static byte MACHINESTATE = 17;
    public static byte CHANGEDEVICESTATE = 0;
    public static boolean CURRENCYSTATE = false;
    public static boolean ISMAKINGADRINK = false;
    public static boolean ISCLEAN_CURRENT = false;
    public static int ISCLEAN_WATER = 0;
    public static int PAYSTATE = 0;
    public static int CURRENTGRINDING_TEMP = 92;
    public static Integer noteRate = 0;
    public static int swipeNum = 0;
    public static String country = "0";
    public static double onChange = 0.0d;
    public static int buy_type = -1;
    public static boolean isExchange = false;
    public static boolean needCurrentgrinding = false;
    public static int needCurrentmicDate = 0;
    public static boolean needCurrentgrinding_other = false;
    public static int needCurrentgrinding_times = 0;
    public static List<ProductBean> step1GoodsBack = new ArrayList();
    public static List<ProductBean> lststep1GoodsBack = new ArrayList();
    private static List<BaseMaterial> materialMessageList = new ArrayList();
    public static String hardware = "0";
    public static String firmware = "0";
    public static int SERIALBaudRate_instant = 9600;
    public static byte[] FLAG_instant = {-86, 85};
    public static byte[] RESPONSEFLAG_instant = {-91, 90};
    public static int SERIALBaudRate_currentgrinding = 9600;
    public static byte[] FLAG_currentgrinding = {85, -86, -127, 24};
    public static byte[] RESPONSEFLAG_currentgrinding = {85, -86, -127, 24};
    public static int SERIALBaudRate_payment = 9600;
    public static byte[] FLAG_payment = {-86, 85};
    public static byte[] RESPONSEFLAG_payment = {-91, 90};
    public static String sugar1 = "糖";
    public static String sugar2 = "sugar";
    public static String sugar3 = "Sugar";
    public static String sugar4 = "SUGAR";
    public static String ice1 = "冰";
    public static String ice2 = "ice";
    public static String ice3 = "Ice";
    public static String ice4 = "ICE";
    public static String ground1 = "现磨";
    public static String ground2 = "ground";
    public static String ground3 = "Ground";
    public static String ground4 = "GROUND";
    public static String ground5 = "现磨";
    public static String ground6 = "bean";
    public static String ground7 = "Bean";
    public static String ground8 = "BEAN";
    public static String instant1 = "速溶";
    public static String instant2 = "instant";
    public static String instant3 = "Instant";
    public static String instant4 = "INSTANT";
    public static String coffee1 = "咖啡";
    public static String coffee2 = "coffee";
    public static String water1 = "水";
    public static String water2 = "water";
    public static String water3 = "Water";
    public static String water4 = "WATER";
    public static String powder1 = "粉";
    public static String powder2 = "powder";
    public static String powder3 = "Powder";
    public static String powder4 = "POWDER";

    /* loaded from: classes.dex */
    public enum CMD_instant {
        CMD_SellOneCupDrink(1),
        CMD_SendCup(2),
        CMD_SetHotDrinkTempThreshold(4),
        CMD_SetColdDrinkTempThreshold(5),
        CMD_ReadDrinkSoldCups(6),
        CMD_SetCupFallingWay(7),
        CMD_CheckMachineCupFalling(8),
        CMD_CheckMachineAllFunction(9),
        CMD_cleanAll(10),
        CMD_ReadMachineStatus(11),
        CMD_ReadMachineErrorCode(12),
        CMD_SetDrinkX_price(14),
        CMD_ReadBalance(15),
        CMD_ChangeOperation(16),
        CMD_DrinkXPipeClean(18),
        CMD_SetTimeMaterialAndWater(21),
        CMD_SetTimeMaterialAndWaterNew(29),
        CMD_SetGetCoinAndPaperSTA(22),
        FreshGroundCoffeeFinished(23),
        CMD_CheckIce(25),
        CMD_Test(26),
        CMD_LOCK(27),
        CMD_ReadRunErrorCode(30),
        CMD_ReadMaking(31),
        CMD_RUN_DOFFING(33),
        CMD_WATER(41),
        CMD_REPORTED_DATA(40);

        private byte mByte;

        CMD_instant(int i) {
            this.mByte = (byte) i;
        }

        public byte getId() {
            return this.mByte;
        }
    }

    /* loaded from: classes.dex */
    public enum CMD_pay {
        CMD_ReadPayDeviceStatus(32),
        CMD_SetPaymentWay(33),
        CMD_SmallChange(34),
        CMD_ReadAmount(35),
        CMD_DrinkStatus(36),
        CMD_AmountRate(37),
        CMD_SetConfig(38),
        CMD_CurrencyLimitSetting(39),
        CMD_SetPaymentWayNew(40),
        CMD_ReadAmountNew(41),
        CMD_AmountSure(42),
        CMD_AmountQ(8),
        CMD_StopAmount(43);

        private byte mByte;

        CMD_pay(int i) {
            this.mByte = (byte) i;
        }

        public byte getId() {
            return this.mByte;
        }
    }

    /* loaded from: classes.dex */
    public enum MDB_pay {
        MDB_NotesStatus(1),
        MDB_CoinStatus(2),
        MDB_SwipeStatus(53),
        MDB_TYPE(48),
        MDB_ReadAmount(35),
        MDB_DrinkStatus(36),
        MDB_AmountRate(37),
        MDB_SetConfig(38),
        MDB_CurrencyLimitSetting(39),
        MDB_SetPaymentWayNew(40),
        MDB_ReadAmountNew(41),
        MDB_AmountSure(42),
        MDB_AmountQ(8),
        MDB_StopAmount(43);

        private byte mByte;

        MDB_pay(int i) {
            this.mByte = (byte) i;
        }

        public byte getId() {
            return this.mByte;
        }
    }

    public static int AMD_instant() {
        DeviceInfoBean.TypeInfoBean typeInfoBean = deviceTypeDetail;
        if (typeInfoBean != null) {
            String[] split = typeInfoBean.getProtocol().split("_");
            if (split.length > 1 && split[0].equals("3")) {
                return 3;
            }
        }
        return 2;
    }

    public static byte[] Amount_payment_sure(byte[] bArr) {
        return getOrderBytes(CMD_pay.CMD_AmountSure, bArr[4], bArr[5], bArr[6], bArr[7], bArr[8], bArr[9], bArr[10], bArr[11], bArr[12], bArr[13], bArr[14], bArr[15]);
    }

    public static String Country() {
        return KvUtil.getInstance().getString("country", "0");
    }

    public static int HomePay() {
        return KvUtil.getInstance().getInt("HomePay", 0);
    }

    public static int LevelPay() {
        return KvUtil.getInstance().getInt("LevelPay", 3);
    }

    public static String SERIAPort_currentgrinding() {
        return KvUtil.getInstance().getString("SERIAPort_currentgrinding", "/dev/ttyS1");
    }

    public static String SERIAPort_instant() {
        return KvUtil.getInstance().getString("SERIAPort_instant", "/dev/ttyS7");
    }

    public static String SERIAPort_payment() {
        return KvUtil.getInstance().getString("SERIAPort_payment", "/dev/ttyS4");
    }

    public static void addOPSMessage(String str) {
        MyAppLocation.myAppLocation.myMqttService.addOpsLog(str, DataUtils.currentTime(), 1);
    }

    public static void clearErrorMsg() {
        InstantErrorMsg = "";
        CurrentErrorMsg = "";
        PaymentErrorMsg = "";
        IceErrorMsg = "";
        NetworkErrorMsg = "";
    }

    public static byte[] createMakerDrinkCmd(int i, ArrayList<Byte> arrayList) {
        byte[] byteMerger = ByteUtils.byteMerger(new byte[]{CMD_instant.CMD_SetTimeMaterialAndWaterNew.getId(), (byte) i}, new byte[]{3, 1});
        byte[] bArr = new byte[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bArr[i2] = arrayList.get(i2).byteValue();
        }
        byte[] byteMerger2 = ByteUtils.byteMerger(ByteUtils.byteMerger(ByteUtils.byteMerger(byteMerger, bArr), new byte[]{5, 0}), new byte[]{6, 0});
        byte[] byteMerger3 = ByteUtils.byteMerger(FLAG_instant, ByteUtils.byteMerger(new byte[]{(byte) (byteMerger2.length + 1)}, byteMerger2));
        byte[] byteMerger4 = ByteUtils.byteMerger(byteMerger3, new byte[]{(byte) (CRC8Util.byteCheckSum(byteMerger3) & 255)});
        Tools.upLocalLog("新的制饮命令：" + Tools.byteToStr(byteMerger4));
        return byteMerger4;
    }

    public static byte[] currencyLimitSetting_payment(byte b) {
        return getOrderBytes(CMD_pay.CMD_CurrencyLimitSetting, b);
    }

    private static int determineModel() {
        String lowerCase = Build.MODEL.toLowerCase();
        if (lowerCase.contains("m2")) {
            return 0;
        }
        if (lowerCase.contains("rk3568_t")) {
            return 3;
        }
        if (lowerCase.contains("rk3568")) {
            return 1;
        }
        return (lowerCase.contains("rk3368") || lowerCase.contains("rk3288")) ? 2 : 9;
    }

    public static DevConfig devConfig() {
        String string = KvUtil.getInstance().getString("DevConfig", "");
        return !string.isEmpty() ? (DevConfig) JSON.parseObject(string, DevConfig.class) : new DevConfig();
    }

    public static String deviceSN() {
        int i = Model;
        return i == 2 ? // // // // TniudsUtils.getMacWifi() : i == 0 ? ((KingsunSmartAPI) MyAppLocation.myAppLocation.getSystemService("kingsunsmartapi")).getCpuSerial() : i == 1 ? // // // // YNHAPI.getInstance().getSerialNo() : i == 3 ? ((ReecamManager) MyAppLocation.myAppLocation.getSystemService("reecam")).getSerial().replaceAll("\\s*", "") : ""; // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
    }

    public static String deviceSN(Application application) {
        int i = Model;
        if (i == 2) {
            return // // // // TniudsUtils.getMacWifi(); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
        }
        if (i == 0) {
            if (application != null) {
                return ((KingsunSmartAPI) application.getSystemService("kingsunsmartapi")).getCpuSerial();
            }
        } else {
            if (i == 1) {
                return // // // // YNHAPI.getInstance().getSerialNo(); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
            }
            if (i == 3) {
                return ((ReecamManager) application.getApplicationContext().getSystemService("reecam")).getSerial().replaceAll("\\s*", "");
            }
        }
        return "";
    }

    public static byte[] drinkStatus_payment(byte b) {
        return getOrderBytes(CMD_pay.CMD_DrinkStatus, b);
    }

    public static int getColumn() {
        return AppUtils.isCurOriLand(MyAppLocation.myAppLocation) ? KvUtil.getInstance().getInt("Column", 4) : KvUtil.getInstance().getInt("Column", 2);
    }

    public static DefaultSetting getDefaultSetting() {
        DeviceInfoBean.TypeInfoBean typeInfoBean = deviceTypeDetail;
        if (typeInfoBean == null) {
            Tools.upLocalLogTest("默认配置Constants.deviceTypeDetail机型详情为空.");
            return null;
        }
        String name = typeInfoBean.getName();
        int i = Model;
        new DefaultSetting();
        if (i == 0) {
            if (name.toUpperCase().contains("JK82") || name.toUpperCase().contains("JK86")) {
                return new DefaultSetting(i, name, 3, 1, 0, true, true, true, true, true, false, true, true, false, false, true, true, true, false, false, false, false);
            }
            if (name.toUpperCase().contains("JK81")) {
                return new DefaultSetting(i, name, 3, 1, 0, true, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false);
            }
            if (name.toUpperCase().contains("JK88")) {
                return new DefaultSetting(i, name, 3, 0, 0, true, true, true, true, false, true, true, true, false, false, true, true, false, false, false, false, true);
            }
            if (name.toUpperCase().contains("JK90")) {
                return new DefaultSetting(i, name, 3, 1, 0, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false);
            }
            if (name.toUpperCase().contains("GS805")) {
                return new DefaultSetting(i, name, 3, 0, 0, true, true, true, true, false, false, false, false, false, false, true, true, false, false, false, false, false);
            }
            if (!name.toUpperCase().contains("GS505") && !name.toUpperCase().contains("GS305")) {
                if (name.toUpperCase().contains("GS801")) {
                    return new DefaultSetting(i, name, 3, 0, 0, true, true, true, false, false, false, false, false, false, false, true, true, false, false, false, false, false);
                }
                Tools.upLocalLogTest("默认配置没有找到相关机器机型：" + name);
                return null;
            }
            return new DefaultSetting(i, name, 3, 0, 0, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false);
        }
        if (i == 1) {
            if (name.toUpperCase().contains("JK82") || name.toUpperCase().contains("JK86")) {
                return new DefaultSetting(i, name, 7, 9, 0, true, true, true, true, true, false, true, true, false, false, true, true, true, false, false, false, false);
            }
            if (name.toUpperCase().contains("JK81")) {
                return new DefaultSetting(i, name, 7, 9, 0, true, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false);
            }
            if (name.toUpperCase().contains("JK88")) {
                return new DefaultSetting(i, name, 7, 0, 0, true, true, true, true, false, true, true, true, false, false, true, true, false, false, false, false, true);
            }
            if (name.toUpperCase().contains("JK90")) {
                return new DefaultSetting(i, name, 7, 9, 0, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false);
            }
            if (name.toUpperCase().contains("GS805")) {
                return new DefaultSetting(i, name, 7, 0, 0, true, true, true, true, false, false, false, false, false, false, true, true, false, false, false, false, false);
            }
            if (!name.toUpperCase().contains("GS505") && !name.toUpperCase().contains("GS305")) {
                if (name.toUpperCase().contains("GS801")) {
                    return new DefaultSetting(i, name, 7, 0, 0, true, true, true, false, false, false, false, false, false, false, true, true, false, false, false, false, false);
                }
                Tools.upLocalLogTest("默认配置没有找到相关机器机型：" + name);
                return null;
            }
            return new DefaultSetting(i, name, 7, 0, 0, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false);
        }
        if (i == 2) {
            if (name.toUpperCase().contains("JK82") || name.toUpperCase().contains("JK86")) {
                return new DefaultSetting(i, name, 3, 4, 0, true, true, true, true, true, false, true, true, false, false, true, true, true, false, false, false, false);
            }
            if (name.toUpperCase().contains("JK81")) {
                return new DefaultSetting(i, name, 3, 4, 0, true, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false);
            }
            if (name.toUpperCase().contains("JK88")) {
                return new DefaultSetting(i, name, 3, 0, 0, true, true, true, true, false, true, true, true, false, false, true, true, false, false, false, false, true);
            }
            if (name.toUpperCase().contains("JK90")) {
                return new DefaultSetting(i, name, 3, 4, 0, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false);
            }
            if (name.toUpperCase().contains("GS805")) {
                return new DefaultSetting(i, name, 3, 0, 0, true, true, true, true, false, false, false, false, false, false, true, true, false, false, false, false, false);
            }
            if (!name.toUpperCase().contains("GS505") && !name.toUpperCase().contains("GS305")) {
                if (name.toUpperCase().contains("GS801")) {
                    return new DefaultSetting(i, name, 3, 0, 0, true, true, true, false, false, false, false, false, false, false, true, true, false, false, false, false, false);
                }
                Tools.upLocalLogTest("默认配置没有找到相关机器机型：" + name);
                return null;
            }
            return new DefaultSetting(i, name, 3, 0, 0, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false);
        }
        if (i != 3) {
            Tools.upLocalLogTest("默认配置没有找到相关安卓设备类型：" + i);
            return null;
        }
        if (name.toUpperCase().contains("JK82") || name.toUpperCase().contains("JK86")) {
            return new DefaultSetting(i, name, 7, 9, 0, true, true, true, true, true, false, true, true, false, false, true, true, true, false, false, false, false);
        }
        if (name.toUpperCase().contains("JK81")) {
            return new DefaultSetting(i, name, 7, 9, 0, true, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false);
        }
        if (name.toUpperCase().contains("JK88")) {
            return new DefaultSetting(i, name, 7, 0, 0, true, true, true, true, false, true, true, true, false, false, true, true, false, false, false, false, true);
        }
        if (name.toUpperCase().contains("JK90")) {
            return new DefaultSetting(i, name, 7, 9, 0, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false);
        }
        if (name.toUpperCase().contains("GS805")) {
            return new DefaultSetting(i, name, 7, 0, 0, true, true, true, true, false, false, false, false, false, false, false, true, false, false, false, false, false);
        }
        if (!name.toUpperCase().contains("GS505") && !name.toUpperCase().contains("GS305")) {
            if (name.toUpperCase().contains("GS801")) {
                return new DefaultSetting(i, name, 7, 0, 0, true, true, true, false, false, false, false, false, false, false, true, true, false, false, false, false, false);
            }
            Tools.upLocalLogTest("默认配置没有找到相关机器机型：" + name);
            return null;
        }
        return new DefaultSetting(i, name, 7, 0, 0, true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false);
    }

    public static int getDualLCD() {
        return KvUtil.getInstance().getInt("setDualLCD", 0);
    }

    public static String getErrorMsg() {
        return InstantErrorMsg + CurrentErrorMsg + PaymentErrorMsg + IceErrorMsg + NetworkErrorMsg;
    }

    public static int getHomeLocation() {
        return KvUtil.getInstance().getInt("HomeLocation", 0);
    }

    private static byte[] getOrderBytes(CMD_pay cMD_pay, int... iArr) {
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            i++;
        }
        byte[] byteMerger = ByteUtils.byteMerger(ByteUtils.byteMerger(FLAG_payment, new byte[]{(byte) ((i + 2) & 255)}), new byte[]{cMD_pay.getId()});
        for (int i3 : iArr) {
            byteMerger = ByteUtils.byteMerger(byteMerger, new byte[]{(byte) i3});
        }
        return ByteUtils.byteMerger(byteMerger, new byte[]{(byte) (CRC8Util.byteCheckSum(byteMerger) & 255)});
    }

    public static byte[] getOrderBytes_Currentgrinding(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7) {
        return getOrderBytes_Currentgrinding2(b, b2, b3, b4, b5, b6, b7);
    }

    private static byte[] getOrderBytes_Currentgrinding2(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7) {
        byte[] bArr = {85, -86, -127, 24, b, b2, b3, b4, 0, b5, b6, 0, 0, 0, b7, -69, 68};
        byte b8 = bArr[0];
        for (int i = 1; i < bArr.length; i++) {
            if (i != 11) {
                b8 = (byte) (b8 ^ bArr[i]);
            }
        }
        bArr[11] = b8;
        return bArr;
    }

    private static byte[] getOrderBytes_Currentgrinding3(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7) {
        byte[] bArr = {85, -86, -127, 24, b, b2, b3, b4, 0, b5, b6, (byte) (((((((((((((((bArr[0] ^ bArr[1]) ^ bArr[2]) ^ bArr[3]) ^ bArr[4]) ^ bArr[5]) ^ bArr[6]) ^ bArr[7]) ^ bArr[8]) ^ bArr[9]) ^ bArr[10]) ^ bArr[12]) ^ bArr[13]) ^ bArr[14]) ^ bArr[15]) ^ bArr[16]), 0, 0, b7, -69, 68};
        return bArr;
    }

    public static byte[] getOrderBytes_instant(CMD_instant cMD_instant, int... iArr) {
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            i++;
        }
        if (cMD_instant.getId() == CMD_instant.CMD_SetTimeMaterialAndWater.getId()) {
            i = ((i - 1) * 2) + 1;
        }
        byte[] byteMerger = ByteUtils.byteMerger(ByteUtils.byteMerger(FLAG_instant, new byte[]{(byte) ((i + 2) & 255)}), new byte[]{cMD_instant.getId()});
        int i3 = 0;
        while (i3 < iArr.length) {
            int i4 = iArr[i3];
            byteMerger = (cMD_instant.getId() != CMD_instant.CMD_SetTimeMaterialAndWater.getId() || i3 == 0) ? ByteUtils.byteMerger(byteMerger, new byte[]{(byte) i4}) : ByteUtils.byteMerger(byteMerger, new byte[]{(byte) (i4 >>> 8), (byte) (i4 & 255)});
            i3++;
        }
        byte[] byteMerger2 = ByteUtils.byteMerger(byteMerger, new byte[]{(byte) (CRC8Util.byteCheckSum(byteMerger) & 255)});
        if (byteMerger2[4] == 2 && byteMerger2[4] == 6) {
            Tools.upLocalLog("制作时落盖" + Tools.byteToStr(byteMerger2));
        }
        return byteMerger2;
    }

    @Deprecated
    public static String getPortStates() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        DevConfig devConfig = devConfig();
        OpsSeting opsSeting = opsSeting();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        StringBuffer stringBuffer3 = new StringBuffer();
        if (getStatePort1() == 1) {
            stringBuffer.append(MyAppLocation.myAppLocation.getString(R.string.srstate));
            stringBuffer2.append(MyAppLocation.myAppLocation.getString(R.string.zbstate));
            if (MyAppLocation.myAppLocation.mSerialDataService == null) {
                return "";
            }
            if (MyAppLocation.myAppLocation.mSerialDataService.communicationStatus_Instant) {
                if (MyAppLocation.myAppLocation.mSerialDataService.state_Instant.isEmpty()) {
                    stringBuffer.append(MyAppLocation.myAppLocation.getString(R.string.ok));
                } else {
                    stringBuffer.append(MyAppLocation.myAppLocation.getString(R.string.ng));
                    stringBuffer.append(MyAppLocation.myAppLocation.mSerialDataService.state_Instant);
                    stringBuffer3.append(MyAppLocation.myAppLocation.mSerialDataService.state_Instant);
                }
                if (!devConfig.isRight2_ice()) {
                    stringBuffer2.setLength(0);
                } else if (MyAppLocation.myAppLocation.mSerialDataService.state_Ice.isEmpty()) {
                    stringBuffer2.append(MyAppLocation.myAppLocation.getString(R.string.ok));
                } else {
                    stringBuffer2.append(MyAppLocation.myAppLocation.getString(R.string.ng));
                }
            } else {
                stringBuffer.append(MyAppLocation.myAppLocation.getString(R.string.notlink));
                stringBuffer3.append(MyAppLocation.myAppLocation.getString(R.string.notlink));
                if (devConfig.isRight2_ice()) {
                    stringBuffer2.append(MyAppLocation.myAppLocation.getString(R.string.notlink));
                } else {
                    stringBuffer2.setLength(0);
                }
            }
        }
        StringBuffer stringBuffer4 = new StringBuffer();
        if (getStatePort2() == 1) {
            stringBuffer4.append(MyAppLocation.myAppLocation.getString(R.string.xmstate));
            if (!MyAppLocation.myAppLocation.mSerialDataService.communicationStatus_Currentgrinding) {
                stringBuffer4.append(MyAppLocation.myAppLocation.getString(R.string.notlink));
            } else if (MyAppLocation.myAppLocation.mSerialDataService.state_Currentgrinding.isEmpty()) {
                stringBuffer4.append(MyAppLocation.myAppLocation.getString(R.string.ok));
            } else {
                stringBuffer4.append(MyAppLocation.myAppLocation.getString(R.string.ng));
                stringBuffer4.append(MyAppLocation.myAppLocation.mSerialDataService.state_Currentgrinding);
                stringBuffer3.append(MyAppLocation.myAppLocation.mSerialDataService.state_Currentgrinding);
            }
        }
        StringBuffer stringBuffer5 = new StringBuffer();
        StringBuffer stringBuffer6 = new StringBuffer();
        StringBuffer stringBuffer7 = new StringBuffer();
        if (getStatePort3() == 1) {
            stringBuffer6.append(MyAppLocation.myAppLocation.getString(R.string.cashstate));
            stringBuffer5.append(MyAppLocation.myAppLocation.getString(R.string.changestate));
            stringBuffer7.append(MyAppLocation.myAppLocation.getString(R.string.casdstate));
            if (MyAppLocation.myAppLocation.mSerialDataService.communicationStatus_Payment) {
                if (!opsSeting.isCardpay()) {
                    stringBuffer7.setLength(0);
                } else if (MyAppLocation.myAppLocation.mSerialDataService.state_Payment_card.isEmpty()) {
                    stringBuffer7.append(MyAppLocation.myAppLocation.getString(R.string.ok));
                } else {
                    stringBuffer7.append(MyAppLocation.myAppLocation.getString(R.string.ng));
                    stringBuffer7.append(MyAppLocation.myAppLocation.mSerialDataService.state_Payment_card);
                    stringBuffer3.append(MyAppLocation.myAppLocation.mSerialDataService.state_Payment_card);
                }
                if (!opsSeting.isCash()) {
                    stringBuffer6.setLength(0);
                } else if (MyAppLocation.myAppLocation.mSerialDataService.state_Payment_cash.isEmpty()) {
                    stringBuffer6.append(MyAppLocation.myAppLocation.getString(R.string.ok));
                } else {
                    stringBuffer6.append(MyAppLocation.myAppLocation.getString(R.string.ng));
                    stringBuffer6.append(MyAppLocation.myAppLocation.mSerialDataService.state_Payment_cash);
                    stringBuffer3.append(MyAppLocation.myAppLocation.mSerialDataService.state_Payment_cash);
                }
                if (!opsSeting.isChange()) {
                    stringBuffer5.setLength(0);
                } else if (MyAppLocation.myAppLocation.mSerialDataService.state_Payment_change.isEmpty()) {
                    stringBuffer5.append(MyAppLocation.myAppLocation.getString(R.string.ok));
                } else {
                    stringBuffer5.append(MyAppLocation.myAppLocation.getString(R.string.ng));
                    stringBuffer5.append(MyAppLocation.myAppLocation.mSerialDataService.state_Payment_change);
                    stringBuffer3.append(MyAppLocation.myAppLocation.mSerialDataService.state_Payment_change);
                }
            } else {
                if (opsSeting.isCardpay()) {
                    stringBuffer7.append(MyAppLocation.myAppLocation.getString(R.string.notlink));
                    stringBuffer3.append(MyAppLocation.myAppLocation.getString(R.string.casdstate) + ":" + MyAppLocation.myAppLocation.getString(R.string.notlink));
                } else {
                    stringBuffer7.setLength(0);
                }
                if (opsSeting.isCash()) {
                    stringBuffer6.append(MyAppLocation.myAppLocation.getString(R.string.notlink));
                    stringBuffer3.append(MyAppLocation.myAppLocation.getString(R.string.cashstate) + ":" + MyAppLocation.myAppLocation.getString(R.string.notlink));
                } else {
                    stringBuffer6.setLength(0);
                }
                if (opsSeting.isChange()) {
                    stringBuffer5.append(MyAppLocation.myAppLocation.getString(R.string.notlink));
                    stringBuffer3.append(MyAppLocation.myAppLocation.getString(R.string.changestate) + ":" + MyAppLocation.myAppLocation.getString(R.string.notlink));
                } else {
                    stringBuffer5.setLength(0);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (stringBuffer.toString().contains(MyAppLocation.myAppLocation.getString(R.string.ok))) {
            str = "";
        } else {
            str = stringBuffer.toString() + "\r\n";
        }
        sb.append(str);
        if (stringBuffer2.toString().contains(MyAppLocation.myAppLocation.getString(R.string.ok))) {
            str2 = "";
        } else {
            str2 = stringBuffer2.toString() + "\r\n";
        }
        sb.append(str2);
        if (stringBuffer4.toString().contains(MyAppLocation.myAppLocation.getString(R.string.ok))) {
            str3 = "";
        } else {
            str3 = stringBuffer4.toString() + "\r\n";
        }
        sb.append(str3);
        if (stringBuffer7.toString().contains(MyAppLocation.myAppLocation.getString(R.string.ok))) {
            str4 = "";
        } else {
            str4 = stringBuffer7.toString() + "\r\n";
        }
        sb.append(str4);
        if (stringBuffer6.toString().contains(MyAppLocation.myAppLocation.getString(R.string.ok))) {
            str5 = "";
        } else {
            str5 = stringBuffer6.toString() + "\r\n";
        }
        sb.append(str5);
        sb.append(stringBuffer5.toString().contains(MyAppLocation.myAppLocation.getString(R.string.ok)) ? "" : stringBuffer5.toString());
        return sb.toString();
    }

    public static int getRow() {
        return AppUtils.isCurOriLand(MyAppLocation.myAppLocation) ? KvUtil.getInstance().getInt("Row", 2) : KvUtil.getInstance().getInt("Row", 4);
    }

    public static int getStatePort1() {
        return 1;
    }

    public static void getStatePort1(int i) {
        KvUtil.getInstance().putInt("getStatePort1", i);
    }

    public static int getStatePort2() {
        return KvUtil.getInstance().getInt("getStatePort2", 0);
    }

    public static void getStatePort2(int i) {
        KvUtil.getInstance().putInt("getStatePort2", i);
    }

    public static int getStatePort3() {
        return KvUtil.getInstance().getInt("getStatePort3", 0);
    }

    public static void getStatePort3(int i) {
        KvUtil.getInstance().putInt("getStatePort3", i);
    }

    public static List<BaseMaterial> getnowProductMaterialMessage() {
        return materialMessageList;
    }

    public static int homeGlideDirection() {
        return KvUtil.getInstance().getInt("homeGlideDirection", -1);
    }

    public static String keyCodeToChar(int i, boolean z) {
        if (i == 59 || i == 60) {
            return "";
        }
        switch (i) {
            case 7:
                return z ? ")" : "0";
            case 8:
                return z ? "!" : "1";
            case 9:
                return z ? "@" : "2";
            case 10:
                return z ? MqttTopic.MULTI_LEVEL_WILDCARD : "3";
            case 11:
                return z ? "$" : "4";
            case 12:
                return z ? "%" : "5";
            case 13:
                return z ? "^" : "6";
            case 14:
                return z ? "&" : "7";
            case 15:
                return z ? "*" : "8";
            case 16:
                return z ? "(" : "9";
            default:
                switch (i) {
                    case 29:
                        return z ? "A" : "a";
                    case 30:
                        return z ? "B" : "b";
                    case 31:
                        return z ? "C" : "c";
                    case 32:
                        return z ? "D" : "d";
                    case 33:
                        return z ? "E" : "e";
                    case 34:
                        return z ? "F" : "f";
                    case 35:
                        return z ? "G" : "g";
                    case 36:
                        return z ? "H" : "h";
                    case 37:
                        return z ? "I" : "i";
                    case 38:
                        return z ? "J" : "j";
                    case 39:
                        return z ? "K" : "k";
                    case 40:
                        return z ? "L" : "l";
                    case 41:
                        return z ? "M" : "m";
                    case 42:
                        return z ? "N" : "n";
                    case 43:
                        return z ? "O" : "o";
                    case 44:
                        return z ? "P" : "p";
                    case 45:
                        return z ? "Q" : "q";
                    case 46:
                        return z ? "R" : "r";
                    case 47:
                        return z ? "S" : "s";
                    case 48:
                        return z ? "T" : "t";
                    case 49:
                        return z ? "U" : "u";
                    case 50:
                        return z ? "V" : "v";
                    case 51:
                        return z ? "W" : "w";
                    case 52:
                        return z ? "X" : "x";
                    case 53:
                        return z ? "Y" : "y";
                    case 54:
                        return z ? "Z" : "z";
                    case 55:
                        return z ? "<" : ",";
                    case 56:
                        return z ? ">" : ".";
                    default:
                        switch (i) {
                            case 68:
                                return z ? "~" : "`";
                            case 69:
                                return z ? "_" : "-";
                            case 70:
                                return z ? MqttTopic.SINGLE_LEVEL_WILDCARD : "=";
                            case 71:
                                return z ? "{" : "[";
                            case 72:
                                return z ? "}" : "]";
                            case 73:
                                return z ? "|" : "\\";
                            case 74:
                                return z ? ":" : ";";
                            case 75:
                                return z ? "\"" : "'";
                            case 76:
                                return z ? "?" : MqttTopic.TOPIC_LEVEL_SEPARATOR;
                            default:
                                return "";
                        }
                }
        }
    }

    public static OpsSeting opsSeting() {
        String string = KvUtil.getInstance().getString("OpsSeting", "");
        return !string.isEmpty() ? (OpsSeting) new Gson().fromJson(string, OpsSeting.class) : new OpsSeting();
    }

    @Deprecated
    public static void reBoot() {
    }

    public static byte[] readAmount_payment() {
        return getOrderBytes(CMD_pay.CMD_ReadAmount, new int[0]);
    }

    public static byte[] readAmount_paymentnew() {
        return getOrderBytes(CMD_pay.CMD_ReadAmountNew, new int[0]);
    }

    public static byte[] readPayDeviceStatus_payment() {
        return getOrderBytes(CMD_pay.CMD_ReadPayDeviceStatus, new int[0]);
    }

    public static void restart() {
        int i = Model;
        if (i == 2) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.yj.coffeemachines.-$$Lambda$Constants$n92VJVldXVX7VL9g85rn2RNdeQM
                @Override // java.lang.Runnable
                public final void run() {
                    // // // // TniudsUtils.setSystemReboot_hard(); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
                }
            }, 2000L);
            return;
        }
        if (i == 0) {
            ((KingsunSmartAPI) MyAppLocation.myAppLocation.getSystemService("kingsunsmartapi")).reboot(0);
        } else if (i == 1) {
            // // // // YNHAPI.getInstance().reboot(); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
        } else if (i == 3) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.yj.coffeemachines.-$$Lambda$Constants$04GAmg4zhxc98dRxmQp3laGy3zs
                @Override // java.lang.Runnable
                public final void run() {
                    ((ReecamManager) MyAppLocation.myAppLocation.getApplicationContext().getSystemService("reecam")).rebootSystem();
                }
            }, 2000L);
        }
    }

    public static byte[] setAmountRate_payment(byte[] bArr) {
        return getOrderBytes(CMD_pay.CMD_AmountRate, bArr[0], bArr[1]);
    }

    public static void setColumn(int i) {
        KvUtil.getInstance().putInt("Column", i);
    }

    public static byte[] setConfig_payment(byte b, byte[] bArr, byte[] bArr2) {
        return getOrderBytes(CMD_pay.CMD_SetConfig, b, bArr[0], bArr[1], bArr2[0], bArr2[1]);
    }

    public static void setCountry(String str) {
        KvUtil.getInstance().putString("country", str);
    }

    public static void setDevConfig(DevConfig devConfig) {
        KvUtil.getInstance().putString("DevConfig", GsonUtils.toJson(devConfig));
    }

    public static void setDualLCD() {
        if (KvUtil.getInstance().getInt("setDualLCD", 0) == 0) {
            KvUtil.getInstance().putInt("setDualLCD", 1);
            MyAppLocation.myAppLocation.showSecondaryDisplayIfNeeded();
        } else {
            KvUtil.getInstance().putInt("setDualLCD", 0);
            MyAppLocation.myAppLocation.closeSearchPresentation();
        }
    }

    public static void setHintTooBar(Activity activity) {
        int i = Model;
        if (i == 2) {
            // // // // TniudsUtils.setNavigationBarEnable(activity, false); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
            return;
        }
        if (i == 0) {
            ((KingsunSmartAPI) activity.getSystemService("kingsunsmartapi")).setStatusBar(false);
            return;
        }
        if (i == 1) {
            YNHAPI ynhapi = // // // // YNHAPI.getInstance(); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
            ynhapi.setNavigationBarVisibility(YNHAPI.NavigationBarVisibility.ALWAYS_INVISIBLE);
            ynhapi.setExtendStatusBarVisibility(YNHAPI.ExtendStatusBarVisibility.INVISIBLE_FOREVER);
        } else if (i == 3) {
            ((ReecamManager) activity.getSystemService("reecam")).setHideNavBar(true);
        }
    }

    public static void setHomeLocation(int i) {
        KvUtil.getInstance().putInt("HomeLocation", i);
    }

    public static void setHomePay(int i) {
        KvUtil.getInstance().putInt("HomePay", i);
    }

    public static void setLevelPay(int i) {
        KvUtil.getInstance().putInt("LevelPay", i);
    }

    public static void setOpenAndCloseDev(List<DeviceInfoBean.PowerPlanBean> list) {
        int i = 0;
        DeviceInfoBean.PowerPlanBean powerPlanBean = list.get(0);
        String powerOffCycleCron = powerPlanBean.getPowerOffCycleCron();
        String powerOnCycleCron = powerPlanBean.getPowerOnCycleCron();
        if (powerOffCycleCron.length() <= 0 || powerOnCycleCron.length() <= 0) {
            return;
        }
        String[] split = powerOffCycleCron.split(" ");
        String[] split2 = powerOnCycleCron.split(" ");
        String[] split3 = split[split.length - 1].split(",");
        String[] split4 = split2[split2.length - 1].split(",");
        String str = split[2].equals("*") ? "0" : split[2];
        String str2 = split[1].equals("*") ? "0" : split[1];
        int parseInt = Integer.parseInt(str);
        int parseInt2 = Integer.parseInt(str2);
        String str3 = split2[2].equals("*") ? "0" : split2[2];
        String str4 = split2[1].equals("*") ? "0" : split2[1];
        int parseInt3 = Integer.parseInt(str3);
        int parseInt4 = Integer.parseInt(str4);
        int[] iArr = new int[split3.length];
        for (int i2 = 0; i2 < split3.length; i2++) {
            iArr[i2] = Integer.parseInt(split4[i2]);
        }
        int i3 = Model;
        int i4 = 5;
        int i5 = 4;
        if (i3 == 2) {
            // // // // TniudsUtils.setRtcHardware(System.currentTimeMillis()); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
            for (int i6 = 0; i6 < 7; i6++) {
                // // // // TniudsUtils.setSystemOffTime(new int[]{0, 0, 0, 0, 0, 0, i6}); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
                // // // // TniudsUtils.setSystemOnTime(new int[]{0, 0, 0, 0, 0, 0, i6}); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
            }
            for (int i7 = 0; i7 < iArr.length; i7++) {
                int[] iArr2 = {0, 0, 0, parseInt, parseInt2, 0, iArr[i7]};
                int[] iArr3 = {0, 0, 0, parseInt3, parseInt4, 0, iArr[i7]};
                // // // // TniudsUtils.setSystemOffTime(iArr2); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
                // // // // TniudsUtils.setSystemOnTime(iArr3); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
            }
            return;
        }
        if (i3 != 0) {
            if (i3 == 1) {
                YNHAPI ynhapi = // // // // YNHAPI.getInstance(); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
                ynhapi.cancelPowerOnOffAlarm();
                ynhapi.setPowerOnOffAlarmCycle(3, iArr, new int[]{parseInt, parseInt2}, new int[]{parseInt3, parseInt4});
                return;
            }
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("cmd", "setTime");
        String str5 = "09:51;OFF;20:01";
        String str6 = "09:51;OFF;20:01";
        String str7 = str6;
        String str8 = str7;
        String str9 = str8;
        String str10 = "10:20;OFF;10:17";
        String str11 = str9;
        while (i < iArr.length) {
            if (iArr[i] == 1) {
                str5 = powerPlanBean.getPowerOnTimeStr() + ";ON;" + powerPlanBean.getPowerOffTimeStr();
            }
            if (iArr[i] == 2) {
                str11 = powerPlanBean.getPowerOnTimeStr() + ";ON;" + powerPlanBean.getPowerOffTimeStr();
            }
            if (iArr[i] == 3) {
                str6 = powerPlanBean.getPowerOnTimeStr() + ";ON;" + powerPlanBean.getPowerOffTimeStr();
            }
            if (iArr[i] == i5) {
                str7 = powerPlanBean.getPowerOnTimeStr() + ";ON;" + powerPlanBean.getPowerOffTimeStr();
            }
            if (iArr[i] == i4) {
                str8 = powerPlanBean.getPowerOnTimeStr() + ";ON;" + powerPlanBean.getPowerOffTimeStr();
            }
            if (iArr[i] == 6) {
                str9 = powerPlanBean.getPowerOnTimeStr() + ";ON;" + powerPlanBean.getPowerOffTimeStr();
            }
            if (iArr[i] == 7) {
                str10 = powerPlanBean.getPowerOnTimeStr() + ";ON;" + powerPlanBean.getPowerOffTimeStr();
            }
            i++;
            i4 = 5;
            i5 = 4;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Kingsun开关机设置：");
        sb.append(str5);
        sb.append("_");
        sb.append(str11);
        sb.append("_");
        sb.append(str6);
        sb.append("_");
        sb.append(str7);
        sb.append("_");
        sb.append(str8);
        sb.append("_");
        String str12 = str9;
        sb.append(str12);
        sb.append("_");
        String str13 = str10;
        sb.append(str13);
        sb.append("_");
        Tools.upLocalLog(sb.toString());
        intent.putExtra("monTime", str5);
        intent.putExtra("tueTime", str11);
        intent.putExtra("wedTime", str6);
        intent.putExtra("thurTime", str7);
        intent.putExtra("firTime", str8);
        intent.putExtra("staTime", str12);
        intent.putExtra("sunTime", str13);
        intent.setAction("com.kingsun.action.TIMER_SWITCH");
        MyAppLocation.myAppLocation.sendBroadcast(intent);
    }

    public static void setOpsSeting(OpsSeting opsSeting) {
        KvUtil.getInstance().putString("OpsSeting", new Gson().toJson(opsSeting));
    }

    public static byte[] setPaymentWay_payment(byte b, byte[] bArr) {
        return getOrderBytes(CMD_pay.CMD_SetPaymentWay, b, bArr[0], bArr[1]);
    }

    public static byte[] setPaymentWay_payment_new(byte b, byte[] bArr, byte[] bArr2) {
        return getOrderBytes(CMD_pay.CMD_SetPaymentWayNew, b, bArr[0], bArr[1], bArr[2], bArr[3], bArr2[0], bArr2[1], bArr2[2], bArr2[3]);
    }

    public static void setRow(int i) {
        KvUtil.getInstance().putInt("Row", i);
    }

    public static void setSERIAPort_currentgrinding(String str) {
        KvUtil.getInstance().putString("SERIAPort_currentgrinding", str);
    }

    public static void setSERIAPort_instant(String str) {
        KvUtil.getInstance().putString("SERIAPort_instant", str);
    }

    public static void setSERIAPort_payment(String str) {
        KvUtil.getInstance().putString("SERIAPort_payment", str);
    }

    public static void setShowTooBar(Activity activity) {
        int i = Model;
        if (i == 2) {
            // // // // TniudsUtils.setNavigationBarEnable(activity, true); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
            return;
        }
        if (i == 0) {
            ((KingsunSmartAPI) activity.getSystemService("kingsunsmartapi")).setStatusBar(true);
            return;
        }
        if (i == 1) {
            YNHAPI ynhapi = // // // // YNHAPI.getInstance(); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
            ynhapi.setNavigationBarVisibility(YNHAPI.NavigationBarVisibility.VISIBLE);
            ynhapi.setExtendStatusBarVisibility(YNHAPI.ExtendStatusBarVisibility.VISIBLE);
        } else if (i == 3) {
            ((ReecamManager) activity.getSystemService("reecam")).setHideNavBar(false);
        }
    }

    public static void sethomeGlideDirection(int i) {
        KvUtil.getInstance().putInt("homeGlideDirection", i);
    }

    public static void setnowProductMaterialMessage(List<BaseMaterial> list) {
        materialMessageList.clear();
        materialMessageList.addAll(list);
    }

    public static boolean showCurrentgrinding() {
        DeviceInfoBean.TypeInfoBean typeInfoBean = deviceTypeDetail;
        if (typeInfoBean != null) {
            String[] split = typeInfoBean.getProtocol().split("_");
            if (split.length > 1 && split[1].equals("000")) {
                return false;
            }
        }
        return true;
    }

    public static byte[] smallChange_payment(byte b) {
        mNumber = b;
        return getOrderBytes(CMD_pay.CMD_SmallChange, b);
    }

    public static byte[] stopAmount() {
        return getOrderBytes(CMD_pay.CMD_StopAmount, 0);
    }

    public static void transferKvData() {
        String str = (String) SPUtils.get(MyAppLocation.myAppLocation, "OpsSeting", "");
        if (!TextUtils.isEmpty(str)) {
            KvUtil.getInstance().putString("OpsSeting", str);
            SPUtils.remove(MyAppLocation.myAppLocation, "OpsSeting");
        }
        String str2 = (String) SPUtils.get(MyAppLocation.myAppLocation, "DevConfig", "");
        if (!TextUtils.isEmpty(str2)) {
            KvUtil.getInstance().putString("DevConfig", str2);
            SPUtils.remove(MyAppLocation.myAppLocation, "DevConfig");
        }
        int intValue = ((Integer) SPUtils.get(MyAppLocation.myAppLocation, "homeGlideDirection", -1)).intValue();
        if (intValue != -1) {
            KvUtil.getInstance().putInt("homeGlideDirection", intValue);
            SPUtils.remove(MyAppLocation.myAppLocation, "homeGlideDirection");
        }
        String str3 = (String) SPUtils.get(MyAppLocation.myAppLocation, "SERIAPort_instant", "");
        if (!TextUtils.isEmpty(str3)) {
            KvUtil.getInstance().putString("SERIAPort_instant", str3);
            SPUtils.remove(MyAppLocation.myAppLocation, "SERIAPort_instant");
        }
        String str4 = (String) SPUtils.get(MyAppLocation.myAppLocation, "SERIAPort_currentgrinding", "");
        if (!TextUtils.isEmpty(str4)) {
            KvUtil.getInstance().putString("SERIAPort_currentgrinding", str4);
            SPUtils.remove(MyAppLocation.myAppLocation, "SERIAPort_currentgrinding");
        }
        String str5 = (String) SPUtils.get(MyAppLocation.myAppLocation, "SERIAPort_payment", "");
        if (!TextUtils.isEmpty(str5)) {
            KvUtil.getInstance().putString("SERIAPort_payment", str5);
            SPUtils.remove(MyAppLocation.myAppLocation, "SERIAPort_payment");
        }
        int intValue2 = ((Integer) SPUtils.get(MyAppLocation.myAppLocation, "Row", 0)).intValue();
        if (intValue2 > 0) {
            KvUtil.getInstance().putInt("Row", intValue2);
            SPUtils.remove(MyAppLocation.myAppLocation, "Row");
        }
        int intValue3 = ((Integer) SPUtils.get(MyAppLocation.myAppLocation, "Column", 0)).intValue();
        if (intValue3 > 0) {
            KvUtil.getInstance().putInt("Column", intValue3);
            SPUtils.remove(MyAppLocation.myAppLocation, "Column");
        }
        int intValue4 = ((Integer) SPUtils.get(MyAppLocation.myAppLocation, "getStatePort1", 1)).intValue();
        if (intValue4 != 1) {
            KvUtil.getInstance().putInt("getStatePort1", intValue4);
            SPUtils.remove(MyAppLocation.myAppLocation, "getStatePort1");
        }
        int intValue5 = ((Integer) SPUtils.get(MyAppLocation.myAppLocation, "getStatePort2", 0)).intValue();
        if (intValue5 != 0) {
            KvUtil.getInstance().putInt("getStatePort2", intValue5);
            SPUtils.remove(MyAppLocation.myAppLocation, "getStatePort2");
        }
        int intValue6 = ((Integer) SPUtils.get(MyAppLocation.myAppLocation, "getStatePort3", 0)).intValue();
        if (intValue6 != 0) {
            KvUtil.getInstance().putInt("getStatePort3", intValue6);
            SPUtils.remove(MyAppLocation.myAppLocation, "getStatePort3");
        }
    }
}
