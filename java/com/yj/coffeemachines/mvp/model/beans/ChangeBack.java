package com.yj.coffeemachines.mvp.model.beans;

import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import java.util.List;

/* loaded from: classes.dex */
public class ChangeBack {
    private int code;
    private List<ProductBean.ProductDetailBean> data;
    private String msg;
    private Boolean success;

    public int getCode() {
        return this.code;
    }

    public List<ProductBean.ProductDetailBean> getData() {
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

    public void setData(List<ProductBean.ProductDetailBean> list) {
        this.data = list;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setSuccess(Boolean bool) {
        this.success = bool;
    }
}
