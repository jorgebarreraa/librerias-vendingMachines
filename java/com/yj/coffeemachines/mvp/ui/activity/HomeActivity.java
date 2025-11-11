package com.yj.coffeemachines.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkRequest;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.badge.BadgeDrawable;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.DevConfig;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.ByteUtils;
import com.yj.coffeemachines.app.utils.CRC8Util;
import com.yj.coffeemachines.app.utils.DataUtils;
import com.yj.coffeemachines.app.utils.DiaLogUtils;
import com.yj.coffeemachines.app.utils.ErrorUtils;
import com.yj.coffeemachines.app.utils.KvUtil;
import com.yj.coffeemachines.app.utils.MD5Utils;
import com.yj.coffeemachines.bean.ReturnAmt;
import com.yj.coffeemachines.constants.PayType;
import com.yj.coffeemachines.di.component.DaggerHomeComponent;
import com.yj.coffeemachines.dialog.IndexDialog;
import com.yj.coffeemachines.dialog.JoinVipDialog;
import com.yj.coffeemachines.eventbusbean.DialogMessageNew;
import com.yj.coffeemachines.eventbusbean.ErrorCodeNew_Msg;
import com.yj.coffeemachines.eventbusbean.GoodsRefresh;
import com.yj.coffeemachines.eventbusbean.LogoMessage;
import com.yj.coffeemachines.eventbusbean.MyEventBusMessage;
import com.yj.coffeemachines.eventbusbean.NetWorkState;
import com.yj.coffeemachines.eventbusbean.ProductRefish;
import com.yj.coffeemachines.eventbusbean.QRCodeMessage;
import com.yj.coffeemachines.eventbusbean.ResetTime;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.HomeContract;
import com.yj.coffeemachines.mvp.model.beans.DeviceInfoBean;
import com.yj.coffeemachines.mvp.model.beans.LoginBack;
import com.yj.coffeemachines.mvp.presenter.HomePresenter;
import com.yj.coffeemachines.mvp.ui.fragment.EventFragment;
import com.yj.coffeemachines.mvp.ui.fragment.Step1Fragment;
import com.yj.coffeemachines.mvp.ui.fragment.Step2Fragment;
import com.yj.coffeemachines.mvp.ui.fragment.Step3Fragment;
import com.yj.coffeemachines.mvp.ui.fragment.Step4Fragment;
import com.yj.coffeemachines.mvp.ui.fragment.Step5Fragment;
import com.yj.coffeemachines.pay.mdb.MdbSendMethod;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import me.jessyan.autosize.AutoSizeCompat;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {
    private static final int LOCATION_PERMISSION_CODE = 123;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;
    public static boolean languageChange = false;

    @BindView(R.id.cardview_location)
    CardView cardviewLocation;
    private IndexDialog indexDialog;

    @BindView(R.id.iv_logo)
    ImageView ivLogo;

    @BindView(R.id.btn_ai)
    Button mBtnAi;

    @BindView(R.id.iv_sn)
    ImageView mIvSn;

    @BindView(R.id.iv_step1)
    ImageView mIvStep1;

    @BindView(R.id.iv_step2)
    ImageView mIvStep2;

    @BindView(R.id.iv_step3)
    ImageView mIvStep3;

    @BindView(R.id.iv_step4)
    ImageView mIvStep4;

    @BindView(R.id.iv_step5)
    ImageView mIvStep5;

    @Inject
    AlertDialog mSportDialog;

    @BindView(R.id.tv_phone)
    TextView mTvPhone;

    @BindView(R.id.tv_phone1)
    TextView mTvPhone1;

    @BindView(R.id.tv_sn)
    TextView mTvSn;

    @BindView(R.id.tv_sn1)
    TextView mTvSn1;

    @BindView(R.id.home_tv_step1)
    TextView mhome_tv_step1;

    @BindView(R.id.home_tv_step2)
    TextView mhome_tv_step2;

    @BindView(R.id.home_tv_step3)
    TextView mhome_tv_step3;

    @BindView(R.id.home_tv_step4)
    TextView mhome_tv_step4;

    @BindView(R.id.home_tv_step5)
    TextView mhome_tv_step5;
    ScheduledFuture<?> scheduleNew;
    private ScheduledExecutorService scheduledExecutorService;
    private SparseArray<String> mSparseTags = new SparseArray<>();
    int checkTime = 0;
    int checkTimeEnd = 2;
    int restartTime = 30;
    int netCheckTime = 120;
    boolean init = true;
    boolean ischeckReturn = false;
    Runnable commandNew = new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.activity.-$$Lambda$HomeActivity$1VEmFafxf6cJ3XCB6eH6KFEe1Bg
        @Override // java.lang.Runnable
        public final void run() {
            HomeActivity.this.lambda$new$2$HomeActivity();
        }
    };
    List<String> permissionDeniedList = new ArrayList();
    List<KeyEvent> scannedCodes = new ArrayList();
    long[] mHits5 = new long[5];
    long[] mHits2 = new long[2];

    private void checkLocationPermission() {
        this.permissionDeniedList.add("android.permission.WAKE_LOCK");
        this.permissionDeniedList.add("android.permission.ACCESS_NETWORK_STATE");
        this.permissionDeniedList.add("android.permission.SYSTEM_ALERT_WINDOW");
        this.permissionDeniedList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        this.permissionDeniedList.add("android.permission.READ_EXTERNAL_STORAGE");
        this.permissionDeniedList.add("android.permission.ACCESS_NOTIFICATION_POLICY");
        this.permissionDeniedList.add("android.permission.RECEIVE_BOOT_COMPLETED");
        this.permissionDeniedList.add("android.permission.ACCESS_COARSE_LOCATION");
        this.permissionDeniedList.add("android.permission.ACCESS_FINE_LOCATION");
        this.permissionDeniedList.add("android.permission.ACCESS_WIFI_STATE");
        this.permissionDeniedList.add("android.permission.CHANGE_WIFI_STATE");
        this.permissionDeniedList.add("android.permission.REORDER_TASKS");
        this.permissionDeniedList.add("android.permission.VIBRATE");
        this.permissionDeniedList.add("android.permission.READ_PHONE_STATE");
        this.permissionDeniedList.add("android.permission.INTERNET");
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            startActivityForResult(intent, 123);
        }
        if (this.permissionDeniedList.isEmpty()) {
            return;
        }
        List<String> list = this.permissionDeniedList;
        ActivityCompat.requestPermissions(this, (String[]) list.toArray(new String[list.size()]), 2);
    }

    private void checkNet() {
        NetworkUtils.isAvailableAsync(new Utils.Consumer() { // from class: com.yj.coffeemachines.mvp.ui.activity.-$$Lambda$HomeActivity$bf384wkvya8yXG7mVJ5gMxHaB_0
            @Override // com.blankj.utilcode.util.Utils.Consumer
            public final void accept(Object obj) {
                HomeActivity.this.lambda$checkNet$1$HomeActivity((Boolean) obj);
            }
        });
    }

    private void checkReturnAmt() {
        if (this.ischeckReturn) {
            return;
        }
        this.ischeckReturn = true;
        if (!Constants.ReturnAmtRemove.equals("")) {
            int i = 0;
            while (i < Constants.returnAmtList.size()) {
                String[] split = Constants.ReturnAmtRemove.split("_");
                int length = split.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        if (Constants.returnAmtList.get(i).getTrade_no().equals(split[i2])) {
                            Constants.returnAmtList.remove(i);
                            i--;
                            break;
                        }
                        i2++;
                    }
                }
                i++;
            }
            Constants.ReturnAmtRemove = "";
        }
        if (!Constants.ReturnAmtAdd.equals("")) {
            for (String str : Constants.ReturnAmtAdd.split("_")) {
                if (!Tools.toString(str).equals("")) {
                    Constants.returnAmtList.add(new ReturnAmt(str, 0, System.currentTimeMillis()));
                }
            }
            Constants.ReturnAmtAdd = "";
        }
        if (Constants.returnAmtList.size() > 0) {
            for (int i3 = 0; i3 < Constants.returnAmtList.size(); i3++) {
                long return_time = Constants.returnAmtList.get(i3).getReturn_time();
                int return_num = Constants.returnAmtList.get(i3).getReturn_num();
                String trade_no = Constants.returnAmtList.get(i3).getTrade_no();
                if (return_num == 0) {
                    ((HomePresenter) this.mPresenter).unionPayReturnAmt(trade_no);
                    Constants.returnAmtList.get(i3).setReturn_num();
                } else if (return_num == 1) {
                    if (System.currentTimeMillis() - return_time >= WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS) {
                        ((HomePresenter) this.mPresenter).unionPayReturnAmt(trade_no);
                        Constants.returnAmtList.get(i3).setReturn_num();
                    }
                } else if (return_num == 2) {
                    if (System.currentTimeMillis() - return_time >= 60000) {
                        ((HomePresenter) this.mPresenter).unionPayReturnAmt(trade_no);
                        Constants.returnAmtList.get(i3).setReturn_num();
                    }
                } else if (return_num == 3) {
                    if (System.currentTimeMillis() - return_time >= 150000) {
                        ((HomePresenter) this.mPresenter).unionPayReturnAmt(trade_no);
                        Constants.returnAmtList.get(i3).setReturn_num();
                    }
                } else if (return_num == 4 && System.currentTimeMillis() - return_time >= PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
                    ((HomePresenter) this.mPresenter).unionPayReturnAmt(trade_no);
                    Constants.returnAmtList.get(i3).setReturn_num();
                    Constants.ReturnAmtRemove += trade_no + "_";
                }
                Tools.upLocalLog(Constants.returnAmtList.get(i3).toString());
            }
        }
        this.ischeckReturn = false;
    }

    private void choseChange(int i) {
        if (i == 1) {
            step1(true);
            step2(false);
            step3(false);
            step4(false);
            step5(false);
            Constants.NowStep = 1;
            Step1Fragment.action = 0;
            return;
        }
        if (i == 2) {
            step1(true);
            step2(true);
            step3(false);
            step4(false);
            step5(false);
            Constants.NowStep = 2;
            return;
        }
        if (i == 3) {
            step1(true);
            step2(true);
            step3(true);
            step4(false);
            step5(false);
            Constants.NowStep = 3;
            return;
        }
        if (i == 4) {
            step1(true);
            step2(true);
            step3(true);
            step4(true);
            step5(false);
            Constants.NowStep = 4;
            return;
        }
        if (i != 5) {
            return;
        }
        step1(true);
        step2(true);
        step3(true);
        step4(true);
        step5(true);
        Constants.NowStep = 5;
    }

    private void disposeErrorMsg() {
        IndexDialog indexDialog;
        if (Constants.e00001 || Constants.e00002) {
            return;
        }
        if (ErrorCodeNew_Msg.ErrorNew.equals(ErrorCodeNew_Msg.ErrorOld)) {
            if (Constants.isSettingInterface || (indexDialog = this.indexDialog) == null || !indexDialog.isShowing()) {
                return;
            }
            String convertCode = ErrorUtils.convertCode(ErrorCodeNew_Msg.ErrorNew);
            if (convertCode.equalsIgnoreCase(this.indexDialog.getMsg())) {
                return;
            }
            this.indexDialog.setMsg(convertCode);
            return;
        }
        String[] isError = ErrorCodeNew_Msg.isError();
        if (isError.length <= 0 || isError == null || isError.length < 8) {
            return;
        }
        ErrorCodeNew_Msg.ErrorOld = ErrorCodeNew_Msg.ErrorNew;
        ((HomePresenter) this.mPresenter).uploadState();
        if (errorShowDialog(isError)) {
            return;
        }
        EventBus.getDefault().post(new GoodsRefresh(isError));
    }

    private boolean errorShowDialog(String[] strArr) {
        if ("1".equals(strArr[0])) {
            showOrHideIndexDialog(new DialogMessageNew(ErrorCodeNew_Msg.ErrorOld, 0));
            return true;
        }
        if ("1".equals(strArr[3])) {
            showOrHideIndexDialog(new DialogMessageNew(ErrorCodeNew_Msg.ErrorOld, 0));
            return true;
        }
        showOrHideIndexDialog(new DialogMessageNew(false, 0));
        return false;
    }

    private void getLid() {
        DevConfig devConfig = Constants.devConfig();
        if (devConfig.isRight7()) {
            String startTime = devConfig.getStartTime();
            String stopTime = devConfig.getStopTime();
            if (startTime.isEmpty() || stopTime.isEmpty()) {
                return;
            }
            Long nowtimehhmmss_long = DataUtils.getNowtimehhmmss_long();
            Long stringHHMMSSDataLong = DataUtils.stringHHMMSSDataLong(startTime + ":00");
            Long stringHHMMSSDataLong2 = DataUtils.stringHHMMSSDataLong(stopTime + ":00");
            if (stringHHMMSSDataLong.longValue() > nowtimehhmmss_long.longValue() || nowtimehhmmss_long.longValue() > stringHHMMSSDataLong2.longValue()) {
                return;
            }
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Instant(Constants.getOrderBytes_instant(Constants.CMD_instant.CMD_Test, 3, 2, 1, 0));
        }
    }

    private void handleKeyCodes() {
        String str = "";
        loop0: while (true) {
            boolean z = false;
            for (KeyEvent keyEvent : this.scannedCodes) {
                if ((keyEvent.getKeyCode() == 59 || keyEvent.getKeyCode() == 60) && keyEvent.getAction() == 0) {
                    z = true;
                } else {
                    if ((keyEvent.getKeyCode() == 59 || keyEvent.getKeyCode() == 60) && keyEvent.getAction() == 1) {
                        break;
                    }
                    if (keyEvent.getAction() == 0) {
                        str = str + Constants.keyCodeToChar(keyEvent.getKeyCode(), z);
                    }
                }
            }
        }
        if (str.trim().isEmpty()) {
            return;
        }
        EventBus.getDefault().post(new QRCodeMessage(str));
        this.scannedCodes.clear();
    }

    private void initsparr() {
        this.mSparseTags.put(R.id.iv_step1, "iv_step1");
        this.mSparseTags.put(R.id.step_ll_getdrinks, "step_ll_getdrinks");
        this.mSparseTags.put(R.id.iv_step2, "iv_step2");
        this.mSparseTags.put(R.id.iv_step3, "iv_step3");
        this.mSparseTags.put(R.id.iv_step4, "iv_step4");
        this.mSparseTags.put(R.id.iv_step5, "iv_step5");
        replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step1Fragment.newInstance(), this.mSparseTags.get(R.id.iv_step1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void makeDialogLogin() {
        String devicePwd;
        if (Constants.deviceDetail != null && (devicePwd = Constants.deviceDetail.getDevicePwd()) != null && devicePwd.equals(MD5Utils.md5_32_low("000000"))) {
            loginSuccess(null);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = View.inflate(getActivity(), R.layout.opslogin_layout, null);
        final EditText editText = (EditText) inflate.findViewById(R.id.password);
        Button button = (Button) inflate.findViewById(R.id.btn_login);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.adminlogin);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.show();
        if (Constants.isLocalFactoryInterface) {
            loginSuccess(null);
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity.5
            @Override // android.view.View.OnClickListener
            @SuppressLint({"MissingPermission"})
            public void onClick(View view) {
                final String obj = editText.getText().toString();
                NetworkUtils.isAvailableAsync(new Utils.Consumer<Boolean>() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity.5.1
                    @Override // com.blankj.utilcode.util.Utils.Consumer
                    public void accept(Boolean bool) {
                        if (!bool.booleanValue()) {
                            if (obj.equals(Constants.networkErrorPassword)) {
                                HomeActivity.this.loginSuccess(null);
                            }
                        } else if (obj.equals(Constants.networkErrorPassword)) {
                            HomeActivity.this.loginSuccess(null);
                        } else {
                            ((HomePresenter) HomeActivity.this.mPresenter).login(obj);
                            Constants.addOPSMessage(HomeActivity.this.getString(R.string.login));
                        }
                    }
                });
                create.dismiss();
            }
        });
    }

    private void makeDialogPic() {
        new JoinVipDialog(this).show();
    }

    private void processRefund(int i) {
        int change = (int) (Constants.pay_num / Constants.opsSeting().getChange());
        if (change < 0) {
            return;
        }
        Tools.upLocalLog((i == 2 ? "刷卡支付退款：" : "现金支付退款：") + change);
        if (i == 2) {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment_main(Constants.drinkStatus_payment((byte) 1));
        } else {
            MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(Constants.smallChange_payment((byte) change));
        }
        Constants.pay_num = 0.0d;
    }

    private void showIndexDialog(DialogMessageNew dialogMessageNew) {
        if (this.indexDialog == null) {
            this.indexDialog = new IndexDialog(this, R.style.dialog_with_full);
        }
        ScheduledFuture<?> scheduledFuture = this.scheduleNew;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        this.indexDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.-$$Lambda$HomeActivity$eUdXuRj3WMhPUwZ4alikyoCotB4
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                HomeActivity.this.lambda$showIndexDialog$3$HomeActivity(dialogInterface);
            }
        });
        this.indexDialog.setListener(new IndexDialog.OnSecretClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity.1
            @Override // com.yj.coffeemachines.dialog.IndexDialog.OnSecretClickListener
            public void onClick() {
                HomeActivity.this.makeDialogLogin();
            }

            @Override // com.yj.coffeemachines.dialog.IndexDialog.OnSecretClickListener
            public void onReloadClick() {
                KvUtil.getInstance().putString("ProductRefish", "ProductRefish");
                EventBus.getDefault().post(new ProductRefish());
                if (Constants.deviceDetail != null) {
                    String servicePhone = Constants.deviceDetail.getServicePhone();
                    if (!Tools.toString(servicePhone).equals("")) {
                        HomeActivity.this.mTvPhone.setText(servicePhone);
                    }
                    HomeActivity.this.mTvSn.setText(Constants.deviceDetail.getDeviceExtNo());
                }
            }
        });
        this.indexDialog.setMsg(ErrorUtils.convertCode(dialogMessageNew.getMsg()));
        this.indexDialog.show();
        Tools.upLocalLog("收到弹窗消息，展示弹窗4");
    }

    private void showOrHideIndexDialog(DialogMessageNew dialogMessageNew) {
        if (Constants.isSettingInterface) {
            return;
        }
        if (TextUtils.isEmpty(dialogMessageNew.getMsg())) {
            dismissIndexDialog();
            return;
        }
        if (dialogMessageNew.getType() == 0) {
            dismissIndexDialog();
            if (dialogMessageNew.isShow()) {
                showIndexDialog(dialogMessageNew);
                return;
            }
            return;
        }
        if (dialogMessageNew.getType() == 1) {
            dismissIndexDialog();
            if (dialogMessageNew.isShow()) {
                showIndexDialog(dialogMessageNew);
                this.scheduleNew = this.scheduledExecutorService.schedule(this.commandNew, 6L, TimeUnit.SECONDS);
            }
        }
    }

    private void step1(boolean z) {
        Resources resources;
        int i;
        this.mhome_tv_step1.setTextColor(Color.parseColor(z ? "#F0B446" : "#FFFFFF"));
        ImageView imageView = this.mIvStep1;
        if (z) {
            resources = getResources();
            i = R.mipmap.step1_icn_c;
        } else {
            resources = getResources();
            i = R.mipmap.step1_icn;
        }
        imageView.setImageDrawable(resources.getDrawable(i));
    }

    private void step2(boolean z) {
        Resources resources;
        int i;
        this.mhome_tv_step2.setTextColor(Color.parseColor(z ? "#F0B446" : "#FFFFFF"));
        ImageView imageView = this.mIvStep2;
        if (z) {
            resources = getResources();
            i = R.mipmap.step2_icn_c;
        } else {
            resources = getResources();
            i = R.mipmap.step2_icn;
        }
        imageView.setImageDrawable(resources.getDrawable(i));
    }

    private void step3(boolean z) {
        Resources resources;
        int i;
        this.mhome_tv_step3.setTextColor(Color.parseColor(z ? "#F0B446" : "#FFFFFF"));
        ImageView imageView = this.mIvStep3;
        if (z) {
            resources = getResources();
            i = R.mipmap.step3_icn_c;
        } else {
            resources = getResources();
            i = R.mipmap.step3_icn;
        }
        imageView.setImageDrawable(resources.getDrawable(i));
    }

    private void step4(boolean z) {
        Resources resources;
        int i;
        this.mhome_tv_step4.setTextColor(Color.parseColor(z ? "#F0B446" : "#FFFFFF"));
        ImageView imageView = this.mIvStep4;
        if (z) {
            resources = getResources();
            i = R.mipmap.step4_icn_c;
        } else {
            resources = getResources();
            i = R.mipmap.step4_icn;
        }
        imageView.setImageDrawable(resources.getDrawable(i));
    }

    private void step5(boolean z) {
        Resources resources;
        int i;
        this.mhome_tv_step5.setTextColor(Color.parseColor(z ? "#F0B446" : "#FFFFFF"));
        ImageView imageView = this.mIvStep5;
        if (z) {
            resources = getResources();
            i = R.mipmap.step5_icn_c;
        } else {
            resources = getResources();
            i = R.mipmap.step5_icn;
        }
        imageView.setImageDrawable(resources.getDrawable(i));
    }

    private void traverseManagers(FragmentManager fragmentManager, SparseArray<FragmentManager> sparseArray, int i) {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            sparseArray.put(i, fragmentManager);
        }
        if (fragmentManager.getFragments() == null) {
            return;
        }
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null) {
                traverseManagers(fragment.getChildFragmentManager(), sparseArray, i + 1);
            }
        }
    }

    public void click2OutPut() {
        long[] jArr = this.mHits2;
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long[] jArr2 = this.mHits2;
        jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
        if (this.mHits2[0] >= SystemClock.uptimeMillis() - 1500) {
            this.mHits2 = new long[2];
            MyAppLocation.myAppLocation.myMqttService.outPutOrders(false, false, true);
            ArmsUtils.snackbarText(getString(R.string.outLogs));
            MyAppLocation.myAppLocation.myMqttService.deleteMessage(5);
        }
    }

    public void click5Login() {
        long[] jArr = this.mHits5;
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long[] jArr2 = this.mHits5;
        jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
        if (this.mHits5[0] >= SystemClock.uptimeMillis() - 1500) {
            this.mHits5 = new long[5];
            makeDialogLogin();
        }
    }

    public void dismissIndexDialog() {
        IndexDialog indexDialog = this.indexDialog;
        if (indexDialog != null && indexDialog.isShowing()) {
            this.indexDialog.dismiss();
        }
        ScheduledFuture<?> scheduledFuture = this.scheduleNew;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0) {
            DiaLogUtils.showAlert(getActivity(), getString(R.string.hint), getString(R.string.isexitapp), getString(R.string.confirm), new DialogInterface.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    Tools.upLocalLog("Home - 关闭应用");
                    MyAppLocation.myAppLocation.onDestroy();
                    ArmsUtils.exitApp();
                }
            }, getString(R.string.cancle), new DialogInterface.OnClickListener() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            return true;
        }
        if (keyEvent.getKeyCode() == 66 && keyEvent.getDeviceId() != -1) {
            handleKeyCodes();
            return true;
        }
        if (keyEvent.getDeviceId() == -1) {
            return super.dispatchKeyEvent(keyEvent);
        }
        this.scannedCodes.add(keyEvent);
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            EventBus.getDefault().post(new ResetTime());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.View
    public Activity getActivity() {
        return this;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources());
        return super.getResources();
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.View
    public String getState() {
        return ErrorUtils.codeDesc(ErrorCodeNew_Msg.ErrorNew);
    }

    @Override // com.jess.arms.mvp.IView
    public void hideLoading() {
        if (this.mSportDialog.isShowing()) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.activity.HomeActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    if (HomeActivity.this.mSportDialog == null) {
                        return;
                    }
                    HomeActivity.this.mSportDialog.dismiss();
                }
            });
        }
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public void initData(@Nullable Bundle bundle) {
        bindService(MyAppLocation.myAppLocation.mIntent1, MyAppLocation.myAppLocation.mConnection1, 1);
        initsparr();
        if (Constants.opsSeting().isHintTooBar()) {
            Constants.setHintTooBar(getActivity());
        }
        checkLocationPermission();
        this.mTvSn.setText(Constants.deviceSN().replaceAll(" ", ""));
        if (Constants.deviceDetail != null) {
            String servicePhone = Constants.deviceDetail.getServicePhone();
            if (!Tools.toString(servicePhone).equals("")) {
                this.mTvPhone.setText(servicePhone);
            }
            this.mTvSn.setText(Constants.deviceDetail.getDeviceExtNo());
        }
        checkNet();
        if (Constants.deviceTypeDetail != null && Constants.Model == 0 && Constants.deviceTypeDetail.getName().equals("JK81")) {
            MyAppLocation.myAppLocation.openSearchPresentation();
        }
        this.scheduledExecutorService = (ScheduledExecutorService) ArmsUtils.obtainAppComponentFromContext(getActivity()).executorService();
        Constants.transferKvData();
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public int initView(@Nullable Bundle bundle) {
        return R.layout.activity_home;
    }

    @Override // com.jess.arms.mvp.IView
    public void killMyself() {
        finish();
    }

    public /* synthetic */ void lambda$checkNet$1$HomeActivity(Boolean bool) {
        Tools.upLocalLog(bool.booleanValue() ? "设备网络情况：有网" : "设备网络情况：无网");
        if (this.mIvSn == null) {
            return;
        }
        final Drawable drawable = ContextCompat.getDrawable(this, bool.booleanValue() ? R.mipmap.ic_network : R.mipmap.ic_no_network);
        runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.activity.-$$Lambda$HomeActivity$aFZoVukz9FPLn-pCeCownO1wfww
            @Override // java.lang.Runnable
            public final void run() {
                HomeActivity.this.lambda$null$0$HomeActivity(drawable);
            }
        });
    }

    public /* synthetic */ void lambda$new$2$HomeActivity() {
        runOnUiThread(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.activity.-$$Lambda$kvkm6jAos6rF1saUttA2Ajm7ojY
            @Override // java.lang.Runnable
            public final void run() {
                HomeActivity.this.dismissIndexDialog();
            }
        });
    }

    public /* synthetic */ void lambda$null$0$HomeActivity(Drawable drawable) {
        if (drawable != null) {
            this.mIvSn.setBackground(drawable);
        }
    }

    public /* synthetic */ void lambda$onEvent$4$HomeActivity(MyEventBusMessage myEventBusMessage) {
        ((HomePresenter) this.mPresenter).saveOrderFailLog(myEventBusMessage.getMsg());
    }

    public /* synthetic */ void lambda$showIndexDialog$3$HomeActivity(DialogInterface dialogInterface) {
        ScheduledFuture<?> scheduledFuture = this.scheduleNew;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override // com.yj.coffeemachines.mvp.contract.HomeContract.View
    public void loginSuccess(LoginBack loginBack) {
        Constants.isSettingInterface = true;
        launchActivity(new Intent(getActivity(), (Class<?>) SetingsHomeActivity.class));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        SparseArray<FragmentManager> sparseArray = new SparseArray<>();
        traverseManagers(getSupportFragmentManager(), sparseArray, 0);
        if (sparseArray.size() > 0) {
            sparseArray.valueAt(sparseArray.size() - 1).popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick({R.id.tv_sn, R.id.tv_phone, R.id.iv_step1, R.id.iv_step2, R.id.iv_step3, R.id.btn_ai, R.id.iv_step4, R.id.iv_step5, R.id.tv_sn1, R.id.tv_phone1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ai /* 2131296350 */:
                startActivity(new Intent(getActivity(), (Class<?>) WebViewActivity.class));
                return;
            case R.id.iv_sn /* 2131296558 */:
            case R.id.iv_step1 /* 2131296559 */:
            case R.id.tv_sn /* 2131297088 */:
            case R.id.tv_sn1 /* 2131297089 */:
                if (Constants.NowStep != 1) {
                    return;
                }
                click5Login();
                return;
            case R.id.iv_step2 /* 2131296560 */:
            case R.id.tv_phone /* 2131297061 */:
            case R.id.tv_phone1 /* 2131297062 */:
                if (Constants.NowStep != 1) {
                    return;
                }
                click2OutPut();
                return;
            case R.id.iv_step3 /* 2131296561 */:
                if (Constants.NowStep != 1) {
                    return;
                }
                click2OutPut();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jess.arms.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ButterKnife.bind(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jess.arms.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unbindService(MyAppLocation.myAppLocation.mConnection1);
        Tools.upLocalLog("Home - onDestroy - 关闭应用");
        MyAppLocation.myAppLocation.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LogoMessage logoMessage) {
        if (Constants.merchantLogo == null || TextUtils.isEmpty(Constants.merchantLogo)) {
            this.ivLogo.setVisibility(8);
        } else {
            this.ivLogo.setVisibility(0);
            Glide.with(this.ivLogo).load(Constants.merchantLogo).diskCacheStrategy(DiskCacheStrategy.ALL).into(this.ivLogo);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(final MyEventBusMessage myEventBusMessage) {
        if (myEventBusMessage.getFrom().equals(Step1Fragment.tag)) {
            int tag = myEventBusMessage.getTag();
            if (tag == 1) {
                makeDialogPic();
                return;
            }
            if (tag == 2) {
                replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step2Fragment.newInstance(Constants.nowProduct), this.mSparseTags.get(R.id.iv_step2));
                return;
            }
            if (tag != 3) {
                if (tag == 4) {
                    replaceFragment(getSupportFragmentManager(), R.id.fl_container, EventFragment.newInstance(), this.mSparseTags.get(R.id.step_ll_getdrinks));
                    return;
                } else {
                    if (tag != 5) {
                        return;
                    }
                    launchActivity(new Intent(getActivity(), (Class<?>) ADActivity.class));
                    return;
                }
            }
            return;
        }
        if (myEventBusMessage.getFrom().equals(EventFragment.tag)) {
            int tag2 = myEventBusMessage.getTag();
            if (tag2 == 1) {
                onBackPressed();
                return;
            } else if (tag2 == 2) {
                replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step2Fragment.newInstance(Constants.nowProduct), this.mSparseTags.get(R.id.iv_step2));
                return;
            } else {
                if (tag2 != 3) {
                    return;
                }
                replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step3Fragment.newInstance(), this.mSparseTags.get(R.id.iv_step3));
                return;
            }
        }
        if (myEventBusMessage.getFrom().equals(Step2Fragment.tag)) {
            int tag3 = myEventBusMessage.getTag();
            if (tag3 == 1) {
                replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step1Fragment.newInstance(), this.mSparseTags.get(R.id.iv_step1));
                return;
            } else if (tag3 == 2) {
                replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step3Fragment.newInstance(), this.mSparseTags.get(R.id.iv_step3));
                return;
            } else {
                if (tag3 != 3) {
                    return;
                }
                replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step4Fragment.newInstance(), this.mSparseTags.get(R.id.iv_step4));
                return;
            }
        }
        if (myEventBusMessage.getFrom().equals(Step3Fragment.tag)) {
            int tag4 = myEventBusMessage.getTag();
            if (tag4 == 1) {
                replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step1Fragment.newInstance(), this.mSparseTags.get(R.id.iv_step1));
                return;
            }
            if (tag4 == 2) {
                replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step4Fragment.newInstance(), this.mSparseTags.get(R.id.iv_step4));
                return;
            } else {
                if (tag4 != 3) {
                    return;
                }
                replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step1Fragment.newInstance(), this.mSparseTags.get(R.id.iv_step1));
                ((HomePresenter) this.mPresenter).orderCancel(Constants.pay_no, Constants.buy_type);
                return;
            }
        }
        if (!myEventBusMessage.getFrom().equals(Step4Fragment.tag)) {
            if (myEventBusMessage.getFrom().equals(Step5Fragment.tag) && myEventBusMessage.getTag() == 1) {
                replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step1Fragment.newInstance(), this.mSparseTags.get(R.id.iv_step1));
                return;
            }
            return;
        }
        int tag5 = myEventBusMessage.getTag();
        if (tag5 == 1) {
            if (Constants.buy_type == 16) {
                byte[] bArr = {-86, 85, 3, 36, 0};
                MyAppLocation.myAppLocation.mSerialDataService.addSendByteToList_Payment(ByteUtils.byteMerger(bArr, new byte[]{(byte) (CRC8Util.byteCheckSum(bArr) & 255)}));
            }
            MdbSendMethod.instance().getCheckMoney("");
            replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step5Fragment.newInstance(), this.mSparseTags.get(R.id.iv_step5));
            ((HomePresenter) this.mPresenter).produceOver();
            ((HomePresenter) this.mPresenter).outStockOver();
            return;
        }
        if (tag5 != 2) {
            return;
        }
        replaceFragment(getSupportFragmentManager(), R.id.fl_container, Step1Fragment.newInstance(), this.mSparseTags.get(R.id.iv_step1));
        if (Constants.buy_type == 1) {
            ((HomePresenter) this.mPresenter).unionPayReturnAmt();
        } else if (Constants.buy_type == 5 || Constants.buy_type == 15 || Constants.buy_type == PayType.NORTH_PAY || Constants.buy_type == PayType.TP_PAY) {
            ((HomePresenter) this.mPresenter).weChartPayReturnAmt(myEventBusMessage.getMsg());
        } else if (Constants.buy_type == 6) {
            ((HomePresenter) this.mPresenter).aLiPayReturnAmt();
        } else if (Constants.buy_type == 7) {
            ((HomePresenter) this.mPresenter).ERMBReturnAmt();
        } else if (Constants.buy_type == 2 || Constants.buy_type == 3) {
            if (Constants.HomePay() == 2) {
                MdbSendMethod.instance().getCheckMoney("制作失败");
            } else {
                processRefund(Constants.buy_type);
            }
        } else if (Constants.buy_type == 8) {
            ((HomePresenter) this.mPresenter).ERMBReturnAmtHui("2");
        } else if (Constants.buy_type == 9) {
            ((HomePresenter) this.mPresenter).icbcPayReturnAmt();
        } else if (Constants.buy_type == 13) {
            ((HomePresenter) this.mPresenter).SaoBeiPayReturnAmt();
        } else if (Constants.buy_type == 14) {
            ((HomePresenter) this.mPresenter).t50PayRefund();
        }
        ((HomePresenter) this.mPresenter).produceFail();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.yj.coffeemachines.mvp.ui.activity.-$$Lambda$HomeActivity$hRYrzDJDkwcUDYDjqPplJq7AnzY
            @Override // java.lang.Runnable
            public final void run() {
                HomeActivity.this.lambda$onEvent$4$HomeActivity(myEventBusMessage);
            }
        }, 2000L);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NetWorkState netWorkState) {
        if (netWorkState.isLink()) {
            Tools.upLocalLog("设备网络情况：有网");
            this.mIvSn.setBackground(getResources().getDrawable(R.mipmap.ic_network));
        } else {
            Tools.upLocalLog("设备网络情况：无网");
            this.mIvSn.setBackground(getResources().getDrawable(R.mipmap.ic_no_network));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TimeMessage timeMessage) {
        checkReturnAmt();
        int i = this.netCheckTime;
        if (i == 120) {
            checkNet();
            this.netCheckTime = 0;
        } else {
            this.netCheckTime = i + 1;
        }
        if (Constants.isRestart) {
            this.restartTime--;
        }
        if (this.restartTime <= 0) {
            this.restartTime = 30;
            Constants.isRestart = false;
            Constants.reBoot();
        }
        if (Constants.NowStep != 1) {
            return;
        }
        int i2 = this.checkTime;
        if (i2 <= this.checkTimeEnd) {
            this.checkTime = i2 + 1;
        } else {
            this.checkTime = 0;
        }
        disposeErrorMsg();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DeviceInfoBean.InfoBean infoBean) {
        if (!Tools.toString(infoBean.getServicePhone()).equals("")) {
            this.mTvPhone.setText(infoBean.getServicePhone());
        }
        this.mTvSn.setText(infoBean.getDeviceExtNo());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        EventBus.getDefault().post(new ResetTime());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        boolean z = true;
        if (i == 123) {
            boolean z2 = true;
            for (int i2 : iArr) {
                if (i2 != 0) {
                    z2 = false;
                }
            }
            z = z2;
        }
        if (z) {
            return;
        }
        Toast.makeText(this, "Permission error !!!", 0).show();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new ResetTime());
        int i = KvUtil.getInstance().getInt("aSwitch", 0);
        String string = KvUtil.getInstance().getString("aiText", "Vorbește cu mine");
        if (i == 1) {
            this.mBtnAi.setVisibility(0);
            this.mBtnAi.setText(string);
        } else {
            this.mBtnAi.setVisibility(8);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        if (Constants.getHomeLocation() == 0) {
            layoutParams.bottomMargin = 15;
            layoutParams.gravity = 81;
        } else {
            layoutParams.leftMargin = 15;
            layoutParams.bottomMargin = 15;
            layoutParams.gravity = BadgeDrawable.BOTTOM_START;
        }
        this.cardviewLocation.setLayoutParams(layoutParams);
        errorShowDialog(ErrorCodeNew_Msg.isError());
    }

    public void replaceFragment(FragmentManager fragmentManager, int i, Fragment fragment, String str) {
        if (fragmentManager.findFragmentByTag(str) == null) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.replace(i, fragment, str);
            beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            beginTransaction.addToBackStack(str);
            beginTransaction.commit();
        } else {
            fragmentManager.popBackStack(str, 0);
        }
        if (str.equals(this.mSparseTags.get(R.id.step_ll_getdrinks))) {
            Constants.ISMAKINGADRINK = true;
        } else if (str.equals(this.mSparseTags.get(R.id.iv_step1))) {
            Constants.ISMAKINGADRINK = false;
            choseChange(1);
        } else if (str.equals(this.mSparseTags.get(R.id.iv_step2))) {
            Constants.ISMAKINGADRINK = true;
            choseChange(2);
        } else if (str.equals(this.mSparseTags.get(R.id.iv_step3))) {
            Constants.ISMAKINGADRINK = true;
            choseChange(3);
        } else if (str.equals(this.mSparseTags.get(R.id.iv_step4))) {
            Constants.ISMAKINGADRINK = true;
            choseChange(4);
        } else if (str.equals(this.mSparseTags.get(R.id.iv_step5))) {
            Constants.ISMAKINGADRINK = true;
            choseChange(5);
        }
        Tools.upLocalLog("切换步骤" + Constants.NowStep);
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
