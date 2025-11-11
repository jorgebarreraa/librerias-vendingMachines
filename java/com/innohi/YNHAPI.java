package com.innohi;

// import android.app.kingsun.rk3288;
// import android.content.Context;
// import android.hardware.Camera;
// import android.os.RemoteException;
// import android.os.ServiceManager;
// import android.util.Log;
// import com.innohi.IGpioListener;
// import com.innohi.IInnohiManager;
// import com.innohi.IWiegandCallback;
// import com.innohi.entity.IpConfig;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Map;
// import java.util.Set;

// /* loaded from: classes.dex */
// public class YNHAPI {
//     public static final int CASHBOX_0 = 22;
//     public static final int CASHBOX_1 = 23;
//     public static final int GPIO_1 = 0;
//     public static final int GPIO_10 = 9;
//     public static final int GPIO_11 = 10;
//     public static final int GPIO_12 = 11;
//     public static final int GPIO_2 = 1;
//     public static final int GPIO_3 = 2;
//     public static final int GPIO_4 = 3;
//     public static final int GPIO_5 = 4;
//     public static final int GPIO_6 = 5;
//     public static final int GPIO_7 = 6;
//     public static final int GPIO_8 = 7;
//     public static final int GPIO_9 = 8;
//     public static final int LIGHT_BLUE = 17;
//     public static final int LIGHT_CAMERA_EXTERNAL_INFRARED = 29;
//     public static final int LIGHT_GREEN = 16;
//     public static final int LIGHT_INFRARED = 19;
//     public static final int LIGHT_RED = 15;
//     public static final int LIGHT_WHITE = 18;
//     public static final int RELAY = 20;
//     private static final String TAG = "YNHAPI";
//     private static YNHAPI mInstance;
//     private static IInnohiManager mService;
//     private static Map<Integer, Set<GpioListenerCallback>> mListenerMap = new HashMap();
//     private static Set<Integer> mAlreadyRegisteredGpio = new HashSet();
//     private static IGpioListener mBinder = new IGpioListener.Stub() { // from class: com.innohi.YNHAPI.1
//         public void onChanged(int i, int i2, int i3) throws RemoteException {
//             Log.d(YNHAPI.TAG, "receive remote server callback: index = " + i + ", oldValue = " + i2 + ", newValue = " + i3);
//             synchronized (YNHAPI.class) {
//                 for (GpioListenerCallback gpioListenerCallback : (Set) YNHAPI.mListenerMap.get(Integer.valueOf(i))) {
//                     Log.d(YNHAPI.TAG, "notify client");
//                     gpioListenerCallback.onChanged(i, i2, i3);
//                 }
//             }
//         }
//     };

//     /* JADX INFO: Access modifiers changed from: package-private */
//     /* renamed from: com.innohi.YNHAPI$2, reason: invalid class name */
//     /* loaded from: classes.dex */
//     public static /* synthetic */ class AnonymousClass2 {
//         static final /* synthetic */ int[] $SwitchMap$com$innohi$YNHAPI$Device = new int[Device.values().length];
//         static final /* synthetic */ int[] $SwitchMap$com$innohi$YNHAPI$Light;
//         static final /* synthetic */ int[] $SwitchMap$com$innohi$YNHAPI$Usb;

//         static {
//             try {
//                 $SwitchMap$com$innohi$YNHAPI$Device[Device.Device_Relay.ordinal()] = 1;
//             } catch (NoSuchFieldError unused) {
//             }
//             $SwitchMap$com$innohi$YNHAPI$Usb = new int[Usb.values().length];
//             try {
//                 $SwitchMap$com$innohi$YNHAPI$Usb[Usb.Usb_1.ordinal()] = 1;
//             } catch (NoSuchFieldError unused2) {
//             }
//             try {
//                 $SwitchMap$com$innohi$YNHAPI$Usb[Usb.Usb_2.ordinal()] = 2;
//             } catch (NoSuchFieldError unused3) {
//             }
//             try {
//                 $SwitchMap$com$innohi$YNHAPI$Usb[Usb.Usb_3.ordinal()] = 3;
//             } catch (NoSuchFieldError unused4) {
//             }
//             $SwitchMap$com$innohi$YNHAPI$Light = new int[Light.values().length];
//             try {
//                 $SwitchMap$com$innohi$YNHAPI$Light[Light.Light_InfraredLed.ordinal()] = 1;
//             } catch (NoSuchFieldError unused5) {
//             }
//             try {
//                 $SwitchMap$com$innohi$YNHAPI$Light[Light.Light_White.ordinal()] = 2;
//             } catch (NoSuchFieldError unused6) {
//             }
//             try {
//                 $SwitchMap$com$innohi$YNHAPI$Light[Light.Light_Green.ordinal()] = 3;
//             } catch (NoSuchFieldError unused7) {
//             }
//             try {
//                 $SwitchMap$com$innohi$YNHAPI$Light[Light.Light_Red.ordinal()] = 4;
//             } catch (NoSuchFieldError unused8) {
//             }
//             try {
//                 $SwitchMap$com$innohi$YNHAPI$Light[Light.Light_Blue.ordinal()] = 5;
//             } catch (NoSuchFieldError unused9) {
//             }
//         }
//     }

