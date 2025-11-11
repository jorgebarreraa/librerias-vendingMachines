package com.yj.coffeemachines.mvp.ui.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.KvUtil;
import com.yj.coffeemachines.eventbusbean.ResetTime;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class WebViewActivity extends BaseActivity {
    private int countDown = 30;

    @BindView(R.id.linearLayout11)
    public FrameLayout linearLayout11;

    @BindView(R.id.countdowntime)
    public TextView mCountdowntime;

    @BindView(R.id.web_view)
    public WebView webView;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            EventBus.getDefault().post(new ResetTime());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public void initData(@Nullable Bundle bundle) {
        this.linearLayout11.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.-$$Lambda$WebViewActivity$AJeqDKi87pTTJ0ga_zcEL9MBlBI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebViewActivity.this.lambda$initData$0$WebViewActivity(view);
            }
        });
        this.webView.setWebViewClient(new WebViewClient());
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setCacheMode(-1);
        settings.setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        settings.setAppCacheEnabled(true);
        this.webView.loadUrl(KvUtil.getInstance().getString("etContent", "https://demo.chatislav.ai").toString());
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public int initView(@Nullable Bundle bundle) {
        return R.layout.activity_webview;
    }

    public /* synthetic */ void lambda$initData$0$WebViewActivity(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jess.arms.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ButterKnife.bind(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jess.arms.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        WebView webView = this.webView;
        if (webView != null) {
            webView.removeAllViews();
            this.webView.destroy();
            this.webView = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ResetTime resetTime) {
        this.countDown = 30;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TimeMessage timeMessage) {
        this.countDown--;
        if (this.mCountdowntime != null) {
            String.format(getString(R.string.hint_countdowntime), this.countDown + "");
            this.mCountdowntime.setText(this.countDown);
        }
        if (this.countDown <= 0) {
            finish();
        }
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
    }
}
