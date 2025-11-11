package com.jess.arms.http.imageloader.glide;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.OkHttpUrlLoader;
import com.jess.arms.http.imageloader.BaseImageLoaderStrategy;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DataHelper;
import java.io.File;
import java.io.InputStream;

@GlideModule(glideName = "GlideArms")
/* loaded from: classes.dex */
public class GlideConfiguration extends AppGlideModule {
    public static final int IMAGE_DISK_CACHE_MAX_SIZE = 104857600;

    @Override // com.bumptech.glide.module.AppGlideModule, com.bumptech.glide.module.AppliesOptions
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder glideBuilder) {
        final AppComponent obtainAppComponentFromContext = ArmsUtils.obtainAppComponentFromContext(context);
        glideBuilder.setDiskCache(new DiskCache.Factory() { // from class: com.jess.arms.http.imageloader.glide.-$$Lambda$GlideConfiguration$7-B5xhwOFIuIg0BNZQ2kv0SuGtw
            @Override // com.bumptech.glide.load.engine.cache.DiskCache.Factory
            public final DiskCache build() {
                DiskCache create;
                create = DiskLruCacheWrapper.create(DataHelper.makeDirs(new File(AppComponent.this.cacheFile(), "Glide")), 104857600L);
                return create;
            }
        });
        int memoryCacheSize = new MemorySizeCalculator.Builder(context).build().getMemoryCacheSize();
        glideBuilder.setMemoryCache(new LruResourceCache((int) (memoryCacheSize * 1.2d)));
        glideBuilder.setBitmapPool(new LruBitmapPool((int) (r1.getBitmapPoolSize() * 1.2d)));
        BaseImageLoaderStrategy loadImgStrategy = obtainAppComponentFromContext.imageLoader().getLoadImgStrategy();
        if (loadImgStrategy instanceof GlideAppliesOptions) {
            ((GlideAppliesOptions) loadImgStrategy).applyGlideOptions(context, glideBuilder);
        }
    }

    @Override // com.bumptech.glide.module.AppGlideModule
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override // com.bumptech.glide.module.LibraryGlideModule, com.bumptech.glide.module.RegistersComponents
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        AppComponent obtainAppComponentFromContext = ArmsUtils.obtainAppComponentFromContext(context);
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(obtainAppComponentFromContext.okHttpClient()));
        BaseImageLoaderStrategy loadImgStrategy = obtainAppComponentFromContext.imageLoader().getLoadImgStrategy();
        if (loadImgStrategy instanceof GlideAppliesOptions) {
            ((GlideAppliesOptions) loadImgStrategy).registerComponents(context, glide, registry);
        }
    }
}
