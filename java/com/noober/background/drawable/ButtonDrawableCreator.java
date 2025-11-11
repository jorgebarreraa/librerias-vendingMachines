package com.noober.background.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import androidx.annotation.AttrRes;
import com.noober.background.R;

/* loaded from: classes.dex */
public class ButtonDrawableCreator implements ICreateDrawable {
    private TypedArray buttonTa;
    private TypedArray typedArray;

    public ButtonDrawableCreator(TypedArray typedArray, TypedArray typedArray2) {
        this.typedArray = typedArray;
        this.buttonTa = typedArray2;
    }

    private void setSelectorDrawable(TypedArray typedArray, TypedArray typedArray2, StateListDrawable stateListDrawable, int i, @AttrRes int i2) throws Exception {
        int i3;
        Drawable drawable;
        try {
            i3 = typedArray2.getColor(i, 0);
            if (i3 == 0) {
                try {
                    drawable = typedArray2.getDrawable(i);
                } catch (Exception unused) {
                    drawable = typedArray2.getDrawable(i);
                    if (drawable == null) {
                    }
                    stateListDrawable.addState(new int[]{i2}, drawable);
                    return;
                }
            } else {
                drawable = null;
            }
        } catch (Exception unused2) {
            i3 = 0;
        }
        if (drawable == null || i3 == 0) {
            stateListDrawable.addState(new int[]{i2}, drawable);
            return;
        }
        GradientDrawable drawable2 = DrawableFactory.getDrawable(typedArray);
        drawable2.setColor(i3);
        stateListDrawable.addState(new int[]{i2}, drawable2);
    }

    @Override // com.noober.background.drawable.ICreateDrawable
    public Drawable create() throws Exception {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < this.buttonTa.getIndexCount(); i++) {
            int index = this.buttonTa.getIndex(i);
            if (index == R.styleable.background_button_drawable_bl_checked_button_drawable) {
                setSelectorDrawable(this.typedArray, this.buttonTa, stateListDrawable, index, android.R.attr.state_checked);
            } else if (index == R.styleable.background_button_drawable_bl_unChecked_button_drawable) {
                setSelectorDrawable(this.typedArray, this.buttonTa, stateListDrawable, index, -16842912);
            }
        }
        return stateListDrawable;
    }
}
