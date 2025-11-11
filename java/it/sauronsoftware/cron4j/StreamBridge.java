package it.sauronsoftware.cron4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class StreamBridge {
    private static ArrayList traced = new ArrayList();
    private InputStream in;
    private OutputStream out;
    private StreamBridge myself = this;
    private Thread thread = new Thread(new Runner());

    /* loaded from: classes2.dex */
    private class Runner implements Runnable {
        private Runner() {
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z = false;
            while (true) {
                try {
                    int read = StreamBridge.this.in.read();
                    if (read != -1) {
                        if (!z) {
                            try {
                                StreamBridge.this.out.write(read);
                            } catch (IOException e) {
                                if (!Thread.interrupted()) {
                                    e.printStackTrace();
                                }
                                z = true;
                            }
                        }
                    }
                } catch (IOException e2) {
                    if (!Thread.interrupted()) {
                        e2.printStackTrace();
                    }
                }
                try {
                    break;
                } catch (Throwable unused) {
                }
            }
            StreamBridge.this.out.close();
            try {
                StreamBridge.this.in.close();
            } catch (Throwable unused2) {
            }
            synchronized (StreamBridge.traced) {
                StreamBridge.traced.remove(StreamBridge.this.myself);
            }
        }
    }

    public StreamBridge(InputStream inputStream, OutputStream outputStream) {
        this.in = inputStream;
        this.out = outputStream;
        synchronized (traced) {
            traced.add(this);
        }
    }

    public void abort() {
        this.thread.interrupt();
        try {
            this.out.close();
        } catch (Throwable unused) {
        }
        try {
            this.in.close();
        } catch (Throwable unused2) {
        }
    }

    public boolean isAlive() {
        return this.thread.isAlive();
    }

    public void join() throws InterruptedException {
        this.thread.join();
    }

    public void join(long j) throws InterruptedException {
        this.thread.join(j);
    }

    public void join(long j, int i) throws IllegalArgumentException, InterruptedException {
        this.thread.join(j, i);
    }

    public void start() {
        this.thread.start();
    }
}
