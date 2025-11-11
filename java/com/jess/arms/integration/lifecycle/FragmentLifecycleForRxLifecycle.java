package com.jess.arms.integration.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.subjects.Subject;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jetbrains.annotations.NotNull;

@Singleton
/* loaded from: classes.dex */
public class FragmentLifecycleForRxLifecycle extends FragmentManager.FragmentLifecycleCallbacks {
    @Inject
    public FragmentLifecycleForRxLifecycle() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Subject<FragmentEvent> obtainSubject(Fragment fragment) {
        return ((FragmentLifecycleable) fragment).provideLifecycleSubject();
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentAttached(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Fragment fragment, @NonNull @NotNull Context context) {
        if (fragment instanceof FragmentLifecycleable) {
            obtainSubject(fragment).onNext(FragmentEvent.ATTACH);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentCreated(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Fragment fragment, Bundle bundle) {
        if (fragment instanceof FragmentLifecycleable) {
            obtainSubject(fragment).onNext(FragmentEvent.CREATE);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentDestroyed(@NotNull FragmentManager fragmentManager, @NotNull Fragment fragment) {
        if (fragment instanceof FragmentLifecycleable) {
            obtainSubject(fragment).onNext(FragmentEvent.DESTROY);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentDetached(@NotNull FragmentManager fragmentManager, @NotNull Fragment fragment) {
        if (fragment instanceof FragmentLifecycleable) {
            obtainSubject(fragment).onNext(FragmentEvent.DETACH);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentPaused(@NotNull FragmentManager fragmentManager, @NotNull Fragment fragment) {
        if (fragment instanceof FragmentLifecycleable) {
            obtainSubject(fragment).onNext(FragmentEvent.PAUSE);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentResumed(@NotNull FragmentManager fragmentManager, @NotNull Fragment fragment) {
        if (fragment instanceof FragmentLifecycleable) {
            obtainSubject(fragment).onNext(FragmentEvent.RESUME);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentStarted(@NotNull FragmentManager fragmentManager, @NotNull Fragment fragment) {
        if (fragment instanceof FragmentLifecycleable) {
            obtainSubject(fragment).onNext(FragmentEvent.START);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentStopped(@NotNull FragmentManager fragmentManager, @NotNull Fragment fragment) {
        if (fragment instanceof FragmentLifecycleable) {
            obtainSubject(fragment).onNext(FragmentEvent.STOP);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentViewCreated(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Fragment fragment, @NonNull @NotNull View view, Bundle bundle) {
        if (fragment instanceof FragmentLifecycleable) {
            obtainSubject(fragment).onNext(FragmentEvent.CREATE_VIEW);
        }
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public void onFragmentViewDestroyed(@NotNull FragmentManager fragmentManager, @NotNull Fragment fragment) {
        if (fragment instanceof FragmentLifecycleable) {
            obtainSubject(fragment).onNext(FragmentEvent.DESTROY_VIEW);
        }
    }
}
