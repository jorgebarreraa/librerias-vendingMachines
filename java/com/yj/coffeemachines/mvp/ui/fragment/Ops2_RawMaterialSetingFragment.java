package com.yj.coffeemachines.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.AppUtils;
import com.yj.coffeemachines.di.component.DaggerOps2_RawMaterialSetingComponent;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import com.yj.coffeemachines.greendao.daos.MaterialMessageDao;
import com.yj.coffeemachines.mvp.contract.Ops2_RawMaterialSetingContract;
import com.yj.coffeemachines.mvp.model.beans.ReplenishListBack;
import com.yj.coffeemachines.mvp.presenter.Ops2_RawMaterialSetingPresenter;
import com.yj.coffeemachines.mvp.ui.adapter.Ops2Adapter;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class Ops2_RawMaterialSetingFragment extends BaseFragment<Ops2_RawMaterialSetingPresenter> implements Ops2_RawMaterialSetingContract.View {
    private Ops2Adapter mAdapter;
    private List<Object> mData;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @BindView(R.id.submit)
    View submit;

    private void initRecycle() {
        this.mAdapter = new Ops2Adapter(R.layout.materailsetting_item_layout, this.mData);
        ArmsUtils.configRecyclerView(this.mRecyclerview, new GridLayoutManager(getActivity(), AppUtils.isCurOriLand(getActivity()) ? 2 : 1));
        this.mRecyclerview.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops2_RawMaterialSetingFragment.1
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ReplenishListBack.DataBean dataBean = (ReplenishListBack.DataBean) Ops2_RawMaterialSetingFragment.this.mData.get(i);
                dataBean.setResidueQty(dataBean.getCapacity());
                Ops2_RawMaterialSetingFragment.this.mAdapter.notifyItemChanged(i);
            }
        });
    }

    public static Ops2_RawMaterialSetingFragment newInstance() {
        return new Ops2_RawMaterialSetingFragment();
    }

    private void saveChange(List<Object> list) {
        MaterialMessageDao materialMessageDao = DBHelper.getMaterialMessageDao();
        for (int i = 0; i < list.size(); i++) {
            materialMessageDao.update((MaterialMessage) list.get(i));
        }
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void hideLoading() {
        IView.CC.$default$hideLoading(this);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        this.mData = new ArrayList();
        this.mData.clear();
        this.mData.addAll(DBHelper.getMaterialMessageDao().queryBuilder().where(MaterialMessageDao.Properties.IsDeleted.eq(0), new WhereCondition[0]).where(MaterialMessageDao.Properties.RawType.notEq(Constants.water1), new WhereCondition[0]).where(MaterialMessageDao.Properties.RawType.notEq(Constants.water2), new WhereCondition[0]).where(MaterialMessageDao.Properties.RawType.notEq(Constants.water3), new WhereCondition[0]).where(MaterialMessageDao.Properties.RawType.notEq(Constants.water4), new WhereCondition[0]).where(MaterialMessageDao.Properties.RawType.notEq(Constants.ice1), new WhereCondition[0]).where(MaterialMessageDao.Properties.RawType.notEq(Constants.ice2), new WhereCondition[0]).where(MaterialMessageDao.Properties.RawType.notEq(Constants.ice3), new WhereCondition[0]).where(MaterialMessageDao.Properties.RawType.notEq(Constants.ice4), new WhereCondition[0]).orderAsc(MaterialMessageDao.Properties.Position).list());
        initRecycle();
        this.submit.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops2_RawMaterialSetingFragment$HM0nhluKGdiE3nXvB3SU85a8HAY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Ops2_RawMaterialSetingFragment.this.lambda$initData$0$Ops2_RawMaterialSetingFragment(view);
            }
        });
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ops2_rawmaterialseting, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public void killMyself() {
    }

    public /* synthetic */ void lambda$initData$0$Ops2_RawMaterialSetingFragment(View view) {
        saveChange(this.mData);
        ArmsUtils.snackbarText(getString(R.string.raw_successfully));
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mAdapter = null;
        this.mRecyclerview = null;
        this.mData = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerOps2_RawMaterialSetingComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
