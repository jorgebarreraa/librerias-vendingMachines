package com.yj.coffeemachines.mvp.ui.adapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import java.util.List;

/* loaded from: classes.dex */
public class EventAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public EventAdapter(int i, @Nullable List<Object> list) {
        super(i, list);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void convert(BaseViewHolder baseViewHolder, Object obj) {
        double price;
        String cashcode = Constants.opsSeting().getCashcode();
        ProductBean.ProductDetailBean productDetailBean = (ProductBean.ProductDetailBean) obj;
        ((TextView) baseViewHolder.getView(R.id.tv_productpriceold)).getPaint().setFlags(16);
        if (productDetailBean.getSoldOut() == 1) {
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
        Glide.with(imageView).load(productDetailBean.getAttachList().get(0).getFullPath()).addListener(new RequestListener<Drawable>() { // from class: com.yj.coffeemachines.mvp.ui.adapter.EventAdapter.1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj2, Target<Drawable> target, boolean z) {
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj2, Target<Drawable> target, DataSource dataSource, boolean z) {
                if (!(drawable instanceof GifDrawable)) {
                    return false;
                }
                ((GifDrawable) drawable).setLoopCount(Integer.MAX_VALUE);
                return false;
            }
        }).into(imageView);
    }
}
