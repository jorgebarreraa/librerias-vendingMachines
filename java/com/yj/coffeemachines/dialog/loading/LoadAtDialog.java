package com.yj.coffeemachines.dialog.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.RotateDrawable;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class LoadAtDialog extends Dialog {
    public LoadAtDialog(@NonNull final Context context) {
        super(context);
        setContentView(R.layout.dialog_loading);
        ((ProgressBar) findViewById(R.id.progress_bar)).setIndeterminateDrawable(new RotateDrawable() { // from class: com.yj.coffeemachines.dialog.loading.LoadAtDialog.1
            {
                setFromDegrees(0.0f);
                setToDegrees(360.0f);
                setPivotX(0.5f);
                setPivotY(0.5f);
                setDrawable(ContextCompat.getDrawable(context, R.drawable.progress_custom));
            }
        });
        setCancelable(false);
    }
}
