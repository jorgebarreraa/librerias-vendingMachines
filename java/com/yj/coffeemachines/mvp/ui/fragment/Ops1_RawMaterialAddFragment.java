package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.blankj.utilcode.util.ViewUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.AppUtils;
import com.yj.coffeemachines.di.component.DaggerOps1_RawMaterialAddComponent;
import com.yj.coffeemachines.mvp.contract.Ops1_RawMaterialAddContract;
import com.yj.coffeemachines.mvp.model.beans.ReplenishListBack;
import com.yj.coffeemachines.mvp.presenter.Ops1_RawMaterialAddPresenter;
import com.yj.coffeemachines.mvp.ui.adapter.Ops1Adapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class Ops1_RawMaterialAddFragment extends BaseFragment<Ops1_RawMaterialAddPresenter> implements Ops1_RawMaterialAddContract.View {
    private long clickTime = System.currentTimeMillis();
    private Ops1Adapter mAdapter;

    @BindView(R.id.add0)
    LinearLayout mAdd0;

    @BindView(R.id.add100)
    LinearLayout mAdd100;

    @BindView(R.id.add25)
    LinearLayout mAdd25;

    @BindView(R.id.add50)
    LinearLayout mAdd50;

    @BindView(R.id.add75)
    LinearLayout mAdd75;
    private List<Object> mData;

    @BindView(R.id.footlayout)
    LinearLayout mFootlayout;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private void initRecycle() {
        this.mAdapter = new Ops1Adapter(R.layout.materail_item_layout, this.mData);
        ArmsUtils.configRecyclerView(this.mRecyclerview, new GridLayoutManager(getActivity(), AppUtils.isCurOriLand(getActivity()) ? 2 : 1));
        this.mRecyclerview.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops1_RawMaterialAddFragment.1
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (view.getId() == R.id.tv_add) {
                    Ops1_RawMaterialAddFragment.this.makeDialog(i);
                }
                if (view.getId() == R.id.tv_clear) {
                    ((ReplenishListBack.DataBean) Ops1_RawMaterialAddFragment.this.mData.get(i)).setNowResidueQty(0.0d);
                    Ops1_RawMaterialAddFragment.this.mAdapter.notifyItemChanged(i);
                    ((Ops1_RawMaterialAddPresenter) Ops1_RawMaterialAddFragment.this.mPresenter).updata(Ops1_RawMaterialAddFragment.this.mData);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void makeDialog(final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.addproductmatear_layout, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.et_addnumber);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.btn_add);
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(editText, 1);
        }
        builder.setTitle(R.string.inputaddnumber);
        builder.setView(inflate);
        builder.setCancelable(true);
        final AlertDialog create = builder.create();
        create.show();
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops1_RawMaterialAddFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty()) {
                    ArmsUtils.snackbarText(Ops1_RawMaterialAddFragment.this.getString(R.string.inputaddnumber));
                    return;
                }
                ReplenishListBack.DataBean dataBean = (ReplenishListBack.DataBean) Ops1_RawMaterialAddFragment.this.mData.get(i);
                double residueQty = dataBean.getResidueQty();
                double capacity = dataBean.getCapacity();
                double parseInt = Integer.parseInt(r8) + residueQty;
                if (parseInt > capacity) {
                    dataBean.setNowResidueQty(capacity);
                } else {
                    dataBean.setNowResidueQty(parseInt);
                }
                create.dismiss();
                Ops1_RawMaterialAddFragment.this.mAdapter.notifyItemChanged(i);
                ((Ops1_RawMaterialAddPresenter) Ops1_RawMaterialAddFragment.this.mPresenter).updata(Ops1_RawMaterialAddFragment.this.mData);
            }
        });
    }

    public static Ops1_RawMaterialAddFragment newInstance() {
        return new Ops1_RawMaterialAddFragment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBtnGray(boolean z) {
        if (z) {
            this.mAdd100.setBackground(getResources().getDrawable(R.drawable.btn_bottom_confirm_bg));
            this.mAdd75.setBackground(getResources().getDrawable(R.drawable.btn_bottom_confirm_bg));
            this.mAdd50.setBackground(getResources().getDrawable(R.drawable.btn_bottom_confirm_bg));
            this.mAdd25.setBackground(getResources().getDrawable(R.drawable.btn_bottom_confirm_bg));
            this.mAdd0.setBackground(getResources().getDrawable(R.drawable.btn_bottom_confirm_bg));
            return;
        }
        this.mAdd100.setBackground(getResources().getDrawable(R.drawable.btn_bottom_confirm_bg_grey));
        this.mAdd75.setBackground(getResources().getDrawable(R.drawable.btn_bottom_confirm_bg_grey));
        this.mAdd50.setBackground(getResources().getDrawable(R.drawable.btn_bottom_confirm_bg_grey));
        this.mAdd25.setBackground(getResources().getDrawable(R.drawable.btn_bottom_confirm_bg_grey));
        this.mAdd0.setBackground(getResources().getDrawable(R.drawable.btn_bottom_confirm_bg_grey));
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void hideLoading() {
        IView.CC.$default$hideLoading(this);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        this.mData = new ArrayList();
        ((Ops1_RawMaterialAddPresenter) this.mPresenter).getData();
        initRecycle();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ops1_rawmaterialadd, viewGroup, false);
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

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @OnClick({R.id.add100, R.id.add75, R.id.add50, R.id.add25, R.id.add0})
    public void onClick(View view) {
        Log.d(this.TAG, "onClick: 点击:" + this.clickTime);
        if (System.currentTimeMillis() - this.clickTime <= 100) {
            return;
        }
        Log.d(this.TAG, "onClick: 点击1：" + this.clickTime);
        int i = 0;
        setBtnGray(false);
        switch (view.getId()) {
            case R.id.add0 /* 2131296325 */:
                while (i < this.mData.size()) {
                    ((ReplenishListBack.DataBean) this.mData.get(i)).setNowResidueQty(0.0d);
                    i++;
                }
                this.mAdapter.notifyDataSetChanged();
                ((Ops1_RawMaterialAddPresenter) this.mPresenter).updata(this.mData);
                break;
            case R.id.add100 /* 2131296326 */:
                while (i < this.mData.size()) {
                    ReplenishListBack.DataBean dataBean = (ReplenishListBack.DataBean) this.mData.get(i);
                    dataBean.getResidueQty();
                    dataBean.setNowResidueQty(dataBean.getCapacity());
                    i++;
                }
                this.mAdapter.notifyDataSetChanged();
                ((Ops1_RawMaterialAddPresenter) this.mPresenter).updata(this.mData);
                break;
            case R.id.add25 /* 2131296327 */:
                while (i < this.mData.size()) {
                    ReplenishListBack.DataBean dataBean2 = (ReplenishListBack.DataBean) this.mData.get(i);
                    dataBean2.getResidueQty();
                    dataBean2.setNowResidueQty((int) (dataBean2.getCapacity() * 0.25d));
                    i++;
                }
                this.mAdapter.notifyDataSetChanged();
                ((Ops1_RawMaterialAddPresenter) this.mPresenter).updata(this.mData);
                break;
            case R.id.add50 /* 2131296328 */:
                while (i < this.mData.size()) {
                    ReplenishListBack.DataBean dataBean3 = (ReplenishListBack.DataBean) this.mData.get(i);
                    dataBean3.getResidueQty();
                    dataBean3.setNowResidueQty((int) (dataBean3.getCapacity() * 0.5d));
                    i++;
                }
                this.mAdapter.notifyDataSetChanged();
                ((Ops1_RawMaterialAddPresenter) this.mPresenter).updata(this.mData);
                break;
            case R.id.add75 /* 2131296329 */:
                while (i < this.mData.size()) {
                    ReplenishListBack.DataBean dataBean4 = (ReplenishListBack.DataBean) this.mData.get(i);
                    dataBean4.getResidueQty();
                    dataBean4.setNowResidueQty((int) (dataBean4.getCapacity() * 0.75d));
                    i++;
                }
                this.mAdapter.notifyDataSetChanged();
                ((Ops1_RawMaterialAddPresenter) this.mPresenter).updata(this.mData);
                break;
        }
        new Thread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops1_RawMaterialAddFragment.3
            @Override // java.lang.Runnable
            public void run() {
                ViewUtils.runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops1_RawMaterialAddFragment.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d(Ops1_RawMaterialAddFragment.this.TAG, "run: " + Ops1_RawMaterialAddFragment.this.clickTime);
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Ops1_RawMaterialAddFragment.this.setBtnGray(true);
                        Ops1_RawMaterialAddFragment.this.clickTime = System.currentTimeMillis();
                        Log.d(Ops1_RawMaterialAddFragment.this.TAG, "run: " + Ops1_RawMaterialAddFragment.this.clickTime);
                    }
                });
            }
        }).start();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mAdapter = null;
        this.mRecyclerview = null;
        this.mData = null;
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops1_RawMaterialAddContract.View
    public void reFrishData(List<ReplenishListBack.DataBean> list) {
        this.mData.clear();
        this.mData.addAll(list);
        this.mAdapter.notifyDataSetChanged();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerOps1_RawMaterialAddComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
