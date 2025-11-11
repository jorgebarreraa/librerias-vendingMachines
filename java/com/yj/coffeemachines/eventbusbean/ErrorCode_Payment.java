package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class ErrorCode_Payment {
    public ErrorCode errorCode;
    public boolean isOK;
    public String message;

    /* loaded from: classes.dex */
    public enum ErrorCode {
        A148("找零器离线，现金支付不可用"),
        A149("找零器缺币，现金支付不可用"),
        A150("找零器少币，现金支付不可用"),
        A151("找零器出币没有回转，现金支付不可用"),
        A152("找零器出币口感应器异常，现金支付不可用"),
        A153("纸币器离线，现金支付不可用"),
        A154("刷卡器离线，刷卡支付不可用"),
        E22("支付模块离线"),
        E("支付模块状态错误"),
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

    public ErrorCode_Payment(boolean z, ErrorCode errorCode, String str) {
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
