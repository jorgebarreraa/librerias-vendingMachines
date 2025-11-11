package com.yj.coffeemachines.mvp.ui.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.mvp.message.IMessage;
import com.yj.coffeemachines.mvp.message.LogManager;

/* loaded from: classes.dex */
public class LogAdapter extends BaseAdapter {

    /* loaded from: classes.dex */
    class ListViewHolder {
        public View itemView;
        private SparseArray<View> mViewArray = new SparseArray<>();
        public int position;

        public ListViewHolder(int i, ViewGroup viewGroup) {
            this.itemView = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
            this.itemView.setTag(this);
        }

        public ListViewHolder(View view) {
            this.itemView = view;
            this.itemView.setTag(this);
        }

        public void bindPosition(int i) {
            this.position = i;
        }

        public ImageView getImage(int i) {
            return (ImageView) getView(i);
        }

        public View getItemView() {
            return this.itemView;
        }

        public int getPosition() {
            return this.position;
        }

        public TextView getText(int i) {
            return (TextView) getView(i);
        }

        public <V extends View> V getView(int i) {
            V v = (V) this.mViewArray.get(i);
            if (v != null) {
                return v;
            }
            V v2 = (V) this.itemView.findViewById(i);
            this.mViewArray.put(i, v2);
            return v2;
        }

        public void setText(int i, CharSequence charSequence) {
            ((TextView) getView(i)).setText(charSequence);
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return LogManager.instance().messages.size();
    }

    @Override // android.widget.Adapter
    public IMessage getItem(int i) {
        return LogManager.instance().messages.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ListViewHolder listViewHolder;
        IMessage item = getItem(i);
        if (view == null) {
            listViewHolder = new ListViewHolder(R.layout.item_log, viewGroup);
            view2 = listViewHolder.getItemView();
        } else {
            view2 = view;
            listViewHolder = (ListViewHolder) view.getTag();
        }
        TextView text = listViewHolder.getText(R.id.tv_log);
        TextView text2 = listViewHolder.getText(R.id.tv_num);
        text.setText(item.getMessage());
        text.setEnabled(item.isToSend());
        text2.setText(String.valueOf(i + 1) + "\r\n" + item.getSourceNmae() + "  " + item.getSource());
        return view2;
    }
}
