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
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.di.component.DaggerOpsComponent;
import com.yj.coffeemachines.mvp.contract.OpsContract;
import com.yj.coffeemachines.mvp.presenter.OpsPresenter;

/* loaded from: classes.dex */
public class OpsFragment extends BaseFragment<OpsPresenter> implements OpsContract.View {

    @BindView(R.id.ll_ops1)
    LinearLayout ll_ops1;

    @BindView(R.id.ll_ops2)
    LinearLayout ll_ops2;

    @BindView(R.id.ll_ops3)
    LinearLayout ll_ops3;

    @BindView(R.id.ll_ops4)
    LinearLayout ll_ops4;

    @BindView(R.id.ll_ops5)
    LinearLayout ll_ops5;

    @BindView(R.id.ll_ops6)
    LinearLayout ll_ops6;

    @BindView(R.id.ll_ops7)
    LinearLayout ll_ops7;

    @BindView(R.id.ll_ops8)
    LinearLayout ll_ops8;

    @BindView(R.id.ll_ops9)
    LinearLayout ll_ops9;

    @BindView(R.id.ops_framelayout)
    FrameLayout mOpsFramelayout;
    private SparseArray<String> mSparseTags = new SparseArray<>();

    @BindView(R.id.titlelayout)
    LinearLayout mTitlelayout;

    @BindView(R.id.tv_ops1)
    TextView mTvOps1;

    @BindView(R.id.tv_ops1_en)
    TextView mTvOps1En;

    @BindView(R.id.tv_ops2)
    TextView mTvOps2;

    @BindView(R.id.tv_ops2_en)
    TextView mTvOps2En;

    @BindView(R.id.tv_ops3)
    TextView mTvOps3;

    @BindView(R.id.tv_ops3_en)
    TextView mTvOps3En;

    @BindView(R.id.tv_ops4)
    TextView mTvOps4;

    @BindView(R.id.tv_ops4_en)
    TextView mTvOps4En;

    @BindView(R.id.tv_ops5)
    TextView mTvOps5;

    @BindView(R.id.tv_ops5_en)
    TextView mTvOps5En;

    @BindView(R.id.tv_ops6)
    TextView mTvOps6;

    @BindView(R.id.tv_ops6_en)
    TextView mTvOps6En;

    @BindView(R.id.tv_ops7)
    TextView mTvOps7;

    @BindView(R.id.tv_ops7_en)
    TextView mTvOps7En;

    @BindView(R.id.tv_ops8)
    TextView mTvOps8;

    @BindView(R.id.tv_ops8_en)
    TextView mTvOps8En;

    @BindView(R.id.tv_ops9)
    TextView mTvOps9;

    @BindView(R.id.tv_ops9_en)
    TextView mTvOps9En;

    @BindView(R.id.unlock)
    LinearLayout mUnlock;

    private void choseChange(int i) {
        step1(1 == i);
        step2(2 == i);
        step3(3 == i);
        step4(4 == i);
        step5(5 == i);
        step6(6 == i);
        step7(7 == i);
        step8(8 == i);
        step9(9 == i);
    }

    private void initfl() {
    }

    private void initsparr() {
        this.mSparseTags.put(R.id.tv_ops1, "tv_ops1");
        this.mSparseTags.put(R.id.tv_ops2, "tv_ops2");
        this.mSparseTags.put(R.id.tv_ops3, "tv_ops3");
        this.mSparseTags.put(R.id.tv_ops4, "tv_ops4");
        this.mSparseTags.put(R.id.tv_ops5, "tv_ops5");
        this.mSparseTags.put(R.id.tv_ops6, "tv_ops6");
        this.mSparseTags.put(R.id.tv_ops7, "tv_ops7");
        this.mSparseTags.put(R.id.tv_ops8, "tv_ops8");
        this.mSparseTags.put(R.id.tv_ops9, "tv_ops9");
        replaceFragment(getChildFragmentManager(), R.id.ops_framelayout, new Ops1_RawMaterialAddFragment(), this.mSparseTags.get(R.id.tv_ops1));
    }

