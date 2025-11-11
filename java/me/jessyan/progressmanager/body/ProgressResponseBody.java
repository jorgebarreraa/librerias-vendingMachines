package me.jessyan.progressmanager.body;

import android.os.Handler;
import android.os.SystemClock;
import java.io.IOException;
import java.util.List;
import me.jessyan.progressmanager.ProgressListener;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* loaded from: classes2.dex */
public class ProgressResponseBody extends ResponseBody {
    private BufferedSource mBufferedSource;
    protected final ResponseBody mDelegate;
    protected Handler mHandler;
    protected final ProgressListener[] mListeners;
    protected final ProgressInfo mProgressInfo = new ProgressInfo(System.currentTimeMillis());
    protected int mRefreshTime;

    public ProgressResponseBody(Handler handler, ResponseBody responseBody, List<ProgressListener> list, int i) {
        this.mDelegate = responseBody;
        this.mListeners = (ProgressListener[]) list.toArray(new ProgressListener[list.size()]);
        this.mHandler = handler;
        this.mRefreshTime = i;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) { // from class: me.jessyan.progressmanager.body.ProgressResponseBody.1
            private long totalBytesRead = 0;
            private long lastRefreshTime = 0;
            private long tempSize = 0;

            @Override // okio.ForwardingSource, okio.Source
            public long read(Buffer buffer, long j) throws IOException {
                AnonymousClass1 anonymousClass1 = this;
                try {
                    long read = super.read(buffer, j);
                    if (ProgressResponseBody.this.mProgressInfo.getContentLength() == 0) {
                        ProgressResponseBody.this.mProgressInfo.setContentLength(ProgressResponseBody.this.getContentLength());
                    }
                    anonymousClass1.totalBytesRead += read != -1 ? read : 0L;
                    anonymousClass1.tempSize += read != -1 ? read : 0L;
                    if (ProgressResponseBody.this.mListeners != null) {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        if (elapsedRealtime - anonymousClass1.lastRefreshTime >= ProgressResponseBody.this.mRefreshTime || read == -1 || anonymousClass1.totalBytesRead == ProgressResponseBody.this.mProgressInfo.getContentLength()) {
                            final long j2 = anonymousClass1.tempSize;
                            long j3 = anonymousClass1.totalBytesRead;
                            final long j4 = elapsedRealtime - anonymousClass1.lastRefreshTime;
                            int i = 0;
                            while (i < ProgressResponseBody.this.mListeners.length) {
                                final ProgressListener progressListener = ProgressResponseBody.this.mListeners[i];
                                final long j5 = j3;
                                final long j6 = read;
                                ProgressResponseBody.this.mHandler.post(new Runnable() { // from class: me.jessyan.progressmanager.body.ProgressResponseBody.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        ProgressResponseBody.this.mProgressInfo.setEachBytes(j6 != -1 ? j2 : -1L);
                                        ProgressResponseBody.this.mProgressInfo.setCurrentbytes(j5);
                                        ProgressResponseBody.this.mProgressInfo.setIntervalTime(j4);
                                        ProgressResponseBody.this.mProgressInfo.setFinish(j6 == -1 && j5 == ProgressResponseBody.this.mProgressInfo.getContentLength());
                                        progressListener.onProgress(ProgressResponseBody.this.mProgressInfo);
                                    }
                                });
                                i++;
                                anonymousClass1 = this;
                                elapsedRealtime = elapsedRealtime;
                                j3 = j5;
                                read = read;
                            }
                            AnonymousClass1 anonymousClass12 = anonymousClass1;
                            long j7 = read;
                            anonymousClass12.lastRefreshTime = elapsedRealtime;
                            anonymousClass12.tempSize = 0L;
                            return j7;
                        }
                    }
                    return read;
                } catch (IOException e) {
                    e.printStackTrace();
                    for (int i2 = 0; i2 < ProgressResponseBody.this.mListeners.length; i2++) {
                        ProgressResponseBody.this.mListeners[i2].onError(ProgressResponseBody.this.mProgressInfo.getId(), e);
                    }
                    throw e;
                }
            }
        };
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: contentLength */
    public long getContentLength() {
        return this.mDelegate.getContentLength();
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: contentType */
    public MediaType get$contentType() {
        return this.mDelegate.get$contentType();
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: source */
    public BufferedSource getBodySource() {
        if (this.mBufferedSource == null) {
            this.mBufferedSource = Okio.buffer(source(this.mDelegate.getBodySource()));
        }
        return this.mBufferedSource;
    }
}
