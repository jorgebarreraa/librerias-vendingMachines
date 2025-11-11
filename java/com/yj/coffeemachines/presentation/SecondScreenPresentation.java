package com.yj.coffeemachines.presentation;

import android.app.Presentation;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.app.utils.FileUtils;
import com.yj.coffeemachines.carousel.Advance;
import com.yj.coffeemachines.carousel.AdvanceView;
import com.yj.coffeemachines.eventbusbean.AdMessage;
import com.yj.coffeemachines.eventbusbean.TimeMessage;
import com.yj.coffeemachines.greendao.DBHelper;
import com.yj.coffeemachines.greendao.beans.ADMessage;
import com.yj.coffeemachines.greendao.daos.ADMessageDao;
import com.yj.coffeemachines.helper.Tools;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class SecondScreenPresentation extends Presentation {
    private static final String IMAGE_SUFFIX_JPG = "jpg";
    private static final String IMAGE_SUFFIX_PNG = "png";
    private static final String MEDIA_TYPE_IMAGE = "2";
    private static final String MEDIA_TYPE_VIDEO = "1";
    private static final int UPDATE_INTERVAL = 60;
    private List<String> listOld;
    private AdvanceView mBanner;
    private ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor;
    private TextView textView;
    private int times;

    public SecondScreenPresentation(Context context, Display display) {
        super(context, display);
        this.listOld = new ArrayList();
    }

    @NonNull
    private List<Advance> buildAdData(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            arrayList.add(new Advance(str, (str.endsWith(IMAGE_SUFFIX_PNG) || str.endsWith(IMAGE_SUFFIX_JPG)) ? MEDIA_TYPE_IMAGE : MEDIA_TYPE_VIDEO));
        }
        return arrayList;
    }

    private void configureWindowType() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.type = Build.VERSION.SDK_INT >= 26 ? 2038 : 2003;
        getWindow().setAttributes(attributes);
    }

    private void initAdList() {
        ADMessageDao aDMessageDao = DBHelper.getADMessageDao();
        long currentTimeMillis = System.currentTimeMillis();
        List<ADMessage> list = aDMessageDao.queryBuilder().where(ADMessageDao.Properties.StartTime_long.le(Long.valueOf(currentTimeMillis)), ADMessageDao.Properties.EndTime_long.ge(Long.valueOf(currentTimeMillis))).orderAsc(ADMessageDao.Properties.Sort).list();
        Tools.upLocalLog("广告播放列表Second-initADlist: " + new Gson().toJson(list));
        if (list == null || list.isEmpty()) {
            updateEmptyView(true);
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<ADMessage> it2 = list.iterator();
        while (it2.hasNext()) {
            String localPath = it2.next().getLocalPath();
            if (FileUtils.isFileExists(localPath)) {
                arrayList.add(localPath);
            }
        }
        if (isListUnchanged(arrayList)) {
            return;
        }
        updateAdView(buildAdData(arrayList));
        this.listOld = new ArrayList(arrayList);
    }

    private void initView() {
        this.mBanner = (AdvanceView) findViewById(R.id.banner);
        this.textView = (TextView) findViewById(R.id.tv_hint);
    }

    private boolean isListUnchanged(List<String> list) {
        if (this.listOld.isEmpty() || this.listOld.size() != list.size()) {
            return false;
        }
        return new HashSet(this.listOld).equals(new HashSet(list));
    }

    private void updateAdView(List<Advance> list) {
        if (list.isEmpty()) {
            updateEmptyView(true);
            return;
        }
        updateEmptyView(false);
        Tools.upLocalLogM2("setData()-Second: " + list);
        this.mBanner.setData(list);
        this.mBanner.setResume();
    }

    private void updateEmptyView(boolean z) {
        this.textView.setVisibility(z ? 0 : 8);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        EventBus.getDefault().unregister(this);
        super.dismiss();
        AdvanceView advanceView = this.mBanner;
        if (advanceView != null) {
            advanceView.setPause();
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ad);
        configureWindowType();
        initView();
        initAdList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AdMessage adMessage) {
        if (isShowing()) {
            initAdList();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TimeMessage timeMessage) {
        this.times++;
        if (this.times % 60 == 0 && isShowing()) {
            initAdList();
        }
        if (this.times >= 60) {
            this.times = 0;
        }
    }

    @Override // android.app.Presentation, android.app.Dialog
    public void show() {
        super.show();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        AdvanceView advanceView = this.mBanner;
        if (advanceView != null) {
            advanceView.setResume();
        }
    }
}
