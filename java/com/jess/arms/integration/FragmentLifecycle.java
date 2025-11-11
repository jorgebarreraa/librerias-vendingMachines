package com.jess.arms.integration;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.jess.arms.base.delegate.FragmentDelegate;
import com.jess.arms.base.delegate.FragmentDelegateImpl;
import com.jess.arms.base.delegate.IFragment;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.IntelligentCache;
import com.jess.arms.utils.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* loaded from: classes.dex */
public class FragmentLifecycle extends FragmentManager.FragmentLifecycleCallbacks {
    @Inject
    public FragmentLifecycle() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FragmentDelegate fetchFragmentDelegate(Fragment fragment) {
        Log.d("FragmentLifecycle", "fetchFragmentDelegate");
        if (fragment instanceof IFragment) {
            return (FragmentDelegate) getCacheFromFragment((IFragment) fragment).get(IntelligentCache.getKeyOfKeep(FragmentDelegate.FRAGMENT_DELEGATE));
        }
        return null;
    }

    @NonNull
    private Cache<String, Object> getCacheFromFragment(IFragment iFragment) {
        Log.d("FragmentLifecycle", "getCacheFromFragment");
        Cache<String, Object> provideCache = iFragment.provideCache();
        Preconditions.checkNotNull(provideCache, "%s cannot be null on Fragment", Cache.class.getName());
        return provideCache;
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentActivityCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, Bundle bundle) {
        Log.d("FragmentLifecycle", "onFragmentActivityCreated");
        FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
        if (fetchFragmentDelegate != null) {
            fetchFragmentDelegate.onActivityCreate(bundle);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
        Log.d("FragmentLifecycle", "onFragmentAttached");
        if (fragment instanceof IFragment) {
            FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
            if (fetchFragmentDelegate == null || !fetchFragmentDelegate.isAdded()) {
                Cache<String, Object> cacheFromFragment = getCacheFromFragment((IFragment) fragment);
                FragmentDelegateImpl fragmentDelegateImpl = new FragmentDelegateImpl(fragmentManager, fragment);
                cacheFromFragment.put(IntelligentCache.getKeyOfKeep(FragmentDelegate.FRAGMENT_DELEGATE), fragmentDelegateImpl);
                fetchFragmentDelegate = fragmentDelegateImpl;
            }
            fetchFragmentDelegate.onAttach(context);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, Bundle bundle) {
        Log.d("FragmentLifecycle", "onFragmentCreated");
        FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
        if (fetchFragmentDelegate != null) {
            fetchFragmentDelegate.onCreate(bundle);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        Log.d("FragmentLifecycle", "onFragmentDestroyed");
        FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
        if (fetchFragmentDelegate != null) {
            fetchFragmentDelegate.onDestroy();
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentDetached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        Log.d("FragmentLifecycle", "onFragmentDetached");
        FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
        if (fetchFragmentDelegate != null) {
            fetchFragmentDelegate.onDetach();
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentPaused(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        Log.d("FragmentLifecycle", "onFragmentPaused");
        FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
        if (fetchFragmentDelegate != null) {
            fetchFragmentDelegate.onPause();
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentResumed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        Log.d("FragmentLifecycle", "onFragmentResumed");
        FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
        if (fetchFragmentDelegate != null) {
            fetchFragmentDelegate.onResume();
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentSaveInstanceState(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Bundle bundle) {
        Log.d("FragmentLifecycle", "onFragmentSaveInstanceState");
        FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
        if (fetchFragmentDelegate != null) {
            fetchFragmentDelegate.onSaveInstanceState(bundle);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentStarted(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        Log.d("FragmentLifecycle", "onFragmentStarted");
        FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
        if (fetchFragmentDelegate != null) {
            fetchFragmentDelegate.onStart();
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentStopped(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        Log.d("FragmentLifecycle", "onFragmentStopped");
        FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
        if (fetchFragmentDelegate != null) {
            fetchFragmentDelegate.onStop();
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull View view, Bundle bundle) {
        Log.d("FragmentLifecycle", "onFragmentViewCreated");
        FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
        if (fetchFragmentDelegate != null) {
            fetchFragmentDelegate.onCreateView(view, bundle);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentViewDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        Log.d("FragmentLifecycle", "onFragmentViewDestroyed");
        FragmentDelegate fetchFragmentDelegate = fetchFragmentDelegate(fragment);
        if (fetchFragmentDelegate != null) {
            fetchFragmentDelegate.onDestroyView();
        }
    }
}
