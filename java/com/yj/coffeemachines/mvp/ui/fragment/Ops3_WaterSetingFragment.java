package com.yj.coffeemachines.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.di.component.DaggerOps3_WaterSetingComponent;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import com.yj.coffeemachines.greendao.daos.MaterialMessageDao;
import com.yj.coffeemachines.mvp.contract.Ops3_WaterSetingContract;
import com.yj.coffeemachines.mvp.presenter.Ops3_WaterSetingPresenter;
import com.yj.coffeemachines.mvp.ui.adapter.FineTuningAdapterCold;
import com.yj.coffeemachines.mvp.ui.adapter.FineTuningAdapterHot;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class Ops3_WaterSetingFragment extends BaseFragment<Ops3_WaterSetingPresenter> implements Ops3_WaterSetingContract.View {

    @BindView(R.id.ln_reset)
    LinearLayout lnReset;
    private FineTuningAdapterCold mAdapter_cold;
    private FineTuningAdapterHot mAdapter_hot;

    @BindView(R.id.cold_recycle)
    RecyclerView mColdRecycle;
    private List<Object> mData;

    @BindView(R.id.hot_recycle)
    RecyclerView mHotRecycle;

    private void initRecycle_cold() {
        this.mAdapter_cold = new FineTuningAdapterCold(R.layout.finetuning_item_layout, this.mData);
        ArmsUtils.configRecyclerView(this.mColdRecycle, new GridLayoutManager(getActivity(), 1));
        this.mColdRecycle.setAdapter(this.mAdapter_cold);
    }

    private void initRecycle_hot() {
        this.mAdapter_hot = new FineTuningAdapterHot(R.layout.finetuning_item_layout, this.mData);
        ArmsUtils.configRecyclerView(this.mHotRecycle, new GridLayoutManager(getActivity(), 1));
        this.mHotRecycle.setAdapter(this.mAdapter_hot);
    }

    public static Ops3_WaterSetingFragment newInstance() {
        return new Ops3_WaterSetingFragment();
    }

    private void saveChange(List<Object> list) {
        MaterialMessageDao materialMessageDao = DBHelper.getMaterialMessageDao();
        for (int i = 0; i < list.size(); i++) {
            materialMessageDao.update((MaterialMessage) list.get(i));
        }
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        this.mData = new ArrayList();
        this.mData.clear();
        this.mData.addAll(DBHelper.getMaterialMessageDao().queryBuilder().where(MaterialMessageDao.Properties.IsDeleted.eq(0), new WhereCondition[0]).whereOr(MaterialMessageDao.Properties.RawType.like("%" + Constants.instant1 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.instant2 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.instant3 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.instant4 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.water1 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.water2 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.water3 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.water4 + "%")).orderAsc(MaterialMessageDao.Properties.Position).list());
        initRecycle_cold();
        initRecycle_hot();
        this.lnReset.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops3_WaterSetingFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                for (int i = 0; i < Ops3_WaterSetingFragment.this.mData.size(); i++) {
                    MaterialMessage materialMessage = (MaterialMessage) Ops3_WaterSetingFragment.this.mData.get(i);
                    materialMessage.setLocalFineTuning_hot(0);
                    materialMessage.setLocalFineTuning_cold(0);
                    Ops3_WaterSetingFragment.this.mAdapter_hot.notifyDataSetChanged();
                    Ops3_WaterSetingFragment.this.mAdapter_cold.notifyDataSetChanged();
                }
            }
        });
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ops3_waterseting, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public void killMyself() {
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mAdapter_hot = null;
        this.mAdapter_cold = null;
        this.mData = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        saveChange(this.mData);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerOps3_WaterSetingComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
