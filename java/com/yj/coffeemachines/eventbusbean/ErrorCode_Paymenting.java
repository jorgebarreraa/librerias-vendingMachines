package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class ErrorCode_Paymenting {
    public ErrorCode errorCode;
    public boolean isOK;
    public String message;

    /* loaded from: classes.dex */
    public enum ErrorCode {
        PAY_ERROR("开启支付失败"),
        PAYSUCCESS("支付成功"),
        CHANGE_ERROR("找零失败");

        private String name;

        ErrorCode(String str) {
            this.name = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }
    }

    public ErrorCode_Paymenting(boolean z, ErrorCode errorCode, String str) {
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
