package com.yj.coffeemachines.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.di.component.DaggerOps9_LocalFileComponent;
import com.yj.coffeemachines.greendao.beans.FileMessage;
import com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract;
import com.yj.coffeemachines.mvp.presenter.Ops9_LocalFilePresenter;
import com.yj.coffeemachines.mvp.ui.activity.SetingsHomeActivity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class Ops9_LocalFileFragment extends BaseFragment<Ops9_LocalFilePresenter> implements Ops9_LocalFileContract.View {

    @BindView(R.id.ll_refresh)
    LinearLayout llRefresh;
    private BaseQuickAdapter<FileMessage, BaseViewHolder> mAdapter;

    @Inject
    AlertDialog mSportDialog;

    @BindView(R.id.rv_list)
    RecyclerView rvList;

    /* JADX INFO: Access modifiers changed from: private */
    public String fileTypeName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? getString(R.string.unknow) : getString(R.string.voice_name) : getString(R.string.ad_name) : getString(R.string.commdity);
    }

    private void initRecycle() {
        this.mAdapter = new BaseQuickAdapter<FileMessage, BaseViewHolder>(R.layout.item_local_file, new ArrayList()) { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops9_LocalFileFragment.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, FileMessage fileMessage) {
                Ops9_LocalFileFragment ops9_LocalFileFragment;
                int i;
                Ops9_LocalFileFragment ops9_LocalFileFragment2;
                int i2;
                Ops9_LocalFileFragment ops9_LocalFileFragment3;
                int i3;
                BaseViewHolder text = baseViewHolder.setText(R.id.tv_name, fileMessage.getFileName());
                if (fileMessage.isLocalExists()) {
                    ops9_LocalFileFragment = Ops9_LocalFileFragment.this;
                    i = R.string.download_complete;
                } else {
                    ops9_LocalFileFragment = Ops9_LocalFileFragment.this;
                    i = R.string.download_not;
                }
                BaseViewHolder text2 = text.setText(R.id.tv_download_status, ops9_LocalFileFragment.getString(i));
                if (fileMessage.isCheck()) {
                    ops9_LocalFileFragment2 = Ops9_LocalFileFragment.this;
                    i2 = R.string.check_success;
                } else {
                    ops9_LocalFileFragment2 = Ops9_LocalFileFragment.this;
                    i2 = R.string.check_fail;
                }
                BaseViewHolder text3 = text2.setText(R.id.tv_check_status, ops9_LocalFileFragment2.getString(i2)).setTextColor(R.id.tv_download_status, fileMessage.isLocalExists() ? -16711936 : SupportMenu.CATEGORY_MASK).setTextColor(R.id.tv_check_status, fileMessage.isCheck() ? -16711936 : SupportMenu.CATEGORY_MASK).setText(R.id.tv_file_type, Ops9_LocalFileFragment.this.fileTypeName(fileMessage.getFileType())).setText(R.id.tv_use_object, fileMessage.getUseObject()).setText(R.id.tv_file_size, fileMessage.getFileSize());
                if (fileMessage.isLocalExists()) {
                    ops9_LocalFileFragment3 = Ops9_LocalFileFragment.this;
                    i3 = R.string.download_again;
                } else {
                    ops9_LocalFileFragment3 = Ops9_LocalFileFragment.this;
                    i3 = R.string.download;
                }
                text3.setText(R.id.tv_download, ops9_LocalFileFragment3.getString(i3));
                baseViewHolder.addOnClickListener(R.id.tv_download);
            }
        };
        this.mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops9_LocalFileFragment$SAXWTBBeQm-vb714_G2WDm6L6oc
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Ops9_LocalFileFragment.this.lambda$initRecycle$1$Ops9_LocalFileFragment(baseQuickAdapter, view, i);
            }
        });
        if (MyAppLocation.myAppLocation.getResources().getConfiguration().orientation == 2) {
            ArmsUtils.configRecyclerView(this.rvList, new GridLayoutManager(getActivity(), 2));
        } else {
            ArmsUtils.configRecyclerView(this.rvList, new LinearLayoutManager(getActivity()));
        }
        this.rvList.setAdapter(this.mAdapter);
    }

    public static Ops9_LocalFileFragment newInstance() {
        return new Ops9_LocalFileFragment();
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract.View
    @Nullable
    public /* bridge */ /* synthetic */ Context getActivity() {
        return super.getActivity();
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
        ((SetingsHomeActivity) getActivity()).startCountTime();
        getActivity().runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops9_LocalFileFragment.3
            @Override // java.lang.Runnable
            public void run() {
                Ops9_LocalFileFragment.this.mSportDialog.dismiss();
            }
        });
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        initRecycle();
        this.llRefresh.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.-$$Lambda$Ops9_LocalFileFragment$TPdo63MjMhkxN-7Be_lZPgy97q4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Ops9_LocalFileFragment.this.lambda$initData$0$Ops9_LocalFileFragment(view);
            }
        });
        ((Ops9_LocalFilePresenter) this.mPresenter).getFileMessage();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ops9_localfile, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public void killMyself() {
    }

    public /* synthetic */ void lambda$initData$0$Ops9_LocalFileFragment(View view) {
        ((Ops9_LocalFilePresenter) this.mPresenter).getFileMessage();
    }

    public /* synthetic */ void lambda$initRecycle$1$Ops9_LocalFileFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        ((Ops9_LocalFilePresenter) this.mPresenter).downloadFile(this.mAdapter.getItem(i));
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
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract.View
    public void setData(List<FileMessage> list) {
        this.mAdapter.setNewData(list);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerOps9_LocalFileComponent.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override // com.jess.arms.mvp.IView
    public void showLoading() {
        ((SetingsHomeActivity) getActivity()).stopCountTime();
        if (this.mSportDialog.isShowing()) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.fragment.Ops9_LocalFileFragment.2
            @Override // java.lang.Runnable
            public void run() {
                Ops9_LocalFileFragment.this.mSportDialog.setMessage(Ops9_LocalFileFragment.this.getString(R.string.dataloading));
                Ops9_LocalFileFragment.this.mSportDialog.show();
            }
        });
    }

    @Override // com.jess.arms.mvp.IView
    public void showMessage(@NonNull String str) {
        Preconditions.checkNotNull(str);
        ArmsUtils.snackbarText(str);
    }
}
