package com.yj.coffeemachines.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.yj.coffeemachines.Constants;
import com.yj.coffeemachines.MyAppLocation;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.FileUtils;
import com.yj.coffeemachines.carousel.Advance;
import com.yj.coffeemachines.carousel.AdvanceView;
import com.yj.coffeemachines.di.component.DaggerADComponent;
import com.yj.coffeemachines.eventbusbean.AdMessage;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.ADMessage;
import com.yj.coffeemachines.greendao.daos.ADMessageDao;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.mvp.contract.ADContract;
import com.yj.coffeemachines.mvp.presenter.ADPresenter;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class ADActivity extends BaseActivity<ADPresenter> implements ADContract.View {
    List<String> list_old = new ArrayList();
    AdvanceView mBanner;
    RelativeLayout mParentlayout;

    private void initADlist() {
        Tools.upLocalLog("播放广告");
        ADMessageDao aDMessageDao = DBHelper.getADMessageDao();
        long currentTimeMillis = System.currentTimeMillis();
        List<ADMessage> list = aDMessageDao.queryBuilder().where(ADMessageDao.Properties.StartTime_long.le(Long.valueOf(currentTimeMillis)), new WhereCondition[0]).where(ADMessageDao.Properties.EndTime_long.ge(Long.valueOf(currentTimeMillis)), new WhereCondition[0]).orderAsc(ADMessageDao.Properties.Sort).list();
        if (list.size() == 0) {
            killMyself();
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<ADMessage> list2 = aDMessageDao.queryBuilder().where(ADMessageDao.Properties.StartTime_long.le(Long.valueOf(currentTimeMillis)), new WhereCondition[0]).where(ADMessageDao.Properties.EndTime_long.ge(Long.valueOf(currentTimeMillis)), new WhereCondition[0]).where(ADMessageDao.Properties.Sort.eq(Integer.valueOf(list.get(0).getSort())), new WhereCondition[0]).list();
        for (int i = 0; i < list2.size(); i++) {
            String localPath = list2.get(i).getLocalPath();
            if (FileUtils.isFileExists(localPath)) {
                arrayList.add(localPath);
            }
        }
        if (arrayList.size() == 0) {
            ArmsUtils.snackbarText(getString(R.string.downloaderror));
            killMyself();
            return;
        }
        if (this.list_old.size() != 0 && this.list_old.size() == arrayList.size()) {
            boolean z = false;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (!this.list_old.contains(arrayList.get(i2))) {
                    z = true;
                }
            }
            if (!z) {
                return;
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            String str = (String) arrayList.get(i3);
            if (str.endsWith("png") || str.endsWith("jpg")) {
                arrayList2.add(new Advance(str, "2"));
            } else {
                arrayList2.add(new Advance(str, "1"));
            }
        }
        if (arrayList2.size() == 0) {
            killMyself();
            return;
        }
        this.mBanner.setData(arrayList2);
        this.mBanner.setResume();
        this.list_old.clear();
        this.list_old.addAll(arrayList);
    }

    private void initView() {
        this.mBanner = (AdvanceView) findViewById(R.id.banner);
        this.mParentlayout = (RelativeLayout) findViewById(R.id.parentlayout);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            Constants.isVoice = true;
            MyAppLocation.myAppLocation.voicePlayService.playVoice(1);
            killMyself();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.yj.coffeemachines.mvp.contract.ADContract.View
    public Context getActivity() {
        return this;
    }

    @Override // com.jess.arms.mvp.IView
    public /* synthetic */ void hideLoading() {
        IView.CC.$default$hideLoading(this);
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public void initData(@Nullable Bundle bundle) {
        initView();
        initADlist();
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public int initView(@Nullable Bundle bundle) {
        return R.layout.activity_ad;
    }

    @Override // com.jess.arms.mvp.IView
    public void killMyself() {
        finish();
    }

    @Override // com.jess.arms.mvp.IView
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
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
        this.mBanner.setDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AdMessage adMessage) {
        initADlist();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Tools.upLocalLog("退出广告播放");
        this.mBanner.setPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mBanner.setResume();
    }

    @Override // com.jess.arms.base.delegate.IActivity
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerADComponent.builder().appComponent(appComponent).view(this).build().inject(this);
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
