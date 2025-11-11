package com.chad.library.adapter.base.entity;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class AbstractExpandableItem<T> implements IExpandable<T> {
    protected boolean mExpandable = false;
    protected List<T> mSubItems;

    public void addSubItem(int i, T t) {
        List<T> list = this.mSubItems;
        if (list == null || i < 0 || i >= list.size()) {
            addSubItem(t);
        } else {
            this.mSubItems.add(i, t);
        }
    }

    public void addSubItem(T t) {
        if (this.mSubItems == null) {
            this.mSubItems = new ArrayList();
        }
        this.mSubItems.add(t);
    }

    public boolean contains(T t) {
        List<T> list = this.mSubItems;
        return list != null && list.contains(t);
    }

    public T getSubItem(int i) {
        if (!hasSubItem() || i >= this.mSubItems.size()) {
            return null;
        }
        return this.mSubItems.get(i);
    }

    public int getSubItemPosition(T t) {
        List<T> list = this.mSubItems;
        if (list != null) {
            return list.indexOf(t);
        }
        return -1;
    }

    @Override // com.chad.library.adapter.base.entity.IExpandable
    public List<T> getSubItems() {
        return this.mSubItems;
    }

    public boolean hasSubItem() {
        List<T> list = this.mSubItems;
        return list != null && list.size() > 0;
    }

    @Override // com.chad.library.adapter.base.entity.IExpandable
    public boolean isExpanded() {
        return this.mExpandable;
    }

    public boolean removeSubItem(int i) {
        List<T> list = this.mSubItems;
        if (list == null || i < 0 || i >= list.size()) {
            return false;
        }
        this.mSubItems.remove(i);
        return true;
    }

    public boolean removeSubItem(T t) {
        List<T> list = this.mSubItems;
        return list != null && list.remove(t);
    }

    @Override // com.chad.library.adapter.base.entity.IExpandable
    public void setExpanded(boolean z) {
        this.mExpandable = z;
    }

    public void setSubItems(List<T> list) {
        this.mSubItems = list;
    }
}
