package com.yj.coffeemachines.mvp.presenter;

import android.app.Application;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.eventbusbean.MyEventBusMessage;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.Step4Contract;
import com.yj.coffeemachines.mvp.model.beans.BaseMaterial;
import com.yj.coffeemachines.mvp.ui.fragment.Step4Fragment;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import org.greenrobot.eventbus.EventBus;

@FragmentScope
/* loaded from: classes.dex */
public class Step4Presenter extends BasePresenter<Step4Contract.Model, Step4Contract.View> {
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
    public Step4Presenter(Step4Contract.Model model, Step4Contract.View view) {
        super(model, view);
    }

    public void changeCardReadState(int i) {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment_main(Constants.drinkStatus_payment((byte) i));
    }

    public void getDrink() {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant_main(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_SellOneCupDrink, this.anInt, 2));
        MyAppLocation.myAppLocation.mSerialDataService.cleanInstantSendList();
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
        return Math.min(Math.max(((int) Constants.needCurrent_data.getGrindQtyMic()) + Constants.needCurrent_MaterialMessage.getLocalMaterialFineTuning(), -20), 20);
    }

    public void keepCurrent() {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Currentgrinding_main(Constants.getOrderBytes_Currentgrinding((byte) 22, (byte) 90, (byte) Constants.needCurrent_data.getGrindQty(), (byte) getGrindWaterQty(), (byte) getQtyMic(), (byte) Constants.needCurrent_data.getGrindWaterQtyMic(), (byte) 0));
    }

    public void makeFailure(String str) {
        if (Constants.buy_type == 2) {
            changeCardReadState(1);
        }
        if (Constants.buy_type == 3) {
            Constants.opsSeting().isChange();
        }
        MyEventBusMessage myEventBusMessage = new MyEventBusMessage();
        myEventBusMessage.setFrom(Step4Fragment.tag);
        myEventBusMessage.setTag(2);
        myEventBusMessage.setMsg(str);
        EventBus.getDefault().post(myEventBusMessage);
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
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant_main(Constants.getOrderBytes_instant(Constants.CMD_instant.FreshGroundCoffeeFinished, 1));
    }

    public void setGetCupType() {
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant_main(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_SetCupFallingWay, 0));
    }

    public void setWaterAndMetail() {
        int i;
        int i2;
        List<BaseMaterial> list = Constants.getnowProductMaterialMessage();
        Collections.sort(list);
        BaseMaterial baseMaterial = null;
        BaseMaterial baseMaterial2 = null;
        BaseMaterial baseMaterial3 = null;
        BaseMaterial baseMaterial4 = null;
        BaseMaterial baseMaterial5 = null;
        BaseMaterial baseMaterial6 = null;
        BaseMaterial baseMaterial7 = null;
        BaseMaterial baseMaterial8 = null;
        BaseMaterial baseMaterial9 = null;
        BaseMaterial baseMaterial10 = null;
        BaseMaterial baseMaterial11 = null;
        BaseMaterial baseMaterial12 = null;
        BaseMaterial baseMaterial13 = null;
        BaseMaterial baseMaterial14 = null;
        BaseMaterial baseMaterial15 = null;
        BaseMaterial baseMaterial16 = null;
        int i3 = 0;
        int i4 = 9;
        while (i3 < list.size()) {
            BaseMaterial baseMaterial17 = list.get(i3);
            StringBuilder sb = new StringBuilder();
            List<BaseMaterial> list2 = list;
            sb.append("商品原材料记录baseMaterial：");
            sb.append(baseMaterial17.toString());
            Tools.upLocalLog(sb.toString());
            MaterialMessage materialMessage = baseMaterial17.getMaterialMessage();
            int waterType = baseMaterial17.getFormulaListBean().getWaterType();
            BaseMaterial baseMaterial18 = baseMaterial8;
            BaseMaterial baseMaterial19 = baseMaterial6;
            if (!baseMaterial17.getMaterialMessage().getPosition().equalsIgnoreCase("8") && (waterType == 1 || waterType == 2)) {
                i4 = waterType;
            }
            String rawMaterialType = baseMaterial17.getFormulaListBean().getRawMaterialType();
            if (!rawMaterialType.contains(Constants.ground1) && !rawMaterialType.contains(Constants.ground2) && !rawMaterialType.contains(Constants.ground3) && !rawMaterialType.contains(Constants.ground4) && !rawMaterialType.contains(Constants.ground5) && !rawMaterialType.contains(Constants.ground6) && !rawMaterialType.contains(Constants.ground7) && !rawMaterialType.contains(Constants.ground8)) {
                if (rawMaterialType.contains(Constants.ice1) || rawMaterialType.contains(Constants.ice2) || rawMaterialType.contains(Constants.ice3) || rawMaterialType.contains(Constants.ice4) || rawMaterialType.toLowerCase().contains("ice")) {
                    i = 1;
                    i2 = 8;
                } else {
                    i2 = Integer.parseInt(materialMessage.getPosition());
                    i = 1;
                }
                if (i2 == i) {
                    if (baseMaterial == null) {
                        baseMaterial = baseMaterial17;
                    } else {
                        baseMaterial2 = baseMaterial17;
                    }
                } else if (i2 == 2) {
                    if (baseMaterial3 == null) {
                        baseMaterial3 = baseMaterial17;
                    } else {
                        baseMaterial4 = baseMaterial17;
                    }
                } else if (i2 == 3) {
                    if (baseMaterial5 == null) {
                        baseMaterial5 = baseMaterial17;
                    } else {
                        baseMaterial6 = baseMaterial17;
                        baseMaterial8 = baseMaterial18;
                        i3++;
                        list = list2;
                    }
                } else if (i2 == 4) {
                    if (baseMaterial7 == null) {
                        baseMaterial7 = baseMaterial17;
                    } else {
                        baseMaterial8 = baseMaterial17;
                        baseMaterial6 = baseMaterial19;
                        i3++;
                        list = list2;
                    }
                } else if (i2 == 5) {
                    if (baseMaterial9 == null) {
                        baseMaterial9 = baseMaterial17;
                    } else {
                        baseMaterial12 = baseMaterial17;
                    }
                } else if (i2 == 6) {
                    if (baseMaterial10 == null) {
                        baseMaterial10 = baseMaterial17;
                    } else {
                        baseMaterial14 = baseMaterial17;
                    }
                } else if (i2 == 7) {
                    if (baseMaterial11 == null) {
                        baseMaterial11 = baseMaterial17;
                    } else {
                        baseMaterial15 = baseMaterial17;
                    }
                } else if (i2 == 8) {
                    if (baseMaterial13 == null) {
                        baseMaterial13 = baseMaterial17;
                    } else {
                        baseMaterial16 = baseMaterial17;
                    }
                }
            }
            baseMaterial6 = baseMaterial19;
            baseMaterial8 = baseMaterial18;
            i3++;
            list = list2;
        }
        BaseMaterial baseMaterial20 = baseMaterial6;
        BaseMaterial baseMaterial21 = baseMaterial8;
        int AMD_instant = Constants.AMD_instant();
        this.anInt = i4 == 1 ? 1 : 17;
        byte[] bArr = new byte[0];
        int materTime = baseMaterial == null ? 0 : (baseMaterial2 == null || baseMaterial2.getMaterTime() <= 0) ? baseMaterial.getMaterTime() : baseMaterial2.getMaterTime();
        int waterTime = baseMaterial == null ? 0 : (baseMaterial2 == null || baseMaterial2.getWaterTime() <= 0) ? baseMaterial.getWaterTime() : baseMaterial2.getWaterTime();
        int materTime2 = baseMaterial3 == null ? 0 : (baseMaterial4 == null || baseMaterial4.getMaterTime() <= 0) ? baseMaterial3.getMaterTime() : baseMaterial4.getMaterTime();
        int waterTime2 = baseMaterial3 == null ? 0 : (baseMaterial4 == null || baseMaterial4.getWaterTime() <= 0) ? baseMaterial3.getWaterTime() : baseMaterial4.getWaterTime();
        int materTime3 = baseMaterial5 == null ? 0 : (baseMaterial20 == null || baseMaterial20.getMaterTime() <= 0) ? baseMaterial5.getMaterTime() : baseMaterial20.getMaterTime();
        int waterTime3 = baseMaterial5 == null ? 0 : (baseMaterial20 == null || baseMaterial20.getWaterTime() <= 0) ? baseMaterial5.getWaterTime() : baseMaterial20.getWaterTime();
        int materTime4 = baseMaterial7 == null ? 0 : (baseMaterial21 == null || baseMaterial21.getMaterTime() <= 0) ? baseMaterial7.getMaterTime() : baseMaterial21.getMaterTime();
        int waterTime4 = baseMaterial7 == null ? 0 : (baseMaterial21 == null || baseMaterial21.getWaterTime() <= 0) ? baseMaterial7.getWaterTime() : baseMaterial21.getWaterTime();
        int materTime5 = baseMaterial9 == null ? 0 : (baseMaterial12 == null || baseMaterial12.getMaterTime() <= 0) ? baseMaterial9.getMaterTime() : baseMaterial12.getMaterTime();
        int waterTime5 = baseMaterial9 == null ? 0 : (baseMaterial12 == null || baseMaterial12.getWaterTime() <= 0) ? baseMaterial9.getWaterTime() : baseMaterial12.getWaterTime();
        int materTime6 = baseMaterial10 == null ? 0 : (baseMaterial14 == null || baseMaterial14.getMaterTime() <= 0) ? baseMaterial10.getMaterTime() : baseMaterial14.getMaterTime();
        int waterTime6 = baseMaterial10 == null ? 0 : (baseMaterial14 == null || baseMaterial14.getWaterTime() <= 0) ? baseMaterial10.getWaterTime() : baseMaterial14.getWaterTime();
        int materTime7 = baseMaterial11 == null ? 0 : (baseMaterial15 == null || baseMaterial15.getMaterTime() <= 0) ? baseMaterial11.getMaterTime() : baseMaterial15.getMaterTime();
        int waterTime7 = baseMaterial11 == null ? 0 : (baseMaterial15 == null || baseMaterial15.getWaterTime() <= 0) ? baseMaterial11.getWaterTime() : baseMaterial15.getWaterTime();
        int materTime8 = baseMaterial13 == null ? 0 : (baseMaterial16 == null || baseMaterial16.getMaterTime() <= 0) ? baseMaterial13.getMaterTime() : baseMaterial16.getMaterTime();
        int waterTime8 = baseMaterial13 == null ? 0 : (baseMaterial16 == null || baseMaterial16.getWaterTime() <= 0) ? baseMaterial13.getWaterTime() : baseMaterial16.getWaterTime();
        if (AMD_instant == 2) {
            bArr = Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_SetTimeMaterialAndWater, this.anInt, materTime, waterTime, materTime2, waterTime2, materTime3, waterTime3, materTime4, waterTime4, materTime5, waterTime5, materTime6, waterTime6, materTime7, waterTime7);
        } else if (AMD_instant == 3) {
            bArr = Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_SetTimeMaterialAndWater, this.anInt, materTime, waterTime, materTime2, waterTime2, materTime3, waterTime3, materTime4, waterTime4, materTime5, waterTime5, materTime6, waterTime6, materTime7, waterTime7, materTime8, waterTime8);
        }
        MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant_main(bArr);
    }

    public void standby() {
        MyAppLocation.myAppLocation.mSerialDataService.standby();
    }

    public void startCurrent() {
        this.startcurrent = true;
        this.currentTimeMillis = System.currentTimeMillis();
        Constants.needCurrentgrinding_times--;
        for (int i = 0; i < 3; i++) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Currentgrinding_main(Constants.getOrderBytes_Currentgrinding((byte) 22, (byte) 90, (byte) Constants.needCurrent_data.getGrindQty(), (byte) getGrindWaterQty(), (byte) getQtyMic(), (byte) Constants.needCurrent_data.getGrindWaterQtyMic(), (byte) -86));
        }
    }
}
