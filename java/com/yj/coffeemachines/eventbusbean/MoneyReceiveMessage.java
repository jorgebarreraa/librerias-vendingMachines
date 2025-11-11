package com.yj.coffeemachines.eventbusbean;

import java.io.Serializable;

/* loaded from: classes.dex */
public class MoneyReceiveMessage implements Serializable {
    private double money_sum;
    private double sk;
    private double sk_par;
    private double yb;
    private double yb_par;
    private double zb;
    private double zb_par;

    public MoneyReceiveMessage() {
    }

    public MoneyReceiveMessage(double d) {
        this.money_sum = d;
    }

    public MoneyReceiveMessage(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        this.yb = d;
        this.zb = d2;
        this.sk = d3;
        this.yb_par = d4;
        this.zb_par = d5;
        this.sk_par = d6;
        this.money_sum = d7;
    }

    public double getMoney_sum() {
        return this.money_sum;
    }

    public double getSk() {
        return this.sk;
    }

    public double getSk_par() {
        return this.sk_par;
    }

    public double getYb() {
        return this.yb;
    }

    public double getYb_par() {
        return this.yb_par;
    }

    public double getZb() {
        return this.zb;
    }

    public double getZb_par() {
        return this.zb_par;
    }

    public void setMoney_sum(double d) {
        this.money_sum = d;
    }

    public void setSk(double d) {
        this.sk = d;
    }

    public void setSk_par(double d) {
        this.sk_par = d;
    }

    public void setYb(double d) {
        this.yb = d;
    }

    public void setYb_par(double d) {
        this.yb_par = d;
    }

    public void setZb(double d) {
        this.zb = d;
    }

    public void setZb_par(double d) {
        this.zb_par = d;
    }

    public String toString() {
        return "MoneyReceiveMessage{yb=" + this.yb + ", zb=" + this.zb + ", sk=" + this.sk + ", sk_par=" + this.sk_par + ", zb_par=" + this.zb_par + ", yb_par=" + this.yb_par + ", money_sum=" + this.money_sum + '}';
    }
}