    public static OpsFragment newInstance() {
        return new OpsFragment();
    }

    private void step1(boolean z) {
        this.ll_ops1.setBackgroundColor(ContextCompat.getColor(requireContext(), z ? R.color.bg_setting_head : R.color.white));
    }

    private void step2(boolean z) {
        this.ll_ops2.setBackgroundColor(ContextCompat.getColor(requireContext(), z ? R.color.bg_setting_head : R.color.white));
    }

    private void step3(boolean z) {
        this.ll_ops3.setBackgroundColor(ContextCompat.getColor(requireContext(), z ? R.color.bg_setting_head : R.color.white));
    }

    private void step4(boolean z) {
        this.ll_ops4.setBackgroundColor(ContextCompat.getColor(requireContext(), z ? R.color.bg_setting_head : R.color.white));
    }

    private void step5(boolean z) {
        this.ll_ops5.setBackgroundColor(ContextCompat.getColor(requireContext(), z ? R.color.bg_setting_head : R.color.white));
    }

    private void step6(boolean z) {
        this.ll_ops6.setBackgroundColor(ContextCompat.getColor(requireContext(), z ? R.color.bg_setting_head : R.color.white));
    }

    private void step7(boolean z) {
        this.ll_ops7.setBackgroundColor(ContextCompat.getColor(requireContext(), z ? R.color.bg_setting_head : R.color.white));
    }

    private void step8(boolean z) {
        this.ll_ops8.setBackgroundColor(ContextCompat.getColor(requireContext(), z ? R.color.bg_setting_head : R.color.white));
    }

    private void step9(boolean z) {
        this.ll_ops9.setBackgroundColor(ContextCompat.getColor(requireContext(), z ? R.color.bg_setting_head : R.color.white));
    }

