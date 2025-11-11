package rx.android.plugins;

import java.util.concurrent.atomic.AtomicReference;
import rx.annotations.Experimental;

/* loaded from: classes2.dex */
public final class RxAndroidPlugins {
    private static final RxAndroidPlugins INSTANCE = new RxAndroidPlugins();
    private final AtomicReference<RxAndroidSchedulersHook> schedulersHook = new AtomicReference<>();

    RxAndroidPlugins() {
    }

    public static RxAndroidPlugins getInstance() {
        return INSTANCE;
    }

    public RxAndroidSchedulersHook getSchedulersHook() {
        if (this.schedulersHook.get() == null) {
            this.schedulersHook.compareAndSet(null, RxAndroidSchedulersHook.getDefaultInstance());
        }
        return this.schedulersHook.get();
    }

    public void registerSchedulersHook(RxAndroidSchedulersHook rxAndroidSchedulersHook) {
        if (this.schedulersHook.compareAndSet(null, rxAndroidSchedulersHook)) {
            return;
        }
        throw new IllegalStateException("Another strategy was already registered: " + this.schedulersHook.get());
    }

    @Experimental
    public void reset() {
        this.schedulersHook.set(null);
    }
}
