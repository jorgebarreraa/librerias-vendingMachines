package com.yj.coffeemachines.mvp.ui.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yj.coffeemachines.R;

/* loaded from: classes.dex */
public class DIYFragment_ViewBinding implements Unbinder {
    private DIYFragment target;

    @UiThread
    public DIYFragment_ViewBinding(DIYFragment dIYFragment, View view) {
        this.target = dIYFragment;
        dIYFragment.mTvFactroy1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy1, "field 'mTvFactroy1'", TextView.class);
        dIYFragment.mTvFactroy1En = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_factroy1_en, "field 'mTvFactroy1En'", TextView.class);
        dIYFragment.mLay01 = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay01, "field 'mLay01'", LinearLayout.class);
        dIYFragment.mUpDown = (RadioButton) Utils.findRequiredViewAsType(view, R.id.up_down, "field 'mUpDown'", RadioButton.class);
        dIYFragment.mLeftRight = (RadioButton) Utils.findRequiredViewAsType(view, R.id.left_right, "field 'mLeftRight'", RadioButton.class);
        dIYFragment.mGroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.group, "field 'mGroup'", RadioGroup.class);
        dIYFragment.rbCenter = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_center, "field 'rbCenter'", RadioButton.class);
        dIYFragment.rbLeft = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_left, "field 'rbLeft'", RadioButton.class);
        dIYFragment.groupLocation = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.group_location, "field 'groupLocation'", RadioGroup.class);
        dIYFragment.mTitlelayout = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.titlelayout, "field 'mTitlelayout'", RelativeLayout.class);
        dIYFragment.mRow = (TextView) Utils.findRequiredViewAsType(view, R.id.row, "field 'mRow'", TextView.class);
        dIYFragment.mEtRow = (EditText) Utils.findRequiredViewAsType(view, R.id.et_row, "field 'mEtRow'", EditText.class);
        dIYFragment.mColumn = (TextView) Utils.findRequiredViewAsType(view, R.id.column, "field 'mColumn'", TextView.class);
        dIYFragment.mEtColumn = (EditText) Utils.findRequiredViewAsType(view, R.id.et_column, "field 'mEtColumn'", EditText.class);
        dIYFragment.etText = (EditText) Utils.findRequiredViewAsType(view, R.id.et_clearCurInterval, "field 'etText'", EditText.class);
        dIYFragment.etContent = (EditText) Utils.findRequiredViewAsType(view, R.id.et_content, "field 'etContent'", EditText.class);
        dIYFragment.aSwitch = (Switch) Utils.findRequiredViewAsType(view, R.id.swith_clearCurInterval, "field 'aSwitch'", Switch.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        DIYFragment dIYFragment = this.target;
        if (dIYFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dIYFragment.mTvFactroy1 = null;
        dIYFragment.mTvFactroy1En = null;
        dIYFragment.mLay01 = null;
        dIYFragment.mUpDown = null;
        dIYFragment.mLeftRight = null;
        dIYFragment.mGroup = null;
        dIYFragment.rbCenter = null;
        dIYFragment.rbLeft = null;
        dIYFragment.groupLocation = null;
        dIYFragment.mTitlelayout = null;
        dIYFragment.mRow = null;
        dIYFragment.mEtRow = null;
        dIYFragment.mColumn = null;
        dIYFragment.mEtColumn = null;
        dIYFragment.etText = null;
        dIYFragment.etContent = null;
        dIYFragment.aSwitch = null;
    }
}
