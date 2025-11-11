package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.yj.coffeemachines.mvp.contract.DIYContract;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

@FragmentScope
/* loaded from: classes.dex */
public class DIYPresenter extends BasePresenter<DIYContract.Model, DIYContract.View> {

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public DIYPresenter(DIYContract.Model model, DIYContract.View view) {
        super(model, view);
    }

    @Override // com.jess.arms.mvp.BasePresenter, com.jess.arms.mvp.IPresenter
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
