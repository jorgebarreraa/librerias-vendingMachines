package com.yj.coffeemachines.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.shencoder.pagergridlayoutmanager.PagerGridLayoutManager;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.OpsSeting;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.serialport.SerialPortManager;
import com.yj.coffeemachines.app.service.SerialOrderBytes;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.app.utils.KvUtil;
import com.yj.coffeemachines.di.component.DaggerStep1Component;
import com.yj.coffeemachines.eventbusbean.DialogMessageNew;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew_Msg;
import com.yj.coffeemachines.eventbusbean.GoodsRefresh;
import com.yj.coffeemachines.eventbusbean.MyEventBusMessage;
import com.yj.coffeemachines.eventbusbean.ProductRefish;
import com.yj.coffeemachines.eventbusbean.RefreshDiyMessage;
import com.yj.coffeemachines.eventbusbean.ResetTime;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.daos.ADMessageDao;
import com.yj.coffeemachines.greendao.daos.MakeDrinkMessageDao;
import com.yj.coffeemachines.helper.DataLoadChecker;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Step1Contract;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.presenter.Step1Presenter;
import com.yj.coffeemachines.mvp.ui.adapter.Step1Adapter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class Step1Fragment extends BaseFragment<Step1Presenter> implements Step1Contract.View {
    public static final int COUNTDOWNTIME = 120;
    public static int action = 0;
    public static String tag = "Step1Fragment";

    @BindView(R.id.cl_step1_getdrink)
    ConstraintLayout cl_step1_getdrink;
    Step1Adapter mAdapter;

    @BindView(R.id.countdowntime)
    TextView mCountdowntime;

    @BindView(R.id.emptyview)
    LinearLayout mEmptyview;

    @BindView(R.id.foot_layout)
    LinearLayout mFootLayout;

    @BindView(R.id.iv_joinmember)
    ImageView mIvJoinmember;

    @BindView(R.id.iv_last)
    ImageView mIvLast;

    @BindView(R.id.iv_next)
    ImageView mIvNext;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @Inject
    AlertDialog mSportDialog;

    @BindView(R.id.tv_errormesage)
    TextView mTvErrormesage;

    @BindView(R.id.tv_step1)
    TextView mTvStep1;

    @BindView(R.id.tv_step1_en)
    TextView mTvStep1En;

    @BindView(R.id.vip_layout)
    LinearLayout mVipLayout;
    private PagerGridLayoutManager pagerGridLayoutManager;

    @BindView(R.id.step1_cl_main)
    LinearLayout step1_cl_main;

    @BindView(R.id.step1_tv_drinks)
    TextView step1_tv_drinks;

    @BindView(R.id.step1_tv_get)
    TextView step1_tv_get;

    @BindView(R.id.step1_tv_join)
    TextView step1_tv_join;

    @BindView(R.id.step1_tv_members)
    TextView step1_tv_members;
    List<Object> mData = new ArrayList();
    public int nowtime = 120;
    boolean isEquipment = true;
    boolean isStartAdCountdown = false;
    boolean isInit = true;

    private void checkDeviceState() {
        if (Constants.getErrorMsg() != "") {
            return;
        }
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant_main(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_ReadMachineStatus, new int[0]));
    }

    private void configRecycleView() {
        this.pagerGridLayoutManager = new PagerGridLayoutManager(Constants.getRow(), Constants.getColumn(), 0);
        ArmsUtils.configRecyclerView(this.mRecyclerview, this.pagerGridLayoutManager);
    }

    private int getLocalSoldOut(boolean z) {
        return z ? 1 : 0;
    }

    private void initFirstStep() {
        ((Step1Presenter) this.mPresenter).deleteMessage();
        ((Step1Presenter) this.mPresenter).closeNoPay();
        ((Step1Presenter) this.mPresenter).getDeviceAndGoodsInfo();
        ((Step1Presenter) this.mPresenter).getADs();
        ((Step1Presenter) this.mPresenter).getVoices();
        ((Step1Presenter) this.mPresenter).uploadApp();
        ((Step1Presenter) this.mPresenter).getPayWaySetting();
        ((Step1Presenter) this.mPresenter).getQrConfig();
    }

    private void initNetworkData() {
        if (Constants.deviceDetail == null && this.isInit) {
            ErrorCodeNew_Msg.updateErrorCode(true, getString(R.string.Cloudservicedata));
            initFirstStep();
        }
    }

    private void initRecycle() {
        configRecycleView();
        this.mRecyclerview.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment.2
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ProductBean productBean = (ProductBean) Step1Fragment.this.mData.get(i);
                Constants.PAYSTATE = 0;
                Tools.upLocalLog("商品选择:" + productBean.getName());
                Constants.goods_no = productBean.getNo();
                if (productBean.getSoldOut() == 1 || productBean.getLocalSoldOut() == 1) {
                    ArmsUtils.snackbarText(Step1Fragment.this.getString(R.string.productsoldout));
                    return;
                }
                OpsSeting opsSeting = Constants.opsSeting();
                if (opsSeting.islimit()) {
                    if (DBHelper.getMakeDrinkMessageDao().queryBuilder().where(MakeDrinkMessageDao.Properties.Par0.eq(DataUtils.getNowtimeYY_MM_DD()), new WhereCondition[0]).list().size() >= opsSeting.getLimit()) {
                        ArmsUtils.snackbarText(Step1Fragment.this.getString(R.string.dialoglimitmessag));
                        return;
                    }
                }
                Constants.isExchange = false;
                Constants.nowProduct = productBean;
                Step1Fragment.action = 1;
                SerialPortManager serialPortManager = MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Instant();
                if (serialPortManager != null) {
                    if (Constants.deviceTypeDetail == null) {
                        return;
                    }
                    serialPortManager.addSendList(SerialOrderBytes.CheckMacStateNew);
                    if (Constants.deviceTypeDetail.getName().contains("JK88") || Constants.deviceTypeDetail.getName().contains("801")) {
                        serialPortManager.addSendList(SerialOrderBytes.CheckMacStateNew);
                    } else {
                        serialPortManager.addSendList(SerialOrderBytes.CheckMacStateOld);
                    }
                }
                MyEventBusMessage myEventBusMessage = new MyEventBusMessage();
                myEventBusMessage.setFrom(Step1Fragment.tag);
                myEventBusMessage.setType(MyEventBusMessage.TYPE.CLICK);
                myEventBusMessage.setTag(2);
                EventBus.getDefault().post(myEventBusMessage);
            }
        });
    }

    private void initResource5() {
        this.step1_cl_main.setBackgroundColor(getResources().getColor(R.color.white));
        this.step1_tv_join.setTextColor(getResources().getColor(R.color.black));
        this.step1_tv_members.setTextColor(getResources().getColor(R.color.black));
        this.mIvJoinmember.setImageDrawable(getResources().getDrawable(R.mipmap.step1_joinvip1));
        this.step1_tv_get.setTextColor(getResources().getColor(R.color.black));
        this.step1_tv_drinks.setTextColor(getResources().getColor(R.color.black));
        this.mCountdowntime.setTextColor(getResources().getColor(R.color.gray_test));
        this.mRecyclerview.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private void initialize() {
        this.isStartAdCountdown = !ErrorCodeNew_Msg.ErrorNew.contains(getResources().getString(R.string.Cloudservicedata));
        Constants.NowStep = 1;
        initResource5();
        this.cl_step1_getdrink.setVisibility(Constants.opsSeting().isOpenGetDrink() ? 0 : 4);
        this.mVipLayout.setVisibility(Constants.opsSeting().isVip() ? 0 : 8);
    }

    public static Step1Fragment newInstance() {
        return new Step1Fragment();
    }

    private List<ProductBean> refreshLocalData(List<ProductBean> list) {
        String[] isError = ErrorCodeNew_Msg.isError();
        if (isError == null || isError.length <= 0) {
            if (list == null || list.size() <= 0) {
                return null;
            }
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setLocalSoldOut(0);
            }
            return list;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            list.get(i2).setLocalSoldOut(0);
        }
        ArrayList arrayList = new ArrayList();
        for (ProductBean productBean : list) {
            if (productBean.getRawMaterialTypes().contains("冰") || productBean.getRawMaterialTypes().toString().toLowerCase().contains("ice")) {
                productBean.setLocalSoldOut(getLocalSoldOut(isError[2] == "1"));
            }
            ProductBean.ProductDetailBean goodsInfoDetailApiVo = productBean.getGoodsInfoDetailApiVo();
            if (goodsInfoDetailApiVo.getFormulaList() != null && !goodsInfoDetailApiVo.getFormulaList().isEmpty()) {
                if (goodsInfoDetailApiVo.getFormulaList().get(0).getWaterType() == 1) {
                    productBean.setLocalSoldOut(getLocalSoldOut(isError[4] == "1"));
                } else {
                    productBean.setLocalSoldOut(getLocalSoldOut(isError[5] == "1"));
                }
                if (Constants.deviceTypeDetail != null) {
                    if (Constants.deviceTypeDetail.getName().toLowerCase().contains("jk88")) {
                        for (int i3 = 0; i3 < goodsInfoDetailApiVo.getFormulaList().size(); i3++) {
                            if (goodsInfoDetailApiVo.getFormulaList().get(i3).getRawMaterialName().contains("8")) {
                                productBean.setLocalSoldOut(getLocalSoldOut(isError[1] == "1"));
                            }
                        }
                    }
                    if (Constants.deviceTypeDetail.getName().contains("杯茶机")) {
                        for (int i4 = 0; i4 < goodsInfoDetailApiVo.getFormulaList().size(); i4++) {
                            if (goodsInfoDetailApiVo.getFormulaList().get(i4).getRawMaterialType().contains("速溶")) {
                                if ((ErrorCodeNew_Msg.isExist(ErrorCodeNew.A156.name()) && goodsInfoDetailApiVo.getFormulaList().get(i4).getRawMaterialName().contains("1")) || ((ErrorCodeNew_Msg.isExist(ErrorCodeNew.A157.name()) && goodsInfoDetailApiVo.getFormulaList().get(i4).getRawMaterialName().contains("2")) || (ErrorCodeNew_Msg.isExist(ErrorCodeNew.A158.name()) && goodsInfoDetailApiVo.getFormulaList().get(i4).getRawMaterialName().contains("3")))) {
                                    productBean.setLocalSoldOut(1);
                                } else {
                                    productBean.setLocalSoldOut(0);
                                }
                            }
                        }
                    }
                }
                if (productBean.getRawMaterialTypes().contains("现磨") || productBean.getRawMaterialTypes().toString().toLowerCase().contains("ground")) {
                    productBean.setLocalSoldOut(getLocalSoldOut(isError[1] == "1"));
                }
                arrayList.add(productBean);
            }
        }
        return arrayList;
    }

    private void setOpsSetting() {
        Constants.opsSeting();
        this.mVipLayout.setVisibility(8);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.View
    public void adCountdown(boolean z) {
        this.isStartAdCountdown = z;
        this.isInit = false;
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.View
    @Nullable
    public /* bridge */ /* synthetic */ Context getActivity() {
        return super.getActivity();
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
        if (this.mSportDialog.isShowing()) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment.4
                @Override // java.lang.Runnable
                public void run() {
                    if (Step1Fragment.this.mSportDialog == null) {
                        return;
                    }
                    Step1Fragment.this.mSportDialog.dismiss();
                }
            });
        }
    }

    @Override // com.jess.arms.base.delegate.IFragment
    @SuppressLint({"MissingPermission"})
    public void initData(@Nullable Bundle bundle) {
        this.mAdapter = new Step1Adapter(R.layout.step1product_item_layout, this.mData);
        setOpsSetting();
        initNetworkData();
        initRecycle();
        if (Constants.deviceDetail == null) {
            DataLoadChecker.getInstance().setLoadCallback(new DataLoadChecker.LoadCallback() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment.1
                @Override // com.yj.coffeemachines.helper.DataLoadChecker.LoadCallback
                public void onRetry() {
                    Tools.upLocalLog("数据加载失败，开启重试！");
                    if (Step1Fragment.this.mPresenter != null) {
                        ((Step1Presenter) Step1Fragment.this.mPresenter).getDeviceAndGoodsInfo();
                    }
                }

                @Override // com.yj.coffeemachines.helper.DataLoadChecker.LoadCallback
                public void onSuccess() {
                    Tools.upLocalLog("数据加载成功！");
                }
            });
            DataLoadChecker.getInstance().start();
            Tools.upLocalLog("数据加载检查启动");
        }
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_step1, viewGroup, false);
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

    @OnClick({R.id.vip_layout, R.id.step_ll_getdrinks, R.id.iv_last, R.id.iv_next, R.id.cl_step1_getdrink, R.id.countdowntime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.countdowntime /* 2131296433 */:
                this.nowtime = 0;
                return;
            case R.id.iv_last /* 2131296548 */:
                this.pagerGridLayoutManager.scrollToPrePager();
                return;
            case R.id.iv_next /* 2131296551 */:
                this.pagerGridLayoutManager.scrollToNextPager();
                return;
            case R.id.step_ll_getdrinks /* 2131296823 */:
                checkDeviceState();
                MyEventBusMessage myEventBusMessage = new MyEventBusMessage();
                myEventBusMessage.setFrom(tag);
                myEventBusMessage.setType(MyEventBusMessage.TYPE.CLICK);
                myEventBusMessage.setTag(4);
                EventBus.getDefault().post(myEventBusMessage);
                return;
            case R.id.vip_layout /* 2131297119 */:
                MyEventBusMessage myEventBusMessage2 = new MyEventBusMessage();
                myEventBusMessage2.setFrom(tag);
                myEventBusMessage2.setType(MyEventBusMessage.TYPE.CLICK);
                myEventBusMessage2.setTag(1);
                EventBus.getDefault().post(myEventBusMessage2);
                return;
            default:
                return;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.isInit = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GoodsRefresh goodsRefresh) {
        String[] strArr = goodsRefresh.getsError();
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        Tools.upLocalLog("出现故障GoodsRefresh.getsError()；" + goodsRefresh.toString());
        refreshData(Constants.step1GoodsBack);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ProductRefish productRefish) {
        if (!KvUtil.getInstance().getString("ProductRefish").isEmpty()) {
            initFirstStep();
            KvUtil.getInstance().putString("ProductRefish", "");
        } else {
            Constants.ProductRefish--;
            ((Step1Presenter) this.mPresenter).getDeviceAndGoodsInfo();
            ((Step1Presenter) this.mPresenter).getGoodsInfo();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshDiyMessage refreshDiyMessage) {
        configRecycleView();
        this.mAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ResetTime resetTime) {
        this.nowtime = 120;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TimeMessage timeMessage) {
        if (this.isEquipment) {
            if (Constants.e00001) {
                this.isEquipment = false;
                EventBus.getDefault().post(new DialogMessageNew("E00001", 0));
                return;
            }
            if (Constants.e00002) {
                this.isEquipment = false;
                EventBus.getDefault().post(new DialogMessageNew("E00002", 0));
                return;
            }
            if (!Constants.isConnecting_mqtt && !Constants.isConnected_mqtt) {
                Constants.iStartMQTT_mqtt--;
                if (Constants.iStartMQTT_mqtt <= 0) {
                    Constants.iStartMQTT_mqtt = 10;
                    MyAppLocation.myAppLocation.myMqttService.initMqtt();
                }
            }
            if (Constants.NowStep != 1) {
                return;
            }
            int i = this.nowtime;
            if (i <= 0) {
                this.nowtime = 120;
                if (Constants.opsSeting().isPlayAD() && Constants.NowStep == 1 && !Constants.isSettingInterface && DBHelper.getADMessageDao().queryBuilder().where(ADMessageDao.Properties.StartTime_long.le(Long.valueOf(System.currentTimeMillis())), new WhereCondition[0]).where(ADMessageDao.Properties.EndTime_long.ge(Long.valueOf(System.currentTimeMillis())), new WhereCondition[0]).orderAsc(ADMessageDao.Properties.Sort).list().size() != 0) {
                    Constants.isVoice = false;
                    MyEventBusMessage myEventBusMessage = new MyEventBusMessage();
                    myEventBusMessage.setFrom(tag);
                    myEventBusMessage.setType(MyEventBusMessage.TYPE.CLICK);
                    myEventBusMessage.setTag(5);
                    EventBus.getDefault().post(myEventBusMessage);
                }
            } else if (this.isStartAdCountdown) {
                this.nowtime = i - 1;
            }
            if (!Constants.opsSeting().isPlayAD()) {
                this.mCountdowntime.setVisibility(8);
                return;
            }
            TextView textView = this.mCountdowntime;
            if (textView != null) {
                textView.setVisibility(0);
                this.mCountdowntime.setText(String.format(getString(R.string.hint_countdowntime), this.nowtime + ""));
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"MissingPermission"})
    public void onResume() {
        super.onResume();
        if (Constants.ProductRefish > 0) {
            ((Step1Presenter) this.mPresenter).getGoodsInfo();
        }
        if (Constants.opsSeting().isHintTooBar()) {
            Constants.setHintTooBar(getActivity());
        }
        initialize();
        Constants.ISMAKINGADRINK = false;
        refreshData(Constants.step1GoodsBack);
        if (Constants.opsSeting().isPlayAD()) {
            this.mCountdowntime.setVisibility(0);
        } else {
            this.mCountdowntime.setVisibility(8);
        }
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.View
    public void refreshData(List<ProductBean> list) {
        Tools.upLocalLogTest("refreshData");
        if (list == null) {
            if (this.mEmptyview == null) {
                return;
            }
            List<Object> list2 = this.mData;
            if (list2 == null || list2.size() == 0) {
                this.mEmptyview.setVisibility(0);
                return;
            } else {
                this.mEmptyview.setVisibility(8);
                return;
            }
        }
        List<Object> list3 = this.mData;
        if (list3 == null) {
            return;
        }
        list3.clear();
        this.mData.addAll(refreshLocalData(list));
        this.mAdapter.notifyDataSetChanged();
        if (this.mEmptyview == null) {
            return;
        }
        if (this.mData.size() == 0) {
            this.mEmptyview.setVisibility(0);
            return;
        }
        this.mEmptyview.setVisibility(8);
        this.mIvNext.setVisibility(0);
        this.mIvLast.setVisibility(0);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerStep1Component.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void showLoading() {
        IView.CC.$default$showLoading(this);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step1Contract.View
    public void showLoading(final String str) {
        if (this.mSportDialog.isShowing()) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment.3
            @Override // java.lang.Runnable
            public void run() {
                Step1Fragment.this.mSportDialog.setMessage(str);
                Step1Fragment.this.mSportDialog.show();
            }
        });
    }

    @Override // com.jess.arms.mvp.IView
    public void showMessage(@NonNull String str) {
        Preconditions.checkNotNull(str);
        ArmsUtils.snackbarText(str);
    }
}
