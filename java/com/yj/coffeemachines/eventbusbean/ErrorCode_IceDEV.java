package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class ErrorCode_IceDEV {
    public ErrorCode errorCode;
    public boolean isOK;
    public String message;

    /* loaded from: classes.dex */
    public enum ErrorCode {
        E22("制冰机关机"),
        A117("制冰机缺水"),
        A118("制冰机缺冰"),
        E15("制冰机离线"),
        E(""),
        UNKNOW("未知错误");

        private String name;

        ErrorCode(String str) {
            this.name = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }
    }

    public ErrorCode_IceDEV(boolean z, ErrorCode errorCode, String str) {
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
}
