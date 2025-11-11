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
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.helper.LanguageHelper;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import java.util.List;

/* loaded from: classes.dex */
public class Step1Adapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public Step1Adapter(int i, @Nullable List<Object> list) {
        super(i, list);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void convert(BaseViewHolder baseViewHolder, Object obj) {
        String priceStr;
        String str;
        String str2;
        String cashcode = Constants.opsSeting().getCashcode();
        ProductBean productBean = (ProductBean) obj;
        ((TextView) baseViewHolder.getView(R.id.tv_productpriceold)).getPaint().setFlags(16);
        if (productBean.getSoldOut() == 1 || productBean.getLocalSoldOut() == 1) {
            baseViewHolder.setGone(R.id.tv_soldout, true);
            baseViewHolder.setTextColor(R.id.tv_productname, SupportMenu.CATEGORY_MASK);
        } else {
            baseViewHolder.setGone(R.id.tv_soldout, false).setTextColor(R.id.tv_productname, ViewCompat.MEASURED_STATE_MASK);
        }
        boolean isDiscount = productBean.isDiscount();
        if (isDiscount) {
            priceStr = productBean.getDiscountPriceStr();
            str = productBean.getDevicePrice() <= 0.0d ? productBean.getPriceStr() : productBean.getDevicePriceStr();
        } else {
            priceStr = productBean.getDevicePrice() <= 0.0d ? productBean.getPriceStr() : productBean.getDevicePriceStr();
            str = "0";
        }
        if (LanguageHelper.language().getLangCode().equals("zh")) {
            str2 = priceStr + "" + cashcode;
        } else {
            str2 = cashcode + "" + priceStr;
        }
        baseViewHolder.setText(R.id.tv_productname, productBean.getName() + productBean.getNameEn()).setGone(R.id.iv_new, productBean.isNews()).setGone(R.id.iv_discount, isDiscount).setGone(R.id.tv_productpriceold, isDiscount).setText(R.id.tv_productpricenew, str2).setText(R.id.tv_productpriceold, str);
        ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_product_pic);
        Glide.with(imageView).load(productBean.getImgUrl()).addListener(new RequestListener<Drawable>() { // from class: com.yj.coffeemachines.mvp.ui.adapter.Step1Adapter.1
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
