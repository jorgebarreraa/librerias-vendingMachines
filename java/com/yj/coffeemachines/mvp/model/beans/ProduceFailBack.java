package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class ProduceFailBack {
    private int code;
    private Boolean data;
    private String msg;
    private Boolean success;

    public int getCode() {
        return this.code;
    }

    public Boolean getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(Boolean bool) {
        this.data = bool;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setSuccess(Boolean bool) {
        this.success = bool;
    }
}
