package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.OpsSeting;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.carousel.Advance;
import com.yj.coffeemachines.carousel.AdvanceView;
import com.yj.coffeemachines.di.component.DaggerStep2Component;
import com.yj.coffeemachines.eventbusbean.ErrorCode_CurrentGrinding;
import com.yj.coffeemachines.eventbusbean.ErrorCode_IceDEV;
import com.yj.coffeemachines.eventbusbean.ErrorCode_Instant;
import com.yj.coffeemachines.eventbusbean.MyEventBusMessage;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import com.yj.coffeemachines.greendao.daos.MaterialMessageDao;
import com.yj.coffeemachines.helper.LanguageHelper;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Step2Contract;
import com.yj.coffeemachines.mvp.model.beans.BaseMaterial;
import com.yj.coffeemachines.mvp.model.beans.ExchangeGenOrderBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.presenter.Step2Presenter;
import com.yj.coffeemachines.mvp.ui.widget.MyChangeView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class Step2Fragment extends BaseFragment<Step2Presenter> implements Step2Contract.View {
    public static String tag = "Step2Fragment";

    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @BindView(R.id.banner)
    AdvanceView mBanner;

    @BindView(R.id.btn_back)
    RelativeLayout mBtnBack;

    @BindView(R.id.changes_group)
    LinearLayout mChangesGroup;

    @BindView(R.id.countdowntime)
    TextView mCountdowntime;

    @Inject
    AlertDialog mSportDialog;

    @BindView(R.id.tv_pice)
    TextView mTvPice;

    @BindView(R.id.tv_productname)
    TextView mTvProductname;

    @BindView(R.id.tv_productname_en)
    TextView mTvProductnameEn;

    @BindView(R.id.main_back_iv)
    ImageView main_back_iv;

    @BindView(R.id.main_back_tv)
    TextView main_back_tv;

    @BindView(R.id.step2_cl_main)
    ConstraintLayout step2_cl_main;

    @BindView(R.id.step2_view)
    View step2_view;
    private int nowtime = 180;
    boolean iceIsOK = true;
    boolean CurrentIsOK = true;
    boolean click = false;
    private List<BaseMaterial> noAdjust = new ArrayList();
    private List<MyChangeView> changeViews = new ArrayList();
    int num = 0;

    private void clickPutorder() {
        this.click = true;
        putOrder();
    }

    private List<BaseMaterial> getAllMaterialMessage() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.noAdjust);
        for (int i = 0; i < this.changeViews.size(); i++) {
            MyChangeView myChangeView = this.changeViews.get(i);
            arrayList.add(new BaseMaterial(myChangeView.getMaterialMessage(), myChangeView.getFormulaListBean(), myChangeView.getProgress()));
        }
        return arrayList;
    }

    private void initResource5() {
        this.mCountdowntime.setTextColor(getResources().getColor(R.color.gray_test));
        this.step2_cl_main.setBackgroundColor(getResources().getColor(R.color.white));
        this.mTvProductname.setTextColor(getResources().getColor(R.color.black));
        this.mTvPice.setTextColor(getResources().getColor(R.color.black));
        this.main_back_tv.setTextColor(getResources().getColor(R.color.black));
        this.main_back_iv.setImageDrawable(getResources().getDrawable(R.mipmap.back));
        this.step2_view.setBackgroundColor(getResources().getColor(R.color.black));
    }

    private void initialize() {
        Constants.NowStep = 2;
    }

    public static Step2Fragment newInstance(Object obj) {
        Step2Fragment step2Fragment = new Step2Fragment();
        step2Fragment.setData(obj);
        return step2Fragment;
    }

    private void putOrder() {
        hideLoading();
        Constants.setnowProductMaterialMessage(getAllMaterialMessage());
        if (Constants.isExchange) {
            ((Step2Presenter) this.mPresenter).putExchangeOrder();
        } else {
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 2));
        }
    }

    private void setOpsSetting() {
        OpsSeting opsSeting = Constants.opsSeting();
        if (opsSeting != null) {
            this.mChangesGroup.setVisibility(opsSeting.isAdjust() ? 0 : 8);
        }
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step2Contract.View
    @Nullable
    public /* bridge */ /* synthetic */ Context getActivity() {
        return super.getActivity();
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
        if (this.mSportDialog.isShowing()) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step2Fragment.2
                @Override // java.lang.Runnable
                public void run() {
                    Step2Fragment.this.mSportDialog.dismiss();
                }
            });
        }
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        initialize();
        initResource5();
        if (Constants.isExchange) {
            showDetailMessage(Constants.nowProduct_Detail);
        } else {
            ProductBean.ProductDetailBean goodsInfoDetailApiVo = Constants.nowProduct.getGoodsInfoDetailApiVo();
            if (goodsInfoDetailApiVo == null) {
                if (Constants.goodsNull <= 0) {
                    Tools.upLocalLog("检测到数据缺失，重新下载数据，安卓板子重启");
                    Constants.reBoot();
                }
                Constants.goodsNull--;
                Tools.sendArMsg(getString(R.string.dataload));
                EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
            } else {
                showDetailMessage(goodsInfoDetailApiVo);
            }
        }
        setOpsSetting();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_step2, viewGroup, false);
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

    @OnClick({R.id.btn_back, R.id.btn_confirm})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_back) {
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
        } else {
            if (id != R.id.btn_confirm) {
                return;
            }
            clickPutorder();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ErrorCode_CurrentGrinding errorCode_CurrentGrinding) {
        if (Constants.NowStep != 2) {
            return;
        }
        this.CurrentIsOK = errorCode_CurrentGrinding.isOK;
        if (this.iceIsOK && this.CurrentIsOK && this.click) {
            putOrder();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ErrorCode_IceDEV errorCode_IceDEV) {
        if (Constants.NowStep != 2) {
            return;
        }
        this.iceIsOK = errorCode_IceDEV.isOK;
        if (this.iceIsOK && this.CurrentIsOK && this.click) {
            putOrder();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ErrorCode_Instant errorCode_Instant) {
        if (errorCode_Instant.isOK || Constants.NowStep != 2) {
            return;
        }
        EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TimeMessage timeMessage) {
        if (Constants.NowStep != 2) {
            return;
        }
        if (this.nowtime % 10 == 0) {
            Tools.upLocalLog("浓度调节倒计时" + this.nowtime);
        }
        this.nowtime--;
        if (this.mCountdowntime != null) {
            String.format(getString(R.string.hint_countdowntime), this.nowtime + "");
            this.mCountdowntime.setText(this.nowtime);
        }
        if (this.nowtime == 0) {
            ArmsUtils.snackbarText(getString(R.string.timeouted));
            EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        initialize();
        this.mBanner.setPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initialize();
        initResource5();
        this.mBanner.setResume();
        this.nowtime = 180;
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step2Contract.View
    public void putExchangeOrderSuccess(ExchangeGenOrderBack exchangeGenOrderBack) {
        Constants.buy_type = 4;
        Constants.trade_no = exchangeGenOrderBack.getData().toString();
        Tools.upLocalLog("兑换商品：" + exchangeGenOrderBack.toString());
        EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 3));
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step2Contract.View
    public void putExchangeOrderfail(ExchangeGenOrderBack exchangeGenOrderBack) {
        EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerStep2Component.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step2Contract.View
    public void showDetailMessage(ProductBean.ProductDetailBean productDetailBean) {
        Constants.needCurrentgrinding = false;
        Constants.needCurrentgrinding_other = false;
        Constants.needIce = false;
        Constants.nowProduct_Detail = productDetailBean;
        ArrayList arrayList = new ArrayList();
        List<ProductBean.ProductDetailBean.AttachBean> attachList = productDetailBean.getAttachList();
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
        this.mTvProductnameEn.setText(productDetailBean.getNameEn());
        this.mTvPice.setText(LanguageHelper.language().getLangCode().equals("zh") ? productDetailBean.realPriceText() + "" + Constants.opsSeting().getCashcode() : Constants.opsSeting().getCashcode() + "" + productDetailBean.realPriceText());
        this.noAdjust.clear();
        this.changeViews.clear();
        List<ProductBean.ProductDetailBean.FormulaListBean> formulaList = productDetailBean.getFormulaList();
        for (int i2 = 0; i2 < formulaList.size(); i2++) {
            ProductBean.ProductDetailBean.FormulaListBean formulaListBean = formulaList.get(i2);
            List<MaterialMessage> list = DBHelper.getMaterialMessageDao().queryBuilder().where(MaterialMessageDao.Properties.Id.eq(formulaListBean.getRawMaterialId()), new WhereCondition[0]).list();
            if (list.size() == 0) {
                EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
                ArmsUtils.snackbarText(getString(R.string.materiallose));
                return;
            }
            MaterialMessage materialMessage = list.get(0);
            String rawMaterialType = formulaListBean.getRawMaterialType();
            if (Constants.deviceTypeDetail.getName().contains("JK88") && materialMessage.getPosition().contains("6") && formulaListBean.getQty() <= 0.0d) {
                this.noAdjust.add(new BaseMaterial(materialMessage, formulaListBean, 0));
            } else {
                if (Constants.deviceTypeDetail.getName().contains("JK88") && materialMessage.getPosition().contains("8")) {
                    Constants.needCurrentgrinding_other = true;
                }
                if (rawMaterialType.contains(Constants.ice1) || rawMaterialType.contains(Constants.ice2) || rawMaterialType.contains(Constants.ice3) || rawMaterialType.contains(Constants.ice4)) {
                    Constants.needIce = true;
                }
                if (this.num >= 5) {
                    this.noAdjust.add(new BaseMaterial(materialMessage, formulaListBean, 0));
                } else if (rawMaterialType.contains(Constants.ground1) || rawMaterialType.contains(Constants.ground2) || rawMaterialType.contains(Constants.ground3) || rawMaterialType.contains(Constants.ground4) || rawMaterialType.contains(Constants.ground5) || rawMaterialType.contains(Constants.ground6) || rawMaterialType.contains(Constants.ground7) || rawMaterialType.contains(Constants.ground8)) {
                    if (materialMessage.ischange() && Constants.opsSeting().isAdjust()) {
                        Constants.needCurrentgrinding = true;
                        Constants.needCurrentgrinding_times = formulaListBean.getGrindLeachTimes();
                        Constants.needCurrent_data = formulaListBean;
                        Constants.needCurrent_MaterialMessage = materialMessage;
                        Constants.needCurrentmicDate = 0;
                        MyChangeView myChangeView = new MyChangeView(getActivity(), formulaListBean, false, true);
                        myChangeView.setMaterialMessage(materialMessage);
                        myChangeView.setFormulaMessage(formulaListBean);
                        this.mChangesGroup.addView(myChangeView);
                        this.changeViews.add(myChangeView);
                        this.num++;
                    } else {
                        Constants.needCurrentgrinding = true;
                        Constants.needCurrentgrinding_times = formulaListBean.getGrindLeachTimes();
                        Constants.needCurrent_data = formulaListBean;
                        Constants.needCurrent_MaterialMessage = materialMessage;
                        Constants.needCurrentmicDate = 0;
                        this.noAdjust.add(new BaseMaterial(materialMessage, formulaListBean, 0));
                    }
                } else if (!materialMessage.ischange() || !Constants.opsSeting().isAdjust()) {
                    this.noAdjust.add(new BaseMaterial(materialMessage, formulaListBean, 0));
                } else if (materialMessage.getLocalIsSuger()) {
                    MyChangeView myChangeView2 = new MyChangeView(getActivity(), formulaListBean, materialMessage.getLocalIsSuger(), false);
                    myChangeView2.setMaterialMessage(materialMessage);
                    myChangeView2.setFormulaMessage(formulaListBean);
                    this.mChangesGroup.addView(myChangeView2);
                    this.changeViews.add(myChangeView2);
                    this.num++;
                } else {
                    MyChangeView myChangeView3 = new MyChangeView(getActivity(), formulaListBean);
                    myChangeView3.setMaterialMessage(materialMessage);
                    myChangeView3.setFormulaMessage(formulaListBean);
                    this.mChangesGroup.addView(myChangeView3);
                    this.changeViews.add(myChangeView3);
                    this.num++;
                }
            }
        }
        if (this.changeViews.size() == 0 || !Constants.opsSeting().isAdjust()) {
            clickPutorder();
        }
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void showLoading() {
        IView.CC.$default$showLoading(this);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step2Contract.View
    public void showLoading(final String str) {
        if (this.mSportDialog.isShowing()) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step2Fragment.1
            @Override // java.lang.Runnable
            public void run() {
                Step2Fragment.this.mSportDialog.setMessage(str);
                Step2Fragment.this.mSportDialog.show();
            }
        });
    }

    @Override // com.jess.arms.mvp.IView
    public void showMessage(@NonNull String str) {
        Preconditions.checkNotNull(str);
        ArmsUtils.snackbarText(str);
    }
}
