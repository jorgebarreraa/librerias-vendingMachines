package com.yj.coffeemachines.carousel;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import java.io.File;

/* loaded from: classes.dex */
public class AdvanceVideoView extends RelativeLayout {
    private ImageView imageView;
    private String path;
    private RelativeLayout videoRela;
    private VideoView videoView;

    public AdvanceVideoView(Context context) {
        super(context);
        initView();
    }

    public AdvanceVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public AdvanceVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        this.videoRela = new RelativeLayout(getContext());
        addView(this.videoRela, new RelativeLayout.LayoutParams(-1, -1));
        this.imageView = new ImageView(getContext());
        this.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.imageView, new RelativeLayout.LayoutParams(-1, -1));
    }

    public /* synthetic */ void lambda$null$0$AdvanceVideoView() {
        this.imageView.setVisibility(8);
    }

    public /* synthetic */ void lambda$setVideo$1$AdvanceVideoView(MediaPlayer mediaPlayer) {
        new Handler().postDelayed(new Runnable() { // from class: com.yj.coffeemachines.carousel.-$$Lambda$AdvanceVideoView$YHvdQI9OoWotp7lzI2ozHif6OZM
            @Override // java.lang.Runnable
            public final void run() {
                AdvanceVideoView.this.lambda$null$0$AdvanceVideoView();
            }
        }, 400L);
    }

    public void setImage(String str) {
        this.path = str;
        Glide.with(getContext()).load(Uri.fromFile(new File(str))).into(this.imageView);
    }

    public void setPause() {
        VideoView videoView = this.videoView;
        if (videoView != null) {
            videoView.pause();
            this.imageView.setVisibility(0);
        }
    }

    public void setRestart() {
        VideoView videoView = this.videoView;
        if (videoView != null) {
            videoView.start();
            this.imageView.setVisibility(8);
        }
    }

    public void setVideo(MediaPlayer.OnCompletionListener onCompletionListener) {
        VideoView videoView = this.videoView;
        if (videoView != null) {
            this.videoRela.removeView(videoView);
            this.videoView = null;
        }
        this.videoView = new VideoView(getContext());
        this.videoView.setVideoPath(this.path);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(9);
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        layoutParams.addRule(12);
        this.videoRela.addView(this.videoView, layoutParams);
        this.videoView.setOnCompletionListener(onCompletionListener);
        this.videoView.start();
        this.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.yj.coffeemachines.carousel.-$$Lambda$AdvanceVideoView$_O5rLgyWdi2bHsQzZumsX8sRiX8
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                AdvanceVideoView.this.lambda$setVideo$1$AdvanceVideoView(mediaPlayer);
            }
        });
        this.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.yj.coffeemachines.carousel.AdvanceVideoView.1
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                AdvanceVideoView.this.videoView.stopPlayback();
                return true;
            }
        });
    }
}
