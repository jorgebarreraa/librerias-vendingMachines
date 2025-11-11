package com.jess.arms.base.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.cache.Cache;

/* loaded from: classes.dex */
public interface IFragment {
    void initData(@Nullable Bundle bundle);

    View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle);

    @NonNull
    Cache<String, Object> provideCache();

    void setData(@Nullable Object obj);

    void setupFragmentComponent(@NonNull AppComponent appComponent);

    boolean useEventBus();
}
