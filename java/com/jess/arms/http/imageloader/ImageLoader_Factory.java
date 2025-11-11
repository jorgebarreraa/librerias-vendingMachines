package com.jess.arms.http.imageloader;

import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ImageLoader_Factory implements Factory<ImageLoader> {
    private final Provider<BaseImageLoaderStrategy> mStrategyProvider;

    public ImageLoader_Factory(Provider<BaseImageLoaderStrategy> provider) {
        this.mStrategyProvider = provider;
    }

    public static ImageLoader_Factory create(Provider<BaseImageLoaderStrategy> provider) {
        return new ImageLoader_Factory(provider);
    }

    public static ImageLoader newInstance() {
        return new ImageLoader();
    }

    @Override // javax.inject.Provider
    public ImageLoader get() {
        ImageLoader newInstance = newInstance();
        ImageLoader_MembersInjector.injectMStrategy(newInstance, this.mStrategyProvider.get());
        return newInstance;
    }
}
