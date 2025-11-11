package com.jess.arms.base;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.hjq.language.MultiLanguages;
import com.jess.arms.integration.EventBusManager;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/* loaded from: classes.dex */
public abstract class BaseService extends Service {
    protected final String TAG = getClass().getSimpleName();
    protected CompositeDisposable mCompositeDisposable;

    protected void addDispose(Disposable disposable) {
        if (this.mCompositeDisposable == null) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(disposable);
    }

    @Override // android.app.Service, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(MultiLanguages.attach(context));
    }

    public abstract void init();

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (useEventBus()) {
            EventBusManager.getInstance().register(this);
        }
        init();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            EventBusManager.getInstance().unregister(this);
        }
        unDispose();
        this.mCompositeDisposable = null;
    }

    protected void unDispose() {
        CompositeDisposable compositeDisposable = this.mCompositeDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public boolean useEventBus() {
        return true;
    }
}
