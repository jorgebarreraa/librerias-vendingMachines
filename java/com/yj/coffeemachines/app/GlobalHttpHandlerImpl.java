package com.yj.coffeemachines.app;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.DeviceUtils;
import com.jess.arms.http.GlobalHttpHandler;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.app.utils.MD5Utils;
import com.yj.coffeemachines.eventbusbean.refreshStep1;
import com.yj.coffeemachines.helper.LanguageHelper;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.model.beans.BaseBackBean;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class GlobalHttpHandlerImpl implements GlobalHttpHandler {
    private Context context;

    public GlobalHttpHandlerImpl(Context context) {
        this.context = context;
    }

    private void createDirectory(File file) {
        if (!file.exists() && !file.mkdirs()) {
            throw new RuntimeException(file.getAbsolutePath());
        }
    }

    private String userAgent() {
        String str;
        String str2 = (((("Machine/34520") + " ") + "(android:" + Build.VERSION.RELEASE + "; ") + DeviceUtils.getModel() + "; ") + "pcb v0) ";
        if (Constants.deviceTypeDetail != null) {
            str = str2 + "coffee/" + Constants.deviceTypeDetail.getName() + " ";
        } else {
            str = str2 + "coffee/- ";
        }
        return ((((str + "apk/Main-3.4.52 ") + "hardware/" + Constants.hardware + " ") + "firmware/" + Constants.firmware + " ") + "device/" + Constants.deviceSN() + " ") + "language/" + LanguageHelper.language().getLangCode() + " ";
    }

    @Override // com.jess.arms.http.GlobalHttpHandler
    @NonNull
    public Request onHttpRequestBefore(@NonNull Interceptor.Chain chain, @NonNull Request request) {
        long currentTimeMillis = System.currentTimeMillis();
        String deviceSN = Constants.deviceSN();
        return chain.request().newBuilder().addHeader("device", deviceSN).addHeader("timestamp", currentTimeMillis + "").addHeader("md5", MD5Utils.md5_32_low(currentTimeMillis + deviceSN)).addHeader("User-Agent", userAgent()).build();
    }

    @Override // com.jess.arms.http.GlobalHttpHandler
    @NonNull
    public Response onHttpResultResponse(@Nullable String str, @NonNull Interceptor.Chain chain, @NonNull Response response) {
        if (str != null && !str.isEmpty()) {
            BaseBackBean baseBackBean = (BaseBackBean) JSON.parseObject(str, BaseBackBean.class);
            if ((baseBackBean.getCode() != 400 || !baseBackBean.toString().contains("未查询到预支付记录")) && baseBackBean.getCode() != 200) {
                if (str.contains("https://docs.github.com/rest")) {
                    EventBus.getDefault().post(new refreshStep1());
                }
                Tools.upLocalLog("网络下载数据错误，httpResult：" + str + ";baseBackBean:" + baseBackBean.toString());
                if (baseBackBean.getMsg() == null) {
                    Constants.netError++;
                } else if (!baseBackBean.getMsg().contains("E00001") && !baseBackBean.getMsg().contains("E00002") && !baseBackBean.getMsg().contains("业务异常分账信息有误") && !baseBackBean.getMsg().contains("业务异常分账信息有误")) {
                    Constants.netError++;
                } else if (!Constants.e00001 && baseBackBean.getMsg().toLowerCase().equals("e00001")) {
                    Constants.e00001 = true;
                } else if (!Constants.e00002 && baseBackBean.getMsg().toLowerCase().equals("e00002")) {
                    Constants.e00002 = true;
                }
                if (Constants.netError > Constants.netErrorNum) {
                    Constants.reBoot();
                }
            }
        }
        return response;
    }

    public void saveUserToJsonFile(String str) {
        String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
        createDirectory(new File(Constants.resPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + format));
        String format2 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = new File(Constants.resPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + format);
        StringBuilder sb = new StringBuilder();
        sb.append(format2);
        sb.append(".json");
        File file2 = new File(file, sb.toString());
        createDirectory(file2);
        try {
            FileWriter fileWriter = new FileWriter(file2);
            Throwable th = null;
            try {
                fileWriter.write(str);
                fileWriter.close();
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
