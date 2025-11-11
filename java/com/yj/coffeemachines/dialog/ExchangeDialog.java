package com.yj.coffeemachines.dialog;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.utils.DeviceUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.dialog.ConfirmDialog;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import java.util.List;

/* loaded from: classes.dex */
public class ExchangeDialog extends BaseDialog {
    private BaseQuickAdapter<ProductBean.ProductDetailBean, BaseViewHolder> adapter;
    private final Context context;
    private onConfirmListener onConfirmListener;
    private TextView tvClose;

    /* loaded from: classes.dex */
    public interface onConfirmListener {
        boolean onConfirm(ProductBean.ProductDetailBean productDetailBean);
    }

    public ExchangeDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @NonNull
    private BaseQuickAdapter<ProductBean.ProductDetailBean, BaseViewHolder> getAdapter() {
        final BaseQuickAdapter<ProductBean.ProductDetailBean, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<ProductBean.ProductDetailBean, BaseViewHolder>(R.layout.product_item_layout) { // from class: com.yj.coffeemachines.dialog.ExchangeDialog.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(@NonNull BaseViewHolder baseViewHolder, ProductBean.ProductDetailBean productDetailBean) {
                double price;
                String cashcode = Constants.opsSeting().getCashcode();
                ((TextView) baseViewHolder.getView(R.id.tv_productpriceold)).getPaint().setFlags(16);
                if (productDetailBean.getSoldOut() == 1 || productDetailBean.getLocalSoldOut() == 1) {
                    baseViewHolder.setGone(R.id.tv_soldout, true);
                    baseViewHolder.setTextColor(R.id.tv_productname, SupportMenu.CATEGORY_MASK).setTextColor(R.id.tv_productname_en, SupportMenu.CATEGORY_MASK);
                } else if (MyAppLocation.myAppLocation.mSerialDataService.communicationStatus_Instant) {
                    baseViewHolder.setGone(R.id.tv_soldout, false);
                    baseViewHolder.setTextColor(R.id.tv_productname, ViewCompat.MEASURED_STATE_MASK).setTextColor(R.id.tv_productname_en, ViewCompat.MEASURED_STATE_MASK);
                } else {
                    baseViewHolder.setGone(R.id.tv_soldout, true);
                    baseViewHolder.setTextColor(R.id.tv_productname, SupportMenu.CATEGORY_MASK).setTextColor(R.id.tv_productname_en, SupportMenu.CATEGORY_MASK);
                }
                boolean isDiscount = productDetailBean.isDiscount();
                double d = 0.0d;
                if (isDiscount) {
                    price = productDetailBean.getDiscountPrice();
                    d = productDetailBean.getDevicePrice() <= 0.0d ? productDetailBean.getPrice() : productDetailBean.getDevicePrice();
                } else {
                    price = productDetailBean.getDevicePrice() <= 0.0d ? productDetailBean.getPrice() : productDetailBean.getDevicePrice();
                }
                baseViewHolder.setText(R.id.tv_productname, productDetailBean.getName()).setText(R.id.tv_productname_en, productDetailBean.getNameEn()).setGone(R.id.iv_new, productDetailBean.isNews()).setGone(R.id.iv_discount, isDiscount).setGone(R.id.tv_productpriceold, isDiscount).setText(R.id.tv_productpricenew, cashcode + "" + price).setText(R.id.tv_productpriceold, cashcode + "" + d);
                ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_product_pic);
                Glide.with(imageView).load(productDetailBean.getAttachList().get(0).getFullPath()).addListener(new RequestListener<Drawable>() { // from class: com.yj.coffeemachines.dialog.ExchangeDialog.1.1
                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                        return false;
                    }

                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                        if (!(drawable instanceof GifDrawable)) {
                            return false;
                        }
                        ((GifDrawable) drawable).setLoopCount(Integer.MAX_VALUE);
                        return false;
                    }
                }).into(imageView);
            }
        };
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$ExchangeDialog$ZX7Eq8IbVuFTk2z1VtnD25mV3yI
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                ExchangeDialog.this.lambda$getAdapter$1$ExchangeDialog(baseQuickAdapter, baseQuickAdapter2, view, i);
            }
        });
        return baseQuickAdapter;
    }

    public /* synthetic */ void lambda$getAdapter$1$ExchangeDialog(BaseQuickAdapter baseQuickAdapter, BaseQuickAdapter baseQuickAdapter2, View view, int i) {
        if (this.onConfirmListener != null) {
            showConfirmDialog((ProductBean.ProductDetailBean) baseQuickAdapter.getData().get(i));
        }
    }

    public /* synthetic */ void lambda$onCreate$0$ExchangeDialog(View view) {
        dismiss();
    }

    public /* synthetic */ void lambda$showConfirmDialog$2$ExchangeDialog(ProductBean.ProductDetailBean productDetailBean, ConfirmDialog confirmDialog) {
        if (this.onConfirmListener.onConfirm(productDetailBean)) {
            confirmDialog.dismiss();
            dismiss();
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_exchange);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        if (window != null) {
            Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            defaultDisplay.getSize(new Point());
            if (DeviceUtils.isLandscape(this.context)) {
                attributes.width = (int) (r3.x * 0.6d);
            } else {
                attributes.width = (int) (r3.x * 0.9d);
            }
            window.setAttributes(attributes);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_product);
        this.adapter = getAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 3);
        gridLayoutManager.setOrientation(1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(this.adapter);
        this.tvClose = (TextView) findViewById(R.id.tv_close);
        this.tvClose.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$ExchangeDialog$2hkihgStWg8pViUBOH3-4qK80-o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExchangeDialog.this.lambda$onCreate$0$ExchangeDialog(view);
            }
        });
    }

    public void setOnConfirmListener(onConfirmListener onconfirmlistener) {
        this.onConfirmListener = onconfirmlistener;
    }

    public void setProductData(List<ProductBean.ProductDetailBean> list) {
        onConfirmListener onconfirmlistener;
        BaseQuickAdapter<ProductBean.ProductDetailBean, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter != null) {
            baseQuickAdapter.setNewData(list);
        }
        if (list.size() == 1 && (onconfirmlistener = this.onConfirmListener) != null && onconfirmlistener.onConfirm(list.get(0))) {
            dismiss();
        }
    }

    public void showConfirmDialog(final ProductBean.ProductDetailBean productDetailBean) {
        final ConfirmDialog confirmDialog = new ConfirmDialog(this.context, WorkRequest.MIN_BACKOFF_MILLIS);
        confirmDialog.setOnConfirmListener(new ConfirmDialog.OnConfirmListener() { // from class: com.yj.coffeemachines.dialog.-$$Lambda$ExchangeDialog$dGNPnvA52XUo1ZRCEl2moVWeFW4
            @Override // com.yj.coffeemachines.dialog.ConfirmDialog.OnConfirmListener
            public final void onConfirm() {
                ExchangeDialog.this.lambda$showConfirmDialog$2$ExchangeDialog(productDetailBean, confirmDialog);
            }
        });
        confirmDialog.show();
        confirmDialog.showClose(true);
        confirmDialog.setTips(this.context.getString(R.string.exchange_confirm) + productDetailBean.getName());
    }
}
