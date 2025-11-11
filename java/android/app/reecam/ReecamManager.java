package android.app.reecam;

/* loaded from: classes.dex */
public interface ReecamManager {
    boolean getDoorStatus();

    String getSerial();

    void rebootSystem();

    void setHideNavBar(boolean z);

    boolean setSerial(String str);

    void silentInstallApk(String str, String str2, boolean z);

    void silentUnInstallApk(String str);
}
