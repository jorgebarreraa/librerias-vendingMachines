package android.os.tniuds;

// import android.content.Context;
// import android.os.Handler;
// import android.os.tniuds.peripherals.Gpio;
// import android.os.tniuds.peripherals.Mcu;
// import android.os.tniuds.utils.PowerUtil;
// import android.os.tniuds.utils.SystemUtil;
// import android.os.tniuds.utils.common.AdbUtil;
// import android.os.tniuds.utils.common.AppUtil;
// import android.os.tniuds.utils.common.NetworkUtil;
// import android.os.tniuds.utils.common.StorageUtil;
// import android.os.tniuds.utils.common.SysUtil;
// import java.io.File;

// /* loaded from: classes.dex */
// public class TniudsUtils {
//     private static String TAG = "TniudsUtils";
//     private Gpio gpio;

//     public TniudsUtils(String str) {
//         this.gpio = new Gpio(str, true);
//     }

//     public static String execSuCmd(String str) {
//         return AdbUtil.execSuCmd(str);
//     }

//     public static boolean feedWatchDog() {
//         return Mcu.feedWatchDog();
//     }

//     public static String getApiVersion() {
//         return SystemUtil.getApiVersion();
//     }

//     public static String getBoardType() {
//         return SystemUtil.getBoardType();
//     }

//     public static int getDisplayRotation(Context context) {
//         return SystemUtil.getDisplayRotation(context);
//     }

//     public static int getDisplayX(Context context) {
//         return SystemUtil.getDisplayX(context);
//     }

//     public static int getDisplayY(Context context) {
//         return SystemUtil.getDisplayY(context);
//     }

//     public static int getEdpBrightness() {
//         return SystemUtil.getEdpBrightness();
//     }

//     public static String getIEMI_Mobile(Context context) {
//         return NetworkUtil.getIEMI_Mobile(context);
//     }

//     public static String getInternalEmmcSize() {
//         return StorageUtil.getInternalEmmcSize();
//     }

//     public static String getIpEthernet(Context context) {
//         return NetworkUtil.getIpEthernet(context);
//     }

//     public static String getIpMobile(Context context) {
//         return NetworkUtil.getIpMobile(context);
//     }

//     public static String getIpWifi(Context context) {
//         return NetworkUtil.getIpWifi(context);
//     }

//     public static boolean getKernelLog(String str) {
//         return SysUtil.getKernelLog(str);
//     }

//     public static int getLvdsBrightness() {
//         return SystemUtil.getLvdsBrightness();
//     }

//     public static String getMacEthernet() {
//         return NetworkUtil.getMacEthernet();
//     }

//     public static String getMacWifi() {
//         return NetworkUtil.getMacWifi();
//     }

//     public static String[] getNameGpios() {
//         return Gpio.getNameGpios();
//     }

//     public static int getNetworkAvailableType(Context context) {
//         return NetworkUtil.getNetworkAvailableType(context);
//     }

//     public static int[] getRtcHardware() {
//         return Mcu.getRtcHardware();
//     }

//     public static String getRunningMemory() {
//         return StorageUtil.getRunningMemory();
//     }

//     public static boolean getSystemLogcat(String str) {
//         return SysUtil.getSystemLogcat(str);
//     }

//     public static int[] getSystemOffTime() {
//         return Mcu.getSystemOffTime();
//     }

//     public static int[] getSystemOnTime() {
//         return Mcu.getSystemOnTime();
//     }

//     public static String getSystemPropValue(Context context, String str) {
//         return SysUtil.getSystemPropValue(context, str);
//     }

//     public static boolean installAppCover(Context context, File file) {
//         return AppUtil.installAppCover(context, file);
//     }

//     public static boolean installAppSilent(Context context, String str) {
//         return AppUtil.installAppSilent(context, str);
//     }

//     public static boolean installAppSilentStart(Context context, String str, String str2) {
//         return AppUtil.installAppSilentStart(context, str, str2);
//     }

//     public static boolean isAppInstalled(Context context, String str) {
//         return AppUtil.isAppInstalled(context, str);
//     }

//     public static boolean isAppRunningFront(Context context, String str) {
//         return AppUtil.isAppRunningFront(context, str);
//     }

//     public static boolean isEthernetConnected(Context context) {
//         return NetworkUtil.isEthernetConnected(context);
//     }

//     public static boolean isHdmiConnected() {
//         return SystemUtil.isHdmiConnected();
//     }

//     public static boolean isMobileConnected(Context context) {
//         return NetworkUtil.isMobileConnected(context);
//     }

//     public static boolean isNetworkConnected(Context context) {
//         return NetworkUtil.isNetworkConnected(context);
//     }

//     public static boolean isRunningBackground(Context context, String str) {
//         return AppUtil.isRunningBackground(context, str);
//     }

//     public static boolean isWifiConnected(Context context) {
//         return NetworkUtil.isWifiConnected(context);
//     }

