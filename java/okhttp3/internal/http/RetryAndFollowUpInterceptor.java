package okhttp3.internal.http;

import androidx.core.app.NotificationCompat;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import me.jessyan.progressmanager.ProgressManager;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection;
import org.jetbrains.annotations.NotNull;

/* compiled from: RetryAndFollowUpInterceptor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J(\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor;", "Lokhttp3/Interceptor;", "client", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "buildRedirectRequest", "Lokhttp3/Request;", "userResponse", "Lokhttp3/Response;", "method", "", "followUpRequest", "exchange", "Lokhttp3/internal/connection/Exchange;", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "isRecoverable", "", "e", "Ljava/io/IOException;", "requestSendStarted", "recover", NotificationCompat.CATEGORY_CALL, "Lokhttp3/internal/connection/RealCall;", "userRequest", "requestIsOneShot", "retryAfter", "", "defaultDelay", "Companion", "okhttp"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes2.dex */
public final class RetryAndFollowUpInterceptor implements Interceptor {
    private static final int MAX_FOLLOW_UPS = 20;
    private final OkHttpClient client;

    public RetryAndFollowUpInterceptor(@NotNull OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        this.client = client;
    }

    private final Request buildRedirectRequest(Response userResponse, String method) {
        String header$default;
        HttpUrl resolve;
        if (!this.client.followRedirects() || (header$default = Response.header$default(userResponse, ProgressManager.LOCATION_HEADER, null, 2, null)) == null || (resolve = userResponse.request().url().resolve(header$default)) == null) {
            return null;
        }
        if (!Intrinsics.areEqual(resolve.scheme(), userResponse.request().url().scheme()) && !this.client.followSslRedirects()) {
            return null;
        }
        Request.Builder newBuilder = userResponse.request().newBuilder();
        if (HttpMethod.permitsRequestBody(method)) {
            int code = userResponse.code();
            boolean z = HttpMethod.INSTANCE.redirectsWithBody(method) || code == 308 || code == 307;
            if (!HttpMethod.INSTANCE.redirectsToGet(method) || code == 308 || code == 307) {
                newBuilder.method(method, z ? userResponse.request().body() : null);
            } else {
                newBuilder.method("GET", null);
            }
            if (!z) {
                newBuilder.removeHeader("Transfer-Encoding");
                newBuilder.removeHeader("Content-Length");
                newBuilder.removeHeader("Content-Type");
            }
        }
        if (!Util.canReuseConnectionFor(userResponse.request().url(), resolve)) {
            newBuilder.removeHeader("Authorization");
        }
        return newBuilder.url(resolve).build();
    }

