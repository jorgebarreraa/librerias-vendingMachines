package com.yj.coffeemachines.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.StringUtils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class TimeAlert extends BaseDialog {
    private long millisInFuture;
    private CountDownTimer timer;
    private TextView tvClose;
    private TextView tvCountdown;
    private TextView tvTips;
    private TextView tvTitle;

    public TimeAlert(@NonNull Context context, int i, long j) {
        super(context, i);
        this.millisInFuture = 0L;
        this.millisInFuture = j;
    }

    public TimeAlert(@NonNull Context context, long j) {
        super(context);
        this.millisInFuture = 0L;
        this.millisInFuture = j;
    }

    protected TimeAlert(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.millisInFuture = 0L;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_time_alert);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        this.tvClose = (TextView) findViewById(R.id.tv_close);
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        this.tvTips = (TextView) findViewById(R.id.tv_tips);
        this.tvCountdown = (TextView) findViewById(R.id.tv_countdown);
        this.timer = new CountDownTimer(this.millisInFuture, 1000L) { // from class: com.yj.coffeemachines.dialog.TimeAlert.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                TimeAlert.this.dismiss();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                TimeAlert.this.tvCountdown.setText(String.format(StringUtils.getString(R.string.order_fail_countdowntime), (j / 1000) + ""));
            }
        };
        this.tvClose.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.TimeAlert.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TimeAlert.this.dismiss();
            }
        });
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
