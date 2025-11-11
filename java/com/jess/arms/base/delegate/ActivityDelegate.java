package com.jess.arms.base.delegate;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public interface ActivityDelegate {
    public static final String ACTIVITY_DELEGATE = "ACTIVITY_DELEGATE";
    public static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    public static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    public static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    void onCreate(@Nullable Bundle bundle);

    void onDestroy();

    void onPause();

    void onResume();

    void onSaveInstanceState(@NonNull Bundle bundle);

    void onStart();

    void onStop();
}
