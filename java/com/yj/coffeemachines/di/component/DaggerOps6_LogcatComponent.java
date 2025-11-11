package com.yj.coffeemachines.di.component;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.yj.coffeemachines.di.component.Ops6_LogcatComponent;
import com.yj.coffeemachines.mvp.contract.Ops6_LogcatContract;
import com.yj.coffeemachines.mvp.model.Ops6_LogcatModel;
import com.yj.coffeemachines.mvp.model.Ops6_LogcatModel_Factory;
import com.yj.coffeemachines.mvp.presenter.Ops6_LogcatPresenter;
import com.yj.coffeemachines.mvp.presenter.Ops6_LogcatPresenter_Factory;
import com.yj.coffeemachines.mvp.ui.fragment.Ops6_LogcatFragment;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class DaggerOps6_LogcatComponent implements Ops6_LogcatComponent {
    private Provider<AppManager> appManagerProvider;
    private Provider<Application> applicationProvider;
    private Provider<Gson> gsonProvider;
    private Provider<ImageLoader> imageLoaderProvider;
    private Provider<Ops6_LogcatModel> ops6_LogcatModelProvider;
    private Provider<Ops6_LogcatPresenter> ops6_LogcatPresenterProvider;
    private Provider<IRepositoryManager> repositoryManagerProvider;
    private Provider<RxErrorHandler> rxErrorHandlerProvider;
    private Provider<Ops6_LogcatContract.View> viewProvider;

    /* loaded from: classes.dex */
    private static final class Builder implements Ops6_LogcatComponent.Builder {
        private AppComponent appComponent;
        private Ops6_LogcatContract.View view;

        private Builder() {
        }

        @Override // com.yj.coffeemachines.di.component.Ops6_LogcatComponent.Builder
        public Builder appComponent(AppComponent appComponent) {
            this.appComponent = (AppComponent) Preconditions.checkNotNull(appComponent);
            return this;
        }

        @Override // com.yj.coffeemachines.di.component.Ops6_LogcatComponent.Builder
        public Ops6_LogcatComponent build() {
            Preconditions.checkBuilderRequirement(this.view, Ops6_LogcatContract.View.class);
            Preconditions.checkBuilderRequirement(this.appComponent, AppComponent.class);
            return new DaggerOps6_LogcatComponent(this.appComponent, this.view);
        }

        @Override // com.yj.coffeemachines.di.component.Ops6_LogcatComponent.Builder
        public Builder view(Ops6_LogcatContract.View view) {
            this.view = (Ops6_LogcatContract.View) Preconditions.checkNotNull(view);
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

    private DaggerOps6_LogcatComponent(AppComponent appComponent, Ops6_LogcatContract.View view) {
        initialize(appComponent, view);
    }

    public static Ops6_LogcatComponent.Builder builder() {
        return new Builder();
    }

    private void initialize(AppComponent appComponent, Ops6_LogcatContract.View view) {
        this.repositoryManagerProvider = new com_jess_arms_di_component_AppComponent_repositoryManager(appComponent);
        this.gsonProvider = new com_jess_arms_di_component_AppComponent_gson(appComponent);
        this.applicationProvider = new com_jess_arms_di_component_AppComponent_application(appComponent);
        this.ops6_LogcatModelProvider = DoubleCheck.provider(Ops6_LogcatModel_Factory.create(this.repositoryManagerProvider, this.gsonProvider, this.applicationProvider));
        this.viewProvider = InstanceFactory.create(view);
        this.rxErrorHandlerProvider = new com_jess_arms_di_component_AppComponent_rxErrorHandler(appComponent);
        this.imageLoaderProvider = new com_jess_arms_di_component_AppComponent_imageLoader(appComponent);
        this.appManagerProvider = new com_jess_arms_di_component_AppComponent_appManager(appComponent);
        this.ops6_LogcatPresenterProvider = DoubleCheck.provider(Ops6_LogcatPresenter_Factory.create(this.ops6_LogcatModelProvider, this.viewProvider, this.rxErrorHandlerProvider, this.applicationProvider, this.imageLoaderProvider, this.appManagerProvider));
    }

    private Ops6_LogcatFragment injectOps6_LogcatFragment(Ops6_LogcatFragment ops6_LogcatFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops6_LogcatFragment, this.ops6_LogcatPresenterProvider.get());
        return ops6_LogcatFragment;
    }

    @Override // com.yj.coffeemachines.di.component.Ops6_LogcatComponent
    public void inject(Ops6_LogcatFragment ops6_LogcatFragment) {
        injectOps6_LogcatFragment(ops6_LogcatFragment);
    }
}
