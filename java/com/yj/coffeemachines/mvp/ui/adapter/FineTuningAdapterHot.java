package com.yj.coffeemachines.mvp.ui.adapter;

import android.widget.SeekBar;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import java.util.List;

/* loaded from: classes.dex */
public class FineTuningAdapterHot extends BaseQuickAdapter<Object, BaseViewHolder> {
    public FineTuningAdapterHot(int i, @Nullable List<Object> list) {
        super(i, list);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void convert(final BaseViewHolder baseViewHolder, final Object obj) {
        if (obj instanceof MaterialMessage) {
            final MaterialMessage materialMessage = (MaterialMessage) obj;
            baseViewHolder.setText(R.id.tv_name, materialMessage.getPosition() + " " + materialMessage.getName()).setText(R.id.tv_num1, String.valueOf(materialMessage.getLocalFineTuning_hot()));
            SeekBar seekBar = (SeekBar) baseViewHolder.getView(R.id.numberProgressBaru);
            seekBar.setMax(100);
            seekBar.setProgress(materialMessage.getLocalFineTuning_hot() + 50);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.adapter.FineTuningAdapterHot.1
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar2, int i, boolean z) {
                    baseViewHolder.setText(R.id.tv_num1, String.valueOf(i - 50));
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar2) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar2) {
                    int progress = seekBar2.getProgress() - 50;
                    ((MaterialMessage) obj).setLocalFineTuning_hot(progress);
                    MyAppLocation.myAppLocation.myMqttService.addOpsLog(materialMessage.getPosition() + " " + materialMessage.getName() + FineTuningAdapterHot.this.mContext.getString(R.string.hotchange) + progress, DataUtils.currentTime(), 1);
                }
            });
        }
    }
}
