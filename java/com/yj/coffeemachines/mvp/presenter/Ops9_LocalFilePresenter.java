package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.FileMessage;
import com.yj.coffeemachines.greendao.daos.FileMessageDao;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Ops9_LocalFileContract;
import com.yj.coffeemachines.mvp.model.beans.CheckFileIntactBack;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import okhttp3.ResponseBody;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.greenrobot.greendao.query.WhereCondition;

@FragmentScope
/* loaded from: classes.dex */
public class Ops9_LocalFilePresenter extends BasePresenter<Ops9_LocalFileContract.Model, Ops9_LocalFileContract.View> {

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    ImageLoader mImageLoader;

    @Inject
    public Ops9_LocalFilePresenter(Ops9_LocalFileContract.Model model, Ops9_LocalFileContract.View view) {
        super(model, view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ File lambda$downloadFile$10(FileMessage fileMessage, CheckFileIntactBack checkFileIntactBack) throws Exception {
        if (!checkFileIntactBack.getSuccess() || !checkFileIntactBack.getData()) {
            Tools.upLocalLog("文件验证失败，请检查！");
            throw new Exception("文件验证失败，请检查！");
        }
        Tools.upLocalLog("本地文件：写入正式文件夹");
        File file = new File(Constants.tempPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileMessage.getFileName());
        File file2 = new File(fileMessage.getLocalPath());
        FileUtils.copy(file, file2);
        return file2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FileMessage lambda$downloadFile$4(FileMessage fileMessage, FileMessage fileMessage2) throws Exception {
        com.yj.coffeemachines.app.utils.FileUtils.deleteFile(new File(fileMessage.getLocalPath()));
        com.yj.coffeemachines.app.utils.FileUtils.deleteFile(new File(Constants.tempPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileMessage.getFileName()));
        Tools.upLocalLog("本地文件：清理旧文件");
        return fileMessage2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FileMessage lambda$downloadFile$5(FileMessage fileMessage) throws Exception {
        FileMessageDao fileMessageDao = DBHelper.getFileMessageDao();
        fileMessage.setCheck(false);
        fileMessageDao.updateInTx(fileMessage);
        Tools.upLocalLog("本地文件：重置校验状态");
        return fileMessage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ File lambda$downloadFile$7(FileMessage fileMessage, ResponseBody responseBody) throws Exception {
        Tools.upLocalLog("本地文件：开始写入临时文件夹");
        File file = new File(Constants.tempPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(Constants.tempPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileMessage.getFileName());
        if (com.yj.coffeemachines.app.utils.FileUtils.isFileExists(file2)) {
            com.yj.coffeemachines.app.utils.FileUtils.deleteFile(file2);
        }
        if (FileIOUtils.writeFileFromIS(file2, responseBody.byteStream(), true)) {
        }
        return file2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CheckFileIntactBack lambda$downloadFile$9(FileMessage fileMessage, CheckFileIntactBack checkFileIntactBack) throws Exception {
        FileMessageDao fileMessageDao = DBHelper.getFileMessageDao();
        List<FileMessage> list = fileMessageDao.queryBuilder().where(FileMessageDao.Properties.Key.eq(fileMessage.getKey()), new WhereCondition[0]).list();
        if (checkFileIntactBack.getSuccess() && checkFileIntactBack.getData()) {
            Tools.upLocalLog("本地文件：检验文件通过，更新本地状态");
            Iterator<FileMessage> it2 = list.iterator();
            while (it2.hasNext()) {
                it2.next().setCheck(true);
            }
            fileMessageDao.updateInTx(list);
        }
        return checkFileIntactBack;
    }

    public void downloadFile(final FileMessage fileMessage) {
        Tools.upLocalLog("本地文件：文件重新下载");
        Observable.just(fileMessage).map(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$XIvj4WuemMBBZf4dC8iF5ipue6I
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Ops9_LocalFilePresenter.lambda$downloadFile$4(FileMessage.this, (FileMessage) obj);
            }
        }).map(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$_nrDHCQ0AGHk-m2oDIp0_w18NeQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Ops9_LocalFilePresenter.lambda$downloadFile$5((FileMessage) obj);
            }
        }).flatMap(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$6gkhyUjvBIrsq4zi91zAY6VANsg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Ops9_LocalFilePresenter.this.lambda$downloadFile$6$Ops9_LocalFilePresenter((FileMessage) obj);
            }
        }).map(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$RIiLjUEh5OEx7sDKZTWtpJ_6G1A
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Ops9_LocalFilePresenter.lambda$downloadFile$7(FileMessage.this, (ResponseBody) obj);
            }
        }).flatMap(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$bACQpZ8iyo6XuNWxFh9eBd1jmWI
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Ops9_LocalFilePresenter.this.lambda$downloadFile$8$Ops9_LocalFilePresenter(fileMessage, (File) obj);
            }
        }).map(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$yo9RhdVw-SQgRdZPZndTMrLEPIU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Ops9_LocalFilePresenter.lambda$downloadFile$9(FileMessage.this, (CheckFileIntactBack) obj);
            }
        }).map(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$Ij9rurFTWAEkcA6hTRKDHD300Ls
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Ops9_LocalFilePresenter.lambda$downloadFile$10(FileMessage.this, (CheckFileIntactBack) obj);
            }
        }).doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$ZsVD0502I4hCwP55ByV-4jPzHA4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Ops9_LocalFilePresenter.this.lambda$downloadFile$11$Ops9_LocalFilePresenter((Disposable) obj);
            }
        }).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$aCWASy6KxsR6RMOcceGSNgEOqes
            @Override // io.reactivex.functions.Action
            public final void run() {
                Ops9_LocalFilePresenter.this.lambda$downloadFile$12$Ops9_LocalFilePresenter();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<File>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Ops9_LocalFilePresenter.2
            @Override // me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber, io.reactivex.Observer
            public void onError(Throwable th) {
                super.onError(th);
                ((Ops9_LocalFileContract.View) Ops9_LocalFilePresenter.this.mRootView).showMessage(Ops9_LocalFilePresenter.this.mApplication.getString(R.string.download_fail));
            }

            @Override // io.reactivex.Observer
            public void onNext(File file) {
                Ops9_LocalFilePresenter.this.getFileMessage();
                ((Ops9_LocalFileContract.View) Ops9_LocalFilePresenter.this.mRootView).showMessage(Ops9_LocalFilePresenter.this.mApplication.getString(R.string.download_success));
            }
        });
    }

    public String formatTraffic(long j) {
        if (j <= 0) {
            return "0 B";
        }
        String[] strArr = {"B", "KB", "MB", "GB", "TB"};
        double d = j;
        int i = 0;
        while (d >= 1024.0d && i < strArr.length - 1) {
            d /= 1024.0d;
            i++;
        }
        return String.format(Locale.getDefault(), "%.2f %s", Double.valueOf(d), strArr[i]);
    }

    public void getFileMessage() {
        Observable.just(1).flatMap(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$MWErT7oCQdBPcbc7N7zFUynnss4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource just;
                just = Observable.just(DBHelper.getFileMessageDao().queryBuilder().list());
                return just;
            }
        }).flatMap(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$PMcQipigtANKXr_LQnPxZn2-Bmc
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Observable.fromIterable((List) obj);
            }
        }).map(new Function() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$Ge37dkVTO-dCx5Ew0HKBcsBC3Ho
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return Ops9_LocalFilePresenter.this.lambda$getFileMessage$1$Ops9_LocalFilePresenter((FileMessage) obj);
            }
        }).toList().toObservable().doOnSubscribe(new Consumer() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$yh9cIFapsR2l2qsWmj7Q0uGndlM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Ops9_LocalFilePresenter.this.lambda$getFileMessage$2$Ops9_LocalFilePresenter((Disposable) obj);
            }
        }).doFinally(new Action() { // from class: com.yj.coffeemachines.mvp.presenter.-$$Lambda$Ops9_LocalFilePresenter$sEDOukk_MPasrLuD_-egjA16SG0
            @Override // io.reactivex.functions.Action
            public final void run() {
                Ops9_LocalFilePresenter.this.lambda$getFileMessage$3$Ops9_LocalFilePresenter();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(RxLifecycleUtils.bindToLifecycle(this.mRootView)).subscribe(new ErrorHandleSubscriber<List<FileMessage>>(this.mErrorHandler) { // from class: com.yj.coffeemachines.mvp.presenter.Ops9_LocalFilePresenter.1
            @Override // io.reactivex.Observer
            public void onNext(List<FileMessage> list) {
                ((Ops9_LocalFileContract.View) Ops9_LocalFilePresenter.this.mRootView).setData(list);
            }
        });
    }

    public /* synthetic */ void lambda$downloadFile$11$Ops9_LocalFilePresenter(Disposable disposable) throws Exception {
        ((Ops9_LocalFileContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$downloadFile$12$Ops9_LocalFilePresenter() throws Exception {
        ((Ops9_LocalFileContract.View) this.mRootView).hideLoading();
    }

    public /* synthetic */ ObservableSource lambda$downloadFile$6$Ops9_LocalFilePresenter(FileMessage fileMessage) throws Exception {
        return ((Ops9_LocalFileContract.Model) this.mModel).download(fileMessage.getDownloadUrl());
    }

    public /* synthetic */ ObservableSource lambda$downloadFile$8$Ops9_LocalFilePresenter(FileMessage fileMessage, File file) throws Exception {
        Tools.upLocalLog("本地文件：开始检验文件");
        return ((Ops9_LocalFileContract.Model) this.mModel).checkFile(FileUtils.getFileMD5ToString(file.getAbsolutePath()), fileMessage.getDownloadUrl());
    }

    public /* synthetic */ FileMessage lambda$getFileMessage$1$Ops9_LocalFilePresenter(FileMessage fileMessage) throws Exception {
        File file = new File(fileMessage.getLocalPath());
        fileMessage.setLocalExists(file.exists());
        fileMessage.setFileSize(formatTraffic(file.length()));
        return fileMessage;
    }

    public /* synthetic */ void lambda$getFileMessage$2$Ops9_LocalFilePresenter(Disposable disposable) throws Exception {
        ((Ops9_LocalFileContract.View) this.mRootView).showLoading();
    }

    public /* synthetic */ void lambda$getFileMessage$3$Ops9_LocalFilePresenter() throws Exception {
        ((Ops9_LocalFileContract.View) this.mRootView).hideLoading();
    }

    @Override // com.jess.arms.mvp.BasePresenter, com.jess.arms.mvp.IPresenter
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
