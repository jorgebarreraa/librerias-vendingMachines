package com.yj.coffeemachines.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.OnClick;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.di.component.DaggerFactoryComponent;
import com.yj.coffeemachines.eventbusbean.LockMessage;
import com.yj.coffeemachines.mvp.contract.FactoryContract;
import com.yj.coffeemachines.mvp.presenter.FactoryPresenter;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class FactoryFragment extends BaseFragment<FactoryPresenter> implements FactoryContract.View {

    @BindView(R.id.factroy_framelayout)
    FrameLayout mFactroyFramelayout;

    @BindView(R.id.lay01)
    LinearLayout mLay01;

    @BindView(R.id.lay02)
    LinearLayout mLay02;
    private SparseArray<String> mSparseTags = new SparseArray<>();

    @BindView(R.id.tv_factroy1)
    TextView mTvFactroy1;

    @BindView(R.id.tv_factroy1_en)
    TextView mTvFactroy1En;

    @BindView(R.id.tv_factroy2)
    TextView mTvFactroy2;

    @BindView(R.id.tv_factroy2_en)
    TextView mTvFactroy2En;

    @BindView(R.id.unlock)
    LinearLayout mUnlock;

    private void choseChange(int i) {
        if (i == 1) {
            step1(true);
            step2(false);
        } else {
            if (i != 2) {
                return;
            }
            step1(false);
            step2(true);
        }
    }

    private void initsparr() {
        this.mSparseTags.put(R.id.tv_factroy1, "tv_factroy1");
        this.mSparseTags.put(R.id.tv_factroy2, "tv_factroy2");
        replaceFragment(getChildFragmentManager(), R.id.factroy_framelayout, Factory1_DevConfigFragment.newInstance(), this.mSparseTags.get(R.id.tv_factroy1));
    }

    public static FactoryFragment newInstance() {
        return new FactoryFragment();
    }

    private void step1(boolean z) {
        this.mLay01.setBackgroundColor(ContextCompat.getColor(requireContext(), z ? R.color.bg_setting_head : R.color.white));
    }

    private void step2(boolean z) {
        this.mLay02.setBackgroundColor(ContextCompat.getColor(requireContext(), z ? R.color.bg_setting_head : R.color.white));
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void hideLoading() {
        IView.CC.$default$hideLoading(this);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        initsparr();
        if (Constants.devConfig().isLeft6()) {
            this.mUnlock.setVisibility(0);
        } else {
            this.mUnlock.setVisibility(8);
        }
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_factory, viewGroup, false);
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

    @OnClick({R.id.lay01, R.id.lay02, R.id.unlock})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay01 /* 2131296567 */:
                replaceFragment(getChildFragmentManager(), R.id.factroy_framelayout, Factory1_DevConfigFragment.newInstance(), this.mSparseTags.get(R.id.tv_factroy1));
                return;
            case R.id.lay02 /* 2131296568 */:
                replaceFragment(getChildFragmentManager(), R.id.factroy_framelayout, Factory2_SeralPortConfigFragment.newInstance(), this.mSparseTags.get(R.id.tv_factroy2));
                return;
            case R.id.unlock /* 2131297107 */:
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_LOCK, 1, 0));
                Constants.isunlock = true;
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.unlockdoor), DataUtils.currentTime(), 3);
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LockMessage lockMessage) {
        if (lockMessage.isLunck()) {
            this.mUnlock.setVisibility(0);
        } else {
            this.mUnlock.setVisibility(8);
        }
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
        if (str.equals(this.mSparseTags.get(R.id.tv_factroy1))) {
            choseChange(1);
            Constants.addOPSMessage(getString(R.string.configuration));
        } else if (str.equals(this.mSparseTags.get(R.id.tv_factroy2))) {
            choseChange(2);
            Constants.addOPSMessage(getString(R.string.serialport));
        } else {
            choseChange(3);
            Constants.addOPSMessage(getString(R.string.custom_configuration));
        }
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void setcheck(String str) {
        char c;
        switch (str.hashCode()) {
            case 895260510:
                if (str.equals("tv_factroy1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 895260511:
                if (str.equals("tv_factroy2")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            choseChange(1);
        } else if (c == 1) {
            choseChange(1);
        }
        getChildFragmentManager().popBackStackImmediate();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerFactoryComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
