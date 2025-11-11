package com.yj.coffeemachines.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.TimeUtils;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.mvp.ui.widget.numberprogressbar.NumberProgressBar;

/* loaded from: classes.dex */
public class UpdateDialog extends BaseDialog {
    private long clickMills;
    private OnUpdateListener listener;
    private Button mBtnCancel;
    private Button mBtnUpdate;
    private ImageView mIvTop;
    private NumberProgressBar mNumberProgressBar;
    private TextView mTvTitle;
    private TextView mTvUpdateInfo;

    /* loaded from: classes.dex */
    public interface OnUpdateListener {
        void onUpdate();
    }

    public UpdateDialog(@NonNull Context context) {
        super(context);
        this.clickMills = 0L;
    }

    public UpdateDialog(@NonNull Context context, int i) {
        super(context, i);
        this.clickMills = 0L;
    }

    protected UpdateDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.clickMills = 0L;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void enable(boolean z) {
        Button button = this.mBtnUpdate;
        if (button != null) {
            button.setEnabled(z);
        }
    }

    protected void initViews() {
        this.mTvTitle = (TextView) findViewById(R.id.tv_title);
        this.mBtnUpdate = (Button) findViewById(R.id.btn_update);
        this.mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        this.mNumberProgressBar = (NumberProgressBar) findViewById(R.id.npb_progress);
        this.mBtnCancel.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$UpdateDialog$BdaJhR590Gta41y-35vG4Ja5Xjg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpdateDialog.this.lambda$initViews$0$UpdateDialog(view);
            }
        });
        this.mBtnUpdate.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$UpdateDialog$cqZcGwK8JOxlal00o5Cz6Sbsa3o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpdateDialog.this.lambda$initViews$1$UpdateDialog(view);
            }
        });
    }

    public /* synthetic */ void lambda$initViews$0$UpdateDialog(View view) {
        dismiss();
    }

    public /* synthetic */ void lambda$initViews$1$UpdateDialog(View view) {
        if (TimeUtils.getNowMills() - this.clickMills > 2000) {
            this.clickMills = TimeUtils.getNowMills();
            OnUpdateListener onUpdateListener = this.listener;
            if (onUpdateListener != null) {
                onUpdateListener.onUpdate();
            }
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_update);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        initViews();
    }

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.listener = onUpdateListener;
    }

    public void updateProgress(int i) {
        NumberProgressBar numberProgressBar = this.mNumberProgressBar;
        if (numberProgressBar != null) {
            numberProgressBar.setProgress(i);
        }
    }
}
