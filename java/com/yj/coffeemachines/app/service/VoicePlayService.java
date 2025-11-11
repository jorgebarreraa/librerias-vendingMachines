package com.yj.coffeemachines.app.service;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import com.google.gson.Gson;
import com.jess.arms.base.BaseService;
import com.yj.coffeemachines.app.utils.FileUtils;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.VoiceMessage;
import com.yj.coffeemachines.greendao.daos.VoiceMessageDao;
import com.yj.coffeemachines.helper.Tools;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class VoicePlayService extends BaseService {
    private MediaPlayer mPlayer;

    /* loaded from: classes.dex */
    public class VoicePlayBinder extends Binder {
        public VoicePlayBinder() {
        }

        public VoicePlayService getService() {
            return VoicePlayService.this;
        }
    }

    @Override // com.jess.arms.base.BaseService
    public void init() {
    }

    @Override // com.jess.arms.base.BaseService, android.app.Service
    public IBinder onBind(Intent intent) {
        return new VoicePlayBinder();
    }

    public void playVoice(final int i) {
        List<VoiceMessage> list = DBHelper.getVoiceMessageDao().queryBuilder().where(VoiceMessageDao.Properties.PositionSort.eq(i + ""), new WhereCondition[0]).list();
        Tools.upLocalLog("语音播放，位置" + i);
        if (list.size() != 0) {
            VoiceMessage voiceMessage = list.get(0);
            Tools.upLocalLog("voiceMessage:" + new Gson().toJson(voiceMessage));
            String localPath = voiceMessage.getLocalPath();
            File file = new File(localPath);
            if (!FileUtils.isFileExists(localPath)) {
                Tools.upLocalLog("语音文件未下载完成，位置" + i);
                return;
            }
            String absolutePath = file.getAbsolutePath();
            MediaPlayer mediaPlayer = this.mPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                this.mPlayer.reset();
            } else {
                this.mPlayer = new MediaPlayer();
            }
            try {
                this.mPlayer.setDataSource(absolutePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.yj.coffeemachines.app.service.VoicePlayService.1
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer2) {
                }
            });
            this.mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.yj.coffeemachines.app.service.VoicePlayService.2
                @Override // android.media.MediaPlayer.OnErrorListener
                public boolean onError(MediaPlayer mediaPlayer2, int i2, int i3) {
                    Tools.upLocalLog("语音播放失败，位置" + i + ",what:" + i2 + ",extra:" + i3);
                    return false;
                }
            });
            try {
                this.mPlayer.prepareAsync();
                this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.yj.coffeemachines.app.service.VoicePlayService.3
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public void onPrepared(MediaPlayer mediaPlayer2) {
                        mediaPlayer2.start();
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
