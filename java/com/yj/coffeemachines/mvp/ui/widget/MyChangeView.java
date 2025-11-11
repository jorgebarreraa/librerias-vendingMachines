package com.yj.coffeemachines.mvp.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.greendao.beans.MaterialMessage;
import com.yj.coffeemachines.mvp.model.beans.ProductBean;

/* loaded from: classes.dex */
public class MyChangeView extends LinearLayout {
    private ProductBean.ProductDetailBean.FormulaListBean formulaListBean;
    private boolean isCoffee;
    private boolean isTang;
    private int lenght;
    String locaName;
    private Context mContext;
    private MaterialMessage materialMessage;
    String name;
    private SeekBar progressBar;
    private TextView tv_name;
    private TextView tv_num1;

    public MyChangeView(Context context, ProductBean.ProductDetailBean.FormulaListBean formulaListBean) {
        super(context);
        this.isTang = false;
        this.lenght = 50;
        this.isCoffee = false;
        this.locaName = "";
        this.name = "";
        this.mContext = context;
        this.formulaListBean = formulaListBean;
        initlayout(context);
    }

    public MyChangeView(Context context, ProductBean.ProductDetailBean.FormulaListBean formulaListBean, boolean z, boolean z2) {
        super(context);
        this.isTang = false;
        this.lenght = 50;
        this.isCoffee = false;
        this.locaName = "";
        this.name = "";
        this.mContext = context;
        this.formulaListBean = formulaListBean;
        this.isTang = z;
        this.isCoffee = z2;
        if (z2) {
            Constants.needCurrentmicDate = 0;
        }
        initlayout(context);
    }

    private void initlayout(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.change_layout, (ViewGroup) this, true);
        this.tv_name = (TextView) inflate.findViewById(R.id.tv_name);
        this.tv_num1 = (TextView) inflate.findViewById(R.id.tv_num1);
        this.tv_name.setSelected(true);
        this.tv_num1.setSelected(true);
        this.tv_num1.setText(getContext().getString(R.string.moderation));
        this.progressBar = (SeekBar) inflate.findViewById(R.id.numberProgressBaru);
        this.progressBar.setMax(100);
        this.progressBar.setProgress(50);
        this.lenght = 50;
        this.progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.widget.MyChangeView.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (i > 60) {
                    MyChangeView.this.tv_num1.setText(MyChangeView.this.getContext().getString(R.string.more));
                    return;
                }
                if (i >= 40 && i <= 60) {
                    MyChangeView.this.tv_num1.setText(MyChangeView.this.getContext().getString(R.string.moderation));
                    return;
                }
                if (i != 0) {
                    MyChangeView.this.tv_num1.setText(MyChangeView.this.getContext().getString(R.string.little));
                } else if (MyChangeView.this.isTang) {
                    MyChangeView.this.tv_num1.setText(MyChangeView.this.getContext().getString(R.string.none));
                } else {
                    MyChangeView.this.tv_num1.setText(MyChangeView.this.getContext().getString(R.string.little));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (MyChangeView.this.isCoffee) {
                    Constants.needCurrentmicDate = seekBar.getProgress();
                }
            }
        });
    }

    public ProductBean.ProductDetailBean.FormulaListBean getFormulaListBean() {
        return this.formulaListBean;
    }

    public MaterialMessage getMaterialMessage() {
        this.materialMessage.setMicRate(this.progressBar.getProgress());
        return this.materialMessage;
    }

    public int getProgress() {
        return this.progressBar.getProgress() - this.lenght;
    }

    public void setFormulaMessage(ProductBean.ProductDetailBean.FormulaListBean formulaListBean) {
        this.formulaListBean = formulaListBean;
    }

    public void setMaterialMessage(MaterialMessage materialMessage) {
        this.materialMessage = materialMessage;
        this.locaName = materialMessage.getLocaName();
        this.name = materialMessage.getName();
        this.tv_name.setText(this.locaName.isEmpty() ? this.name : this.locaName);
    }

    public void setName(String str) {
        this.tv_name.setText(str);
    }

    public void setProgress(int i) {
    }
}
