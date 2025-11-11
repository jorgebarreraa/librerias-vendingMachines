package dmax.dialog;

import android.view.animation.Interpolator;

/* loaded from: classes.dex */
class HesitateInterpolator implements Interpolator {
    private static double POW = 0.5d;

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return ((double) f) < 0.5d ? ((float) Math.pow(f * 2.0f, POW)) * 0.5f : (((float) Math.pow((1.0f - f) * 2.0f, POW)) * (-0.5f)) + 1.0f;
    }
}
