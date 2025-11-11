package com.squareup.leakcanary;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public final class LeakCanary {
    private LeakCanary() {
        throw new AssertionError();
    }

    @NonNull
    public static RefWatcher install(@NonNull Application application) {
        return RefWatcher.DISABLED;
    }

    @NonNull
    public static RefWatcher installedRefWatcher() {
        return RefWatcher.DISABLED;
    }

    public static boolean isInAnalyzerProcess(@NonNull Context context) {
        return false;
    }
}