    private final Request followUpRequest(Response userResponse, Exchange exchange) throws IOException {
        RealConnection connection;
        Route route = (exchange == null || (connection = exchange.getConnection()) == null) ? null : connection.getRoute();
        int code = userResponse.code();
        String method = userResponse.request().method();
        if (code != 307 && code != 308) {
            if (code == 401) {
                return this.client.authenticator().authenticate(route, userResponse);
            }
            if (code == 421) {
                RequestBody body = userResponse.request().body();
                if ((body != null && body.isOneShot()) || exchange == null || !exchange.isCoalescedConnection$okhttp()) {
                    return null;
                }
                exchange.getConnection().noCoalescedConnections$okhttp();
                return userResponse.request();
            }
            if (code == 503) {
                Response priorResponse = userResponse.priorResponse();
                if ((priorResponse == null || priorResponse.code() != 503) && retryAfter(userResponse, Integer.MAX_VALUE) == 0) {
                    return userResponse.request();
                }
                return null;
            }
            if (code == 407) {
                Intrinsics.checkNotNull(route);
                if (route.proxy().type() == Proxy.Type.HTTP) {
                    return this.client.proxyAuthenticator().authenticate(route, userResponse);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            if (code == 408) {
                if (!this.client.retryOnConnectionFailure()) {
                    return null;
                }
                RequestBody body2 = userResponse.request().body();
                if (body2 != null && body2.isOneShot()) {
                    return null;
                }
                Response priorResponse2 = userResponse.priorResponse();
                if ((priorResponse2 == null || priorResponse2.code() != 408) && retryAfter(userResponse, 0) <= 0) {
                    return userResponse.request();
                }
                return null;
            }
            switch (code) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        return buildRedirectRequest(userResponse, method);
    }

    private final boolean isRecoverable(IOException e, boolean requestSendStarted) {
        if (e instanceof ProtocolException) {
            return false;
        }
        return e instanceof InterruptedIOException ? (e instanceof SocketTimeoutException) && !requestSendStarted : (((e instanceof SSLHandshakeException) && (e.getCause() instanceof CertificateException)) || (e instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private final boolean recover(IOException e, RealCall call, Request userRequest, boolean requestSendStarted) {
        if (this.client.retryOnConnectionFailure()) {
            return !(requestSendStarted && requestIsOneShot(e, userRequest)) && isRecoverable(e, requestSendStarted) && call.retryAfterFailure();
        }
        return false;
    }

    private final boolean requestIsOneShot(IOException e, Request userRequest) {
        RequestBody body = userRequest.body();
        return (body != null && body.isOneShot()) || (e instanceof FileNotFoundException);
    }

    private final int retryAfter(Response userResponse, int defaultDelay) {
        String header$default = Response.header$default(userResponse, "Retry-After", null, 2, null);
        if (header$default == null) {
            return defaultDelay;
        }
        if (!new Regex("\\d+").matches(header$default)) {
            return Integer.MAX_VALUE;
        }
        Integer valueOf = Integer.valueOf(header$default);
        Intrinsics.checkNotNullExpressionValue(valueOf, "Integer.valueOf(header)");
        return valueOf.intValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002a, code lost:
    
        if (r4 == null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002c, code lost:
    
        r0 = r0.newBuilder().priorResponse(r4.newBuilder().body(null).build()).build();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0044, code lost:
    
        r4 = r0;
        r0 = r1.getInterceptorScopedExchange();
        r3 = followUpRequest(r4, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004d, code lost:
    
        if (r3 != null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x005e, code lost:
    
        r0 = r3.body();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0062, code lost:
    
        if (r0 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0068, code lost:
    
        if (r0.isOneShot() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006a, code lost:
    
        r1.exitNetworkInterceptorExchange$okhttp(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006d, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006e, code lost:
    
        r0 = r4.body();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0072, code lost:
    
        if (r0 == null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0074, code lost:
    
        okhttp3.internal.Util.closeQuietly(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0079, code lost:
    
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x007d, code lost:
    
        if (r8 > 20) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x009c, code lost:
    
        throw new java.net.ProtocolException("Too many follow-up requests: " + r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x004f, code lost:
    
        if (r0 == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0055, code lost:
    
        if (r0.getIsDuplex() == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0057, code lost:
    
        r1.timeoutEarlyExit();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x005a, code lost:
    
        r1.exitNetworkInterceptorExchange$okhttp(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x005d, code lost:
    
        return r4;
     */
    @Override // okhttp3.Interceptor
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull okhttp3.Interceptor.Chain r11) throws java.io.IOException {
        /*
            r10 = this;
            java.lang.String r0 = "chain"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            okhttp3.internal.http.RealInterceptorChain r11 = (okhttp3.internal.http.RealInterceptorChain) r11
            okhttp3.Request r0 = r11.getRequest()
            okhttp3.internal.connection.RealCall r1 = r11.getCall()
            r2 = 0
            r3 = r2
            okhttp3.Response r3 = (okhttp3.Response) r3
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
            r5 = 1
            r6 = 0
            r7 = r4
            r8 = 0
            r4 = r3
        L1c:
            r3 = 1
        L1d:
            r1.enterNetworkInterceptorExchange(r0, r3)
            boolean r3 = r1.getCanceled()     // Catch: java.lang.Throwable -> Le9
            if (r3 != 0) goto Ldf
            okhttp3.Response r0 = r11.proceed(r0)     // Catch: java.io.IOException -> L9d okhttp3.internal.connection.RouteException -> Lb9 java.lang.Throwable -> Le9
            if (r4 == 0) goto L44
            okhttp3.Response$Builder r0 = r0.newBuilder()     // Catch: java.lang.Throwable -> Le9
            okhttp3.Response$Builder r3 = r4.newBuilder()     // Catch: java.lang.Throwable -> Le9
            okhttp3.Response$Builder r3 = r3.body(r2)     // Catch: java.lang.Throwable -> Le9
            okhttp3.Response r3 = r3.build()     // Catch: java.lang.Throwable -> Le9
            okhttp3.Response$Builder r0 = r0.priorResponse(r3)     // Catch: java.lang.Throwable -> Le9
            okhttp3.Response r0 = r0.build()     // Catch: java.lang.Throwable -> Le9
        L44:
            r4 = r0
            okhttp3.internal.connection.Exchange r0 = r1.getInterceptorScopedExchange()     // Catch: java.lang.Throwable -> Le9
            okhttp3.Request r3 = r10.followUpRequest(r4, r0)     // Catch: java.lang.Throwable -> Le9
            if (r3 != 0) goto L5e
            if (r0 == 0) goto L5a
            boolean r11 = r0.getIsDuplex()     // Catch: java.lang.Throwable -> Le9
            if (r11 == 0) goto L5a
            r1.timeoutEarlyExit()     // Catch: java.lang.Throwable -> Le9
        L5a:
            r1.exitNetworkInterceptorExchange$okhttp(r6)
            return r4
        L5e:
            okhttp3.RequestBody r0 = r3.body()     // Catch: java.lang.Throwable -> Le9
            if (r0 == 0) goto L6e
            boolean r0 = r0.isOneShot()     // Catch: java.lang.Throwable -> Le9
            if (r0 == 0) goto L6e
            r1.exitNetworkInterceptorExchange$okhttp(r6)
            return r4
        L6e:
            okhttp3.ResponseBody r0 = r4.body()     // Catch: java.lang.Throwable -> Le9
            if (r0 == 0) goto L79
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch: java.lang.Throwable -> Le9
            okhttp3.internal.Util.closeQuietly(r0)     // Catch: java.lang.Throwable -> Le9
        L79:
            int r8 = r8 + 1
            r0 = 20
            if (r8 > r0) goto L84
            r1.exitNetworkInterceptorExchange$okhttp(r5)
            r0 = r3
            goto L1c
        L84:
            java.net.ProtocolException r11 = new java.net.ProtocolException     // Catch: java.lang.Throwable -> Le9
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Le9
            r0.<init>()     // Catch: java.lang.Throwable -> Le9
            java.lang.String r2 = "Too many follow-up requests: "
            r0.append(r2)     // Catch: java.lang.Throwable -> Le9
            r0.append(r8)     // Catch: java.lang.Throwable -> Le9
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Le9
            r11.<init>(r0)     // Catch: java.lang.Throwable -> Le9
            java.lang.Throwable r11 = (java.lang.Throwable) r11     // Catch: java.lang.Throwable -> Le9
            throw r11     // Catch: java.lang.Throwable -> Le9
        L9d:
            r3 = move-exception
            boolean r9 = r3 instanceof okhttp3.internal.http2.ConnectionShutdownException     // Catch: java.lang.Throwable -> Le9
            if (r9 != 0) goto La4
            r9 = 1
            goto La5
        La4:
            r9 = 0
        La5:
            boolean r9 = r10.recover(r3, r1, r0, r9)     // Catch: java.lang.Throwable -> Le9
            if (r9 == 0) goto Lb2
            java.util.Collection r7 = (java.util.Collection) r7     // Catch: java.lang.Throwable -> Le9
            java.util.List r7 = kotlin.collections.CollectionsKt.plus(r7, r3)     // Catch: java.lang.Throwable -> Le9
            goto Lce
        Lb2:
            java.lang.Exception r3 = (java.lang.Exception) r3     // Catch: java.lang.Throwable -> Le9
            java.lang.Throwable r11 = okhttp3.internal.Util.withSuppressed(r3, r7)     // Catch: java.lang.Throwable -> Le9
            throw r11     // Catch: java.lang.Throwable -> Le9
        Lb9:
            r3 = move-exception
            java.io.IOException r9 = r3.getLastConnectException()     // Catch: java.lang.Throwable -> Le9
            boolean r9 = r10.recover(r9, r1, r0, r6)     // Catch: java.lang.Throwable -> Le9
            if (r9 == 0) goto Ld4
            java.util.Collection r7 = (java.util.Collection) r7     // Catch: java.lang.Throwable -> Le9
            java.io.IOException r3 = r3.getFirstConnectException()     // Catch: java.lang.Throwable -> Le9
            java.util.List r7 = kotlin.collections.CollectionsKt.plus(r7, r3)     // Catch: java.lang.Throwable -> Le9
        Lce:
            r1.exitNetworkInterceptorExchange$okhttp(r5)
            r3 = 0
            goto L1d
        Ld4:
            java.io.IOException r11 = r3.getFirstConnectException()     // Catch: java.lang.Throwable -> Le9
            java.lang.Exception r11 = (java.lang.Exception) r11     // Catch: java.lang.Throwable -> Le9
            java.lang.Throwable r11 = okhttp3.internal.Util.withSuppressed(r11, r7)     // Catch: java.lang.Throwable -> Le9
            throw r11     // Catch: java.lang.Throwable -> Le9
        Ldf:
            java.io.IOException r11 = new java.io.IOException     // Catch: java.lang.Throwable -> Le9
            java.lang.String r0 = "Canceled"
            r11.<init>(r0)     // Catch: java.lang.Throwable -> Le9
            java.lang.Throwable r11 = (java.lang.Throwable) r11     // Catch: java.lang.Throwable -> Le9
            throw r11     // Catch: java.lang.Throwable -> Le9
        Le9:
            r11 = move-exception
            r1.exitNetworkInterceptorExchange$okhttp(r5)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }
}
