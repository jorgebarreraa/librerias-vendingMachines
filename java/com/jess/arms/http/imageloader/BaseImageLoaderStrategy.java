package com.jess.arms.http.imageloader;

import android.content.Context;
import androidx.annotation.Nullable;
import com.jess.arms.http.imageloader.ImageConfig;

/* loaded from: classes.dex */
public interface BaseImageLoaderStrategy<T extends ImageConfig> {
    void clear(@Nullable Context context, @Nullable T t);

    void loadImage(@Nullable Context context, @Nullable T t);
}
