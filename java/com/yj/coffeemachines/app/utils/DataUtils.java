package com.yj.coffeemachines.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes.dex */
public class DataUtils {
    public static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static String currentTime() {
        return DEFAULT_FORMAT.format(new Date());
    }

    public static String getFIleNameNowtimeyyymmddhhmmss() {
        Long valueOf = Long.valueOf(new Date(System.currentTimeMillis()).getTime());
        return (valueOf == null ? "" : new SimpleDateFormat("yyyyMMddHHmmss").format(valueOf)) + "_" + System.currentTimeMillis();
    }

    public static String getNowtimeYY_MM_DD() {
        Long valueOf = Long.valueOf(new Date(System.currentTimeMillis()).getTime());
        return valueOf == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(valueOf);
    }

    public static String getNowtimehhmmss() {
        Long valueOf = Long.valueOf(new Date(System.currentTimeMillis()).getTime());
        return valueOf == null ? "" : new SimpleDateFormat("HH:mm:ss").format(valueOf);
    }

    public static Long getNowtimehhmmss_long() {
        Long valueOf = Long.valueOf(new Date(System.currentTimeMillis()).getTime());
        return stringHHMMSSDataLong(valueOf == null ? "" : new SimpleDateFormat("HH:mm:ss").format(valueOf));
    }

    public static String getNowtimeyyymmddhhmmss() {
        Long valueOf = Long.valueOf(new Date(System.currentTimeMillis()).getTime());
        return valueOf == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(valueOf);
    }

    public static Long getOneDayStartTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return Long.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(simpleDateFormat.format(date) + " 00:00:00").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return Long.valueOf(System.currentTimeMillis());
        }
    }

    public static Long getTimeAdd(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(12, i);
        return Long.valueOf(calendar.getTime().getTime());
    }

    public static String getTimeYYMMDD(Long l) {
        return l == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(l);
    }

    public static String getTimeyyymmddhhmmss(Long l) {
        return l == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(l);
    }

    public static String getYestodaytimeYYYYMMDD() {
        Long valueOf = Long.valueOf(new Date(System.currentTimeMillis()).getTime() - 86400000);
        return valueOf == null ? "" : new SimpleDateFormat("yyyyMMdd").format(valueOf);
    }

    public static Long stringHHMMSSDataLong(String str) {
        try {
            return Long.valueOf(new SimpleDateFormat("HH:mm:ss").parse(str).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Long stringyyymmddhhmmssDataLong(String str) {
        try {
            return Long.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
