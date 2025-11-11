package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class OrderStateBean {
    private String orderId;
    private String orderNo;
    private int status;

    public String getOrderId() {
        return this.orderId;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public int getStatus() {
        return this.status;
    }

    public boolean isInterrupt() {
        int i = this.status;
        return i == 3 || i == 4 || i == 5 || i == 6;
    }

    public boolean isSuccess() {
        return this.status == 2;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String toString() {
        return "OrderRefundBean{status=" + this.status + ", orderNo='" + this.orderNo + "', orderId='" + this.orderId + "'}";
    }
}
