package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.WorkRequest;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.serialport.SerialPortManager;
import com.yj.coffeemachines.app.service.SerialOrderBytes;
import com.yj.coffeemachines.di.component.DaggerEventComponent;
import com.yj.coffeemachines.dialog.ExchangeDialog;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew_Msg;
import com.yj.coffeemachines.eventbusbean.MyEventBusMessage;
import com.yj.coffeemachines.eventbusbean.QRCodeMessage;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.EventContract;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.presenter.EventPresenter;
import java.util.List;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class EventFragment extends BaseFragment<EventPresenter> implements EventContract.View {
    public static String tag = "EventFragment";
    private ExchangeDialog dialog;

    @BindView(R.id.event_tv_1)
    TextView event_tv_1;

    @BindView(R.id.event_tv_2)
    TextView event_tv_2;

    @BindView(R.id.event_tv_3)
    TextView event_tv_3;

    @BindView(R.id.event_tv_4)
    TextView event_tv_4;

    @BindView(R.id.btn_back)
    RelativeLayout mBtnBack;

    @BindView(R.id.countdowntime)
    TextView mCountdowntime;

    @BindView(R.id.iv_qrcode)
    ImageView mIvQrcode;

    @BindView(R.id.parentlayout)
    RelativeLayout mParentlayout;

    @Inject
    AlertDialog mSportDialog;

    @BindView(R.id.tv_0)
    TextView mTv0;

    @BindView(R.id.tv_1)
    TextView mTv1;

    @BindView(R.id.tv_2)
    TextView mTv2;

    @BindView(R.id.tv_3)
    TextView mTv3;

    @BindView(R.id.tv_4)
    TextView mTv4;

    @BindView(R.id.tv_5)
    TextView mTv5;

    @BindView(R.id.tv_6)
    TextView mTv6;

    @BindView(R.id.tv_7)
    TextView mTv7;

    @BindView(R.id.tv_8)
    TextView mTv8;

    @BindView(R.id.tv_9)
    TextView mTv9;

    @BindView(R.id.tv_delete)
    TextView mTvDelete;

    @BindView(R.id.tv_inputmessage)
    TextView mTvInputmessage;

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    @BindView(R.id.main_back_iv)
    ImageView main_back_iv;

    @BindView(R.id.main_back_tv)
    TextView main_back_tv;
    private int countDown = 180;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable cupExitTask = new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.EventFragment.1
        @Override // java.lang.Runnable
        public void run() {
            if (EventFragment.this.mSportDialog != null && EventFragment.this.mSportDialog.isShowing()) {
                EventFragment.this.mSportDialog.dismiss();
            }
            if (EventFragment.this.dialog != null && EventFragment.this.dialog.isShowing()) {
                EventFragment.this.dialog.dismiss();
            }
            EventFragment eventFragment = EventFragment.this;
            eventFragment.onClick(eventFragment.mBtnBack);
        }
    };
    private String inputMessage = "";

    private void checkIsCup() {
        if (!ErrorCodeNew_Msg.ErrorNew.contains(ErrorCodeNew.A09.name())) {
            this.handler.removeCallbacks(this.cupExitTask);
            return;
        }
        AlertDialog alertDialog = this.mSportDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            this.handler.postDelayed(this.cupExitTask, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
            this.countDown += 30;
            if (MyAppLocation.myAppLocation == null || MyAppLocation.myAppLocation.mSerialDataService == null) {
                return;
            }
            if (MyAppLocation.myAppLocation.voicePlayService != null) {
                MyAppLocation.myAppLocation.voicePlayService.playVoice(5);
            } else {
                Tools.upLocalLog("step3_MyAppLocation.myAppLocation.voicePlayService_为空");
            }
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant_main(new byte[]{-86, 85, 4, 36, 0, 20, 59});
            showLoading(getResources().getString(R.string.present_cup_hint));
        }
    }

    private void checkStatus() {
        SerialPortManager serialPortManager = MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Instant();
        if (serialPortManager != null) {
            serialPortManager.addSendList(SerialOrderBytes.CheckMacStateNew);
            if (Constants.deviceTypeDetail.getName().contains("JK88") || Constants.deviceTypeDetail.getName().contains("801")) {
                serialPortManager.addSendList(SerialOrderBytes.CheckMacStateNew);
            } else {
                serialPortManager.addSendList(SerialOrderBytes.CheckMacStateOld);
            }
        }
    }

    private void deleteAndShow() {
        if (this.inputMessage.length() <= 0) {
            this.mTvInputmessage.setText(getResources().getString(R.string.shuru));
            return;
        }
        this.inputMessage = this.inputMessage.substring(0, r0.length() - 1);
        this.mTvInputmessage.setText(this.inputMessage);
        if (this.inputMessage.length() == 0) {
            this.mTvInputmessage.setText(getResources().getString(R.string.shuru));
        }
    }

    private void initResources5() {
        this.mParentlayout.setBackgroundColor(getResources().getColor(R.color.white));
        this.main_back_tv.setTextColor(getResources().getColor(R.color.black));
        this.main_back_iv.setImageDrawable(getResources().getDrawable(R.mipmap.back));
        this.event_tv_1.setTextColor(getResources().getColor(R.color.black));
        this.event_tv_2.setTextColor(getResources().getColor(R.color.black));
        this.event_tv_3.setTextColor(getResources().getColor(R.color.black));
        this.event_tv_4.setTextColor(getResources().getColor(R.color.black));
        this.mTv0.setTextColor(getResources().getColor(R.color.black));
        this.mTv1.setTextColor(getResources().getColor(R.color.black));
        this.mTv2.setTextColor(getResources().getColor(R.color.black));
        this.mTv3.setTextColor(getResources().getColor(R.color.black));
        this.mTv4.setTextColor(getResources().getColor(R.color.black));
        this.mTv5.setTextColor(getResources().getColor(R.color.black));
        this.mTv6.setTextColor(getResources().getColor(R.color.black));
        this.mTv7.setTextColor(getResources().getColor(R.color.black));
        this.mTv8.setTextColor(getResources().getColor(R.color.black));
        this.mTv9.setTextColor(getResources().getColor(R.color.black));
        this.mTvDelete.setTextColor(getResources().getColor(R.color.black));
        this.mTvSubmit.setTextColor(getResources().getColor(R.color.black));
        this.mTvInputmessage.setTextColor(getResources().getColor(R.color.black));
    }

    private void initialize() {
        Constants.NowStep = 7;
    }

    private void inputAndShow(int i) {
        this.inputMessage += i;
        this.mTvInputmessage.setText(this.inputMessage);
    }

    public static EventFragment newInstance() {
        return new EventFragment();
    }

    @Override // com.yj.coffeemachines.mvp.contract.EventContract.View
    @Nullable
    public /* bridge */ /* synthetic */ Activity getActivity() {
        return super.getActivity();
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void hideLoading() {
        IView.CC.$default$hideLoading(this);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        initialize();
        if (TextUtils.isEmpty(Constants.exchangeQr) || TextUtils.isEmpty(Constants.exchangeTips)) {
            this.mIvQrcode.setVisibility(8);
            this.event_tv_1.setVisibility(8);
        } else {
            Glide.with(requireContext()).load(Constants.exchangeQr).into(this.mIvQrcode);
            this.event_tv_1.setText(String.format("1. %s", Constants.exchangeTips));
            this.event_tv_2.setText(String.format("2. %s", getString(R.string.eventmsg2)));
        }
        checkIsCup();
        checkStatus();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_event, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void killMyself() {
        IView.CC.$default$killMyself(this);
    }

    public /* synthetic */ boolean lambda$showExchangeDialog$1$EventFragment(String str, ProductBean.ProductDetailBean productDetailBean) {
        if (productDetailBean.getSoldOut() == 1 || productDetailBean.getLocalSoldOut() == 1) {
            ArmsUtils.snackbarText(getString(R.string.productsoltout));
            return false;
        }
        Constants.nowProduct_Detail = productDetailBean;
        submitSuccess(str);
        return true;
    }

    public /* synthetic */ void lambda$showLoading$0$EventFragment(String str) {
        this.mSportDialog.setMessage(str);
        this.mSportDialog.show();
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @OnClick({R.id.btn_back, R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.tv_6, R.id.tv_7, R.id.tv_8, R.id.tv_9, R.id.tv_0, R.id.tv_delete, R.id.tv_submit})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_back) {
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
            return;
        }
        if (id == R.id.tv_delete) {
            deleteAndShow();
            return;
        }
        if (id == R.id.tv_submit) {
            ((EventPresenter) this.mPresenter).submit(this.mTvInputmessage.getText().toString());
            return;
        }
        switch (id) {
            case R.id.tv_0 /* 2131296914 */:
                inputAndShow(0);
                return;
            case R.id.tv_1 /* 2131296915 */:
                inputAndShow(1);
                return;
            case R.id.tv_2 /* 2131296916 */:
                inputAndShow(2);
                return;
            case R.id.tv_3 /* 2131296917 */:
                inputAndShow(3);
                return;
            case R.id.tv_4 /* 2131296918 */:
                inputAndShow(4);
                return;
            case R.id.tv_5 /* 2131296919 */:
                inputAndShow(5);
                return;
            case R.id.tv_6 /* 2131296920 */:
                inputAndShow(6);
                return;
            case R.id.tv_7 /* 2131296921 */:
                inputAndShow(7);
                return;
            case R.id.tv_8 /* 2131296922 */:
                inputAndShow(8);
                return;
            case R.id.tv_9 /* 2131296923 */:
                inputAndShow(9);
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(QRCodeMessage qRCodeMessage) {
        String qrcode = qRCodeMessage.getQrcode();
        this.mTvInputmessage.setText(qrcode);
        ((EventPresenter) this.mPresenter).submit(qrcode);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TimeMessage timeMessage) {
        if (Constants.NowStep != 7) {
            return;
        }
        checkIsCup();
        this.countDown--;
        if (this.mCountdowntime != null) {
            String.format(getString(R.string.hint_countdowntime), this.countDown + "");
            this.mCountdowntime.setText(this.countDown);
        }
        if (this.countDown <= 0) {
            AlertDialog alertDialog = this.mSportDialog;
            if (alertDialog != null && alertDialog.isShowing()) {
                this.mSportDialog.dismiss();
            }
            ExchangeDialog exchangeDialog = this.dialog;
            if (exchangeDialog != null && exchangeDialog.isShowing()) {
                this.dialog.dismiss();
            }
            onClick(this.mBtnBack);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initResources5();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerEventComponent.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override // com.yj.coffeemachines.mvp.contract.EventContract.View
    public void showExchangeDialog(final String str, List<ProductBean.ProductDetailBean> list) {
        if (this.dialog == null) {
            this.dialog = new ExchangeDialog(requireContext());
        }
        this.dialog.setOnConfirmListener(new ExchangeDialog.onConfirmListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$EventFragment$PiPWP4dX_cfEdHCJQwGHbcVgca4
            @Override // com.yj.coffeemachines.dialog.ExchangeDialog.onConfirmListener
            public final boolean onConfirm(ProductBean.ProductDetailBean productDetailBean) {
                return EventFragment.this.lambda$showExchangeDialog$1$EventFragment(str, productDetailBean);
            }
        });
        this.dialog.show();
        this.dialog.setProductData(list);
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void showLoading() {
        IView.CC.$default$showLoading(this);
    }

    public void showLoading(final String str) {
        if (this.mSportDialog.isShowing()) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$EventFragment$Eph9VCZkanWU--ok1cxK3GSMgFE
            @Override // java.lang.Runnable
            public final void run() {
                EventFragment.this.lambda$showLoading$0$EventFragment(str);
            }
        });
    }

    @Override // com.jess.arms.mvp.IView
    public void showMessage(@NonNull String str) {
        Preconditions.checkNotNull(str);
        ArmsUtils.snackbarText(str);
    }

    @Override // com.yj.coffeemachines.mvp.contract.EventContract.View
    public void submitSuccess(String str) {
        Constants.isExchange = true;
        Constants.exchangeCode = str;
        EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 2));
    }
}
