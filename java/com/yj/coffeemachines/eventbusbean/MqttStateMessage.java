package com.yj.coffeemachines.eventbusbean;

/* loaded from: classes.dex */
public class MqttStateMessage {
    private boolean isConnect;

    public MqttStateMessage(boolean z) {
        this.isConnect = z;
    }

    public boolean isConnect() {
        return this.isConnect;
    }

    public void setConnect(boolean z) {
        this.isConnect = z;
    }
}