//     /* loaded from: classes.dex */
//     private static class BnWiegandCallback extends IWiegandCallback.Stub {
//         static final /* synthetic */ boolean $assertionsDisabled = false;
//         private WiegandCallback mCallback;

//         public BnWiegandCallback(WiegandCallback wiegandCallback) {
//             this.mCallback = wiegandCallback;
//         }

//         public void onError() throws RemoteException {
//             this.mCallback.onFailure();
//         }

//         public void onGet(long j) throws RemoteException {
//             this.mCallback.onSuccess(j);
//         }
//     }

//     /* loaded from: classes.dex */
//     class CameraInfo {
//         public static final int CAMERA_FACING_BACK = 0;
//         public static final int CAMERA_FACING_FRONT = 1;
//         public static final int CAMERA_MIRROR = 1;
//         public static final int CAMERA_MODE_HDR = 1;
//         public static final int CAMERA_MODE_NORMAL = 0;
//         public static final int CAMERA_NON_MIRROR = 0;
//         public int facing;
//         public int hdr;
//         public int mirror;
//         public int rotation;

//         CameraInfo() {
//         }
//     }

//     /* loaded from: classes.dex */
//     public enum Device {
//         Device_5V,
//         Device_12V,
//         Device_Relay
//     }

//     /* loaded from: classes.dex */
//     public enum ExtendStatusBarVisibility {
//         INVISIBLE(0),
//         VISIBLE(1),
//         INVISIBLE_FOREVER(2),
//         VISIBLE_FOREVER(3),
//         UNKNOWN(-1);

//         public int mValue;

//         ExtendStatusBarVisibility(int i) {
//             this.mValue = i;
//         }
//     }

//     /* loaded from: classes.dex */
//     public interface GpioListenerCallback {
//         void onChanged(int i, int i2, int i3);
//     }

//     /* loaded from: classes.dex */
//     public enum GpioMode {
//         INPUT(0),
//         OUTPUT(1),
//         UNKNOWN(-1);

//         public int mValue;

//         GpioMode(int i) {
//             this.mValue = i;
//         }
//     }

//     /* loaded from: classes.dex */
//     public enum GpioState {
//         LOW(0),
//         HIGH(1),
//         UNKNOWN(-1);

//         public int mValue;

//         GpioState(int i) {
//             this.mValue = i;
//         }
//     }

//     /* loaded from: classes.dex */
//     public enum IpMode {
//         STATIC(0),
//         DHCP(1),
//         UNKNOWN(-1);

//         public int mValue;

//         IpMode(int i) {
//             this.mValue = i;
//         }
//     }

//     /* loaded from: classes.dex */
//     public enum LcdDensity {
//         DENSITY_140(140),
//         DENSITY_160(160),
//         DENSITY_240(rk3288.RK30_PIN7_PC0),
//         DENSITY_320(320),
//         DENSITY_UNKNOWN(-1);

//         public int mDensity;

//         LcdDensity(int i) {
//             this.mDensity = i;
//         }
//     }

//     /* loaded from: classes.dex */
//     public enum Light {
//         Light_InfraredLed,
//         Light_White,
//         Light_Green,
//         Light_Red,
//         Light_Blue
//     }

//     /* loaded from: classes.dex */
//     public enum NavigationBarVisibility {
//         INVISIBLE(0),
//         VISIBLE(1),
//         ALWAYS_INVISIBLE(2),
//         INVISIBLE_FOREVER(3),
//         VISIBLE_FOREVER(4),
//         ALWAYS_INVISIBLE_FOREVER(5),
//         UNKNOWN(-1);

//         public int mValue;

