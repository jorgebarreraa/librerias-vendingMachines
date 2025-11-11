package com.noober.background.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.noober.background.BackgroundFactory;

/* loaded from: classes.dex */
public class BLView extends View {
    public BLView(Context context) {
        super(context);
    }

    public BLView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public BLView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        BackgroundFactory.setViewBackground(context, attributeSet, this);
    }
}
