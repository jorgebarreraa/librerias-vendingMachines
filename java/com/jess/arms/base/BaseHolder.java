package com.jess.arms.base;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected final String TAG;
    protected OnViewClickListener mOnViewClickListener;

    /* loaded from: classes.dex */
    public interface OnViewClickListener {
        void onViewClick(View view, int i);
    }

    public BaseHolder(View view) {
        super(view);
        this.TAG = getClass().getSimpleName();
        this.mOnViewClickListener = null;
        view.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnViewClickListener onViewClickListener = this.mOnViewClickListener;
        if (onViewClickListener != null) {
            onViewClickListener.onViewClick(view, getPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRelease() {
    }

    public abstract void setData(@NonNull T t, int i);

    public void setOnItemClickListener(OnViewClickListener onViewClickListener) {
        this.mOnViewClickListener = onViewClickListener;
    }
}
