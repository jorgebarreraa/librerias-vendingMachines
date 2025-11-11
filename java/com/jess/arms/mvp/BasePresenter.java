package com.jess.arms.mvp;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.jess.arms.integration.EventBusManager;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.Preconditions;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/* loaded from: classes.dex */
public class BasePresenter<M extends IModel, V extends IView> implements IPresenter, LifecycleObserver {
    protected final String TAG = getClass().getSimpleName();
    protected CompositeDisposable mCompositeDisposable;
    protected M mModel;
    protected V mRootView;

    public BasePresenter() {
        onStart();
    }

    public BasePresenter(M m, V v) {
        Preconditions.checkNotNull(m, "%s cannot be null", IModel.class.getName());
        Preconditions.checkNotNull(v, "%s cannot be null", IView.class.getName());
        this.mModel = m;
        this.mRootView = v;
        onStart();
    }

    public BasePresenter(V v) {
        Preconditions.checkNotNull(v, "%s cannot be null", IView.class.getName());
        this.mRootView = v;
        onStart();
    }

    public void addDispose(Disposable disposable) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(disposable);
    }

    @Override // com.jess.arms.mvp.IPresenter
    public void onDestroy() {
        if (useEventBus()) {
            EventBusManager.getInstance().unregister(this);
        }
        unDispose();
        M m = this.mModel;
        if (m != null) {
            m.onDestroy();
        }
        this.mModel = null;
        this.mRootView = null;
        this.mCompositeDisposable = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner lifecycleOwner) {
        lifecycleOwner.getLifecycle().removeObserver(this);
    }

    @Override // com.jess.arms.mvp.IPresenter
    public void onStart() {
        V v = this.mRootView;
        if (v != null && (v instanceof LifecycleOwner)) {
            ((LifecycleOwner) v).getLifecycle().addObserver(this);
            M m = this.mModel;
            if (m != null && (m instanceof LifecycleObserver)) {
                ((LifecycleOwner) this.mRootView).getLifecycle().addObserver((LifecycleObserver) this.mModel);
            }
        }
        if (useEventBus()) {
            EventBusManager.getInstance().register(this);
        }
    }

    public void unDispose() {
        CompositeDisposable compositeDisposable = this.mCompositeDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public boolean useEventBus() {
        return true;
    }
}
