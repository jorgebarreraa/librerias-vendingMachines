package com.jess.arms.mvp;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.jess.arms.integration.IRepositoryManager;

/* loaded from: classes.dex */
public class BaseModel implements IModel, LifecycleObserver {
    protected IRepositoryManager mRepositoryManager;

    public BaseModel(IRepositoryManager iRepositoryManager) {
        this.mRepositoryManager = iRepositoryManager;
    }

    @Override // com.jess.arms.mvp.IModel
    public void onDestroy() {
        this.mRepositoryManager = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner lifecycleOwner) {
        lifecycleOwner.getLifecycle().removeObserver(this);
    }
}
