package com.yj.coffeemachines.mvp.ui.adapter;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.mvp.model.beans.ReplenishListBack;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes.dex */
public class Ops1Adapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public Ops1Adapter(int i, @Nullable List<Object> list) {
        super(i, list);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void convert(BaseViewHolder baseViewHolder, Object obj) {
        ReplenishListBack.DataBean dataBean = (ReplenishListBack.DataBean) obj;
        baseViewHolder.setText(R.id.tv_name, dataBean.getPosition() + " " + dataBean.getName()).setText(R.id.tv_number, dataBean.getNowResidueQty() + MqttTopic.TOPIC_LEVEL_SEPARATOR + dataBean.getCapacity());
        baseViewHolder.addOnClickListener(R.id.tv_add);
        baseViewHolder.addOnClickListener(R.id.tv_clear);
    }
}
