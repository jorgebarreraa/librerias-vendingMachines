package com.yj.coffeemachines.app.service;

import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.hardware.display.DisplayManagerCompat;
import com.jess.arms.base.BaseService;
import com.yj.coffeemachines.helper.Tools;
import com.yj.coffeemachines.presentation.SecondScreenPresentation;

/* loaded from: classes.dex */
public class MultiScreenService extends BaseService {
    private DisplayManager.DisplayListener mDisplayListener;
    private DisplayManager mDisplayManager;
    private SecondScreenPresentation presentation;

    /* loaded from: classes.dex */
    public class MultiScreenBinder extends Binder {
        public MultiScreenBinder() {
        }

        public MultiScreenService getService() {
            return MultiScreenService.this;
        }
    }

    private void initMultiScreen() {
        this.mDisplayManager = (DisplayManager) getSystemService("display");
        Display[] displays = this.mDisplayManager.getDisplays(DisplayManagerCompat.DISPLAY_CATEGORY_PRESENTATION);
        int length = displays.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Display display = displays[i];
            if (isValidExternalDisplay(display)) {
                setupPresentation(display);
                break;
            }
            i++;
        }
        registerDisplayListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidExternalDisplay(Display display) {
        return ((display.getFlags() & 8) == 0 || display.getDisplayId() == 0) ? false : true;
    }

    private void registerDisplayListener() {
        this.mDisplayListener = new DisplayManager.DisplayListener() { // from class: com.yj.coffeemachines.app.service.MultiScreenService.1
            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayAdded(int i) {
                Display display = MultiScreenService.this.mDisplayManager.getDisplay(i);
                if (MultiScreenService.this.isValidExternalDisplay(display)) {
                    MultiScreenService.this.setupPresentation(display);
                }
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayChanged(int i) {
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayRemoved(int i) {
                if (MultiScreenService.this.presentation == null || MultiScreenService.this.presentation.getDisplay().getDisplayId() != i) {
                    return;
                }
                MultiScreenService.this.presentation.dismiss();
                MultiScreenService.this.presentation = null;
            }
        };
        this.mDisplayManager.registerDisplayListener(this.mDisplayListener, new Handler(Looper.getMainLooper()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupPresentation(Display display) {
        try {
            this.presentation = new SecondScreenPresentation(this, display);
            int i = Build.VERSION.SDK_INT < 26 ? 2003 : 2038;
            Window window = this.presentation.getWindow();
            if (window != null) {
                window.setType(i);
                window.addFlags(8192);
            }
            this.presentation.show();
        } catch (WindowManager.InvalidDisplayException e) {
            Tools.upLocalLogM2("MultiScreenInvalid display: " + e.getMessage());
        } catch (RuntimeException e2) {
            Tools.upLocalLogM2("MultiScreenPresentation failed: " + e2.getMessage());
        }
    }

    public void hindSearchPresentation() {
        Tools.upLocalLogM2("M2:隐藏显示第二块屏幕");
        SecondScreenPresentation secondScreenPresentation = this.presentation;
        if (secondScreenPresentation != null && secondScreenPresentation.isShowing()) {
            this.presentation.dismiss();
        }
    }

    @Override // com.jess.arms.base.BaseService
    public void init() {
        initMultiScreen();
    }

    @Override // com.jess.arms.base.BaseService, android.app.Service
    public IBinder onBind(Intent intent) {
        return new MultiScreenBinder();
    }

    @Override // com.jess.arms.base.BaseService, android.app.Service
    public void onDestroy() {
        DisplayManager.DisplayListener displayListener;
        super.onDestroy();
        DisplayManager displayManager = this.mDisplayManager;
        if (displayManager != null && (displayListener = this.mDisplayListener) != null) {
            displayManager.unregisterDisplayListener(displayListener);
        }
        SecondScreenPresentation secondScreenPresentation = this.presentation;
        if (secondScreenPresentation != null) {
            secondScreenPresentation.dismiss();
            this.presentation = null;
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        hindSearchPresentation();
        return super.onUnbind(intent);
    }

    public void showSearchPresentation() {
        Tools.upLocalLogM2("M2:显示第二块屏幕");
        SecondScreenPresentation secondScreenPresentation = this.presentation;
        if (secondScreenPresentation == null || secondScreenPresentation.isShowing()) {
            return;
        }
        this.presentation.show();
    }
}
