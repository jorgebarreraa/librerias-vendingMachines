package com.yj.coffeemachines;

import com.yj.coffeemachines.helper.Tools;

/* loaded from: classes.dex */
public class OpsSeting {
    private boolean adjust;
    public boolean baoPay;
    private boolean cardpay;
    private boolean cardpaynew;
    private boolean cash;
    private String cashcode;
    private double change;
    private boolean clearCurInterval;
    private int clearCurIntervalTime;
    private boolean doorFaultIgnore;
    private boolean hintTooBar;
    private boolean isChange;
    private boolean islimit;
    private int limit;
    public boolean mdbPay;
    public boolean newcardpay;
    private long noPayExpire;
    private boolean nopay;
    private boolean openGetDrink;
    private boolean playAD;
    private double pos;
    private double rate;
    private double rate_y;
    private boolean saobeiPay;
    private boolean v3Lbq;
    private boolean vip;

    public OpsSeting() {
        this.pos = 0.01d;
    }

    public OpsSeting(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, long j, boolean z7, boolean z8, int i, String str, double d, double d2, double d3, double d4, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, int i2, boolean z16, boolean z17, boolean z18) {
        this.pos = 0.01d;
        this.cash = z;
        this.isChange = z2;
        this.cardpay = z3;
        this.vip = z4;
        this.playAD = z5;
        this.nopay = z6;
        this.noPayExpire = j;
        this.adjust = z7;
        this.islimit = z8;
        this.limit = i;
        this.cashcode = str;
        this.rate = d;
        this.rate_y = d2;
        this.change = d3;
        this.pos = d4;
        this.cardpaynew = z9;
        this.saobeiPay = z10;
        this.newcardpay = z11;
        this.baoPay = z12;
        this.mdbPay = z13;
        this.openGetDrink = z14;
        this.clearCurInterval = z15;
        this.clearCurIntervalTime = i2;
        this.hintTooBar = z16;
        this.doorFaultIgnore = z17;
        this.v3Lbq = z18;
    }

    public String getCashcode() {
        String str = this.cashcode;
        return str == null ? "" : str;
    }

    public double getChange() {
        double d = Tools.toDouble(Double.valueOf(this.change), 1.0d);
        if (d == 0.0d) {
            return 1.0d;
        }
        return d;
    }

    public int getClearCurIntervalTime() {
        return this.clearCurIntervalTime;
    }

    public int getLimit() {
        return this.limit;
    }

    public long getNoPayExpire() {
        return this.noPayExpire;
    }

    public double getPos() {
        return Tools.toDouble(Double.valueOf(this.pos), 0.01d);
    }

    public double getRate() {
        return Tools.toDouble(Double.valueOf(this.rate), 1.0d);
    }

    public double getRate_y() {
        return Tools.toDouble(Double.valueOf(this.rate_y), 1.0d);
    }

    public boolean isAdjust() {
        return this.adjust;
    }

    public boolean isCardpay() {
        return this.cardpay;
    }

    public boolean isCardpaynew() {
        return this.cardpaynew;
    }

    public boolean isCash() {
        return this.cash;
    }

    public boolean isChange() {
        return this.isChange;
    }

    public boolean isClearCurInterval() {
        return this.clearCurInterval;
    }

    public boolean isDoorFaultIgnore() {
        return this.doorFaultIgnore;
    }

    public boolean isHintTooBar() {
        return this.hintTooBar;
    }

    public boolean isIslimit() {
        return this.islimit;
    }

    public boolean isNopay() {
        long j = this.noPayExpire;
        if (j > 0 && j > System.currentTimeMillis()) {
            return this.nopay;
        }
        return false;
    }

    public boolean isOpenGetDrink() {
        return this.openGetDrink;
    }

    public boolean isPlayAD() {
        return this.playAD;
    }

    public boolean isSaobeiPay() {
        return this.saobeiPay;
    }

    public boolean isV3Lbq() {
        return this.v3Lbq;
    }

    public boolean isVip() {
        return this.vip;
    }

    public boolean islimit() {
        return this.islimit;
    }

    public void setAdjust(boolean z) {
        this.adjust = z;
    }

    public void setCardpay(boolean z) {
        this.cardpay = z;
    }

    public void setCardpaynew(boolean z) {
        this.cardpaynew = z;
    }

    public void setCash(boolean z) {
        this.cash = z;
    }

    public void setCashcode(String str) {
        if (str == null) {
            str = "";
        }
        this.cashcode = str;
    }

    public void setChange(double d) {
        this.change = d;
    }

    public void setChange(boolean z) {
        this.isChange = z;
    }

    public void setClearCurInterval(boolean z) {
        this.clearCurInterval = z;
    }

    public void setClearCurIntervalTime(int i) {
        this.clearCurIntervalTime = i;
    }

    public void setDoorFaultIgnore(boolean z) {
        this.doorFaultIgnore = z;
    }

    public void setHintTooBar(boolean z) {
        this.hintTooBar = z;
    }

    public void setIslimit(boolean z) {
        this.islimit = z;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public void setNoPayExpire(long j) {
        this.noPayExpire = j;
    }

    public void setNopay(boolean z, long j) {
        this.noPayExpire = j;
        this.nopay = z;
    }

    public void setOpenGetDrink(boolean z) {
        this.openGetDrink = z;
    }

    public void setPlayAD(boolean z) {
        this.playAD = z;
    }

    public void setPos(double d) {
        this.pos = d;
    }

    public void setRate(double d) {
        this.rate = d;
    }

    public void setRate_y(double d) {
        this.rate_y = d;
    }

    public void setSaobeiPay(boolean z) {
        this.saobeiPay = z;
    }

    public void setV3Lbq(boolean z) {
        this.v3Lbq = z;
    }

    public void setVip(boolean z) {
        this.vip = z;
    }

    public String toString() {
        return "OpsSeting{cash=" + this.cash + ", isChange=" + this.isChange + ", cardpay=" + this.cardpay + ", cardpaynew=" + this.cardpaynew + ", saobeiPay=" + this.saobeiPay + ", vip=" + this.vip + ", playAD=" + this.playAD + ", nopay=" + this.nopay + ", noPayExpire=" + this.noPayExpire + ", adjust=" + this.adjust + ", islimit=" + this.islimit + ", limit=" + this.limit + ", cashcode='" + this.cashcode + "', rate=" + this.rate + ", rate_y=" + this.rate_y + ", change=" + this.change + ", pos=" + this.pos + ", clearCurInterval=" + this.clearCurInterval + ", clearCurIntervalTime=" + this.clearCurIntervalTime + ", doorFaultIgnore=" + this.doorFaultIgnore + ", openGetDrink=" + this.openGetDrink + ", hintTooBar=" + this.hintTooBar + '}';
    }
}
