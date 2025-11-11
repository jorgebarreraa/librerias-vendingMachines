package org.eclipse.paho.client.mqttv3.internal;

import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
// import org.eclipse.paho.android.service.MqttServiceConstants; // Eliminado: clase privada
import org.eclipse.paho.client.mqttv3.BufferedMessage;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes2.dex */
public class ClientComms {
    public static String BUILD_LEVEL = "L${build.level}";
    private static final byte CLOSED = 4;
    private static final byte CONNECTED = 0;
    private static final byte CONNECTING = 1;
    private static final byte DISCONNECTED = 3;
    private static final byte DISCONNECTING = 2;
    public static String VERSION = "${project.version}";
    private CommsCallback callback;
    private IMqttAsyncClient client;
    private ClientState clientState;
    private MqttConnectOptions conOptions;
    private byte conState;
    private DisconnectedMessageBuffer disconnectedMessageBuffer;
    private ExecutorService executorService;
    private int networkModuleIndex;
    private NetworkModule[] networkModules;
    private MqttClientPersistence persistence;
    private MqttPingSender pingSender;
    private CommsReceiver receiver;
    private CommsSender sender;
    private CommsTokenStore tokenStore;
    private static final String CLASS_NAME = "org.eclipse.paho.client.mqttv3.internal.ClientComms";
    private static final Logger log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    private boolean stoppingComms = false;
    private Object conLock = new Object();
    private boolean closePending = false;
    private boolean resting = false;

    /* loaded from: classes2.dex */
    private class ConnectBG implements Runnable {
        ClientComms clientComms;
        MqttConnect conPacket;
        MqttToken conToken;
        private String threadName;

        ConnectBG(ClientComms clientComms, MqttToken mqttToken, MqttConnect mqttConnect, ExecutorService executorService) {
            this.clientComms = null;
            this.clientComms = clientComms;
            this.conToken = mqttToken;
            this.conPacket = mqttConnect;
            this.threadName = "MQTT Con: " + ClientComms.this.getClient().getClientId();
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName(this.threadName);
            ClientComms.log.fine(ClientComms.CLASS_NAME, "connectBG:run", "220");
            MqttException e = null;
            try {
                for (MqttDeliveryToken mqttDeliveryToken : ClientComms.this.tokenStore.getOutstandingDelTokens()) {
                    mqttDeliveryToken.internalTok.setException(null);
                }
                ClientComms.this.tokenStore.saveToken(this.conToken, this.conPacket);
                NetworkModule networkModule = ClientComms.this.networkModules[ClientComms.this.networkModuleIndex];
                networkModule.start();
                ClientComms.this.receiver = new CommsReceiver(this.clientComms, ClientComms.this.clientState, ClientComms.this.tokenStore, networkModule.getInputStream());
                ClientComms.this.receiver.start("MQTT Rec: " + ClientComms.this.getClient().getClientId(), ClientComms.this.executorService);
                ClientComms.this.sender = new CommsSender(this.clientComms, ClientComms.this.clientState, ClientComms.this.tokenStore, networkModule.getOutputStream());
                ClientComms.this.sender.start("MQTT Snd: " + ClientComms.this.getClient().getClientId(), ClientComms.this.executorService);
                ClientComms.this.callback.start("MQTT Call: " + ClientComms.this.getClient().getClientId(), ClientComms.this.executorService);
                ClientComms.this.internalSend(this.conPacket, this.conToken);
            } catch (MqttException e2) {
                e = e2;
                ClientComms.log.fine(ClientComms.CLASS_NAME, "connectBG:run", "212", null, e);
            } catch (Exception e3) {
                ClientComms.log.fine(ClientComms.CLASS_NAME, "connectBG:run", "209", null, e3);
                e = ExceptionHelper.createMqttException(e3);
            }
            if (e != null) {
                ClientComms.this.shutdownConnection(this.conToken, e);
            }
        }

        void start() {
            ClientComms.this.executorService.execute(this);
        }
    }

    /* loaded from: classes2.dex */
    private class DisconnectBG implements Runnable {
        MqttDisconnect disconnect;
        long quiesceTimeout;
        private String threadName;
        MqttToken token;

