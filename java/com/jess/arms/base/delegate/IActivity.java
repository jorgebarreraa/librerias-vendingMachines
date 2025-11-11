package com.jess.arms.base.delegate;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.cache.Cache;

/* loaded from: classes.dex */
public interface IActivity {
    void initData(@Nullable Bundle bundle);

    int initView(@Nullable Bundle bundle);

    @NonNull
    Cache<String, Object> provideCache();

    void setupActivityComponent(@NonNull AppComponent appComponent);

    boolean useEventBus();

    boolean useFragment();
}
