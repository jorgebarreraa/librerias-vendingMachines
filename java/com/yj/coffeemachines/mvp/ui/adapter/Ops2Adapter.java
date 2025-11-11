package com.yj.coffeemachines.mvp.ui.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import java.util.List;

/* loaded from: classes.dex */
public class Ops2Adapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public Ops2Adapter(int i, @Nullable List<Object> list) {
        super(i, list);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void convert(BaseViewHolder baseViewHolder, Object obj) {
        EditText editText = (EditText) baseViewHolder.getView(R.id.et_inputname);
        final MaterialMessage materialMessage = (MaterialMessage) obj;
        if (materialMessage.getLocaName().isEmpty()) {
            baseViewHolder.setText(R.id.et_inputname, "");
        } else {
            baseViewHolder.setText(R.id.et_inputname, materialMessage.getLocaName());
        }
        baseViewHolder.setText(R.id.tv_name, materialMessage.getPosition() + " " + materialMessage.getName()).setChecked(R.id.swith_open, materialMessage.getIschange()).setChecked(R.id.swith_suger, materialMessage.getLocalIsSuger());
        editText.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.adapter.Ops2Adapter.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String obj2 = editable.toString();
                materialMessage.setLocaName(obj2);
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(materialMessage.getPosition() + " " + materialMessage.getName() + Ops2Adapter.this.mContext.getString(R.string.makename) + obj2, DataUtils.currentTime(), 1);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        ((Switch) baseViewHolder.getView(R.id.swith_open)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.adapter.Ops2Adapter.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                materialMessage.setIschange(z);
                if (z) {
                    MyAppLocation.myAppLocation.myMqttService.addOpsLog(materialMessage.getPosition() + " " + materialMessage.getName() + Ops2Adapter.this.mContext.getString(R.string.openchange), DataUtils.currentTime(), 1);
                    return;
                }
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(materialMessage.getPosition() + " " + materialMessage.getName() + Ops2Adapter.this.mContext.getString(R.string.closechange), DataUtils.currentTime(), 1);
            }
        });
        ((Switch) baseViewHolder.getView(R.id.swith_suger)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.adapter.Ops2Adapter.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                materialMessage.setLocalIsSuger(z);
                if (z) {
                    MyAppLocation.myAppLocation.myMqttService.addOpsLog(materialMessage.getPosition() + " " + materialMessage.getName() + Ops2Adapter.this.mContext.getString(R.string.opensugar), DataUtils.currentTime(), 1);
                    return;
                }
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(materialMessage.getPosition() + " " + materialMessage.getName() + Ops2Adapter.this.mContext.getString(R.string.closesugar), DataUtils.currentTime(), 1);
            }
        });
        baseViewHolder.setIsRecyclable(false);
    }
}
