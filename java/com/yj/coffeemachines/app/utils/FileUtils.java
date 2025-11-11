package com.yj.coffeemachines.app.utils;

import android.annotation.SuppressLint;
import android.app.kingsun.KingsunSmartAPI;
import android.content.Intent;
import android.net.Uri;
// // // // import android.os.tniuds.TniudsUtils; // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible
import android.util.Log;
// // // // import com.innohi.YNHAPI; // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible // Hardware propietario no disponible
import com.jess.arms.utils.ArmsUtils;
import com.yj.coffeemachines.Api;
import com.yj.coffeemachines.BuildConfig;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.FileMessage;
import com.yj.coffeemachines.greendao.daos.FileMessageDao;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.model.beans.CheckFileIntactBack;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public final class FileUtils {
    private FileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void broadcastUpdateApk(File file) {
        Intent intent = new Intent("com.ys.installapk");
        intent.putExtra("uri", file.getAbsolutePath());
        intent.putExtra("start", 1);
        intent.putExtra("pkgName", BuildConfig.APPLICATION_ID);
        intent.putExtra("activityName", "com.yj.coffeemachines.mvp.ui.activity.MainActivity");
        MyAppLocation.myAppLocation.getApplicationContext().sendBroadcast(intent);
    }

    public static String calculateMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
            }
            fileInputStream.close();
            String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
            while (bigInteger.length() < 32) {
                bigInteger = "0" + bigInteger;
            }
            return bigInteger;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkFile(final String str, final File file, final File file2) {
        String fileMD5ToString = com.blankj.utilcode.util.FileUtils.getFileMD5ToString(file.getAbsolutePath());
        Tools.upLocalLog("网络接口-缓存路径：" + file.getAbsolutePath() + "\nMD5：" + fileMD5ToString + "\n下载路径：" + str);
        ((Api) ArmsUtils.obtainAppComponentFromContext(MyAppLocation.myAppLocation).repositoryManager().obtainRetrofitService(Api.class)).checkFileIntact(fileMD5ToString, str).map(new Function() { // from class: com.yj.coffeemachines.app.utils.-$$Lambda$FileUtils$xsBD0pjlwSwo65xFVMr9X2fioqo
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return FileUtils.lambda$checkFile$0(str, (CheckFileIntactBack) obj);
            }
        }).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.app.utils.-$$Lambda$FileUtils$maobJcK0v-CLrKZMa31P-_SHEs8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                FileUtils.lambda$checkFile$1((Disposable) obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).doFinally(new Action() { // from class: com.yj.coffeemachines.app.utils.-$$Lambda$FileUtils$WWTbWwtHZU-U2_-WdMHqawX09UY
            @Override // io.reactivex.functions.Action
            public final void run() {
                FileUtils.lambda$checkFile$2();
            }
        }).subscribe(new ErrorHandleSubscriber<CheckFileIntactBack>(ArmsUtils.obtainAppComponentFromContext(MyAppLocation.myAppLocation).rxErrorHandler()) { // from class: com.yj.coffeemachines.app.utils.FileUtils.2
            @Override // io.reactivex.Observer
            @SuppressLint({"WrongConstant"})
            public void onNext(@NonNull CheckFileIntactBack checkFileIntactBack) {
                if (!checkFileIntactBack.getData()) {
                    FileUtils.deleteDir(file);
                    if (Constants.loadCount > 0) {
                        FileUtils.downloadFile(str, file);
                        Tools.upLocalLog("检查文件:" + file.getAbsolutePath() + "检查文件错误");
                    }
                    Constants.loadCount--;
                    return;
                }
                new Thread(new Runnable() { // from class: com.yj.coffeemachines.app.utils.FileUtils.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.blankj.utilcode.util.FileUtils.copy(file, file2);
                    }
                }).start();
                if (str.endsWith(".zip")) {
                    try {
                        if (ZIPUtils.GetFileList(file.getAbsolutePath(), true, false).size() > 0) {
                            ZIPUtils.UnZipFolder(file.getAbsolutePath(), MyAppLocation.myAppLocation.getFilesDir().getAbsolutePath());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (str.endsWith(".apk")) {
                    Uri.fromFile(file);
                    if (Constants.Model == 2) {
                        // // // // TniudsUtils.installAppCover(MyAppLocation.myAppLocation, file); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
                        return;
                    }
                    if (Constants.Model == 0) {
                        ((KingsunSmartAPI) MyAppLocation.myAppLocation.getSystemService("kingsunsmartapi")).installApk(file.getAbsolutePath());
                        Constants.isRestart = true;
                    } else if (Constants.Model == 1) {
                        // // // // YNHAPI.getInstance().installApkSilently(file.getAbsolutePath(), BuildConfig.APPLICATION_ID, "com.yj.coffeemachines.mvp.ui.activity.TranslucentActivity"); // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado // Hardware propietario comentado
                    } else if (Constants.Model == 3) {
                        FileUtils.broadcastUpdateApk(file);
                    }
                }
            }
        });
    }

    public static boolean createFileByDeleteOldFile(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && !file.delete()) || !createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    if (!deleteFile(file2)) {
                        return false;
                    }
                } else if (file2.isDirectory() && !deleteDir(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean deleteDir(String str) {
        return deleteDir(getFileByPath(str));
    }

    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static void downloadFile(final String str, final File file) {
        if (!Tools.toString(str).toLowerCase().contains("http")) {
            Tools.upLocalLog("下载路径检测有问题：fullPath" + str);
            return;
        }
        String str2 = Constants.tempPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + file.getName();
        if (isFileExists(str2)) {
            checkFile(str, new File(str2), file);
            Tools.upLocalLog("文件已存在，复制temp文件到本地文件夹：" + str2);
            return;
        }
        final File file2 = new File(str2);
        createFileByDeleteOldFile(file2);
        System.currentTimeMillis();
        ArmsUtils.obtainAppComponentFromContext(MyAppLocation.myAppLocation).okHttpClient().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.yj.coffeemachines.app.utils.FileUtils.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                FileUtils.deleteDir(file2);
                Tools.upLocalLog("下载失败：地址" + file2.getAbsolutePath() + ";原因：;" + iOException.toString());
            }

            /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
            
                if (r2 == null) goto L12;
             */
            /* JADX WARN: Code restructure failed: missing block: B:6:0x0039, code lost:
            
                com.yj.coffeemachines.helper.Tools.upLocalLog("下载成功：地址" + r1.getAbsolutePath());
                com.yj.coffeemachines.app.utils.FileUtils.checkFile(r2, r1, r3);
             */
            /* JADX WARN: Code restructure failed: missing block: B:7:0x005c, code lost:
            
                return;
             */
            @Override // okhttp3.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onResponse(okhttp3.Call r2, okhttp3.Response r3) throws java.io.IOException {
                /*
                    r1 = this;
                    r2 = 0
                    java.io.File r0 = r1     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
                    okio.Sink r0 = okio.Okio.sink(r0)     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
                    okio.BufferedSink r2 = okio.Okio.buffer(r0)     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
                    okhttp3.ResponseBody r3 = r3.body()     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
                    okio.BufferedSource r3 = r3.getBodySource()     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
                    r2.writeAll(r3)     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
                    r2.close()     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
                    if (r2 == 0) goto L39
                L1b:
                    r2.close()
                    goto L39
                L1f:
                    r3 = move-exception
                    goto L5d
                L21:
                    r3 = move-exception
                    r3.printStackTrace()     // Catch: java.lang.Throwable -> L1f
                    java.io.File r3 = r1     // Catch: java.lang.Throwable -> L1f
                    com.yj.coffeemachines.app.utils.FileUtils.deleteDir(r3)     // Catch: java.lang.Throwable -> L1f
                    java.lang.String r3 = "download failed;下载文件失败。"
                    com.yj.coffeemachines.helper.Tools.upLocalLog(r3)     // Catch: java.lang.Throwable -> L1f
                    java.lang.String r3 = r2     // Catch: java.lang.Throwable -> L1f
                    java.io.File r0 = r1     // Catch: java.lang.Throwable -> L1f
                    com.yj.coffeemachines.app.utils.FileUtils.downloadFile(r3, r0)     // Catch: java.lang.Throwable -> L1f
                    if (r2 == 0) goto L39
                    goto L1b
                L39:
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "下载成功：地址"
                    r2.append(r3)
                    java.io.File r3 = r1
                    java.lang.String r3 = r3.getAbsolutePath()
                    r2.append(r3)
                    java.lang.String r2 = r2.toString()
                    com.yj.coffeemachines.helper.Tools.upLocalLog(r2)
                    java.lang.String r2 = r2
                    java.io.File r3 = r1
                    java.io.File r0 = r3
                    com.yj.coffeemachines.app.utils.FileUtils.access$000(r2, r3, r0)
                    return
                L5d:
                    if (r2 == 0) goto L62
                    r2.close()
                L62:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.yj.coffeemachines.app.utils.FileUtils.AnonymousClass1.onResponse(okhttp3.Call, okhttp3.Response):void");
            }
        });
    }

    public static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static boolean isFileExists(String str) {
        return isFileExists(getFileByPath(str));
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CheckFileIntactBack lambda$checkFile$0(String str, CheckFileIntactBack checkFileIntactBack) throws Exception {
        FileMessageDao fileMessageDao = DBHelper.getFileMessageDao();
        List<FileMessage> list = fileMessageDao.queryBuilder().where(FileMessageDao.Properties.DownloadUrl.eq(str), new WhereCondition[0]).list();
        if (checkFileIntactBack.getSuccess() && checkFileIntactBack.getData()) {
            Iterator<FileMessage> it2 = list.iterator();
            while (it2.hasNext()) {
                it2.next().setCheck(true);
            }
            fileMessageDao.updateInTx(list);
        }
        return checkFileIntactBack;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$checkFile$1(Disposable disposable) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$checkFile$2() throws Exception {
    }

    public static File makeFilePath(String str, String str2) {
        File file;
        makeRootDirectory(str);
        try {
            file = new File(str + str2);
        } catch (Exception e) {
            e = e;
            file = null;
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return file;
        }
        return file;
    }

    public static void makeRootDirectory(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return;
            }
            file.mkdir();
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0050 A[Catch: IOException -> 0x0054, TryCatch #3 {IOException -> 0x0054, blocks: (B:3:0x0015, B:9:0x0030, B:24:0x0047, B:22:0x0053, B:21:0x0050, B:28:0x004c), top: B:2:0x0015, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String readTxtFromFile(java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            r0.append(r6)
            java.lang.String r5 = r0.toString()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.io.IOException -> L54
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.io.IOException -> L54
            r2.<init>(r5)     // Catch: java.io.IOException -> L54
            r1.<init>(r2)     // Catch: java.io.IOException -> L54
        L1f:
            java.lang.String r2 = r1.readLine()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3f
            if (r2 == 0) goto L30
            r6.append(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3f
            java.lang.String r2 = java.lang.System.lineSeparator()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3f
            r6.append(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3f
            goto L1f
        L30:
            r1.close()     // Catch: java.io.IOException -> L54
            java.lang.String r5 = r6.toString()
            java.lang.String r5 = r5.trim()
            return r5
        L3c:
            r6 = move-exception
            r2 = r0
            goto L45
        L3f:
            r6 = move-exception
            throw r6     // Catch: java.lang.Throwable -> L41
        L41:
            r2 = move-exception
            r4 = r2
            r2 = r6
            r6 = r4
        L45:
            if (r2 == 0) goto L50
            r1.close()     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L54
            goto L53
        L4b:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch: java.io.IOException -> L54
            goto L53
        L50:
            r1.close()     // Catch: java.io.IOException -> L54
        L53:
            throw r6     // Catch: java.io.IOException -> L54
        L54:
            r6 = move-exception
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Error reading file '"
            r2.append(r3)
            r2.append(r5)
            java.lang.String r5 = "': "
            r2.append(r5)
            java.lang.String r5 = r6.getMessage()
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            r1.println(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yj.coffeemachines.app.utils.FileUtils.readTxtFromFile(java.lang.String, java.lang.String):java.lang.String");
    }

    public static void writeTxtToFile(String str, String str2, String str3) {
        makeFilePath(str2, str3);
        String str4 = str2 + str3;
        String str5 = str + "\r\n";
        try {
            File file = new File(str4);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + str4);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(file.length());
            randomAccessFile.write(str5.getBytes());
            randomAccessFile.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }
}
