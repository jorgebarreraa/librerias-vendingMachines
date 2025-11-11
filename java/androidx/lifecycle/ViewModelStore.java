package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class ViewModelStore {
    private final HashMap<String, ViewModel> mMap = new HashMap<>();

    public final void clear() {
        Iterator<ViewModel> it2 = this.mMap.values().iterator();
        while (it2.hasNext()) {
            it2.next().clear();
        }
        this.mMap.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ViewModel get(String str) {
        return this.mMap.get(str);
    }

    Set<String> keys() {
        return new HashSet(this.mMap.keySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void put(String str, ViewModel viewModel) {
        ViewModel put = this.mMap.put(str, viewModel);
        if (put != null) {
            put.onCleared();
        }
    }
}
