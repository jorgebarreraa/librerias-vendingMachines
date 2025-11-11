package com.jess.arms.http.log;

import com.jess.arms.http.GlobalHttpHandler;
import com.jess.arms.http.log.RequestInterceptor;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class RequestInterceptor_Factory implements Factory<RequestInterceptor> {
    private final Provider<GlobalHttpHandler> mHandlerProvider;
    private final Provider<FormatPrinter> mPrinterProvider;
    private final Provider<RequestInterceptor.Level> printLevelProvider;

    public RequestInterceptor_Factory(Provider<GlobalHttpHandler> provider, Provider<FormatPrinter> provider2, Provider<RequestInterceptor.Level> provider3) {
        this.mHandlerProvider = provider;
        this.mPrinterProvider = provider2;
        this.printLevelProvider = provider3;
    }

    public static RequestInterceptor_Factory create(Provider<GlobalHttpHandler> provider, Provider<FormatPrinter> provider2, Provider<RequestInterceptor.Level> provider3) {
        return new RequestInterceptor_Factory(provider, provider2, provider3);
    }

    public static RequestInterceptor newInstance() {
        return new RequestInterceptor();
    }

    @Override // javax.inject.Provider
    public RequestInterceptor get() {
        RequestInterceptor newInstance = newInstance();
        RequestInterceptor_MembersInjector.injectMHandler(newInstance, this.mHandlerProvider.get());
        RequestInterceptor_MembersInjector.injectMPrinter(newInstance, this.mPrinterProvider.get());
        RequestInterceptor_MembersInjector.injectPrintLevel(newInstance, this.printLevelProvider.get());
        return newInstance;
    }
}
