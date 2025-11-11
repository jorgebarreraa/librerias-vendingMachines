package com.jess.arms.base;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.hjq.language.MultiLanguages;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.utils.ArmsUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import javax.inject.Inject;

/* loaded from: classes.dex */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity, ActivityLifecycleable {
    private Cache<String, Object> mCache;

    @Nullable
    @Inject
    protected P mPresenter;
    private Unbinder mUnbinder;
    protected final String TAG = getClass().getSimpleName();
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(MultiLanguages.attach(context));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        try {
            int initView = initView(bundle);
            if (initView != 0) {
                setContentView(initView);
                this.mUnbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            if (e instanceof InflateException) {
                throw e;
            }
            e.printStackTrace();
        }
        initData(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Unbinder unbinder = this.mUnbinder;
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            this.mUnbinder.unbind();
        }
        this.mUnbinder = null;
        P p = this.mPresenter;
        if (p != null) {
            p.onDestroy();
        }
        this.mPresenter = null;
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        InputMethodManager inputMethodManager;
        return (getCurrentFocus() == null || (inputMethodManager = (InputMethodManager) getSystemService("input_method")) == null) ? super.onTouchEvent(motionEvent) : inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override // com.jess.arms.base.delegate.IActivity
    @NonNull
    public synchronized Cache<String, Object> provideCache() {
        if (this.mCache == null) {
            this.mCache = ArmsUtils.obtainAppComponentFromContext(this).cacheFactory().build(CacheType.ACTIVITY_CACHE);
        }
        return this.mCache;
    }

    @Override // com.jess.arms.integration.lifecycle.Lifecycleable
    @NonNull
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return this.mLifecycleSubject;
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public boolean useEventBus() {
        return true;
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public boolean useFragment() {
        return true;
    }
}