        DisconnectBG(MqttDisconnect mqttDisconnect, long j, MqttToken mqttToken, ExecutorService executorService) {
            this.disconnect = mqttDisconnect;
            this.quiesceTimeout = j;
            this.token = mqttToken;
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName(this.threadName);
            ClientComms.log.fine(ClientComms.CLASS_NAME, "disconnectBG:run", "221");
            ClientComms.this.clientState.quiesce(this.quiesceTimeout);
            try {
                ClientComms.this.internalSend(this.disconnect, this.token);
                this.token.internalTok.waitUntilSent();
            } catch (MqttException unused) {
            } catch (Throwable th) {
                this.token.internalTok.markComplete(null, null);
                ClientComms.this.shutdownConnection(this.token, null);
                throw th;
            }
            this.token.internalTok.markComplete(null, null);
            ClientComms.this.shutdownConnection(this.token, null);
        }

        void start() {
            this.threadName = "MQTT Disc: " + ClientComms.this.getClient().getClientId();
            ClientComms.this.executorService.execute(this);
        }
    }

    /* loaded from: classes2.dex */
    class ReconnectDisconnectedBufferCallback implements IDisconnectedBufferCallback {
        final String methodName;

        ReconnectDisconnectedBufferCallback(String str) {
            this.methodName = str;
        }

        @Override // org.eclipse.paho.client.mqttv3.internal.IDisconnectedBufferCallback
        public void publishBufferedMessage(BufferedMessage bufferedMessage) throws MqttException {
            if (!ClientComms.this.isConnected()) {
                ClientComms.log.fine(ClientComms.CLASS_NAME, this.methodName, "208");
                throw ExceptionHelper.createMqttException(32104);
            }
            while (ClientComms.this.clientState.getActualInFlight() >= ClientComms.this.clientState.getMaxInFlight() - 1) {
                Thread.yield();
            }
            ClientComms.log.fine(ClientComms.CLASS_NAME, this.methodName, "510", new Object[]{bufferedMessage.getMessage().getKey()});
            ClientComms.this.internalSend(bufferedMessage.getMessage(), bufferedMessage.getToken());
            ClientComms.this.clientState.unPersistBufferedMessage(bufferedMessage.getMessage());
        }
    }

    public ClientComms(IMqttAsyncClient iMqttAsyncClient, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender, ExecutorService executorService) throws MqttException {
        this.conState = (byte) 3;
        this.conState = (byte) 3;
        this.client = iMqttAsyncClient;
        this.persistence = mqttClientPersistence;
        this.pingSender = mqttPingSender;
        this.pingSender.init(this);
        this.executorService = executorService;
        this.tokenStore = new CommsTokenStore(getClient().getClientId());
        this.callback = new CommsCallback(this);
        this.clientState = new ClientState(mqttClientPersistence, this.tokenStore, this.callback, this, mqttPingSender);
        this.callback.setClientState(this.clientState);
        log.setResourceName(getClient().getClientId());
    }

    private MqttToken handleOldTokens(MqttToken mqttToken, MqttException mqttException) {
        log.fine(CLASS_NAME, "handleOldTokens", "222");
        MqttToken mqttToken2 = null;
        if (mqttToken != null) {
            try {
                if (this.tokenStore.getToken(mqttToken.internalTok.getKey()) == null) {
                    this.tokenStore.saveToken(mqttToken, mqttToken.internalTok.getKey());
                }
            } catch (Exception unused) {
            }
        }
        Enumeration elements = this.clientState.resolveOldTokens(mqttException).elements();
        while (elements.hasMoreElements()) {
            MqttToken mqttToken3 = (MqttToken) elements.nextElement();
            if (!mqttToken3.internalTok.getKey().equals(MqttDisconnect.KEY) && !mqttToken3.internalTok.getKey().equals("Con")) {
                this.callback.asyncOperationComplete(mqttToken3);
            }
            mqttToken2 = mqttToken3;
        }
        return mqttToken2;
    }

    private void handleRunException(Exception exc) {
        log.fine(CLASS_NAME, "handleRunException", "804", null, exc);
        shutdownConnection(null, !(exc instanceof MqttException) ? new MqttException(32109, exc) : (MqttException) exc);
    }

