package com.yj.coffeemachines.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.DevConfig;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.serialport.SerialPortManager;
import com.yj.coffeemachines.app.service.SerialOrderBytes;
import com.yj.coffeemachines.app.utils.ByteUtils;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.di.component.DaggerOps5_FeatureTestComponent;
import com.yj.coffeemachines.dialog.DrainWaterDialog;
import com.yj.coffeemachines.dialog.TestDialog;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew_Msg;
import com.yj.coffeemachines.eventbusbean.ErrorCode_Making;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Ops5_FeatureTestContract;
import com.yj.coffeemachines.mvp.message.IMessage;
import com.yj.coffeemachines.mvp.presenter.Ops5_FeatureTestPresenter;
import com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity;
import java.util.concurrent.atomic.AtomicInteger;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class Ops5_FeatureTestFragment extends BaseFragment<Ops5_FeatureTestPresenter> implements Ops5_FeatureTestContract.View {

    @BindView(R.id.btn_make_test)
    Button btnMakeTest;

    @BindView(R.id.btn_start_cycle)
    Button btnStartCycle;

    @BindView(R.id.cycle_layout)
    LinearLayout cycleLayout;
    private DevConfig devConfig;

    @BindView(R.id.ed_cycle_count)
    EditText edCycleCount;

    @BindView(R.id.ed_cycle_pipe_count)
    EditText edCyclePipeCount;

    @BindView(R.id.ed_path_cycle_count)
    EditText edPathCycleCount;

    @BindView(R.id.ed_path_index_count)
    EditText edPathIndexCount;
    boolean isOpenedDoor;

    @BindView(R.id.ll_make_test)
    LinearLayout llMakeTest;

    @BindView(R.id.btn_10s)
    Button mBtn10s;

    @BindView(R.id.btn_1s)
    Button mBtn1s;

    @BindView(R.id.btn_5s)
    Button mBtn5s;

    @BindView(R.id.btn_addwater)
    Button mBtnAddwater;

    @BindView(R.id.btn_clean1)
    Button mBtnClean1;

    @BindView(R.id.btn_clean2)
    Button mBtnClean2;

    @BindView(R.id.btn_clean3)
    Button mBtnClean3;

    @BindView(R.id.btn_close)
    Button mBtnClose;

    @BindView(R.id.btn_drain_water)
    Button mBtnDrainWater;

    @BindView(R.id.btn_drool1)
    Button mBtnDrool1;

    @BindView(R.id.btn_drool2)
    Button mBtnDrool2;

    @BindView(R.id.btn_drool3)
    Button mBtnDrool3;

    @BindView(R.id.btn_drool4)
    Button mBtnDrool4;

    @BindView(R.id.btn_drool5)
    Button mBtnDrool5;

    @BindView(R.id.btn_hideavigationbar)
    Button mBtnHideavigationbar;

    @BindView(R.id.btn_ice100)
    Button mBtnIce100;

    @BindView(R.id.btn_ice150)
    Button mBtnIce150;

    @BindView(R.id.btn_ice50)
    Button mBtnIce50;

    @BindView(R.id.btn_icepush)
    Button mBtnIcepush;

    @BindView(R.id.btn_open)
    Button mBtnOpen;

    @BindView(R.id.btn_reback)
    Button mBtnReback;

    @BindView(R.id.btn_send)
    Button mBtnSend;

    @BindView(R.id.btn_send_data)
    Button mBtnSendData;

    @BindView(R.id.btn_shownavigationbar)
    Button mBtnShownavigationbar;

    @BindView(R.id.btn_state1)
    Button mBtnState1;

    @BindView(R.id.btn_state2)
    Button mBtnState2;

    @BindView(R.id.btn_state3)
    Button mBtnState3;

    @BindView(R.id.btn_systemseting)
    Button mBtnSystemseting;

    @BindView(R.id.clean_layout)
    LinearLayout mCleanLayout;

    @BindView(R.id.clock_layout)
    LinearLayout mClockLayout;

    @BindView(R.id.devstate_layout)
    LinearLayout mDevstateLayout;

    @BindView(R.id.drool_layout)
    LinearLayout mDroolLayout;

    @BindView(R.id.ed_gallery)
    EditText mEdGallery;

    @BindView(R.id.et_data)
    EditText mEtData;

    @BindView(R.id.getwater_layout)
    LinearLayout mGetwaterLayout;

    @BindView(R.id.ice_layout)
    LinearLayout mIceLayout;
    private LogFragment mLogFragment;

    @BindView(R.id.path_layout)
    LinearLayout mPathLayout;

    @BindView(R.id.rb_coldwater)
    RadioButton mRbColdwater;

    @BindView(R.id.rb_flou)
    RadioButton mRbFlou;

    @BindView(R.id.rb_hotwater)
    RadioButton mRbHotwater;
    private String[] mSeriaport;

    @BindView(R.id.seriaportsend_layout)
    LinearLayout mSeriaportsendLayout;

    @BindView(R.id.spinner_index)
    EditText mSpinnerIndex;

    @BindView(R.id.spinner_seriaport)
    Spinner mSpinnerSeriaport;

    @BindView(R.id.waterorflour_layout)
    LinearLayout mWaterorflourLayout;

    @BindView(R.id.path_cycle_layout)
    LinearLayout pathCycleLayout;

    @BindView(R.id.rg_cycle_object)
    RadioGroup rgCycleObject;

    @BindView(R.id.rg_cycle_time)
    RadioGroup rgCycleTime;
    private TestDialog testDialog;
    private Thread thread;
    private int cycleObject = 1;
    private int cycleTime = 1;
    private int cyclePipeCount = 0;
    private int cycleCount = 0;
    private int pathIndex = 0;
    private long lastClickMills = 0;
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private int cyclicQueryTimes = 30;
    private boolean checkState = true;
    SerialPortManager serialPortManager = null;
    boolean isMaking = false;
    int clearCurrentNum = 0;
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
    private int nowtime = (Constants.needCurrentgrinding_times * 60) + 180;
    private int nowtime_ = (Constants.needCurrentgrinding_times * 60) + 180;
    private boolean isStartTest = false;
    int clear = 0;
    int check = 5;

    @SuppressLint({"StringFormatMatches"})
    private String buildPathTestingTips(int i, int i2, int i3) {
        return String.format(getString(R.string.testing_path_tip), Integer.valueOf(i), Integer.valueOf(i2));
    }

    private String buildTestCompleteTips(int i) {
        return String.format(getString(R.string.test_complete_tip), Integer.valueOf(i));
    }

    private String buildTestingTips(int i, int i2, int i3) {
        return String.format(getString(R.string.testing_tips), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
    }

    private void checkClearCurrent() {
        Constants.clearCurrentTime--;
        this.check--;
        if (this.check >= 0) {
            return;
        }
        if (Constants.clearCurrentTime - 5 <= 0) {
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

    private void cycleMaterialTest() {
        String obj = this.edCycleCount.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            showMessage(getString(R.string.input_test_count_hint));
            return;
        }
        try {
            this.cycleCount = Integer.parseInt(obj);
            if (this.cycleCount <= 0) {
                showMessage(getString(R.string.input_ok_test_count_hint));
                return;
            }
            String obj2 = this.edCyclePipeCount.getText().toString();
            if (TextUtils.isEmpty(obj2)) {
                showMessage(getString(R.string.input_pipe_count_hint));
                return;
            }
            try {
                this.cyclePipeCount = Integer.parseInt(obj2);
                if (this.cyclePipeCount <= 0) {
                    showMessage(getString(R.string.input_ok_pipe_count_hint));
                    return;
                }
                Tools.upLocalLog("----------------出水出粉周期测试开始----------------");
                getTestThread();
                this.testDialog = new TestDialog(requireContext());
                this.testDialog.show();
                this.testDialog.showClose(false);
                this.testDialog.setTitle(getString(R.string.cycle_material_test));
                ((SetingsHomeActivity) getActivity()).stopCountTime();
                this.testDialog.setOnStopTestListener(new TestDialog.OnStopTestListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops5_FeatureTestFragment$R0XadmeWx9kE63qoq5sNv8lRgs8
                    @Override // com.yj.coffeemachines.dialog.TestDialog.OnStopTestListener
                    public final void onStopTest() {
                        Ops5_FeatureTestFragment.this.lambda$cycleMaterialTest$6$Ops5_FeatureTestFragment();
                    }
                });
                this.testDialog.setTips(buildTestingTips(this.cycleCount, 1, 1));
            } catch (Exception unused) {
                showMessage(getString(R.string.input_ok_pipe_count_hint));
            }
        } catch (Exception unused2) {
            showMessage(getString(R.string.input_ok_test_count_hint));
        }
    }

    private void cyclePathTest() {
        String obj = this.edPathCycleCount.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            showMessage(getString(R.string.input_test_count_hint));
            return;
        }
        try {
            this.cycleCount = Integer.parseInt(obj);
            if (this.cycleCount <= 0) {
                showMessage(getString(R.string.input_ok_test_count_hint));
                return;
            }
            String obj2 = this.edPathIndexCount.getText().toString();
            if (TextUtils.isEmpty(obj2)) {
                showMessage(getString(R.string.input_path_index_hint));
                return;
            }
            try {
                this.pathIndex = Integer.parseInt(obj2);
                if (this.pathIndex <= 0) {
                    showMessage(getString(R.string.input_ok_path_index_hint));
                    return;
                }
                Tools.upLocalLog("----------------轨道周期测试开始----------------");
                getPathTestThread();
                this.testDialog = new TestDialog(requireContext());
                this.testDialog.show();
                this.testDialog.showClose(false);
                this.testDialog.setTitle(getString(R.string.cycle_path_test));
                ((SetingsHomeActivity) getActivity()).stopCountTime();
                this.testDialog.setOnStopTestListener(new TestDialog.OnStopTestListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops5_FeatureTestFragment$YaaN69xPtIfp6kfk4V9-6GWVPQQ
                    @Override // com.yj.coffeemachines.dialog.TestDialog.OnStopTestListener
                    public final void onStopTest() {
                        Ops5_FeatureTestFragment.this.lambda$cyclePathTest$2$Ops5_FeatureTestFragment();
                    }
                });
                this.testDialog.setTips(buildPathTestingTips(this.cycleCount, 1, 1));
            } catch (Exception unused) {
                showMessage(getString(R.string.input_ok_path_index_hint));
            }
        } catch (Exception unused2) {
            showMessage(getString(R.string.input_ok_test_count_hint));
        }
    }

    private void getPathTestThread() {
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        this.thread = new Thread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops5_FeatureTestFragment$33yz8tsvbYT9it78diwzyPDE14I
            @Override // java.lang.Runnable
            public final void run() {
                Ops5_FeatureTestFragment.this.lambda$getPathTestThread$5$Ops5_FeatureTestFragment(atomicInteger);
            }
        });
        this.thread.start();
    }

    private void getTestThread() {
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        this.thread = new Thread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops5_FeatureTestFragment$qlC3DiSoj1t16xeEyA2DXll_GBg
            @Override // java.lang.Runnable
            public final void run() {
                Ops5_FeatureTestFragment.this.lambda$getTestThread$9$Ops5_FeatureTestFragment(atomicInteger);
            }
        });
        this.thread.start();
    }

    private void goSystemSetting() {
        Intent intent = new Intent("android.settings.SETTINGS");
        intent.putExtra("extra_prefs_show_button_bar", true);
        intent.putExtra("extra_prefs_set_next_text", "完成");
        intent.putExtra("extra_prefs_set_back_text", "返回");
        startActivity(intent);
    }

    private void initFragment() {
        this.mLogFragment = (LogFragment) getChildFragmentManager().findFragmentById(R.id.log_fragment);
    }

    private void initSpinners() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_default_item, this.mSeriaport);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        this.mSpinnerSeriaport.setAdapter((SpinnerAdapter) arrayAdapter);
    }

    private void initVisiti() {
        this.mBtnClean2.setVisibility(Constants.showCurrentgrinding() ? 0 : 8);
        this.mBtnClean3.setVisibility(Constants.showCurrentgrinding() ? 0 : 8);
        this.devConfig = Constants.devConfig();
        DevConfig devConfig = this.devConfig;
        if (devConfig != null) {
            this.mCleanLayout.setVisibility(devConfig.isLeft1() ? 0 : 8);
            this.mPathLayout.setVisibility(this.devConfig.isLeft3() ? 0 : 8);
            this.pathCycleLayout.setVisibility(this.devConfig.isLeft3() ? 0 : 8);
            this.mBtnDrool1.setVisibility(this.devConfig.isLeft4() ? 0 : 8);
            this.mBtnDrool2.setVisibility(this.devConfig.isLeft7() ? 0 : 8);
            this.mBtnDrool3.setVisibility(this.devConfig.isLeft7() ? 0 : 8);
            this.mGetwaterLayout.setVisibility(this.devConfig.isRight1() ? 0 : 8);
            this.mSeriaportsendLayout.setVisibility(this.devConfig.isRight3() ? 0 : 8);
            this.mBtnClean2.setVisibility(this.devConfig.isCmd27() ? 0 : 8);
            this.mBtnClean3.setVisibility(this.devConfig.isCmd27() ? 0 : 8);
            boolean isRight2 = this.devConfig.isRight2();
            if (isRight2) {
                this.mWaterorflourLayout.setVisibility(this.devConfig.isLeft2() ? 0 : 8);
                this.cycleLayout.setVisibility(this.devConfig.isLeft2() ? 0 : 8);
                this.mIceLayout.setVisibility(this.devConfig.isLeft5() ? 0 : 8);
                this.mClockLayout.setVisibility(this.devConfig.isLeft6() ? 0 : 8);
                this.mBtnDrool2.setVisibility((isRight2 && this.devConfig.isLeft7()) ? 0 : 8);
                this.mBtnDrool3.setVisibility(isRight2 ? 0 : 8);
                this.mBtnDrool4.setVisibility(isRight2 ? 0 : 8);
                this.mBtnDrool5.setVisibility(isRight2 ? 0 : 8);
            } else {
                this.mWaterorflourLayout.setVisibility(8);
                this.cycleLayout.setVisibility(8);
                this.mIceLayout.setVisibility(8);
                this.mClockLayout.setVisibility(8);
                this.mBtnDrool2.setVisibility(8);
                this.mBtnDrool3.setVisibility(8);
                this.mBtnDrool4.setVisibility(8);
                this.mBtnDrool5.setVisibility(8);
            }
            this.mDevstateLayout.setVisibility(this.devConfig.isRight5() ? 0 : 8);
        }
        this.pathCycleLayout.setVisibility(0);
    }

    private void makeFail() {
        this.isStartTest = false;
        Tools.upLocalLog("制作失败");
        ArmsUtils.snackbarText("制作失败");
    }

    private void makeTest() {
        if (Constants.ISMAKINGADRINK) {
            ArmsUtils.snackbarText(getString(R.string.currently));
            return;
        }
        ArmsUtils.snackbarText(getString(R.string.started_making));
        Constants.ISMAKINGADRINK = true;
        this.cyclicQueryTimes = 30;
        this.checkState = true;
        this.isMaking = false;
        this.clearCurrentNum = 0;
        this.setcuptype_1 = false;
        this.issetWaterAndMetail_2 = false;
        this.getondrink_3 = false;
        this.getcupsuccess_4 = false;
        this.geticesuccess_5 = false;
        this.istandby_6 = false;
        this.setcurrentfinish_7 = false;
        this.makecesuccess_8 = false;
        this.makeSuccessInstent = 0;
        this.makeSuccessCurrent = 0;
        this.makeSuccessIce = 0;
        this.iSETWATERANDMATERA = 2;
        this.nowtime = (Constants.needCurrentgrinding_times * 60) + 180;
        this.nowtime_ = (Constants.needCurrentgrinding_times * 60) + 180;
        this.isStartTest = true;
    }

    public static Ops5_FeatureTestFragment newInstance() {
        return new Ops5_FeatureTestFragment();
    }

    private void refreshLogList() {
        this.mLogFragment.updateAutoEndButton();
        this.mLogFragment.updateList();
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

    private void sendpathreback() {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 2, 0, 0, 0));
    }

    private void sendwaterorpink(int i) {
        int i2 = this.mRbFlou.isChecked() ? 1 : this.mRbColdwater.isChecked() ? 3 : this.mRbHotwater.isChecked() ? 2 : 0;
        String obj = this.mEdGallery.getText().toString();
        if (obj.isEmpty()) {
            ArmsUtils.snackbarText(getString(R.string.index_gallery));
        } else {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 1, i2, Integer.parseInt(obj), i * 10));
        }
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void hideLoading() {
        IView.CC.$default$hideLoading(this);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        this.serialPortManager = MyAppLocation.myAppLocation.mSerialDataService.getmSerialPortManager_Instant();
        this.mSeriaport = getResources().getStringArray(R.array.seriaport);
        initFragment();
        initSpinners();
        initVisiti();
        if (Constants.deviceTypeDetail.getName().contains("JK88")) {
            this.mBtnDrainWater.setVisibility(0);
        } else {
            this.mBtnDrainWater.setVisibility(8);
        }
        this.rgCycleObject.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops5_FeatureTestFragment$HbzkQHh8AzpualgCg7aVFpcF7Dw
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                Ops5_FeatureTestFragment.this.lambda$initData$0$Ops5_FeatureTestFragment(radioGroup, i);
            }
        });
        this.rgCycleTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops5_FeatureTestFragment$PVRgriqQTYsyuSkatIwmewHXxMU
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                Ops5_FeatureTestFragment.this.lambda$initData$1$Ops5_FeatureTestFragment(radioGroup, i);
            }
        });
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ops5_featuretest, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void killMyself() {
        IView.CC.$default$killMyself(this);
    }

    public /* synthetic */ void lambda$cycleMaterialTest$6$Ops5_FeatureTestFragment() {
        this.cycleCount = 0;
        this.thread.interrupt();
        ((SetingsHomeActivity) getActivity()).startCountTime();
    }

    public /* synthetic */ void lambda$cyclePathTest$2$Ops5_FeatureTestFragment() {
        this.cycleCount = 0;
        this.thread.interrupt();
        ((SetingsHomeActivity) getActivity()).startCountTime();
    }

    public /* synthetic */ void lambda$getPathTestThread$5$Ops5_FeatureTestFragment(final AtomicInteger atomicInteger) {
        while (this.cycleCount >= atomicInteger.get()) {
            Tools.upLocalLog("----------------轨道周期测试第" + atomicInteger + "次----------------");
            for (final int i = 1; i <= this.pathIndex; i++) {
                try {
                    MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 2, i - 1, 0, 0));
                    this.mainHandler.post(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops5_FeatureTestFragment$BKzkEtMRW-5k7XVQaksxfx3BmaI
                        @Override // java.lang.Runnable
                        public final void run() {
                            Ops5_FeatureTestFragment.this.lambda$null$3$Ops5_FeatureTestFragment(atomicInteger, i);
                        }
                    });
                    Thread.sleep((this.cycleTime * 1000) + 6000);
                } catch (InterruptedException e) {
                    Tools.upLocalLog("轨道周期测试出现问题：" + e.getMessage());
                }
            }
            atomicInteger.getAndIncrement();
            Thread.sleep((this.cycleTime * 1000) + 6000);
        }
        Tools.upLocalLog("----------------轨道周期测试结束----------------");
        this.mainHandler.post(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops5_FeatureTestFragment$wQrG8z5Pp7cvsV8Kxggz_Yj1YXY
            @Override // java.lang.Runnable
            public final void run() {
                Ops5_FeatureTestFragment.this.lambda$null$4$Ops5_FeatureTestFragment();
            }
        });
    }

    public /* synthetic */ void lambda$getTestThread$9$Ops5_FeatureTestFragment(final AtomicInteger atomicInteger) {
        while (this.cycleCount >= atomicInteger.get()) {
            Tools.upLocalLog("----------------出水出粉周期测试第" + atomicInteger + "次----------------");
            for (final int i = 1; i <= this.cyclePipeCount; i++) {
                try {
                    MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 1, this.cycleObject, i, this.cycleTime * 10));
                    this.mainHandler.post(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops5_FeatureTestFragment$TWfTzyKJa0xxQtvFp-g-vXLABBo
                        @Override // java.lang.Runnable
                        public final void run() {
                            Ops5_FeatureTestFragment.this.lambda$null$7$Ops5_FeatureTestFragment(atomicInteger, i);
                        }
                    });
                    Thread.sleep((this.cycleTime * 1000) + 6000);
                } catch (InterruptedException e) {
                    Tools.upLocalLog("出水出粉周期测试出现问题：" + e.getMessage());
                }
            }
            atomicInteger.getAndIncrement();
            Thread.sleep((this.cycleTime * 1000) + 6000);
        }
        Tools.upLocalLog("----------------出水出粉周期测试结束----------------");
        this.mainHandler.post(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops5_FeatureTestFragment$unKWGY_Nh1gj9jC25-Jn47dJT3s
            @Override // java.lang.Runnable
            public final void run() {
                Ops5_FeatureTestFragment.this.lambda$null$8$Ops5_FeatureTestFragment();
            }
        });
    }

    public /* synthetic */ void lambda$initData$0$Ops5_FeatureTestFragment(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_cycle_coldwater /* 2131296718 */:
                this.cycleObject = 3;
                return;
            case R.id.rb_cycle_five /* 2131296719 */:
            default:
                return;
            case R.id.rb_cycle_flou /* 2131296720 */:
                this.cycleObject = 1;
                return;
            case R.id.rb_cycle_hotwater /* 2131296721 */:
                this.cycleObject = 2;
                return;
        }
    }

    public /* synthetic */ void lambda$initData$1$Ops5_FeatureTestFragment(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_cycle_five /* 2131296719 */:
                this.cycleTime = 5;
                return;
            case R.id.rb_cycle_flou /* 2131296720 */:
            case R.id.rb_cycle_hotwater /* 2131296721 */:
            default:
                return;
            case R.id.rb_cycle_one /* 2131296722 */:
                this.cycleTime = 1;
                return;
            case R.id.rb_cycle_ten /* 2131296723 */:
                this.cycleTime = 10;
                return;
        }
    }

    public /* synthetic */ void lambda$null$3$Ops5_FeatureTestFragment(AtomicInteger atomicInteger, int i) {
        if (this.testDialog != null) {
            this.testDialog.setTips(buildPathTestingTips(this.cycleCount, atomicInteger.get(), i));
        }
    }

    public /* synthetic */ void lambda$null$4$Ops5_FeatureTestFragment() {
        if (this.testDialog != null) {
            this.testDialog.setTips(buildTestCompleteTips(this.cycleCount));
        }
    }

    public /* synthetic */ void lambda$null$7$Ops5_FeatureTestFragment(AtomicInteger atomicInteger, int i) {
        if (this.testDialog != null) {
            this.testDialog.setTips(buildTestingTips(this.cycleCount, atomicInteger.get(), i));
        }
    }

    public /* synthetic */ void lambda$null$8$Ops5_FeatureTestFragment() {
        if (this.testDialog != null) {
            this.testDialog.setTips(buildTestCompleteTips(this.cycleCount));
        }
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @OnClick({R.id.btn_clean1, R.id.btn_clean2, R.id.btn_clean3, R.id.btn_1s, R.id.btn_5s, R.id.btn_10s, R.id.btn_reback, R.id.btn_drool1, R.id.btn_drool2, R.id.btn_drool3, R.id.btn_drool4, R.id.btn_drool5, R.id.btn_ice50, R.id.btn_ice100, R.id.btn_ice150, R.id.btn_icepush, R.id.btn_open, R.id.btn_close, R.id.btn_addwater, R.id.btn_systemseting, R.id.btn_state1, R.id.btn_state2, R.id.btn_state3, R.id.btn_send_data, R.id.btn_send, R.id.btn_make_test, R.id.btn_drain_water, R.id.btn_start_cycle, R.id.btn_start_path})
    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastClickMills < 1000) {
            return;
        }
        this.lastClickMills = currentTimeMillis;
        switch (view.getId()) {
            case R.id.btn_10s /* 2131296345 */:
                sendwaterorpink(10);
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.ops5_msg2) + "" + getString(R.string.ten_s), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_1s /* 2131296346 */:
                sendwaterorpink(1);
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.ops5_msg2) + "" + getString(R.string.one_s), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_5s /* 2131296347 */:
                sendwaterorpink(5);
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.ops5_msg2) + "" + getString(R.string.five_s), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_add /* 2131296348 */:
            case R.id.btn_ai /* 2131296350 */:
            case R.id.btn_auto_end /* 2131296351 */:
            case R.id.btn_back /* 2131296352 */:
            case R.id.btn_cancel /* 2131296353 */:
            case R.id.btn_clear_log /* 2131296357 */:
            case R.id.btn_confirm /* 2131296359 */:
            case R.id.btn_doubleactivity /* 2131296360 */:
            case R.id.btn_exitapp /* 2131296367 */:
            case R.id.btn_hideavigationbar /* 2131296368 */:
            case R.id.btn_hint /* 2131296369 */:
            case R.id.btn_language /* 2131296374 */:
            case R.id.btn_login /* 2131296375 */:
            case R.id.btn_ok /* 2131296377 */:
            case R.id.btn_query /* 2131296379 */:
            case R.id.btn_show /* 2131296383 */:
            case R.id.btn_shownavigationbar /* 2131296384 */:
            case R.id.btn_state2 /* 2131296388 */:
            case R.id.btn_state3 /* 2131296389 */:
            default:
                return;
            case R.id.btn_addwater /* 2131296349 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(SerialOrderBytes.DrawWater);
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd26), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_clean1 /* 2131296354 */:
                MyAppLocation.myAppLocation.mSerialDataService.cleanPipe(1);
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd14), DataUtils.currentTime(), 2);
                return;
            case R.id.btn_clean2 /* 2131296355 */:
                MyAppLocation.myAppLocation.myMqttService.cleanGround(0);
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd15), DataUtils.currentTime(), 2);
                this.mBtnClean2.setVisibility(0);
                return;
            case R.id.btn_clean3 /* 2131296356 */:
                if (Constants.deviceTypeDetail.getName().contains("JK88")) {
                    MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(SerialOrderBytes.FreshlyGroundPaiKong);
                } else {
                    MyAppLocation.myAppLocation.myMqttService.emptying();
                }
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd16), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_close /* 2131296358 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_LOCK, 1, 1));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd25), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_drain_water /* 2131296361 */:
                final DrainWaterDialog drainWaterDialog = new DrainWaterDialog(requireContext());
                drainWaterDialog.setListener(new DrainWaterDialog.OnSecretClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops5_FeatureTestFragment.1
                    @Override // com.yj.coffeemachines.dialog.DrainWaterDialog.OnSecretClickListener
                    public void onClick() {
                        ((SetingsHomeActivity) Ops5_FeatureTestFragment.this.requireActivity()).startCountTime();
                        drainWaterDialog.dismiss();
                    }
                });
                ((SetingsHomeActivity) requireActivity()).stopCountTime();
                drainWaterDialog.show();
                return;
            case R.id.btn_drool1 /* 2131296362 */:
                DevConfig devConfig = this.devConfig;
                if (devConfig != null) {
                    if (devConfig.isRight2()) {
                        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 3, 1, 1, 0));
                    } else {
                        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_CheckMachineCupFalling, new int[0]));
                    }
                }
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd18), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_drool2 /* 2131296363 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 3, 2, 1, 0));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd19), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_drool3 /* 2131296364 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 3, 3, 0, 0));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd20), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_drool4 /* 2131296365 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 3, 4, 0, 0));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd21), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_drool5 /* 2131296366 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 3, 4, 1, 0));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd22), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_ice100 /* 2131296370 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 4, 1, 100, 0));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string._100g), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_ice150 /* 2131296371 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 4, 1, 150, 0));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string._150g), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_ice50 /* 2131296372 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 4, 1, 50, 0));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string._50g), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_icepush /* 2131296373 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 4, 2, 0, 0));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.push_ice), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_make_test /* 2131296376 */:
                makeTest();
                return;
            case R.id.btn_open /* 2131296378 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_LOCK, 1, 0));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd24), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_reback /* 2131296380 */:
                sendpathreback();
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.gd_reback), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_send /* 2131296381 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 2, Tools.toInt(this.mSpinnerIndex.getText().toString(), 1) - 1, 0, 0));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.coordinateTest), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_send_data /* 2131296382 */:
                onSend();
                return;
            case R.id.btn_start_cycle /* 2131296385 */:
                Thread thread = this.thread;
                if (thread != null && thread.isAlive()) {
                    this.cycleCount = 0;
                    this.thread.interrupt();
                }
                cycleMaterialTest();
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cycle_material_test) + ":" + this.cycleCount, DataUtils.currentTime(), 3);
                return;
            case R.id.btn_start_path /* 2131296386 */:
                Thread thread2 = this.thread;
                if (thread2 != null && thread2.isAlive()) {
                    this.cycleCount = 0;
                    this.thread.interrupt();
                }
                cyclePathTest();
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cycle_path_test) + ":" + this.cycleCount, DataUtils.currentTime(), 3);
                return;
            case R.id.btn_state1 /* 2131296387 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_ReadMachineStatus, new int[0]));
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.devicestatus), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_systemseting /* 2131296390 */:
                goSystemSetting();
                return;
        }
    }

    @Override // com.jess.arms.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Thread thread = this.thread;
        if (thread != null) {
            this.cycleCount = 0;
            thread.interrupt();
        }
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ErrorCode_Making errorCode_Making) {
        int i;
        String str = errorCode_Making.message;
        if (errorCode_Making.isOK) {
            if (str.equals(ErrorCode_Making.ErrorCode.CUPTYPE.name())) {
                if (this.setcuptype_1) {
                    return;
                }
                this.setcuptype_1 = true;
                ((Ops5_FeatureTestPresenter) this.mPresenter).setWaterAndMetail();
            } else if (str.equals(ErrorCode_Making.ErrorCode.SETWATERANDMATERA.name())) {
                if (this.issetWaterAndMetail_2) {
                    return;
                }
                this.issetWaterAndMetail_2 = true;
                ((Ops5_FeatureTestPresenter) this.mPresenter).getDrink();
                Constants.MacMakingState = 1;
            } else if (str.equals(ErrorCode_Making.ErrorCode.GETDRINK.name())) {
                if (this.getondrink_3) {
                    return;
                }
                this.getondrink_3 = true;
                this.isStartTest = false;
            } else if (str.equals(ErrorCode_Making.ErrorCode.GETCUP.name())) {
                if (this.getcupsuccess_4) {
                    return;
                }
                this.getcupsuccess_4 = true;
                int i2 = Constants.buy_type;
                if (!Constants.needIce) {
                    ((Ops5_FeatureTestPresenter) this.mPresenter).sendFreshGroundCoffeeFinished();
                }
            } else if (str.equals(ErrorCode_Making.ErrorCode.FRESHGROUNDCOFFEEFINISHED.name())) {
                if (this.setcurrentfinish_7) {
                    return;
                }
                this.setcurrentfinish_7 = true;
                Constants.ISMAKINGADRINK = false;
            } else if (str.equals(ErrorCode_Making.ErrorCode.GETICE.name())) {
                if (this.geticesuccess_5) {
                    return;
                }
                this.makeSuccessIce++;
                this.geticesuccess_5 = true;
                if (Constants.needIce) {
                    ((Ops5_FeatureTestPresenter) this.mPresenter).sendFreshGroundCoffeeFinished();
                }
            } else if (str.equals(ErrorCode_Making.ErrorCode.FINISH.name())) {
                Constants.MacMakingState = 2;
                if (this.makecesuccess_8) {
                    return;
                } else {
                    this.makeSuccessInstent++;
                }
            } else if (str.equals(ErrorCode_Making.ErrorCode.CURRENTFINISH.name())) {
                if (System.currentTimeMillis() - ((Ops5_FeatureTestPresenter) this.mPresenter).currentTimeMillis <= 3000 || !((Ops5_FeatureTestPresenter) this.mPresenter).startcurrent) {
                    ((Ops5_FeatureTestPresenter) this.mPresenter).keepCurrent();
                } else if (Constants.needCurrentgrinding_times <= 0) {
                    if (this.istandby_6) {
                        return;
                    }
                    this.istandby_6 = true;
                    ((Ops5_FeatureTestPresenter) this.mPresenter).sendFreshGroundCoffeeFinished();
                    ((Ops5_FeatureTestPresenter) this.mPresenter).standby();
                    ((Ops5_FeatureTestPresenter) this.mPresenter).startcurrent = false;
                    this.makeSuccessCurrent++;
                }
            } else if (str.equals(ErrorCode_Making.ErrorCode.CURRENTFINISHING.name())) {
                ((Ops5_FeatureTestPresenter) this.mPresenter).keepCurrent();
            }
        } else {
            if (errorCode_Making.errorCode == ErrorCode_Making.ErrorCode.SETWATERANDMATERA && (i = this.iSETWATERANDMATERA) >= 1) {
                this.iSETWATERANDMATERA = i - 1;
                ((Ops5_FeatureTestPresenter) this.mPresenter).setWaterAndMetail();
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
                ArmsUtils.snackbarText(getString(R.string.test_cup_fail));
            } else {
                ArmsUtils.snackbarText(getString(R.string.test_make_error));
            }
            Constants.ISMAKINGADRINK = false;
            ((Ops5_FeatureTestPresenter) this.mPresenter).standby();
        }
        if (this.makeSuccessCurrent < 1 || this.makeSuccessInstent < 1 || this.makeSuccessIce < 1) {
            return;
        }
        this.makecesuccess_8 = true;
        ArmsUtils.snackbarText("制作完成！");
        Constants.ISMAKINGADRINK = false;
        this.isStartTest = false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TimeMessage timeMessage) {
        int i;
        if (this.isStartTest) {
            if (Constants.JK88ClearCurrent == 1) {
                checkClearCurrent();
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
                if (ErrorCodeNew_Msg.MacState == ErrorCodeNew.Busy || ErrorCodeNew_Msg.ErrorNew.contains(ErrorCodeNew.A09.name())) {
                    return;
                } else {
                    this.checkState = false;
                }
            }
            if (this.cyclicQueryTimes < 0 && this.checkState) {
                Tools.sendArMsg(getString(R.string.test_machine_busy));
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
            if (Constants.ISCLEAN_CURRENT) {
                return;
            }
            if (!this.checkState && !this.isMaking) {
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
                ((Ops5_FeatureTestPresenter) this.mPresenter).setGetCupType();
            }
            if (this.nowtime % 10 == 0) {
                Tools.upLocalLog("订单制作倒计时" + this.nowtime);
            }
            this.nowtime--;
            if (this.nowtime_ - this.nowtime == 5 && Constants.ISMAKINGADRINK && !this.issetWaterAndMetail_2) {
                if (this.setcuptype_1) {
                    ((Ops5_FeatureTestPresenter) this.mPresenter).setWaterAndMetail();
                } else {
                    ((Ops5_FeatureTestPresenter) this.mPresenter).setGetCupType();
                }
            }
            if (this.nowtime_ - this.nowtime == 15 && Constants.ISMAKINGADRINK && !this.getcupsuccess_4 && !this.getondrink_3 && !this.issetWaterAndMetail_2) {
                if (this.setcuptype_1) {
                    ((Ops5_FeatureTestPresenter) this.mPresenter).setWaterAndMetail();
                } else {
                    ((Ops5_FeatureTestPresenter) this.mPresenter).setGetCupType();
                }
            }
            if (this.nowtime_ - this.nowtime == 40 && Constants.ISMAKINGADRINK && !this.getcupsuccess_4) {
                Tools.upLocalLog("40秒后没有检测到落杯，默认落杯失败");
                EventBus.getDefault().post(new ErrorCode_Making(false, ErrorCode_Making.ErrorCode.GETCUP, ErrorCode_Making.ErrorCode.GETCUP.name()));
            }
            if (this.nowtime == 0) {
                Tools.upLocalLog("订单制作超时");
                ArmsUtils.snackbarText(getString(R.string.timeouted));
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(IMessage iMessage) {
        this.mLogFragment.add(iMessage);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.isOpenedDoor) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 3, 4, 1, 0));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        refreshLogList();
    }

    public void onSend() {
        String trim = this.mEtData.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ArmsUtils.snackbarText("onSend:  null");
            return;
        }
        if (trim.length() % 2 != 0) {
            ArmsUtils.snackbarText("onSend: 命令格式错误，长度为奇数位，需要补0");
            return;
        }
        byte[] hexTobytes = ByteUtils.hexTobytes(trim);
        String obj = this.mSpinnerSeriaport.getSelectedItem().toString();
        if (obj.equals(this.mSeriaport[0])) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(hexTobytes);
        } else if (obj.equals(this.mSeriaport[1])) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(hexTobytes);
        } else if (obj.equals(this.mSeriaport[2])) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Currentgrinding(hexTobytes);
        }
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerOps5_FeatureTestComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
