package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import com.blankj.utilcode.util.PermissionUtils;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.serialport.SerialPortFinder;
import com.yj.coffeemachines.app.service.MyMqttService;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.di.component.DaggerFactory2_SeralPortConfigComponent;
import com.yj.coffeemachines.mvp.contract.Factory2_SeralPortConfigContract;
import com.yj.coffeemachines.mvp.presenter.Factory2_SeralPortConfigPresenter;

/* loaded from: classes.dex */
public class Factory2_SeralPortConfigFragment extends BaseFragment<Factory2_SeralPortConfigPresenter> implements Factory2_SeralPortConfigContract.View {
    private String[] allDevicesPath;

    @BindView(R.id.lay01)
    LinearLayout mLay01;

    @BindView(R.id.lay02)
    LinearLayout mLay02;

    @BindView(R.id.lay03)
    LinearLayout mLay03;

    @BindView(R.id.save)
    LinearLayout mSave;

    @BindView(R.id.spinner1)
    TextView mSpinner1;

    @BindView(R.id.spinner2)
    TextView mSpinner2;

    @BindView(R.id.spinner3)
    TextView mSpinner3;

    @BindView(R.id.swith_port1)
    Switch mSwithPort1;

    @BindView(R.id.swith_port2)
    Switch mSwithPort2;

    @BindView(R.id.swith_port3)
    Switch mSwithPort3;

    @BindView(R.id.tv_factroy1)
    TextView mTvFactroy1;

    @BindView(R.id.tv_factroy1_en)
    TextView mTvFactroy1En;

    @BindView(R.id.tv_factroy2)
    TextView mTvFactroy2;

    @BindView(R.id.tv_factroy2_en)
    TextView mTvFactroy2En;

    @BindView(R.id.tv_factroy3)
    TextView mTvFactroy3;

    @BindView(R.id.tv_factroy3_en)
    TextView mTvFactroy3En;

