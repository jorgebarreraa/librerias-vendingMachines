package com.jaredrummler.materialspinner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* loaded from: classes.dex */
final class Utils {
    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int darker(int i, float f) {
        return Color.argb(Color.alpha(i), Math.max((int) (Color.red(i) * f), 0), Math.max((int) (Color.green(i) * f), 0), Math.max((int) (Color.blue(i) * f), 0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Drawable getDrawable(Context context, int i) {
        return Build.VERSION.SDK_INT >= 21 ? context.getDrawable(i) : context.getResources().getDrawable(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isRtl(Context context) {
        return Build.VERSION.SDK_INT >= 17 && context.getResources().getConfiguration().getLayoutDirection() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int lighter(int i, float f) {
        float f2 = 1.0f - f;
        return Color.argb(Color.alpha(i), (int) ((((Color.red(i) * f2) / 255.0f) + f) * 255.0f), (int) ((((Color.green(i) * f2) / 255.0f) + f) * 255.0f), (int) ((((Color.blue(i) * f2) / 255.0f) + f) * 255.0f));
    }
}
