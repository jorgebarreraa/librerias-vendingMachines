package com.yj.coffeemachines.mvp.contract;

import android.content.Context;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.yj.coffeemachines.greendao.beans.FileMessage;
import com.yj.coffeemachines.mvp.model.beans.CheckFileIntactBack;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.ResponseBody;

/* loaded from: classes.dex */
public interface Ops9_LocalFileContract {

    /* loaded from: classes.dex */
    public interface Model extends IModel {
        Observable<CheckFileIntactBack> checkFile(String str, String str2);

        Observable<ResponseBody> download(String str);
    }

    /* loaded from: classes.dex */
    public interface View extends IView {
        Context getActivity();

        void setData(List<FileMessage> list);
    }
}
