package com.jess.arms.http.log;

import com.jess.arms.http.GlobalHttpHandler;
import com.jess.arms.http.log.RequestInterceptor;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class RequestInterceptor_MembersInjector implements MembersInjector<RequestInterceptor> {
    private final Provider<GlobalHttpHandler> mHandlerProvider;
    private final Provider<FormatPrinter> mPrinterProvider;
    private final Provider<RequestInterceptor.Level> printLevelProvider;

    public RequestInterceptor_MembersInjector(Provider<GlobalHttpHandler> provider, Provider<FormatPrinter> provider2, Provider<RequestInterceptor.Level> provider3) {
        this.mHandlerProvider = provider;
        this.mPrinterProvider = provider2;
        this.printLevelProvider = provider3;
    }

    public static MembersInjector<RequestInterceptor> create(Provider<GlobalHttpHandler> provider, Provider<FormatPrinter> provider2, Provider<RequestInterceptor.Level> provider3) {
        return new RequestInterceptor_MembersInjector(provider, provider2, provider3);
    }

    @InjectedFieldSignature("com.jess.arms.http.log.RequestInterceptor.mHandler")
    public static void injectMHandler(RequestInterceptor requestInterceptor, GlobalHttpHandler globalHttpHandler) {
        requestInterceptor.mHandler = globalHttpHandler;
    }

    @InjectedFieldSignature("com.jess.arms.http.log.RequestInterceptor.mPrinter")
    public static void injectMPrinter(RequestInterceptor requestInterceptor, FormatPrinter formatPrinter) {
        requestInterceptor.mPrinter = formatPrinter;
    }

    @InjectedFieldSignature("com.jess.arms.http.log.RequestInterceptor.printLevel")
    public static void injectPrintLevel(RequestInterceptor requestInterceptor, RequestInterceptor.Level level) {
        requestInterceptor.printLevel = level;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RequestInterceptor requestInterceptor) {
        injectMHandler(requestInterceptor, this.mHandlerProvider.get());
        injectMPrinter(requestInterceptor, this.mPrinterProvider.get());
        injectPrintLevel(requestInterceptor, this.printLevelProvider.get());
    }
}
