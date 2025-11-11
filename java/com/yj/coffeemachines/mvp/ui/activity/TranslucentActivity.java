package com.yj.coffeemachines.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.yj.coffeemachines.R;
import com.yj.coffeemachines.dialog.SetLanguageDialog;
import com.yj.coffeemachines.helper.LanguageHelper;

/* loaded from: classes.dex */
public class TranslucentActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!LanguageHelper.isSetLanguage()) {
            new SetLanguageDialog(this, true).show();
        } else {
            startActivity(new Intent(this, (Class<?>) HomeActivity.class));
            finish();
        }
    }
}
