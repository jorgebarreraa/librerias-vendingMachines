package com.yj.coffeemachines.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.EventBusManager;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.app.utils.KvUtil;
import com.yj.coffeemachines.di.component.DaggerDIYComponent;
import com.yj.coffeemachines.eventbusbean.RefreshDiyMessage;
import com.yj.coffeemachines.mvp.contract.DIYContract;
import com.yj.coffeemachines.mvp.presenter.DIYPresenter;

/* loaded from: classes.dex */
public class DIYFragment extends BaseFragment<DIYPresenter> implements DIYContract.View {

    @BindView(R.id.swith_clearCurInterval)
    Switch aSwitch;

    @BindView(R.id.et_content)
    EditText etContent;

    @BindView(R.id.et_clearCurInterval)
    EditText etText;

    @BindView(R.id.group_location)
    RadioGroup groupLocation;

    @BindView(R.id.column)
    TextView mColumn;

    @BindView(R.id.et_column)
    EditText mEtColumn;

    @BindView(R.id.et_row)
    EditText mEtRow;

    @BindView(R.id.group)
    RadioGroup mGroup;

    @BindView(R.id.lay01)
    LinearLayout mLay01;

    @BindView(R.id.left_right)
    RadioButton mLeftRight;

    @BindView(R.id.row)
    TextView mRow;

    @BindView(R.id.titlelayout)
    RelativeLayout mTitlelayout;

    @BindView(R.id.tv_factroy1)
    TextView mTvFactroy1;

    @BindView(R.id.tv_factroy1_en)
    TextView mTvFactroy1En;

    @BindView(R.id.up_down)
    RadioButton mUpDown;

    @BindView(R.id.rb_center)
    RadioButton rbCenter;

    @BindView(R.id.rb_left)
    RadioButton rbLeft;

    public static DIYFragment newInstance() {
        return new DIYFragment();
    }

    @Override // com.yj.coffeemachines.mvp.contract.DIYContract.View
    @Nullable
    public /* bridge */ /* synthetic */ Context getActivity() {
        return super.getActivity();
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void hideLoading() {
        IView.CC.$default$hideLoading(this);
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void initData(@Nullable Bundle bundle) {
        this.mGroup.check(Constants.homeGlideDirection() == -1 ? R.id.left_right : R.id.up_down);
        this.mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.DIYFragment.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.left_right) {
                    Constants.sethomeGlideDirection(-1);
                    MyAppLocation.myAppLocation.myMqttService.addOpsLog(DIYFragment.this.getString(R.string.glidedirection) + "  " + DIYFragment.this.getString(R.string.landr), DataUtils.currentTime(), 3);
                    return;
                }
                if (i == R.id.up_down) {
                    Constants.sethomeGlideDirection(1);
                    MyAppLocation.myAppLocation.myMqttService.addOpsLog(DIYFragment.this.getString(R.string.glidedirection) + "  " + DIYFragment.this.getString(R.string.upanddown), DataUtils.currentTime(), 3);
                }
            }
        });
        this.groupLocation.check(Constants.getHomeLocation() == 0 ? R.id.rb_center : R.id.rb_left);
        this.groupLocation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.DIYFragment.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_center) {
                    Constants.setHomeLocation(0);
                } else if (i == R.id.rb_left) {
                    Constants.setHomeLocation(1);
                }
            }
        });
        this.mEtRow.setText(Constants.getRow() + "");
        this.mEtColumn.setText(Constants.getColumn() + "");
        this.mEtRow.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.DIYFragment.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                if (obj.isEmpty()) {
                    return;
                }
                int parseInt = Integer.parseInt(obj);
                if (DeviceUtils.isLandscape(DIYFragment.this.getActivity())) {
                    if (parseInt > 2) {
                        ArmsUtils.snackbarText(DIYFragment.this.getString(R.string.most2row));
                        DIYFragment.this.mEtRow.setText("2");
                    }
                } else if (parseInt > 4) {
                    ArmsUtils.snackbarText(DIYFragment.this.getString(R.string.most4row));
                    DIYFragment.this.mEtRow.setText("4");
                }
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(DIYFragment.this.getString(R.string.row) + "  " + obj, DataUtils.currentTime(), 3);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mEtColumn.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.DIYFragment.4
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                if (obj.isEmpty()) {
                    return;
                }
                int parseInt = Integer.parseInt(obj);
                if (DeviceUtils.isLandscape(DIYFragment.this.getActivity())) {
                    if (parseInt > 5) {
                        ArmsUtils.snackbarText(DIYFragment.this.getString(R.string.most4collumn));
                        DIYFragment.this.mEtColumn.setText("5");
                    }
                } else if (parseInt > 2) {
                    ArmsUtils.snackbarText(DIYFragment.this.getString(R.string.most2collumn));
                    DIYFragment.this.mEtColumn.setText("2");
                }
                MyAppLocation.myAppLocation.myMqttService.addOpsLog(DIYFragment.this.getString(R.string.column) + "  " + obj, DataUtils.currentTime(), 3);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        int i = KvUtil.getInstance().getInt("aSwitch", 0);
        this.etText.setText(KvUtil.getInstance().getString("aiText", "Vorbește cu mine"));
        this.etContent.setText(KvUtil.getInstance().getString("etContent", "https://demo.chatislav.ai"));
        if (i == 1) {
            this.aSwitch.setChecked(true);
        }
        this.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yj.coffeemachines.mvp.ui.fragment.DIYFragment.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                KvUtil.getInstance().putInt("aSwitch", z ? 1 : 0);
            }
        });
        this.etText.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.DIYFragment.6
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                KvUtil.getInstance().putString("aiText", editable.toString());
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        });
        this.etContent.addTextChangedListener(new TextWatcher() { // from class: com.yj.coffeemachines.mvp.ui.fragment.DIYFragment.7
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                KvUtil.getInstance().putString("etContent", editable.toString());
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        });
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_diy, viewGroup, false);
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void killMyself() {
        IView.CC.$default$killMyself(this);
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        String obj = this.mEtRow.getText().toString();
        String obj2 = this.mEtColumn.getText().toString();
        Constants.setRow(Integer.parseInt(obj));
        Constants.setColumn(Integer.parseInt(obj2));
        EventBusManager.getInstance().post(new RefreshDiyMessage());
        super.onPause();
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setData(@Nullable Object obj) {
    }

    @Override // com.jess.arms.base.delegate.IFragment
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerDIYComponent.builder().appComponent(appComponent).view(this).build().inject(this);
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void showLoading() {
        IView.CC.$default$showLoading(this);
    }

    @Override // com.jess.arms.mvp.IView
    public void showMessage(@NonNull String str) {
        Preconditions.checkNotNull(str);
        ArmsUtils.snackbarText(str);
    }
}
