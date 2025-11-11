package com.jess.arms.http.imageloader.glide;

import android.graphics.Bitmap;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.jess.arms.utils.FastBlur;
import java.security.MessageDigest;

/* loaded from: classes.dex */
public class BlurTransformation extends BitmapTransformation {
    public static final int DEFAULT_RADIUS = 15;
    private static final String ID = "com.jess.arms.http.imageloader.glide.BlurTransformation";
    private static final byte[] ID_BYTES = ID.getBytes(Key.CHARSET);
    private int mRadius;

    public BlurTransformation(@IntRange(from = 0) int i) {
        this.mRadius = 15;
        this.mRadius = i;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        return obj instanceof BlurTransformation;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return ID.hashCode();
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i, int i2) {
        return FastBlur.doBlur(bitmap, this.mRadius, true);
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
