package com.squareup.leakcanary;

import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public final class RefWatcher {

    @NonNull
    public static final RefWatcher DISABLED = new RefWatcher();

    private RefWatcher() {
    }

    public void watch(@NonNull Object obj) {
    }

    public void watch(@NonNull Object obj, @NonNull String str) {
    }
}
