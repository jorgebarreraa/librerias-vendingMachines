package com.yj.coffeemachines.app;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (activity.getIntent().getBooleanExtra("isInitToolbar", false)) {
            return;
        }
        activity.getIntent().putExtra("isInitToolbar", true);
        if (activity.findViewById(R.id.toolbar) != null) {
            if (activity instanceof AppCompatActivity) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
                appCompatActivity.setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
                appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            } else if (Build.VERSION.SDK_INT >= 21) {
                activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
                activity.getActionBar().setDisplayShowTitleEnabled(false);
            }
        }
        if (activity.findViewById(R.id.toolbar_title) != null) {
            ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
        }
        View decorView = activity.getWindow().getDecorView();
        decorView.setFocusable(true);
        decorView.setFocusableInTouchMode(true);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
