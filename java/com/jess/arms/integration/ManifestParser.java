package com.jess.arms.integration;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class ManifestParser {
    private static final String MODULE_VALUE = "ConfigModule";
    private final Context context;

    public ManifestParser(Context context) {
        this.context = context;
    }

    private static ConfigModule parseModule(String str) {
        try {
            Class<?> cls = Class.forName(str);
            try {
                Object newInstance = cls.newInstance();
                if (newInstance instanceof ConfigModule) {
                    return (ConfigModule) newInstance;
                }
                throw new RuntimeException("Expected instanceof ConfigModule, but found: " + newInstance);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to instantiate ConfigModule implementation for " + cls, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Unable to instantiate ConfigModule implementation for " + cls, e2);
            }
        } catch (ClassNotFoundException e3) {
            throw new IllegalArgumentException("Unable to find ConfigModule implementation", e3);
        }
    }

    public List<ConfigModule> parse() {
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                for (String str : applicationInfo.metaData.keySet()) {
                    if (MODULE_VALUE.equals(applicationInfo.metaData.get(str))) {
                        arrayList.add(parseModule(str));
                    }
                }
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse ConfigModule", e);
        }
    }
}
