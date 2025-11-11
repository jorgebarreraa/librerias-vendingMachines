package com.yj.coffeemachines.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.di.component.DaggerOps6_LogcatComponent;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.OpsActionMessage;
import com.yj.coffeemachines.greendao.daos.OpsActionMessageDao;
import com.yj.coffeemachines.mvp.contract.Ops6_LogcatContract;
import com.yj.coffeemachines.mvp.presenter.Ops6_LogcatPresenter;
import com.yj.coffeemachines.mvp.ui.adapter.Ops6Adapter;
import com.yj.coffeemachines.mvp.ui.widget.MyDatePickerDialog;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class Ops6_LogcatFragment extends BaseFragment<Ops6_LogcatPresenter> implements Ops6_LogcatContract.View {
    Ops6Adapter mAdapter;

    @BindView(R.id.btn_query)
    Button mBtnQuery;
    List<Object> mData;

    @BindView(R.id.logcat_recycleview)
    RecyclerView mLogcatRecycleview;

    @BindView(R.id.tv_querytime)
    TextView mTvQuerytime;

    private void configRecycleView() {
        ArmsUtils.configRecyclerView(this.mLogcatRecycleview, new GridLayoutManager(getActivity(), 1));
    }

    private void initRecycle() {
        configRecycleView();
        this.mLogcatRecycleview.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops6_LogcatFragment.1
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadData(String str) {
        List<OpsActionMessage> list = DBHelper.getOpsActionMessageDao().queryBuilder().where(OpsActionMessageDao.Properties.Par0.eq(str), new WhereCondition[0]).orderAsc(OpsActionMessageDao.Properties.Time).list();
        this.mData.clear();
        this.mData.addAll(list);
        this.mAdapter.notifyDataSetChanged();
    }

    private void makeDialog() {
        MyDatePickerDialog myDatePickerDialog = new MyDatePickerDialog(getActivity(), 3);
        myDatePickerDialog.setOnImgDialogListener(new MyDatePickerDialog.OnImgDialogListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops6_LogcatFragment.2
            @Override // com.yj.coffeemachines.mvp.ui.widget.MyDatePickerDialog.OnImgDialogListener
            public void cancle() {
            }

            @Override // com.yj.coffeemachines.mvp.ui.widget.MyDatePickerDialog.OnImgDialogListener
            public void onItemImg(int i, int i2, int i3, String str) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(i, i2, i3);
                String timeYYMMDD = DataUtils.getTimeYYMMDD(Long.valueOf(calendar.getTime().getTime()));
                Ops6_LogcatFragment.this.mTvQuerytime.setText(timeYYMMDD);
                Ops6_LogcatFragment.this.loadData(timeYYMMDD);
            }
        });
        myDatePickerDialog.myShow();
    }

    public static Ops6_LogcatFragment newInstance() {
        return new Ops6_LogcatFragment();
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        this.mData = new ArrayList();
        this.mAdapter = new Ops6Adapter(R.layout.logcat_item_layout, this.mData);
        initRecycle();
        this.mTvQuerytime.setText(DataUtils.getTimeYYMMDD(Long.valueOf(System.currentTimeMillis())));
        loadData(DataUtils.getTimeYYMMDD(Long.valueOf(System.currentTimeMillis())));
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ops6_logcat, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public void killMyself() {
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @OnClick({R.id.tv_querytime, R.id.btn_query})
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_query) {
            if (id != R.id.tv_querytime) {
                return;
            }
            makeDialog();
        } else {
            List<OpsActionMessage> list = DBHelper.getOpsActionMessageDao().queryBuilder().where(OpsActionMessageDao.Properties.Par0.eq(this.mTvQuerytime.getText().toString()), new WhereCondition[0]).list();
            this.mData.clear();
            this.mData.addAll(list);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mAdapter = null;
        this.mLogcatRecycleview = null;
        this.mData = null;
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerOps6_LogcatComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
