package com.yj.coffeemachines.dialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.language.MultiLanguages;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.bean.LanguageItem;
import com.yj.coffeemachines.helper.LanguageHelper;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.ui.activity.HomeActivity;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class SetLanguageDialog extends BaseDialog {
    private final boolean isNowSet;
    private final List<LanguageItem> languageItems;
    private onConfirmListener onConfirmListener;
    private TextView tvClose;
    private int width;

    /* loaded from: classes.dex */
    public interface onConfirmListener {
        void onConfirm(LanguageItem languageItem);
    }

    public SetLanguageDialog(@NonNull Context context, boolean z) {
        super(context);
        this.languageItems = LanguageHelper.languageOptions();
        this.isNowSet = z;
    }

    @NonNull
    private BaseQuickAdapter<LanguageItem, BaseViewHolder> getAdapter() {
        BaseQuickAdapter<LanguageItem, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<LanguageItem, BaseViewHolder>(R.layout.item_set_language, this.languageItems) { // from class: com.yj.coffeemachines.dialog.SetLanguageDialog.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(@NonNull BaseViewHolder baseViewHolder, LanguageItem languageItem) {
                baseViewHolder.setText(R.id.tv_native_language, languageItem.getNativeName());
                baseViewHolder.setText(R.id.tv_language, languageItem.getName());
            }
        };
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$SetLanguageDialog$V7mgnuP2VA80YrxJIw6Di03q-Ic
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                SetLanguageDialog.this.lambda$getAdapter$2$SetLanguageDialog(baseQuickAdapter2, view, i);
            }
        });
        return baseQuickAdapter;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public /* synthetic */ void lambda$getAdapter$2$SetLanguageDialog(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (!this.isNowSet) {
            onConfirmListener onconfirmlistener = this.onConfirmListener;
            if (onconfirmlistener != null) {
                onconfirmlistener.onConfirm(this.languageItems.get(i));
                dismiss();
                return;
            }
            return;
        }
        LanguageHelper.isLanguageChange(this.languageItems.get(i));
        ArmsUtils.snackbarText(StringUtils.getString(R.string.havebeensaved) + StringUtils.getString(R.string.language_change_restart));
        MultiLanguages.setAppLanguage(getContext(), new Locale(LanguageHelper.language().getLangCode()));
        new Handler().postDelayed(new Runnable() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$SetLanguageDialog$w88EyQdehXcXt7jN2xrOrEtvWLc
            @Override // java.lang.Runnable
            public final void run() {
                SetLanguageDialog.this.lambda$null$1$SetLanguageDialog();
            }
        }, 2000L);
    }

    public /* synthetic */ void lambda$null$1$SetLanguageDialog() {
        Tools.upLocalLog("切换语言重启应用");
        Intent intent = new Intent(getContext(), (Class<?>) HomeActivity.class);
        intent.setFlags(268468224);
        getContext().startActivity(intent);
        Process.killProcess(Process.myPid());
    }

    public /* synthetic */ void lambda$onCreate$0$SetLanguageDialog(View view) {
        dismiss();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_set_language);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        if (window != null) {
            Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            defaultDisplay.getSize(new Point());
            if (DeviceUtils.isLandscape(this.context)) {
                attributes.width = (int) (r3.x * 0.7d);
            } else {
                attributes.width = (int) (r3.x * 0.9d);
            }
            this.width = attributes.width;
            window.setAttributes(attributes);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_language);
        BaseQuickAdapter<LanguageItem, BaseViewHolder> adapter = getAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 4);
        gridLayoutManager.setOrientation(1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        this.tvClose = (TextView) findViewById(R.id.tv_close);
        this.tvClose.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$SetLanguageDialog$EYSbcgUNNrSxU4PbD0o3uVmIf5o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetLanguageDialog.this.lambda$onCreate$0$SetLanguageDialog(view);
            }
        });
        if (this.isNowSet) {
            this.tvClose.setVisibility(8);
        } else {
            this.tvClose.setVisibility(0);
        }
    }

    public void setOnConfirmListener(onConfirmListener onconfirmlistener) {
        this.onConfirmListener = onconfirmlistener;
    }
}
