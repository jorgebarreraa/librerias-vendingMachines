package com.yj.coffeemachines.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.service.SerialOrderBytes;

/* loaded from: classes.dex */
public class DrainWaterDialog extends BaseDialog {
    private Button btnOk;
    private Handler handler;
    boolean isShow;
    private OnSecretClickListener listener;
    private LinearLayout llPermission;
    private TextView tvTips;

    /* loaded from: classes.dex */
    public interface OnSecretClickListener {
        void onClick();
    }

    public DrainWaterDialog(@NonNull Context context) {
        super(context);
        this.isShow = true;
    }

    private void stopQuery() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        stopQuery();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void getWaterResult() {
        this.isShow = false;
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant_main(SerialOrderBytes.WaterNotice);
        Constants.ISCLEAN_WATER = 0;
        setTips(0);
    }

    public /* synthetic */ void lambda$onCreate$0$DrainWaterDialog(View view) {
        this.listener.onClick();
    }

    public /* synthetic */ void lambda$onCreate$1$DrainWaterDialog(View view) {
        this.llPermission.setVisibility(8);
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(SerialOrderBytes.Water);
        this.handler = new Handler(Looper.getMainLooper());
        this.handler.post(new Runnable() { // from class: com.yj.coffeemachines.dialog.DrainWaterDialog.1
            @Override // java.lang.Runnable
            public void run() {
                int i = Constants.ISCLEAN_WATER;
                if (i == 1) {
                    DrainWaterDialog.this.setTips(1);
                } else if (i == 2) {
                    DrainWaterDialog.this.setTips(2);
                }
                if (i == 0) {
                    MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(SerialOrderBytes.GetWaterResult);
                }
                DrainWaterDialog.this.handler.postDelayed(this, 1000L);
            }
        });
        setTips(0);
    }

    public /* synthetic */ void lambda$onCreate$2$DrainWaterDialog(View view) {
        getWaterResult();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_drain_water);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        this.tvTips = (TextView) findViewById(R.id.tv_tips);
        this.btnOk = (Button) findViewById(R.id.btn_ok);
        Button button = (Button) findViewById(R.id.bt_cancel);
        Button button2 = (Button) findViewById(R.id.bt_confirm);
        this.llPermission = (LinearLayout) findViewById(R.id.ll_permission);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$DrainWaterDialog$NlAqXV8HOFx9UjCj8tbKyHg35dQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DrainWaterDialog.this.lambda$onCreate$0$DrainWaterDialog(view);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$DrainWaterDialog$106I-aa1jJxQpMhJBuxIVkRPYUk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DrainWaterDialog.this.lambda$onCreate$1$DrainWaterDialog(view);
            }
        });
        this.btnOk.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$DrainWaterDialog$Mu6NOIefux9OFyGPJFMqFZG_4kA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DrainWaterDialog.this.lambda$onCreate$2$DrainWaterDialog(view);
            }
        });
    }

    public void setListener(OnSecretClickListener onSecretClickListener) {
        this.listener = onSecretClickListener;
    }

    public void setTips(int i) {
        if (i == 0) {
            this.btnOk.setVisibility(8);
            this.tvTips.setText(R.string.Emptying_the_waterway);
            return;
        }
        if (i != 1) {
            this.btnOk.setVisibility(8);
            this.tvTips.setText(R.string.evacuation_completed);
        } else if (this.isShow) {
            this.btnOk.setVisibility(0);
            this.tvTips.setText(R.string.drainage_outlets);
        } else {
            this.btnOk.setVisibility(8);
            this.tvTips.setText(R.string.Emptying_the_waterway);
            Constants.ISCLEAN_WATER = 0;
        }
    }
}
