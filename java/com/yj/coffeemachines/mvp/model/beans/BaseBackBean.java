package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class BaseBackBean {
    private int code;
    private String msg;
    private boolean success;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public String toString() {
        return "BaseBackBean{code=" + this.code + ", success=" + this.success + ", msg='" + this.msg + "'}";
    }
}
