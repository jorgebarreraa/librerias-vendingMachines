package com.noober.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class BackgroundFactory implements LayoutInflater.Factory2 {
    private LayoutInflater.Factory mViewCreateFactory;
    private LayoutInflater.Factory2 mViewCreateFactory2;
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private static final Object[] mConstructorArgs = new Object[2];
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();
    private static final HashMap<String, HashMap<String, Method>> methodMap = new HashMap<>();

    private static View createView(Context context, String str, String str2) throws InflateException {
        String str3;
        Constructor<? extends View> constructor = sConstructorMap.get(str);
        if (constructor == null) {
            try {
                ClassLoader classLoader = context.getClassLoader();
                if (str2 != null) {
                    str3 = str2 + str;
                } else {
                    str3 = str;
                }
                constructor = classLoader.loadClass(str3).asSubclass(View.class).getConstructor(sConstructorSignature);
                sConstructorMap.put(str, constructor);
            } catch (Exception unused) {
                Log.w("BackgroundLibrary", "cannot create 【" + str + "】 : ");
                return null;
            }
        }
        constructor.setAccessible(true);
        return constructor.newInstance(mConstructorArgs);
    }

    private static View createViewFromTag(Context context, String str, AttributeSet attributeSet) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            mConstructorArgs[0] = context;
            mConstructorArgs[1] = attributeSet;
            if (-1 != str.indexOf(46)) {
                return createView(context, str, null);
            }
            View createView = "View".equals(str) ? createView(context, str, "android.view.") : null;
            if (createView == null) {
                createView = createView(context, str, "android.widget.");
            }
            if (createView == null) {
                createView = createView(context, str, "android.webkit.");
            }
            return createView;
        } catch (Exception unused) {
            Log.w("BackgroundLibrary", "cannot create 【" + str + "】 : ");
            return null;
        } finally {
            Object[] objArr = mConstructorArgs;
            objArr[0] = null;
            objArr[1] = null;
        }
    }

    private static Method findDeclaredMethod(Class cls, String str) {
        Method method = null;
        try {
            method = cls.getDeclaredMethod(str, new Class[0]);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException unused) {
            return cls.getSuperclass() != null ? findDeclaredMethod(cls.getSuperclass(), str) : method;
        }
    }

    private static Method findMethod(Class cls, String str) {
        try {
            return cls.getMethod(str, new Class[0]);
        } catch (NoSuchMethodException unused) {
            return findDeclaredMethod(cls, str);
        }
    }

    private static Method getMethod(Class cls, String str) {
        Method method;
        HashMap<String, Method> hashMap = methodMap.get(cls.getCanonicalName());
        if (hashMap != null) {
            method = methodMap.get(cls.getCanonicalName()).get(str);
        } else {
            hashMap = new HashMap<>();
            methodMap.put(cls.getCanonicalName(), hashMap);
            method = null;
        }
        if (method == null && (method = findMethod(cls, str)) != null) {
            hashMap.put(str, method);
        }
        return method;
    }

    private static boolean hasGradientState(TypedArray typedArray) {
        return typedArray.hasValue(R.styleable.background_bl_checkable_gradient_startColor) || typedArray.hasValue(R.styleable.background_bl_checked_gradient_startColor) || typedArray.hasValue(R.styleable.background_bl_enabled_gradient_startColor) || typedArray.hasValue(R.styleable.background_bl_selected_gradient_startColor) || typedArray.hasValue(R.styleable.background_bl_pressed_gradient_startColor) || typedArray.hasValue(R.styleable.background_bl_focused_gradient_startColor);
    }

    private static boolean hasStatus(int i, int i2) {
        return (i & i2) == i2;
    }

    private static void setBackground(Drawable drawable, View view, TypedArray typedArray) {
        Drawable drawable2;
        float f = 0.0f;
        if (typedArray.hasValue(R.styleable.background_bl_stroke_width) && typedArray.hasValue(R.styleable.background_bl_stroke_position)) {
            float dimension = typedArray.getDimension(R.styleable.background_bl_stroke_width, 0.0f);
            int i = typedArray.getInt(R.styleable.background_bl_stroke_position, 0);
            float f2 = hasStatus(i, 2) ? 0.0f : -dimension;
            float f3 = hasStatus(i, 4) ? 0.0f : -dimension;
            float f4 = hasStatus(i, 8) ? 0.0f : -dimension;
            float f5 = hasStatus(i, 16) ? 0.0f : -dimension;
            drawable2 = new LayerDrawable(new Drawable[]{drawable});
            ((LayerDrawable) drawable2).setLayerInset(0, (int) f2, (int) f3, (int) f4, (int) f5);
        } else {
            drawable2 = drawable;
        }
        if (typedArray.hasValue(R.styleable.background_bl_shape_alpha)) {
            float f6 = typedArray.getFloat(R.styleable.background_bl_shape_alpha, 0.0f);
            if (f6 >= 1.0f) {
                f = 255.0f;
            } else if (f6 > 0.0f) {
                f = f6 * 255.0f;
            }
            drawable2.setAlpha((int) f);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable2);
        } else {
            view.setBackgroundDrawable(drawable2);
        }
    }

    private static void setDrawable(Drawable drawable, View view, TypedArray typedArray, TypedArray typedArray2) {
        if (!(view instanceof TextView)) {
            setBackground(drawable, view, typedArray2);
            return;
        }
        if (!typedArray.hasValue(R.styleable.bl_other_bl_position)) {
            setBackground(drawable, view, typedArray2);
            return;
        }
        if (typedArray.getInt(R.styleable.bl_other_bl_position, 0) == 1) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables(drawable, null, null, null);
            return;
        }
        if (typedArray.getInt(R.styleable.bl_other_bl_position, 0) == 2) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables(null, drawable, null, null);
        } else if (typedArray.getInt(R.styleable.bl_other_bl_position, 0) == 4) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables(null, null, drawable, null);
        } else if (typedArray.getInt(R.styleable.bl_other_bl_position, 0) == 8) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            ((TextView) view).setCompoundDrawables(null, null, null, drawable);
        }
    }

    @Nullable
    public static View setViewBackground(Context context, AttributeSet attributeSet, View view) {
        return setViewBackground(null, context, attributeSet, view);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x01af A[Catch: Exception -> 0x0289, all -> 0x02be, TryCatch #2 {Exception -> 0x0289, blocks: (B:39:0x01ab, B:41:0x01af, B:43:0x01b5, B:44:0x01e5, B:46:0x01ee, B:48:0x01f6, B:52:0x0206, B:55:0x0215, B:56:0x023b, B:58:0x0243, B:60:0x024f, B:62:0x025d, B:80:0x01c0, B:82:0x01c4, B:84:0x01ca, B:85:0x01d5, B:87:0x01d9, B:89:0x01df, B:115:0x01a0, B:117:0x01a6), top: B:114:0x01a0 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01ee A[Catch: Exception -> 0x0289, all -> 0x02be, TryCatch #2 {Exception -> 0x0289, blocks: (B:39:0x01ab, B:41:0x01af, B:43:0x01b5, B:44:0x01e5, B:46:0x01ee, B:48:0x01f6, B:52:0x0206, B:55:0x0215, B:56:0x023b, B:58:0x0243, B:60:0x024f, B:62:0x025d, B:80:0x01c0, B:82:0x01c4, B:84:0x01ca, B:85:0x01d5, B:87:0x01d9, B:89:0x01df, B:115:0x01a0, B:117:0x01a6), top: B:114:0x01a0 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0243 A[Catch: Exception -> 0x0289, all -> 0x02be, TryCatch #2 {Exception -> 0x0289, blocks: (B:39:0x01ab, B:41:0x01af, B:43:0x01b5, B:44:0x01e5, B:46:0x01ee, B:48:0x01f6, B:52:0x0206, B:55:0x0215, B:56:0x023b, B:58:0x0243, B:60:0x024f, B:62:0x025d, B:80:0x01c0, B:82:0x01c4, B:84:0x01ca, B:85:0x01d5, B:87:0x01d9, B:89:0x01df, B:115:0x01a0, B:117:0x01a6), top: B:114:0x01a0 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01c4 A[Catch: Exception -> 0x0289, all -> 0x02be, TryCatch #2 {Exception -> 0x0289, blocks: (B:39:0x01ab, B:41:0x01af, B:43:0x01b5, B:44:0x01e5, B:46:0x01ee, B:48:0x01f6, B:52:0x0206, B:55:0x0215, B:56:0x023b, B:58:0x0243, B:60:0x024f, B:62:0x025d, B:80:0x01c0, B:82:0x01c4, B:84:0x01ca, B:85:0x01d5, B:87:0x01d9, B:89:0x01df, B:115:0x01a0, B:117:0x01a6), top: B:114:0x01a0 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01d9 A[Catch: Exception -> 0x0289, all -> 0x02be, TryCatch #2 {Exception -> 0x0289, blocks: (B:39:0x01ab, B:41:0x01af, B:43:0x01b5, B:44:0x01e5, B:46:0x01ee, B:48:0x01f6, B:52:0x0206, B:55:0x0215, B:56:0x023b, B:58:0x0243, B:60:0x024f, B:62:0x025d, B:80:0x01c0, B:82:0x01c4, B:84:0x01ca, B:85:0x01d5, B:87:0x01d9, B:89:0x01df, B:115:0x01a0, B:117:0x01a6), top: B:114:0x01a0 }] */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.view.View setViewBackground(java.lang.String r19, android.content.Context r20, android.util.AttributeSet r21, android.view.View r22) {
        /*
            Method dump skipped, instructions count: 739
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.noober.background.BackgroundFactory.setViewBackground(java.lang.String, android.content.Context, android.util.AttributeSet, android.view.View):android.view.View");
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return onCreateView(str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View view = null;
        if (str.startsWith("com.noober.background.view")) {
            return null;
        }
        LayoutInflater.Factory2 factory2 = this.mViewCreateFactory2;
        if (factory2 != null) {
            View onCreateView = factory2.onCreateView(str, context, attributeSet);
            view = onCreateView == null ? this.mViewCreateFactory2.onCreateView(null, str, context, attributeSet) : onCreateView;
        } else {
            LayoutInflater.Factory factory = this.mViewCreateFactory;
            if (factory != null) {
                view = factory.onCreateView(str, context, attributeSet);
            }
        }
        return setViewBackground(str, context, attributeSet, view);
    }

    public void setInterceptFactory(LayoutInflater.Factory factory) {
        this.mViewCreateFactory = factory;
    }

    public void setInterceptFactory2(LayoutInflater.Factory2 factory2) {
        this.mViewCreateFactory2 = factory2;
    }
}
