package com.jess.arms.http.imageloader;

import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class ImageLoader_MembersInjector implements MembersInjector<ImageLoader> {
    private final Provider<BaseImageLoaderStrategy> mStrategyProvider;

    public ImageLoader_MembersInjector(Provider<BaseImageLoaderStrategy> provider) {
        this.mStrategyProvider = provider;
    }

    public static MembersInjector<ImageLoader> create(Provider<BaseImageLoaderStrategy> provider) {
        return new ImageLoader_MembersInjector(provider);
    }

    @InjectedFieldSignature("com.jess.arms.http.imageloader.ImageLoader.mStrategy")
    public static void injectMStrategy(ImageLoader imageLoader, BaseImageLoaderStrategy baseImageLoaderStrategy) {
        imageLoader.mStrategy = baseImageLoaderStrategy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ImageLoader imageLoader) {
        injectMStrategy(imageLoader, this.mStrategyProvider.get());
    }
}
