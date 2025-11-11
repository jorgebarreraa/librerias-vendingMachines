package com.yj.coffeemachines.dialog.loading;

import android.content.Context;
import android.view.View;

/* loaded from: classes.dex */
public class AnimatedView extends View {
    private int target;

    public AnimatedView(Context context) {
        super(context);
    }

    public int getTarget() {
        return this.target;
    }

    public float getXFactor() {
        return getX() / this.target;
    }

    public void setTarget(int i) {
        this.target = i;
    }

    public void setXFactor(float f) {
        setX(this.target * f);
    }
}
