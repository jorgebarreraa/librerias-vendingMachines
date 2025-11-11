package com.jess.arms.base.delegate;

import android.app.Application;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.inject.Named;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class AppDelegate_MembersInjector implements MembersInjector<AppDelegate> {
    private final Provider<Application.ActivityLifecycleCallbacks> mActivityLifecycleForRxLifecycleProvider;
    private final Provider<Application.ActivityLifecycleCallbacks> mActivityLifecycleProvider;

    public AppDelegate_MembersInjector(Provider<Application.ActivityLifecycleCallbacks> provider, Provider<Application.ActivityLifecycleCallbacks> provider2) {
        this.mActivityLifecycleProvider = provider;
        this.mActivityLifecycleForRxLifecycleProvider = provider2;
    }

    public static MembersInjector<AppDelegate> create(Provider<Application.ActivityLifecycleCallbacks> provider, Provider<Application.ActivityLifecycleCallbacks> provider2) {
        return new AppDelegate_MembersInjector(provider, provider2);
    }

    @InjectedFieldSignature("com.jess.arms.base.delegate.AppDelegate.mActivityLifecycle")
    @Named("ActivityLifecycle")
    public static void injectMActivityLifecycle(AppDelegate appDelegate, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        appDelegate.mActivityLifecycle = activityLifecycleCallbacks;
    }

    @InjectedFieldSignature("com.jess.arms.base.delegate.AppDelegate.mActivityLifecycleForRxLifecycle")
    @Named("ActivityLifecycleForRxLifecycle")
    public static void injectMActivityLifecycleForRxLifecycle(AppDelegate appDelegate, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        appDelegate.mActivityLifecycleForRxLifecycle = activityLifecycleCallbacks;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AppDelegate appDelegate) {
        injectMActivityLifecycle(appDelegate, this.mActivityLifecycleProvider.get());
        injectMActivityLifecycleForRxLifecycle(appDelegate, this.mActivityLifecycleForRxLifecycleProvider.get());
    }
}
