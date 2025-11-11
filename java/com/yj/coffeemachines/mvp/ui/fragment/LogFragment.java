package com.yj.coffeemachines.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.OnClick;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.di.component.DaggerLogComponent;
import com.yj.coffeemachines.mvp.contract.LogContract;
import com.yj.coffeemachines.mvp.message.IMessage;
import com.yj.coffeemachines.mvp.message.LogManager;
import com.yj.coffeemachines.mvp.presenter.LogPresenter;
import com.yj.coffeemachines.mvp.ui.adapter.LogAdapter;
import java.util.Objects;

/* loaded from: classes.dex */
public class LogFragment extends BaseFragment<LogPresenter> implements LogContract.View {
    private LogAdapter mAdapter;
    private Boolean mAutoEnd = false;

    @BindView(R.id.btn_auto_end)
    Button mBtnAutoEnd;

    @BindView(R.id.btn_clear_log)
    Button mBtnClearLog;

    @BindView(R.id.btn_whether_hexadecimal)
    Button mBtnWhetherHexadecimal;

    @BindView(R.id.lv_logs)
    ListView mLvLogs;

    public static LogFragment newInstance() {
        return new LogFragment();
    }

    public void add(final IMessage iMessage) {
        ((FragmentActivity) Objects.requireNonNull(getActivity())).runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.LogFragment.1
            @Override // java.lang.Runnable
            public void run() {
                LogManager.instance().add(iMessage);
                LogFragment.this.updateList();
            }
        });
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void hideLoading() {
        IView.CC.$default$hideLoading(this);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        this.mAdapter = new LogAdapter();
        this.mLvLogs.setAdapter((ListAdapter) this.mAdapter);
        updateAutoEndButton();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_log, viewGroup, false);
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

    @OnClick({R.id.btn_clear_log, R.id.btn_auto_end, R.id.btn_whether_hexadecimal})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_auto_end) {
            LogManager.instance().changAutoEnd();
            updateAutoEndButton();
        } else if (id == R.id.btn_clear_log) {
            LogManager.instance().clear();
            this.mAdapter.notifyDataSetChanged();
        } else {
            if (id != R.id.btn_whether_hexadecimal) {
                return;
            }
            if (MyAppLocation.myAppLocation.mSerialDataService.mConversionNotice) {
                MyAppLocation.myAppLocation.mSerialDataService.mConversionNotice = false;
            } else {
                MyAppLocation.myAppLocation.mSerialDataService.mConversionNotice = true;
            }
        }
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLogComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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

    public void updateAutoEndButton() {
        if (getView() != null) {
            if (!LogManager.instance().isAutoEnd()) {
                this.mAutoEnd = false;
                this.mBtnAutoEnd.setText(R.string.yesthnewlog);
            } else {
                this.mBtnAutoEnd.setText(R.string.nothenewlog);
                this.mLvLogs.setSelection(this.mAdapter.getCount() - 1);
                this.mAutoEnd = true;
            }
        }
    }

    public void updateList() {
        if (this.mAutoEnd.booleanValue() && getView() != null) {
            this.mAdapter.notifyDataSetChanged();
            if (LogManager.instance().isAutoEnd()) {
                this.mLvLogs.setSelection(this.mAdapter.getCount() - 1);
            }
        }
    }
}
