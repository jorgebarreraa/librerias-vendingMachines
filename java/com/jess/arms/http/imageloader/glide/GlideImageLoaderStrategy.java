package com.jess.arms.http.imageloader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.jess.arms.http.imageloader.BaseImageLoaderStrategy;
import com.jess.arms.utils.Preconditions;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/* loaded from: classes.dex */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy<ImageConfigImpl>, GlideAppliesOptions {
    @Override // com.jess.arms.http.imageloader.glide.GlideAppliesOptions
    public void applyGlideOptions(Context context, GlideBuilder glideBuilder) {
        Timber.w("applyGlideOptions", new Object[0]);
    }

    @Override // com.jess.arms.http.imageloader.BaseImageLoaderStrategy
    public void clear(final Context context, ImageConfigImpl imageConfigImpl) {
        Preconditions.checkNotNull(context, "Context is required");
        Preconditions.checkNotNull(imageConfigImpl, "ImageConfigImpl is required");
        if (imageConfigImpl.getImageView() != null) {
            GlideArms.get(context).getRequestManagerRetriever().get(context).clear(imageConfigImpl.getImageView());
        }
        if (imageConfigImpl.getImageViews() != null && imageConfigImpl.getImageViews().length > 0) {
            for (ImageView imageView : imageConfigImpl.getImageViews()) {
                GlideArms.get(context).getRequestManagerRetriever().get(context).clear(imageView);
            }
        }
        if (imageConfigImpl.isClearDiskCache()) {
            Completable.fromAction(new Action() { // from class: com.jess.arms.http.imageloader.glide.GlideImageLoaderStrategy.1
                @Override // io.reactivex.functions.Action
                public void run() throws Exception {
                    Glide.get(context).clearDiskCache();
                }
            }).subscribeOn(Schedulers.io()).subscribe();
        }
        if (imageConfigImpl.isClearMemory()) {
            Completable.fromAction(new Action() { // from class: com.jess.arms.http.imageloader.glide.GlideImageLoaderStrategy.2
                @Override // io.reactivex.functions.Action
                public void run() throws Exception {
                    Glide.get(context).clearMemory();
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
        }
    }

    @Override // com.jess.arms.http.imageloader.BaseImageLoaderStrategy
    public void loadImage(Context context, ImageConfigImpl imageConfigImpl) {
        Preconditions.checkNotNull(context, "Context is required");
        Preconditions.checkNotNull(imageConfigImpl, "ImageConfigImpl is required");
        Preconditions.checkNotNull(imageConfigImpl.getImageView(), "ImageView is required");
        RequestBuilder<Drawable> load = GlideArms.with(context).load(imageConfigImpl.getUrl());
        int cacheStrategy = imageConfigImpl.getCacheStrategy();
        if (cacheStrategy == 0) {
            load.diskCacheStrategy(DiskCacheStrategy.ALL);
        } else if (cacheStrategy == 1) {
            load.diskCacheStrategy(DiskCacheStrategy.NONE);
        } else if (cacheStrategy == 2) {
            load.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        } else if (cacheStrategy == 3) {
            load.diskCacheStrategy(DiskCacheStrategy.DATA);
        } else if (cacheStrategy != 4) {
            load.diskCacheStrategy(DiskCacheStrategy.ALL);
        } else {
            load.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        }
        if (imageConfigImpl.isCrossFade()) {
            load.transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade());
        }
        if (imageConfigImpl.isCenterCrop()) {
            load.centerCrop();
        }
        if (imageConfigImpl.isCircle()) {
            load.circleCrop();
        }
        if (imageConfigImpl.isImageRadius()) {
            load.transform((Transformation<Bitmap>) new RoundedCorners(imageConfigImpl.getImageRadius()));
        }
        if (imageConfigImpl.isBlurImage()) {
            load.transform((Transformation<Bitmap>) new BlurTransformation(imageConfigImpl.getBlurValue()));
        }
        if (imageConfigImpl.getTransformation() != null) {
            load.transform((Transformation<Bitmap>) imageConfigImpl.getTransformation());
        }
        if (imageConfigImpl.getPlaceholder() != 0) {
            load.placeholder(imageConfigImpl.getPlaceholder());
        }
        if (imageConfigImpl.getErrorPic() != 0) {
            load.error(imageConfigImpl.getErrorPic());
        }
        if (imageConfigImpl.getFallback() != 0) {
            load.fallback(imageConfigImpl.getFallback());
        }
        load.into(imageConfigImpl.getImageView());
    }
}
