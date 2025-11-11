package com.yj.coffeemachines.app.mqtt;

import android.content.Context;
import android.text.TextUtils;
import com.yj.coffeemachines.Api;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.helper.Tools;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/* loaded from: classes.dex */
public class MqttManager {
    private static volatile MqttManager mqttManager;
    private boolean autoReconnect = true;
    private MqttCallback callback;
    private Context mContext;
    private volatile MqttAndroidClient mqttAndroidClient;
    private String[] mqttTopic;
    private String passWord;
    private int[] qos;
    private String serverUri;
    private String userName;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyMqttCallbackExtended implements MqttCallbackExtended {
        private MyMqttCallbackExtended() {
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallbackExtended
        public void connectComplete(boolean z, String str) {
            Tools.upLocalLog("MQTT-connectComplete:" + z + ";serverURI:" + str);
            if (MqttManager.this.callback != null) {
                MqttManager.this.callback.connectSuccess(z);
            }
            MqttManager.this.subscribeToTopic();
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void connectionLost(Throwable th) {
            if (MqttManager.this.callback != null) {
                MqttManager.this.callback.connectLost("connectionLost:断开");
            }
            if (th != null) {
                th.printStackTrace();
            }
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            if (MqttManager.this.callback != null) {
                try {
                    MqttManager.this.callback.deliveryComplete(iMqttDeliveryToken.getMessage().toString());
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void messageArrived(String str, MqttMessage mqttMessage) {
            Tools.upLocalLog("收到网络消息，message.getId()：" + mqttMessage.getId() + ";receive message: " + mqttMessage.toString() + ";topic:" + str);
            if (MqttManager.this.callback != null) {
                MqttManager.this.callback.receiveMessage(str, mqttMessage.toString());
            }
        }
    }

    private MqttManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void cleanupResources() {
        try {
            try {
                if (this.mqttAndroidClient != null) {
                    this.mqttAndroidClient.close();
                    this.mqttAndroidClient.unregisterResources();
                }
            } catch (Exception e) {
                Tools.upLocalLog("MQTT resource cleanup error" + e);
            }
        } finally {
            mqttManager = null;
            this.mqttAndroidClient = null;
        }
    }

    public static MqttManager getInstance() {
        if (mqttManager == null) {
            synchronized (MqttManager.class) {
                if (mqttManager == null) {
                    mqttManager = new MqttManager();
                }
            }
        }
        return mqttManager;
    }

    private boolean isConnect() {
        if (this.mqttAndroidClient != null) {
            return this.mqttAndroidClient.isConnected();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void subscribeToTopic() {
        if (this.mqttAndroidClient == null || !this.mqttAndroidClient.isConnected() || this.mqttTopic == null || this.qos == null) {
            return;
        }
        try {
            this.mqttAndroidClient.subscribe(this.mqttTopic, this.qos, this.mContext.getApplicationContext(), new IMqttActionListener() { // from class: com.yj.coffeemachines.app.mqtt.MqttManager.1
                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    Tools.upLocalLog("订阅失败");
                    Tools.upLocalLog("订阅失败：" + (th != null ? th.getMessage() : "未知错误"));
                }

                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    if (MqttManager.this.callback != null) {
                        MqttManager.this.callback.subscribedSuccess(MqttManager.this.mqttTopic);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MqttCallback mqttCallback = this.callback;
            if (mqttCallback != null) {
                mqttCallback.subscribedFail(e.toString());
            }
        }
    }

    public void connect() {
        if (isConnect()) {
            Tools.upLocalLog("MQTT已连接，无需连接");
            return;
        }
        if (this.mqttAndroidClient == null) {
            Tools.upLocalLog("MQTT未初始化，设置参数和回调");
            this.mqttAndroidClient = new MqttAndroidClient(this.mContext, this.serverUri, Api.MQTT_CLIENTID);
            this.mqttAndroidClient.registerResources(this.mContext);
            this.mqttAndroidClient.setCallback(new MyMqttCallbackExtended());
        }
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(this.autoReconnect);
        mqttConnectOptions.setCleanSession(false);
        if (!TextUtils.isEmpty(this.userName)) {
            mqttConnectOptions.setUserName(this.userName);
        }
        if (!TextUtils.isEmpty(this.passWord)) {
            mqttConnectOptions.setPassword(this.passWord.toCharArray());
        }
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(20);
        try {
            this.mqttAndroidClient.connect(mqttConnectOptions);
            Tools.upLocalLog("MQTT已发起连接");
        } catch (Exception e) {
            Tools.upLocalLog("MQTT发起连接失败：" + e.toString());
            Constants.isConnected_mqtt = false;
            Constants.isConnecting_mqtt = false;
            e.printStackTrace();
            MqttCallback mqttCallback = this.callback;
            if (mqttCallback != null) {
                mqttCallback.connectFail(e.toString());
            }
        }
    }

    public void disconnect() {
        if (this.mqttAndroidClient == null) {
            return;
        }
        try {
            this.mqttAndroidClient.disconnect().setActionCallback(new IMqttActionListener() { // from class: com.yj.coffeemachines.app.mqtt.MqttManager.2
                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    Tools.upLocalLog("MQTT disconnect failed" + th);
                    MqttManager.this.cleanupResources();
                }

                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    MqttManager.this.cleanupResources();
                }
            });
        } catch (MqttException e) {
            Tools.upLocalLog("MQTT disconnect error" + e);
            cleanupResources();
        }
    }

    public MqttManager init(String str, String str2, String str3, Context context) {
        init(str, str2, str3, context, null, null);
        return this;
    }

    public MqttManager init(String str, String str2, String str3, Context context, String[] strArr, int[] iArr) {
        this.serverUri = str;
        this.userName = str2;
        this.passWord = str3;
        this.mqttTopic = strArr;
        this.qos = iArr;
        this.mContext = context;
        return this;
    }

    public void publishMessage(String str, String str2, boolean z) {
        if (!isConnect()) {
            Tools.upLocalLog("检测心跳断开，连接心跳测试");
            try {
                disconnect();
                connect();
                return;
            } catch (Exception e) {
                Tools.upLocalLog("连接心跳失败：" + e.toString());
                return;
            }
        }
        try {
            Tools.upLocalLog("网络发送消息：topic：" + str + "；msg:" + str2);
            this.mqttAndroidClient.publish(str, str2.getBytes(), 2, z);
        } catch (MqttException e2) {
            Tools.upLocalLog("网络发送消息失败Error Publishing: " + e2.toString());
        }
    }

    public MqttManager setAutoReconnect(boolean z) {
        this.autoReconnect = z;
        return this;
    }

    public MqttManager setCallback(MqttCallback mqttCallback) {
        this.callback = mqttCallback;
        return this;
    }

    public void setTopicAndQos(String[] strArr, int[] iArr) {
        if (this.mqttAndroidClient != null || !this.mqttAndroidClient.isConnected()) {
            try {
                this.mqttAndroidClient.unsubscribe(this.mqttTopic);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        this.mqttTopic = strArr;
        this.qos = iArr;
        subscribeToTopic();
    }
}
