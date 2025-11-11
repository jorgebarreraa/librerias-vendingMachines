package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.hjq.language.MultiLanguages;
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
import com.yj.coffeemachines.app.service.MyMqttService;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.bean.LanguageItem;
import com.yj.coffeemachines.di.component.DaggerFactory1_DevConfigComponent;
import com.yj.coffeemachines.dialog.SetLanguageDialog;
import com.yj.coffeemachines.eventbusbean.LockMessage;
import com.yj.coffeemachines.helper.LanguageHelper;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Factory1_DevConfigContract;
import com.yj.coffeemachines.mvp.presenter.Factory1_DevConfigPresenter;
import com.yj.coffeemachines.mvp.ui.activity.HomeActivity;
import java.util.Calendar;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class Factory1_DevConfigFragment extends BaseFragment<Factory1_DevConfigPresenter> implements Factory1_DevConfigContract.View {

    @BindView(R.id.btn_language)
    Button btnLanguage;
    private DevConfig devConfig;

    @BindView(R.id.et_clearCurInterval)
    EditText et_clearCurInterval;

    @BindView(R.id.group_pay)
    RadioGroup group_pay;

    @BindView(R.id.parent_timegroup)
    LinearLayout mParentTimegroup;

    @BindView(R.id.swith_cardpay)
    Switch mSwithCardpay;

    @BindView(R.id.swith_cash)
    Switch mSwithCash;

    @BindView(R.id.swith_ischange)
    Switch mSwithIschange;

    @BindView(R.id.swith_order1)
    Switch mSwithOrder1;

    @BindView(R.id.swith_order1_l)
    Switch mSwithOrder1L;

    @BindView(R.id.swith_order2)
    Switch mSwithOrder2;

    @BindView(R.id.swith_order27)
    Switch mSwithOrder27;

    @BindView(R.id.swith_order2_l)
    Switch mSwithOrder2L;

    @BindView(R.id.swith_order2_l_ice)
    Switch mSwithOrder2LIce;

    @BindView(R.id.swith_order3)
    Switch mSwithOrder3;

    @BindView(R.id.swith_order3_l)
    Switch mSwithOrder3L;

    @BindView(R.id.swith_order4)
    Switch mSwithOrder4;

    @BindView(R.id.swith_order4_l)
    Switch mSwithOrder4L;

    @BindView(R.id.swith_order5)
    Switch mSwithOrder5;

    @BindView(R.id.swith_order5_l)
    Switch mSwithOrder5L;

    @BindView(R.id.swith_order6)
    Switch mSwithOrder6;

    @BindView(R.id.swith_order6_l)
    Switch mSwithOrder6L;

    @BindView(R.id.swith_order7)
    Switch mSwithOrder7;

    @BindView(R.id.swith_order7_l)
    Switch mSwithOrder7L;

    @BindView(R.id.swith_order8_l)
    Switch mSwithOrder8L;

    @BindView(R.id.timestart)
    Button mTimestart;

    @BindView(R.id.timestop)
    Button mTimestop;

    @BindView(R.id.tv_cardpay)
    TextView mTvCardpay;

    @BindView(R.id.tv_cardpay_en)
    TextView mTvCardpayEn;

    @BindView(R.id.tv_cash)
    TextView mTvCash;

    @BindView(R.id.tv_cash_en)
    TextView mTvCashEn;

    @BindView(R.id.tv_ischange)
    TextView mTvIschange;

    @BindView(R.id.tv_ischange_en)
    TextView mTvIschangeEn;

    @BindView(R.id.tv_order1)
    TextView mTvOrder1;

    @BindView(R.id.tv_order1_en)
    TextView mTvOrder1En;

    @BindView(R.id.tv_order1_en_l)
    TextView mTvOrder1EnL;

    @BindView(R.id.tv_order1_l)
    TextView mTvOrder1L;

    @BindView(R.id.tv_order2)
    TextView mTvOrder2;

    @BindView(R.id.tv_order27)
    TextView mTvOrder27;

    @BindView(R.id.tv_order27_en)
    TextView mTvOrder27En;

    @BindView(R.id.tv_order2_en)
    TextView mTvOrder2En;

    @BindView(R.id.tv_order2_en_l)
    TextView mTvOrder2EnL;

    @BindView(R.id.tv_order2_en_l_ice)
    TextView mTvOrder2EnLIce;

    @BindView(R.id.tv_order2_l)
    TextView mTvOrder2L;

    @BindView(R.id.tv_order2_l_ice)
    TextView mTvOrder2LIce;

    @BindView(R.id.tv_order3)
    TextView mTvOrder3;

    @BindView(R.id.tv_order3_en)
    TextView mTvOrder3En;

    @BindView(R.id.tv_order3_en_l)
    TextView mTvOrder3EnL;

    @BindView(R.id.tv_order3_l)
    TextView mTvOrder3L;

    @BindView(R.id.tv_order4)
    TextView mTvOrder4;

    @BindView(R.id.tv_order4_en)
    TextView mTvOrder4En;

    @BindView(R.id.tv_order4_en_l)
    TextView mTvOrder4EnL;

    @BindView(R.id.tv_order4_l)
    TextView mTvOrder4L;

    @BindView(R.id.tv_order5)
    TextView mTvOrder5;

    @BindView(R.id.tv_order5_en)
    TextView mTvOrder5En;

    @BindView(R.id.tv_order5_en_l)
    TextView mTvOrder5EnL;

    @BindView(R.id.tv_order5_l)
    TextView mTvOrder5L;

    @BindView(R.id.tv_order6)
    TextView mTvOrder6;

    @BindView(R.id.tv_order6_en)
    TextView mTvOrder6En;

    @BindView(R.id.tv_order6_en_l)
    TextView mTvOrder6EnL;

    @BindView(R.id.tv_order6_l)
    TextView mTvOrder6L;

    @BindView(R.id.tv_order7)
    TextView mTvOrder7;

    @BindView(R.id.tv_order7_en)
    TextView mTvOrder7En;

    @BindView(R.id.tv_order7_en_l)
    TextView mTvOrder7EnL;

    @BindView(R.id.tv_order7_l)
    TextView mTvOrder7L;

    @BindView(R.id.tv_order8)
    TextView mTvOrder8;

    @BindView(R.id.tv_order8_en)
    TextView mTvOrder8En;

    @BindView(R.id.tv_order8_en_l)
    TextView mTvOrder8EnL;

    @BindView(R.id.tv_order8_l)
    TextView mTvOrder8L;
    private OpsSeting m_opsSeting;

    @BindView(R.id.ops4_cl_newcapepay)
    ConstraintLayout ops4_cl_NewCapePay;

    @BindView(R.id.ops4_cl_cardpay)
    ConstraintLayout ops4_cl_cardpay;

    @BindView(R.id.ops4_cl_cash)
    ConstraintLayout ops4_cl_cash;

    @BindView(R.id.ops4_cl_clearCurInterval)
    ConstraintLayout ops4_cl_clearCurInterval;

    @BindView(R.id.ops4_cl_ignore_qianmen)
    ConstraintLayout ops4_cl_ignore_qianmen;

    @BindView(R.id.ops4_cl_isChange)
    ConstraintLayout ops4_cl_isChange;

    @BindView(R.id.ops4_cl_language)
    ConstraintLayout ops4_cl_language;

    @BindView(R.id.ops4_cl_saobeipay)
    ConstraintLayout ops4_cl_saobeipay;

    @BindView(R.id.ops4_cl_v3_lbq)
    ConstraintLayout ops4_cl_v3_lbq;

    @BindView(R.id.ops4_swith_language)
    Switch ops4_swith_language;

    @BindView(R.id.rb_mdbpay)
    RadioButton rbMdbPay;

    @BindView(R.id.rb_newcar)
    RadioButton rbNewCar;

    @BindView(R.id.rb_oldcar)
    RadioButton rbOldCar;

    @BindView(R.id.rb_universalpay)
    RadioButton rbUniversalPay;

    @BindView(R.id.submit)
    LinearLayout submit;

    @BindView(R.id.swith_clearCurInterval)
    Switch swith_clearCurInterval;

    @BindView(R.id.swith_ignore_qianmen)
    Switch swith_ignore_qianmen;

    @BindView(R.id.swith_newcapepay)
    Switch swith_newCapePay;

    @BindView(R.id.swith_saobeipay)
    Switch swith_saobeipay;

    @BindView(R.id.swith_v3_lbq)
    Switch swith_v3_lbq;
    private LanguageItem languageItem = null;
    int show = 1;
    private long clickTime = 0;
    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Factory1_DevConfigFragment$8MiWOyiv2Stkj_f3sIUISuWtnIw
        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public final void onTimeSet(TimePicker timePicker, int i, int i2) {
            Factory1_DevConfigFragment.this.lambda$new$2$Factory1_DevConfigFragment(timePicker, i, i2);
        }
    };

    private void addListener() {
        this.mTimestop.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Factory1_DevConfigFragment factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                factory1_DevConfigFragment.show = 2;
                factory1_DevConfigFragment.showTimePicker();
            }
        });
        this.mTimestart.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Factory1_DevConfigFragment factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                factory1_DevConfigFragment.show = 1;
                factory1_DevConfigFragment.showTimePicker();
            }
        });
    }

    public static Factory1_DevConfigFragment newInstance() {
        return new Factory1_DevConfigFragment();
    }

    private int setClearCurIntervalTime(int i) {
        if (i <= 0) {
            return 0;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(getActivity(), 2131755399, this.timeSetListener, calendar.get(11), calendar.get(12), true).show();
    }

    private String toStartString(String str, String str2) {
        return (Tools.toString(str).equals("") && Tools.toString(str2).equals("")) ? "00:01" : Tools.toString(str);
    }

    private String toStopString(String str, String str2) {
        return (Tools.toString(str).equals("") && Tools.toString(str2).equals("")) ? "23:59" : Tools.toString(str2);
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void hideLoading() {
        IView.CC.$default$hideLoading(this);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        addListener();
        this.devConfig = Constants.devConfig();
        this.m_opsSeting = Constants.opsSeting();
        OpsSeting opsSeting = this.m_opsSeting;
        if (opsSeting != null) {
            this.mSwithCash.setChecked(opsSeting.isCash());
            this.mSwithCardpay.setChecked(this.m_opsSeting.isCardpay());
            this.rbNewCar.setChecked(this.m_opsSeting.isCardpaynew());
            this.swith_saobeipay.setChecked(this.m_opsSeting.isSaobeiPay());
            this.swith_newCapePay.setChecked(this.m_opsSeting.newcardpay);
            this.rbUniversalPay.setChecked(this.m_opsSeting.baoPay);
            this.rbMdbPay.setChecked(this.m_opsSeting.mdbPay);
            this.swith_ignore_qianmen.setChecked(this.m_opsSeting.isDoorFaultIgnore());
            this.swith_v3_lbq.setChecked(this.m_opsSeting.isV3Lbq());
            this.mSwithIschange.setChecked(this.m_opsSeting.isChange());
            this.swith_clearCurInterval.setChecked(this.m_opsSeting.isClearCurInterval());
            this.et_clearCurInterval.setText(setClearCurIntervalTime(this.m_opsSeting.getClearCurIntervalTime()) + "");
        }
        DevConfig devConfig = this.devConfig;
        if (devConfig != null) {
            this.mSwithOrder1.setChecked(devConfig.isLeft1());
            this.mSwithOrder2.setChecked(this.devConfig.isLeft2());
            this.mSwithOrder3.setChecked(this.devConfig.isLeft3());
            this.mSwithOrder4.setChecked(this.devConfig.isLeft4());
            this.mSwithOrder5.setChecked(this.devConfig.isLeft5());
            this.mSwithOrder6.setChecked(this.devConfig.isLeft6());
            this.mSwithOrder7.setChecked(this.devConfig.isLeft7());
            this.mSwithOrder1L.setChecked(this.devConfig.isRight1());
            this.mSwithOrder2L.setChecked(this.devConfig.isRight2());
            this.mSwithOrder2LIce.setChecked(this.devConfig.isRight2_ice());
            this.mSwithOrder3L.setChecked(this.devConfig.isRight3());
            this.mSwithOrder4L.setChecked(this.devConfig.isRight4());
            this.mSwithOrder5L.setChecked(this.devConfig.isRight5());
            this.mSwithOrder6L.setChecked(this.devConfig.isRight6());
            this.mSwithOrder7L.setChecked(this.devConfig.isRight7());
            this.mSwithOrder8L.setChecked(this.devConfig.isRight8());
            this.mTimestart.setText(this.devConfig.getStartTime());
            this.mTimestop.setText(this.devConfig.getStopTime());
            this.mSwithOrder27.setChecked(this.devConfig.isCmd27());
        }
        this.et_clearCurInterval.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(Factory1_DevConfigFragment.this.getString(R.string.openclearCurInterval) + editable.toString(), DataUtils.currentTime(), 3);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.ops4_cl_clearCurInterval.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Factory1_DevConfigFragment.this.swith_clearCurInterval.setChecked(!Factory1_DevConfigFragment.this.swith_clearCurInterval.isChecked());
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.openclearCurInterval));
                sb.append(Factory1_DevConfigFragment.this.swith_clearCurInterval.isChecked() ? Factory1_DevConfigFragment.this.getString(R.string.open) : Factory1_DevConfigFragment.this.getString(R.string.close));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_language.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Factory1_DevConfigFragment.this.ops4_swith_language.setChecked(!Factory1_DevConfigFragment.this.ops4_swith_language.isChecked());
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.english));
                sb.append(Factory1_DevConfigFragment.this.ops4_swith_language.isChecked() ? Factory1_DevConfigFragment.this.getString(R.string.open) : Factory1_DevConfigFragment.this.getString(R.string.close));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_cash.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Factory1_DevConfigFragment.this.mSwithCash.setChecked(!Factory1_DevConfigFragment.this.mSwithCash.isChecked());
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.opencash));
                sb.append(Factory1_DevConfigFragment.this.mSwithCash.isChecked() ? Factory1_DevConfigFragment.this.getString(R.string.open) : Factory1_DevConfigFragment.this.getString(R.string.close));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_isChange.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Factory1_DevConfigFragment.this.mSwithIschange.setChecked(!Factory1_DevConfigFragment.this.mSwithIschange.isChecked());
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.ischange));
                sb.append(Factory1_DevConfigFragment.this.mSwithIschange.isChecked() ? Factory1_DevConfigFragment.this.getString(R.string.open) : Factory1_DevConfigFragment.this.getString(R.string.close));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_cardpay.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Factory1_DevConfigFragment.this.mSwithCardpay.setChecked(!Factory1_DevConfigFragment.this.mSwithCardpay.isChecked());
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.opencardpay));
                sb.append(Factory1_DevConfigFragment.this.mSwithCardpay.isChecked() ? Factory1_DevConfigFragment.this.getString(R.string.open) : Factory1_DevConfigFragment.this.getString(R.string.close));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithCardpay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            }
        });
        if (Constants.HomePay() == 0) {
            this.group_pay.check(R.id.rb_newcar);
        } else if (Constants.HomePay() == 1) {
            this.group_pay.check(R.id.rb_universalpay);
        } else if (Constants.HomePay() == 2) {
            this.group_pay.check(R.id.rb_mdbpay);
        } else {
            this.group_pay.check(R.id.rb_oldcar);
        }
        this.group_pay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.8
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_universalpay) {
                    Constants.setHomePay(1);
                    MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                    StringBuilder sb = new StringBuilder();
                    sb.append(Factory1_DevConfigFragment.this.getString(R.string.UniversalPayment));
                    sb.append(Factory1_DevConfigFragment.this.rbUniversalPay.isChecked() ? Factory1_DevConfigFragment.this.getString(R.string.open) : Factory1_DevConfigFragment.this.getString(R.string.close));
                    myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
                    return;
                }
                if (i == R.id.rb_newcar) {
                    Constants.setHomePay(0);
                    MyMqttService myMqttService2 = MyAppLocation.myAppLocation.myMqttService;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(Factory1_DevConfigFragment.this.getString(R.string.opencardpay));
                    sb2.append(Factory1_DevConfigFragment.this.rbNewCar.isChecked() ? Factory1_DevConfigFragment.this.getString(R.string.open) : Factory1_DevConfigFragment.this.getString(R.string.close));
                    myMqttService2.addOpsLog(sb2.toString(), DataUtils.currentTime(), 3);
                    return;
                }
                if (i == R.id.rb_mdbpay) {
                    Constants.setHomePay(2);
                    MyMqttService myMqttService3 = MyAppLocation.myAppLocation.myMqttService;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(Factory1_DevConfigFragment.this.getString(R.string.opencardpay));
                    sb3.append(Factory1_DevConfigFragment.this.rbMdbPay.isChecked() ? Factory1_DevConfigFragment.this.getString(R.string.open) : Factory1_DevConfigFragment.this.getString(R.string.close));
                    myMqttService3.addOpsLog(sb3.toString(), DataUtils.currentTime(), 3);
                    return;
                }
                Constants.setHomePay(3);
                MyMqttService myMqttService4 = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(Factory1_DevConfigFragment.this.getString(R.string.opencardpay));
                sb4.append(Factory1_DevConfigFragment.this.rbOldCar.isChecked() ? Factory1_DevConfigFragment.this.getString(R.string.open) : Factory1_DevConfigFragment.this.getString(R.string.close));
                myMqttService4.addOpsLog(sb4.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_saobeipay.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Factory1_DevConfigFragment.this.swith_saobeipay.setChecked(!Factory1_DevConfigFragment.this.swith_saobeipay.isChecked());
            }
        });
        this.swith_saobeipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.10
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.saobeipayscan));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_NewCapePay.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Factory1_DevConfigFragment.this.swith_newCapePay.setChecked(!Factory1_DevConfigFragment.this.swith_newCapePay.isChecked());
            }
        });
        this.swith_newCapePay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.12
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.newcapepay));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_ignore_qianmen.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Factory1_DevConfigFragment.this.swith_ignore_qianmen.setChecked(!Factory1_DevConfigFragment.this.swith_ignore_qianmen.isChecked());
            }
        });
        this.swith_ignore_qianmen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.14
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.ignore_qianmen));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_v3_lbq.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Factory1_DevConfigFragment.this.swith_v3_lbq.setChecked(!Factory1_DevConfigFragment.this.swith_v3_lbq.isChecked());
            }
        });
        this.swith_v3_lbq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.16
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.v3_lbq));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.17
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd1));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.18
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd2));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.19
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd3));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.20
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd4));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.21
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd5));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.22
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                EventBus.getDefault().post(new LockMessage(z));
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd6));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.23
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd7));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder1L.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.24
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd9));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder2L.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.25
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.type3_cmd));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder2LIce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.26
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.type3_cmd_ice));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder27.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.27
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd27));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder3L.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.28
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd10));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder4L.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.29
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd11));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder5L.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.30
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd12));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder6L.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.31
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd13));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder7L.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.32
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.cmd8));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithOrder8L.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory1_DevConfigFragment.33
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory1_DevConfigFragment factory1_DevConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory1_DevConfigFragment.this.getString(R.string.skippayment));
                if (z) {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory1_DevConfigFragment = Factory1_DevConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory1_DevConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.btnLanguage.setText(LanguageHelper.language().getNativeName());
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_factory1_devconfig, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void killMyself() {
        IView.CC.$default$killMyself(this);
    }

    public /* synthetic */ void lambda$new$2$Factory1_DevConfigFragment(TimePicker timePicker, int i, int i2) {
        StringBuilder sb;
        if (i2 < 10) {
            sb = new StringBuilder();
            sb.append("0");
            sb.append(i2);
        } else {
            sb = new StringBuilder();
            sb.append(i2);
            sb.append("");
        }
        String str = i + ":" + sb.toString();
        if (this.show == 1) {
            this.mTimestart.setText(str);
            MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd8) + " start " + str, DataUtils.currentTime(), 3);
            return;
        }
        this.mTimestop.setText(str);
        MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.cmd8) + " stop " + str, DataUtils.currentTime(), 3);
    }

    public /* synthetic */ void lambda$onClick$0$Factory1_DevConfigFragment(LanguageItem languageItem) {
        this.languageItem = languageItem;
        this.btnLanguage.setText(languageItem.getNativeName());
    }

    public /* synthetic */ void lambda$save$1$Factory1_DevConfigFragment() {
        Tools.upLocalLog("工厂设置 - 切换语言重启应用");
        Intent intent = new Intent(requireContext(), (Class<?>) HomeActivity.class);
        intent.setFlags(268468224);
        requireContext().startActivity(intent);
        Process.killProcess(Process.myPid());
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @OnClick({R.id.submit, R.id.btn_language})
    public void onClick(View view) {
        if (System.currentTimeMillis() - this.clickTime <= 1000) {
            this.clickTime = System.currentTimeMillis();
            return;
        }
        int id = view.getId();
        if (id != R.id.btn_language) {
            if (id != R.id.submit) {
                return;
            }
            save();
        } else {
            SetLanguageDialog setLanguageDialog = new SetLanguageDialog(requireContext(), false);
            setLanguageDialog.setOnConfirmListener(new SetLanguageDialog.onConfirmListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Factory1_DevConfigFragment$nei5Y9M77wEluYC3evxdUKlPeTk
                @Override // com.yj.coffeemachines.dialog.SetLanguageDialog.onConfirmListener
                public final void onConfirm(LanguageItem languageItem) {
                    Factory1_DevConfigFragment.this.lambda$onClick$0$Factory1_DevConfigFragment(languageItem);
                }
            });
            setLanguageDialog.show();
        }
    }

    public void save() {
        Constants.setOpsSeting(new OpsSeting(this.mSwithCash.isChecked(), this.mSwithIschange.isChecked(), this.mSwithCardpay.isChecked(), this.m_opsSeting.isVip(), this.m_opsSeting.isPlayAD(), this.m_opsSeting.isNopay(), this.m_opsSeting.getNoPayExpire(), this.m_opsSeting.isAdjust(), this.m_opsSeting.islimit(), this.m_opsSeting.getLimit(), this.m_opsSeting.getCashcode(), this.m_opsSeting.getRate(), this.m_opsSeting.getRate_y(), this.m_opsSeting.getChange(), this.m_opsSeting.getPos(), this.rbNewCar.isChecked(), this.swith_saobeipay.isChecked(), this.swith_newCapePay.isChecked(), this.rbUniversalPay.isChecked(), this.rbMdbPay.isChecked(), this.m_opsSeting.isOpenGetDrink(), this.swith_clearCurInterval.isChecked(), setClearCurIntervalTime(Tools.toInt(this.et_clearCurInterval.getText().toString())), this.m_opsSeting.isHintTooBar(), this.swith_ignore_qianmen.isChecked(), this.swith_v3_lbq.isChecked()));
        Constants.setDevConfig(new DevConfig(this.mSwithOrder1.isChecked(), this.mSwithOrder2.isChecked(), this.mSwithOrder3.isChecked(), this.mSwithOrder4.isChecked(), this.mSwithOrder5.isChecked(), this.mSwithOrder6.isChecked(), this.mSwithOrder7.isChecked(), this.mSwithOrder1L.isChecked(), this.mSwithOrder2L.isChecked(), this.mSwithOrder2LIce.isChecked(), this.mSwithOrder3L.isChecked(), this.mSwithOrder4L.isChecked(), this.mSwithOrder5L.isChecked(), this.mSwithOrder6L.isChecked(), this.mSwithOrder7L.isChecked(), this.mSwithOrder8L.isChecked(), toStartString(this.mTimestart.getText().toString(), this.mTimestop.getText().toString()), toStopString(this.mTimestart.getText().toString(), this.mTimestop.getText().toString()), this.mSwithOrder27.isChecked()));
        if (!LanguageHelper.isLanguageChange(this.languageItem)) {
            ArmsUtils.snackbarText(getString(R.string.havebeensaved));
            return;
        }
        ArmsUtils.snackbarText(getString(R.string.havebeensaved) + "。" + getString(R.string.language_change_restart));
        MultiLanguages.setAppLanguage(requireContext(), new Locale(LanguageHelper.language().getLangCode()));
        new Handler().postDelayed(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Factory1_DevConfigFragment$Ju59evXK7rhKoOWmTaoTZCysbf8
            @Override // java.lang.Runnable
            public final void run() {
                Factory1_DevConfigFragment.this.lambda$save$1$Factory1_DevConfigFragment();
            }
        }, 2000L);
        this.submit.setEnabled(false);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerFactory1_DevConfigComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
