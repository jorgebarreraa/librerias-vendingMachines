package com.jess.arms.http.imageloader.glide;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;

/* loaded from: classes.dex */
public interface GlideAppliesOptions {
    void applyGlideOptions(@NonNull Context context, @NonNull GlideBuilder glideBuilder);

    void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry);
}
