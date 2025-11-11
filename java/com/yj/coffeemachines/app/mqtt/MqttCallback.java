package com.yj.coffeemachines.app.mqtt;

/* loaded from: classes.dex */
public abstract class MqttCallback {
    public void connectFail(String str) {
    }

    public void connectLost(String str) {
    }

    public void connectSuccess(boolean z) {
    }

    public void deliveryComplete(String str) {
    }

    public abstract void receiveMessage(String str, String str2);

    public void subscribedFail(String str) {
    }

    public void subscribedSuccess(String[] strArr) {
    }
}
