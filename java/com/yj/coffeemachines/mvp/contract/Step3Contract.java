package com.yj.coffeemachines.mvp.contract;

import android.content.Context;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.yj.coffeemachines.mvp.model.beans.ALiPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.DigitalPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.GenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.OrderCancelBack;
import com.yj.coffeemachines.mvp.model.beans.OrderStateBean;
import com.yj.coffeemachines.mvp.model.beans.PayCodeGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.PayOrderBean;
import com.yj.coffeemachines.mvp.model.beans.PayWaySettingBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.model.beans.QueryPayResulBack;
import com.yj.coffeemachines.mvp.model.beans.T50PayOrderQrBean;
import com.yj.coffeemachines.mvp.model.beans.T50PayWayBean;
import com.yj.coffeemachines.mvp.model.beans.UnionPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.WeChartPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.aggregationCodePayment;
import com.yj.coffeemachines.mvp.model.beans.barcodepay;
import com.yj.coffeemachines.mvp.model.beans.icbcPayGenOrderBack;
import io.reactivex.Observable;
import java.util.List;

/* loaded from: classes.dex */
public interface Step3Contract {

    /* loaded from: classes.dex */
    public interface Model extends IModel {
        Observable<aggregationCodePayment> aggregationCodePayment();

        Observable<barcodepay> barcodepay(String str);

        Observable<Boolean> cancelOrder();

        Observable<QueryPayResulBack> checkPayState();

        Observable<DigitalPayGenOrderBack> digitalPayGenOrder();

        Observable<DigitalPayGenOrderBack> digitalPayGenOrder(String str);

        Observable<String> getPayWaySetting();

        Observable<icbcPayGenOrderBack> icbcPayGenOrder();

        Observable<OrderCancelBack> orderCancel(String str);

        Observable<UnionPayGenOrderBack> putOrder();

        Observable<ALiPayGenOrderBack> putOrderALiPay();

        Observable<GenOrderBack> putOrderCrsh(int i);

        Observable<PayCodeGenOrderBack> putOrderPayCode(String str);

        Observable<WeChartPayGenOrderBack> putOrderWeCharPay();

        Observable<OrderStateBean> queryOrder();

        Observable<T50PayOrderQrBean> t50PayCreateQr(String str);

        Observable<List<T50PayWayBean>> t50PayGetWay();

        Observable<PayOrderBean> unifiedOrder(int i);
    }

    /* loaded from: classes.dex */
    public interface View extends IView {
        Context getActivity();

        boolean isSaobeiScan();

        void payError();

        void payError(int i);

        void paySuccess();

        void payWaySettingFail();

        void payWaySettingSuccess(PayWaySettingBack payWaySettingBack);

        void putOrderFailQrcode(String str);

        void putOrderOtherSuccess(GenOrderBack genOrderBack, int i);

        void putOrderSuccessALiPay(ALiPayGenOrderBack.DataBean dataBean);

        void putOrderSuccessAggregationCodePayment(aggregationCodePayment.DataBean dataBean);

        void putOrderSuccessERMB(DigitalPayGenOrderBack.Databean1 databean1);

        void putOrderSuccessERMBCode(WeChartPayGenOrderBack.DataBean dataBean);

        void putOrderSuccessHui(DigitalPayGenOrderBack.Databean1 databean1);

        void putOrderSuccessIcbc(icbcPayGenOrderBack.DataBean dataBean);

        void putOrderSuccessSaoBeiFan(barcodepay.DataBean dataBean);

        void putOrderSuccessUnion(UnionPayGenOrderBack.DataBean dataBean);

        void putOrderSuccessWeChar(PayOrderBean payOrderBean);

        void showDetailMessage(ProductBean.ProductDetailBean productDetailBean);

        void showLoading(String str);

        void showOrderFail(boolean z);

        void t50PayCreateQr(T50PayOrderQrBean t50PayOrderQrBean);

        void t50PayWay(List<T50PayWayBean> list);
    }
}
