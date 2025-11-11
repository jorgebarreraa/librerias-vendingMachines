package com.yj.coffeemachines.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import butterknife.BindView;
import butterknife.OnClick;
import com.blankj.utilcode.util.NumberUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.DevConfig;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.OpsSeting;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.serialport.SerialPortManager;
import com.yj.coffeemachines.app.service.SerialOrderBytes;
import com.yj.coffeemachines.app.utils.ByteUtils;
import com.yj.coffeemachines.app.utils.CRC8Util;
import com.yj.coffeemachines.app.utils.DiaLogUtils;
import com.yj.coffeemachines.app.utils.QRCodeUtils;
import com.yj.coffeemachines.carousel.Advance;
import com.yj.coffeemachines.carousel.AdvanceView;
import com.yj.coffeemachines.constants.PayType;
import com.yj.coffeemachines.di.component.DaggerStep3Component;
import com.yj.coffeemachines.dialog.OrderFailDialog;
import com.yj.coffeemachines.dialog.T50PayDialog;
import com.yj.coffeemachines.dialog.loading.LoadAtDialog;
import com.yj.coffeemachines.eventbusbean.DialogMessageNew;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew_Msg;
import com.yj.coffeemachines.eventbusbean.ErrorCode_Instant;
import com.yj.coffeemachines.eventbusbean.ErrorCode_Payment;
import com.yj.coffeemachines.eventbusbean.ErrorCode_Paymenting;
import com.yj.coffeemachines.eventbusbean.MoneyReceiveMessage;
import com.yj.coffeemachines.eventbusbean.MyEventBusMessage;
import com.yj.coffeemachines.eventbusbean.QRCodeMessage;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import com.yj.coffeemachines.helper.LanguageHelper;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Step3Contract;
import com.yj.coffeemachines.mvp.model.beans.ALiPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.DigitalPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.GenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.PayOrderBean;
import com.yj.coffeemachines.mvp.model.beans.PayWaySettingBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.model.beans.T50PayOrderQrBean;
import com.yj.coffeemachines.mvp.model.beans.T50PayWayBean;
import com.yj.coffeemachines.mvp.model.beans.UnionPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.WeChartPayGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.aggregationCodePayment;
import com.yj.coffeemachines.mvp.model.beans.barcodepay;
import com.yj.coffeemachines.mvp.model.beans.icbcPayGenOrderBack;
import com.yj.coffeemachines.mvp.presenter.Step3Presenter;
import com.yj.coffeemachines.pay.mdb.MdbSendMethod;
import com.yj.coffeemachines.pay.mdb.MdbUtils;
import com.yj.coffeemachines.pay.mdb.TransmissionUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class Step3Fragment extends BaseFragment<Step3Presenter> implements Step3Contract.View {
    private static final int COUNTDOWNTIME = 180;
    public static String tag = "Step3Fragment";

    @BindView(R.id.constraintLayout2)
    ConstraintLayout constraintLayout2;

    @BindView(R.id.constraintLayout3)
    ConstraintLayout constraintLayout3;
    private CountDownTimer countDownTimer;
    private Handler handler;
    LoadAtDialog loadingDialog;

    @BindView(R.id.banner)
    AdvanceView mBanner;

    @BindView(R.id.btn_back)
    Button mBtnBack;

    @BindView(R.id.countdowntime)
    TextView mCountdowntime;

    @BindView(R.id.iv_qrcode)
    ImageView mIvQrcode;

    @BindView(R.id.layout_change)
    LinearLayout mLayoutChange;

    @BindView(R.id.ll_pay_code)
    LinearLayout mLayoutPayCode;

    @BindView(R.id.layout_payother)
    LinearLayout mLayoutPayother;

    @BindView(R.id.payway)
    TextView mPayway;

    @Inject
    AlertDialog mSportDialog;

    @BindView(R.id.tv_changenumber)
    TextView mTvChangenumber;

    @BindView(R.id.tv_changenumber_en)
    TextView mTvChangenumberEn;

    @BindView(R.id.tv_errormsg)
    TextView mTvErrormsg;

    @BindView(R.id.tv_pay1)
    LinearLayout mTvPay1;

    @BindView(R.id.tv_pay2)
    LinearLayout mTvPay2;

    @BindView(R.id.tv_pay3)
    LinearLayout mTvPay3;

    @BindView(R.id.tv_pay4)
    LinearLayout mTvPay4;

    @BindView(R.id.tv_pay5)
    LinearLayout mTvPay5;

    @BindView(R.id.tv_pay6)
    LinearLayout mTvPay6;

    @BindView(R.id.tv_pay7)
    LinearLayout mTvPay7;

    @BindView(R.id.tv_pay8)
    LinearLayout mTvPay8;

    @BindView(R.id.tv_pay9)
    LinearLayout mTvPay9;

    @BindView(R.id.tv_pice)
    TextView mTvPice;

    @BindView(R.id.tv_pice_en)
    TextView mTvPiceEn;

    @BindView(R.id.tv_productname)
    TextView mTvProductname;

    @BindView(R.id.tv_recivernumber)
    TextView mTvRecivernumber;

    @BindView(R.id.tv_recivernumber_en)
    TextView mTvRecivernumberEn;
    String m_ProName;
    String moneycode;
    private T50PayDialog payDialog;

    @BindView(R.id.tv_pay_newcape)
    LinearLayout payNewCape;

    @BindView(R.id.tv_pay_north_star)
    LinearLayout payNorthStar;

    @BindView(R.id.step3_cl)
    ConstraintLayout step3_cl;

    @BindView(R.id.step3_cl_main)
    ConstraintLayout step3_cl_main;

    @BindView(R.id.step3_tv_price)
    TextView step3_tv_price;

    @BindView(R.id.step3_tv_zhifu)
    TextView step3_tv_zhifu;

    @BindView(R.id.step3_tv_zhifufangshi)
    TextView step3_tv_zhifufangshi;

    @BindView(R.id.step3_view)
    View step3_view;

    @BindView(R.id.tv_pay_code_tips)
    TextView tvPayCodeTips;

    @BindView(R.id.tv_pay4_mian)
    ImageView tv_pay4_mian;

    @BindView(R.id.tv_pay_agt)
    LinearLayout tv_pay_agt;

    @BindView(R.id.tv_pay_bao)
    LinearLayout tv_pay_bao;

    @BindView(R.id.tv_pay_saobei_fansao_13)
    LinearLayout tv_pay_saobei_fansao_13;

    @BindView(R.id.tv_pay_saobei_zhengsao_13)
    LinearLayout tv_pay_saobei_zhengsao_13;

    @BindView(R.id.tv_pay_t50)
    LinearLayout tv_pay_t50;

    @BindView(R.id.tv_pay_tp)
    LinearLayout tv_pay_tp;
    private int nowtime = 180;
    boolean putOrderSuccess = false;
    private boolean checkState = true;
    SerialPortManager serialPortManager = null;
    private boolean isCard = true;
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MoneyReceiveMessage moneyReceiveMessage = (MoneyReceiveMessage) intent.getSerializableExtra("MoneyReceive");
            if (moneyReceiveMessage != null) {
                Tools.upLocalLog("广播-MoneyReceiveMessage；" + moneyReceiveMessage.toString());
                Step3Fragment.this.setMoneyText(moneyReceiveMessage);
            }
        }
    };
    private final IntentFilter filter = new IntentFilter("com.yj.coffeemachines.MONEY_RECEIVE");
    private int cyclicQueryTimes = 30;
    private int count = 0;
    boolean isPaySuccess = false;
    private int onePay = 0;

    /* loaded from: classes.dex */
    public static class BackReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra(NotificationCompat.CATEGORY_MESSAGE), 0).show();
            EventBus.getDefault().post(new MyEventBusMessage(Step3Fragment.tag, MyEventBusMessage.TYPE.CLICK, "", 1));
        }
    }

    /* loaded from: classes.dex */
    public static class PayReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra(NotificationCompat.CATEGORY_MESSAGE), 0).show();
            EventBus.getDefault().post(new ErrorCode_Paymenting(true, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS.name()));
        }
    }

    static /* synthetic */ int access$108(Step3Fragment step3Fragment) {
        int i = step3Fragment.count;
        step3Fragment.count = i + 1;
        return i;
    }

    private void backHandler() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
    }

    private void btnBack(String str) {
        hideLoading();
        EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
        Tools.upLocalLogTest("返回商品列表:" + str);
    }

    private void btnBackPayment(int i) {
        Constants.PAYSTATE = 2;
        stopGetPayState();
        ((Step3Presenter) this.mPresenter).orderCancel(Constants.pay_no, i);
    }

    private void changeCardReadState(int i) {
        if (MyAppLocation.myAppLocation == null || MyAppLocation.myAppLocation.mSerialDataService == null) {
            return;
        }
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(Constants.drinkStatus_payment((byte) i));
    }

    private void checkAbnormal() {
        if (ErrorCodeNew_Msg.MacState == ErrorCodeNew.Error || ErrorCodeNew_Msg.isError()[0].equals("1")) {
            this.cyclicQueryTimes = 30;
            this.checkState = true;
            return;
        }
        if (!ErrorCodeNew_Msg.ErrorNew.contains(ErrorCodeNew.A09.name())) {
            this.cyclicQueryTimes = 0;
            this.checkState = false;
            return;
        }
        this.cyclicQueryTimes = 30;
        this.checkState = true;
        if (MyAppLocation.myAppLocation == null || MyAppLocation.myAppLocation.mSerialDataService == null) {
            Tools.upLocalLog("step3_MyAppLocation.myAppLocation.mSerialDataService_为空");
            EventBus.getDefault().post(new DialogMessageNew(true, "速溶串口有问题_杯托有杯，请取杯", true, 1, 30));
            btnBack("杯托有杯");
        } else {
            if (MyAppLocation.myAppLocation.voicePlayService != null) {
                MyAppLocation.myAppLocation.voicePlayService.playVoice(5);
            } else {
                Tools.upLocalLog("step3_MyAppLocation.myAppLocation.voicePlayService_为空");
            }
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant_main(new byte[]{-86, 85, 4, 36, 0, 20, 59});
            showLoading(getResources().getString(R.string.present_cup_hint));
        }
    }

    private void checkDeviceState() {
        if (ErrorCodeNew_Msg.ErrorNew.contains(ErrorCodeNew.A09.name())) {
            AlertDialog alertDialog = this.mSportDialog;
            if (alertDialog == null || !alertDialog.isShowing()) {
                Constants.ISMAKINGADRINK = false;
                this.cyclicQueryTimes = 30;
                this.checkState = true;
                if (MyAppLocation.myAppLocation == null || MyAppLocation.myAppLocation.mSerialDataService == null) {
                    Tools.upLocalLog("step3_MyAppLocation.myAppLocation.mSerialDataService_为空");
                    EventBus.getDefault().post(new DialogMessageNew(true, "速溶串口有问题_杯托有杯，请取杯", true, 1, 30));
                    btnBack("杯托有杯2");
                } else {
                    if (MyAppLocation.myAppLocation.voicePlayService != null) {
                        MyAppLocation.myAppLocation.voicePlayService.playVoice(5);
                    } else {
                        Tools.upLocalLog("step3_MyAppLocation.myAppLocation.voicePlayService_为空");
                    }
                    MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant_main(new byte[]{-86, 85, 4, 36, 0, 20, 59});
                    showLoading(getResources().getString(R.string.present_cup_hint));
                }
            }
        }
    }

    private void checkOnePay() {
        int i = (this.mTvPay1.getVisibility() == 8 ? 0 : 1) + 0 + (this.mTvPay2.getVisibility() == 8 ? 0 : 1) + (this.mTvPay3.getVisibility() == 8 ? 0 : 1) + (this.mTvPay4.getVisibility() == 8 ? 0 : 1) + (this.mTvPay5.getVisibility() == 8 ? 0 : 1) + (this.mTvPay6.getVisibility() == 8 ? 0 : 1) + (this.mTvPay7.getVisibility() == 8 ? 0 : 1) + (this.mTvPay8.getVisibility() == 8 ? 0 : 1) + (this.mTvPay9.getVisibility() == 8 ? 0 : 1) + (this.tv_pay_saobei_zhengsao_13.getVisibility() == 8 ? 0 : 1) + (this.tv_pay_t50.getVisibility() == 8 ? 0 : 1) + (this.tv_pay_agt.getVisibility() == 8 ? 0 : 1) + (this.payNorthStar.getVisibility() == 8 ? 0 : 1) + (this.tv_pay_tp.getVisibility() == 8 ? 0 : 1) + (this.tv_pay_bao.getVisibility() == 8 ? 0 : 1) + (this.tv_pay_saobei_fansao_13.getVisibility() == 8 ? 0 : 1) + (this.payNewCape.getVisibility() != 8 ? 1 : 0);
        this.onePay = i;
        if (i != 1) {
            return;
        }
        if (this.mTvPay1.getVisibility() == 0) {
            onClick(this.mTvPay1);
            return;
        }
        if (this.mTvPay2.getVisibility() == 0) {
            onClick(this.mTvPay2);
            return;
        }
        if (this.mTvPay3.getVisibility() == 0) {
            onClick(this.mTvPay3);
            return;
        }
        if (this.mTvPay5.getVisibility() == 0) {
            onClick(this.mTvPay5);
            return;
        }
        if (this.mTvPay6.getVisibility() == 0) {
            onClick(this.mTvPay6);
            return;
        }
        if (this.mTvPay7.getVisibility() == 0) {
            onClick(this.mTvPay7);
            return;
        }
        if (this.mTvPay8.getVisibility() == 0) {
            onClick(this.mTvPay8);
            return;
        }
        if (this.mTvPay9.getVisibility() == 0) {
            onClick(this.mTvPay9);
            return;
        }
        if (this.tv_pay_saobei_zhengsao_13.getVisibility() == 0) {
            onClick(this.tv_pay_saobei_zhengsao_13);
            return;
        }
        if (this.payNewCape.getVisibility() == 0) {
            onClick(this.payNewCape);
            return;
        }
        if (this.tv_pay_agt.getVisibility() == 0) {
            onClick(this.tv_pay_agt);
            return;
        }
        if (this.payNorthStar.getVisibility() == 0) {
            onClick(this.payNorthStar);
        } else if (this.tv_pay_tp.getVisibility() == 0) {
            onClick(this.tv_pay_tp);
        } else if (this.tv_pay_bao.getVisibility() == 0) {
            onClick(this.tv_pay_bao);
        }
    }

    private void getPayNewCape() {
        Constants.buy_type = 2;
        Intent intent = new Intent("com.coffeji.plug.STATE_ACTION");
        intent.setPackage("com.example.plugin");
        intent.putExtra("money", Constants.nowProduct_Detail.realPrice());
        getActivity().sendBroadcast(intent);
        this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.CardPay));
        this.mLayoutPayother.setVisibility(0);
        this.mIvQrcode.setVisibility(8);
        this.mTvErrormsg.setVisibility(8);
        this.mLayoutChange.setVisibility(8);
    }

    private void init() {
        Constants.NowStep = 3;
        this.step3_tv_zhifu.setText("");
        this.step3_cl.setVisibility(0);
        this.step3_tv_zhifu.setText("");
    }

    private void initResource5() {
        this.mCountdowntime.setTextColor(getResources().getColor(R.color.gray_test));
        this.step3_cl_main.setBackgroundColor(getResources().getColor(R.color.white));
        this.mTvProductname.setTextColor(getResources().getColor(R.color.black));
        this.mTvPice.setTextColor(getResources().getColor(R.color.black));
        this.step3_tv_zhifu.setTextColor(getResources().getColor(R.color.black));
        this.step3_view.setBackgroundColor(getResources().getColor(R.color.black));
        this.step3_tv_zhifufangshi.setTextColor(getResources().getColor(R.color.black));
        this.step3_tv_price.setTextColor(getResources().getColor(R.color.black));
    }

    private void initResource6() {
        this.tv_pay4_mian.setImageDrawable(getResources().getDrawable(R.mipmap.payment_mian));
    }

    public static Step3Fragment newInstance() {
        return new Step3Fragment();
    }

    private void requsterPay(int i) {
        if (i == 11) {
            Constants.buy_type = 11;
            this.mLayoutPayother.setVisibility(8);
            this.mIvQrcode.setVisibility(0);
            this.mTvErrormsg.setVisibility(0);
            this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
            ((Step3Presenter) this.mPresenter).putOrder(11);
            return;
        }
        if (i == 14) {
            Constants.buy_type = i;
            this.mLayoutPayother.setVisibility(8);
            this.mIvQrcode.setVisibility(0);
            this.mTvErrormsg.setVisibility(0);
            this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
            ((Step3Presenter) this.mPresenter).t50PayGetWay();
            return;
        }
        Constants.buy_type = i;
        this.mLayoutPayother.setVisibility(8);
        this.mIvQrcode.setVisibility(0);
        this.mTvErrormsg.setVisibility(0);
        this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
        ((Step3Presenter) this.mPresenter).putOrder(i);
    }

    private void requsterPay1() {
        Constants.buy_type = 1;
        this.mLayoutPayother.setVisibility(8);
        this.mIvQrcode.setVisibility(0);
        this.mTvErrormsg.setVisibility(0);
        this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
        ((Step3Presenter) this.mPresenter).putOrder(1);
    }

    private void requsterPay14() {
        Constants.buy_type = 14;
        this.mLayoutPayother.setVisibility(8);
        this.mIvQrcode.setVisibility(0);
        this.mTvErrormsg.setVisibility(0);
        this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
        ((Step3Presenter) this.mPresenter).putOrder(14);
    }

    private void requsterPay2() {
        Constants.buy_type = 2;
        ((Step3Presenter) this.mPresenter).cardPay();
        this.mLayoutPayother.setVisibility(0);
        this.mIvQrcode.setVisibility(8);
        this.mTvErrormsg.setVisibility(8);
        this.mLayoutChange.setVisibility(8);
        this.mPayway.setText(getString(R.string.cardpay));
    }

    private void requsterPay3() {
        setMoneyText(new MoneyReceiveMessage(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d));
        Constants.buy_type = 3;
        ((Step3Presenter) this.mPresenter).crshPay();
        this.mLayoutPayother.setVisibility(0);
        this.mIvQrcode.setVisibility(8);
        this.mTvErrormsg.setVisibility(8);
        this.mLayoutChange.setVisibility(0);
        this.mPayway.setText(getString(R.string.crishpay));
    }

    private void requsterPay4() {
        Constants.buy_type = 4;
        ((Step3Presenter) this.mPresenter).putOrder(4);
        this.mLayoutPayother.setVisibility(8);
        this.mIvQrcode.setVisibility(8);
        this.mTvErrormsg.setVisibility(8);
    }

    private void requsterPay5() {
        Constants.buy_type = 5;
        this.mLayoutPayother.setVisibility(8);
        this.mIvQrcode.setVisibility(0);
        this.mTvErrormsg.setVisibility(0);
        this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
        ((Step3Presenter) this.mPresenter).putOrder(5);
    }

    private void requsterPay6() {
        Constants.buy_type = 6;
        this.mLayoutPayother.setVisibility(8);
        this.mIvQrcode.setVisibility(0);
        this.mTvErrormsg.setVisibility(0);
        this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
        ((Step3Presenter) this.mPresenter).putOrder(6);
    }

    private void requsterPay7() {
        Constants.buy_type = 7;
        this.mLayoutPayother.setVisibility(8);
        this.mIvQrcode.setVisibility(0);
        this.mTvErrormsg.setVisibility(0);
        this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
        ((Step3Presenter) this.mPresenter).putOrder(7);
    }

    private void requsterPay8() {
        Constants.buy_type = 8;
        this.mLayoutPayother.setVisibility(8);
        this.mIvQrcode.setVisibility(0);
        this.mTvErrormsg.setVisibility(0);
        this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
        ((Step3Presenter) this.mPresenter).putOrder(8);
    }

    private void requsterPay9() {
        Constants.buy_type = 9;
        this.mLayoutPayother.setVisibility(8);
        this.mIvQrcode.setVisibility(0);
        this.mTvErrormsg.setVisibility(0);
        this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
        ((Step3Presenter) this.mPresenter).putOrder(9);
    }

    private void requsterPayMdb() {
        setMoneyText(new MoneyReceiveMessage(0.0d));
        Constants.buy_type = 2;
        this.handler = new Handler(Looper.getMainLooper());
        Constants.swipeNum = 0;
        this.handler.post(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment.5
            @Override // java.lang.Runnable
            public void run() {
                Step3Fragment.access$108(Step3Fragment.this);
                if (Constants.swipeNum != 1) {
                    MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(TransmissionUtils.Query);
                    Step3Fragment.this.handler.postDelayed(this, 900L);
                } else {
                    Step3Fragment.this.setMoneyText(new MoneyReceiveMessage(Constants.nowProduct_Detail.realPrice()));
                    Constants.swipeNum = 0;
                    EventBus.getDefault().post(new ErrorCode_Paymenting(true, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS.name()));
                }
            }
        });
        this.mLayoutPayother.setVisibility(0);
        this.mIvQrcode.setVisibility(8);
        this.mTvErrormsg.setVisibility(8);
        this.mLayoutChange.setVisibility(8);
        this.mPayway.setText(getString(R.string.cardpay));
    }

    private void requsterPayMdbCoin() {
        MdbSendMethod.instance().openPay();
        setMoneyText(new MoneyReceiveMessage(0.0d));
        Constants.buy_type = 3;
        this.handler = new Handler(Looper.getMainLooper());
        this.handler.post(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment.7
            @Override // java.lang.Runnable
            public void run() {
                Step3Fragment.access$108(Step3Fragment.this);
                if (Constants.countNum >= Constants.nowProduct_Detail.realPrice()) {
                    MdbSendMethod.instance().closePay();
                    EventBus.getDefault().post(new ErrorCode_Paymenting(true, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS, ErrorCode_Paymenting.ErrorCode.PAYSUCCESS.name()));
                } else {
                    MdbSendMethod.instance().checkMoney();
                    Step3Fragment.this.handler.postDelayed(this, 500L);
                }
            }
        });
        this.mLayoutPayother.setVisibility(0);
        this.mIvQrcode.setVisibility(8);
        this.mTvErrormsg.setVisibility(8);
        this.mLayoutChange.setVisibility(0);
        this.mPayway.setText(getString(R.string.crishpay));
    }

    private void requsterPay_v1() {
        Constants.buy_type = 16;
        Constants.payType = 2;
        double realPrice = Constants.nowProduct_Detail.realPrice();
        double pos = Constants.opsSeting().getPos();
        int i = (int) (realPrice / pos);
        byte[] intToBytes2_HL = ByteUtils.intToBytes2_HL(i, 2);
        byte[] paymentWay_payment = Constants.setPaymentWay_payment((byte) 2, intToBytes2_HL);
        Tools.upLocalLog("通用支付:v；" + realPrice + "；rate；" + pos + "；v1；" + i + "；data；" + Tools.byteToStr(intToBytes2_HL) + "；bytes；" + Tools.byteToStr(paymentWay_payment) + "；Cardpay:" + Constants.opsSeting().isCardpay() + "；CardpayNew:" + Constants.opsSeting().isCardpaynew());
        if (i <= 0) {
            Tools.upLocalLog("记录错误：商品价格为" + i + ",刷卡倍率设置错误。");
        }
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(paymentWay_payment);
        final byte[] bArr = {-86, 85, 2, 8};
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(ByteUtils.byteMerger(bArr, new byte[]{(byte) (CRC8Util.byteCheckSum(bArr) & 255)}));
        this.countDownTimer = new CountDownTimer(180000L, 800L) { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment.6
            @Override // android.os.CountDownTimer
            public void onFinish() {
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(bArr);
            }
        };
        this.countDownTimer.start();
        this.mLayoutPayother.setVisibility(0);
        this.mIvQrcode.setVisibility(8);
        this.mTvErrormsg.setVisibility(8);
        this.mLayoutChange.setVisibility(8);
        this.mPayway.setText(getString(R.string.UniversalPayment));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMoneyText(MoneyReceiveMessage moneyReceiveMessage) {
        if (Constants.NowStep != 3) {
            return;
        }
        double money_sum = moneyReceiveMessage.getMoney_sum();
        int keepDecimals = Constants.nowProduct_Detail.getKeepDecimals();
        String format = NumberUtils.format(moneyReceiveMessage.getMoney_sum(), keepDecimals);
        String string = getString(R.string.recevicemoney);
        if (money_sum > 0.0d) {
            string = string.replace("0.00", format);
        }
        this.mTvRecivernumber.setText(string + this.moneycode);
        this.mTvRecivernumberEn.setText("Paid:" + format + this.moneycode);
        String format2 = NumberUtils.format(Math.max(0.0d, money_sum - Constants.nowProduct_Detail.realPrice()), keepDecimals);
        this.mTvChangenumber.setText(getString(R.string.change) + format2 + this.moneycode);
        this.mTvChangenumberEn.setText("Change:" + format2 + this.moneycode);
        Tools.upLocalLog("sdata；" + string + ":moneySum" + money_sum + "change：" + format2);
    }

    private void setOpsSetting() {
        DevConfig devConfig = Constants.devConfig();
        OpsSeting opsSeting = Constants.opsSeting();
        this.mTvPay2.setVisibility(opsSeting.isCardpay() ? 0 : 8);
        this.mTvPay3.setVisibility(opsSeting.isCash() ? 0 : 8);
        this.tv_pay_bao.setVisibility(opsSeting.baoPay ? 0 : 8);
        this.mTvPay4.setVisibility(devConfig.isRight8() || opsSeting.isNopay() ? 0 : 8);
        this.payNewCape.setVisibility(opsSeting.newcardpay ? 0 : 8);
    }

    private void showRefundLoading() {
        this.loadingDialog = new LoadAtDialog(requireContext());
        this.loadingDialog.show();
    }

    private void stopGetPayState() {
        this.putOrderSuccess = false;
    }

    public String convertToTwoDecimalFloat(double d) {
        return String.format("%.2f", Double.valueOf(d));
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    @Nullable
    public /* bridge */ /* synthetic */ Context getActivity() {
        return super.getActivity();
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
        if (this.mSportDialog.isShowing()) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment.4
                @Override // java.lang.Runnable
                public void run() {
                    Step3Fragment.this.mSportDialog.dismiss();
                }
            });
        }
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        this.moneycode = Constants.opsSeting().getCashcode();
        this.constraintLayout3.setVisibility(0);
        this.constraintLayout2.setVisibility(8);
        init();
        initResource5();
        this.cyclicQueryTimes = 30;
        this.checkState = true;
        setOpsSetting();
        Constants.pay_num = 0.0d;
        Constants.pay_no = "";
        Constants.buy_type = -1;
        ((Step3Presenter) this.mPresenter).getPayWaySetting();
        showDetailMessage(Constants.nowProduct_Detail);
        if (Constants.ISOPEN_PAYMENT) {
            OpsSeting opsSeting = Constants.opsSeting();
            if (Constants.HomePay() != 2) {
                if (opsSeting.isCash() || opsSeting.isCardpay()) {
                    MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(Constants.readPayDeviceStatus_payment());
                } else if (opsSeting.baoPay) {
                    MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(Constants.readPayDeviceStatus_payment());
                }
            }
        }
        this.serialPortManager = MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Instant();
        checkAbnormal();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_step3, viewGroup, false);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public boolean isSaobeiScan() {
        return Constants.opsSeting().isSaobeiPay();
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void killMyself() {
        IView.CC.$default$killMyself(this);
    }

    public /* synthetic */ void lambda$onClick$0$Step3Fragment() {
        LoadAtDialog loadAtDialog = this.loadingDialog;
        if (loadAtDialog == null || !loadAtDialog.isShowing()) {
            return;
        }
        this.loadingDialog.dismiss();
        Constants.pay_num = 0.0d;
    }

    public /* synthetic */ void lambda$onClick$1$Step3Fragment() {
        LoadAtDialog loadAtDialog = this.loadingDialog;
        if (loadAtDialog == null || !loadAtDialog.isShowing()) {
            return;
        }
        this.loadingDialog.dismiss();
        Constants.pay_num = 0.0d;
    }

    public /* synthetic */ void lambda$showOrderFail$2$Step3Fragment(DialogInterface dialogInterface) {
        onClick(this.mBtnBack);
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @OnClick({R.id.tv_pay1, R.id.tv_pay2, R.id.tv_pay3, R.id.tv_pay4, R.id.tv_pay5, R.id.tv_pay6, R.id.tv_pay_bao, R.id.tv_pay7, R.id.btn_back, R.id.tv_pay8, R.id.tv_pay9, R.id.tv_pay_saobei_zhengsao_13, R.id.tv_pay_saobei_fansao_13, R.id.tv_pay_t50, R.id.tv_pay_agt, R.id.tv_pay_north_star, R.id.tv_pay_newcape, R.id.tv_pay_tp})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_back) {
            this.nowtime = 180;
            this.mTvErrormsg.setText("");
            backHandler();
            if (Constants.buy_type == 16) {
                ToastUtils.showLong(getString(R.string.pleasewait));
                return;
            }
            if (Constants.opsSeting().newcardpay) {
                Intent intent = new Intent("com.coffeji.plug.BACK_ACTION");
                intent.setPackage("com.example.plugin");
                intent.putExtra("back", "取消支付");
                getActivity().sendBroadcast(intent);
            }
            this.mLayoutPayCode.setVisibility(8);
            this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.select_pay_way));
            AlertDialog alertDialog = this.mSportDialog;
            if (alertDialog == null || !alertDialog.isShowing()) {
                if (Constants.buy_type == 2) {
                    if (this.isCard) {
                        MdbSendMethod.instance().cancel();
                    }
                    this.isCard = true;
                    showRefundLoading();
                    new Handler().postDelayed(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Step3Fragment$n_GaudMF6FD_lhpobvghpvIa3bM
                        @Override // java.lang.Runnable
                        public final void run() {
                            Step3Fragment.this.lambda$onClick$0$Step3Fragment();
                        }
                    }, 4000L);
                    if (Constants.pay_num == 0.0d) {
                        ((Step3Presenter) this.mPresenter).backMoney();
                    }
                    Constants.PAYSTATE = 2;
                    changeCardReadState(1);
                }
                if (Constants.buy_type == 3) {
                    showRefundLoading();
                    new Handler().postDelayed(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Step3Fragment$e1AD8QU2-zDlFCU-QyonPK_Xu7Q
                        @Override // java.lang.Runnable
                        public final void run() {
                            Step3Fragment.this.lambda$onClick$1$Step3Fragment();
                        }
                    }, 4000L);
                    MdbSendMethod.instance().backMoney(Constants.buy_type);
                    ((Step3Presenter) this.mPresenter).backMoney();
                    Constants.PAYSTATE = 2;
                }
                if (this.onePay != 1) {
                    if (this.constraintLayout3.getVisibility() == 8) {
                        this.step3_cl.setVisibility(0);
                        this.step3_tv_zhifu.setText("");
                        this.constraintLayout3.setVisibility(0);
                        this.constraintLayout2.setVisibility(8);
                        btnBackPayment(Constants.buy_type);
                        return;
                    }
                } else if (this.constraintLayout3.getVisibility() == 8) {
                    btnBackPayment(Constants.buy_type);
                }
                this.putOrderSuccess = false;
                Constants.buy_type = -1;
                btnBack("点击返回");
                return;
            }
            return;
        }
        switch (id) {
            case R.id.tv_pay1 /* 2131297041 */:
                this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.selectpayment));
                this.step3_tv_zhifu.setText(getResources().getString(R.string.Aggregatepayment));
                this.constraintLayout3.setVisibility(8);
                this.constraintLayout2.setVisibility(0);
                stopGetPayState();
                this.nowtime = 180;
                this.putOrderSuccess = false;
                requsterPay1();
                return;
            case R.id.tv_pay2 /* 2131297042 */:
                this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.CardPay));
                this.step3_cl.setVisibility(8);
                this.constraintLayout3.setVisibility(8);
                this.constraintLayout2.setVisibility(0);
                stopGetPayState();
                this.nowtime = 180;
                if (Constants.HomePay() != 2) {
                    requsterPay2();
                    return;
                } else {
                    MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment_main(MdbUtils.PayMoneyNew(Double.valueOf(Constants.nowProduct_Detail.realPrice())));
                    requsterPayMdb();
                    return;
                }
            case R.id.tv_pay3 /* 2131297043 */:
                this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.CashPay));
                this.step3_cl.setVisibility(8);
                this.constraintLayout3.setVisibility(8);
                this.constraintLayout2.setVisibility(0);
                stopGetPayState();
                this.nowtime = 180;
                if (Constants.HomePay() != 2) {
                    requsterPay3();
                    return;
                }
                Constants.countNum = 0.0d;
                Constants.coin_Num = 0.0d;
                Constants.note_Num = 0.0d;
                requsterPayMdbCoin();
                return;
            case R.id.tv_pay4 /* 2131297044 */:
                if (Constants.buy_type == 4) {
                    return;
                }
                if (Constants.buy_type == 2) {
                    ((Step3Presenter) this.mPresenter).backMoney();
                    changeCardReadState(1);
                }
                stopGetPayState();
                this.nowtime = 180;
                requsterPay4();
                return;
            default:
                switch (id) {
                    case R.id.tv_pay5 /* 2131297046 */:
                        this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.selectpayment));
                        this.step3_tv_zhifu.setText(getResources().getString(R.string.WeChatPay));
                        this.constraintLayout3.setVisibility(8);
                        this.constraintLayout2.setVisibility(0);
                        this.putOrderSuccess = false;
                        this.nowtime = 180;
                        requsterPay5();
                        return;
                    case R.id.tv_pay6 /* 2131297047 */:
                        this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.selectpayment));
                        this.step3_tv_zhifu.setText(getResources().getString(R.string.AlipayPay));
                        this.constraintLayout3.setVisibility(8);
                        this.constraintLayout2.setVisibility(0);
                        this.putOrderSuccess = false;
                        this.nowtime = 180;
                        requsterPay6();
                        return;
                    case R.id.tv_pay7 /* 2131297048 */:
                        this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.selectpayment));
                        this.step3_tv_zhifu.setText(getResources().getString(R.string.NumberPay));
                        this.constraintLayout3.setVisibility(8);
                        this.constraintLayout2.setVisibility(0);
                        this.putOrderSuccess = false;
                        this.nowtime = 180;
                        requsterPay7();
                        return;
                    case R.id.tv_pay8 /* 2131297049 */:
                        this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.selectpayment));
                        this.step3_tv_zhifu.setText(getResources().getString(R.string.HuiPay));
                        this.constraintLayout3.setVisibility(8);
                        this.constraintLayout2.setVisibility(0);
                        this.putOrderSuccess = false;
                        this.nowtime = 180;
                        requsterPay8();
                        return;
                    case R.id.tv_pay9 /* 2131297050 */:
                        this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.selectpayment));
                        this.step3_tv_zhifu.setText(getResources().getString(R.string.GongHangPay));
                        this.constraintLayout3.setVisibility(8);
                        this.constraintLayout2.setVisibility(0);
                        this.putOrderSuccess = false;
                        this.nowtime = 180;
                        requsterPay9();
                        return;
                    case R.id.tv_pay_agt /* 2131297051 */:
                        this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.selectpayment));
                        this.step3_tv_zhifu.setText(getResources().getString(R.string.pay_agt));
                        this.constraintLayout3.setVisibility(8);
                        this.constraintLayout2.setVisibility(0);
                        this.putOrderSuccess = false;
                        this.nowtime = 180;
                        requsterPay(15);
                        return;
                    case R.id.tv_pay_bao /* 2131297052 */:
                        this.step3_cl.setVisibility(8);
                        this.constraintLayout3.setVisibility(8);
                        this.constraintLayout2.setVisibility(0);
                        stopGetPayState();
                        this.nowtime = 180;
                        requsterPay_v1();
                        return;
                    default:
                        switch (id) {
                            case R.id.tv_pay_newcape /* 2131297055 */:
                                this.step3_cl.setVisibility(8);
                                this.constraintLayout3.setVisibility(8);
                                this.constraintLayout2.setVisibility(0);
                                stopGetPayState();
                                this.nowtime = 180;
                                getPayNewCape();
                                return;
                            case R.id.tv_pay_north_star /* 2131297056 */:
                                this.step3_tv_zhifu.setText(getResources().getString(R.string.north_star));
                                this.constraintLayout3.setVisibility(8);
                                this.constraintLayout2.setVisibility(0);
                                this.putOrderSuccess = false;
                                this.nowtime = 180;
                                requsterPay(PayType.NORTH_PAY);
                                return;
                            case R.id.tv_pay_saobei_fansao_13 /* 2131297057 */:
                                Tools.upLocalLogTest("检测到扫呗反扫;Constants.pay_num=" + Constants.pay_num);
                                this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.showPayCode));
                                this.tvPayCodeTips.setText(getResources().getString(R.string.showPayCode));
                                this.constraintLayout3.setVisibility(8);
                                this.constraintLayout2.setVisibility(0);
                                this.mLayoutPayCode.setVisibility(0);
                                this.mLayoutPayother.setVisibility(8);
                                this.step3_cl.setVisibility(8);
                                this.putOrderSuccess = false;
                                this.nowtime = 180;
                                return;
                            case R.id.tv_pay_saobei_zhengsao_13 /* 2131297058 */:
                                Tools.upLocalLogTest("检测到扫呗正扫;Constants.pay_num=" + Constants.pay_num);
                                this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.selectpayment));
                                this.step3_tv_zhifu.setText(getResources().getString(R.string.SaobeiPay));
                                this.constraintLayout3.setVisibility(8);
                                this.constraintLayout2.setVisibility(0);
                                this.putOrderSuccess = false;
                                this.nowtime = 180;
                                requsterPay(13);
                                return;
                            case R.id.tv_pay_t50 /* 2131297059 */:
                                this.tv_pay_t50.setEnabled(false);
                                Tools.upLocalLogTest("检测到T50支付;Constants.pay_num=" + Constants.pay_num);
                                this.step3_tv_zhifu.setText(getResources().getString(R.string.t50pay));
                                ((Step3Presenter) this.mPresenter).t50PayGetWay();
                                return;
                            case R.id.tv_pay_tp /* 2131297060 */:
                                this.step3_tv_zhifufangshi.setText(getResources().getString(R.string.selectpayment));
                                this.step3_tv_zhifu.setText(getResources().getString(R.string.tp_qr_pay));
                                this.constraintLayout3.setVisibility(8);
                                this.constraintLayout2.setVisibility(0);
                                this.putOrderSuccess = false;
                                this.nowtime = 180;
                                requsterPay(PayType.TP_PAY);
                                return;
                            default:
                                return;
                        }
                }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        backHandler();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ErrorCode_Instant errorCode_Instant) {
        if (errorCode_Instant.isOK || Constants.NowStep != 3) {
            return;
        }
        hideLoading();
        Tools.upLocalLog("设备状态错误，返回主页");
        EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ErrorCode_Payment errorCode_Payment) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ErrorCode_Paymenting errorCode_Paymenting) {
        if (Constants.NowStep == 3 && errorCode_Paymenting.message.equals(ErrorCode_Paymenting.ErrorCode.PAYSUCCESS.name())) {
            Tools.upLocalLog("支付完成:" + Constants.buy_type);
            ((Step3Presenter) this.mPresenter).putOrder(Constants.buy_type);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MoneyReceiveMessage moneyReceiveMessage) {
        Tools.upLocalLog("EventBus-MoneyReceiveMessage；" + moneyReceiveMessage.toString());
        setMoneyText(moneyReceiveMessage);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(QRCodeMessage qRCodeMessage) {
        Tools.upLocalLog("反扫：" + qRCodeMessage.toString());
        if (Constants.buy_type == 7) {
            this.mLayoutPayother.setVisibility(8);
            this.mIvQrcode.setVisibility(0);
            this.mTvErrormsg.setVisibility(0);
            this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
            ((Step3Presenter) this.mPresenter).putOrder_eRmbScan(7, qRCodeMessage.getQrcode());
            return;
        }
        if (isSaobeiScan() && this.mLayoutPayCode.getVisibility() == 0) {
            this.mLayoutPayother.setVisibility(8);
            this.mIvQrcode.setVisibility(0);
            this.mTvErrormsg.setVisibility(0);
            this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
            ((Step3Presenter) this.mPresenter).barcodepay(qRCodeMessage.getQrcode());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TimeMessage timeMessage) {
        if (Constants.NowStep != 3) {
            return;
        }
        checkDeviceState();
        this.cyclicQueryTimes--;
        if (this.cyclicQueryTimes > 0 && this.checkState) {
            SerialPortManager serialPortManager = this.serialPortManager;
            if (serialPortManager != null) {
                serialPortManager.addSendList(SerialOrderBytes.CheckMacStateNew);
                if (Constants.deviceTypeDetail.getName().contains("JK88") || Constants.deviceTypeDetail.getName().contains("801")) {
                    this.serialPortManager.addSendList(SerialOrderBytes.CheckMacStateNew);
                } else {
                    this.serialPortManager.addSendList(SerialOrderBytes.CheckMacStateOld);
                }
            }
            if (ErrorCodeNew_Msg.MacState == ErrorCodeNew.Busy || ErrorCodeNew_Msg.ErrorNew.contains(ErrorCodeNew.A09.name())) {
                return;
            } else {
                this.checkState = false;
            }
        }
        if (this.cyclicQueryTimes < 0 && this.checkState) {
            hideLoading();
            btnBack("超时");
            return;
        }
        if (!this.checkState) {
            hideLoading();
        }
        if (this.nowtime % 10 == 0) {
            Tools.upLocalLog(this.nowtime + "秒后返回。");
        }
        this.nowtime--;
        if (this.mCountdowntime != null) {
            this.mCountdowntime.setText(String.format(getString(R.string.pay_countdowntime), this.nowtime + ""));
        }
        if (this.nowtime == 6 && Constants.buy_type == 3) {
            MdbSendMethod.instance().closePay();
        }
        if (this.nowtime == 90 && Constants.buy_type == 2) {
            this.isCard = false;
            MdbSendMethod.instance().cancel();
        }
        if (this.nowtime == 0) {
            ((Step3Presenter) this.mPresenter).orderCancel(Constants.pay_no, Constants.buy_type);
            T50PayDialog t50PayDialog = this.payDialog;
            if (t50PayDialog != null && t50PayDialog.isShowing()) {
                this.payDialog.dismiss();
            }
            stopGetPayState();
            int change = (int) (Constants.pay_num / Constants.opsSeting().getChange());
            Tools.upLocalLog("开始退款：Constants.buy_type：" + Constants.buy_type + ";v;" + change);
            MdbSendMethod.instance().backMoney(Constants.buy_type);
            if (Constants.buy_type == 3) {
                if (change < 0) {
                    return;
                }
                Tools.upLocalLog("现金退款：" + change);
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(Constants.smallChange_payment((byte) change));
            }
            if (Constants.buy_type == 2) {
                Tools.upLocalLog("刷卡退款：" + change);
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment_main(Constants.drinkStatus_payment((byte) 1));
            }
            Constants.pay_num = 0.0d;
            ArmsUtils.snackbarText(getString(R.string.timeouted));
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 3));
        }
        if (this.putOrderSuccess) {
            ((Step3Presenter) this.mPresenter).checkPayState();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mBanner.setPause();
        hideLoading();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initResource5();
        initResource6();
        this.mBanner.setResume();
        this.nowtime = 180;
        this.isPaySuccess = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(this.broadcastReceiver, this.filter);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(this.broadcastReceiver);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void payError() {
        stopGetPayState();
        DiaLogUtils.showAlert(getActivity(), getString(R.string.warmhint), getString(R.string.payfail), getString(R.string.confirm), new DialogInterface.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Constants.buy_type == 7) {
                    ((Step3Presenter) Step3Fragment.this.mPresenter).putOrder(7);
                } else {
                    EventBus.getDefault().post(new MyEventBusMessage(Step3Fragment.tag, MyEventBusMessage.TYPE.CLICK, "", 3));
                }
            }
        });
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void payError(int i) {
        stopGetPayState();
        DiaLogUtils.showAlert(getActivity(), getString(R.string.payfail), i == 3 ? getString(R.string.payfail) : i == 4 ? getString(R.string.backmoney) : i == 5 ? getString(R.string.backmoneyed) : i == 6 ? getString(R.string.timeouted) : "", getString(R.string.confirm), new DialogInterface.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (Constants.buy_type == 7) {
                    ((Step3Presenter) Step3Fragment.this.mPresenter).putOrder(7);
                } else {
                    EventBus.getDefault().post(new MyEventBusMessage(Step3Fragment.tag, MyEventBusMessage.TYPE.CLICK, "", 3));
                }
            }
        });
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void paySuccess() {
        if (this.isPaySuccess) {
            return;
        }
        EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 2));
        stopGetPayState();
        this.isPaySuccess = true;
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void payWaySettingFail() {
        this.mTvPay5.setVisibility(8);
        this.mTvPay6.setVisibility(8);
        this.mTvPay7.setVisibility(8);
        this.mTvPay1.setVisibility(8);
        this.tv_pay_saobei_zhengsao_13.setVisibility(8);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void payWaySettingSuccess(PayWaySettingBack payWaySettingBack) {
        this.constraintLayout3.setVisibility(0);
        this.constraintLayout2.setVisibility(8);
        List<Integer> data = payWaySettingBack.getData();
        this.mTvPay8.setVisibility(8);
        this.mTvPay9.setVisibility(8);
        this.mTvPay5.setVisibility(8);
        this.mTvPay6.setVisibility(8);
        this.mTvPay1.setVisibility(8);
        this.mTvPay7.setVisibility(8);
        this.tv_pay_saobei_zhengsao_13.setVisibility(8);
        int i = 0;
        for (int i2 = 0; i2 < data.size(); i2++) {
            Integer num = data.get(i2);
            if (num.intValue() == 1) {
                if (i == 0) {
                    i = 1;
                }
                this.mTvPay5.setVisibility(0);
            } else if (num.intValue() == 2) {
                if (i == 0) {
                    i = 2;
                }
                this.mTvPay6.setVisibility(0);
            } else if (num.intValue() == 3) {
                if (i == 0) {
                    i = 3;
                }
                this.mTvPay1.setVisibility(0);
            } else if (num.intValue() == 4) {
                if (i == 0) {
                    i = 4;
                }
            } else if (num.intValue() == 5) {
                if (i == 0) {
                    i = 5;
                }
                this.mTvPay8.setVisibility(0);
            } else if (num.intValue() == 6) {
                if (i == 0) {
                    i = 6;
                }
                this.mTvPay9.setVisibility(0);
            } else if (num.intValue() == 13) {
                if (i == 0) {
                    i = 13;
                }
                this.tv_pay_saobei_zhengsao_13.setVisibility(0);
                this.tv_pay_saobei_fansao_13.setVisibility(isSaobeiScan() ? 0 : 8);
            } else if (num.intValue() == 14) {
                if (i == 0) {
                    i = 14;
                }
                this.tv_pay_t50.setVisibility(0);
            } else if (num.intValue() == 15) {
                if (i == 0) {
                    i = 15;
                }
                this.tv_pay_agt.setVisibility(0);
            } else if (num.intValue() == PayType.NORTH_PAY) {
                if (i == 0) {
                    i = PayType.NORTH_PAY;
                }
                this.payNorthStar.setVisibility(0);
            } else if (num.intValue() == PayType.TP_PAY) {
                if (i == 0) {
                    i = PayType.TP_PAY;
                }
                this.tv_pay_tp.setVisibility(0);
            } else if (num.intValue() == PayType.CAPE_PAY) {
                if (i == 0) {
                    i = PayType.CAPE_PAY;
                }
                this.payNewCape.setVisibility(Constants.opsSeting().newcardpay ? 8 : 0);
            }
        }
        checkOnePay();
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void putOrderFailQrcode(String str) {
        this.putOrderSuccess = false;
        TextView textView = this.mTvErrormsg;
        if (textView != null) {
            textView.setVisibility(0);
            this.mTvErrormsg.setText(str);
        }
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void putOrderOtherSuccess(GenOrderBack genOrderBack, int i) {
        Constants.trade_no = genOrderBack.getData().toString();
        EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 2));
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void putOrderSuccessALiPay(ALiPayGenOrderBack.DataBean dataBean) {
        this.putOrderSuccess = true;
        this.mTvErrormsg.setVisibility(8);
        Constants.pay_no = dataBean.getOut_trade_no();
        this.mIvQrcode.setImageBitmap(QRCodeUtils.generateBitmap(dataBean.getQr_code(), 400, 400));
        this.mIvQrcode.setVisibility(0);
        this.mLayoutPayother.setVisibility(8);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void putOrderSuccessAggregationCodePayment(aggregationCodePayment.DataBean dataBean) {
        this.putOrderSuccess = true;
        this.mTvErrormsg.setVisibility(8);
        Constants.pay_no = dataBean.getTerminal_trace();
        this.mIvQrcode.setImageBitmap(QRCodeUtils.generateBitmap(dataBean.getQr_url(), 400, 400));
        this.mIvQrcode.setVisibility(0);
        this.mLayoutPayother.setVisibility(8);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void putOrderSuccessERMB(DigitalPayGenOrderBack.Databean1 databean1) {
        this.putOrderSuccess = true;
        this.mTvErrormsg.setVisibility(8);
        Constants.pay_no = databean1.getData().getPayJson().getThirdOrderNo();
        this.mIvQrcode.setImageBitmap(QRCodeUtils.generateBitmap(databean1.getData().getPayJson().getQCodeUrl(), 400, 400));
        this.mIvQrcode.setVisibility(0);
        this.mLayoutPayother.setVisibility(8);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void putOrderSuccessERMBCode(WeChartPayGenOrderBack.DataBean dataBean) {
        this.putOrderSuccess = true;
        this.mTvErrormsg.setVisibility(8);
        Constants.pay_no = dataBean.getOut_trade_no();
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void putOrderSuccessHui(DigitalPayGenOrderBack.Databean1 databean1) {
        this.putOrderSuccess = true;
        this.mTvErrormsg.setVisibility(8);
        Tools.upLocalLogTest("慧支付：" + databean1.toString());
        Constants.pay_no = databean1.getData().getPayJson().getThirdOrderNo();
        this.mIvQrcode.setImageBitmap(QRCodeUtils.generateBitmap(databean1.getData().getPayJson().getQCodeUrl(), 400, 400));
        this.mIvQrcode.setVisibility(0);
        this.mLayoutPayother.setVisibility(8);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void putOrderSuccessIcbc(icbcPayGenOrderBack.DataBean dataBean) {
        this.putOrderSuccess = true;
        this.mTvErrormsg.setVisibility(8);
        Constants.pay_no = dataBean.getOutTradeNo();
        if (dataBean == null || dataBean.getQrcode() == null || dataBean.getQrcode().equals("")) {
            Tools.sendArMsg("获取数据为错误：" + dataBean.toString());
            return;
        }
        this.mIvQrcode.setImageBitmap(QRCodeUtils.generateBitmap(dataBean.getQrcode(), 400, 400));
        this.mIvQrcode.setVisibility(0);
        this.mLayoutPayother.setVisibility(8);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void putOrderSuccessSaoBeiFan(barcodepay.DataBean dataBean) {
        ((Step3Presenter) this.mPresenter).checkPayState();
        this.putOrderSuccess = true;
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void putOrderSuccessUnion(UnionPayGenOrderBack.DataBean dataBean) {
        this.putOrderSuccess = true;
        this.mTvErrormsg.setVisibility(8);
        Constants.pay_no = dataBean.getOut_trade_no();
        this.mIvQrcode.setImageBitmap(QRCodeUtils.generateBitmap(dataBean.getPoly_code_url(), 400, 400));
        this.mIvQrcode.setVisibility(0);
        this.mLayoutPayother.setVisibility(8);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void putOrderSuccessWeChar(PayOrderBean payOrderBean) {
        this.putOrderSuccess = true;
        this.mTvErrormsg.setVisibility(8);
        Constants.pay_no = payOrderBean.getOrderNo();
        this.mIvQrcode.setImageBitmap(QRCodeUtils.generateBitmap(payOrderBean.getPayData(), 400, 400));
        this.mIvQrcode.setVisibility(0);
        this.mLayoutPayother.setVisibility(8);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerStep3Component.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    @SuppressLint({"SetTextI18n"})
    public void showDetailMessage(ProductBean.ProductDetailBean productDetailBean) {
        String str;
        Tools.upLocalLog("商品详细信息获取");
        if (productDetailBean == null) {
            ArmsUtils.snackbarText(getString(R.string.Step3Error1));
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 3));
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<ProductBean.ProductDetailBean.AttachBean> attachList = productDetailBean.getAttachList();
        if (attachList == null) {
            ArmsUtils.snackbarText(getString(R.string.Step3Error2));
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 3));
            return;
        }
        if (attachList.size() == 0) {
            ArmsUtils.snackbarText(getString(R.string.Step3Error3));
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 3));
            return;
        }
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= attachList.size()) {
                break;
            }
            ProductBean.ProductDetailBean.AttachBean attachBean = attachList.get(i);
            if (attachBean.getTemplateFileType() != 1) {
                String fullPath = attachBean.getFullPath();
                String fileName = attachBean.getFileName();
                String existLocalpathByName = Tools.getExistLocalpathByName(fileName);
                if (existLocalpathByName.equals("")) {
                    Tools.loadFileByFullpath(attachBean);
                } else {
                    fullPath = existLocalpathByName;
                }
                if (!fileName.endsWith(".jpg") && !fileName.endsWith(".png")) {
                    z = false;
                }
                arrayList.add(new Advance(fullPath, z ? "2" : "1"));
            }
            i++;
        }
        this.mBanner.setData(arrayList);
        this.mBanner.setResume();
        this.mTvProductname.setText(productDetailBean.getName());
        if (productDetailBean.getSpec() != null) {
            this.mTvProductname.setText(productDetailBean.getName() + " " + productDetailBean.getSpec());
        }
        this.mTvProductname.setSelected(true);
        this.m_ProName = productDetailBean.getName();
        if (LanguageHelper.language().getLangCode().equals("zh")) {
            str = productDetailBean.realPriceText() + "" + this.moneycode;
        } else {
            str = this.moneycode + "" + productDetailBean.realPriceText();
        }
        this.mTvPice.setText(str);
    }

    @Override // com.jess.arms.mvp.IView
    public void showLoading() {
        if (this.mSportDialog.isShowing()) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment.3
            @Override // java.lang.Runnable
            public void run() {
                Step3Fragment.this.mSportDialog.setMessage(Step3Fragment.this.getString(R.string.dataloading));
                Step3Fragment.this.mSportDialog.show();
            }
        });
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void showLoading(final String str) {
        if (this.mSportDialog.isShowing()) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment.2
            @Override // java.lang.Runnable
            public void run() {
                Step3Fragment.this.mSportDialog.setMessage(str);
                Step3Fragment.this.mSportDialog.show();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                Window window = Step3Fragment.this.mSportDialog.getWindow();
                if (window != null) {
                    layoutParams.copyFrom(window.getAttributes());
                    layoutParams.width = (int) (Step3Fragment.this.getResources().getDisplayMetrics().widthPixels * 0.2d);
                    layoutParams.height = -2;
                    window.setAttributes(layoutParams);
                }
            }
        });
    }

    @Override // com.jess.arms.mvp.IView
    public void showMessage(@NonNull String str) {
        Preconditions.checkNotNull(str);
        ArmsUtils.snackbarText(str);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void showOrderFail(boolean z) {
        OrderFailDialog orderFailDialog = new OrderFailDialog(requireContext());
        orderFailDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Step3Fragment$prpuWcH2tDiLGdqguMocjphCGWo
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                Step3Fragment.this.lambda$showOrderFail$2$Step3Fragment(dialogInterface);
            }
        });
        orderFailDialog.show();
        orderFailDialog.setTips(z);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void t50PayCreateQr(T50PayOrderQrBean t50PayOrderQrBean) {
        this.constraintLayout3.setVisibility(8);
        this.constraintLayout2.setVisibility(0);
        Constants.buy_type = 14;
        this.mLayoutPayother.setVisibility(8);
        this.mIvQrcode.setVisibility(0);
        this.mTvErrormsg.setVisibility(0);
        this.mIvQrcode.setImageDrawable(getResources().getDrawable(R.mipmap.loding));
        if (TextUtils.isEmpty(t50PayOrderQrBean.getQr())) {
            this.mTvErrormsg.setVisibility(0);
            this.mTvErrormsg.setText(R.string.qr_code_information_error);
            return;
        }
        this.putOrderSuccess = true;
        this.mTvErrormsg.setVisibility(8);
        Constants.pay_no = t50PayOrderQrBean.getOrderNo();
        this.mIvQrcode.setImageBitmap(QRCodeUtils.generateBitmap(t50PayOrderQrBean.getQr(), 400, 400));
        this.mIvQrcode.setVisibility(0);
        this.mLayoutPayother.setVisibility(8);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step3Contract.View
    public void t50PayWay(List<T50PayWayBean> list) {
        this.tv_pay_t50.setEnabled(true);
        this.payDialog = new T50PayDialog(requireContext(), list);
        this.payDialog.setListener(new T50PayDialog.OnPayClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment.10
            @Override // com.yj.coffeemachines.dialog.T50PayDialog.OnPayClickListener
            public void onPayClick(T50PayWayBean t50PayWayBean) {
                Step3Fragment.this.constraintLayout3.setVisibility(8);
                Step3Fragment.this.constraintLayout2.setVisibility(0);
                Step3Fragment.this.step3_tv_zhifufangshi.setText(Step3Fragment.this.getResources().getString(R.string.selectpayment));
                Constants.buy_type = 14;
                Step3Fragment.this.mLayoutPayother.setVisibility(8);
                Step3Fragment.this.mIvQrcode.setVisibility(0);
                Step3Fragment.this.mTvErrormsg.setVisibility(0);
                Step3Fragment.this.mIvQrcode.setImageDrawable(Step3Fragment.this.getResources().getDrawable(R.mipmap.loding));
                ((Step3Presenter) Step3Fragment.this.mPresenter).t50PayCreateQr(t50PayWayBean.getId());
            }
        });
        this.payDialog.show();
    }
}
