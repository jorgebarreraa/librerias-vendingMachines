package com.jess.arms.base;

/* loaded from: classes.dex */
public class Platform {
    public static final boolean DEPENDENCY_AUTO_LAYOUT = findClassByClassName("com.zhy.autolayout.AutoLayoutInfo");
    public static final boolean DEPENDENCY_SUPPORT_DESIGN = findClassByClassName("com.google.android.material.snackbar.Snackbar");
    public static final boolean DEPENDENCY_GLIDE = findClassByClassName("com.bumptech.glide.Glide");
    public static final boolean DEPENDENCY_ANDROID_EVENTBUS = findClassByClassName("org.simple.eventbus.EventBus");
    public static final boolean DEPENDENCY_EVENTBUS = findClassByClassName("org.greenrobot.eventbus.EventBus");

    private static boolean findClassByClassName(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
