package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class ErrorCode_CurrentGrinding {
    public ErrorCode errorCode;
    public boolean isOK;
    public String message;

    /* loaded from: classes.dex */
    public enum ErrorCode {
        E06("现磨：咖啡锅炉超温"),
        E07("现磨：需要排空"),
        E08("现磨：核心堵转"),
        E09("现磨：NTC 状态异常"),
        E10("现磨：现磨磨组离线"),
        E(""),
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

    public ErrorCode_CurrentGrinding(boolean z, ErrorCode errorCode, String str) {
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
        return "ErrorCode_CurrentGrinding{isOK=" + this.isOK + ", message='" + this.message + "', errorCode=" + this.errorCode + '}';
    }
}
