package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew_Msg;
import com.yj.coffeemachines.mvp.contract.EventContract;
import com.yj.coffeemachines.mvp.model.beans.ChangeBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@FragmentScope
/* loaded from: classes.dex */
public class EventPresenter extends BasePresenter<EventContract.Model, EventContract.View> {

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public EventPresenter(EventContract.Model model, EventContract.View view) {
        super(model, view);
    }

    private List<ProductBean.ProductDetailBean> convert(List<ProductBean.ProductDetailBean> list) {
        String[] isError = ErrorCodeNew_Msg.isError();
        for (ProductBean.ProductDetailBean productDetailBean : list) {
            if ("1".equals(isError[0])) {
                productDetailBean.setLocalSoldOut(getLocalSoldOut(productDetailBean.getLocalSoldOut(), true));
            } else {
                productDetailBean.setLocalSoldOut(getLocalSoldOut(productDetailBean.getLocalSoldOut(), false));
                List<String> rawMaterialTypes = getRawMaterialTypes(productDetailBean.getFormulaList());
                if (rawMaterialTypes.contains(Constants.ground1) || rawMaterialTypes.contains(Constants.ground2) || rawMaterialTypes.contains(Constants.ground3) || rawMaterialTypes.contains(Constants.ground4) || rawMaterialTypes.contains(Constants.ground5) || rawMaterialTypes.contains(Constants.ground6) || rawMaterialTypes.contains(Constants.ground7) || rawMaterialTypes.contains(Constants.ground8)) {
                    productDetailBean.setLocalSoldOut(getLocalSoldOut(productDetailBean.getLocalSoldOut(), Objects.equals(isError[1], "1")));
                }
                if (rawMaterialTypes.contains(Constants.ice1) || rawMaterialTypes.contains(Constants.ice2) || rawMaterialTypes.contains(Constants.ice3) || rawMaterialTypes.contains(Constants.ice4)) {
                    productDetailBean.setLocalSoldOut(getLocalSoldOut(productDetailBean.getLocalSoldOut(), isError[2] == "1"));
                }
                if (productDetailBean.getFormulaList() != null && !productDetailBean.getFormulaList().isEmpty()) {
                    if (productDetailBean.getFormulaList().get(0).getWaterType() == 1) {
                        productDetailBean.setLocalSoldOut(getLocalSoldOut(productDetailBean.getLocalSoldOut(), isError[4] == "1"));
                    } else {
                        productDetailBean.setLocalSoldOut(getLocalSoldOut(productDetailBean.getLocalSoldOut(), isError[5] == "1"));
                    }
                }
            }
        }
        return list;
    }

    private int getLocalSoldOut(int i, boolean z) {
        return (i == 1 || z) ? 1 : 0;
    }

    private List<String> getRawMaterialTypes(List<ProductBean.ProductDetailBean.FormulaListBean> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ProductBean.ProductDetailBean.FormulaListBean> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().getRawMaterialType());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$submit$2() throws Exception {
    }

    public /* synthetic */ void lambda$submit$0$EventPresenter(Disposable disposable) throws Exception {
        ArmsUtils.snackbarText(((EventContract.View) this.mRootView).getActivity().getString(R.string.dataloading));
    }

    public /* synthetic */ List lambda$submit$1$EventPresenter(ChangeBack changeBack) throws Exception {
        if (changeBack.getCode() != 200) {
            throw new Exception(changeBack.getMsg());
        }
        ArrayList arrayList = new ArrayList();
        List<ProductBean.ProductDetailBean> data = changeBack.getData();
        for (int i = 0; i < data.size(); i++) {
            ProductBean.ProductDetailBean productDetailBean = data.get(i);
            if (productDetailBean != null && productDetailBean.getName() != null && !productDetailBean.getName().isEmpty()) {
                productDetailBean.setLocalSoldOut(0);
                arrayList.add(productDetailBean);
            }
        }
        return convert(arrayList);
    }

    @Override // com.jess.arms.mvp.BasePresenter, com.jess.arms.mvp.IPresenter
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void submit(final String str) {
        ((EventContract.Model) this.mModel).submitCode(str).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$EventPresenter$UH5aE-itn2A7q5YEm2Z9JALCk-A
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                EventPresenter.this.lambda$submit$0$EventPresenter((Disposable) obj);
            }
        }).map(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$EventPresenter$SIFJsjvy7-uTZpRMXLJTT36dIsE
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return EventPresenter.this.lambda$submit$1$EventPresenter((ChangeBack) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$EventPresenter$MN1f3uG3UVP663yxRS3dmCIAEQM
            @Override // io.reactivex.functions.Action
            public final void run() {
                EventPresenter.lambda$submit$2();
            }
        }).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<List<ProductBean.ProductDetailBean>>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.EventPresenter.1
            @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
            public void onError(Throwable th) {
                super.onError(th);
                ArmsUtils.snackbarText(th.getMessage());
            }

            @Override // io.reactivex.Observer
            public void onNext(List<ProductBean.ProductDetailBean> list) {
                ((EventContract.View) EventPresenter.this.mRootView).showExchangeDialog(str, list);
            }
        });
    }
}
