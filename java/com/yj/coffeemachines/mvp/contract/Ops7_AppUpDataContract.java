package com.yj.coffeemachines.mvp.contract;

import android.content.Context;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.yj.coffeemachines.mvp.model.beans.AppUploadBean;
import io.reactivex.Observable;
import java.io.File;

/* loaded from: classes.dex */
public interface Ops7_AppUpDataContract {

    /* loaded from: classes.dex */
    public interface Model extends IModel {
        Observable<File> downLoadApp(String str);

        Observable<AppUploadBean> uploadApp();
    }

    /* loaded from: classes.dex */
    public interface View extends IView {
        Context getActivity();

        void makeDialogNewVersion(AppUploadBean appUploadBean);
    }
}
