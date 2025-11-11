package me.jessyan.rxerrorhandler.core;

import android.content.Context;
import me.jessyan.rxerrorhandler.handler.ErrorHandlerFactory;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

/* loaded from: classes2.dex */
public class RxErrorHandler {
    public final String TAG;
    private ErrorHandlerFactory mHandlerFactory;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private Context context;
        private ErrorHandlerFactory errorHandlerFactory;
        private ResponseErrorListener mResponseErrorListener;

        private Builder() {
        }

        public RxErrorHandler build() {
            Context context = this.context;
            if (context == null) {
                throw new IllegalStateException("Context is required");
            }
            ResponseErrorListener responseErrorListener = this.mResponseErrorListener;
            if (responseErrorListener == null) {
                throw new IllegalStateException("ResponseErrorListener is required");
            }
            this.errorHandlerFactory = new ErrorHandlerFactory(context, responseErrorListener);
            return new RxErrorHandler(this);
        }

        public Builder responseErrorListener(ResponseErrorListener responseErrorListener) {
            this.mResponseErrorListener = responseErrorListener;
            return this;
        }

        public Builder with(Context context) {
            this.context = context;
            return this;
        }
    }

    private RxErrorHandler(Builder builder) {
        this.TAG = getClass().getSimpleName();
        this.mHandlerFactory = builder.errorHandlerFactory;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ErrorHandlerFactory getHandlerFactory() {
        return this.mHandlerFactory;
    }
}
