package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class NetWorkState {
    private boolean link;
    private String message;

    public NetWorkState(boolean z, String str) {
        this.link = z;
        this.message = str;
    }

    public String getMessage() {
        String str = this.message;
        return str == null ? "" : str;
    }

    public boolean isLink() {
        return this.link;
    }
}
