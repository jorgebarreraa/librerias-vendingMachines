package com.yj.coffeemachines.app.utils;

import android.net.TrafficStats;
import android.os.Process;
import com.alibaba.fastjson.JSONObject;
import com.yj.coffeemachines.helper.Tools;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes.dex */
public class TrafficUtils {
    private static final long BYTES_PER_MB = 1048576;
    private static final long COUNTER_MAX = 9223372026117357567L;
    private static final int DECIMAL_PLACES = 3;
    private static final String KEY_CURRENT_DATE = "currentDate";
    private static final String KEY_DAILY_BASELINE = "dailyBaseline";
    private static final String KEY_HISTORY_TOTAL = "historyTotal";
    private static final String KEY_STARTUP_TRAFFIC = "startupTraffic";
    private static final Object LOCK = new Object();

    private static long calculateTotalTraffic() {
        long j;
        synchronized (LOCK) {
            try {
                try {
                    long currentTrafficBytes = getCurrentTrafficBytes();
                    long j2 = KvUtil.getInstance().getLong(KEY_STARTUP_TRAFFIC, 0L);
                    long j3 = KvUtil.getInstance().getLong(KEY_HISTORY_TOTAL, 0L);
                    if (currentTrafficBytes < j2) {
                        j3 += j2;
                        if (j2 > COUNTER_MAX) {
                            j3 += LongCompanionObject.MAX_VALUE - j2;
                        }
                        KvUtil.getInstance().putLong(KEY_HISTORY_TOTAL, j3);
                    }
                    KvUtil.getInstance().putLong(KEY_STARTUP_TRAFFIC, currentTrafficBytes);
                    j = j3 + currentTrafficBytes;
                } catch (Exception e) {
                    logError("流量计算异常", e);
                    return -1L;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return j;
    }

    private static String formatMB(double d) {
        return String.format("%.3fMB", Double.valueOf(d));
    }

    private static long getCurrentTrafficBytes() {
        int myUid = Process.myUid();
        return Math.max(TrafficStats.getUidRxBytes(myUid), 0L) + Math.max(TrafficStats.getUidTxBytes(myUid), 0L);
    }

    public static double getTodayTraffic() {
        try {
            return (calculateTotalTraffic() - KvUtil.getInstance().getLong(KEY_DAILY_BASELINE, 0L)) / 1048576.0d;
        } catch (Exception e) {
            logError("获取今日流量异常", e);
            return -1.0d;
        }
    }

    public static String getTrafficData() {
        JSONObject jSONObject = new JSONObject();
        try {
            updateDailyBaseline();
            jSONObject.put("timestamp", (Object) Long.valueOf(System.currentTimeMillis()));
            jSONObject.put("today", (Object) formatMB(getTodayTraffic()));
        } catch (Exception e) {
            logError("生成流量数据异常", e);
        }
        return jSONObject.toString();
    }

    private static void logError(String str, Exception exc) {
        Tools.upLocalLog(str + ": " + exc.toString());
        exc.printStackTrace();
    }

    private static void updateDailyBaseline() {
        synchronized (LOCK) {
            try {
                String format = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                KvUtil kvUtil = KvUtil.getInstance();
                if (!format.equals(kvUtil.getString(KEY_CURRENT_DATE, ""))) {
                    kvUtil.putLong(KEY_DAILY_BASELINE, calculateTotalTraffic());
                    kvUtil.putString(KEY_CURRENT_DATE, format);
                }
            } catch (Exception e) {
                logError("更新每日基线异常", e);
            }
        }
    }
}
