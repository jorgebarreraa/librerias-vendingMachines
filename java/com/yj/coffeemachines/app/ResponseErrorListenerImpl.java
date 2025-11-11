package com.yj.coffeemachines.app;

import android.content.Context;
import android.net.ParseException;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.jess.arms.utils.ArmsUtils;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import org.json.JSONException;
import retrofit2.HttpException;
import timber.log.Timber;

/* loaded from: classes.dex */
public class ResponseErrorListenerImpl implements ResponseErrorListener {
    private String convertStatusCode(HttpException httpException) {
        return httpException.code() == 500 ? "服务器发生错误" : httpException.code() == 404 ? "请求地址不存在" : httpException.code() == 403 ? "请求被服务器拒绝" : httpException.code() == 307 ? "请求被重定向到其他页面" : httpException.message();
    }

    @Override // me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener
    public void handleResponseError(Context context, Throwable th) {
        Timber.tag("Catch-Error").w(th.getMessage(), new Object[0]);
        ArmsUtils.snackbarText((th instanceof UnknownHostException ? "网络不可用" : th instanceof SocketTimeoutException ? "请求网络超时" : th instanceof HttpException ? convertStatusCode((HttpException) th) : ((th instanceof JsonParseException) || (th instanceof ParseException) || (th instanceof JSONException) || (th instanceof JsonIOException)) ? "数据解析错误" : "未知错误") + "\r\n" + th.getMessage());
    }
}
