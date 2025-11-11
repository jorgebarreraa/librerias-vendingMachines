package com.yj.coffeemachines.di.component;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.yj.coffeemachines.di.component.LogComponent;
import com.yj.coffeemachines.mvp.contract.LogContract;
import com.yj.coffeemachines.mvp.model.LogModel;
import com.yj.coffeemachines.mvp.model.LogModel_Factory;
import com.yj.coffeemachines.mvp.presenter.LogPresenter;
import com.yj.coffeemachines.mvp.presenter.LogPresenter_Factory;
import com.yj.coffeemachines.mvp.ui.fragment.LogFragment;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class DaggerLogComponent implements LogComponent {
    private Provider<AppManager> appManagerProvider;
    private Provider<Application> applicationProvider;
    private Provider<Gson> gsonProvider;
    private Provider<ImageLoader> imageLoaderProvider;
    private Provider<LogModel> logModelProvider;
    private Provider<LogPresenter> logPresenterProvider;
    private Provider<IRepositoryManager> repositoryManagerProvider;
    private Provider<RxErrorHandler> rxErrorHandlerProvider;
    private Provider<LogContract.View> viewProvider;

    /* loaded from: classes.dex */
    private static final class Builder implements LogComponent.Builder {
        private AppComponent appComponent;
        private LogContract.View view;

        private Builder() {
        }

        @Override // com.yj.coffeemachines.di.component.LogComponent.Builder
        public Builder appComponent(AppComponent appComponent) {
            this.appComponent = (AppComponent) Preconditions.checkNotNull(appComponent);
            return this;
        }

        @Override // com.yj.coffeemachines.di.component.LogComponent.Builder
        public LogComponent build() {
            Preconditions.checkBuilderRequirement(this.view, LogContract.View.class);
            Preconditions.checkBuilderRequirement(this.appComponent, AppComponent.class);
            return new DaggerLogComponent(this.appComponent, this.view);
        }

        @Override // com.yj.coffeemachines.di.component.LogComponent.Builder
        public Builder view(LogContract.View view) {
            this.view = (LogContract.View) Preconditions.checkNotNull(view);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_appManager implements Provider<AppManager> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_appManager(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public AppManager get() {
            return (AppManager) Preconditions.checkNotNull(this.appComponent.appManager(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_application implements Provider<Application> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_application(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public Application get() {
            return (Application) Preconditions.checkNotNull(this.appComponent.application(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_gson implements Provider<Gson> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_gson(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public Gson get() {
            return (Gson) Preconditions.checkNotNull(this.appComponent.gson(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_imageLoader implements Provider<ImageLoader> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_imageLoader(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public ImageLoader get() {
            return (ImageLoader) Preconditions.checkNotNull(this.appComponent.imageLoader(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_repositoryManager implements Provider<IRepositoryManager> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_repositoryManager(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public IRepositoryManager get() {
            return (IRepositoryManager) Preconditions.checkNotNull(this.appComponent.repositoryManager(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class com_jess_arms_di_component_AppComponent_rxErrorHandler implements Provider<RxErrorHandler> {
        private final AppComponent appComponent;

        com_jess_arms_di_component_AppComponent_rxErrorHandler(AppComponent appComponent) {
            this.appComponent = appComponent;
        }

        @Override // javax.inject.Provider
        public RxErrorHandler get() {
            return (RxErrorHandler) Preconditions.checkNotNull(this.appComponent.rxErrorHandler(), "Cannot return null from a non-@Nullable component method");
        }
    }

    private DaggerLogComponent(AppComponent appComponent, LogContract.View view) {
        initialize(appComponent, view);
    }

    public static LogComponent.Builder builder() {
        return new Builder();
    }

    private void initialize(AppComponent appComponent, LogContract.View view) {
        this.repositoryManagerProvider = new com_jess_arms_di_component_AppComponent_repositoryManager(appComponent);
        this.gsonProvider = new com_jess_arms_di_component_AppComponent_gson(appComponent);
        this.applicationProvider = new com_jess_arms_di_component_AppComponent_application(appComponent);
        this.logModelProvider = DoubleCheck.provider(LogModel_Factory.create(this.repositoryManagerProvider, this.gsonProvider, this.applicationProvider));
        this.viewProvider = InstanceFactory.create(view);
        this.rxErrorHandlerProvider = new com_jess_arms_di_component_AppComponent_rxErrorHandler(appComponent);
        this.imageLoaderProvider = new com_jess_arms_di_component_AppComponent_imageLoader(appComponent);
        this.appManagerProvider = new com_jess_arms_di_component_AppComponent_appManager(appComponent);
        this.logPresenterProvider = DoubleCheck.provider(LogPresenter_Factory.create(this.logModelProvider, this.viewProvider, this.rxErrorHandlerProvider, this.applicationProvider, this.imageLoaderProvider, this.appManagerProvider));
    }

    private LogFragment injectLogFragment(LogFragment logFragment) {
        BaseFragment_MembersInjector.injectMPresenter(logFragment, this.logPresenterProvider.get());
        return logFragment;
    }

    @Override // com.yj.coffeemachines.di.component.LogComponent
    public void inject(LogFragment logFragment) {
        injectLogFragment(logFragment);
    }
}
