package com.jess.arms.http.imageloader;

import android.content.Context;
import androidx.annotation.Nullable;
import com.jess.arms.utils.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* loaded from: classes.dex */
public final class ImageLoader {

    @Nullable
    @Inject
    BaseImageLoaderStrategy mStrategy;

    @Inject
    public ImageLoader() {
    }

    public <T extends ImageConfig> void clear(Context context, T t) {
        Preconditions.checkNotNull(this.mStrategy, "Please implement BaseImageLoaderStrategy and call GlobalConfigModule.Builder#imageLoaderStrategy(BaseImageLoaderStrategy) in the applyOptions method of ConfigModule");
        this.mStrategy.clear(context, t);
    }

    @Nullable
    public BaseImageLoaderStrategy getLoadImgStrategy() {
        return this.mStrategy;
    }

    public <T extends ImageConfig> void loadImage(Context context, T t) {
        Preconditions.checkNotNull(this.mStrategy, "Please implement BaseImageLoaderStrategy and call GlobalConfigModule.Builder#imageLoaderStrategy(BaseImageLoaderStrategy) in the applyOptions method of ConfigModule");
        this.mStrategy.loadImage(context, t);
    }

    public void setLoadImgStrategy(BaseImageLoaderStrategy baseImageLoaderStrategy) {
        Preconditions.checkNotNull(baseImageLoaderStrategy, "strategy == null");
        this.mStrategy = baseImageLoaderStrategy;
    }
}
