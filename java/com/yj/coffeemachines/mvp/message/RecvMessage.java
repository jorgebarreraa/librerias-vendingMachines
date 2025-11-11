package com.yj.coffeemachines.mvp.message;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class RecvMessage implements IMessage {
    private String command;
    private String message;
    private String source;
    private String sourceName;
    private String time;

    public RecvMessage(String str, String str2) {
        this.source = str;
        this.sourceName = str;
        String[] stringArray = StringUtils.getStringArray(R.array.seriaport);
        if (str.equals(Constants.SERIAPort_currentgrinding())) {
            this.sourceName = stringArray[2];
        } else if (str.equals(Constants.SERIAPort_instant())) {
            this.sourceName = stringArray[0];
        } else if (str.equals(Constants.SERIAPort_payment())) {
            this.sourceName = stringArray[1];
        }
        this.time = TimeUtils.getNowString();
        this.command = str2;
        this.message = StringUtils.getString(R.string.receive_data) + "：" + str2;
    }

    @Override // com.yj.coffeemachines.mvp.message.IMessage
    public String getMessage() {
        return this.message;
    }

    @Override // com.yj.coffeemachines.mvp.message.IMessage
    public String getSource() {
        return this.source;
    }

    @Override // com.yj.coffeemachines.mvp.message.IMessage
    public String getSourceNmae() {
        return this.sourceName;
    }

    @Override // com.yj.coffeemachines.mvp.message.IMessage
    public String getTime() {
        return this.time;
    }

    @Override // com.yj.coffeemachines.mvp.message.IMessage
    public boolean isToSend() {
        return false;
    }
}
