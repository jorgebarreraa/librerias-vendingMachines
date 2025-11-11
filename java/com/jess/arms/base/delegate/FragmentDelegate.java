package com.jess.arms.base.delegate;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public interface FragmentDelegate {
    public static final String FRAGMENT_DELEGATE = "FRAGMENT_DELEGATE";

    boolean isAdded();

    void onActivityCreate(@Nullable Bundle bundle);

    void onAttach(@NonNull Context context);

    void onCreate(@Nullable Bundle bundle);

    void onCreateView(@Nullable View view, @Nullable Bundle bundle);

    void onDestroy();

    void onDestroyView();

    void onDetach();

    void onPause();

    void onResume();

    void onSaveInstanceState(@NonNull Bundle bundle);

    void onStart();

    void onStop();
}
