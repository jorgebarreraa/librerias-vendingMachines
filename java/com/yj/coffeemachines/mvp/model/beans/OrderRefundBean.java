package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class OrderRefundBean {
    private String orderNo;
    private double refundAmount;
    private String refundNo;
    private String refundReason;
    private int refundStatus;

    public String getOrderNo() {
        return this.orderNo;
    }

    public double getRefundAmount() {
        return this.refundAmount;
    }

    public String getRefundNo() {
        return this.refundNo;
    }

    public String getRefundReason() {
        return this.refundReason;
    }

    public int getRefundStatus() {
        return this.refundStatus;
    }

    public boolean isRefundSuccess() {
        int i = this.refundStatus;
        return i == 4 || i == 5;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }

    public void setRefundAmount(double d) {
        this.refundAmount = d;
    }

    public void setRefundNo(String str) {
        this.refundNo = str;
    }

    public void setRefundReason(String str) {
        this.refundReason = str;
    }

    public void setRefundStatus(int i) {
        this.refundStatus = i;
    }
}
