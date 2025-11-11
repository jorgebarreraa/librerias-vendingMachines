package com.yj.coffeemachines.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.StringUtils;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class IndexDialog extends BaseDialog {
    private Button btReload;
    private int click_lightNum;
    private OnSecretClickListener listener;
    private long time_lightClick;
    private TextView tvMsg;

    /* loaded from: classes.dex */
    public interface OnSecretClickListener {
        void onClick();

        void onReloadClick();
    }

    public IndexDialog(@NonNull Context context) {
        super(context);
        this.time_lightClick = System.currentTimeMillis();
        this.click_lightNum = 0;
    }

    public IndexDialog(@NonNull Context context, int i) {
        super(context, i);
        this.time_lightClick = System.currentTimeMillis();
        this.click_lightNum = 0;
    }

    protected IndexDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.time_lightClick = System.currentTimeMillis();
        this.click_lightNum = 0;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public String getMsg() {
        TextView textView = this.tvMsg;
        return textView != null ? textView.getText().toString() : "";
    }

    public /* synthetic */ void lambda$onCreate$0$IndexDialog(View view) {
        this.listener.onReloadClick();
    }

    public /* synthetic */ void lambda$onCreate$1$IndexDialog(View view) {
        int i = this.click_lightNum;
        if (i == 0) {
            this.click_lightNum = 1;
        } else if (i == 1 && System.currentTimeMillis() - this.time_lightClick <= 1500) {
            this.click_lightNum = 2;
        }
        this.time_lightClick = System.currentTimeMillis();
    }

    public /* synthetic */ void lambda$onCreate$2$IndexDialog(View view) {
        if (this.click_lightNum == 2 && System.currentTimeMillis() - this.time_lightClick <= 3000) {
            dismiss();
            OnSecretClickListener onSecretClickListener = this.listener;
            if (onSecretClickListener != null) {
                onSecretClickListener.onClick();
            }
        }
        this.click_lightNum = 0;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialogview);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        if (window != null) {
            Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            defaultDisplay.getSize(new Point());
            if (DeviceUtils.isLandscape(this.context)) {
                attributes.width = (int) (r2.x * 0.7d);
                attributes.height = (int) (r2.y * 0.5d);
            } else {
                attributes.width = (int) (r2.x * 0.9d);
                attributes.height = (int) (r2.y * 0.5d);
            }
            window.setAttributes(attributes);
        }
        this.tvMsg = (TextView) findViewById(R.id.tv_errormsg);
        this.btReload = (Button) findViewById(R.id.bt_reload);
        ((ImageView) findViewById(R.id.error_iv)).setImageDrawable(ArmsUtils.getResources(this.context).getDrawable(R.mipmap.ic_error));
        this.btReload.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$IndexDialog$_TelpzjVEnrS6DPyLXSasOKCloY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IndexDialog.this.lambda$onCreate$0$IndexDialog(view);
            }
        });
        findViewById(R.id.dialog_tv_lightclick).setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$IndexDialog$qrljun71x7fo6Czqr1-FyeJjHno
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IndexDialog.this.lambda$onCreate$1$IndexDialog(view);
            }
        });
        findViewById(R.id.dialog_tv_rightclick).setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$IndexDialog$0PuTJe-vhWzPhnKR3SavQc_LDu4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IndexDialog.this.lambda$onCreate$2$IndexDialog(view);
            }
        });
    }

    public void setListener(OnSecretClickListener onSecretClickListener) {
        this.listener = onSecretClickListener;
    }

    public void setMsg(String str) {
        if (StringUtils.isEmpty(str)) {
            str = "";
        }
        TextView textView = this.tvMsg;
        if (textView != null) {
            textView.setText(str);
            if (str.contains(StringUtils.getString(R.string.Cloudservicedata))) {
                this.btReload.setVisibility(0);
            } else {
                this.btReload.setVisibility(8);
            }
        }
    }
}
