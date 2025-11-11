package me.jessyan.progressmanager.body;

import android.os.Handler;
import android.os.SystemClock;
import java.io.IOException;
import java.util.List;
import me.jessyan.progressmanager.ProgressListener;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/* loaded from: classes2.dex */
public class ProgressRequestBody extends RequestBody {
    private BufferedSink mBufferedSink;
    protected final RequestBody mDelegate;
    protected Handler mHandler;
    protected final ProgressListener[] mListeners;
    protected final ProgressInfo mProgressInfo = new ProgressInfo(System.currentTimeMillis());
    protected int mRefreshTime;

    /* loaded from: classes2.dex */
    protected final class CountingSink extends ForwardingSink {
        private long lastRefreshTime;
        private long tempSize;
        private long totalBytesRead;

        public CountingSink(Sink sink) {
            super(sink);
            this.totalBytesRead = 0L;
            this.lastRefreshTime = 0L;
            this.tempSize = 0L;
        }

        @Override // okio.ForwardingSink, okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            try {
                super.write(buffer, j);
                if (ProgressRequestBody.this.mProgressInfo.getContentLength() == 0) {
                    ProgressRequestBody.this.mProgressInfo.setContentLength(ProgressRequestBody.this.contentLength());
                }
                this.totalBytesRead += j;
                this.tempSize += j;
                if (ProgressRequestBody.this.mListeners != null) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (elapsedRealtime - this.lastRefreshTime >= ProgressRequestBody.this.mRefreshTime || this.totalBytesRead == ProgressRequestBody.this.mProgressInfo.getContentLength()) {
                        long j2 = this.tempSize;
                        final long j3 = this.totalBytesRead;
                        final long j4 = elapsedRealtime - this.lastRefreshTime;
                        int i = 0;
                        while (i < ProgressRequestBody.this.mListeners.length) {
                            final ProgressListener progressListener = ProgressRequestBody.this.mListeners[i];
                            final long j5 = j2;
                            ProgressRequestBody.this.mHandler.post(new Runnable() { // from class: me.jessyan.progressmanager.body.ProgressRequestBody.CountingSink.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    ProgressRequestBody.this.mProgressInfo.setEachBytes(j5);
                                    ProgressRequestBody.this.mProgressInfo.setCurrentbytes(j3);
                                    ProgressRequestBody.this.mProgressInfo.setIntervalTime(j4);
                                    ProgressRequestBody.this.mProgressInfo.setFinish(j3 == ProgressRequestBody.this.mProgressInfo.getContentLength());
                                    progressListener.onProgress(ProgressRequestBody.this.mProgressInfo);
                                }
                            });
                            i++;
                            j2 = j2;
                        }
                        this.lastRefreshTime = elapsedRealtime;
                        this.tempSize = 0L;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                for (int i2 = 0; i2 < ProgressRequestBody.this.mListeners.length; i2++) {
                    ProgressRequestBody.this.mListeners[i2].onError(ProgressRequestBody.this.mProgressInfo.getId(), e);
                }
                throw e;
            }
        }
    }

    public ProgressRequestBody(Handler handler, RequestBody requestBody, List<ProgressListener> list, int i) {
        this.mDelegate = requestBody;
        this.mListeners = (ProgressListener[]) list.toArray(new ProgressListener[list.size()]);
        this.mHandler = handler;
        this.mRefreshTime = i;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        try {
            return this.mDelegate.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    @Override // okhttp3.RequestBody
    /* renamed from: contentType */
    public MediaType getContentType() {
        return this.mDelegate.getContentType();
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        if (this.mBufferedSink == null) {
            this.mBufferedSink = Okio.buffer(new CountingSink(bufferedSink));
        }
        try {
            this.mDelegate.writeTo(this.mBufferedSink);
            this.mBufferedSink.flush();
        } catch (IOException e) {
            e.printStackTrace();
            int i = 0;
            while (true) {
                ProgressListener[] progressListenerArr = this.mListeners;
                if (i >= progressListenerArr.length) {
                    break;
                }
                progressListenerArr[i].onError(this.mProgressInfo.getId(), e);
                i++;
            }
            throw e;
        }
    }
}
