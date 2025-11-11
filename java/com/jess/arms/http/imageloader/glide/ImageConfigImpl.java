package com.jess.arms.http.imageloader.glide;

import android.widget.ImageView;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.jess.arms.http.imageloader.ImageConfig;

/* loaded from: classes.dex */
public class ImageConfigImpl extends ImageConfig {
    private int blurValue;
    private int cacheStrategy;
    private int fallback;
    private int imageRadius;
    private ImageView[] imageViews;
    private boolean isCenterCrop;
    private boolean isCircle;
    private boolean isClearDiskCache;
    private boolean isClearMemory;
    private boolean isCrossFade;

    @Deprecated
    private BitmapTransformation transformation;

    /* loaded from: classes.dex */
    public static final class Builder {
        private int blurValue;
        private int cacheStrategy;
        private int errorPic;
        private int fallback;
        private int imageRadius;
        private ImageView imageView;
        private ImageView[] imageViews;
        private boolean isCenterCrop;
        private boolean isCircle;
        private boolean isClearDiskCache;
        private boolean isClearMemory;
        private boolean isCrossFade;
        private int placeholder;

        @Deprecated
        private BitmapTransformation transformation;
        private String url;

        private Builder() {
        }

        public Builder blurValue(int i) {
            this.blurValue = i;
            return this;
        }

        public ImageConfigImpl build() {
            return new ImageConfigImpl(this);
        }

        public Builder cacheStrategy(int i) {
            this.cacheStrategy = i;
            return this;
        }

        public Builder errorPic(int i) {
            this.errorPic = i;
            return this;
        }

        public Builder fallback(int i) {
            this.fallback = i;
            return this;
        }

        public Builder imageRadius(int i) {
            this.imageRadius = i;
            return this;
        }

        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder imageViews(ImageView... imageViewArr) {
            this.imageViews = imageViewArr;
            return this;
        }

        public Builder isCenterCrop(boolean z) {
            this.isCenterCrop = z;
            return this;
        }

        public Builder isCircle(boolean z) {
            this.isCircle = z;
            return this;
        }

        public Builder isClearDiskCache(boolean z) {
            this.isClearDiskCache = z;
            return this;
        }

        public Builder isClearMemory(boolean z) {
            this.isClearMemory = z;
            return this;
        }

        public Builder isCrossFade(boolean z) {
            this.isCrossFade = z;
            return this;
        }

        public Builder placeholder(int i) {
            this.placeholder = i;
            return this;
        }

        @Deprecated
        public Builder transformation(BitmapTransformation bitmapTransformation) {
            this.transformation = bitmapTransformation;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }
    }

    private ImageConfigImpl(Builder builder) {
        this.url = builder.url;
        this.imageView = builder.imageView;
        this.placeholder = builder.placeholder;
        this.errorPic = builder.errorPic;
        this.fallback = builder.fallback;
        this.cacheStrategy = builder.cacheStrategy;
        this.imageRadius = builder.imageRadius;
        this.blurValue = builder.blurValue;
        this.transformation = builder.transformation;
        this.imageViews = builder.imageViews;
        this.isCrossFade = builder.isCrossFade;
        this.isCenterCrop = builder.isCenterCrop;
        this.isCircle = builder.isCircle;
        this.isClearMemory = builder.isClearMemory;
        this.isClearDiskCache = builder.isClearDiskCache;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getBlurValue() {
        return this.blurValue;
    }

    public int getCacheStrategy() {
        return this.cacheStrategy;
    }

    public int getFallback() {
        return this.fallback;
    }

    public int getImageRadius() {
        return this.imageRadius;
    }

    public ImageView[] getImageViews() {
        return this.imageViews;
    }

    public BitmapTransformation getTransformation() {
        return this.transformation;
    }

    public boolean isBlurImage() {
        return this.blurValue > 0;
    }

    public boolean isCenterCrop() {
        return this.isCenterCrop;
    }

    public boolean isCircle() {
        return this.isCircle;
    }

    public boolean isClearDiskCache() {
        return this.isClearDiskCache;
    }

    public boolean isClearMemory() {
        return this.isClearMemory;
    }

    public boolean isCrossFade() {
        return this.isCrossFade;
    }

    public boolean isImageRadius() {
        return this.imageRadius > 0;
    }
}
