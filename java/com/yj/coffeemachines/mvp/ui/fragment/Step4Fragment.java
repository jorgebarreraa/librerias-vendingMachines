package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import com.blankj.utilcode.util.TimeUtils;
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
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.app.utils.DiaLogUtils;
import com.yj.coffeemachines.app.utils.RxUtils;
import com.yj.coffeemachines.carousel.Advance;
import com.yj.coffeemachines.carousel.AdvanceView;
import com.yj.coffeemachines.di.component.DaggerStep4Component;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew_Msg;
import com.yj.coffeemachines.eventbusbean.ErrorCode_Making;
import com.yj.coffeemachines.eventbusbean.ErrorCode_Paymenting;
import com.yj.coffeemachines.eventbusbean.MyEventBusMessage;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.MakeDrinkMessage;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Step4Contract;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.presenter.Step4Presenter;
import com.yj.coffeemachines.mvp.ui.widget.numberprogressbar.NumberProgressBar;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class Step4Fragment extends BaseFragment<Step4Presenter> implements Step4Contract.View {
    private static final int READ_AMOUNT_MAX = 15;
    public static String tag = "Step4Fragment";
    AlertDialog alertDialog;
    AlertDialog alertDialogMakeing;

    @BindView(R.id.banner)
    AdvanceView mBanner;

    @BindView(R.id.countdowntime)
    TextView mCountdowntime;
    private ScheduledThreadPoolExecutor mExecutorService;

    @BindView(R.id.iv_product_pic)
    ImageView mIvProductPic;

    @BindView(R.id.pb_schedule)
    NumberProgressBar mPbSchedule;

    @Inject
    AlertDialog mSportDialog;

    @BindView(R.id.tv_message)
    TextView mTvMessage;

    @BindView(R.id.tv_message_en)
    TextView mTvMessageEn;

    @BindView(R.id.tv_productname)
    TextView mTvProductname;

    @BindView(R.id.tv_pice)
    TextView mtv_pice;

    @BindView(R.id.step4_cl_main)
    ConstraintLayout step4_cl_main;

    @BindView(R.id.step4_view)
    View step4_view;
    private int orderCountTime = (Constants.needCurrentgrinding_times * 60) + 180;
    private int nowtime = (Constants.needCurrentgrinding_times * 60) + 180;
    private int nowtime_ = (Constants.needCurrentgrinding_times * 60) + 180;
    private long getDrinkMills = 0;
    private int sendGetCup = 0;
    private int cyclicQueryTimes = 30;
    private boolean checkState = true;
    SerialPortManager serialPortManager = null;
    boolean isMaking = false;
    int clearCurrentNum = 0;
    boolean isCupTips = false;
    private int readAmountCount = 0;
    int clear = 0;
    int check = 3;
    boolean setcuptype_1 = false;
    boolean issetWaterAndMetail_2 = false;
    boolean getondrink_3 = false;
    boolean getcupsuccess_4 = false;
    boolean geticesuccess_5 = false;
    boolean istandby_6 = false;
    boolean setcurrentfinish_7 = false;
    boolean makecesuccess_8 = false;
    int makeSuccessInstent = 0;
    int makeSuccessCurrent = 0;
    int makeSuccessIce = 0;
    int iSETWATERANDMATERA = 2;
    int receiveCount = 0;

    private void checkClearCurrent() {
        Constants.clearCurrentTime--;
        this.check--;
        if (this.check >= 0) {
            return;
        }
        if (Constants.clearCurrentTime - 3 <= 0) {
            if (this.clear <= 0) {
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(SerialOrderBytes.cancleFreshlyGroundClear);
            }
            this.clear++;
        }
        if (Constants.clearCurrentTime <= 0) {
            Constants.JK88ClearCurrent = 2;
        } else {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(SerialOrderBytes.CurErrorQuery);
        }
    }

    private void checkMachineState() {
        if (ErrorCodeNew_Msg.MacState != ErrorCodeNew.Error && !ErrorCodeNew_Msg.isError()[0].equals("1")) {
            if (ErrorCodeNew_Msg.MacState == ErrorCodeNew.Busy) {
                this.cyclicQueryTimes = 30;
                this.checkState = true;
                return;
            }
            return;
        }
        Tools.upLocalLog("机器状态：" + ErrorCodeNew_Msg.MacState.name() + "机器错误" + ErrorCodeNew_Msg.ErrorNew);
        EventBus.getDefault().post(new ErrorCode_Making(false, ErrorCode_Making.ErrorCode.CHECKFALSE, ErrorCodeNew_Msg.MacState.name()));
    }

    private void init() {
        Constants.NowStep = 4;
        this.orderCountTime = (Constants.needCurrentgrinding_times * 60) + 180;
        orderCountDown();
    }

    private void initResource5() {
        this.mCountdowntime.setTextColor(getResources().getColor(R.color.gray_test));
        this.step4_cl_main.setBackgroundColor(getResources().getColor(R.color.white));
        this.mTvProductname.setTextColor(getResources().getColor(R.color.black));
        this.mtv_pice.setTextColor(getResources().getColor(R.color.black));
        this.mTvMessage.setTextColor(getResources().getColor(R.color.black));
        this.mTvMessageEn.setTextColor(getResources().getColor(R.color.black));
        Glide.with(this).load(Integer.valueOf(R.mipmap.step4_zhizuozhong_pic)).into(this.mIvProductPic);
        this.step4_view.setBackgroundColor(getResources().getColor(R.color.black));
    }

    private void makeFail() {
        Tools.upLocalLog("制作失败");
        EventBus.getDefault().post(new ErrorCode_Making(false, ErrorCode_Making.ErrorCode.CHECKFALSE, ErrorCode_Making.ErrorCode.CHECKFALSE.name()));
    }

    public static Step4Fragment newInstance() {
        return new Step4Fragment();
    }

    private void orderCountDown() {
        RxUtils.countdown(this.orderCountTime, 0L, this, new Consumer() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Step4Fragment$dvF-a0d6XWRJbz16PQdS2QZUxqE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Step4Fragment.this.lambda$orderCountDown$0$Step4Fragment((Long) obj);
            }
        });
    }

    private void runClearCurrent() {
        if (Constants.deviceTypeDetail.getName().contains("JK88")) {
            Constants.JK88ClearCurrent = 1;
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(SerialOrderBytes.FreshlyGroundClear);
        } else {
            MyAppLocation.myAppLocation.mSerialDataService.cleanGround("");
        }
        Constants.coffeeTime = System.currentTimeMillis();
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step4Contract.View
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
        this.mExecutorService = (ScheduledThreadPoolExecutor) ArmsUtils.obtainAppComponentFromContext(this.mContext).executorService();
        init();
        showDetailMessage(Constants.nowProduct_Detail);
        this.serialPortManager = MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Instant();
        checkMachineState();
        MyAppLocation.myAppLocation.voicePlayService.playVoice(2);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_step4, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void killMyself() {
        IView.CC.$default$killMyself(this);
    }

    public /* synthetic */ void lambda$orderCountDown$0$Step4Fragment(Long l) throws Exception {
        if (l.longValue() % 10 == 0) {
            Tools.upLocalLog("订单制作倒计时" + l);
        }
        if (l.longValue() <= 0) {
            Tools.upLocalLog("订单制作超时");
            ArmsUtils.snackbarText(getString(R.string.timeouted));
            ((Step4Presenter) this.mPresenter).makeFailure(getString(R.string.placement_timeout));
        }
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step4Contract.View
    public void makeFinish() {
        if (Constants.buy_type == 3 && Constants.opsSeting().isChange() && Constants.HomePay() != 2) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(Constants.smallChange_payment((byte) Constants.change_num_amd_success));
            Tools.upLocalLog("5退款：" + Constants.change_num_amd_success);
        }
        MyAppLocation.myAppLocation.voicePlayService.playVoice(3);
        MakeDrinkMessage makeDrinkMessage = new MakeDrinkMessage();
        makeDrinkMessage.setKey(null);
        makeDrinkMessage.setProductPrice(Constants.pice_now);
        makeDrinkMessage.setPayNumber(Constants.pay_num);
        makeDrinkMessage.setChangeNumber(Constants.change_num);
        makeDrinkMessage.setIsExchange(Constants.isExchange);
        makeDrinkMessage.setTime(Long.valueOf(System.currentTimeMillis()));
        makeDrinkMessage.setPar0(DataUtils.getNowtimeYY_MM_DD());
        DBHelper.getMakeDrinkMessageDao().insert(makeDrinkMessage);
        EventBus.getDefault().post(new MyEventBusMessage(tag, MyEventBusMessage.TYPE.CLICK, "", 1));
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(final ErrorCode_Making errorCode_Making) {
        int i;
        String str = errorCode_Making.message;
        if (Constants.NowStep != 4) {
            return;
        }
        if (errorCode_Making.isOK) {
            if (str.equals(ErrorCode_Making.ErrorCode.CUPTYPE.name())) {
                if (this.setcuptype_1) {
                    return;
                }
                this.setcuptype_1 = true;
                ((Step4Presenter) this.mPresenter).setWaterAndMetail();
            } else if (str.equals(ErrorCode_Making.ErrorCode.SETWATERANDMATERA.name())) {
                if (this.issetWaterAndMetail_2) {
                    return;
                }
                this.issetWaterAndMetail_2 = true;
                ((Step4Presenter) this.mPresenter).getDrink();
                Constants.MacMakingState = 1;
            } else if (str.equals(ErrorCode_Making.ErrorCode.GETDRINK.name())) {
                if (this.getondrink_3) {
                    return;
                }
                this.getondrink_3 = true;
                this.getDrinkMills = TimeUtils.getNowMills();
            } else if (str.equals(ErrorCode_Making.ErrorCode.GETCUP.name())) {
                if (this.getcupsuccess_4) {
                    return;
                }
                this.getcupsuccess_4 = true;
                int i2 = Constants.buy_type;
                SerialPortManager serialPortManager = MyAppLocation.myAppLocation.mSerialDataService.getmmSerialPortManager_Payment();
                if (serialPortManager != null) {
                    serialPortManager.addSendList(Constants.readAmount_payment());
                }
                if (!Constants.needIce) {
                    if (Constants.needCurrentgrinding) {
                        ((Step4Presenter) this.mPresenter).startCurrent();
                    } else {
                        ((Step4Presenter) this.mPresenter).sendFreshGroundCoffeeFinished();
                    }
                }
            } else if (str.equals(ErrorCode_Making.ErrorCode.FRESHGROUNDCOFFEEFINISHED.name())) {
                if (this.setcurrentfinish_7) {
                    return;
                } else {
                    this.setcurrentfinish_7 = true;
                }
            } else if (str.equals(ErrorCode_Making.ErrorCode.CURRENTERROR.name())) {
                this.receiveCount++;
                if (this.receiveCount > 5) {
                    this.receiveCount = 0;
                    makeFail();
                }
            } else if (str.equals(ErrorCode_Making.ErrorCode.GETICE.name())) {
                if (this.geticesuccess_5) {
                    return;
                }
                this.makeSuccessIce++;
                this.geticesuccess_5 = true;
                if (Constants.needIce) {
                    if (Constants.needCurrentgrinding) {
                        ((Step4Presenter) this.mPresenter).startCurrent();
                    } else {
                        ((Step4Presenter) this.mPresenter).sendFreshGroundCoffeeFinished();
                    }
                }
            } else if (str.equals(ErrorCode_Making.ErrorCode.FINISH.name())) {
                Constants.MacMakingState = 2;
                if (this.makecesuccess_8) {
                    return;
                } else {
                    this.makeSuccessInstent++;
                }
            } else if (str.equals(ErrorCode_Making.ErrorCode.CURRENTFINISH.name())) {
                this.receiveCount = 0;
                if (System.currentTimeMillis() - ((Step4Presenter) this.mPresenter).currentTimeMillis > 3000 && ((Step4Presenter) this.mPresenter).startcurrent) {
                    if (Constants.needCurrentgrinding_times > 0) {
                        new Thread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step4Fragment.4
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    Thread.sleep(2000L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                ((Step4Presenter) Step4Fragment.this.mPresenter).startCurrent();
                            }
                        }).start();
                    } else {
                        if (this.istandby_6) {
                            return;
                        }
                        this.istandby_6 = true;
                        ((Step4Presenter) this.mPresenter).startcurrent = false;
                        ((Step4Presenter) this.mPresenter).sendFreshGroundCoffeeFinished();
                        ((Step4Presenter) this.mPresenter).standby();
                        this.makeSuccessCurrent++;
                    }
                }
            } else {
                str.equals(ErrorCode_Making.ErrorCode.CURRENTFINISHING.name());
            }
        } else {
            if (errorCode_Making.errorCode == ErrorCode_Making.ErrorCode.SETWATERANDMATERA && (i = this.iSETWATERANDMATERA) >= 1) {
                this.iSETWATERANDMATERA = i - 1;
                ((Step4Presenter) this.mPresenter).setWaterAndMetail();
                return;
            }
            Constants.MacMakingState = 3;
            if (!Constants.ISMAKINGADRINK) {
                return;
            }
            Tools.upLocalLog("制作异常，开始播放语音_" + errorCode_Making.toString());
            this.makeSuccessCurrent = 0;
            this.makeSuccessIce = 0;
            this.makeSuccessInstent = 0;
            MyAppLocation.myAppLocation.voicePlayService.playVoice(4);
            if (str.equals(ErrorCode_Making.ErrorCode.GETCUP.name())) {
                this.mExecutorService.schedule(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step4Fragment.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Step4Fragment.this.alertDialogMakeing.isShowing()) {
                            Step4Fragment.this.alertDialogMakeing.dismiss();
                            ((Step4Presenter) Step4Fragment.this.mPresenter).makeFailure(errorCode_Making.errorCode.toString());
                        }
                    }
                }, 3L, TimeUnit.SECONDS);
            } else {
                this.mExecutorService.schedule(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step4Fragment.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Step4Fragment.this.alertDialogMakeing.isShowing()) {
                            Step4Fragment.this.alertDialogMakeing.dismiss();
                            ((Step4Presenter) Step4Fragment.this.mPresenter).makeFailure(errorCode_Making.errorCode.toString());
                        }
                    }
                }, 10L, TimeUnit.SECONDS);
            }
            Constants.ISMAKINGADRINK = false;
            this.alertDialogMakeing = DiaLogUtils.showAlert(getActivity(), getString(R.string.errohint), getString(R.string.makefail) + "\r\n", getString(R.string.confirm), new DialogInterface.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Step4Fragment.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i3) {
                    if (Constants.needCurrentgrinding) {
                        SerialPortManager serialPortManager2 = MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Currentgrinding();
                        if (serialPortManager2 != null) {
                            serialPortManager2.cleanSendByteToList();
                        }
                        ((Step4Presenter) Step4Fragment.this.mPresenter).standby();
                    }
                    ((Step4Presenter) Step4Fragment.this.mPresenter).makeFailure(errorCode_Making.errorCode.toString());
                }
            });
        }
        if (this.makeSuccessCurrent < 1 || this.makeSuccessInstent < 1 || this.makeSuccessIce < 1) {
            return;
        }
        this.makecesuccess_8 = true;
        makeFinish();
        ((Step4Presenter) this.mPresenter).changeCardReadState(0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ErrorCode_Paymenting errorCode_Paymenting) {
        if (Constants.NowStep != 4) {
            return;
        }
        Tools.upLocalLog("退款过程的错误信息:" + errorCode_Paymenting.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TimeMessage timeMessage) {
        int i;
        if (Constants.NowStep != 4) {
            return;
        }
        if (((Step4Presenter) this.mPresenter).startcurrent) {
            ((Step4Presenter) this.mPresenter).keepCurrent();
        }
        if (this.readAmountCount <= 15 && Constants.payType == 1 && Constants.opsSeting().isCardpaynew()) {
            SerialPortManager serialPortManager = MyAppLocation.myAppLocation.mSerialDataService.getmmSerialPortManager_Payment();
            if (serialPortManager != null) {
                serialPortManager.addSendList(Constants.readAmount_paymentnew());
            }
            this.readAmountCount++;
        }
        if (Constants.JK88ClearCurrent == 1) {
            checkClearCurrent();
            return;
        }
        if (Constants.ISCLEAN_CURRENT) {
            return;
        }
        this.cyclicQueryTimes--;
        if (this.cyclicQueryTimes > 0 && this.checkState) {
            if (this.serialPortManager != null) {
                if (Constants.deviceTypeDetail.getName().contains("JK88") || Constants.deviceTypeDetail.getName().contains("801")) {
                    this.serialPortManager.addSendList(SerialOrderBytes.CheckMacStateNew);
                } else {
                    this.serialPortManager.addSendList(SerialOrderBytes.CheckMacStateOld);
                }
            }
            Tools.upLocalLog("step4机器状态：ErrorCodeNew_Msg.MacState" + ErrorCodeNew_Msg.MacState + "ErrorCodeNew_Msg.ErrorNew" + ErrorCodeNew_Msg.ErrorNew);
            if (ErrorCodeNew_Msg.MacState == ErrorCodeNew.Busy) {
                return;
            }
            if (ErrorCodeNew_Msg.ErrorNew.contains(ErrorCodeNew.A09.name())) {
                if (this.isCupTips) {
                    return;
                }
                this.isCupTips = true;
                if (MyAppLocation.myAppLocation.voicePlayService != null) {
                    MyAppLocation.myAppLocation.voicePlayService.playVoice(5);
                } else {
                    Tools.upLocalLog("step3_MyAppLocation.myAppLocation.voicePlayService_为空");
                }
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant_main(new byte[]{-86, 85, 4, 36, 0, 20, 59});
                return;
            }
            this.checkState = false;
        }
        if (this.cyclicQueryTimes < 0 && this.checkState) {
            Tools.sendArMsg("机器正忙，请稍后制作！");
            makeFail();
            return;
        }
        if (Constants.opsSeting().isClearCurInterval()) {
            Constants.coffeeInterval = Constants.opsSeting().getClearCurIntervalTime();
            if ((Constants.needCurrentgrinding || Constants.needCurrentgrinding_other) && (i = this.clearCurrentNum) <= 0) {
                this.clearCurrentNum = i + 1;
                if (System.currentTimeMillis() - Constants.coffeeTime >= Constants.coffeeInterval * 60 * 1000) {
                    Tools.upLocalLog("上一杯制作时间" + Constants.coffeeTime + "超过设计时间" + Constants.coffeeInterval + "分钟;执行现磨清洗。");
                    Constants.clearCurrentTime = 70;
                    runClearCurrent();
                    return;
                }
                Constants.coffeeTime = System.currentTimeMillis();
            }
        }
        if (!this.isMaking) {
            Tools.upLocalLog("机器状态" + ErrorCodeNew_Msg.MacState + "，开始制作出杯" + ErrorCodeNew_Msg.ErrorNew);
            this.isMaking = true;
            this.getcupsuccess_4 = false;
            this.geticesuccess_5 = false;
            this.makecesuccess_8 = false;
            this.getondrink_3 = false;
            this.istandby_6 = false;
            this.issetWaterAndMetail_2 = false;
            this.setcuptype_1 = false;
            this.setcurrentfinish_7 = false;
            if (!Constants.needIce) {
                this.makeSuccessIce = 1;
            }
            if (!Constants.needCurrentgrinding) {
                this.makeSuccessCurrent++;
            }
            ((Step4Presenter) this.mPresenter).setGetCupType();
        }
        this.nowtime--;
        if (this.nowtime_ - this.nowtime == 5 && Constants.ISMAKINGADRINK && !this.issetWaterAndMetail_2) {
            if (this.setcuptype_1) {
                ((Step4Presenter) this.mPresenter).setWaterAndMetail();
            } else {
                ((Step4Presenter) this.mPresenter).setGetCupType();
            }
        }
        if (this.nowtime_ - this.nowtime == 15 && Constants.ISMAKINGADRINK && !this.getcupsuccess_4 && !this.getondrink_3 && !this.issetWaterAndMetail_2) {
            if (this.setcuptype_1) {
                ((Step4Presenter) this.mPresenter).setWaterAndMetail();
            } else {
                ((Step4Presenter) this.mPresenter).setGetCupType();
            }
        }
        long nowMills = TimeUtils.getNowMills();
        long j = this.getDrinkMills;
        if (nowMills - j < 40000 || j <= 0 || !Constants.ISMAKINGADRINK || this.getcupsuccess_4) {
            return;
        }
        Tools.upLocalLog("40秒后没有检测到落杯，默认落杯失败");
        EventBus.getDefault().post(new ErrorCode_Making(false, ErrorCode_Making.ErrorCode.GETCUP, ErrorCode_Making.ErrorCode.GETCUP.name()));
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
        this.nowtime = (Constants.needCurrentgrinding_times * 60) + 180;
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerStep4Component.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Step4Contract.View
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
