package com.jess.arms.di.module;

import com.jess.arms.http.log.FormatPrinter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class GlobalConfigModule_ProvideFormatPrinterFactory implements Factory<FormatPrinter> {
    private final GlobalConfigModule module;

    public GlobalConfigModule_ProvideFormatPrinterFactory(GlobalConfigModule globalConfigModule) {
        this.module = globalConfigModule;
    }

    public static GlobalConfigModule_ProvideFormatPrinterFactory create(GlobalConfigModule globalConfigModule) {
        return new GlobalConfigModule_ProvideFormatPrinterFactory(globalConfigModule);
    }

    public static FormatPrinter provideFormatPrinter(GlobalConfigModule globalConfigModule) {
        return (FormatPrinter) Preconditions.checkNotNull(globalConfigModule.provideFormatPrinter(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    public FormatPrinter get() {
        return provideFormatPrinter(this.module);
    }
}
