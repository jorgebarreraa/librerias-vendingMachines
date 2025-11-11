package com.jess.arms.base.delegate;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jess.arms.integration.EventBusManager;
import com.jess.arms.utils.ArmsUtils;

/* loaded from: classes.dex */
public class ActivityDelegateImpl implements ActivityDelegate {
    private IActivity iActivity;
    private Activity mActivity;

    /* JADX WARN: Multi-variable type inference failed */
    public ActivityDelegateImpl(@NonNull Activity activity) {
        this.mActivity = activity;
        this.iActivity = (IActivity) activity;
    }

    @Override // com.jess.arms.base.delegate.ActivityDelegate
    public void onCreate(@Nullable Bundle bundle) {
        if (this.iActivity.useEventBus()) {
            EventBusManager.getInstance().register(this.mActivity);
        }
        this.iActivity.setupActivityComponent(ArmsUtils.obtainAppComponentFromContext(this.mActivity));
    }

    @Override // com.jess.arms.base.delegate.ActivityDelegate
    public void onDestroy() {
        IActivity iActivity = this.iActivity;
        if (iActivity != null && iActivity.useEventBus()) {
            EventBusManager.getInstance().unregister(this.mActivity);
        }
        this.iActivity = null;
        this.mActivity = null;
    }

    @Override // com.jess.arms.base.delegate.ActivityDelegate
    public void onPause() {
    }

    @Override // com.jess.arms.base.delegate.ActivityDelegate
    public void onResume() {
    }

    @Override // com.jess.arms.base.delegate.ActivityDelegate
    public void onSaveInstanceState(@NonNull Bundle bundle) {
    }

    @Override // com.jess.arms.base.delegate.ActivityDelegate
    public void onStart() {
    }

    @Override // com.jess.arms.base.delegate.ActivityDelegate
    public void onStop() {
    }
}
