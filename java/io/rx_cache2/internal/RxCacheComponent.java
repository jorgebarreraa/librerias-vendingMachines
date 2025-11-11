package io.rx_cache2.internal;

import dagger.Component;
import javax.inject.Singleton;

@Component(modules = {RxCacheModule.class})
@Singleton
/* loaded from: classes2.dex */
public interface RxCacheComponent {
    ProcessorProviders providers();
}
