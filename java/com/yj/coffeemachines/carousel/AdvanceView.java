package com.yj.coffeemachines.carousel;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import androidx.viewpager.widget.ViewPager;
import java.util.List;

/* loaded from: classes.dex */
public class AdvanceView extends RelativeLayout {
    private AdvancePagerAdapter adapter;
    private ViewPager viewPager;

    public AdvanceView(Context context) {
        super(context);
        initView();
    }

    public AdvanceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public AdvanceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        removeAllViews();
        this.viewPager = new ViewPager(getContext());
        this.adapter = new AdvancePagerAdapter(getContext(), this.viewPager);
        this.viewPager.setAdapter(this.adapter);
        addView(this.viewPager, new RelativeLayout.LayoutParams(-1, -1));
    }

    public void setData(List<Advance> list) {
        initView();
        this.adapter.setData(list);
    }

    public void setDestroy() {
        this.adapter.setDestroy();
    }

    public void setPause() {
        this.adapter.setPause();
    }

    public void setResume() {
        this.adapter.setResume();
    }
}
