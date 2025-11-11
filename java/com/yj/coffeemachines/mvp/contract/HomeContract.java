package com.yj.coffeemachines.mvp.contract;

import android.app.Activity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.yj.coffeemachines.mvp.model.beans.AliPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.DigitalRefundBack;
import com.yj.coffeemachines.mvp.model.beans.LoginBack;
import com.yj.coffeemachines.mvp.model.beans.OrderCancelBack;
import com.yj.coffeemachines.mvp.model.beans.OrderRefundBean;
import com.yj.coffeemachines.mvp.model.beans.OutStockOverBack;
import com.yj.coffeemachines.mvp.model.beans.ProduceFailBack;
import com.yj.coffeemachines.mvp.model.beans.ProduceOverBack;
import com.yj.coffeemachines.mvp.model.beans.UnionPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.WeChartPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.icbcPayReturnAmtBack;
import com.yj.coffeemachines.mvp.model.beans.refund;
import io.reactivex.Observable;

/* loaded from: classes.dex */
public interface HomeContract {

    /* loaded from: classes.dex */
    public interface Model extends IModel {
        Observable<DigitalRefundBack> ERMBReturnAmt();

        Observable<DigitalRefundBack> ERMBReturnAmtHui(String str);

        Observable<AliPayReturnAmtBack> aLiPayReturnAmt();

        Observable<Boolean> cancelOrder();

        Observable<icbcPayReturnAmtBack> icbcPayReturnAmt();

        Observable<LoginBack> login(String str);

        Observable<OrderCancelBack> orderCancel(String str);

        Observable<OutStockOverBack> outStockOver();

        Observable<ProduceFailBack> produceFail();

        Observable<ProduceOverBack> produceOver();

        Observable<refund> refund();

        Observable<OrderRefundBean> refundOrder(String str);

        Observable<Object> saveOrderFailLog(String str);

        Observable<Object> t50PayRefund();

        Observable<UnionPayReturnAmtBack> unionPayReturnAmt();

        Observable<UnionPayReturnAmtBack> unionPayReturnAmt(String str);

        Observable<String> uploadState(String str);

        Observable<WeChartPayReturnAmtBack> weChartPayReturnAmt();
    }

    /* loaded from: classes.dex */
    public interface View extends IView {
        Activity getActivity();

        String getState();

        void loginSuccess(LoginBack loginBack);
    }
}
