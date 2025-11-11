package com.noober.background.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import com.noober.background.R;
import com.noober.background.common.MultiSelector;
import com.noober.background.common.ResourceUtils;

/* loaded from: classes.dex */
public class MultiTextColorSelectorColorCreator implements ICreateColorState {
    private Context context;
    private int index;
    private TypedArray selectorTa;
    private int[][] states = new int[0];
    private int[] colors = new int[0];

    public MultiTextColorSelectorColorCreator(Context context, TypedArray typedArray) {
        this.selectorTa = typedArray;
        this.context = context;
    }

    private void addState(int i) {
        String string = this.selectorTa.getString(i);
        if (string != null) {
            String[] split = string.split(",");
            if (split.length < 2) {
                throw new IllegalArgumentException("Attributes and drawable must be set at the same time");
            }
            int[] iArr = new int[split.length - 1];
            int i2 = 0;
            for (int i3 = 0; i3 < split.length; i3++) {
                String str = split[i3];
                if (i3 == split.length - 1) {
                    i2 = ResourceUtils.getColor(this.context, str);
                    if (i2 == -1) {
                        throw new IllegalArgumentException("cannot find color from the last attribute");
                    }
                } else {
                    MultiSelector multiAttr = MultiSelector.getMultiAttr(str.replace("-", ""));
                    if (multiAttr == null) {
                        throw new IllegalArgumentException("the attribute of bl_multi_selector only support state_checkable, state_checked, state_enabled, state_selected, state_pressed, state_focused, state_hovered, state_activated");
                    }
                    if (str.contains("-")) {
                        iArr[i3] = -multiAttr.id;
                    } else {
                        iArr[i3] = multiAttr.id;
                    }
                }
            }
            int[][] iArr2 = this.states;
            int i4 = this.index;
            iArr2[i4] = iArr;
            this.colors[i4] = i2;
            this.index = i4 + 1;
        }
    }

    @Override // com.noober.background.drawable.ICreateColorState
    public ColorStateList create() {
        this.states = new int[this.selectorTa.getIndexCount()];
        this.colors = new int[this.selectorTa.getIndexCount()];
        for (int i = 0; i < this.selectorTa.getIndexCount(); i++) {
            int index = this.selectorTa.getIndex(i);
            if (index == R.styleable.background_multi_selector_text_bl_multi_text_selector1) {
                addState(index);
            } else if (index == R.styleable.background_multi_selector_text_bl_multi_text_selector2) {
                addState(index);
            } else if (index == R.styleable.background_multi_selector_text_bl_multi_text_selector3) {
                addState(index);
            } else if (index == R.styleable.background_multi_selector_text_bl_multi_text_selector4) {
                addState(index);
            } else if (index == R.styleable.background_multi_selector_text_bl_multi_text_selector5) {
                addState(index);
            } else if (index == R.styleable.background_multi_selector_text_bl_multi_text_selector6) {
                addState(index);
            }
        }
        return new ColorStateList(this.states, this.colors);
    }
}
