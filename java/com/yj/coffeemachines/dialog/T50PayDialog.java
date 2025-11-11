package com.yj.coffeemachines.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.utils.DeviceUtils;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.mvp.model.beans.T50PayWayBean;
import java.util.List;

/* loaded from: classes.dex */
public class T50PayDialog extends BaseDialog {
    private BaseQuickAdapter<T50PayWayBean, BaseViewHolder> adapter;
    private List<T50PayWayBean> beans;
    private OnPayClickListener listener;
    private RecyclerView rvPayWay;
    private int width;

    /* loaded from: classes.dex */
    public interface OnPayClickListener {
        void onPayClick(T50PayWayBean t50PayWayBean);
    }

    public T50PayDialog(@NonNull Context context) {
        super(context);
    }

    public T50PayDialog(@NonNull Context context, int i) {
        super(context, i);
    }

    public T50PayDialog(@NonNull Context context, List<T50PayWayBean> list) {
        super(context);
        this.beans = list;
    }

    protected T50PayDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public /* synthetic */ void lambda$onCreate$0$T50PayDialog(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        OnPayClickListener onPayClickListener = this.listener;
        if (onPayClickListener != null) {
            onPayClickListener.onPayClick(this.adapter.getData().get(i));
            dismiss();
        }
    }

    public /* synthetic */ void lambda$onCreate$1$T50PayDialog(View view) {
        dismiss();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_t50_pay);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        if (window != null) {
            Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            defaultDisplay.getSize(new Point());
            if (DeviceUtils.isLandscape(this.context)) {
                attributes.width = (int) (r3.x * 0.7d);
                attributes.height = (int) (r3.y * 0.4d);
            } else {
                attributes.width = (int) (r3.x * 0.9d);
                attributes.height = (int) (r3.y * 0.3d);
            }
            this.width = attributes.width;
            window.setAttributes(attributes);
        }
        this.rvPayWay = (RecyclerView) findViewById(R.id.rv_pay_way);
        this.adapter = new BaseQuickAdapter<T50PayWayBean, BaseViewHolder>(R.layout.item_t50_pay_way, this.beans) { // from class: com.yj.coffeemachines.dialog.T50PayDialog.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(@NonNull BaseViewHolder baseViewHolder, T50PayWayBean t50PayWayBean) {
                if (T50PayDialog.this.beans != null && !T50PayDialog.this.beans.isEmpty()) {
                    ((TextView) baseViewHolder.getView(R.id.tv_pay_name)).setLayoutParams(new LinearLayout.LayoutParams((T50PayDialog.this.width / T50PayDialog.this.beans.size()) - 12, -2));
                }
                baseViewHolder.setText(R.id.tv_pay_name, t50PayWayBean.getName());
                ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_pay_img);
                Glide.with(imageView).load(t50PayWayBean.getImg()).into(imageView);
            }
        };
        this.adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$T50PayDialog$G6OVeY1827iiW5y6wTE3BST1FxY
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                T50PayDialog.this.lambda$onCreate$0$T50PayDialog(baseQuickAdapter, view, i);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setOrientation(0);
        this.rvPayWay.setLayoutManager(linearLayoutManager);
        this.rvPayWay.setAdapter(this.adapter);
        findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$T50PayDialog$h5Y5M07b1EYZqrbA725RfM-olwg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                T50PayDialog.this.lambda$onCreate$1$T50PayDialog(view);
            }
        });
    }

    public void setListener(OnPayClickListener onPayClickListener) {
        this.listener = onPayClickListener;
    }
}
