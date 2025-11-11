package com.yj.coffeemachines.app.serialport;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.jess.arms.utils.ArmsUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.app.serialport.listener.OnOpenSerialPortListener;
import com.yj.coffeemachines.app.serialport.listener.OnSerialPortDataListener;
import com.yj.coffeemachines.app.serialport.thread.SerialPortReadThread;
import com.yj.coffeemachines.app.utils.ByteUtils;
import com.yj.coffeemachines.exception.SerialPortOpenFailureException;
import com.yj.coffeemachines.helper.Tools;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class SerialPortManager extends SerialPort {
    private static final String TAG = "SerialPortManager";
    private long interval;
    private boolean isOpen;
    boolean isSend;
    boolean listEmpty;
    private FileDescriptor mFd;
    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;
    private OnOpenSerialPortListener mOnOpenSerialPortListener;
    private OnSerialPortDataListener mOnSerialPortDataListener;
    private ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor;
    private ConfigurationSdk mSdk;
    private Handler mSendingHandler;
    private HandlerThread mSendingHandlerThread;
    private SerialPortReadThread mSerialPortReadThread;
    long receiveMills;
    private final BlockingQueue<byte[]> sendMainQueue;
    private final BlockingQueue<byte[]> sendQueue;

    public SerialPortManager() {
        this.isOpen = false;
        this.interval = 700L;
        this.sendQueue = new LinkedBlockingQueue();
        this.sendMainQueue = new LinkedBlockingQueue();
        this.listEmpty = false;
        this.isSend = false;
        this.receiveMills = 0L;
    }

    public SerialPortManager(long j) {
        this.isOpen = false;
        this.interval = 700L;
        this.sendQueue = new LinkedBlockingQueue();
        this.sendMainQueue = new LinkedBlockingQueue();
        this.listEmpty = false;
        this.isSend = false;
        this.receiveMills = 0L;
        this.interval = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBytes(byte[] bArr) {
        if (this.mFd == null || this.mFileInputStream == null || this.mFileOutputStream == null || this.mSendingHandler == null) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.obj = bArr;
        this.mSendingHandler.sendMessage(obtain);
    }

    private void startReadThread() {
        this.mSerialPortReadThread = new SerialPortReadThread(this.mFileInputStream, this.mSdk) { // from class: com.yj.coffeemachines.app.serialport.SerialPortManager.3
            @Override // com.yj.coffeemachines.app.serialport.thread.SerialPortReadThread
            public void onDataReceived(byte[] bArr) {
                if (SerialPortManager.this.mOnSerialPortDataListener != null) {
                    if (!SerialPortManager.this.mSdk.getDevice().getAbsolutePath().equals(Constants.SERIAPort_currentgrinding())) {
                        if (ByteUtils.checkByte(bArr)) {
                            SerialPortManager.this.mOnSerialPortDataListener.onDataReceived(SerialPortManager.this.mSdk.getDevice(), bArr, true, true);
                            return;
                        }
                        if (bArr.length <= 11) {
                            if (ByteUtils.byte2HexStr2(bArr).contains("0D 0A")) {
                                SerialPortManager.this.mOnSerialPortDataListener.onDataReceived(SerialPortManager.this.mSdk.getDevice(), bArr, true, true);
                                return;
                            } else {
                                SerialPortManager.this.mOnSerialPortDataListener.onDataReceived(SerialPortManager.this.mSdk.getDevice(), bArr, false, true);
                                return;
                            }
                        }
                        String[] split = ByteUtils.byte2HexStr2(bArr).split("A5 5A ");
                        if (split.length <= 1) {
                            SerialPortManager.this.mOnSerialPortDataListener.onDataReceived(SerialPortManager.this.mSdk.getDevice(), bArr, true, true);
                            return;
                        }
                        for (int i = 1; i < split.length; i++) {
                            byte[] hexTobytes = ByteUtils.hexTobytes(("A5 5A " + split[i]).replaceAll(" ", ""));
                            if (ByteUtils.checkByte(hexTobytes)) {
                                SerialPortManager.this.mOnSerialPortDataListener.onDataReceived(SerialPortManager.this.mSdk.getDevice(), hexTobytes, true, true);
                            } else {
                                Tools.upLocalLog("2速溶或支付数据丢包" + Tools.byteToStr(hexTobytes));
                                SerialPortManager.this.mOnSerialPortDataListener.onDataReceived(SerialPortManager.this.mSdk.getDevice(), hexTobytes, false, true);
                            }
                        }
                        return;
                    }
                    if (bArr.length != 21) {
                        SerialPortManager.this.mOnSerialPortDataListener.onDataReceived(SerialPortManager.this.mSdk.getDevice(), bArr, false, false);
                        return;
                    }
                    if (bArr[0] != 85 || bArr[1] != -86) {
                        Tools.upLocalLog("校验现磨数据协议头失败");
                        SerialPortManager.this.mOnSerialPortDataListener.onDataReceived(SerialPortManager.this.mSdk.getDevice(), bArr, false, false);
                        return;
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    if (bArr[6] == Byte.MAX_VALUE) {
                        Message obtain = Message.obtain();
                        obtain.obj = MyAppLocation.myAppLocation.mSerialDataService.standyOrder;
                        SerialPortManager.this.mSendingHandler.sendMessage(obtain);
                        SerialPortManager.this.isSend = true;
                    }
                    if (SerialPortManager.this.isSend) {
                        SerialPortManager.this.mOnSerialPortDataListener.onDataReceived(SerialPortManager.this.mSdk.getDevice(), bArr, true, true);
                        SerialPortManager.this.isSend = false;
                    } else if (currentTimeMillis - SerialPortManager.this.receiveMills > 3000) {
                        if (Constants.ISMAKINGADRINK) {
                            SerialPortManager serialPortManager = SerialPortManager.this;
                            serialPortManager.receiveMills = currentTimeMillis;
                            serialPortManager.mOnSerialPortDataListener.onDataReceived(SerialPortManager.this.mSdk.getDevice(), bArr, true, false);
                        } else {
                            SerialPortManager serialPortManager2 = SerialPortManager.this;
                            serialPortManager2.receiveMills = currentTimeMillis;
                            serialPortManager2.mOnSerialPortDataListener.onDataReceived(SerialPortManager.this.mSdk.getDevice(), bArr, true, true);
                        }
                    }
                }
            }
        };
        this.mSerialPortReadThread.start();
    }

    private void startSendThread() {
        if (this.mSendingHandlerThread != null) {
            return;
        }
        this.mSendingHandlerThread = new HandlerThread("mSendingHandlerThread");
        this.mSendingHandlerThread.start();
        this.mSendingHandler = new Handler(this.mSendingHandlerThread.getLooper()) { // from class: com.yj.coffeemachines.app.serialport.SerialPortManager.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                byte[] bArr = (byte[]) message.obj;
                if (SerialPortManager.this.mFileOutputStream == null || bArr == null || bArr.length <= 0) {
                    return;
                }
                try {
                    SerialPortManager.this.mFileOutputStream.write(bArr);
                    if (SerialPortManager.this.mOnSerialPortDataListener != null) {
                        SerialPortManager.this.mOnSerialPortDataListener.onDataSent(SerialPortManager.this.mSdk.getDevice(), bArr);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void stopReadThread() {
        SerialPortReadThread serialPortReadThread = this.mSerialPortReadThread;
        if (serialPortReadThread != null) {
            serialPortReadThread.release();
        }
    }

    private void stopSendThread() {
        this.mSendingHandler = null;
        HandlerThread handlerThread = this.mSendingHandlerThread;
        if (handlerThread != null) {
            handlerThread.interrupt();
            this.mSendingHandlerThread.quit();
            this.mSendingHandlerThread = null;
        }
    }

    public void addSendList(byte[] bArr) {
        if (!this.isOpen || bArr == null) {
            return;
        }
        this.sendQueue.add(bArr);
    }

    public void addSendMainList(byte[] bArr) {
        if (!this.isOpen || bArr == null) {
            return;
        }
        this.sendMainQueue.add(bArr);
    }

    public void cleanSendByteToList() {
        this.listEmpty = true;
    }

    public void cleanSendList() {
        this.sendQueue.clear();
    }

    public void closeSerialPort() {
        if (this.mFd != null) {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mFd = null;
        }
        stopSendThread();
        stopReadThread();
        FileInputStream fileInputStream = this.mFileInputStream;
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.mFileInputStream = null;
        }
        FileOutputStream fileOutputStream = this.mFileOutputStream;
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            this.mFileOutputStream = null;
        }
    }

    public void init(ConfigurationSdk configurationSdk) throws SerialPortOpenFailureException {
        this.mSdk = configurationSdk;
        if (!openSerialPort()) {
            throw new SerialPortOpenFailureException("串口打开失败，路径：" + configurationSdk.getDevice().getAbsolutePath());
        }
        if (this.mScheduledThreadPoolExecutor == null) {
            this.mScheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) ArmsUtils.obtainAppComponentFromContext(MyAppLocation.myAppLocation).executorService();
            this.mScheduledThreadPoolExecutor.scheduleWithFixedDelay(new Runnable() { // from class: com.yj.coffeemachines.app.serialport.SerialPortManager.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (SerialPortManager.this.listEmpty) {
                            if (!SerialPortManager.this.sendQueue.isEmpty()) {
                                SerialPortManager.this.sendQueue.clear();
                            }
                            if (!SerialPortManager.this.sendMainQueue.isEmpty()) {
                                SerialPortManager.this.sendMainQueue.clear();
                            }
                            SerialPortManager.this.listEmpty = false;
                            return;
                        }
                        if (!SerialPortManager.this.sendMainQueue.isEmpty()) {
                            byte[] bArr = (byte[]) SerialPortManager.this.sendMainQueue.take();
                            if (SerialPortManager.this.listEmpty) {
                                return;
                            }
                            SerialPortManager.this.sendBytes(bArr);
                            return;
                        }
                        if (SerialPortManager.this.sendQueue.isEmpty()) {
                            return;
                        }
                        byte[] bArr2 = (byte[]) SerialPortManager.this.sendQueue.take();
                        if (SerialPortManager.this.listEmpty) {
                            return;
                        }
                        SerialPortManager.this.sendBytes(bArr2);
                    } catch (Exception e) {
                        Tools.upLocalLogTest("指令发送失败：" + e.getMessage());
                    }
                }
            }, 0L, this.interval, TimeUnit.MILLISECONDS);
        }
    }

    public boolean openSerialPort() {
        if ((!this.mSdk.getDevice().canRead() || !this.mSdk.getDevice().canWrite()) && !chmod777(this.mSdk.getDevice())) {
            OnOpenSerialPortListener onOpenSerialPortListener = this.mOnOpenSerialPortListener;
            if (onOpenSerialPortListener != null) {
                onOpenSerialPortListener.onFail(this.mSdk.getDevice(), OnOpenSerialPortListener.Status.NO_READ_WRITE_PERMISSION);
            }
            return false;
        }
        if (!this.mSdk.getDevice().canRead() || !this.mSdk.getDevice().canWrite()) {
            try {
                ArrayList arrayList = new ArrayList();
                arrayList.add("chmod 777 " + this.mSdk.getDevice().getAbsolutePath());
                ShellUtils.execCommand((List) arrayList, true);
            } catch (Exception e) {
                e.printStackTrace();
                throw new SecurityException();
            }
        }
        try {
            this.mFd = open(this.mSdk.getDevice().getAbsolutePath(), this.mSdk.getBaudRate(), 0);
            this.mFileInputStream = new FileInputStream(this.mFd);
            this.mFileOutputStream = new FileOutputStream(this.mFd);
            this.isOpen = true;
            startSendThread();
            startReadThread();
            if (this.mOnOpenSerialPortListener != null) {
                this.mOnOpenSerialPortListener.onSuccess(this.mSdk.getDevice());
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            OnOpenSerialPortListener onOpenSerialPortListener2 = this.mOnOpenSerialPortListener;
            if (onOpenSerialPortListener2 != null) {
                onOpenSerialPortListener2.onFail(this.mSdk.getDevice(), OnOpenSerialPortListener.Status.OPEN_FAIL);
            }
            this.isOpen = false;
            return false;
        }
    }

    public SerialPortManager setOnOpenSerialPortListener(OnOpenSerialPortListener onOpenSerialPortListener) {
        this.mOnOpenSerialPortListener = onOpenSerialPortListener;
        return this;
    }

    public SerialPortManager setOnSerialPortDataListener(OnSerialPortDataListener onSerialPortDataListener) {
        this.mOnSerialPortDataListener = onSerialPortDataListener;
        return this;
    }
}
