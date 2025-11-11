package com.yj.coffeemachines.mvp.contract;

import android.content.Context;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.yj.coffeemachines.mvp.model.beans.DeviceInfoBean;
import com.yj.coffeemachines.mvp.model.beans.DeviceQrBean;
import com.yj.coffeemachines.mvp.model.beans.ListTypeAllMaterialBack;
import com.yj.coffeemachines.mvp.model.beans.PositionVoiceListBack;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;
import com.yj.coffeemachines.mvp.model.beans.ProgramPlanListBack;
import io.reactivex.Observable;
import java.io.File;
import java.util.List;

/* loaded from: classes.dex */
public interface Step1Contract {

    /* loaded from: classes.dex */
    public interface Model extends IModel {
        Observable<DeviceInfoBean> getDeviceInfo();

        Observable<List<ProductBean>> getGoodsInfo();

        Observable<File> getMaxAppVersion();

        Observable<String> getPayWaySetting();

        Observable<DeviceQrBean> getQrConfig();

        Observable<ListTypeAllMaterialBack> listTypeAllMaterial();

        Observable<PositionVoiceListBack> positionVoiceList();

        Observable<ProgramPlanListBack> programPlanList();
    }

    /* loaded from: classes.dex */
    public interface View extends IView {
        void adCountdown(boolean z);

        Context getActivity();

        void refreshData(List<ProductBean> list);

        void showLoading(String str);
    }
}
