package com.yj.coffeemachines.app.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class DiaLogUtils {
    public static AlertDialog showAlert(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener) {
        return showAlert(context, str, str2, str3, onClickListener, null, null, null, null, null, true, null, null);
    }

    public static AlertDialog showAlert(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        return showAlert(context, str, str2, str3, onClickListener, null, null, str4, onClickListener2, null, true, null, null);
    }

    public static AlertDialog showAlert(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2, String str5, DialogInterface.OnClickListener onClickListener3, DialogInterface.OnShowListener onShowListener, boolean z, DialogInterface.OnCancelListener onCancelListener, DialogInterface.OnDismissListener onDismissListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (str != null) {
            builder.setTitle(str);
        }
        if (str2 != null) {
            builder.setMessage(str2);
        }
        if (str3 != null) {
            builder.setPositiveButton(str3, onClickListener);
        }
        if (str4 != null) {
            builder.setNeutralButton(str4, onClickListener2);
        }
        if (str5 != null) {
            builder.setNegativeButton(str5, onClickListener3);
        }
        if (str3 == null && str4 == null && str5 == null) {
            builder.setCancelable(true);
        } else {
            builder.setCancelable(false);
        }
        if (z) {
            builder.setOnCancelListener(onCancelListener);
        }
        AlertDialog create = builder.create();
        if (!(context instanceof Activity)) {
            if (Build.VERSION.SDK_INT >= 25) {
                create.getWindow().setType(2038);
            } else {
                create.getWindow().setType(2003);
            }
        }
        create.setOnDismissListener(onDismissListener);
        create.setOnShowListener(onShowListener);
        create.show();
        create.getButton(-1).setTextSize(30.0f);
        create.getButton(-2).setTextSize(30.0f);
        try {
            Field declaredField = AlertDialog.class.getDeclaredField("mAlert");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(create);
            Field declaredField2 = obj.getClass().getDeclaredField("mTitleView");
            declaredField2.setAccessible(true);
            TextView textView = (TextView) declaredField2.get(obj);
            textView.setTextSize(36.0f);
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            Field declaredField3 = obj.getClass().getDeclaredField("mMessageView");
            declaredField3.setAccessible(true);
            TextView textView2 = (TextView) declaredField3.get(obj);
            textView2.setTextSize(32.0f);
            textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        return create;
    }

    public static AlertDialog showPrompt(Context context, String str) {
        return showAlert(context, null, str, "OK", null, null, null, null, null, null, true, null, null);
    }

    public static AlertDialog showPrompt(Context context, String str, String str2) {
        return showAlert(context, null, str, str2, null, null, null, null, null, null, true, null, null);
    }
}
