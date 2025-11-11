package com.jess.arms.integration;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.module.GlobalConfigModule;
import java.util.List;

/* loaded from: classes.dex */
public interface ConfigModule {
    void applyOptions(@NonNull Context context, @NonNull GlobalConfigModule.Builder builder);

    void injectActivityLifecycle(@NonNull Context context, @NonNull List<Application.ActivityLifecycleCallbacks> list);

    void injectAppLifecycle(@NonNull Context context, @NonNull List<AppLifecycles> list);

    void injectFragmentLifecycle(@NonNull Context context, @NonNull List<FragmentManager.FragmentLifecycleCallbacks> list);
}
