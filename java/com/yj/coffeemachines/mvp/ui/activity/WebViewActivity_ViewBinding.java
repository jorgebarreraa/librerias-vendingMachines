package com.yj.coffeemachines.mvp.ui.activity;

import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class WebViewActivity_ViewBinding implements Unbinder {
    private WebViewActivity target;

    @UiThread
    public WebViewActivity_ViewBinding(WebViewActivity webViewActivity) {
        this(webViewActivity, webViewActivity.getWindow().getDecorView());
    }

    @UiThread
    public WebViewActivity_ViewBinding(WebViewActivity webViewActivity, View view) {
        this.target = webViewActivity;
        webViewActivity.mCountdowntime = (TextView) Utils.findRequiredViewAsType(view, R.id.countdowntime, "field 'mCountdowntime'", TextView.class);
        webViewActivity.linearLayout11 = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.linearLayout11, "field 'linearLayout11'", FrameLayout.class);
        webViewActivity.webView = (WebView) Utils.findRequiredViewAsType(view, R.id.web_view, "field 'webView'", WebView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        WebViewActivity webViewActivity = this.target;
        if (webViewActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        webViewActivity.mCountdowntime = null;
        webViewActivity.linearLayout11 = null;
        webViewActivity.webView = null;
    }
}
