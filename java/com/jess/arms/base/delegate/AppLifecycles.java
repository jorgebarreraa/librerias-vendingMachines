package com.jess.arms.base.delegate;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public interface AppLifecycles {
    void attachBaseContext(@NonNull Context context);

    void onCreate(@NonNull Application application);

    void onTerminate(@NonNull Application application);
}
