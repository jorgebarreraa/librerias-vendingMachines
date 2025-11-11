package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class LockMessage {
    private boolean isLunck;

    public LockMessage(boolean z) {
        this.isLunck = z;
    }

    public boolean isLunck() {
        return this.isLunck;
    }

    public void setLunck(boolean z) {
        this.isLunck = z;
    }
}
