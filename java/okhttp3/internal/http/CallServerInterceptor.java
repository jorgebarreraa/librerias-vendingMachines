package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okio.BufferedSink;
import okio.Okio;
import org.jetbrains.annotations.NotNull;

/* compiled from: CallServerInterceptor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "forWebSocket", "", "(Z)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "okhttp"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes2.dex */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        boolean z;
        Intrinsics.checkNotNullParameter(chain, "chain");
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Exchange exchange = realInterceptorChain.getExchange();
        Intrinsics.checkNotNull(exchange);
        Request request = realInterceptorChain.getRequest();
        RequestBody body = request.body();
        long currentTimeMillis = System.currentTimeMillis();
        exchange.writeRequestHeaders(request);
        Response.Builder builder = (Response.Builder) null;
        if (!HttpMethod.permitsRequestBody(request.method()) || body == null) {
            exchange.noRequestBody();
            z = true;
        } else {
            if (StringsKt.equals("100-continue", request.header("Expect"), true)) {
                exchange.flushRequest();
                builder = exchange.readResponseHeaders(true);
                exchange.responseHeadersStart();
                z = false;
            } else {
                z = true;
            }
            if (builder != null) {
                exchange.noRequestBody();
                if (!exchange.getConnection().isMultiplexed$okhttp()) {
                    exchange.noNewExchangesOnConnection();
                }
            } else if (body.isDuplex()) {
                exchange.flushRequest();
                body.writeTo(Okio.buffer(exchange.createRequestBody(request, true)));
            } else {
                BufferedSink buffer = Okio.buffer(exchange.createRequestBody(request, false));
                body.writeTo(buffer);
                buffer.close();
            }
        }
        if (body == null || !body.isDuplex()) {
            exchange.finishRequest();
        }
        if (builder == null) {
            builder = exchange.readResponseHeaders(false);
            Intrinsics.checkNotNull(builder);
            if (z) {
                exchange.responseHeadersStart();
                z = false;
            }
        }
        Response build = builder.request(request).handshake(exchange.getConnection().getHandshake()).sentRequestAtMillis(currentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        int code = build.code();
        if (code == 100) {
            Response.Builder readResponseHeaders = exchange.readResponseHeaders(false);
            Intrinsics.checkNotNull(readResponseHeaders);
            if (z) {
                exchange.responseHeadersStart();
            }
            build = readResponseHeaders.request(request).handshake(exchange.getConnection().getHandshake()).sentRequestAtMillis(currentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
            code = build.code();
        }
        exchange.responseHeadersEnd(build);
        Response build2 = (this.forWebSocket && code == 101) ? build.newBuilder().body(Util.EMPTY_RESPONSE).build() : build.newBuilder().body(exchange.openResponseBody(build)).build();
        if (StringsKt.equals("close", build2.request().header("Connection"), true) || StringsKt.equals("close", Response.header$default(build2, "Connection", null, 2, null), true)) {
            exchange.noNewExchangesOnConnection();
        }
        if (code == 204 || code == 205) {
            ResponseBody body2 = build2.body();
            if ((body2 != null ? body2.getContentLength() : -1L) > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("HTTP ");
                sb.append(code);
                sb.append(" had non-zero Content-Length: ");
                ResponseBody body3 = build2.body();
                sb.append(body3 != null ? Long.valueOf(body3.getContentLength()) : null);
                throw new ProtocolException(sb.toString());
            }
        }
        return build2;
    }
}
