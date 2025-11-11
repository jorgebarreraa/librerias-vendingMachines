package com.jaredrummler.materialspinner;

import android.content.Context;
import java.util.List;

/* loaded from: classes.dex */
public class MaterialSpinnerAdapter<T> extends MaterialSpinnerBaseAdapter {
    private final List<T> items;

    public MaterialSpinnerAdapter(Context context, List<T> list) {
        super(context);
        this.items = list;
    }

    @Override // com.jaredrummler.materialspinner.MaterialSpinnerBaseAdapter
    public T get(int i) {
        return this.items.get(i);
    }

    @Override // com.jaredrummler.materialspinner.MaterialSpinnerBaseAdapter, android.widget.Adapter
    public int getCount() {
        int size = this.items.size();
        return (size == 1 || isHintEnabled()) ? size : size - 1;
    }

    @Override // com.jaredrummler.materialspinner.MaterialSpinnerBaseAdapter, android.widget.Adapter
    public T getItem(int i) {
        return isHintEnabled() ? this.items.get(i) : (i < getSelectedIndex() || this.items.size() == 1) ? this.items.get(i) : this.items.get(i + 1);
    }

    @Override // com.jaredrummler.materialspinner.MaterialSpinnerBaseAdapter
    public List<T> getItems() {
        return this.items;
    }
}