//         NavigationBarVisibility(int i) {
//             this.mValue = i;
//         }
//     }

//     /* loaded from: classes.dex */
//     public enum RotationDegree {
//         DEGREE_0(0),
//         DEGREE_90(90),
//         DEGREE_180(180),
//         DEGREE_270(rk3288.RK30_PIN8_PB6),
//         DEGREE_UNKNOWN(-1);

//         public int mDegree;

//         RotationDegree(int i) {
//             this.mDegree = i;
//         }
//     }

//     /* loaded from: classes.dex */
//     public enum ScreenType {
//         MAIN(0),
//         AUX(1);

//         public int mValue;

//         ScreenType(int i) {
//             this.mValue = i;
//         }
//     }

//     /* loaded from: classes.dex */
//     public enum Usb {
//         Usb_1,
//         Usb_2,
//         Usb_3
//     }

//     /* loaded from: classes.dex */
//     public enum Wiegand {
//         Wiegand_Input,
//         Wiegand_Output26,
//         Wiegand_Output34
//     }

//     /* loaded from: classes.dex */
//     public interface WiegandCallback {
//         void onFailure();

//         void onSuccess(long j);
//     }

//     /* loaded from: classes.dex */
//     public enum WiegandFormat {
//         FORMAT_26,
//         FORMAT_34
//     }

//     /* loaded from: classes.dex */
//     public enum WiegandMode {
//         INPUT(0),
//         OUTPUT(1),
//         UNKNOWN(-1);

//         public int mMode;

//         WiegandMode(int i) {
//             this.mMode = i;
//         }
//     }

//     private YNHAPI() {
//         init(null);
//     }

//     public static int Gpio_readGpioMux(int i) {
//         try {
//             return mService.getGpioMode(i);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return -1;
//         }
//     }

//     public static int Gpio_readGpioVal(int i) {
//         try {
//             return mService.readGpio(i);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return -1;
//         }
//     }

//     public static void Gpio_setGpioMux(int i, int i2) {
//         try {
//             mService.setGpioMode(i, i2);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void Gpio_writeGpioVal(int i, boolean z) {
//         try {
//             mService.writeGpio(i, z ? 1 : 0);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_allwaysHideNavBar(boolean z) {
//         try {
//             mService.setNavigationBarVisibility(2);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_cancelTimingPowerOnOff() {
//         try {
//             mService.cancelPowerOnOffAlarm();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_capScreen(String str) {
//         try {
//             mService.screenshot();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_clearWatchDog() {
//         System_feedWatchDog();
//     }

//     public static void System_closeWatchDog() {
//         try {
//             mService.enableWatchdog(false);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_feedWatchDog() {
//         try {
//             mService.feedWatchdog();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static String[] System_getIP() {
//         EthernetInfo ethernetInfo;
//         try {
//             ethernetInfo = mService.getIp();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             ethernetInfo = null;
//         }
//         String[] strArr = new String[4];
//         if (ethernetInfo != null) {
//             strArr[0] = ethernetInfo.ip;
//             strArr[1] = ethernetInfo.mask;
//             strArr[2] = ethernetInfo.gateway;
//             strArr[3] = (String) ethernetInfo.dnsList.get(0);
//         }
//         return strArr;
//     }

//     public static String System_getNavBarState() {
//         int i;
//         try {
//             i = mService.getNavigationBarVisibility();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             i = -1;
//         }
//         return i == 1 ? "true" : "false";
//     }

//     public static String System_getSerialNo() {
//         try {
//             return mService.getSerialNo();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return "";
//         }
//     }

