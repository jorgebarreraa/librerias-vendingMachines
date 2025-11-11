package com.yj.coffeemachines.mvp.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.DiaLogUtils;
import com.yj.coffeemachines.di.component.DaggerSetingsHomeComponent;
import com.yj.coffeemachines.eventbusbean.MqttStateMessage;
import com.yj.coffeemachines.mvp.contract.SetingsHomeContract;
import com.yj.coffeemachines.mvp.model.beans.DeviceInfoBean;
import com.yj.coffeemachines.mvp.presenter.SetingsHomePresenter;
import com.yj.coffeemachines.mvp.ui.fragment.DIYFragment;
import com.yj.coffeemachines.mvp.ui.fragment.FactoryFragment;
import com.yj.coffeemachines.mvp.ui.fragment.OpsFragment;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class SetingsHomeActivity extends BaseActivity<SetingsHomePresenter> implements SetingsHomeContract.View {

    @BindView(R.id.ll_factory)
    LinearLayout llFactory;

    @BindView(R.id.ll_setting)
    LinearLayout llSetting;

    @BindView(R.id.ll_ui)
    LinearLayout llUi;

    @BindView(R.id.ln_setings)
    LinearLayout lnSetings;

    @BindView(R.id.btn_back)
    RelativeLayout mBtnBack;

    @BindView(R.id.countdowntime)
    TextView mCountdowntime;

    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;

    @BindView(R.id.iv_onlin)
    ImageView mIvOnlin;

    @BindView(R.id.iv_sn)
    ImageView mIvSn;

    @BindView(R.id.tv_onlin)
    TextView mTvOnlin;

    @BindView(R.id.tv_out)
    TextView mTvOut;

    @BindView(R.id.tv_out_en)
    TextView mTvOutEn;

    @BindView(R.id.tv_seting1)
    TextView mTvSeting1;

    @BindView(R.id.tv_seting1_en)
    TextView mTvSeting1En;

    @BindView(R.id.tv_seting2)
    TextView mTvSeting2;

    @BindView(R.id.tv_seting2_en)
    TextView mTvSeting2En;

    @BindView(R.id.tv_seting3)
    TextView mTvSeting3;

    @BindView(R.id.tv_seting3_en)
    TextView mTvSeting3En;

    @BindView(R.id.tv_sn)
    TextView mTvSn;
    private SparseArray<String> mSparseTags = new SparseArray<>();
    private OpsFragment fragment1 = OpsFragment.newInstance();
    private CountDownTimer timer = new CountDownTimer(180000, 1000) { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity.1
        @Override // android.os.CountDownTimer
        public void onFinish() {
            Constants.isSettingInterface = false;
            Constants.isAgainDialog = true;
            SetingsHomeActivity.this.killMyself();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (SetingsHomeActivity.this.mCountdowntime != null) {
                SetingsHomeActivity.this.mCountdowntime.setText(String.format(SetingsHomeActivity.this.getString(R.string.hint_countdowntime), (j / 1000) + ""));
            }
        }
    };
    final FactoryFragment fragment2 = FactoryFragment.newInstance();

    private void choseChange(int i) {
        if (i == 1) {
            step1(true);
            step2(false);
            step3(false);
        } else if (i == 2) {
            step1(false);
            step2(true);
            step3(false);
        } else {
            if (i != 3) {
                return;
            }
            step1(false);
            step2(false);
            step3(true);
        }
    }

    private void initialize() {
        Constants.NowStep = 6;
    }

    private void initsparr() {
        this.mSparseTags.put(R.id.tv_seting1, "tv_seting1");
        this.mSparseTags.put(R.id.tv_seting2, "tv_seting2");
        this.mSparseTags.put(R.id.tv_seting3, "tv_seting3");
        replaceFragment(getSupportFragmentManager(), R.id.fl_container, this.fragment1, this.mSparseTags.get(R.id.tv_seting1));
    }

    private void makeDialogLoginFactory() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = View.inflate(getActivity(), R.layout.opslogin_layout, null);
        final EditText editText = (EditText) inflate.findViewById(R.id.password);
        Button button = (Button) inflate.findViewById(R.id.btn_login);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.adminlogin);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.show();
        button.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String obj = editText.getText().toString();
                if (Constants.isLocalFactoryInterface || obj.equals(Constants.factroyPsw) || obj.equals("123456")) {
                    SetingsHomeActivity setingsHomeActivity = SetingsHomeActivity.this;
                    setingsHomeActivity.replaceFragment(setingsHomeActivity.getSupportFragmentManager(), R.id.fl_container, SetingsHomeActivity.this.fragment2, (String) SetingsHomeActivity.this.mSparseTags.get(R.id.tv_seting2));
                }
                create.dismiss();
            }
        });
    }

    private void step1(boolean z) {
        this.llSetting.setBackgroundColor(ContextCompat.getColor(this, z ? R.color.bg_setting_head : R.color.white));
    }

    private void step2(boolean z) {
        this.llFactory.setBackgroundColor(ContextCompat.getColor(this, z ? R.color.bg_setting_head : R.color.white));
    }

    private void step3(boolean z) {
        this.llUi.setBackgroundColor(ContextCompat.getColor(this, z ? R.color.bg_setting_head : R.color.white));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0) {
            DiaLogUtils.showAlert(getActivity(), getString(R.string.hint), getString(R.string.isexitapp), getString(R.string.confirm), new DialogInterface.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    ArmsUtils.exitApp();
                }
            }, getString(R.string.cancle), new DialogInterface.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (currentFocus instanceof EditText) {
                Rect rect = new Rect();
                currentFocus.getGlobalVisibleRect(rect);
                if (!rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                    currentFocus.clearFocus();
                    DeviceUtils.hideSoftKeyboard(this, this.lnSetings);
                }
            }
        }
        startCountTime();
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.yj.coffeemachines.mvp.contract.SetingsHomeContract.View
    public Context getActivity() {
        return this;
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void hideLoading() {
        IView.CC.$default$hideLoading(this);
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public void initData(@Nullable Bundle bundle) {
        if (Constants.deviceDetail != null) {
            this.mTvSn.setText(Constants.deviceDetail.getDeviceExtNo());
        }
        initialize();
        initsparr();
        startCountTime();
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public int initView(@Nullable Bundle bundle) {
        return R.layout.activity_setingshome;
    }

    @Override // com.jess.arms.mvp.IView
    public void killMyself() {
        finish();
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        killMyself();
    }

    @OnClick({R.id.btn_back, R.id.tv_seting1, R.id.tv_seting1_en, R.id.tv_seting2, R.id.tv_seting2_en, R.id.tv_seting3, R.id.tv_seting3_en, R.id.iv_onlin, R.id.tv_onlin, R.id.tv_out, R.id.tv_out_en})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_back) {
            Constants.isSettingInterface = false;
            Constants.isAgainDialog = true;
            onBackPressed();
        } else {
            if (id == R.id.iv_onlin || id == R.id.tv_onlin) {
                return;
            }
            switch (id) {
                case R.id.tv_out /* 2131297039 */:
                case R.id.tv_out_en /* 2131297040 */:
                    killMyself();
                    return;
                default:
                    switch (id) {
                        case R.id.tv_seting1 /* 2131297082 */:
                        case R.id.tv_seting1_en /* 2131297083 */:
                            replaceFragment(getSupportFragmentManager(), R.id.fl_container, this.fragment1, this.mSparseTags.get(R.id.tv_seting1));
                            return;
                        case R.id.tv_seting2 /* 2131297084 */:
                        case R.id.tv_seting2_en /* 2131297085 */:
                            makeDialogLoginFactory();
                            return;
                        case R.id.tv_seting3 /* 2131297086 */:
                        case R.id.tv_seting3_en /* 2131297087 */:
                            replaceFragment(getSupportFragmentManager(), R.id.fl_container, DIYFragment.newInstance(), this.mSparseTags.get(R.id.tv_seting3));
                            return;
                        default:
                            return;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jess.arms.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ButterKnife.bind(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jess.arms.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        stopCountTime();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MqttStateMessage mqttStateMessage) {
        Resources resources;
        int i;
        ImageView imageView = this.mIvOnlin;
        if (mqttStateMessage.isConnect()) {
            resources = getResources();
            i = R.mipmap.online_icn;
        } else {
            resources = getResources();
            i = R.mipmap.offline_icn;
        }
        imageView.setImageDrawable(resources.getDrawable(i));
        this.mTvOnlin.setText(getString(mqttStateMessage.isConnect() ? R.string.online : R.string.offline));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DeviceInfoBean.InfoBean infoBean) {
        this.mTvSn.setText(infoBean.getDeviceExtNo());
    }

    public void replaceFragment(FragmentManager fragmentManager, int i, Fragment fragment, String str) {
        if (fragmentManager.findFragmentByTag(str) == null) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.replace(i, fragment, str);
            beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            beginTransaction.addToBackStack(str);
            beginTransaction.commit();
        } else {
            fragmentManager.popBackStack(str, 0);
        }
        if (str.equals(this.mSparseTags.get(R.id.tv_seting1))) {
            choseChange(1);
            Constants.addOPSMessage(getString(R.string.seting1));
        } else if (str.equals(this.mSparseTags.get(R.id.tv_seting2))) {
            choseChange(2);
            Constants.addOPSMessage(getString(R.string.seting2));
        } else if (str.equals(this.mSparseTags.get(R.id.tv_seting3))) {
            choseChange(3);
            Constants.addOPSMessage(getString(R.string.seting3));
        }
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSetingsHomeComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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

    public void startCountTime() {
        this.timer.cancel();
        this.timer.start();
    }

    public void stopCountTime() {
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
