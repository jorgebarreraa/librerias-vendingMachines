package com.yj.coffeemachines.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.ui.activity.HomeActivity;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* loaded from: classes.dex */
public class BootCompleteReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent intent2 = new Intent(context, (Class<?>) HomeActivity.class);
            intent2.setFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent2);
            Tools.upLocalLog("开机广播，启动应用");
        }
    }
}
