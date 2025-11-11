package com.yj.coffeemachines.app.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import com.yj.coffeemachines.R;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* loaded from: classes.dex */
public class NotificationHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private NotificationCompat.Builder mBuilder;
    private Context mContext;
    private NotificationManager mNotificationManager;

    public NotificationHelper(Context context) {
        this.mContext = context;
    }

    private void showBigNotification(Bitmap bitmap, NotificationCompat.Builder builder, int i, String str, String str2, PendingIntent pendingIntent) {
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle(str);
        bigPictureStyle.setSummaryText(Html.fromHtml(str2).toString());
        bigPictureStyle.bigPicture(bitmap);
        builder.setSmallIcon(i).setTicker(str).setWhen(0L).setAutoCancel(true).setContentTitle(str).setContentIntent(pendingIntent).setStyle(bigPictureStyle).setSmallIcon(i).setLargeIcon(BitmapFactory.decodeResource(this.mContext.getResources(), i)).setContentText(str2).build();
        this.mNotificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", 4);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            this.mNotificationManager.createNotificationChannel(notificationChannel);
        }
        this.mNotificationManager.notify(0, builder.build());
    }

    private void showSmallNotification(NotificationCompat.Builder builder, int i, String str, String str2, PendingIntent pendingIntent) {
        builder.setSmallIcon(i);
        builder.setContentTitle(str).setContentText(str2).setAutoCancel(false).setSound(Settings.System.DEFAULT_NOTIFICATION_URI).setContentIntent(pendingIntent);
        this.mNotificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", 4);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            this.mNotificationManager.createNotificationChannel(notificationChannel);
        }
        this.mNotificationManager.notify(0, builder.build());
    }

    public void createNotification(String str, String str2, int i, Intent intent, int i2) {
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        PendingIntent activity = PendingIntent.getActivity(this.mContext, 0, intent, 134217728);
        this.mBuilder = new NotificationCompat.Builder(this.mContext);
        this.mBuilder.setSmallIcon(i);
        this.mBuilder.setContentTitle(str).setContentText(str2).setAutoCancel(false).setPriority(i2).setSound(Settings.System.DEFAULT_NOTIFICATION_URI).setContentIntent(activity);
        this.mNotificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", 4);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            this.mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            this.mNotificationManager.createNotificationChannel(notificationChannel);
        }
        this.mNotificationManager.notify(0, this.mBuilder.build());
    }

    public void createNotification(String str, String str2, int i, String str3, Intent intent, int i2) {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        }
        this.mBuilder = new NotificationCompat.Builder(this.mContext).setPriority(i2);
        intent.setFlags(603979776);
        PendingIntent activity = PendingIntent.getActivity(this.mContext, 0, intent, ClientDefaults.MAX_MSG_SIZE);
        if (TextUtils.isEmpty(str3)) {
            showSmallNotification(this.mBuilder, i, str, str2, activity);
            return;
        }
        if (str3 == null || str3.length() <= 4 || !Patterns.WEB_URL.matcher(str3).matches()) {
            return;
        }
        Bitmap bitmapFromURL = getBitmapFromURL(str3);
        if (bitmapFromURL != null) {
            showBigNotification(bitmapFromURL, this.mBuilder, i, str, str2, activity);
        } else {
            showSmallNotification(this.mBuilder, i, str, str2, activity);
        }
    }

    public void createNotification(String str, String str2, Intent intent, int i) {
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        PendingIntent activity = PendingIntent.getActivity(this.mContext, 0, intent, 134217728);
        this.mBuilder = new NotificationCompat.Builder(this.mContext);
        this.mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        this.mBuilder.setContentTitle(str).setContentText(str2).setAutoCancel(false).setSound(Settings.System.DEFAULT_NOTIFICATION_URI).setPriority(i).setContentIntent(activity);
        this.mNotificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", 4);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            this.mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            this.mNotificationManager.createNotificationChannel(notificationChannel);
        }
        this.mNotificationManager.notify(0, this.mBuilder.build());
    }

    public Bitmap getBitmapFromURL(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
