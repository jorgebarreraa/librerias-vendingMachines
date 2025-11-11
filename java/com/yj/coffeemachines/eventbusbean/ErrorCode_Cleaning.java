package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class ErrorCode_Cleaning {
    public ErrorCode errorCode;
    public boolean isOK;
    public String message;

    /* loaded from: classes.dex */
    public enum ErrorCode {
        CURRENTFINISH("清洗完成"),
        CURRENTFINISHING("清洗正在进行");

        private String name;

        ErrorCode(String str) {
            this.name = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }
    }

    public ErrorCode_Cleaning(boolean z, ErrorCode errorCode, String str) {
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
