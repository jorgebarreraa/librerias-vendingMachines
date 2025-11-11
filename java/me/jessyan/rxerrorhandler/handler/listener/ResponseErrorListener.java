package me.jessyan.rxerrorhandler.handler.listener;

import android.content.Context;

/* loaded from: classes2.dex */
public interface ResponseErrorListener {
    public static final ResponseErrorListener EMPTY = new ResponseErrorListener() { // from class: me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener.1
        @Override // me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener
        public void handleResponseError(Context context, Throwable th) {
        }
    };

    void handleResponseError(Context context, Throwable th);
}
