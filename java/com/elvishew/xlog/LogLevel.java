package com.elvishew.xlog;

/* loaded from: classes.dex */
public class LogLevel {
    public static final int ALL = Integer.MIN_VALUE;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int NONE = Integer.MAX_VALUE;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    public static String getLevelName(int i) {
        if (i == 2) {
            return "VERBOSE";
        }
        if (i == 3) {
            return "DEBUG";
        }
        if (i == 4) {
            return "INFO";
        }
        if (i == 5) {
            return "WARN";
        }
        if (i == 6) {
            return "ERROR";
        }
        if (i < 2) {
            return "VERBOSE-" + (2 - i);
        }
        return "ERROR+" + (i - 6);
    }

    public static String getShortLevelName(int i) {
        if (i == 2) {
            return "V";
        }
        if (i == 3) {
            return "D";
        }
        if (i == 4) {
            return "I";
        }
        if (i == 5) {
            return "W";
        }
        if (i == 6) {
            return "E";
        }
        if (i < 2) {
            return "V-" + (2 - i);
        }
        return "E+" + (i - 6);
    }
}