//     public static void System_hideNavBar(boolean z) {
//         try {
//             mService.setNavigationBarVisibility(z ? 0 : 1);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_installApkSilence(String str) {
//         try {
//             mService.installApk(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_openWatchDog() {
//         try {
//             mService.enableWatchdog(true);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_otaUpdate(String str) {
//         try {
//             mService.otaUpdate(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_reboot() {
//         try {
//             mService.reboot();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_setIP(String str, String str2, String str3, String str4) {
//         try {
//             mService.setStaticIp(new EthernetInfo(str, str2, str3, Arrays.asList(str4)));
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_setRotation(boolean z, int i, int i2) {
//         try {
//             mService.setRotation(0, i);
//             mService.setRotation(1, i);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_setSystemTime(long j) {
//         try {
//             mService.setSystemTime(j);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_setTimingPowerOnOff(int[] iArr, int[] iArr2) {
//         try {
//             mService.setPowerOnOffAlarm(iArr, iArr2);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_shutDown() {
//         try {
//             mService.shutdown();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_sleep() {
//         try {
//             mService.systemSleep();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_uninstallApkSilence(String str) {
//         try {
//             mService.uninstallApk(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void System_wakeUp() {
//         try {
//             mService.systemWakeup();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void closeWiegand(Wiegand wiegand) {
//     }

//     private static int convertDevice(Device device) {
//         if (AnonymousClass2.$SwitchMap$com$innohi$YNHAPI$Device[device.ordinal()] == 1) {
//             return 20;
//         }
//         Log.d(TAG, "device " + device + " is invalid");
//         return -1;
//     }

//     private static int convertLight(Light light) {
//         int i = AnonymousClass2.$SwitchMap$com$innohi$YNHAPI$Light[light.ordinal()];
//         if (i == 1) {
//             return 19;
//         }
//         if (i == 2) {
//             return 18;
//         }
//         if (i == 3) {
//             return 16;
//         }
//         if (i != 4) {
//             return i != 5 ? -1 : 17;
//         }
//         return 15;
//     }

//     private static int convertUsbIndex(Usb usb) {
//         int i = AnonymousClass2.$SwitchMap$com$innohi$YNHAPI$Usb[usb.ordinal()];
//         if (i == 1) {
//             return 25;
//         }
//         if (i != 2) {
//             return i != 3 ? -1 : 27;
//         }
//         return 26;
//     }

//     public static boolean getDeviceState(Device device) {
//         int convertDevice = convertDevice(device);
//         if (convertDevice == -1) {
//             return false;
//         }
//         try {
//             Log.d(TAG, "getDeviceState: device = " + device);
//             return mService.readGpio(convertDevice) == 1;
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public static YNHAPI getInstance() {
//         if (mInstance == null) {
//             synchronized (YNHAPI.class) {
//                 mInstance = new YNHAPI();
//             }
//         }
//         return mInstance;
//     }

//     public static int getLightBrightness(Light light) {
//         try {
//             return mService.getLightBrightness(convertLight(light));
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return -1;
//         }
//     }

//     public static boolean getLightState(Light light) {
//         int convertLight = convertLight(light);
//         if (convertLight == -1) {
//             return false;
//         }
//         try {
//             return mService.getLightBrightness(convertLight) > 0;
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public static boolean getUsbState(Usb usb) {
//         try {
//             return mService.readGpio(convertUsbIndex(usb)) == 1;
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public static int getWiegandMode() {
//         try {
//             return mService.readWiegandMode();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return -1;
//         }
//     }

//     public static void init(Context context) {
//         mService = IInnohiManager.Stub.asInterface(ServiceManager.getService("innohi"));
//     }

//     public static boolean openWiegand(Wiegand wiegand) {
//         return true;
//     }

//     public static String readWiegand(Wiegand wiegand) {
//         try {
//             return String.valueOf(mService.readWiegand());
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return "";
//         }
//     }

//     public static void readWiegandAsync(WiegandCallback wiegandCallback) {
//         try {
//             mService.readWiegandAsync(new BnWiegandCallback(wiegandCallback));
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             wiegandCallback.onFailure();
//         }
//     }

//     public static synchronized void registerGpioListener(int i, GpioListenerCallback gpioListenerCallback) {
//         synchronized (YNHAPI.class) {
//             Log.d(TAG, "registerGpioListener: index " + i);
//             Set<GpioListenerCallback> set = mListenerMap.get(Integer.valueOf(i));
//             if (set == null || set.size() == 0) {
//                 try {
//                     Log.d(TAG, "register callback to server.");
//                     mService.registerGpioListener(i, mBinder);
//                 } catch (RemoteException e) {
//                     e.printStackTrace();
//                 }
//             }
//             if (set == null) {
//                 set = new HashSet<>();
//                 mListenerMap.put(Integer.valueOf(i), set);
//             }
//             set.add(gpioListenerCallback);
//         }
//     }

//     public static void setBootLaunch(String str, boolean z) {
//         try {
//             mService.setBootLaunch(str, z);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void setDeviceState(Device device, boolean z) {
//         int convertDevice = convertDevice(device);
//         if (convertDevice != -1) {
//             try {
//                 mService.writeGpio(convertDevice, z ? 1 : 0);
//             } catch (RemoteException e) {
//                 e.printStackTrace();
//             }
//         }
//     }

//     public static void setLightBrightness(Light light, int i) {
//         try {
//             mService.setLightBrightness(convertLight(light), i);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void setLightState(Light light, boolean z) {
//         int convertLight = convertLight(light);
//         if (convertLight != -1) {
//             try {
//                 if (z) {
//                     mService.setLightBrightness(convertLight, rk3288.RK30_PIN6_PB4);
//                 } else {
//                     mService.setLightBrightness(convertLight, 0);
//                 }
//             } catch (RemoteException e) {
//                 e.printStackTrace();
//             }
//         }
//     }

//     public static void setUsbState(Usb usb, boolean z) {
//         try {
//             mService.writeGpio(convertUsbIndex(usb), z ? 1 : 0);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void setWiegandMode(Wiegand wiegand) {
//         try {
//             mService.setWiegandMode(wiegand == Wiegand.Wiegand_Input ? 0 : 1);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public static synchronized void unregisterGpioListener(int i, GpioListenerCallback gpioListenerCallback) {
//         synchronized (YNHAPI.class) {
//             Log.d(TAG, "unregisterGpioListener: index " + i);
//             Set<GpioListenerCallback> set = mListenerMap.get(Integer.valueOf(i));
//             if (set != null && set.remove(gpioListenerCallback) && set.size() == 0) {
//                 try {
//                     mService.unregisterGpioListener(i, mBinder);
//                 } catch (RemoteException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//     }

//     public static void writeWiegand(Wiegand wiegand, String str) {
//         try {
//             if (wiegand != Wiegand.Wiegand_Output26 && wiegand != Wiegand.Wiegand_Output34) {
//                 throw new IllegalArgumentException("index must be Wiegand_Output26 or Wiegand_Output34");
//             }
//             mService.writeWiegand(wiegand == Wiegand.Wiegand_Output26 ? 0 : 1, Long.parseLong(str));
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void cancelPowerOnOffAlarm() {
//         System_cancelTimingPowerOnOff();
//     }

//     public boolean enableRoot(boolean z) {
//         try {
//             return mService.enableRoot(z);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public void enableWatchdog(boolean z) {
//         try {
//             mService.enableWatchdog(z);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void feedWatchDog() {
//         try {
//             mService.feedWatchdog();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public List<String> get4GModuleNames() {
//         ArrayList arrayList = new ArrayList();
//         try {
//             return mService.get4GModuleNames();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return arrayList;
//         }
//     }

//     public float getApiVersion() {
//         try {
//             return mService.getApiVersion();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return 0.0f;
//         }
//     }

//     public String getBoardModel() {
//         try {
//             return mService.getBoardModel();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return "";
//         }
//     }

//     CameraInfo getCameraInfo(int i) {
//         if (i < 0) {
//             Log.e(TAG, "setCameraInfo camera " + i + " is error ");
//             return null;
//         }
//         int numberOfCameras = Camera.getNumberOfCameras();
//         if (i >= numberOfCameras) {
//             Log.e(TAG, "setCameraInfo camera " + i + " is error , max is " + numberOfCameras);
//             return null;
//         }
//         Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
//         Camera.getCameraInfo(i, cameraInfo);
//         CameraInfo cameraInfo2 = new CameraInfo();
//         cameraInfo2.facing = cameraInfo.facing;
//         StringBuilder sb = new StringBuilder();
//         sb.append("persist.innohi.settings.camera");
//         int i2 = i + 1;
//         sb.append(i2);
//         sb.append("_mirror");
//         cameraInfo2.mirror = "true".equals(PropertyUtil.get(sb.toString(), "")) ? 1 : 0;
//         StringBuilder sb2 = new StringBuilder();
//         sb2.append("persist.innohi.settings.camera");
//         sb2.append(i2);
//         sb2.append("_hdr");
//         cameraInfo2.hdr = "true".equals(PropertyUtil.get(sb2.toString(), "")) ? 1 : 0;
//         try {
//             if (i == 0) {
//                 cameraInfo2.rotation = Integer.parseInt(PropertyUtil.get("persist.innohi.settings.camera_rotation", "0"));
//             } else {
//                 cameraInfo2.rotation = Integer.parseInt(PropertyUtil.get("persist.innohi.settings.camera_ai_rotation", "0"));
//             }
//         } catch (Exception e) {
//             Log.e(TAG, "", e);
//         }
//         return cameraInfo2;
//     }

//     public String getEthernetMAC() {
//         Log.d(TAG, "getEthernetMAC");
//         try {
//             return mService.getEthernetMAC();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return "";
//         }
//     }

//     public ExtendStatusBarVisibility getExtendStatusBarVisibility() {
//         try {
//             int extendStatusBarVisibility = mService.getExtendStatusBarVisibility();
//             for (ExtendStatusBarVisibility extendStatusBarVisibility2 : ExtendStatusBarVisibility.values()) {
//                 if (extendStatusBarVisibility == extendStatusBarVisibility2.ordinal()) {
//                     return extendStatusBarVisibility2;
//                 }
//             }
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//         return ExtendStatusBarVisibility.INVISIBLE;
//     }

//     public List<String> getGPSModuleNames() {
//         ArrayList arrayList = new ArrayList();
//         try {
//             return mService.getGPSModuleNames();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return arrayList;
//         }
//     }

//     public GpioMode getGpioMode(char c, int i) {
//         return GpioMode.UNKNOWN;
//     }

//     public GpioMode getGpioMode(int i) {
//         GpioMode gpioMode = GpioMode.UNKNOWN;
//         try {
//             int gpioMode2 = mService.getGpioMode(i);
//             if (gpioMode2 != -1) {
//                 return gpioMode2 == 1 ? GpioMode.OUTPUT : GpioMode.INPUT;
//             }
//             return gpioMode;
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return gpioMode;
//         }
//     }

//     public GpioState getGpioState(char c, int i) {
//         return GpioState.UNKNOWN;
//     }

//     public GpioState getGpioState(int i) {
//         GpioState gpioState = GpioState.UNKNOWN;
//         try {
//             int readGpio = mService.readGpio(i);
//             if (readGpio != -1) {
//                 return readGpio == 1 ? GpioState.HIGH : GpioState.LOW;
//             }
//             return gpioState;
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return gpioState;
//         }
//     }

//     public String getIMEI() {
//         Log.d(TAG, "getIMEI");
//         try {
//             return mService.getIMEI();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return "";
//         }
//     }

//     public IpConfig getIpConfig() {
//         try {
//             EthernetInfo ip = mService.getIp();
//             if (ip == null) {
//                 Log.d(TAG, "info is null");
//             }
//             if (ip != null) {
//                 return new IpConfig(ip.ip, ip.mask, ip.gateway, ip.dnsList);
//             }
//             return null;
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return null;
//         }
//     }

//     public IpMode getIpMode() {
//         IpMode ipMode = IpMode.UNKNOWN;
//         try {
//             int ipMode2 = mService.getIpMode();
//             if (ipMode2 == 0) {
//                 ipMode = IpMode.STATIC;
//             } else if (ipMode2 == 1) {
//                 ipMode = IpMode.DHCP;
//             }
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//         return ipMode;
//     }

//     public LcdDensity getLcdDensity() {
//         LcdDensity lcdDensity = LcdDensity.DENSITY_UNKNOWN;
//         try {
//             int lcdDensity2 = mService.getLcdDensity();
//             if (lcdDensity2 == 140) {
//                 lcdDensity = LcdDensity.DENSITY_140;
//             } else if (lcdDensity2 == 160) {
//                 lcdDensity = LcdDensity.DENSITY_160;
//             } else if (lcdDensity2 == 240) {
//                 lcdDensity = LcdDensity.DENSITY_240;
//             } else if (lcdDensity2 == 320) {
//                 lcdDensity = LcdDensity.DENSITY_320;
//             }
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//         return lcdDensity;
//     }

//     public int getLightBrightness(int i) {
//         try {
//             return mService.getLightBrightness(i);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return -1;
//         }
//     }

//     public NavigationBarVisibility getNavigationBarVisibility() {
//         NavigationBarVisibility navigationBarVisibility = NavigationBarVisibility.UNKNOWN;
//         try {
//             int navigationBarVisibility2 = mService.getNavigationBarVisibility();
//             if (navigationBarVisibility2 == 0) {
//                 navigationBarVisibility = NavigationBarVisibility.INVISIBLE;
//             } else if (navigationBarVisibility2 == 1) {
//                 navigationBarVisibility = NavigationBarVisibility.VISIBLE;
//             } else if (navigationBarVisibility2 == 2) {
//                 navigationBarVisibility = NavigationBarVisibility.ALWAYS_INVISIBLE;
//             }
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//         return navigationBarVisibility;
//     }

//     public String getProductModel() {
//         try {
//             return mService.getProductModel();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return "";
//         }
//     }

//     public RotationDegree getScreenRotation(ScreenType screenType) {
//         RotationDegree rotationDegree;
//         RotationDegree rotationDegree2 = RotationDegree.DEGREE_UNKNOWN;
//         try {
//             int rotation = mService.getRotation(screenType.mValue);
//             if (rotation == 0) {
//                 rotationDegree = RotationDegree.DEGREE_0;
//             } else if (rotation == 90) {
//                 rotationDegree = RotationDegree.DEGREE_90;
//             } else if (rotation == 180) {
//                 rotationDegree = RotationDegree.DEGREE_180;
//             } else {
//                 if (rotation != 270) {
//                     return rotationDegree2;
//                 }
//                 rotationDegree = RotationDegree.DEGREE_270;
//             }
//             return rotationDegree;
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return rotationDegree2;
//         }
//     }

//     public String getSerialNo() {
//         try {
//             return mService.getSerialNo();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return "";
//         }
//     }

//     public List<StorageInfo> getStorageInfos() {
//         try {
//             return mService.getStorageInfos();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return null;
//         }
//     }

//     public void installApkSilently(String str) {
//         try {
//             mService.installApk(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void installApkSilently(String str, String str2, String str3) {
//         try {
//             mService.installApk2(str, str2, str3);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public boolean isEnableNetworkProvidedTime() {
//         try {
//             return mService.isEnableNetworkProvidedTime();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public boolean isEthernetOpen() {
//         try {
//             return mService.isEthernetOpen();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public boolean isRoot() {
//         try {
//             return mService.isRoot();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public boolean isScreenOn() {
//         try {
//             return mService.isScreenOn();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public synchronized void listenGpio(int i, GpioListenerCallback gpioListenerCallback) {
//         registerGpioListener(i, gpioListenerCallback);
//     }

//     public void otaUpdate(String str) {
//         try {
//             mService.otaUpdate(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public long readWiegand() {
//         try {
//             return mService.readWiegand();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return -1L;
//         }
//     }

//     public void readWiegandAsyn(WiegandCallback wiegandCallback) {
//         try {
//             mService.readWiegandAsync(new BnWiegandCallback(wiegandCallback));
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             wiegandCallback.onFailure();
//         }
//     }

//     public WiegandMode readWiegandMode() {
//         WiegandMode wiegandMode = WiegandMode.UNKNOWN;
//         try {
//             int readWiegandMode = mService.readWiegandMode();
//             wiegandMode = readWiegandMode == 0 ? WiegandMode.INPUT : readWiegandMode == 1 ? WiegandMode.OUTPUT : WiegandMode.UNKNOWN;
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//         return wiegandMode;
//     }

//     public void reboot() {
//         try {
//             mService.reboot();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void set4GModule(String str) {
//         try {
//             mService.set4GModule(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setAppKeepLive(String str, int i) {
//         try {
//             mService.setAppKeepLive(str, i);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setBootAnimation(String str) {
//         try {
//             mService.setBootAnimation(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setBootLaunchApk(String str, boolean z) {
//         try {
//             mService.setBootLaunch(str, z);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setBootLogo(String str) {
//         try {
//             mService.setBootLogo(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setCameraInfo(int i, CameraInfo cameraInfo) {
//         if (i < 0) {
//             Log.e(TAG, "setCameraInfo camera " + i + " is error ");
//             return;
//         }
//         int numberOfCameras = Camera.getNumberOfCameras();
//         if (i >= numberOfCameras) {
//             Log.e(TAG, "setCameraInfo camera " + i + " is error , max is " + numberOfCameras);
//             return;
//         }
//         StringBuilder sb = new StringBuilder();
//         sb.append("persist.innohi.settings.camera");
//         int i2 = i + 1;
//         sb.append(i2);
//         sb.append("_facing");
//         PropertyUtil.set(sb.toString(), cameraInfo.facing == 1 ? "true" : "false");
//         PropertyUtil.set("persist.innohi.settings.camera" + i2 + "_mirror", cameraInfo.mirror == 1 ? "true" : "false");
//         PropertyUtil.set("persist.innohi.settings.camera" + i2 + "_hdr", cameraInfo.hdr != 1 ? "false" : "true");
//         try {
//             ShellCmdUtil.executeCmd("killall cameraserver");
//             ShellCmdUtil.executeCmd("killall android.hardware.camera.provider@2.4-service");
//             ShellCmdUtil.executeCmd("killall android.hardware.camera.provider@2.4-external-service");
//         } catch (Exception e) {
//             Log.e(TAG, "", e);
//         }
//     }

//     public void setDhcpIp() {
//         try {
//             mService.setDhcpIp();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setEnableNetworkProvidedTime(boolean z) {
//         try {
//             mService.setEnableNetworkProvidedTime(z);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setEthernetMAC(String str) {
//         try {
//             mService.setEthernetMAC(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setEthernetState(boolean z) {
//         try {
//             mService.setEthernetState(z);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setExtendStatusBarVisibility(ExtendStatusBarVisibility extendStatusBarVisibility) {
//         try {
//             mService.setExtendStatusBarVisibility(extendStatusBarVisibility.ordinal());
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setForegroundAppKeepLive(String str, int i) {
//         try {
//             mService.setForegroundAppKeepLive(str, i);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setGPSModule(String str, String str2, String str3) {
//         try {
//             mService.setGPSModule(str, str2, str3);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public boolean setGpioMode(char c, int i, GpioMode gpioMode) {
//         return false;
//     }

//     public boolean setGpioMode(int i, GpioMode gpioMode) {
//         if (gpioMode == GpioMode.UNKNOWN) {
//             return false;
//         }
//         try {
//             return mService.setGpioMode(i, gpioMode == GpioMode.INPUT ? 0 : 1);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public boolean setGpioState(char c, int i, GpioState gpioState) {
//         return false;
//     }

//     public boolean setGpioState(int i, GpioState gpioState) {
//         if (gpioState == GpioState.UNKNOWN) {
//             return false;
//         }
//         try {
//             return mService.writeGpio(i, gpioState == GpioState.HIGH ? 1 : 0);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public void setIMEI(String str) {
//         try {
//             mService.setIMEI(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setLcdDensity(LcdDensity lcdDensity) {
//         try {
//             if (lcdDensity != LcdDensity.DENSITY_UNKNOWN) {
//                 mService.setLcdDensity(lcdDensity.mDensity);
//             }
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setLightBrightness(int i, int i2) {
//         try {
//             mService.setLightBrightness(i, i2);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setNavigationBarVisibility(NavigationBarVisibility navigationBarVisibility) {
//         try {
//             mService.setNavigationBarVisibility(navigationBarVisibility.ordinal());
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setPowerOnOffAlarm(int[] iArr, int[] iArr2) {
//         System_setTimingPowerOnOff(iArr, iArr2);
//     }

//     public void setPowerOnOffAlarmCycle(int i, int[] iArr, int[] iArr2, int[] iArr3) {
//         try {
//             mService.setPowerOnOffAlarmCycle(i, iArr, iArr2, iArr3);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setProductModel(String str) {
//         try {
//             mService.setProductModel(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setScreenOnOff(boolean z) {
//         try {
//             mService.setScreenOnOff(z);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setScreenRotation(ScreenType screenType, RotationDegree rotationDegree) {
//         try {
//             mService.setRotation(screenType.mValue, rotationDegree.mDegree);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setSerialNo(String str) {
//         try {
//             mService.setSerialNo(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setStaticIp(IpConfig ipConfig) {
//         try {
//             mService.setStaticIp(new EthernetInfo(ipConfig.ip, ipConfig.mask, ipConfig.gateway, ipConfig.dnsList));
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void setSystemTime(long j) {
//         try {
//             mService.setSystemTime(j);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void shutdown() {
//         try {
//             mService.shutdown();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void sleep() {
//         try {
//             mService.systemSleep();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void uninstallApkSilently(String str) {
//         try {
//             mService.uninstallApk(str);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public synchronized void unlistenGpio(int i, GpioListenerCallback gpioListenerCallback) {
//         unregisterGpioListener(i, gpioListenerCallback);
//     }

//     public void wakeUp() {
//         try {
//             mService.systemWakeup();
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void writeWiegand(WiegandFormat wiegandFormat, long j) {
//         try {
//             mService.writeWiegand(wiegandFormat == WiegandFormat.FORMAT_26 ? 0 : 1, j);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }

//     public void writeWiegandMode(WiegandMode wiegandMode) {
//         try {
//             mService.setWiegandMode(wiegandMode == WiegandMode.INPUT ? 0 : 1);
//         } catch (RemoteException e) {
//             e.printStackTrace();
//         }
//     }
// }
