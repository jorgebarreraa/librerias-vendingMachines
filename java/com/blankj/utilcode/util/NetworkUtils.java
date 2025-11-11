package com.blankj.utilcode.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.blankj.utilcode.util.Utils;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* loaded from: classes.dex */
public final class NetworkUtils {
    private static final long SCAN_PERIOD_MILLIS = 3000;
    private static final Set<Utils.Consumer<WifiScanResults>> SCAN_RESULT_CONSUMERS = new CopyOnWriteArraySet();
    private static WifiScanResults sPreWifiScanResults;
    private static Timer sScanWifiTimer;

    /* loaded from: classes.dex */
    public static final class NetworkChangedReceiver extends BroadcastReceiver {
        private Set<OnNetworkStatusChangedListener> mListeners = new HashSet();
        private NetworkType mType;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class LazyHolder {
            private static final NetworkChangedReceiver INSTANCE = new NetworkChangedReceiver();

            private LazyHolder() {
            }
        }

        static /* synthetic */ NetworkChangedReceiver access$000() {
            return getInstance();
        }

        private static NetworkChangedReceiver getInstance() {
            return LazyHolder.INSTANCE;
        }

        boolean isRegistered(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener == null) {
                return false;
            }
            return this.mListeners.contains(onNetworkStatusChangedListener);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                UtilsBridge.runOnUiThreadDelayed(new Runnable() { // from class: com.blankj.utilcode.util.NetworkUtils.NetworkChangedReceiver.3
                    @Override // java.lang.Runnable
                    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
                    public void run() {
                        NetworkType networkType = NetworkUtils.getNetworkType();
                        if (NetworkChangedReceiver.this.mType == networkType) {
                            return;
                        }
                        NetworkChangedReceiver.this.mType = networkType;
                        if (networkType == NetworkType.NETWORK_NO) {
                            Iterator it2 = NetworkChangedReceiver.this.mListeners.iterator();
                            while (it2.hasNext()) {
                                ((OnNetworkStatusChangedListener) it2.next()).onDisconnected();
                            }
                        } else {
                            Iterator it3 = NetworkChangedReceiver.this.mListeners.iterator();
                            while (it3.hasNext()) {
                                ((OnNetworkStatusChangedListener) it3.next()).onConnected(networkType);
                            }
                        }
                    }
                }, 1000L);
            }
        }

        @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
        void registerListener(final OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener == null) {
                return;
            }
            UtilsBridge.runOnUiThread(new Runnable() { // from class: com.blankj.utilcode.util.NetworkUtils.NetworkChangedReceiver.1
                @Override // java.lang.Runnable
                @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
                public void run() {
                    int size = NetworkChangedReceiver.this.mListeners.size();
                    NetworkChangedReceiver.this.mListeners.add(onNetworkStatusChangedListener);
                    if (size == 0 && NetworkChangedReceiver.this.mListeners.size() == 1) {
                        NetworkChangedReceiver.this.mType = NetworkUtils.getNetworkType();
                        Utils.getApp().registerReceiver(NetworkChangedReceiver.access$000(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                    }
                }
            });
        }

        void unregisterListener(final OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener == null) {
                return;
            }
            UtilsBridge.runOnUiThread(new Runnable() { // from class: com.blankj.utilcode.util.NetworkUtils.NetworkChangedReceiver.2
                @Override // java.lang.Runnable
                public void run() {
                    int size = NetworkChangedReceiver.this.mListeners.size();
                    NetworkChangedReceiver.this.mListeners.remove(onNetworkStatusChangedListener);
                    if (size == 1 && NetworkChangedReceiver.this.mListeners.size() == 0) {
                        Utils.getApp().unregisterReceiver(NetworkChangedReceiver.access$000());
                    }
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public enum NetworkType {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_5G,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    /* loaded from: classes.dex */
    public interface OnNetworkStatusChangedListener {
        void onConnected(NetworkType networkType);

        void onDisconnected();
    }

    /* loaded from: classes.dex */
    public static final class WifiScanResults {
        private List<ScanResult> allResults = new ArrayList();
        private List<ScanResult> filterResults = new ArrayList();

        private static List<ScanResult> filterScanResult(List<ScanResult> list) {
            ScanResult scanResult;
            if (list == null || list.isEmpty()) {
                return new ArrayList();
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
            for (ScanResult scanResult2 : list) {
                if (!TextUtils.isEmpty(scanResult2.SSID) && ((scanResult = (ScanResult) linkedHashMap.get(scanResult2.SSID)) == null || scanResult.level < scanResult2.level)) {
                    linkedHashMap.put(scanResult2.SSID, scanResult2);
                }
            }
            return new ArrayList(linkedHashMap.values());
        }

        public List<ScanResult> getAllResults() {
            return this.allResults;
        }

        public List<ScanResult> getFilterResults() {
            return this.filterResults;
        }

        public void setAllResults(List<ScanResult> list) {
            this.allResults = list;
            this.filterResults = filterScanResult(list);
        }
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"})
    public static void addOnWifiChangedConsumer(final Utils.Consumer<WifiScanResults> consumer) {
        if (consumer == null) {
            return;
        }
        UtilsBridge.runOnUiThread(new Runnable() { // from class: com.blankj.utilcode.util.NetworkUtils.7
            @Override // java.lang.Runnable
            public void run() {
                if (NetworkUtils.SCAN_RESULT_CONSUMERS.isEmpty()) {
                    NetworkUtils.SCAN_RESULT_CONSUMERS.add(Utils.Consumer.this);
                    NetworkUtils.startScanWifi();
                } else {
                    Utils.Consumer.this.accept(NetworkUtils.sPreWifiScanResults);
                    NetworkUtils.SCAN_RESULT_CONSUMERS.add(Utils.Consumer.this);
                }
            }
        });
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static String getBroadcastIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp() && !nextElement.isLoopback()) {
                    List<InterfaceAddress> interfaceAddresses = nextElement.getInterfaceAddresses();
                    int size = interfaceAddresses.size();
                    for (int i = 0; i < size; i++) {
                        InetAddress broadcast = interfaceAddresses.get(i).getBroadcast();
                        if (broadcast != null) {
                            return broadcast.getHostAddress();
                        }
                    }
                }
            }
            return "";
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String getDomainAddress(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<String> getDomainAddressAsync(final String str, @NonNull Utils.Consumer<String> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<String>(consumer) { // from class: com.blankj.utilcode.util.NetworkUtils.6
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            @RequiresPermission("android.permission.INTERNET")
            public String doInBackground() {
                return NetworkUtils.getDomainAddress(str);
            }
        });
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getGatewayByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().gateway);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String getIPAddress(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            LinkedList linkedList = new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp() && !nextElement.isLoopback()) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        linkedList.addFirst(inetAddresses.nextElement());
                    }
                }
            }
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                InetAddress inetAddress = (InetAddress) it2.next();
                if (!inetAddress.isLoopbackAddress()) {
                    String hostAddress = inetAddress.getHostAddress();
                    boolean z2 = hostAddress.indexOf(58) < 0;
                    if (z) {
                        if (z2) {
                            return hostAddress;
                        }
                    } else if (!z2) {
                        int indexOf = hostAddress.indexOf(37);
                        return indexOf < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, indexOf).toUpperCase();
                    }
                }
            }
            return "";
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Utils.Task<String> getIPAddressAsync(final boolean z, @NonNull Utils.Consumer<String> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<String>(consumer) { // from class: com.blankj.utilcode.util.NetworkUtils.5
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            @RequiresPermission("android.permission.INTERNET")
            public String doInBackground() {
                return NetworkUtils.getIPAddress(z);
            }
        });
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getIpAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().ipAddress);
    }

    public static boolean getMobileDataEnabled() {
        TelephonyManager telephonyManager;
        try {
            telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (telephonyManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return telephonyManager.isDataEnabled();
        }
        Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
        if (declaredMethod != null) {
            return ((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])).booleanValue();
        }
        return false;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getNetMaskByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().netmask);
    }

    public static String getNetworkOperatorName() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        return telephonyManager == null ? "" : telephonyManager.getNetworkOperatorName();
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static NetworkType getNetworkType() {
        if (isEthernet()) {
            return NetworkType.NETWORK_ETHERNET;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return NetworkType.NETWORK_NO;
        }
        if (activeNetworkInfo.getType() == 1) {
            return NetworkType.NETWORK_WIFI;
        }
        if (activeNetworkInfo.getType() != 0) {
            return NetworkType.NETWORK_UNKNOWN;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return NetworkType.NETWORK_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return NetworkType.NETWORK_3G;
            case 13:
            case 18:
                return NetworkType.NETWORK_4G;
            case 19:
            default:
                String subtypeName = activeNetworkInfo.getSubtypeName();
                return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? NetworkType.NETWORK_3G : NetworkType.NETWORK_UNKNOWN;
            case 20:
                return NetworkType.NETWORK_5G;
        }
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getSSID() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi");
        if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return "";
        }
        String ssid = connectionInfo.getSSID();
        return TextUtils.isEmpty(ssid) ? "" : (ssid.length() > 2 && ssid.charAt(0) == '\"' && ssid.charAt(ssid.length() - 1) == '\"') ? ssid.substring(1, ssid.length() - 1) : ssid;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getServerAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().serverAddress);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"})
    public static WifiScanResults getWifiScanResult() {
        List<ScanResult> scanResults;
        WifiScanResults wifiScanResults = new WifiScanResults();
        if (getWifiEnabled() && (scanResults = ((WifiManager) Utils.getApp().getSystemService("wifi")).getScanResults()) != null) {
            wifiScanResults.setAllResults(scanResults);
        }
        return wifiScanResults;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean is4G() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getSubtype() == 13;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean is5G() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getSubtype() == 20;
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailable() {
        return isAvailableByDns() || isAvailableByPing(null);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<Boolean> isAvailableAsync(@NonNull Utils.Consumer<Boolean> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) { // from class: com.blankj.utilcode.util.NetworkUtils.1
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            @RequiresPermission("android.permission.INTERNET")
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isAvailable());
            }
        });
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByDns() {
        return isAvailableByDns("");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByDns(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "www.baidu.com";
        }
        try {
            return InetAddress.getByName(str) != null;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task isAvailableByDnsAsync(final String str, @NonNull Utils.Consumer<Boolean> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) { // from class: com.blankj.utilcode.util.NetworkUtils.3
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            @RequiresPermission("android.permission.INTERNET")
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isAvailableByDns(str));
            }
        });
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void isAvailableByDnsAsync(Utils.Consumer<Boolean> consumer) {
        isAvailableByDnsAsync("", consumer);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByPing() {
        return isAvailableByPing("");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByPing(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "223.5.5.5";
        }
        return ShellUtils.execCmd(String.format("ping -c 1 %s", str), false).result == 0;
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<Boolean> isAvailableByPingAsync(final String str, @NonNull Utils.Consumer<Boolean> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) { // from class: com.blankj.utilcode.util.NetworkUtils.2
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            @RequiresPermission("android.permission.INTERNET")
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isAvailableByPing(str));
            }
        });
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void isAvailableByPingAsync(Utils.Consumer<Boolean> consumer) {
        isAvailableByPingAsync("", consumer);
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isConnected() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static boolean isEthernet() {
        NetworkInfo networkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(9)) == null || (state = networkInfo.getState()) == null) {
            return false;
        }
        return state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isMobileData() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 0;
    }

    public static boolean isRegisteredNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        return NetworkChangedReceiver.access$000().isRegistered(onNetworkStatusChangedListener);
    }

    private static boolean isSameScanResultContent(ScanResult scanResult, ScanResult scanResult2) {
        return scanResult != null && scanResult2 != null && UtilsBridge.equals(scanResult.BSSID, scanResult2.BSSID) && UtilsBridge.equals(scanResult.SSID, scanResult2.SSID) && UtilsBridge.equals(scanResult.capabilities, scanResult2.capabilities) && scanResult.level == scanResult2.level;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSameScanResults(List<ScanResult> list, List<ScanResult> list2) {
        if (list == null && list2 == null) {
            return true;
        }
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!isSameScanResultContent(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static boolean isWifiAvailable() {
        return getWifiEnabled() && isAvailable();
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static Utils.Task<Boolean> isWifiAvailableAsync(@NonNull Utils.Consumer<Boolean> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) { // from class: com.blankj.utilcode.util.NetworkUtils.4
            @Override // com.blankj.utilcode.util.ThreadUtils.Task
            @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isWifiAvailable());
            }
        });
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isWifiConnected() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 1) ? false : true;
    }

    public static void openWirelessSettings() {
        Utils.getApp().startActivity(new Intent("android.settings.WIRELESS_SETTINGS").setFlags(ClientDefaults.MAX_MSG_SIZE));
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static void registerNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        NetworkChangedReceiver.access$000().registerListener(onNetworkStatusChangedListener);
    }

    public static void removeOnWifiChangedConsumer(final Utils.Consumer<WifiScanResults> consumer) {
        if (consumer == null) {
            return;
        }
        UtilsBridge.runOnUiThread(new Runnable() { // from class: com.blankj.utilcode.util.NetworkUtils.9
            @Override // java.lang.Runnable
            public void run() {
                NetworkUtils.SCAN_RESULT_CONSUMERS.remove(Utils.Consumer.this);
                if (NetworkUtils.SCAN_RESULT_CONSUMERS.isEmpty()) {
                    NetworkUtils.stopScanWifi();
                }
            }
        });
    }

    @RequiresPermission("android.permission.CHANGE_WIFI_STATE")
    public static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null || z == wifiManager.isWifiEnabled()) {
            return;
        }
        wifiManager.setWifiEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startScanWifi() {
        sPreWifiScanResults = new WifiScanResults();
        sScanWifiTimer = new Timer();
        sScanWifiTimer.schedule(new TimerTask() { // from class: com.blankj.utilcode.util.NetworkUtils.8
            @Override // java.util.TimerTask, java.lang.Runnable
            @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"})
            public void run() {
                NetworkUtils.startScanWifiIfEnabled();
                WifiScanResults wifiScanResult = NetworkUtils.getWifiScanResult();
                if (NetworkUtils.isSameScanResults(NetworkUtils.sPreWifiScanResults.allResults, wifiScanResult.allResults)) {
                    return;
                }
                WifiScanResults unused = NetworkUtils.sPreWifiScanResults = wifiScanResult;
                UtilsBridge.runOnUiThread(new Runnable() { // from class: com.blankj.utilcode.util.NetworkUtils.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it2 = NetworkUtils.SCAN_RESULT_CONSUMERS.iterator();
                        while (it2.hasNext()) {
                            ((Utils.Consumer) it2.next()).accept(NetworkUtils.sPreWifiScanResults);
                        }
                    }
                });
            }
        }, 0L, SCAN_PERIOD_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE"})
    public static void startScanWifiIfEnabled() {
        if (getWifiEnabled()) {
            ((WifiManager) Utils.getApp().getSystemService("wifi")).startScan();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void stopScanWifi() {
        Timer timer = sScanWifiTimer;
        if (timer != null) {
            timer.cancel();
            sScanWifiTimer = null;
        }
    }

    public static void unregisterNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        NetworkChangedReceiver.access$000().unregisterListener(onNetworkStatusChangedListener);
    }
}
