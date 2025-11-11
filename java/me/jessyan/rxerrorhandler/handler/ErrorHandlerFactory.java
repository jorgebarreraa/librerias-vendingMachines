package me.jessyan.rxerrorhandler.handler;

import android.content.Context;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

/* loaded from: classes2.dex */
public class ErrorHandlerFactory {
    public final String TAG = getClass().getSimpleName();
    private Context mContext;
    private ResponseErrorListener mResponseErrorListener;

    public ErrorHandlerFactory(Context context, ResponseErrorListener responseErrorListener) {
        this.mResponseErrorListener = responseErrorListener;
        this.mContext = context;
    }

    public void handleError(Throwable th) {
        this.mResponseErrorListener.handleResponseError(this.mContext, th);
    }
}
