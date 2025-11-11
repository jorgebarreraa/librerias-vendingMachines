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
import com.yj.coffeemachines.di.component.DaggerOps8_MaterialSetingComponent;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import com.yj.coffeemachines.greendao.daos.MaterialMessageDao;
import com.yj.coffeemachines.mvp.contract.Ops8_MaterialSetingContract;
import com.yj.coffeemachines.mvp.presenter.Ops8_MaterialSetingPresenter;
import com.yj.coffeemachines.mvp.ui.adapter.MaterialFineTuningAdapter;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class Ops8_MaterialSetingFragment extends BaseFragment<Ops8_MaterialSetingPresenter> implements Ops8_MaterialSetingContract.View {

    @BindView(R.id.ln_reset)
    LinearLayout lnReset;
    private MaterialFineTuningAdapter mAdapter_rv;
    private List<Object> mData;

    @BindView(R.id.step8_rv)
    RecyclerView step8_rv;

    private void initRecycle_hot() {
        this.mAdapter_rv = new MaterialFineTuningAdapter(R.layout.finetuning_item_layout, this.mData);
        ArmsUtils.configRecyclerView(this.step8_rv, new GridLayoutManager(getActivity(), 1));
        this.step8_rv.setAdapter(this.mAdapter_rv);
    }

    public static Ops8_MaterialSetingFragment newInstance() {
        return new Ops8_MaterialSetingFragment();
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
        this.mData.addAll(DBHelper.getMaterialMessageDao().queryBuilder().where(MaterialMessageDao.Properties.IsDeleted.eq(0), new WhereCondition[0]).whereOr(MaterialMessageDao.Properties.RawType.like("%" + Constants.instant1 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.instant2 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.instant3 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.instant4 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.ground1 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.ground2 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.ground3 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.ground4 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.ground5 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.ground6 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.ground7 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.ground8 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.powder1 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.powder2 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.powder3 + "%"), MaterialMessageDao.Properties.RawType.like("%" + Constants.powder4 + "%")).orderAsc(MaterialMessageDao.Properties.Position).list());
        initRecycle_hot();
        this.lnReset.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops8_MaterialSetingFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                for (int i = 0; i < Ops8_MaterialSetingFragment.this.mData.size(); i++) {
                    ((MaterialMessage) Ops8_MaterialSetingFragment.this.mData.get(i)).setLocalMaterialFineTuning(0);
                    Ops8_MaterialSetingFragment.this.mAdapter_rv.notifyDataSetChanged();
                }
            }
        });
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ops8_materialseting, viewGroup, false);
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
        this.mAdapter_rv = null;
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
        DaggerOps8_MaterialSetingComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
