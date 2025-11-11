package com.warkiz.widget;

/* loaded from: classes.dex */
public class SeekParams {
    public boolean fromUser;
    public int progress;
    public float progressFloat;
    public IndicatorSeekBar seekBar;
    public int thumbPosition;
    public String tickText;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SeekParams(IndicatorSeekBar indicatorSeekBar) {
        this.seekBar = indicatorSeekBar;
    }
}
