package dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class SetBuilder<T> {
    private static final String SET_CONTRIBUTIONS_CANNOT_BE_NULL = "Set contributions cannot be null";
    private final List<T> contributions;

    private SetBuilder(int i) {
        this.contributions = new ArrayList(i);
    }

    public static <T> SetBuilder<T> newSetBuilder(int i) {
        return new SetBuilder<>(i);
    }

    public SetBuilder<T> add(T t) {
        this.contributions.add(Preconditions.checkNotNull(t, SET_CONTRIBUTIONS_CANNOT_BE_NULL));
        return this;
    }

    public SetBuilder<T> addAll(Collection<? extends T> collection) {
        Iterator<? extends T> it2 = collection.iterator();
        while (it2.hasNext()) {
            Preconditions.checkNotNull(it2.next(), SET_CONTRIBUTIONS_CANNOT_BE_NULL);
        }
        this.contributions.addAll(collection);
        return this;
    }

    public Set<T> build() {
        int size = this.contributions.size();
        return size != 0 ? size != 1 ? Collections.unmodifiableSet(new HashSet(this.contributions)) : Collections.singleton(this.contributions.get(0)) : Collections.emptySet();
    }
}
