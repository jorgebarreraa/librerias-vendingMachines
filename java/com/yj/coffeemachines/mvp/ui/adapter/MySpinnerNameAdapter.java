package com.yj.coffeemachines.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.serialport.Device;

/* loaded from: classes.dex */
public class MySpinnerNameAdapter extends BaseAdapter implements SpinnerAdapter {
    Context mContext;
    String[] messageList;

    public MySpinnerNameAdapter(String[] strArr, Context context) {
        this.messageList = strArr;
        this.mContext = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.messageList.length;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.messageList[i];
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.spinner_item_layout, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.name)).setText(((Device) getItem(i)).getFile().getAbsolutePath());
        return inflate;
    }
}
