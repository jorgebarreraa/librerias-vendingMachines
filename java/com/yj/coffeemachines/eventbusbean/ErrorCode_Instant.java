package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class ErrorCode_Instant {
    public ErrorCode errorCode;
    public boolean isOK;
    public String message;

    /* loaded from: classes.dex */
    public enum ErrorCode {
        E01("无水"),
        E02("无杯"),
        E03("无水无杯"),
        E04("温度传感器异常"),
        E25("机器正在加热中，请稍后购买"),
        BUSY("上一杯饮品未取走，请先取走后再购买"),
        E("上一杯饮品未取走，请先取走后再购买"),
        UNKNOWN("未知错误");

        private String name;

        ErrorCode(String str) {
            this.name = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }
    }

    public ErrorCode_Instant(boolean z, ErrorCode errorCode, String str) {
        this.isOK = z;
        this.errorCode = errorCode;
        this.message = str;
    }

    public String getMessage() {
        String str = this.message;
        return str == null ? "" : str;
    }

    public boolean isOK() {
        return this.isOK;
    }

    public String toString() {
        return "ErrorCode_Instant{isOK=" + this.isOK + ", message='" + this.message + "', errorCode=" + this.errorCode + '}';
    }
}