//     public static boolean pingTest() {
//         return NetworkUtil.pingTest();
//     }

//     public static boolean screenShots(Context context, String str) {
//         return SystemUtil.screenShots(context, str);
//     }

//     public static boolean setDisplayRotation(Context context, int i) {
//         return SystemUtil.setDisplayRotation(context, i);
//     }

//     public static boolean setEdpBrightness(int i) {
//         return SystemUtil.setEdpBrightness(i);
//     }

//     public static boolean setEdpPowerEnable(boolean z) {
//         return PowerUtil.setEdpPowerEnable(z);
//     }

//     public static void setEthStaticIPAddress(Context context, String str, String str2, String str3, String str4) {
//         NetworkUtil.setEthStaticIPAddress(context, str, str2, str3, str4);
//     }

//     public static boolean setEthernetPowerEnalble(boolean z) {
//         return PowerUtil.setEthernetPowerEnalble(z);
//     }

//     public static boolean setEthernet_enable(Context context, boolean z) {
//         return NetworkUtil.setEthernet_enable(context, z);
//     }

//     public static boolean setHdmiEnable(boolean z) {
//         return SystemUtil.setHdmiEnable(z);
//     }

//     public static boolean setHubPowerEnable(int i, boolean z) {
//         return PowerUtil.setHubPowerEnable(i, z);
//     }

//     public static boolean setLvdsBrightness(int i) {
//         return SystemUtil.setLvdsBrightness(i);
//     }

//     public static boolean setLvdsPowerEnable(boolean z) {
//         return PowerUtil.setLvdsPowerEnable(z);
//     }

//     public static boolean setMiniPciEPowerEnable(boolean z) {
//         return PowerUtil.setMiniPciEPowerEnable(z);
//     }

//     public static boolean setMobileEnabled(Context context, boolean z) {
//         return NetworkUtil.setMobileEnabled(context, z);
//     }

//     public static boolean setNavigationBarEnable(Context context, boolean z) {
//         return SystemUtil.setNavigationBarEnable(context, z);
//     }

//     public static boolean setRtcHardware(long j) {
//         return Mcu.setRtcHardware(j);
//     }

//     public static boolean setRtcHardware(int[] iArr) {
//         return Mcu.setRtcHardware(iArr);
//     }

//     public static boolean setRtcHardware_from_systimeTime() {
//         return Mcu.setRtcHardware_from_systimeTime();
//     }

//     public static boolean setSpkerPowerEnable(boolean z) {
//         return PowerUtil.setSpkerPowerEnable(z);
//     }

//     public static boolean setSystemOffTime(int[] iArr) {
//         return Mcu.setSystemOffTime(iArr);
//     }

//     public static boolean setSystemOnTime(int[] iArr) {
//         return Mcu.setSystemOnTime(iArr);
//     }

//     public static boolean setSystemReboot(Context context) {
//         return SysUtil.setSystemReboot_soft(context);
//     }

//     public static boolean setSystemReboot_hard() {
//         return Mcu.setSystemReboot_hard();
//     }

//     public static boolean setSystemShutDown() {
//         return Mcu.setSystemShutDown();
//     }

//     public static boolean setUsbHostPowerEnable(boolean z) {
//         return PowerUtil.setUsbHostPowerEnable(z);
//     }

//     public static boolean setUsbOtgSwitch(boolean z) {
//         return PowerUtil.setUsbOtgSwitch(z);
//     }

//     public static boolean setWatchDogEnable(boolean z) {
//         return Mcu.setWatchDogEnable(z);
//     }

//     public static boolean setWifiPowerEnable(boolean z) {
//         return PowerUtil.setWifiPowerEnable(z);
//     }

//     public static boolean updateSystemAndroid(Context context, String str) {
//         return SystemUtil.updateSystemAndroid(context, str);
//     }

//     public void freeGpioIrq() {
//         this.gpio.freeGpioIrq();
//     }

//     public int getGpioMode() {
//         return this.gpio.getGpioMode();
//     }

//     public int getGpioValue() {
//         return this.gpio.getGpioValue();
//     }

//     public boolean setGpioIrqBoth(Handler handler) {
//         return this.gpio.setGpioIrqBoth(handler);
//     }

//     public boolean setGpioIrqFalling(Handler handler) {
//         return this.gpio.setGpioIrqFalling(handler);
//     }

//     public boolean setGpioIrqRising(Handler handler) {
//         return this.gpio.setGpioIrqRising(handler);
//     }

//     public boolean setGpioModeInput() {
//         return this.gpio.setGpioModeInput();
//     }

//     public boolean setGpioModeOutputHigh() {
//         return this.gpio.setGpioModeOutputHigh();
//     }

//     public boolean setGpioModeOutputLow() {
//         return this.gpio.setGpioModeOutputLow();
//     }
// }
