package com.yj.coffeemachines.mvp.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.OpsSeting;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.service.MyMqttService;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.app.utils.DiaLogUtils;
import com.yj.coffeemachines.app.utils.KvUtil;
import com.yj.coffeemachines.di.component.DaggerOps4_FeatureSettingComponent;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Ops4_FeatureSettingContract;
import com.yj.coffeemachines.mvp.presenter.Ops4_FeatureSettingPresenter;
import java.text.DecimalFormat;

/* loaded from: classes.dex */
public class Ops4_FeatureSettingFragment extends BaseFragment<Ops4_FeatureSettingPresenter> implements Ops4_FeatureSettingContract.View {

    @BindView(R.id.group_pay)
    RadioGroup group_pay;

    @BindView(R.id.btn_doubleactivity)
    Button mBtnDoubleactivity;

    @BindView(R.id.btn_exitapp)
    Button mBtnExitapp;

    @BindView(R.id.btn_hint)
    Button mBtnHint;

    @BindView(R.id.btn_show)
    Button mBtnShow;

    @BindView(R.id.btn_uplog)
    Button mBtnUplog;

    @BindView(R.id.et_cashcode)
    EditText mEtCashcode;

    @BindView(R.id.et_change)
    EditText mEtChange;

    @BindView(R.id.et_cups)
    EditText mEtCups;

    @BindView(R.id.et_mdb)
    EditText mEtMdb;

    @BindView(R.id.et_pos)
    EditText mEtPos;

    @BindView(R.id.et_rate)
    EditText mEtRate;

    @BindView(R.id.et_rate_y)
    EditText mEtRateY;

    @BindView(R.id.parent_layout)
    LinearLayout mParentLayout;

    @BindView(R.id.swith_ad)
    Switch mSwithAd;

    @BindView(R.id.swith_adjust)
    Switch mSwithAdjust;

    @BindView(R.id.swith_limit)
    Switch mSwithLimit;

    @BindView(R.id.swith_nopay)
    Switch mSwithNopay;

    @BindView(R.id.swith_vip)
    Switch mSwithVip;

    @BindView(R.id.tv_ad)
    TextView mTvAd;

    @BindView(R.id.tv_ad_en)
    TextView mTvAdEn;

    @BindView(R.id.tv_adjust)
    TextView mTvAdjust;

    @BindView(R.id.tv_adjust_en)
    TextView mTvAdjustEn;

    @BindView(R.id.tv_cashcode_en)
    TextView mTvCashcodeEn;

    @BindView(R.id.tv_change)
    TextView mTvChange;

    @BindView(R.id.tv_change_en)
    TextView mTvChangeEn;

    @BindView(R.id.tv_limit)
    TextView mTvLimit;

    @BindView(R.id.tv_limit_en)
    TextView mTvLimitEn;

    @BindView(R.id.tv_nopay)
    TextView mTvNopay;

    @BindView(R.id.tv_nopay_en)
    TextView mTvNopayEn;

    @BindView(R.id.tv_pos)
    TextView mTvPos;

    @BindView(R.id.tv_pos_en)
    TextView mTvPosEn;

    @BindView(R.id.tv_rate)
    TextView mTvRate;

    @BindView(R.id.tv_rate_en)
    TextView mTvRateEn;

    @BindView(R.id.tv_rate_en_y)
    TextView mTvRateEnY;

    @BindView(R.id.tv_rate_y)
    TextView mTvRateY;

    @BindView(R.id.tv_vip)
    TextView mTvVip;

    @BindView(R.id.tv_vip_en)
    TextView mTvVipEn;

    @BindView(R.id.ops4_cl_ad)
    ConstraintLayout ops4_cl_ad;

    @BindView(R.id.ops4_cl_adjust)
    ConstraintLayout ops4_cl_adjust;

    @BindView(R.id.ops4_cl_getdrink)
    ConstraintLayout ops4_cl_getdrink;

    @BindView(R.id.ops4_cl_limit)
    ConstraintLayout ops4_cl_limit;

