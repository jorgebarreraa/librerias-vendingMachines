package com.jess.arms.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes.dex */
public interface GlobalHttpHandler {
    public static final GlobalHttpHandler EMPTY = new GlobalHttpHandler() { // from class: com.jess.arms.http.GlobalHttpHandler.1
        @Override // com.jess.arms.http.GlobalHttpHandler
        @NonNull
        public Request onHttpRequestBefore(@NonNull Interceptor.Chain chain, @NonNull Request request) {
            return request;
        }

        @Override // com.jess.arms.http.GlobalHttpHandler
        @NonNull
        public Response onHttpResultResponse(@Nullable String str, @NonNull Interceptor.Chain chain, @NonNull Response response) {
            return response;
        }
    };

    @NonNull
    Request onHttpRequestBefore(@NonNull Interceptor.Chain chain, @NonNull Request request);

    @NonNull
    Response onHttpResultResponse(@Nullable String str, @NonNull Interceptor.Chain chain, @NonNull Response response);
}
