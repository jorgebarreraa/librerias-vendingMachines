package com.yj.coffeemachines.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.StringUtils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class OrderFailDialog extends BaseDialog {
    private TextView tvCountdown;
    private TextView tvTips;

    public OrderFailDialog(@NonNull Context context) {
        super(context);
    }

    public OrderFailDialog(@NonNull Context context, int i) {
        super(context, i);
    }

    protected OrderFailDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    /* JADX WARN: Type inference failed for: r7v9, types: [com.yj.coffeemachines.dialog.OrderFailDialog$1] */
    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_order_fail);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        this.tvTips = (TextView) findViewById(R.id.tv_tips);
        this.tvCountdown = (TextView) findViewById(R.id.tv_countdown);
        new CountDownTimer(6000L, 1000L) { // from class: com.yj.coffeemachines.dialog.OrderFailDialog.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                OrderFailDialog.this.dismiss();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                OrderFailDialog.this.tvCountdown.setText(String.format(StringUtils.getString(R.string.order_fail_countdowntime), (j / 1000) + ""));
            }
        }.start();
    }

    public void setTips(boolean z) {
        if (z) {
            this.tvTips.setText(R.string.order_fail_tips_net);
        } else {
            this.tvTips.setText(R.string.order_fail_tips_hardware);
        }
    }
}
