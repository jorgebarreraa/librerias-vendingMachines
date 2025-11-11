package com.jess.arms.utils;

import android.R;
import android.app.kingsun.rk3288;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.TextView;
// import androidx.exifinterface.media.ExifInterface; // Eliminado: dependencia no disponible
import java.io.IOException;

/* loaded from: classes.dex */
public class DrawableProvider {
    private DrawableProvider() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static int getBitmapDegree(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return rk3288.RK30_PIN8_PB6;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Bitmap getReSizeBitmap(Bitmap bitmap, float f, float f2) {
        Bitmap bitmap2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(f / width, f2 / height);
        try {
            bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
            bitmap2 = null;
        }
        if (bitmap2 == null) {
            bitmap2 = bitmap;
        }
        if (bitmap != bitmap2) {
            bitmap.recycle();
        }
        return bitmap2;
    }

    public static Drawable getScaleDrawable(float f, Drawable drawable) {
        drawable.setBounds(0, 0, (int) ((drawable.getIntrinsicWidth() * f) + 0.5f), (int) ((drawable.getIntrinsicHeight() * f) + 0.5f));
        return drawable;
    }

    public static Drawable getScaleDrawable2(float f, Drawable drawable) {
        return getScaleDrawable((f * 1.0f) / drawable.getIntrinsicWidth(), drawable);
    }

    public static Drawable getScaleDrawableForRadioButton(float f, TextView textView) {
        Drawable drawable = null;
        for (Drawable drawable2 : textView.getCompoundDrawables()) {
            if (drawable2 != null) {
                drawable = drawable2;
            }
        }
        return getScaleDrawable(f, drawable);
    }

    public static Drawable getScaleDrawableForRadioButton2(float f, TextView textView) {
        Drawable drawable = null;
        for (Drawable drawable2 : textView.getCompoundDrawables()) {
            if (drawable2 != null) {
                drawable = drawable2;
            }
        }
        return getScaleDrawable2(f, drawable);
    }

    public static Drawable getStateListDrawable(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_checked}, drawable2);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    public static Bitmap rotateBitmapByDegree(Bitmap bitmap, int i) {
        Bitmap bitmap2;
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        try {
            bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            bitmap2 = null;
        }
        if (bitmap2 == null) {
            bitmap2 = bitmap;
        }
        if (bitmap != bitmap2) {
            bitmap.recycle();
        }
        return bitmap2;
    }

    public static void setLeftDrawable(TextView textView, Drawable drawable) {
        textView.setCompoundDrawables(drawable, null, null, null);
    }
}
