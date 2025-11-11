package com.yj.coffeemachines.mvp.model.beans;

import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;

/* loaded from: classes.dex */
public class BaseMaterial implements Comparable<BaseMaterial> {
    private ProductBean.ProductDetailBean.FormulaListBean formulaListBean;
    private MaterialMessage materialMessage;
    private double micRate;

    public BaseMaterial(MaterialMessage materialMessage, ProductBean.ProductDetailBean.FormulaListBean formulaListBean, int i) {
        this.micRate = 0.0d;
        this.materialMessage = materialMessage;
        this.formulaListBean = formulaListBean;
        this.micRate = i;
    }

    private double calculateRate() {
        return this.micRate / 2.5d;
    }

    private int coffeeRecalculate(double d, double d2, double d3) {
        double d4 = (d2 + 5.0d + (d * 0.5d)) * 1.0d;
        int i = (int) ((((d4 + (d3 * d4)) - d2) - 5.0d) * 2.0d);
        if (i <= -20) {
            i = -20;
        }
        if (i >= 20) {
            return 20;
        }
        return i;
    }

    public double coffeeRecalculate() {
        double localMaterialFineTuning = this.materialMessage.getLocalMaterialFineTuning();
        int qty = (int) (this.formulaListBean.getQty() + localMaterialFineTuning + localMaterialFineTuning);
        if (qty <= -20) {
            qty = -20;
        }
        if (qty >= 20) {
            qty = 20;
        }
        double d = ((qty * 0.5d) + 9.0d) * 1.0d;
        if (d <= 2.0d) {
            d = 2.0d;
        }
        return d * 2.0d;
    }

    public int coffeeRecalculate(double d, double d2) {
        int i = (int) (d + d2);
        if (i <= -20) {
            i = -20;
        }
        if (i >= 20) {
            return 20;
        }
        return i;
    }

    public double coffeeRecalculate1() {
        double grindQtyMic = this.formulaListBean.getGrindQtyMic();
        double grindQty = this.formulaListBean.getGrindQty();
        int localMaterialFineTuning = (int) (grindQtyMic + this.materialMessage.getLocalMaterialFineTuning());
        if (localMaterialFineTuning <= -20) {
            localMaterialFineTuning = -20;
        }
        if (localMaterialFineTuning >= 20) {
            localMaterialFineTuning = 20;
        }
        double d = grindQty + 5.0d + (localMaterialFineTuning * 0.5d);
        if (d <= 2.0d) {
            d = 2.0d;
        }
        return d * 2.0d;
    }

    @Override // java.lang.Comparable
    public int compareTo(BaseMaterial baseMaterial) {
        return Integer.parseInt(this.materialMessage.getPosition()) - Integer.parseInt(baseMaterial.getMaterialMessage().getPosition());
    }

    public ProductBean.ProductDetailBean.FormulaListBean getFormulaListBean() {
        return this.formulaListBean;
    }

    public int getKqty() {
        Double valueOf = Double.valueOf(this.formulaListBean.getKqty());
        String rawMaterialType = this.formulaListBean.getRawMaterialType();
        if (rawMaterialType.contains(Constants.ice1) || rawMaterialType.contains(Constants.ice2) || rawMaterialType.contains(Constants.ice3) || rawMaterialType.contains(Constants.ice4)) {
            valueOf = Double.valueOf(this.formulaListBean.getKqty());
        } else if (rawMaterialType.contains(Constants.instant1) || rawMaterialType.contains(Constants.instant2) || rawMaterialType.contains(Constants.instant3) || rawMaterialType.contains(Constants.instant4)) {
            valueOf = Double.valueOf(this.formulaListBean.getQty());
        }
        double doubleValue = valueOf.doubleValue() + (valueOf.doubleValue() * (calculateRate() / 100.0d));
        int waterTime = getWaterTime();
        return (((double) waterTime) >= doubleValue || !Constants.devConfig().isRight6()) ? (int) doubleValue : waterTime;
    }

