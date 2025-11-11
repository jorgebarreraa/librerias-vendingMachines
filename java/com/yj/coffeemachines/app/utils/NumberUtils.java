package com.yj.coffeemachines.app.utils;

import java.text.DecimalFormat;

/* loaded from: classes.dex */
public class NumberUtils {
    public static double five(double d) {
        return stringToDouble(new DecimalFormat("##0.0000").format(d));
    }

    public static String fiveString(double d) {
        return new DecimalFormat("#,##0.00000").format(d);
    }

    public static double four(double d) {
        return stringToDouble(new DecimalFormat("##0.0000").format(d));
    }

    public static String fourString(double d) {
        return new DecimalFormat("#,##0.0000").format(d);
    }

    public static double one(double d) {
        return stringToDouble(new DecimalFormat("##0.0").format(d));
    }

    public static String oneString(double d) {
        return new DecimalFormat("#,##0.0").format(d);
    }

    public static double six(double d) {
        return stringToDouble(new DecimalFormat("##0.0000").format(d));
    }

    public static String sixString(double d) {
        return new DecimalFormat("#,##0.000000").format(d);
    }

    private static double stringToDouble(String str) {
        return Double.valueOf(str).doubleValue();
    }

    public static double three(double d) {
        return stringToDouble(new DecimalFormat("##0.000").format(d));
    }

    public static String threeString(double d) {
        return new DecimalFormat("#,##0.000").format(d);
    }

    public static double two(double d) {
        return stringToDouble(new DecimalFormat("##0.00").format(d));
    }

    public static String twoString(double d) {
        return new DecimalFormat("#,##0.00").format(d);
    }
}