    private void shutdownExecutorService() {
        this.executorService.shutdown();
        try {
            if (this.executorService.awaitTermination(1L, TimeUnit.SECONDS)) {
                return;
            }
            this.executorService.shutdownNow();
            if (this.executorService.awaitTermination(1L, TimeUnit.SECONDS)) {
                return;
            }
            log.fine(CLASS_NAME, "shutdownExecutorService", "executorService did not terminate");
        } catch (InterruptedException unused) {
            this.executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public MqttToken checkForActivity() {
        return checkForActivity(null);
    }

    public MqttToken checkForActivity(IMqttActionListener iMqttActionListener) {
        try {
            return this.clientState.checkForActivity(iMqttActionListener);
        } catch (MqttException e) {
            handleRunException(e);
            return null;
        } catch (Exception e2) {
            handleRunException(e2);
            return null;
        }
    }

    public void close(boolean z) throws MqttException {
        synchronized (this.conLock) {
            if (!isClosed()) {
                if (!isDisconnected() || z) {
                    log.fine(CLASS_NAME, "close", "224");
                    if (isConnecting()) {
                        throw new MqttException(32110);
                    }
                    if (isConnected()) {
                        throw ExceptionHelper.createMqttException(32100);
                    }
                    if (isDisconnecting()) {
                        this.closePending = true;
                        return;
                    }
                }
                this.conState = (byte) 4;
                shutdownExecutorService();
                this.clientState.close();
                this.clientState = null;
                this.callback = null;
                this.persistence = null;
                this.sender = null;
                this.pingSender = null;
                this.receiver = null;
                this.networkModules = null;
                this.conOptions = null;
                this.tokenStore = null;
            }
        }
    }

    public void connect(MqttConnectOptions mqttConnectOptions, MqttToken mqttToken) throws MqttException {
        synchronized (this.conLock) {
            if (!isDisconnected() || this.closePending) {
                log.fine(CLASS_NAME, MqttServiceConstants.CONNECT_ACTION, "207", new Object[]{new Byte(this.conState)});
                if (isClosed() || this.closePending) {
                    throw new MqttException(32111);
                }
                if (isConnecting()) {
                    throw new MqttException(32110);
                }
                if (!isDisconnecting()) {
                    throw ExceptionHelper.createMqttException(32100);
                }
                throw new MqttException(32102);
            }
            log.fine(CLASS_NAME, MqttServiceConstants.CONNECT_ACTION, "214");
            this.conState = (byte) 1;
            this.conOptions = mqttConnectOptions;
            MqttConnect mqttConnect = new MqttConnect(this.client.getClientId(), this.conOptions.getMqttVersion(), this.conOptions.isCleanSession(), this.conOptions.getKeepAliveInterval(), this.conOptions.getUserName(), this.conOptions.getPassword(), this.conOptions.getWillMessage(), this.conOptions.getWillDestination());
            this.clientState.setKeepAliveSecs(this.conOptions.getKeepAliveInterval());
            this.clientState.setCleanSession(this.conOptions.isCleanSession());
            this.clientState.setMaxInflight(this.conOptions.getMaxInflight());
            this.tokenStore.open();
            new ConnectBG(this, mqttToken, mqttConnect, this.executorService).start();
        }
    }

    public void connectComplete(MqttConnack mqttConnack, MqttException mqttException) throws MqttException {
        int returnCode = mqttConnack.getReturnCode();
        synchronized (this.conLock) {
            if (returnCode != 0) {
                log.fine(CLASS_NAME, "connectComplete", "204", new Object[]{new Integer(returnCode)});
                throw mqttException;
            }
            log.fine(CLASS_NAME, "connectComplete", "215");
            this.conState = (byte) 0;
        }
    }

    public void deleteBufferedMessage(int i) {
        this.disconnectedMessageBuffer.deleteMessage(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void deliveryComplete(int i) throws MqttPersistenceException {
        this.clientState.deliveryComplete(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void deliveryComplete(MqttPublish mqttPublish) throws MqttPersistenceException {
        this.clientState.deliveryComplete(mqttPublish);
    }

    public void disconnect(MqttDisconnect mqttDisconnect, long j, MqttToken mqttToken) throws MqttException {
        synchronized (this.conLock) {
            if (isClosed()) {
                log.fine(CLASS_NAME, MqttServiceConstants.DISCONNECT_ACTION, "223");
                throw ExceptionHelper.createMqttException(32111);
            }
            if (isDisconnected()) {
                log.fine(CLASS_NAME, MqttServiceConstants.DISCONNECT_ACTION, "211");
                throw ExceptionHelper.createMqttException(32101);
            }
            if (isDisconnecting()) {
                log.fine(CLASS_NAME, MqttServiceConstants.DISCONNECT_ACTION, "219");
                throw ExceptionHelper.createMqttException(32102);
            }
            if (Thread.currentThread() == this.callback.getThread()) {
                log.fine(CLASS_NAME, MqttServiceConstants.DISCONNECT_ACTION, "210");
                throw ExceptionHelper.createMqttException(32107);
            }
            log.fine(CLASS_NAME, MqttServiceConstants.DISCONNECT_ACTION, "218");
            this.conState = (byte) 2;
            new DisconnectBG(mqttDisconnect, j, mqttToken, this.executorService).start();
        }
    }

    public void disconnectForcibly(long j, long j2) throws MqttException {
        disconnectForcibly(j, j2, true);
    }

    public void disconnectForcibly(long j, long j2, boolean z) throws MqttException {
        ClientState clientState = this.clientState;
        if (clientState != null) {
            clientState.quiesce(j);
        }
        MqttToken mqttToken = new MqttToken(this.client.getClientId());
        if (z) {
            try {
                internalSend(new MqttDisconnect(), mqttToken);
                mqttToken.waitForCompletion(j2);
            } catch (Exception unused) {
            } catch (Throwable th) {
                mqttToken.internalTok.markComplete(null, null);
                shutdownConnection(mqttToken, null);
                throw th;
            }
        }
        mqttToken.internalTok.markComplete(null, null);
        shutdownConnection(mqttToken, null);
    }

    public int getActualInFlight() {
        return this.clientState.getActualInFlight();
    }

    public MqttMessage getBufferedMessage(int i) {
        return ((MqttPublish) this.disconnectedMessageBuffer.getMessage(i).getMessage()).getMessage();
    }

    public int getBufferedMessageCount() {
        return this.disconnectedMessageBuffer.getMessageCount();
    }

    public IMqttAsyncClient getClient() {
        return this.client;
    }

    public ClientState getClientState() {
        return this.clientState;
    }

    public MqttConnectOptions getConOptions() {
        return this.conOptions;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("conState", new Integer(this.conState));
        properties.put("serverURI", getClient().getServerURI());
        properties.put("callback", this.callback);
        properties.put("stoppingComms", new Boolean(this.stoppingComms));
        return properties;
    }

    public long getKeepAlive() {
        return this.clientState.getKeepAlive();
    }

    public int getNetworkModuleIndex() {
        return this.networkModuleIndex;
    }

    public NetworkModule[] getNetworkModules() {
        return this.networkModules;
    }

    public MqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.tokenStore.getOutstandingDelTokens();
    }

    CommsReceiver getReceiver() {
        return this.receiver;
    }

    protected MqttTopic getTopic(String str) {
        return new MqttTopic(str, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void internalSend(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        log.fine(CLASS_NAME, "internalSend", "200", new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
        if (mqttToken.getClient() != null) {
            log.fine(CLASS_NAME, "internalSend", "213", new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
            throw new MqttException(32201);
        }
        mqttToken.internalTok.setClient(getClient());
        try {
            this.clientState.send(mqttWireMessage, mqttToken);
        } catch (MqttException e) {
            if (mqttWireMessage instanceof MqttPublish) {
                this.clientState.undo((MqttPublish) mqttWireMessage);
            }
            throw e;
        }
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 4;
        }
        return z;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 0;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.conLock) {
            z = true;
            if (this.conState != 1) {
                z = false;
            }
        }
        return z;
    }

    public boolean isDisconnected() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 3;
        }
        return z;
    }

    public boolean isDisconnecting() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 2;
        }
        return z;
    }

    public boolean isResting() {
        boolean z;
        synchronized (this.conLock) {
            z = this.resting;
        }
        return z;
    }

    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.callback.messageArrivedComplete(i, i2);
    }

    public void notifyConnect() {
        if (this.disconnectedMessageBuffer != null) {
            log.fine(CLASS_NAME, "notifyConnect", "509");
            this.disconnectedMessageBuffer.setPublishCallback(new ReconnectDisconnectedBufferCallback("notifyConnect"));
            this.executorService.execute(this.disconnectedMessageBuffer);
        }
    }

    public void removeMessageListener(String str) {
        this.callback.removeMessageListener(str);
    }

    public void sendNoWait(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (!isConnected() && ((isConnected() || !(mqttWireMessage instanceof MqttConnect)) && (!isDisconnecting() || !(mqttWireMessage instanceof MqttDisconnect)))) {
            if (this.disconnectedMessageBuffer == null) {
                log.fine(CLASS_NAME, "sendNoWait", "208");
                throw ExceptionHelper.createMqttException(32104);
            }
            log.fine(CLASS_NAME, "sendNoWait", "508", new Object[]{mqttWireMessage.getKey()});
            if (this.disconnectedMessageBuffer.isPersistBuffer()) {
                this.clientState.persistBufferedMessage(mqttWireMessage);
            }
            this.disconnectedMessageBuffer.putMessage(mqttWireMessage, mqttToken);
            return;
        }
        DisconnectedMessageBuffer disconnectedMessageBuffer = this.disconnectedMessageBuffer;
        if (disconnectedMessageBuffer == null || disconnectedMessageBuffer.getMessageCount() == 0) {
            internalSend(mqttWireMessage, mqttToken);
            return;
        }
        log.fine(CLASS_NAME, "sendNoWait", "507", new Object[]{mqttWireMessage.getKey()});
        if (this.disconnectedMessageBuffer.isPersistBuffer()) {
            this.clientState.persistBufferedMessage(mqttWireMessage);
        }
        this.disconnectedMessageBuffer.putMessage(mqttWireMessage, mqttToken);
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.callback.setCallback(mqttCallback);
    }

    public void setDisconnectedMessageBuffer(DisconnectedMessageBuffer disconnectedMessageBuffer) {
        this.disconnectedMessageBuffer = disconnectedMessageBuffer;
    }

    public void setManualAcks(boolean z) {
        this.callback.setManualAcks(z);
    }

    public void setMessageListener(String str, IMqttMessageListener iMqttMessageListener) {
        this.callback.setMessageListener(str, iMqttMessageListener);
    }

    public void setNetworkModuleIndex(int i) {
        this.networkModuleIndex = i;
    }

    public void setNetworkModules(NetworkModule[] networkModuleArr) {
        this.networkModules = networkModuleArr;
    }

    public void setReconnectCallback(MqttCallbackExtended mqttCallbackExtended) {
        this.callback.setReconnectCallback(mqttCallbackExtended);
    }

    public void setRestingState(boolean z) {
        this.resting = z;
    }

    public void shutdownConnection(MqttToken mqttToken, MqttException mqttException) {
        CommsCallback commsCallback;
        NetworkModule networkModule;
        synchronized (this.conLock) {
            if (!this.stoppingComms && !this.closePending && !isClosed()) {
                this.stoppingComms = true;
                log.fine(CLASS_NAME, "shutdownConnection", "216");
                boolean z = isConnected() || isDisconnecting();
                this.conState = (byte) 2;
                if (mqttToken != null && !mqttToken.isComplete()) {
                    mqttToken.internalTok.setException(mqttException);
                }
                CommsCallback commsCallback2 = this.callback;
                if (commsCallback2 != null) {
                    commsCallback2.stop();
                }
                CommsReceiver commsReceiver = this.receiver;
                if (commsReceiver != null) {
                    commsReceiver.stop();
                }
                try {
                    if (this.networkModules != null && (networkModule = this.networkModules[this.networkModuleIndex]) != null) {
                        networkModule.stop();
                    }
                } catch (Exception unused) {
                }
                this.tokenStore.quiesce(new MqttException(32102));
                MqttToken handleOldTokens = handleOldTokens(mqttToken, mqttException);
                try {
                    this.clientState.disconnected(mqttException);
                    if (this.clientState.getCleanSession()) {
                        this.callback.removeMessageListeners();
                    }
                } catch (Exception unused2) {
                }
                CommsSender commsSender = this.sender;
                if (commsSender != null) {
                    commsSender.stop();
                }
                MqttPingSender mqttPingSender = this.pingSender;
                if (mqttPingSender != null) {
                    mqttPingSender.stop();
                }
                try {
                    if (this.disconnectedMessageBuffer == null && this.persistence != null) {
                        this.persistence.close();
                    }
                } catch (Exception unused3) {
                }
                synchronized (this.conLock) {
                    log.fine(CLASS_NAME, "shutdownConnection", "217");
                    this.conState = (byte) 3;
                    this.stoppingComms = false;
                }
                if ((handleOldTokens != null) & (this.callback != null)) {
                    this.callback.asyncOperationComplete(handleOldTokens);
                }
                if (z && (commsCallback = this.callback) != null) {
                    commsCallback.connectionLost(mqttException);
                }
                synchronized (this.conLock) {
                    if (this.closePending) {
                        try {
                            close(true);
                        } catch (Exception unused4) {
                        }
                    }
                }
            }
        }
    }
}
