package com.yj.coffeemachines.mvp.model.beans;

import java.util.List;

/* loaded from: classes.dex */
public class PayWaySettingBack {
    private int code;
    private List<Integer> data;
    private String msg;
    private boolean success;

    public int getCode() {
        return this.code;
    }

    public List<Integer> getData() {
        return this.data;
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

    public void setData(List<Integer> list) {
        this.data = list;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }
}
