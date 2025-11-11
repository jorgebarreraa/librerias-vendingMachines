package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class AdaptScreenUtils {
    private static List<Field> sMetricsFields;

    private AdaptScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @NonNull
    public static Resources adaptHeight(@NonNull Resources resources, int i) {
        return adaptHeight(resources, i, false);
    }

    @NonNull
    public static Resources adaptHeight(@NonNull Resources resources, int i, boolean z) {
        applyDisplayMetrics(resources, ((resources.getDisplayMetrics().heightPixels + (z ? getNavBarHeight(resources) : 0)) * 72.0f) / i);
        return resources;
    }

    @NonNull
    public static Resources adaptWidth(@NonNull Resources resources, int i) {
        applyDisplayMetrics(resources, (resources.getDisplayMetrics().widthPixels * 72.0f) / i);
        return resources;
    }

    private static void applyDisplayMetrics(@NonNull Resources resources, float f) {
        resources.getDisplayMetrics().xdpi = f;
        Utils.getApp().getResources().getDisplayMetrics().xdpi = f;
        applyOtherDisplayMetrics(resources, f);
    }

    private static void applyMetricsFields(Resources resources, float f) {
        Iterator<Field> it2 = sMetricsFields.iterator();
        while (it2.hasNext()) {
            try {
                DisplayMetrics displayMetrics = (DisplayMetrics) it2.next().get(resources);
                if (displayMetrics != null) {
                    displayMetrics.xdpi = f;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void applyOtherDisplayMetrics(Resources resources, float f) {
        if (sMetricsFields != null) {
            applyMetricsFields(resources, f);
            return;
        }
        sMetricsFields = new ArrayList();
        Class<?> cls = resources.getClass();
        Field[] declaredFields = cls.getDeclaredFields();
        while (declaredFields != null && declaredFields.length > 0) {
            for (Field field : declaredFields) {
                if (field.getType().isAssignableFrom(DisplayMetrics.class)) {
                    field.setAccessible(true);
                    DisplayMetrics metricsFromField = getMetricsFromField(resources, field);
                    if (metricsFromField != null) {
                        sMetricsFields.add(field);
                        metricsFromField.xdpi = f;
                    }
                }
            }
            cls = cls.getSuperclass();
            if (cls == null) {
                return;
            } else {
                declaredFields = cls.getDeclaredFields();
            }
        }
    }

    @NonNull
    public static Resources closeAdapt(@NonNull Resources resources) {
        applyDisplayMetrics(resources, Resources.getSystem().getDisplayMetrics().density * 72.0f);
        return resources;
    }

    private static DisplayMetrics getMetricsFromField(Resources resources, Field field) {
        try {
            return (DisplayMetrics) field.get(resources);
        } catch (Exception unused) {
            return null;
        }
    }

    private static int getNavBarHeight(@NonNull Resources resources) {
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Runnable getPreLoadRunnable() {
        return new Runnable() { // from class: com.blankj.utilcode.util.AdaptScreenUtils.1
            @Override // java.lang.Runnable
            public void run() {
                AdaptScreenUtils.preLoad();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void preLoad() {
        applyDisplayMetrics(Resources.getSystem(), Resources.getSystem().getDisplayMetrics().xdpi);
    }

    public static int pt2Px(float f) {
        return (int) (((f * Utils.getApp().getResources().getDisplayMetrics().xdpi) / 72.0f) + 0.5d);
    }

    public static int px2Pt(float f) {
        return (int) (((f * 72.0f) / Utils.getApp().getResources().getDisplayMetrics().xdpi) + 0.5d);
    }
}
