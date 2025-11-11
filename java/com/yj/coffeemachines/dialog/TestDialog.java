package com.yj.coffeemachines.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jess.arms.utils.DeviceUtils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class TestDialog extends BaseDialog {
    private OnStopTestListener onStopTestListener;
    private TextView tvClose;
    private TextView tvCountdown;
    private TextView tvTips;
    private TextView tvTitle;

    /* loaded from: classes.dex */
    public interface OnStopTestListener {
        void onStopTest();
    }

    public TestDialog(@NonNull Context context) {
        super(context);
    }

    public /* synthetic */ void lambda$onCreate$0$TestDialog(DialogInterface dialogInterface) {
        OnStopTestListener onStopTestListener = this.onStopTestListener;
        if (onStopTestListener != null) {
            onStopTestListener.onStopTest();
        }
    }

    public /* synthetic */ void lambda$onCreate$1$TestDialog(View view) {
        dismiss();
    }

    public /* synthetic */ void lambda$onCreate$2$TestDialog(View view) {
        dismiss();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_test);
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
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$TestDialog$63pMvBt6QBUkXm84to9eobbBDB8
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                TestDialog.this.lambda$onCreate$0$TestDialog(dialogInterface);
            }
        });
        this.tvClose.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$TestDialog$2gAvABFHjvfA3xwrWY_vs-BPtuM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestDialog.this.lambda$onCreate$1$TestDialog(view);
            }
        });
        this.tvCountdown.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$TestDialog$W5ctP1WY5cC3VMV8T6AhuS-3DrY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestDialog.this.lambda$onCreate$2$TestDialog(view);
            }
        });
    }

    public void setOnStopTestListener(OnStopTestListener onStopTestListener) {
        this.onStopTestListener = onStopTestListener;
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

    public void showClose(boolean z) {
        this.tvClose.setVisibility(z ? 0 : 8);
    }
}
