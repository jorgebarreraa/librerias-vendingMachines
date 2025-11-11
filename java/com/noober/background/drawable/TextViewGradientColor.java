package com.noober.background.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;
import com.noober.background.R;

/* loaded from: classes.dex */
public class TextViewGradientColor implements ITextViewOperator {
    private int endColor = -1;
    private int startColor = -1;
    private int orientation = 0;

    @Override // com.noober.background.drawable.ITextViewOperator
    public void invoke(Context context, AttributeSet attributeSet, final TextView textView) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.bl_text);
        try {
        } catch (Exception unused) {
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
        if (obtainStyledAttributes.getIndexCount() == 0) {
            obtainStyledAttributes.recycle();
            return;
        }
        for (int i = 0; i < obtainStyledAttributes.getIndexCount(); i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.bl_text_bl_text_gradient_endColor) {
                this.endColor = obtainStyledAttributes.getColor(index, -1);
            } else if (index == R.styleable.bl_text_bl_text_gradient_startColor) {
                this.startColor = obtainStyledAttributes.getColor(index, -1);
            } else if (index == R.styleable.bl_text_bl_text_gradient_orientation) {
                this.orientation = obtainStyledAttributes.getInt(index, 0);
            }
        }
        if (this.endColor == -1 && this.startColor != -1) {
            textView.setTextColor(this.startColor);
        } else if (this.startColor == -1 && this.endColor != -1) {
            textView.setTextColor(this.endColor);
        } else if (this.endColor != -1 && this.startColor != -1) {
            if (this.orientation == 0) {
                textView.post(new Runnable() { // from class: com.noober.background.drawable.TextViewGradientColor.1
                    @Override // java.lang.Runnable
                    public void run() {
                        textView.getPaint().setShader(new LinearGradient(0.0f, 0.0f, 0.0f, textView.getPaint().descent() - textView.getPaint().ascent(), TextViewGradientColor.this.startColor, TextViewGradientColor.this.endColor, Shader.TileMode.REPEAT));
                        textView.invalidate();
                    }
                });
            } else {
                textView.post(new Runnable() { // from class: com.noober.background.drawable.TextViewGradientColor.2
                    @Override // java.lang.Runnable
                    public void run() {
                        textView.getPaint().setShader(new LinearGradient(0.0f, 0.0f, textView.getMeasuredWidth(), 0.0f, TextViewGradientColor.this.startColor, TextViewGradientColor.this.endColor, Shader.TileMode.REPEAT));
                        textView.invalidate();
                    }
                });
            }
        }
        obtainStyledAttributes.recycle();
    }
}