    public int getCheckCount() {
        return getChildFragmentManager().getBackStackEntryCount();
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        initsparr();
        initfl();
        if (Constants.devConfig().isLeft6()) {
            this.mUnlock.setVisibility(0);
        } else {
            this.mUnlock.setVisibility(8);
        }
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ops, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public void killMyself() {
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @OnClick({R.id.ll_ops1, R.id.ll_ops2, R.id.ll_ops3, R.id.ll_ops4, R.id.ll_ops5, R.id.ll_ops6, R.id.ll_ops7, R.id.ll_ops8, R.id.ll_ops9, R.id.unlock})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.unlock) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_LOCK, 1, 0));
            Constants.isunlock = true;
            MyAppLocation.myAppLocation.myMqttService.addOpsLog(getString(R.string.unlockdoor), DataUtils.currentTime(), 3);
            return;
        }
        switch (id) {
            case R.id.ll_ops1 /* 2131296586 */:
                replaceFragment(getChildFragmentManager(), R.id.ops_framelayout, Ops1_RawMaterialAddFragment.newInstance(), this.mSparseTags.get(R.id.tv_ops1));
                return;
            case R.id.ll_ops2 /* 2131296587 */:
                replaceFragment(getChildFragmentManager(), R.id.ops_framelayout, Ops2_RawMaterialSetingFragment.newInstance(), this.mSparseTags.get(R.id.tv_ops2));
                return;
            case R.id.ll_ops3 /* 2131296588 */:
                replaceFragment(getChildFragmentManager(), R.id.ops_framelayout, Ops3_WaterSetingFragment.newInstance(), this.mSparseTags.get(R.id.tv_ops3));
                return;
            case R.id.ll_ops4 /* 2131296589 */:
                replaceFragment(getChildFragmentManager(), R.id.ops_framelayout, Ops4_FeatureSettingFragment.newInstance(), this.mSparseTags.get(R.id.tv_ops4));
                return;
            case R.id.ll_ops5 /* 2131296590 */:
                replaceFragment(getChildFragmentManager(), R.id.ops_framelayout, Ops5_FeatureTestFragment.newInstance(), this.mSparseTags.get(R.id.tv_ops5));
                return;
            case R.id.ll_ops6 /* 2131296591 */:
                replaceFragment(getChildFragmentManager(), R.id.ops_framelayout, Ops6_LogcatFragment.newInstance(), this.mSparseTags.get(R.id.tv_ops6));
                return;
            case R.id.ll_ops7 /* 2131296592 */:
                replaceFragment(getChildFragmentManager(), R.id.ops_framelayout, Ops7_AppUpDataFragment.newInstance(), this.mSparseTags.get(R.id.tv_ops7));
                return;
            case R.id.ll_ops8 /* 2131296593 */:
                replaceFragment(getChildFragmentManager(), R.id.ops_framelayout, Ops8_MaterialSetingFragment.newInstance(), this.mSparseTags.get(R.id.tv_ops8));
                return;
            case R.id.ll_ops9 /* 2131296594 */:
                replaceFragment(getChildFragmentManager(), R.id.ops_framelayout, Ops9_LocalFileFragment.newInstance(), this.mSparseTags.get(R.id.tv_ops9));
                return;
            default:
                return;
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
        if (str.equals(this.mSparseTags.get(R.id.tv_ops1))) {
            choseChange(1);
            Constants.addOPSMessage(getString(R.string.ops1));
            return;
        }
        if (str.equals(this.mSparseTags.get(R.id.tv_ops2))) {
            choseChange(2);
            Constants.addOPSMessage(getString(R.string.ops2));
            return;
        }
        if (str.equals(this.mSparseTags.get(R.id.tv_ops3))) {
            choseChange(3);
            Constants.addOPSMessage(getString(R.string.ops3));
            return;
        }
        if (str.equals(this.mSparseTags.get(R.id.tv_ops8))) {
            choseChange(8);
            Constants.addOPSMessage(getString(R.string.ops8));
            return;
        }
        if (str.equals(this.mSparseTags.get(R.id.tv_ops4))) {
            choseChange(4);
            Constants.addOPSMessage(getString(R.string.ops4));
            return;
        }
        if (str.equals(this.mSparseTags.get(R.id.tv_ops5))) {
            choseChange(5);
            Constants.addOPSMessage(getString(R.string.ops5));
            return;
        }
        if (str.equals(this.mSparseTags.get(R.id.tv_ops6))) {
            choseChange(6);
            Constants.addOPSMessage(getString(R.string.ops6));
        } else if (str.equals(this.mSparseTags.get(R.id.tv_ops7))) {
            choseChange(7);
            Constants.addOPSMessage(getString(R.string.ops7));
        } else if (str.equals(this.mSparseTags.get(R.id.tv_ops9))) {
            choseChange(9);
            Constants.addOPSMessage(getString(R.string.ops9));
            Constants.addOPSMessage(getString(R.string.ops9));
        }
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void setcheck(String str) {
        char c;
        switch (str.hashCode()) {
            case -954363044:
                if (str.equals("tv_ops1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -954363043:
                if (str.equals("tv_ops2")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -954363042:
                if (str.equals("tv_ops3")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -954363041:
                if (str.equals("tv_ops4")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -954363040:
                if (str.equals("tv_ops5")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -954363039:
                if (str.equals("tv_ops6")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -954363038:
                if (str.equals("tv_ops7")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -954363037:
                if (str.equals("tv_ops8")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -954363036:
                if (str.equals("tv_ops9")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                choseChange(1);
                break;
            case 1:
                choseChange(2);
                break;
            case 2:
                choseChange(3);
                break;
            case 3:
                choseChange(8);
                break;
            case 4:
                choseChange(4);
                break;
            case 5:
                choseChange(5);
                break;
            case 6:
                choseChange(6);
                break;
            case 7:
                choseChange(7);
                break;
            case '\b':
                choseChange(9);
                break;
        }
        getChildFragmentManager().popBackStackImmediate();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerOpsComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
