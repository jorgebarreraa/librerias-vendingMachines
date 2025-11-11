package com.yj.coffeemachines.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.StringUtils;
import com.jess.arms.utils.DeviceUtils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class ConfirmDialog extends BaseDialog {
    private long millisInFuture;
    private OnConfirmListener onConfirmListener;
    private CountDownTimer timer;
    private TextView tvClose;
    private TextView tvCountdown;
    private TextView tvTips;
    private TextView tvTitle;

    /* loaded from: classes.dex */
    public interface OnConfirmListener {
        void onConfirm();
    }

    public ConfirmDialog(@NonNull Context context, int i, long j) {
        super(context, i);
        this.millisInFuture = 0L;
        this.millisInFuture = j;
    }

    public ConfirmDialog(@NonNull Context context, long j) {
        super(context);
        this.millisInFuture = 0L;
        this.millisInFuture = j;
    }

    protected ConfirmDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.millisInFuture = 0L;
    }

    public /* synthetic */ void lambda$onCreate$0$ConfirmDialog(View view) {
        OnConfirmListener onConfirmListener = this.onConfirmListener;
        if (onConfirmListener != null) {
            onConfirmListener.onConfirm();
        }
    }

    public /* synthetic */ void lambda$onCreate$1$ConfirmDialog(View view) {
        dismiss();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_confirm);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        if (window != null) {
            Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            defaultDisplay.getSize(new Point());
            if (DeviceUtils.isLandscape(this.context)) {
                attributes.width = (int) (r2.x * 0.4d);
            } else {
                attributes.width = (int) (r2.x * 0.7d);
            }
            window.setAttributes(attributes);
        }
        this.tvClose = (TextView) findViewById(R.id.tv_close);
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        this.tvTips = (TextView) findViewById(R.id.tv_tips);
        this.tvCountdown = (TextView) findViewById(R.id.tv_countdown);
        this.timer = new CountDownTimer(this.millisInFuture, 1000L) { // from class: com.yj.coffeemachines.dialog.ConfirmDialog.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                ConfirmDialog.this.dismiss();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                ConfirmDialog.this.tvCountdown.setText(StringUtils.getString(R.string.confirm) + "(" + String.format(StringUtils.getString(R.string.order_fail_countdowntime), (j / 1000) + "") + ")");
            }
        };
        this.tvCountdown.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$ConfirmDialog$LaM4K7oLNAyrBAzXNLa5HL1Zv0o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConfirmDialog.this.lambda$onCreate$0$ConfirmDialog(view);
            }
        });
        this.tvClose.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$ConfirmDialog$6XeZ2hLWOXdi_ot6NWREztwAyOg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConfirmDialog.this.lambda$onCreate$1$ConfirmDialog(view);
            }
        });
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    public void setTips(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.tvTips.setText(str);
    }

    public void setTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.tvTitle.setText(str);
    }

    @Override // android.app.Dialog
    public void show() {
        CountDownTimer countDownTimer;
        super.show();
        if (this.millisInFuture < 1000 || (countDownTimer = this.timer) == null) {
            return;
        }
        countDownTimer.start();
    }

    public void showClose(boolean z) {
        this.tvClose.setVisibility(z ? 0 : 8);
    }
}