    private void makeDialog(final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.choseseariport);
        builder.setItems(this.allDevicesPath, new DialogInterface.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory2_SeralPortConfigFragment.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                String str = Factory2_SeralPortConfigFragment.this.allDevicesPath[i2];
                int i3 = i;
                if (i3 == 1) {
                    if (str.equals(Factory2_SeralPortConfigFragment.this.mSpinner2.getText().toString()) || str.equals(Factory2_SeralPortConfigFragment.this.mSpinner3.getText().toString())) {
                        ArmsUtils.snackbarText(Factory2_SeralPortConfigFragment.this.getString(R.string.portnameusered));
                        return;
                    }
                    Factory2_SeralPortConfigFragment.this.mSpinner1.setText(str);
                    MyAppLocation.myAppLocation.myMqttService.addOpsLog(Factory2_SeralPortConfigFragment.this.getString(R.string.port1) + "  " + str, DataUtils.currentTime(), 3);
                    return;
                }
                if (i3 == 2) {
                    if (str.equals(Factory2_SeralPortConfigFragment.this.mSpinner1.getText().toString()) || str.equals(Factory2_SeralPortConfigFragment.this.mSpinner3.getText().toString())) {
                        ArmsUtils.snackbarText(Factory2_SeralPortConfigFragment.this.getString(R.string.portnameusered));
                        return;
                    }
                    Factory2_SeralPortConfigFragment.this.mSpinner2.setText(str);
                    MyAppLocation.myAppLocation.myMqttService.addOpsLog(Factory2_SeralPortConfigFragment.this.getString(R.string.port2) + "  " + str, DataUtils.currentTime(), 3);
                    return;
                }
                if (i3 == 3) {
                    if (str.equals(Factory2_SeralPortConfigFragment.this.mSpinner2.getText().toString()) || str.equals(Factory2_SeralPortConfigFragment.this.mSpinner1.getText().toString())) {
                        ArmsUtils.snackbarText(Factory2_SeralPortConfigFragment.this.getString(R.string.portnameusered));
                        return;
                    }
                    Factory2_SeralPortConfigFragment.this.mSpinner3.setText(str);
                    MyAppLocation.myAppLocation.myMqttService.addOpsLog(Factory2_SeralPortConfigFragment.this.getString(R.string.port3) + "  " + str, DataUtils.currentTime(), 3);
                }
            }
        });
        builder.show();
    }

    public static Factory2_SeralPortConfigFragment newInstance() {
        return new Factory2_SeralPortConfigFragment();
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        PermissionUtils.permission("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").callback(new PermissionUtils.SimpleCallback() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory2_SeralPortConfigFragment.1
            @Override // com.blankj.utilcode.util.PermissionUtils.SimpleCallback
            public void onDenied() {
                ArmsUtils.snackbarText(Factory2_SeralPortConfigFragment.this.getString(R.string.nopermission));
            }

            @Override // com.blankj.utilcode.util.PermissionUtils.SimpleCallback
            public void onGranted() {
                ArmsUtils.snackbarText(Factory2_SeralPortConfigFragment.this.getString(R.string.yespermission));
            }
        }).request();
        this.allDevicesPath = new SerialPortFinder().getAllDevicesPath();
        this.mSpinner1.setText(Constants.SERIAPort_instant());
        this.mSpinner2.setText(Constants.SERIAPort_currentgrinding());
        this.mSpinner3.setText(Constants.SERIAPort_payment());
        this.mSwithPort1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory2_SeralPortConfigFragment.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory2_SeralPortConfigFragment factory2_SeralPortConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory2_SeralPortConfigFragment.this.getString(R.string.port1));
                if (z) {
                    factory2_SeralPortConfigFragment = Factory2_SeralPortConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory2_SeralPortConfigFragment = Factory2_SeralPortConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory2_SeralPortConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
                Constants.getStatePort1(z ? 1 : 0);
            }
        });
        this.mSwithPort2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory2_SeralPortConfigFragment.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory2_SeralPortConfigFragment factory2_SeralPortConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory2_SeralPortConfigFragment.this.getString(R.string.port2));
                if (z) {
                    factory2_SeralPortConfigFragment = Factory2_SeralPortConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory2_SeralPortConfigFragment = Factory2_SeralPortConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory2_SeralPortConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
                Constants.getStatePort2(z ? 1 : 0);
            }
        });
        this.mSwithPort3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Factory2_SeralPortConfigFragment.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Factory2_SeralPortConfigFragment factory2_SeralPortConfigFragment;
                int i;
                MyMqttService myMqttService = MyAppLocation.myAppLocation.myMqttService;
                StringBuilder sb = new StringBuilder();
                sb.append(Factory2_SeralPortConfigFragment.this.getString(R.string.port3));
                if (z) {
                    factory2_SeralPortConfigFragment = Factory2_SeralPortConfigFragment.this;
                    i = R.string.open;
                } else {
                    factory2_SeralPortConfigFragment = Factory2_SeralPortConfigFragment.this;
                    i = R.string.close;
                }
                sb.append(factory2_SeralPortConfigFragment.getString(i));
                myMqttService.addOpsLog(sb.toString(), DataUtils.currentTime(), 3);
                Constants.getStatePort3(z ? 1 : 0);
            }
        });
        this.mSwithPort1.setChecked(Constants.getStatePort1() == 1);
        this.mSwithPort2.setChecked(Constants.getStatePort2() == 1);
        this.mSwithPort3.setChecked(Constants.getStatePort3() == 1);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_factory2_seralportconfig, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public void killMyself() {
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @OnClick({R.id.spinner1, R.id.spinner2, R.id.spinner3, R.id.save})
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.save) {
            switch (id) {
                case R.id.spinner1 /* 2131296785 */:
                    makeDialog(1);
                    return;
                case R.id.spinner2 /* 2131296786 */:
                    makeDialog(2);
                    return;
                case R.id.spinner3 /* 2131296787 */:
                    makeDialog(3);
                    return;
                default:
                    return;
            }
        }
        String charSequence = this.mSpinner1.getText().toString();
        String charSequence2 = this.mSpinner2.getText().toString();
        String charSequence3 = this.mSpinner3.getText().toString();
        Constants.setSERIAPort_instant(charSequence);
        Constants.setSERIAPort_currentgrinding(charSequence2);
        Constants.setSERIAPort_payment(charSequence3);
        ArmsUtils.snackbarText(getString(R.string.havebeensaved));
        MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.save) + "  " + charSequence + " " + charSequence2 + " " + charSequence3, DataUtils.currentTime(), 3);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerFactory2_SeralPortConfigComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
