package me.jessyan.lifecyclemodel;

import android.app.Activity;
import android.app.Application;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/* loaded from: classes2.dex */
public class LifecycleModelProviders {
    private static Activity checkActivity(Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("Can't create LifecycleModelStore for detached fragment");
    }

    private static Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application != null) {
            return application;
        }
        throw new IllegalStateException("Your activity/fragment is not yet attached to Application. You can't request LifecycleModel before onCreate call.");
    }

    @MainThread
    public static LifecycleModelStore of(@NonNull Fragment fragment) {
        checkApplication(checkActivity(fragment));
        return LifecycleModelStores.of(fragment);
    }

    @MainThread
    public static LifecycleModelStore of(@NonNull FragmentActivity fragmentActivity) {
        checkApplication(fragmentActivity);
        return LifecycleModelStores.of(fragmentActivity);
    }
}
