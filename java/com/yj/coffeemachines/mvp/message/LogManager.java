package com.yj.coffeemachines.mvp.message;

import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class LogManager {
    private boolean mAutoEnd = true;
    public final List<IMessage> messages = new ArrayList();

    /* loaded from: classes.dex */
    private static class InstanceHolder {
        public static LogManager sManager = new LogManager();

        private InstanceHolder() {
        }
    }

    public static LogManager instance() {
        return InstanceHolder.sManager;
    }

    public void add(IMessage iMessage) {
        this.messages.add(iMessage);
    }

    public void changAutoEnd() {
        this.mAutoEnd = !this.mAutoEnd;
    }

    public void clear() {
        this.messages.clear();
    }

    public boolean isAutoEnd() {
        return this.mAutoEnd;
    }

    public void post(IMessage iMessage) {
        EventBus.getDefault().post(iMessage);
    }

    public void setAutoEnd(boolean z) {
        this.mAutoEnd = z;
    }
}
