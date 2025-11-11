package butterknife;

import androidx.annotation.UiThread;
import butterknife.Unbinder;

/* loaded from: classes.dex */
public interface Unbinder {
    public static final Unbinder EMPTY = new Unbinder() { // from class: butterknife.-$$Lambda$Unbinder$aAZZEjRDCcxQlrGZAdKWmLXqruY
        @Override // butterknife.Unbinder
        public final void unbind() {
            Unbinder.CC.lambda$static$0();
        }
    };

    /* renamed from: butterknife.Unbinder$-CC, reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static /* synthetic */ void lambda$static$0() {
        }
    }

    @UiThread
    void unbind();
}
