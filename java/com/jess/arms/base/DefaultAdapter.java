package com.jess.arms.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jess.arms.base.BaseHolder;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes.dex */
public abstract class DefaultAdapter<T> extends RecyclerView.Adapter<BaseHolder<T>> {
    protected List<T> mInfos;
    protected OnRecyclerViewItemClickListener mOnItemClickListener = null;

    /* loaded from: classes.dex */
    public interface OnRecyclerViewItemClickListener<T> {
        void onItemClick(@NonNull View view, int i, @NonNull T t, int i2);
    }

    public DefaultAdapter(List<T> list) {
        this.mInfos = list;
    }

    public static void releaseAllHolder(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return;
        }
        for (int childCount = recyclerView.getChildCount() - 1; childCount >= 0; childCount--) {
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(recyclerView.getChildAt(childCount));
            if (childViewHolder instanceof BaseHolder) {
                ((BaseHolder) childViewHolder).onRelease();
            }
        }
    }

    @NonNull
    public abstract BaseHolder<T> getHolder(@NonNull View view, int i);

    public List<T> getInfos() {
        return this.mInfos;
    }

    public T getItem(int i) {
        List<T> list = this.mInfos;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mInfos.size();
    }

    public abstract int getLayoutId(int i);

    public /* synthetic */ void lambda$onCreateViewHolder$0$DefaultAdapter(int i, View view, int i2) {
        if (this.mOnItemClickListener == null || this.mInfos.size() <= 0) {
            return;
        }
        this.mOnItemClickListener.onItemClick(view, i, this.mInfos.get(i2), i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BaseHolder<T> baseHolder, int i) {
        baseHolder.setData(this.mInfos.get(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public BaseHolder<T> onCreateViewHolder(ViewGroup viewGroup, final int i) {
        BaseHolder<T> holder = getHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(i), viewGroup, false), i);
        holder.setOnItemClickListener(new BaseHolder.OnViewClickListener() { // from class: com.jess.arms.base.-$$Lambda$DefaultAdapter$90htLwAiL_e0s_dmG5HaA4wVz44
            @Override // com.jess.arms.base.BaseHolder.OnViewClickListener
            public final void onViewClick(View view, int i2) {
                DefaultAdapter.this.lambda$onCreateViewHolder$0$DefaultAdapter(i, view, i2);
            }
        });
        return holder;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.mOnItemClickListener = onRecyclerViewItemClickListener;
    }
}
