package com.jess.arms.integration;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.jess.arms.utils.ArmsUtils;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import timber.log.Timber;

/* loaded from: classes.dex */
public final class AppManager {
    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_not_add_activity_list";
    private static volatile AppManager sAppManager;
    protected final String TAG = getClass().getSimpleName();
    private List<Activity> mActivityList;
    private Application mApplication;
    private Activity mCurrentActivity;
    private HandleListener mHandleListener;

    @Deprecated
    /* loaded from: classes.dex */
    public interface HandleListener {
        void handleMessage(AppManager appManager, Message message);
    }

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if (sAppManager == null) {
            synchronized (AppManager.class) {
                if (sAppManager == null) {
                    sAppManager = new AppManager();
                }
            }
        }
        return sAppManager;
    }

    @Deprecated
    public static void post(Message message) {
        getAppManager().onReceive(message);
    }

    public boolean activityClassIsLive(Class<?> cls) {
        List<Activity> list = this.mActivityList;
        if (list == null) {
            Timber.tag(this.TAG).w("mActivityList == null when activityClassIsLive(Class)", new Object[0]);
            return false;
        }
        Iterator<Activity> it2 = list.iterator();
        while (it2.hasNext()) {
            if (it2.next().getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean activityInstanceIsLive(Activity activity) {
        List<Activity> list = this.mActivityList;
        if (list != null) {
            return list.contains(activity);
        }
        Timber.tag(this.TAG).w("mActivityList == null when activityInstanceIsLive(Activity)", new Object[0]);
        return false;
    }

    public void addActivity(Activity activity) {
        synchronized (AppManager.class) {
            List<Activity> activityList = getActivityList();
            if (!activityList.contains(activity)) {
                activityList.add(activity);
            }
        }
    }

    public void appExit() {
        try {
            killAll();
            Process.killProcess(Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Activity findActivity(Class<?> cls) {
        List<Activity> list = this.mActivityList;
        if (list == null) {
            Timber.tag(this.TAG).w("mActivityList == null when findActivity(Class)", new Object[0]);
            return null;
        }
        for (Activity activity : list) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    public List<Activity> getActivityList() {
        if (this.mActivityList == null) {
            this.mActivityList = new LinkedList();
        }
        return this.mActivityList;
    }

    @Nullable
    public Activity getCurrentActivity() {
        return this.mCurrentActivity;
    }

    @Deprecated
    public HandleListener getHandleListener() {
        return this.mHandleListener;
    }

    @Nullable
    public Activity getTopActivity() {
        List<Activity> list = this.mActivityList;
        if (list == null) {
            Timber.tag(this.TAG).w("mActivityList == null when getTopActivity()", new Object[0]);
            return null;
        }
        if (list.size() <= 0) {
            return null;
        }
        return this.mActivityList.get(r0.size() - 1);
    }

    public AppManager init(Application application) {
        this.mApplication = application;
        return sAppManager;
    }

    public void killActivity(Class<?> cls) {
        if (this.mActivityList == null) {
            Timber.tag(this.TAG).w("mActivityList == null when killActivity(Class)", new Object[0]);
            return;
        }
        synchronized (AppManager.class) {
            Iterator<Activity> it2 = getActivityList().iterator();
            while (it2.hasNext()) {
                Activity next = it2.next();
                if (next.getClass().equals(cls)) {
                    it2.remove();
                    next.finish();
                }
            }
        }
    }

    public void killAll() {
        synchronized (AppManager.class) {
            Iterator<Activity> it2 = getActivityList().iterator();
            while (it2.hasNext()) {
                Activity next = it2.next();
                it2.remove();
                next.finish();
            }
        }
    }

    public void killAll(Class<?>... clsArr) {
        List asList = Arrays.asList(clsArr);
        synchronized (AppManager.class) {
            Iterator<Activity> it2 = getActivityList().iterator();
            while (it2.hasNext()) {
                Activity next = it2.next();
                if (!asList.contains(next.getClass())) {
                    it2.remove();
                    next.finish();
                }
            }
        }
    }

    public void killAll(String... strArr) {
        List asList = Arrays.asList(strArr);
        synchronized (AppManager.class) {
            Iterator<Activity> it2 = getActivityList().iterator();
            while (it2.hasNext()) {
                Activity next = it2.next();
                if (!asList.contains(next.getClass().getName())) {
                    it2.remove();
                    next.finish();
                }
            }
        }
    }

    public /* synthetic */ void lambda$showSnackbar$0$AppManager(String str, boolean z) throws Exception {
        try {
            ArmsUtils.makeText(this.mApplication, str);
        } catch (Exception | NoClassDefFoundError unused) {
            Toast.makeText(this.mApplication, str, z ? 1 : 0).show();
        }
    }

    @Deprecated
    public void onReceive(Message message) {
        HandleListener handleListener = this.mHandleListener;
        if (handleListener != null) {
            handleListener.handleMessage(this, message);
        }
    }

    public void release() {
        this.mActivityList.clear();
        this.mHandleListener = null;
        this.mActivityList = null;
        this.mCurrentActivity = null;
        this.mApplication = null;
    }

    public Activity removeActivity(int i) {
        if (this.mActivityList == null) {
            Timber.tag(this.TAG).w("mActivityList == null when removeActivity(int)", new Object[0]);
            return null;
        }
        synchronized (AppManager.class) {
            if (i > 0) {
                if (i < this.mActivityList.size()) {
                    return this.mActivityList.remove(i);
                }
            }
            return null;
        }
    }

    public void removeActivity(Activity activity) {
        if (this.mActivityList == null) {
            Timber.tag(this.TAG).w("mActivityList == null when removeActivity(Activity)", new Object[0]);
        } else {
            synchronized (AppManager.class) {
                this.mActivityList.remove(activity);
            }
        }
    }

    public void setCurrentActivity(Activity activity) {
        this.mCurrentActivity = activity;
    }

    @Deprecated
    public void setHandleListener(HandleListener handleListener) {
        this.mHandleListener = handleListener;
    }

    public void showSnackbar(final String str, final boolean z) {
        if (getCurrentActivity() == null && getTopActivity() == null) {
            Timber.tag(this.TAG).w("mCurrentActivity == null when showSnackbar(String,boolean)", new Object[0]);
        } else {
            Completable.fromAction(new Action() { // from class: com.jess.arms.integration.-$$Lambda$AppManager$ltkrp_serNmGG_nwHnmb7s5d7cc
                @Override // io.reactivex.functions.Action
                public final void run() {
                    AppManager.this.lambda$showSnackbar$0$AppManager(str, z);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
        }
    }

    public void startActivity(Intent intent) {
        if (getTopActivity() != null) {
            getTopActivity().startActivity(intent);
            return;
        }
        Timber.tag(this.TAG).w("mCurrentActivity == null when startActivity(Intent)", new Object[0]);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        this.mApplication.startActivity(intent);
    }

    public void startActivity(Class cls) {
        startActivity(new Intent(this.mApplication, (Class<?>) cls));
    }
}
