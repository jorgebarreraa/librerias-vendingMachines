package com.jess.arms.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jess.arms.base.delegate.IFragment;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.integration.lifecycle.FragmentLifecycleable;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.utils.ArmsUtils;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import javax.inject.Inject;

/* loaded from: classes.dex */
public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IFragment, FragmentLifecycleable {
    private Cache<String, Object> mCache;
    protected Context mContext;

    @Nullable
    @Inject
    protected P mPresenter;
    protected final String TAG = getClass().getSimpleName();
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return initView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        P p = this.mPresenter;
        if (p != null) {
            p.onDestroy();
        }
        this.mPresenter = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mContext = null;
    }

    @Override // com.jess.arms.base.delegate.IFragment
    @NonNull
    public synchronized Cache<String, Object> provideCache() {
        if (this.mCache == null) {
            this.mCache = ArmsUtils.obtainAppComponentFromContext(getActivity()).cacheFactory().build(CacheType.FRAGMENT_CACHE);
        }
        return this.mCache;
    }

    @Override // com.jess.arms.integration.lifecycle.Lifecycleable
    @NonNull
    public final Subject<FragmentEvent> provideLifecycleSubject() {
        return this.mLifecycleSubject;
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public boolean useEventBus() {
        return true;
    }
}
