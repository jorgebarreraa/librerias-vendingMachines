package com.jess.arms.base.delegate;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.jess.arms.integration.EventBusManager;
import com.jess.arms.utils.ArmsUtils;
import timber.log.Timber;

/* loaded from: classes.dex */
public class FragmentDelegateImpl implements FragmentDelegate {
    private IFragment iFragment;
    private Fragment mFragment;
    private FragmentManager mFragmentManager;
    private Unbinder mUnbinder;

    /* JADX WARN: Multi-variable type inference failed */
    public FragmentDelegateImpl(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        this.mFragmentManager = fragmentManager;
        this.mFragment = fragment;
        this.iFragment = (IFragment) fragment;
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public boolean isAdded() {
        Fragment fragment = this.mFragment;
        return fragment != null && fragment.isAdded();
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onActivityCreate(@Nullable Bundle bundle) {
        this.iFragment.initData(bundle);
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onAttach(@NonNull Context context) {
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onCreate(@Nullable Bundle bundle) {
        if (this.iFragment.useEventBus()) {
            EventBusManager.getInstance().register(this.mFragment);
        }
        this.iFragment.setupFragmentComponent(ArmsUtils.obtainAppComponentFromContext(this.mFragment.getActivity()));
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onCreateView(@Nullable View view, @Nullable Bundle bundle) {
        if (view != null) {
            this.mUnbinder = ButterKnife.bind(this.mFragment, view);
        }
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onDestroy() {
        IFragment iFragment = this.iFragment;
        if (iFragment != null && iFragment.useEventBus()) {
            EventBusManager.getInstance().unregister(this.mFragment);
        }
        this.mUnbinder = null;
        this.mFragmentManager = null;
        this.mFragment = null;
        this.iFragment = null;
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onDestroyView() {
        Unbinder unbinder = this.mUnbinder;
        if (unbinder == null || unbinder == Unbinder.EMPTY) {
            return;
        }
        try {
            this.mUnbinder.unbind();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Timber.w("onDestroyView: %s", e.getMessage());
        }
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onDetach() {
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onPause() {
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onResume() {
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onSaveInstanceState(@NonNull Bundle bundle) {
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onStart() {
    }

    @Override // com.jess.arms.base.delegate.FragmentDelegate
    public void onStop() {
    }
}
