package com.yj.coffeemachines.mvp.ui.adapter;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.greendao.beans.OpsActionMessage;
import java.util.List;

/* loaded from: classes.dex */
public class Ops6Adapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public Ops6Adapter(int i, @Nullable List<Object> list) {
        super(i, list);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void convert(BaseViewHolder baseViewHolder, Object obj) {
        OpsActionMessage opsActionMessage = (OpsActionMessage) obj;
        baseViewHolder.setText(R.id.tv_sn, (baseViewHolder.getAdapterPosition() + 1) + "").setText(R.id.tv_time, DataUtils.getTimeyyymmddhhmmss(opsActionMessage.getTime())).setText(R.id.tv_action, opsActionMessage.getAction());
    }
}
