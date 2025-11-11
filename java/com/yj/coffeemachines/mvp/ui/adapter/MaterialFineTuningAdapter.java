package com.yj.coffeemachines.mvp.ui.adapter;

import android.widget.SeekBar;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import java.util.List;

/* loaded from: classes.dex */
public class MaterialFineTuningAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    private boolean isCoffee;

    public MaterialFineTuningAdapter(int i, @Nullable List<Object> list) {
        super(i, list);
        this.isCoffee = false;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void convert(final BaseViewHolder baseViewHolder, final Object obj) {
        if (obj instanceof MaterialMessage) {
            MaterialMessage materialMessage = (MaterialMessage) obj;
            baseViewHolder.setText(R.id.tv_name, materialMessage.getPosition() + " " + materialMessage.getName()).setText(R.id.tv_num1, String.valueOf(materialMessage.getLocalMaterialFineTuning()));
            if (materialMessage.getDeviceTypeName().toLowerCase().contains("jk88") && materialMessage.getPosition().contains("8")) {
                this.isCoffee = true;
                SeekBar seekBar = (SeekBar) baseViewHolder.getView(R.id.numberProgressBaru);
                seekBar.setMax(40);
                seekBar.setProgress(materialMessage.getLocalMaterialFineTuning() + 20);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.adapter.MaterialFineTuningAdapter.1
                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onProgressChanged(SeekBar seekBar2, int i, boolean z) {
                        baseViewHolder.setText(R.id.tv_num1, String.valueOf(i - 20));
                    }

                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onStartTrackingTouch(SeekBar seekBar2) {
                    }

                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onStopTrackingTouch(SeekBar seekBar2) {
                        ((MaterialMessage) obj).setLocalMaterialFineTuning(seekBar2.getProgress() - 20);
                    }
                });
                return;
            }
            if (!materialMessage.getRawType().contains(Constants.ground1) && !materialMessage.getRawType().toLowerCase().contains(Constants.ground2) && !materialMessage.getRawType().toLowerCase().contains(Constants.ground6)) {
                SeekBar seekBar2 = (SeekBar) baseViewHolder.getView(R.id.numberProgressBaru);
                seekBar2.setMax(100);
                seekBar2.setProgress(materialMessage.getLocalMaterialFineTuning() + 50);
                seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.adapter.MaterialFineTuningAdapter.3
                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onProgressChanged(SeekBar seekBar3, int i, boolean z) {
                        baseViewHolder.setText(R.id.tv_num1, String.valueOf(i - 50));
                    }

                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onStartTrackingTouch(SeekBar seekBar3) {
                    }

                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onStopTrackingTouch(SeekBar seekBar3) {
                        ((MaterialMessage) obj).setLocalMaterialFineTuning(seekBar3.getProgress() - 50);
                    }
                });
                return;
            }
            this.isCoffee = true;
            SeekBar seekBar3 = (SeekBar) baseViewHolder.getView(R.id.numberProgressBaru);
            seekBar3.setMax(100);
            seekBar3.setProgress(materialMessage.getLocalMaterialFineTuning() + 50);
            seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.adapter.MaterialFineTuningAdapter.2
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar4, int i, boolean z) {
                    baseViewHolder.setText(R.id.tv_num1, String.valueOf(i - 50));
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar4) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar4) {
                    ((MaterialMessage) obj).setLocalMaterialFineTuning(seekBar4.getProgress() - 50);
                }
            });
        }
    }
}
