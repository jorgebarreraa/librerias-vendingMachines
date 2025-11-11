package com.google.android.material.theme;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textview.MaterialTextView;

@Keep
/* loaded from: classes.dex */
public class MaterialComponentsViewInflater extends AppCompatViewInflater {
    private static int floatingToolbarItemBackgroundResId = -1;

    @Override // androidx.appcompat.app.AppCompatViewInflater
    @NonNull
    protected AppCompatButton createButton(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        return shouldInflateAppCompatButton(context, attributeSet) ? new AppCompatButton(context, attributeSet) : new MaterialButton(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    @NonNull
    protected AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeSet) {
        return new MaterialCheckBox(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    @NonNull
    protected AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeSet) {
        return new MaterialRadioButton(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    @NonNull
    protected AppCompatTextView createTextView(Context context, AttributeSet attributeSet) {
        return new MaterialTextView(context, attributeSet);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected boolean shouldInflateAppCompatButton(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        if (Build.VERSION.SDK_INT != 23 && Build.VERSION.SDK_INT != 24 && Build.VERSION.SDK_INT != 25) {
            return false;
        }
        if (floatingToolbarItemBackgroundResId == -1) {
            floatingToolbarItemBackgroundResId = context.getResources().getIdentifier("floatingToolbarItemBackgroundDrawable", "^attr-private", "android");
        }
        int i = floatingToolbarItemBackgroundResId;
        if (i != 0 && i != -1) {
            for (int i2 = 0; i2 < attributeSet.getAttributeCount(); i2++) {
                if (attributeSet.getAttributeNameResource(i2) == 16842964) {
                    if (floatingToolbarItemBackgroundResId == attributeSet.getAttributeListValue(i2, null, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
