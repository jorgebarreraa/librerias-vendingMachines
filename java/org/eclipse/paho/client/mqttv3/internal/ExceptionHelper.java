package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

/* loaded from: classes2.dex */
public class ExceptionHelper {
    private ExceptionHelper() {
    }

    public static MqttException createMqttException(int i) {
        return (i == 4 || i == 5) ? new MqttSecurityException(i) : new MqttException(i);
    }

    public static MqttException createMqttException(Throwable th) {
        return th.getClass().getName().equals("java.security.GeneralSecurityException") ? new MqttSecurityException(th) : new MqttException(th);
    }

    public static boolean isClassAvailable(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
