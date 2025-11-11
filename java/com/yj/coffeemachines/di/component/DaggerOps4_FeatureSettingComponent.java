package com.yj.coffeemachines.di.component;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.base.BaseFragment_MembersInjector;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.yj.coffeemachines.di.component.Ops4_FeatureSettingComponent;
import com.yj.coffeemachines.mvp.contract.Ops4_FeatureSettingContract;
import com.yj.coffeemachines.mvp.model.Ops4_FeatureSettingModel;
import com.yj.coffeemachines.mvp.model.Ops4_FeatureSettingModel_Factory;
import com.yj.coffeemachines.mvp.presenter.Ops4_FeatureSettingPresenter;
import com.yj.coffeemachines.mvp.presenter.Ops4_FeatureSettingPresenter_Factory;
import com.yj.coffeemachines.mvp.ui.fragment.Ops4_FeatureSettingFragment;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/* loaded from: classes.dex */
public final class DaggerOps4_FeatureSettingComponent implements Ops4_FeatureSettingComponent {
    private Provider<AppManager> appManagerProvider;
    private Provider<Application> applicationProvider;
    private Provider<Gson> gsonProvider;
    private Provider<ImageLoader> imageLoaderProvider;
    private Provider<Ops4_FeatureSettingModel> ops4_FeatureSettingModelProvider;
    private Provider<Ops4_FeatureSettingPresenter> ops4_FeatureSettingPresenterProvider;
    private Provider<IRepositoryManager> repositoryManagerProvider;
    private Provider<RxErrorHandler> rxErrorHandlerProvider;
    private Provider<Ops4_FeatureSettingContract.View> viewProvider;

    /* loaded from: classes.dex */
    private static final class Builder implements Ops4_FeatureSettingComponent.Builder {
        private AppComponent appComponent;
        private Ops4_FeatureSettingContract.View view;

        private Builder() {
        }

        @Override // com.yj.coffeemachines.di.component.Ops4_FeatureSettingComponent.Builder
        public Builder appComponent(AppComponent appComponent) {
            this.appComponent = (AppComponent) Preconditions.checkNotNull(appComponent);
            return this;
        }

        @Override // com.yj.coffeemachines.di.component.Ops4_FeatureSettingComponent.Builder
        public Ops4_FeatureSettingComponent build() {
            Preconditions.checkBuilderRequirement(this.view, Ops4_FeatureSettingContract.View.class);
            Preconditions.checkBuilderRequirement(this.appComponent, AppComponent.class);
            return new DaggerOps4_FeatureSettingComponent(this.appComponent, this.view);
        }

        @Override // com.yj.coffeemachines.di.component.Ops4_FeatureSettingComponent.Builder
        public Builder view(Ops4_FeatureSettingContract.View view) {
            this.view = (Ops4_FeatureSettingContract.View) Preconditions.checkNotNull(view);
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

    private DaggerOps4_FeatureSettingComponent(AppComponent appComponent, Ops4_FeatureSettingContract.View view) {
        initialize(appComponent, view);
    }

    public static Ops4_FeatureSettingComponent.Builder builder() {
        return new Builder();
    }

    private void initialize(AppComponent appComponent, Ops4_FeatureSettingContract.View view) {
        this.repositoryManagerProvider = new com_jess_arms_di_component_AppComponent_repositoryManager(appComponent);
        this.gsonProvider = new com_jess_arms_di_component_AppComponent_gson(appComponent);
        this.applicationProvider = new com_jess_arms_di_component_AppComponent_application(appComponent);
        this.ops4_FeatureSettingModelProvider = DoubleCheck.provider(Ops4_FeatureSettingModel_Factory.create(this.repositoryManagerProvider, this.gsonProvider, this.applicationProvider));
        this.viewProvider = InstanceFactory.create(view);
        this.rxErrorHandlerProvider = new com_jess_arms_di_component_AppComponent_rxErrorHandler(appComponent);
        this.imageLoaderProvider = new com_jess_arms_di_component_AppComponent_imageLoader(appComponent);
        this.appManagerProvider = new com_jess_arms_di_component_AppComponent_appManager(appComponent);
        this.ops4_FeatureSettingPresenterProvider = DoubleCheck.provider(Ops4_FeatureSettingPresenter_Factory.create(this.ops4_FeatureSettingModelProvider, this.viewProvider, this.rxErrorHandlerProvider, this.applicationProvider, this.imageLoaderProvider, this.appManagerProvider));
    }

    private Ops4_FeatureSettingFragment injectOps4_FeatureSettingFragment(Ops4_FeatureSettingFragment ops4_FeatureSettingFragment) {
        BaseFragment_MembersInjector.injectMPresenter(ops4_FeatureSettingFragment, this.ops4_FeatureSettingPresenterProvider.get());
        return ops4_FeatureSettingFragment;
    }

    @Override // com.yj.coffeemachines.di.component.Ops4_FeatureSettingComponent
    public void inject(Ops4_FeatureSettingFragment ops4_FeatureSettingFragment) {
        injectOps4_FeatureSettingFragment(ops4_FeatureSettingFragment);
    }
}
