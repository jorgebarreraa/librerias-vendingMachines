package com.yj.coffeemachines.bean;

/* loaded from: classes.dex */
public class ReturnAmt {
    private int return_num;
    private long return_time;
    private String trade_no;

    public ReturnAmt(String str, int i, long j) {
        this.trade_no = str;
        this.return_num = i;
        this.return_time = j;
    }

    public int getReturn_num() {
        return this.return_num;
    }

    public long getReturn_time() {
        return this.return_time;
    }

    public String getTrade_no() {
        return this.trade_no;
    }

    public void setReturn_num() {
        int i = this.return_num;
        this.return_num = i + 1;
        this.return_num = i;
    }

    public void setReturn_time(long j) {
        this.return_time = j;
    }

    public void setTrade_no(String str) {
        this.trade_no = str;
    }

    public String toString() {
        return "ReturnAmt{trade_no='" + this.trade_no + "', return_num=" + this.return_num + ", return_time=" + this.return_time + '}';
    }
}
