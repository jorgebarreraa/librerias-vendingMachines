package com.trello.rxlifecycle2.android;

import android.view.View;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.MainThreadDisposable;

/* loaded from: classes.dex */
final class ViewDetachesOnSubscribe implements ObservableOnSubscribe<Object> {
    static final Object SIGNAL = new Object();
    final View view;

    /* loaded from: classes.dex */
    class EmitterListener extends MainThreadDisposable implements View.OnAttachStateChangeListener {
        final ObservableEmitter<Object> emitter;

        public EmitterListener(ObservableEmitter<Object> observableEmitter) {
            this.emitter = observableEmitter;
        }

        @Override // io.reactivex.android.MainThreadDisposable
        protected void onDispose() {
            ViewDetachesOnSubscribe.this.view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            this.emitter.onNext(ViewDetachesOnSubscribe.SIGNAL);
        }
    }

    public ViewDetachesOnSubscribe(View view) {
        this.view = view;
    }

    @Override // io.reactivex.ObservableOnSubscribe
    public void subscribe(ObservableEmitter<Object> observableEmitter) throws Exception {
        MainThreadDisposable.verifyMainThread();
        EmitterListener emitterListener = new EmitterListener(observableEmitter);
        observableEmitter.setDisposable(emitterListener);
        this.view.addOnAttachStateChangeListener(emitterListener);
    }
}
