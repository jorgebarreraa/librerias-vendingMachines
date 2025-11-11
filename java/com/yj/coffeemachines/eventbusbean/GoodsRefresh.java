package com.yj.coffeemachines.eventbusbean;

import java.util.Arrays;

/* loaded from: classes.dex */
public class GoodsRefresh {
    private String[] sError;

    public GoodsRefresh(String[] strArr) {
        this.sError = null;
        this.sError = strArr;
    }

    public String[] getsError() {
        return this.sError;
    }

    public String toString() {
        return "GoodsRefresh{sError=" + Arrays.toString(this.sError) + '}';
    }
}
