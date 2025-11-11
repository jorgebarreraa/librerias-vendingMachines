package com.yj.coffeemachines.carousel;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;

/* loaded from: classes.dex */
public class AdvanceImageView extends RelativeLayout {
    private ImageView imageView;

    public AdvanceImageView(Context context) {
        super(context);
        initView();
    }

    public AdvanceImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public AdvanceImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        this.imageView = new ImageView(getContext());
        this.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        addView(this.imageView, new RelativeLayout.LayoutParams(-1, -1));
    }

    public void setImage(String str) {
        Glide.with(getContext()).load(str).into(this.imageView);
    }
}