    public int getMaterTime() {
        double localMaterialFineTuning = this.materialMessage.getLocalMaterialFineTuning();
        Double valueOf = Double.valueOf(this.formulaListBean.getQty());
        Double valueOf2 = Double.valueOf(valueOf.doubleValue() + ((localMaterialFineTuning / 100.0d) * valueOf.doubleValue()));
        if (Constants.deviceTypeDetail.getName().contains("JK88") && this.materialMessage.getPosition().contains("8")) {
            return coffeeRecalculate(this.formulaListBean.getQty(), localMaterialFineTuning);
        }
        if (this.materialMessage.getLocalIsSuger()) {
            this.micRate += 50.0d;
            return toInt((int) (valueOf2.doubleValue() * (this.micRate / 100.0d)));
        }
        if (this.formulaListBean.getRawMaterialType().contains(Constants.ice1) || this.formulaListBean.getRawMaterialType().toLowerCase().contains(Constants.ice2)) {
            double kqty = this.formulaListBean.getKqty();
            return toInt((int) (kqty + ((calculateRate() / 100.0d) * kqty)));
        }
        double doubleValue = valueOf2.doubleValue() + (valueOf2.doubleValue() * (calculateRate() / 100.0d));
        int waterTime = getWaterTime();
        return (((double) waterTime) >= doubleValue || !Constants.devConfig().isRight6()) ? toInt((int) doubleValue) : toInt(waterTime);
    }

    public MaterialMessage getMaterialMessage() {
        return this.materialMessage;
    }

    public double getMicRate() {
        return this.micRate;
    }

    public int getWaterTime() {
        if (this.formulaListBean.getWaterType() == 1) {
            int localFineTuning_hot = this.materialMessage.getLocalFineTuning_hot();
            if (this.formulaListBean.getWaterQty() <= 0.0d) {
                return 0;
            }
            double d = localFineTuning_hot / 100.0d;
            Double valueOf = Double.valueOf(this.formulaListBean.getWaterQty());
            Double valueOf2 = Double.valueOf(valueOf.doubleValue() + (valueOf.doubleValue() * d));
            if (!Constants.deviceTypeDetail.getName().contains("JK88") || !this.materialMessage.getPosition().contains("8")) {
                return toInt((int) (valueOf.doubleValue() + (d * valueOf.doubleValue())));
            }
            return toInt((int) (valueOf2.doubleValue() + (valueOf2.doubleValue() * (calculateRate() / 100.0d))));
        }
        int localFineTuning_cold = this.materialMessage.getLocalFineTuning_cold();
        if (this.formulaListBean.getWaterQty() <= 0.0d) {
            return 0;
        }
        double d2 = localFineTuning_cold / 100.0d;
        Double valueOf3 = Double.valueOf(this.formulaListBean.getWaterQty());
        Double valueOf4 = Double.valueOf(valueOf3.doubleValue() + (valueOf3.doubleValue() * d2));
        if (!Constants.deviceTypeDetail.getName().contains("JK88") || !this.materialMessage.getPosition().contains("8")) {
            return toInt((int) (valueOf3.doubleValue() + (d2 * valueOf3.doubleValue())));
        }
        return toInt((int) (valueOf4.doubleValue() + (valueOf4.doubleValue() * (calculateRate() / 100.0d))));
    }

    public void setFormulaListBean(ProductBean.ProductDetailBean.FormulaListBean formulaListBean) {
        this.formulaListBean = formulaListBean;
    }

    public void setMaterialMessage(MaterialMessage materialMessage) {
        this.materialMessage = materialMessage;
    }

    public void setMicRate(double d) {
        this.micRate = d;
    }

    int toInt(int i) {
        if (i <= 0) {
            return 0;
        }
        return i;
    }

    public String toString() {
        return "BaseMaterial{materialMessage=" + this.materialMessage.toString() + ", formulaListBean=" + this.formulaListBean.toString() + ", micRate=" + this.micRate + '}';
    }
}
