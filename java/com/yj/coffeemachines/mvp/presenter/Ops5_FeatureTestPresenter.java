package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Ops5_FeatureTestContract;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

@FragmentScope
/* loaded from: classes.dex */
public class Ops5_FeatureTestPresenter extends BasePresenter<Ops5_FeatureTestContract.Model, Ops5_FeatureTestContract.View> {
    private int anInt;
    public long currentTimeMillis;

    @Inject
    AppManager mAppManager;

    @Inject
    Application mApplication;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    ImageLoader mImageLoader;
    public boolean startcurrent;

    @Inject
    public Ops5_FeatureTestPresenter(Ops5_FeatureTestContract.Model model, Ops5_FeatureTestContract.View view) {
        super(model, view);
        this.anInt = 1;
    }

    public void getDrink() {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_SellOneCupDrink, this.anInt, 2));
    }

    int getGrindWaterQty() {
        int i = Constants.needCurrentmicDate;
        int micRate = (Constants.needCurrent_MaterialMessage.ischange() && Constants.opsSeting().isAdjust()) ? Constants.needCurrent_MaterialMessage.getMicRate() - 50 : Constants.needCurrent_MaterialMessage.getMicRate();
        double grindWaterQty = Constants.needCurrent_data.getGrindWaterQty();
        float f = (float) (grindWaterQty + (((micRate * grindWaterQty) / 2.5d) / 100.0d));
        if (f < 0.0f) {
            return 0;
        }
        return (int) f;
    }

    int getQtyMic() {
        int grindQtyMic = ((int) Constants.needCurrent_data.getGrindQtyMic()) + Constants.needCurrent_MaterialMessage.getLocalMaterialFineTuning();
        if (grindQtyMic <= -20) {
            grindQtyMic = -20;
        }
        if (grindQtyMic >= 20) {
            return 20;
        }
        return grindQtyMic;
    }

    public void keepCurrent() {
        for (int i = 0; i < 3; i++) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Currentgrinding(Constants.getOrderBytes_Currentgrinding((byte) 22, (byte) 90, (byte) Constants.needCurrent_data.getGrindQty(), (byte) getGrindWaterQty(), (byte) getQtyMic(), (byte) Constants.needCurrent_data.getGrindWaterQtyMic(), (byte) 0));
        }
    }

    @Override // com.jess.arms.mvp.BasePresenter, com.jess.arms.mvp.IPresenter
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void sendFreshGroundCoffeeFinished() {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.FreshGroundCoffeeFinished, 1));
    }

    public void setGetCupType() {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_SetCupFallingWay, 0));
    }

    public void setWaterAndMetail() {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Tools.strToByte("AA55231501000000000000000000000000000000000000000000000000000A000A000000004C"));
    }

    public void standby() {
        MyAppLocation.myAppLocation.mSerialDataService.standby();
    }
}
