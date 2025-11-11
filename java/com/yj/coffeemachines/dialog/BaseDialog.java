package com.yj.coffeemachines.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class BaseDialog extends Dialog {
    protected Context context;

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public BaseDialog(@NonNull Context context, int i) {
        super(context, i);
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.context = context;
    }
}
