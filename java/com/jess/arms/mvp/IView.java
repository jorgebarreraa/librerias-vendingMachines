package com.jess.arms.mvp;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;

/* loaded from: classes.dex */
public interface IView {

    /* renamed from: com.jess.arms.mvp.IView$-CC, reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static void $default$hideLoading(IView iView) {
        }

        public static void $default$killMyself(IView iView) {
        }

        public static void $default$launchActivity(@NonNull IView iView, Intent intent) {
            Preconditions.checkNotNull(intent);
            ArmsUtils.startActivity(intent);
        }

        public static void $default$showLoading(IView iView) {
        }
    }

    void hideLoading();

    void killMyself();

    void launchActivity(@NonNull Intent intent);

    void showLoading();

    void showMessage(@NonNull String str);
}
