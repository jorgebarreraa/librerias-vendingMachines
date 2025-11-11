package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class GenOrderBack {
    private Integer code;
    private Object data;
    private String msg;
    private Boolean success;

    public Integer getCode() {
        return this.code;
    }

    public Object getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setCode(Integer num) {
        this.code = num;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setSuccess(Boolean bool) {
        this.success = bool;
    }
}
