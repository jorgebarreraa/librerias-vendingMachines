package com.yj.coffeemachines.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.di.component.DaggerOps7_AppUpDataComponent;
import com.yj.coffeemachines.dialog.UpdateDialog;
import com.yj.coffeemachines.mvp.contract.Ops7_AppUpDataContract;
import com.yj.coffeemachines.mvp.model.beans.AppUploadBean;
import com.yj.coffeemachines.mvp.presenter.Ops7_AppUpDataPresenter;
import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;

/* loaded from: classes.dex */
public class Ops7_AppUpDataFragment extends BaseFragment<Ops7_AppUpDataPresenter> implements Ops7_AppUpDataContract.View {

    @BindView(R.id.tv_message)
    TextView mTvMessage;

    @BindView(R.id.tv_sn)
    TextView mTvSN;

    @BindView(R.id.tv_version)
    TextView mTvVersion;

    @BindView(R.id.updataapp)
    LinearLayout mUpdataapp;

    public static Ops7_AppUpDataFragment newInstance() {
        return new Ops7_AppUpDataFragment();
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops7_AppUpDataContract.View
    @Nullable
    public /* bridge */ /* synthetic */ Context getActivity() {
        return super.getActivity();
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        this.mTvSN.setText(getString(R.string.device_no) + Constants.deviceSN());
        this.mTvVersion.setText(String.format(getString(R.string.versiondest), "Main-3.4.52", 2));
        StringBuffer stringBuffer = new StringBuffer();
        if (Constants.deviceDetail != null) {
            stringBuffer.append(Constants.deviceDetail.toMyString());
        }
        if (Constants.deviceTypeDetail != null) {
            stringBuffer.append(Constants.deviceTypeDetail.toMyString());
        }
        this.mTvMessage.setText(stringBuffer.toString());
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ops7_appupdata, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public void killMyself() {
    }

    public /* synthetic */ void lambda$makeDialogNewVersion$0$Ops7_AppUpDataFragment(AppUploadBean appUploadBean) {
        ((Ops7_AppUpDataPresenter) this.mPresenter).downloadApk(appUploadBean.getAppFullPath());
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops7_AppUpDataContract.View
    public void makeDialogNewVersion(final AppUploadBean appUploadBean) {
        final UpdateDialog updateDialog = new UpdateDialog(requireContext());
        updateDialog.setOnUpdateListener(new UpdateDialog.OnUpdateListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops7_AppUpDataFragment$GKOW7CzCxvLsjD2CD89LfUgSdg4
            @Override // com.yj.coffeemachines.dialog.UpdateDialog.OnUpdateListener
            public final void onUpdate() {
                Ops7_AppUpDataFragment.this.lambda$makeDialogNewVersion$0$Ops7_AppUpDataFragment(appUploadBean);
            }
        });
        updateDialog.show();
        ProgressManager.getInstance().addResponseListener(appUploadBean.getAppFullPath(), new ProgressListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops7_AppUpDataFragment.1
            @Override // me.jessyan.progressmanager.ProgressListener
            public void onError(long j, Exception exc) {
                updateDialog.enable(true);
                ArmsUtils.snackbarText(Ops7_AppUpDataFragment.this.getString(R.string.downloadfail));
            }

            @Override // me.jessyan.progressmanager.ProgressListener
            public void onProgress(ProgressInfo progressInfo) {
                updateDialog.enable(progressInfo.isFinish());
                updateDialog.updateProgress(progressInfo.getPercent());
                if (progressInfo.isFinish()) {
                    updateDialog.dismiss();
                }
            }
        });
    }

    @OnClick({R.id.tv_version, R.id.updataapp})
    public void onClick(View view) {
        if (view.getId() != R.id.updataapp) {
            return;
        }
        ((Ops7_AppUpDataPresenter) this.mPresenter).uploadApp();
        MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.updaapp), DataUtils.currentTime(), 3);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerOps7_AppUpDataComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
