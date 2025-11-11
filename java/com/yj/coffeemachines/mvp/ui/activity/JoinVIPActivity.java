package com.yj.coffeemachines.mvp.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.QRCodeUtils;
import com.yj.coffeemachines.di.component.DaggerJoinVIPComponent;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.JoinVIPContract;
import com.yj.coffeemachines.mvp.model.beans.DeviceInfoBean;
import com.yj.coffeemachines.mvp.presenter.JoinVIPPresenter;
import javax.inject.Inject;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class JoinVIPActivity extends BaseActivity<JoinVIPPresenter> implements JoinVIPContract.View {

    @Inject
    AlertDialog mAlertDialog;

    @BindView(R.id.btn_back)
    RelativeLayout mBtnBack;

    @BindView(R.id.iv_phone)
    ImageView mIvPhone;

    @BindView(R.id.iv_sn)
    ImageView mIvSn;

    @BindView(R.id.iv_vipqrcode)
    ImageView mIvVipqrcode;

    @BindView(R.id.tv_joinvip)
    TextView mTvJoinvip;

    @BindView(R.id.tv_joinvip_en)
    TextView mTvJoinvipEn;

    @BindView(R.id.tv_phone)
    TextView mTvPhone;

    @BindView(R.id.tv_sn)
    TextView mTvSn;

    @Override // com.yj.coffeemachines.mvp.contract.JoinVIPContract.View
    public Context getActivity() {
        return this;
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public void initData(@Nullable Bundle bundle) {
        this.mIvVipqrcode.setImageBitmap(QRCodeUtils.generateBitmap(Constants.deviceSN(), 300, 300));
        if (Constants.deviceDetail != null) {
            if (!Tools.toString(Constants.deviceDetail.getServicePhone()).equals("")) {
                this.mTvPhone.setText(Constants.deviceDetail.getServicePhone());
            }
            this.mTvSn.setText(Constants.deviceDetail.getDeviceExtNo());
        }
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public int initView(@Nullable Bundle bundle) {
        return R.layout.activity_joinvip;
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

    @OnClick({R.id.btn_back})
    public void onClick(View view) {
        if (view.getId() != R.id.btn_back) {
            return;
        }
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jess.arms.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DeviceInfoBean.InfoBean infoBean) {
        if (!Tools.toString(infoBean.getServicePhone()).equals("")) {
            this.mTvPhone.setText(infoBean.getServicePhone());
        }
        this.mTvSn.setText(infoBean.getDeviceExtNo());
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerJoinVIPComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
