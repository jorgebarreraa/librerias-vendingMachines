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
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.jess.arms.utils.DeviceUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.QRCodeUtils;

/* loaded from: classes.dex */
public class JoinVipDialog extends BaseDialog {
    private ImageView ivQr;
    private TextView tvCountdown;
    private TextView tvTips;
    private TextView tvTitle;

    public JoinVipDialog(@NonNull Context context) {
        super(context);
    }

    public JoinVipDialog(@NonNull Context context, int i) {
        super(context, i);
    }

    protected JoinVipDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public /* synthetic */ void lambda$onCreate$0$JoinVipDialog(View view) {
        dismiss();
    }

    /* JADX WARN: Type inference failed for: r8v25, types: [com.yj.coffeemachines.dialog.JoinVipDialog$1] */
    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_join_vip);
        Window window = getWindow();
        if (window != null) {
            Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            defaultDisplay.getSize(new Point());
            if (DeviceUtils.isLandscape(this.context)) {
                attributes.width = (int) (r2.x * 0.4d);
            } else {
                attributes.width = (int) (r2.x * 0.6d);
            }
            window.setAttributes(attributes);
        }
        findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$JoinVipDialog$J4hhHTXyX2Qd8p6ZqRdJapL2RAo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JoinVipDialog.this.lambda$onCreate$0$JoinVipDialog(view);
            }
        });
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        this.tvTitle.setText(String.format("%s%s", StringUtils.getString(R.string.join), StringUtils.getString(R.string.members)));
        this.tvTips = (TextView) findViewById(R.id.tv_tips);
        this.ivQr = (ImageView) findViewById(R.id.iv_qr);
        this.tvCountdown = (TextView) findViewById(R.id.tv_countdown);
        if (!TextUtils.isEmpty(Constants.memberTips)) {
            this.tvTips.setText(Constants.memberTips);
        }
        if (TextUtils.isEmpty(Constants.memberQr)) {
            this.ivQr.setImageBitmap(QRCodeUtils.generateBitmap(Constants.deviceSN(), 300, 300));
        } else {
            Glide.with(this.ivQr).load(Constants.memberQr).into(this.ivQr);
        }
        new CountDownTimer(180000L, 1000L) { // from class: com.yj.coffeemachines.dialog.JoinVipDialog.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                JoinVipDialog.this.dismiss();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                JoinVipDialog.this.tvCountdown.setText(String.format(StringUtils.getString(R.string.order_fail_countdowntime), (j / 1000) + ""));
            }
        }.start();
    }
}