    @BindView(R.id.ops4_cl_nopay)
    ConstraintLayout ops4_cl_nopay;

    @BindView(R.id.ops4_cl_vip)
    ConstraintLayout ops4_cl_vip;

    @BindView(R.id.ops4_swith_getdrink)
    Switch ops4_swith_getdrink;
    private OpsSeting opsSeting;

    @BindView(R.id.rb_Level2)
    RadioButton rbLevel2;

    @BindView(R.id.rb_Level3)
    RadioButton rbLevel3;
    private boolean hintTooBar = false;
    private long expireNoPay = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public double getDoubleFromEditText(Editable editable) {
        int i = 1;
        try {
            int parseInt = Integer.parseInt(editable.toString());
            if (parseInt >= 1) {
                i = parseInt;
            }
            KvUtil.getInstance().putInt("etmdb", i);
        } catch (NumberFormatException unused) {
        }
        return i;
    }

    public static Ops4_FeatureSettingFragment newInstance() {
        return new Ops4_FeatureSettingFragment();
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        this.opsSeting = Constants.opsSeting();
        new DecimalFormat("###0.#####");
        OpsSeting opsSeting = this.opsSeting;
        if (opsSeting != null) {
            this.expireNoPay = opsSeting.getNoPayExpire();
            this.mSwithVip.setChecked(this.opsSeting.isVip());
            this.mSwithAd.setChecked(this.opsSeting.isPlayAD());
            this.mSwithNopay.setChecked(this.opsSeting.isNopay());
            this.mSwithAdjust.setChecked(this.opsSeting.isAdjust());
            this.mSwithLimit.setChecked(this.opsSeting.islimit());
            this.mEtCups.setText(this.opsSeting.getLimit() + "");
            this.mEtCashcode.setText(this.opsSeting.getCashcode() + "");
            this.mEtRate.setText(this.opsSeting.getRate() + "");
            this.mEtRateY.setText(this.opsSeting.getRate_y() + "");
            this.mEtChange.setText(this.opsSeting.getChange() + "");
            this.mEtPos.setText(this.opsSeting.getPos() + "");
            if (Tools.toDouble(this.mEtRate.getText(), 1.0d) == 0.0d) {
                this.mEtRate.setText("1");
            }
            if (Tools.toDouble(this.mEtRateY.getText(), 1.0d) == 0.0d) {
                this.mEtRateY.setText("1");
            }
            if (Tools.toDouble(this.mEtChange.getText(), 1.0d) == 0.0d) {
                this.mEtChange.setText("1");
            }
            if (Tools.toDouble(this.mEtPos.getText(), 0.01d) == 0.0d) {
                this.mEtPos.setText("0.01");
            }
            this.ops4_swith_getdrink.setChecked(this.opsSeting.isOpenGetDrink());
        }
        if (Constants.Model == 0) {
            this.mBtnDoubleactivity.setVisibility(0);
        } else {
            this.mBtnDoubleactivity.setVisibility(8);
        }
        this.ops4_cl_getdrink.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Ops4_FeatureSettingFragment.this.ops4_swith_getdrink.setChecked(!Ops4_FeatureSettingFragment.this.ops4_swith_getdrink.isChecked());
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Ops4_FeatureSettingFragment.this.getString(R.string.openGetDrink));
                sb.append(Ops4_FeatureSettingFragment.this.ops4_swith_getdrink.isChecked() ? Ops4_FeatureSettingFragment.this.getString(R.string.open) : Ops4_FeatureSettingFragment.this.getString(R.string.close));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_vip.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Ops4_FeatureSettingFragment.this.mSwithVip.setChecked(!Ops4_FeatureSettingFragment.this.mSwithVip.isChecked());
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Ops4_FeatureSettingFragment.this.getString(R.string.openvip));
                sb.append(Ops4_FeatureSettingFragment.this.mSwithVip.isChecked() ? Ops4_FeatureSettingFragment.this.getString(R.string.open) : Ops4_FeatureSettingFragment.this.getString(R.string.close));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_ad.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Ops4_FeatureSettingFragment.this.mSwithAd.setChecked(!Ops4_FeatureSettingFragment.this.mSwithAd.isChecked());
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Ops4_FeatureSettingFragment.this.getString(R.string.openad));
                sb.append(Ops4_FeatureSettingFragment.this.mSwithAd.isChecked() ? Ops4_FeatureSettingFragment.this.getString(R.string.open) : Ops4_FeatureSettingFragment.this.getString(R.string.close));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_nopay.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Ops4_FeatureSettingFragment.this.mSwithNopay.setChecked(!Ops4_FeatureSettingFragment.this.mSwithNopay.isChecked());
                Ops4_FeatureSettingFragment.this.expireNoPay = System.currentTimeMillis() + Constants.npPayInterval;
                Constants.noPayTime = System.currentTimeMillis();
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Ops4_FeatureSettingFragment.this.getString(R.string.opennopay));
                sb.append(Ops4_FeatureSettingFragment.this.mSwithNopay.isChecked() ? Ops4_FeatureSettingFragment.this.getString(R.string.open) : Ops4_FeatureSettingFragment.this.getString(R.string.close));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mSwithNopay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Ops4_FeatureSettingFragment.this.expireNoPay = System.currentTimeMillis() + Constants.npPayInterval;
            }
        });
        this.ops4_cl_adjust.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Ops4_FeatureSettingFragment.this.mSwithAdjust.setChecked(!Ops4_FeatureSettingFragment.this.mSwithAdjust.isChecked());
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Ops4_FeatureSettingFragment.this.getString(R.string.openconcentration));
                sb.append(Ops4_FeatureSettingFragment.this.mSwithAdjust.isChecked() ? Ops4_FeatureSettingFragment.this.getString(R.string.open) : Ops4_FeatureSettingFragment.this.getString(R.string.close));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.ops4_cl_limit.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Ops4_FeatureSettingFragment.this.mSwithLimit.setChecked(!Ops4_FeatureSettingFragment.this.mSwithLimit.isChecked());
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Ops4_FeatureSettingFragment.this.getString(R.string.openlimit));
                sb.append(Ops4_FeatureSettingFragment.this.mSwithLimit.isChecked() ? Ops4_FeatureSettingFragment.this.getString(R.string.open) : Ops4_FeatureSettingFragment.this.getString(R.string.close));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
            }
        });
        this.mEtCups.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.8
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(Ops4_FeatureSettingFragment.this.getString(R.string.openlimit) + editable.toString(), DataUtils.currentTime(), 3);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mEtCashcode.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.9
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(Ops4_FeatureSettingFragment.this.getString(R.string.payseting_1) + editable.toString(), DataUtils.currentTime(), 3);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mEtRate.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.10
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(Ops4_FeatureSettingFragment.this.getString(R.string.payseting_2) + editable.toString(), DataUtils.currentTime(), 3);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mEtRateY.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.11
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(Ops4_FeatureSettingFragment.this.getString(R.string.payseting_3) + editable.toString(), DataUtils.currentTime(), 3);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mEtChange.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.12
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(Ops4_FeatureSettingFragment.this.getString(R.string.payseting_4) + editable.toString(), DataUtils.currentTime(), 3);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mEtPos.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.13
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(Ops4_FeatureSettingFragment.this.getString(R.string.payseting_6) + editable.toString(), DataUtils.currentTime(), 3);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mEtMdb.setText(KvUtil.getInstance().getInt("etmdb", 100) + "");
        this.mEtMdb.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.14
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Ops4_FeatureSettingFragment.this.getDoubleFromEditText(editable);
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(Ops4_FeatureSettingFragment.this.getString(R.string.mdb_magnification) + editable.toString(), DataUtils.currentTime(), 3);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mEtMdb.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.15
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    return;
                }
                Ops4_FeatureSettingFragment.this.mEtMdb.setText(KvUtil.getInstance().getInt("etmdb", 100) + "");
            }
        });
        if (Constants.LevelPay() == 2) {
            this.group_pay.check(R.id.rb_Level2);
        } else {
            this.group_pay.check(R.id.rb_Level3);
        }
        this.group_pay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.16
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_Level2) {
                    Constants.setLevelPay(2);
                } else if (i == R.id.rb_Level3) {
                    Constants.setLevelPay(3);
                }
            }
        });
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ops4_featuresetting, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public void killMyself() {
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @OnClick({R.id.btn_show, R.id.btn_hint, R.id.btn_doubleactivity, R.id.btn_exitapp, R.id.btn_uplog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_doubleactivity /* 2131296360 */:
                Constants.setDualLCD();
                if (Constants.getDualLCD() != 1) {
                    MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.open_double) + getString(R.string.close), DataUtils.currentTime(), 3);
                    return;
                }
                this.mSwithAd.setChecked(false);
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.open_double) + getString(R.string.open), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_exitapp /* 2131296367 */:
                DiaLogUtils.showAlert(getActivity(), getString(R.string.hint), getString(R.string.isexitapp), getString(R.string.confirm), new DialogInterface.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.17
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyAppLocation.myAppLocation.myMqttService.addOpsLog(Ops4_FeatureSettingFragment.this.getString(R.string.exitapp) + Ops4_FeatureSettingFragment.this.getString(R.string.open), DataUtils.currentTime(), 3);
                        Tools.upLocalLog("Setting - 关闭应用");
                        MyAppLocation.myAppLocation.onDestroy();
                        ArmsUtils.exitApp();
                    }
                }, getString(R.string.cancle), new DialogInterface.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment.18
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyAppLocation.myAppLocation.myMqttService.addOpsLog(Ops4_FeatureSettingFragment.this.getString(R.string.exitapp) + Ops4_FeatureSettingFragment.this.getString(R.string.close), DataUtils.currentTime(), 3);
                    }
                });
                return;
            case R.id.btn_hint /* 2131296369 */:
                this.hintTooBar = true;
                Constants.setHintTooBar(getActivity());
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.hinttoobar), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_show /* 2131296383 */:
                this.hintTooBar = false;
                Constants.setShowTooBar(getActivity());
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.showtoobar), DataUtils.currentTime(), 3);
                return;
            case R.id.btn_uplog /* 2131296392 */:
                MyAppLocation.myAppLocation.myMqttService.outPutOrders(false, false, true);
                ArmsUtils.snackbarText(getString(R.string.outLogs));
                return;
            default:
                return;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        Constants.setOpsSeting(new OpsSeting(this.opsSeting.isCash(), this.opsSeting.isChange(), this.opsSeting.isCardpay(), this.mSwithVip.isChecked(), this.mSwithAd.isChecked(), this.mSwithNopay.isChecked(), this.expireNoPay, this.mSwithAdjust.isChecked(), this.mSwithLimit.isChecked(), Tools.toInt(this.mEtCups.getText().toString()), this.mEtCashcode.getText().toString(), Tools.toDouble(this.mEtRate.getText().toString(), 1.0d), Tools.toDouble(this.mEtRateY.getText().toString(), 1.0d), Tools.toDouble(this.mEtChange.getText().toString(), 1.0d), Tools.toDoubleMin(this.mEtPos.getText().toString(), 0.01d, 0.01d), this.opsSeting.isCardpaynew(), this.opsSeting.isSaobeiPay(), this.opsSeting.newcardpay, this.opsSeting.baoPay, this.opsSeting.mdbPay, this.ops4_swith_getdrink.isChecked(), this.opsSeting.isClearCurInterval(), this.opsSeting.getClearCurIntervalTime(), this.hintTooBar, this.opsSeting.isDoorFaultIgnore(), this.opsSeting.isV3Lbq()));
        super.onPause();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerOps4_FeatureSettingComponent.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override // com.jess.arms.mvp.IView
    public void showLoading() {
    }

    @Override // com.jess.arms.mvp.IView
    public void showMessage(@NonNull String str) {
        Preconditions.checkNotNull(str);
        ArmsUtils.snackbarText(str);
    }
}
