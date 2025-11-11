package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.yj.coffeemachines.DevConfig;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.carousel.Advance;
import com.yj.coffeemachines.carousel.AdvanceView;
import com.yj.coffeemachines.di.component.DaggerStep5Component;
import com.yj.coffeemachines.eventbusbean.ErrorCode_Making;
import com.yj.coffeemachines.eventbusbean.MyEventBusMessage;
import com.yj.coffeemachines.eventbusbean.ProductRefish;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Step5Contract;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.presenter.Step5Presenter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class Step5Fragment extends BaseFragment<Step5Presenter> implements Step5Contract.View {
    public static String tag = "Step5Fragment";

    @BindView(R.id.banner)
    AdvanceView mBanner;

    @BindView(R.id.btn_back)
    RelativeLayout mBtnBack;

    @BindView(R.id.countdowntime)
    TextView mCountdowntime;

    @Inject
    AlertDialog mSportDialog;

    @BindView(R.id.tv_productname)
    TextView mTvProductname;

    @BindView(R.id.tv_pice)
    TextView mtv_pice;

    @BindView(R.id.step5_cl_main)
    ConstraintLayout step5_cl_main;

    @BindView(R.id.step5_iv_enjoy)
    ImageView step5_iv_enjoy;

    @BindView(R.id.step5_iv_takethelid)
    ImageView step5_iv_takethelid;

    @BindView(R.id.step5_ll_takethelid)
    LinearLayout step5_ll_takethelid;

    @BindView(R.id.step5_tv_enjoy)
    TextView step5_tv_enjoy;

    @BindView(R.id.step5_tv_takethelid)
    TextView step5_tv_takethelid;

    @BindView(R.id.step5_view)
    View step5_view;
    private int time;
    private int finaltime = 180;
    private long currentTimeMillis = System.currentTimeMillis();
    private boolean isTakeCup = false;
    private boolean isTakeCap = false;
    private int takeCupTime = 15;
    private int taketime = 1;

    private void find() {
        this.mBtnBack.setVisibility(8);
        DevConfig devConfig = Constants.devConfig();
        if (devConfig.isRight7()) {
            String startTime = devConfig.getStartTime();
            String stopTime = devConfig.getStopTime();
            if (startTime.isEmpty() || stopTime.isEmpty()) {
                this.step5_ll_takethelid.setVisibility(8);
                this.step5_tv_takethelid.setVisibility(8);
                this.step5_iv_enjoy.setVisibility(0);
                this.step5_tv_enjoy.setVisibility(0);
                return;
            }
            Long nowtimehhmmss_long = DataUtils.getNowtimehhmmss_long();
            Long stringHHMMSSDataLong = DataUtils.stringHHMMSSDataLong(startTime + ":00");
            Long stringHHMMSSDataLong2 = DataUtils.stringHHMMSSDataLong(stopTime + ":00");
            if (stringHHMMSSDataLong.longValue() <= nowtimehhmmss_long.longValue() && nowtimehhmmss_long.longValue() <= stringHHMMSSDataLong2.longValue()) {
                this.step5_ll_takethelid.setVisibility(0);
                this.step5_tv_takethelid.setVisibility(0);
                this.step5_iv_enjoy.setVisibility(8);
                this.step5_tv_enjoy.setVisibility(8);
                return;
            }
        }
        this.step5_ll_takethelid.setVisibility(8);
        this.step5_tv_takethelid.setVisibility(8);
        this.step5_iv_enjoy.setVisibility(0);
        this.step5_tv_enjoy.setVisibility(0);
    }

    private void initResource5() {
        this.mCountdowntime.setTextColor(getResources().getColor(R.color.gray_test));
        this.step5_cl_main.setBackgroundColor(getResources().getColor(R.color.white));
        this.mTvProductname.setTextColor(getResources().getColor(R.color.black));
        this.mtv_pice.setTextColor(getResources().getColor(R.color.black));
        if (this.step5_ll_takethelid.getVisibility() == 0) {
            this.step5_iv_enjoy.setImageResource(R.mipmap.step5_finish_pic);
        } else {
            Glide.with(this).load(Integer.valueOf(R.mipmap.step5_finish_pic)).into(this.step5_iv_enjoy);
        }
        this.step5_tv_enjoy.setTextColor(getResources().getColor(R.color.black));
        Glide.with(this).load(Integer.valueOf(R.mipmap.doffing)).into(this.step5_iv_takethelid);
        this.step5_tv_takethelid.setTextColor(getResources().getColor(R.color.black));
        this.step5_view.setBackgroundColor(getResources().getColor(R.color.black));
    }

    private void initialize() {
        Constants.NowStep = 5;
        this.step5_ll_takethelid.setVisibility(8);
        this.step5_tv_takethelid.setVisibility(8);
    }

    public static Step5Fragment newInstance() {
        return new Step5Fragment();
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step5Contract.View
    @Nullable
    public /* bridge */ /* synthetic */ Context getActivity() {
        return super.getActivity();
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void hideLoading() {
        IView.CC.$default$hideLoading(this);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        initialize();
        find();
        showDetailMessage(Constants.nowProduct_Detail);
        if (this.mPresenter != 0) {
            ((Step5Presenter) this.mPresenter).checkMaterialIsEnoughAndSetsSoldOut();
        }
        EventBus.getDefault().post(new ProductRefish());
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_step5, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void killMyself() {
        IView.CC.$default$killMyself(this);
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @OnClick({R.id.btn_back, R.id.step5_ll_takethelid})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_back) {
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
            return;
        }
        if (id != R.id.step5_ll_takethelid) {
            return;
        }
        Tools.upLocalLog("点击取盖");
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_RUN_DOFFING, 2, 6, 0, 36, 6, 1));
        this.isTakeCap = true;
        this.step5_ll_takethelid.setVisibility(8);
        this.step5_tv_takethelid.setVisibility(8);
        if (this.isTakeCup) {
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ErrorCode_Making errorCode_Making) {
        if (Constants.NowStep == 5 && errorCode_Making.isOK && errorCode_Making.message.equals(ErrorCode_Making.ErrorCode.FINISH.name())) {
            this.currentTimeMillis = System.currentTimeMillis();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TimeMessage timeMessage) {
        if (Constants.NowStep != 5) {
            return;
        }
        this.time++;
        if ((this.finaltime - this.time) % 10 == 0) {
            Tools.upLocalLog("取杯倒计时" + (this.finaltime - this.time));
        }
        TextView textView = this.mCountdowntime;
        if (textView != null) {
            textView.setText((this.finaltime - this.time) + "");
        }
        if (this.time >= this.finaltime) {
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.currentTimeMillis;
        if (this.step5_ll_takethelid.getVisibility() == 8) {
            if (currentTimeMillis > 2000) {
                EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
                return;
            }
            return;
        }
        if (currentTimeMillis < WorkRequest.MIN_BACKOFF_MILLIS) {
            return;
        }
        if (this.isTakeCap) {
            Tools.upLocalLog("已取杯&已取盖。返回主界面");
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
            return;
        }
        int i = this.taketime;
        if (i >= 1) {
            this.taketime = i - 1;
            this.mBtnBack.setVisibility(0);
            this.time = this.finaltime - this.takeCupTime;
            Tools.upLocalLog("已经取杯&未取盖，设置倒计时为" + (this.finaltime - this.time) + "秒");
            this.isTakeCup = true;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mBanner.setPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initResource5();
        this.mBanner.setResume();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerStep5Component.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step5Contract.View
    public void showDetailMessage(ProductBean.ProductDetailBean productDetailBean) {
        ArrayList arrayList = new ArrayList();
        List<ProductBean.ProductDetailBean.AttachBean> attachList = productDetailBean.getAttachList();
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= attachList.size()) {
                this.mBanner.setData(arrayList);
                this.mTvProductname.setText(productDetailBean.getName());
                this.mTvProductname.setSelected(true);
                this.mtv_pice.setText(Constants.opsSeting().getCashcode() + productDetailBean.realPriceText());
                return;
            }
            ProductBean.ProductDetailBean.AttachBean attachBean = attachList.get(i);
            if (attachBean.getTemplateFileType() != 1) {
                String fullPath = attachBean.getFullPath();
                String fileName = attachBean.getFileName();
                String existLocalpathByName = Tools.getExistLocalpathByName(fileName);
                if (!existLocalpathByName.equals("")) {
                    fullPath = existLocalpathByName;
                }
                if (!fileName.endsWith(".jpg") && !fileName.endsWith(".png")) {
                    z = false;
                }
                arrayList.add(new Advance(fullPath, z ? "2" : "1"));
            }
            i++;
        }
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void showLoading() {
        IView.CC.$default$showLoading(this);
    }

    @Override // com.jess.arms.mvp.IView
    public void showMessage(@NonNull String str) {
        Preconditions.checkNotNull(str);
        ArmsUtils.snackbarText(str);
    }
}
