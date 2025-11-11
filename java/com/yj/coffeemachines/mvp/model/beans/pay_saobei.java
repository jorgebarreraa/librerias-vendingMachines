package com.yj.coffeemachines.mvp.model.beans;

/* loaded from: classes.dex */
public class pay_saobei {
    private String orderNo;
    private String payType;
    private String terminalIp;
    private String terminalTime;
    private String totalFee;

    public String getOrderNo() {
        return this.orderNo;
    }

    public String getPayType() {
        return this.payType;
    }

    public String getTerminalIp() {
        return this.terminalIp;
    }

    public String getTerminalTime() {
        return this.terminalTime;
    }

    public String getTotalFee() {
        return this.totalFee;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }

    public void setPayType(String str) {
        this.payType = str;
    }

    public void setTerminalIp(String str) {
        this.terminalIp = str;
    }

    public void setTerminalTime(String str) {
        this.terminalTime = str;
    }

    public void setTotalFee(String str) {
        this.totalFee = str;
    }

    public String toString() {
        return "pay_saobei{orderNo='" + this.orderNo + "', terminalTime='" + this.terminalTime + "', totalFee='" + this.totalFee + "', terminalIp='" + this.terminalIp + "', payType='" + this.payType + "'}";
    }
}
