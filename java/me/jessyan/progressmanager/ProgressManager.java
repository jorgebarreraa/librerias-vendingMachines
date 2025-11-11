package me.jessyan.progressmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import me.jessyan.progressmanager.body.ProgressRequestBody;
import me.jessyan.progressmanager.body.ProgressResponseBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes2.dex */
public final class ProgressManager {
    public static final int DEFAULT_REFRESH_TIME = 150;
    public static final boolean DEPENDENCY_OKHTTP;
    public static final String IDENTIFICATION_HEADER = "JessYan";
    public static final String IDENTIFICATION_NUMBER = "?JessYan=";
    public static final String LOCATION_HEADER = "Location";
    public static final String OKHTTP_PACKAGE_NAME = "okhttp3.OkHttpClient";
    private static volatile ProgressManager mProgressManager;
    private final Map<String, List<ProgressListener>> mRequestListeners = new WeakHashMap();
    private final Map<String, List<ProgressListener>> mResponseListeners = new WeakHashMap();
    private int mRefreshTime = 150;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Interceptor mInterceptor = new Interceptor() { // from class: me.jessyan.progressmanager.ProgressManager.1
        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            ProgressManager progressManager = ProgressManager.this;
            return progressManager.wrapResponseBody(chain.proceed(progressManager.wrapRequestBody(chain.request())));
        }
    };

    static {
        boolean z;
        try {
            Class.forName(OKHTTP_PACKAGE_NAME);
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        DEPENDENCY_OKHTTP = z;
    }

    private ProgressManager() {
    }

    private void forEachListenersOnError(Map<String, List<ProgressListener>> map, String str, Exception exc) {
        if (map.containsKey(str)) {
            List<ProgressListener> list = map.get(str);
            for (ProgressListener progressListener : (ProgressListener[]) list.toArray(new ProgressListener[list.size()])) {
                progressListener.onError(-1L, exc);
            }
        }
    }

    public static final ProgressManager getInstance() {
        if (mProgressManager == null) {
            if (!DEPENDENCY_OKHTTP) {
                throw new IllegalStateException("Must be dependency Okhttp");
            }
            synchronized (ProgressManager.class) {
                if (mProgressManager == null) {
                    mProgressManager = new ProgressManager();
                }
            }
        }
        return mProgressManager;
    }

    private boolean haveRedirect(Response response) {
        String valueOf = String.valueOf(response.code());
        if (TextUtils.isEmpty(valueOf)) {
            return false;
        }
        return valueOf.contains("301") || valueOf.contains("302") || valueOf.contains("303") || valueOf.contains("307");
    }

    private Response modifyLocation(Response response, String str) {
        return (TextUtils.isEmpty(str) || !str.contains(IDENTIFICATION_NUMBER)) ? response : response.newBuilder().header(LOCATION_HEADER, str).build();
    }

    private Request pruneIdentification(String str, Request request) {
        return !str.contains(IDENTIFICATION_NUMBER) ? request : request.newBuilder().url(str.substring(0, str.indexOf(IDENTIFICATION_NUMBER))).header(IDENTIFICATION_HEADER, str).build();
    }

    private String resolveRedirect(Map<String, List<ProgressListener>> map, Response response, String str) {
        List<ProgressListener> list = map.get(str);
        if (list == null || list.size() <= 0) {
            return null;
        }
        String header = response.header(LOCATION_HEADER);
        if (TextUtils.isEmpty(header)) {
            return header;
        }
        if (str.contains(IDENTIFICATION_NUMBER) && !header.contains(IDENTIFICATION_NUMBER)) {
            header = header + str.substring(str.indexOf(IDENTIFICATION_NUMBER), str.length());
        }
        if (!map.containsKey(header)) {
            map.put(header, list);
            return header;
        }
        List<ProgressListener> list2 = map.get(header);
        for (ProgressListener progressListener : list) {
            if (!list2.contains(progressListener)) {
                list2.add(progressListener);
            }
        }
        return header;
    }

    public String addDiffRequestListenerOnSameUrl(String str, String str2, ProgressListener progressListener) {
        String str3 = str + IDENTIFICATION_NUMBER + str2;
        addRequestListener(str3, progressListener);
        return str3;
    }

    public String addDiffRequestListenerOnSameUrl(String str, ProgressListener progressListener) {
        return addDiffRequestListenerOnSameUrl(str, String.valueOf(SystemClock.elapsedRealtime() + progressListener.hashCode()), progressListener);
    }

    public String addDiffResponseListenerOnSameUrl(String str, String str2, ProgressListener progressListener) {
        String str3 = str + IDENTIFICATION_NUMBER + str2;
        addResponseListener(str3, progressListener);
        return str3;
    }

    public String addDiffResponseListenerOnSameUrl(String str, ProgressListener progressListener) {
        return addDiffResponseListenerOnSameUrl(str, String.valueOf(SystemClock.elapsedRealtime() + progressListener.hashCode()), progressListener);
    }

    public void addRequestListener(String str, ProgressListener progressListener) {
        List<ProgressListener> list;
        synchronized (ProgressManager.class) {
            list = this.mRequestListeners.get(str);
            if (list == null) {
                list = new LinkedList<>();
                this.mRequestListeners.put(str, list);
            }
        }
        list.add(progressListener);
    }

    public void addResponseListener(String str, ProgressListener progressListener) {
        List<ProgressListener> list;
        synchronized (ProgressManager.class) {
            list = this.mResponseListeners.get(str);
            if (list == null) {
                list = new LinkedList<>();
                this.mResponseListeners.put(str, list);
            }
        }
        list.add(progressListener);
    }

    public void notifyOnErorr(String str, Exception exc) {
        forEachListenersOnError(this.mRequestListeners, str, exc);
        forEachListenersOnError(this.mResponseListeners, str, exc);
    }

    public void setRefreshTime(int i) {
        this.mRefreshTime = i;
    }

    public OkHttpClient.Builder with(OkHttpClient.Builder builder) {
        return builder.addNetworkInterceptor(this.mInterceptor);
    }

    public Request wrapRequestBody(Request request) {
        if (request == null) {
            return request;
        }
        String url = request.url().getUrl();
        Request pruneIdentification = pruneIdentification(url, request);
        if (pruneIdentification.body() == null || !this.mRequestListeners.containsKey(url)) {
            return pruneIdentification;
        }
        return pruneIdentification.newBuilder().method(pruneIdentification.method(), new ProgressRequestBody(this.mHandler, pruneIdentification.body(), this.mRequestListeners.get(url), this.mRefreshTime)).build();
    }

    public Response wrapResponseBody(Response response) {
        if (response == null) {
            return response;
        }
        String url = response.request().url().getUrl();
        if (!TextUtils.isEmpty(response.request().header(IDENTIFICATION_HEADER))) {
            url = response.request().header(IDENTIFICATION_HEADER);
        }
        if (haveRedirect(response)) {
            resolveRedirect(this.mRequestListeners, response, url);
            return modifyLocation(response, resolveRedirect(this.mResponseListeners, response, url));
        }
        if (response.body() == null || !this.mResponseListeners.containsKey(url)) {
            return response;
        }
        return response.newBuilder().body(new ProgressResponseBody(this.mHandler, response.body(), this.mResponseListeners.get(url), this.mRefreshTime)).build();
    }
}
