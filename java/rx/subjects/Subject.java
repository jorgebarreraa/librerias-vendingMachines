package rx.subjects;

import rx.Observable;
import rx.Observer;

/* loaded from: classes2.dex */
public abstract class Subject<T, R> extends Observable<R> implements Observer<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public Subject(Observable.OnSubscribe<R> onSubscribe) {
        super(onSubscribe);
    }

    public abstract boolean hasObservers();

    public final SerializedSubject<T, R> toSerialized() {
        return getClass() == SerializedSubject.class ? (SerializedSubject) this : new SerializedSubject<>(this);
    }
}
