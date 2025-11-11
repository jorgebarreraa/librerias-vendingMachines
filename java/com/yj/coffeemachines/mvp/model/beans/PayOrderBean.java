package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class PayOrderBean {
    private String orderNo;
    private String payData;
    private String thirdOrderNo;

    public String getOrderNo() {
        return this.orderNo;
    }

    public String getPayData() {
        return this.payData;
    }

    public String getThirdOrderNo() {
        return this.thirdOrderNo;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }

    public void setPayData(String str) {
        this.payData = str;
    }

    public void setThirdOrderNo(String str) {
        this.thirdOrderNo = str;
    }

    public String toString() {
        return "PayOrderBean{orderNo='" + this.orderNo + "', thirdOrderNo='" + this.thirdOrderNo + "', payData='" + this.payData + "'}";
    }
}
