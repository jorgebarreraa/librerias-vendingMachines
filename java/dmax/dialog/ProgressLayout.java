package dmax.dialog;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public class ProgressLayout extends FrameLayout {
    private static final int DEFAULT_COUNT = 5;
    private int spotsCount;

    public ProgressLayout(Context context) {
        this(context, null);
    }

    public ProgressLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProgressLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, 0);
    }

    @TargetApi(21)
    public ProgressLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.Dialog, i, i2);
        this.spotsCount = obtainStyledAttributes.getInt(R.styleable.Dialog_DialogSpotCount, 5);
        obtainStyledAttributes.recycle();
    }

    public int getSpotsCount() {
        return this.spotsCount;
    }
}
